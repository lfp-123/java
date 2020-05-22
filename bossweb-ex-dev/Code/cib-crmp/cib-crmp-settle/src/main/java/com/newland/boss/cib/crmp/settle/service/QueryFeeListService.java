package com.newland.boss.cib.crmp.settle.service;

import java.util.List;

import com.newland.boss.cib.crmp.domain.QueryCallListReq;
import com.newland.boss.cib.crmp.domain.QueryFeeListReq;
import com.newland.boss.cib.crmp.domain.QueryFeeListResp;
import com.newland.boss.cib.crmp.domain.RatedCdr;

public interface QueryFeeListService {
	// 资源使用情况分页查询
	List<QueryFeeListResp> queryFeeListResult(QueryFeeListReq req, Integer[] orgIdArray, int page, int size);
	// 资源使用情况查询总记录数
	int queryFeeListCount(QueryFeeListReq req, Integer[] orgIdArray);
	// 资源使用情况全量查询
	List<QueryFeeListResp> queryFeeListAllResult(QueryFeeListReq req, Integer[] orgIdArray);
	
	// 部门资源使用情况分页查询
	List<QueryFeeListResp> queryDeptFeeListResult(QueryFeeListReq req, Integer[] orgIdArray, int page, int size);
	// 部门资源使用情况查询总记录数
	int queryDeptFeeListCount(QueryFeeListReq req, Integer[] orgIdArray);
	// 部门资源使用情况全量查询
	List<QueryFeeListResp> queryDeptFeeListAllResult(QueryFeeListReq req, Integer[] orgIdArray);
	
	// TopN查询
	List<QueryFeeListResp> queryTopNListResult(Integer[] orgIdArray, Integer topN, String beginDate, String endDate, int page, int size);
	// TopN查询总记录数
	int queryTopNListCount(Integer[] orgIdArray, Integer topN, String beginDate, String endDate);
	// TopN查询导出报表
	List<QueryFeeListResp> queryTopNListAllResult(Integer[] orgIdArray, Integer topN, String beginDate, String endDate);
	
	// 超阀值查询
	List<QueryFeeListResp> queryThresholdListResult(Integer[] orgIdArray, Double thresholdValue, String beginDate, String endDate, int page, int size);
	// 超阀值查询总记录数
	int queryThresholdListCount(Integer[] orgIdArray, Double thresholdValue, String beginDate, String endDate);
	// 超阀值查询导出报表
	List<QueryFeeListResp> queryThresholdListAllResult(Integer[] orgIdArray, Double thresholdValue, String beginDate, String endDate);
	
	// 非活跃用户查询
	List<QueryFeeListResp> queryNotActiveUserListResult(Integer[] orgIdArray, Double thresholdValue, String beginDate, String endDate, int page, int size);
	// 非活跃用户查询总记录数
	int queryNotActiveUserListCount(Integer[] orgIdArray, Double thresholdValue, String beginDate, String endDate);
	// 非活跃用户查询报表导出
	List<QueryFeeListResp> queryNotActiveUserListAllResult(Integer[] orgIdArray,Double thresholdValue, String beginDate, String endDate);
	
	// 个人批量查询
	List<QueryFeeListResp> batchQueryFeeListResult(QueryFeeListReq req);
	// 部门批量查询
	List<QueryFeeListResp> batchQueryDeptFeeListResult(QueryFeeListReq req);
	
	// 查询部门月租费详情
	List<RatedCdr> queryOrgMonFeeDetail(QueryCallListReq req, int page, int size);  
	// 查询部门月租费详情数量
	int queryOrgMonFeeDetailCount(QueryCallListReq req);  
	
}
