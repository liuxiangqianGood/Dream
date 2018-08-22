<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>



 


<body bgcolor="#eeeeee">

	
<form id="formtwo" method="post">
<div align="center">
	<p>UserName:<input type="text" name="user.username"  ></p><br>
	<p>PassWord:<input type="password" name="user.password"></p><br>
	<button type="button" onclick="regist()">确定注册</button>
	<input type="reset"/>
	<button type="button" onclick="cancel()">取消注册</button>
	</div>
	
</form>
</body>
<script src="JS/jquery.min.js"></script>
<script type="text/javascript">
		function regist(){

		$.ajax({
			url:"login_add.action",
			type:"POST",
			async:true,
			data:$("#formtwo").serialize(),
			success:function(data){
				alert(data);
			},
			error:function(data){
				alert(data);
			},
		})
		
		}
	function cancel(){
		$.ajax({
			url:"login_Signout.action",
			type:"POST",
			async:true,
			success:function(data){
				alert(data);
				window.location.href="login_showlogin.action";
			}
			
		})
		
	}

</script>
</html>