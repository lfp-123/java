package com.newland.boss.kpi.security.exception;

import org.springframework.security.core.AuthenticationException;

public class VerifyCodeAuthenticationException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public VerifyCodeAuthenticationException(String msg) {
		super(msg);
	}

	 	

}
