package com.newland.boss.cib.crmp.settle;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.domain.QueryCallListReq;
import com.newland.boss.cib.crmp.domain.QueryCallListResp;
import com.newland.boss.cib.crmp.domain.RatedCdr;
import com.newland.boss.cib.crmp.domain.RatedDma;
import com.newland.boss.cib.crmp.domain.RatedNetwork;
import com.newland.boss.cib.crmp.settle.common.AbstractControllerTestNGTest;
import com.newland.boss.cib.crmp.settle.controller.CallListController;
import com.newland.boss.cib.crmp.settle.dao.RatedCdrDao;
import com.newland.boss.cib.crmp.settle.dao.RatedDmaDao;
import com.newland.boss.cib.crmp.settle.dao.RatedNetworkDao;
import com.newland.boss.cib.crmp.settle.service.impl.QueryCallListServiceImpl;
import com.newland.boss.kpi.admin.dao.OperatorDao;
import com.newland.boss.kpi.dao.DictDefDao;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;
import com.newland.boss.kpi.server.DictDefService;

public class CallListControllerTest extends AbstractControllerTestNGTest {
	
	@Autowired
    private CallListController callListController;
	@Autowired
	private QueryCallListServiceImpl queryCallListServiceImpl;
	@Autowired
	private AppSession appSession;
	@Autowired
	private RatedCdrDao ratedCdrDao;
	@Autowired
	private RatedDmaDao ratedDmaDao;
	@Autowired
	private RatedNetworkDao ratedNetworkDao;
	@Autowired
	private OperatorDao operatorDao;
	@Autowired
	private DictDefService dictDefService;
	@Autowired
    private DictDefDao dictDefDao;
	
    @Test
    public void testQueryCallList() throws Exception {
    	HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
    	when(req.getParameter("page")).thenReturn("1");
    	when(req.getParameter("size")).thenReturn("10");
    	User user = new User();
    	user.setOperatorId(100001);
    	when(appSession.getUser()).thenReturn(user);
    	DictDef dictdef = new DictDef();
    	dictdef.setEntryId(2);
    	dictdef.setEntryDesc("9");
    	dictdef.setEntryName("被叫");
    	List<DictDef> dicList = new ArrayList<DictDef>();
    	dicList.add(dictdef);
    	when(dictDefDao.selectAllDictDef()).thenReturn(dicList);
    	
    	List<RatedCdr> cdrList = new ArrayList<RatedCdr>();
    	RatedCdr ratedCdr = new RatedCdr();
    	ratedCdr.setCdrId("1");
    	ratedCdr.setOrgId(1011);
    	ratedCdr.setOrgName("XXX机构");
    	ratedCdr.setOperatorId(100001);
    	ratedCdr.setBillMonth(201801);
    	ratedCdr.setCdrType(1);
    	ratedCdr.setRegion(591);
    	ratedCdr.setCallNumber("18059685968");
    	ratedCdr.setCalledNumber("18359165916");
    	ratedCdr.setCallType("9");
    	ratedCdr.setStartTime(new Date());
    	ratedCdr.setEndTime(new Date());
    	ratedCdr.setFee(100L);
    	cdrList.add(ratedCdr);
    	List<RatedDma> dmaList = new ArrayList<RatedDma>();
    	RatedDma ratedDma = new RatedDma();
    	ratedDma.setUserId("100001");
    	ratedDma.setIp("10.1.1.1");
    	ratedDma.setConferenceNumber(1);
    	ratedDma.setTerminalName("name");
    	ratedDma.setStartTime(new Date());
    	ratedDma.setEndTime(new Date());
    	dmaList.add(ratedDma);
    	List<RatedNetwork> networkList = new ArrayList<RatedNetwork>();
    	RatedNetwork ratedNetwork = new RatedNetwork();
    	ratedNetwork.setCdrId("1");
    	ratedNetwork.setOrgId(1011);
    	ratedNetwork.setOrgName("XXX机构");
    	ratedNetwork.setOperatorId(100001);
    	ratedNetwork.setBillMonth(201801);
    	ratedNetwork.setCdrType(1);
    	ratedNetwork.setRegion(591);
    	ratedNetwork.setCallNumber(18059685968L);
    	ratedNetwork.setCalledNumber(18359165916L);
    	ratedNetwork.setStartTime(new Date());
    	ratedNetwork.setEndTime(new Date());
    	ratedNetwork.setFee(100L);
    	ratedNetwork.setNetResource("net");
    	networkList.add(ratedNetwork);

    	QueryCallListReq queryCallListReq = new QueryCallListReq();
    	queryCallListReq.setBeginDate("2018-08-08");
    	queryCallListReq.setEndDate("2018-08-08");
    	
    	// cdr话单（黑户话单）查询下载
    	when(ratedCdrDao.queryCdrCallListResult(queryCallListReq)).thenReturn(cdrList);
    	when(ratedCdrDao.queryCdrBlackCallListResult(queryCallListReq)).thenReturn(cdrList);
    	String cdrResult = callListController.queryCdrList(req, queryCallListReq);
    	callListController.callListQueryForDownload(queryCallListReq);
    	callListController.cdrListQueryForDownload(queryCallListReq);
		String blackCdr = callListController.queryBlackCdrList(req, queryCallListReq);
		callListController.blackCallListQueryForDownload(queryCallListReq);
		callListController.blackCdrListQueryForDownload(queryCallListReq);
    	Map<String, Object> cdrMap = new HashMap<String, Object>();
    	List<QueryCallListResp> cdrRespList = new ArrayList<QueryCallListResp>();
    	queryCallListServiceImpl.cdrListToRespList(cdrRespList, cdrList, "");
    	cdrMap.put("list", cdrRespList);
    	cdrMap.put("cnt", 1);
		assertEquals(cdrResult, JSONArray.toJSONString(cdrMap));
		assertEquals(blackCdr, JSONArray.toJSONString(cdrMap));
		Map<String, String> map = new HashMap<String, String>();
		// 黑户查询下载
		when(ratedCdrDao.queryCdrBlackUsersResult(queryCallListReq)).thenReturn(cdrList);
		callListController.queryCdrBlackUserList(req, queryCallListReq);
		callListController.blackCdrUserQueryForDownload(queryCallListReq);
		
		// dma话单（黑户话单）查询下载
		when(ratedDmaDao.queryDmaCallListResult(queryCallListReq)).thenReturn(dmaList);
    	when(ratedDmaDao.queryDmaBlackCallListResult(queryCallListReq)).thenReturn(dmaList);
    	String DmaResult = callListController.queryDmaList(req, queryCallListReq);
    	callListController.dmaListQueryForDownload(queryCallListReq);
    	String blackDma = callListController.queryBlackDmaList(req, queryCallListReq);
    	callListController.blackDmaListQueryForDownload(queryCallListReq);
    	Map<String, Object> dmaMap = new HashMap<String, Object>();
    	List<QueryCallListResp> dmaRespList = new ArrayList<QueryCallListResp>();
    	new QueryCallListServiceImpl().dmaListToRespList(dmaRespList, dmaList);
    	dmaMap.put("list", dmaRespList);
    	dmaMap.put("cnt", 1);
		assertEquals(DmaResult, JSONArray.toJSONString(dmaMap));
		assertEquals(blackDma, JSONArray.toJSONString(dmaMap));
		// 黑户查询下载
		when(ratedDmaDao.queryDmaBlackUsersResult(queryCallListReq)).thenReturn(dmaList);
		String blackDmaUser = callListController.queryDmaBlackUserList(req, queryCallListReq);
		assertEquals(blackDmaUser, JSONArray.toJSONString(dmaMap));
		callListController.blackDmaUserQueryForDownload(queryCallListReq);
		
		// 网络专线话单（黑户话单）查询下载
		when(ratedNetworkDao.queryNetworkCallListResult(queryCallListReq)).thenReturn(networkList);
    	when(ratedNetworkDao.queryNetworkBlackCallListResult(queryCallListReq)).thenReturn(networkList);
    	String networkResult = callListController.queryNetList(req, queryCallListReq);
    	callListController.netListQueryForDownload(queryCallListReq);
    	String blackNet = callListController.queryBlackNetList(req, queryCallListReq);
    	callListController.blackNetListQueryForDownload(queryCallListReq);
    	Map<String, Object> networkMap = new HashMap<String, Object>();
    	List<QueryCallListResp> networkRespList = new ArrayList<QueryCallListResp>();
    	new QueryCallListServiceImpl().networkListToRespList(networkRespList, networkList);
    	networkMap.put("list", networkRespList);
    	networkMap.put("cnt", 1);
		assertEquals(networkResult, JSONArray.toJSONString(networkMap));
		assertEquals(blackNet, JSONArray.toJSONString(networkMap));
		// 黑户查询下载
		when(ratedNetworkDao.queryNetBlackUsersResult(queryCallListReq)).thenReturn(networkList);
		String blackNetUser = callListController.queryNetBlackUserList(req, queryCallListReq);
		assertEquals(blackNetUser, JSONArray.toJSONString(networkMap));
		callListController.blackNetUserQueryForDownload(queryCallListReq);
		
		queryCallListReq.setOrgId(1);
		queryCallListReq.setBillMonth("201808");
		queryCallListReq.setAddFlag(true);
		callListController.editOrgInfo(queryCallListReq);
		callListController.editUserInfo(queryCallListReq);
		callListController.editUserInfoForDetail(queryCallListReq);
		callListController.addInsuranceFee(queryCallListReq);
    }

	@Override
	protected Object getController() {
		return callListController;
	}
}