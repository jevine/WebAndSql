package com.example.WebAndSql;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glodon
 */

@WebServlet("/searchList")
public class SearchList extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String sql = "select * from webconsql";
        String name = request.getParameter("ID");
        if (name != null && !"".equals(name)) {
            sql = "select * from webconsql where student_id=" + name;
        }

        PreparedStatement preparedStatement=null;
        Connection con=null;
        try {
            con = DbHelper.getConnection();
            preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudent_id(resultSet.getInt("student_id"));
                student.setName(resultSet.getString("name"));
                student.setArea(resultSet.getString("area"));
                student.setBirth(resultSet.getDate("birth"));
                student.setSex(resultSet.getBoolean("sex"));
                list.add(student);
            }
            request.setAttribute("students", list);
            request.getRequestDispatcher("list.jsp").forward(request, response);

        } catch (Exception sqlException) {
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
