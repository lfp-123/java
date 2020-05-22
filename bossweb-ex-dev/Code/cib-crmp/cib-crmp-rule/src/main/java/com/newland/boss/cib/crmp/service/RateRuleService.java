package com.newland.boss.cib.crmp.service;

import java.util.List;
import java.util.Map;

import com.newland.boss.cib.crmp.domain.RateRule;

public interface RateRuleService {

    /**
     * 添加资费规则
     * 
     * @param rateRule
     * @return
     */
    Map<String, String> addRateRule(RateRule rateRule);

    /**
     * 删除资费规则
     * 
     * @param rateRuleJson
     */
    void removeRateRule(String rateRuleJson);

    /**
     * 修改资费规则状态
     * 
     * @param rateRuleJson
     * @return
     */
    Map<String, String> editRateRuleStatus(String rateRuleJson);

    /**
     * 修改资费规则
     * 
     * @param rateRule
     * @return
     */
    Map<String, String> editRateRule(RateRule rateRule);

    /**
     * 搜索资费规则
     * 
     * @param rateRule
     * @return
     */
    List<RateRule> searchRateRule(RateRule rateRule);

    /**
     * 搜索可分配的资费规则
     * 
     * @param rateRule
     * @return
     */
    List<RateRule> searchRateRuleToDistribute(RateRule rateRule);

    /**
     * 导出资费规则
     * 
     * @param list
     */
    Map<String, String> exportRateRule(List<RateRule> list);

    /**
     * 导入资费规则
     * 
     * @param path
     * @param operatorId
     * @return
     */
    Map<String, String> importRateRule(String path, Integer operatorId);
}
