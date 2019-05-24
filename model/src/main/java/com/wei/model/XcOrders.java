package com.wei.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2018/2/10.
 */
@Data
public class XcOrders implements Serializable {
    private static final long serialVersionUID = -916357210051689789L;
    private String orderNumber;

    private Float initialPrice;

    private Float price;

    private Date startTime;

    private Date endTime;

    private String status;

    private String userId;

    private String details;

}
