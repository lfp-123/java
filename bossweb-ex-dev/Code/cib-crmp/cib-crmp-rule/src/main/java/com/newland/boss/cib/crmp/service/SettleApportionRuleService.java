/**
 * 
 */
package com.newland.boss.cib.crmp.service;

import java.util.List;
import java.util.Map;

import com.newland.boss.cib.crmp.domain.SettleApportionRule;

/**
 * @author ylc
 *
 * 2018-05-11
 */
public interface SettleApportionRuleService {
	
	Boolean addSettleApportionRule(SettleApportionRule settleApportionRule);

	void removeSettleApportionRule(String ruleJson);

	Map<String, String> modifySettleApportionRuleStatus(String ruleJson);

	Map<String, String> editSettleApportionRule(SettleApportionRule settleApportionRule);

	List<SettleApportionRule> loadAllSettleApportionRule();

	List<SettleApportionRule> loadSettleApportionRuleByParam(SettleApportionRule settleApportionRule);
	
	SettleApportionRule getSettleApportionRuleById(Integer settleApportionRuleId);

	Map<String, String> exportSettleApportionRule(SettleApportionRule settleApportionRule);
	
	/**
	 * 
	 * @param tempPath 文件的缓存路径
	 * @return
	 */
	Map<String, String> importSettleApportionRule(String tempPath,Integer operatorId);
}
