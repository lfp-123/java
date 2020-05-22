package com.newland.boss.cib.crmp.web.service;

import java.util.List;
import java.util.Map;

import com.newland.boss.cib.crmp.web.model.QueryRecalculationReq;
import com.newland.boss.cib.crmp.web.model.QueryRecalculationResp;

public interface QueryRecalculationService {

	List<QueryRecalculationResp> queryRecalcCdrTaskListResult(QueryRecalculationReq recalculationReq, int page, int size);

	int queryRecalcCdrTaskListCount(QueryRecalculationReq recalculationReq);

	Map<String, String> createRecalcCdrTask(QueryRecalculationReq recalculationReq);

	
}
