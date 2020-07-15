
$(function() {
	init();
});


//创建和初始化地图函数：
function init() {
	var latitude=$("#latitude").val();
	var longitude=$("#longitude").val();
	var name=$("#storeName").val();
	var address=$("#addressDetail").val();
	var createLongitude=$("#createLongitude").val();
	var createLatitude=$("#createLatitude").val();
	if(latitude!=undefined && latitude!="" && longitude!=undefined && longitude!=""){
		//此处由于地图所有tab默认不显示，需要在显示后再绘图及打点
		if($("#storeMapTab").length == 1){
			$("#storeMapTab").click(function(){
				$("#storeMap").addClass("active");
				if(!initPointFlag){
					initPointFlag = true;
					initMapStore(name,address,latitude, longitude,createLatitude,createLongitude);
				}
			});
		}else{
			initMapStore(name,address,latitude, longitude,createLatitude,createLongitude);
		}
	}else{
		map = new BMap.Map("mapDiv");
		map.centerAndZoom("上海", 15);
	}
}

var initPointFlag = false;
function initMapStore(name,address,latitude, longitude,createLatitude,createLongitude){
	if($('#panorama').length == 1){
		createPanorama(latitude,longitude);
	}
	createMap(latitude, longitude);// 创建地图
	setMapEvent();// 设置地图事件
	addMapControl();// 向地图添加控件
	var myIcon = new BMap.Icon(BASE_PATH+"/meta/images/crm/store_marker.png", new BMap.Size(22,31));
	addMapOverlay(name,address,latitude, longitude,myIcon);// 向地图添加覆盖物
	if(!$("#storeMapTab").length == 1){
		addMapOverlay("创建人位置","",createLatitude, createLongitude);// 向地图添加覆盖物
	}
}
function createMap(latitude, longitude) {
	map = new BMap.Map("mapDiv");
	map.centerAndZoom(new BMap.Point(longitude, latitude), 20);
}
function createPanorama(latitude, longitude){
	var panorama = new BMap.Panorama('panorama'); //默认为显示导航控件
	panorama.setPosition(new BMap.Point(longitude, latitude));
}
function setMapEvent() {

}
function addClickHandler(target, window) {

}
function addMapOverlay(title,content,latitude, longitude,myIcon) {
	var markers = [ {
		title : title,
		content : content,
		imageOffset : {
			width : 0,
			height : 3
		},
		position : {
			lat : latitude,
			lng : longitude
		}
	} ];
	for (var index = 0; index < markers.length; index++) {
		var point = new BMap.Point(markers[index].position.lng,
				markers[index].position.lat);
		var marker=null;
		if(myIcon!=null){
			marker = new BMap.Marker(point,{icon : myIcon});
		}else{
			marker = new BMap.Marker(point);
		}

		var label = new BMap.Label(markers[index].title, {
			offset : new BMap.Size(25, 5)
		});
		marker.setLabel(label);
		marker.disableDragging();//不可拖拽
		marker.data=markers[index];
		marker.addEventListener("click",function(e){
			var p = e.target;
			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
			if(p.data.content==""){
				var myGeo = new BMap.Geocoder();
				myGeo.getLocation(point, function (rs) {
					var addComp = rs.addressComponents;
					var addressDetail = addComp.province + "" + addComp.city + "" + addComp.district + addComp.street + "" + addComp.streetNumber;
					console.log("当前地址："+addressDetail);
					var content=p.data.title+"<br/>地址："+addComp.district + addComp.street + addComp.streetNumber;
					var opts={
						width : 400,     // 信息窗口宽度
						height: 50,     // 信息窗口高度
						enableMessage:false//设置允许信息窗发送短息
					};
					var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
					map.openInfoWindow(infoWindow,point); //开启信息窗口
				});
			}else{
				var content=p.data.title+"<br/>地址："+p.data.content;
				var opts={
					width : 200,     // 信息窗口宽度
					height: 50,     // 信息窗口高度
					enableMessage:false//设置允许信息窗发送短息
				};
				var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
				map.openInfoWindow(infoWindow,point); //开启信息窗口
			}

		});
		map.addOverlay(marker);
	}
	;
}
// 向地图添加控件
function addMapControl() {
	var scaleControl = new BMap.ScaleControl({
		anchor : BMAP_ANCHOR_BOTTOM_LEFT
	});
	scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
	map.addControl(scaleControl);
	var navControl = new BMap.NavigationControl({
		anchor : BMAP_ANCHOR_TOP_LEFT,
		type : BMAP_NAVIGATION_CONTROL_LARGE
	});
	map.addControl(navControl);

}