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
import com.newland.boss.kpi.admin.dao.RoleAuthDao;
import com.newland.boss.kpi.admin.model.Resource;
import com.newland.boss.kpi.admin.model.Role;
import com.newland.boss.kpi.admin.model.RoleAuth;
import com.newland.boss.kpi.admin.service.RoleAuthService;

@Component
public class RoleAuthServiceImpl implements RoleAuthService {
	
	@Autowired
	private RoleAuthDao roleAuthDao;

	@Override
	public void addRoleAuth(String roleAuthJson) {
		
		//取传过来的角色ID和资源ID数组
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(roleAuthJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer roleId = jsonObj.get("roleId").getAsInt();     //选中的操作员
		JsonArray resourceArray = jsonObj.get("selectedResource").getAsJsonArray(); //选中的机构
		Integer modifyOperatorId = jsonObj.get("modifyOperatorId").getAsInt();
		
		if (roleAuthDao.selectAuthByRoleId(roleId) != null) {
			roleAuthDao.deleteAuthByRoleId(roleId);
			//循环插入操作员机构管理关系表
			for (int i = 0;i < resourceArray.size();i++) {
				
				RoleAuth roleAuth = new RoleAuth();
				roleAuth.setRoleId(roleId);
				roleAuth.setResourceId(resourceArray.get(i).getAsInt());
				roleAuth.setStatus(0);
				roleAuth.setModifyOperatorId(modifyOperatorId);
				roleAuthDao.insertRoleAuth(roleAuth);
			}
		}else {
			//循环插入操作员机构管理关系表
			for (int i = 0;i < resourceArray.size();i++) {
				
				RoleAuth roleAuth = new RoleAuth();
				roleAuth.setRoleId(roleId);
				roleAuth.setResourceId(resourceArray.get(i).getAsInt());
				roleAuth.setStatus(0);
				roleAuth.setModifyOperatorId(modifyOperatorId);
				roleAuthDao.insertRoleAuth(roleAuth);
			}
		}
		
		
	}

	@Override
	public RoleAuth findAnRoleAuth(RoleAuth roleAuth) {
		Map<String, Integer> roleAuthMap = new HashMap<String, Integer>();
		roleAuthMap.put("roleId", roleAuth.getRoleId());
		roleAuthMap.put("resourceId", roleAuth.getResourceId());
		return roleAuthDao.selectAnRoleAuth(roleAuthMap);
	}

	@Override
	public void removeRoleAuth(String roleAuthJson) {
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(roleAuthJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray authArray = jsonObj.get("resourceIdArray").getAsJsonArray(); //选中的机构
		Integer roleId = jsonObj.get("roleId").getAsInt();
		
		//循环删除操作员
		for (int i = 0;i < authArray.size();i++) {
			Map<String, Integer> roleAuthMap = new HashMap<String, Integer>();
			roleAuthMap.put("roleId",roleId);
			roleAuthMap.put("resourceId",authArray.get(i).getAsInt());
			roleAuthDao.deleteRoleAuth(roleAuthMap);
		}
	}

	@Override
	public void editRoleAuth(String roleAuthJson) {
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(roleAuthJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer roleId = jsonObj.get("roleId").getAsInt();     //选中的角色
		Integer newResourceId = jsonObj.get("newResourceId").getAsInt(); //修改后的资源ID
		Integer resourceId = jsonObj.get("resourceId").getAsInt();  //选中的资源
		Integer status = jsonObj.get("status").getAsInt();  
		Integer modifyOperatorId = jsonObj.get("modifyOperatorId").getAsInt();
		
		Map<String, Integer> roleAuthMap = new HashMap<String, Integer>();
		roleAuthMap.put("roleId",roleId);
		roleAuthMap.put("newResourceId",newResourceId);
		roleAuthMap.put("resourceId",resourceId);
		roleAuthMap.put("status",status);
		roleAuthMap.put("modifyOperatorId",modifyOperatorId);
		
		roleAuthDao.updateRoleAuth(roleAuthMap);
	}

	@Override
	public List<RoleAuth> findAuthOfRole(RoleAuth roleAuth) {
		return roleAuthDao.selectAuthByRoleId(roleAuth.getRoleId());
	}

	@Override
	public void removeAuthByRoleId(Integer roleId) {
		roleAuthDao.deleteAuthByRoleId(roleId);
	}

	@Override
	public void addRoleAuth(Role role) {
		roleAuthDao.deleteAuthByRoleId(role.getRoleId());
		if(role.getResourceList() != null && !role.getResourceList().isEmpty()) {
			for (Resource resource : role.getResourceList()) {
				RoleAuth newRoleAuth = new RoleAuth();
				newRoleAuth.setRoleId(role.getRoleId());
				newRoleAuth.setResourceId(resource.getResourceId());
				newRoleAuth.setStatus(0);
				newRoleAuth.setModifyOperatorId(role.getModifyOperatorId());
				roleAuthDao.insertRoleAuth(newRoleAuth);
			}
		}
	}
}
