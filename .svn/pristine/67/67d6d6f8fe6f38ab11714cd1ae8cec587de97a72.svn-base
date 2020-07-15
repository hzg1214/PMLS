
$(function() {
	init();
});

/**
 * 标记地图
 */
function btnMark(){
	var cityNo=$("#cityNo").val();
	var districtNo=$("#districtNo").val();
	var areaNo=$("#areaNo").val();
	var city=$("#cityNo").find("option:selected").text();;
	var district=$("#districtNo").find("option:selected").text();;
	var area=$("#areaNo").find("option:selected").text();;
	var address=$("#address").val();
	if(districtNo==undefined || districtNo==""){
		Dialog.alertError("请选择城市、区域");
		return;
	}
	var detailAddress=city.trim()+district.trim()+area.trim()+address;	
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint(detailAddress, function(point){
		if (point) {
			var latitude=point.lat;
			var longitude=point.lng;
			var name=$("#name").val();
			if(name.length > '20'){
				name = name.substring(0,20)+"...";
			}
			var address=$("#address").val();
			initMap(name,address,latitude, longitude);
			$("#latitude").val(latitude);
			 $("#longitude").val(longitude);
		}else{
			Dialog.alertError("您选择地址没有解析到结果!");
		}
	}, city.trim());
}

//创建和初始化地图函数：
function init() {
	var latitude=$("#latitude").val();
	var longitude=$("#longitude").val();
	var name=$("#name").val();
	var address=$("#address").val();
	if(latitude!=undefined && latitude!="" && longitude!=undefined && longitude!=""){
		initMap(name,address,latitude, longitude);
	}else{
		map = new BMap.Map("mapDiv");
		map.centerAndZoom($("#cityName").val(), 14);
	}
}
function initMap(name,address,latitude, longitude){
	createMap(latitude, longitude);// 创建地图
	setMapEvent();// 设置地图事件
	addMapControl();// 向地图添加控件
	addMapOverlay(name,address,latitude, longitude);// 向地图添加覆盖物
}
function createMap(latitude, longitude) {
	map = new BMap.Map("mapDiv");
	map.centerAndZoom(new BMap.Point(longitude, latitude), 17);
}
function setMapEvent() {
	map.enableScrollWheelZoom();
	map.enableKeyboard();
	map.enableDragging();
	map.enableDoubleClickZoom();
	map.addEventListener("dragend", function(){    
		 var center = map.getCenter();
		 var name=$("#name").val();
		 var address=$("#address").val();
		 $("#latitude").val(center.lat);
		 $("#longitude").val(center.lng);
		 map.clearOverlays();
		 addMapOverlay(name,address,center.lat, center.lng);
//		 alert("地图中心点变更为：" + center.lng + ", " + center.lat);    
		});
}
function addClickHandler(target, window) {
//	target.addEventListener("click", function() {
//		target.openInfoWindow(window);
//	});
	target.addEventListener("dragend", function(e){
		 $("#latitude").val(e.point.lat);
		 $("#longitude").val(e.point.lng);

//		 var pt = e.point;
//		 var geoc = new BMap.Geocoder();
//			geoc.getLocation(pt, function(rs){
//				var addComp = rs.addressComponents;
//				$("#address").val(addComp.street+addComp.streetNumber);
////				Dialog.alertError(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
//			});   
		});
}
/**
 * @param title--门店名称
 * @param content--门店地址
 * @param latitude--门店纬度
 * @param longitude--门店经度
 */
function addMapOverlay(title,content,latitude, longitude) {
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
		var marker = new BMap.Marker(
				point,
				{
					icon : new BMap.Icon(
							"http://api.map.baidu.com/lbsapi/createmap/images/icon.png",
							new BMap.Size(20, 25), {
								imageOffset : new BMap.Size(
										markers[index].imageOffset.width,
										markers[index].imageOffset.height)
							})
				});
		var label = new BMap.Label(markers[index].title, {
			offset : new BMap.Size(25, 5)
		});
		var opts = {
			width : 200,
			title : markers[index].title,
			enableMessage : false
		};
		var infoWindow = new BMap.InfoWindow(markers[index].content, opts);
		marker.setLabel(label);
		marker.enableDragging();
		addClickHandler(marker, infoWindow);
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
	/*var overviewControl = new BMap.OverviewMapControl({
		anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
		isOpen : true
	});
	map.addControl(overviewControl);*/
}