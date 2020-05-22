package com.newland.boss.kpi.server.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newland.boss.kpi.dao.OrgDao;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.server.OrgService;

@Component
public class OrgServiceImpl implements OrgService {
	
	@Autowired
	private OrgDao orgDao;
	
	/**
	 * 机构缓存信息
	 */
	private static volatile List<Organization> allOrgCache = null;
	
	/**
     * 初始化数据字典缓存
     */
	@Override
    public void initCache() {
    	allOrgCache = orgDao.getAllOrganization();
    }
	
	@Override
	public List<Organization> getAllOrganization() {
		return orgDao.getAllOrganization();
	}

	@Override
	public List<Organization> getAllOrganizationFromCache() {
		if(allOrgCache == null) {
			initCache();
		}
		return allOrgCache;
	}

	@Override
	public void removeOrg(Organization organization) {
		if(allOrgCache == null || organization == null) {
			return;
		}
		Iterator<Organization> itor = allOrgCache.iterator();
		while (itor.hasNext()) {
			Organization org = itor.next();
			if(organization.getOrgId().equals(org.getOrgId())) {
				itor.remove();
				break;
			}
		}
		removeSunOrg(organization);
	}
	
	private void removeSunOrg(Organization organization) {
		if(organization.getChildOrgList() == null || organization.getChildOrgList().isEmpty()) {
			return;
		}
		for (Organization tmp : organization.getChildOrgList()) {
			Iterator<Organization> itor = allOrgCache.iterator();
			while (itor.hasNext()) {
				Organization org = itor.next();
				if(tmp.getOrgId().equals(org.getOrgId())) {
					itor.remove();
					removeSunOrg(tmp);
					break;
				}
			}
			removeOrg(tmp);
		}
	}

	@Override
	public void editOrg(Organization organization) {
		if(allOrgCache == null || organization == null) {
			return;
		}
		for (Organization org : allOrgCache) {
			if(organization.getOrgId().equals(org.getOrgId())) {
				org.getOrgName();
				org.setModifyOperatorId(organization.getModifyOperatorId());
				org.setOrgName(organization.getOrgName());
				org.setOrgNameFull(organization.getOrgNameFull());
				org.setOrgStatus(organization.getOrgStatus());
				org.setOrgDesc(organization.getOrgDesc());
				break;
			}
		}
		editSubOrgFullName(organization, organization.getOrgNameFull());
	}
	
	private void editSubOrgFullName(Organization organization, String newFullName) {
		if(organization.getChildOrgList() == null || organization.getChildOrgList().isEmpty()) {
			return;
		}
		for (Organization childOrg : organization.getChildOrgList()) {
			for (Organization org : allOrgCache) {
				if(childOrg.getOrgId().equals(org.getOrgId())) {
					org.setOrgNameFull(newFullName + "\\" + org.getOrgName());
					org.setModifyOperatorId(organization.getModifyOperatorId());
					editSubOrgFullName(childOrg, org.getOrgNameFull());
					break;
				}
			}
		}
	}
}
