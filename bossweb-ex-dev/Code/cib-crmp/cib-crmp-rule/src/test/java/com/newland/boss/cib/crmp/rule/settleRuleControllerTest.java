package com.newland.boss.cib.crmp.rule;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.controller.SettleApportionRuleController;
import com.newland.boss.cib.crmp.dao.SettleApportionRuleDao;
import com.newland.boss.cib.crmp.domain.SettleApportionRule;
import com.newland.boss.cib.crmp.exception.ExcelParseException;
import com.newland.boss.cib.crmp.rule.common.AbstractControllerTestNGTest;
import com.newland.boss.cib.crmp.service.SettleApportionRuleService;
import com.newland.boss.kpi.dao.DictDefDao;
import com.newland.boss.kpi.entity.DictDef;

public class settleRuleControllerTest extends AbstractControllerTestNGTest{

	@Autowired
    private SettleApportionRuleController settleApportionRuleController;
	@Autowired
	SettleApportionRuleService settleApportionRuleService;
	@Autowired
    private SettleApportionRuleDao settleApportionRuleDao;
    
	@Autowired
    private DictDefDao dictDefDao;
	
	private static final Logger LOGGER = LogManager.getLogger(settleRuleControllerTest.class);
	
	@Override
	protected Object getController() {
		return settleApportionRuleController;
	}
	
	@Test(enabled=true) 
	public void testLoadSettleApportionRuleByParam() {
		List<SettleApportionRule> ruleList=new ArrayList<SettleApportionRule>();
		SettleApportionRule settleRules=new SettleApportionRule();
		settleRules.setApportionRuleName("测试规则");
		settleRules.setCdrType(2);
		settleRules.setInureDate(new Date());
		settleRules.setExpireDate(new Date());
		settleRules.setStatus(0);
		LOGGER.info(settleRules.toString());
		ruleList.add(settleRules);
		when(settleApportionRuleDao.querySettleApportionRuleByParam(settleRules)).thenReturn(ruleList);
		String jsonStr=settleApportionRuleController.loadSettleApportionRuleByParam(settleRules);
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
	@Test(enabled=true)
	public void testLoadAllSettleApportionRule() {
		List<SettleApportionRule> ruleList=new ArrayList<SettleApportionRule>();
		when(settleApportionRuleDao.queryAllSettleApportionRule()).thenReturn(ruleList);
		String jsonStr=settleApportionRuleController.loadAllSettleApportionRule();
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	 
	@Test(enabled=true)
	public void testAddSettleApportionRule() {
		List<SettleApportionRule> ruleList=new ArrayList<SettleApportionRule>();
		SettleApportionRule settleRules=new SettleApportionRule();
		settleRules.setApportionItemId(100000001);
		settleRules.setApportionRuleName("测试规则");
		settleRules.setCdrType(2);
		settleRules.setRatio(0.85);
		settleRules.setInureDate(new Date());
		settleRules.setExpireDate(new Date());
		settleRules.setStatus(0);
		settleRules.setApportionDesc("11");
		settleRules.setOperatorId(10000050);
		settleRules.setCreateTime(new Date());
		when(settleApportionRuleDao.queryAllSettleApportionRule()).thenReturn(ruleList);
		String jsonStr=settleApportionRuleController.addSettleApportionRule(settleRules);
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
	@Test(enabled=true)
	public void testEditSettleApportionRule() {
		List<SettleApportionRule> ruleList=new ArrayList<SettleApportionRule>();
		SettleApportionRule settleRules=new SettleApportionRule();
		settleRules.setApportionItemId(100000001);
		settleRules.setApportionRuleName("测试规则");
		settleRules.setCdrType(2);
		settleRules.setRatio(0.85);
		settleRules.setInureDate(new Date());
		settleRules.setExpireDate(new Date());
		settleRules.setStatus(0);
		settleRules.setApportionDesc("11");
		settleRules.setOperatorId(10000050);
		when(settleApportionRuleDao.queryAllSettleApportionRule()).thenReturn(ruleList);
		String jsonStr=settleApportionRuleController.updateSettleApportionRule(settleRules);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", "true");
		assertEquals(jsonStr, JSONArray.toJSONString(resultMap));
	}
	
	@Test(enabled=true)
	public void testDeleteSettleApportionRule() {
		List<SettleApportionRule> ruleList=new ArrayList<SettleApportionRule>();
		when(settleApportionRuleDao.queryAllSettleApportionRule()).thenReturn(ruleList);
		String jsonStr=settleApportionRuleController.deleteSettleApportionRule("{'ruleIdJson':[100000001,100000002],'operatorId':10000050}");
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
	@Test(enabled=true)
	public void testModifySettleApportionRuleStatus() {
		List<SettleApportionRule> ruleList=new ArrayList<SettleApportionRule>();
		when(settleApportionRuleDao.queryAllSettleApportionRule()).thenReturn(ruleList);
		String jsonStr=settleApportionRuleController.modifySettleApportionRuleStatus("{'ruleIdArray':[100000001,100000002],'ruleStatus':0,'operatorId':10000050}");
		System.out.println(jsonStr);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", "true");
		assertEquals(jsonStr, JSONArray.toJSONString(resultMap));
	}
	
	@Test(enabled=true)
	public void testShowRoleById() {
		SettleApportionRule ruleList=new SettleApportionRule();
		SettleApportionRule settleRules=new SettleApportionRule();
		settleRules.setApportionItemId(100000001);
		settleRules.setApportionRuleName("测试规则");
		settleRules.setCdrType(2);
		settleRules.setRatio(0.85);
		settleRules.setInureDate(new Date());
		settleRules.setExpireDate(new Date());
		settleRules.setStatus(0);
		settleRules.setApportionDesc("11");
		settleRules.setOperatorId(10000050);
		when(settleApportionRuleDao.getSettleApportionRuleById(settleRules.getApportionItemId())).thenReturn(ruleList);
		String jsonStr=settleApportionRuleController.showRoleById(settleRules.getApportionItemId());
		assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
	}
	
    @Test(enabled = true)
	public void testExportSettleApportionRuleByParam() {
		SettleApportionRule settleRules=new SettleApportionRule();
		settleRules.setApportionRuleName("测试规则");
		settleRules.setCdrType(2);
		settleRules.setInureDate(new Date());
		settleRules.setExpireDate(new Date());
		settleRules.setStatus(0);
		settleRules.setOperatorId(10000050);
		List<DictDef> dictList=RateRuleControllerTest.getDictDef();
		when(dictDefDao.selectAllDictDef()).thenReturn(dictList);
		String jsonStr=settleApportionRuleController.exportSettleApportionRuleByParam(settleRules);
		@SuppressWarnings("unchecked")
		Map<String, String> resultMap1 = (Map<String, String>) JSONArray.parse(jsonStr);
		System.out.println(jsonStr);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", "true");
		resultMap.put("filename", resultMap1.get("filename"));
		assertEquals(jsonStr, JSONArray.toJSONString(resultMap));
	}

	@Test(enabled=true)
	public void testImportSettleApportionRule() {
	
	    File file=new File(System.getProperty("user.dir")+"/src/test/resources/rule/template/SettleRuleTemplate.xls");
	    try {
			RateRuleControllerTest.copyFile(file.getParent()+"/SettleRule.xls", file.getAbsolutePath());
		} catch (IOException e) {
			LOGGER.debug("文件复制失败"+e.getMessage());
		}
	    File file2=new File(System.getProperty("user.dir")+"/src/test/resources/rule/template/SettleRule.xls");
	    List<DictDef> dictList=RateRuleControllerTest.getDictDef();
	    when(dictDefDao.selectAllDictDef()).thenReturn(dictList);
	    Map<String, String> preResultMap= settleApportionRuleService.importSettleApportionRule(file2.getAbsolutePath(), 10000050);
	    Map<String, String> resultMap = new HashMap<>();
	    resultMap.put("result", "true");
	    resultMap.put("msg","导入摊分规则成功,成功导入1条规则");
	    assertEquals(JSONArray.toJSONString(preResultMap), JSONArray.toJSONString(resultMap));
	}
	
	@Test(enabled=true)
	public void ExcelParseException() {
		
		try{
			Throwable throwable = null;
			throw new ExcelParseException("规则范围信息不匹配，请检查！",throwable);
		}catch(ExcelParseException e){
			LOGGER.debug(e.getMessage());
		}
		
		try{
			Throwable throwable = null;
			throw new ExcelParseException("规则范围信息不匹配，请检查！",throwable,true,true);
		}catch(ExcelParseException e){
			LOGGER.debug(e.getMessage());
		}

	}
	
	

}
