package com.newland.boss.cib.crmp.common.mq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 描述:
 * 操作日志类
 * create 2018-10-23 22:03
 */
public class OperateLogUtil {

    private OperateLogUtil() {

    }

    private static Log logger = LogFactory.getLog(OperateLogUtil.class);

    public static String createMsg(String fileName, boolean send) {
        SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        aDate.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String format = aDate.format(new Date());
        String result = send ? "成功" : "失败";
        return format + " " + fileName + "," + result;
    }

    /**
     * 插入采集日志信息
     *
     * @param fileName 文件名称
     */
    public static void insert(String fileName, boolean send, String clazz, String url, String userName, String pw,
                              int operateModule) {
        String msg = createMsg(fileName, send);
        String sql = "insert into t_operate_log (OPERATE_ID, OPERATOR_ID, OPERATE_MODULE, " +
                "OPERATE_TYPE, OPERATE_MONTH, OPERATE_DESC, CREATE_TIME)" +
                "values (seq_operator_log.nextval,77777777,?,1,to_char(sysdate,'MM'),?,sysdate)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getCon(clazz, url, userName, pw);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, operateModule);
            ps.setString(2, msg);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            logger.error("执行插入数据库异常。请查看数据库驱动包是否正确");
        } catch (SQLException e) {
            logger.error(e);
            logger.error("执行插入数据库异常。" + e.getMessage());
            rollback(conn);
        } finally {
            close(conn, ps);
        }
    }

    /**
     * 插入采集日志信息
     */
    public static void insert(String msg, String clazz, String url,
                              String userName, String pw, int operateModule, String keyValue) {

        logger.debug(keyValue);
        String sql = "insert into t_operate_log (OPERATE_ID, OPERATOR_ID, OPERATE_MODULE, " +
                "OPERATE_TYPE, OPERATE_MONTH, OPERATE_DESC, CREATE_TIME)" +
                "values (seq_operator_log.nextval,77777777,?,1,to_char(sysdate,'MM'),?,sysdate)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getCon(clazz, url, userName, pw);
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, operateModule);
            ps.setString(2, msg);
            ps.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (ClassNotFoundException e) {
            logger.error("执行插入数据库异常。请查看数据库驱动包是否正确");
        } catch (SQLException e) {
            logger.error(e);
            logger.error("执行插入数据库异常。" + e.getMessage());
            rollback(conn);
        } finally {
            close(conn, ps);
        }
    }

    private static void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error("回滚失败");
        }

    }

    public static Connection getCon(String clazz, String url, String userName, String pw) throws SQLException, ClassNotFoundException {
        Class.forName(clazz);
        return DriverManager.getConnection(url, userName, pw);
    }

    public static void close(Connection conn, PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("关闭会话异常，" + e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("关闭连接异常，" + e.getMessage());
            }
        }

    }
}
