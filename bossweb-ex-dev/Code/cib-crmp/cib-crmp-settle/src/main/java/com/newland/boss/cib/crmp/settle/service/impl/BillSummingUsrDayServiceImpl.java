package com.newland.boss.cib.crmp.settle.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newland.boss.cib.crmp.domain.BillSummingUsrDay;
import com.newland.boss.cib.crmp.settle.dao.BillSummingUsrDayDao;
import com.newland.boss.cib.crmp.settle.service.BillSummingUsrDayService;

@Component
public class BillSummingUsrDayServiceImpl implements BillSummingUsrDayService {
	
	@Autowired
	private BillSummingUsrDayDao billSummingUsrDayDao;
	
	public List<BillSummingUsrDay> selecPieResultByOperId(Map<String, Object> params) {
		return billSummingUsrDayDao.selecPieResultByOperId(params);
	}

	public List<BillSummingUsrDay> selectBarResult(Map<String, Object> params) {
		return billSummingUsrDayDao.selectBarResult(params);
	}

	public List<Map<String, Object>> selecLineResult(Map<String, Object> params) {
		return billSummingUsrDayDao.selecLineResultByOperId(params);
	}

}
