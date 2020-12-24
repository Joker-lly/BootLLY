package com.lly;

import com.lly.config.JedisTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LlyApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private JedisTemplate jedisTemplate;
    @Test
    void testJeds(){
        try {
            jedisTemplate.set("lv", "lhm", "刘慧敏");
        }catch (Exception e) {
            System.out.println("有异常" );
        }


    }
    @Test
    void getRedisKey(){
        String s = jedisTemplate.get("lv", "lhm");
        System.out.println(s);

    }
}
