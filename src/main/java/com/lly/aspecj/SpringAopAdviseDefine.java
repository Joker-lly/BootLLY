package com.lly.aspecj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Component;

/**
 * 描述: 学习 Spring Aop 演示的例子 以及用法说明
 * 依赖：在 AppConfig 添加注解 @EnableAspectJAutoProxy
 *
 * @author Joker-lly
 * @since 2020-12-23
 */
@Component
@Aspect//切面
public class SpringAopAdviseDefine {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @DeclareParents: 扩展类，让一个类多实现另个接口的方法
     * perthis : 让切面变成多例的
     * 这两个东西没太理解，以后有时间再来写测试用例
     * 官方文档的 5.4.5和5.4.6的
     */

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
     * this 表示当前产生的代理对象 和 com.lly.business.service.UserService 相等
     */
    @Pointcut( "this(com.lly.business.service.UserServiceImpl)")
    public void thisCut(){}

    /**
     * target 表示当前产生的代理对象的目标对象 和 com.lly.business.service.UserService 相等
     */
    @Pointcut( "target(com.lly.business.service.UserServiceImpl)")
    public void targetCut(){}

    /**
     * 于方法执行前执行
     * （）中可以直接写定义的切点，也可以直接写  execution 等表达式
     * 各个切点之间可以用 与或等符合进行组合
     */
    @Before("executionPointCut() && pointCutArgs()")
    public void before() {
        System.out.println("前置通知");
    }

    /**
     * 后置通知
     */
    @After("executionPointCut() && pointCutArgs()")
    public void after() {
        System.out.println("后置通知");
    }
    /**
     * 环绕通知
     * ProceedingJoinPoint 这个类是方法的对象，可以获得方法的相关属性，例如：方法名称，参数类型及个数，返回类型等
     * JoinPoint ：ProceedingJoinPoint 的父类，ProceedingJoinPoint。proceed 可以执行目标方法
     *
     * @param pjp 当前增强的方法的对象
     */
    @Around("execution(* com.lly.business.service.*.testAround(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
       // new ProxyFactory()
        System.out.println("环绕通知前");

        pjp.proceed();

        System.out.println("环绕通知后");
    }
}
