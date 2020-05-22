package com.newland.boss.kpi.admin.service;

import java.util.List;

import com.newland.boss.kpi.admin.model.OperatorAuth;

public interface OperatorAuthService {
	void addOperatorAuth(String operAuthJson);
	List<OperatorAuth> findResourceOfOperator(OperatorAuth operatorAuth);
	OperatorAuth findAnOperatorAuth(OperatorAuth operatorAuth);
	void removeOperatorAuth(String operAuthJson);
}
