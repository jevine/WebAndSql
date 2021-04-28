package com.example.WebAndSql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author glodon
 */

@WebServlet("/updateMid")
public class UpdateMid extends HttpServlet {
    private IfExsit ifExsit;
    private Student student;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (ifExsit == null) {
                ifExsit = new IfExsit();
            }
            if (student == null) {
                student = new Student();
            }
            ResultSet rs = ifExsit.checkExsit(request, response);
            while (rs.next()) {
                student.setStudent_id(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setArea(rs.getString("area"));
                student.setBirth(rs.getDate("birth"));
                student.setSex(rs.getBoolean("sex"));
            }
            request.setAttribute("stu", student);
            request.getRequestDispatcher("update.jsp").forward(request, response);
        } catch (SQLException | IOException | ServletException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);

    }
}
