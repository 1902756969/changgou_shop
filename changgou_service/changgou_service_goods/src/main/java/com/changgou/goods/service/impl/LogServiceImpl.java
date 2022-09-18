package com.changgou.goods.service.impl;

import com.changgou.goods.dao.LogMapper;
import com.changgou.goods.service.LogService;
import com.changgou.goods.pojo.Log;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Log> findAll() {
        return logMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Log findById(Long id){
        return  logMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param log
     */
    @Override
    public void add(Log log){
        logMapper.insert(log);
    }


    /**
     * 修改
     * @param log
     */
    @Override
    public void update(Log log){
        logMapper.updateByPrimaryKey(log);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        logMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Log> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return logMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Log> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Log>)logMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Log> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Log>)logMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Log.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // xid
            if(searchMap.get("xid")!=null && !"".equals(searchMap.get("xid"))){
                criteria.andLike("xid","%"+searchMap.get("xid")+"%");
           	}
            // ext
            if(searchMap.get("ext")!=null && !"".equals(searchMap.get("ext"))){
                criteria.andLike("ext","%"+searchMap.get("ext")+"%");
           	}

            // log_status
            if(searchMap.get("logStatus")!=null ){
                criteria.andEqualTo("logStatus",searchMap.get("logStatus"));
            }

        }
        return example;
    }

}
