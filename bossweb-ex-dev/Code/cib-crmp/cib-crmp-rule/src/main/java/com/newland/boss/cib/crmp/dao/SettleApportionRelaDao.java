package com.newland.boss.cib.crmp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newland.boss.cib.crmp.domain.RuleRela;
import com.newland.boss.cib.crmp.domain.SettleApportionRela;
import com.newland.boss.cib.crmp.domain.SettleApportionRule;

/**
 * @author ylc
 *
 * 2018-05-11
 */
public interface SettleApportionRelaDao {

	void insertSettleApportionRela(SettleApportionRela settleApportionRela);

	void deleteSettleApportionRela(Integer settleApportionRelaId);
	
	void deleteSettleApportionRelaByObjectId(Integer objectId);
	
	void deleteSettleApportionRelaByRuleId(Integer apportionItemId);

	SettleApportionRela querySettleApportionRelaIsExistence(Integer apportionItemId,Integer objectId);
	
	Integer queryRelaByRuleId(Integer apportionItemId);
	
	void updateSettleApportionRela(SettleApportionRela settleApportionRela);
	
	void updateSettleApportionRelaTime(SettleApportionRule settleApportionRule);

	List<SettleApportionRela> queryAllSettleApportionRela();
	
	List<SettleApportionRela> querySettleApportionRelaByOrgId(Integer objectId);
	
	List<SettleApportionRela> querySettleApportionRelaByRuleId(Integer apportionItemId);
	
	List<RuleRela> combinQueryRuleRela(@Param(value="ruleRela") RuleRela ruleRela,@Param(value="orgIdList")  List<Integer> orgIdList);

	List<SettleApportionRela> querySettleApportionRelaByParam(SettleApportionRela settleApportionRela);
}
