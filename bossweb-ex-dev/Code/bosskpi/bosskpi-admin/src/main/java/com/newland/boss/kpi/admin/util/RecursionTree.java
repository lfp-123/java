package com.newland.boss.kpi.admin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.newland.boss.kpi.admin.model.Resource;
import com.newland.boss.kpi.admin.model.Role;
import com.newland.boss.kpi.entity.Organization;

/**
 * 递归树
 * @author ZJUNE
 *
 */
public class RecursionTree {
	
	private RecursionTree() {}
	
	//构建菜单树
	public static List<Resource> getResource(int superMenuId , List<Resource> resList) {
		if( null == resList ) {
			return Collections.emptyList();
		}
		List<Resource> menuList = new ArrayList<>();
		for(Resource res:resList) {
			if( res.getSuperResourceId() == superMenuId) {
				Resource menu = new Resource();
				menu.setResourceId(res.getResourceId());
				menu.setResourceName(res.getResourceName());
				menu.setResourceUrl(res.getResourceUrl());
				menu.setResourceType(res.getResourceType());
				menu.setSuperResourceId(res.getSuperResourceId());
				menu.setResourceOrder(res.getResourceOrder());
				menu.setChildResourceList(getResource(res.getResourceId(), resList));
				menuList.add(menu);
			}
		}
		if( menuList.isEmpty()) {
			return Collections.emptyList();
		}else {
			return menuList;
		}
	} 
	
	
	//构建机构树
	public static List<Organization> getOrganization(int superOrgId ,List<Organization> orgList) {
		if( null == orgList ) {
			return Collections.emptyList();
		}
		
		List<Organization> organizationList = new ArrayList<>();
		
		for(Organization org:orgList) {
			if( org.getSuperOrgId() == superOrgId) {
				Organization organization = new Organization();
				organization.setOrgId(org.getOrgId());
				organization.setSuperOrgId(org.getSuperOrgId());
				organization.setOrgName(org.getOrgName());
				organization.setOrgDesc(org.getOrgDesc());
				organization.setOrgStatus(org.getOrgStatus());
				organization.setOrgNameFull(org.getOrgNameFull());
				organization.setIsSync(org.getIsSync());
				organization.setCreateTime(org.getCreateTime());
				organization.setChildOrgList(getOrganization(org.getOrgId(), orgList));
				organizationList.add(organization);
			}
		}
		if(organizationList.isEmpty()) {
			return Collections.emptyList();
		}else {
			return organizationList;
		}
	} 

	
	//构建菜单树Json
	public static String getResourceTreeJson(List<Resource> resList) {
		List<Resource> menuList = RecursionTree.getResource(-1,resList);
		return JSONArray.toJSONString(menuList);
	}
	
	//构建机构树Json
	public static String getOrgTreeJson(List<Organization> orgList) {
		List<Organization> organizationList = RecursionTree.getOrganization(-1, orgList);
		return JSONArray.toJSONString(sort(organizationList));
	}
	
	//构建角色树
	public static String getRoleTreeJson(List<Role> roleList) {
		Gson gson = new Gson();
		String respJson = gson.toJson(roleList);
		respJson = respJson.replaceAll("roleName", "text");
		return respJson;
		
	}
	
	//构建树形机构菜单
	public static String getOrgTreeSelect(List<Organization> orgList) {
		
		List<Organization> organizationList = RecursionTree.getOrganization(-1, orgList);
		
		Gson gson = new Gson();
		String respJson = gson.toJson(organizationList);
		respJson = respJson.replaceAll("orgId", "id");
		respJson = respJson.replaceAll("superOrgId", "pid");
		respJson = respJson.replaceAll("childOrgList", "children");
		respJson = respJson.replaceAll("orgName", "name");
		return respJson;
	}
	
	private static List<Organization> sort(List<Organization> organizationList) {
		if(organizationList == null || organizationList.isEmpty()) {
			return Collections.emptyList();
		}
		List<Organization> returnList = new ArrayList<>();
		List<Organization> subList = new ArrayList<>();
		List<Organization> cList = new ArrayList<>();
		List<Organization> otherList = new ArrayList<>();
		for (Organization organization : organizationList) {
			if("总行".equals(organization.getOrgName())) {
				returnList.add(organization);
			} else if(organization.getOrgName().endsWith("分行")) {
				subList.add(organization);
			} else if(organization.getOrgName().endsWith("公司")) {
				cList.add(organization);
			} else {
				otherList.add(organization);
			}
		}
		returnList.addAll(subList);
		returnList.addAll(cList);
		returnList.addAll(otherList);
		return returnList;
	}
	
}
