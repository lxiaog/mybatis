package org.example.demo.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/person")
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/list", produces = "application/json;charset=utf-8")
    public String geiList() {
        log.info("查询list");
        return JSON.toJSONString(personService.getList());
    }
}
