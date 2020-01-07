package com.springboot.demo.repository.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：xiaog.li
 * @date ： 2020年1月3日14:55:58
 * @description：产品实体
 */
@Data
public class Product {
    private long id;
    private String num;
    private String name;
    private String category;
    private BigDecimal price;
    private String place;
    private String code;
}
