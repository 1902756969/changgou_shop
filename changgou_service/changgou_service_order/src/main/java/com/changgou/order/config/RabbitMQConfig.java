package com.changgou.order.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //添加积分任务交换机
    public static final String EX_BUYING_ADDPOINTUSER = "ex_buying_addpointuser";

    //添加积分消息队列
    public static final String CG_BUYING_ADDPOINT = "cg_buying_addpoint";

    //完成添加积分消息队列
    public static final String CG_BUYING_FINISHADDPOINT = "cg_buying_finishaddpoint";

    //添加积分路由key
    public static final String CG_BUYING_ADDPOINT_KEY = "addpoint";

    //完成添加积分路由key
    public static final String CG_BUYING_FINISHADDPOINT_KEY = "finishaddpoint";

    public static final String ORDER_PAY="order_pay";

    public static final String ORDER_TACK="order_tack";

    //声明交换机
    @Bean(EX_BUYING_ADDPOINTUSER)
    public Exchange EX_BUYING_ADDPOINTUSER(){
        return ExchangeBuilder.directExchange(EX_BUYING_ADDPOINTUSER).durable(true).build();
    }

    //声明队列
    @Bean(CG_BUYING_ADDPOINT)
    public Queue CG_BUYING_ADDPOINT(){
        Queue queue = new Queue(CG_BUYING_ADDPOINT,true);
        return queue;
    }
    @Bean(CG_BUYING_FINISHADDPOINT)
    public Queue CG_BUYING_FINISHADDPOINT(){
        Queue queue = new Queue(CG_BUYING_FINISHADDPOINT);
        return queue;
    }

    //队列绑定交换机
    @Bean
    public Binding BINDING_CG_BUYING_ADDPOINT(@Qualifier(CG_BUYING_ADDPOINT) Queue queue,@Qualifier(EX_BUYING_ADDPOINTUSER)Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(CG_BUYING_ADDPOINT_KEY).noargs();
    }
    @Bean
    public Binding BINDING_CG_BUYING_FINISHADDPOINT(@Qualifier(CG_BUYING_FINISHADDPOINT) Queue queue,@Qualifier(EX_BUYING_ADDPOINTUSER)Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(CG_BUYING_FINISHADDPOINT_KEY).noargs();
    }

    @Bean
    public Queue queue(){
        return  new Queue(ORDER_PAY);
    }

    @Bean
    public Queue ORDER_TACK(){
        return  new Queue(ORDER_TACK);
    }

}
