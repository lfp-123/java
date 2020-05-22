package com.newland.boss.cib.crmp.code.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newland.boss.cib.crmp.code.entity.DataUser;
import com.newland.boss.cib.crmp.code.entity.Model;
import com.newland.boss.cib.crmp.code.service.ModelService;
import com.newland.boss.cib.crmp.code.tool.ResourceMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping
public class ModelController {
	static 	Map<String,String> resource;
 	static {
		resource = ResourceMap.getJdbcMap();

	}

	@Resource
	private ModelService modelService;


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> login(DataUser dataUser, HttpServletRequest request) {

		if (dataUser.getJdbcDriver().equals("OracleSID")) {
			resource.put("name",dataUser.getJdbcName());
			resource.put("type","OracleSID");
			resource.put("driver", "oracle.jdbc.driver.OracleDriver");
			resource.put("url", "jdbc:oracle:thin:@"+dataUser.getJdbcPort()+":"+dataUser.getJdbcName());
		}
		if (dataUser.getJdbcDriver().equals("OracleServiceName")) {
			resource.put("type", "OracleServiceName");
			resource.put("driver", "oracle.jdbc.driver.OracleDriver");
			resource.put("url", "jdbc:oracle:thin:@//"+dataUser.getJdbcPort()+"/"+dataUser.getJdbcName());
		}
		if (dataUser.getJdbcDriver().equals("Altibase")) {
			resource.put("type", "Altibase");
			resource.put("driver", "Altibase.jdbc.driver.AltibaseDriver");
			resource.put("url", "jdbc:Altibase://"+dataUser.getJdbcPort()+"/"+dataUser.getJdbcName());
		}
		if (dataUser.getJdbcDriver().equals("TimeTen")) {
			resource.put("type", "TimeTen");
			resource.put("driver", "com.timesten.jdbc.TimesTenDriver");
			resource.put("url", "jdbc:timesten:"+dataUser.getJdbcPort()+":dsn="+dataUser.getJdbcName());
		}
		if (dataUser.getJdbcDriver().equals("MySQL")) {
			resource.put("type", "MySQL");
			resource.put("driver", "com.mysql.jdbc.Driver");
			resource.put("url", "jdbc:mysql://"+dataUser.getJdbcPort()+"/"+dataUser.getJdbcName()
			+"?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
		}

		return resource;
	}


	@RequestMapping(value = "/databaseName", method = RequestMethod.POST)
	public @ResponseBody Map<String, String>  getDatabaseName() {

		return resource;
	}
	
	@RequestMapping(value = "/out", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> login() {
		resource.put("name","无连接");
		resource.put("username","无用户");
		resource.put("port","8080");
		resource.put("driver","null");
		resource.put("type","null");
		resource.put("url","null");
		resource.put("password","null");
		return resource;
	}
	

	@RequestMapping(value = "/getmodel", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> Find(HttpServletResponse resp, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Model> modelList = modelService.getStringList(name,resource.get("type"));
			map.put("tablename", name);
			map.put("modellist", modelList);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "查无此表或者数据库连接错误");
		}
		return map;

	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> query(@RequestBody Map<String, Object> prarms) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<String> valuelist = (ArrayList<String>) prarms.get("checkID");
		ArrayList<String> valuelist2 = (ArrayList<String>) prarms.get("checkID2");
		String tablename = prarms.get("tablename").toString();
		String type = prarms.get("type").toString();
		String model = modelService.getModel(tablename, type, valuelist, valuelist2,resource.get("type"));
		map.put("model", model);
		map.put("tablename", tablename);
		return map;

	}

}
