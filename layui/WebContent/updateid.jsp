<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色</title>
<%@include file="/context.jsp"%>
</head>
<body>

	<form id="formone" method="post">

		<div align="center">

			ID:<input id="id" type="text" name="tuid" value="${tuid}" readonly="true"><br>
			<br>
			name:<input id="name" type="text" name="username" value="${username}" readonly="true"><br>
			<br>
			pwd:<input id="psw" type="password" name="password" value="${password}" readonly="true"><br>
			<br>
			角色选择:<select  name="rid"><option>1</option>
						 <option>2</option>
			</select>
			<br>
			<button type="button" onclick="updatetuid()">确认修改</button>

		</div>
	</form>

</body>
<script src="static/jquery.min.js"></script>
<script type="text/javascript">
	function updatetuid() {
		$.ajax({
			url : "updatername",
			type : "post",
			async : true,
			data:$("#formone").serialize(),
			success : function(data) {
				alert(data);
				window.parent.location.reload();  
	            parent.layer.closeAll('iframe');  
	  
			}
		})

	}
</script>


</html>