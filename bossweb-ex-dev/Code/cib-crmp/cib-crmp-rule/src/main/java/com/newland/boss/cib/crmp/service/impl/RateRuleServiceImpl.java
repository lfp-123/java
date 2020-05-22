package com.newland.boss.cib.crmp.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.cib.crmp.common.JxlTool;
import com.newland.boss.cib.crmp.dao.RateRuleDao;
import com.newland.boss.cib.crmp.dao.RateRuleRelaDao;
import com.newland.boss.cib.crmp.domain.RateRule;
import com.newland.boss.cib.crmp.domain.RateRuleRela;
import com.newland.boss.cib.crmp.domain.RateRuleRelaView;
import com.newland.boss.cib.crmp.exception.ExcelParseException;
import com.newland.boss.cib.crmp.service.RateRuleRelaService;
import com.newland.boss.cib.crmp.service.RateRuleService;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.server.DictDefService;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

@Component
public class RateRuleServiceImpl implements RateRuleService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat filesdf = new SimpleDateFormat("yyyyMMddHHmmss");
    @Autowired
    private RateRuleDao rateRuleDao;

    @Autowired
    private RateRuleRelaDao rateRuleRelaDao;

    @Autowired
    private RateRuleRelaService rateRuleRelaService;

    @Autowired
    private DictDefService dictDefService;

    private static final Logger log = LogManager.getLogger(RateRuleServiceImpl.class);

    private static final String STATUS = "status";
    private static final String STATUSCODE = "statuscode";
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
    public Map<String, String> addRateRule(RateRule rateRule) {
        Map<String, String> resultMap = new HashMap<>();
        boolean isCross = false;
        if (rateRule.getScope() == 1 && !rateRuleDao.selectRuleCross(rateRule).isEmpty()) {
            isCross = true;
        }
        if (rateRuleDao.searchSameRateRule(rateRule).isEmpty() && !isCross) {
            rateRuleDao.insertRateRule(rateRule);
            resultMap.put(STATUS, SUCCESS);
            resultMap.put(STATUSCODE, "1");
            resultMap.put(INFO, "规则添加成功");
        } else if (isCross) {
            resultMap.put(STATUS, FAIL);
            resultMap.put(STATUSCODE, "2");
            resultMap.put(INFO, "该时段存在同类全局规则，规则添加失败");
        } else {
            resultMap.put(STATUS, FAIL);
            resultMap.put(STATUSCODE, "3");
            resultMap.put(INFO, "存在相同规则，规则添加失败");
        }
        return resultMap;
    }

    @Override
    public Map<String, String> editRateRule(RateRule rateRule) {
        Map<String, String> resultMap = new HashMap<>();
        if (rateRule.getRateDesc() == null)
            rateRule.setRateDesc("");
        if (rateRule.getRateStatus() == 2
                && (!rateRuleRelaService.selectRateRuleRelaByRateItemId(rateRule.getRateItemId()).isEmpty())) {
            resultMap.put(STATUS, FAIL);
            resultMap.put(INFO, "改规则存在分配关系无法禁用，规则更新失败");
        } else if (rateRule.getScope() == 1 && !rateRuleDao.selectRuleCross(rateRule).isEmpty()) {
            resultMap.put(STATUS, FAIL);
            resultMap.put(INFO, "该时段存在同类全局规则，规则更新失败");
        } else {
            List<RateRuleRela> RelaList = rateRuleRelaService.selectRateRuleRelaByRateItemId(rateRule.getRateItemId());
            if (rateRule.getScope() == 0) {
                for (RateRuleRela rela : RelaList) {
                    RateRuleRelaView relaView = rateRuleRelaService.RelaToview(rela);
                    try {
                        relaView.setRateType(rateRule.getRateType());
                        relaView.setInureDate(sdf.parse(rateRule.getInureDate()));
                        relaView.setExpireDate(sdf.parse(rateRule.getExpireDate()));
                        if (!rateRuleRelaDao.selectRuleRelaCross(relaView).isEmpty()) {
                            resultMap.put(STATUS, FAIL);
                            resultMap.put(INFO, "该规则存在分配关系，此次修改将引起时间冲突，修改失败");
                            return resultMap;
                        }
                    } catch (ParseException e) {
                        log.debug(e.getMessage());
                        resultMap.put(STATUS, FAIL);
                        resultMap.put(INFO, "时间转换出错");
                        return resultMap;
                    }
                }
            }
            rateRuleDao.updateRateRule(rateRule);
            try {
                RateRuleRela relaupdate = new RateRuleRela();
                relaupdate.setRateItemId(rateRule.getRateItemId());
                relaupdate.setInureDate(sdf.parse(rateRule.getInureDate()));
                relaupdate.setExpireDate(sdf.parse(rateRule.getExpireDate()));
                rateRuleRelaDao.updateRateRuleRelaDate(relaupdate);
            } catch (ParseException e) {
                log.debug(e.getMessage());
            }
            resultMap.put(STATUS, SUCCESS);
            resultMap.put(INFO, "规则修改成功");
        }
        return resultMap;
    }

    @Override
    public void removeRateRule(String rateRuleJson) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(rateRuleJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        JsonArray rateRuleArray = jsonObj.get("rateItemIdArray").getAsJsonArray(); // 选中的资费规则
        for (int i = 0; i < rateRuleArray.size(); i++) {
            Integer rateItemId = rateRuleArray.get(i).getAsInt();
            rateRuleDao.deleteRateRule(rateItemId);
            rateRuleRelaService.removeRateRuleRelaByRateItemId(rateItemId);
        }

    }

    @Override
    public List<RateRule> searchRateRule(RateRule rateRule) {
        if (rateRule.getRateRuleName() != null) {
            rateRule.setRateRuleName(rateRule.getRateRuleName().replaceAll("_", "/_").replaceAll("%", "/%"));
        }
        return rateRuleDao.searchRateRule(rateRule);
    }

    @Override
    public Map<String, String> editRateRuleStatus(String rateRuleJson) {
        Map<String, String> resultMap = new HashMap<>();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(rateRuleJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        JsonArray rateArray = jsonObj.get("rateItemIdArray").getAsJsonArray(); // 选中的rate
        Integer rateStatus = jsonObj.get("rateStatus").getAsInt();// 要改变的规则状态
        RateRule rateRule = new RateRule();
        rateRule.setRateStatus(rateStatus);
        List<Integer> rateItemIdList = new ArrayList<>();
        // 循环更新规则状态
        for (int i = 0; i < rateArray.size(); i++) {
            Integer rateItemId = rateArray.get(i).getAsInt();
            rateRule.setRateItemId(rateItemId);
            if (rateRule.getRateStatus() == 2
                    && (!rateRuleRelaService.selectRateRuleRelaByRateItemId(rateItemId).isEmpty())) {
                rateItemIdList.add(rateItemId);
                continue;
            }
            rateRuleDao.updateRateRuleStatus(rateRule);
        }
        if (rateItemIdList.size() > 0) {
            String strID = "";
            for (int j = 0; j < rateItemIdList.size(); j++) {
                strID = strID + rateItemIdList.get(j);
                if (j < rateItemIdList.size() - 1) {
                    strID = strID + "、";
                }
            }
            resultMap.put(STATUS, FAIL);
            resultMap.put(INFO, "资费规则ID为：" + strID + "的规则存在分配关系无法禁用,请先解除分配关系。");
        } else {
            resultMap.put(STATUS, SUCCESS);
        }
        return resultMap;
    }

    @Override
    public Map<String, String> exportRateRule(List<RateRule> list) {
        Map<String, String> resultMap = new HashMap<>();
        Map<Integer, String> statusMap = getDicMap(1004);
        Map<Integer, String> cdrTypeMap = getDicMap(1001);
        Map<Integer, String> rateTypeMap = getDicMap(1006);
        Map<Integer, String> scopeTypeMap = getDicMap(1019);
        String title = "RateRule" + filesdf.format(new Date()) + ".xls";
        String path = System.getProperty("user.dir") + "/downloadTemp/" + title;
        String[] columns = { "规则ID", "规则名称", "资源类型", "资费类型", "范围", "资费(元)", "其他费用(元)",
                /* "开始时间(月-日:时)", "结束时间(月-日:时)", (注释备用) */"启用状态", "生效时间", "失效时间", "资费说明", "操作员ID", "创建时间" };
        boolean[] numberFlag = { false, false, false, false, false, false, false, false, false, false, false, false,
                false };
        List<String[]> dataList = new ArrayList<>();
        for (RateRule r : list) {
            if (r.getRateDesc() == null)
                r.setRateDesc("");
            if (r.getOtherFee() == null)
                r.setOtherFee(0L);
            String[] date = { String.valueOf(r.getRateItemId()), r.getRateRuleName(), cdrTypeMap.get(r.getCdrType()),
                    rateTypeMap.get(r.getRateType()), scopeTypeMap.get(r.getScope()),
                    String.valueOf(r.getFee() / 1000d), String.valueOf(r.getOtherFee() / 1000d),
                    /* String.valueOf(r.getStartTime()), String.valueOf(r.getEndTime()),(注释备用) */ statusMap
                            .get(r.getRateStatus()),
                    r.getInureDate(), r.getExpireDate(), String.valueOf(r.getRateDesc()),
                    String.valueOf(r.getOperatorId()), r.getCreateTime() };
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
    public Map<String, String> importRateRule(String path, Integer operatorId) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put(SUCCESS, "规则导入全部失败");
        int addnum = 0;
        try {
            List<String[]> dataList = JxlTool.importExcel(path, 0);
            File file = new File(path);
            if (file.exists() && file.isFile() && !file.delete()) {
                log.debug("删除资费规则导入文件失败。");
            }
            for (int j = 1; j < dataList.size(); j++) {

                String[] s = dataList.get(j);
                if ("".equals(s[0]) && "".equals(s[1]) && "".equals(s[2]) && "".equals(s[3]) && "".equals(s[6])
                        && "".equals(s[7]) && "".equals(s[8])) {
                    break;
                }
                if ("".equals(s[0]) || "".equals(s[1]) || "".equals(s[2]) || "".equals(s[3]) || "".equals(s[6])
                        || "".equals(s[7]) || "".equals(s[8])) {
                    throw new ExcelParseException("该行有必填数据为空，请检查！");

                }
                RateRule rateRule = dealDataFormat(dataList.get(j), operatorId);

                Map<String, String> addMap = addRateRule(rateRule);
                if ("2".equals(addMap.get(STATUSCODE))) {
                    throw new ExcelParseException("该时段存在同类全局规则，请检查！");
                }
                addnum++;
            }
            resultMap.put(SUCCESS, "导入资费规则成功,成功导入" + addnum + "条规则");
        } catch (ExcelParseException | BiffException | IOException e) {
            log.debug("Excel文件格式转换异常" + e.getMessage());
            resultMap.put(SUCCESS, "导入资费规则执行至第" + (addnum + 2) + "行停止," + e.getMessage());
        }
        return resultMap;
    }

    private RateRule dealDataFormat(String[] data, Integer operatorId) {
        Map<Integer, String> statusMap = getDicMap(1004);
        Map<Integer, String> cdrTypeMap = getDicMap(1001);
        Map<Integer, String> rateTypeMap = getDicMap(1006);
        Map<Integer, String> scopeTypeMap = getDicMap(1019);
        String[] s = data;
        RateRule rateRule = new RateRule();
        if (length(s[0]) > 32) {
            throw new ExcelParseException("规则名称太长，请检查！");
        } else {
            rateRule.setRateRuleName(s[0]);
        }
        if (getKey(cdrTypeMap, s[1]) == null) {
            throw new ExcelParseException("资源类型信息不匹配，请检查！");
        } else {
            rateRule.setCdrType(getKey(cdrTypeMap, s[1]));
        }
        if (getKey(rateTypeMap, s[2]) == null) {
            throw new ExcelParseException("资费类型信息不匹配，请检查！");
        } else {
            rateRule.setRateType(getKey(rateTypeMap, s[2]));
        }
        if (!isRelativeType(rateRule.getCdrType(), rateRule.getRateType())) {
            throw new ExcelParseException("资源类型与资费类型不匹配，请检查！");
        } else {
            rateRule.setRateType(getKey(rateTypeMap, s[2]));
        }
        if (getKey(scopeTypeMap, s[3]) == null) {
            throw new ExcelParseException("规则范围信息不匹配，请检查！");
        } else {
            rateRule.setScope(getKey(scopeTypeMap, s[3]));
            if (rateRule.getRateType() == 10) {
                rateRule.setScope(1);
            }
        }
        if ("".equals(s[4])) {
            s[4] = "0";
        }
        if ("".equals(s[5])) {
            s[5] = "0";
        }
        if (isNumber(s[4]) == null) {
            throw new ExcelParseException("资费格式有误，请检查！");
        } else {
            rateRule.setFee(Math.round(isNumber(s[4]) * 1000));
        }
        if (isNumber(s[5]) == null) {
            throw new ExcelParseException("其他费用格式有误，请检查！");
        } else {
            rateRule.setOtherFee(Math.round(isNumber(s[5]) * 1000));
        }
        if (getKey(statusMap, s[6]) == null) {
            throw new ExcelParseException("规则状态信息不匹配，请检查！");
        } else {
            rateRule.setRateStatus(getKey(statusMap, s[6]));
        }
        if (realDate(s[7]) == null || realDate(s[8]) == null || isDateError(s[7], s[8])) {
            throw new ExcelParseException("生效时间或失效时间错误，请检查！");
        } else {
            rateRule.setInureDate(realDate(s[7]));
            rateRule.setExpireDate(realDate(s[8]));
        }
        if (length(s[9]) > 512) {
            throw new ExcelParseException("备注太长，请检查！");
        } else {
            rateRule.setRateDesc(s[9]);
        }
        if (rateRule.getRateType() == 3 || rateRule.getRateType() == 4 || rateRule.getRateType() == 5
                || rateRule.getRateType() == 6 || rateRule.getRateType() == 8 || rateRule.getRateType() == 9
                || rateRule.getRateType() == 10) {
            rateRule.setFee(0L);
        }
        if (rateRule.getRateType() == 1 || rateRule.getRateType() == 2 || rateRule.getRateType() == 7) {
            rateRule.setOtherFee(0L);
        }
        rateRule.setOperatorId(operatorId);
        rateRule.setCreateTime(sdf.format(new Date()));
        setPriority(rateRule);
        return rateRule;
    }

    /**
     * 通过Key值获取value
     * 
     * @param map
     * @param value
     * @return
     */
    private Integer getKey(Map<Integer, String> map, String value) {
        Integer key = null;
        Set<?> set = map.entrySet();
        Iterator<?> it = set.iterator();
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue().equals(value)) {
                key = Integer.valueOf(entry.getKey().toString());
            }
        }
        return key;
    }

    /**
     * 计算规则优先级
     * 
     * @param rateRule
     */
    private void setPriority(RateRule rateRule) {
        if (rateRule.getRateType() == 8) {
            rateRule.setPriority(-1);
        } else if (rateRule.getRateType() == 10) {
            rateRule.setPriority(-1);
        } else {
                if (rateRule.getRateType() == 3) {
                    rateRule.setPriority(3);
                } else if (rateRule.getRateType() == 4) {
                    rateRule.setPriority(4);
                } else if (rateRule.getRateType() == 5) {
                    rateRule.setPriority(5);
                } else if (rateRule.getRateType() == 6) {
                    rateRule.setPriority(6);
                } else {
                    rateRule.setPriority(2);
                }
        }
    }

    /**
     * 判断规则中的资费类型与收费类型是否是对应的
     * 
     * @param cdrType  资费类型
     * @param rateType 收费类型
     * @return
     */
    Boolean isRelativeType(Integer cdrType, Integer rateType) {
        return ((cdrType == 1 && (rateType == 1 || rateType == 3 || rateType == 6 || rateType == 8 || rateType == 10))
                || (cdrType == 2 && rateType == 1) || (cdrType == 3 && (rateType == 2 || rateType == 9))
                || (cdrType == 4 && (rateType == 3 || rateType == 6)));
    }

    /**
     * 导入时间格式转化
     * 
     * @param sDate
     * @return
     */
    private String realDate(String sDate) {
        try {
            sdf.parse(sDate);
        } catch (ParseException e) {
            return null;
        }
        return sDate;
    }

    private Boolean isDateError(String iInureDate, String iExpireDate) {
        Boolean correct = false;
        try {
            Date inureDate = sdf.parse(iInureDate);
            Date expireDate = sdf.parse(iExpireDate);
            if (inureDate.after(expireDate) || expireDate.after(sdf.parse("3000-01-01"))
                    || inureDate.before(sdf.parse("1900-01-01"))) {
                correct = true;
            }
        } catch (ParseException e) {
            return true;
        }
        return correct;
    }

    /*
     * private String checkTimeFormate(String stime) { String time=null; String
     * regex="(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]):([0-1][0-9]|2[0-3])";//
     * 正则表达式 Pattern p=Pattern.compile(regex); Matcher m=p.matcher(stime);
     * if(m.matches()){ time=stime; } return time; }
     */
    /**
     * 计算字符串字符长度
     * 
     * @param str
     * @return
     */
    private int length(String str) {
        int strLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < str.length(); i++) {
            /* 获取一个字符 */
            String temp = str.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                strLength += 2;
            } else {
                /* 其他字符长度为1 */
                strLength += 1;
            }
        }
        return strLength;
    }

    /**
     * 判断是否为数字类型，是则返回double数据，否返回空
     * 
     * @param sNumber
     * @return
     */
    private Double isNumber(String sNumber) {
        if (NumberUtils.isNumber(sNumber))
            return Double.valueOf(sNumber);
        else
            return null;
    }

    @Override
    public List<RateRule> searchRateRuleToDistribute(RateRule rateRule) {
        if (rateRule.getRateRuleName() != null) {
            rateRule.setRateRuleName(rateRule.getRateRuleName().replaceAll("_", "/_").replaceAll("%", "/%"));
        }
        return rateRuleDao.searchRuleToDistribute(rateRule);
    }

}
