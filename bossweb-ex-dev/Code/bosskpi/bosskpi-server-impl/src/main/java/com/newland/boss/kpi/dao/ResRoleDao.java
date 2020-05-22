package com.newland.boss.kpi.dao;

import java.util.List;
import com.newland.boss.kpi.entity.ResourceRole;

public interface ResRoleDao {
	List<ResourceRole> selectRoleAliasByResourceUrl();
}
