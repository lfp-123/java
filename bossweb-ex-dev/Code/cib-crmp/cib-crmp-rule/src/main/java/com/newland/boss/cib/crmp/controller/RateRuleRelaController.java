package com.newland.boss.cib.crmp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.cib.crmp.domain.RateRuleRela;
import com.newland.boss.cib.crmp.domain.RateRuleRelaView;
import com.newland.boss.cib.crmp.service.RateRuleRelaService;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.server.OperateLogService;

@RestController
public class RateRuleRelaController {

    @Autowired
    private RateRuleRelaService rateRuleRelaService;

    @Autowired
    private OperateLogService operateLogService;
    private static final String RESULT = "[{\"status\":\"success\"}]";

    // 操作日志
    private void addOperateLog(Integer operateId, Integer operateModule, Integer operateType, String logInfo) {
        // 添加操作日志到记录
        OperateLog operateLog = new OperateLog();
        operateLog.setOperatorId(operateId);
        operateLog.setOperateModule(operateModule);
        operateLog.setOperateType(operateType);
        operateLog.setOperateDesc(logInfo);
        operateLogService.addLog(operateLog);
    }

    // 查找对象资费规则分配信息
    @RequestMapping(value = "/getRelaByobjectId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String showRateRuleRelaByObjectId(@RequestBody RateRuleRelaView rateRuleRelaView) {
        List<RateRuleRelaView> rateRuleRelaViewList = rateRuleRelaService
                .selectRateRuleRelaByObjectId(rateRuleRelaView);
        return JSONArray.toJSONString(rateRuleRelaViewList);
    }

    // 查询规则分配信息
    @RequestMapping(value = "/selectRateRuleRela", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String selectRateRuleRela(@RequestBody String serachRelaJson) throws ParseException {
        RateRuleRelaView rateRuleRelaView = stringToRateRuleRelaView(serachRelaJson);
        Integer[] orgIdList = stringToOrgIdlist(serachRelaJson);
        List<RateRuleRelaView> rateRuleRelaViewList = rateRuleRelaService.searchRateRuleRela(rateRuleRelaView,
                orgIdList);
        return JSONArray.toJSONString(rateRuleRelaViewList);
    }

    // 批量删除规则分配
    @RequestMapping(value = "/deleteRateRuleRela", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String deleteRateRuleRela(@RequestBody String rateRuleRelaJson) {
        rateRuleRelaService.removeRateRuleRela(rateRuleRelaJson);
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(rateRuleRelaJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        Integer operatorId = jsonObj.get("operatorId").getAsInt();
        addOperateLog(operatorId, 14, 2, "批量删除已分配规则信息" + rateRuleRelaJson);
        return RESULT;
    }

    // 批量增加规则分配
    @RequestMapping(value = "/addRateRuleRela", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String addRateRuleRela(@RequestBody String rateRuleRelaJson) throws ParseException {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(rateRuleRelaJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        Integer operatorId = jsonObj.get("operatorId").getAsInt();
        JsonArray relaListArray = jsonObj.get("relaLists").getAsJsonArray();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String info = "";
        for (int i = 0; i < relaListArray.size(); i++) {
            JsonObject jsonRela = relaListArray.get(i).getAsJsonObject();
            RateRuleRelaView rateRuleRelaView = new RateRuleRelaView();
            rateRuleRelaView.setObjectType(jsonRela.get("objectType").getAsInt());
            rateRuleRelaView.setObjectId(jsonRela.get("objectId").getAsInt());
            rateRuleRelaView.setCdrType(jsonRela.get("cdrType").getAsInt());
            rateRuleRelaView.setRateType(jsonRela.get("rateType").getAsInt());
            rateRuleRelaView.setPriority(jsonRela.get("priority").getAsInt());
            rateRuleRelaView.setRateItemId(jsonRela.get("rateItemId").getAsInt());
            Date inureDate = new Date(Long.parseLong(jsonRela.get("inureDate").toString().replace("\"", "")));
            Date expireDate = new Date(Long.parseLong(jsonRela.get("expireDate").toString().replace("\"", "")));
            Date createDate = sdf.parse(jsonRela.get("createDate").toString().replace("\"", ""));
            rateRuleRelaView.setInureDate(inureDate);
            rateRuleRelaView.setExpireDate(expireDate);
            rateRuleRelaView.setCreateDate(createDate);
            rateRuleRelaView.setStatus(jsonRela.get("status").getAsInt());
            rateRuleRelaView.setModifyOperator(jsonRela.get("modifyOperator").getAsInt());
            Map<String, String> addResultMap = rateRuleRelaService.addRateRuleRela(rateRuleRelaView);
            info = info + "规则：" + addResultMap.get("ID") + "," + addResultMap.get("status") + "<br>";
            addOperateLog(operatorId, 14, 1, "新增资费规则分配信息:objectId=" + rateRuleRelaView.getObjectId() + ";rateItemId="
                    + rateRuleRelaView.getRateItemId());
        }
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("status", info);
        return JSONArray.toJSONString(resultMap);
    }

    // 批量修改规则状态
    @RequestMapping(value = "/updateRateRuleRelaStatus", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String updateRateRuleRelaStatus(@RequestBody String rateRuleRelaJson) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(rateRuleRelaJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        Integer operatorId = jsonObj.get("operatorId").getAsInt();
        rateRuleRelaService.editRateRuleRelaStatus(rateRuleRelaJson);
        addOperateLog(operatorId, 14, 3, "修改资费规则分配信息状态：" + rateRuleRelaJson);
        return RESULT;
    }

    // 导出资费规则分配情况
    @RequestMapping(value = "/exportRateRuleRela", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String exportRateRuleRela(@RequestBody String serachRelaJson) throws ParseException {
        RateRuleRelaView rateRuleRelaView = stringToRateRuleRelaView(serachRelaJson);
        Integer[] orgIdList = stringToOrgIdlist(serachRelaJson);
        Map<String, String> resultMap = rateRuleRelaService
                .exportRateRuleRela(rateRuleRelaService.searchRateRuleRela(rateRuleRelaView, orgIdList));
        addOperateLog(rateRuleRelaView.getModifyOperator(), 14, 5, "导出资费规则分配信息:" + serachRelaJson);
        return JSONArray.toJSONString(resultMap);
    }

    // 查询分配指定规则的机构
    @RequestMapping(value = "/loadRateRuleRelaByRuleItemId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String loadRateRuleRelaByRuleItemId(@RequestBody RateRuleRela rateRuleRela) {
        return JSONArray.toJSONString(rateRuleRelaService.selectRateRuleRelaByRateItemId(rateRuleRela.getRateItemId()));
    }

    // 为单个规则批量增加资费规则与机构的关系
    @RequestMapping(value = "/addRateRuleRelaByRuleId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String addRateRuleRelaByRuleId(@RequestBody List<RateRuleRelaView> rateRuleRelaViewList) {
        Integer operatorId = rateRuleRelaViewList.get(0).getModifyOperator();
        rateRuleRelaService.removeRateRuleRelaByRateItemId(rateRuleRelaViewList.get(0).getRateItemId());
        for (RateRuleRelaView rateRuleRelaView : rateRuleRelaViewList) {
            if (rateRuleRelaView.getObjectId() == null) {
                continue;
            }
            rateRuleRelaService.addRateRuleRela(rateRuleRelaView);
            addOperateLog(operatorId, 14, 1, "新增资费规则分配信息:objectId=" + rateRuleRelaView.getObjectId() + ";rateItemId="
                    + rateRuleRelaView.getRateItemId());
        }
        return RESULT;
    }

    // 解析json为RateRuleRelaView对象
    private RateRuleRelaView stringToRateRuleRelaView(String Json) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        RateRuleRelaView rateRuleRelaView = new RateRuleRelaView();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(Json);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        if (jsonObj.get("operatorName") != null && !"\"\"".equals(jsonObj.get("operatorName").toString())
                && !jsonObj.get("operatorName").isJsonNull())
            rateRuleRelaView.setOperatorName(jsonObj.get("operatorName").toString().replaceAll("\"", ""));
        if (jsonObj.get("rateRuleName") != null && !"\"\"".equals(jsonObj.get("rateRuleName").toString())
                && !jsonObj.get("rateRuleName").isJsonNull())
            rateRuleRelaView.setRateRuleName(jsonObj.get("rateRuleName").toString().replaceAll("\"", ""));
        if (jsonObj.get("status") != null && !"\"\"".equals(jsonObj.get("status").toString())
                && !jsonObj.get("status").isJsonNull())
            rateRuleRelaView.setStatus(jsonObj.get("status").getAsInt());
        if (jsonObj.get("inureDate") != null && !"\"\"".equals(jsonObj.get("inureDate").toString())
                && !jsonObj.get("inureDate").isJsonNull())
            rateRuleRelaView.setInureDate(sdf.parse(jsonObj.get("inureDate").toString().replaceAll("\"", "")));
        if (jsonObj.get("expireDate") != null && !"\"\"".equals(jsonObj.get("expireDate").toString())
                && !jsonObj.get("expireDate").isJsonNull())
            rateRuleRelaView.setExpireDate(sdf.parse(jsonObj.get("expireDate").toString().replaceAll("\"", "")));
        if (jsonObj.get("modifyOperator") != null && !"\"\"".equals(jsonObj.get("modifyOperator").toString())
                && !jsonObj.get("modifyOperator").isJsonNull())
            rateRuleRelaView.setModifyOperator(jsonObj.get("modifyOperator").getAsInt());
        return rateRuleRelaView;
    }

    // 解析json中的机构ID数组
    private Integer[] stringToOrgIdlist(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(json);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        if (jsonObj.get("orgIdList") != null && !"\"\"".equals(jsonObj.get("orgIdList").toString())
                && !jsonObj.get("orgIdList").isJsonNull()) {
            JsonArray orgIdArray = jsonObj.get("orgIdList").getAsJsonArray(); // 选中的资费规则分配
            Integer[] orgIdList = new Integer[orgIdArray.size()];
            for (int i = 0; i < orgIdArray.size(); i++) {
                Integer orgId = orgIdArray.get(i).getAsInt();
                orgIdList[i] = orgId;
            }
            return orgIdList;
        }
        return null;
    }
}
