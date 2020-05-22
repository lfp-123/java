package com.newland.boss.kpi.security.entity;

import java.util.ArrayList;
import java.util.Collection;
 
import java.util.List;
 

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.newland.boss.kpi.entity.Role;
import com.newland.boss.kpi.entity.User;

public class UserDetailsWrapper implements UserDetails {

	private static final long serialVersionUID = -8540072484269005971L;

	private static Log logger = LogFactory.getLog(UserDetailsWrapper.class.getName());
	
	private User user;
	

	public String getAuthoritiesString() {
		List<String> authorities = new ArrayList<String>();
		for (GrantedAuthority authority : this.getAuthorities()) {
			authorities.add(authority.getAuthority());
		}
		return StringUtils.join(authorities, ",");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		logger.debug("getAuthorities");
		// 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
		if ( null != user.getRoleList() && !user.getRoleList().isEmpty()) {
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			for (Role role : user.getRoleList()) {
				logger.debug("role alias:" + role.getRoleAlias());
				GrantedAuthority au = new SimpleGrantedAuthority(role.getRoleAlias());
				list.add(au);
			}

			return list;
		}
		return null;
	}


	/*
	 * 帐号是否不过期，false则验证不通过
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * 帐号是否不锁定，false则验证不通过
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * 凭证是否不过期，false则验证不通过
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * 该帐号是否启用，false则验证不通过
	 */
	@Override
	public boolean isEnabled() {
		return !user.isDisabled();
	}

	@Override
	public String getUsername() {
		return user.getOperatorName();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
