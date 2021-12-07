package com.lly.business.entity;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<TestEntity,String> map = new HashMap<>();
        TestEntity t1 = new TestEntity(5);
        TestEntity t2 = new TestEntity(6);
        map.put(t1,"key1");
        map.put(t2,"key2");
        map.forEach((key,value) ->{
            System.out.println("key :" + key.getTest() + "  value :" + value);
        });
        System.out.println("第一次比较 ：" + t1.equals(t2));
        t2.setTest(5);
        map.forEach((key,value) ->{
            System.out.println("key :" + key.getTest() + "  value :" + value);
        });
        System.out.println("第二次比较 ：" + t1.equals(t2));

        map.clear();
        System.out.println("map.size() "+map.size());
        map.put(t1,"key3");
        map.put(t2,"key4");

        map.forEach((key,value) ->{
            System.out.println("key :" + key.getTest() + "  value :" + value);
        });

    }
}
