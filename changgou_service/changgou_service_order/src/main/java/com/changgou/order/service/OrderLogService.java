package com.changgou.order.service;

import com.changgou.order.pojo.OrderLog;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface OrderLogService {

    /***
     * 查询所有
     * @return
     */
    List<OrderLog> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    OrderLog findById(String id);

    /***
     * 新增
     * @param orderLog
     */
    void add(OrderLog orderLog);

    /***
     * 修改
     * @param orderLog
     */
    void update(OrderLog orderLog);

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
    List<OrderLog> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<OrderLog> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<OrderLog> findPage(Map<String, Object> searchMap, int page, int size);




}
