package com.springboot.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.demo.repository.dao.PersonDao;
import com.springboot.demo.repository.dao.ProductDao;
import com.springboot.demo.repository.entity.Person;
import com.springboot.demo.service.ProductService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author ：xiaog.li
 * @date ： 2020/1/3 15:12
 * @description：产品service 实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired(required = false)
    private ProductDao productDao;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired(required = false)
    private PersonDao personDao;

    @Override
    public Object getProductList() {

        PageHelper.startPage(1, 10);
        return productDao.getList();
    }

    @Override
    public Long addPersons(int type) {
        if (type == 0) {
            return batch();
        } else {
            return add();
        }
    }

    public Long batch() {
        System.out.println(sqlSessionTemplate.getExecutorType());
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        PersonDao personDao = sqlSession.getMapper(PersonDao.class);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Person person = new Person();
            person.setName("name-" + i)
                    .setAddress("成都-" + i)
                    .setAge(new Random(1).nextInt(99));
            personDao.insertSelective(person);
        }
        sqlSession.commit();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public Long add() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Person person = new Person();
            person.setName("姓名-" + i)
                    .setAddress("成都-" + i)
                    .setAge(new Random(1).nextInt(99));
            personDao.insertSelective(person);
        }
        sqlSessionTemplate.commit();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
