package com.example.WebAndSql;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IfExsit {

    public ResultSet checkExsit(HttpServletRequest request, HttpServletResponse response) {
        Connection con = null;
        String sqlSearch = "select * from webconsql where student_id=" + request.getParameter("id");
        PreparedStatement preparedStatement = null;
        try {
            con = DbHelper.getConnection();
            preparedStatement = con.prepareStatement(sqlSearch);
            return preparedStatement.executeQuery(sqlSearch);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            DbHelper.closeAll(con,preparedStatement);
        }
        return null;
    }

    public ResultSet checkExsit(HttpServletRequest request, HttpServletResponse response, Boolean flag) {
        if (flag) {
            try {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            return checkExsit(request, response);
        }
        return null;
    }
}
