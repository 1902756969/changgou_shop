package com.changgou.goods.service;

import com.changgou.goods.pojo.Template;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface TemplateService {

    /***
     * 查询所有
     * @return
     */
    List<Template> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Template findById(Integer id);

    /***
     * 新增
     * @param template
     */
    void add(Template template);

    /***
     * 修改
     * @param template
     */
    void update(Template template);

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
    List<Template> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Template> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Template> findPage(Map<String, Object> searchMap, int page, int size);




}
