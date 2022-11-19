package com.changgou.consumer.service.impl;

import com.changgou.consumer.dao.SeckillGoodsMapper;
import com.changgou.consumer.dao.SeckillOrderMapper;
import com.changgou.consumer.service.SecKillOrderService;
import com.changgou.seckill.pojo.SeckillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecKillOrderServiceImpl implements SecKillOrderService {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Override
    @Transactional
    public int createOrder(SeckillOrder seckillOrder) {
        //同步mysql中的数据
        //1.扣减秒杀商品的库存
        int result = seckillGoodsMapper.updateStockCount(seckillOrder.getSeckillId());
        if (result <= 0){
            return 0;
        }
        //2.新增秒杀订单
        result = seckillOrderMapper.insertSelective(seckillOrder);
        if (result <= 0){
            return 0;
        }
        return 1;
    }
}
