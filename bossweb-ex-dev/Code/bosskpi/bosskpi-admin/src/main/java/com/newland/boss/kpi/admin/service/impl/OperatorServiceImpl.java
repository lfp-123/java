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
import com.newland.boss.kpi.admin.dao.OperatorDao;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.admin.service.OperatorService;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.util.MD5Util;

@Component
public class OperatorServiceImpl implements OperatorService {
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Override
	public boolean loginJudge(Operator operator) {
		
		Integer operatorId = operator.getOperatorId();
		String password = MD5Util.Encrypt(operator.getPassword());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("operatorId", operatorId);
		map.put("password", password);

		return operatorDao.selectOpeByIdAndPassword(map) != null;
	}

	@Override
	public List<Operator> findAllOperator(Map<String, Object> params) {
		return operatorDao.selectAllOperator(params);
	}

	@Override
	public Operator showOneOperator(Operator operator) {
		return operatorDao.selectOpeById(operator);
	}

	@Override
	public void editOperator(Operator operator) {
		operatorDao.updateOperator(operator);
	}

	@Override
	public void addOperator(Operator operator) {
		operatorDao.insertOperator(operator);
	}

	@Override
	public List<Operator> searchOperator(Map<String, Object> params) {
		return operatorDao.fuzzySearchOperator(params);

	}

	@Override
	public void removeOperator(String operJson) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray operArray = jsonObj.get("operatorIdArray").getAsJsonArray(); //选中的机构
		
		//循环删除操作员
		for (int i = 0;i < operArray.size();i++) {
			Integer operatorId = operArray.get(i).getAsInt();
			operatorDao.deleteOperator(operatorId);
		}
	}

	@Override
	public List<Operator> findIdAndNameByOrgId(Organization organization) {
		return operatorDao.selectIdAndNameByOrgId(organization.getOrgId());
	}

	@Override
	public void removeOperator(List<Operator> opers) {
		if(opers == null || opers.isEmpty()) {
			return;
		}
		//循环删除操作员
		for (Operator operator : opers) {
			operatorDao.insertOperatorHisById(operator.getOperatorId());
			operatorDao.deleteOperator(operator.getOperatorId());
		}
	}

	@Override
	public List<Operator> searchOperatorWithRole(Map<String, Object> params) {
		return operatorDao.fuzzySearchOperator(params);
	}

	@Override
	public void insertOperatorHisById(int operatorId) {
		operatorDao.insertOperatorHisById(operatorId);
	}

	@Override
	public void resetOpersPwd(List<Operator> opers, int modifyOperatorId) {
		if(opers == null || opers.isEmpty()) {
			return;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("password", MD5Util.Encrypt("123456"));
		params.put("modifyOperatorId", modifyOperatorId);
		params.put("opers", opers);
		operatorDao.resetOpersPwd(params);
	}

	@Override
	public void changeOperPwd(Map<String, Object> params) {
		if(params == null) {
			return;
		}
		operatorDao.changeOperPwd(params);
	}

	@Override
	public Operator findByLoginName(String loginName) {
		return operatorDao.findByLoginName(loginName);
	}

}
