<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table align="center">
<c:forEach items="${showid}" var="show">
<form id="formthree">
	id:<input type="text" name="uid" value="${show.uid}" readonly="true"><br>
	username:<input type="text" name="username" value="${show.username}"><br>
	password:<input type="text" name="password" value="${show.password}"><br>
	
	
	<button type="button" onclick="update()">确认修改</button>
	<input type="reset">

</form>
</c:forEach>
</table>
</body>
<script src="static/jquery.min.js"></script>
<script type="text/javascript">
		function update(){
			$.ajax({
				url:"updateid",
				type:"POST",
				async:true,
				data:$("#formthree").serialize(),
				success:function(data){
					alert(data);
					window.location.href="showuser";
				}
				
			})
		}


</script>
</html>