<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<title>茶部落</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script src="${pageContext.request.contextPath}/static/jquery.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/fqqf/amazeui.min.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/static/css/amazeui.min.css"
	type="text/css" rel="stylesheet" />

<link href="${pageContext.request.contextPath}/static/css/style.css"
	type="text/css" rel="stylesheet" />
</head>
<body>
	<header data-am-widget="header"
		class="am-header am-header-default sq-head ">
	<div class="am-header-left am-header-nav" style="padding-top: 5px">
		<a href="#">取消</a>
		<!-- 返回点击页面  url -->
	</div>
	<p align="center">第10桌</p>

	<div class="am-header-right am-header-nav">
		<button type="button" class="am-btn am-btn-warning"
			id="doc-confirm-toggle"
			style="background: none; border: 0; font-size: 24px;">
			<i class="am-header-icon am-icon-trash"></i>
		</button>
	</div>
	</header>

	<!-- 导航 -->
	<div class="content-list" id="outer">
		<div class="list-left" id="tab">
			<li class="current"><a style="position: relative;">新品推荐<span
					class="am-badge am-badge-danger am-round" id="dwl1">6</span></a></li>


		</div>


		<!-- 内容 -->

		<div class="list-right" id="content">
			<ul class="list-pro">
			</ul>
		</div>
	</div>
	<!--底部-->
	<form method="post">
		<div style="height: 100px;"></div>
		<div class="fix-bot" id="hj">
			<a href="JavaScript:void(0);" class="list-js">合计：<i>0</i>元<em>(0)份</em></a>

			<input type="submit"
				style="border: none; margin-top: 0px; margin-right: 0px; height: 43px"
				value="选好了" onclick="sumbit()" class="list-jsk" /> <input
				type="hidden" id="name" name="name" /> <input type="hidden"
				id="qian" name="qian" />

		</div>
	</form>

	<!-- 右上角按钮点击后触发 -->
	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
		<div class="am-modal-dialog">
			<div class="am-modal-bd" style="height: 80px; line-height: 80px;">您确定要清空选择吗？</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					class="am-modal-btn" data-am-modal-confirm>确定</span>
			</div>
		</div>
	</div>

	<script>




//购物数量加减

function a(a){
	$(a).addClass('current');

	$("#tab li").removeClass('current');
	
}

	$(function(){
		$.ajax({
			url:'http://localhost:8088/XBMMavenSSM/Food/getAllFood', //菜 的url()
			async:true,
			success:function(data){
				//alert(data);
				var json = JSON.parse(data);
			
				for(var i=0;i<=json.length-1;i++){
					var list = json[i].list;		
					var lie ="<li><a  href=\"#dw"+json[i].id+"\">"+json[i].name+
							"<span class=\"am-badge am-badge-danger am-round\" id=\"dwl"+json[i].id+"\"></span></a></li>"; 
					$("#tab").append(lie);
					
					
					$("#content ul").append("<li><h4 id=\"dw"+json[i].id+"\">"+json[i].name+"</h4></li>");
					for(var j=0;j<=list.length-1;j++){
						var text =	    	
				    	"<li>"+
				    		"<a href=\"detail.html\"><img src=\""+list[j].URL+"\" class=\"list-pic\"/></a>"+
				    		"<div class=\"shop-list-mid\">"+
			                	"<div class=\"tit\"><a href=\"detail.html\">"+list[j].MYFOODNAME+"</a></div>"+
			                	"<div class=\"am-gallery-desc\">￥"+list[j].PRICE+"</div>"+
			                "</div>"+
			                "<div class=\"list-cart\">"+
				                "<div class=\"d-stock\">"+
						             "<a class=\"decrease\" name=\"dwl"+json[i].id+"\">-</a>"+
						             "<input class=\"text_box\" name=\"shu\" type=\"text\" value=\"0\">"+
						             "<input name=\"qian\" type=\"hidden\" value=\""+list[j].PRICE+"\">"+
						             "<input name=\"name\" type=\"hidden\" value=\""+list[j].MYFOODNAME+"\">"+      	
						             "<a class=\"increase\" name=\""+json[i].id+"\">+</a>"+
				                "</div>"+
				             "</div>"+ 
				           "</li>";
						$("#content ul").append(text);
					}	 
					
				}
				
				/* var lie ="<li class=\"current\"><a  href=\"#dw"+json.id+"\" style=\"position: relative;\">"+json.name+
				"<span class=\"am-badge am-badge-danger am-round\" id=\"dwl"+json.id+"\"></span></a></li>";
				var lie1="<li><a href=\"#dw"++"\">"+data.name+"</a></li>";
				$("#tab").append(lie);
				 */
				 
			     /* 控制选择 */
				/*  var $li = $('#tab li');
	             var $ul = $('#content ul');
	        
	             $li.click(function(){
	                     var $this = $(this);
	                     var $t = $this.index();
	                     $li.removeClass();
	                     $this.addClass('current');
	                    // $ul.css('display','none');
	                     $ul.eq($t).css('display','block');
	                
	             }) */
	        /* 控制选择  end*/
	             
	        /* + 号按钮 */
	             $('.increase').click(function(){
	     			var self = $(this);
	     			
	     			//改变
	     			var dwl =$("#"+(self.attr('name')));
	     			if(dwl.text().length==0){
	     				dwl.text(1);	
	     			}else{
	     				dwl.text(parseInt(dwl.text())+1);
	     			}			
	     			alert("id:"+self.attr('name'));
	     			var current_num = parseInt(self.siblings('input[name="shu"]').val());
	     			current_num += 1;
	     			hjj(self,current_num);
	     			if(current_num > 0){
	     				self.siblings(".decrease").fadeIn();
	     				self.siblings(".text_box").fadeIn();
	     			}
	     			self.siblings('input[name="shu"]').val(current_num);
	     			//self.siblings('input[name="name"]').val(current_num);
	     			//update_item(self.siblings('input').data('item-id'));
	     		});
	       /* + 号按钮  end */
	       
	       
	       /* - 号按钮 */      
	         	$('.decrease').click(function(){
	    			var self = $(this);
	    			var current_num = parseInt(self.siblings('input[name="shu"]').val());
	    			
	    			//改变
	    			var dwl =$("#"+(self.attr('name')));
	    			if(dwl.text()==1){
	    				dwl.text("");
	    			}else{
	    				dwl.text(parseInt(dwl.text())-1);	
	    			}
	    			
	    			
	    			if(current_num > 0){
	    				current_num -= 1;
	                    if(current_num < 1){
	    	                self.fadeOut();
	    					self.siblings(".text_box").fadeOut();
	                    }
	                    hjr(self,current_num);
	                
	        			
	    				self.siblings('input[name="shu"]').val(current_num);
	    				//update_item(self.siblings('input').data('item-id'));
	    			}
	    		});
	        /* - 号按钮 end */        
	             
			},
			error:function(){
				alert("失败");
			}
			
		});
	
	});
	
     
      function hjj(ss,gs){
      	  	  	
			var qian = parseInt($('#hj i').text()); //点餐共钱
		    var shu = parseInt(gs);  //菜数量
			var jg = parseInt(ss.siblings('input[name="qian"]').val()); //菜价格			
		    var cai  =  ss.siblings('input[name="name"]').val()+"("+jg+"元)X"; //组合菜名
		    var name =  $("#hj input[name='name']").val(); //所有菜名
		    
		   if(name.indexOf(cai)!=-1){
			 	name =  name.replace(cai+(shu-1),cai+shu);		
			 }else{
				 name = name +cai+shu+"<br/>";
			 }
		    
		   $("#hj input[name='name']").val(name);
		    alert("点的菜："+cai);
		    alert("商品价格:"+jg);
			alert("我是钱:"+($('#hj i').text(qian+jg)).text());
			alert("每个菜的id:"+$("#name").text());
			$("#hj input[name='qian']").val($('#hj i').text());
			//食物数量
			var foodCount=$("#hj em").text();
			alert(foodCount);
			alert("substring:"+foodCount.substr(1,1));
			/* if(foodCount.substr(1,1)=="0"){
				$("#hj em").text("("+shu+")份");
			}else{}  */
				$("#hj em").text("("+(parseInt(foodCount.substr(1,1))+parseInt(shu))+")份");
			
			
       }
       
       
       function hjr(ss,gs){	  	  	
			var qian = parseInt($('#hj i').text()); //点餐共钱
			var jg	 = parseInt(ss.siblings('input[name="qian"]').val()); //价格
			$('#hj i').text(qian-jg);	
			var shu = parseInt(gs);  //现在菜数量
		    var cai  =  ss.siblings('input[name="name"]').val()+"("+jg+"元)X"; //组合菜名
		    var name =  $("#hj input[name='name']").val(); //所有菜名
		    
		   	if(shu==0){
			 	 name = name.replace(cai+(shu+1)+"<br/>","");	
			 }else{
				 name =name.replace(cai+(shu+1),cai+shu);
			 }
		    
		   	alert("前面："+$("#hj input[name='name']").val());
		    
		   	$("#hj input[name='name']").val(name);
			
			alert("后面："+$("#hj input[name='name']").val());
			$("#hj input[name='qian']").val($('#hj i').text());
     }
       
       function sumbit(){
    	   
    	   alert("账单金额："+$("#hj i").text());
    	   alert("点餐内容："+$("#hj input[name='name']").val());
    	   alert("提交的钱"+$("#hj input[name='qian']").val());
       }
       
  
            
</script>
</body>
<script type="text/javascript">


$("a").click(function() {
   alert(1);
/* $("html, body").animate({
    scrollTop: $($(this).attr("href")).offset().top + "px"
}, {
    duration: fast,
    easing: "swing"
});  */
});
</script>
</html>
