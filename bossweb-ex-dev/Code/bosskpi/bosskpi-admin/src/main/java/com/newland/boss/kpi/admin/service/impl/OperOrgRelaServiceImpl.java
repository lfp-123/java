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
import com.newland.boss.kpi.admin.dao.OperOrgRelaDao;
import com.newland.boss.kpi.admin.dao.OperatorDao;
import com.newland.boss.kpi.admin.dao.OrganizationDao;
import com.newland.boss.kpi.admin.model.OperOrgRela;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.admin.service.OperOrgRelaService;
import com.newland.boss.kpi.entity.Organization;

@Component
public class OperOrgRelaServiceImpl implements OperOrgRelaService {
	
	@Autowired
	private OperOrgRelaDao operOrgRelaDao;
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Override
	public void editOperOrgRela(String operOrgJson) {
		
		//取传过来的操作员ID和机构ID数组
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operOrgJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer operatorId = jsonObj.get("operatorId").getAsInt();     //选中的操作员
		JsonArray orgArray = jsonObj.get("orgNameArray").getAsJsonArray(); //选中的机构名
		Integer modifyOperatorId = jsonObj.get("modifyOperatorId").getAsInt();
		
		if (operOrgRelaDao.selectOperOrgRelaByOperId(operatorId) != null) {
			
			operOrgRelaDao.deleteOperOrgRelaByOperId(operatorId);
			
			//循环插入操作员机构管理关系表
			for(int i = 0;i < orgArray.size();i++) {
				String orgName = orgArray.get(i).getAsString();
				Integer orgId = organizationDao.selectOrganizationByOrgName(orgName).getOrgId();
				OperOrgRela operOrgRela = new OperOrgRela();
				operOrgRela.setOperatorId(operatorId);
				operOrgRela.setOrgId(orgId);
				operOrgRela.setRelaStatus(0);
				operOrgRela.setModifyOperatorId(modifyOperatorId);
				operOrgRelaDao.insertOperOrgRela(operOrgRela);
			}
			
		}else {
			
			//循环插入操作员机构管理关系表
			for(int i = 0;i < orgArray.size();i++) {
				String orgName = orgArray.get(i).getAsString();
				Integer orgId = organizationDao.selectOrganizationByOrgName(orgName).getOrgId();
				OperOrgRela operOrgRela = new OperOrgRela();
				operOrgRela.setOperatorId(operatorId);
				operOrgRela.setOrgId(orgId);
				operOrgRela.setRelaStatus(0);
				operOrgRela.setModifyOperatorId(modifyOperatorId);
				operOrgRelaDao.insertOperOrgRela(operOrgRela);
			}
		}
		
	}

	@Override
	public List<OperOrgRela> findOrgOfOper(OperOrgRela operOrgRela) {
		return operOrgRelaDao.selectOperOrgRelaByOperId(operOrgRela.getOperatorId());
	}

	@Override
	public OperOrgRela findAnOperOrgRela(OperOrgRela operOrgRela) {
		
		Map<String, Integer> operOrgRelaMap = new HashMap<String, Integer>();
		operOrgRelaMap.put("operatorId", operOrgRela.getOperatorId());
		operOrgRelaMap.put("orgId", operOrgRela.getOrgId());
		
		return operOrgRelaDao.selectAnOperOrgRela(operOrgRelaMap);
	}

	@Override
	public void removeOperOrgRela(String operOrgJson) {
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operOrgJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray orgArray = jsonObj.get("orgIdArray").getAsJsonArray(); //选中的机构
		Integer operatorId = jsonObj.get("operatorId").getAsInt();
		
		//循环删除操作员
		for (int i = 0;i < orgArray.size();i++) {
			Map<String, Integer> operOrgRelaMap = new HashMap<String, Integer>();
			operOrgRelaMap.put("operatorId", operatorId);
			operOrgRelaMap.put("orgId", orgArray.get(i).getAsInt());
			operOrgRelaDao.deleteOperOrgRela(operOrgRelaMap);
		}
		
	}

	@Override
	public void addOrgForOper(String orgNameArrayJson) {
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(orgNameArrayJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray orgArray = jsonObj.get("orgNameArray").getAsJsonArray(); //选中的机构
		Integer modifyOperatorId = jsonObj.get("modifyOperatorId").getAsInt();
		
		int operatorId = operatorDao.selectLastOperator().getOperatorId();

		for(int i = 0;i < orgArray.size();i++) {
			String orgName = orgArray.get(i).getAsString();
			Integer orgId = organizationDao.selectOrganizationByOrgName(orgName).getOrgId();
			OperOrgRela operOrgRela = new OperOrgRela();
			operOrgRela.setOperatorId(operatorId);
			operOrgRela.setOrgId(orgId);
			operOrgRela.setRelaStatus(0);
			operOrgRela.setModifyOperatorId(modifyOperatorId);
			operOrgRelaDao.insertOperOrgRela(operOrgRela);
		}
		
	}

	@Override
	public void addOrgForOper(Operator operator, List<Organization> orgList) {
		if(orgList == null || orgList.isEmpty()) {
			return;
		}
		for (Organization organization : orgList) {
			String fullName = organization.getOrgNameFull();
            Organization org = organizationDao.findByFullName(fullName);
            OperOrgRela operOrgRela = new OperOrgRela();
            operOrgRela.setOperatorId(operator.getOperatorId());
            operOrgRela.setOrgId(org.getOrgId());
            operOrgRela.setRelaStatus(0);
            operOrgRela.setModifyOperatorId(operator.getModifyOperatorId());
            operOrgRelaDao.insertOperOrgRela(operOrgRela);

		}
	}

	@Override
	public void editOperOrgRela(Operator operator, List<Organization> orgList) {
		if(orgList == null || orgList.isEmpty()) {
			return;
		}
		
		OperOrgRela operOrgRela = new OperOrgRela();
		operOrgRela.setOperatorId(operator.getOperatorId());
		operOrgRela.setModifyOperatorId(operator.getModifyOperatorId());
		operOrgRelaDao.updateStatusOperOrgRelaByOperId(operOrgRela);
		//循环插入操作员机构管理关系表
		for (Organization organization : orgList) {
			operOrgRela = new OperOrgRela();
			operOrgRela.setOperatorId(operator.getOperatorId());
			operOrgRela.setOrgId(organization.getOrgId());
			operOrgRela.setRelaStatus(0);
			operOrgRela.setModifyOperatorId(operator.getModifyOperatorId());
			operOrgRelaDao.insertOperOrgRela(operOrgRela);
		}
	}

	@Override
	public void removeOperOrgRela(List<Operator> opers, Integer modifyOperatorId) {
		if(opers == null || opers.isEmpty()) {
			return;
		}
		for (Operator operator : opers) {
			OperOrgRela operOrgRela = new OperOrgRela();
			operOrgRela.setOperatorId(operator.getOperatorId());
			operOrgRela.setModifyOperatorId(modifyOperatorId);
			operOrgRelaDao.updateStatusOperOrgRelaByOperId(operOrgRela);
//			operOrgRelaDao.deleteOperOrgRelaByOperId(operator.getOperatorId())
		}
	}
	
}
