package com.lly;

import com.lly.business.service.UserServiceImpl;
import com.lly.util.page.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
       // System.out.println(productByPage);
    }

    @Test
    void testAnnotation() {
       userServiceImpl.testAnnotation();
    }


    @Test
    void testAround() {
        userServiceImpl.testAround("str");
    }
}
