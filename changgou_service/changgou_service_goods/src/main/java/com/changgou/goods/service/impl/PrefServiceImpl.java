package com.changgou.goods.service.impl;

import com.changgou.goods.dao.PrefMapper;
import com.changgou.goods.service.PrefService;
import com.changgou.goods.pojo.Pref;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class PrefServiceImpl implements PrefService {

    @Autowired
    private PrefMapper prefMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Pref> findAll() {
        return prefMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Pref findById(Integer id){
        return  prefMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param pref
     */
    @Override
    public void add(Pref pref){
        prefMapper.insert(pref);
    }


    /**
     * 修改
     * @param pref
     */
    @Override
    public void update(Pref pref){
        prefMapper.updateByPrimaryKey(pref);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        prefMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Pref> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return prefMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Pref> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Pref>)prefMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Pref> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Pref>)prefMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Pref.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 类型
            if(searchMap.get("type")!=null && !"".equals(searchMap.get("type"))){
                criteria.andLike("type","%"+searchMap.get("type")+"%");
           	}
            // 状态
            if(searchMap.get("state")!=null && !"".equals(searchMap.get("state"))){
                criteria.andLike("state","%"+searchMap.get("state")+"%");
           	}

            // ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 分类ID
            if(searchMap.get("cateId")!=null ){
                criteria.andEqualTo("cateId",searchMap.get("cateId"));
            }
            // 消费金额
            if(searchMap.get("buyMoney")!=null ){
                criteria.andEqualTo("buyMoney",searchMap.get("buyMoney"));
            }
            // 优惠金额
            if(searchMap.get("preMoney")!=null ){
                criteria.andEqualTo("preMoney",searchMap.get("preMoney"));
            }

        }
        return example;
    }

}
