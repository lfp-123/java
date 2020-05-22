package com.newland.boss.kpi.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.kpi.admin.dao.RoleDao;
import com.newland.boss.kpi.admin.model.Role;
import com.newland.boss.kpi.admin.service.RoleService;

@Component
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> findAllRoles() {
		return roleDao.selectAllRole();
	}

	@Override
	public void addRole(Role role) {
		roleDao.insertRole(role);
	}

	@Override
	public void removeRole(String roleJson) {
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(roleJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray roleArray = jsonObj.get("roleIdArray").getAsJsonArray(); //选中的机构
		
		//循环删除角色
		for (int i = 0;i < roleArray.size();i++) {
			Integer roleId = roleArray.get(i).getAsInt();
			roleDao.deleteRole(roleId);
		}
	}

	@Override
	public Role findRole(Role role) {
		Map<String, Object>  roleIdMap = new HashMap<>();
		roleIdMap.put("roleId", role.getRoleId());
		return roleDao.selectRoleById(roleIdMap);
	}

	@Override
	public void editRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public List<Role> searchRoles(Role role) {
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("roleId", role.getRoleId());
		searchMap.put("roleName", role.getRoleName());
		return roleDao.fuzzySearchRole(searchMap);
	}

	@Override
	public void removeRole(List<Role> roles) {
		if(roles == null || roles.isEmpty()) {
			return;
		}
		for (Role role : roles) {
			roleDao.deleteRole(role.getRoleId());
		}
	}

	@Override
	public List<Role> findAllRolesWithResource(Role role) {
		return roleDao.selectAllRolesWithResource(role);
	}

	@Override
	public List<Role> selectOperatorRole(Map<String, Object> params) {
		return roleDao.selectOperatorRole(params);
	}

}
