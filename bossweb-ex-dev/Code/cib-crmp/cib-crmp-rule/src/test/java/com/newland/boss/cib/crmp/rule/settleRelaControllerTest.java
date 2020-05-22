package com.newland.boss.cib.crmp.rule;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.controller.SettleApportionRelaController;
import com.newland.boss.cib.crmp.dao.SettleApportionRelaDao;
import com.newland.boss.cib.crmp.domain.RuleRela;
import com.newland.boss.cib.crmp.domain.SettleApportionRela;
import com.newland.boss.cib.crmp.rule.common.AbstractControllerTestNGTest;
import com.newland.boss.kpi.dao.DictDefDao;
import com.newland.boss.kpi.entity.DictDef;

public class settleRelaControllerTest extends AbstractControllerTestNGTest{

	@Autowired
    private SettleApportionRelaController settleApportionRelaController;
	
	@Autowired
    private SettleApportionRelaDao settleApportionRelaDao;
	
	@Autowired
    private DictDefDao dictDefDao;
	
	private static final Logger LOGGER = LogManager.getLogger(settleRelaControllerTest.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected Object getController() {
		return settleApportionRelaController;
	}
	
	@Test(enabled=true)
	public void testSelectSettleApportionRela() {
		List<RuleRela> ruleList=new ArrayList<RuleRela>();
		RuleRela settleRelas=new RuleRela();
		settleRelas.setApportionRelaId(10000001);
		settleRelas.setObjectId(1);
		settleRelas.setObjectType(1001);
		settleRelas.setOrgName("机构");
		settleRelas.setApportionItemId(10000010);
		settleRelas.setApportionRuleName("测试规则");
		settleRelas.setStatus(0);
		settleRelas.setInureDate(new Date());
		settleRelas.setExpireDate(new Date());
		settleRelas.setOperatorId(100000050);
		settleRelas.setCreateDate(new Date());
		LOGGER.info(settleRelas.toString());
		ruleList.add(settleRelas);
		when(settleApportionRelaDao.combinQueryRuleRela(settleRelas,null)).thenReturn(ruleList);
		String jsonStr=settleApportionRelaController.loadComQueryRelaByParam(settleRelas);
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
	@Test(enabled=true)
	public void testSelectOrgSettleApportionRela() {
		List<RuleRela> ruleList=new ArrayList<RuleRela>();
		RuleRela settleRelas=new RuleRela();
		try {
			settleRelas.setInureDate(sdf.parse("2018-07-23"));
			settleRelas.setExpireDate(sdf.parse("2018-07-23"));
		} catch (ParseException e) {
			LOGGER.debug("时间类型转换错误"+e.getMessage());
		}
		settleRelas.setObjectId(1001);
		settleRelas.setStatus(0);
		settleRelas.setApportionRuleName("测试规则");
		settleRelas.setOperatorId(100000050);
		LOGGER.info(settleRelas.toString());
		List<Integer> orgIdList = new ArrayList<>();
		orgIdList.add(1001);
		when(settleApportionRelaDao.combinQueryRuleRela(settleRelas,orgIdList)).thenReturn(ruleList);
		String jsonStr=settleApportionRelaController.loadComQueryRelaByOrgParam("{'orgIdList':[],'operatorId':10000050,'apportionRuleName':'测试规则','status':'0','inureDate':'2018-07-23','expireDate':'2018-07-23'}");
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
	@Test
	public void testloadSettleApportionRelaByRuleId() {
		List<SettleApportionRela> ruleList=new ArrayList<SettleApportionRela>();
		SettleApportionRela ruleRela = new SettleApportionRela();
		ruleRela.setApportionRelaId(10000001);
		when(settleApportionRelaDao.querySettleApportionRelaByRuleId(ruleRela.getApportionRelaId())).thenReturn(ruleList);
		String jsonStr=settleApportionRelaController.loadSettleApportionRelaByRuleId(10000001);
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	 
	@Test
	public void testloadSettleApportionRelaByOrgId() {
		List<SettleApportionRela> ruleList=new ArrayList<SettleApportionRela>();
		SettleApportionRela ruleRela = new SettleApportionRela();
		ruleRela.setApportionRelaId(10000001);
		ruleRela.setObjectId(5555);
		when(settleApportionRelaDao.querySettleApportionRelaByOrgId(ruleRela.getObjectId())).thenReturn(ruleList);
		String jsonStr=settleApportionRelaController.loadSettleApportionRelaByOrgId(ruleRela);
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
	@Test(enabled=true)
	public void testCombinQuerySettleApportionRela() {
		List<RuleRela> ruleList= new ArrayList<RuleRela>();
		SettleApportionRela settleRuleRela = new SettleApportionRela();
		settleRuleRela.setObjectType(2);
		settleRuleRela.setObjectId(1001);
		settleRuleRela.setApportionItemId(10000001);
		settleRuleRela.setStatus(0);
		settleRuleRela.setInureDate(new Date(Long.parseLong("1528819200000")));
		settleRuleRela.setExpireDate(new Date(Long.parseLong("1528819200000")));
		settleRuleRela.setOperatorId(10000050);
		settleRuleRela.setCreateDate(new Date(Long.parseLong("1528819200000")));
		RuleRela ruleRela = new RuleRela();
		ruleRela.setObjectType(settleRuleRela.getObjectType());
		ruleRela.setObjectId(settleRuleRela.getObjectId());
		ruleRela.setApportionItemId(settleRuleRela.getApportionItemId());
		ruleRela.setStatus(settleRuleRela.getStatus());
		ruleRela.setInureDate(settleRuleRela.getInureDate());
		ruleRela.setExpireDate(settleRuleRela.getExpireDate());
		ruleRela.setOperatorId(settleRuleRela.getOperatorId());
		ruleRela.setCreateDate(settleRuleRela.getCreateDate());
		when(settleApportionRelaDao.combinQueryRuleRela(ruleRela,null)).thenReturn(ruleList);
		String jsonStr=settleApportionRelaController.addSettleApportionRela("{'relaLists':[{'objectType':2,'objectId':1001,'apportionItemId':10000001,'status':0,'inureDate':1528819200000,'expireDate':1528819200000,'operatorId':10000050,'createDate':1528819200000}],'operatorId':10000050,'orgId':10001}");
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
	@Test(enabled=true)
	public void testAddSettleApportionRelaByRuleId() {
		List<SettleApportionRela> ruleList= new ArrayList<SettleApportionRela>();
		SettleApportionRela settleRules= new SettleApportionRela();
		settleRules.setApportionRelaId(100000001);
		settleRules.setObjectType(2);
		settleRules.setObjectId(1000);
		settleRules.setApportionItemId(100000001);
		settleRules.setStatus(0);
		settleRules.setInureDate(new Date());
		settleRules.setExpireDate(new Date());
		settleRules.setOperatorId(10000050);
		settleRules.setCreateDate(new Date());
		LOGGER.info(settleRules.toString());
		ruleList.add(settleRules);
		String jsonStr=settleApportionRelaController.addSettleApportionRelaByRuleId(ruleList);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", "true");
		assertEquals(jsonStr, "[{\"result\":\"true\"}]");
	}
	
	@Test(enabled=true)
	public void testDeleteSettleApportionRule() {
		List<RuleRela> ruleList=new ArrayList<RuleRela>();
		RuleRela ruleRela = new RuleRela();
		when(settleApportionRelaDao.combinQueryRuleRela(ruleRela,null)).thenReturn(ruleList);
		String jsonStr=settleApportionRelaController.deleteSettleApportionRela("{'relaIdJson':[100000001,100000002],'operatorId':10000050,'orgId':10001}");
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
	@Test(enabled=true)
	public void testModifySettleApportionRelaStatus() {
		List<RuleRela> ruleList=new ArrayList<RuleRela>();
		RuleRela ruleRela = new RuleRela();
		when(settleApportionRelaDao.combinQueryRuleRela(ruleRela,null)).thenReturn(ruleList);
		String jsonStr=settleApportionRelaController.modifySettleApportionRelaStatus("{'relaIdArray':[100000001,100000002],'relaStatus':0,'operatorId':10000050,'orgId':10001}");
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
    @Test(enabled = true)
	public void testExportSettleApportionRelaByParam() {
		RuleRela settleRelas=new RuleRela();
		settleRelas.setOrgName("机构");
		settleRelas.setApportionRuleName("测试规则");
		settleRelas.setStatus(0);
		settleRelas.setInureDate(new Date());
		settleRelas.setExpireDate(new Date());
		List<DictDef> dictList= new RateRuleControllerTest().getDictDef();
		when(dictDefDao.selectAllDictDef()).thenReturn(dictList);
		String jsonStr=settleApportionRelaController.exportSettleApportionRelaByParam("{'orgIdList':[],'operatorId':10000050,'apportionRuleName':'','status':'','inureDate':'','expireDate':''}");
		@SuppressWarnings("unchecked")
		Map<String, String> resultMap1 = (Map<String, String>) JSONArray.parse(jsonStr);
		System.out.println(jsonStr);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", "true");
		resultMap.put("filename", resultMap1.get("filename"));
		assertEquals(jsonStr, JSONArray.toJSONString(resultMap));
	}


}
