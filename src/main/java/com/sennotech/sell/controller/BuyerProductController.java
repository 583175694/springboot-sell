package com.sennotech.sell.controller;
/*
 *   买家商品
 *   @author 吴少航
 *   @date 2019/10/7-23:00
 */

import com.sennotech.sell.VO.ProductInfoVO;
import com.sennotech.sell.VO.ProductVO;
import com.sennotech.sell.VO.ResultVO;
import com.sennotech.sell.dataobject.ProductCategory;
import com.sennotech.sell.dataobject.ProductInfo;
import com.sennotech.sell.service.CategoryService;
import com.sennotech.sell.service.ProductService;
import com.sennotech.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public ResultVO list() {

        // 1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2.查询类目（一次性查询）
        List<Integer> categoryTypeList = new ArrayList<>();

        //  传统方法
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }

        //  精简方法(java8, lambda)
//        List<Integer> categoryTypeList = productInfoList.stream()
//                .map(e -> e.getCategoryType())
//                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();

        // 先循环data最外层数据（类目）
        for (ProductCategory productCategory : productCategoryList) {

            // 接着把类目名称和类目编号设置到产品视图对象(VO)里面去
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            // 接着循环产品信息
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {

                // 当产品信息中的编号和类目编号相同时，把产品信息添加到产品信息视图对象(VO)里面去
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();

                    // 这里用BeanUtils.copyProperties可以直接将productInfo里的信息拷贝到VO里面去
                    BeanUtils.copyProperties(productInfo, productInfoVO);

                    //  接着将产品信息添加到这个产品信息列表里面
                    productInfoVOList.add(productInfoVO);
                }
            }
            // 然后把这些展品列表设置到每个类目里面
            productVO.setProductInfoVOList(productInfoVOList);

            // 最后把这个添加到一个类目列表里面
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}