package com.newland.boss.cib.crmp.code.entity;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: BBFDICT
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/7 17:01
 */
public class Dict {
    private Integer DictId;
    private String  DictName;
    private Integer GroupId;
    private List<AppConfig> configs;

    public List<AppConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<AppConfig> configs) {
        this.configs = configs;
    }

    public Dict() {
        super();
    }

    public Dict(Integer dictId, String dictName, Integer groupId,List<AppConfig> config) {
        configs =config;
        DictId = dictId;
        DictName = dictName;
        GroupId = groupId;
    }

    public Integer getDictId() {
        return DictId;
    }

    public void setDictId(Integer dictId) {
        DictId = dictId;
    }

    public String getDictName() {
        return DictName;
    }

    public void setDictName(String dictName) {
        DictName = dictName;
    }

    public Integer getGroupId() {
        return GroupId;
    }

    public void setGroupId(Integer groupId) {
        GroupId = groupId;
    }
}
