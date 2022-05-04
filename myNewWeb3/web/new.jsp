<%--
  Created by IntelliJ IDEA.
  User: AzI
  Date: 2022/04/29
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新建部门</title>
</head>
<body>
<script>
    function redirect(){
        window.location.href="/myweb/dept/list";
    }
</script>
<form action ="${pageContext.request.contextPath}/dept/new">
    部门编号：<input type="text" name="no" ><br>
    部门名称：<input type="text" name="name" ><br>
    部门地址：<input type="text" name="loc" ><br>
    部门人数：<input type="text" name="num" ><br>
    <input type="submit" value="提交">
    <input type="button" value="返回" onclick="redirect()">
</form>


</body>
</html>
