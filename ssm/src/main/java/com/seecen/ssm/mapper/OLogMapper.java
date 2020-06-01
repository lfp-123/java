package com.seecen.ssm.mapper;

import com.seecen.ssm.pojo.OLog;

public interface OLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OLog record);

    int insertSelective(OLog record);

    OLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OLog record);

    int updateByPrimaryKey(OLog record);
}