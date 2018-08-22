<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<form action="login_update.action" method="post">

		<p>ID:<input type="text" name="user.id" value="${ user.id}" readonly="true"></p><br>
		<p>username:<input type="text" name="user.username" value="${ user.username}"></p><br>
		<p>pwd:<input type="text" name="user.password" value="${ user.password}"></p><br>
		<input type="submit" value="确认修改">


</form>
</div>
</body>
</html>