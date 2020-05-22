package com.newland.boss.cib.crmp.settle.service.impl;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newland.boss.cib.crmp.common.JxlTool;
import com.newland.boss.cib.crmp.dao.RateRuleDao;
import com.newland.boss.cib.crmp.domain.QueryCallListReq;
import com.newland.boss.cib.crmp.domain.QueryCallListResp;
import com.newland.boss.cib.crmp.domain.RateRule;
import com.newland.boss.cib.crmp.domain.RatedCdr;
import com.newland.boss.cib.crmp.domain.RatedDma;
import com.newland.boss.cib.crmp.domain.RatedNetwork;
import com.newland.boss.cib.crmp.domain.SummingSettleTask;
import com.newland.boss.cib.crmp.settle.dao.RatedCdrDao;
import com.newland.boss.cib.crmp.settle.dao.RatedDmaDao;
import com.newland.boss.cib.crmp.settle.dao.RatedNetworkDao;
import com.newland.boss.cib.crmp.settle.service.QueryCallListService;
import com.newland.boss.kpi.admin.dao.OperatorDao;
import com.newland.boss.kpi.admin.model.Operator;

@Component
public class QueryCallListServiceImpl implements QueryCallListService {

	private static final Logger LOGGER = LogManager.getLogger(QueryCallListServiceImpl.class);

	@Autowired
	private RatedCdrDao ratedCdrDao;
	@Autowired
	private RatedDmaDao ratedDmaDao;
	@Autowired
	private RatedNetworkDao ratedNetworkDao;
	@Autowired
	private OperatorDao operatorDao;
	@Autowired
	private RateRuleDao rateRuleDao;

	private static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FULL_DATE_FORMAT);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static final String FILE_NAME = "fileName";
	private static final String PATH = System.getProperty("user.dir") + "/downloadTemp/";
	private static final String FEE = "费用/(元)";
	private static final String LOGIN_NAME = "notes名";
	private static final String ERROR = "error";
	private static final String SUCCESS = "success";
	private static final String STATUS = "status";

	@Override
	public Map<String, Object> queryCallListResult(QueryCallListReq req, int page, int size) {
		List<QueryCallListResp> callList = new ArrayList<QueryCallListResp>();
		long total = 0l;
		String resourceType = req.getResourceType();
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.clearPage();
		PageHelper.startPage(page, size);

		if ("1".equals(resourceType)) {
			List<RatedCdr> originList = ratedCdrDao.queryCdrCallListResult(req);
			PageInfo<RatedCdr> pageInfo = new PageInfo<>(originList);
			total = pageInfo.getTotal();
			List<RatedCdr> list = pageInfo.getList();
			cdrListToRespList(callList, list, req.getCallType());
		} else if ("2".equals(resourceType)) {
			List<RatedDma> originList = ratedDmaDao.queryDmaCallListResult(req);
			PageInfo<RatedDma> pageInfo = new PageInfo<>(originList);
			total = pageInfo.getTotal();
			List<RatedDma> list = pageInfo.getList();
			dmaListToRespList(callList, list);
		} else if ("3".equals(resourceType)) {
			List<RatedNetwork> originList = ratedNetworkDao.queryNetworkCallListResult(req);
			PageInfo<RatedNetwork> pageInfo = new PageInfo<>(originList);
			total = pageInfo.getTotal();
			List<RatedNetwork> list = pageInfo.getList();
			networkListToRespList(callList, list);
		}
		map.put("list", callList);
		map.put("cnt", total);
		return map;
	}

//	@Override
//	public int queryCallListCount(QueryCallListReq req) {
//		String resourceType = req.getResourceType();
//		int count = 0;
//		if ("1".equals(resourceType)) {
//			count = ratedCdrDao.queryCdrCallListCount(req);
//		} else if ("2".equals(resourceType)) {
//			count = ratedDmaDao.queryDmaCallListCount(req);
//		} else if ("3".equals(resourceType)) {
//			count = ratedNetworkDao.queryNetworkCallListCount(req);
//		}
//		return count;
//	}

	@Override
	public Map<String, String> createCallListExcel(QueryCallListReq req) {
		Map<String, String> map = new HashMap<String, String>();
		String resourceType = req.getResourceType();
		if ("1".equals(resourceType)) {
			map = createCdrCallListExcel(req);
		} else if ("2".equals(resourceType)) {
			if (req.getLoginName() != null && !("".equals(req.getLoginName()))) {
				Operator operator = operatorDao.findByLoginName(req.getLoginName());
				if (operator != null) {
					req.setOperatorId(String.valueOf(operator.getOperatorId()));
				} else {
					req.setOperatorId("-2");
				}
			}
			map = createDmaCallListExcel(req);
		} else if ("3".equals(resourceType)) {
			List<RatedNetwork> list = ratedNetworkDao.queryNetworkCallListResult(req);
			map = createNetworkExcel(list, "网络专线话单"+dateFormat.format(new Date())+".xls");
		}
		return map;
	}

	private Map<String, String> createDmaCallListExcel(QueryCallListReq req) {
		Map<String, String> map;
		List<RatedDma> list = ratedDmaDao.queryDmaCallListResult(req);

		String[] columns = { LOGIN_NAME, "姓名", "单位名称", "方数", "会议号", "会议起始时间", "会议结束时间", "时长/(时)", FEE, "保障费/(元)" };
		boolean[] numberFlag = { false, false, false, false, false, false, false, false, true, true };
		List<String[]> dataList = new ArrayList<String[]>();
		for (RatedDma r : list) {
			double holdingTime = (double) r.getHoldingTime() / (60 * 60);
			String[] data = { String.valueOf(r.getUserId()), String.valueOf(r.getOperatorName()),
					String.valueOf(r.getOrgName()),
					String.valueOf(r.getPartCount()), String.valueOf(r.getRoomId()),
					simpleDateFormat.format(r.getStartTime()), simpleDateFormat.format(r.getEndTime()),
					String.valueOf((long) Math.ceil(holdingTime)),
					String.valueOf((double) r.getFee() / 1000),
					String.valueOf((double) r.getOtherFee() / 1000) };
			dataList.add(data);
		}
		map = createExcel("dma话单"+dateFormat.format(new Date())+".xls", columns, dataList, numberFlag);
		return map;
	}

	private Map<String, String> createCdrCallListExcel(QueryCallListReq req) {
		Map<String, String> map;
		List<RatedCdr> list = ratedCdrDao.queryCdrCallListResult(req);
		List<String[]> dataList = new ArrayList<String[]>();
		String[] columns = { LOGIN_NAME, "姓名", "单位名称", "主叫号码", "被叫号码", "话单类型", "通话起始时间", "通话结束时间", "时长/(分)", FEE };
		boolean[] numberFlag = { false, false, false, false, false, false, false, false, true, true };// 设置第9、10个字段为数字类型
		for (RatedCdr r : list) {
			double holdingTime = (double) r.getHoldingTime() / 60;
			String startTime = "";
			String endTime = "";
			if (r.getEndTime() != null && r.getStartTime() != null) {
				startTime = simpleDateFormat.format(r.getStartTime());
				endTime = simpleDateFormat.format(r.getEndTime());
			}
			String[] data = { String.valueOf(r.getUserId()), String.valueOf(r.getOperatorName()),
					String.valueOf(r.getOrgName()), String.valueOf(r.getCallNumber()),
					String.valueOf(r.getCalledNumber()), r.getCallTypeName(), startTime, endTime,
					String.valueOf((long) Math.ceil(holdingTime)), String.valueOf((double) r.getFee() / 1000) };
			dataList.add(data);
		}
		map = createExcel("cdr话单"+dateFormat.format(new Date())+".xls", columns, dataList, numberFlag);
		return map;
	}

	@Override
	public Map<String, Object> queryBlackCallListResult(QueryCallListReq req, int page, int size) {
		List<QueryCallListResp> callList = new ArrayList<QueryCallListResp>();
		String resourceType = req.getResourceType();
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.clearPage();
		PageHelper.startPage(page, size);
		long total = 0l;
		if ("1".equals(resourceType)) {
			// cdr
			List<RatedCdr> originList = ratedCdrDao.queryCdrBlackCallListResult(req);
			PageInfo<RatedCdr> pageInfo = new PageInfo<>(originList);
			total = pageInfo.getTotal();
			List<RatedCdr> list = pageInfo.getList();
			cdrListToRespList(callList, list, req.getCallType());
		} else if ("2".equals(resourceType)) {
			// dma
			List<RatedDma> originList = ratedDmaDao.queryDmaBlackCallListResult(req);
			PageInfo<RatedDma> pageInfo = new PageInfo<>(originList);
			total = pageInfo.getTotal();
			List<RatedDma> list = pageInfo.getList();
			dmaListToRespList(callList, list);
		} else if ("3".equals(resourceType)) {
			// network
			List<RatedNetwork> originList = ratedNetworkDao.queryNetworkBlackCallListResult(req);
			PageInfo<RatedNetwork> pageInfo = new PageInfo<>(originList);
			total = pageInfo.getTotal();
			List<RatedNetwork> list = pageInfo.getList();
			networkListToRespList(callList, list);
		}
		map.put("list", callList);
		map.put("cnt", total);
		return map;
	}

//	@Override
//	public int queryBlackCallListCount(QueryCallListReq req) {
//		String resourceType = req.getResourceType();
//		int count = 0;
//		if ("1".equals(resourceType)) {
//			count = ratedCdrDao.queryCdrBlackCallListCount(req);
//		} else if ("2".equals(resourceType)) {
//			count = ratedDmaDao.queryDmaBlackCallListCount(req);
//		} else if ("3".equals(resourceType)) {
//			count = ratedNetworkDao.queryNetworkBlackCallListCount(req);
//		}
//		return count;
//	}

	@Override
	public Map<String, String> createBlackCallListExcel(QueryCallListReq req) {
		Map<String, String> map = new HashMap<String, String>();
		String resourceType = req.getResourceType();
		if ("1".equals(resourceType)) {
			List<RatedCdr> list = ratedCdrDao.queryCdrBlackCallListResult(req);
			List<String[]> dataList = new ArrayList<String[]>();
			String[] columns = { "主叫号码", "被叫号码", "话单类型", "通话起始时间", "通话结束时间", "时长/(分)", FEE };
			boolean[] numberFlag = { false, false, false, false, false, true, true };
			for (RatedCdr r : list) {
				double holdingTime = (double) r.getHoldingTime() / 60;
				String startTime = "";
				String endTime = "";
				if (r.getEndTime() != null && r.getStartTime() != null) {
					startTime = simpleDateFormat.format(r.getStartTime());
					endTime = simpleDateFormat.format(r.getEndTime());
				}
				String[] data = { String.valueOf(r.getCallNumber()),
						String.valueOf(r.getCalledNumber()), r.getCallTypeName(), startTime, endTime,
						String.valueOf((long) Math.ceil(holdingTime)), String.valueOf((double) r.getFee() / 1000) };
				dataList.add(data);
			}
			map = createExcel("cdr黑户话单"+dateFormat.format(new Date())+".xls", columns, dataList, numberFlag);
		} else if ("2".equals(resourceType)) {
			List<RatedDma> list = ratedDmaDao.queryDmaBlackCallListResult(req);

			String[] columns = { LOGIN_NAME, "方数", "会议号", "会议起始时间", "会议结束时间", "时长/(时)", FEE, "保障费/(元)" };
			boolean[] numberFlag = { false, false, false, false, false, true, true, true };
			List<String[]> dataList = new ArrayList<String[]>();
			for (RatedDma r : list) {
				double holdingTime = (double) r.getHoldingTime() / (60 * 60);
				String[] data = { String.valueOf(r.getUserId()),
						String.valueOf(r.getPartCount()), String.valueOf(r.getRoomId()),
						simpleDateFormat.format(r.getStartTime()), simpleDateFormat.format(r.getEndTime()),
						String.valueOf((long) Math.ceil(holdingTime)),
						String.valueOf((double) r.getFee() / 1000),
						String.valueOf((double) r.getOtherFee() / 1000)};
				dataList.add(data);
			}
			map = createExcel("dma黑户话单"+dateFormat.format(new Date())+".xls", columns, dataList,numberFlag);
		} else if ("3".equals(resourceType)) {
			List<RatedNetwork> list = ratedNetworkDao.queryNetworkBlackCallListResult(req);
			map = createNetworkExcel(list, "网络专线黑户话单"+dateFormat.format(new Date())+".xls");
		}
		return map;
	}

	public void cdrListToRespList(List<QueryCallListResp> callList, List<RatedCdr> list, String callTypeId) {
		for (RatedCdr ratedCdr : list) {
			QueryCallListResp callListResp = new QueryCallListResp();
			if (ratedCdr.getEndTime() != null && ratedCdr.getStartTime() != null) {
				double holdingTime = (double) ratedCdr.getHoldingTime() / 60;// 单位min
				callListResp.setStartTime(simpleDateFormat.format(ratedCdr.getStartTime()));
				callListResp.setEndTime(simpleDateFormat.format(ratedCdr.getEndTime()));
				callListResp.setHoldingTime(String.valueOf((long) Math.ceil(holdingTime)));
			}
			callListResp.setCallNumber(String.valueOf(ratedCdr.getCallNumber()));
			callListResp.setCalledNumber(String.valueOf(ratedCdr.getCalledNumber()));
			callListResp.setRegion(String.valueOf(ratedCdr.getRegion()));
			callListResp.setFee(String.valueOf((double) ratedCdr.getFee() / 1000));
			callListResp.setOperatorId(String.valueOf(ratedCdr.getUserId()));
			callListResp.setOperatorName(String.valueOf(ratedCdr.getOperatorName()));
			callListResp.setOrgName(String.valueOf(ratedCdr.getOrgName()));
			callListResp.setCount(String.valueOf(ratedCdr.getCount()));
			callListResp.setBillMonth(String.valueOf(ratedCdr.getBillMonth()));
			callListResp.setCallType(ratedCdr.getCallTypeName());
			callListResp.setCdrId(String.valueOf(ratedCdr.getCdrId()));
			if (!"".equals(callTypeId)) {
				callListResp.setCallTypeId(callTypeId);
			}

			callList.add(callListResp);
		}
	}

	public void dmaListToRespList(List<QueryCallListResp> callList, List<RatedDma> list) {
		for (RatedDma ratedDma : list) {
			QueryCallListResp callListResp = new QueryCallListResp();
			if (ratedDma.getEndTime() != null && ratedDma.getStartTime() != null) {
				double holdingTime = (double) ratedDma.getHoldingTime() / (60 * 60);// 单位h
				callListResp.setStartTime(simpleDateFormat.format(ratedDma.getStartTime()));
				callListResp.setEndTime(simpleDateFormat.format(ratedDma.getEndTime()));
				callListResp.setHoldingTime(String.valueOf((long) Math.ceil(holdingTime)));
			}
			callListResp.setOperatorId(String.valueOf(ratedDma.getUserId()));
			callListResp.setIp(String.valueOf(ratedDma.getIp()));
			callListResp.setTerminalName(String.valueOf(ratedDma.getTerminalName()));
			callListResp.setConferenceNumber(String.valueOf(ratedDma.getRoomId()));
			callListResp.setPartCount(String.valueOf(ratedDma.getPartCount()));
			callListResp.setFee(String.valueOf((double) ratedDma.getFee() / 1000));
			callListResp.setCount(String.valueOf(ratedDma.getCount()));
			callListResp.setBillMonth(String.valueOf(ratedDma.getBillMonth()));
			callListResp.setOrgId(String.valueOf(ratedDma.getOrgId()));
			callListResp.setOrgName(String.valueOf(ratedDma.getOrgName()));
			callListResp.setOperatorName(String.valueOf(ratedDma.getOperatorName()));
			callListResp.setConfUuid(String.valueOf(ratedDma.getConfUuid()));
			callListResp.setOtherFee(String.valueOf((double) ratedDma.getOtherFee() / 1000));
			callList.add(callListResp);
		}
	}

	public void networkListToRespList(List<QueryCallListResp> callList, List<RatedNetwork> list) {
		for (RatedNetwork ratedNetwork : list) {
			QueryCallListResp callListResp = new QueryCallListResp();
			callListResp.setOrgName(ratedNetwork.getOrgName());
			callListResp.setBillMonth(String.valueOf(ratedNetwork.getBillMonth()));
			callListResp.setFee(String.valueOf((double) ratedNetwork.getFee() / 1000));
			callListResp.setFeeType(ratedNetwork.getFeeType());
			callList.add(callListResp);
		}
	}

	private Map<String, String> createExcel(String fileName, String[] columns,
			List<String[]> dataList, boolean[] numberFlag) {
		Map<String, String> map = new HashMap<String, String>();
		String path = PATH + fileName;

		try {
			map.put(FILE_NAME, URLEncoder.encode(fileName , "UTF-8"));
			JxlTool.exportExcel(path, columns, dataList, numberFlag);
		} catch (Exception e) {
			LOGGER.error("exportExcel err.", e);
			map.put(STATUS, ERROR);
			return map;
		}
		map.put(STATUS, SUCCESS);
		return map;
	}

	private Map<String, String> createNetworkExcel(List<RatedNetwork> list, String fileName) {
		Map<String, String> map = new HashMap<String, String>();
		String path = PATH + fileName;
		String[] columns = { "单位名称", "月份", FEE };
		boolean[] numberFlag = { false, false, true };
		List<String[]> dataList = new ArrayList<String[]>();
		for (RatedNetwork r : list) {
			String[] data = { String.valueOf(r.getOrgName()),
					String.valueOf(r.getBillMonth()), String.valueOf((double) r.getFee() / 1000) };
			dataList.add(data);
		}
		try {
			map.put(FILE_NAME, URLEncoder.encode(fileName , "UTF-8"));
			JxlTool.exportExcel(path, columns, dataList,numberFlag);
		} catch (Exception e) {
			LOGGER.error("Network exportExcel err.", e);
			map.put(STATUS, ERROR);
			return map;
		}
		map.put(STATUS, SUCCESS);
		return map;
	}

	@Override
	public Map<String, Object> queryBlackUserListResult(QueryCallListReq req, int page, int size) {
		List<QueryCallListResp> callList = new ArrayList<QueryCallListResp>();
		String resourceType = req.getResourceType();
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.clearPage();
		PageHelper.startPage(page, size);
		long total = 0l;
		if ("1".equals(resourceType)) {
			List<RatedCdr> originList = ratedCdrDao.queryCdrBlackUsersResult(req);
			PageInfo<RatedCdr> pageInfo = new PageInfo<>(originList);
			total = pageInfo.getTotal();
			List<RatedCdr> list = pageInfo.getList();
			cdrListToRespList(callList, list, req.getCallType());
		} else if ("2".equals(resourceType)) {
			List<RatedDma> originList = ratedDmaDao.queryDmaBlackUsersResult(req);
			PageInfo<RatedDma> pageInfo = new PageInfo<>(originList);
			total = pageInfo.getTotal();
			List<RatedDma> list = pageInfo.getList();
			dmaListToRespList(callList, list);
		} else if ("3".equals(resourceType)) {
			List<RatedNetwork> originList = ratedNetworkDao.queryNetBlackUsersResult(req);
			PageInfo<RatedNetwork> pageInfo = new PageInfo<>(originList);
			total = pageInfo.getTotal();
			List<RatedNetwork> list = pageInfo.getList();
			networkListToRespList(callList, list);
		}
		map.put("list", callList);
		map.put("cnt", total);
		return map;
	}

//	@Override
//	public int queryBlackUserListCount(QueryCallListReq req) {
//		String resourceType = req.getResourceType();
//		int count = 0;
//		if ("1".equals(resourceType)) {
//			count = ratedCdrDao.queryCdrBlackUsersCount(req);
//		} else if ("2".equals(resourceType)) {
//			count = ratedDmaDao.queryDmaBlackUsersCount(req);
//		} else if ("3".equals(resourceType)) {
//			count = ratedNetworkDao.queryNetBlackUsersListCount(req);
//		}
//		return count;
//	}

	@Override
	public Map<String, String> createBlackUserListExcel(QueryCallListReq req) {
		Map<String, String> map = new HashMap<String, String>();
		String resourceType = req.getResourceType();
		if ("1".equals(resourceType)) {
			List<RatedCdr> list = ratedCdrDao.queryCdrBlackUsersResult(req);
			List<String[]> dataList = new ArrayList<String[]>();
			String[] columns = { "号码", "话单类型", "话单数", FEE, "归属月份" };
			boolean[] numberFlag = { false, false, true, true, false };
			for (RatedCdr r : list) {
				String[] data = { String.valueOf(r.getCallNumber()), r.getCallTypeName(), String.valueOf(r.getCount()),
						String.valueOf((double) r.getFee() / 1000), String.valueOf(r.getBillMonth()) };
				dataList.add(data);
			}

			map = createExcel("cdr黑户用户"+dateFormat.format(new Date())+".xls", columns, dataList, numberFlag);
		} else if ("2".equals(resourceType)) {
			List<RatedDma> list = ratedDmaDao.queryDmaBlackUsersResult(req);

			String[] columns = { "会议号", "话单数", FEE, "归属月份" };
			boolean[] numberFlag = { false, true, true, false };
			List<String[]> dataList = new ArrayList<String[]>();
			for (RatedDma r : list) {
				String[] data = { String.valueOf(r.getRoomId()), String.valueOf(r.getCount()),
						String.valueOf((double) r.getFee() / 1000), String.valueOf(r.getBillMonth()) };
				dataList.add(data);
			}
			map = createExcel("dma黑户用户"+dateFormat.format(new Date())+".xls", columns, dataList, numberFlag);
		} else if ("3".equals(resourceType)) {
			List<RatedNetwork> list = ratedNetworkDao.queryNetBlackUsersResult(req);
			map = createNetworkExcel(list, "网络专线黑户用户"+dateFormat.format(new Date())+".xls");
		}
		return map;
	}

	@Override
	public Map<String, String> editUserInfo(QueryCallListReq req) {
		Map<String, String> map = new HashMap<String, String>();
		// 根据用户名和部门查询该部门下是否有该用户
		String userName = req.getOperatorName();
		int orgId = req.getOrgId();
		int operatorId = 0;
		String loginName = "";
		List<Operator> operators = operatorDao.selectIdAndNameByOrgId(orgId);
		for (Operator operator : operators) {
			if (userName.equals(operator.getOperatorName())) {
				operatorId = operator.getOperatorId();
				loginName = operator.getLoginName();
				break;
			}
		}
		if (operatorId == 0 && "".equals(loginName)) {
			map.put(STATUS, ERROR);
			map.put("info", "更新失败,该机构下不存在“" + userName + "”用户，请确认！");
		} else {
			req.setLoginName(loginName);
			req.setOperatorId(String.valueOf(operatorId));
			ratedCdrDao.updateUserInfo(req);// update by callNumber和billmonth
			insertSummingSettleTask(Integer.parseInt(req.getBillMonth()), 1);
			map.put(STATUS, SUCCESS);
			map.put("info", "黑户用户编辑成功");
		}
		return map;
	}

	@Override
	public Map<String, String> editUserInfoForDetail(QueryCallListReq req) {
		// 根据用户名和部门查询该部门下是否有该用户
		String userName = req.getOperatorName();
		int orgId = req.getOrgId();
		List<Operator> operators = operatorDao.selectIdAndNameByOrgId(orgId);
		int operatorId = 0;
		String loginName = "";
		for (Operator operator : operators) {
			if (userName.equals(operator.getOperatorName())) {
				operatorId = operator.getOperatorId();
				loginName = operator.getLoginName();
				break;
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		if (operatorId == 0 && "".equals(loginName)) {
			map.put(STATUS, ERROR);
			map.put("info", "更新失败,该机构下不存在“" + userName + "”用户，请确认！");
		} else {
			req.setLoginName(loginName);
			req.setOperatorId(String.valueOf(operatorId));
			ratedCdrDao.updateUserInfoForDetail(req);
			insertSummingSettleTask(Integer.parseInt(req.getBillMonth()), 1);
			map.put(STATUS, SUCCESS);
			map.put("info", "黑户用户编辑成功");
		}
		return map;
	}

	private void insertSummingSettleTask(int billMonth, int cdrType) {
		SummingSettleTask summingSettleTask = new SummingSettleTask();
		summingSettleTask.setBillMonth(billMonth);
		summingSettleTask.setCdrType(cdrType);
		summingSettleTask.setTaskType(1);
		summingSettleTask.setTaskSrc(1);
		summingSettleTask.setCreateTime(new Date());
		ratedCdrDao.insertSummingSettleTask(summingSettleTask);
	}

	@Override
	public Map<String, String> editOrgInfo(QueryCallListReq req) {
		ratedDmaDao.updateOrgInfo(req);
		insertSummingSettleTask(Integer.parseInt(req.getBillMonth()), 3);
		Map<String, String> map = new HashMap<String, String>();
		map.put(STATUS, SUCCESS);
		map.put("info", "编辑成功");
		return map;
	}
	
	@Override
	public Map<String, String> addInsuranceFee(QueryCallListReq req) {
		Map<String, String> map = new HashMap<String, String>();
		// 查询保障费 如果未找到资费需要进行提示“未找到设备保障费资费，请在资费配置中添加”
		// 计算费用 计算设备保障费费用（设备保障费=设备保障费标准*视频总时长）
		if (req.isAddFlag()) {
			List<RateRule> rules = getRateRule(req);
			if (rules.isEmpty()) {
				map.put(STATUS, ERROR);
				map.put("info", "未找到设备保障费资费，请在资费配置中添加！");
			} else {
				long fee = rules.get(0).getOtherFee() * req.getHoldingTime();
				req.setFee(fee);
				ratedDmaDao.addInsuranceFee(req);
				ratedDmaDao.updateOrgInfoForDetail(req);
				insertSummingSettleTask(Integer.parseInt(req.getBillMonth()), 3);
				map.put(STATUS, SUCCESS);
				map.put("info", "编辑成功");
			}
		} else {
			req.setFee(0l);
			ratedDmaDao.addInsuranceFee(req);
			ratedDmaDao.updateOrgInfoForDetail(req);
			insertSummingSettleTask(Integer.parseInt(req.getBillMonth()), 3);
			map.put(STATUS, SUCCESS);
			map.put("info", "编辑成功");
		}
		return map;
	}

	private List<RateRule> getRateRule(QueryCallListReq req) {
		//先看资费分配表里有没有个性化，有个性化取个性化的
		RateRule rateRule = new RateRule();
		rateRule.setOrgId(req.getOrgId());
		rateRule.setInureDate(req.getStartTime());
		rateRule.setExpireDate(req.getEndTime());
		List<RateRule> rules = rateRuleDao.searchPersonalInsuranceRule(rateRule);
		if(rules.isEmpty()){
			rules = rateRuleDao.searchInsuranceRule(rateRule);
		}
		return rules;
	}
}
