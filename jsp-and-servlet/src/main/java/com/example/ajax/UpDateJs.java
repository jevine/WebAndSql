package com.example.ajax;

import WebAndSql.DbHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: ZhengJJ
 * Date: 2021/4/30
 */
@WebServlet("/upDateJs")
public class UpDateJs extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Connection con = null;
        PreparedStatement statement = null;
        String sql = "update webconsql set `area`=?  ,`name`=?, `birth`=?,`sex`=? where student_id=?";
        try {
            con = DbHelper.getConnection();
            statement = con.prepareStatement(sql);
            statement.setString(2, request.getParameter("name"));
            statement.setString(1, request.getParameter("area"));
            statement.setDate(3, Date.valueOf(request.getParameter("birth")));
            statement.setBoolean(4, Boolean.parseBoolean(request.getParameter("sex")));
            statement.setInt(5, Integer.parseInt(request.getParameter("addId")));
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            DbHelper.closeAll(con,statement);
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        doGet(request,response);
    }
}
