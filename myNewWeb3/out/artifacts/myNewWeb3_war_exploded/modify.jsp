<%--
  Created by IntelliJ IDEA.
  User: AzI
  Date: 2022/04/29
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modifyPage</title>
</head>
<script>
    function redirect(){
        window.location.href="/myweb/dept/list";
    }
</script>
<form action="${pageContext.request.contextPath}/dept/update">
部门编号：<input type="text" readonly name="no" value="${dept.no}"><br>
部门名称：<input type="text" name="name" value="${dept.name}"><br>
地理位置：<input type="text" name="loc" value="${dept.loc}"><br>
部门人数：<input type="text" name="num" value="${dept.num}"><br>
    <input type="submit" value="提交">
    <input type="button" value="返回" onclick="redirect()">
</form>
</body>
</html>
