<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/context.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>center</title>
</head>
<!-- 登录认证成功后先进入这个页面再从这里进入后台 -->
<body>
<div align="center">
<shiro:hasRole name="admin">
<button type="button" class="btn btn-default btn-lg" onclick="showHT()">
<span class="glyphicon glyphicon-grain" aria-hidden="true"></span>
欢迎您${sessionScope.username}！Admin
 </button>
</shiro:hasRole><br>

<shiro:hasRole name="qiantai">
<button type="button" class="btn btn-default btn-lg" onclick="showHT()">
<span class="glyphicon glyphicon-grain" aria-hidden="true"></span>
欢迎您${sessionScope.username}！前台专区
</button>
</shiro:hasRole><br>

<shiro:hasRole name="chushi">
<button type="button" class="btn btn-default btn-lg" onclick="showHT()">
<span class="glyphicon glyphicon-grain" aria-hidden="true"></span>
欢迎您${sessionScope.username}！厨房专区
</button>
</shiro:hasRole><br>

<br>
<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
<a href="logout.action">注销</a>
</div>
</body>
<script src="/Static/bootstrap/jquery.min.js"></script>
<script type="text/javascript">
	//进入后台
	function showHT(){
		$.ajax({
			url:"showHT",
			type:"POST",
			async:true,
			success:function(data){
				alert(data);
				window.location.href="HTsee";
			}
			
		})
	}


</script>
</html>