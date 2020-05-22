package com.newland.boss.kpi.admin.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newland.boss.kpi.admin.dao.OperOrgRelaDao;
import com.newland.boss.kpi.admin.dao.OrganizationDao;
import com.newland.boss.kpi.admin.model.OperOrgRela;
import com.newland.boss.kpi.admin.service.OrganizationService;
import com.newland.boss.kpi.entity.Organization;

@Component
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private OperOrgRelaDao operOrgRelaDao;

	@Override
	public void addOrg(Organization organization) {
		if(-1 == organization.getSuperOrgId()) {
			organization.setOrgNameFull(organization.getOrgName());
		} else {
			Organization superOrg = organizationDao.selectOrganizationById(organization.getSuperOrgId());
			organization.setOrgNameFull(superOrg.getOrgNameFull() + "\\" + organization.getOrgName());
		}
		organizationDao.insertOrganization(organization);
	}

	@Override
	public List<Organization> showAllOrg() {
		return organizationDao.selectAllOrganization();
	}

	@Override
	public Organization findOrgById(Organization organization) {
		return organizationDao.selectOrganizationById(organization.getOrgId());
	}

	@Override
	public void editOrg(Organization organization) {
		String tmp = organization.getOrgNameFull();
		int index = tmp.lastIndexOf('\\');
		if(index != -1) {
			tmp = tmp.substring(0, index) + "\\" + organization.getOrgName();
			organization.setOrgNameFull(tmp);
		} else {
			organization.setOrgNameFull(organization.getOrgName());
		}
		organizationDao.updateOrganization(organization);
		editSubOrgFullName(organization, organization.getOrgNameFull());
	}
	
	private void editSubOrgFullName(Organization organization, String newFullName) {
		if(organization.getChildOrgList() == null || organization.getChildOrgList().isEmpty()) {
			return;
		}
		for (Organization childOrg : organization.getChildOrgList()) {
			childOrg.setOrgNameFull(newFullName + "\\" + childOrg.getOrgName());
			childOrg.setModifyOperatorId(organization.getModifyOperatorId());
			organizationDao.updateOrganization(childOrg);
			editSubOrgFullName(childOrg, childOrg.getOrgNameFull());
		}
	}
	
	//递归删除机构和机构下面的所有子机构
	@Override
	public void removeOrg(Organization organization, Integer modifyOperatorId) {
		organizationDao.deleteOrganization(organization.getOrgId());
		OperOrgRela param = new OperOrgRela();
		param.setOrgId(organization.getOrgId());
		param.setModifyOperatorId(modifyOperatorId);
		operOrgRelaDao.updateStatusOperOrgRelaByOrgId(param);
		
		if(organization.getChildOrgList() != null && !organization.getChildOrgList().isEmpty()) {
			for (Organization tmp : organization.getChildOrgList()) {
				removeOrg(tmp, modifyOperatorId);
			}
		}
	}

	@Override
	public List<Organization> searchOrg(Organization organization) {
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("orgId",organization.getOrgId());
		searchMap.put("orgName",organization.getOrgName());
		return organizationDao.fuzzySearchOrganization(searchMap);
	}

	@Override
	public Organization findByFullName(String fullName) {
		return organizationDao.findByFullName(fullName);
	}

	@Override
	public List<Organization> selectHeadOrgInfos(int orgId) {
		return organizationDao.selectHeadOrgInfos(orgId);
	}

	@Override
	public List<Organization> findOperByOperatorId(int operatorId) {
		return organizationDao.selectOrgByOperatorId(operatorId);
	}

	@Override
	public List<Organization> selectSubLeafBySubRootId(int orgId) {
		return organizationDao.selectSubLeafBySubRootId(orgId);
	}
	
	@Override
	public List<Organization> selectAllRootOrgId() {
		return organizationDao.selectAllRootOrgId();
	}

	@Override
	public List<Organization> selectOrgTreeInfos() {
		Organization headOrg = organizationDao.findByFullName("总行");
		if(headOrg == null) {
			return Collections.emptyList();
		}
		List<Organization> headOrgs = organizationDao.selectHeadOrgInfos(headOrg.getOrgId());
		List<Organization> orgs = organizationDao.selectAllRootOrgId();
		for (Organization organization : orgs) {
			if("总行".equals(organization.getOrgName())) {
				continue;
			}
			headOrgs.add(organization);
		}
		return headOrgs;
	}
	
}
