package com.newland.boss.kpi.admin.service;

import java.util.List;

import com.newland.boss.kpi.entity.Organization;

public interface OrganizationService {
	void addOrg(Organization organization);
	List<Organization> showAllOrg();
	Organization findOrgById(Organization organization);
	void editOrg(Organization organization);
	void removeOrg(Organization organization, Integer modifyOperatorId);
	List<Organization> searchOrg(Organization organization);
	Organization findByFullName(String fullName);
	List<Organization> selectHeadOrgInfos(int orgId);
	List<Organization> findOperByOperatorId(int operatorId); 
	List<Organization> selectSubLeafBySubRootId(int orgId);
	List<Organization> selectAllRootOrgId();
	List<Organization> selectOrgTreeInfos();
}
