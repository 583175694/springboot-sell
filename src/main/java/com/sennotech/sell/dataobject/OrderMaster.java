package com.sennotech.sell.dataobject;
/*
 *   @author 吴少航
 *   @date 2019/10/11-15:38
 */

import com.sennotech.sell.enums.OrderStatusEnum;
import com.sennotech.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class OrderMaster {

    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    //  支付状态
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    //  创建时间
    private Date createTime;

    //  更新时间
    private Date updateTime;
}
