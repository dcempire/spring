<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/21
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Student Enrollment Detail Confirmation</title>
    <link href="<c:url value='/static/css/custom.css' />" rel="stylesheet"></link>
</head>
<body>
<div class="success">
    确认信息 : ${success}
    <br>
    我们同时也发送了一封确认邮件到你的邮箱：${student.email}.
</div>
</body>
</html>
