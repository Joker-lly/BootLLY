package com.lly.business.mapper;

import com.lly.business.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserDao {
   // public UserEntity getUserinfor(String username);

    public List<UserEntity> getUsers();
    //插件分页
    List<Map<String,Object>> getProductByPage(Map<String, Object> map);
}
