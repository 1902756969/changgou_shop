package com.changgou.user.service.impl;

import com.changgou.user.dao.CitiesMapper;
import com.changgou.user.service.CitiesService;
import com.changgou.user.pojo.Cities;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private CitiesMapper citiesMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Cities> findAll() {
        return citiesMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param cityid
     * @return
     */
    @Override
    public Cities findById(String cityid){
        return  citiesMapper.selectByPrimaryKey(cityid);
    }


    /**
     * 增加
     * @param cities
     */
    @Override
    public void add(Cities cities){
        citiesMapper.insert(cities);
    }


    /**
     * 修改
     * @param cities
     */
    @Override
    public void update(Cities cities){
        citiesMapper.updateByPrimaryKey(cities);
    }

    /**
     * 删除
     * @param cityid
     */
    @Override
    public void delete(String cityid){
        citiesMapper.deleteByPrimaryKey(cityid);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Cities> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return citiesMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Cities> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Cities>)citiesMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Cities> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Cities>)citiesMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Cities.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 城市ID
            if(searchMap.get("cityid")!=null && !"".equals(searchMap.get("cityid"))){
                criteria.andLike("cityid","%"+searchMap.get("cityid")+"%");
           	}
            // 城市名称
            if(searchMap.get("city")!=null && !"".equals(searchMap.get("city"))){
                criteria.andLike("city","%"+searchMap.get("city")+"%");
           	}
            // 省份ID
            if(searchMap.get("provinceid")!=null && !"".equals(searchMap.get("provinceid"))){
                criteria.andLike("provinceid","%"+searchMap.get("provinceid")+"%");
           	}


        }
        return example;
    }

}
