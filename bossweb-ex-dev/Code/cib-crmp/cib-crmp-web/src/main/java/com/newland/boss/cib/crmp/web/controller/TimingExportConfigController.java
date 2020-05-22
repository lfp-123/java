package com.newland.boss.cib.crmp.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.domain.TimingSchedulerCfg;
import com.newland.boss.cib.crmp.web.service.TimingExportConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: TimingExportConfigController
 * @Package com.newland.boss.cib.crmp.web.controller
 * @Description: 定时器任务配置控制器
 * @date 2018/7/4
 */
@RestController
public class TimingExportConfigController {

    private final Logger logger = LogManager.getLogger(getClass());


    @Autowired
    private TimingExportConfigService timingExportConfigService;

    @RequestMapping(value = "/queryTimingSchedulerCfg")
    public List<TimingSchedulerCfg> queryTimingSchedulerCfg() {
        logger.debug("TimingExportConfigController : queryTimingSchedulerCfg start");
        List<TimingSchedulerCfg> timingSchedulerCfgs = timingExportConfigService.queryTimingExportConfig();
        logger.debug("Json result : " + JSONArray.toJSONString(timingSchedulerCfgs));
        logger.debug("TimingExportConfigController : queryTimingSchedulerCfg end");
        return timingSchedulerCfgs;
    }

    @RequestMapping(value = "/addTimingSchedulerCfg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addTimingSchedulerCfg(@RequestBody TimingSchedulerCfg timingSchedulerCfg) {
        logger.debug("TimingExportConfigController : addTimingSchedulerCfg start");
        try {
            if (timingSchedulerCfg == null || timingSchedulerCfg.getSchedulerType() == null || timingSchedulerCfg.getStatus() == null || timingSchedulerCfg.getSyncCycle() == null || timingSchedulerCfg.getCycleValue() == null) {
                return "{\"msg\":\"必填项未填\", \"code:\":\"-1\"}";
            }
            if (judgeCycleValue(timingSchedulerCfg)) return "{\"msg\":\"周期值不正确\", \"code:\":\"-1\"}";
            timingExportConfigService.insertTimingExportConfig(timingSchedulerCfg);
        } catch (Exception e) {
            logger.debug(e);
            return "{\"msg\":\"添加失败\", \"code:\":\"-1\"}";
        }
        logger.debug("TimingExportConfigController : addTimingSchedulerCfg end");
        return "{\"msg\":\"添加成功\", \"code:\":\"0\"}";
    }

    @RequestMapping(value = "/updateTimingSchedulerCfg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateTimingSchedulerCfg(@RequestBody TimingSchedulerCfg timingSchedulerCfg) {
        logger.debug("TimingExportConfigController : updateTimingSchedulerCfg start");
        try {
            if (timingSchedulerCfg == null || timingSchedulerCfg.getSchedulerType() == null || timingSchedulerCfg.getStatus() == null || timingSchedulerCfg.getSyncCycle() == null || timingSchedulerCfg.getCycleValue() == null) {
                return "{\"msg\":\"必填项未填\", \"code:\":\"-1\"}";
            }
            if (judgeCycleValue(timingSchedulerCfg)) return "{\"msg\":\"周期值不正确\", \"code:\":\"-1\"}";

            timingExportConfigService.updateTimingExportConfig(timingSchedulerCfg);
        } catch (Exception e) {
            logger.debug(e);
            return "{\"msg\":\"修改失败\", \"code:\":\"-1\"}";
        }
        logger.debug("TimingExportConfigController : updateTimingSchedulerCfg end");
        return "{\"msg\":\"修改成功\", \"code:\":\"-1\"}";
    }

    private boolean judgeCycleValue(@RequestBody TimingSchedulerCfg timingSchedulerCfg) {
        try {
            boolean flag = timingExportConfigService.verification(timingSchedulerCfg);
            if (!flag) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/deleteTimingSchedulerCfg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteTimingSchedulerCfg(@RequestBody List<TimingSchedulerCfg> timingSchedulerCfgs) {
        logger.debug("TimingExportConfigController : deleteTimingSchedulerCfg start");
        try {
            timingExportConfigService.deleteTimingExportConfig(timingSchedulerCfgs);
        } catch (Exception e) {
            logger.debug(e);
            return "{\"msg\":\"删除失败\", \"code:\":\"-1\"}";
        }
        logger.debug("TimingExportConfigController : deleteTimingSchedulerCfg end");
        return "{\"msg\":\"删除成功\", \"code:\":\"0\"}";
    }
}
