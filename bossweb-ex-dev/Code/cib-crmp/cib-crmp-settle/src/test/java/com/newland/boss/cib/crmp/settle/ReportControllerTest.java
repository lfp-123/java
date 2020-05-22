package com.newland.boss.cib.crmp.settle;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.newland.boss.cib.crmp.domain.QueryFeeListReq;
import com.newland.boss.cib.crmp.domain.QueryFeeListResp;
import com.newland.boss.cib.crmp.settle.common.AbstractControllerTestNGTest;
import com.newland.boss.cib.crmp.settle.controller.ReportController;
import com.newland.boss.cib.crmp.settle.dao.QueryFeeListDao;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;

import net.sf.jasperreports.engine.JRException;

public class ReportControllerTest extends AbstractControllerTestNGTest {

	@Autowired
	private ReportController reportController;

	@Autowired
	private QueryFeeListDao queryFeeListDao;

	@Autowired
	private HttpSession session;

	@Autowired
	private AppSession appSession;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Override
	protected Object getController() {
		// TODO Auto-generated method stub
		return reportController;
	}

	public String testModelJson() {
		return "{\"unitName\":[93],\"userName\":\"王五\",\"feeType\":\"1\",\"beginDate\":\"2018-05-09\",\"endDate\":\"2018-07-13\"}";
	}

	public QueryFeeListReq testModel() {
		QueryFeeListReq queryFeeListReq = new QueryFeeListReq();
		queryFeeListReq.setUserName("王五");
		queryFeeListReq.setFeeType("1");
		queryFeeListReq.setBeginDate("2018-05-09");
		queryFeeListReq.setEndDate("2018-07-13");
		return queryFeeListReq;
	}

	@Test
	public void testQueryFeeList() {

		List<QueryFeeListResp> feeList = new ArrayList<>();
		Integer[] orgIdArray = new Integer[2];

		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setOperatorLevel(i);
			when(appSession.getUser()).thenReturn(user);
		}

		when(queryFeeListDao.queryFeeListResult(testModel(), orgIdArray, 1, 10)).thenReturn(feeList);
		when(queryFeeListDao.queryFeeListCount(testModel(), orgIdArray)).thenReturn(0);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryFeeList(request, testModelJson());
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");

	}

	@Test
	public void testQueryFeeList1() {
		List<QueryFeeListResp> retList = new ArrayList<>();

		Gson gson = new Gson();
		String json = "{\"unitName\":\"[10,20,30]\",\"userName\":\"王五\",\"feeType\":\"1\"}";
		QueryFeeListReq queryFeeListReq = gson.fromJson(json, QueryFeeListReq.class);

		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setOperatorLevel(i);
			when(appSession.getUser()).thenReturn(user);
		}

		when(queryFeeListDao.queryFeeListResult(queryFeeListReq, reportController.jsonToIntegerArray(json), 1, 10))
				.thenReturn(retList);
		when(queryFeeListDao.queryFeeListCount(queryFeeListReq, reportController.jsonToIntegerArray(json)))
				.thenReturn(0);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryFeeList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");
	}

	@Test
	public void testQueryFeeList2() {
		List<QueryFeeListResp> retList = new ArrayList<>();

		Gson gson = new Gson();
		String json = "{\"unitName\":\"[10,20,30]\"}";
		QueryFeeListReq queryFeeListReq = gson.fromJson(json, QueryFeeListReq.class);

		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setOperatorLevel(i);
			when(appSession.getUser()).thenReturn(user);
		}

		when(queryFeeListDao.queryFeeListResult(queryFeeListReq, reportController.jsonToIntegerArray(json), 1, 10))
				.thenReturn(retList);
		when(queryFeeListDao.queryFeeListCount(queryFeeListReq, reportController.jsonToIntegerArray(json)))
				.thenReturn(0);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryFeeList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");
	}

	@Test
	public void testQueryFeeList3() {
		List<QueryFeeListResp> retList = new ArrayList<>();

		Gson gson = new Gson();
		String json = "{\"beginDate\":\"\",\"endDate\":\"\"}";
		QueryFeeListReq queryFeeListReq = gson.fromJson(json, QueryFeeListReq.class);

		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setOperatorLevel(i);
			when(appSession.getUser()).thenReturn(user);
		}

		when(queryFeeListDao.queryFeeListResult(queryFeeListReq, reportController.jsonToIntegerArray(json), 1, 10))
				.thenReturn(retList);
		when(queryFeeListDao.queryFeeListCount(queryFeeListReq, reportController.jsonToIntegerArray(json)))
				.thenReturn(0);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryFeeList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");
	}

	@Test
	public void queryDeptFeeListTest() {
		List<QueryFeeListResp> retList = new ArrayList<>();
		Gson gson = new Gson();
		String json = "{\"beginDate\":\"\",\"endDate\":\"201806\"}";
		QueryFeeListReq queryFeeListReq = gson.fromJson(json, QueryFeeListReq.class);
		when(queryFeeListDao.queryDeptFeeListResult(queryFeeListReq, reportController.jsonToIntegerArray(json), 1, 10))
				.thenReturn(retList);
		when(queryFeeListDao.queryDeptFeeListCount(queryFeeListReq, reportController.jsonToIntegerArray(json)))
				.thenReturn(0);
		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryDeptFeeList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");
	}

	@Test
	public void queryDeptFeeListTest1() {
		List<QueryFeeListResp> retList = new ArrayList<>();
		Gson gson = new Gson();
		String json = "{\"beginDate\":\"201806\",\"endDate\":\"\"}";
		QueryFeeListReq queryFeeListReq = gson.fromJson(json, QueryFeeListReq.class);
		when(queryFeeListDao.queryDeptFeeListResult(queryFeeListReq, reportController.jsonToIntegerArray(json), 1, 10))
				.thenReturn(retList);
		when(queryFeeListDao.queryDeptFeeListCount(queryFeeListReq, reportController.jsonToIntegerArray(json)))
				.thenReturn(0);
		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryDeptFeeList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");
	}

	@Test
	public void jsonToIntegerArrayTest() {
		String json = "{\"unitName\":[5385,5601],\"beginDate\":\"201807\",\"endDate\":\"201807\"}";
		reportController.jsonToIntegerArray(json);
	}

	@Test
	public void testQueryTopNList() {

		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\",\"topN\":10}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(queryFeeListDao.queryTopNListResult(reportController.jsonToIntegerArray(json),
				Integer.parseInt(req.getTopN()), null, null, 1, 10)).thenReturn(retList);
		when(queryFeeListDao.queryTopNListCount(reportController.jsonToIntegerArray(json),
				Integer.parseInt(req.getTopN()), "", "")).thenReturn(100);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryTopNList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");
	}

	@Test
	public void testQueryTopNList1() {

		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\"}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(queryFeeListDao.queryTopNListResult(reportController.jsonToIntegerArray(json), 10, null, null, 1, 10))
				.thenReturn(retList);
		when(queryFeeListDao.queryTopNListCount(reportController.jsonToIntegerArray(json), 10, null, null))
				.thenReturn(100);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryTopNList(request, json);
		assertEquals(jsonStr, "{\"cnt\":100,\"list\":[]}");
	}

	@Test
	public void testQueryThresholdList() {

		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\",\"thresholdValue\":200.0}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(queryFeeListDao.queryThresholdListResult(reportController.jsonToIntegerArray(json),
				Double.parseDouble(req.getThresholdValue()), "", "", 1, 10)).thenReturn(retList);
		when(queryFeeListDao.queryThresholdListCount(reportController.jsonToIntegerArray(json),
				Double.parseDouble(req.getThresholdValue()), "", "")).thenReturn(100);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryThresholdList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");

	}

	@Test
	public void testQueryThresholdList1() {

		List<QueryFeeListResp> retList = new ArrayList<>();
		String json = "{\"unitName\":\"[10,20,30]\"}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(queryFeeListDao.queryThresholdListResult(reportController.jsonToIntegerArray(json), 100.0, "", "", 1, 10))
				.thenReturn(retList);
		when(queryFeeListDao.queryThresholdListCount(reportController.jsonToIntegerArray(json), 100.0, "", ""))
				.thenReturn(100);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryThresholdList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");

	}

	@Test
	public void testQueryNotActiveUserList() {

		List<QueryFeeListResp> retList = new ArrayList<>();
		String json = "{\"unitName\":\"[10,20,30]\",\"thresholdValue\":200.0}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(queryFeeListDao.queryNotActiveUserListResult(reportController.jsonToIntegerArray(json),
				Double.parseDouble(req.getThresholdValue()), "", "", 1, 10)).thenReturn(retList);
		when(queryFeeListDao.queryNotActiveUserListCount(reportController.jsonToIntegerArray(json),
				Double.parseDouble(req.getThresholdValue()), "", "")).thenReturn(100);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryNotActiveUserList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");
	}

	@Test
	public void testQueryNotActiveUserList1() {

		List<QueryFeeListResp> retList = new ArrayList<>();
		String json = "{\"unitName\":\"[10,20,30]\"}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(queryFeeListDao.queryNotActiveUserListResult(reportController.jsonToIntegerArray(json), 0.0, "", "", 1,
				10)).thenReturn(retList);
		when(queryFeeListDao.queryNotActiveUserListCount(reportController.jsonToIntegerArray(json), 0.0, "", ""))
				.thenReturn(100);

		when(request.getParameter("page")).thenReturn("1");
		when(request.getParameter("size")).thenReturn("10");

		String jsonStr = reportController.queryNotActiveUserList(request, json);
		assertEquals(jsonStr, "{\"cnt\":0,\"list\":[]}");
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testPreviewReport() throws Exception {
		List<QueryFeeListResp> retList = new ArrayList<>();

		QueryFeeListReq req = new QueryFeeListReq();
		req.setBeginDate("");
		req.setEndDate("");

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		Gson gson = new Gson();
		String json = "{\"unitName\":\"[10,20,30]\",\"userName\":\"王五\",\"feeType\":\"1\"}";
		QueryFeeListReq queryFeeListReq = gson.fromJson(json, QueryFeeListReq.class);

		when(request.getParameter("businessType")).thenReturn("1");

		when(queryFeeListDao.queryFeeListAllResult(req, reportController.stringToIntegerArr(json))).thenReturn(retList);

		reportController.previewReport(request, response, session, json);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testPreviewReport1() throws Exception {

		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\",\"topN\":10}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(request.getParameter("businessType")).thenReturn("1");

		when(queryFeeListDao.queryFeeListAllResult(req, reportController.jsonToIntegerArray(json))).thenReturn(retList);

		reportController.previewReport(request, response, session, json);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testPreviewReport2() throws Exception {
		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\",\"topN\":10}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(request.getParameter("businessType")).thenReturn("2");

		when(queryFeeListDao.queryTopNListAllResult(reportController.jsonToIntegerArray(json),
				Integer.parseInt(req.getTopN()), "", "")).thenReturn(retList);

		reportController.previewReport(request, response, session, json);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testPreviewReport3() throws Exception {
		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\"}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(request.getParameter("businessType")).thenReturn("2");

		when(queryFeeListDao.queryTopNListAllResult(reportController.jsonToIntegerArray(json), 10, "", ""))
				.thenReturn(retList);

		reportController.previewReport(request, response, session, json);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testPreviewReport4() throws Exception {

		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\",\"thresholdValue\":200.0}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(request.getParameter("businessType")).thenReturn("3");

		when(queryFeeListDao.queryThresholdListAllResult(reportController.jsonToIntegerArray(json),
				Double.parseDouble(req.getThresholdValue()), "", "")).thenReturn(retList);

		reportController.previewReport(request, response, session, json);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testPreviewReport5() throws Exception {
		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\"}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(request.getParameter("businessType")).thenReturn("3");

		when(queryFeeListDao.queryThresholdListAllResult(reportController.jsonToIntegerArray(json), 0.0, "", ""))
				.thenReturn(retList);

		reportController.previewReport(request, response, session, json);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testPreviewReport6() throws Exception {

		List<QueryFeeListResp> retList = new ArrayList<>();

		for (int i = 0; i < 501; i++) {
			QueryFeeListResp resp = new QueryFeeListResp();
			resp.setFee("" + i);
			retList.add(resp);
		}

		String json = "{\"unitName\":\"[10,20,30]\"}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(request.getParameter("businessType")).thenReturn("4");

		when(queryFeeListDao.queryNotActiveUserListAllResult(reportController.jsonToIntegerArray(json), 0.0, "", ""))
				.thenReturn(retList);

		reportController.previewReport(request, response, session, json);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testPreviewReport7() throws Exception {

		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\",\"topN\":10}";
		Gson gson = new Gson();
		QueryFeeListReq req = gson.fromJson(json, QueryFeeListReq.class);

		when(request.getParameter("businessType")).thenReturn("5");

		when(queryFeeListDao.queryDeptFeeListAllResult(req, reportController.jsonToIntegerArray(json)))
				.thenReturn(retList);

		reportController.previewReport(request, response, session, json);
	}

	@Test(expectedExceptions = JRException.class)
	public void testPreviewReport8() throws Exception {

		String json = "{\"unitName\":\"[10,20,30]\",\"userName\":\"王五\",\"feeType\":\"1\"}";

		User user = new User();
		user.setOperatorLevel(3);
		when(appSession.getUser()).thenReturn(user);

		when(request.getParameter("businessType")).thenReturn("6");

		reportController.previewReport(request, response, session, json);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testExportExcel() throws Exception {

		List<QueryFeeListResp> retList = new ArrayList<>();

		QueryFeeListReq req = new QueryFeeListReq();
		req.setBeginDate("2018-06-01");
		req.setEndDate("2018-06-30");
		Gson gson = new Gson();
		String json = gson.toJson(req);

		when(request.getParameter("businessType")).thenReturn("1");
		when(request.getParameter("unitName")).thenReturn("a");
		when(request.getParameter("userName")).thenReturn("b");
		when(request.getParameter("feeType")).thenReturn("1");
		when(request.getParameter("beginDate")).thenReturn(null);
		when(request.getParameter("endDate")).thenReturn(null);

		when(queryFeeListDao.queryFeeListAllResult(req, reportController.stringToIntegerArr(json))).thenReturn(retList);

		reportController.exportExcel(request, response);

	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testExportExcel1() throws Exception {
		List<QueryFeeListResp> retList = new ArrayList<>();

		QueryFeeListReq req = new QueryFeeListReq();
		req.setBeginDate("2018-06-01");
		req.setEndDate("2018-06-30");
		Gson gson = new Gson();
		String json = gson.toJson(req);

		when(request.getParameter("businessType")).thenReturn("1");
		when(request.getParameter("unitName")).thenReturn("a");
		when(request.getParameter("userName")).thenReturn("b");
		when(request.getParameter("feeType")).thenReturn("1");
		when(request.getParameter("beginDate")).thenReturn("2018-06-01");
		when(request.getParameter("endDate")).thenReturn("2018-06-30");

		when(queryFeeListDao.queryFeeListAllResult(req, reportController.stringToIntegerArr(json))).thenReturn(retList);

		reportController.exportExcel(request, response);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testExportExcel2() throws Exception {

		List<QueryFeeListResp> retList = new ArrayList<>();

		String json = "{\"unitName\":\"[10,20,30]\",\"userName\":\"王五\",\"feeType\":\"1\",\"topN\":10}";

		when(request.getParameter("businessType")).thenReturn("2");
		when(request.getParameter("topN")).thenReturn("10");

		when(queryFeeListDao.queryTopNListAllResult(reportController.jsonToIntegerArray(json), 10, "", ""))
				.thenReturn(retList);

		reportController.exportExcel(request, response);

	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testExportExcel3() throws Exception {

		List<QueryFeeListResp> retList = new ArrayList<>();

		when(request.getParameter("businessType")).thenReturn("2");
		when(request.getParameter("topN")).thenReturn("a");

		String json = "{\"unitName\":\"[10,20,30]\",\"userName\":\"王五\",\"feeType\":\"1\",\"topN\":10}";

		when(queryFeeListDao.queryTopNListAllResult(reportController.jsonToIntegerArray(json), 10, "", ""))
				.thenReturn(retList);

		reportController.exportExcel(request, response);

	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testExportExcel4() throws Exception {
		List<QueryFeeListResp> retList = new ArrayList<>();

		QueryFeeListReq req = new QueryFeeListReq();
		req.setThresholdValue("100.0");
		Gson gson = new Gson();
		String json = gson.toJson(req);

		when(request.getParameter("businessType")).thenReturn("3");
		when(request.getParameter("thresholdValue")).thenReturn("100.0");

		when(queryFeeListDao.queryThresholdListAllResult(reportController.jsonToIntegerArray(json), 100.0D, "", ""))
				.thenReturn(retList);

		reportController.exportExcel(request, response);

	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testExportExcel5() throws Exception {
		List<QueryFeeListResp> retList = new ArrayList<>();

		QueryFeeListReq req = new QueryFeeListReq();
		Gson gson = new Gson();
		String json = gson.toJson(req);

		when(request.getParameter("businessType")).thenReturn("3");
		when(request.getParameter("thresholdValue")).thenReturn("a");

		when(queryFeeListDao.queryThresholdListAllResult(reportController.jsonToIntegerArray(json), 0D, "", ""))
				.thenReturn(retList);

		reportController.exportExcel(request, response);

	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testExportExcel6() throws Exception {

		List<QueryFeeListResp> retList = new ArrayList<>();

		QueryFeeListReq req = new QueryFeeListReq();
		Gson gson = new Gson();
		String json = gson.toJson(req);

		when(request.getParameter("businessType")).thenReturn("4");
		when(request.getParameter("unitName")).thenReturn("aa");
		when(request.getParameter("thresholdValue")).thenReturn("100.0");
		when(queryFeeListDao.queryNotActiveUserListAllResult(reportController.jsonToIntegerArray(json), 0.0, "", ""))
				.thenReturn(retList);

		reportController.exportExcel(request, response);

	}

	@Test(expectedExceptions = RuntimeException.class)
	public void downloadExcelTest() throws IOException {
		reportController.downloadExcel(request, response);
	}

	@Test
	public void batchQueryTest() {
		MultipartFile file = Mockito.mock(MultipartFile.class);

		when(file.getOriginalFilename()).thenReturn("test.tdxt");

		reportController.batchQuery(file);
	}

	@Test
	public void stringToIntegerArrTest() {
		String unitName = "";
		Integer[] orgIdArray;
		orgIdArray = reportController.stringToIntegerArr(unitName);
		for (int i = 0; i < orgIdArray.length; i++) {
			System.out.println(orgIdArray[i]);
		}
	}

}
