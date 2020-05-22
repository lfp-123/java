package com.newland.boss.kpi.admin.util;

import java.util.Collections;
import java.util.List;

import com.newland.boss.kpi.admin.model.Resource;
import com.newland.boss.kpi.entity.Organization;

/**
 *循环查找子集 
 * @author ZJUNE
 *
 */
public class RecursionFindChildren {
	
	private RecursionFindChildren() {}
	
	//找出一个机构的所有下属机构
	public static List<Organization> getChildOrgByAndOrg(Organization organization,List<Organization> orgList,List<Organization> newList){
		if (null == organization) {
			return Collections.emptyList();
		}else {
			for(Organization org : orgList) {
				if(org.getSuperOrgId() == organization.getOrgId()) {
					newList.add(org);
					getChildOrgByAndOrg(org,orgList,newList);
				}
			}
		}
		if (newList.isEmpty()) {
			return Collections.emptyList();
		}else {
			return newList;
		}
	}
	
	// 根据机构编号寻找出所有下属机构的机构
	public static List<Organization> getChildOrg(int superOrgId ,List<Organization> allOrgList,List<Organization> orgList){
		if( null == orgList ) {
			return Collections.emptyList();
		}
		
		for(Organization org : allOrgList) {
			if(org.getSuperOrgId() == superOrgId) {
				orgList.add(org);
				getChildOrg(org.getOrgId(), allOrgList, orgList);
			}
		}
		if (orgList.isEmpty()) {
			return Collections.emptyList();
		}else {
			return orgList;
		}
		
	}
	
	// 根据机构编号寻找出所有下属机构的机构ID
	public static List<Integer> getChildOrgId(int superOrgId ,List<Organization> orgList,List<Integer> orgIdList){
		if( null == orgList ) {
			return Collections.emptyList();
		}
		
		for(Organization org : orgList) {
			if(org.getSuperOrgId() == superOrgId) {
				orgIdList.add(org.getOrgId());
				getChildOrgId(org.getOrgId(), orgList, orgIdList);
			}
		}
		if (orgIdList.isEmpty()) {
			return Collections.emptyList();
		}else {
			return orgIdList;
		}
		
	}
		
	//根据资源编号找出下属所有资源
	public static List<Integer> getChildResId(int superResourceId ,List<Resource> resList,List<Integer> resIdList){
		if( null == resList ) {
			return Collections.emptyList();
		}
		
		for(Resource res:resList) {
			if(res.getSuperResourceId() == superResourceId) {
				resIdList.add(res.getResourceId());
				getChildResId(res.getResourceId(), resList,resIdList);
			}
		}
		if (resIdList.isEmpty()) {
			return Collections.emptyList();
		}else {
			return resIdList;
		}
		
	}
	
	
}
