package com.newland.boss.cib.crmp.web.service;

import java.util.List;

import com.newland.boss.cib.crmp.domain.OperatorRecord;

public interface OperatorRecordService {

	// 根据条件查询操作日志
	List<OperatorRecord> queryOperRecordList(OperatorRecord operatorRecord, Integer[] orgIdArray, int page, int size);

	Integer countOperRecordNumber(OperatorRecord operatorRecord,Integer[] orgIdArray);
}
