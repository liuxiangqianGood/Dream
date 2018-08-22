<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/context.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>人员</title>

<style type="text/css">
</style>
</head>


<body class="layui-layout-body">

	<div class="layui-container" style="width: 100%">

		<div class="layui-row">
			<div class="layui-col-xs2 layui-col-sm2 layui-col-md2 layui-col-lg2">
				<button class="layui-btn" onclick="add()">
					<i class="layui-icon">&#xe608;</i> 添加
				</button>
			</div>

			<div
				class="layui-col-xs10 layui-col-sm10 layui-col-md10 layui-col-lg10">
				<div class="layui-inline">
					<input class="layui-input" name="username" placeholder="查找用户。。"
						autocomplete="off">
				</div>

				<button class="layui-btn" id="ssdf" onclick="select()">查询</button>


			</div>
		</div>
		<div class="layui-row">
			<table id="tab1"></table>
		</div>
	</div>



	<script type="text/html" id="barDemo">


	
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改角色</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除用户</a>



 		
</script>

	<script src="static/jquery.min.js"></script>
	<script type="text/javascript">

layui.use('element', function(){
	  var element = layui.element;
	  
	
layui.use(['table','form'], function(){
	  var table = layui.table;
	  var form = layui.form;
	 /*  未被接    //字段如果不对应自己改  */
	  table.render({
	    elem: '#tab1'   
	    ,url: 'findUser' //数据接口
	   // ,height: 932
	   	,height: 'full-200'
	   	,method: 'post'
	   	,page: true //开启分页
	    ,cellMinWidth: 80 //最小 width:80px	   
	    ,cols: [[ //表头
	       {field: 'tuid', title: '编号', sort: true}
	      ,{field: 'username', title: '账号'}
	      ,{field: 'password', title: '密码'}
	      ,{field: 'rname', title: '角色'}
	      ,{ title: '操作',toolbar: '#barDemo'}
	    ]]
	  });
	 
	  //监听工具条
	  table.on('tool', function(obj){
	    var data = obj.data;
	  
	    if(obj.event === 'detail'){
	    	/* 回收站恢复 */

	      } else if(obj.event === 'del'){
	    	/* 删除 */    	

	  		alert("tuid:"+data.tuid),
	  		//删除按钮
  			$.ajax({
	    		url:"deletetuid",  
	    		type:"POST",
	    		async:true,
	    		data:{"tuid":data.tuid},	
	    		success:function(data){
	    		alert(data);
	    		window.location.reload();
	    		},
	    		error:function(){
	    			
	    		}
	    		  
	    	  });
	    	  
	        layer.close(index);
	   
	    } else if(obj.event === 'edit'){
	    	
	
	     addrname(JSON.stringify(data));
	    }
	  });
	});	  
	 element.init();
	});


	//开启添加弹窗
	
	 function addrname(data){
		 layer.open({
			  type: 2,
			  area: ['700px', '450px'],
			  fixed: false, //不固定
			  maxmin: true,
			 // data:{"data":data},
			  content: 'updateid.jsp?',
			  success: function (layero, index) {
				  var datas = JSON.parse(data);
				  var body = layer.getChildFrame('body', index); //巧妙的地方在这里哦
					 body.contents().find("#id").val(datas.tuid);
					 body.contents().find("#name").val(datas.username);
					 body.contents().find("#psw").val(datas.password);
			  }
			});
		
			} 
	
	//table 查询重载
	function select(){

	var username = $('input[username="username"]').val();		//姓名

	layui.use('table', function(){
			var table = layui.table;
			
			table.reload('tab1', {
				  where: {
				  username:username
				  }
				  ,page: {
				    curr: 1 
				  }
				});		
		});
		
	}
	 
	
		setTimeout("aas()",300);
	
		function aas(){
			parent.$("[lay-filter='jd']").hide()
		}
</script>

</body>
</html>