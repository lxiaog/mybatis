package org.example.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.service.HelloService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public Object hello(String word) {
        log.info(word);
        return "hello "+word;
    }
}
