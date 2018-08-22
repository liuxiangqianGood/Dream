<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="context.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>newIM</title>
<link rel="stylesheet" type="text/css"
	href="static/layui/css/layui.css"
	media="all" />

<script
	src="static/layui/lay/modules/layer.js"></script>

</head>
<body bgcolor="#f2f2f2">
	<h2 align="center">IM集团通告</h2>
	<form id="formone" method="post">
		<table border="2" align="center">
			<tr>
				<td align="right"><a class="layui-btn layui-btn-xs"
					lay-event="edit" onclick="addIM()">发布通告</a></td>
			</tr>
			<c:forEach items="${return_list}" var="show">
				<tr>
					<td align="center">${show.name}</td>
					<td>${show.id}</td>
					<td>update</td>
				</tr>
				<tr>
					<td>${show.body}</td>
					<td><a class="layui-btn layui-btn-danger layui-btn-xs"
						lay-event="del" onclick="del(${show.id})">删除</a></td>
						<td><a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" onclick="findid(${show.id})">修改</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<a href="HTlayui.jsp"><--<--回到上一层</a>
</body>
<script src="http://127.0.0.1:8088/layui/static/jquery.min.js"></script>
<script type="text/javascript">

function del(id){
	$.ajax({
		url:"delIM",
		type:"POST",
		data:{id:id},
		async:true,
		success:function(data){
			alert(data);
			window.location.reload();
		},
		error:function(data){
			alert(500);
		}

	})

}
function addIM(){
	layui.use('layer', function(){ 
		var layer = layui.layer;
		layer.open({
			  type: 1, 
			  content: '传入任意的文本或html' //这里content是一个普通的String
			});
});
}

	function findid(id){
		$.ajax({
			url:"findid",
			type:"POST",
			data:{id:id},
			success:function(data){
				$('body').html(data);
			}
		})
		
		
	}
</script>
</html>