package com.lly;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 演示 spring 处理循环依赖的基本原理
 *
 * @author Joker-lly
 * @since 2020-12-24
 */
public class CircularReferenceTest {
    /**
     * 放置创建好的bean Map
     */
    private static Map<String, Object> cacheMap = new HashMap<>(2);

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // 假装扫描出来的对象
        Class[] classes = {A.class, B.class};
        // 假装项目初始化实例化所有bean
        for (Class aClass : classes) {
            getBean(aClass);
        }
        // check
        System.out.println(getBean(B.class).getA() == getBean(A.class));
        System.out.println(getBean(A.class).getB() == getBean(B.class));
    }

    private static <T> T getBean(Class<T> beanClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 本文用类名小写 简单代替bean的命名规则
        String beanName = beanClass.getSimpleName().toLowerCase();
        // 如果已经是一个bean，则直接返回
        if (cacheMap.containsKey(beanName)) {
            return (T) cacheMap.get(beanName);
        }
        // 将对象本身实例化
        Object object = beanClass.getDeclaredConstructor().newInstance();
        // 放入缓存
        cacheMap.put(beanName, object);
        // 把所有字段当成需要注入的bean，创建并注入到当前bean中
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // 获取需要注入字段的class
            Class<?> fieldClass = field.getType();
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            // 如果需要注入的bean，已经在缓存Map中，那么把缓存Map中的值注入到该field即可
            // 如果缓存没有 继续创建
            field.set(object, cacheMap.containsKey(fieldBeanName)
                    ? cacheMap.get(fieldBeanName) : getBean(fieldClass));
        }
        // 属性填充完成，返回
        return (T) object;
    }
}
