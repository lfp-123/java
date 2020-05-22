package com.newland.boss.cib.crmp.settle.service;

import java.util.List;
import java.util.Map;

import com.newland.boss.cib.crmp.domain.BillSummingOrgDay;

public interface BillSummingOrgDayService {
	List<BillSummingOrgDay> selecPieResultByOrgId(Map<String, Object> params);

	List<BillSummingOrgDay> selectBarResult(Map<String, Object> params);
	
	List<Map<String, Object>> selecLineResult(Map<String, Object> params);
	
	List<BillSummingOrgDay> selecTotalPieResult(Map<String, Object> params);

	List<Map<String, Object>> selecTotalLineResult(Map<String, Object> params);
	
	List<BillSummingOrgDay> selectSubTotalBarResult(Map<String, Object> params);
}
