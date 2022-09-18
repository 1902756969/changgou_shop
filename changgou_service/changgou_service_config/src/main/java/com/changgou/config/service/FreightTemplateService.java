package com.changgou.config.service;

import com.changgou.config.pojo.FreightTemplate;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface FreightTemplateService {

    /***
     * 查询所有
     * @return
     */
    List<FreightTemplate> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    FreightTemplate findById(Integer id);

    /***
     * 新增
     * @param freightTemplate
     */
    void add(FreightTemplate freightTemplate);

    /***
     * 修改
     * @param freightTemplate
     */
    void update(FreightTemplate freightTemplate);

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
    List<FreightTemplate> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<FreightTemplate> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<FreightTemplate> findPage(Map<String, Object> searchMap, int page, int size);




}
