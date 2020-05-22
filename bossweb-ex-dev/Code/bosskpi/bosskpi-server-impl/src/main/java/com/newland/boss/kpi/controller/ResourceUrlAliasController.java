package com.newland.boss.kpi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.newland.boss.kpi.entity.Resource;
import com.newland.boss.kpi. entity.User;
import com.newland.boss.kpi.server.AppSession;
import com.newland.boss.kpi.util.ResourceComparetor;

@RestController
public class ResourceUrlAliasController {
	
	@Autowired
	private AppSession appSession;
	
	@RequestMapping(value = "/findResourceByOperatorId", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String findResourceByOperatorId() {
		
		User user = appSession.getUser();
				
		List<Resource> resList = user.getResourceList();
		for(Map.Entry<String, List<Resource>> entry :user.getRoleResourcesMap().entrySet()) {
			resList.addAll(entry.getValue());
		}
		
		List<Resource> menuList = this.getResource(-1, resList);
		Collections.sort(menuList, new ResourceComparetor());
		Gson gson = new Gson();
		return gson.toJson(menuList);
	}
	
	//递归构建菜单树
	private List<Resource> getResource(int superMenuId ,List<Resource> resList) {
		
		if( null == resList ) {
			return Collections.emptyList();
		}
		
		List<Resource> menuList = new ArrayList<>();
		for(Resource res:resList) {
			if( res.getSuperResourceId() == superMenuId) {
				if(menuList.contains(res)) {
					continue;
				}
				Resource menu = new Resource();
				menu.setResourceId(res.getResourceId());
				menu.setSuperResourceId(res.getSuperResourceId());
				menu.setResourceOrder(res.getResourceOrder());
				menu.setResourceName(res.getResourceName());
				String resUrl = "";
				if (res.getResourceUrl() != null) {
					resUrl = res.getResourceUrl().substring(1, res.getResourceUrl().length());
				}
				menu.setResourceUrl(resUrl);
				menu.setChildResourceList(getResource(res.getResourceId(), resList));
				menuList.add(menu);
			}
		}
		if(menuList.isEmpty()) {
			return Collections.emptyList();
		}else {
			Collections.sort(menuList, new ResourceComparetor());
			return menuList;
		}
	}
	
}
