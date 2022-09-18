package com.changgou.consumer.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.consumer.config.RabbitMQConfig;
import com.changgou.consumer.service.SecKillOrderService;
import com.changgou.seckill.pojo.SeckillOrder;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsumerListener {

    @Autowired
    private SecKillOrderService secKillOrderService;

    @RabbitListener(queues = RabbitMQConfig.SECKILL_ORDER_QUEUE)
    public void receiveSecKillOrderMessage(Message message, Channel channel){
        //设置预抓取总数
        try {
            channel.basicQos(300);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //1.转换消息格式
        SeckillOrder seckillOrder = JSON.parseObject(message.getBody(), SeckillOrder.class);

        //2.基于业务层完成同步mysql的操作
        int result = secKillOrderService.createOrder(seckillOrder);
        if (result>0){
            //同步mysql成功
            //向消息服务器返回成功通知
            try {
                /**
                 * 第一个参数:消息的唯一标识
                 * 第二个参数:是否开启批处理
                 */
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //同步mysql失败
            //向消息服务器返回失败通知
            try {
                /**
                 * 第一个参数:消息的唯一标识
                 * 第二个参数: true所有消费者都会拒绝这个消息,false只有当前消费者拒绝
                 * 第三个参数:true当前消息会进入到死信队列(延迟消息队列),false当前的消息会重新进入到原有队列中,默认回到头部
                 */
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
