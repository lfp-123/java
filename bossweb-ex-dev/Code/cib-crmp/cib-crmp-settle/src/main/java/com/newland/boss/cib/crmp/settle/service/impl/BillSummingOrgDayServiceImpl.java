package com.newland.boss.cib.crmp.settle.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newland.boss.cib.crmp.domain.BillSummingOrgDay;
import com.newland.boss.cib.crmp.settle.dao.BillSummingOrgDayDao;
import com.newland.boss.cib.crmp.settle.service.BillSummingOrgDayService;

@Component
public class BillSummingOrgDayServiceImpl implements BillSummingOrgDayService {
	
	@Autowired
	private BillSummingOrgDayDao billSummingOrgDayDao;
	
	public List<BillSummingOrgDay> selecPieResultByOrgId(Map<String, Object> params) {
		return billSummingOrgDayDao.selecPieResultByOrgId(params);
	}

	public List<BillSummingOrgDay> selectBarResult(Map<String, Object> params) {
		return billSummingOrgDayDao.selectBarResult(params);
	}

	public List<Map<String, Object>> selecLineResult(Map<String, Object> params) {
		return billSummingOrgDayDao.selecLineResultByOrgId(params);
	}

	@Override
	public List<BillSummingOrgDay> selecTotalPieResult(Map<String, Object> params) {
		return billSummingOrgDayDao.selecTotalPieResult(params);
	}

	@Override
	public List<Map<String, Object>> selecTotalLineResult(Map<String, Object> params) {
		return billSummingOrgDayDao.selecTotalLineResult(params);
	}

	@Override
	public List<BillSummingOrgDay> selectSubTotalBarResult(Map<String, Object> params) {
		return billSummingOrgDayDao.selectSubTotalBarResult(params);
	}
	
}
