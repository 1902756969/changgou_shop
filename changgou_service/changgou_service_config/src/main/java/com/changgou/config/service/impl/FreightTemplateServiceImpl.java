package com.changgou.config.service.impl;

import com.changgou.config.dao.FreightTemplateMapper;
import com.changgou.config.service.FreightTemplateService;
import com.changgou.config.pojo.FreightTemplate;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class FreightTemplateServiceImpl implements FreightTemplateService {

    @Autowired
    private FreightTemplateMapper freightTemplateMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<FreightTemplate> findAll() {
        return freightTemplateMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public FreightTemplate findById(Integer id){
        return  freightTemplateMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param freightTemplate
     */
    @Override
    public void add(FreightTemplate freightTemplate){
        freightTemplateMapper.insert(freightTemplate);
    }


    /**
     * 修改
     * @param freightTemplate
     */
    @Override
    public void update(FreightTemplate freightTemplate){
        freightTemplateMapper.updateByPrimaryKey(freightTemplate);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        freightTemplateMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<FreightTemplate> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return freightTemplateMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<FreightTemplate> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<FreightTemplate>)freightTemplateMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<FreightTemplate> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<FreightTemplate>)freightTemplateMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(FreightTemplate.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 模板名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}
            // 计费方式
            if(searchMap.get("type")!=null && !"".equals(searchMap.get("type"))){
                criteria.andLike("type","%"+searchMap.get("type")+"%");
           	}

            // ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
