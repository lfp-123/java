package com.newland.boss.kpi.controller;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.newland.boss.kpi.server.DictDefService;
import com.newland.boss.kpi.server.OrgService;

/**
 * @author weixc
 * 2018-05-18
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = -8503993355539942598L;

	private static Logger logger = LogManager.getLogger(InitServlet.class);

    @Autowired
    private DictDefService dictDefService;
    
    @Autowired
    private OrgService orgService;

    public void init(ServletConfig config) {
        // servlet的初始化方法中调用spring的方法进行注入
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        dictDefService.initCache();
        logger.info("dictMapCache size :" + dictDefService.selectAllDictDef().size());
        
        orgService.initCache();
        logger.info("org size :" + orgService.getAllOrganizationFromCache().size());
        
    }
}
