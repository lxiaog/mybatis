package com.springboot.demo.service.impl;

import com.springboot.demo.repository.dao.PersonDao;
import com.springboot.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired(required = false)
    private PersonDao personDao;

    @Override
    public Object getList() {
        return personDao.list();
    }
}
