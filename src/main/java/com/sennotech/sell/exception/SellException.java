package com.sennotech.sell.exception;
/*
 *   @author 吴少航
 *   @date 2019/10/13-16:28
 */

import com.sennotech.sell.enums.ResultEnum;

public class SellException extends RuntimeException {

    //  错误码
    private Integer code;


    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
