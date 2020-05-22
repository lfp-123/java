package com.newland.boss.cib.crmp.settle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.newland.boss.cib.crmp.domain.QueryCallListReq;
import com.newland.boss.cib.crmp.domain.RatedCdr;
import com.newland.boss.cib.crmp.domain.SummingSettleTask;

@Repository
public interface RatedCdrDao {

//	List<RatedCdr> ratedCdrQuery(@Param(value="req") QueryCallListReq req);
	List<RatedCdr> queryCdrCallListResult(@Param(value="req") QueryCallListReq req);
//	int queryCdrCallListCount(@Param(value="req") QueryCallListReq req);

//	List<RatedCdr> ratedCdrBlackQuery(@Param(value="req") QueryCallListReq req);
	List<RatedCdr> queryCdrBlackCallListResult(@Param(value="req") QueryCallListReq req);
//	int queryCdrBlackCallListCount(@Param(value="req") QueryCallListReq req);
	
//	List<RatedCdr> queryAllBlackCdrUser(@Param(value="req") QueryCallListReq req);
	List<RatedCdr> queryCdrBlackUsersResult(@Param(value="req") QueryCallListReq req);
//	int queryCdrBlackUsersCount(@Param(value="req") QueryCallListReq req);
	
	void updateUserInfo(@Param(value="req") QueryCallListReq req);//cdr黑户用户编辑-黑户统计
	void updateUserInfoForDetail(@Param(value="req") QueryCallListReq req);//cdr黑户用户编辑-黑户详单
	
	void insertSummingSettleTask(SummingSettleTask summingSettleTask);//插入合账任务
	String queryLoginName(int operatorId);
	
	List<RatedCdr> queryOrgMonFeeDetail(@Param(value="req") QueryCallListReq req, @Param(value="page") int page, @Param(value="size") int size);  // 查询部门月租费详情
	int queryOrgMonFeeDetailCount(@Param(value="req") QueryCallListReq req);  // 查询部门月租费详情数量
	
}
