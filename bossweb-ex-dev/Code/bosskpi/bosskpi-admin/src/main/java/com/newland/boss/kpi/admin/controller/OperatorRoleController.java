package com.newland.boss.kpi.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.admin.model.OperatorRole;
import com.newland.boss.kpi.admin.model.Role;
import com.newland.boss.kpi.admin.service.OperatorRoleService;
import com.newland.boss.kpi.admin.service.OperatorService;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;
import com.newland.boss.kpi.server.OperateLogService;
import com.newland.boss.kpi.util.MD5Util;

@RestController
public class OperatorRoleController {
	
	@Autowired
	private OperatorRoleService operatorRoleService;
	
	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private AppSession appSession;
	
	//根据操作员ID查找所有操作员角色关系
	@RequestMapping(value = "/showOperRoleByOperId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showOperRoleByOperId(@RequestBody OperatorRole operatorRole){
		
		//查询数据库数据并封装成Json集合发送到前台
		List<OperatorRole> operRoleList = operatorRoleService.findRoleOfOperator(operatorRole);
		List<Integer> roleIdList = new ArrayList<>();
		
		for (int i = 0;i < operRoleList.size();i++) {
			roleIdList.add(operRoleList.get(i).getRoleId());
		}
		
		Gson gson = new Gson();
		return gson.toJson(roleIdList);
	
	}
		
	@RequestMapping(value = "/showAnOperatorRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAnOperatorRole(@RequestBody OperatorRole operatorRole){
		
		OperatorRole operRole =  operatorRoleService.findAnOperatorRole(operatorRole);
		
		String respJson;
		Gson gson = new Gson();
		respJson = gson.toJson(operRole);
		return respJson;
	}
	
	//删除操作员角色关系
	@RequestMapping(value = "/removeOperatorRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String removeOperatorRole(@RequestBody String operRoleJson){
		
		operatorRoleService.removeOperRole(operRoleJson);
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operRoleJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer operatorId = jsonObj.get("operatorId").getAsInt();
		
		OperatorRole operatorRole = new OperatorRole();
		operatorRole.setOperatorId(operatorId);
		List<OperatorRole> operRoleList = operatorRoleService.findRoleOfOperator(operatorRole);
		Gson gson = new Gson();
		return gson.toJson(operRoleList);
	}
	
	//编辑操作员角色关系 
	@RequestMapping(value = "/editOperatorRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editOperatorRole(@RequestBody Operator oper){
		operatorRoleService.addOperatorRole(oper);
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(oper.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(3);
		StringBuilder roleIds = new StringBuilder();
		for (Role role : oper.getRoleList()) {
			roleIds.append(role.getRoleId()).append(",");
		}
		operateLog.setOperateDesc("修改帐号[" + oper.getOperatorId() + "]角色信息：" + roleIds);
		operateLogService.addLog(operateLog);
		
		return "{\"msg\":\"修改成功\", \"code:\":\"0\"}";
	}
	
	//检查用户角色
	@RequestMapping(value = "/checkUserRole", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String checkUserRole(@RequestBody int loginType){
		User user = appSession.getUser();
		if(user == null) {
			return "{\"msg\":\"获取登录用户失败！\", \"code\":-2}";
		}
		
		String msg = "正常";
		int code = 0;
		String errMsg = "";
		
		Operator param = new Operator();
		param.setOperatorId(user.getOperatorId());
		Operator operator = operatorService.showOneOperator(param);
		if(MD5Util.Encrypt("123456").equals(operator.getPassword()) && loginType == 0) {
			code = -1;
			errMsg += "当前密码为默认，请修改密码!<br/>";
		}
		
		OperatorRole operRole = new OperatorRole();
		operRole.setOperatorId(user.getOperatorId());
		
		List<OperatorRole> operRoleList = operatorRoleService.findRoleOfOperator(operRole);
		if(operRoleList == null ||operRoleList.isEmpty()) {
			code = -1;
			errMsg += "用户没有分配角色，请通知管理员!";
		}
		
		JSONObject returnMsg = new JSONObject();
		returnMsg.put("msg", code == 0 ? msg : errMsg);
		returnMsg.put("code", code);
		return returnMsg.toJSONString();
	}
	
}
