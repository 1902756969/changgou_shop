package com.changgou.system.service.impl;

import com.changgou.system.dao.RoleMapper;
import com.changgou.system.pojo.Role;
import com.changgou.system.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Role findById(Integer id){
        return  roleMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param role
     */
    @Override
    public void add(Role role){
        roleMapper.insert(role);
    }


    /**
     * 修改
     * @param role
     */
    @Override
    public void update(Role role){
        roleMapper.updateByPrimaryKey(role);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        roleMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Role> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return roleMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Role> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Role>)roleMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Role> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Role>)roleMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 角色名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}

            // ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
