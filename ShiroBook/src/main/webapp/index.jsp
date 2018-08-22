<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 登录页面 -->
<head>
<%@include file="/context.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<link href="${pageContext.request.contextPath}/Static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/Static/bootstrap/js/bootstrap.min.js"></script>
</head>

<!-- <style>
.b{background-image:url(img/2.jpg)}
</style>
 -->
<body>

<form action="login" method="post">
<div align="center" style="margin-top: 300px;margin-left: 400px;">

<div style="width: 100px;height: 50px">username: 
<span class="glyphicon glyphicon-sunglasses" aria-hidden="true"></span> 
<input type="text" name="username" placeholder="请输入你的用户名"></div>

<div style="width: 100px;height: 50px">userpsw: 
<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span> 
<input type="password" name="userpsw" placeholder="请输入你的密码"></div>
<input type="submit" value="登录"><input type="reset">

</div>
</form>
</body>
</html>