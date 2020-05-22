package com.newland.boss.kpi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newland.boss.kpi.AbstractControllerTestNGTest;
import com.newland.boss.kpi.dao.DictDefDao;
import com.newland.boss.kpi.entity.DictDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class DictDefControllerTestng extends AbstractControllerTestNGTest {


    @Autowired
    private DictDefController dictDefController;

    @Autowired
    private DictDefDao dictDefDao;

    @Override
    protected Object getController() {
        return dictDefController;
    }

    @Test
    public void testGetDictDefByDictClass() throws Exception {
        when(dictDefDao.selectAllDictDef()).thenReturn(selectAllDictDefTestData());
        DictDef dictDef = new DictDef();
        dictDef.setDictClass(1001);
        String dictDefByDictClass = dictDefController.getDictDefByDictClass(dictDef);
        JSONArray jsonArray = JSONArray.parseArray(dictDefByDictClass);
        System.out.println("===>:" + dictDefByDictClass);
        Assert.assertEquals(jsonArray.size(), 3);
    }

    @Test
    public void testGetDictDefByDictClassAndEntryId() throws Exception {

        DictDef dictDef = new DictDef();
        dictDef.setDictClass(1001);
        dictDef.setEntryId(1);
        String dictDefByDictClass = dictDefController.getDictDefByDictClassAndEntryId(dictDef);
        System.out.println("===>:" + dictDefByDictClass);
        JSONObject jsonObject = JSONObject.parseObject(dictDefByDictClass);
        Assert.assertEquals(jsonObject.getString("entryName"), "CDR");

    }

    private static List<DictDef> selectAllDictDefTestData() {
        List<DictDef> dictDefs = new ArrayList<>();
        DictDef dictDef = new DictDef();
        dictDef.setDictClass(1001);
        dictDef.setEntryId(1);
        dictDef.setDictName("话单类型1");
        dictDef.setEntryName("CDR");

        DictDef dictDef2 = new DictDef();
        dictDef2.setDictClass(1001);
        dictDef2.setEntryId(2);
        dictDef2.setDictName("话单类型");
        dictDef2.setEntryName("DMA");

        DictDef dictDef3 = new DictDef();
        dictDef3.setDictClass(1001);
        dictDef3.setEntryId(3);
        dictDef3.setDictName("话单类型");
        dictDef3.setEntryName("网络专线");
        dictDefs.add(dictDef);
        dictDefs.add(dictDef2);
        dictDefs.add(dictDef3);

        return dictDefs;
    }

}