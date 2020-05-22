package com.newland.boss.kpi.admin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.newland.boss.kpi.admin.service.OrganizationService;
import com.newland.boss.kpi.admin.util.RecursionTree;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;
import com.newland.boss.kpi.server.OperateLogService;
import com.newland.boss.kpi.server.OrgService;

@RestController
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
	private AppSession appSession;
	
	@Autowired
	private OrgService orgService;
	
	//添加机构
	@RequestMapping(value = "/addOrganization", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addOrganization(@RequestBody Organization organization){
		
		try {
			organizationService.addOrg(organization);
			organization.setIsSync(1);
			orgService.getAllOrganizationFromCache().add(organization);
			
			//添加操作日志到记录
			OperateLog operateLog = new OperateLog();
			operateLog.setOperatorId(organization.getModifyOperatorId());
			operateLog.setOperateModule(12);
			operateLog.setOperateType(1);
			operateLog.setOperateDesc("添加机构信息：" + organization);
			operateLogService.addLog(operateLog);
			
			return "{\"msg\":\"添加成功\", \"code:\":\"0\"}";
		} catch (Exception e) {
			String tmp = e.getMessage();
			if(tmp.contains("违反唯一约束条件 ")) {
				return "{\"msg\":\"该机构已存在！\", \"code:\":\"-1\"}";
			}
			return "{\"msg\":\"异常！" + e.getMessage() + "\", \"code:\":\"-1\"}";
		}
	}
	
	
	//显示所有机构
	@RequestMapping(value = "/showAllOrganization", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAllOrganization(){	
		//查询出所有后封装成一个Json集合返回
		List<Organization> orgList = orgService.getAllOrganizationFromCache();
		return RecursionTree.getOrgTreeJson(orgList);
	}
	
	//显示分行顶级和总行二机机构
	@RequestMapping(value = "/showHeadOrgs", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showHeadOrgs(){
		List<Organization> orgList = organizationService.selectOrgTreeInfos();
		return JSONArray.toJSONString(treeSort(orgList));
	}
	
	//编辑机构
	@RequestMapping(value = "/editOrganization", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editOrganization(@RequestBody Organization organization){
		
		organizationService.editOrg(organization);
		orgService.editOrg(organization);
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(organization.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(3);
		operateLog.setOperateDesc("修改机构信息：" + organization);
		operateLogService.addLog(operateLog);
		
		return "{\"msg\":\"修改成功\", \"code:\":\"0\"}";
	}
	
	//删除机构
	@RequestMapping(value = "/removeOrganization", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String removeOrganization(@RequestBody Organization organization){
		User user = appSession.getUser();
		
		organizationService.removeOrg(organization, user.getOperatorId());
		orgService.removeOrg(organization);
				
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(organization.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(2);
		operateLog.setOperateDesc("删除机构信息：" + organization);
		operateLogService.addLog(operateLog);
		
		return "{\"msg\":\"删除成功\", \"code:\":\"0\"}";
	}
	
	//搜索机构
	@RequestMapping(value = "/searchOrganization", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String searchOrganization(@RequestBody Organization organization){
		//查询出所有后封装成一个Json集合返回
		List<Organization> orgList = organizationService.searchOrg(organization);
		Gson gson = new Gson();
		return gson.toJson(orgList);
	}
	
	//查找机构树 
	@RequestMapping(value = "/showOrgTree", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showOrgTree(){	
		//查找出所有机构，封装成一个集合
		return getOrgTree();
	}
	
	//查找树形机构下拉框
	@RequestMapping(value = "/showOrgTreeSelect", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showOrgTreeSelect(){	
		//查找出所有机构，封装成一个集合
		return getOrgTree();
	}
	
	private String getOrgTree() {
		User user = appSession.getUser();
		if(user.getOperatorLevel() == 3) {
			List<Organization> orgList = organizationService.selectOrgTreeInfos();
			return JSONArray.toJSONString(treeSort(orgList));
		}
		if(user.getOperatorLevel() == 2) {
			Organization param = new Organization();
			param.setOrgId(user.getOrgId());
			List<Organization> orgList = organizationService.searchOrg(param);
			return JSONArray.toJSONString(orgList);
		}
		return "[]";
	}
	
	private List<Organization> treeSort(List<Organization> orgList) {
		if(orgList == null || orgList.isEmpty()) {
			return Collections.emptyList();
		}
		List<Organization> returnList = new ArrayList<>();
		List<Organization> subList = new ArrayList<>();
		List<Organization> cList = new ArrayList<>();
		List<Organization> otherList = new ArrayList<>();
		for (Organization organization : orgList) {
			if(organization.getOrgNameFull().startsWith("总行\\")) {
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
