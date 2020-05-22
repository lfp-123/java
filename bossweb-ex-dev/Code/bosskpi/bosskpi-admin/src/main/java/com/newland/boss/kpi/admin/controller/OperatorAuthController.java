package com.newland.boss.kpi.admin.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.kpi.admin.model.OperatorAuth;
import com.newland.boss.kpi.admin.service.OperatorAuthService;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.server.OperateLogService;

@RestController
public class OperatorAuthController {
	
	@Autowired
	private OperatorAuthService operatorAuthService;
	
	@Autowired
	private OperateLogService operateLogService;
	
	//根据操作员ID来查找操作员资源关系
	@RequestMapping(value = "/showOperAuthByOperId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showOperAuthByOperId(@RequestBody OperatorAuth operatorAuth){
		
		List<OperatorAuth> operAuthList = operatorAuthService.findResourceOfOperator(operatorAuth);
		List<Integer> resourceIdList = new ArrayList<Integer>();
		
		for(int i = 0 ; i < operAuthList.size() ; i++) {
			resourceIdList.add(operAuthList.get(i).getResourceId()); 
		}
		
		Gson gson = new Gson();
		String respJson = gson.toJson(resourceIdList);
		return respJson;
	}
	
	//编辑操作员资源关系
	@RequestMapping(value = "/editOperatorAuth", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editOperatorAuth(@RequestBody String operAuthJson){
		
		operatorAuthService.addOperatorAuth(operAuthJson);
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operAuthJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer modifyOperatorId = jsonObj.get("modifyOperatorId").getAsInt();
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(modifyOperatorId);
		operateLog.setOperateModule(12);
		operateLog.setOperateType(3);
		operateLog.setOperateDesc("update operAuth");
		operateLogService.addLog(operateLog);
		
		return null;
	}
	
	//显示一组操作员资源关系 
	@RequestMapping(value = "/showAnOperatorAuth", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAnOperatorAuth(@RequestBody OperatorAuth operatorAuth){
		
		OperatorAuth operAuth = operatorAuthService.findAnOperatorAuth(operatorAuth);
		
		Gson gson = new Gson(); 
		String respJson = gson.toJson(operAuth);
		return respJson;
	}
	
	//删除一组操作员资源关系 
	@RequestMapping(value = "/removeOperatorAuth", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String removeOperatorAuth(@RequestBody String operAuthJson){
		
		operatorAuthService.removeOperatorAuth(operAuthJson); 
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(operAuthJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		Integer operatorId = jsonObj.get("operatorId").getAsInt();
		
		OperatorAuth operAuth = new OperatorAuth();
		operAuth.setOperatorId(operatorId);
		List<OperatorAuth> operAuthList = operatorAuthService.findResourceOfOperator(operAuth);
		Gson gson = new Gson();
		String respJson = gson.toJson(operAuthList);
		return respJson;	
		
	}
		
}
