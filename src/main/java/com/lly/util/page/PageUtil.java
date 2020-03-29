package com.lly.util.page;

import java.util.HashMap;
import java.util.Map;


public class PageUtil {

    private int page;

    private  int limit;

    private  int count;

    private  int start;

    public  PageUtil(){
    }

    public  PageUtil(int page,int limit){
        this.page = page;
        this.limit = limit;
        this.start = (page-1)*limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public static void main(String[] args) {
        Map<String, PageUtil> map = new HashMap<>();
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPage(1);
        map.put("key",pageUtil);
        //调用逻辑修改map
        方法1(map);
        //直接使用原来对象
        System.out.println(pageUtil.getPage());
    }

    public static void  方法1(Map<String, PageUtil> map){
        map.get("key").setPage(2);
    }

}
