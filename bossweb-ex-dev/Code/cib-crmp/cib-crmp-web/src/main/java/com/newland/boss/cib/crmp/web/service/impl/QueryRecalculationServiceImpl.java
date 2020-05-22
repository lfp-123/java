package com.newland.boss.cib.crmp.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newland.boss.cib.crmp.web.dao.RecalcCdrTaskDao;
import com.newland.boss.cib.crmp.web.model.QueryRecalculationReq;
import com.newland.boss.cib.crmp.web.model.QueryRecalculationResp;
import com.newland.boss.cib.crmp.web.service.QueryRecalculationService;
@Component
public class QueryRecalculationServiceImpl implements QueryRecalculationService {

	@Autowired
	private RecalcCdrTaskDao recalcCdrTaskDao;

	@Override
	public List<QueryRecalculationResp> queryRecalcCdrTaskListResult(QueryRecalculationReq recalculationReq, int page,
			int size) {
		return recalcCdrTaskDao.queryRecalcCdrTaskListResult(recalculationReq, page, size);
	}

	@Override
	public int queryRecalcCdrTaskListCount(QueryRecalculationReq recalculationReq) {
		return recalcCdrTaskDao.queryRecalcCdrTaskListCount(recalculationReq);
	}

	@Override
	public Map<String, String> createRecalcCdrTask(QueryRecalculationReq recalculationReq) {
		Map<String, String> map = new HashMap<String, String>();
		int count = recalcCdrTaskDao.undoneRecalcCdrTasktCount(recalculationReq);
		if (count == 0) {
			try{
			recalcCdrTaskDao.insertRecalcCdrTask(recalculationReq);
			map.put("status", "success");
			map.put("info", "重算任务生成成功");
			} catch (Exception e){
				System.out.println(e.getMessage());
				map.put("status", "error");
				map.put("info", e.getMessage());
			}
		} else {
			map.put("status", "error");
			map.put("info", "存在未完成重算，请确认！");
		}
		return map;
	}

}
