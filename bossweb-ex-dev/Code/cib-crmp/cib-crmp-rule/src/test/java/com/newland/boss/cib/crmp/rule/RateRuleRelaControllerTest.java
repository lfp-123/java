package com.newland.boss.cib.crmp.rule;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.controller.RateRuleRelaController;
import com.newland.boss.cib.crmp.dao.RateRuleRelaDao;
import com.newland.boss.cib.crmp.domain.RateRuleRela;
import com.newland.boss.cib.crmp.domain.RateRuleRelaView;
import com.newland.boss.cib.crmp.rule.common.AbstractControllerTestNGTest;
import com.newland.boss.cib.crmp.service.RateRuleRelaService;
import com.newland.boss.kpi.dao.DictDefDao;
import com.newland.boss.kpi.entity.DictDef;

public class RateRuleRelaControllerTest extends AbstractControllerTestNGTest {

    @Autowired
    private RateRuleRelaController rateRuleRelaController;

    @Autowired
    private RateRuleRelaService rateRuleRelaService;

    @Autowired
    private RateRuleRelaDao rateRuleRelaDao;

    @Autowired
    private DictDefDao dictDefDao;

    @Override
    protected Object getController() {
        return rateRuleRelaController;
    }

    private List<RateRuleRelaView> getRelaList() {
        List<RateRuleRelaView> relaList = new ArrayList<>();
        RateRuleRelaView rateRuleRelaView1 = new RateRuleRelaView();
        rateRuleRelaView1.setOperatorId(null);
        rateRuleRelaView1.setOperatorName(null);
        rateRuleRelaView1.setOrgId(1001);
        rateRuleRelaView1.setOrgName("福州分公司");
        rateRuleRelaView1.setRateItemId(10000001);
        rateRuleRelaView1.setRateRuleName("规则1");
        rateRuleRelaView1.setRateRuleRelaId(10000001);
        rateRuleRelaView1.setCdrType(1);
        rateRuleRelaView1.setStatus(1);
        rateRuleRelaView1.setInureDate(new Date());
        rateRuleRelaView1.setExpireDate(new Date());
        rateRuleRelaView1.setCreateDate(new Date());
        rateRuleRelaView1.setObjectType(2);
        rateRuleRelaView1.setObjectId(10000050);
        rateRuleRelaView1.setModifyOperator(1000001);
        rateRuleRelaView1.toString();
        relaList.add(rateRuleRelaView1);
        return relaList;
    }

    @Test
    public void testShowRateRuleRelaByObjectId() {
        RateRuleRelaView rateRuleRelaView = new RateRuleRelaView();
        rateRuleRelaView.setOperatorId(10000050);
        List<RateRuleRelaView> relaList = getRelaList();
        when(rateRuleRelaDao.selectRateRuleRelaByObjectId(Mockito.any(RateRuleRelaView.class))).thenReturn(relaList);
        String jsonStr = rateRuleRelaController.showRateRuleRelaByObjectId(rateRuleRelaView);
        assertEquals(jsonStr, JSONArray.toJSONString(relaList));
    }

    @Test
    public void testDeleteRateRuleRela() {
        String jsonStr = rateRuleRelaController
                .deleteRateRuleRela("{\"rateRuleRelaIdArray\":[10001,10002,10003],\"operatorId\":10000050}");
        assertEquals(jsonStr, "[{\"status\":\"success\"}]");
    }

    @Test
    public void testAddRateRuleRela() throws ParseException {
        RateRuleRela rateRuleRela = new RateRuleRela();
        when(rateRuleRelaDao.selectRateRuleRela(Mockito.any(RateRuleRela.class))).thenReturn(rateRuleRela);
        List<RateRuleRela> list = new ArrayList<>();
        list.add(rateRuleRela);
        when(rateRuleRelaDao.selectRuleRelaHeight(Mockito.any(RateRuleRelaView.class))).thenReturn(list);
        String jsonStr = rateRuleRelaController.addRateRuleRela("{\"relaLists\":[{\"objectType\":1,\"objectId\":1006,"
                + "\"rateItemId\":10000007,\"cdrType\":1,\"rateType\":3,\"priority\":3,\"inureDate\":1528819200000,"
                + "\"expireDate\":1528992000000,\"status\":\"1\",\"modifyOperator\":\"10000050\","
                + "\"createDate\":\"2018-06-27T01:27:54.638Z\"}],\"operatorId\":\"10000050\",\"objectId\":1006}");
        assertEquals(jsonStr, "{\"status\":\"规则：10000007,配置更新成功。<br>\"}");
    }

    @Test
    public void testRemoveRateRuleRelaByObjectIdForServiceImp() throws ParseException {
        rateRuleRelaService.removeRateRuleRelaByObjectId(10000050);
    }

    @Test
    public void testUpdateRateRuleRelaStatus() throws ParseException {
        String jsonStr = rateRuleRelaController.updateRateRuleRelaStatus(
                "{\"rateRuleRelaIdArray\":[10001,10002,10003],\"operatorId\":10000050,\"status\":1}");
        assertEquals(jsonStr, "[{\"status\":\"success\"}]");
    }

    @Test
    public void testLoadRateRuleRelaByRuleItemId() throws ParseException {
        RateRuleRela rateRuleRela = new RateRuleRela();
        rateRuleRela.setObjectType(1);
        rateRuleRela.setRateItemId(10000022);
        rateRuleRela.toString();
        List<RateRuleRela> ruleRelaList = new ArrayList<>();
        RateRuleRela rateRuleRela1 = new RateRuleRela();
        rateRuleRela1.setRateRuleRelaId(10000011);
        rateRuleRela1.setRateItemId(10000022);
        rateRuleRela1.setObjectId(100001);
        RateRuleRela rateRuleRela2 = new RateRuleRela();
        rateRuleRela2.setRateRuleRelaId(10000012);
        rateRuleRela2.setRateItemId(10000022);
        rateRuleRela2.setObjectId(100002);
        ruleRelaList.add(rateRuleRela1);
        ruleRelaList.add(rateRuleRela2);
        when(rateRuleRelaDao.selectRateRuleRelaByRateItemId(rateRuleRela.getRateItemId())).thenReturn(ruleRelaList);
        String jsonStr = rateRuleRelaController.loadRateRuleRelaByRuleItemId(rateRuleRela);
        assertEquals(jsonStr, JSONArray.toJSONString(ruleRelaList));
    }

    @Test
    public void testAddRateRuleRelaByRuleId() throws ParseException {
        List<RateRuleRelaView> rateRuleRelaViewlist = new ArrayList<>();
        RateRuleRelaView rateRuleRelaView = new RateRuleRelaView();
        rateRuleRelaView.setCdrType(1);
        rateRuleRelaView.setObjectId(1);
        rateRuleRelaView.setRateItemId(10000011);
        rateRuleRelaView.setStatus(1);
        rateRuleRelaView.setInureDate(new Date());
        rateRuleRelaView.setExpireDate(new Date());
        rateRuleRelaViewlist.add(rateRuleRelaView);
        String jsonStr = rateRuleRelaController.addRateRuleRelaByRuleId(rateRuleRelaViewlist);
        assertEquals(jsonStr, "[{\"status\":\"success\"}]");
    }

    @Test
    public void testExportRateRule() throws ParseException {
        List<DictDef> dictList = RateRuleControllerTest.getDictDef();
        when(dictDefDao.selectAllDictDef()).thenReturn(dictList);
        when(rateRuleRelaDao.selectRateRuleRelaView(Mockito.any(RateRuleRelaView.class), Mockito.any(Integer[].class)))
                .thenReturn(getRelaList());
        rateRuleRelaController.exportRateRuleRela(
                "{\"orgIdList\":[7,42],\"operatorName\":\"王五\",\"modifyOperator\":\"10000050\",\"rateRuleName\":\"IP\",\"status\":\"1\",\"inureDate\":\"2018-07-06\",\"expireDate\":\"2018-07-20\"}");
    }

}
