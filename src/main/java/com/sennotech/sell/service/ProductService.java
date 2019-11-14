package com.sennotech.sell.service;

import com.sennotech.sell.dataobject.ProductInfo;
import com.sennotech.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    //  查询所有上架商品列表
    List<ProductInfo> findUpAll();

    //  查询所有商品列表
    Page<ProductInfo> findAll(Pageable pageable);

    //  储存商品
    ProductInfo save(ProductInfo productInfo);

    //  加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //  减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
