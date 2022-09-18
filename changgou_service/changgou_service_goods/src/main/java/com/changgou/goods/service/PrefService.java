package com.changgou.goods.service;

import com.changgou.goods.pojo.Pref;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface PrefService {

    /***
     * 查询所有
     * @return
     */
    List<Pref> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Pref findById(Integer id);

    /***
     * 新增
     * @param pref
     */
    void add(Pref pref);

    /***
     * 修改
     * @param pref
     */
    void update(Pref pref);

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
    List<Pref> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Pref> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Pref> findPage(Map<String, Object> searchMap, int page, int size);




}
