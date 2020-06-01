package com.seecen.work1;

import org.apache.log4j.Logger;

/**
 * log4j日志
 * @Author 林锋鹏
 * @Date 2019/8/2 10:12
 * @Description
 */


public class TestLog {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(TestLog.class);
        logger.info(args.length);
        try {
            String n = null;
            System.out.println(n.toLowerCase());
        } catch (Exception e) {
            logger.error(e);
        } finally {
        }
    }
}
