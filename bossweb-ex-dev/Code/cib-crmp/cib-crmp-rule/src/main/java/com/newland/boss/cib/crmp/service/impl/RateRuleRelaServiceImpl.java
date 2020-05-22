package com.newland.boss.cib.crmp.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
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
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.cib.crmp.common.JxlTool;
import com.newland.boss.cib.crmp.dao.RateRuleRelaDao;
import com.newland.boss.cib.crmp.domain.RateRuleRela;
import com.newland.boss.cib.crmp.domain.RateRuleRelaView;
import com.newland.boss.cib.crmp.service.RateRuleRelaService;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.server.DictDefService;

import jxl.write.WriteException;

@Component
public class RateRuleRelaServiceImpl implements RateRuleRelaService {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat filesdf = new SimpleDateFormat("yyyyMMddHHmmss");
    @Autowired
    private RateRuleRelaDao rateRuleRelaDao;

    @Autowired
    private DictDefService dictDefService;

    private static final Logger log = LogManager.getLogger(RateRuleRelaServiceImpl.class);
    private static final String STATUS = "status";
    private static final String INFO = "info";
    private static final String FILENAME = "filename";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    /**
     * 根据ID查询数据字典
     * 
     * @return Map
     */
    public Map<Integer, String> getDicMap(Integer dicId) {
        Map<Integer, String> dicMap = new HashMap<>();
        List<DictDef> dicList = dictDefService.getDictDefByDictClass(dicId);
        for (DictDef dictDef : dicList) {
            dicMap.put(dictDef.getEntryId(), dictDef.getEntryName());
        }
        return dicMap;
    }

    @Override
    public Map<String, String> addRateRuleRela(RateRuleRelaView rateRuleRelaView) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("ID", rateRuleRelaView.getRateItemId().toString());
        RateRuleRela rateRuleRela = viewToRela(changeDateFormat(rateRuleRelaView));
        // 检查规则是否存在
        RateRuleRela rela = rateRuleRelaDao.selectRateRuleRela(rateRuleRela);
        if (rela != null) {
            rateRuleRela.setRateRuleRelaId(rela.getRateRuleRelaId());
            rateRuleRelaDao.deleteRateRuleRela(rela.getRateRuleRelaId());
            rateRuleRelaDao.insertRateRuleRela(rateRuleRela);
            resultMap.put(STATUS, "配置更新成功。");
        } else {
            if (rateRuleRelaDao.selectRuleRelaCross(changeDateFormat(rateRuleRelaView)).isEmpty()) {
                // 规则不存在同类型时间交叉性
                rateRuleRelaDao.insertRateRuleRela(rateRuleRela);
                resultMap.put(STATUS, "添加成功。");
            } else {
                resultMap.put(STATUS, "存在同种规则，添加失败");
            }
        }
        return resultMap;
    }

    @Override
    public void removeRateRuleRela(String rateRuleRelaJson) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(rateRuleRelaJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        JsonArray rateRuleRelaArray = jsonObj.get("rateRuleRelaIdArray").getAsJsonArray(); // 选中的资费规则分配
        for (int i = 0; i < rateRuleRelaArray.size(); i++) {
            Integer rateRuleRelaId = rateRuleRelaArray.get(i).getAsInt();
            rateRuleRelaDao.deleteRateRuleRela(rateRuleRelaId);
        }

    }

    @Override
    public List<RateRuleRelaView> selectRateRuleRelaByObjectId(RateRuleRelaView rateRuleRelaView) {
        return rateRuleRelaDao.selectRateRuleRelaByObjectId(rateRuleRelaView);
    }

    @Override
    public Map<String, String> exportRateRuleRela(List<RateRuleRelaView> list) {
        Map<String, String> resultMap = new HashMap<>();
        Map<Integer, String> statusMap = getDicMap(1004);
        Map<Integer, String> cdrTypeMap = getDicMap(1001);
        String title = "RateRuleRela" + filesdf.format(new Date()) + ".xls";
        String path = System.getProperty("user.dir") + "/downloadTemp/" + title;
        String[] columns = { "编号", "单位编号", "单位名称", "规则ID", "规则名称", "资费类型", "分配生效时间", "分配失效时间", "启用状态" };
        boolean[] numberFlag = { false, false, false, false, false, false, false, false, false };
        List<String[]> dataList = new ArrayList<>();
        String operationName;
        String operationId;
        for (RateRuleRelaView r : list) {

            String[] date = { String.valueOf(r.getRateRuleRelaId()), String.valueOf(r.getOrgId()),
                    String.valueOf(r.getOrgName()), String.valueOf(r.getRateItemId()),
                    String.valueOf(r.getRateRuleName()), cdrTypeMap.get(r.getCdrType()), sdf.format(r.getInureDate()),
                    sdf.format(r.getExpireDate()), statusMap.get(r.getStatus()) };
            dataList.add(date);
        }
        try {
            JxlTool.exportExcel(path, columns, dataList, numberFlag);
            resultMap.put(STATUS, SUCCESS);
            resultMap.put(FILENAME, URLEncoder.encode(title, "UTF-8"));
        } catch (WriteException | IOException e) {
            log.debug(e.getMessage());
            resultMap.put(STATUS, FAIL);
            resultMap.put(INFO, e.getMessage());
        }
        return resultMap;
    }

    @Override
    public void editRateRuleRelaStatus(String rateRuleJson) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(rateRuleJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        JsonArray rateRuleRelaArray = jsonObj.get("rateRuleRelaIdArray").getAsJsonArray(); // 选中的rela
        Integer status = jsonObj.get(STATUS).getAsInt();// 要改变的规则状态
        RateRuleRela rateRuleRela = new RateRuleRela();
        rateRuleRela.setStatus(status);
        // 循环更新规则状态
        for (int i = 0; i < rateRuleRelaArray.size(); i++) {
            Integer rateRuleRelaId = rateRuleRelaArray.get(i).getAsInt();
            rateRuleRela.setRateRuleRelaId(rateRuleRelaId);
            rateRuleRelaDao.updateRateRuleRelaStatus(rateRuleRela);
        }
    }

    @Override
    public void removeRateRuleRelaByRateItemId(Integer rateItemId) {
        rateRuleRelaDao.deleteRateRuleRelaByRateItemId(rateItemId);
    }

    @Override
    public List<RateRuleRela> selectRateRuleRelaByRateItemId(Integer rateItemId) {
        return rateRuleRelaDao.selectRateRuleRelaByRateItemId(rateItemId);
    }

    @Override
    public void removeRateRuleRelaByObjectId(Integer objectId) {
        rateRuleRelaDao.deleteRateRuleRelaByObjectId(objectId);
    }

    @Override
    public List<RateRuleRelaView> searchRateRuleRela(RateRuleRelaView rateRuleRelaView, Integer[] orgIdList) {
        if (rateRuleRelaView.getRateRuleName() != null) {
            rateRuleRelaView.setRateRuleName(rateRuleRelaView.getRateRuleName().replaceAll("_", "/_").replaceAll("%", "/%"));
        }
        return rateRuleRelaDao.selectRateRuleRelaView(changeDateFormat(rateRuleRelaView), orgIdList);
    }

    /**
     * 该方法用于解决前台传回来的时间，默认加8小时问题
     * 
     * @param
     * @return
     */
    private RateRuleRelaView changeDateFormat(RateRuleRelaView rateRuleRelaView) {

        try {
            if (rateRuleRelaView.getInureDate() != null) {
                rateRuleRelaView.setInureDate(sdf.parse(sdf.format(rateRuleRelaView.getInureDate())));
            }
            if (rateRuleRelaView.getExpireDate() != null) {
                rateRuleRelaView.setExpireDate(sdf.parse(sdf.format(rateRuleRelaView.getExpireDate())));
            }
        } catch (ParseException e) {
            log.debug(e.getMessage());
        }
        return rateRuleRelaView;
    }

    private RateRuleRela viewToRela(RateRuleRelaView rateRuleRelaView) {
        RateRuleRela rateRuleRela = new RateRuleRela();
        rateRuleRela.setCdrType(rateRuleRelaView.getCdrType());
        rateRuleRela.setObjectType(rateRuleRelaView.getObjectType());
        rateRuleRela.setObjectId(rateRuleRelaView.getObjectId());
        rateRuleRela.setRateItemId(rateRuleRelaView.getRateItemId());
        rateRuleRela.setStatus(rateRuleRelaView.getStatus());
        rateRuleRela.setInureDate(rateRuleRelaView.getInureDate());
        rateRuleRela.setExpireDate(rateRuleRelaView.getExpireDate());
        rateRuleRela.setOperatorId(rateRuleRelaView.getModifyOperator());
        rateRuleRela.setCreateDate(rateRuleRelaView.getCreateDate());
        return rateRuleRela;
    }

    public RateRuleRelaView RelaToview(RateRuleRela rateRuleRela) {
        RateRuleRelaView rateRuleRelaView = new RateRuleRelaView();
        rateRuleRelaView.setCdrType(rateRuleRela.getCdrType());
        rateRuleRelaView.setObjectType(rateRuleRela.getObjectType());
        rateRuleRelaView.setObjectId(rateRuleRela.getObjectId());
        rateRuleRelaView.setRateItemId(rateRuleRela.getRateItemId());
        rateRuleRelaView.setStatus(rateRuleRela.getStatus());
        rateRuleRelaView.setInureDate(rateRuleRela.getInureDate());
        rateRuleRelaView.setExpireDate(rateRuleRela.getExpireDate());
        rateRuleRelaView.setCreateDate(rateRuleRela.getCreateDate());
        return rateRuleRelaView;
    }
}
