package com.changgou.system.service;


import com.changgou.system.pojo.Resource;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ResourceService {

    /***
     * 查询所有
     * @return
     */
    List<Resource> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Resource findById(Integer id);

    /***
     * 新增
     * @param resource
     */
    void add(Resource resource);

    /***
     * 修改
     * @param resource
     */
    void update(Resource resource);

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
    List<Resource> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Resource> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Resource> findPage(Map<String, Object> searchMap, int page, int size);




}
