package com.newland.boss.cib.crmp.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.domain.BillSummingOrgDay;
import com.newland.boss.cib.crmp.domain.BillSummingUsrDay;
import com.newland.boss.cib.crmp.settle.service.BillSummingOrgDayService;
import com.newland.boss.cib.crmp.settle.service.BillSummingUsrDayService;
import com.newland.boss.kpi.admin.service.OrganizationService;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;
import com.newland.boss.kpi.server.DictDefService;
import com.newland.boss.kpi.server.OrgService;

@RestController
public class IndexController {
	
	private static final Logger LOGGER = LogManager.getLogger(IndexController.class);
	
	@Autowired
	private BillSummingOrgDayService billSummingOrgDayService; 
	
	@Autowired
	private BillSummingUsrDayService billSummingUsrDayService; 
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private AppSession appSession;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private DictDefService dictDefService;
	
	@RequestMapping(value = "/getTotalAnalysisResult", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String getTotalAnalysisResult(){
		Map<String, Object> params = new HashMap<>();
		//计算折线图时间断
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		LocalDate start = date.minus(12, ChronoUnit.MONTHS);
		int startDate = Integer.parseInt(start.format(formatter));
		List<Integer> dates = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			dates.add(Integer.parseInt(date.minus(i, ChronoUnit.MONTHS).format(formatter)));
		}
		LOGGER.debug("dates = " + dates);
		params.put("dates", dates);
		params.put("startDate", startDate * 100 + 1);
		params.put("endDate", Integer.parseInt(date.format(DateTimeFormatter.ofPattern("yyyyMMdd"))));
		User user = appSession.getUser();
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("dates", dates);
		if(user.getOperatorLevel() == 1) {
			params.put("operatorId", user.getOperatorId());
			List<BillSummingUsrDay> pieResult = billSummingUsrDayService.selecPieResultByOperId(params);
			List<Map<String, Object>> lineResult = billSummingUsrDayService.selecLineResult(params);
			resultMap.put("line", lineResult);
			resultMap.put("pie", pieResult);
		} else if(user.getOperatorLevel() == 2) {
			params.put("orgId", user.getOrgId());
			List<Map<String, Object>> lineResult = billSummingOrgDayService.selecLineResult(params);
			List<BillSummingOrgDay> pieResult = billSummingOrgDayService.selecPieResultByOrgId(params);
			
			resultMap.put("line", lineResult);
			resultMap.put("pie", pieResult);
		} else if(user.getOperatorLevel() == 3) {
			List<BillSummingOrgDay> totalBarResult = billSummingOrgDayService.selectBarResult(params);
			
			List<Map<String, Object>> lineResult = billSummingOrgDayService.selecLineResult(params);
			List<BillSummingOrgDay> pieResult = billSummingOrgDayService.selecPieResultByOrgId(params);
			List<Organization> allRootOrgs = organizationService.selectOrgTreeInfos();
			resultMap.put("orgs", allRootOrgs);
			resultMap.put("totalBar", totalBarResult);
			resultMap.put("line", lineResult);
			resultMap.put("pie", pieResult);
		}
		LOGGER.debug("resultMap = " + resultMap);
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value = "/reFreshInitData", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String reFreshInitData() {
		User user = appSession.getUser();
		if(user == null || user.getOperatorLevel() == null) {
			return "{\"msg\":\"获取登录用户失败！\", \"code:\":\"-1\"}";
		}
		if(user.getOperatorLevel() == 3) {
			orgService.initCache();
			dictDefService.initCache();
		}
		return "{\"msg\":\"刷新成功！\", \"code:\":\"0\"}";
	}
	
	@RequestMapping(value = "/commonError", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String commonError() {
		return "{\"msg\":\"请求异常！\", \"code:\":\"-999\"}";
	}
}
