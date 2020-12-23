package com.lly.aspecj;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 描述: 学习 Spring Aop 演示的例子 以及用法说明
 *
 * @author Joker-lly
 * @since 2020-12-23
 */
@Component
@Aspect//切面
public class LogAopAdviseDefine {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 语法如下，摘抄自官方文档 ：https://docs.spring.io/spring-framework/docs/5.3.3-SNAPSHOT/reference/html/core.html#aop-pointcuts-designators
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
     *                 throws-pattern?)
     *  其中含义如下
     *  modifiers-pattern：表示方法的可见性
     * ？ ：可有可无
     * ret-type-pattern：方法返回值类型，如 int String等
     * declaring-type-pattern：方法所在类的全路径名，
     * name-pattern ：方法名类型
     * (param-pattern) ：方法参数类型,如：java.lang.String
     * throws-pattern：抛出异常类型
     * ex ；
     *      execution(* com.lly.business.service.*.*(..))的含义是 com.lly.business.service所有类里的所有类型的方法，都进行拦截
     */
    @Pointcut("execution(* com.lly.business.service.*.*(..))")
    public void executionPointCut(){
    }

    /**
     * within 的语法与 execution 相同，但区别在于 within 的粒度只细化到类，而 execution 粒度细化到了参数类型及个数
     */
    @Pointcut("within(com.lly.business.service.*)")
    public void withinPointCut(){
    }

    /**
     * args() 限定参数的切点
     */
    @Pointcut("args(java.lang.Integer,java.lang.Integer)")
    public void pointCutArgs(){
    }

    /**
     * 匹配注解的切点，作用方法级别
     */
    @Pointcut( "@annotation(com.lly.anno.Lly)")
    public void annotationCut(){}

    /**
     *
     */
    @Pointcut( "this(com.lly.business.service.UserService)")
    public void thisCut(){}

    /**
     *
     */
    @Pointcut( "target(com.lly.business.service.UserService)")
    public void targetCut(){}

    /**
     * 于方法执行前执行
     * （）中可以直接写定义的切点，也可以直接写  execution 等表达式
     * 各个切点之间可以用 与或等符合进行组合
     */
    @Before("executionPointCut() &&! pointCutArgs()")
    public void before() {
        System.out.println("前置通知");
    }

    @Around("annotationCut()")
    public void around(){
        System.out.println("注解环绕通知");
    }
}
