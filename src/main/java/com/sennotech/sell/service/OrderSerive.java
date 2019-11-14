package com.sennotech.sell.service;
/*
 *   @author 吴少航
 *   @date 2019/10/13-13:31
 */

import com.sennotech.sell.dataobject.OrderDetail;
import com.sennotech.sell.dataobject.OrderMaster;
import com.sennotech.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderSerive {
    //  创建订单
    OrderDTO create(OrderDTO orderDTO);

    //  查询单个订单
    OrderDTO findOne(String orderId);

    //  查询订单列表
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    //  取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //  完结订单
    OrderDTO finish(OrderDTO orderDTO);

    //  支付订单
    OrderDTO paid(OrderDTO orderDTO);
}
