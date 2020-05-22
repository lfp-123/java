package com.newland.boss.kpi.admin.service;

import java.util.List;

import com.newland.boss.kpi.admin.model.Role;
import com.newland.boss.kpi.admin.model.RoleAuth;

public interface RoleAuthService {
	void addRoleAuth(String roleAuthJson);
	RoleAuth findAnRoleAuth(RoleAuth roleAuth);
	void removeRoleAuth(String roleAuthJson);
	void editRoleAuth(String roleAuthJson);
	List<RoleAuth> findAuthOfRole(RoleAuth roleAuth);
	void removeAuthByRoleId(Integer roleId);
	void addRoleAuth(Role roleAuth);
}
