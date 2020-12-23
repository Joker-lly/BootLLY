package com.lly;

import com.lly.business.service.UserService;
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
    private UserService userService;
    @Test
    void testUserService() {
        R productByPage = userService.getProductByPage(1, 10);
        System.out.println(productByPage);
    }

    @Test
    void testAnnotation() {
       userService.testAnnotation();
    }
}
