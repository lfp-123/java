package com.newland.boss.kpi.admin.service;

import java.util.List;

import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.admin.model.OperatorRole;

public interface OperatorRoleService {
	void addOperatorRole(String operRoleJson);
	List<OperatorRole> findRoleOfOperator(OperatorRole operatorRole);
	OperatorRole findAnOperatorRole(OperatorRole operatorRole);
	void removeOperRole(String operRoleJson);
	void editOperatorRole(String operRoleJson);
	void addOperatorRole(Operator oper);
	void removeOperatorRole(List<Operator> opers);
}
