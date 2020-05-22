package com.newland.boss.kpi.controller;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.server.OperateLogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ylc 2018-10-24
 */
@RestController
public class OperateLogController {
	
	private static final String OPERATE_MODULE = "operateModule";
	private static final String OPERATE_MONTH = "beginDate";
	
	@Autowired
	private OperateLogService operateLogService;

	@RequestMapping(value = "/getOperateLogList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String getOperateLogList(HttpServletRequest req, @RequestBody String reqJson) {
		int page = Integer.parseInt(req.getParameter("page"));
		int size = Integer.parseInt(req.getParameter("size"));
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateType(formatJsonToValue(OPERATE_MODULE, reqJson)==null?null:Integer.parseInt(formatJsonToValue(OPERATE_MODULE, reqJson)));
		operateLog.setOperateMonth(formatJsonToValue(OPERATE_MONTH, reqJson)==null?null:Integer.parseInt(formatJsonToValue(OPERATE_MONTH, reqJson)));
		List<OperateLog> logList = operateLogService.queryOperLogList(operateLog, page, size);
		int cnt = operateLogService.countOperLogNumber(operateLog);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", logList);
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

}