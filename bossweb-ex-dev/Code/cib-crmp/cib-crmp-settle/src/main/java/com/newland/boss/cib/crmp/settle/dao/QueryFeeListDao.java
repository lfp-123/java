package com.newland.boss.cib.crmp.settle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.newland.boss.cib.crmp.domain.QueryFeeListReq;
import com.newland.boss.cib.crmp.domain.QueryFeeListResp;

@Repository
public interface QueryFeeListDao {
	
	// 资源使用情况分页查询
	List<QueryFeeListResp> queryFeeListResult(@Param(value="req") QueryFeeListReq req,@Param(value="orgIdArray") Integer[] orgIdArray,
												@Param(value="page") int page, @Param(value="size") int size);
	// 资源使用情况查询总记录数
	int queryFeeListCount(@Param(value="req") QueryFeeListReq req, @Param(value="orgIdArray") Integer[] orgIdArray);
	// 资源使用情况全量查询
	List<QueryFeeListResp> queryFeeListAllResult(@Param(value="req") QueryFeeListReq req, @Param(value="orgIdArray") Integer[] orgIdArray);
	
	// 部门资源使用情况分页查询 
	List<QueryFeeListResp> queryDeptFeeListResult(@Param(value="req") QueryFeeListReq req,@Param(value="orgIdArray") Integer[] orgIdArray,
			@Param(value="page") int page, @Param(value="size") int size);
	// 部门资源使用情况查询记录数
	int queryDeptFeeListCount(@Param(value="req") QueryFeeListReq req, @Param(value="orgIdArray") Integer[] orgIdArray);
	// 部门资源使用情况全量查询
	List<QueryFeeListResp> queryDeptFeeListAllResult(@Param(value="req") QueryFeeListReq req, @Param(value="orgIdArray") Integer[] orgIdArray);
	
	// TopN查询
	List<QueryFeeListResp> queryTopNListResult(@Param(value="orgIdArray") Integer[] orgIdArray, 
			@Param(value="topN") Integer topN, 
			@Param(value="beginDate") String beginDate, @Param(value="endDate") String endDate, 
			@Param(value="page") int page, @Param(value="size") int size);
	// TopN查询总记录数
	int queryTopNListCount(@Param(value="orgIdArray") Integer[] orgIdArray, @Param(value="topN") Integer topN, 
			@Param(value="beginDate") String beginDate, @Param(value="endDate") String endDate);
	// TopN查询导出报表
	List<QueryFeeListResp> queryTopNListAllResult(@Param(value="orgIdArray") Integer[] orgIdArray, 
			@Param(value="topN") Integer topN,
			@Param(value="beginDate") String beginDate, @Param(value="endDate") String endDate);
	
	// 超阀值查询
	List<QueryFeeListResp> queryThresholdListResult(@Param(value="orgIdArray") Integer[] orgIdArray, 
			@Param(value="thresholdValue") Double thresholdValue, 
			@Param(value="beginDate") String beginDate, @Param(value="endDate") String endDate, 
			@Param(value="page") int page, @Param(value="size") int size);
	// 超阀值查询总记录数
	int queryThresholdListCount(@Param(value="orgIdArray") Integer[] orgIdArray, 
			@Param(value="thresholdValue") Double thresholdValue,
			@Param(value="beginDate") String beginDate, @Param(value="endDate") String endDate
			);
	// 超阀值查询导出报表
	List<QueryFeeListResp> queryThresholdListAllResult(@Param(value="orgIdArray") Integer[] orgIdArray, 
			@Param(value="thresholdValue") Double thresholdValue,
			@Param(value="beginDate") String beginDate, @Param(value="endDate") String endDate);
	
	// 非活跃用户查询
	List<QueryFeeListResp> queryNotActiveUserListResult(@Param(value="orgIdArray") Integer[] orgIdArray, 
			@Param(value="thresholdValue") Double thresholdValue, 
			@Param(value="beginDate") String beginDate, @Param(value="endDate") String endDate,
			@Param(value="page") int page, @Param(value="size") int size);
	// 非活跃用户查询总记录数
	int queryNotActiveUserListCount(@Param(value="orgIdArray") Integer[] orgIdArray, 
			@Param(value="thresholdValue") Double thresholdValue,
			@Param(value="beginDate") String beginDate, @Param(value="endDate") String endDate
			);
	// 非活跃用户查询报表导出
	List<QueryFeeListResp> queryNotActiveUserListAllResult(@Param(value="orgIdArray") Integer[] orgIdArray, 
			@Param(value="thresholdValue") Double thresholdValue,
			@Param(value="beginDate") String beginDate, @Param(value="endDate") String endDate
			);
	
	// 批量查询
	List<QueryFeeListResp> batchQueryFeeListResult(@Param(value="req") QueryFeeListReq req);
	
	// 部门批量查询
	List<QueryFeeListResp> batchQueryDeptFeeListResult(@Param(value="req") QueryFeeListReq req);
}
