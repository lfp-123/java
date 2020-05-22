package com.newland.boss.cib.crmp.settle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newland.boss.cib.crmp.domain.QueryCallListReq;
import com.newland.boss.cib.crmp.domain.RatedDma;

public interface RatedDmaDao {

//	List<RatedDma> ratedDmaQuery(@Param(value="req") QueryCallListReq req);
	List<RatedDma> queryDmaCallListResult(@Param(value="req") QueryCallListReq req);
//	int queryDmaCallListCount(@Param(value="req") QueryCallListReq req);

//	List<RatedDma> ratedDmaBlackQuery(@Param(value="req") QueryCallListReq req);
	List<RatedDma> queryDmaBlackCallListResult(@Param(value="req") QueryCallListReq req);
//	int queryDmaBlackCallListCount(@Param(value="req") QueryCallListReq req);
	
//	List<RatedDma> queryAllBlackDmaUser(@Param(value="req") QueryCallListReq req);
	List<RatedDma> queryDmaBlackUsersResult(@Param(value="req") QueryCallListReq req);
//	int queryDmaBlackUsersCount(@Param(value="req") QueryCallListReq req);

	void updateOrgInfo(@Param(value="req") QueryCallListReq req);//dma黑户统计部门编辑
	void updateOrgInfoForDetail(@Param(value="req") QueryCallListReq req);//dma黑户详单部门编辑
	void addInsuranceFee(@Param(value="req") QueryCallListReq req);//dma详单查询 增加保障费
}
