package com.changgou.order.service.impl;

import com.changgou.order.dao.OrderConfigMapper;
import com.changgou.order.service.OrderConfigService;
import com.changgou.order.pojo.OrderConfig;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class OrderConfigServiceImpl implements OrderConfigService {

    @Autowired
    private OrderConfigMapper orderConfigMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<OrderConfig> findAll() {
        return orderConfigMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public OrderConfig findById(Integer id){
        return  orderConfigMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param orderConfig
     */
    @Override
    public void add(OrderConfig orderConfig){
        orderConfigMapper.insert(orderConfig);
    }


    /**
     * 修改
     * @param orderConfig
     */
    @Override
    public void update(OrderConfig orderConfig){
        orderConfigMapper.updateByPrimaryKey(orderConfig);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        orderConfigMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<OrderConfig> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return orderConfigMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<OrderConfig> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<OrderConfig>)orderConfigMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<OrderConfig> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<OrderConfig>)orderConfigMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(OrderConfig.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){

            // ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 正常订单超时时间（分）
            if(searchMap.get("orderTimeout")!=null ){
                criteria.andEqualTo("orderTimeout",searchMap.get("orderTimeout"));
            }
            // 秒杀订单超时时间（分）
            if(searchMap.get("seckillTimeout")!=null ){
                criteria.andEqualTo("seckillTimeout",searchMap.get("seckillTimeout"));
            }
            // 自动收货（天）
            if(searchMap.get("takeTimeout")!=null ){
                criteria.andEqualTo("takeTimeout",searchMap.get("takeTimeout"));
            }
            // 售后期限
            if(searchMap.get("serviceTimeout")!=null ){
                criteria.andEqualTo("serviceTimeout",searchMap.get("serviceTimeout"));
            }
            // 自动五星好评
            if(searchMap.get("commentTimeout")!=null ){
                criteria.andEqualTo("commentTimeout",searchMap.get("commentTimeout"));
            }

        }
        return example;
    }

}
