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
<form action="userrole_add.action" method="post">

<p>桥梁ID:<input type="text" name="ur.urid"></p><br>
<p>用户ID:<input type="text" name="ur.id"></p><br>
<p>角色ID:
<div align="center">
				<select>
				<option>1</option>
				<option>2</option>
				</select>
</div>
</p><br>
<input type="submit" value="提交赋值权限">
<input type="reset">




</form>
</div>
</body>
</html>