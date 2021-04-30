package com.example.ajax;

import WebAndSql.DbHelper;
import WebAndSql.Student;
import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glodon
 */

@WebServlet("/ajax/searchList")
public class SearchJs extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");

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

            String string=JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd");
            PrintWriter out=response.getWriter();
            out.println(string);


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

