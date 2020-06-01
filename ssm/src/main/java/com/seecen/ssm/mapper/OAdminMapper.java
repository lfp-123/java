package com.seecen.ssm.mapper;

import com.seecen.ssm.pojo.OAdmin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OAdminMapper {

    int deleteByPrimaryKey(Long id);

    int insert(OAdmin record);

    int insertSelective(OAdmin record);

    OAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OAdmin record);

    int updateByPrimaryKey(OAdmin record);

    OAdmin queryAccountPassword(OAdmin admin);
}