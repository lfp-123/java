package com.newland.boss.kpi.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.newland.boss.kpi.admin.model.Role;
import com.newland.boss.kpi.admin.service.RoleService;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;
import com.newland.boss.kpi.server.OperateLogService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
	private AppSession appsession;
	
	//查找所有角色
	@RequestMapping(value = "/showAllRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAllRole(){
		return getAllRoles();
	}
	
	//添加角色
	@RequestMapping(value = "/addRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addRole(@RequestBody Role role){
		roleService.addRole(role);
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(role.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(1);
		operateLog.setOperateDesc("添加角色信息：" + role);
		operateLogService.addLog(operateLog);
		
		List<Role> roleList = roleService.findAllRoles();
		return JSONArray.toJSONString(roleList);
	}
	
	//通过id查找角色
	@RequestMapping(value = "/showRoleById", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showRoleById(@RequestBody Role role){
		String respJson;
		Role theRole = roleService.findRole(role);
		if (theRole != null) {
			Gson gson = new Gson();
			respJson = gson.toJson(theRole);
		} else{
			respJson = "{\"find\":\"fail\"}";
		}
		
		return respJson;
	}
	
	//更新角色信息
	@RequestMapping(value = "/editRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editRole(@RequestBody Role role){
		roleService.editRole(role);
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(role.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(3);
		operateLog.setOperateDesc("修改角色信息：" + role);
		operateLogService.addLog(operateLog);
		
		//返回更新后的json集合到前台
		List<Role> roleList = roleService.findAllRoles();		
		return JSONArray.toJSONString(roleList);
	}
	
	//删除角色
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String deleteRole(@RequestBody List<Role> roles){
		User user = appsession.getUser();
		
		try {
			roleService.removeRole(roles);
			
			//添加操作日志到记录
			OperateLog operateLog = new OperateLog();
			operateLog.setOperatorId(user.getOperatorId());
			operateLog.setOperateModule(12);
			operateLog.setOperateType(2);
			StringBuilder roleIds = new StringBuilder();
			for (Role role : roles) {
				roleIds.append(role.getRoleId()).append(",");
			}
			operateLog.setOperateDesc("删除角色信息：" + roleIds);
			operateLogService.addLog(operateLog);
			
			return "{\"msg\":\"刪除成功\", \"code:\":\"0\"}";
		} catch (Exception e) {
			//违反完整约束条件
			String tmp = e.getMessage();
			if(tmp.contains("违反完整约束条件 ") || tmp.contains("integrity constraint")) {
				return "{\"msg\":\"有用户使用该角色，无法删除\", \"code:\":\"-1\"}";
			}
			return "{\"msg\":\"异常！" + e.getMessage() + "\", \"code:\":\"-1\"}";
		}
	}
	
	//搜索角色
	@RequestMapping(value = "/searchRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String searchRole(@RequestBody Role role){
		List<Role> roleList = roleService.searchRoles(role);
		return JSONArray.toJSONString(roleList);
	}
	
	//以grid表格形式返回所有的角色  
	@RequestMapping(value = "/showAllRoleGrid", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAllRoleGrid(@RequestBody Role role){
		List<Role> roleList = roleService.findAllRolesWithResource(role);
		return JSONArray.toJSONString(roleList);
	}
	
	//以菜单树形式返回所有角色列表 
	@RequestMapping(value = "/showRoleTree", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showRoleTree(){
		return getAllRoles();
	}
	
	private String getAllRoles() {
		List<Role> roleList = roleService.findAllRoles();
		return JSONArray.toJSONString(roleList);
	}
	
	//搜索用户角色
	@RequestMapping(value = "/searchOperatorRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String searchRole(@RequestBody Map<String, Object> params){
		List<Role> roleList = roleService.selectOperatorRole(params);
		return JSONArray.toJSONString(roleList);
	}
}
