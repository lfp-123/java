package com.newland.boss.cib.crmp.settle.dao;

import java.util.List;
import java.util.Map;

import com.newland.boss.cib.crmp.domain.BillSummingUsrDay;

public interface BillSummingUsrDayDao {

	List<BillSummingUsrDay> selectBarResult(Map<String, Object> params);

	List<BillSummingUsrDay> selecPieResultByOperId(Map<String, Object> params);
	
	List<Map<String, Object>> selecLineResultByOperId(Map<String, Object> params);
}
