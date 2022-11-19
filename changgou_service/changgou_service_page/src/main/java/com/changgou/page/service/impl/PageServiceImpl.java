package com.changgou.page.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.Result;
import com.changgou.goods.feign.CategoryFeign;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.page.service.PageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService {

    @Value("${pagepath}")
    private String pagepath;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void generateHtml(String spuId) {
        //1.获取context对象,用于存储商品的相关数据
        Context context = new Context();

        //获取静态化页面的相关数据
        Map<String,Object> itemData= this.getItemData(spuId);
        context.setVariables(itemData);

        //2.获取商品详情页面的存储位置
        File dir = new File(pagepath);
        //3.判断当前存储位置的文件夹是否存在,如果不存在,则新建
        if (!dir.exists()){
            dir.mkdirs();
        }
        //4.定义输出流,完成文件的生成
        File file = new File(dir+"/"+spuId+".html");
        Writer out = null;
        try{
            out = new PrintWriter(file);
            //生成静态化页面
            /**
             * 1. 模板名称
             * 2.context
             * 3.输出流
             */
            templateEngine.process("item",context,out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //5.关闭流
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Autowired
    private SpuFeign spuFeign;

    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private SkuFeign skuFeign;

    //获取静态化页面的相关数据
    private Map<String, Object> getItemData(String spuId) {
        Map<String, Object> resultMap = new HashMap<>();
        //获取spu
        Spu spu = spuFeign.findSpuById(spuId).getData();
        resultMap.put("spu",spu);
        //获取图片信息
        if (spu != null){
            if (StringUtils.isNotEmpty(spu.getImages())){
                resultMap.put("imageList",spu.getImages().split(","));
            }
        }
        //获取商品的分类信息
        Category category1 = categoryFeign.findById(spu.getCategory1Id()).getData();
        resultMap.put("category1",category1);

        Category category2 = categoryFeign.findById(spu.getCategory2Id()).getData();
        resultMap.put("category2",category2);

        Category category3 = categoryFeign.findById(spu.getCategory3Id()).getData();
        resultMap.put("category3",category3);

        //获取sku的相关信息
        List<Sku> skuList = skuFeign.findSkuListBySpuId(spuId);
        resultMap.put("skuList",skuList);

        //获取商品规格信息
        resultMap.put("specificationList",JSON.parseObject(spu.getSpecItems(),Map.class));
        return resultMap;
    }
}
