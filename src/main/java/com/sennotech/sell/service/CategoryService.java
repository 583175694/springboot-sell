package com.sennotech.sell.service;

import com.sennotech.sell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory fineOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}