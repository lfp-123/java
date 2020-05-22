package com.newland.boss.kpi.admin.service;

import java.util.List;
import java.util.Map;

import com.newland.boss.kpi.admin.model.Role;

public interface RoleService {
	List<Role> findAllRoles();
	void addRole(Role role);
	void removeRole(String roleJson);
	Role findRole(Role role);
	void editRole(Role role);
	List<Role> searchRoles(Role role);
	void removeRole(List<Role> roles);
	List<Role> findAllRolesWithResource(Role role);
	List<Role> selectOperatorRole(Map<String,Object> params);
}
