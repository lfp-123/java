package com.newland.boss.kpi.task;

import com.newland.boss.kpi.server.DictDefService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 数据字典初始化类
 *
 * @author weixc
 * 2018-05-18
 */
@Component
public class DictCacheTask {
    private static Logger logger = LogManager.getLogger(DictCacheTask.class);

    @Autowired
    private DictDefService dictDefService;

    @Scheduled(cron = "0 15 1 * * ? ")
    public void taskCycle() {
        dictDefService.initCache();
        logger.info("dictMapCache size :" + dictDefService.selectAllDictDef().size());
    }
}
