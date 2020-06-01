package com.seecen.ssm.mapper;

import com.seecen.ssm.pojo.OCategory;

public interface OCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OCategory record);

    int insertSelective(OCategory record);

    OCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OCategory record);

    int updateByPrimaryKey(OCategory record);
}