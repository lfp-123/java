package com.newland.boss.cib.crmp.domain;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: DictDef
 * @Package com.newland.boss.cib.crmp.model
 * @Description: 字典表
 * @date 2018/7/26
 */
public class DictDef {
    private Integer dictClass;
    private String dictName;
    private Integer entryId;
    private String entryName;
    private String entryDesc;

    /**
     * Getter for property 'dictClass'.
     *
     * @return Value for property 'dictClass'.
     */
    public Integer getDictClass() {
        return dictClass;
    }

    /**
     * Setter for property 'dictClass'.
     *
     * @param dictClass Value to set for property 'dictClass'.
     */
    public void setDictClass(Integer dictClass) {
        this.dictClass = dictClass;
    }

    /**
     * Getter for property 'dictName'.
     *
     * @return Value for property 'dictName'.
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * Setter for property 'dictName'.
     *
     * @param dictName Value to set for property 'dictName'.
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * Getter for property 'entryId'.
     *
     * @return Value for property 'entryId'.
     */
    public Integer getEntryId() {
        return entryId;
    }

    /**
     * Setter for property 'entryId'.
     *
     * @param entryId Value to set for property 'entryId'.
     */
    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    /**
     * Getter for property 'entryName'.
     *
     * @return Value for property 'entryName'.
     */
    public String getEntryName() {
        return entryName;
    }

    /**
     * Setter for property 'entryName'.
     *
     * @param entryName Value to set for property 'entryName'.
     */
    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    /**
     * Getter for property 'entryDesc'.
     *
     * @return Value for property 'entryDesc'.
     */
    public String getEntryDesc() {
        return entryDesc;
    }

    /**
     * Setter for property 'entryDesc'.
     *
     * @param entryDesc Value to set for property 'entryDesc'.
     */
    public void setEntryDesc(String entryDesc) {
        this.entryDesc = entryDesc;
    }
}
