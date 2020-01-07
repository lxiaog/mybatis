package com.springboot.demo.repository.dao;

import com.springboot.demo.repository.entity.Product;

import java.util.List;

/**
 * @author ：xiaog.li
 * @date ： 2020/1/3 14:56
 * @description：接口
 */
public interface ProductDao {
    List<Product> getList();

}
