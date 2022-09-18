package com.changgou.order.service;

import com.changgou.order.pojo.ReturnOrder;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ReturnOrderService {

    /***
     * 查询所有
     * @return
     */
    List<ReturnOrder> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    ReturnOrder findById(String id);

    /***
     * 新增
     * @param returnOrder
     */
    void add(ReturnOrder returnOrder);

    /***
     * 修改
     * @param returnOrder
     */
    void update(ReturnOrder returnOrder);

    /***
     * 删除
     * @param id
     */
    void delete(String id);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<ReturnOrder> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<ReturnOrder> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<ReturnOrder> findPage(Map<String, Object> searchMap, int page, int size);




}
