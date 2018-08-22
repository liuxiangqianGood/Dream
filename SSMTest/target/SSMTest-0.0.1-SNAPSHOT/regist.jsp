<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table align="center">

<form id="formregist">

	username:<input type="text" name="username"><br>
	password:<input type="text" name="password"><br>
	
	
	<button type="button" onclick="regist()">确认修改</button>
	<input type="reset">

</form>

</table>
</body>
<script src="static/jquery.min.js"></script>
<script type="text/javascript">
		function regist(){
			$.ajax({
				url:"adduser",
				type:"POST",
				async:true,
				data:$("#formregist").serialize(),
				success:function(data){
					alert(data);
					window.location.href="showlogin";
				}
				
			})
		}


</script>
</html>