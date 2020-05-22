package com.newland.boss.cib.crmp.service;

import java.util.List;
import java.util.Map;

import com.newland.boss.cib.crmp.domain.RateRuleRela;
import com.newland.boss.cib.crmp.domain.RateRuleRelaView;

public interface RateRuleRelaService {

    /**
     * 添加资费规则关系
     * 
     * @param rateRuleRela
     */
    Map<String, String> addRateRuleRela(RateRuleRelaView rateRuleRelaView);

    /**
     * 删除资费规则关系
     * 
     * @param rateRuleRelaJson
     */
    void removeRateRuleRela(String rateRuleRelaJson);

    /**
     * 通过 资费规则ID删除资费规则关系
     * 
     * @param rateItemId
     */
    void removeRateRuleRelaByRateItemId(Integer rateItemId);

    /**
     * 通过对象ID删除资费规则关系
     * 
     * @param objectId
     */
    void removeRateRuleRelaByObjectId(Integer objectId);

    /**
     * 修改资费规则状态
     * 
     * @param rateRuleJson
     */
    void editRateRuleRelaStatus(String rateRuleJson);

    /**
     * 搜索资费规则关系
     * 
     * @param rateRuleRelaView
     * @return
     */
    List<RateRuleRelaView> searchRateRuleRela(RateRuleRelaView rateRuleRelaView, Integer[] orgIdList);

    /**
     * 通过对象ID查询资费规则关系
     * 
     * @param rateRuleRelaView
     * @return
     */
    List<RateRuleRelaView> selectRateRuleRelaByObjectId(RateRuleRelaView rateRuleRelaView);

    /**
     * 导出资费规则关系
     * 
     * @param list
     */
    Map<String, String> exportRateRuleRela(List<RateRuleRelaView> list);

    /**
     * 通过规则ID查找映射关系
     * 
     * @param rateRuleRela
     * @return
     */
    List<RateRuleRela> selectRateRuleRelaByRateItemId(Integer rateItemId);

    RateRuleRelaView RelaToview(RateRuleRela rateRuleRela);
}
