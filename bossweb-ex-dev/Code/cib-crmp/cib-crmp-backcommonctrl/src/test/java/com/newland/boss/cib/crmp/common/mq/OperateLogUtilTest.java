package com.newland.boss.cib.crmp.common.mq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;

/**
 * 2018/10/23.
 */
public class OperateLogUtilTest {
    private static Log logger = LogFactory.getLog(OperateLogUtilTest.class);

    private String fileName = "1231231";
    private boolean send = true;
    private String clazz = "oracle.jdbc.driver.OracleDriver";
    private String url = "";
    private String userName = "cib";
    private String pw = "cib";
    private int operateModule = 19;

    @Test(enabled = false)
    public void testInsert() {
        url = "jdbc:oracle:thin:@ (DESCRIPTION = (ADDRESS_LIST =(ADDRESS =(PROTOCOL = TCP)(HOST = 10.1.0.211)(PORT = " +
                "1521)))(CONNECT_DATA=(SID = kf) ))";
        OperateLogUtil.insert(fileName, send, clazz, url, userName, pw, operateModule);
        int con = select();
        if (con >= 1) {
            Assert.assertEquals(1, 1);
        } else {
            Assert.assertEquals(1, 2);

        }


    }

    public int select() {
        String sql = "select count(1) AS CON from cib.t_operate_log t where t.operate_module = 19 " +
                "and t.create_time > sysdate - 1/256 and t.operate_desc like '%1231231%'";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            Class.forName(clazz);
            conn = DriverManager.getConnection(url, userName, pw);
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("CON");
            }
        } catch (ClassNotFoundException e) {
            logger.error("执行插入数据库异常。请查看数据库驱动包是否正确");
        } catch (SQLException e) {
            logger.error(e);
            logger.error("执行插入数据库异常。" + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            OperateLogUtil.close(conn, ps);
        }
        return 0;
    }

}