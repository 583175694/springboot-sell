package com.sennotech.sell.enums;
import lombok.Getter;
/*
 *   @author 吴少航
 *   @date 2019/10/11-15:45
 */


@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
