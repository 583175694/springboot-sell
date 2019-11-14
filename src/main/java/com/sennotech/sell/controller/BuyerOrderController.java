package com.sennotech.sell.controller;
/*
 *   @author 吴少航
 *   @date 2019/10/20-13:32
 */

import com.sennotech.sell.VO.ResultVO;
import com.sennotech.sell.converter.OrderForm2OrderDTOConverter;
import com.sennotech.sell.dto.OrderDTO;
import com.sennotech.sell.enums.ResultEnum;
import com.sennotech.sell.exception.SellException;
import com.sennotech.sell.form.OrderForm;
import com.sennotech.sell.service.BuyerService;
import com.sennotech.sell.service.OrderSerive;
import com.sennotech.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderSerive orderSerive;

    @Autowired
    private BuyerService buyerService;

    /*
    * 创建订单
    * 返回值ResultVO对象，包含code，msg，data
    * 接收参数新建一个【表单验证】的类，包含电话，姓名，地址等
    * BindingResult是用来参数验证，可以直接拿到bindingResult中的错误信息
    * */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        /*
         * 如果返回信息有错误，则报商品信息不存在
         * 这里给SellException类增加了一个构造函数，接收code和msg
         * */
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        /*
         * 这里创建一个OrderForm2OrderDTOConverter类，将orderForm对象转化为orderDTO
         * 判断购物车是否为空
         * */
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        //  调用service层创建的方法
        OrderDTO createResult = orderSerive.create(orderDTO);

        //  新建一个map = { "orderId": "1571638372821823502" }}
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        //  将这个map设置到ResultVO的data里面
        return ResultVOUtil.success(map);
    }

    /*
    * 订单列表
    * 因为参数不多，直接将参数放上去
    * */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size
                                         ) {
        //  判断openid是否为空
        if (StringUtils.isEmpty(openid)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        //  调用Service的方法查找订单列表
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderSerive.findList(openid, request);

        //  返回订单列表
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //  订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId
    ) {
        OrderDTO result = buyerService.findOrderOne(openid, orderId);

        return ResultVOUtil.success(result);
    }

    //  取消订单
    @GetMapping("/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId
    ) {
        buyerService.cancelOrder(openid, orderId);

        return ResultVOUtil.success();
    }
}
