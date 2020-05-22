package com.newland.boss.kpi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.newland.boss.kpi.entity.OperateLog;

@Repository
public interface OperateLogDao {

	void insertOperateLog(OperateLog operateLog);

	// 查询日志
	List<OperateLog> queryOperLogList(@Param(value = "req") OperateLog req, @Param(value = "page") int page,
			@Param(value = "size") int size);

	Integer countOperLogNumber(OperateLog operateLog);
}
