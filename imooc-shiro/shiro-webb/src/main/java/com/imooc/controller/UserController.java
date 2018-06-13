package com.imooc.controller;

import com.imooc.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.RequestWrapper;
@Controller
public class UserController {
    @RequestMapping(value="/subLogin",method = RequestMethod.POST,
    produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        subject.login(token);
        return "/index.html";
    }
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String login(){
        return "index";
    }

}
