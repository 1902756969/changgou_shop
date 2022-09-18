package com.changgou.business.service;

import com.changgou.business.pojo.Activity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    /***
     * 查询所有
     * @return
     */
    List<Activity> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Activity findById(Integer id);

    /***
     * 新增
     * @param activity
     */
    void add(Activity activity);

    /***
     * 修改
     * @param activity
     */
    void update(Activity activity);

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
    List<Activity> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Activity> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Activity> findPage(Map<String, Object> searchMap, int page, int size);




}
