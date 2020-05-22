/**
 * 
 */
package com.newland.boss.cib.crmp.service;

import java.util.List;
import java.util.Map;

import com.newland.boss.cib.crmp.domain.RuleRela;
import com.newland.boss.cib.crmp.domain.SettleApportionRela;


/**
 * @author ylc
 *
 * 2018-05-11
 */
public interface SettleApportionRelaService {
	
	void addSettleApportionRela(String relaJson);
	
	void addSettleApportionRelaByRuleId(List<SettleApportionRela> settleApportionRelaLists);

	void removeSettleApportionRela(String relaJson);
	
	void modifySettleApportionRelaStatus(String relaJson);

	void editSettleApportionRela(SettleApportionRela settleApportionRela);

	List<SettleApportionRela> loadAllSettleApportionRela();
	
	List<SettleApportionRela> loadSettleApportionRelaByOrgId(Integer objectId);
	
	List<SettleApportionRela> loadSettleApportionRelaByRuleId(Integer apportionItemId);

	List<SettleApportionRela> loadSettleApportionRelaByParam(SettleApportionRela settleApportionRela);
	
	List<RuleRela> loadComQueryRelaByParam(RuleRela ruleRela);
	
	List<RuleRela> loadComQueryRelaByOrgParam(String relaJson);
	
	Map<String, String> exportSettleApportionRela(String relaJson);
}
