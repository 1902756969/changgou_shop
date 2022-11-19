package com.changgou.order.service.impl;

import com.changgou.order.dao.OrderLogMapper;
import com.changgou.order.service.OrderLogService;
import com.changgou.order.pojo.OrderLog;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class OrderLogServiceImpl implements OrderLogService {

    @Autowired
    private OrderLogMapper orderLogMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<OrderLog> findAll() {
        return orderLogMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public OrderLog findById(String id){
        return  orderLogMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param orderLog
     */
    @Override
    public void add(OrderLog orderLog){
        orderLogMapper.insert(orderLog);
    }


    /**
     * 修改
     * @param orderLog
     */
    @Override
    public void update(OrderLog orderLog){
        orderLogMapper.updateByPrimaryKey(orderLog);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        orderLogMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<OrderLog> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return orderLogMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<OrderLog> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<OrderLog>)orderLogMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<OrderLog> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<OrderLog>)orderLogMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(OrderLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // ID
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
           	}
            // 操作员
            if(searchMap.get("operater")!=null && !"".equals(searchMap.get("operater"))){
                criteria.andLike("operater","%"+searchMap.get("operater")+"%");
           	}
            // 订单ID
            if(searchMap.get("order_id")!=null && !"".equals(searchMap.get("order_id"))){
                criteria.andLike("order_id","%"+searchMap.get("order_id")+"%");
           	}
            // 订单状态
            if(searchMap.get("order_status")!=null && !"".equals(searchMap.get("order_status"))){
                criteria.andLike("order_status","%"+searchMap.get("order_status")+"%");
           	}
            // 付款状态
            if(searchMap.get("pay_status")!=null && !"".equals(searchMap.get("pay_status"))){
                criteria.andLike("pay_status","%"+searchMap.get("pay_status")+"%");
           	}
            // 发货状态
            if(searchMap.get("consign_status")!=null && !"".equals(searchMap.get("consign_status"))){
                criteria.andLike("consign_status","%"+searchMap.get("consign_status")+"%");
           	}
            // 备注
            if(searchMap.get("remarks")!=null && !"".equals(searchMap.get("remarks"))){
                criteria.andLike("remarks","%"+searchMap.get("remarks")+"%");
           	}


        }
        return example;
    }

}
