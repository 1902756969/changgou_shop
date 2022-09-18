package com.changgou.seckill.service;

public interface SecKillOrderService {

    //秒杀下单
    boolean add(Long id,String time,String username);
}
