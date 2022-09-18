package com.changgou.order.service;

import com.changgou.order.pojo.ReturnCause;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ReturnCauseService {

    /***
     * 查询所有
     * @return
     */
    List<ReturnCause> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    ReturnCause findById(Integer id);

    /***
     * 新增
     * @param returnCause
     */
    void add(ReturnCause returnCause);

    /***
     * 修改
     * @param returnCause
     */
    void update(ReturnCause returnCause);

    /***
     * 删除
     * @param id
     */
    void delete(Integer id);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<ReturnCause> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<ReturnCause> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<ReturnCause> findPage(Map<String, Object> searchMap, int page, int size);




}
