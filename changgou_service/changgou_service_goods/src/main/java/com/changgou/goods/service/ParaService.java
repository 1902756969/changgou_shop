package com.changgou.goods.service;

import com.changgou.goods.pojo.Para;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ParaService {

    /***
     * 查询所有
     * @return
     */
    List<Para> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Para findById(Integer id);

    /***
     * 新增
     * @param para
     */
    void add(Para para);

    /***
     * 修改
     * @param para
     */
    void update(Para para);

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
    List<Para> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Para> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Para> findPage(Map<String, Object> searchMap, int page, int size);




}
