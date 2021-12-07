package com.lly;

import com.lly.business.entity.UserEntity;
import com.lly.business.mapper.UserDao;
import com.lly.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserDao userDao=(UserDao) context.getBean("userDao");

        List<UserEntity> users = userDao.getUsers();

    }
}
