package com.newland.boss.kpi.admin.dao;

import java.util.List;
import java.util.Map;

import com.newland.boss.kpi.admin.model.Operator;

public interface OperatorDao {
	Operator selectOpeByIdAndPassword(Map<String, Object> map);
	List<Operator> selectAllOperator();
	List<Operator> selectAllOperator(Map<String, Object> params);
	Operator selectOpeById(Operator operator);
	void updateOperator(Operator operator);
	void insertOperator(Operator operator);
	List<Operator> fuzzySearchOperator(Map<String, Object> searchMap);
	void deleteOperator(Integer operatorId);
	Operator selectLastOperator();
	List<Operator> selectIdAndNameByOrgId(Integer orgId);
	void insertOperatorHisById(int operatorId);
	void resetOpersPwd(Map<String, Object> params);
	void changeOperPwd(Map<String, Object> params);
	Operator findByLoginName(String loginName);
}
