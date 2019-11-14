package com.sennotech.sell.dto;
/*
 *   @author 吴少航
 *   @date 2019/10/13-15:01
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sennotech.sell.dataobject.OrderDetail;
import com.sennotech.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    //  id
    private String orderId;

    //  买家名字
    private String buyerName;

    //  买家手机号
    private String buyerPhone;

    //  买家地址
    private String buyerAddress;

    //  买家openId
    private String buyerOpenid;

    //  订单总金额
    private BigDecimal orderAmount;

    //  订单状态。默认为新下单
    private Integer orderStatus;

    //  支付状态
    private Integer payStatus;

    //  创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //  更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    //  订单列表
    private List<OrderDetail> orderDetailList;
}
