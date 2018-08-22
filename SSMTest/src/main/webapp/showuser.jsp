<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">

<tr><td>uid</td><td>username</td><td>password</td><td>操作</td></tr>
<c:forEach items="${showuser}" var="show">
<tr><td>${show.uid}</td><td>${show.username}</td><td>${show.password}</td><td><button type="button" onclick="deleteid(${show.uid})">删除</button><button type="button" onclick="findid(${show.uid})">修改</button></td></tr>
</c:forEach>
</table>
</body>
<script src="static/jquery.min.js"></script>
<script type="text/javascript">
			function deleteid(uid){
				$.ajax({
						url:"deleteid",
						type:"POST",
						data:{id:uid},
						async:true,
						success:function(data){
							alert(data);
							window.location.reload();
						},
						error:function(data){
							alert(data);
						}
				})
			}
			
			function findid(uid){
				$.ajax({
					url:"findid",
					type:"POST",
					async:true,
					data:{id:uid},
					success:function(data){
						document.write(data);
					},
					error:function(data){
						alert(data);
					}
					
				})
				
				
			}
</script>
</html>