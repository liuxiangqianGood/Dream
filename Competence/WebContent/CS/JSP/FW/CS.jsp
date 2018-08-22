<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../context.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>厨师</title>

<style type="text/css">
</style>
</head>


<body class="layui-layout-body">


	<div class="layui-container">

		<div class="layui-row">
			<div
				class="layui-col-xs12 layui-col-sm12 layui-col-md12 	layui-col-lg12">
				<div class="layui-tab layui-tab-brief " lay-filter="docDemoTabBrief">
					<ul class="layui-tab-title ">


						<li class="layui-this layui-anim layui-anim-scale">未接单</li>
						<li>已接单</li>
						<li>我的接单</li>

					</ul>
					<div class="layui-tab-content">

						<div class="layui-tab-item layui-show">
							<table id="tab1" lay-filter="demo"></table>
						</div>


						<div class="layui-tab-item">
							<table class="layui-table" id="tab2"></table>
						</div>


						<div class="layui-tab-item">
							<table class="layui-hide" id="test"></table>
						</div>


					</div>


				</div>
			</div>

		</div>

	</div>
	<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">接桌</a>
</script>
	<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-xs" lay-event="edit">完成</a>
</script>

	<script type="text/javascript">

layui.use('element', function(){
	  var element = layui.element;
	  
	  //一些事件监听
	  element.on('tab(docDemoTabBrief)', function(data){
	    
	  });
	  
	 
	});
	
layui.use('table', function(){
	  var table = layui.table;
	  
	 /*  未被接    //字段如果不对应自己改  */
	  table.render({
	    elem: '#tab1'   
	    ,url: 'http://localhost:8088/XBMMavenSSM/Order/selUnansweredOrder' //数据接口
	   // ,height: 932
	    ,cellMinWidth: 80 //最小 width:80px	   
	    ,cols: [[ //表头
	       {field: 'TABLEID', title: '桌号', sort: true}
	      ,{field: 'FOODLIST', title: '菜(点击显示全部)'}
	      ,{field: 'ALLMONEY', title: '金额'}
	      ,{ title: '接桌',toolbar: '#barDemo' , fixed: 'right'}
	    ]]
	  });
	  
	  /*  已经被接过的桌  //字段如果不对应自己改*/
	  table.render({
		    elem: '#tab2'   
		    ,url: 'http://localhost:8088/XBMMavenSSM/Order/selUnansweredOrder' //数据接口
		    ,page: true //开启分页
		    ,cols: [[ //表头
		           {field: 'TABLEID', title: '桌号', sort: true} 
			      ,{field: 'FOODLIST', title: '菜'}
			      ,{field: 'CHERF', title: '大厨'}
			      ,{field: 'ALLMONEY', title: '金额'}
			 
		    ]]
		  });
	  
	  /*  我的接桌  //字段如果不对应自己改*/
	  table.render({
		    elem: '#test'
		    ,url:'http://localhost:8088/XBMMavenSSM/Order/selUnansweredOrder'
		    ,heigh:'100%'
		    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    ,cols: [[
		    	/*   */
		       	   {field: 'id', title: '桌号', sort: true}
			      ,{field: 'name', title: '菜'}
			      ,{field: 'dc', title: '大厨'}
			      ,{field: 'qian', title: '金额'}
			      ,{field: 'cz', title: '完成',toolbar: '#barDemo1' , fixed: 'right'}
			    
		    ]]
		  });
		  
		  
	//监听工具条
	  table.on('tool', function(obj){
	    var data = obj.data;	
	    
	    /* 接桌  */
	    if(obj.event === 'edit'){
	    	alert("点击了");
	      layer.alert('编辑行：<br>'+ JSON.stringify(data))
	      	if(((data.CHERF).indexOf('null')!=-1) && (parseInt(data.id)<3)){  //判断接桌数是否多余3桌 ,前面的那个判断null是为了在页面区别	      																
	      	  $.ajax({	    											//2个按钮(接桌or完成)的点击，2个按钮用的同一个按钮监听
		    	  url:'', 				//url用与向后台提交时          《==大厨 接单 ==》  看清别看错      	
		    	  anync:false,		    		
		    	  data:{
		    		""  :data.CHREF,  		//大厨id 后台接受字段(自己写)
		    		""  :data.TORDERID	//订单id 后台接受字段(自己写)
		    	  },
		    	  success:function(data){
		    		  if(data.indexOf('')!=-1){
		    			  location.reload();  //刷新本页面
		    		  }
		    	  },
		    	  error:function(){	    		  
		    	  }		      	    	  
		      });
	      	}else {      		
	      	  layer.alert("接的太多了做完在接");
	      	}
	      
	      	/* 完成一桌 */
	      $.ajax({	    		
	    	  url:'', 				//url用与向后台提交时 接的桌完成   	
	    	  anync:false,		    		
	    	  data:{
	    		""  :data.TORDERID	    //订单id 后台接受字段(自己写)
	    	  },
	    	  success:function(data){
	    		  if(data.indexOf('')!=-1){
	    			  location.reload();  //刷新本页面
	    		  }
	    	  },
	    	  error:function(){	    		  
	    	  }		      	    	  
	      });
	      
	    
	      
	     
	    }
	  });
	  
	  
	});
	
	
</script>

</body>
</html>