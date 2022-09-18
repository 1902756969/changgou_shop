package com.changgou.canal.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //定义交换机名称
    public static final String GOODS_UP_EXCHANGE="goods_up_exchange";
    public static final String GOODS_DOWN_EXCHANGE="goods_down_exchange";

    //定义队列名称
    public static final String AD_UPDATE_QUEUE="ad_update_queue";
    public static final String SEARCH_ADD_QUEUE="search_add_queue";
    public static final String SEARCH_DEL_QUEUE="search_del_queue";
    public static final String PAGE_CREATE_QUEUE="page_create_queue";

    //声明队列
    @Bean
    public Queue queue(){
        return new Queue(AD_UPDATE_QUEUE);
    }
    @Bean(SEARCH_ADD_QUEUE)
    public Queue SEARCH_ADD_QUEUE(){
        return new Queue(SEARCH_ADD_QUEUE);
    }
    @Bean(SEARCH_DEL_QUEUE)
    public Queue SEARCH_DEL_QUEUE(){
        return new Queue(SEARCH_DEL_QUEUE);
    }
    @Bean(PAGE_CREATE_QUEUE)
    public Queue PAGE_CREATE_QUEUE(){
        return new Queue(PAGE_CREATE_QUEUE);
    }

    //声明交换机
    @Bean(GOODS_UP_EXCHANGE)
    public Exchange GOODS_UP_EXCHANGE(){
        return ExchangeBuilder.fanoutExchange(GOODS_UP_EXCHANGE).durable(true).build();
    }
    @Bean(GOODS_DOWN_EXCHANGE)
    public Exchange GOODS_DOWN_EXCHANGE(){
        return ExchangeBuilder.fanoutExchange(GOODS_DOWN_EXCHANGE).durable(true).build();
    }


    //队列与交换机的绑定
    @Bean
    public Binding GOODS_UP_EXCHANGE_BINDING(@Qualifier(SEARCH_ADD_QUEUE)Queue queue,@Qualifier(GOODS_UP_EXCHANGE)Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
    @Bean
    public Binding PAGE_CREATE_QUEUE_BINDING(@Qualifier(PAGE_CREATE_QUEUE)Queue queue,@Qualifier(GOODS_UP_EXCHANGE)Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
    @Bean
    public Binding GOODS_DOWN_EXCHANGE_BINDING(@Qualifier(SEARCH_DEL_QUEUE)Queue queue,@Qualifier(GOODS_DOWN_EXCHANGE)Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

}