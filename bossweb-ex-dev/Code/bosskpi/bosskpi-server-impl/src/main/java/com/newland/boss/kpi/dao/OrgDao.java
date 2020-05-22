package com.newland.boss.kpi.dao;

import java.util.List;

import com.newland.boss.kpi.entity.Organization;

public interface OrgDao {
	
	/**
	 * 获取所有机构
	 * @return
	 */
	public List<Organization> getAllOrganization();
}
