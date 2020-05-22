package com.newland.boss.cib.crmp.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.cib.crmp.domain.RateRule;
import com.newland.boss.cib.crmp.service.RateRuleService;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.server.OperateLogService;

@RestController
public class RateRuleController {

    @Autowired
    private RateRuleService rateRuleService;

    @Autowired
    private OperateLogService operateLogService;

    private static final Logger log = LogManager.getLogger(RateRuleController.class);

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

    // 搜索资费规则
    @RequestMapping(value = "/selectRateRule", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String selectRateRule(@RequestBody RateRule rateRule) {
        List<RateRule> rateRuleList = rateRuleService.searchRateRule(rateRule);
        return JSONArray.toJSONString(rateRuleList);
    }

    // 搜索可分配的资费规则
    @RequestMapping(value = "/selectRateRuleToDistribute", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String selectRateRuleToDistribute(@RequestBody RateRule rateRule) {
        List<RateRule> rateRuleList = rateRuleService.searchRateRuleToDistribute(rateRule);
        return JSONArray.toJSONString(rateRuleList);
    }

    // 添加资费规则
    @RequestMapping(value = "/addRateRule", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String addRateRule(@RequestBody RateRule rateRule) {
        Map<String, String> map = rateRuleService.addRateRule(rateRule);
        addOperateLog(rateRule.getOperatorId(), 13, 1, "添加资费规则:rateItemId=" + rateRule.getRateItemId());
        return JSONArray.toJSONString(map);
    }

    // 更新规则
    @RequestMapping(value = "/editRateRule", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String editRateRule(@RequestBody RateRule rateRule) {
        Map<String, String> map = rateRuleService.editRateRule(rateRule);
        addOperateLog(rateRule.getOperatorId(), 13, 3, "更新资费规则:rateItemId=" + rateRule.getRateItemId());
        return JSONArray.toJSONString(map);
    }

    // 批量删除规则
    @RequestMapping(value = "/deleteRateRule", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String deleteRateRule(@RequestBody String rateRuleJson) {
        rateRuleService.removeRateRule(rateRuleJson);
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(rateRuleJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        Integer operatorId = jsonObj.get("operatorId").getAsInt();
        addOperateLog(operatorId, 13, 2, "批量删除资费规则" + rateRuleJson);
        return selectRateRule(new RateRule());
    }

    // 批量修改规则状态
    @RequestMapping(value = "/updateRateRuleStatus", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String updateRateRuleStatus(@RequestBody String rateRuleJson) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(rateRuleJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        Integer operatorId = jsonObj.get("operatorId").getAsInt();
        Map<String, String> map = rateRuleService.editRateRuleStatus(rateRuleJson);
        addOperateLog(operatorId, 13, 3, "批量修改资费规则状态" + rateRuleJson);
        return JSONArray.toJSONString(map);
    }

    // 导出资费规则
    @RequestMapping(value = "/exportRateRule", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String exportRateRule(@RequestBody RateRule rateRule) {
        Map<String, String> resultMap = rateRuleService.exportRateRule(rateRuleService.searchRateRule(rateRule));
        addOperateLog(rateRule.getOperatorId(), 13, 5, "导出资费规则");
        return JSONArray.toJSONString(resultMap);
    }

    // 导入资费规则
    @RequestMapping(value = "/importRateRule")
    public Map<String, String> importRateRule(@RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "operatorId", required = true) Integer operatorId) {
        Map<String, String> map = new HashMap<>();
        // 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        if (file.getSize() == 0) {
            map.put("success", "文件为空");
            return map;
        }
        String newFileName = "RateRule" + ".xls";
        // 创建文件
        String dir = System.getProperty("user.dir") + "/upload/";
        File dest = new File(dir, newFileName);
        // 判断文件的目录是否存在，不存在则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        // 上传到指定目录
        try {
            file.transferTo(dest);
            String path = dir + newFileName;
            map = rateRuleService.importRateRule(path, operatorId);
            addOperateLog(operatorId, 13, 6, "导入资费规则");
        } catch (IOException e) {
            log.debug(e.getMessage());
            map.put("info", e.getMessage());
        }
        return map;
    }

}
