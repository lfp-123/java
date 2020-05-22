package com.newland.boss.cib.crmp.settle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newland.boss.cib.crmp.domain.QueryCallListReq;
import com.newland.boss.cib.crmp.domain.RatedNetwork;

public interface RatedNetworkDao {

//	List<RatedNetwork> ratedNetworkQuery(@Param(value="req") QueryCallListReq req);
	List<RatedNetwork> queryNetworkCallListResult(@Param(value="req") QueryCallListReq req);
//	int queryNetworkCallListCount(@Param(value="req") QueryCallListReq req);

//	List<RatedNetwork> ratedNetworkBlackQuery(@Param(value="req") QueryCallListReq req);
	List<RatedNetwork> queryNetworkBlackCallListResult(@Param(value="req") QueryCallListReq req);
//	int queryNetworkBlackCallListCount(@Param(value="req") QueryCallListReq req);
	
//	List<RatedNetwork> queryAllBlackNetUser(@Param(value="req") QueryCallListReq req);
	List<RatedNetwork> queryNetBlackUsersResult(@Param(value="req") QueryCallListReq req);
//	int queryNetBlackUsersListCount(@Param(value="req") QueryCallListReq req);
}
