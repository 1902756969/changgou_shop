package com.changgou.system.service.impl;

import com.changgou.system.dao.LoginLogMapper;
import com.changgou.system.pojo.LoginLog;
import com.changgou.system.service.LoginLogService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<LoginLog> findAll() {
        return loginLogMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public LoginLog findById(Integer id){
        return  loginLogMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param loginLog
     */
    @Override
    public void add(LoginLog loginLog){
        loginLogMapper.insert(loginLog);
    }


    /**
     * 修改
     * @param loginLog
     */
    @Override
    public void update(LoginLog loginLog){
        loginLogMapper.updateByPrimaryKey(loginLog);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        loginLogMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<LoginLog> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return loginLogMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<LoginLog> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<LoginLog>)loginLogMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<LoginLog> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<LoginLog>)loginLogMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(LoginLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // login_name
            if(searchMap.get("login_name")!=null && !"".equals(searchMap.get("login_name"))){
                criteria.andLike("login_name","%"+searchMap.get("login_name")+"%");
           	}
            // ip
            if(searchMap.get("ip")!=null && !"".equals(searchMap.get("ip"))){
                criteria.andLike("ip","%"+searchMap.get("ip")+"%");
           	}
            // browser_name
            if(searchMap.get("browser_name")!=null && !"".equals(searchMap.get("browser_name"))){
                criteria.andLike("browser_name","%"+searchMap.get("browser_name")+"%");
           	}
            // 地区
            if(searchMap.get("location")!=null && !"".equals(searchMap.get("location"))){
                criteria.andLike("location","%"+searchMap.get("location")+"%");
           	}

            // id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
