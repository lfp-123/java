package com.sc.spmvc.controller;

import com.sc.spmvc.pojo.SUser;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ${林锋鹏}
 * @Title: TestFilter
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/10 16:17
 */
 /* postHandle()在控制层方法之后，在视图解析器之前执行
    常用于通过该方法请求域中的数据及返回的页面做修改

     */
public class TestFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SUser user = (SUser)request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect("/mvc/login.jsp");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }



}
