package com.newland.boss.kpi.security.auth.provider;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.newland.boss.kpi.constant.Constants;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
	
	private static final Log LOGGER = LogFactory.getLog(CustomWebAuthenticationDetails.class);
	
	private static final long serialVersionUID = 1L;
	
	private String verifyCode;
	
	private String sessionVerifyCode;

    private long sessionVerifyTime;
    
    private int loginType;
	
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		LOGGER.debug("CustomWebAuthenticationDetails in : " + request.getParameterMap());
		this.verifyCode = request.getParameter("verifyCode");
		LOGGER.debug("verifyCode:"+verifyCode);
        this.sessionVerifyCode = (String)request.getSession().getAttribute(Constants.SESSION_KEY_OF_RAND_CODE);
        String sessionVerifyTimeStr = String.valueOf(request.getSession().getAttribute(Constants.SESSION_KEY_OF_RAND_TIME));
        if(sessionVerifyTimeStr == null) {
            this.sessionVerifyTime= 0L;
        } else {
            this.sessionVerifyTime= Long.parseLong(sessionVerifyTimeStr);
        }
        String loginTypeStr = request.getParameter("loginType");
        if(loginTypeStr == null || loginTypeStr.length() == 0) {
        	this.loginType = 0;
        } else {
        	this.loginType = Integer.parseInt(loginTypeStr);
        }
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getSessionVerifyCode() {
		return sessionVerifyCode;
	}

	public void setSessionVerifyCode(String sessionVerifyCode) {
		this.sessionVerifyCode = sessionVerifyCode;
	}

	public long getSessionVerifyTime() {
		return sessionVerifyTime;
	}

	public void setSessionVerifyTime(long sessionVerifyTime) {
		this.sessionVerifyTime = sessionVerifyTime;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}


}
