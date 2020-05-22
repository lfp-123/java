package com.newland.boss.kpi.security.intercept;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

//校验用户的权限是否足够
public class ResourceSecurityAccessDecisionManager implements AccessDecisionManager {
	
	private static final Log logger = LogFactory.getLog(ResourceSecurityAccessDecisionManager.class.getName());
	
	/*检查用户是否够权限访问资源 
	 * (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		logger.debug("decide");
		//如果访问资源不需要任何权限则直接通过  
		if(configAttributes == null){   
			return;          
		}    
       
		//遍历configAttributes看用户是否有访问资源的权限  
		Iterator<ConfigAttribute> ite=configAttributes.iterator();  
		//判断用户所拥有的权限，是否符合对应的Url权限，如果实现了UserDetailsService，则用户权限是loadUserByUsername返回用户所对应的权限  
		while(ite.hasNext()){  
			ConfigAttribute ca=ite.next();    
			String needRole=((SecurityConfig)ca).getAttribute();  
			logger.debug("用户：" + authentication.getName() + ">>>>>>>>>>>>>>>>user need role:"+needRole);
   
			//ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。  
			for(GrantedAuthority ga : authentication.getAuthorities()) {  
				logger.debug("用户：" + authentication.getName() + ">>>>>>>>>>>>>>>>user has role:"+ga.getAuthority());
				if(needRole.equals(ga.getAuthority())) {    
					return;                
				}              
			}        
		}
		logger.debug("用户：" + authentication.getName() + ">>>>>>>>>>>>>>>>没有权限访问!");
		throw new AccessDeniedException("没有权限访问！");    
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
