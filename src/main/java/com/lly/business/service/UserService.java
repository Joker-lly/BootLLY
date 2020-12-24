package com.lly.business.service;

import com.lly.util.page.R;

public interface UserService {
    public R getProductByPage(int page, int limit);
    public String testAround(String string);
}
