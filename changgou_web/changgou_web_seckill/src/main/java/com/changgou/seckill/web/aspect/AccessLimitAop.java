package com.changgou.seckill.web.aspect;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Scope
@Aspect
public class AccessLimitAop {

    @Autowired
    private HttpServletResponse response;

    //设置令牌的生成速率
    private RateLimiter rateLimiter = RateLimiter.create(2.0); //每秒生成两个令牌存入桶中

    @Pointcut("@annotation(com.changgou.seckill.web.aspect.AccessLimit)")
    public void limit(){}

    @Around("limit()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){

        boolean flag = rateLimiter.tryAcquire();
        Object obj = null; //返回值

        if (flag){
            //允许访问
            try {
                obj = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }else{
            //不允许访问,拒绝
            String errorMessage = JSON.toJSONString(new Result<>(false, StatusCode.ACCESSERROR,"fail"));
            //将信息返回到客户端上
            this.outMessage(response,errorMessage);
        }

        return obj;
    }

    private void outMessage(HttpServletResponse response,String errorMessage){

        ServletOutputStream outputStream = null;
        try {
            response.setContentType("application/json;charset=utf-8");
            outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
