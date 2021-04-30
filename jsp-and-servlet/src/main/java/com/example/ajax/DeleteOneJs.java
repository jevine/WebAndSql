package com.example.ajax;

import WebAndSql.DbHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: ZhengJJ
 * Date: 2021/4/30
 */

@WebServlet("/deleteOneJs")
public class DeleteOneJs extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection con=null;
        String id =request.getParameter("ID");
        String sql="delete from webconsql where student_id=?";
        PreparedStatement statement=null;
        try {
            con= DbHelper.getConnection();
            statement=con.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));
            statement.execute();
           /* response.sendRedirect("index.html");*/
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            DbHelper.closeAll(con,statement);
        }
    }
    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response){
        doGet(request,response);
    }
}
