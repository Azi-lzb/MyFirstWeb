<%--
  Created by IntelliJ IDEA.
  User: AzI
  Date: 2022/05/04
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/login" >
    用户名<input type="type" name="username"><br>
    密码  <input type="password" name="password"><br>
    十天内免登陆 <input type="checkbox" name="check" value="yes"><br>
        <input type="submit" value="提交">
    </form>

</body>
</html>
