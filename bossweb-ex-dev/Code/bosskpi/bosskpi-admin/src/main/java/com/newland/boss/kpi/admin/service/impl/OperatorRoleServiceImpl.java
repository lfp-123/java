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
import com.newland.boss.kpi.admin.dao.OperatorRoleDao;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.admin.model.OperatorRole;
import com.newland.boss.kpi.admin.model.Role;
import com.newland.boss.kpi.admin.service.OperatorRoleService;

@Component
public class OperatorRoleServiceImpl implements OperatorRoleService {
	@Autowired
	private OperatorRoleDao operatorRoleDao;

	@Override
	public void addOperatorRole(String operRoleJson) {
		//取传过来的操作员ID和角色ID数组
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operRoleJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer operatorId = jsonObj.get("operatorId").getAsInt();     //选中的操作员
		JsonArray roleArray = jsonObj.get("selectedRole").getAsJsonArray(); //选中的机构
		Integer modifyOperatorId = jsonObj.get("modifyOperatorId").getAsInt();
		
		if (operatorRoleDao.selectOperatorRoleByOperId(operatorId) != null) {
			operatorRoleDao.deleteOperatorRoleByOperId(operatorId);
			//循环插入操作员机构管理关系表
			for (int i = 0;i < roleArray.size();i++) {
				OperatorRole operatorRole = new OperatorRole();
				operatorRole.setOperatorId(operatorId);
				operatorRole.setRoleId(roleArray.get(i).getAsInt());
				operatorRole.setStatus(0);
				operatorRole.setModifyOperatorId(modifyOperatorId);
				operatorRoleDao.insertOperatorRole(operatorRole);
			}
		}else {
			//循环插入操作员机构管理关系表
			for (int i = 0;i < roleArray.size();i++) {
				OperatorRole operatorRole = new OperatorRole();
				operatorRole.setOperatorId(operatorId);
				operatorRole.setRoleId(roleArray.get(i).getAsInt());
				operatorRole.setStatus(0);
				operatorRole.setModifyOperatorId(modifyOperatorId);
				operatorRoleDao.insertOperatorRole(operatorRole);
			}
		}
	
	}

	@Override
	public List<OperatorRole> findRoleOfOperator(OperatorRole operatorRole) {
		return operatorRoleDao.selectOperatorRoleByOperId(operatorRole.getOperatorId());
	}

	@Override
	public OperatorRole findAnOperatorRole(OperatorRole operatorRole) {
		
		Map<String, Integer> operRoleMap = new HashMap<String, Integer>();
		operRoleMap.put("operatorId", operatorRole.getOperatorId());
		operRoleMap.put("roleId", operatorRole.getRoleId());
		
		return operatorRoleDao.selectAnOperatorRole(operRoleMap);
		
	}

	@Override
	public void removeOperRole(String operRoleJson) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operRoleJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray roleArray = jsonObj.get("roleIdArray").getAsJsonArray(); //选中的机构
		Integer operatorId = jsonObj.get("operatorId").getAsInt();
		
		//循环删除操作员角色关系
		for (int i = 0;i < roleArray.size();i++) {
			Map<String, Integer> operRoleMap = new HashMap<String, Integer>();
			operRoleMap.put("operatorId", operatorId);
			operRoleMap.put("roleId", roleArray.get(i).getAsInt());
			operatorRoleDao.deleteOperatorRole(operRoleMap);
		}
	}

	@Override
	public void editOperatorRole(String operRoleJson) {
		//取传过来的json里面的操作员ID和机构ID数组
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operRoleJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer operatorId = jsonObj.get("operatorId").getAsInt();     //选中的操作员
		Integer newRoleId = jsonObj.get("newRoleId").getAsInt(); //修改后的roleId
		Integer roleId = jsonObj.get("roleId").getAsInt();  //选中的角色
		Integer status = jsonObj.get("status").getAsInt();  
		Integer modifyOperatorId = jsonObj.get("modifyOperatorId").getAsInt();
		
		Map<String, Integer> operRoleMap = new HashMap<String, Integer>();
		operRoleMap.put("operatorId",operatorId);
		operRoleMap.put("newRoleId",newRoleId);
		operRoleMap.put("roleId",roleId);
		operRoleMap.put("status",status);
		operRoleMap.put("modifyOperatorId",modifyOperatorId);
		
		operatorRoleDao.updateOperatorRole(operRoleMap);
		
	}

	@Override
	public void addOperatorRole(Operator oper) {
		operatorRoleDao.deleteOperatorRoleByOperId(oper.getOperatorId());
		if(oper.getRoleList() != null && !oper.getRoleList().isEmpty()) {
			//循环插入操作员机构管理关系表
			for (Role role : oper.getRoleList()) {
				OperatorRole operatorRole = new OperatorRole();
				operatorRole.setOperatorId(oper.getOperatorId());
				operatorRole.setRoleId(role.getRoleId());
				operatorRole.setStatus(0);
				operatorRole.setModifyOperatorId(oper.getModifyOperatorId());
				operatorRoleDao.insertOperatorRole(operatorRole);
			}
		}
	}

	@Override
	public void removeOperatorRole(List<Operator> opers) {
		if(opers == null || opers.isEmpty()) {
			return;
		}
		for (Operator operator : opers) {
			operatorRoleDao.deleteOperatorRoleByOperId(operator.getOperatorId());
		}
	}
	
}
