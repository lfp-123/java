package com.newland.boss.kpi.dao;

import java.util.List;
import com.newland.boss.kpi.entity.Role;

public interface UserRoleDao {
	List<Role> selectRoleByOperatorId(Integer operatorId);
}
