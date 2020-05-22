package com.newland.boss.cib.crmp.domain;

/**
 * 描述:
 * 话单规则表
 *
 * @author weixc
 * create 2018-09-17 16:16
 */
public class PhoneNumberRule {

    private String callType;
    private String phoneNumberType;
    private String regexValue;
    private int ruleGroup;

    public int getRuleGroup() {
        return ruleGroup;
    }

    public void setRuleGroup(int ruleGroup) {
        this.ruleGroup = ruleGroup;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getPhoneNumberType() {
        return phoneNumberType;
    }

    public void setPhoneNumberType(String phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }

    public String getRegexValue() {
        return regexValue;
    }

    public void setRegexValue(String regexValue) {
        this.regexValue = regexValue;
    }

}
