<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="formone">
		username:<input type="text" name="username" placeholder="username"><br>
		password:<input type="password" name="password" placeholder="password"><br>
						<button type="button" onclick="login()">登录</button>
						<input type="reset">
						<a href="regist.action">注册</a>
</form>

</body>
<script src="static/jquery.min.js"></script>
<script type="text/javascript">

	function login(){
		$.ajax({
			url:"login",
			type:"POST",
			data:$("#formone").serialize(),
			async:true,
			success:function(data){
				alert(data);
				if(data=="success"){
					window.location.href="showuser";
				}
			},
		error:function(data){
			alert(data);
		}	
		})	
	}


</script>
</html>