package com.changgou.user.service;

import com.changgou.user.pojo.Address;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface AddressService {

    /***
     * 查询所有
     * @return
     */
    List<Address> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Address findById(Integer id);

    /***
     * 新增
     * @param address
     */
    void add(Address address);

    /***
     * 修改
     * @param address
     */
    void update(Address address);

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
    List<Address> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Address> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Address> findPage(Map<String, Object> searchMap, int page, int size);

    //根据当前的登录人名称获取与之相关的收件人地址信息
    List<Address> list(String username);


}
