var map;

$(function() {
	initFollowList();
	initMap();
});

Follow = function() {
	dialog: null;
};

/**
 * 加载门店的跟进列表
 */
initFollowList = function() {
	httpGet('storeDetailForm', 'LoadFollowCxt', BASE_PATH + '/follow/q',
			function() {
				pageInfo("storeDetailForm", function() {
					initFollowList();
				});

			});
};

/**
 * 查询
 * 
 */
Follow.search = function() {
	$('#curPage').val('1');
	initFollowList();
};

/**
 * @author lhd 查看详情
 */
Follow.Detail = function(followId) {
	var url = BASE_PATH + "/follow/" + followId;
	var params = {};
	var dialogOptions = {
		cancel : true,
		cancelVal : '返回'
	};
	// 跳转至跟进详情
	Dialog.ajaxOpenDialog(url, params, "跟进详情", function(dialog, resData) {
		Follow.dialog = dialog;
	}, dialogOptions);
};

/**
 * @author lhd 关闭弹出框
 */
Follow.close = function() {
	Follow.dialog.close();
};

/**
 * @author lhd
 * 进入创建跟进记录页面
 */
Follow.toAdd = function(storeId){
			var url = BASE_PATH + "/follow/c";
			var params = {
					storeId:storeId,
			};
			var dialogOptions = {
					ok : function() {
						
						// 确定
						var reback = Follow.add(storeId);

						return reback;
						
					},
					okVal : '确定',
					cancel : true,
					cancelVal : '返回'
			};
			// 跳转至添加跟进页面
			Dialog.ajaxOpenDialog(url, params, "添加跟进", function(dialog, resData) {
				Follow.dialog = dialog;
			}, dialogOptions);

};

/**
 * 创建
 */
Follow.add = function(storeId) {
	var url = BASE_PATH + '/follow';
	var backUrl = "/store/follow/"+storeId;
	// 校验输入信息
	if (Validator.validForm("followAddForm")) {
		systemLoading("", true);
		httpPost('followAddForm', url, function(data) {
			systemLoaded();
			location.href = BASE_PATH + backUrl;
			
		}, function(data) {
			
			Dialog.alertError(data.returnMsg);
			systemLoaded();
			return false;
		});
	} else {
		
		systemLoaded();
		return false;
	}
	return true;
};

/**
 * @author lhd
 * 进入编辑跟进记录页面
 */
Follow.toUpdate=function(followId,storeId){
	var url = BASE_PATH + "/follow/u";
	var params = {
			followId:followId
	};
	var dialogOptions = {
			ok : function() {
				
				// 确定
				var reback = Follow.update(followId,storeId);

				return reback;
			},
			okVal : '确定',
			cancel : true,
			cancelVal : '返回'
	};
	// 跳转至添加跟进页面
	Dialog.ajaxOpenDialog(url, params, "编辑跟进", function(dialog, resData) {
		Follow.dialog = dialog;
	}, dialogOptions);
};

/**
 * 修改
 */
Follow.update = function(followId,storeId) {
	var url = BASE_PATH + '/follow/' + followId;
	var params = $("#followEditForm").serialize();
	// 校验输入信息
	if (Validator.validForm("followEditForm")) {
		systemLoading("", true);
		httpPut(url, params, function(data) {
			location.href = BASE_PATH + "/store/"+storeId;
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
			return false;
		});
	} else {
		systemLoaded();
		return false;
	}
	return true;
};

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
	/*var overviewControl = new BMap.OverviewMapControl({
		anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
		isOpen : true
	});
	map.addControl(overviewControl);*/
}
