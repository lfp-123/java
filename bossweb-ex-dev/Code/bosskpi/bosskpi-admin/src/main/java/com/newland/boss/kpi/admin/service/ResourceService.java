package com.newland.boss.kpi.admin.service;

import java.util.List;

import com.newland.boss.kpi.admin.model.Resource;

public interface ResourceService {
	void addRes(Resource resource);
	List<Resource> findAllRes();
	Resource findAnRes(Resource resource);
	void editRes(Resource resource);
	void removeRes(Resource resource);
	List<Resource> searchRes(Resource resource);
}
