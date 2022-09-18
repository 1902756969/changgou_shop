package com.changgou.goods.service;

import com.changgou.goods.pojo.Category;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    /***
     * 查询所有
     * @return
     */
    List<Category> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Category findById(Integer id);

    /***
     * 新增
     * @param category
     */
    void add(Category category);

    /***
     * 修改
     * @param category
     */
    void update(Category category);

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
    List<Category> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Category> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Category> findPage(Map<String, Object> searchMap, int page, int size);




}
