package com.rlj.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @RequestMapping(value = "/dashboard",method = RequestMethod.POST)
    public String Login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "111111".equals(password)){
            //登陆成功
            session.setAttribute("loginUser",username);
            return "redirect:/main";
        }
        //登录失败，向共享域容器中放一个错误提示消息
        map.put("msg","用户名密码错误");
        return "index";
    }

}
