package com.newland.boss.cib.crmp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newland.boss.cib.crmp.domain.RateRuleRela;
import com.newland.boss.cib.crmp.domain.RateRuleRelaView;

public interface RateRuleRelaDao {
    List<RateRuleRelaView> selectRateRuleRelaView(@Param(value = "rateRuleRelaView") RateRuleRelaView rateRuleRelaView,
            @Param(value = "orgIdList") Integer[] orgIdList);

    void insertRateRuleRela(RateRuleRela rateRuleRela);

    void deleteRateRuleRela(Integer rateRuleRelaId);

    void deleteRateRuleRelaByRateItemId(Integer rateItemId);

    void deleteRateRuleRelaByObjectId(Integer objectId);

    List<RateRuleRelaView> selectRateRuleRelaByObjectId(RateRuleRelaView rateRuleRelaView);

    RateRuleRela selectRateRuleRela(RateRuleRela rateRuleRela);

    void updateRateRuleRelaStatus(RateRuleRela rateRuleRela);

    List<RateRuleRela> selectRateRuleRelaByRateItemId(Integer rateItemId);

    List<RateRuleRela> selectRuleRelaCross(RateRuleRelaView rateRuleRelaView);

    List<RateRuleRela> selectRuleRelaHeight(RateRuleRelaView rateRuleRelaView);

    List<RateRuleRela> selectRuleRelalow(RateRuleRelaView rateRuleRelaView);

    void updateRateRuleRelaToLose(RateRuleRela rateRuleRela);

    void updateRateRuleRelaDate(RateRuleRela rateRuleRela);
}
