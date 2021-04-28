package com.example.WebAndSql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * @author glodon
 */

@WebServlet("/add")
public class AddOne extends HttpServlet {
    private IfExsit ifExsit;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (ifExsit == null) {
            ifExsit = new IfExsit();
        }
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String area = request.getParameter("area");
        String date = request.getParameter("date");
        String id = request.getParameter("id");
        boolean flag = "".equals(name) || "".equals(area) || "".equals(sex) || "".equals(date) || "".equals(id);
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            ResultSet resultSet = ifExsit.checkExsit(request, response, flag);
            String sqlInsert = "insert into webconsql (`student_id`,`name`,`area`,`birth`,`sex`)" +
                    "values(?,?,?,?,?)";

            if (resultSet.next()) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } else {
                con = DbHelper.getConnection();
                preparedStatement = con.prepareStatement(sqlInsert);
                preparedStatement.setInt(1, Integer.parseInt(id));
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, area);
                preparedStatement.setDate(4, Date.valueOf(date));
                preparedStatement.setBoolean(5, Boolean.parseBoolean(sex));
                preparedStatement.executeUpdate();
                //request.getRequestDispatcher("searchList").forward(request,response);
                response.sendRedirect("searchList");
                //new SearchList().doGet(request, response);
            }
        } catch (
                SQLException | IOException | ServletException sqlException) {
            sqlException.printStackTrace();
        } finally {
            DbHelper.closeAll(con, preparedStatement);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

}
