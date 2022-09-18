package com.changgou.goods.service;

import com.changgou.goods.pojo.Spec;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SpecService {

    /***
     * 查询所有
     * @return
     */
    List<Spec> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Spec findById(Integer id);

    /***
     * 新增
     * @param spec
     */
    void add(Spec spec);

    /***
     * 修改
     * @param spec
     */
    void update(Spec spec);

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
    List<Spec> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Spec> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Spec> findPage(Map<String, Object> searchMap, int page, int size);

    List<Map> findSpecListByCategoryName(String categoryName);


}
