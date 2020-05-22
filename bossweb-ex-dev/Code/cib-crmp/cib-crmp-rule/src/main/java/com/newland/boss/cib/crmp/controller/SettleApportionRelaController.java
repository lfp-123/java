/**
 * 
 */
package com.newland.boss.cib.crmp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.cib.crmp.domain.RuleRela;
import com.newland.boss.cib.crmp.domain.SettleApportionRela;
import com.newland.boss.cib.crmp.service.SettleApportionRelaService;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.server.OperateLogService;

/**
 * @author ylc
 *
 *         2018-05-11
 */
@RestController
public class SettleApportionRelaController {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final Logger LOGGER = LogManager.getLogger(SettleApportionRelaController.class);
	
	@Autowired
	private OperateLogService operateLogService;

	@Autowired
	private SettleApportionRelaService settleApportionRelaService;

	// 添加结算分配
	@RequestMapping(value = "/addSettleApportionRela", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addSettleApportionRela(@RequestBody String relaJson) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(relaJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray relaArray = jsonObj.get("relaLists").getAsJsonArray();
		StringBuilder idx = new StringBuilder();
		for (int i = 0; i < relaArray.size(); i++) {
			JsonObject obj = relaArray.get(i).getAsJsonObject();
			idx.append(obj.get("apportionItemId").getAsInt()).append(",");
		}
		operateLogRecord(jsonObj.get("operatorId").getAsInt(), 14, 1, "批量新增单位结算摊分规则，单位ID：" + jsonObj.get("orgId").getAsInt() + "结算摊分规则ID" + idx);
		settleApportionRelaService.addSettleApportionRela(relaJson);
		return JSONArray.toJSONString(settleApportionRelaService.loadSettleApportionRelaByOrgId(jsonObj.get("orgId").getAsInt()));
	}

	// 多单位添加结算分配
	@RequestMapping(value = "/addSettleApportionRelaByRuleId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addSettleApportionRelaByRuleId(@RequestBody List<SettleApportionRela> settleApportionRelaLists) {
		Integer operateId = settleApportionRelaLists.get(0).getOperatorId();
		StringBuilder idx = new StringBuilder();
		if(settleApportionRelaLists.get(0).getObjectId() != null){
			for (SettleApportionRela settleApportionRela : settleApportionRelaLists) {
				idx.append(settleApportionRela.getObjectId()).append(",");
			}
		}
		operateLogRecord(operateId, 14, 1, "多单位添加结算摊分规则，规则ID：" + settleApportionRelaLists.get(0).getApportionItemId() + "单位ID：" + idx);
		settleApportionRelaService.addSettleApportionRelaByRuleId(settleApportionRelaLists);
		return "[{\"result\":\"true\"}]";
	}
	
	// 删除结算分配
	@RequestMapping(value = "/deleteSettleApportionRela", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String deleteSettleApportionRela(@RequestBody String relaJson) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(relaJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		operateLogRecord(jsonObj.get("operatorId").getAsInt(), 14, 2, "删除已分配的结算摊分规则：" + jsonObj.get("relaIdJson").getAsJsonArray());
		settleApportionRelaService.removeSettleApportionRela(relaJson);
		return JSONArray.toJSONString(settleApportionRelaService.loadSettleApportionRelaByOrgId(jsonObj.get("orgId").getAsInt()));
	}

	// 修改结算配置状态
	@RequestMapping(value = "/modifySettleApportionRelaStatus", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String modifySettleApportionRelaStatus(@RequestBody String relaJson) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(relaJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		operateLogRecord(jsonObj.get("operatorId").getAsInt(), 14, 3, "修改已分配的结算摊分规则的状态：摊分规则[" + jsonObj.get("relaIdArray").getAsJsonArray() + "],状态为：" + jsonObj.get("relaStatus").getAsInt());
		settleApportionRelaService.modifySettleApportionRelaStatus(relaJson);
		return JSONArray.toJSONString(settleApportionRelaService.loadSettleApportionRelaByOrgId(jsonObj.get("orgId").getAsInt()));
	}

	// 条件组合查询结算分配
	@RequestMapping(value = "/loadComQueryRelaByParam", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String loadComQueryRelaByParam(@RequestBody RuleRela ruleRela) {
		return JSONArray.toJSONString(settleApportionRelaService.loadComQueryRelaByParam(changeDateFormat(ruleRela)));
	}
		
	// 条件组合查询结算分配
	@RequestMapping(value = "/loadComQueryRelaByOrgParam", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String loadComQueryRelaByOrgParam(@RequestBody String relaJson) {
		return JSONArray.toJSONString(settleApportionRelaService.loadComQueryRelaByOrgParam(relaJson));
	}
	
	// 根据规则Id查询规则关系
	@RequestMapping(value = "/loadSettleApportionRelaByRuleId", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String loadSettleApportionRelaByRuleId(@RequestParam Integer apportionItemId) {
		return JSONArray.toJSONString(settleApportionRelaService.loadSettleApportionRelaByRuleId(apportionItemId));
	}
	
	// 根据组织查询规则关系
	@RequestMapping(value = "/loadSettleApportionRelaByOrgId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String loadSettleApportionRelaByOrgId(@RequestBody SettleApportionRela ruleRela) {
		return JSONArray.toJSONString(settleApportionRelaService.loadSettleApportionRelaByOrgId(ruleRela.getObjectId()));
	}

	// 导出
	@RequestMapping(value = "/exportSettleApportionRelaByParam", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String exportSettleApportionRelaByParam(@RequestBody String relaJson) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(relaJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();		
		operateLogRecord(jsonObj.get("operatorId").getAsInt(), 14, 5, "根据条件导出已分配结算摊分规则：" + relaJson);
		return JSONArray.toJSONString(settleApportionRelaService.exportSettleApportionRela(relaJson));
	}

	private void operateLogRecord(Integer operatorId, Integer operateModule, Integer operateType, String operateDesc) {
		// 添加操作日志到记录
		String regEx = "[\\u4e00-\\u9fa5]";
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(operatorId);
		operateLog.setOperateModule(operateModule);
		operateLog.setOperateType(operateType);
		int zwByte = operateDesc.length()-operateDesc.replaceAll(regEx, "").length();
		operateLog.setOperateDesc(operateDesc.getBytes().length > 230 ? operateDesc.substring(0, 230-2 * zwByte) : operateDesc);
		operateLogService.addLog(operateLog);
	}
	
	/**
	 * 该方法用于解决前台传回来的时间，默认加8小时问题
	 * @param settleApportionRule
	 * @return
	 */
	private RuleRela changeDateFormat(RuleRela ruleRela) {
		try {
			if (ruleRela.getInureDate() != null) {
				ruleRela.setInureDate(sdf.parse(sdf.format(ruleRela.getInureDate())));
			}
			if (ruleRela.getExpireDate() != null) {
				ruleRela.setExpireDate(sdf.parse(sdf.format(ruleRela.getExpireDate())));
			}
		} catch (ParseException e) {
			LOGGER.debug(e.getMessage());
		}
		return ruleRela;
	}
}
