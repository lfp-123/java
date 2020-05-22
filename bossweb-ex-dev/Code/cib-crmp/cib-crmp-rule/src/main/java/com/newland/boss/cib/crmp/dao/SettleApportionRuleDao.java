/**
 * 
 */
package com.newland.boss.cib.crmp.dao;

import java.util.List;

import com.newland.boss.cib.crmp.domain.SettleApportionRule;


/**
 * @author ylc
 *
 * 2018-05-11
 */
public interface SettleApportionRuleDao {

	void insertSettleApportionRule(SettleApportionRule settleApportionRule);

	void deleteSettleApportionRule(Integer settleApportionRuleId);

	void updateSettleApportionRule(SettleApportionRule settleApportionRule);

	List<SettleApportionRule> queryAllSettleApportionRule();

	List<SettleApportionRule> querySettleApportionRuleByParam(SettleApportionRule settleApportionRule);
	
	SettleApportionRule getSettleApportionRuleById(Integer settleApportionRuleId);
	
	/**
	 * 判断规则的生失效时间是否合法
	 * @param settleApportionRule
	 * @return
	 */
	List<SettleApportionRule> judgeRuleDateValidity(SettleApportionRule settleApportionRule);

}
