package com.newland.boss.kpi.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newland.boss.kpi.admin.dao.ResourceDao;
import com.newland.boss.kpi.admin.model.Resource;
import com.newland.boss.kpi.admin.service.ResourceService;

@Component
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public void addRes(Resource resource) {
		resourceDao.insertResource(resource);
	}

	@Override
	public List<Resource> findAllRes() {
		return resourceDao.selectAllResource();
	}

	@Override
	public Resource findAnRes(Resource resource) {
		return resourceDao.selectResourceById(resource.getResourceId());
	}

	@Override
	public void editRes(Resource resource) {
		resourceDao.updateResource(resource);
	}

	//递归删除资源和资源下面的所有子资源
	@Override
	public void removeRes(Resource resource) {
		resourceDao.deleteResource(resource.getResourceId());
		if(resource.getChildResourceList() != null && !resource.getChildResourceList().isEmpty()) {
			for (Resource tmp : resource.getChildResourceList()) {
				removeRes(tmp);
			}
		}
	}

	@Override
	public List<Resource> searchRes(Resource resource) {
			
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("resourceId",resource.getResourceId());
		searchMap.put("resourceName",resource.getResourceName());
		return resourceDao.fuzzySearchResource(searchMap);
	}

}
