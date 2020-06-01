package com.seecen.ssm.mapper;

import com.seecen.ssm.pojo.OMenu;

public interface OMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OMenu record);

    int insertSelective(OMenu record);

    OMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OMenu record);

    int updateByPrimaryKey(OMenu record);
}