package com.sc.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ${林锋鹏}
 * @Title: Logger2
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/14 14:29
 */
/*
通过AOP的注解实现日志功能
 */
@Component //告诉Spring 扫描帮我创建对象
@Aspect //告诉Spring 这是一个切面
public class Logger2 {
    //声明需要  切面的方法
    @Pointcut("execution(* com.sc.spring.service.impl.*.*(..))")
    public void pc(){}

    @Around("pc()")
    public Object around(ProceedingJoinPoint jp){
        String name = jp.getSignature().getName(); //方法名
        System.out.println(name);
        Object[] args = jp.getArgs(); //参数
        for (Object arg : args) {
            System.out.println(arg);
        }
        Object result=null;
    try {
        System.out.println("环绕实现前置通知调用了"+name+"中的方法"+"参数是"+ Arrays.toString(args));
         result = jp.proceed();
        System.out.println("环绕实现后置通知");
    } catch (Throwable throwable) {
        throwable.printStackTrace();
        System.out.println("环绕实现异常通知信息"+throwable.getMessage());
    }finally {
        System.out.println("环绕实现最后通知");
    }
    return  result;
    }
//
//    @Before("pc()")
//    public void before(){
//        System.out.println("注解实现前置通知！");
//    }
//    @AfterReturning(value = "pc()",returning = "o")
//    public void agterReturning(Object o){
//        System.out.println("注解实现后置通知！");
//    }
//    @AfterThrowing(value = "pc()",throwing = "e")
//    public void afterThrowing(Exception e){
//        System.out.println("注解实现异常通知！");
//    }
//    @After("pc()")
//    public void after(){
//        System.out.println("注解实现最后通知！");
//    }



}
