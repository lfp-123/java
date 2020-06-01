package com.seecee.sc1911.struts;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.omg.CORBA.*;

/**
 * @author ${林锋鹏}
 * @Title: MyInterceptor
 * @ProjectName Java
 * @Description: 自定义拦截器 实现查询方法调用时间
 * @date 2019/12/23 16:14
 */
public class MyInterceptor implements Interceptor {


    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }
    /*

     */
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        System.out.println("session 执行之前");
        long l = System.currentTimeMillis();
        String invoke = actionInvocation.invoke();
        System.out.println("之后"
        );
        long end = System.currentTimeMillis();
        System.out.println(end-l);
        return invoke;
    }
}
