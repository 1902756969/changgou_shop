package com.changgou.user.controller;
import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.user.service.ProvincesService;
import com.changgou.user.pojo.Provinces;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin
@RequestMapping("/provinces")
public class ProvincesController {


    @Autowired
    private ProvincesService provincesService;

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll(){
        List<Provinces> provincesList = provincesService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",provincesList) ;
    }

    /***
     * 根据ID查询数据
     * @param provinceid
     * @return
     */
    @GetMapping("/{provinceid}")
    public Result findById(@PathVariable String provinceid){
        Provinces provinces = provincesService.findById(provinceid);
        return new Result(true,StatusCode.OK,"查询成功",provinces);
    }


    /***
     * 新增数据
     * @param provinces
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Provinces provinces){
        provincesService.add(provinces);
        return new Result(true,StatusCode.OK,"添加成功");
    }


    /***
     * 修改数据
     * @param provinces
     * @param provinceid
     * @return
     */
    @PutMapping(value="/{provinceid}")
    public Result update(@RequestBody Provinces provinces,@PathVariable String provinceid){
        provinces.setProvinceid(provinceid);
        provincesService.update(provinces);
        return new Result(true,StatusCode.OK,"修改成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param provinceid
     * @return
     */
    @DeleteMapping(value = "/{provinceid}" )
    public Result delete(@PathVariable String provinceid){
        provincesService.delete(provinceid);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search" )
    public Result findList(@RequestParam Map searchMap){
        List<Provinces> list = provincesService.findList(searchMap);
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
        Page<Provinces> pageList = provincesService.findPage(searchMap, page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }


}
