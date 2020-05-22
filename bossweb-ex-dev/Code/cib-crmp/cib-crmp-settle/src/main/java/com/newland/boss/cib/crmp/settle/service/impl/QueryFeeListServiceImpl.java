package com.newland.boss.cib.crmp.settle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newland.boss.cib.crmp.domain.QueryCallListReq;
import com.newland.boss.cib.crmp.domain.QueryFeeListReq;
import com.newland.boss.cib.crmp.domain.QueryFeeListResp;
import com.newland.boss.cib.crmp.domain.RatedCdr;
import com.newland.boss.cib.crmp.settle.dao.QueryFeeListDao;
import com.newland.boss.cib.crmp.settle.dao.RatedCdrDao;
import com.newland.boss.cib.crmp.settle.service.QueryFeeListService;

@Component
public class QueryFeeListServiceImpl implements QueryFeeListService {
	
	@Autowired
	private QueryFeeListDao queryFeeListDao;
	
	@Autowired
	private RatedCdrDao ratedCdrDao;
	
	@Override
	public List<QueryFeeListResp> queryFeeListResult(QueryFeeListReq req, Integer[] orgIdArray, int page, int size) {
		if (req.getUserName() != null) {
			req.setUserName(req.getUserName().replaceAll("_", "/_").replaceAll("%", "/%"));  // 解决输入占位符百分号符依旧搜索出结果
		}
		return queryFeeListDao.queryFeeListResult(req, orgIdArray, page, size);
	}
	
	@Override
	public int queryFeeListCount(QueryFeeListReq req, Integer[] orgIdArray) {
		if (req.getUserName() != null) {
			req.setUserName(req.getUserName().replaceAll("_", "/_").replaceAll("%", "/%"));  // 解决输入占位符百分号符依旧搜索出结果
		}
		return queryFeeListDao.queryFeeListCount(req, orgIdArray);
	}

	@Override
	public List<QueryFeeListResp> queryFeeListAllResult(QueryFeeListReq req, Integer[] orgIdArray) {
		if (req.getUserName() != null) {
			req.setUserName(req.getUserName().replaceAll("_", "/_").replaceAll("%", "/%"));  // 解决输入占位符百分号符依旧搜索出结果
		}
		return queryFeeListDao.queryFeeListAllResult(req, orgIdArray);
	}

	@Override
	public List<QueryFeeListResp> queryTopNListResult(Integer[] orgIdArray, Integer topN, String beginDate, String endDate, int page, int size) {
		return queryFeeListDao.queryTopNListResult(orgIdArray, topN, beginDate, endDate, page, size);
	}

	@Override
	public int queryTopNListCount(Integer[] orgIdArray, Integer topN, String beginDate, String endDate) {
		return queryFeeListDao.queryTopNListCount(orgIdArray, topN, beginDate, endDate);
	}

	@Override
	public List<QueryFeeListResp> queryTopNListAllResult(Integer[] orgIdArray, Integer topN, String beginDate, String endDate) {
		return queryFeeListDao.queryTopNListAllResult(orgIdArray, topN, beginDate, endDate);
	}

	@Override
	public List<QueryFeeListResp> queryThresholdListResult(Integer[] orgIdArray,Double thresholdValue, String beginDate, String endDate, int page, int size) {
		return queryFeeListDao.queryThresholdListResult(orgIdArray, thresholdValue, beginDate, endDate, page, size);
	}

	@Override
	public int queryThresholdListCount(Integer[] orgIdArray, Double thresholdValue, String beginDate, String endDate) {
		return queryFeeListDao.queryThresholdListCount(orgIdArray, thresholdValue, beginDate, endDate);
	}

	@Override
	public List<QueryFeeListResp> queryThresholdListAllResult(Integer[] orgIdArray,Double thresholdValue, String beginDate, String endDate) {
		return queryFeeListDao.queryThresholdListAllResult(orgIdArray, thresholdValue, beginDate, endDate);
	}

	@Override
	public List<QueryFeeListResp> queryNotActiveUserListResult(Integer[] orgIdArray,Double thresholdValue, String beginDate, String endDate, int page, int size) {
		return queryFeeListDao.queryNotActiveUserListResult(orgIdArray, thresholdValue, beginDate, endDate, page, size);
	}

	@Override
	public int queryNotActiveUserListCount(Integer[] orgIdArray,Double thresholdValue, String beginDate, String endDate) {
		return queryFeeListDao.queryNotActiveUserListCount(orgIdArray, thresholdValue, beginDate, endDate);
	}

	@Override
	public List<QueryFeeListResp> queryNotActiveUserListAllResult(Integer[] orgIdArray,Double thresholdValue, String beginDate, String endDate) {
		return queryFeeListDao.queryNotActiveUserListAllResult(orgIdArray, thresholdValue, beginDate, endDate);
	}

	@Override
	public List<QueryFeeListResp> batchQueryFeeListResult(QueryFeeListReq req) {
		return queryFeeListDao.batchQueryFeeListResult(req);
	}

	@Override
	public List<QueryFeeListResp> queryDeptFeeListResult(QueryFeeListReq req, Integer[] orgIdArray, int page,
			int size) {
		return queryFeeListDao.queryDeptFeeListResult(req, orgIdArray, page, size);
	}

	@Override
	public int queryDeptFeeListCount(QueryFeeListReq req, Integer[] orgIdArray) {
		return queryFeeListDao.queryDeptFeeListCount(req, orgIdArray);
	}

	@Override
	public List<QueryFeeListResp> queryDeptFeeListAllResult(QueryFeeListReq req, Integer[] orgIdArray) {
		return queryFeeListDao.queryDeptFeeListAllResult(req, orgIdArray);
	}

	@Override
	public List<QueryFeeListResp> batchQueryDeptFeeListResult(QueryFeeListReq req) {
		return queryFeeListDao.batchQueryDeptFeeListResult(req);
	}

	@Override
	public List<RatedCdr> queryOrgMonFeeDetail(QueryCallListReq req, int page, int size) {
		return ratedCdrDao.queryOrgMonFeeDetail(req, page, size);
	}

	@Override
	public int queryOrgMonFeeDetailCount(QueryCallListReq req) {
		return ratedCdrDao.queryOrgMonFeeDetailCount(req);
	}
	
}
