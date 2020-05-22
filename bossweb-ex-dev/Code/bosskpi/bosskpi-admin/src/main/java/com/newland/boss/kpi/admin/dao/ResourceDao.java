package com.newland.boss.kpi.admin.dao;

import java.util.List;
import java.util.Map;
import com.newland.boss.kpi.admin.model.Resource;

public interface ResourceDao {
	List<Resource> selectAllResource();
	Resource selectResourceById(Integer resourceId);
	List<Resource> fuzzySearchResource(Map<String, Object> searchMap);
	void insertResource(Resource resource);
	void updateResource(Resource resource);
	void deleteResource(Integer resourceId);
}
