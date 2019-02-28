package com.csl.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库链接工具类
 * <p>
 * 陈思龙
 * 2018 12 5
 */
public class JdbcUtil {
    private String dbUrl = "jdbc:mysql://localhost:3306/db_studentInfo";
    private String dbUserName = "root";
    private String dbPassword = "123456";
    private String jdbcName = "com.mysql.jdbc.Driver";

    /**
     * 获取数据库连接
     *
     * @return
     * @throws Exception
     */
    public Connection getCon() throws Exception {
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    /**
     * 关闭数据库连接
     *
     * @param con
     * @throws Exception
     */
    public void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }

    /**
     * 测试数据库链接
     *
     * @param args
     */
    public static void main(String[] args) {
        JdbcUtil dbUtil = new JdbcUtil();
        try {
            dbUtil.getCon();
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

