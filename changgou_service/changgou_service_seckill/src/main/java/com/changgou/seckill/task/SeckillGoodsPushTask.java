package com.changgou.seckill.task;

import com.changgou.seckill.dao.SeckillGoodsMapper;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class SeckillGoodsPushTask {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String SECKILL_GOODS_KEY="seckill_goods_";

    public static final String SECKILL_GOODS_STOCK_COUNT_KEY="seckill_goods_stock_count_";

    @Scheduled(cron = "0/30 * * * * ?")
    public void  loadSecKillGoodsToRedis(){
        /**
         * 1.查询所有符合条件的秒杀商品
         * 	1) 获取时间段集合并循环遍历出每一个时间段
         * 	2) 获取每一个时间段名称,用于后续redis中key的设置
         * 	3) 状态必须为审核通过 status=1
         * 	4) 商品库存个数>0
         * 	5) 秒杀商品开始时间>=当前时间段
         * 	6) 秒杀商品结束<当前时间段+2小时
         * 	7) 排除之前已经加载到Redis缓存中的商品数据
         * 	8) 执行查询获取对应的结果集
         * 2.将秒杀商品存入缓存
         */

        List<Date> dateMenus = DateUtil.getDateMenus(); // 5个

        for (Date dateMenu : dateMenus) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String redisExtName = DateUtil.date2Str(dateMenu);

            Example example = new Example(SeckillGoods.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("status","1");
            criteria.andGreaterThan("stockCount",0);
            criteria.andGreaterThanOrEqualTo("startTime",simpleDateFormat.format(dateMenu));
            criteria.andLessThan("endTime",simpleDateFormat.format(DateUtil.addDateHour(dateMenu,2)));

            Set keys = redisTemplate.boundHashOps(SECKILL_GOODS_KEY + redisExtName).keys();//key field value

            if (keys != null && keys.size()>0){
                criteria.andNotIn("id",keys);
            }

            List<SeckillGoods> seckillGoodsList = seckillGoodsMapper.selectByExample(example);

            //添加到缓存中
            for (SeckillGoods seckillGoods : seckillGoodsList) {
                redisTemplate.opsForHash().put(SECKILL_GOODS_KEY + redisExtName,seckillGoods.getId(),seckillGoods);

                //加载秒杀商品的库存
                redisTemplate.opsForValue().set(SECKILL_GOODS_STOCK_COUNT_KEY+seckillGoods.getId(),seckillGoods.getStockCount());
            }
        }

    }
}
