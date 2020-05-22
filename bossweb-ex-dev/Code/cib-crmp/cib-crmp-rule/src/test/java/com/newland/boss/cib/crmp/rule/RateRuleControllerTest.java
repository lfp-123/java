package com.newland.boss.cib.crmp.rule;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.controller.RateRuleController;
import com.newland.boss.cib.crmp.dao.RateRuleDao;
import com.newland.boss.cib.crmp.domain.RateRule;
import com.newland.boss.cib.crmp.rule.common.AbstractControllerTestNGTest;
import com.newland.boss.cib.crmp.service.RateRuleService;
import com.newland.boss.kpi.dao.DictDefDao;
import com.newland.boss.kpi.entity.DictDef;

public class RateRuleControllerTest extends AbstractControllerTestNGTest {

    @Autowired
    private RateRuleController rateRuleController;
    @Autowired
    private RateRuleService rateRuleService;
    @Autowired
    private RateRuleDao rateRuleDao;

    @Autowired
    private DictDefDao dictDefDao;

    @Override
    protected Object getController() {
        return rateRuleController;
    }

    private List<RateRule> getRuleList() {
        List<RateRule> ruleList = new ArrayList<RateRule>();
        RateRule rateRule1 = new RateRule();
        rateRule1.setRateItemId(100000001);
        rateRule1.setRateRuleName("规则1");
        rateRule1.setCdrType(1);
        rateRule1.setRateType(1);
        rateRule1.setFee(20000L);
        rateRule1.setOtherFee(2000L);
        rateRule1.setStartTime("10-22:10");
        rateRule1.setEndTime("10-26:10");
        rateRule1.setRateStatus(1);
        rateRule1.setInureDate("2018-08-13");
        rateRule1.setExpireDate("2030-08-13");
        rateRule1.setRateDesc("****");
        rateRule1.setOperatorId(10000050);
        rateRule1.setCreateTime("2018-08-13");
        rateRule1.setPriority(1);
        ruleList.add(rateRule1);
        return ruleList;
    }

    @Test
    public void testSelectRateRule() {
        RateRule rateRule = new RateRule();
        when(rateRuleDao.searchRateRule(Mockito.any(RateRule.class))).thenReturn(getRuleList());
        String jsonStr = rateRuleController.selectRateRule(rateRule);
        assertEquals(jsonStr, JSONArray.toJSONString(getRuleList()));
    }

    @Test
    public void testAddRateRule() {
        List<RateRule> ruleList = new ArrayList<RateRule>();
        RateRule rateRule1 = new RateRule();
        rateRule1.setRateRuleName("规则1");
        rateRule1.setCdrType(1);
        rateRule1.setRateType(1);
        rateRule1.setFee(20000L);
        rateRule1.setOtherFee(200000L);
        rateRule1.setStartTime("10-22:10");
        rateRule1.setEndTime("10-26:10");
        rateRule1.setRateStatus(1);
        rateRule1.setInureDate("2018-08-13");
        rateRule1.setExpireDate("2030-08-13");
        rateRule1.setOperatorId(10000050);
        rateRule1.setCreateTime("2018-08-13");
        rateRule1.setPriority(1);
        rateRule1.setScope(1);
        RateRule rateRule = new RateRule();
        when(rateRuleDao.selectRuleCross(rateRule)).thenReturn(ruleList);
        String jsonStr = rateRuleController.addRateRule(rateRule1);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("status", "success");
        resultMap.put("statuscode","1");
        resultMap.put("info", "规则添加成功");
        assertEquals(jsonStr, JSONArray.toJSONString(resultMap));
    }

    @Test
    public void testEditRateRule() {
        List<RateRule> ruleList = new ArrayList<RateRule>();
        RateRule rateRule1 = new RateRule();
        rateRule1.setRateItemId(100000001);
        rateRule1.setRateRuleName("规则1");
        rateRule1.setCdrType(1);
        rateRule1.setRateType(1);
        rateRule1.setFee(20000L);
        rateRule1.setOtherFee(20000L);
        rateRule1.setStartTime("10-22:10");
        rateRule1.setEndTime("10-26:10");
        rateRule1.setRateStatus(1);
        rateRule1.setInureDate("2018-08-13");
        rateRule1.setExpireDate("2030-08-13");
        rateRule1.setOperatorId(10000050);
        rateRule1.setScope(0);
        RateRule rateRule = new RateRule();
        when(rateRuleDao.selectRuleCross(rateRule)).thenReturn(ruleList);
        String jsonStr = rateRuleController.editRateRule(rateRule1);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("status", "success");
        resultMap.put("info", "规则修改成功");
        assertEquals(jsonStr, JSONArray.toJSONString(resultMap));
    }

    @Test
    public void testDeleteRateRule() {
        List<RateRule> ruleList = new ArrayList<RateRule>();
        when(rateRuleDao.searchRateRule(new RateRule())).thenReturn(ruleList);
        String jsonStr = rateRuleController
                .deleteRateRule("{\"rateItemIdArray\":[1000001,1000002,1000003],\"operatorId\":10000050}");
        assertEquals(jsonStr, JSONArray.toJSONString(ruleList));
    }

    @Test
    public void testUpdateRateRuleStatus() {
        List<RateRule> ruleList = new ArrayList<RateRule>();
        when(rateRuleDao.searchRateRule(Mockito.any(RateRule.class))).thenReturn(ruleList);
        String jsonStr = rateRuleController.updateRateRuleStatus(
                "{\"rateItemIdArray\":[1000001,1000002,1000003],\"operatorId\":10000050,\"rateStatus\":\"1\"}");
        assertEquals(jsonStr, "{\"status\":\"success\"}");
    }

    @Test
    public void testExportRateRule() {
        RateRule rateRule = new RateRule();
        List<RateRule> ruleList = new ArrayList<RateRule>();
        RateRule rateRule1 = new RateRule();
        rateRule1.setRateItemId(100000001);
        rateRule1.setRateRuleName("规则1");
        rateRule1.setCdrType(1);
        rateRule1.setRateType(1);
        rateRule1.setFee(2000000L);
        rateRule1.setStartTime("10-22:10");
        rateRule1.setEndTime("10-26:10");
        rateRule1.setRateStatus(1);
        rateRule1.setInureDate("2018-08-13");
        rateRule1.setExpireDate("2030-08-13");
        rateRule1.setOperatorId(10000050);
        rateRule1.setCreateTime("2018-08-13");
        rateRule1.setPriority(1);
        rateRule1.toString();
        ruleList.add(rateRule1);
        when(rateRuleDao.searchRateRule(rateRule)).thenReturn(ruleList);
        List<DictDef> dictList = getDictDef();
        when(dictDefDao.selectAllDictDef()).thenReturn(dictList);
        rateRuleController.exportRateRule(rateRule);
    }

    @Test
    public void testImportRateRuleForService() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/rule/template/RateRuleTemplate.xls");
        copyFile(file.getParent() + "/RateRule.xls", file.getAbsolutePath());
        File file2 = new File(System.getProperty("user.dir") + "/src/test/resources/rule/template/RateRule.xls");
        List<DictDef> dictList = getDictDef();
        when(dictDefDao.selectAllDictDef()).thenReturn(dictList);
        Map<String, String> resultMap1 = rateRuleService.importRateRule(file2.getAbsolutePath(), 10000050);
        Map<String, String> resultMap2 = new HashMap<>();
        resultMap2.put("success", "导入资费规则执行至第8行停止,资源类型与资费类型不匹配，请检查！");
        assertEquals(JSONArray.toJSONString(resultMap1), JSONArray.toJSONString(resultMap2));
    }

    @Test
    public void testSelectRateRuleToDistribute() {
        when(rateRuleDao.searchRuleToDistribute(Mockito.any(RateRule.class))).thenReturn(getRuleList());
        String jsonStr = rateRuleController.selectRateRuleToDistribute(new RateRule());
        assertEquals(jsonStr, JSONArray.toJSONString(getRuleList()));
    }

    protected static List<DictDef> getDictDef() {
        List<DictDef> dictList = new ArrayList<DictDef>();
        DictDef dictDef41 = new DictDef();
        dictDef41.setDictClass(1019);
        dictDef41.setDictName("规则使用范围");
        dictDef41.setEntryId(0);
        dictDef41.setEntryName("个性化");
        DictDef dictDef42 = new DictDef();
        dictDef42.setDictClass(1019);
        dictDef42.setDictName("规则使用范围");
        dictDef42.setEntryId(1);
        dictDef42.setEntryName("全局");

        DictDef dictDef31 = new DictDef();
        dictDef31.setDictClass(1006);
        dictDef31.setDictName("收费类型");
        dictDef31.setEntryId(1);
        dictDef31.setEntryName("分钟");
        DictDef dictDef32 = new DictDef();
        dictDef32.setDictClass(1006);
        dictDef32.setDictName("收费类型");
        dictDef32.setEntryId(2);
        dictDef32.setEntryName("方数/小时");
        DictDef dictDef33 = new DictDef();
        dictDef33.setDictClass(1006);
        dictDef33.setDictName("收费类型");
        dictDef33.setEntryId(3);
        dictDef33.setEntryName("包月");
        DictDef dictDef34 = new DictDef();
        dictDef34.setDictClass(1006);
        dictDef34.setDictName("收费类型");
        dictDef34.setEntryId(6);
        dictDef34.setEntryName("包年");
        DictDef dictDef35 = new DictDef();
        dictDef35.setDictClass(1006);
        dictDef35.setDictName("收费类型");
        dictDef35.setEntryId(7);
        dictDef35.setEntryName("方数/分钟");
        DictDef dictDef36 = new DictDef();
        dictDef36.setDictClass(1006);
        dictDef36.setDictName("收费类型");
        dictDef36.setEntryId(8);
        dictDef36.setEntryName("固定费");

        DictDef dictDef21 = new DictDef();
        dictDef21.setDictClass(1004);
        dictDef21.setDictName("规则状态");
        dictDef21.setEntryId(1);
        dictDef21.setEntryName("启用");
        DictDef dictDef22 = new DictDef();
        dictDef22.setDictClass(1004);
        dictDef22.setDictName("规则状态");
        dictDef22.setEntryId(2);
        dictDef22.setEntryName("禁用");

        DictDef dictDef11 = new DictDef();
        dictDef11.setDictClass(1001);
        dictDef11.setDictName("话单类型");
        dictDef11.setEntryId(1);
        dictDef11.setEntryName("IP电话");
        DictDef dictDef12 = new DictDef();
        dictDef12.setDictClass(1001);
        dictDef12.setDictName("话单类型");
        dictDef12.setEntryId(3);
        dictDef12.setEntryName("DMA");
        DictDef dictDef13 = new DictDef();
        dictDef13.setDictClass(1001);
        dictDef13.setDictName("话单类型");
        dictDef13.setEntryId(4);
        dictDef13.setEntryName("网络专线");
        DictDef dictDef14 = new DictDef();
        dictDef14.setDictClass(1001);
        dictDef14.setDictName("话单类型");
        dictDef14.setEntryName("IP语音会议");
        dictDef14.setEntryId(2);
        dictList.add(dictDef11);
        dictList.add(dictDef12);
        dictList.add(dictDef13);
        dictList.add(dictDef14);
        dictList.add(dictDef21);
        dictList.add(dictDef22);
        dictList.add(dictDef31);
        dictList.add(dictDef32);
        dictList.add(dictDef33);
        dictList.add(dictDef34);
        dictList.add(dictDef35);
        dictList.add(dictDef36);
        dictList.add(dictDef41);
        dictList.add(dictDef42);
        return dictList;
    }

    public static void copyFile(String fname, String targetPath) throws IOException {
        FileInputStream in = new FileInputStream(new File(targetPath));
        FileOutputStream out = new FileOutputStream(new File(fname));
        byte[] buff = new byte[10240]; // 限制大小
        int n = 0;
        while ((n = in.read(buff)) != -1) {
            out.write(buff, 0, n);
        }
        out.flush();
        in.close();
        out.close();
    }

}
