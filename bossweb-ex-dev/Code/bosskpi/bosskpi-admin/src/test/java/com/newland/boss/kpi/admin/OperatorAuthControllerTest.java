package com.newland.boss.kpi.admin;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.newland.boss.kpi.admin.common.AbstractControllerTestNGTest;
import com.newland.boss.kpi.admin.controller.OperatorAuthController;
import com.newland.boss.kpi.admin.dao.OperatorAuthDao;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.admin.model.OperatorAuth;
import com.newland.boss.kpi.admin.service.OperatorAuthService;
import com.newland.boss.kpi.server.OperateLogService;

public class OperatorAuthControllerTest extends AbstractControllerTestNGTest{
	
	@Autowired
	private OperatorAuthController operatorAuthController;
	
	@Autowired
	private OperatorAuthService operatorAuthService;
	
	@Autowired
	private OperateLogService operateLogService;
	
	@Autowired
	private OperatorAuthDao operatorAuthDao;
	
	private SqlSessionFactory sessionFactory;
    private static SqlSession session;
	
    @Override
	protected Object getController() {
		// TODO Auto-generated method stub
		return operatorAuthController;
	}
    
    
    @Test
    public void testShowOperAuthByOperId() {
    	
    	OperatorAuth operatorAuth = new OperatorAuth();
    	operatorAuth.setOperatorId(10000092);
    	
    	List<OperatorAuth> operAuthList = new ArrayList<>();
    	OperatorAuth operAuth1 = new OperatorAuth();
    	operAuth1.setResourceId(11000028);
    	OperatorAuth operAuth2 = new OperatorAuth();
    	operAuth2.setResourceId(11000029);
    	OperatorAuth operAuth3 = new OperatorAuth();
    	operAuth3.setResourceId(11000030);
    	operAuthList.add(operAuth1);
    	operAuthList.add(operAuth2);
    	operAuthList.add(operAuth3);
    	
    	when(operatorAuthDao.selectOperatorAuthByOperId(operatorAuth.getOperatorId())).thenReturn(operAuthList);

    	String expected = operatorAuthController.showOperAuthByOperId(operatorAuth);
    	
    	List<Integer> resourceIdList = new ArrayList<>();
    	for(OperatorAuth operAuth : operAuthList) {
    		resourceIdList.add(operAuth.getResourceId());
    	}
    	Gson gson = new Gson();
    	String actual = gson.toJson(resourceIdList);
    	
    	assertEquals(actual, expected);
    	
    }
    
    @Test
    public void testEditOperatorAuth() {
    	String operAuthJson = "{\"operatorId\":\"10000096\",\"selectedResource\":[10000028,10000029,10000030],\"modifyOperatorId\":10000050}";
    	operatorAuthController.editOperatorAuth(operAuthJson);
    }
        
    @Test
    public void testShowAnOperatorAuth() {
    	OperatorAuth operAuth = new OperatorAuth();
    	operAuth.setOperatorId(10000092);
    	operAuth.setResourceId(10000030);
    	
    	Map<String, Integer> operAuthMap = new HashMap<>();
    	operAuthMap.put("operatorId", 10000092);
    	operAuthMap.put("resourceId", 10000030);
    	when(operatorAuthDao.selectAnOperatorAuth(operAuthMap)).thenReturn(operAuth);
    	Gson gson = new Gson();
    	String actual = operatorAuthController.showAnOperatorAuth(operAuth);
    	String expected = gson.toJson(operAuth);
    	assertEquals(actual, expected);
    }
	
    @Test
    public void testRemoveOperatorAuth() {
    	
    	List<OperatorAuth> operAuthList = new ArrayList<>();
    	
    	OperatorAuth operAuth = new OperatorAuth();
    	operAuth.setOperatorId(10000096);
    	operAuth.setCreateTime(null);
    	operAuth.setResourceId(10000029);
    	operAuth.setStatus(0);
    	Integer[] resourceIdArray = new Integer[3];
    	resourceIdArray[0] = 10000028;
    	resourceIdArray[1] = 10000029;
    	resourceIdArray[2] = 10000030;
    	String operAuthJson = "{\"operatorId\":\"10000096\",\"resourceIdArray\":[10000028,10000029,10000030]}";
    	
    	when(operatorAuthDao.selectOperatorAuthByOperId(operAuth.getOperatorId())).thenReturn(operAuthList);
    	String actual = operatorAuthController.removeOperatorAuth(operAuthJson);
    	assertEquals(actual, JSONArray.toJSONString(operAuthList)); 
        	
    }
    	
}
