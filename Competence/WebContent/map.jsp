<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>This is baidu map</title>
		<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		</style>
		<script type="text/javascript" src="JS/jquery.min.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=1XPG1MxxQlVAkkZlq4YsrOvA3KBlNUYn"></script>
</head>
<body>
<div id="mapone" style="width:1000px;height:1000px;"></div>
</body>
<script type="text/javascript">
//地图引入API功能
	var map = new BMap.Map("mapone");
	var point = new BMap.Point(113.64964385,34.7566100641);
	map.centerAndZoom(point,12);
	var myGeo = new BMap.Geocoder();
// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint("郑州市", function(point){
		if (point) {
			map.centerAndZoom(point, 16);
			map.addOverlay(new BMap.Marker(point));
		}else{
			alert("您选择地址没有解析到结果!");
		}
	}, "郑州市");
//弹跳点
	var marker = new BMap.Marker(point);
	map.addOverlay(marker);
	marker.setAnimation(BMAP_ANIMATION_BOUNCE);
//滑轮放大放小
	map.enableScrollWheelZoom(true);
//开启拉框放大	
	var myDrag = new BMapLib.RectangleZoom(map, {
		followText: "拖拽鼠标进行操作"
	});
	myDrag.open();
</script>
</html>