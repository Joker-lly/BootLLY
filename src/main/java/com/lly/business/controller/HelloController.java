package com.lly.business.controller;


import com.lly.business.service.UserServiceImpl;
import com.lly.util.page.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller

public class HelloController {

    @Autowired
    @Resource
    private UserServiceImpl userService;

//    @Autowired
//    private RedisTemplate<String,String> template;




    @RequestMapping("/hello")
    @ResponseBody
    public  String Helloword(){


        System.out.println("当前线程名称为： "+Thread.currentThread().getName());

//        UserEntity user = userService.getUser("张三");
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        System.out.println("index----");
       // template.boundValueOps("username").set("张三");

     //   List<UserEntity> users = userService.getUsers();
        //System.out.println(users.get(0).getPassword());
        return "index.html";
    }
    @RequestMapping("/login")
    public ModelAndView login(){
        System.out.println("login-----");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apptab/login.html");
        return mv;
    }
    @ResponseBody
    @RequestMapping("/getProductByPage")
    public Object getProductByPage(int page,int limit){

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();


        R productByPage = userService.getProductByPage(page, limit);
        return userService.getProductByPage(page,limit);
    }
}
