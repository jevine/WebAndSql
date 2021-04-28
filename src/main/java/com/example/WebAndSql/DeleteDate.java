package com.example.WebAndSql;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库查询方法
 * @author glodon
 */
@WebServlet("/delete")
public class DeleteDate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection con=null;
        String name=request.getParameter("id");
        String sql="delete from webconsql where student_id=?";
        PreparedStatement statement=null;
        try {
            con=DbHelper.getConnection();
            statement=con.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(name));
            statement.execute();
            response.sendRedirect("searchList");
        } catch (SQLException | IOException sqlException) {
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
