package com.newland.boss.kpi.admin.dao;

import java.util.List;
import java.util.Map;
import com.newland.boss.kpi.admin.model.OperatorRole;

public interface OperatorRoleDao {
	void insertOperatorRole(OperatorRole operatorRole);
	List<OperatorRole> selectOperatorRoleByOperId(Integer operatorId);
	OperatorRole selectAnOperatorRole(Map<String, Integer> operRoleMap);
	void deleteOperatorRole(Map<String, Integer> operRoleMap);
	void updateOperatorRole(Map<String, Integer> operRoleMap);
	void deleteOperatorRoleByOperId(Integer operatorId);
}
