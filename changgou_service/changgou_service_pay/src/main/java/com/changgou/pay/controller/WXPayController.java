package com.changgou.pay.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.pay.config.RabbitMQConfig;
import com.changgou.pay.service.WXPayService;
import com.changgou.util.ConvertUtils;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/wxpay")
@RestController
public class WXPayController {

    @Autowired
    private WXPayService wxPayService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //下单
    @GetMapping("/nativePay")
    public Result nativePay(@RequestParam("orderId") String orderId, @RequestParam("money")Integer money){
        Map resultMap = wxPayService.nativePay(orderId, money);
        return  new Result(true, StatusCode.OK,"",resultMap);
    }

    @RequestMapping("/notify")
    public void notifyLogic(HttpServletRequest request, HttpServletResponse response){
        System.out.println("支付成功回调");
        try{
            //输入流转换为字符串
            String xml = ConvertUtils.convertToString(request.getInputStream());
            System.out.println(xml);

            //基于微信发送的通知内容,完成后续的业务逻辑处理
            Map<String, String> map = WXPayUtil.xmlToMap(xml);
            if ("SUCCESS".equals(map.get("result_code"))){

                //查询订单
                Map result = wxPayService.queryOrder(map.get("out_trade_no"));
                System.out.println("查询订单结果:"+result);

                if ("SUCCESS".equals(result.get("result_code"))){

                    //将订单的消息发送到mq'
                    Map message = new HashMap();
                    message.put("orderId",result.get("out_trade_no"));
                    message.put("transactionId",result.get("transaction_id"));

                    //消息的发送
                    rabbitTemplate.convertAndSend("", RabbitMQConfig.ORDER_PAY, JSON.toJSONString(message));

                    //完成双向通信
                    rabbitTemplate.convertAndSend("paynotify","",result.get("out_trade_no"));
                }else {
                    //输出错误原因
                    System.out.println(map.get("err_code_des"));
                }

            }else{
                //输出错误原因
                System.out.println(map.get("err_code_des"));
            }



            //给微信一个结果通知
            response.setContentType("text/xml");
            String data="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
            response.getWriter().write(data);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //基于微信查询订单
    @GetMapping("/query/{orderId}")
    public Result queryOrder(@PathVariable("orderId") String orderId){
        Map map = wxPayService.queryOrder(orderId);
        return new Result(true,StatusCode.OK,"查询订单成功",map);
    }

    //基于微信关闭订单
    @PutMapping("/close/{orderId}")
    public Result closeOrder(@PathVariable("orderId") String orderId){
        Map map = wxPayService.closeOrder(orderId);
        return new Result(true,StatusCode.OK,"关闭订单成功",map);
    }
}
