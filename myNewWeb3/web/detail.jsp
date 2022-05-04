<%--
  Created by IntelliJ IDEA.
  User: AzI
  Date: 2022/04/29
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>detail</title>
</head>
<body>
部门编号：${dept.no}<br>
部门名称：${dept.name}<br>
地理位置：${dept.loc}<br>
部门人数：${dept.num}<br>
<a href="${pageContext.request.contextPath}/dept/list">返回部门列表</a>
</body>
</html>
