package com.newland.boss.cib.crmp.settle.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.domain.BillSummingOrgDay;
import com.newland.boss.cib.crmp.domain.BillSummingUsrDay;
import com.newland.boss.cib.crmp.settle.service.BillSummingOrgDayService;
import com.newland.boss.cib.crmp.settle.service.BillSummingUsrDayService;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;

@RestController
public class AnalysisController {
	
	private static final Logger LOGGER = LogManager.getLogger(AnalysisController.class);
	
	@Autowired
	private BillSummingOrgDayService billSummingOrgDayService; 
	
	@Autowired
	private BillSummingUsrDayService billSummingUsrDayService; 
	
	@Autowired
	private AppSession appSession;
	
	private static final String OPERATOR_ID = "operatorId";
	private static final String ORG_ID = "orgId";
	
	@RequestMapping(value = "/getFeeAnalysisPie", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String getFeeAnalysisPie(@RequestBody Map<String, Object> params){
		User user = appSession.getUser();
		if(user.getOperatorLevel() == 1) {
			params.put(OPERATOR_ID, user.getOperatorId());
			List<BillSummingUsrDay> pieResult = billSummingUsrDayService.selecPieResultByOperId(params);
			return JSONArray.toJSONString(pieResult);
		} else if (user.getOperatorLevel() == 2) {
			params.put(ORG_ID, user.getOrgId());
			List<BillSummingOrgDay> pieResult = billSummingOrgDayService.selecPieResultByOrgId(params);
			return JSONArray.toJSONString(pieResult);
		} else {
			List<BillSummingOrgDay> pieResult = billSummingOrgDayService.selecTotalPieResult(params);
			return JSONArray.toJSONString(pieResult);
		}
	}
	
	@RequestMapping(value = "/getFeeAnalysisBar", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String getFeeAnalysisBar(@RequestBody  Map<String, Object> params){
		User user = appSession.getUser();
		if(user.getOperatorLevel() == 1) {
			params.put(OPERATOR_ID, user.getOperatorId());
			List<BillSummingUsrDay> barResult = billSummingUsrDayService.selectBarResult(params);
			return JSONArray.toJSONString(barResult);
		} else if (user.getOperatorLevel() == 2) {
			List<Organization> orgs = new ArrayList<>();
			Organization org = new Organization();
			org.setOrgId(user.getOrgId());
			orgs.add(org);
			params.put("orgs", orgs);
			List<BillSummingOrgDay> barResult = billSummingOrgDayService.selectBarResult(params);
			return JSONArray.toJSONString(barResult);
		} else {
			List<BillSummingOrgDay> barResult = billSummingOrgDayService.selectBarResult(params);
			return JSONArray.toJSONString(barResult);
		}
	}
	
	@RequestMapping(value = "/getFeeAnalysisLine", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String getFeeAnalysisLine(@RequestBody Map<String, Object> params){
		LOGGER.debug("getFeeAnalysisBar params = " + params);
		User user = appSession.getUser();
		List<Map<String, Object>> lineResult = null;
		if(user.getOperatorLevel() == 1) {
			params.put(OPERATOR_ID, user.getOperatorId());
			lineResult = billSummingUsrDayService.selecLineResult(params);
		} else if (user.getOperatorLevel() == 2) {
			params.put(ORG_ID, user.getOrgId());
			lineResult = billSummingOrgDayService.selecLineResult(params);
		} else {
			lineResult = billSummingOrgDayService.selecLineResult(params);
		}
		return JSONArray.toJSONString(lineResult);
	}
	
}
