package com.springboot.demo.controller;

import com.springboot.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
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
    public Object geiList() {
        log.debug("查询list");
        log.info("查询list");
        log.warn("查询list");
        log.error("查询list");
        return personService.getList();
    }
}
