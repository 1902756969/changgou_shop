package com.changgou.system.service;


import com.changgou.system.pojo.Menu;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface MenuService {

    /***
     * 查询所有
     * @return
     */
    List<Menu> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Menu findById(String id);

    /***
     * 新增
     * @param menu
     */
    void add(Menu menu);

    /***
     * 修改
     * @param menu
     */
    void update(Menu menu);

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
    List<Menu> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Menu> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Menu> findPage(Map<String, Object> searchMap, int page, int size);




}
