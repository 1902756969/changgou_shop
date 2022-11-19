package com.changgou.system.service;


import com.changgou.system.pojo.LoginLog;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface LoginLogService {

    /***
     * 查询所有
     * @return
     */
    List<LoginLog> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    LoginLog findById(Integer id);

    /***
     * 新增
     * @param loginLog
     */
    void add(LoginLog loginLog);

    /***
     * 修改
     * @param loginLog
     */
    void update(LoginLog loginLog);

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
    List<LoginLog> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<LoginLog> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<LoginLog> findPage(Map<String, Object> searchMap, int page, int size);




}
