package com.newland.boss.cib.crmp.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.web.model.QueryRecalculationReq;
import com.newland.boss.cib.crmp.web.model.QueryRecalculationResp;
import com.newland.boss.cib.crmp.web.service.QueryRecalculationService;
import com.newland.boss.kpi.server.AppSession;

@RestController
public class RecalculationController {

	@Autowired
	private QueryRecalculationService queryRecalculationService;
	
	@Autowired
	private AppSession appSession;
	
	@RequestMapping(value="/queryRecalculation", produces = "text/json;charset=UTF-8")
	public String queryRecalculation(HttpServletRequest req, @RequestBody QueryRecalculationReq recalculationReq) {
		int page = Integer.parseInt(req.getParameter("page"));
		int size = Integer.parseInt(req.getParameter("size"));
		
		List<QueryRecalculationResp> list = queryRecalculationService.queryRecalcCdrTaskListResult(recalculationReq, page, size);
		int cnt = queryRecalculationService.queryRecalcCdrTaskListCount(recalculationReq);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("cnt", cnt);
		return JSONArray.toJSONString(map);
	}
	
	@RequestMapping(value="/createRecalcCdrTask", produces = "text/json;charset=UTF-8")
	public String createRecalcCdrTask(@RequestBody QueryRecalculationReq recalculationReq) {
		recalculationReq.setOperatorId(appSession.getUser().getOperatorId());
		Map<String, String> map = queryRecalculationService.createRecalcCdrTask(recalculationReq); 
		return JSONArray.toJSONString(map);
	}
}
