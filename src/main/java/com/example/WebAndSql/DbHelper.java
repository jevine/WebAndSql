package com.example.WebAndSql;

import java.sql.*;

/**
 * 连接数据库的方法类
 *
 * @author glodon
 */
public class DbHelper {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://10.32.203.32:3306/zjjtest?characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static Connection con = null;

    private DbHelper() {
        throw new UnsupportedOperationException("不支持实例化");
    }

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void closeAll(Connection con , PreparedStatement statement) {
        try {
            if (con!=null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
