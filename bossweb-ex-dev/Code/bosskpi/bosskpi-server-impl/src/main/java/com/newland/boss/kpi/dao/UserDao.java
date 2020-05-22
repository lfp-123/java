package com.newland.boss.kpi.dao;

import com.newland.boss.kpi.entity.User;

public interface UserDao {
	User selectOperatorById(Integer operatorId);

	String selectOrgNameFullByOperatorId(int operatorId);

	int selectByFullName(String orgNameFull);
	
	User selectOperatorByLoginName(String loginName);

	void resetErrCount(User user);

	void updateErrCount(Integer operatorId);
}
