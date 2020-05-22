package com.newland.boss.cib.crmp.settle;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.domain.BillSummingOrgDay;
import com.newland.boss.cib.crmp.domain.BillSummingUsrDay;
import com.newland.boss.cib.crmp.settle.common.AbstractControllerTestNGTest;
import com.newland.boss.cib.crmp.settle.controller.AnalysisController;
import com.newland.boss.cib.crmp.settle.dao.BillSummingOrgDayDao;
import com.newland.boss.cib.crmp.settle.dao.BillSummingUsrDayDao;
import com.newland.boss.cib.crmp.settle.service.BillSummingOrgDayService;
import com.newland.boss.kpi.dao.OrgDao;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;

public class AnalysisControllerTest extends AbstractControllerTestNGTest {
	
	@Autowired
    private AnalysisController analysisController;
	
	@Autowired
    private BillSummingOrgDayDao billSummingOrgDayDao;
	
	@Autowired
    private BillSummingUsrDayDao billSummingUsrDayDao;
	
	@Autowired
    private BillSummingOrgDayService billSummingUsrDayService;
	
	@Autowired
    private OrgDao orgDao;
	
	@Autowired
    private AppSession appSession;
	
	@Override
	protected Object getController() {
		return analysisController;
	}
	
	@Test
    public void testGetFeeAnalysisPie() throws Exception {
    	List<BillSummingOrgDay> retList = new ArrayList<BillSummingOrgDay>();
    	BillSummingOrgDay tmp = new BillSummingOrgDay();
    	tmp.setCdrType(1);
    	tmp.setTotalFee(2020L);
    	BillSummingOrgDay tmp1 = new BillSummingOrgDay();
    	tmp1.setCdrType(2);
    	tmp1.setTotalFee(3120L);
    	BillSummingOrgDay tmp2 = new BillSummingOrgDay();
    	tmp2.setCdrType(3);
    	tmp2.setTotalFee(7421L);
    	retList.add(tmp);
    	retList.add(tmp1);
    	retList.add(tmp2);
    	
    	User user = new User();
    	user.setOperatorLevel(3);
    	when(appSession.getUser()).thenReturn(user);
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("orgId", 1006);
    	param.put("startDate", 20180101);
    	param.put("endDate", 20180522);
    	when(billSummingOrgDayDao.selecPieResultByOrgId(param)).thenReturn(retList);
       
        String jsonStr = analysisController.getFeeAnalysisPie(param);
        assertEquals(jsonStr, JSONArray.toJSONString(retList));
    }
	
    @Test
    public void testGetFeeAnalysisBar() throws Exception {
    	List<Organization> orgs = new ArrayList<>();
    	Organization o1 = new Organization();
    	o1.setOrgId(1006);
    	o1.setSuperOrgId(-1);
    	orgs.add(o1);
    	Organization o2 = new Organization();
    	o2.setOrgId(1012);
    	o2.setSuperOrgId(-1);
    	orgs.add(o2);
    	
    	List<BillSummingOrgDay> retList = new ArrayList<BillSummingOrgDay>();
    	BillSummingOrgDay tmp = new BillSummingOrgDay();
    	tmp.setOrgId(1006);
    	tmp.setTotalFee(2020L);
    	BillSummingOrgDay tmp1 = new BillSummingOrgDay();
    	tmp1.setOrgId(1012);
    	tmp1.setTotalFee(5121L);
    	retList.add(tmp);
    	retList.add(tmp1);
    	
    	User user = new User();
    	user.setOperatorLevel(3);
    	when(appSession.getUser()).thenReturn(user);
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("startDate", 20180101);
    	param.put("endDate", 20180522);
    	when(billSummingOrgDayDao.selectBarResult(param)).thenReturn(retList);
    	when(orgDao.getAllOrganization()).thenReturn(orgs);
    	
    	String jsonStr = analysisController.getFeeAnalysisBar(param);
    	assertEquals(jsonStr, JSONArray.toJSONString(retList));
    }

    @Test
    public void testGetFeeAnalysisLine() throws Exception {
    	List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
    	
    	User user = new User();
    	user.setOperatorLevel(3);
    	when(appSession.getUser()).thenReturn(user);
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("orgId", 1012);
    	param.put("startDate", 20180101);
    	param.put("endDate", 20180522);
    	when(billSummingOrgDayDao.selecLineResultByOrgId(param)).thenReturn(retList);
    	
    	String jsonStr = analysisController.getFeeAnalysisLine(param);
    	assertEquals(jsonStr, JSONArray.toJSONString(retList));
    }
    
    @Test
    public void testGetFeeAnalysisPie1() throws Exception {
    	List<BillSummingUsrDay> retList = new ArrayList<BillSummingUsrDay>();
    	BillSummingUsrDay tmp = new BillSummingUsrDay();
    	tmp.setCdrType(1);
    	tmp.setTotalFee(2020L);
    	BillSummingUsrDay tmp1 = new BillSummingUsrDay();
    	tmp1.setCdrType(2);
    	tmp1.setTotalFee(3120L);
    	BillSummingUsrDay tmp2 = new BillSummingUsrDay();
    	tmp2.setCdrType(3);
    	tmp2.setTotalFee(7421L);
    	retList.add(tmp);
    	retList.add(tmp1);
    	retList.add(tmp2);
    	
    	User user = new User();
    	user.setOperatorLevel(1);
    	user.setOperatorId(10000050);
    	when(appSession.getUser()).thenReturn(user);
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("startDate", 20180101);
    	param.put("endDate", 20180522);
    	when(billSummingUsrDayDao.selecPieResultByOperId(param)).thenReturn(retList);
       
        String jsonStr = analysisController.getFeeAnalysisPie(param);
        assertEquals(jsonStr, JSONArray.toJSONString(retList));
    }
	
    @Test
    public void testGetFeeAnalysisBar1() throws Exception {
    	List<BillSummingUsrDay> retList = new ArrayList<BillSummingUsrDay>();
    	BillSummingUsrDay tmp = new BillSummingUsrDay();
    	tmp.setOrgId(1006);
    	tmp.setTotalFee(2020L);
    	BillSummingUsrDay tmp1 = new BillSummingUsrDay();
    	tmp.setOrgId(1012);
    	tmp.setTotalFee(5121L);
    	retList.add(tmp);
    	retList.add(tmp1);
    	
    	User user = new User();
    	user.setOperatorLevel(1);
    	user.setOperatorId(10000050);
    	when(appSession.getUser()).thenReturn(user);
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("startDate", 20180101);
    	param.put("endDate", 20180522);
    	when(billSummingUsrDayDao.selectBarResult(param)).thenReturn(retList);
    	
    	String jsonStr = analysisController.getFeeAnalysisBar(param);
    	assertEquals(jsonStr, JSONArray.toJSONString(retList));
    }

    @Test
    public void testGetFeeAnalysisLine1() throws Exception {
    	List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
    	
    	User user = new User();
    	user.setOperatorLevel(1);
    	user.setOperatorId(10000050);
    	when(appSession.getUser()).thenReturn(user);
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("startDate", 20180101);
    	param.put("endDate", 20180522);
    	when(billSummingUsrDayDao.selecLineResultByOperId(param)).thenReturn(retList);
    	
    	String jsonStr = analysisController.getFeeAnalysisLine(param);
    	assertEquals(jsonStr, JSONArray.toJSONString(retList));
    }
    
    
    @Test
    public void testGetFeeAnalysisPie2() throws Exception {
    	List<BillSummingUsrDay> retList = new ArrayList<BillSummingUsrDay>();
    	BillSummingUsrDay tmp = new BillSummingUsrDay();
    	tmp.setCdrType(1);
    	tmp.setTotalFee(2020L);
    	BillSummingUsrDay tmp1 = new BillSummingUsrDay();
    	tmp1.setCdrType(2);
    	tmp1.setTotalFee(3120L);
    	BillSummingUsrDay tmp2 = new BillSummingUsrDay();
    	tmp2.setCdrType(3);
    	tmp2.setTotalFee(7421L);
    	retList.add(tmp);
    	retList.add(tmp1);
    	retList.add(tmp2);
    	
    	User user = new User();
    	user.setOperatorLevel(2);
    	user.setOrgId(1);
    	user.setOperatorId(10000050);
    	when(appSession.getUser()).thenReturn(user);
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("startDate", 20180101);
    	param.put("endDate", 20180522);
    	when(billSummingOrgDayDao.selecPieResultByOrgId(Mockito.anyMap())).thenReturn(retList);
       
        String jsonStr = analysisController.getFeeAnalysisPie(param);
        assertEquals(jsonStr, JSONArray.toJSONString(retList));
    }
	
    @Test
    public void testGetFeeAnalysisBar2() throws Exception {
    	List<BillSummingUsrDay> retList = new ArrayList<BillSummingUsrDay>();
    	BillSummingUsrDay tmp = new BillSummingUsrDay();
    	tmp.setOrgId(1006);
    	tmp.setTotalFee(2020L);
    	BillSummingUsrDay tmp1 = new BillSummingUsrDay();
    	tmp.setOrgId(1012);
    	tmp.setTotalFee(5121L);
    	retList.add(tmp);
    	retList.add(tmp1);
    	
    	User user = new User();
    	user.setOperatorLevel(2);
    	user.setOrgId(1);
    	user.setOperatorId(10000050);
    	when(appSession.getUser()).thenReturn(user);
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("startDate", 20180101);
    	param.put("endDate", 20180522);
    	when(billSummingOrgDayDao.selectBarResult(Mockito.anyMap())).thenReturn(retList);
    	
    	String jsonStr = analysisController.getFeeAnalysisBar(param);
    	assertEquals(jsonStr, JSONArray.toJSONString(retList));
    }

    @Test
    public void testGetFeeAnalysisLine2() throws Exception {
    	List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
    	
    	User user = new User();
    	user.setOperatorLevel(2);
    	user.setOrgId(1);
    	user.setOperatorId(10000050);
    	when(appSession.getUser()).thenReturn(user);
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("startDate", 20180101);
    	param.put("endDate", 20180522);
    	when(billSummingOrgDayDao.selecLineResultByOrgId(Mockito.anyMap())).thenReturn(retList);
    	
    	String jsonStr = analysisController.getFeeAnalysisLine(param);
    	assertEquals(jsonStr, JSONArray.toJSONString(retList));
    }
    
    @Test
    public void testBillSummingOrgDay() {
    	BillSummingOrgDay billSummingOrgDay = new BillSummingOrgDay();
    	billSummingOrgDay.setBillDay(20180524);
    	billSummingOrgDay.setBillMonth(201805);
    	billSummingOrgDay.setCdrType(1);
    	billSummingOrgDay.setCreateTime("20180524161110");
    	billSummingOrgDay.setOrgId(1006);
    	billSummingOrgDay.setTotalFee(200012L);
    	billSummingOrgDay.setTotalMinutes(2000L);
    	billSummingOrgDay.setTotalNumber(32L);
    }
    
    @Test
    public void testBillSummingUsrDay() {
    	BillSummingUsrDay billSummingUsrDay = new BillSummingUsrDay();
    	billSummingUsrDay.setOperatorId(10000050);
    	billSummingUsrDay.setBillDay(20180524);
    	billSummingUsrDay.setBillMonth(201805);
    	billSummingUsrDay.setCdrType(1);
    	billSummingUsrDay.setCreateTime("20180524161110");
    	billSummingUsrDay.setOrgId(1006);
    	billSummingUsrDay.setTotalFee(200012L);
    	billSummingUsrDay.setTotalMinutes(2000L);
    	billSummingUsrDay.setTotalNumber(32L);
    }
    
    @Test
    public void billSummingUsrDayServiceTest() {
    	List<BillSummingUsrDay> retList = new ArrayList<BillSummingUsrDay>();
    	BillSummingUsrDay tmp = new BillSummingUsrDay();
    	tmp.setCdrType(1);
    	tmp.setTotalFee(2020L);
    	BillSummingUsrDay tmp1 = new BillSummingUsrDay();
    	tmp1.setCdrType(2);
    	tmp1.setTotalFee(3120L);
    	BillSummingUsrDay tmp2 = new BillSummingUsrDay();
    	tmp2.setCdrType(3);
    	tmp2.setTotalFee(7421L);
    	retList.add(tmp);
    	retList.add(tmp1);
    	retList.add(tmp2);
    	
    	List<Map<String, Object>> lineList = new ArrayList<Map<String, Object>>();
    	
    	when(billSummingOrgDayDao.selecTotalPieResult(Mockito.anyMap())).thenReturn(retList);
    	billSummingUsrDayService.selecTotalPieResult(Mockito.anyMap());
    	
    	when(billSummingOrgDayDao.selecTotalLineResult(Mockito.anyMap())).thenReturn(lineList);
    	billSummingUsrDayService.selecTotalLineResult(Mockito.anyMap());
    	
    	when(billSummingOrgDayDao.selectSubTotalBarResult(Mockito.anyMap())).thenReturn(retList);
    	billSummingUsrDayService.selectSubTotalBarResult(Mockito.anyMap());
    }
}