package com.example.ajax;

import WebAndSql.DbHelper;
import WebAndSql.IfExsit;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @Author: ZhengJJ
 * Date: 2021/4/30
 */

@WebServlet("/addOneJs")
public class AddOneJs extends HttpServlet {
    private IfExsit ifExsit;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (ifExsit == null) {
            ifExsit = new IfExsit();
        }
        String id = request.getParameter("addId");
        String area = request.getParameter("area");
        String birth = request.getParameter("birth");
        String sex = request.getParameter("sex");
        String name = request.getParameter("name");
        String sql = "insert ignore into webconsql (`student_id`,`name`,`area`,`birth`,`sex`)values(?,?,?,?,?)";
        Connection con = null;
        PreparedStatement statement = null;
        boolean flag = "".equals(name) || "".equals(area) || "".equals(sex) || "".equals(birth)|| "".equals(id);
        try {

            if (flag){
                return;
            }
            con = DbHelper.getConnection();
            statement = con.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));
            statement.setString(2, name);
            statement.setString(3, area);
            statement.setDate(4, Date.valueOf(birth));
            statement.setBoolean(5, Boolean.parseBoolean(sex));
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            DbHelper.closeAll(con, statement);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        doGet(request, response);
    }
}
