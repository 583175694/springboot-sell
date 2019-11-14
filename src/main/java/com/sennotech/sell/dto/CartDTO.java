package com.sennotech.sell.dto;
/*
 *   @author 吴少航
 *   @date 2019/10/15-16:27
 */

import lombok.Data;

@Data
public class CartDTO {

    //  商品Id
    private String productId;

    //  数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
