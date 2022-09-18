package com.changgou.order.service.impl;

import com.changgou.order.dao.ReturnCauseMapper;
import com.changgou.order.service.ReturnCauseService;
import com.changgou.order.pojo.ReturnCause;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ReturnCauseServiceImpl implements ReturnCauseService {

    @Autowired
    private ReturnCauseMapper returnCauseMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<ReturnCause> findAll() {
        return returnCauseMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public ReturnCause findById(Integer id){
        return  returnCauseMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param returnCause
     */
    @Override
    public void add(ReturnCause returnCause){
        returnCauseMapper.insert(returnCause);
    }


    /**
     * 修改
     * @param returnCause
     */
    @Override
    public void update(ReturnCause returnCause){
        returnCauseMapper.updateByPrimaryKey(returnCause);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        returnCauseMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<ReturnCause> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return returnCauseMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<ReturnCause> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<ReturnCause>)returnCauseMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<ReturnCause> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<ReturnCause>)returnCauseMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(ReturnCause.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 原因
            if(searchMap.get("cause")!=null && !"".equals(searchMap.get("cause"))){
                criteria.andLike("cause","%"+searchMap.get("cause")+"%");
           	}
            // 是否启用
            if(searchMap.get("status")!=null && !"".equals(searchMap.get("status"))){
                criteria.andLike("status","%"+searchMap.get("status")+"%");
           	}

            // ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 排序
            if(searchMap.get("seq")!=null ){
                criteria.andEqualTo("seq",searchMap.get("seq"));
            }

        }
        return example;
    }

}
