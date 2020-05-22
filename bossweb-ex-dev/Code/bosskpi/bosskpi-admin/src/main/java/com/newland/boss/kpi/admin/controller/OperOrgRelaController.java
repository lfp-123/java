package com.newland.boss.kpi.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.kpi.admin.model.OperOrgRela;
import com.newland.boss.kpi.admin.service.OperOrgRelaService;
import com.newland.boss.kpi.admin.service.OrganizationService;
import com.newland.boss.kpi.admin.util.RecursionFindChildren;
import com.newland.boss.kpi.admin.util.RecursionTree;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;
import com.newland.boss.kpi.server.OperateLogService;

@RestController
public class OperOrgRelaController {
	
	@Autowired
	private OperOrgRelaService operOrgRelaService;
	
	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private AppSession appSession;
	
	@RequestMapping(value = "/showOperOrgRelaByOperId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showOperOrgRelaByOperId(@RequestBody OperOrgRela operOrgRela){
		
		List<OperOrgRela> operOrgRelaList = operOrgRelaService.findOrgOfOper(operOrgRela);
		List<Integer> orgIdList = new ArrayList<Integer>();
		
		for(int i = 0 ; i < operOrgRelaList.size() ; i++) {
			orgIdList.add(operOrgRelaList.get(i).getOrgId()); 
		}
		
		Gson gson = new Gson();
		String respJson = gson.toJson(orgIdList);
		return respJson;
	}
	
	//编辑操作员机构关系
	@RequestMapping(value = "/editOperOrgRela", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editOperOrgRela(@RequestBody String operOrgJson){
		
		operOrgRelaService.editOperOrgRela(operOrgJson);
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operOrgJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer modifyOperatorId = jsonObj.get("modifyOperatorId").getAsInt();
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(modifyOperatorId);
		operateLog.setOperateModule(12);
		operateLog.setOperateType(3);
		operateLog.setOperateDesc("update operOrg");
		operateLogService.addLog(operateLog);
		
		return null;
		
	}
	
	//查找一组操作员机构关系 
	@RequestMapping(value = "/showAnOperOrgRela", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAnOperOrgRela(@RequestBody OperOrgRela operOrgRela){
		
		//查询数据库数据并封装成Json集合发送到前台
		OperOrgRela rela = operOrgRelaService.findAnOperOrgRela(operOrgRela);
		String respJson;
		Gson gson = new Gson();
		respJson = gson.toJson(rela);
		return respJson;
	
	}
	
	//删除选中的多组操作员机构关系
	@RequestMapping(value = "/removeOperOrgRela", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String removeOperOrgRela(@RequestBody String operOrgJson){
		
		
		operOrgRelaService.removeOperOrgRela(operOrgJson);
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operOrgJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer operatorId = jsonObj.get("operatorId").getAsInt();
		
		OperOrgRela operOrgRela = new OperOrgRela();
		operOrgRela.setOperatorId(operatorId);
		List<OperOrgRela> operOrgRelaList = operOrgRelaService.findOrgOfOper(operOrgRela);
		String respJson;
		Gson gson = new Gson();
		respJson = gson.toJson(operOrgRelaList);
		return respJson;	
	}
	
	//添加操作员机构关系
	@RequestMapping(value = "/addOrganizationForOperator", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addOrganizationForOperator(@RequestBody String orgNameArray){
		operOrgRelaService.addOrgForOper(orgNameArray);
		return null;
	}
	
	// 综合部分的树形机构下拉框
	@RequestMapping(value = "/showOrgTreeSelectOfGeneral", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showOrgTreeSelectOfGeneral(){	
		return getOrgTree();
	}
	
	// 得到机构下拉树
	public String getOrgTree() {
		String respJson = null;
		User user = appSession.getUser();
		List<Organization> orgList = null;
		if(user.getOperatorLevel() == 3) {
		    orgList = organizationService.selectOrgTreeInfos();
			respJson = JSONArray.toJSONString(orgList);
		} else if(user.getOperatorLevel() == 1) {
			Integer operatorId = user.getOperatorId();
			respJson = JSONArray.toJSONString(organizationService.findOperByOperatorId(operatorId)); 
		} else {
			Integer operatorId = user.getOperatorId();
			List<Organization> allOrgList = organizationService.showAllOrg();
			orgList = organizationService.findOperByOperatorId(operatorId);
			List<Organization> listOfOper = new ArrayList<Organization>();
			for (int i = 0;i < orgList.size();i++) {
				List<Organization> newList = new ArrayList<>();
				listOfOper.add(orgList.get(i));
				listOfOper.addAll(RecursionFindChildren.getChildOrg(orgList.get(i).getOrgId(), allOrgList, newList));
			}
			respJson = JSONArray.toJSONString(listOfOper); 

		}
		return respJson;
	}
	
}
