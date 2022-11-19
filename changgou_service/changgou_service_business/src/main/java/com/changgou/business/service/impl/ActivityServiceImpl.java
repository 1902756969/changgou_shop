package com.changgou.business.service.impl;

import com.changgou.business.dao.ActivityMapper;
import com.changgou.business.service.ActivityService;
import com.changgou.business.pojo.Activity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Activity> findAll() {
        return activityMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Activity findById(Integer id){
        return  activityMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param activity
     */
    @Override
    public void add(Activity activity){
        activityMapper.insert(activity);
    }


    /**
     * 修改
     * @param activity
     */
    @Override
    public void update(Activity activity){
        activityMapper.updateByPrimaryKey(activity);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        activityMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Activity> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return activityMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Activity> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Activity>)activityMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Activity> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Activity>)activityMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 活动标题
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
           	}
            // 状态
            if(searchMap.get("status")!=null && !"".equals(searchMap.get("status"))){
                criteria.andLike("status","%"+searchMap.get("status")+"%");
           	}
            // 活动内容
            if(searchMap.get("content")!=null && !"".equals(searchMap.get("content"))){
                criteria.andLike("content","%"+searchMap.get("content")+"%");
           	}

            // ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
