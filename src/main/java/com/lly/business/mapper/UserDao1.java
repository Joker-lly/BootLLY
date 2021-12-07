package com.lly.business.mapper;

import com.lly.business.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UserDao1 {
 
    public List<UserEntity> getUsers();
    //插件分页
    List<Map<String,Object>> getProductByPage(Map<String, Object> map);

    void insertUsers(@Param("list") List<UserEntity> list);
}
