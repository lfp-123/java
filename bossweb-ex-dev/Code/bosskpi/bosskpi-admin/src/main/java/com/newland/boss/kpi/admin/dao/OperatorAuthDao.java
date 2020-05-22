package com.newland.boss.kpi.admin.dao;

import java.util.List;
import java.util.Map;
import com.newland.boss.kpi.admin.model.OperatorAuth;

public interface OperatorAuthDao {
	void insertOperatorAuth(OperatorAuth operatorAuth);
	List<OperatorAuth> selectOperatorAuthByOperId(Integer operatorId);
	OperatorAuth selectAnOperatorAuth(Map<String, Integer> operAuthMap);
	void deleteOperatorAuth(Map<String, Integer> operAuthMap);
	void deleteOperAuthByOperatorId(Integer operatorId);
}
