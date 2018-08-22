<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/context.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<title>I M后台</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/layui/css/layui.css" />
<script src="${pageContext.request.contextPath}/static/layui/lay/modules/layer.js"></script>

</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header" style="background-color: #c2c2c2">
			<div class="layui-logo">IM后台管理</div>
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="javaScript:void(0);"
					onclick="newIM()">最新活动</a></li>
				<li class="layui-nav-item layui-this"><a href="">菜品管理</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">中餐管理</a>
						</dd>
						<dd>
							<a href="">西餐管理</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="">IM数据</a></li>
				<li class="layui-nav-item"><a href="javascript:;">旗下公司</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">IM酒店</a>
						</dd>
						<dd>
							<a href="">IM会所</a>
						</dd>
						<dd>
							<a href="">IM健身房</a>
						</dd>
						<dd>
							<a href="">IM高尔夫</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="">IM后花园</a></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="static/images/11.jpg" class="layui-nav-img">
						${sessionScope.username}
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javaScript:void(0);"
					onclick="cancel()">注销登录</a></li>
			</ul>
		</div>

		<!--左侧区域 -->
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll" style="background-color: #393D49">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" id="left" lay-filter="test">
				</ul>
			</div>
		</div>


		<!-- 内容主体区域 -->
		<div class="layui-body">
			<iframe id="center" src="" height="100%" width="99%"
				style="border: 0; margin-top: 10px"> </iframe>
		</div>

		<!-- 底部固定区域 -->
		<div class="layui-footer">© IM.com-IM版权所有,翻版必究</div>
	</div>

	<script>
		layui
				.use(
						'element',
						function() {
							var element = layui.element;
							/* 得到内容的左侧导航的列表 */
							//$("[lay-filter='jd']").css('display', 'none');
							$
									.ajax({
										url : "findmenu", //菜单列表的url() 
										async : true,
										success : function(data) {

											var json = JSON.parse(data);
											for (var i = 0; i < json.length; i++) {
												var a = "<li class=\"layui-nav-item\">"
														+ "<a href=\"javascript:;\">"
														+ json[i].MNAME
														+ "</a>"
														+ "<dl class=\"layui-nav-child\">";

												for (var j = 0; j < json[i].list.length; j++) {
													a += "<dd><a href=\"javascript:;\" onclick=\"show('"+json[i].list[j].ACTION+"')\" >"
															+ json[i].list[j].MNAME
															+ "</a></dd>";
												}
												a += "</dl>" + "</li>";
												$("#left").append(a);

											}
											//元素初始化
											element.init();
										},
										error : function(data) {
											alert(500)
										}
									});
						});
	</script>

</body>
<script src="/layui/static/jquery.min.js"></script>
<script type="text/javascript">
	//注销
	function cancel() {
		$.ajax({
			url : "cancel",
			type : "post",
			async : true,
			success : function(data) {
				alert(data);
				window.location.href = "okcancel";
			}
		})
	}
	function newIM() {
		$.ajax({
			url : "findnewIM",
			type : "post",
			success : function(data) {
				document.write(data);
			},
			error : function(data) {
				alert(111);
			}

		})
	}

	function show(menu) {


			$('#center').attr('src', menu);
			layui.use('element', function() {
			var element = layui.element;
			element.progress('jd', '0%');
			$("[lay-filter='jd']").show();
			element.progress('jd', '100%');

		});

	}
</script>
</html>