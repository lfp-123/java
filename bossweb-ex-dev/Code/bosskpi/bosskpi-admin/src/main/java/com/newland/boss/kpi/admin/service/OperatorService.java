package com.newland.boss.kpi.admin.service;

import java.util.List;
import java.util.Map;

import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.entity.Organization;

public interface OperatorService {
	boolean loginJudge (Operator operator);
	List<Operator> findAllOperator(Map<String, Object> params);
	Operator showOneOperator(Operator operator);
	void editOperator(Operator operator);
	void addOperator(Operator operator);
	List<Operator> searchOperator(Map<String, Object> params);
	void removeOperator(String operJson);
	List<Operator> findIdAndNameByOrgId(Organization organization);
	void removeOperator(List<Operator> opers);
	List<Operator> searchOperatorWithRole(Map<String, Object> params);
	void insertOperatorHisById(int operatorId);
	void resetOpersPwd(List<Operator> opers, int modifyOperatorId);
	void changeOperPwd(Map<String, Object> params);
	Operator findByLoginName(String loginName);
}
