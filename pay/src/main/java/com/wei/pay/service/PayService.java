package com.wei.pay.service;

import com.wei.model.Pay;
import com.wei.model.XcTaskHis;

public interface PayService {
    //插入支付表
    int insertPay(Pay pay, XcTaskHis xcTaskHis);
}
