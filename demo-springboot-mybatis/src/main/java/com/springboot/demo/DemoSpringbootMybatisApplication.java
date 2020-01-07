package com.springboot.demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//开启管理平台
@EnableAdminServer
//扫描mapper对应的dao接口 ，也可以在每个dao接口类上注解@Mapper
@MapperScan(basePackages = {"com.springboot.demo.repository.dao"})
public class DemoSpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringbootMybatisApplication.class, args);
    }

}
