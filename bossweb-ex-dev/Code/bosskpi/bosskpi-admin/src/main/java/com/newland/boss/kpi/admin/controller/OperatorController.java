package com.newland.boss.kpi.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.admin.service.OperOrgRelaService;
import com.newland.boss.kpi.admin.service.OperatorRoleService;
import com.newland.boss.kpi.admin.service.OperatorService;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;
import com.newland.boss.kpi.server.OperateLogService;
import com.newland.boss.kpi.util.MD5Util;

@RestController
public class OperatorController {
	
	@Autowired
    private OperatorService operatorService;
	
	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
	private OperOrgRelaService operOrgRelaService;
	
	@Autowired
	private OperatorRoleService operatorRoleService;
	
	@Autowired
	private AppSession appsession;
	
	private static final String PAGE_NO = "pageNo";
	private static final String PAGE_SIZE = "pageSize";
	
	//查找所有操作员
	@RequestMapping(value = "/showAllOperator", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAllOperator(){
		Map<String, Object> params = new HashMap<>();
		User user = appsession.getUser();
		if(user == null) {
			return "{\"msg\":\"获取登录用户失败！\", \"code:\":\"-1\"}";
		}
		List<Operator> operList = operatorService.findAllOperator(params);
		return JSONArray.toJSONString(operList);
	}
	
	//通过id查找该操作员
	@RequestMapping(value = "/showOperatorById", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showOperatorById(@RequestBody Operator operator){
		String respJson;
		Operator ope = operatorService.showOneOperator(operator);
		if (ope != null) {
			respJson = JSONObject.toJSONString(ope);
		} else{
			respJson = "{\"find\":\"fail\"}";
		}
		
		return respJson;
	}
	
	//编辑操作员
	@RequestMapping(value = "/editOperator", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editOperator(@RequestBody Operator operator){
		User user = appsession.getUser();
		if(user == null) {
			return "{\"msg\":\"获取登录用户失败！\", \"code:\":\"-1\"}";
		}
		operator.setModifyOperatorId(user.getOperatorId());
		operatorService.editOperator(operator);
		
		operatorService.insertOperatorHisById(operator.getOperatorId());
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(operator.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(3);
		operateLog.setOperateDesc("修改帐号信息：" + operator);
		operateLogService.addLog(operateLog);
		
		operOrgRelaService.editOperOrgRela(operator, operator.getOrgList());
		//添加操作日志到记录
		StringBuilder orgIds = new StringBuilder();
		for (Organization org : operator.getOrgList()) {
			orgIds.append(org.getOrgId()).append(",");
		}
		operateLog.setOperateDesc("修改了[" + operator.getOperatorId() + "]帐号机构信息：" + orgIds);
		operateLogService.addLog(operateLog);
		
		//返回更新信息后的集合
		return "{\"msg\":\"修改成功\", \"code:\":\"0\"}";
		
	}
	
	//添加操作员
	@RequestMapping(value = "/addOperator", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addOperator(@RequestBody Operator operator){
		User user = appsession.getUser();
		if(user == null) {
			return "{\"msg\":\"获取登录用户失败！\", \"code:\":\"-1\"}";
		}
		operator.setModifyOperatorId(user.getOperatorId());
		operator.setPassword(MD5Util.Encrypt("123456"));		
		operatorService.addOperator(operator);
		operOrgRelaService.addOrgForOper(operator, operator.getOrgList());
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(operator.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(1);
		operateLog.setOperateDesc("添加帐号信息：" + operator);
		operateLogService.addLog(operateLog);
		
		//返回更新信息后的集合
		return "{\"msg\":\"添加成功\", \"code:\":\"0\"}";
	}
	
	//模糊搜索
	@RequestMapping(value = "/searchOperator", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String searchOperator(@RequestBody Map<String, Object> params){
		//根据传递过来的值进行模糊搜索
		User user = appsession.getUser();
		if(user == null) {
			return "{\"msg\":\"获取登录用户失败！\", \"code:\":\"-1\"}";
		}
		int currentPage = Integer.parseInt(params.get(PAGE_NO) == null ? "1" : params.get(PAGE_NO).toString());
		int pageSize = Integer.parseInt(params.get(PAGE_SIZE) == null ? "10" : params.get(PAGE_SIZE).toString());
		Map<String, Object> resultMap = new HashMap<>();
		PageHelper.clearPage();
		PageHelper.startPage(currentPage, pageSize);
		List<Operator> searchOperList = operatorService.searchOperator(params);
		PageInfo<Operator> pageInfo = new PageInfo<>(searchOperList);
		resultMap.put("total", pageInfo.getTotal());
		resultMap.put("list", pageInfo.getList());
		return JSONObject.toJSONString(resultMap);
	}
	
	//删除操作员
	@RequestMapping(value = "/deleteOperator", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String deleteOperator(@RequestBody List<Operator> opers){
		User user = appsession.getUser();
		if(user == null) {
			return "{\"msg\":\"获取登录用户失败！\", \"code:\":\"-1\"}";
		}
		operatorRoleService.removeOperatorRole(opers);
		operOrgRelaService.removeOperOrgRela(opers, user.getOperatorId());
		operatorService.removeOperator(opers);
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(user.getOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(2);
		StringBuilder operIds = new StringBuilder();
		for (Operator operator : opers) {
			operIds.append(operator.getOperatorId()).append(",");
		}
		operateLog.setOperateDesc("删除帐号信息：" + operIds);
		operateLogService.addLog(operateLog);
		return "{\"msg\":\"删除成功\", \"code:\":\"0\"}";
	}
	
	//模糊搜索操作员
	@RequestMapping(value = "/searchOperatorGrid", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String searchOperatorGrid(@RequestBody Map<String, Object> params){
		//根据传递过来的值进行模糊搜索
		List<Operator> searchOperList = operatorService.searchOperator(params);
		return JSONArray.toJSONString(searchOperList);
	}
	
	//根据机构ID查找该机构下的所有操作员工号和姓名
	@RequestMapping(value = "/findOrgIdAndOrgNameByOrgId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String findOrgIdAndOrgNameByOrgId(@RequestBody Organization organization){
		List<Operator> operList = operatorService.findIdAndNameByOrgId(organization);
		return JSONArray.toJSONString(operList);
	}
		
	@RequestMapping(value = "/showAllOperGrid", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAllOperGrid(@RequestBody Map<String, Object> params){
		//根据传递过来的值进行模糊搜索
		int currentPage = Integer.parseInt(params.get(PAGE_NO) == null ? "1" : params.get(PAGE_NO).toString());
		int pageSize = Integer.parseInt(params.get(PAGE_SIZE) == null ? "10" : params.get(PAGE_SIZE).toString());
		PageHelper.startPage(currentPage, pageSize, true);
		List<Operator> searchOperList = operatorService.searchOperatorWithRole(params);
		PageInfo<Operator> pageInfo = new PageInfo<>(searchOperList);
		return JSONArray.toJSONString(pageInfo);
	}
	
	@RequestMapping(value = "/resetOperatorPwd", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String resetOperatorPwd(@RequestBody List<Operator> opers){
		User user = appsession.getUser();
		if(user == null) {
			return "{\"msg\":\"获取登录用户失败！\", \"code:\":\"-1\"}";
		}
		//重置操作员密码为初始密码123456
		operatorService.resetOpersPwd(opers, user.getOperatorId());
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(user.getOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(1);
		StringBuilder operIds = new StringBuilder();
		for (Operator operator : opers) {
			operIds.append(operator.getOperatorId()).append(",");
		}
		operateLog.setOperateDesc("重置操作员密码：" + operIds);
		operateLogService.addLog(operateLog);
				
		return "{\"msg\":\"重置密码成功\", \"code:\":\"0\"}";
	}
	
	@RequestMapping(value = "/changeOperPwd", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String changeOperPwd(@RequestBody Map<String, Object> params){
		User user = appsession.getUser();
		if(user == null) {
			return "{\"msg\":\"获取登录用户失败！\", \"code:\":\"-1\"}";
		}
		//return  "{\"msg\":\"只能修改自己的密码！\", \"code:\":\"-2\"}"
		
		Operator oper = new Operator();
		oper.setOperatorId(user.getOperatorId());
		oper.setPassword(String.valueOf(params.get("oldPassword")));
		boolean flag = operatorService.loginJudge(oper);
		if(!flag) {
			return  "{\"msg\":\"旧密码错误！\", \"code:\":\"-3\"}";
		}
		
		params.put("password", MD5Util.Encrypt(String.valueOf(params.get("password"))));
		params.put("operatorId", user.getOperatorId());
		params.put("modifyOperatorId", user.getOperatorId());
		operatorService.changeOperPwd(params);
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(user.getOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(1);
		operateLog.setOperateDesc("修改操作员密码：" + params);
		operateLogService.addLog(operateLog);

		return "{\"msg\":\"修改密码成功\", \"code:\":\"0\"}";
	}
}
