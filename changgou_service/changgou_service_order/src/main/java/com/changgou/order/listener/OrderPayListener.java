package com.changgou.order.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.order.config.RabbitMQConfig;
import com.changgou.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderPayListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_PAY)
    public void receivePayMessage(String message){
        System.out.println("接收到了订单支付的消息:"+message);

        Map map = JSON.parseObject(message, Map.class);

        //调用业务层,完成订单数据库的修改
        orderService.updatePayStatus((String)map.get("orderId"),(String)map.get("transactionId"));
    }
}
