package com.newland.boss.cib.crmp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newland.boss.cib.crmp.domain.OperatorRecord;
import com.newland.boss.cib.crmp.web.dao.OperatorRecordDao;
import com.newland.boss.cib.crmp.web.service.OperatorRecordService;

@Component
public class OperatorRecordServiceImpl implements OperatorRecordService {

	@Autowired
	private OperatorRecordDao operatorRecordDao;

	@Override
	public List<OperatorRecord> queryOperRecordList(OperatorRecord operatorRecord, Integer[] orgIdArray, int page,
			int size) {
		return operatorRecordDao.queryOperRecordList(operatorRecord, orgIdArray, page, size);
	}

	@Override
	public Integer countOperRecordNumber(OperatorRecord req, Integer[] orgIdArray) {
		return operatorRecordDao.countOperRecordNumber(req, orgIdArray);
	}

}
