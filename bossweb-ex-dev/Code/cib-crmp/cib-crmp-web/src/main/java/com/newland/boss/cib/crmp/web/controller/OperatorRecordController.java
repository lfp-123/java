package com.newland.boss.cib.crmp.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.cib.crmp.domain.OperatorRecord;
import com.newland.boss.cib.crmp.web.service.OperatorRecordService;

@RestController
public class OperatorRecordController {

	private static final String UNIT_NAME = "unitName";
	private static final String NOTES_ID = "notesId";
	private static final String LOG_STATUS = "logStatusType";
	private static final String BEGIN_DATE = "beginDate";

	@Autowired
	private OperatorRecordService operatorRecordService;

	// 查询结算配置
	@RequestMapping(value = "/loadOperatorRecordByParam", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String loadOperatorRecordByParam(HttpServletRequest req, @RequestBody String reqJson){
		int page = Integer.parseInt(req.getParameter("page"));
		int size = Integer.parseInt(req.getParameter("size"));
		
		OperatorRecord operatorRecord = new OperatorRecord();
		Integer[] orgIdArray = jsonToIntegerArray(reqJson);
		operatorRecord.setNotesId(formatJsonToValue(NOTES_ID, reqJson));
		operatorRecord.setMonth(formatJsonToValue(BEGIN_DATE, reqJson)==null?null:Integer.parseInt(formatJsonToValue(BEGIN_DATE, reqJson)));
		operatorRecord.setSyncStatus(formatJsonToValue(LOG_STATUS, reqJson)==null?null:Integer.parseInt(formatJsonToValue(LOG_STATUS, reqJson)));
		
		List<OperatorRecord> recordList = operatorRecordService.queryOperRecordList(operatorRecord,orgIdArray, page, size);
		int cnt = operatorRecordService.countOperRecordNumber(operatorRecord, orgIdArray);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", recordList);
		map.put("cnt", cnt);
		return JSONArray.toJSONString(map);
	}
	
	/**
	 * 转化Json的字段为值
	 */
	public String formatJsonToValue(String json, String reqJson) {
		String value = null;
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonEl = jsonParser.parse(reqJson);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		
		try {
			value = jsonObj.get(json).getAsString();
			if (value.isEmpty()) {
				value = null;
			}
		} catch (Exception e) {
			value = null;
		}
		
		return value;
	}
	
	/**
	 *  解析传过来的机构编号数组
	 * @param json
	 * @return
	 */
	public Integer[] jsonToIntegerArray(String json) {
		JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(json);
        JsonObject jsonObj = jsonEl.getAsJsonObject(); 
        JsonArray orgIdJsonArray;
        try {
        	orgIdJsonArray = jsonObj.get(UNIT_NAME).getAsJsonArray();
        } catch (Exception e) {
        	orgIdJsonArray = null;
		}
        if (null != orgIdJsonArray) {
        	Integer[] orgIdArray = new Integer[orgIdJsonArray.size()];
        	for (int i = 0; i < orgIdJsonArray.size(); i++) {
                Integer orgId = orgIdJsonArray.get(i).getAsInt();
                orgIdArray[i] = orgId;
            }
            return orgIdArray;
        } else {
        	return new Integer[0];
        }
	}
}
