package com.changgou.system.service.impl;

import com.changgou.system.dao.MenuMapper;
import com.changgou.system.pojo.Menu;
import com.changgou.system.service.MenuService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Menu> findAll() {
        return menuMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Menu findById(String id){
        return  menuMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param menu
     */
    @Override
    public void add(Menu menu){
        menuMapper.insert(menu);
    }


    /**
     * 修改
     * @param menu
     */
    @Override
    public void update(Menu menu){
        menuMapper.updateByPrimaryKey(menu);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        menuMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Menu> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return menuMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Menu> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Menu>)menuMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Menu> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Menu>)menuMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 菜单ID
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
           	}
            // 菜单名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}
            // 图标
            if(searchMap.get("icon")!=null && !"".equals(searchMap.get("icon"))){
                criteria.andLike("icon","%"+searchMap.get("icon")+"%");
           	}
            // URL
            if(searchMap.get("url")!=null && !"".equals(searchMap.get("url"))){
                criteria.andLike("url","%"+searchMap.get("url")+"%");
           	}
            // 上级菜单ID
            if(searchMap.get("parent_id")!=null && !"".equals(searchMap.get("parent_id"))){
                criteria.andLike("parent_id","%"+searchMap.get("parent_id")+"%");
           	}


        }
        return example;
    }

}
