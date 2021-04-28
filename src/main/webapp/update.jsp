<%@ page import="com.example.WebAndSql.Student" %>
<%@ page import="java.sql.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: glodon
  Date: 2021/4/27
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UPDATE</title>
</head>
<body>
<form action="update" method="post">
   <% Student st= (Student) request.getAttribute("stu");%>
    <table border="1">
        <tr>
            <td>姓名：<input type="text" name="name" value="<%=st.getName()%>"></td>
            <td>地区: <input type="text" name="area" value="<%=st.getArea()%>"></td>
            <td>id:<input type="text"  name="id" readonly value="<%=st.getStudent_id()%>"></td>
            <td> 性别:
<%--                <input type="radio"  name="sex" value="true" <%=st.isSex()? "checked=\"checked\"" : "" %>>女--%>
<%--                <input type="radio" name="sex" value="false" <%=!st.isSex()? "checked=\"checked\"" : "" %>>男--%>

            <select name="sex" >
                <option value="true" <%=st.isSex()? "selected='selected'" : "" %>>女</option>
                <option value="false" <%=st.isSex()?  "" : "selected='selected'" %>>男</option>
            </select>
            </td>
            <td> 出生日期 <input type="date" name="birth" value="<%= (Date)st.getBirth()%>"></td>
            <td>  <input type="submit" value="更改信息"></td>
        </tr>
    </table>
</form>
</body>
</html>
