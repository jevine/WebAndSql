<%--
  Created by IntelliJ IDEA.
  User: glodon
  Date: 2021/4/26
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta>
    <title>Title</title>
</head>
<body>
<form action="add" method="get">
    姓名：<input type="text" name="name">
    <br/>
    地区: <input type="text" name="area">
    id:<input type="text" oninput="value=value.replace(/[^\d]/g,'')" name="id">
    性别:
    <select name="sex">
        <option value="false">女</option>
        <option value="true">男</option>
    </select>
    出生日期 <input type="date" name="date">
    <input type="submit" value="添加信息">
</form>
</body>
</html>
