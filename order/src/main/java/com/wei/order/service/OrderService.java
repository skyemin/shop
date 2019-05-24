package com.wei.order.service;

import com.wei.model.XcOrders;
import com.wei.model.XcTask;

import java.util.List;

public interface OrderService {

    //查询当前时间之间的任务
    List<XcTask> selectTaskBeforeTime();

    int insertXcTask(XcTask xcTask);

    int updateXcTask(XcTask xcTask);

    int updateVersion(XcTask xcTask);

    //删除任务
    int deleteXcTaskById(String id);

    //下单
    int insertXcOrders(XcOrders xcOrders);

    void buy();
}
