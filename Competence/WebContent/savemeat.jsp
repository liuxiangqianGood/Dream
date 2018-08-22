<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form id="formone">
<div align="center">

编号:<input type="text" name="id"><br>
菜名:<input type="text" name="name"><br>
口味:<input type="text" name="flavor"><br>
<button type="button" onclick="enter()">确认添加</button>
<input type="reset">

</div>
</form>
</body>
<script src="JS/jquery.min.js"></script>
<script type="text/javascript">
		function enter(){
			
			$.ajax({
				url:"meat_enter.action",
				type:"POST",
				async:true,
				data:$("#formone").serialize(),
				success:function(data){
					alert(data);
					window.location.reload();
				}
				
			})
			
		}



</script>
</html>