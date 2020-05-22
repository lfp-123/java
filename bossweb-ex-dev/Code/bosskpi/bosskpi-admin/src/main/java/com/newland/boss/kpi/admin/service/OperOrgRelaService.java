package com.newland.boss.kpi.admin.service;

import java.util.List;

import com.newland.boss.kpi.admin.model.OperOrgRela;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.entity.Organization;

public interface OperOrgRelaService {
	List<OperOrgRela> findOrgOfOper(OperOrgRela operOrgRela);
	OperOrgRela findAnOperOrgRela(OperOrgRela operOrgRela);
	void editOperOrgRela(String operOrgJson);
	void removeOperOrgRela(String operOrgJson);
	void addOrgForOper(String orgNameArrayJson);
	void addOrgForOper(Operator operator, List<Organization> orgList);
	void editOperOrgRela(Operator operator, List<Organization> orgList);
	void removeOperOrgRela(List<Operator> opers, Integer modifyOperatorId);
}
