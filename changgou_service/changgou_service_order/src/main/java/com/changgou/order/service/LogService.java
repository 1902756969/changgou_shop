package com.changgou.order.service;

import com.changgou.order.pojo.Log;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface LogService {

    /***
     * 查询所有
     * @return
     */
    List<Log> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Log findById(Long id);

    /***
     * 新增
     * @param log
     */
    void add(Log log);

    /***
     * 修改
     * @param log
     */
    void update(Log log);

    /***
     * 删除
     * @param id
     */
    void delete(Long id);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<Log> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Log> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Log> findPage(Map<String, Object> searchMap, int page, int size);




}
