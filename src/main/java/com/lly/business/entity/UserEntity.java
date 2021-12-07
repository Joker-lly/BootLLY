package com.lly.business.entity;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class UserEntity {


    @JSONField(name = "user:name") 
    private String username;
    private String password;
    private String role;

    public UserEntity(String username, String password, String role) {
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public static void main(String[] args) {
        String ss = "{\n" +
                "    \"user:name\":\"sehngdan\"\n" +
                "}";


        UserEntity userEntity = JSON.parseObject(ss, UserEntity.class);
        System.out.println(userEntity.getUsername());
    }
    public UserEntity() {

    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
