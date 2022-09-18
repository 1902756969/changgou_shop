package com.changgou.user.service;

import com.changgou.user.pojo.Areas;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface AreasService {

    /***
     * 查询所有
     * @return
     */
    List<Areas> findAll();

    /**
     * 根据ID查询
     * @param areaid
     * @return
     */
    Areas findById(String areaid);

    /***
     * 新增
     * @param areas
     */
    void add(Areas areas);

    /***
     * 修改
     * @param areas
     */
    void update(Areas areas);

    /***
     * 删除
     * @param id
     */
    void delete(String areaid);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<Areas> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Areas> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Areas> findPage(Map<String, Object> searchMap, int page, int size);




}
