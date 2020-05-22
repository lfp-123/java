package com.newland.boss.kpi.admin.dao;

import java.util.List;
import java.util.Map;

import com.newland.boss.kpi.admin.model.OperOrgRela;

public interface OperOrgRelaDao {
	void insertOperOrgRela(OperOrgRela operOrgRela);
	List<OperOrgRela> selectOperOrgRelaByOperId(Integer operatorId);
	OperOrgRela selectAnOperOrgRela(Map<String,Integer> operOrgRelaMap);
	void updateOperOrgRela(Map<String, Integer> operOrgMap);
	void deleteOperOrgRela(Map<String,Integer> operOrgRelaMap);
	void deleteOperOrgRelaByOperId(Integer operatorId);
	void updateStatusOperOrgRelaByOperId(OperOrgRela operOrgRela);
	void updateStatusOperOrgRelaByOrgId(OperOrgRela operOrgRela);
}
