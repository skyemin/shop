package com.wei.order.service;

import com.wei.model.XcOrders;
import com.wei.model.XcTask;
import com.wei.order.dao.TaskMapper;
import com.wei.order.dao.XcOrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Author: weizz
 * @Date: 2019/5/23 15:36
 * @Description:
 * @Version:1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private XcOrdersMapper xcOrdersMapper;

    @Override
    public List<XcTask> selectTaskBeforeTime() {
        //得到1分钟之前的时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(GregorianCalendar.MINUTE,-1);
        Date time = calendar.getTime();
        List<XcTask> xcTasks = taskMapper.selectTaskBeforeTime(time);
        return xcTasks;
    }

    @Override
    public int insertXcTask(XcTask xcTask) {
        return taskMapper.insertXcTask(xcTask);
    }

    @Override
    public int updateXcTask(XcTask xcTask) {
        return taskMapper.updateXcTask(xcTask);
    }

    @Override
    @Transactional
    public int updateVersion(XcTask xcTask) {
        return taskMapper.updateVersion(xcTask);
    }

    @Override
    public int deleteXcTaskById(String id) {
        return taskMapper.deleteXcTaskById(id);
    }

    @Override
    public int insertXcOrders(XcOrders xcOrders) {
        return xcOrdersMapper.insertXcOrders(xcOrders);
    }

    @Override
    @Transactional
    public void buy() {
        XcOrders order = new XcOrders();
        order.setOrderNumber("001");
        order.setStartTime(new Date());
        order.setEndTime(new Date());
        order.setInitialPrice(100f);
        order.setPrice(80f);
        //插入订单表
        xcOrdersMapper.insertXcOrders(order);
        //插入任务表
        XcTask xcTask = new XcTask();
        xcTask.setId("1");
        xcTask.setUpdateTime(new Date());
        xcTask.setCreateTime(new Date());
        xcTask.setMqExchange("order_exchange");
        xcTask.setTaskType("order");
        taskMapper.insertXcTask(xcTask);
    }

}
