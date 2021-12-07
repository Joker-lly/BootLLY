package com.lly;

import com.lly.business.entity.UserEntity;
import com.lly.business.mapper.UserDao;
import com.lly.business.service.UserServiceImpl;
import com.lly.util.page.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: UserService 测试用例
 *
 * @author Joker-lly
 * @since 2020-12-23
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Test
    void testUserService() {
        R productByPage = userServiceImpl.getProductByPage(1, 10);
        System.out.println(productByPage);
    }

    @Test
    void testAnnotation() {
       userServiceImpl.testAnnotation();
    }


    @Test
    void testAround() {
        userServiceImpl.testAround("str");
    }

    @Test
    void testInsertUsers(){
        List<UserEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserEntity userEntity = new UserEntity("wangzi" + i, "12233"+ i, "manner" + i);
            list.add(userEntity);
        }
        userServiceImpl.testInsertUsers(list);
    }

    @Autowired
    private UserDao userDao;
    @Test
    void testGetUsers(){

        List<UserEntity> users = userDao.getUsers();
        UserEntity userEntity = users.get(0);
        System.out.println(userEntity);
    }
}
