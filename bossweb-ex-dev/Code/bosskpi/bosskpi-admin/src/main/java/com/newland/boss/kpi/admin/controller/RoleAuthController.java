package com.newland.boss.kpi.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.kpi.admin.model.Resource;
import com.newland.boss.kpi.admin.model.Role;
import com.newland.boss.kpi.admin.model.RoleAuth;
import com.newland.boss.kpi.admin.service.RoleAuthService;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.server.OperateLogService;

@RestController
public class RoleAuthController {
	
	@Autowired
	private RoleAuthService roleAuthService;
	
	@Autowired
	private OperateLogService operateLogService;
		
	@RequestMapping(value = "/addRoleResource", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addRoleResource(@RequestBody Role roleAuth){
		roleAuthService.addRoleAuth(roleAuth);
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(roleAuth.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(3);
		StringBuilder resIds = new StringBuilder();
		for (Resource resource : roleAuth.getResourceList()) {
			resIds.append(resource.getResourceId()).append(",");
		}
		operateLog.setOperateDesc("修改角色[" + roleAuth.getRoleId() + "]资源信息：" + resIds);
		operateLogService.addLog(operateLog);
		
		return "{\"msg\":\"添加成功\", \"code:\":\"0\"}";
	}
		
	//根据角色ID查找角色资源关系
	@RequestMapping(value = "/showAuthRoleByRoleId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAuthRoleByRoleId(@RequestBody RoleAuth roleAuth){
		
		List<RoleAuth> roAuth = roleAuthService.findAuthOfRole(roleAuth);
		List<Integer> roleIdList = new ArrayList<>();
		
		for(int i = 0 ; i < roAuth.size() ; i++) {
			roleIdList.add(roAuth.get(i).getResourceId()); 
		}
		
		Gson gson = new Gson();
		return  gson.toJson(roleIdList);
	}
	
	//查找一组角色资源关系
	@RequestMapping(value = "/showAnRoleAuth", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAnRoleAuth(@RequestBody RoleAuth roleAuth){
		RoleAuth roAuth = roleAuthService.findAnRoleAuth(roleAuth);
		Gson gson = new Gson();
		return gson.toJson(roAuth);
		
	}
	
	//删除选中的角色资源关系
	@RequestMapping(value = "/removeRoleResource", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String removeRoleResource(@RequestBody String roleAuthJson){

		roleAuthService.removeRoleAuth(roleAuthJson);
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(roleAuthJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer roleId = jsonObj.get("roleId").getAsInt();
		
		RoleAuth roleAuth = new RoleAuth();
		roleAuth.setRoleId(roleId);
		List<RoleAuth> roleAuthList = roleAuthService.findAuthOfRole(roleAuth);
		Gson gson = new Gson();
		return gson.toJson(roleAuthList);
			
	}
	
	//编辑一组角色资源关系
	@RequestMapping(value = "/editRoleResource", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editRoleResource(@RequestBody String roleAuthJson){
		
		roleAuthService.editRoleAuth(roleAuthJson);
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(roleAuthJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer roleId = jsonObj.get("roleId").getAsInt();
		
		RoleAuth roleAuth = new RoleAuth();
		roleAuth.setRoleId(roleId);
		List<RoleAuth> roleAuthList = roleAuthService.findAuthOfRole(roleAuth);
		Gson gson = new Gson();
		return gson.toJson(roleAuthList);
	}
	
	
}
