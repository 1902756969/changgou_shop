package com.changgou.user.service.impl;

import com.changgou.user.dao.AreasMapper;
import com.changgou.user.service.AreasService;
import com.changgou.user.pojo.Areas;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class AreasServiceImpl implements AreasService {

    @Autowired
    private AreasMapper areasMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Areas> findAll() {
        return areasMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param areaid
     * @return
     */
    @Override
    public Areas findById(String areaid){
        return  areasMapper.selectByPrimaryKey(areaid);
    }


    /**
     * 增加
     * @param areas
     */
    @Override
    public void add(Areas areas){
        areasMapper.insert(areas);
    }


    /**
     * 修改
     * @param areas
     */
    @Override
    public void update(Areas areas){
        areasMapper.updateByPrimaryKey(areas);
    }

    /**
     * 删除
     * @param areaid
     */
    @Override
    public void delete(String areaid){
        areasMapper.deleteByPrimaryKey(areaid);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Areas> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return areasMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Areas> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Areas>)areasMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Areas> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Areas>)areasMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Areas.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 区域ID
            if(searchMap.get("areaid")!=null && !"".equals(searchMap.get("areaid"))){
                criteria.andLike("areaid","%"+searchMap.get("areaid")+"%");
           	}
            // 区域名称
            if(searchMap.get("area")!=null && !"".equals(searchMap.get("area"))){
                criteria.andLike("area","%"+searchMap.get("area")+"%");
           	}
            // 城市ID
            if(searchMap.get("cityid")!=null && !"".equals(searchMap.get("cityid"))){
                criteria.andLike("cityid","%"+searchMap.get("cityid")+"%");
           	}


        }
        return example;
    }

}
