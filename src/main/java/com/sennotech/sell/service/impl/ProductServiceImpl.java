package com.sennotech.sell.service.impl;

import com.sennotech.sell.dataobject.ProductInfo;
import com.sennotech.sell.dto.CartDTO;
import com.sennotech.sell.enums.ProductStatusEnum;
import com.sennotech.sell.enums.ResultEnum;
import com.sennotech.sell.exception.SellException;
import com.sennotech.sell.repository.ProductInfoRepository;
import com.sennotech.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() { return repository.findByProductStatus(ProductStatusEnum.UP.getCode()); }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        //  循环购物车列表
        for (CartDTO cartDTO: cartDTOList) {
            //  获取单个商品信息
            ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //  增加商品库存
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();

            //  设置计算后的库存
            productInfo.setProductStock(result);

            //  将库存
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //  库存减去商品数量
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }
}
