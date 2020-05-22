package com.newland.boss.kpi.server;

import java.util.List;

import com.newland.boss.kpi.entity.OperateLog;

public interface OperateLogService {
	void addLog(OperateLog operateLog);

	// 查询日志
	List<OperateLog> queryOperLogList(OperateLog operateLog, int page, int size);

	Integer countOperLogNumber(OperateLog operateLog);
}
