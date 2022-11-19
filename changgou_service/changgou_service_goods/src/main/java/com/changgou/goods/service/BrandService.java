package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {

    /***
     * 查询所有
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /***
     * 新增
     * @param brand
     */
    void add(Brand brand);

    /***
     * 修改
     * @param brand
     */
    void update(Brand brand);

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
    List<Brand> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Brand> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Brand> findPage(Map<String, Object> searchMap, int page, int size);

    /**
     * 根据分类名称查询品牌列表
     * @param categoryName
     * @return
     */
    public List<Map> findBrandListByCategoryName(String categoryName);


}
