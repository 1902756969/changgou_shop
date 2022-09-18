package com.changgou.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.changgou.canal.config.RabbitMQConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@CanalEventListener
public class SpuListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ListenPoint(schema = "changgou_goods",table = "tb_spu")
    public void goodsUp(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        //获取改变之前的数据并将这部分数据转换为map
        Map<String,String> oldData=new HashMap<>();
        rowData.getBeforeColumnsList().forEach((c)->oldData.put(c.getName(),c.getValue()));

        //获取改变之后的数据并这部分数据转换为map
        Map<String,String> newData = new HashMap<>();
        rowData.getAfterColumnsList().forEach((c)->newData.put(c.getName(),c.getValue()));

        //获取最新上架的商品 0->1
        if ("0".equals(oldData.get("is_marketable")) && "1".equals(newData.get("is_marketable"))){
            //将商品的spuid发送到mq
            rabbitTemplate.convertAndSend(RabbitMQConfig.GOODS_UP_EXCHANGE,"",newData.get("id"));
        }

        //获取最新下架的商品 1->0
        if ("1".equals(oldData.get("is_marketable")) && "0".equals(newData.get("is_marketable"))){
            //将商品的spuid发送到mq
            rabbitTemplate.convertAndSend(RabbitMQConfig.GOODS_DOWN_EXCHANGE,"",newData.get("id"));
        }

        //获取最新被审核通过的商品  status    0->1
        if ("0".equals(oldData.get("status")) && "1".equals(newData.get("status"))){
            //将商品的spuid发送到mq
            rabbitTemplate.convertAndSend(RabbitMQConfig.GOODS_UP_EXCHANGE,"",newData.get("id"));
        }
    }
}
