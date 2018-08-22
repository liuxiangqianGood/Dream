<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 后台页面 -->
<head>
<%@include file="/context.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HT Boot</title>

</head>
<body style="padding-top: 50px;">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#" style="margin-top: 8px;">
        <img alt="Brand" src="">
      </a>
    </div>
    <ul class="nav nav-pills" style="margin-left: 150px; margin-top: 12px;">
  <li role="presentation" class="active"><a href="#">Home</a></li>
  <li role="presentation"><a href="#">Profile</a></li>
  <li role="presentation"><a href="#">Messages</a></li>
  <li role="presentation"><a href="#">liuxiangqian</a></li>
  </ul>
	<form class="navbar-form navbar-left" role="search">
  <div class="form-group" style="margin-top: -45px;margin-left: 700px;">
    <input type="text" class="form-control" placeholder="Search">
  
  <button type="submit" class="btn btn-default">Submit</button>
  </div>
</form>

<nav aria-label="...">
  <ul class="pager" style="margin-left: 1000px;margin-top: -35px;">
    <li><a href="#">用户：${sessionScope.username}</a></li>
    <li><a href="#">角色：${sessionScope.rname}</a></li>
    <li><a href="logout.action">注销</a></li>
    <span class="glyphicon glyphicon-grain" aria-hidden="true"></span> 
  </ul>
</nav>
</nav>

<!-- 左侧边框 -->
<!-- Single button -->
<div class="btn-group" id="left" style="margin-top: 80px; width:200px; height:500px; background-color: olive;">

</div>
<!-- 下导航条 -->
<div class="btn-group" style="margin-top: 10px; width:1500px; height:50px;">
<h5 align="center">@liuxiangqian 项目专利 翻版必究 商务联系至邮箱:874641722@qq.com</h5>

</div>

</body>
<script src="Static/bootstrap/jquery.min.js"></script>
<script type="text/javascript">

		$.ajax({
			url:"findmenus",
			type:"POST",
			async:true,
			success:function(data){
				
			}
			
		})


</script>
</html>