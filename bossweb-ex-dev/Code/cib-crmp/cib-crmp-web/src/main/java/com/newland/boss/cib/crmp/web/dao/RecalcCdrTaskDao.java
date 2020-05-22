package com.newland.boss.cib.crmp.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.newland.boss.cib.crmp.web.model.QueryRecalculationReq;
import com.newland.boss.cib.crmp.web.model.QueryRecalculationResp;
@Repository
public interface RecalcCdrTaskDao {

	// 未完成重算任务计数
	int undoneRecalcCdrTasktCount(@Param(value = "req") QueryRecalculationReq req);
	//插入重算任务
	void insertRecalcCdrTask(@Param(value = "req") QueryRecalculationReq recalculationReq);
	// 重算任务分页查询
	List<QueryRecalculationResp> queryRecalcCdrTaskListResult(@Param(value = "req") QueryRecalculationReq req, @Param(value = "page") int page, @Param(value = "size") int size);
	// 重算任务查询总记录数
	int queryRecalcCdrTaskListCount(@Param(value = "req") QueryRecalculationReq req);
}
