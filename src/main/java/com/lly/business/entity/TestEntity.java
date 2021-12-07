package com.lly.business.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


//@Component
//@ConfigurationProperties(prefix = "info")
public class TestEntity {

    // 从配置文件中读取信息 并给其赋值
    //@Value("${info.username}")
    private String username;
   // @Value("${info.password}")
    private String password;

    public int getTest() {
        return test;
    }

    private int test;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TestEntity(String username, String password, int test) {
        this.username = username;
        this.password = password;
        this.test = test;
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    public void setTest(int test) {
        this.test = test;
    }

    public TestEntity(int test) {
        this.test = test;
    }

    @Override
    public int hashCode() {
       // return test ;
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TestEntity) {
            TestEntity temp =(TestEntity) obj;
            return test == temp.getTest();
        }
       return false;
    }

}
