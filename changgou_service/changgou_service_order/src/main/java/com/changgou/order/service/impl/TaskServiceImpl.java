package com.changgou.order.service.impl;

import com.changgou.order.dao.TaskHisMapper;
import com.changgou.order.dao.TaskMapper;
import com.changgou.order.pojo.Task;
import com.changgou.order.pojo.TaskHis;
import com.changgou.order.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskHisMapper taskHisMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    @Transactional
    public void delTask(Task task) {

        //1.记录删除时间
        task.setDeleteTime(new Date());
        Long taskId = task.getId();
        task.setId(null);

        //bean拷贝
        TaskHis taskHis = new TaskHis();
        BeanUtils.copyProperties(task,taskHis);

        //记录历史任务数据
        taskHisMapper.insertSelective(taskHis);

        //删除原有任务数据
        task.setId(taskId);
        taskMapper.deleteByPrimaryKey(task);

        System.out.println("订单服务完成了添加历史任务并删除原有任务的操作");

    }
}
