package com.newland.boss.kpi.admin.dao;

import java.util.List;
import java.util.Map;

import com.newland.boss.kpi.admin.model.RoleAuth;

public interface RoleAuthDao {
	void insertRoleAuth(RoleAuth roleAuth);
	RoleAuth selectAnRoleAuth(Map<String, Integer> roleAuthMap);
	void deleteRoleAuth(Map<String, Integer> roleAuthMap);
	void updateRoleAuth(Map<String, Integer> roleAuthMap);
	List<RoleAuth> selectAuthByRoleId(Integer roleId);
	void deleteAuthByRoleId(Integer roleId);
}
