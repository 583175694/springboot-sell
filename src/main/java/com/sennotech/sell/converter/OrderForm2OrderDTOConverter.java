package com.sennotech.sell.converter;
/*
 *   @author 吴少航
 *   @date 2019/10/21-11:25
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sennotech.sell.dataobject.OrderDetail;
import com.sennotech.sell.dto.OrderDTO;
import com.sennotech.sell.enums.ResultEnum;
import com.sennotech.sell.exception.SellException;
import com.sennotech.sell.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        //  把基本信息设置进去
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        //  购物车比较麻烦，得通过「Gson」插件把这个字符串转化为json格式
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        //  设置购物车列表
        orderDTO.setOrderDetailList(orderDetailList);

        //  返回orderDTO
        return orderDTO;
    }
}