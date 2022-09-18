package com.changgou.system.service;


import com.changgou.system.pojo.Role;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface RoleService {

    /***
     * 查询所有
     * @return
     */
    List<Role> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Role findById(Integer id);

    /***
     * 新增
     * @param role
     */
    void add(Role role);

    /***
     * 修改
     * @param role
     */
    void update(Role role);

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
    List<Role> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Role> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Role> findPage(Map<String, Object> searchMap, int page, int size);




}
