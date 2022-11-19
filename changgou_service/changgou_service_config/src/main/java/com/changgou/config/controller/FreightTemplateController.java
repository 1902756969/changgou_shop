package com.changgou.config.controller;
import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.config.service.FreightTemplateService;
import com.changgou.config.pojo.FreightTemplate;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin
@RequestMapping("/freightTemplate")
public class FreightTemplateController {


    @Autowired
    private FreightTemplateService freightTemplateService;

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll(){
        List<FreightTemplate> freightTemplateList = freightTemplateService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",freightTemplateList) ;
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        FreightTemplate freightTemplate = freightTemplateService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",freightTemplate);
    }


    /***
     * 新增数据
     * @param freightTemplate
     * @return
     */
    @PostMapping
    public Result add(@RequestBody FreightTemplate freightTemplate){
        freightTemplateService.add(freightTemplate);
        return new Result(true,StatusCode.OK,"添加成功");
    }


    /***
     * 修改数据
     * @param freightTemplate
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody FreightTemplate freightTemplate,@PathVariable Integer id){
        freightTemplate.setId(id);
        freightTemplateService.update(freightTemplate);
        return new Result(true,StatusCode.OK,"修改成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        freightTemplateService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search" )
    public Result findList(@RequestParam Map searchMap){
        List<FreightTemplate> list = freightTemplateService.findList(searchMap);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }


    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result findPage(@RequestParam Map searchMap, @PathVariable  int page, @PathVariable  int size){
        Page<FreightTemplate> pageList = freightTemplateService.findPage(searchMap, page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }


}
