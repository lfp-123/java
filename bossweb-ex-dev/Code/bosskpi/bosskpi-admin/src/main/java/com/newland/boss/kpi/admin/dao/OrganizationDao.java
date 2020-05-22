package com.newland.boss.kpi.admin.dao;

import java.util.List;
import java.util.Map;

import com.newland.boss.kpi.entity.Organization;


public interface OrganizationDao {
	void insertOrganization(Organization organization);
	List<Organization> selectAllOrganization();
	Organization selectOrganizationById(Integer orgId);
	void updateOrganization(Organization organization);
	void deleteOrganization(Integer orgId);
	List<Organization> fuzzySearchOrganization(Map<String, Object> searchMap);
	Organization selectOrganizationByOrgName(String orgName);
	Organization findByFullName(String fullName);
	List<Organization> selectHeadOrgInfos(int orgId);
	List<Organization> selectOrgByOperatorId(Integer operatorId); 
	List<Organization> selectSubLeafBySubRootId(int orgId);
	List<Organization> selectAllRootOrgId();
}
