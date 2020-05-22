package com.newland.boss.cib.crmb.web;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.newland.boss.cib.crmb.web.common.AbstractControllerTestNGTest;
import com.newland.boss.cib.crmp.domain.BillSummingOrgDay;
import com.newland.boss.cib.crmp.domain.BillSummingUsrDay;
import com.newland.boss.cib.crmp.settle.dao.BillSummingOrgDayDao;
import com.newland.boss.cib.crmp.settle.dao.BillSummingUsrDayDao;
import com.newland.boss.cib.crmp.web.controller.IndexController;
import com.newland.boss.kpi.admin.dao.OrganizationDao;
import com.newland.boss.kpi.dao.DictDefDao;
import com.newland.boss.kpi.dao.OrgDao;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;

public class IndexControllerTest extends AbstractControllerTestNGTest {
	
	@Autowired
	private IndexController indexController;
	
	@Autowired
    private BillSummingOrgDayDao billSummingOrgDayDao;
	
	@Autowired
    private BillSummingUsrDayDao billSummingUsrDayDao;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private DictDefDao dictDefDao;
	
	@Autowired
	private AppSession appSession;
	
	@Autowired
    private OrgDao orgDao;
	
	@Override
	protected Object getController() {
		return indexController;
	}
	
	@Test
	public void getTotalAnalysisResultTest() {
		List<BillSummingUsrDay> pieList = new ArrayList<BillSummingUsrDay>();
    	BillSummingUsrDay tmp = new BillSummingUsrDay();
    	tmp.setCdrType(1);
    	tmp.setTotalFee(2020L);
    	BillSummingUsrDay tmp1 = new BillSummingUsrDay();
    	tmp1.setCdrType(2);
    	tmp1.setTotalFee(3120L);
    	BillSummingUsrDay tmp2 = new BillSummingUsrDay();
    	tmp2.setCdrType(3);
    	tmp2.setTotalFee(7421L);
    	pieList.add(tmp);
    	pieList.add(tmp1);
    	pieList.add(tmp2);
		
    	List<Map<String, Object>> lineList = new ArrayList<Map<String, Object>>();
    	
    	LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		List<Integer> dates = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			dates.add(Integer.parseInt(date.minus(i, ChronoUnit.MONTHS).format(formatter)));
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("dates", dates);
		resultMap.put("line", lineList);
		resultMap.put("pie", pieList);
    	
		User user = new User();
    	user.setOperatorLevel(1);
    	user.setOperatorId(10000050);
    	when(appSession.getUser()).thenReturn(user);
    	
    	when(billSummingUsrDayDao.selecPieResultByOperId(Mockito.anyMap())).thenReturn(pieList);
    	when(billSummingUsrDayDao.selecLineResultByOperId(Mockito.anyMap())).thenReturn(lineList);
		
		String jsonStr = indexController.getTotalAnalysisResult();
		assertEquals(jsonStr, JSONObject.toJSONString(resultMap));
	}
	
	@Test
	public void getTotalAnalysisResultTest1() {
		User user = new User();
    	user.setOperatorLevel(2);
    	user.setOperatorId(10000050);
    	user.setOrgId(1005);
    	when(appSession.getUser()).thenReturn(user);
    	
    	List<Organization> orgs = new ArrayList<>();
    	Organization org = new Organization();
    	org.setOrgId(1005);
    	org.setOrgName("aa");
    	orgs.add(org);
    	
    	List<BillSummingOrgDay> barList = new ArrayList<BillSummingOrgDay>();
    	BillSummingOrgDay bartmp = new BillSummingOrgDay();
    	bartmp.setOrgId(1006);
    	bartmp.setTotalFee(2020L);
    	BillSummingOrgDay bartmp1 = new BillSummingOrgDay();
    	bartmp.setOrgId(1012);
    	bartmp.setTotalFee(5121L);
    	barList.add(bartmp);
    	barList.add(bartmp1);
    	
    	List<BillSummingOrgDay> pieList = new ArrayList<BillSummingOrgDay>();
    	BillSummingOrgDay pietmp = new BillSummingOrgDay();
    	pietmp.setCdrType(1);
    	pietmp.setTotalFee(2020L);
    	BillSummingOrgDay pietmp1 = new BillSummingOrgDay();
    	pietmp1.setCdrType(2);
    	pietmp1.setTotalFee(3120L);
    	BillSummingOrgDay pietmp2 = new BillSummingOrgDay();
    	pietmp2.setCdrType(3);
    	pietmp2.setTotalFee(7421L);
    	pieList.add(pietmp);
    	pieList.add(pietmp1);
    	pieList.add(pietmp2);
    	
    	List<Map<String, Object>> lineList = new ArrayList<Map<String, Object>>();
    	
    	LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		List<Integer> dates = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			dates.add(Integer.parseInt(date.minus(i, ChronoUnit.MONTHS).format(formatter)));
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("dates", dates);
		resultMap.put("line", lineList);
		resultMap.put("pie", pieList);
		
		List<DictDef> cdrTypes = new ArrayList<>();
		DictDef d1 = new DictDef();
		d1.setDictClass(1001);
		d1.setEntryId(1);
		DictDef d2 = new DictDef();
		d2.setDictClass(1001);
		d2.setEntryId(2);
		DictDef d3 = new DictDef();
		d3.setDictClass(1001);
		d3.setEntryId(3);
		cdrTypes.add(d1);
		cdrTypes.add(d2);
		cdrTypes.add(d3);
		
		when(dictDefDao.selectAllDictDef()).thenReturn(cdrTypes);
		
    	when(billSummingOrgDayDao.selecPieResultByOrgId(Mockito.anyMap())).thenReturn(pieList);
    	when(billSummingOrgDayDao.selecLineResultByOrgId(Mockito.anyMap())).thenReturn(lineList);
		
    	String jsonStr = indexController.getTotalAnalysisResult();
		assertEquals(jsonStr, JSONObject.toJSONString(resultMap));
	}
	
	@Test
	public void getTotalAnalysisResultTest2() {
		User user = new User();
    	user.setOperatorLevel(3);
    	user.setOperatorId(10000050);
    	user.setOrgId(-1);
    	when(appSession.getUser()).thenReturn(user);
    	
    	List<Organization> orgs = new ArrayList<>();
    	Organization org = new Organization();
    	org.setOrgId(1005);
    	org.setOrgName("aa");
    	org.setSuperOrgId(-1);
    	orgs.add(org);
    	Organization org1 = new Organization();
    	org1.setOrgId(1006);
    	org1.setOrgName("bb");
    	org1.setSuperOrgId(1005);
    	orgs.add(org1);
    	Organization org2 = new Organization();
    	org2.setOrgId(1007);
    	org2.setOrgName("cc");
    	org2.setSuperOrgId(1006);
    	orgs.add(org2);
    	
    	List<BillSummingOrgDay> barList = new ArrayList<BillSummingOrgDay>();
    	BillSummingOrgDay bartmp = new BillSummingOrgDay();
    	bartmp.setTotalFee(2020L);
    	bartmp.setOrgId(1004);
    	BillSummingOrgDay bartmp1 = new BillSummingOrgDay();
    	bartmp1.setTotalFee(5121L);
    	bartmp1.setOrgId(1007);
    	BillSummingOrgDay bartmp2 = new BillSummingOrgDay();
    	bartmp2.setTotalFee(2121L);
    	bartmp2.setOrgId(1007);
    	barList.add(bartmp);
    	barList.add(bartmp1);
    	barList.add(bartmp2);
    	
    	List<BillSummingOrgDay> pieList = new ArrayList<BillSummingOrgDay>();
    	BillSummingOrgDay pietmp = new BillSummingOrgDay();
    	pietmp.setCdrType(1);
    	pietmp.setTotalFee(2020L);
    	BillSummingOrgDay pietmp1 = new BillSummingOrgDay();
    	pietmp1.setCdrType(2);
    	pietmp1.setTotalFee(3120L);
    	BillSummingOrgDay pietmp2 = new BillSummingOrgDay();
    	pietmp2.setCdrType(3);
    	pietmp2.setTotalFee(7421L);
    	pieList.add(pietmp);
    	pieList.add(pietmp1);
    	pieList.add(pietmp2);
    	
    	List<Map<String, Object>> lineList = new ArrayList<Map<String, Object>>();
    	
    	LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		List<Integer> dates = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			dates.add(Integer.parseInt(date.minus(i, ChronoUnit.MONTHS).format(formatter)));
		}
		Map<String, Object> barResult = new HashMap<>();
		barResult.put("1", barList);
		barResult.put("2", barList);
		barResult.put("3", barList);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("dates", dates);
		resultMap.put("orgs", orgs);
		resultMap.put("totalBar", barList);
		resultMap.put("bar", barResult);
		resultMap.put("line", lineList);
		resultMap.put("pie", pieList);
		
    	when(billSummingOrgDayDao.selectSubTotalBarResult(Mockito.anyMap())).thenReturn(barList);
    	when(billSummingOrgDayDao.selecTotalPieResult(Mockito.anyMap())).thenReturn(pieList);
    	when(billSummingOrgDayDao.selecTotalLineResult(Mockito.anyMap())).thenReturn(lineList);
    	when(organizationDao.selectAllOrganization()).thenReturn(orgs);
    	when(organizationDao.selectAllRootOrgId()).thenReturn(orgs);
		
    	String jsonStr = indexController.getTotalAnalysisResult();
    	System.out.println(jsonStr);
//		assertEquals(jsonStr, JSONObject.toJSONString(resultMap));
	}
	
	@Test
	public void getTotalAnalysisResultTest3() {
		User user = new User();
    	user.setOperatorLevel(4);
    	user.setOperatorId(10000050);
    	user.setOrgId(-1);
    	when(appSession.getUser()).thenReturn(user);
    	
    	LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		List<Integer> dates = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			dates.add(Integer.parseInt(date.minus(i, ChronoUnit.MONTHS).format(formatter)));
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("dates", dates);
		
    	String jsonStr = indexController.getTotalAnalysisResult();
		assertEquals(jsonStr, JSONObject.toJSONString(resultMap));
	}
	
	@Test
	public void getTotalAnalysisResultTest4() {
		User user = new User();
    	user.setOperatorLevel(3);
    	user.setOperatorId(10000050);
    	user.setOrgId(-1);
    	when(appSession.getUser()).thenReturn(user);
    	
    	List<Organization> orgs = new ArrayList<>();
    	Organization org = new Organization();
    	org.setOrgId(1005);
    	org.setOrgName("aa");
    	org.setSuperOrgId(-1);
    	Organization org1 = new Organization();
    	org1.setOrgId(1006);
    	org1.setOrgName("bb");
    	org1.setSuperOrgId(1005);
    	orgs.add(org1);
    	Organization org2 = new Organization();
    	org2.setOrgId(1007);
    	org2.setOrgName("cc");
    	org2.setSuperOrgId(1006);
    	orgs.add(org2);
    	
    	List<BillSummingOrgDay> barList = new ArrayList<BillSummingOrgDay>();
    	
    	List<BillSummingOrgDay> pieList = new ArrayList<BillSummingOrgDay>();
    	BillSummingOrgDay pietmp = new BillSummingOrgDay();
    	pietmp.setCdrType(1);
    	pietmp.setTotalFee(2020L);
    	BillSummingOrgDay pietmp1 = new BillSummingOrgDay();
    	pietmp1.setCdrType(2);
    	pietmp1.setTotalFee(3120L);
    	BillSummingOrgDay pietmp2 = new BillSummingOrgDay();
    	pietmp2.setCdrType(3);
    	pietmp2.setTotalFee(7421L);
    	pieList.add(pietmp);
    	pieList.add(pietmp1);
    	pieList.add(pietmp2);
    	
    	List<Map<String, Object>> lineList = new ArrayList<Map<String, Object>>();
    	
    	LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		List<Integer> dates = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			dates.add(Integer.parseInt(date.minus(i, ChronoUnit.MONTHS).format(formatter)));
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("dates", dates);
		resultMap.put("orgs", orgs);
		resultMap.put("totalBar", barList);
		resultMap.put("line", lineList);
		resultMap.put("pie", pieList);
		
    	when(billSummingOrgDayDao.selectBarResult(Mockito.anyMap())).thenReturn(barList);
    	when(billSummingOrgDayDao.selecPieResultByOrgId(Mockito.anyMap())).thenReturn(pieList);
    	when(billSummingOrgDayDao.selecLineResultByOrgId(Mockito.anyMap())).thenReturn(lineList);
    	when(organizationDao.findByFullName(Mockito.anyString())).thenReturn(org);
    	when(organizationDao.selectHeadOrgInfos(Mockito.anyInt())).thenReturn(new ArrayList<Organization>());
    	when(organizationDao.selectAllRootOrgId()).thenReturn(orgs);
    	when(orgDao.getAllOrganization()).thenReturn(null);
    	
    	String jsonStr = indexController.getTotalAnalysisResult();
		assertEquals(jsonStr, JSONObject.toJSONString(resultMap));
	}
	
	@Test
	public void reFreshInitDataTest() {
		User user = new User();
		when(appSession.getUser()).thenReturn(user);
		String jsonStr = indexController.reFreshInitData();
		assertEquals(jsonStr, "{\"msg\":\"获取登录用户失败！\", \"code:\":\"-1\"}");
	}
	
	@Test
	public void reFreshInitDataTest1() {
		User user = new User();
    	user.setOperatorLevel(3);
    	user.setOperatorId(10000050);
    	user.setOrgId(-1);
    	when(appSession.getUser()).thenReturn(user);
    	
		String jsonStr = indexController.reFreshInitData();
		assertEquals(jsonStr, "{\"msg\":\"刷新成功！\", \"code:\":\"0\"}");
	}
}
