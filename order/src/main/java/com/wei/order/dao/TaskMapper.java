package com.wei.order.dao;

import com.wei.model.XcTask;

import java.util.Date;
import java.util.List;

/**
 * @Author: weizz
 * @Date: 2019/5/23 15:36
 * @Description:
 * @Version:1.0
 */
public interface TaskMapper {
    //查询当前时间之间的任务
    List<XcTask> selectTaskBeforeTime(Date date);

    int updateXcTask(XcTask xcTask);

    int updateVersion(XcTask xcTask);

    int insertXcTask(XcTask xcTask);

    //删除任务
    int deleteXcTaskById(String id);
}
