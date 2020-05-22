package com.newland.boss.kpi.server;

import java.util.List;

import com.newland.boss.kpi.entity.Organization;

public interface OrgService {
	
	/**
	 * 获取所有机构
	 * @return
	 */
	List<Organization> getAllOrganization();
	
	/**
     * 初始化
     */
    void initCache();
    
    /**
	 * 获取机构缓存
	 * @return
	 */
    List<Organization> getAllOrganizationFromCache();
    
    /**
     * 从缓存删除机构
     */
	void removeOrg(Organization organization);
	
	/**
     * 修改缓存机构
     */
	void editOrg(Organization organization);
    
}
