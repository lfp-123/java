package com.newland.boss.kpi.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.newland.boss.kpi.admin.model.Resource;
import com.newland.boss.kpi.admin.service.ResourceService;
import com.newland.boss.kpi.admin.util.RecursionTree;
import com.newland.boss.kpi.entity.OperateLog;
import com.newland.boss.kpi.server.OperateLogService;

@RestController
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private OperateLogService operateLogService;
	
	//查询所有资源
	@RequestMapping(value = "/findAllResources", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String findAllResources(){
			
		//查询数据库数据并封装成Json集合发送到前台
		List<Resource> resourceList = resourceService.findAllRes();
		String respJson;
		Gson gson = new Gson();
		respJson = gson.toJson(resourceList);
		return respJson;
		
	}	
	
	//添加资源
	@RequestMapping(value = "/addResource", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addResource(@RequestBody Resource resource){
		resourceService.addRes(resource);
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(resource.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(1);
		operateLog.setOperateDesc("添加菜单信息：" + resource);
		operateLogService.addLog(operateLog);
		
		//以资源树形式返回添加资源后的所有资源
		return "{\"msg\":\"添加成功\", \"code:\":\"0\"}";
	}	
	
	//查询出一个资源
	@RequestMapping(value = "/findResourceById", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String findResourceById(@RequestBody Resource resource){
				
		//查询数据库数据并封装成Json集合发送到前台
		Resource res = resourceService.findAnRes(resource);
		String respJson;
		Gson gson = new Gson();
		respJson = gson.toJson(res);
		return respJson;
		
	}
	
	//修改资源信息
	@RequestMapping(value = "/editResource", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editResource(@RequestBody Resource resource){
		resourceService.editRes(resource);
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(resource.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(3);
		operateLog.setOperateDesc("修改菜单信息：" + resource);
		operateLogService.addLog(operateLog);
		
		return "{\"msg\":\"修改成功\", \"code:\":\"0\"}";
	}
	
	//删除资源
	@RequestMapping(value = "/removeResource", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String removeResource(@RequestBody Resource resource){
		resourceService.removeRes(resource);
		
		//添加操作日志到记录
		OperateLog operateLog = new OperateLog();
		operateLog.setOperatorId(resource.getModifyOperatorId());
		operateLog.setOperateModule(12);
		operateLog.setOperateType(2);
		operateLog.setOperateDesc("删除菜单信息：" + resource);
		operateLogService.addLog(operateLog);
		
		return "{\"msg\":\"删除成功\", \"code:\":\"0\"}";
	}
	
	//搜索资源
	@RequestMapping(value = "/searchResource", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String searchResource(@RequestBody Resource resource){
		List<Resource> searchList = resourceService.searchRes(resource);
		Gson gson = new Gson();
		return gson.toJson(searchList);
	}
	
	//查找菜单树 
	@RequestMapping(value = "/showAuthTree", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String showAuthTree(){
		List<Resource> resList = resourceService.findAllRes();		
		return RecursionTree.getResourceTreeJson(resList);
	}
	
}
