package com.springboot.demo.repository.dao;

import com.springboot.demo.repository.entity.Person;

/**
 * @author ：xiaog.li
 * @date ： 2020/1/3 15:59
 * @description：人员接口
 */
public interface PersonDao {

    Integer insertSelective(Person preson);

}
