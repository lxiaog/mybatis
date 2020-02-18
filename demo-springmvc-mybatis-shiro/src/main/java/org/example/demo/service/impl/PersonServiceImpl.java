package org.example.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.repository.dao.PersonMapper;
import org.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public Object getList() {
        return personMapper.list();
    }
}
