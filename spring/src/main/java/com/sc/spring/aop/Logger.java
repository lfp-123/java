package com.sc.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author ${林锋鹏}
 * @Title: Logger
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/13 22:18
 */
/*
定义一个切面，实现日志功能
 */
public class Logger {
    //编写前置通知，可以让它在指定连接点的方法执行之前执行
    public void beforeAdvice(){
        System.out.println("程序运行，开始记录日志，前置通知");
    }
    public void afterAdvice(Object result){
        System.out.println("程序运行，后置通知,方法的返回值"+result);
    }
    public void afterThrowing(){
        System.out.println("异常通知！");
    }
    public void  after(){
        System.out.println("最后通知");
    }
    public Object around(ProceedingJoinPoint jp){
        Object result=null;
        try {
            System.out.println("前置通知！");
            result = jp.proceed();
            System.out.println("后置通知！");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("异常通知！");
        }finally {
            System.out.println("最后通知！");
        }
        System.out.println("最后程序运行,环绕通知");
        return  result;
    }
}
