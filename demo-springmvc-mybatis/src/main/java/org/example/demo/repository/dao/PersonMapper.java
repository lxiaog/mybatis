package org.example.demo.repository.dao;

import org.example.demo.repository.entity.Person;

import java.util.List;

public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    List<Person> list();

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}