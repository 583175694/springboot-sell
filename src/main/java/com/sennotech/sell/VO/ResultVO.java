package com.sennotech.sell.VO;
/*
 *   http请求返回的最外层对象
 *   @author 吴少航
 *   @date 2019/10/7-23:21
 */

import lombok.Data;

@Data
public class ResultVO<T> {

    //  错误码
    private Integer code;

    //  错误信息
    private String msg;

    //  具体内容
    private T data;
}
