<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="context.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<link rel="stylesheet" type="text/css" href="static/layui/css/layui.css"
	media="all" />
</head>
<body bgcolor="#dddddd">
	<div align="center">
		<button class="layui-btn layui-btn-disabled layui-btn-radius">IM集团后台系统</button>
	</div>

	<div><h2>liuxiangqiande</h2></div>

		<div style="margin-top: 300px; margin-left: 700px;">
			<form id="formone" method="post">
			Username:<input type="text" name="username" placeholder="Username"><br> <br>
			Password:<input type="password" name="password" placeholder="Password"><br> <br>
			</form>
			<fieldset class="layui-elem-field site-demo-button">
				<div>
					<button class="layui-btn layui-btn-normal layui-btn-radius"
						onclick="login()">login</button>
					<button class="layui-btn layui-btn-warm layui-btn-radius"
						onclick="regist()">regist</button>
				</div>
			</fieldset>
		</div>
	
</body>
<script src="static/jquery.min.js"></script>
<script type="text/javascript">
	function login(){
		$.ajax({
			url:"login",
			type:"post",
			async:true,
			data:$("#formone").serialize(),
			success:function(data){
				alert(data);
				if(data=="success"){
					window.location.href="showHTlayui";
				}
			},
			error:function(data){
				alert(data);
			}
		})
	}
	function regist(){
		$.ajax({
			url:"regist",
			type:"post",
			success:function(data){
				document.write(data);
			}
		})
	}
</script>
</html>