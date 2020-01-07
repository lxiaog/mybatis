package com.springboot.demo.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：xiaog.li
 * @date ： 2020/1/3 16:13
 * @description：配置类型
 */
@Configuration
public class DataSourceConfig {


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }

    @Bean
    public SqlSession sqlSession() {
        return sqlSessionFactory.openSession(ExecutorType.BATCH, false);
    }
}
