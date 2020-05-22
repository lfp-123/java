/**
 * 
 */
package com.newland.boss.cib.crmp.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.cib.crmp.domain.SettleApportionRule;
import com.newland.boss.cib.crmp.service.SettleApportionRuleService;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.server.OperateLogService;

/**
 * @author ylc
 *
 *         2018-05-14
 */
@RestController
public class SettleApportionRuleController {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final Logger LOGGER = LogManager.getLogger(SettleApportionRuleController.class);
	@Autowired
	private OperateLogService operateLogService;

	@Autowired
	private SettleApportionRuleService settleApportionRuleService;

	// 添加结算配置
	@RequestMapping(value = "/addSettleApportionRule", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addSettleApportionRule(@RequestBody SettleApportionRule settleApportionRule) {
		Boolean isSuccess = settleApportionRuleService.addSettleApportionRule(changeDateFormat(settleApportionRule));
		if (isSuccess) {
			operateLogRecord(settleApportionRule.getOperatorId(), 13, 1, "新增结算摊分规则：" + settleApportionRule);
			return JSONArray.toJSONString(settleApportionRuleService.loadAllSettleApportionRule());
		} else {
			operateLogRecord(settleApportionRule.getOperatorId(), 13, 1, "新增结算摊分规则失败，生失效时间区间重复：" + settleApportionRule);
			return "[{\"result\":\"false\"}]";
		}
	}

	// 修改结算配置
	@RequestMapping(value = "/updateSettleApportionRule", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String updateSettleApportionRule(@RequestBody SettleApportionRule settleApportionRule) {
		operateLogRecord(settleApportionRule.getOperatorId(), 13, 3, "更新结算摊分规则：" + settleApportionRule);
		return JSONArray.toJSONString(settleApportionRuleService.editSettleApportionRule(changeDateFormat(settleApportionRule)));
	}

	// 删除结算配置
	@RequestMapping(value = "/deleteSettleApportionRule", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String deleteSettleApportionRule(@RequestBody String ruleJson) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(ruleJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		settleApportionRuleService.removeSettleApportionRule(ruleJson);
		operateLogRecord(jsonObj.get("operatorId").getAsInt(), 13, 2, "批量删除结算摊分规则，规则ID[" + jsonObj.get("ruleIdJson").getAsJsonArray()+"]");
		return JSONArray.toJSONString(settleApportionRuleService.loadAllSettleApportionRule());
	}

	// 修改结算配置状态
	@RequestMapping(value = "/modifySettleApportionRuleStatus", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String modifySettleApportionRuleStatus(@RequestBody String ruleJson) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(ruleJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		operateLogRecord(jsonObj.get("operatorId").getAsInt(), 13, 3, "修改结算摊分规则状态，规则ID["+jsonObj.get("ruleIdArray").getAsJsonArray()+"],状态修改为:"+jsonObj.get("ruleStatus").getAsInt());
		return JSONArray.toJSONString(settleApportionRuleService.modifySettleApportionRuleStatus(ruleJson));
	}

	// 查询结算配置
	@RequestMapping(value = "/loadAllSettleApportionRule", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String loadAllSettleApportionRule() {
		return JSONArray.toJSONString(settleApportionRuleService.loadAllSettleApportionRule());
	}

	// 通过id查找
	@RequestMapping(value = "/getRuleById", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String showRoleById(@RequestParam Integer settleApportionRuleId) {
		return JSONArray.toJSONString(settleApportionRuleService.getSettleApportionRuleById(settleApportionRuleId));

	}

	// 条件查询结算配置
	@RequestMapping(value = "/loadSettleApportionRuleByParam", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String loadSettleApportionRuleByParam(@RequestBody SettleApportionRule settleApportionRule) {
		return JSONArray.toJSONString(
				settleApportionRuleService.loadSettleApportionRuleByParam(changeDateFormat(settleApportionRule)));
	}

	// 导出
	@RequestMapping(value = "/exportSettleApportionRuleByParam", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String exportSettleApportionRuleByParam(@RequestBody SettleApportionRule settleApportionRule) {
		operateLogRecord(settleApportionRule.getOperatorId(), 13, 5, "根据规则条件导出规则：" + settleApportionRule);
		return JSONArray.toJSONString(
				settleApportionRuleService.exportSettleApportionRule(changeDateFormat(settleApportionRule)));
	}

	// 导入
	@RequestMapping(value = "/importSettleApportionRule")
	public Map<String, String> importSettleApportionRule(
			@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "operatorId", required = true) Integer operatorId) {
		Map<String, String> resultMap = new HashMap<>();
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (size > 0 && size <= 10485760) {
			String newFileName = String.valueOf(System.currentTimeMillis()) + ".xls";
			// 创建文件
			String dir = System.getProperty("user.dir") + "/upload/";
			File dest = new File(dir, newFileName);
			// 判断文件的目录是否存在，不存在则创建
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			// 上传到指定目录
			try {
				file.transferTo(dest);
				operateLogRecord(operatorId, 13, 6, "导入结算摊分规则");
				return settleApportionRuleService.importSettleApportionRule(dir + newFileName, operatorId);
			} catch (IOException e) {
				LOGGER.debug("复制文件异常" + e.getMessage());
				resultMap.put("result", "false");
				resultMap.put("error", e.getMessage());
				operateLogRecord(operatorId, 13, 6, "导入结算摊分规则失败");
				return resultMap;
			}
		} else {
			resultMap.put("result", "false");
			resultMap.put("error", "导入文件不能为空或文件应小于10M");
			return resultMap;
		}
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
	 * 
	 * @param settleApportionRule
	 * @return
	 */
	private SettleApportionRule changeDateFormat(SettleApportionRule settleApportionRule) {
		try {
			if (settleApportionRule.getInureDate() != null) {
				settleApportionRule.setInureDate(sdf.parse(sdf.format(settleApportionRule.getInureDate())));
			}
			if (settleApportionRule.getExpireDate() != null) {
				settleApportionRule.setExpireDate(sdf.parse(sdf.format(settleApportionRule.getExpireDate())));
			}
		} catch (ParseException e) {
			LOGGER.debug(e.getMessage());
		}
		return settleApportionRule;
	}
}
