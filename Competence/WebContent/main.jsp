<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="contextjs.jsp" %>
<link  rel="stylesheet" href="<%=path%>/JS/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="<%=path%>/JS/zTree/js/jquery.ztree.core.js"></script>

<title>Insert title here</title>
</head>
<body class="easyui-layout">
<!-- 最上框 -->
	
	<div data-options="region:'north',border:false" style="height:60px;background:#eeeeee;overflow: visible; "align="center"><font size="20px" color="#2F4056" >酒店后台管理欢迎您<select><option>${sessionScope.name}</option></select><button type="button" onclick="Signout()">退出当前登录</button></font></div>
	<!-- 左侧jsp框 -->
<div data-options="region:'west'" title="菜单" style="width:150px;overflow:visible;">
		<div class="easyui-accordion" style="width:100%;height:100%;">
			<div class="easyui-panel" style="padding:5px">
				<ul id="tt" class="ztree" ></ul>
		
			</div>	
		</div>
	</div>
	<div data-options="region:'center'">
	<!-- 中间框 -->
		<div class="easyui-tabs" id="tabs" style="width:100%;height:100%;">
			<div title="Main" style="padding:10px">
				<h3>lxq的主题餐厅</h3>
			<div style="width: 400px;height: 400px;" align="center"><img src="photo/2016031115592811.jpg"></div>
			<div style="width: 400px;height: 400px; margin-left: 600px; margin-top:-200px;" ><img src="photo/2016031115592811.jpg"></div>
		</div>
	</div>
</body>
	<script type="text/javascript">
	
	//第四步中间框增加选项卡
	function addPanel(name,url){
		$('#tabs').tabs('add',{
			title:name,
			content:'<div style="width:100%;height:100%;"><iframe scrolling="no" id="middle"  frameborder="0" name="middle" width="95%" height="95%" src="'+url+'"></iframe></div>',
			closable:true
		});
	}
	//第三部点击第二步之前触发
	function ztreeclick(treeid,treeNode,clickFlag){
		//alert("treeid:"+treeid+",treeNode:"+treeNode.url+",clickFlag:"+clickFlag);
		addPanel(treeNode.name,treeNode.urlaction);
		 return (treeNode.id !== 1);
	};
	
	var setting={//第二步

			view:{
				dblClickExpand:false,
				showLine:true,
				showlcon:true,
				
			},
			data:{
				simpleData:{
					enable:true,
					idkey:"id",
					pIdKey: "fid",
					rootPId: ""
				}
			},
			
			callback: {//回调时间
				        beforeClick: ztreeclick//点击之前
				    }
	};
  // var zNodes = [
	        	   //{name:"test1", open:false, children:[
	        		    //  {name:"test1_1"}, {name:"test1_2"}
	        		     // ]
	        	  // },
	        		//   {name:"test2", open:false, children:[
	        		   //   {name:"test2_1"}, {name:"test2_2"}]}
	        		// ];
  //登录成功进入此jsp执行ajax调用方法在左侧jspIDtt第一步
  //菜单表ajax
	$.ajax({
		url:"login_userRoleZtree.action",
		success:function(data){
			//alert(data);
			//alert(data);
			$.fn.zTree.init($("#tt"), setting, JSON.parse(data));
			//setting执行到这调用上面 var setting方法
		}
	});
  //用户表ajax
  $.ajax({
	  	url:"login_userZtree.action",
	  	success:function(data){
	  		//alert(data);
	  		$.fn.zTree.init($("#user"),setting,JSON.parse(data));
	  		
	  	}
  });
	
  //退出当前登录AJAX
  function Signout(){
	  $.ajax({
		  url:"login_Signout.action",
		  type:"POST",
		  async:true,
		  success:function(data){
			alert(data);
			window.location.href="login_showlogin.action"
		  }
		  
	  })
  }
	</script>



</html>