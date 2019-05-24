package com.wei.pay.service;

import com.wei.model.Pay;
import com.wei.model.XcTaskHis;
import com.wei.pay.mapper.PayMapper;
import com.wei.pay.mapper.TaskHisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: weizz
 * @Date: 2019/5/24 9:23
 * @Description:
 * @Version:1.0
 */
@Service
public class PayServiceImpl implements PayService{
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private TaskHisMapper taskHisMapper;

    @Override
    @Transactional
    public int insertPay(Pay pay, XcTaskHis xcTaskHis) {
        payMapper.insertPay(pay);
        taskHisMapper.insertTaskHis(xcTaskHis);
        return 1;
    }
}
