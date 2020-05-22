package com.newland.boss.cib.crmp.settle.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newland.boss.cib.crmp.common.JxlTool;
import com.newland.boss.cib.crmp.domain.QueryCallListReq;
import com.newland.boss.cib.crmp.domain.QueryFeeListReq;
import com.newland.boss.cib.crmp.domain.QueryFeeListResp;
import com.newland.boss.cib.crmp.domain.QueryReportList;
import com.newland.boss.cib.crmp.domain.RatedCdr;
import com.newland.boss.cib.crmp.settle.service.QueryCallListService;
import com.newland.boss.cib.crmp.settle.service.QueryFeeListService;
import com.newland.boss.kpi.admin.dao.OrganizationDao;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.admin.service.OperatorService;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AppSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RestController
public class ReportController {

    private static final Logger LOGGER = LogManager.getLogger(ReportController.class);

    @Autowired
    private QueryFeeListService queryFeeListService;
    @Autowired
    private AppSession appSession;
    @Autowired
    private QueryCallListService queryCallListService;
    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private AppSession appsession;
    @Autowired
    private OperatorService operatorService;

    private static final String TITLE = "title";
    private static final String OPERATOR_NAME = "operatorName";
    private static final String ERR_CODE = "errCode";
    private static final String ERR_MSG = "errMsg";
    private static final String FILE_NAME = "fileName";
    private static final String JASPER_PATH = "/WEB-INF/jasperFiles/queryFeeListReport.jasper";
    private static final String JASPER_PATH1 = "/WEB-INF/jasperFiles/queryFeeListReport1.jasper";
    private static final String JASPER_PATH2 = "/WEB-INF/jasperFiles/queryFeeListReport2.jasper";
    private static final String NO_CACHE = "No-cache";
    private static final String UNIT_NAME = "unitName";
    private static final String USER_NAME = "userName";
    private static final String BEGIN_DATE = "beginDate";
    private static final String END_DATE = "endDate";
    private static final String FEE_TYPE = "feeType";
    private static final String CDR_TYPE = "cdrType";
    private static final String THRESHOLD_VALUE = "thresholdValue";
    private static final String TMP = "tmp = ";
    private static final String TMP1 = "tmp1 = ";
    private static final String TMP2 = "tmp2 = ";
    private static final String NOTES_ID = "notesId";
    private static final String TOP_N = "topN";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String IMPORT_MONTH = "importMonth";
    private static final String OPERATOR_ID = "operatorId";
    private static final String DOWNLOAD_PATH = "/home/oracle/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/reports/";

    /**
     * 解析传过来的机构编号数组
     *
     * @param json
     * @return
     */
    public Integer[] jsonToIntegerArray(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(json);
        JsonObject jsonObj = jsonEl.getAsJsonObject();
        JsonArray orgIdJsonArray;
        try {
            orgIdJsonArray = jsonObj.get(UNIT_NAME).getAsJsonArray();
        } catch (Exception e) {
            orgIdJsonArray = null;
        }
        if (null != orgIdJsonArray) {
            Integer[] orgIdArray = new Integer[orgIdJsonArray.size()];
            for (int i = 0; i < orgIdJsonArray.size(); i++) {
                Integer orgId = orgIdJsonArray.get(i).getAsInt();
                orgIdArray[i] = orgId;
            }
            return orgIdArray;
        } else {
            return new Integer[0];
        }
    }

    /**
     * 转化Json的字段为值
     */
    public String formatJsonToValue(String json, String queryFeeListReqJson) {
        String value = null;
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(queryFeeListReqJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();

        try {
            value = jsonObj.get(json).getAsString();
            if (value.isEmpty()) {
                value = null;
            }
        } catch (Exception e) {
            value = null;
        }

        return value;
    }

    /**
     * 数据处理
     *
     * @param feeList
     * @return
     */
    public List<QueryFeeListResp> listToRespList(List<QueryFeeListResp> feeList) {

        for (int i = 0; i < feeList.size(); i++) {
            // 费用保留一位小数
            String fee = String.format("%.1f", Double.parseDouble(feeList.get(i).getFee()) / 1000);
            feeList.get(i).setFee(fee);

            // 服务单价
            if (null != feeList.get(i).getRateFee()) {
                if (null != feeList.get(i).getOtherFee() && (!feeList.get(i).getOtherFee().equals("0"))) {
                    feeList.get(i).setRateFee(feeList.get(i).getOtherFee());
                }
                String rateFee = String.format("%.1f", Double.parseDouble(feeList.get(i).getRateFee()) / 1000);
                feeList.get(i).setRateFee(rateFee);
            }

            // 服务单位
            // cdr
            if (null != feeList.get(i).getCdrType() && feeList.get(i).getCdrType().equals("1")) {
                if (null != feeList.get(i).getFeeType() && feeList.get(i).getFeeType().equals("1")) {
                    feeList.get(i).setServerUnit("元/分钟");
                }
                if (null != feeList.get(i).getFeeType() && feeList.get(i).getFeeType().equals("3")) {
                    feeList.get(i).setServerUnit("元/号/月");
                }
                if (null != feeList.get(i).getFeeType() && feeList.get(i).getFeeType().equals("6")) {
                    feeList.get(i).setServerUnit("元/年");
                }
                if (null != feeList.get(i).getFeeType() && feeList.get(i).getFeeType().equals("8")) {
                    feeList.get(i).setServerUnit("元/号/月");
                }
                if (null != feeList.get(i).getFeeType() && feeList.get(i).getFeeType().equals("10")) {
                    feeList.get(i).setServerUnit("次");
                }
            }

            // dma
            if (null != feeList.get(i).getCdrType() && feeList.get(i).getCdrType().equals("3")) { // cdr
                if (null != feeList.get(i).getFeeType() && feeList.get(i).getFeeType().equals("2")) {
                    feeList.get(i).setServerUnit("元/终端/小时");
                }
                if (null != feeList.get(i).getFeeType() && feeList.get(i).getFeeType().equals("9")) {
                    feeList.get(i).setServerUnit("元/人/小时");
                }
            }

            // 网络专线
            if (null != feeList.get(i).getCdrType() && feeList.get(i).getCdrType().equals("4")) {
                feeList.get(i).setServerUnit("条");
            }

        }
        return feeList;
    }

    /**
     * 转化为报表展示的值
     */
    public List<QueryReportList> toReportList(List<QueryFeeListResp> feeList) {

        List<QueryReportList> reportList = new ArrayList<>();

        for (QueryFeeListResp resp : feeList) {

            QueryReportList report = new QueryReportList();
            report.setFeeTypeName(resp.getFeeTypeName());
            report.setUserName(resp.getUserName());
            report.setMonth(resp.getMonth());
            report.setNotesId(resp.getNotesId());
            report.setOrgNameFull(resp.getOrgNameFull());
            report.setServerUnit(resp.getServerUnit());
            report.setCallTime(Integer.parseInt(resp.getCallTime()));
            report.setFee(Float.parseFloat(resp.getFee()));

            if (StringUtils.isNotEmpty(resp.getCdrType())) {
                report.setCdrType(resp.getCdrType());
            }

            if (StringUtils.isNotEmpty(resp.getFeeType())) {
                report.setFeeType(resp.getFeeType());
            }

            if (null != resp.getRateFee()) {
                report.setRateFee(Float.parseFloat(resp.getRateFee()));
            }
            report.setTotalMinutes(resp.getTotalMinutes());

            // 服务数量
            if (null != report.getRateFee() && report.getRateFee() != 0.0) {
                report.setCallTime((int) Math.round(report.getFee() / report.getRateFee()));
            } else {
                report.setCallTime(Integer.parseInt(resp.getCallTime()));
            }

            reportList.add(report);

        }

        return reportList;

    }

    /**
     * 资源使用情况查询
     *
     * @param req
     * @param queryFeeListReq
     * @return
     */
    @RequestMapping(value = "/queryFeeList", produces = "text/json;charset=UTF-8")
    public String queryFeeList(HttpServletRequest req, @RequestBody String queryFeeListReqJson) {

        User user = appsession.getUser();

        String userName = null;
        String feeType = null;
        String beginDate = null;
        String endDate = null;
        String notesId = null;
        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));
        Integer[] orgIdArray = jsonToIntegerArray(queryFeeListReqJson);

        feeType = formatJsonToValue(FEE_TYPE, queryFeeListReqJson);
        beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
        endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);
        userName = formatJsonToValue(USER_NAME, queryFeeListReqJson);
        notesId = formatJsonToValue(NOTES_ID, queryFeeListReqJson);

        QueryFeeListReq queryFeeListReq = new QueryFeeListReq();
        queryFeeListReq.setFeeTypeName(feeType);
        queryFeeListReq.setUserName(userName);
        queryFeeListReq.setNotesId(notesId);
        queryFeeListReq.setOperatorLevel(user.getOperatorLevel());
        queryFeeListReq.setOrgId(user.getOrgId());
        queryFeeListReq.setOperatorId(user.getOperatorId());

        if (null != beginDate && beginDate.equals("")) {
            queryFeeListReq.setBeginDate(null);
        } else {
            queryFeeListReq.setBeginDate(beginDate);
        }
        if (null != endDate && endDate.equals("")) {
            queryFeeListReq.setEndDate(null);
        } else {
            queryFeeListReq.setEndDate(endDate);
        }

        List<QueryFeeListResp> feeList = queryFeeListService.queryFeeListResult(queryFeeListReq, orgIdArray, page,
                size);
        feeList = listToRespList(feeList);
        int cnt = queryFeeListService.queryFeeListCount(queryFeeListReq, orgIdArray);

        Map<String, Object> map = new HashMap<>();
        map.put("list", feeList);
        map.put("cnt", cnt);
        return JSONArray.toJSONString(map);
    }

    /**
     * 部门资源使用情况查询
     */
    @RequestMapping(value = "/queryDeptFeeList", produces = "text/json;charset=UTF-8")
    public String queryDeptFeeList(HttpServletRequest req, @RequestBody String queryFeeListReqJson) {

        String userName = null;
        String feeType = null;
        String beginDate = null;
        String endDate = null;
        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));
        Integer[] orgIdArray = jsonToIntegerArray(queryFeeListReqJson);

        User user = appsession.getUser();

        feeType = formatJsonToValue(FEE_TYPE, queryFeeListReqJson);
        beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
        endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);
        userName = formatJsonToValue(USER_NAME, queryFeeListReqJson);

        QueryFeeListReq queryFeeListReq = new QueryFeeListReq();
        queryFeeListReq.setFeeTypeName(feeType);
        queryFeeListReq.setOperatorLevel(user.getOperatorLevel());
        queryFeeListReq.setUserName(userName);
        queryFeeListReq.setOrgId(user.getOrgId());

        if (null != beginDate && beginDate.equals("")) {
            queryFeeListReq.setBeginDate(null);
            queryFeeListReq.setEndDate(null);
        } else {
            queryFeeListReq.setBeginDate(beginDate);
            queryFeeListReq.setEndDate(beginDate);
        }

        List<QueryFeeListResp> feeList = queryFeeListService.queryDeptFeeListResult(queryFeeListReq, orgIdArray, page,
                size);
        feeList = listToRespList(feeList);
        List<QueryReportList> reportList = toReportList(feeList);
        int cnt = queryFeeListService.queryDeptFeeListCount(queryFeeListReq, orgIdArray);

        Map<String, Object> map = new HashMap<>();
        map.put("list", reportList);
        map.put("cnt", cnt);
        return JSONArray.toJSONString(map);
    }

    /**
     * TOP N查询
     *
     * @param request
     * @param queryFeeListReq
     * @return
     */
    @RequestMapping(value = "/queryTopNList", produces = "text/json;charset=UTF-8")
    public String queryTopNList(HttpServletRequest request, @RequestBody String queryFeeListReqJson) {

        String topN = null;
        String beginDate = null;
        String endDate = null;
        Integer[] orgIdArray = jsonToIntegerArray(queryFeeListReqJson);
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));

        topN = formatJsonToValue(TOP_N, queryFeeListReqJson);
        beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
        endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);

        try {
            int tmp = Integer.parseInt(topN);
            LOGGER.debug(TMP + tmp);
        } catch (Exception e) {
            LOGGER.error("parseInt topN err.", e);
            topN = "10";
        }

        List<QueryFeeListResp> feeList = queryFeeListService.queryTopNListResult(orgIdArray, Integer.parseInt(topN),
                beginDate, endDate, page, size);
        feeList = listToRespList(feeList);
        int cnt = queryFeeListService.queryTopNListCount(orgIdArray, Integer.parseInt(topN), beginDate, endDate);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", feeList);
        map.put("cnt", cnt);
        return JSONArray.toJSONString(map);
    }

    /**
     * 超阀值查询
     *
     * @param request
     * @param queryFeeListReq
     * @return
     */
    @RequestMapping(value = "/queryThresholdList", produces = "text/json;charset=UTF-8")
    public String queryThresholdList(HttpServletRequest request, @RequestBody String queryFeeListReqJson) {

        Integer[] orgIdArray = jsonToIntegerArray(queryFeeListReqJson);
        String thresholdValue = null;
        String beginDate = null;
        String endDate = null;
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));

        thresholdValue = formatJsonToValue(THRESHOLD_VALUE, queryFeeListReqJson);
        beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
        endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);

        try {
            double tmp = Double.parseDouble(thresholdValue);
            LOGGER.debug(TMP + tmp);
        } catch (Exception e) {
            LOGGER.error("parseDouble thresholdValue err.", e);
            thresholdValue = "100";
        }

        List<QueryFeeListResp> feeList = queryFeeListService.queryThresholdListResult(orgIdArray,
                Double.parseDouble(thresholdValue), beginDate, endDate, page, size);
        feeList = listToRespList(feeList);
        int cnt = queryFeeListService.queryThresholdListCount(orgIdArray, Double.parseDouble(thresholdValue), beginDate,
                endDate);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", feeList);
        map.put("cnt", cnt);
        return JSONArray.toJSONString(map);

    }

    /**
     * 非活跃用户查询
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryNotActiveUserList", produces = "text/json;charset=UTF-8")
    public String queryNotActiveUserList(HttpServletRequest request, @RequestBody String queryFeeListReqJson) {

        Integer[] orgIdArray = jsonToIntegerArray(queryFeeListReqJson);
        String thresholdValue = null;
        String beginDate = null;
        String endDate = null;
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));

        thresholdValue = formatJsonToValue(THRESHOLD_VALUE, queryFeeListReqJson);
        beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
        endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);

        try {
            double tmp = Double.parseDouble(thresholdValue);
            LOGGER.debug(TMP + tmp);
        } catch (Exception e) {
            LOGGER.error("parseDouble thresholdValue err.", e);
            thresholdValue = "0";
        }

        List<QueryFeeListResp> feeList = queryFeeListService.queryNotActiveUserListResult(orgIdArray,
                Double.parseDouble(thresholdValue), beginDate, endDate, page, size);
        feeList = listToRespList(feeList);
        int cnt = queryFeeListService.queryNotActiveUserListCount(orgIdArray, Double.parseDouble(thresholdValue),
                beginDate, endDate);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", feeList);
        map.put("cnt", cnt);
        return JSONArray.toJSONString(map);

    }

    /**
     * 预览报表 businessType: 1:资源使用情况查询 2:TOP N查询 3:超阀值查询 4:非活跃用户查询 5:部门资源使用情况报表查询
     *
     * @param request
     * @param queryFeeListReq
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/previewReport")
    public void previewReport(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
                              @RequestBody String queryFeeListReqJson) throws JRException, IOException {

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonEl = jsonParser.parse(queryFeeListReqJson);
        JsonObject jsonObj = jsonEl.getAsJsonObject();

        String businessType = request.getParameter("businessType");
        List<QueryFeeListResp> feeList = null;
        List<QueryReportList> reportList = null;

        User user = appsession.getUser();

        String userName = null;
        String feeType = null;
        String beginDate = null;
        String endDate = null;
        String notesId = null;
        Integer[] orgIdArray = jsonToIntegerArray(queryFeeListReqJson);
        String topN = null;
        String thresholdValue;
        String fileName;
        QueryFeeListReq queryFeeListReq = new QueryFeeListReq();
        String importMonth = "";

        switch (businessType) {
            case "1":

                feeType = formatJsonToValue(FEE_TYPE, queryFeeListReqJson);
                beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
                endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);
                userName = formatJsonToValue(USER_NAME, queryFeeListReqJson);
                notesId = formatJsonToValue(NOTES_ID, queryFeeListReqJson);

                queryFeeListReq.setFeeTypeName(feeType);
                queryFeeListReq.setBeginDate(beginDate);
                queryFeeListReq.setEndDate(endDate);
                queryFeeListReq.setNotesId(notesId);
                queryFeeListReq.setUserName(userName);
                queryFeeListReq.setOperatorLevel(user.getOperatorLevel());
                queryFeeListReq.setOrgId(user.getOrgId());
                queryFeeListReq.setOperatorId(user.getOperatorId());

                feeList = queryFeeListService.queryFeeListResult(queryFeeListReq, orgIdArray, 1, 500);
                reportList = toReportList(listToRespList(feeList));
                fileName = "资源使用情况查询报表";
                break;

            case "2":

                topN = formatJsonToValue(TOP_N, queryFeeListReqJson);
                beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
                endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);

                try {
                    int tmp = Integer.parseInt(topN);
                    LOGGER.debug(TMP1 + tmp);
                } catch (Exception e) {
                    LOGGER.error("parseInt topN err1.", e);
                    topN = "10";
                }

                feeList = queryFeeListService.queryTopNListResult(orgIdArray, Integer.parseInt(topN), beginDate, endDate, 1,
                        500);
                reportList = toReportList(listToRespList(feeList));
                fileName = "TOP N查询报表";
                break;

            case "3":

                thresholdValue = formatJsonToValue(THRESHOLD_VALUE, queryFeeListReqJson);
                beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
                endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);

                try {
                    double tmp = Double.parseDouble(thresholdValue);
                    LOGGER.debug(TMP1 + tmp);
                } catch (Exception e) {
                    LOGGER.error("parseDouble thresholdValue err1.", e);
                    thresholdValue = "100";
                }

                feeList = queryFeeListService.queryThresholdListResult(orgIdArray, Double.parseDouble(thresholdValue),
                        beginDate, endDate, 1, 500);
                reportList = toReportList(listToRespList(feeList));
                fileName = "超阀值查询报表";
                break;

            case "4":

                thresholdValue = formatJsonToValue(THRESHOLD_VALUE, queryFeeListReqJson);
                beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
                endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);

                if (thresholdValue == null || thresholdValue.equals("")) {
                    thresholdValue = "0";
                }

                feeList = queryFeeListService.queryNotActiveUserListResult(orgIdArray, Double.parseDouble(thresholdValue),
                        beginDate, endDate, 1, 500);
                reportList = toReportList(listToRespList(feeList));
                fileName = "非活跃用户报表";
                break;

            case "5":

                feeType = formatJsonToValue(FEE_TYPE, queryFeeListReqJson);
                beginDate = formatJsonToValue(BEGIN_DATE, queryFeeListReqJson);
                endDate = formatJsonToValue(END_DATE, queryFeeListReqJson);

                queryFeeListReq.setFeeTypeName(feeType);
                queryFeeListReq.setBeginDate(beginDate);
                queryFeeListReq.setEndDate(beginDate);
                queryFeeListReq.setOrgId(user.getOrgId());
                queryFeeListReq.setOperatorLevel(user.getOperatorLevel());

                feeList = queryFeeListService.queryDeptFeeListResult(queryFeeListReq, orgIdArray, 1, 500);
                reportList = toReportList(listToRespList(feeList));
                fileName = "部门资源使用情况查询报表";
                break;
            default:
                throw new JRException("业务类型无法识别");
        }

        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();

        URL url = servletContext.getResource("/");
        String weblogicPath = url.getPath().replaceAll("%20", " ");

        String jasperPath = "";
        if (businessType.equals("2") || businessType.equals("3") || businessType.equals("4")) {
            jasperPath = weblogicPath + JASPER_PATH2;
        } else if (businessType.equals("5")) {
            jasperPath = weblogicPath + JASPER_PATH1;
        } else {
            jasperPath = weblogicPath + JASPER_PATH;
        }

        if (!new File(jasperPath).exists()) {
            jasperPath = jasperPath.replaceFirst("/", "");
        }

        String destFileName = servletContext.getRealPath("/") + "pages" + File.separator + "queryFeeListReport.html";
        destFileName = weblogicPath + "pages" + "/" + "queryFeeListReport.html";

        JRDataSource dataSource = new JRBeanCollectionDataSource(reportList, true);
        Map<String, Object> params = new HashMap<String, Object>();

        if (StringUtils.isNotEmpty(beginDate)) {
            importMonth = beginDate.substring(0, 4) + "年" + beginDate.substring(4) + "月";
        }

        params.put(IMPORT_MONTH, importMonth);
        params.put(TITLE, fileName);
        params.put(OPERATOR_NAME, appSession.getUser().getOperatorName());
        JasperRunManager.runReportToHtmlFile(jasperPath, destFileName, params, dataSource);

    }

    /**
     * 导出报表
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        List<QueryFeeListResp> feeList = getFeeList(request);
        List<QueryReportList> reportList = new ArrayList<>();
        reportList = toReportList(listToRespList(feeList));
        String fileName = (String) request.getAttribute(FILE_NAME);
        String importMonth = (String) request.getAttribute(IMPORT_MONTH);
        String businessType = request.getParameter("businessType");
        String jasperPath = "";

        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();

        URL url = servletContext.getResource("/");
        String weblogicPath = url.getPath().replaceAll("%20", " ");

        if (businessType.equals("2") || businessType.equals("3") || businessType.equals("4")) {
            jasperPath = weblogicPath + JASPER_PATH2;
        } else if (businessType.equals("5")) {
            jasperPath = weblogicPath + JASPER_PATH1;
        } else {
            jasperPath = weblogicPath + JASPER_PATH;
        }

        if (!new File(jasperPath).exists()) {
            jasperPath = jasperPath.replaceFirst("/", "");
        }

        JRDataSource dataSource = new JRBeanCollectionDataSource(reportList, true);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(TITLE, fileName);
        params.put(OPERATOR_NAME, appSession.getUser().getOperatorName());
        params.put(IMPORT_MONTH, importMonth);
        JasperPrint print = JasperFillManager.fillReport(jasperPath, params, dataSource);

        response.setHeader("Pragma", NO_CACHE);
        response.setHeader("Cache-Control", NO_CACHE);
        response.setDateHeader("Expires", -1);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition",
                "attachment;filename=" + new String((fileName + ".xlsx").getBytes("GBK"), "ISO-8859-1"));

        SimpleXlsxReportConfiguration conf = new SimpleXlsxReportConfiguration();
        conf.setWhitePageBackground(false);
        conf.setDetectCellType(true);

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setConfiguration(conf);

        // 输入
        ExporterInput exporterInput = new SimpleExporterInput(print);
        exporter.setExporterInput(exporterInput);

        // 输出
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(response.getOutputStream());
        exporter.setExporterOutput(exporterOutput);

        // 生成文件
        if (!(DOWNLOAD_PATH.contains("base_domain") || DOWNLOAD_PATH.contains("cib")
                || DOWNLOAD_PATH.contains("eclipse"))) {
            throw new Exception("该路径不合法");
        }
        File file = new File(DOWNLOAD_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        File reportFile = new File(DOWNLOAD_PATH + fileName + ".xlsx");

        FileOutputStream fileOutputStream = new FileOutputStream(reportFile);
        // 输出到文件
        OutputStreamExporterOutput exporterOutputfile = new SimpleOutputStreamExporterOutput(fileOutputStream);
        exporter.setExporterOutput(exporterOutputfile);

        // 导出报表
        exporter.exportReport();

        exporterOutput.close();

    }

    /**
     * 把接收到的unitName转换为orgId数组
     */
    public Integer[] stringToIntegerArr(String unitName) {
        if (StringUtils.isNotEmpty(unitName)) {

            String[] unitArray = unitName.split(",");
            List<Integer> orgIdList = new ArrayList<>();
            for (String orgName : unitArray) {
                if (orgName.trim().isEmpty()) {
                    continue;
                } else {
                    orgIdList.add(Integer.parseInt(orgName));
                }
            }
            Integer[] orgIdArray = new Integer[orgIdList.size()];
            return orgIdList.toArray(orgIdArray);
        } else {
            return new Integer[0];
        }
    }

    /**
     * 转化Request的字段为值
     */
    public String formatRequestToValue(String json, HttpServletRequest request) {
        String value = "";
        try {
            value = request.getParameter(json);
            if (value.isEmpty()) {
                value = null;
            }
        } catch (Exception e) {
            value = null;
        }
        return value;
    }

    /**
     * businessType: 1:资源使用情况查询 2:TOP N查询 3:超阀值查询 4:非活跃用户查询 5:部门资源情况使用报表查询
     *
     * @param request
     * @return
     */
    private List<QueryFeeListResp> getFeeList(HttpServletRequest request) throws JRException {

        String businessType = request.getParameter("businessType");
        List<QueryFeeListResp> feeList = null;
        String thresholdValue = null;
        String beginDate = null;
        String endDate = null;
        Integer[] orgIdArray;
        String importMonth = null;
        QueryFeeListReq queryFeeListReq = new QueryFeeListReq();
        User user = appsession.getUser();

        switch (businessType) {
            case "1":

                Operator oper = new Operator();
                oper.setOperatorId(user.getOperatorId());
                Operator operator = operatorService.showOneOperator(oper);

                orgIdArray = stringToIntegerArr(request.getParameter(UNIT_NAME));
                queryFeeListReq.setUserName(formatRequestToValue(USER_NAME, request));
                queryFeeListReq.setFeeTypeName(formatRequestToValue(FEE_TYPE, request));
                queryFeeListReq.setBeginDate(formatRequestToValue(BEGIN_DATE, request));
                queryFeeListReq.setEndDate(formatRequestToValue(END_DATE, request));
                queryFeeListReq.setNotesId(formatRequestToValue(NOTES_ID, request));
                queryFeeListReq.setOperatorLevel(user.getOperatorLevel());
                queryFeeListReq.setOrgId(user.getOrgId());
                queryFeeListReq.setOperatorId(user.getOperatorId());

                feeList = queryFeeListService.queryFeeListAllResult(queryFeeListReq, orgIdArray);
                request.setAttribute(FILE_NAME, "资源使用情况查询报表");
                break;
            case "2":
                String topN = formatRequestToValue(TOP_N, request);
                beginDate = formatRequestToValue(BEGIN_DATE, request);
                endDate = formatRequestToValue(END_DATE, request);
                orgIdArray = stringToIntegerArr(request.getParameter(UNIT_NAME));

                try {
                    int tmp = Integer.parseInt(topN);
                    LOGGER.debug(TMP2 + tmp);
                } catch (Exception e) {
                    LOGGER.error("parseInt topN err2.", e);
                    topN = "10";
                }

                feeList = queryFeeListService.queryTopNListAllResult(orgIdArray, Integer.parseInt(topN), beginDate,
                        endDate);
                request.setAttribute(FILE_NAME, "TOP N查询报表");
                break;
            case "3":
                thresholdValue = formatRequestToValue(THRESHOLD_VALUE, request);
                orgIdArray = stringToIntegerArr(request.getParameter(UNIT_NAME));
                beginDate = formatRequestToValue(BEGIN_DATE, request);
                endDate = formatRequestToValue(END_DATE, request);

                try {
                    double tmp = Double.parseDouble(thresholdValue);
                    LOGGER.debug(TMP2 + tmp);
                } catch (Exception e) {
                    LOGGER.error("parseDouble thresholdValue err2.", e);
                    thresholdValue = "100";
                }

                feeList = queryFeeListService.queryThresholdListAllResult(orgIdArray, Double.parseDouble(thresholdValue),
                        beginDate, endDate);
                request.setAttribute(FILE_NAME, "超阀值查询报表");
                break;
            case "4":
                thresholdValue = formatRequestToValue(THRESHOLD_VALUE, request);
                orgIdArray = stringToIntegerArr(request.getParameter(UNIT_NAME));
                beginDate = formatRequestToValue(BEGIN_DATE, request);
                endDate = formatRequestToValue(END_DATE, request);

                try {
                    double tmp = Double.parseDouble(thresholdValue);
                    LOGGER.debug(TMP2 + tmp);
                } catch (Exception e) {
                    LOGGER.error("parseDouble thresholdValue err2.", e);
                    thresholdValue = "0";
                }

                feeList = queryFeeListService.queryNotActiveUserListAllResult(orgIdArray,
                        Double.parseDouble(thresholdValue), beginDate, endDate);
                request.setAttribute(FILE_NAME, "非活跃用户报表");
                break;
            case "5":
                orgIdArray = stringToIntegerArr(request.getParameter(UNIT_NAME));
                queryFeeListReq.setFeeTypeName(formatRequestToValue(FEE_TYPE, request));
                queryFeeListReq.setBeginDate(formatRequestToValue(BEGIN_DATE, request));
                queryFeeListReq.setEndDate(formatRequestToValue(BEGIN_DATE, request));
                queryFeeListReq.setOrgId(user.getOrgId());
                queryFeeListReq.setOperatorLevel(user.getOperatorLevel());

                feeList = queryFeeListService.queryDeptFeeListAllResult(queryFeeListReq, orgIdArray);
                request.setAttribute(FILE_NAME, "部门资源使用情况查询报表");
                break;
            default:
                throw new JRException("业务类型无法识别");
        }

        beginDate = formatRequestToValue(BEGIN_DATE, request);

        if (StringUtils.isNotEmpty(beginDate)) {
            importMonth = beginDate.substring(0, 4) + "年" + beginDate.substring(4) + "月";
        }

        request.setAttribute(IMPORT_MONTH, importMonth);
        return feeList;
    }

    private JasperPrint getPrint(List<QueryReportList> feeList, String businessType) throws JRException, IOException {

        String jasperPath = "";
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();

        URL url = servletContext.getResource("/");
        String weblogicPath = url.getPath().replaceAll("%20", " ");

        if (businessType.equals("5")) {
            jasperPath = weblogicPath + JASPER_PATH1;
        } else {
            jasperPath = weblogicPath + JASPER_PATH;
        }

        if (!new File(jasperPath).exists()) {
            jasperPath = jasperPath.replaceFirst("/", "");
        }

        JRDataSource dataSource = new JRBeanCollectionDataSource(feeList, true);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(TITLE, "资源使用情况查询");
        params.put(OPERATOR_NAME, appSession.getUser().getOperatorName());
        return JasperFillManager.fillReport(jasperPath, params, dataSource);
    }

    /**
     * 去除集合重复元素
     *
     * @param list
     * @return
     */
    public List removeDuplicate(List list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = list.size() - 1; j > i; j--) {
                    if (list.get(j).toString().equals(list.get(i).toString())) {
                        list.remove(j);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 批量查询
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchQuery", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String batchQuery(@RequestParam(value = "file", required = true) MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Map<String, String> responseMap = new HashMap<String, String>();
        String newFileName = String.valueOf(System.currentTimeMillis()) + fileName.substring(fileName.lastIndexOf("."));

        // 创建文件
        String dir = System.getProperty("java.io.tmpdir");
        File dest = new File(dir, newFileName);
        // 判断文件的目录是否存在，不存在则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        List<QueryFeeListResp> usrFeeList = new ArrayList<>();

        // 上传到指定目录
        try {
            file.transferTo(dest);
            List<String[]> dataList = JxlTool.importExcel(dest.getAbsolutePath(), 0);

            if (!dest.delete()) {
                LOGGER.info(dest + " is delete fail");
            }
            SimpleXlsxReportConfiguration conf = new SimpleXlsxReportConfiguration();
            conf.setWhitePageBackground(false);
            conf.setDetectCellType(true);
            conf.setOnePagePerSheet(true);

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setConfiguration(conf);

            List<ExporterInputItem> listItems = new ArrayList<ExporterInputItem>();
            for (String[] data : dataList) {

                if ("单位名称".equals(data[0].trim()) && "notesID".equals(data[1].trim()) && "用户名".equals(data[2].trim())
                        && "资费类型".equals(data[3])) {
                    continue;
                }
                QueryFeeListReq req = new QueryFeeListReq();
                req.setUnitName(data[0]);
                req.setNotesId(data[1]);
                req.setUserName(data[2]);
                req.setFeeTypeName(data[3]);

                SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
                String defaultDate = String.valueOf(Integer.parseInt(format.format(new Date())) - 1);

                req.setBeginDate("".equals(data[4]) ? defaultDate : data[4]);
                req.setEndDate("".equals(data[5]) ? defaultDate : data[5]);

                List<QueryFeeListResp> feeList = queryFeeListService.batchQueryFeeListResult(req);
                feeList = listToRespList(feeList);
                usrFeeList.addAll(feeList);
            }

            usrFeeList = removeDuplicate(usrFeeList);
            List<QueryReportList> usrReportList = toReportList(usrFeeList);
            JasperPrint print = getPrint(usrReportList, "1");
            listItems.add(new SimpleExporterInputItem(print));

            ExporterInput exporterInput = new SimpleExporterInput(listItems);
            exporter.setExporterInput(exporterInput);

            // 输出
            File exportFile = new File(dir, "批量查询结果.xlsx");
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                    new FileOutputStream(exportFile));
            exporter.setExporterOutput(exporterOutput);
            // 导出报表
            exporter.exportReport();
            exporterOutput.close();

            responseMap.put(ERR_CODE, "0000");
            responseMap.put(ERR_MSG, "success");
            responseMap.put("fileDir", exportFile.getAbsolutePath());
        } catch (Exception e) {
            LOGGER.error(e);
            if (e instanceof org.springframework.dao.DataIntegrityViolationException) {
                responseMap.put(ERR_CODE, "1001");
                responseMap.put(ERR_MSG, "输入条件有误，请依次检查是否按\"单位名称|用户名|资费类型|开始时间|结束时间\"输入！");
            } else if (e instanceof java.lang.IndexOutOfBoundsException) {
                responseMap.put(ERR_CODE, "1002");
                responseMap.put(ERR_MSG, "空文件！");
            } else {
                responseMap.put(ERR_CODE, "9999");
                responseMap.put(ERR_MSG, "系统异常！");
            }
        }

        return JSONArray.toJSONString(responseMap);

    }

    /**
     * 下载批量查询结果
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/downloadBatchResult", method = RequestMethod.POST)
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String absoluteFileName = request.getParameter("fileDir");
        boolean isTemplate = false;
        if (!new File(absoluteFileName).exists()) {
            isTemplate = true;
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            ServletContext servletContext = webApplicationContext.getServletContext();
            URL url = servletContext.getResource("/");
            String weblogicPath = url.getPath().replaceAll("%20", " ");
            absoluteFileName = weblogicPath + servletContext.getRealPath(absoluteFileName);
        }
        File downloadFile = new File(absoluteFileName);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setHeader("Pragma", NO_CACHE);
            response.setHeader("Cache-Control", NO_CACHE);
            response.setDateHeader("Expires", -1);
            response.setHeader("content-disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(downloadFile.getName(), "utf-8"));
            byte[] buf = FileUtils.readFileToByteArray(downloadFile);
            out.write(buf);
            out.flush();
            out.close();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
        }
        if (!isTemplate) {
            // 下载完成删除文件
            Path path = Paths.get(absoluteFileName);
            Files.delete(path);
        }
    }

    /**
     * 显示个人详情
     */
    @RequestMapping(value = "/showDetailUsrList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String showDetailUsrList(@RequestBody String reqJson) {

        Gson gson = new Gson();
        String cdrType = formatJsonToValue(CDR_TYPE, reqJson);
        String beginDate = formatJsonToValue(BEGIN_DATE, reqJson);
        String notesId = formatJsonToValue(NOTES_ID, reqJson);
        String feeType = formatJsonToValue(FEE_TYPE, reqJson);
        String unitName = formatJsonToValue(UNIT_NAME, reqJson);
        String operatorId = formatJsonToValue(OPERATOR_ID, reqJson);

        QueryCallListReq req = new QueryCallListReq();
        req.setLoginName(notesId);
        req.setBillMonth(beginDate);
        req.setFeeType(feeType);
        req.setOperatorId(operatorId);

        // 传入机构参数
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("orgNameFull", unitName);
        List<String> orgIdList = new ArrayList<>();
        // 判断是否黑户
        if (organizationDao.fuzzySearchOrganization(searchMap).isEmpty()) {
            orgIdList.add("0");
        } else {
            Integer orgId = organizationDao.fuzzySearchOrganization(searchMap).get(0).getOrgId();
            orgIdList.add(orgId.toString());
        }
        req.setUnitName(orgIdList);

        if (cdrType.equals("1") || cdrType.equals("2")) {
            req.setResourceType("1");
        } else if (cdrType.equals("3")) {
            req.setResourceType("2");
        } else {
            req.setResourceType("3");
        }

        // 黑户处理
        if (req.getLoginName().equals("黑户")) {
            req.setLoginName(null);
            if (cdrType.equals("1") || cdrType.equals("2")) {
                req.setOperatorId("0");
            } else {
                req.setOperatorId("-1");
            }

        }
        req.setLoginName(null);

        // 分页展示
        int currentPage = Integer.parseInt(formatJsonToValue(PAGE_NO, reqJson));
        int pageSize = Integer.parseInt(formatJsonToValue(PAGE_SIZE, reqJson));
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> map = queryCallListService.queryCallListResult(req, currentPage, pageSize);

        resultMap.put("total", map.get("cnt"));
        resultMap.put("list", map.get("list"));

        return JSONObject.toJSONString(resultMap);
    }

    /**
     * 显示部门详情
     *
     * @param reqJson
     * @return
     */
    @RequestMapping(value = "/showDetailOrgList", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String showDetailOrgList(@RequestBody String reqJson) {

        String cdrType = formatJsonToValue(CDR_TYPE, reqJson);
        String beginDate = formatJsonToValue(BEGIN_DATE, reqJson);
        String unitName = formatJsonToValue(UNIT_NAME, reqJson);
        String feeType = formatJsonToValue(FEE_TYPE, reqJson);

        QueryCallListReq req = new QueryCallListReq();
        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("orgNameFull", unitName);
        List<String> orgIdList = new ArrayList<>();

        // 判断是否黑户
        /**
         * 垂直越权 漏洞修复 直接取session机构id
         */
//        if (organizationDao.fuzzySearchOrganization(searchMap).isEmpty()) {
//            orgIdList.add("0");
//        } else {
//            Integer orgId = organizationDao.fuzzySearchOrganization(searchMap).get(0).getOrgId();
//            orgIdList.add(orgId.toString());
//        }
        if (appSession.getUser().getOrgId() == null){
            orgIdList.add("0");
        }else {
            orgIdList.add(appSession.getUser().getOrgId().toString());
        }
        req.setOrgId(appSession.getUser().getOrgId());
        req.setBillMonth(beginDate);
        req.setUnitName(orgIdList);
        req.setCdrType(cdrType);
        req.setFeeType(feeType);

        if (cdrType.equals("1") || cdrType.equals("2")) {
            req.setResourceType("1");
        } else if (cdrType.equals("3")) {
            req.setResourceType("2");
        } else {
            req.setResourceType("3");
        }

        // 分页展示
        int currentPage = Integer.parseInt(formatJsonToValue(PAGE_NO, reqJson));
        int pageSize = Integer.parseInt(formatJsonToValue(PAGE_SIZE, reqJson));
        Map<String, Object> resultMap = new HashMap<>();

        // 判断是否月租费
        if (cdrType.equals("1")
                && (feeType.equals("8") || feeType.equals("10") || feeType.equals("3") || feeType.equals("6"))) {

            List<RatedCdr> cdrList = queryFeeListService.queryOrgMonFeeDetail(req, currentPage, pageSize);
            for (int i = 0; i < cdrList.size(); i++) {
                String totalFee = String.format("%.1f", Double.parseDouble(cdrList.get(i).getTotalFee()) / 1000);
                cdrList.get(i).setTotalFee(totalFee);
            }
            resultMap.put("total", queryFeeListService.queryOrgMonFeeDetailCount(req));
            resultMap.put("list", cdrList);

        } else {

            Map<String, Object> map = queryCallListService.queryCallListResult(req, currentPage, pageSize);

            resultMap.put("total", map.get("cnt"));
            resultMap.put("list", map.get("list"));
        }

        return JSONObject.toJSONString(resultMap);
    }

}
