package com.newland.boss.cib.crmp.settle.service;

import java.util.Map;

import com.newland.boss.cib.crmp.domain.QueryCallListReq;

public interface QueryCallListService {
	Map<String, Object> queryCallListResult(QueryCallListReq req, int page, int size);
//	int queryCallListCount(QueryCallListReq req);
	Map<String, String> createCallListExcel(QueryCallListReq req);
	
	Map<String, Object> queryBlackCallListResult(QueryCallListReq req, int page, int size);
//	int queryBlackCallListCount(QueryCallListReq req);
	Map<String, String> createBlackCallListExcel(QueryCallListReq req);
	
	Map<String, Object> queryBlackUserListResult(QueryCallListReq req, int page, int size);
//	int queryBlackUserListCount(QueryCallListReq req);
	Map<String, String> createBlackUserListExcel(QueryCallListReq req);
	
	Map<String, String> editUserInfo(QueryCallListReq req);
	Map<String, String> editUserInfoForDetail(QueryCallListReq req);
	Map<String, String> editOrgInfo(QueryCallListReq req);
	Map<String, String> addInsuranceFee(QueryCallListReq req);
}
