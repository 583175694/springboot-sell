package com.sennotech.sell.service.impl;

import com.sennotech.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void fineOne() {
        ProductCategory productCategory = categoryService.fineOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        System.out.println(productCategoryList.toString());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(2, 3, 4);

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(list);
        System.out.println(productCategoryList.toString());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("xixixi", 3);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}