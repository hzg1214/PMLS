layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    initMap();
 // 创建和初始化地图函数：
    function initMap() {
    	var latitude = $("#latitude").val();
    	var longitude = $("#longitude").val();
    	if(latitude!=null && longitude!=null){
    		createMap(latitude, longitude);// 创建地图
    		setMapEvent();// 设置地图事件
    		addMapControl();// 向地图添加控件
    		addMapOverlay(latitude, longitude);// 向地图添加覆盖物
    	}
    }
    function createMap(latitude, longitude) {
    	map = new BMap.Map("map");
    	map.centerAndZoom(new BMap.Point(longitude, latitude), 17);
    }
    function setMapEvent() {
    	map.enableScrollWheelZoom();
    	map.enableKeyboard();
    	map.enableDragging();
    	map.enableDoubleClickZoom();
    }
    function addClickHandler(target, window) {
    	target.addEventListener("click", function() {
    		target.openInfoWindow(window);
    	});
    }
    function addMapOverlay(latitude, longitude) {
    	var markers = [ {
    		content : "公司",
    		title : "我的位置",
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
    }

});