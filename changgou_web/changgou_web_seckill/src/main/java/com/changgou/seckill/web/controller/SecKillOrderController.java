package com.changgou.seckill.web.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.seckill.feign.SecKillOrderFeign;
import com.changgou.seckill.web.aspect.AccessLimit;
import com.changgou.seckill.web.util.CookieUtil;
import com.changgou.util.RandomUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/wseckillorder")
public class SecKillOrderController {

    @Autowired
    private SecKillOrderFeign secKillOrderFeign;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/add")
    @AccessLimit
    public Result add(@RequestParam("time") String time, @RequestParam("id")Long id,String random){

        String cookieValue = this.readCookie();
        String redisRandomCode = (String) redisTemplate.opsForValue().get("randomcode_"+cookieValue);
        if (StringUtils.isEmpty(redisRandomCode)){
            return new Result(false, StatusCode.ERROR,"下单失败");
        }
        if (!random.equals(redisRandomCode)){
            return new Result(false, StatusCode.ERROR,"下单失败");
        }

        Result result = secKillOrderFeign.add(time, id);
        return result;
    }

    @GetMapping("/getToken")
    @ResponseBody
    public String getToken(){
        String randomString = RandomUtil.getRandomString();

        String cookieValue = this.readCookie();

        redisTemplate.opsForValue().set("randomcode_"+cookieValue,randomString,5, TimeUnit.SECONDS);

        return randomString;
    }

    private String readCookie(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String jti = CookieUtil.readCookie(request, "uid").get("uid");

        return jti;
    }
}
