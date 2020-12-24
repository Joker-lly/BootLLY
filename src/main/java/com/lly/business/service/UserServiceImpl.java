package com.lly.business.service;


import com.lly.anno.Lly;
import com.lly.business.entity.UserEntity;
import com.lly.business.mapper.UserDao;
import com.lly.util.page.PageUtil;
import com.lly.util.page.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext applicationContext;

//    public UserEntity getUser(String username){
//        return userDao.getUserinfor(username);
//    }
    /*public List<UserEntity> getUsers(){
        return userDao.getUsers();
    }*/


    public R getProductByPage(int page, int limit) {

        UserDao userDao = (UserDao) applicationContext.getBean("userDao");

        Map<String,Object> map =new HashMap<>();
        PageUtil pageUtil = new PageUtil(page,limit);
        map.put("page",pageUtil);
        List<Map<String, Object>> productByPage = this.userDao.getProductByPage(map);
        return R.success().data(productByPage).set("count",pageUtil.getCount());
    }

    /**
     * 测试 Spring Aop 对加有注解的测试
     */
    @Lly
    public void testAnnotation(){}

    /**
     * 测试 Spring Aop 对Around的测试
     */
    public String testAround(String string){
        System.out.println("testAround start ========");
        return string;
    }
}
