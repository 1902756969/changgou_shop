package com.changgou.order.service;

import com.changgou.order.pojo.OrderItem;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface OrderItemService {

    /***
     * 查询所有
     * @return
     */
    List<OrderItem> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    OrderItem findById(String id);

    /***
     * 新增
     * @param orderItem
     */
    void add(OrderItem orderItem);

    /***
     * 修改
     * @param orderItem
     */
    void update(OrderItem orderItem);

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
    List<OrderItem> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<OrderItem> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<OrderItem> findPage(Map<String, Object> searchMap, int page, int size);




}
