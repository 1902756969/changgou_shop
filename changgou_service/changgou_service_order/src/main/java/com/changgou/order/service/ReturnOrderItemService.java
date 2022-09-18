package com.changgou.order.service;

import com.changgou.order.pojo.ReturnOrderItem;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ReturnOrderItemService {

    /***
     * 查询所有
     * @return
     */
    List<ReturnOrderItem> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    ReturnOrderItem findById(String id);

    /***
     * 新增
     * @param returnOrderItem
     */
    void add(ReturnOrderItem returnOrderItem);

    /***
     * 修改
     * @param returnOrderItem
     */
    void update(ReturnOrderItem returnOrderItem);

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
    List<ReturnOrderItem> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<ReturnOrderItem> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<ReturnOrderItem> findPage(Map<String, Object> searchMap, int page, int size);




}
