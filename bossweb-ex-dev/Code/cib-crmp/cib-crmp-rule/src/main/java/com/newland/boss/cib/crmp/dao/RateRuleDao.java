package com.newland.boss.cib.crmp.dao;

import java.util.List;

import com.newland.boss.cib.crmp.domain.RateRule;

public interface RateRuleDao {

    void insertRateRule(RateRule rateRule);

    void updateRateRule(RateRule rateRule);

    void deleteRateRule(Integer rateItemId);

    List<RateRule> searchRateRule(RateRule rateRule);

    List<RateRule> selectRuleCross(RateRule rateRule);

    void updateRateRuleStatus(RateRule rateRule);

    List<RateRule> searchSameRateRule(RateRule rateRule);

    List<RateRule> searchRuleToDistribute(RateRule rateRule);
    
    List<RateRule> searchPersonalInsuranceRule(RateRule rateRule);

    List<RateRule> searchInsuranceRule(RateRule rateRule);
}
