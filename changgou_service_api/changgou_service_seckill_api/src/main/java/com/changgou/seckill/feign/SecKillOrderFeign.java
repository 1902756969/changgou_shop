package com.changgou.seckill.feign;

import com.changgou.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "seckill")
public interface SecKillOrderFeign {

    @RequestMapping("/seckillorder/add")
    public Result add(@RequestParam("time") String time, @RequestParam("id") Long id);
}
