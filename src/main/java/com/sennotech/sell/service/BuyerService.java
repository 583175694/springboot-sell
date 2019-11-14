package com.sennotech.sell.service;
/*
 *   @author 吴少航
 *   @date 2019/10/21-17:22
 */

import com.sennotech.sell.dto.OrderDTO;

public interface BuyerService {

    //  查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //  取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
