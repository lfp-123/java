/**
 * 
 */
package com.newland.boss.cib.crmp.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.cib.crmp.common.JxlTool;
import com.newland.boss.cib.crmp.dao.SettleApportionRelaDao;
import com.newland.boss.cib.crmp.dao.SettleApportionRuleDao;
import com.newland.boss.cib.crmp.domain.SettleApportionRule;
import com.newland.boss.cib.crmp.exception.ExcelParseException;
import com.newland.boss.cib.crmp.service.SettleApportionRuleService;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.server.DictDefService;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 * @author ylc
 *
 *         2018-05-11
 */
@Component
@Transactional
public class SettleApportionRuleServiceImpl implements SettleApportionRuleService {

	@Autowired
	private SettleApportionRuleDao settleApportionRuleDao;
	@Autowired
	private SettleApportionRelaDao settleApportionRelaDao;
	@Autowired
	private DictDefService dictDefService;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private SimpleDateFormat filesdf = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final Logger LOGGER = LogManager.getLogger(SettleApportionRuleServiceImpl.class);

	public static final String RESULT = "result";

	public static final String FILENAME = "filename";

	public static final String ERROR = "error";

	public static final String TRUE = "true";

	public static final String FALSE = "false";

	/**
	 * 根据ID查询数据字典
	 * 
	 * @return Map
	 */
	public Map<Integer, String> getDicMap(Integer dicId) {
		Map<Integer, String> dicMap = new HashMap<>();
		List<DictDef> dicList = dictDefService.getDictDefByDictClass(dicId);
		for (DictDef dictDef : dicList) {
			dicMap.put(dictDef.getEntryId(), dictDef.getEntryName());
		}
		return dicMap;
	}

	/**
	 * 根据Name查询数据字典Id
	 * 
	 * @return Map
	 */
	public Map<String, Integer> getDicIdMap(Integer dicId) {
		Map<String, Integer> dicMap = new HashMap<>();
		List<DictDef> dicList = dictDefService.getDictDefByDictClass(dicId);
		for (DictDef dictDef : dicList) {
			dicMap.put(dictDef.getEntryName(), dictDef.getEntryId());
		}
		return dicMap;
	}

	@Override
	public Boolean addSettleApportionRule(SettleApportionRule settleApportionRule) {

		// 默认系统创建时间
		settleApportionRule.setCreateTime(new Date());

		List<SettleApportionRule> dateValidity = settleApportionRuleDao.judgeRuleDateValidity(settleApportionRule);
		if (dateValidity.isEmpty()) {
			settleApportionRuleDao.insertSettleApportionRule(settleApportionRule);
			return true;
		}
		return false;

	}

	@Override
	public void removeSettleApportionRule(String ruleJson) {

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(ruleJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray ruleArray = jsonObj.get("ruleIdJson").getAsJsonArray(); // 选中的机构

		// 循环删除角色
		for (int i = 0; i < ruleArray.size(); i++) {
			Integer ruleId = ruleArray.get(i).getAsInt();
			settleApportionRelaDao.deleteSettleApportionRelaByRuleId(ruleId);
			settleApportionRuleDao.deleteSettleApportionRule(ruleId);
		}
	}

	@Override
	public Map<String, String> editSettleApportionRule(SettleApportionRule settleApportionRule) {
		Map<String, String> resultMap = new HashMap<>();
		List<SettleApportionRule> dateValidity = settleApportionRuleDao.judgeRuleDateValidity(settleApportionRule);
		if (dateValidity.isEmpty() || dateValidity.size() == 1
				&& dateValidity.get(0).getApportionItemId().equals(settleApportionRule.getApportionItemId())) {

			if (settleApportionRule.getStatus() == 2
					&& settleApportionRelaDao.queryRelaByRuleId(settleApportionRule.getApportionItemId()) != 0) {
				resultMap.put(RESULT, FALSE);
				resultMap.put(ERROR, "该摊分规则存在分配关系无法禁用，请先解除分配关系");
			} else {
				settleApportionRuleDao.updateSettleApportionRule(settleApportionRule);
				// 同时更新关系规则的生失效时间
				settleApportionRelaDao.updateSettleApportionRelaTime(settleApportionRule);
				resultMap.put(RESULT, TRUE);
			}

		} else {
			resultMap.put(RESULT, FALSE);
			resultMap.put(ERROR, "修改失败生失效时间冲突，请重新选择!");
		}
		return resultMap;

	}

	@Override
	public List<SettleApportionRule> loadAllSettleApportionRule() {
		return settleApportionRuleDao.queryAllSettleApportionRule();
	}

	@Override
	public List<SettleApportionRule> loadSettleApportionRuleByParam(SettleApportionRule settleApportionRule) {
		if (settleApportionRule.getApportionRuleName() != null) {
			settleApportionRule.setApportionRuleName(
					settleApportionRule.getApportionRuleName().replace("_", "/_").replaceAll("%", "/%"));
		}
		return settleApportionRuleDao.querySettleApportionRuleByParam(settleApportionRule);
	}

	@Override
	public SettleApportionRule getSettleApportionRuleById(Integer settleApportionRuleId) {

		return settleApportionRuleDao.getSettleApportionRuleById(settleApportionRuleId);
	}

	@Override
	public Map<String, String> modifySettleApportionRuleStatus(String ruleJson) {
		Map<String, String> resultMap = new HashMap<>();
		StringBuilder result = new StringBuilder();
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(ruleJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray ruleArray = jsonObj.get("ruleIdArray").getAsJsonArray(); // 选中的rule
		Integer ruleStatus = jsonObj.get("ruleStatus").getAsInt();// 要改变的规则状态
		SettleApportionRule settleApportionRule = new SettleApportionRule();
		settleApportionRule.setStatus(ruleStatus);
		// 循环更新规则状态
		for (int i = 0; i < ruleArray.size(); i++) {
			Integer ruleId = ruleArray.get(i).getAsInt();
			// 判断如果把规则禁用，需要查询是否将该规则分配出去
			if (settleApportionRelaDao.queryRelaByRuleId(ruleId) != 0) {
				result.append(ruleId).append("、");
				continue;
			}
			settleApportionRule.setApportionItemId(ruleId);
			settleApportionRuleDao.updateSettleApportionRule(settleApportionRule);
		}
		if (result.length() == 0) {
			resultMap.put(RESULT, TRUE);
		} else {
			resultMap.put(RESULT, FALSE);
			resultMap.put(ERROR, "摊分规则ID为:" + result.substring(0, result.length() - 1) + "的规则存在分配关系无法禁用，请先解除分配关系");
		}
		return resultMap;
	}

	@Override
	public Map<String, String> exportSettleApportionRule(SettleApportionRule settleApportionRule) {
		Map<String, String> resultMap = new HashMap<>();
		// 1004规则类型数据字典
		Map<Integer, String> statusMap = getDicMap(1004);
		// 1001资费类型数据字典
		Map<Integer, String> cdrTypeMap = getDicMap(1001);
		// 1019资费类型数据字典
		Map<Integer, String> scopeMap = getDicMap(1019);
		String title = "SettleRuleExport" + filesdf.format(new Date()) + ".xls";
		String path = System.getProperty("user.dir") + "/downloadTemp/" + title;
		String[] columns = { "摊分规则ID", "摊分规则名称", "资源类型", "规则范围", "摊分比例", "生效时间", "失效时间", "规则状态", "操作工号ID", "创建时间",
				"备注说明" };
		boolean[] numberFlag = { false, false, false, false, false, false, false, false, false, false, false };
		List<String[]> dataList = new ArrayList<>();
		if (settleApportionRule.getApportionRuleName() != null) {
			settleApportionRule.setApportionRuleName(
					settleApportionRule.getApportionRuleName().replace("_", "/_").replaceAll("%", "/%"));
		}
		List<SettleApportionRule> saRLists = settleApportionRuleDao
				.querySettleApportionRuleByParam(settleApportionRule);
		for (SettleApportionRule r : saRLists) {
			String[] date = { String.valueOf(r.getApportionItemId()), String.valueOf(r.getApportionRuleName()),
					cdrTypeMap.get(r.getCdrType()), scopeMap.get(r.getScope()), String.valueOf(r.getRatio()),
					sdf.format(r.getInureDate()), sdf.format(r.getExpireDate()), statusMap.get(r.getStatus()),
					String.valueOf(r.getOperatorId()), sdf.format(r.getCreateTime()),
					String.valueOf(r.getApportionDesc() == null ? ""
							: r.getApportionDesc()) };
			dataList.add(date);
		}
		try {
			JxlTool.exportExcel(path, columns, dataList, numberFlag);
			resultMap.put(RESULT, TRUE);
			resultMap.put(FILENAME, URLEncoder.encode(title, "UTF-8"));
		} catch (WriteException | IOException e) {
			LOGGER.debug("文件导出异常" + e.getMessage());
			resultMap.put(RESULT, FALSE);
			resultMap.put(ERROR, e.getMessage());
		}
		return resultMap;
	}

	@Override
	public Map<String, String> importSettleApportionRule(String tempPath, Integer operatorId) {
		Map<String, String> resultMap = new HashMap<>();
		Integer counter = 1;
		try {
			List<String[]> dataList = JxlTool.importExcel(tempPath, 0);
			File file = new File(tempPath);
			if (file.exists() && file.isFile() && !file.delete()) {
				LOGGER.debug("删除临时结算摊分上传文件失败");
			}
			for (int j = 1; j < dataList.size(); j++) {
				// 某行空单元格数量
				int nullData = 0;

				for (int i = 0; i <= 7; i++) {
					if ("".equals(dataList.get(j)[i])) {
						nullData = nullData + 1;
					}
				}

				if (nullData == 8) {
					break;
				} else if (nullData == 0) {
					SettleApportionRule rule = dealDataFormat(dataList.get(j), operatorId);
					if (!this.addSettleApportionRule(rule)) {
						resultMap.put(RESULT, FALSE);
						if (counter > 1) {
							resultMap.put(ERROR, "导入前" + (counter - 1) + "行摊分规则成功,执行至第" + counter + "行停止,生失效时间冲突,请检查!");
						} else {
							resultMap.put(ERROR, "导入摊分规则失败,执行至第" + counter + "行停止,生失效时间冲突,请检查!");
						}
						return resultMap;
					}
				} else {
					throw new ExcelParseException("该行有数据为空，请检查！");
				}

				counter++;
			}
			resultMap.put(RESULT, TRUE);
			resultMap.put("msg", "导入摊分规则成功,成功导入" + (counter - 1) + "条规则");
		} catch (ExcelParseException | BiffException | IOException e) {
			LOGGER.debug("Excel文件格式转换异常" + e.getMessage());
			resultMap.put(RESULT, FALSE);
			resultMap.put(ERROR, "导入摊分规则执行至第" + counter + "行停止," + e.getMessage());
		}
		return resultMap;

	}

	private SettleApportionRule dealDataFormat(String[] data, Integer operatorId) {
		Map<String, Integer> statusMap = getDicIdMap(1004);
		Map<String, Integer> cdrTypeMap = getDicIdMap(1001);
		Map<String, Integer> scopeMap = getDicIdMap(1019);
		String[] s = data;
		SettleApportionRule rule = new SettleApportionRule();
		if (length(s[0]) > 32) {
			throw new ExcelParseException("规则名称太长或为空，请检查！");
		} else {
			rule.setApportionRuleName(s[0]);
		}
		if (cdrTypeMap.get(s[1]) == null) {
			throw new ExcelParseException("资费类型信息不匹配，请检查！");
		} else {
			rule.setCdrType(cdrTypeMap.get(s[1]));
		}
		if (scopeMap.get(s[2]) == null) {
			throw new ExcelParseException("规则范围信息不匹配，请检查！");
		} else {
			rule.setScope(scopeMap.get(s[2]));
		}
		if (Double.valueOf(s[3]) > 1 || Double.valueOf(s[3]) < 0) {
			throw new ExcelParseException("摊分比例应该0~1之间，请检查！");
		} else {
			rule.setRatio(Double.valueOf(s[3]));
		}
		try {
			rule.setInureDate(sdf.parse(s[4]));
			rule.setExpireDate(sdf.parse(s[5]));
		} catch (ParseException e) {
			throw new ExcelParseException("生效时间或失效时间格式错误，请检查！");
		}
		if (statusMap.get(s[6]) == null) {
			throw new ExcelParseException("规则状态信息不匹配，请检查！");
		} else {
			rule.setStatus(statusMap.get(s[6]));
		}
		if (length(s[7]) > 512) {
			throw new ExcelParseException("备注太长，请检查！");
		} else {
			rule.setApportionDesc(s[7]);
		}
		rule.setOperatorId(operatorId);
		return rule;
	}

	/**
	 * 计算字符串字符长度
	 * 
	 * @param str
	 * @return
	 */
	private int length(String str) {
		int strLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < str.length(); i++) {
			/* 获取一个字符 */
			String temp = str.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				strLength += 2;
			} else {
				/* 其他字符长度为1 */
				strLength += 1;
			}
		}
		return strLength;
	}

}
