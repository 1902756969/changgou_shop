package com.changgou.system.service.impl;

import com.changgou.system.dao.ResourceMapper;
import com.changgou.system.pojo.Resource;
import com.changgou.system.service.ResourceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Resource> findAll() {
        return resourceMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Resource findById(Integer id){
        return  resourceMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param resource
     */
    @Override
    public void add(Resource resource){
        resourceMapper.insert(resource);
    }


    /**
     * 修改
     * @param resource
     */
    @Override
    public void update(Resource resource){
        resourceMapper.updateByPrimaryKey(resource);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        resourceMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Resource> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return resourceMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Resource> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Resource>)resourceMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Resource> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Resource>)resourceMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Resource.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // res_key
            if(searchMap.get("res_key")!=null && !"".equals(searchMap.get("res_key"))){
                criteria.andLike("res_key","%"+searchMap.get("res_key")+"%");
           	}
            // res_name
            if(searchMap.get("res_name")!=null && !"".equals(searchMap.get("res_name"))){
                criteria.andLike("res_name","%"+searchMap.get("res_name")+"%");
           	}

            // id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // parent_id
            if(searchMap.get("parentId")!=null ){
                criteria.andEqualTo("parentId",searchMap.get("parentId"));
            }

        }
        return example;
    }

}
