<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	<link href="${pageContext.request.contextPath}/static/BootStrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/static/BootStrap/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/BootStrap/js/bootstrap.min.js"></script>
<title>桌子情况</title>
<style type="text/css">

</style>

</head>
	<body>
	<div class="container">
		<div class="row" style="padding-top: 10px" id="row">
		</div>
	</div>

<script type="text/javascript">
 $(function(){
	$.ajax({
		url:'http://localhost:8088/XBMMavenSSM/Table/getAllTable',
		anync:true,
		success:function(data){
			var json = JSON.parse(data);
			for(var i=0;i<json.length;i++){
				
				var a =	"<div class=\"col-lg-4 col-md-4 col-sm-4 col-xs-4\" style=\"padding-top: 15px\" \">";
				if(json[i].ISUSE.indexOf('使用中')!=-1){
					a+="";
				}else{
					a+=" <a href=\"\" onclick=\"dz(this)\" name=\""+json[i].MYTABLEID+"\">"; //a链接跳转
				}
						
				a+="<div style=\"width: 95%; border: 1px solid; background-color:#DDDDDD\" role=\"group\" class=\"btn\">"+
						"<p name=\"zh\" align=\"center\">"+json[i].MYTABLENAME+"</p>";
					if(json[i].ISUSE.indexOf('使用中')!=-1){
						a+="<span class=\"label label-default\">";
					}else{
						a+="<span class=\"label label-success\">";
					}
					a += json[i].ISUSE+"</span>"+
					"<p style=\"padding-top: 9px\" align=\"center\">消费"+json[i].HOWMONEY+"元</p>"+
				"</div>";
				if(json[i].ISUSE.indexOf('使用中')!=-1){
					a+="";
				}else{
					a+="</a>"; //a链接跳转
				}
				
			"</div>";
					
					
				$("#row").append(a);	
			}
			
			
		},
		error:function(){
			
		}
		
		
	});
	 
	 
	 
 });
 
 //传递参数
function dz(ans){
	 alert(ans.siblings('p[name="zh"]').text());

	alert("桌子id"+(ans.href=ans.href+"?zh="+ans.name+"&&zname="+nas)); //跳转并传输桌子:zid 桌子名字:zname 
	alert("测试是否先弹窗后跳转");
	
}



</script>
</body>
</html>
