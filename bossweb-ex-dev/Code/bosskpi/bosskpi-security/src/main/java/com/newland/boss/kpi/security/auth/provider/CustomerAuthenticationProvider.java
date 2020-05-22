package com.newland.boss.kpi.security.auth.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.security.exception.VerifyCodeAuthenticationException;
import com.newland.boss.kpi.server.AdminService;
import com.newland.boss.kpi.server.OperateLogService;
import com.newland.boss.kpi.util.MD5Util;
import com.newland.boss.kpi.util.ResourceUtil;

public class CustomerAuthenticationProvider implements AuthenticationProvider {

	private Log logger = LogFactory.getLog(this.getClass().getName());

	private UserDetailsService userDetailsService;

	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
	private AdminService adminSerivce;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomWebAuthenticationDetails cad =(CustomWebAuthenticationDetails)authentication.getDetails();
		String verifyCode = cad.getVerifyCode().toUpperCase();
		String sessionVerify = cad.getSessionVerifyCode().toUpperCase();
		long sessionVerifyTime = cad.getSessionVerifyTime();
		int loginType = cad.getLoginType();
		
		if(0 == loginType) {
			String noCheckFlag = ResourceUtil.getValue("noCheckFalg");
			if(!"true".equals(noCheckFlag)) {
				if ((null == verifyCode) || (null == sessionVerify) || (!verifyCode.equals(sessionVerify))) {
					throw new VerifyCodeAuthenticationException("验证码失效");
				}
				
				if ((System.currentTimeMillis()-sessionVerifyTime) > 60*1000) {
					throw new VerifyCodeAuthenticationException("验证码错误或超时");
				}
			}
			logger.info("username：" + authentication.getName());
			logger.info("password：" + authentication.getCredentials());
			String encryptPass = MD5Util.Encrypt((String)authentication.getCredentials());
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(authentication.getName());
			logger.info("database username：" + userDetails.getUsername());
			logger.info("database password： " + userDetails.getPassword());
			logger.info("database Authorities：" + userDetails.getAuthorities());
			
			// 判断用户输入的密码和服务器上已经保存的密码是否一致
			if (encryptPass.equals(userDetails.getPassword())) {
				logger.info("author success");
				adminSerivce.resetErrCount(authentication.getName(), loginType);
				
				//记录操作日志(登录)
				OperateLog operateLog = new OperateLog();
				operateLog.setOperatorId(Integer.parseInt(authentication.getName()));
				operateLog.setOperateModule(11);
				operateLog.setOperateType(1);
				operateLog.setOperateDesc("账户登录[" + authentication.getName() + "]");
				operateLogService.addLog(operateLog);
				
				adminSerivce.initRoleAliasByResourceUrl();
				
				return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
			}  else {
				throw new BadCredentialsException("用户名或密码错误");
			}
		}
		
		adminSerivce.resetErrCount(authentication.getName(), loginType);
		User user = adminSerivce.selectOperatorByLoginName(authentication.getName());
		if(user == null) {
			throw new BadCredentialsException("用户名或密码错误");
		}
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(user.getOperatorId());
		operateLog.setOperateModule(11);
		operateLog.setOperateType(1);
		operateLog.setOperateDesc("notes用户登录[" + authentication.getName() + "]");
		operateLogService.addLog(operateLog);
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(String.valueOf(user.getOperatorId()));
		logger.info("userDetails：" + userDetails);
		logger.info("authorities：" + userDetails.getAuthorities());
		logger.info("authentication：" + authentication.getCredentials());

        adminSerivce.initRoleAliasByResourceUrl();

		return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
	}

	
	public boolean supports(Class<?> authentication) {
		return true;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}