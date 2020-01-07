package com.springboot.demo.controller;

import com.springboot.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xiaog.li
 * @date ： 2020/1/3 15:15
 * @description：产品控制层
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/list")
    public Object getProductList() {
        return productService.getProductList();
    }

    @RequestMapping(value = "/add/{type}")
    public Object add(@PathVariable(value = "type") int type) {
        return productService.addPersons(type);
    }

}
