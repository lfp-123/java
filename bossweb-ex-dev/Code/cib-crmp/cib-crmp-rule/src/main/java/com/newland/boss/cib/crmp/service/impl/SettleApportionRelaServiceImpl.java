/**
 * 
 */
package com.newland.boss.cib.crmp.service.impl;

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
import com.newland.boss.cib.crmp.domain.RuleRela;
import com.newland.boss.cib.crmp.domain.SettleApportionRela;
import com.newland.boss.cib.crmp.service.SettleApportionRelaService;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.server.DictDefService;
import jxl.write.WriteException;
/**
 * @author ylc
 *
 *         2018-05-11
 */

@Component
@Transactional
public class SettleApportionRelaServiceImpl implements SettleApportionRelaService {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
    private SimpleDateFormat filesdf = new SimpleDateFormat("yyyyMMddHHmmss");

	@Autowired
	private SettleApportionRelaDao settleApportionRelaDao;
	@Autowired
	private DictDefService dictDefService;
	
	private static final Logger LOGGER = LogManager.getLogger(SettleApportionRelaService.class);
	
	public static final String RESULT= "result";
	
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
	public void addSettleApportionRela(String relaJson) {
		SettleApportionRela settleApportionRela = new SettleApportionRela();
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(relaJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		// 选择机构的ID
		Integer objectId = jsonObj.get("orgId").getAsInt();
		// 操作员工号
		Integer operatorId = jsonObj.get("operatorId").getAsInt();

		//新增默认规则为启用
		settleApportionRela.setStatus(1);
		settleApportionRela.setObjectType(1);
		settleApportionRela.setObjectId(objectId);
		settleApportionRela.setOperatorId(operatorId);
		settleApportionRela.setCreateDate(new Date());

		JsonArray relaArray = jsonObj.get("relaLists").getAsJsonArray();

		for (int i = 0; i < relaArray.size(); i++) {
			JsonObject obj = relaArray.get(i).getAsJsonObject();
			settleApportionRela.setApportionItemId(obj.get("apportionItemId").getAsInt());
			settleApportionRela.setInureDate(new Date(Long.parseLong(obj.get("inureDate").getAsString())));
			settleApportionRela.setExpireDate(new Date(Long.parseLong(obj.get("expireDate").getAsString())));
			// 查询某条规则是否存在，存在则更新，不存在则删除
			SettleApportionRela rela = settleApportionRelaDao
					.querySettleApportionRelaIsExistence(obj.get("apportionItemId").getAsInt(), objectId);
			if (rela != null) {
				settleApportionRela.setApportionRelaId(rela.getApportionRelaId());
				settleApportionRelaDao.updateSettleApportionRela(settleApportionRela);
			} else {
				settleApportionRelaDao.insertSettleApportionRela(settleApportionRela);
			}
		}
	}

	@Override
	public void removeSettleApportionRela(String relaJson) {

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(relaJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray relaArray = jsonObj.get("relaIdJson").getAsJsonArray(); // 选中的机构

		// 循环删除角色
		for (int i = 0; i < relaArray.size(); i++) {
			Integer relaId = relaArray.get(i).getAsInt();
			settleApportionRelaDao.deleteSettleApportionRela(relaId);
		}

	}

	@Override
	public void editSettleApportionRela(SettleApportionRela settleApportionRelaId) {
		settleApportionRelaDao.updateSettleApportionRela(settleApportionRelaId);
	}

	@Override
	public List<SettleApportionRela> loadAllSettleApportionRela() {
		return settleApportionRelaDao.queryAllSettleApportionRela();
	}

	@Override
	public List<SettleApportionRela> loadSettleApportionRelaByParam(SettleApportionRela settleApportionRela) {
		return settleApportionRelaDao.querySettleApportionRelaByParam(settleApportionRela);
	}

	@Override
	public void modifySettleApportionRelaStatus(String relaJson) {

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(relaJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray relaArray = jsonObj.get("relaIdArray").getAsJsonArray(); // 选中的rela
		Integer relaStatus = jsonObj.get("relaStatus").getAsInt();// 要改变的规则状态
		SettleApportionRela settleApportionRela = new SettleApportionRela();
		settleApportionRela.setStatus(relaStatus);
		// 循环更新规则状态
		for (int i = 0; i < relaArray.size(); i++) {
			Integer relaId = relaArray.get(i).getAsInt();
			settleApportionRela.setApportionRelaId(relaId);
			settleApportionRelaDao.updateSettleApportionRela(settleApportionRela);
		}
	}

	@Override
	public List<RuleRela> loadComQueryRelaByParam(RuleRela ruleRela) {
		if(ruleRela.getApportionRuleName() != null){
			ruleRela.setApportionRuleName(ruleRela.getApportionRuleName().replaceAll("_", "/_").replaceAll("%", "/%"));
		}
		return settleApportionRelaDao.combinQueryRuleRela(ruleRela,null);
	}
	
	@Override
	public List<RuleRela> loadComQueryRelaByOrgParam(String relaJson) {	
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(relaJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonArray orgIdArray = jsonObj.get("orgIdList").getAsJsonArray();
		List<Integer> orgIdList = new ArrayList<>();
		RuleRela ruleRela = new RuleRela();
		try {
			for (int i = 0; i < orgIdArray.size(); i++) {
				orgIdList.add(orgIdArray.get(i).getAsInt());
			}
			if (!jsonObj.get("apportionRuleName").isJsonNull() && !"\"\"".equals(jsonObj.get("apportionRuleName").toString())) {
				ruleRela.setApportionRuleName(jsonObj.get("apportionRuleName").getAsString().replaceAll("_", "/_").replaceAll("%", "/%"));
			}
			if (!jsonObj.get("status").isJsonNull() && !"\"\"".equals(jsonObj.get("status").toString())) {
				ruleRela.setStatus(jsonObj.get("status").getAsInt());
			}
			if(!jsonObj.get("inureDate").isJsonNull() && !"\"\"".equals(jsonObj.get("inureDate").toString())){
				ruleRela.setInureDate(sdf.parse(jsonObj.get("inureDate").getAsString()));
			}
			if (!jsonObj.get("expireDate").isJsonNull() && !"\"\"".equals(jsonObj.get("expireDate").toString())) {
				ruleRela.setExpireDate(sdf.parse(jsonObj.get("expireDate").getAsString()));
			}
		} catch (ParseException e) {
			LOGGER.debug("数据转换异常"+e.getMessage());
		}
		return settleApportionRelaDao.combinQueryRuleRela(ruleRela, orgIdList);
	}

	@Override
	public Map<String, String> exportSettleApportionRela(String relaJson) {
		Map<String, String> resultMap = new HashMap<>();
		// 1004规则类型数据字典
		Map<Integer, String> statusMap = getDicMap(1004);
		String title = "SettleRuleRelaExport"+ filesdf.format(new Date())+ ".xls";
		String path = System.getProperty("user.dir") + "/downloadTemp/" + title;     
		String[] columns = { "关系ID","单位ID", "单位名称", "摊分规则ID", "摊分规则名称", "启用状态", "生效时间", "失效时间", "操作工号ID", "创建时间" };
		boolean[] numberFlag = { false, false, false, false, false, false, false, false, false, false };
		List<String[]> dataList = new ArrayList<>();
		List<RuleRela> saRLists = this.loadComQueryRelaByOrgParam(relaJson);
		for (RuleRela r : saRLists) {
			String[] data = { String.valueOf(r.getApportionRelaId()),String.valueOf(r.getObjectId()), String.valueOf(r.getOrgName()),
					String.valueOf(r.getApportionItemId()), String.valueOf(r.getApportionRuleName()),
					statusMap.get(r.getStatus()), sdf.format(r.getInureDate()), sdf.format(r.getExpireDate()),
					String.valueOf(r.getOperatorId()), sdf.format(r.getCreateDate()) };
			dataList.add(data);
		}
		try {
			JxlTool.exportExcel(path, columns, dataList, numberFlag);
			resultMap.put(RESULT, TRUE);
			resultMap.put(FILENAME, URLEncoder.encode(title, "UTF-8"));
		} catch (WriteException|IOException e) {
			LOGGER.debug("文件导出异常" + e.getMessage());
			resultMap.put(RESULT, FALSE);
			resultMap.put(ERROR, e.getMessage());
		} 
		return resultMap;
	}

	@Override
	public List<SettleApportionRela> loadSettleApportionRelaByRuleId(Integer apportionItemId) {
		return settleApportionRelaDao.querySettleApportionRelaByRuleId(apportionItemId);
	}

	@Override
	public void addSettleApportionRelaByRuleId(List<SettleApportionRela> settleApportionRelaLists) {
		
		//新增之前先删除原有的规则
		settleApportionRelaDao.deleteSettleApportionRelaByRuleId(settleApportionRelaLists.get(0).getApportionItemId());
		if(settleApportionRelaLists.get(0).getObjectId() != null){
			for (SettleApportionRela settleApportionRela : settleApportionRelaLists) {
				settleApportionRela.setObjectType(1);
				settleApportionRela.setCreateDate(new Date());		
	            settleApportionRelaDao.insertSettleApportionRela(settleApportionRela);
			}
		}
	
	}

	@Override
	public List<SettleApportionRela> loadSettleApportionRelaByOrgId(Integer objectId) {
		return settleApportionRelaDao.querySettleApportionRelaByOrgId(objectId);
	}

}
