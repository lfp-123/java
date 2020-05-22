package com.newland.boss.kpi.security.intercept;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.newland.boss.kpi.server.AdminService;

public class ResourceSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
	
	private static final Log logger = LogFactory.getLog(ResourceSecurityMetadataSource.class.getName());
	
	@Autowired
	private AdminService adminService;
	
	private PathMatcher matcher;
	
	//key是菜单url,value是角色别名
	private Map<String, Collection<ConfigAttribute>> resourceRoleMap = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
	
	/**
	 * 加载所有权限关系
	 */
	public void afterPropertiesSet() throws Exception {
		logger.info("ResourceSecurityMetadataSource.afterPropertiesSet");
		//加载所有菜单拥有的角色
		
		this.matcher = new AntPathMatcher(); 
		
		adminService.initRoleAliasByResourceUrl();
		
		//查找菜单路径对应的角色
		resourceRoleMap = adminService.getResourceRoleMap();
		logger.info("resourceRoleMap = " + resourceRoleMap);
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {	
		//获取请求的url地址  
        resourceRoleMap = adminService.getResourceRoleMap();
		FilterInvocation filterInvocation = (FilterInvocation) object;
		String requestURI = filterInvocation.getRequestUrl();
		logger.info(">>>>>>>>>>>>>>requestURI:"+requestURI);
		for( Map.Entry<String, Collection<ConfigAttribute>> entry :  resourceRoleMap.entrySet()) {
			if (matcher.match(requestURI, entry.getKey())) {
				return entry.getValue();
			}
		}
		
		logger.info(">>>>>>>>>>>>>>ConfigAttribute is null.");
		
		//防止数据库中没有页面权限，不能进行权限拦截  
		Collection<ConfigAttribute> tempList = new ArrayList<ConfigAttribute>();
		ConfigAttribute cano = new SecurityConfig("ROLE_NONE");
		tempList.add(cano);
		return tempList;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#
	 * getConfigAttributeDefinitions()
	 */
	@SuppressWarnings("rawtypes")
	public Collection getConfigAttributeDefinitions() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.intercept.ObjectDefinitionSource#supports
	 * (java.lang.Class)
	 */
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return true;
	}

	/**
	 * 
	 * @param filterInvocation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> getUrlAuthorities(FilterInvocation filterInvocation) {
		ServletContext servletContext = filterInvocation.getHttpRequest().getSession().getServletContext();
		return (Map<String, String>) servletContext.getAttribute("urlAuthorities");
	}
}
