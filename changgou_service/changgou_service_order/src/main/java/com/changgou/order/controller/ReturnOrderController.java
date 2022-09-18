package com.changgou.order.controller;
import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.service.ReturnOrderService;
import com.changgou.order.pojo.ReturnOrder;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin
@RequestMapping("/returnOrder")
public class ReturnOrderController {


    @Autowired
    private ReturnOrderService returnOrderService;

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll(){
        List<ReturnOrder> returnOrderList = returnOrderService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",returnOrderList) ;
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        ReturnOrder returnOrder = returnOrderService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",returnOrder);
    }


    /***
     * 新增数据
     * @param returnOrder
     * @return
     */
    @PostMapping
    public Result add(@RequestBody ReturnOrder returnOrder){
        returnOrderService.add(returnOrder);
        return new Result(true,StatusCode.OK,"添加成功");
    }


    /***
     * 修改数据
     * @param returnOrder
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody ReturnOrder returnOrder,@PathVariable String id){
        returnOrder.setId(id);
        returnOrderService.update(returnOrder);
        return new Result(true,StatusCode.OK,"修改成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        returnOrderService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search" )
    public Result findList(@RequestParam Map searchMap){
        List<ReturnOrder> list = returnOrderService.findList(searchMap);
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
        Page<ReturnOrder> pageList = returnOrderService.findPage(searchMap, page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }


}
