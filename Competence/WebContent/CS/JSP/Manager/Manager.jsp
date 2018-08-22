<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../context.jsp" %>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>layout 后台大布局 - Layui</title>

<style type="text/css">

</style>
</head>


<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">layui 后台布局</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退了</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" id="zlie"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">订单管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">订单列表</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">员工管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">员工列表</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
          <li class="layui-nav-item">
          <a href="javascript:;">回收站</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">移除员工</a></dd>
            <dd><a href="javascript:;">移除订单</a></dd>
            <dd><a href="javascript:;">移除厨师</a></dd>
            <dd><a href="javascript:;">移除</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="">云市场</a></li>
        <li class="layui-nav-item"><a href="">发布商品</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <iframe></iframe>
    <div style="padding: 15px;">内容主体区域</div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © JDFW.com - 认真工作谢谢合作
  </div>
</div>

<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  /* 得到内容的左侧导航的列表 */
  $.ajax({
	  url:'text_menu',  //菜单列表的url() 
	  anync:false,
	  success:function(data){
		  alert(data);
		  var json = JSON.parse(data);
		  for(var i=0;i<json.length;i++){
			  var a	= "<li class=\"layui-nav-item\">"+
			          "<a href=\"javascript:;\">"+json[i].TMENUNAME+"</a>"+
			          "<dl class=\"layui-nav-child\">";
			          alert(JSON.parse(json[i].list.length))
			          alert(json[i].list);
					for(var j=0;j<json[i].list.length;j++){
						a+=	"<dd><a href=\"javascript:;\" onclick=\"ans(this)\" name=\""+json[i].list[j].TMENUDESC+"\" >"+json[i].list[j].TMENUNAME+"</a></dd>";
					}         
	        			
	           
	         a+= "</dl>"+
	        	 "</li>";
			
			 
	         $("#zlie").append(a);  
		  }
		  
		  alert(a);
		  element.init();
		
	  },
	  error:function(){
		  
	  }
	  
  });
  

  
});
function ans(Ans){
	  alert("我是url:"+Ans.name);
	  
}
</script>
</body>
</html>