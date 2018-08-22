<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="contextjs.jsp" %>
</head>
<body>
	<div id="cc" class="easyui-layout" style ="width:800px;height:600px;margin: auto;">   
	    <div data-options="region:'north',split:true,collapsible:false" style="height:100px;">
	    	<div style="text-align: center;padding-top: 5%">
	    		<input  class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:50%;height: 35px;font: 28px;" > 
	    		<a id="btn" target="iframelist"  href="login_listpage.action" class="easyui-linkbutton" 
	    		style="width: 80px;height: 35px;" data-options="iconCls:'icon-search'">搜索</a>  
	    	</div>
	    </div>   
	    <div data-options="region:'south',split:true,collapsible:false" style="height:100px;">
	    	
	    </div>   
	    <div data-options="region:'center',collapsible:false" style="background:#eee;height: 80%;">
		    <table  id="dd" class="easyui-datagrid">   
			
			</table>  
	    </div>   
	</div>  
	<div id="div1"></div>

</body>
<!-- 此表显示Order商品表信息 -->
 <script type="text/javascript">

 


        $(function () {
            var datagrid; //定义全局变量datagrid
            var editRow = undefined; //定义全局变量：当前编辑的行
            datagrid = $("#dd").datagrid({
                url: 'role_findall.action', //请求的数据源
                iconCls: 'icon-save', //图标
                pagination: true, //显示分页
                pageSize: 15, //页大小
                pageList: [15, 30, 45, 60], //页大小下拉选项此项各value是pageSize的倍数
                fit: true, //datagrid自适应宽度
                fitColumn: false, //列自适应宽度
                striped: true, //行背景交换
                nowap: true, //列内容多时自动折至第二行
                border: false,
                idField: 'rid', //主键
                columns: [[//显示的列
                {field: 'rid', title: '编号', width: 100, sortable: true, 
                	editor: { type: 'validatebox', options: { required: true} }
                	},
                 { field: 'rname', title: '角色名称', width: 100, sortable: true,
                    
                	editor: { type: 'validatebox', options: { required: true} }
                 },
                 
                  { field: 'rdescribe', title: '角色描述', width: 100, sortable: true,
                	 editor: { type: 'validatebox', options: { required: true}}
                	},
                  { field: 'fid', title: '父ID', width: 100, sortable: true,  
                	 editor: { type: 'validatebox', options: { required: true} }
                  }
                   ]],
                queryParams: { action: 'findall' }, //查询参数
                toolbar: [
                        { text: '添加', iconCls: 'icon-add', handler: function () {
                	//添加列表的操作按钮添加，修改，删除等
                    //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
                    alert("点击了add!")
                    if (editRow != undefined) {
                    	alert("请先取消编辑!");
                        datagrid.datagrid("endEdit", editRow);
                    }
                    //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                    if (editRow == undefined) {
                        datagrid.datagrid('insertRow', {
                            index: 0, // index start with 0
                            row: {

                            }
                        });
                        //将新插入的那一行
                        datagrid.datagrid("beginEdit", 0);
                        //给当前编辑的行赋值
                        editRow = 0;
                    }

                }
               },
               { text: '删除', iconCls: 'icon-remove', handler: function () {
                   //删除时先获取选择行
                   var rows = datagrid.datagrid("getSelections");
                   //选择要删除的行
                    alert("点击了delete!");
                    
                
                   if(rows.length>0){
                	   $.messager.confirm("提示","你确定要删除吗?",function (r){
                		var idss=[];
                		if(r){
                			for(var i=0;i<rows.length;i++){
                				idss.push(row[i].idss);
                			}
                			alert(idss);
                			console.info(idss);
                			$.ajax({
                				datatype:'json',
                				type:'post',
                				data:{idss:idss.join(',')},
                				url:"order_deleteorder.action",
                				success:function(data){
                					alert("成功");
                					$("#dd").datagrid('reload');
                					
                				},error:function(){
                					alert("错误")
                				}
                			})
                		}
                	   })
                   }
                   
                   
                }
               },

               { text: '修改', iconCls: 'icon-edit', handler: function () {
                 //修改时要获取选择到的行
                 var rows = datagrid.datagrid("getSelections");
                 //如果只选择了一行则可以进行修改，否则不操作
                  alert("点击了update!")
                  if (rows.length ==1) {
                  //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                  if (editRow != undefined) {
                   datagrid.datagrid("endEdit", editRow);
                   }
                   //当无编辑行时
                   if (editRow == undefined) {
                   //获取到当前选择行的下标
                   var index = datagrid.datagrid("getRowIndex", rows[0]);
                   //开启编辑
                   datagrid.datagrid("beginEdit", index);
                   //把当前开启编辑的行赋值给全局变量editRow
                   editRow = index;
                   //当开启了当前选择行的编辑状态之后，
                   //应该取消当前列表的所有选择行，要不然双击之后无法再选择其 他行进行编辑
                   datagrid.datagrid("unselectAll");
                   
                                }
                                }
                                }
                                }, '-',
                                {text: '保存', iconCls: 'icon-save', handler: function () {          
                               	 alert(editRow);
                                    //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互 可将数据通过Ajax提交后台
                                  		//alert($('#dd').datagrid('getData').rows[editRow].id);
                                  		datagrid.datagrid("endEdit", 0);      //获得属性 
                                  		  //获得属性值
                                  	// alert("editRow_id:"+ editrow.id);
                                  		//var row=$("#dd").datagrid("getChanges");
                                    
                                }
                                }, '-',

                { text: '取消编辑', iconCls: 'icon-redo', handler: function () {
                                    //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
                                    editRow = undefined;
                                    datagrid.datagrid("rejectChanges");
                                    datagrid.datagrid("unselectAll");
                                } 
                                },'-',
                                
               { text:'赋予等级', iconCls:'icon-add',handler:function(){

                   var url="role_addorup.action?status=add";
                	$("#div1").dialog('refresh',url);
                	div1.dialog('open');
                }   	              
				 },'-',
			  {	text: '弹窗修改', iconCls: 'icon-edit', handler: function(){
				  var row=$('#dd').datagrid('getSelected');
				  var url="order_addorup.action?status=up&orderid="+row.orderid;
				  $('#div1').dialog('refresh',url);
				  div1.dialog('open');
  
			  }		 
				 }],
               

    //..........1,   
               //增加修改AJAX,
                 onAfterEdit:function(rowIndex, rowData, changes) {
                	 console.info(rowData);  
                     alert("1");
                 	//alert("rowIndex:"+rowIndex+" ,rowData:"+JSON.stringify(rowData));
                     $.ajax({
                  		//async : false,
                  		url:"order_saveorupdate.action",
                  		type:"POST",
                  		data:{
                  			"order.shop":rowData.shop,
                  			"order.adress":rowData.adress,
                  			"order.tel":rowData.tel
                  		},
                  		success:function(data){
                  			alert(data);
                  		}
                  	});
                     editRow = undefined;
                 },
                 

                onDblClickRow: function (rowIndex, rowData) {
                //双击开启编辑行
                    if (editRow != undefined) {
                        datagrid.datagrid("endEdit", editRow);
                    }
                    if (editRow == undefined) {
                        datagrid.datagrid("beginEdit", rowIndex);
                        editRow = rowIndex;
                    }
                }
 
                 
                 
            });
   
          var div1=  $('#div1').dialog({
                title: 'My Dialog',
                width: 500,
                height: 400,
                closed: true,
                cache: false,
                modal: true
            });
          
      
        });
        
  
        
        
        
         
        
        
    </script>
</html>