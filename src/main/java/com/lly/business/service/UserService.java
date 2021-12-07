package com.lly.business.service;

import com.lly.business.entity.UserEntity;
import com.lly.util.page.R;

import java.util.List;

public interface UserService {
    public R getProductByPage(int page, int limit);
    public String testAround(String string);
    public void testInsertUsers(List<UserEntity> list);
}
