package com.newland.boss.cib.crmb.web;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmb.web.common.AbstractControllerTestNGTest;
import com.newland.boss.cib.crmp.web.controller.RecalculationController;
import com.newland.boss.cib.crmp.web.dao.RecalcCdrTaskDao;
import com.newland.boss.cib.crmp.web.model.QueryRecalculationReq;
import com.newland.boss.cib.crmp.web.model.QueryRecalculationResp;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;



public class RecalculationControllerTest extends AbstractControllerTestNGTest  {

	@Autowired
	private RecalculationController recalculationController;
	@Autowired
	private AppSession appSession;
	@Autowired
	private RecalcCdrTaskDao recalcCdrTaskDao;
	
	@Test
    public void testRecalculation() throws Exception {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
    	when(req.getParameter("page")).thenReturn("1");
    	when(req.getParameter("size")).thenReturn("10");
    	User user = new User();
    	user.setOperatorId(100001);
    	when(appSession.getUser()).thenReturn(user);
    	
    	QueryRecalculationReq recalculationReq = new QueryRecalculationReq();
    	recalculationReq.setBeginDate("20180405");
    	recalculationReq.setBillMonth("201806");
    	recalculationReq.setCdrType("1");
    	recalculationReq.setEndDate("20180606");
    	recalculationReq.setOperatorId(100001);
    	recalculationReq.setRecalcType("1");
    	System.out.println(recalculationReq.getCdrType()+" "+recalculationReq.getRecalcType()+" "+recalculationReq.getBillMonth()+" "+recalculationReq.getBeginDate()+" "+recalculationReq.getEndDate());
    	QueryRecalculationResp queryRecalculationResp = new QueryRecalculationResp();
    	queryRecalculationResp.setBillMonth("201806");
    	queryRecalculationResp.setCdrType("1");
    	queryRecalculationResp.setCreateTime("20180606");
    	queryRecalculationResp.setEndTime("20180606");
    	queryRecalculationResp.setOperatorId("100001");
    	queryRecalculationResp.setRecalcProgress("预处理");
    	queryRecalculationResp.setRecalcTaskId("100000");
    	queryRecalculationResp.setRecalcType("预处理前");
    	queryRecalculationResp.setStartTime("20180606");
    	queryRecalculationResp.setStatus("处理中");
    	List<QueryRecalculationResp> respList = new ArrayList<QueryRecalculationResp>();
    	respList.add(queryRecalculationResp);
    	
    	when(recalcCdrTaskDao.queryRecalcCdrTaskListResult(recalculationReq, 1, 10)).thenReturn(respList);
    	when(recalcCdrTaskDao.queryRecalcCdrTaskListCount(recalculationReq)).thenReturn(1);
    	String queryResult = recalculationController.queryRecalculation(req, recalculationReq);
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", respList);
		map.put("cnt", 1);
		assertEquals(queryResult, JSONArray.toJSONString(map));
		
		when(recalcCdrTaskDao.undoneRecalcCdrTasktCount(recalculationReq)).thenReturn(0);
		queryResult = recalculationController.createRecalcCdrTask(recalculationReq);
		Map<String, Object> insertResultmap = new HashMap<String, Object>();
		insertResultmap.put("status", "success");
		insertResultmap.put("info", "重算任务生成成功");
		assertEquals(queryResult, JSONArray.toJSONString(insertResultmap));
		
		when(recalcCdrTaskDao.undoneRecalcCdrTasktCount(recalculationReq)).thenReturn(1);
		queryResult = recalculationController.createRecalcCdrTask(recalculationReq);
		insertResultmap.put("status", "error");
		insertResultmap.put("info", "存在未完成重算，请确认！");
		assertEquals(queryResult, JSONArray.toJSONString(insertResultmap));
	}
	
	@Override
	protected Object getController() {
		return recalculationController;
	}

}
