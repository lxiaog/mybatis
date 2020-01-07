package com.springboot.demo.repository.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ：xiaog.li
 * @date ： 2020/1/3 15:58
 * @description：人员
 */
@Data
@Accessors(chain = true)
public class Person {
    private Integer id;
    private String address;
    private Integer age;
    private String name;
}
