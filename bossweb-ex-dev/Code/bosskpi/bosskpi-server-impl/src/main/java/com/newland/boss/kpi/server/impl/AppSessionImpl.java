package com.newland.boss.kpi.server.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;

@Component
public class AppSessionImpl implements AppSession {
	
	private static final String KEY_SESSION_USER = "KEY_SESSION_USER";
	
	@Autowired    
	private HttpSession session; 
	
	@Override
	public void setValue(String key, Object value) {
		session.setAttribute(key, value);
	}

	@Override
	public Object getValue(String key) {
		return session.getAttribute(key);
	}
	
	@Override
	public void setUser(User user) {
		session.setAttribute(KEY_SESSION_USER, user);
	}
	
	@Override
	public User getUser() {
		Object object = this.getValue(KEY_SESSION_USER);
		if( null != object ) {
			return (User )object;
		}
		return new User();
	}
	
	public HttpSession getHttpSession() {
		 HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		 return req.getSession();
	}

}
