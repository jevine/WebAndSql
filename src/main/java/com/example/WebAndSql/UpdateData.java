package com.example.WebAndSql;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;

/**
 * @author glodon
 */

@WebServlet("/update")
public class UpdateData extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

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
            statement.setInt(5, Integer.parseInt(request.getParameter("id")));
            statement.executeUpdate();
            response.sendRedirect("searchList");
        } catch (SQLException | IOException sqlException) {
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
