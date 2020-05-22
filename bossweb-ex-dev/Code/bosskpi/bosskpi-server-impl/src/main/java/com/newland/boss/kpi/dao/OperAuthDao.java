package com.newland.boss.kpi.dao;

import java.util.List;
import com.newland.boss.kpi.entity.Resource;

public interface OperAuthDao {
	List<Resource> selectResourceByOperId(Integer operatorId);
	List<Resource> selectResourceByRoleId(Integer roleId);
}
