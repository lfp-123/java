package com.seecen.ssm.mapper;

import com.seecen.ssm.pojo.ORole;

public interface ORoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ORole record);

    int insertSelective(ORole record);

    ORole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ORole record);

    int updateByPrimaryKey(ORole record);
}