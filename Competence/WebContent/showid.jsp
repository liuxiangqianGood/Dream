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

<script src="JS/jquery.min.js"></script>
<script type="text/javascript"></script>
<form id="formone">
<table align="center">
<c:forEach items="${jsonArray}" var="a">
<tr><td>编号:<input type="text" name="id" value="${a.id}" readonly="true"></td></tr>
<tr><td>菜品:<input type="text" name="name" value="${a.name}" readonly="true"></td></tr>
<tr><td>口感:<select name="flavor">
<option>${a.flavor}</option>
<option>微辣</option>
<option>麻辣</option>
</select></td></tr>
<tr><td>数量:<select name="cnumber">
<option>1</option>
<option>2</option>
</select></td></tr>
<tr><td><button type="button" onclick="addcart()">确认点餐</button></td></tr>
</c:forEach>
</table>
</form>

</body>
<script type="text/javascript">
		function addcart(){
			$.ajax({
				url:"meat_addmeat.action",
				type:"POST",
				async:true,
				data:$("#formone").serialize(),
				success:function(data){
					alert(data);
					window.location.href="meat_findmeat.action";
				}

			})
			
		}



</script>
</html>