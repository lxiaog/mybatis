package org.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.example.demo.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/shiro")
@Slf4j
public class ShiroController {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/testShiroAnnotation")
    public String testShiroAnnotation(HttpSession session){
        session.setAttribute("key", "value12345");
        shiroService.testMethod();
        return "redirect:/list.jsp";
    }

    @RequestMapping(value = "/login")
    public String login(String username,String password){
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);

            }catch (AuthenticationException e){
                log.error("登录失败:"+e.getMessage());
            }
        }
        return "redirect:/list.jsp";

    }

    @RequestMapping(value = "/list")
    public String list(){
        return "/list.jsp";
    }

    @RequestMapping(value = "/unauthorized")
    public String unauthorized(){
        return "/unauthorized.jsp";
    }

}
