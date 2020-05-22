package com.newland.boss.cib.crmp.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newland.boss.cib.crmp.domain.ServerCfg;
import com.newland.boss.cib.crmp.web.service.UploadServerConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: UploadServerConfigController
 * @Package com.newland.boss.cib.crmp.web.controller
 * @Description: 上次服务器配置
 * @date 2018/7/17
 */
@RestController
public class UploadServerConfigController {
    private final Logger logger = LogManager.getLogger(getClass());


    @Autowired
    private UploadServerConfigService uploadServerConfigService;

    @RequestMapping(value = "/queryServerCfg")
    public List<ServerCfg> queryServerCfg() {
        logger.debug("UploadServerConfigController : queryServerCfg start");
        List<ServerCfg> serverCfgs = uploadServerConfigService.queryServerCfg();
        for (ServerCfg serverCfg : serverCfgs) {
            serverCfg.setPassword("******");
            if (isboolIp(serverCfg.getHostName())) {
                serverCfg.setHostName(ipEncrypt(serverCfg.getHostName()));
            }
        }
        logger.debug("Json query result : " + JSONArray.toJSONString(serverCfgs));
        logger.debug("UploadServerConfigController : queryServerCfg end");
        return serverCfgs;
    }

    @RequestMapping(value = "/addServerCfg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addServerCfg(@RequestBody ServerCfg serverCfg) {
        logger.debug("UploadServerConfigController : addServerCfg start");
        logger.debug("Json add : " + JSONObject.toJSONString(serverCfg));
        try {
            boolean flag1 = serverCfg == null || serverCfg.getHostName() == null || serverCfg.getServerType() == null || serverCfg.getUserName() == null || serverCfg.getPassword() == null || serverCfg.getStatus() == null;
            boolean flag2 = serverCfg == null || (serverCfg != null && serverCfg.getServerType() != 13) && (serverCfg.getHostName() == null || serverCfg.getServerType() == null || serverCfg.getUserName() == null || serverCfg.getPassword() == null || serverCfg.getStatus() == null || serverCfg.getSrcPath() == null || serverCfg.getDestPath() == null || serverCfg.getTransProtocol() == null);
            if (flag1 || flag2) {
                return "{\"msg\":\"必填项未填\", \"code:\":\"-1\"}";
            }
            uploadServerConfigService.insertServerCfg(serverCfg);
        } catch (Exception e) {
            logger.debug(e);
            return "{\"msg\":\"添加失败\", \"code:\":\"-1\"}";
        }
        logger.debug("UploadServerConfigController : addServerCfg end");
        return "{\"msg\":\"添加成功\", \"code:\":\"0\"}";
    }

    @RequestMapping(value = "/updateServerCfg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateServerCfg(@RequestBody ServerCfg serverCfg) {
        logger.debug("UploadServerConfigController : updateServerCfg start");
        logger.debug("Json update : " + JSONObject.toJSONString(serverCfg));
        try {
            boolean flag1 = serverCfg == null || serverCfg.getHostName() == null || serverCfg.getServerType() == null || serverCfg.getUserName() == null || serverCfg.getPassword() == null || serverCfg.getStatus() == null;
            boolean flag2 = serverCfg == null || (serverCfg != null && serverCfg.getServerType() != 13) && (serverCfg.getHostName() == null || serverCfg.getServerType() == null || serverCfg.getUserName() == null || serverCfg.getPassword() == null || serverCfg.getStatus() == null || serverCfg.getSrcPath() == null || serverCfg.getDestPath() == null || serverCfg.getTransProtocol() == null);
            if (flag1 || flag2) {
                return "{\"msg\":\"必填项未填\", \"code:\":\"-1\"}";
            }
            uploadServerConfigService.updateServerCfg(serverCfg);
        } catch (Exception e) {
            logger.debug(e);
            return "{\"msg\":\"修改失败\", \"code:\":\"-1\"}";
        }
        logger.debug("UploadServerConfigController : updateServerCfg end");
        return "{\"msg\":\"修改成功\", \"code:\":\"-1\"}";
    }

    @RequestMapping(value = "/deleteServerCfg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteServerCfg(@RequestBody List<ServerCfg> serverCfgs) {
        logger.debug("UploadServerConfigController : deleteServerCfg start");
        logger.debug("Json delete : " + JSONArray.toJSONString(serverCfgs));
        try {
            uploadServerConfigService.deleteServerCfg(serverCfgs);
        } catch (Exception e) {
            logger.debug(e);
            return "{\"msg\":\"删除失败\", \"code:\":\"-1\"}";
        }
        logger.debug("UploadServerConfigController : deleteServerCfg end");
        return "{\"msg\":\"删除成功\", \"code:\":\"0\"}";
    }

    /**
     * 判断是否为合法IP * @return the ip
     */
    public static boolean isboolIp(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }


    public static String ipEncrypt(String mobile) {
        return mobile.replaceAll("(\\d{1,3}).(\\d{1,3}).(\\d{1,3}).(\\d{1,3})", "$1.***.***.$4");
    }

}
