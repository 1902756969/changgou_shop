package com.changgou.order.service.impl;

import com.changgou.order.dao.ReturnOrderItemMapper;
import com.changgou.order.service.ReturnOrderItemService;
import com.changgou.order.pojo.ReturnOrderItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ReturnOrderItemServiceImpl implements ReturnOrderItemService {

    @Autowired
    private ReturnOrderItemMapper returnOrderItemMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<ReturnOrderItem> findAll() {
        return returnOrderItemMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public ReturnOrderItem findById(String id){
        return  returnOrderItemMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param returnOrderItem
     */
    @Override
    public void add(ReturnOrderItem returnOrderItem){
        returnOrderItemMapper.insert(returnOrderItem);
    }


    /**
     * 修改
     * @param returnOrderItem
     */
    @Override
    public void update(ReturnOrderItem returnOrderItem){
        returnOrderItemMapper.updateByPrimaryKey(returnOrderItem);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        returnOrderItemMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<ReturnOrderItem> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return returnOrderItemMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<ReturnOrderItem> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<ReturnOrderItem>)returnOrderItemMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<ReturnOrderItem> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<ReturnOrderItem>)returnOrderItemMapper.selectByExample(example);
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(ReturnOrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // ID
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
           	}
            // SPU_ID
            if(searchMap.get("spu_id")!=null && !"".equals(searchMap.get("spu_id"))){
                criteria.andLike("spu_id","%"+searchMap.get("spu_id")+"%");
           	}
            // SKU_ID
            if(searchMap.get("sku_id")!=null && !"".equals(searchMap.get("sku_id"))){
                criteria.andLike("sku_id","%"+searchMap.get("sku_id")+"%");
           	}
            // 订单ID
            if(searchMap.get("order_id")!=null && !"".equals(searchMap.get("order_id"))){
                criteria.andLike("order_id","%"+searchMap.get("order_id")+"%");
           	}
            // 订单明细ID
            if(searchMap.get("order_item_id")!=null && !"".equals(searchMap.get("order_item_id"))){
                criteria.andLike("order_item_id","%"+searchMap.get("order_item_id")+"%");
           	}
            // 退货订单ID
            if(searchMap.get("return_order_id")!=null && !"".equals(searchMap.get("return_order_id"))){
                criteria.andLike("return_order_id","%"+searchMap.get("return_order_id")+"%");
           	}
            // 标题
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
           	}
            // 图片地址
            if(searchMap.get("image")!=null && !"".equals(searchMap.get("image"))){
                criteria.andLike("image","%"+searchMap.get("image")+"%");
           	}

            // 分类ID
            if(searchMap.get("categoryId")!=null ){
                criteria.andEqualTo("categoryId",searchMap.get("categoryId"));
            }
            // 单价
            if(searchMap.get("price")!=null ){
                criteria.andEqualTo("price",searchMap.get("price"));
            }
            // 数量
            if(searchMap.get("num")!=null ){
                criteria.andEqualTo("num",searchMap.get("num"));
            }
            // 总金额
            if(searchMap.get("money")!=null ){
                criteria.andEqualTo("money",searchMap.get("money"));
            }
            // 支付金额
            if(searchMap.get("payMoney")!=null ){
                criteria.andEqualTo("payMoney",searchMap.get("payMoney"));
            }
            // 重量
            if(searchMap.get("weight")!=null ){
                criteria.andEqualTo("weight",searchMap.get("weight"));
            }

        }
        return example;
    }

}
