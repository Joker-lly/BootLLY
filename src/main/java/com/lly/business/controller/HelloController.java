package com.lly.business.controller;

import com.lly.business.entity.TestEntity;
import com.lly.business.entity.UserEntity;
import com.lly.business.service.UserService;
import com.lly.util.page.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private RedisTemplate<String,String> template;


    @Autowired
    private TestEntity entity;

    @RequestMapping("/hello")
    @ResponseBody
    public  String Helloword(){



//        UserEntity user = userService.getUser("张三");
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        System.out.println("index----");
       // template.boundValueOps("username").set("张三");

        List<UserEntity> users = userService.getUsers();
        System.out.println(users.get(0).getPassword());
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
        R productByPage = userService.getProductByPage(page, limit);
        return userService.getProductByPage(page,limit);
    }
}
