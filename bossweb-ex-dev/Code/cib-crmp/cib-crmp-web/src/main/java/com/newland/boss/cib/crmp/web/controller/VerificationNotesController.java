package com.newland.boss.cib.crmp.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.newland.boss.cib.crmp.common.sm.SM4Utils;
import com.newland.boss.cib.crmp.web.util.LDAPUtil;
import com.newland.boss.kpi.admin.model.Operator;
import com.newland.boss.kpi.admin.service.OperOrgRelaService;
import com.newland.boss.kpi.admin.service.OperatorService;
import com.newland.boss.kpi.admin.service.OrganizationService;
import com.newland.boss.kpi.constant.Constants;
import com.newland.boss.kpi.entity.DictDef;
import com.newland.boss.kpi.entity.Organization;
import com.newland.boss.kpi.server.AdminService;
import com.newland.boss.kpi.server.DictDefService;
import com.newland.boss.kpi.util.MD5Util;
import com.newland.boss.kpi.util.ResourceUtil;

@Controller
public class VerificationNotesController {
	
	private static final Logger LOGGER = LogManager.getLogger(VerificationNotesController.class);
	
	@Autowired
	private DictDefService dictDefService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OperOrgRelaService operOrgRelaService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/verifyNotesUser", method={RequestMethod.POST})
	public void verifyNotesUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/json;charset=UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String line = "";
		StringBuilder sb = new StringBuilder("");
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String reqStr = sb.toString();
		br.close();
		
		String verifyStatus = "0";
		String verifyMessage = "校验成功！";
		
		String username = reqStr.split("\\|")[0];
		String password = reqStr.split("\\|")[1];
		String verifyCode = reqStr.split("\\|")[2];
		String sessionVerifyCode = (String)request.getSession().getAttribute(Constants.SESSION_KEY_OF_RAND_CODE);
        long sessionVerifyTime = Long.parseLong(String.valueOf(request.getSession().getAttribute(Constants.SESSION_KEY_OF_RAND_TIME)));
        
        if("false".equals(ResourceUtil.getValue("noCheckFalg"))) {
			if ((null == verifyCode) || (null == sessionVerifyCode) || (!verifyCode.equalsIgnoreCase(sessionVerifyCode))) {
				verifyStatus = "1003";
				verifyMessage = "验证码错误！";
			} else if ((System.currentTimeMillis() - sessionVerifyTime) > 60*1000) {
				verifyStatus = "1004";
				verifyMessage = "验证码已失效！";
			}
			if(!"0".equals(verifyStatus)) {
				write(verifyStatus, verifyMessage, null, response);
				return;
			}
		}
        
        String ret = adminService.checkUserLock(password, username, 1);
        if(ret != null) {
        	verifyStatus = "1005";
        	write(verifyStatus, ret, null, response);
			return;
        }
        
        //notes认证
        LOGGER.debug("进行notes认证");
        DictDef dictDef = dictDefService.getDictDefByDictClassAndEntryId(1011, 0);
        if(dictDef == null) {
        	verifyStatus = "1005";
			verifyMessage = "notes用户认证失败！请检查地址！";
        	write(verifyStatus, verifyMessage, null, response);
        	return;
        }
        SM4Utils sm4 = new SM4Utils();
        String ldapURL = dictDef.getEntryDesc();
        boolean verifyFlag = LDAPUtil.verifyNotesUser(ldapURL, username, sm4.decryptDataECB(password, "OwBPDAAl4htTdTNr"));
        Operator oper = null;
        if(verifyFlag) {
        	//同步用户
        	try {
        		oper = syncOperator(username);
        		if(oper.getOperatorStatus() != 0) {
        			verifyStatus = "1005";
    	        	verifyMessage = "notes用户状态不可用！";
        		}
			} catch (Exception e) {
				LOGGER.error("同步用户信息异常！", e);
				verifyStatus = "1005";
	        	verifyMessage = "notes用户认证失败！";
			}
        } else {
        	verifyStatus = "1005";
    		verifyMessage = adminService.loginVerify(password, username, 1);
        }
		write(verifyStatus, verifyMessage, oper, response);
	}
	
	private void write(String verifyStatus, String verifyMessage, Operator oper, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		json.put("verifyStatus", verifyStatus);
		json.put("verifyMessage", verifyMessage);
		if(oper != null) {
			json.put("operatorLevel", oper.getOperatorLevel());
			json.put("operatorId", oper.getOperatorId());
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 同步用户
	 * @param username
	 * @return
	 */
	private Operator syncOperator(String username) {
		//判断是否同步到系统
		Operator oper = operatorService.findByLoginName(username);
        //不存在则同步
        if(oper == null) {
        	LOGGER.debug("同步notes用户");
        	int modifyOperatorId = -1;
        	String departmentName = LDAPUtil.getDepartmentName();
        	LOGGER.debug("notes部门信息 : " + departmentName);
        	Organization org = syncDepartment(departmentName, modifyOperatorId);
        	LOGGER.debug("部门信息 : " + org);
        	
        	oper = LDAPUtil.getOperator();
        	oper.setPassword(MD5Util.Encrypt("123456"));
        	oper.setOperatorStatus(0);
        	oper.setOperatorLevel(1);
        	oper.setModifyOperatorId(modifyOperatorId);
        	List<Organization> orgList = new ArrayList<>();
        	if(org != null) {
        		orgList.add(org);
        	}

        	LOGGER.debug("用户信息 : " + oper);
        	operatorService.addOperator(oper);
        	
        	operOrgRelaService.addOrgForOper(oper, orgList);
        }
        return oper;
	}
	
	/**
     * 同步部门
     * @param distinguishedName
     * @param modifyOperatorId
     */
    private Organization syncDepartment(String distinguishedName, int modifyOperatorId) {
    	LOGGER.info("BureauDataCollectionService:getDepartment:start");
        String[] dstr = distinguishedName.split(",");
        StringBuilder sb = new StringBuilder();
        //如果没有所属部门，则跳过
        Organization organization = null;
        int superOrgId = -1;
        for (int i = dstr.length - 4; i > 0; i--) {
            String de = dstr[i].split("=")[1];
            // 如果部门名称包含数字，全部移除
            if (StringUtils.isNumeric(de)) {
                String pat = "\\d+";
                Pattern p = Pattern.compile(pat);
                de = p.matcher(de).replaceAll("");
            }
            sb.append(de);
            Organization tmp = organizationService.findByFullName(sb.toString());
            if(tmp == null) {
            	tmp = new Organization();
            	tmp.setOrgName(de);
            	tmp.setOrgNameFull(sb.toString());
            	tmp.setOrgStatus(0);
            	tmp.setModifyOperatorId(modifyOperatorId);
            	tmp.setSuperOrgId(superOrgId);
            	organizationService.addOrg(tmp);
            	superOrgId = tmp.getOrgId();
            }
            sb.append("\\");
            if(i == 1) {
        		organization = tmp;
        	}
        }
        LOGGER.info("distinguishedName" + sb.toString());
        LOGGER.info("BureauDataCollectionService:getDepartment:end");
        return organization;
    }
}
