
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>LIST页面</title>
  <meta charset="utf-8">
  <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<h3>欢迎${username},在线人数${onlinecount}</h3>
<script>
  function  del(no){
    if(window.confirm("请确定删除吗？")){
      document.location.href="${pageContext.request.contextPath}/dept/delete?no="+no;
    }

  }

</script>
<table border="" cellspacing="" cellpadding="">
  <tr>
    <th>序号</th>
    <th>no</th>
    <th>name</th>
    <th>loc</th>
    <th>num</th>
    <th>操作</th>
  </tr>
  <c:forEach items="${deptList}"  varStatus="deptStatus" var="dept">
  <tr>
    <td>${deptStatus.count}</td>
    <td>${dept.no}</td>
    <td>${dept.name}</td>
    <td>${dept.loc}</td>
    <td>${dept.num}</td>
    <td><a href="javascript:void(0)" onclick="del(${dept.no})">删除</a>
      <a href="${pageContext.request.contextPath}/dept/modify?no=${dept.no}">修改</a>
      <a href="${pageContext.request.contextPath}/dept/detail?no=${dept.no}">详情</a>
    </td>
  </tr>
  </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/new.jsp"> 新增部门</a>
<a href="${pageContext.request.contextPath}/user/exit"> 退出登陆</a>

</body>
</html>
