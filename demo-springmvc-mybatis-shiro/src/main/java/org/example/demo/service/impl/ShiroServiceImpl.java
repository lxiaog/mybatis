package org.example.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.example.demo.service.ShiroService;
import org.springframework.stereotype.Service;

import java.util.Date;

//@Service
@Slf4j
public class ShiroServiceImpl implements ShiroService {

    @RequiresRoles({"admin"})
    public void testMethod(){
        log.info("testMethod, time: " + new Date());

        Session session = SecurityUtils.getSubject().getSession();
        Object val = session.getAttribute("key");

        log.info("Service SessionVal: " + val);
    }
}
