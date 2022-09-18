package com.changgou.seckill.config;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ConfirmMessageSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String MESSAGE_CONFIRM_KEY="message_confirm_";

    public ConfirmMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    //接收消息服务器返回的通知的
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            //成功通知
            //删除redis中的相关数据
            redisTemplate.delete(correlationData.getId());
            redisTemplate.delete(MESSAGE_CONFIRM_KEY+correlationData.getId());
        }else{
            //失败通知
            //从redis中获取刚才的消息内容
            Map<String,String> map = (Map<String,String>)redisTemplate.opsForHash().entries(MESSAGE_CONFIRM_KEY+correlationData.getId());
            //重新发送
            String exchange = map.get("exchange");
            String routingkey = map.get("routingkey");
            String message = map.get("message");
            rabbitTemplate.convertAndSend(exchange,routingkey, JSON.toJSONString(message));
        }
    }

    //自定义消息发送方法
    public void sendMessage(String exchange,String routingKey,String message){

        //设置消息的唯一标识并存入到redis中
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        redisTemplate.opsForValue().set(correlationData.getId(),message);

        //将本次发送消息的相关元数据保存到redis中
        Map<String,String> map = new HashMap<>();
        map.put("exchange",exchange);
        map.put("routingKey",routingKey);
        map.put("message",message);
        redisTemplate.opsForHash().putAll(MESSAGE_CONFIRM_KEY+correlationData.getId(),map);

        //携带着本次消息的唯一标识,进行数据发送
        rabbitTemplate.convertAndSend(exchange,routingKey,message,correlationData);

    }
}
