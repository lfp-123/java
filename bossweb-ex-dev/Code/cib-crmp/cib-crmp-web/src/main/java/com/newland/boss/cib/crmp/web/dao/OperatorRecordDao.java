package com.newland.boss.cib.crmp.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.newland.boss.cib.crmp.domain.OperatorRecord;

@Repository
public interface OperatorRecordDao {

	// 根据条件查询操作日志
	List<OperatorRecord> queryOperRecordList(@Param(value = "req") OperatorRecord req,
			@Param(value = "orgIdArray") Integer[] orgIdArray, @Param(value = "page") int page,
			@Param(value = "size") int size);

	Integer countOperRecordNumber(@Param(value = "req") OperatorRecord req,
			@Param(value = "orgIdArray") Integer[] orgIdArray);

}
