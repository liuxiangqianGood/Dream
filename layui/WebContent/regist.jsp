<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="context.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Regist</title>
<link rel="stylesheet" type="text/css" href="static/layui/css/layui.css"
	media="all" />
</head>
<body bgcolor="#d2d2d2">
	<div align="center">
		<button class="layui-btn layui-btn-disabled layui-btn-radius">IM集团后台注册系统</button>
	</div>
<div align="center" style="margin-top: 200px;">
<form id="formone" method="post">
	Username:<input type="text" name="username"><br>
	<br>
	Password:<input type="password" name="password"><br>
</form>
<fieldset class="layui-elem-field site-demo-button">
				<div>
					<button class="layui-btn layui-btn-normal layui-btn-radius"
						onclick="regist()">Enter</button>
					<button class="layui-btn layui-btn-warm layui-btn-radius"
						onclick="showlogin()">noRegist</button>
				</div>
			</fieldset>
			</div>
</body>
<script src="static/jquery.min.js"></script>
<script type="text/javascript">
	function regist(){
		$.ajax({
			url:"enterregist",
			type:"post",
			data:$("#formone").serialize(),
			success:function(data){
				alert(data);
				if(data=="success"){
					window.location.href="findaddtuserid";
				}else{
					window.location.href="okcancel";
				}
			
			}
		})
	}
	function showlogin(){
		$.ajax({
			url:"cancel",
			type:"post",
			async:true,
			success:function(data){
				alert(data);
				window.location.href="okcancel";
			}
			
		})
	}

</script>
</html>