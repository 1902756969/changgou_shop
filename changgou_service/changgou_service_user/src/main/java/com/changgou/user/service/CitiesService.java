package com.changgou.user.service;

import com.changgou.user.pojo.Cities;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CitiesService {

    /***
     * 查询所有
     * @return
     */
    List<Cities> findAll();

    /**
     * 根据ID查询
     * @param cityid
     * @return
     */
    Cities findById(String cityid);

    /***
     * 新增
     * @param cities
     */
    void add(Cities cities);

    /***
     * 修改
     * @param cities
     */
    void update(Cities cities);

    /***
     * 删除
     * @param id
     */
    void delete(String cityid);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<Cities> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Cities> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Cities> findPage(Map<String, Object> searchMap, int page, int size);




}
