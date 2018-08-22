<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<div id="div1"></div>
<body>
<script src="JS/jquery.min.js"></script>
<script type="text/javascript"></script>
<form id="formone" method="post">
<table border="1"style="width: 1000px; height: 500px;">
	<tr><td><button type="button" onclick="savemeat()">增加菜品</button></td><td></td><td><input type="text" name="name"/><button type="button" onclick="select()">查询</button></td></tr>
	<tr><td>图片展示</td><td>ID</td><td>菜名</td><td>口味</td><td>add</td><td>delete</td></tr>
	<c:forEach items="${jsonArray}" var="a">
	<tr><td></td><td>${a.id}</td><td>${a.name}</td><td>${a.flavor}</td><td><button type="button" onclick="findid(${a.id})">点餐</button></td><td><button type="button" onclick="drop(${a.id})">删除</button></td></tr>
	</c:forEach>
	
	</table>
</form>
</body>
<script type="text/javascript">
//查询id下的数据

		function findid(id){
			alert(id);
			$.ajax({
				url:"meat_findid.action",
				type:"POST",
				async:true,
				data:{id:id},
				success:function(data){
					document.write(data);
				},
			})
		}
//菜单表删除
		function drop(id){
			alert(id);
			$.ajax({
				url:"meat_deletemeat.action",
				type:"POST",
				async:true,
				data:{id:id},
				success:function(data){
					alert(data);
					window.location.reload();
				},
				error:function(data){
					alert(data);
				}
			})
		}
		function savemeat(){
			$.ajax({
				url:"meat_savemeat.action",
				type:"POST",
				async:true,
				success:function(data){
					document.write(data);
				}
			})
		}
		function select(){
			var name = $('input[name="name"]').val();	
			$.ajax({
				url:"meat_selectname.action",
				type:"POST",
				async:true,
				data:{name:name},
				success:function(data){
 					document.write(data);
				}
				
			})
		}
</script>
</html>