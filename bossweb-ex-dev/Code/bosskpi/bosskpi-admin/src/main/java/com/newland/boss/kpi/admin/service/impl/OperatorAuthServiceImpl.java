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
import com.newland.boss.kpi.admin.dao.OperatorAuthDao;
import com.newland.boss.kpi.admin.model.OperatorAuth;
import com.newland.boss.kpi.admin.service.OperatorAuthService;

@Component
public class OperatorAuthServiceImpl implements OperatorAuthService {
	
	@Autowired
	private OperatorAuthDao operatorAuthDao;

	@Override
	public void addOperatorAuth(String operAuthJson) {
		

		
		//取传过来的角色ID和资源ID数组
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operAuthJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer operatorId = jsonObj.get("operatorId").getAsInt();     //选中的操作员
		JsonArray resourceArray = jsonObj.get("selectedResource").getAsJsonArray(); //选中的机构
		Integer modifyOperatorId = jsonObj.get("modifyOperatorId").getAsInt();
		
		System.out.println(operatorId);
		System.out.println(resourceArray);
		System.out.println(modifyOperatorId);
		
		if (operatorAuthDao.selectOperatorAuthByOperId(operatorId) != null) { 
			operatorAuthDao.deleteOperAuthByOperatorId(operatorId);
			//循环插入操作员机构管理关系表
			for (int i = 0;i < resourceArray.size();i++) { 
				OperatorAuth operAuth = new OperatorAuth();
				operAuth.setOperatorId(operatorId);
				operAuth.setResourceId(resourceArray.get(i).getAsInt());
				operAuth.setStatus(0);
				operAuth.setModifyOperatorId(modifyOperatorId);
				operatorAuthDao.insertOperatorAuth(operAuth);
			}
		}else {
			//循环插入操作员机构管理关系表
			for (int i = 0;i < resourceArray.size();i++) {
				OperatorAuth operAuth = new OperatorAuth();
				operAuth.setOperatorId(operatorId);
				operAuth.setResourceId(resourceArray.get(i).getAsInt());
				operAuth.setStatus(0);
				operAuth.setModifyOperatorId(modifyOperatorId);
				operatorAuthDao.insertOperatorAuth(operAuth);
			}
		}
	}

	@Override
	public List<OperatorAuth> findResourceOfOperator(OperatorAuth operatorAuth) {
		return operatorAuthDao.selectOperatorAuthByOperId(operatorAuth.getOperatorId());
	}

	@Override
	public OperatorAuth findAnOperatorAuth(OperatorAuth operatorAuth) {
		Map<String, Integer> operAuthMap = new HashMap<String, Integer>();
		operAuthMap.put("operatorId",operatorAuth.getOperatorId());
		operAuthMap.put("resourceId", operatorAuth.getResourceId());
		return operatorAuthDao.selectAnOperatorAuth(operAuthMap);
	}

	@Override
	public void removeOperatorAuth(String operAuthJson) {
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operAuthJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray authArray = jsonObj.get("resourceIdArray").getAsJsonArray(); //选中的机构
		Integer operatorId = jsonObj.get("operatorId").getAsInt();
		
		//循环删除操作员
		for (int i = 0;i < authArray.size();i++) {
			Map<String, Integer> operAuthMap = new HashMap<String, Integer>();
			operAuthMap.put("operatorId", operatorId);
			operAuthMap.put("resourceId", authArray.get(i).getAsInt());
			operatorAuthDao.deleteOperatorAuth(operAuthMap);
		}
	}

	
	
}
