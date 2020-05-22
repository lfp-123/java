package com.newland.boss.cib.crmp.settle.dao;

import java.util.List;
import java.util.Map;

import com.newland.boss.cib.crmp.domain.BillSummingOrgDay;

public interface BillSummingOrgDayDao {

	List<BillSummingOrgDay> selectBarResult(Map<String, Object> params);

	List<BillSummingOrgDay> selecPieResultByOrgId(Map<String, Object> params);
	
	List<Map<String, Object>> selecLineResultByOrgId(Map<String, Object> params);
	
	List<BillSummingOrgDay> selecTotalPieResult(Map<String, Object> params);

	List<Map<String, Object>> selecTotalLineResult(Map<String, Object> params);
	
	List<BillSummingOrgDay> selectSubTotalBarResult(Map<String, Object> params);
}
