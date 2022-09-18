package com.changgou.goods.service;

import com.changgou.goods.pojo.Sku;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SkuService {

    /***
     * 查询所有
     * @return
     */
    List<Sku> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Sku findById(String id);

    /***
     * 新增
     * @param sku
     */
    void add(Sku sku);

    /***
     * 修改
     * @param sku
     */
    void update(Sku sku);

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
    List<Sku> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Sku> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Sku> findPage(Map<String, Object> searchMap, int page, int size);

    void decrCount(String username);

    //回滚库存
    void resumeStockNum(String skuId,Integer num);


}
