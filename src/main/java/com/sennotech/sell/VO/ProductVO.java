package com.sennotech.sell.VO;
/*
 *   @author 吴少航
 *   @date 2019/10/7-23:38
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> ProductInfoVOList;
}
