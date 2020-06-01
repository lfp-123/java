package com.seecen.ssm.mapper;

import com.seecen.ssm.pojo.OFeedback;

public interface OFeedbackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OFeedback record);

    int insertSelective(OFeedback record);

    OFeedback selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OFeedback record);

    int updateByPrimaryKey(OFeedback record);
}