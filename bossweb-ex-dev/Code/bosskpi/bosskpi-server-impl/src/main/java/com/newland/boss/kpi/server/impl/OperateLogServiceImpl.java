package com.newland.boss.kpi.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.newland.boss.kpi.dao.OperateLogDao;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.server.OperateLogService;

@Component
public class OperateLogServiceImpl implements OperateLogService {

	@Autowired
	private OperateLogDao operateLogDao;
	
	@Override
	public void addLog(OperateLog operateLog) {
		operateLogDao.insertOperateLog(operateLog);
	}

	@Override
	public List<OperateLog> queryOperLogList(OperateLog operateLog, int page, int size){
		return operateLogDao.queryOperLogList(operateLog,page,size);
	}

	@Override
	public Integer countOperLogNumber(OperateLog operateLog) {
		return operateLogDao.countOperLogNumber(operateLog);
	}
	
}
