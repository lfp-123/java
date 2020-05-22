package com.newland.boss.cib.crmp.settle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.domain.QueryCallListReq;
import com.newland.boss.cib.crmp.domain.QueryCallListResp;
import com.newland.boss.cib.crmp.settle.dao.RatedCdrDao;
import com.newland.boss.cib.crmp.settle.service.QueryCallListService;
import com.newland.boss.kpi.admin.dao.OperatorDao;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.server.AppSession;
import com.newland.boss.kpi.server.DictDefService;

@RestController
public class CallListController {

	@Autowired
	private QueryCallListService queryCallListService;
	@Autowired
	private AppSession appSession;
	@Autowired
	private DictDefService dictDefService;
	@Autowired
	private RatedCdrDao ratedCdrDao;
	@Autowired
	private OperatorDao operatorDao;

	@RequestMapping(value = "/queryCdrList", produces = "text/json;charset=UTF-8")
	public String queryCdrList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("1");
		return queryCallList(req, queryCallListReq);
	}

	@RequestMapping(value = "/queryDmaList", produces = "text/json;charset=UTF-8")
	public String queryDmaList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("2");
		return queryCallList(req, queryCallListReq);
	}

	@RequestMapping(value = "/queryNetList", produces = "text/json;charset=UTF-8")
	public String queryNetList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("3");
		return queryCallList(req, queryCallListReq);
	}

	@RequestMapping(value = "/queryCallList", produces = "text/json;charset=UTF-8")
	public String queryCallList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		initRequest(queryCallListReq);

		int page = Integer.parseInt(req.getParameter("page"));
		int size = Integer.parseInt(req.getParameter("size"));
//		List<QueryCallListResp> callList = queryCallListService.queryCallListResult(queryCallListReq, page, size);
//		int cnt = queryCallListService.queryCallListCount(queryCallListReq);
		Map<String, Object> map = queryCallListService.queryCallListResult(queryCallListReq, page, size);
//		map.put("list", callList);
//		map.put("cnt", cnt);
		return JSONArray.toJSONString(map);
	}

	/**
	 * 初始化请求信息
	 * 
	 * @param queryCallListReq
	 */
	private void initRequest(QueryCallListReq queryCallListReq) {
		queryCallListReq.setOperatorLevel(appSession.getUser().getOperatorLevel());
		queryCallListReq.setUserOperatorId(appSession.getUser().getOperatorId());
		queryCallListReq.setUserLoginName(ratedCdrDao.queryLoginName(appSession.getUser().getOperatorId()));
		queryCallListReq.setOrgId(appSession.getUser().getOrgId());

		if (!"".equals(queryCallListReq.getRealCallTypes()) && queryCallListReq.getRealCallTypes() != null) {
			queryCallListReq.setCallTypes(queryCallListReq.getRealCallTypes().split(","));
		}

		if (queryCallListReq.getOperatorName() != null) {
			queryCallListReq.setOperatorName(queryCallListReq.getOperatorName().replaceAll("%", "/%")
					.replaceAll("_", "/_"));
		}
		
		if ("2".equals(queryCallListReq.getResourceType())) {
			if (queryCallListReq.getLoginName() != null && !("".equals(queryCallListReq.getLoginName()))) {
				Operator operator = operatorDao.findByLoginName(queryCallListReq.getLoginName());
				if (operator != null) {
					queryCallListReq.setOperatorId(String.valueOf(operator.getOperatorId()));
				} else {
					queryCallListReq.setOperatorId("-2");
				}
			}
		}
		
		queryCallListReq.setStartBillMonth(queryCallListReq.getBeginDate().replace("-", "").substring(0, 6));
		queryCallListReq.setEndBillMonth(queryCallListReq.getEndDate().replace("-", "").substring(0, 6));
		
	}

	@RequestMapping(value = "/createCallListExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String callListQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createCallListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/createCdrListExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String cdrListQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("1");
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createCallListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/createDmaListExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String dmaListQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("2");
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createCallListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/createNetListExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String netListQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("3");
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createCallListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/queryBlackCallList", produces = "text/json;charset=UTF-8")
	public String queryBlackCallList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		int page = Integer.parseInt(req.getParameter("page"));
		int size = Integer.parseInt(req.getParameter("size"));
		initRequest(queryCallListReq);
		Map<String, Object> map = queryCallListService.queryBlackCallListResult(queryCallListReq, page, size);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/queryBlackCdrList", produces = "text/json;charset=UTF-8")
	public String queryBlackCdrList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("1");
		initForBlackCdr(queryCallListReq);
		return queryBlackCallList(req, queryCallListReq);
	}

	/**
	 * 黑户查询设置默认查询呼叫类型为主叫
	 * 
	 * @param queryCallListReq
	 */
	private void initForBlackCdr(QueryCallListReq queryCallListReq) {
		if ("".equals(queryCallListReq.getCallType())) {
			queryCallListReq.setCallType("1");
			List<DictDef> dicList = dictDefService.getDictDefByDictClass(1021);
			for (DictDef dictDef : dicList) {
				if (dictDef.getEntryId() == 1) {
					queryCallListReq.setRealCallTypes(dictDef.getEntryDesc());
				}
			}
		}
	}

	@RequestMapping(value = "/queryBlackDmaList", produces = "text/json;charset=UTF-8")
	public String queryBlackDmaList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("2");
		return queryBlackCallList(req, queryCallListReq);
	}

	@RequestMapping(value = "/queryBlackNetList", produces = "text/json;charset=UTF-8")
	public String queryBlackNetList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("3");
		return queryBlackCallList(req, queryCallListReq);
	}

	@RequestMapping(value = "/createBlackCallListExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String blackCallListQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		initForBlackCdr(queryCallListReq);
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createBlackCallListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/createBlackCdrListExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String blackCdrListQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("1");
		initForBlackCdr(queryCallListReq);
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createBlackCallListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/createBlackDmaListExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String blackDmaListQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("2");
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createBlackCallListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/createBlackNetListExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String blackNetListQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("3");
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createBlackCallListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	public String queryBlackUserList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		int page = Integer.parseInt(req.getParameter("page"));
		int size = Integer.parseInt(req.getParameter("size"));
		initRequest(queryCallListReq);
		Map<String, Object> map = queryCallListService.queryBlackUserListResult(queryCallListReq, page, size);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/queryCdrBlackUserList", produces = "text/json;charset=UTF-8")
	public String queryCdrBlackUserList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("1");
		initForBlackCdr(queryCallListReq);
		return queryBlackUserList(req, queryCallListReq);
	}

	@RequestMapping(value = "/queryDmaBlackUserList", produces = "text/json;charset=UTF-8")
	public String queryDmaBlackUserList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("2");
		return queryBlackUserList(req, queryCallListReq);
	}

	@RequestMapping(value = "/queryNetrBlackUserList", produces = "text/json;charset=UTF-8")
	public String queryNetBlackUserList(HttpServletRequest req, @RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("3");
		return queryBlackUserList(req, queryCallListReq);
	}

	@RequestMapping(value = "/createCdrBlackUserExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String blackCdrUserQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("1");
		initForBlackCdr(queryCallListReq);
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createBlackUserListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/createDmaBlackUserExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String blackDmaUserQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("2");
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createBlackUserListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/createNetBlackUserExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String blackNetUserQueryForDownload(@RequestBody QueryCallListReq queryCallListReq) {
		queryCallListReq.setResourceType("3");
		initRequest(queryCallListReq);
		Map<String, String> map = queryCallListService.createBlackUserListExcel(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/editUserInfo", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editUserInfo(@RequestBody QueryCallListReq queryCallListReq) {
		Map<String, String> map = queryCallListService.editUserInfo(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/editUserInfoForDetail", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editUserInfoForDetail(@RequestBody QueryCallListReq queryCallListReq) {
		Map<String, String> map = queryCallListService.editUserInfoForDetail(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/editOrgInfo", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String editOrgInfo(@RequestBody QueryCallListReq queryCallListReq) {
		Map<String, String> map = queryCallListService.editOrgInfo(queryCallListReq);
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/addInsuranceFee", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String addInsuranceFee(@RequestBody QueryCallListReq queryCallListReq) {
		Map<String, String> map = queryCallListService.addInsuranceFee(queryCallListReq);
		return JSONArray.toJSONString(map);
	}
}
