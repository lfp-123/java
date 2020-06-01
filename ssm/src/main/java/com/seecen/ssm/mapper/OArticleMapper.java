package com.seecen.ssm.mapper;

import com.seecen.ssm.pojo.OArticle;

public interface OArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OArticle record);

    int insertSelective(OArticle record);

    OArticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OArticle record);

    int updateByPrimaryKeyWithBLOBs(OArticle record);

    int updateByPrimaryKey(OArticle record);
}