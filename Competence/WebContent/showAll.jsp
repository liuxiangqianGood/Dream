<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="contextjs.jsp" %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'North Title',split:true"
		style="height: 100px;"></div>
	<div data-options="region:'south',title:'South Title',split:true"
		style="height: 100px;"></div>
	<div
		data-options="region:'east',iconCls:'icon-reload',title:'订单信息',split:true"
		style="width: 100px;">
		<ul class="easyui-tree">

				</ul>
		</ul>
		</div>
	<div data-options="region:'west',title:'表格信息',split:true"
		style="width: 100px;">
		<ul class="easyui-tree">
			
				<ul>
							<li><span> <a href="javaScript:void(0)"
							onclick="aa('easyui','<%=basePath%>login_onclickshowUser.action')">用户显示</a></span></li>

							<li><span> <a href="javaScript:void(0)"
							onclick="aa('easyui','<%=basePath%>role_onclickshowrole.action')">showrole</a></span></li>
							
							<li><span> <a href="javaScript:void(0)"
							onclick="aa('easyui','<%=basePath%>userrole_onclickshowT_User_Role.action')">showT_User_Role</a></span></li>
				
							<li><span> <a href="javaScript:void(0)"
					
							onclick="aa('easyui','<%=basePath%>menu_onclickshowT_Menu.action')">showT_Menu</a></span></li>
		
					</ul> 
			
	
	</div>	
	<div data-options="region:'center',border:0" id="tt"
		class="easyui-tabs" style="padding: 5px; background: #eee;">

	</div>
</body>
<script type="text/javascript">
			$('#uu').tree({
				onClick:function(node){
					alert(node.urlaction+","+node.text);
					return node.text;
				}
			});
				</script>

<script type="text/javascript">
	function ztreeclick(treeld,treeNode,clickFlag){
		alert("treeld:"+treeld+",treeNode:"+treeNode.url+",clickFlag:"+clickFlag);
		addPanel(treeNode.name,treeNode.action);
	};

var setting={
	view:{
		dblClickExpand:false,
		showLine:true,
		showlcon:true
	},
	data:{
		simpleData:{
			enable:true,
			idKey:"id",
			pldKey:"pld",
			rootPld:""			
		}

	},
	callback:{
		beforeClick:ztreeclick
	}

}


   function aa(title, url){
	  
	   alert(url);
	  if ($('#tt').tabs('exists', title)){
				alert("已经存在");
				$('#tt').tabs('select', title);
			} else {
				alert("不存在");
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				$('#tt').tabs('add',{
					title:title,
					content:content,
					closable:true,
					 tools:[{    
					        iconCls:'icon-mini-refresh',    
					        handler:function(){    
					            alert('refresh');    
					        }    
					    }]   
				});
			}
		}

   
</script>

</html>