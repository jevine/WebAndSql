<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.WebAndSql.Student" %><%--
  Created by IntelliJ IDEA.
  User: glodon
  Date: 2021/4/26
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <td>姓名</td>
    <td>地区</td>
    <td>生日</td>
    <td>Id</td>
    <td> 是否女性</td>
    <td>delete</td>
    <td>update</td>
    <c:forEach items="${students}" var="st">
        <tr>
            <td>${st.getName()}</td>
            <td> ${st.getArea()}</td>
            <td>${st.getBirth()}</td>
            <td>${st.getStudent_id()}</td>
            <td> ${st.isSex()}</td>
            <td><a href="delete?id=${st.student_id}">删除本条</a></td>
            <td><a href="updateMid?id=${st.student_id}">更新本条</a></td>

        </tr>

    </c:forEach>
</table>
<table border="2">
    <tr>
        <td>
            <a href="add.jsp">增加信息</a>
            <form action="searchList" method="post">
                <input type="text" oninput="value=value.replace(/[^\d]/g,'')" name="ID">
                <input type="submit" value="按ID查找">
            </form>
        </td>
    </tr>
</table>


</body>
</html>
