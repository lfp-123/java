package com.newland.boss.cib.crmp.settle.service;

import java.util.List;
import java.util.Map;

import com.newland.boss.cib.crmp.domain.BillSummingUsrDay;

public interface BillSummingUsrDayService {
	List<BillSummingUsrDay> selecPieResultByOperId(Map<String, Object> params);

	List<BillSummingUsrDay> selectBarResult(Map<String, Object> params);
	
	List<Map<String, Object>> selecLineResult(Map<String, Object> params);
}
