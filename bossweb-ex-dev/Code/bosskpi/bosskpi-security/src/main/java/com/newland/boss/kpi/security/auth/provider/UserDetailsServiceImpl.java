package com.newland.boss.kpi.security.auth.provider;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.newland.boss.kpi.entity.Resource;
import com.newland.boss.kpi.entity.Role;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.security.entity.UserDetailsWrapper;
import com.newland.boss.kpi.server.AdminService;
import com.newland.boss.kpi.server.AppSession;


public class UserDetailsServiceImpl implements UserDetailsService {

	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AppSession appSession;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.info("userName:" + username);
		
		// get TOperator from db
		User user = adminService.getOperatorById(Integer.parseInt(username));
			
		if( null != user ) {
			UserDetailsWrapper userDetails = new UserDetailsWrapper();
			 
			userDetails.setUser(user);
			//根据操作员用户名查找所拥有的的角色  
			List<Role> roleList = adminService.getRoleByOperatorId(Integer.parseInt(username));
			
			for(Role role : roleList ) {
				List<Resource> resList = adminService.getResourceByRoleId(role.getRoleId());
				role.setResourceList(resList);
			}
			
			List<Resource> resourceList = adminService.getResourceByOperatorId(Integer.parseInt(username));
				
			user.setRoleList(roleList);  
			user.setResourceList(resourceList);
			
			if(user.getOperatorLevel() == 3) {
				//总行管理员
				user.setOrgId(-1);
			} else if (user.getOperatorLevel() == 2) {
				//分行管理员 -- 查询分行id
				Integer orgId = adminService.getOrgIdByOperatorId(Integer.parseInt(username));
				user.setOrgId(orgId);
			}
			
			appSession.setUser(user);
			
			return userDetails;
		}
		return null;
	}
}
