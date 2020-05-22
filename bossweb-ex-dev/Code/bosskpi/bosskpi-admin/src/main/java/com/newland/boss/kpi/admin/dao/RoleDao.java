package com.newland.boss.kpi.admin.dao;

import java.util.List;
import java.util.Map;

import com.newland.boss.kpi.admin.model.Role;

public interface RoleDao {
	List<Role> selectAllRole();
	void insertRole(Role role);
	void deleteRole(Integer roleId);
	Role selectRoleById(Map<String, Object> roleIdMap);
	void updateRole(Role role);
	List<Role> fuzzySearchRole(Map<String,Object> searchMap);
	List<Role> selectAllRolesWithResource(Role role);
	List<Role> selectOperatorRole(Map<String,Object> params);
}
