<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>



<body bgcolor="#dddddd">
<h1 align="center">I M</h1>
<form id="formone" method="post">
<div style="margin-left: 250px; margin-top: 250px">
	<p>UserName:<input type="text" name="user.username" placeholder="输入您的Username" ></p><br>
	<p>PassWord:<input type="password" name="user.password" placeholder="输入您的Password"></p><br>
	<p>验证码:<input type="text" name="verify" placeholder="输不输入都OK">
	<img id="imgs" onclick="changimg()" src="login_verify.action" style="margin-left: 100px"/>
	</p>
		<button type="button" onclick="login()">登录</button>

	<input type="reset"/>
	<button type="button" id="btn" onclick="regist()" style="margin-left: 50px;color: blue;">注册</button>
	</div>
	
</form>
<script src="JS/jquery.min.js"></script>
<script type="text/javascript">
	function login(){
		$.ajax({
			url:"login_login.action",
			async:true,
			type:"POST",
			data:$("#formone").serialize(),
			success:function(data){
				alert(data);
				
				if(data=="success"){
				
					window.location.href="login_shoumain.action";
				}
			},
			error:function(data){
				alert(data);
			},
		})
	}
	
	function regist(){
		
		window.location.href="login_regist.action";
		
	}

	function changimg(){
		var dat=new Date();
		alert(dat)
		$("#imgs").attr("src","login_verify.action?date="+dat)
	}
	</script>
</body>
</html>