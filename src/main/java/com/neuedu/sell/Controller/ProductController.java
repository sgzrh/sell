package com.neuedu.sell.Controller;

import com.neuedu.sell.Utils.ResultVOUtils;
import com.neuedu.sell.VO.ProductCategoryVO;
import com.neuedu.sell.VO.ProductInfoVO;
import com.neuedu.sell.VO.ResultVO;
import com.neuedu.sell.entity.ProductCategory;
import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.service.ProductCategoryService;
import com.neuedu.sell.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class ProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有在架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2.根据商品列表构建一个类别编号的集合
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        //3.根据商品类别编号集合查询对应的类别
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //4.数据拼装
        //1>将元数据集合转化成VO集合
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            //构建新的VO对象
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            //将原对象放到VO对象中，Spring 提供了一个叫BeanUtils的类(要求属性名字一样)
            //最简单的实现方法
            // productCategoryVO.setCategoryName(productCategory.getCategoryName());
            BeanUtils.copyProperties(productCategory, productCategoryVO);

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            //将商品原数据转换成商品VO集合
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            //将转化好的商品VO集合设置到类别中
            productCategoryVO.setProductInfoVOList(productInfoVOList);
            //将VO对象放到集合中
            productCategoryVOList.add(productCategoryVO);
        }

        return ResultVOUtils.success(productCategoryVOList);
    }

}
