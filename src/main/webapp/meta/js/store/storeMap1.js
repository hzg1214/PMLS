$(function () {
    $("#mapDiv").height($(window).height() - 165);
    cityName = $("#cityName").val();
    getOrgCode();
    initContractType();
    initShoupaiType();
    init();
});

var orgCode;
function getOrgCode() {
    var url = BASE_PATH + "/commons/queryOrgList";
    var params = {};
    ajaxGet(url, params, function (data) {

        var list = data.returnValue;
        if (list != null && list.length > 0) {
          //  orgCode = list[list.length - 1].orgCode;
        	orgCode='1082';
            initCityList();
        }
    }, function () {
    });
}

function initCityList() {
    var url = BASE_PATH + "/commons/queryCityList";
    var params = {orgCode: orgCode};
    ajaxGet(url, params, function (data) {
        var list = data.returnValue;
        if (list != null && list.length > 0) {
            var result = '<option value="">请选择</option>';
            for (var i = 0; i < list.length; i++) {
                result += "<option value='" + list[i].cityNo + "'>"
                    + list[i].cityName + "</option>";
            }

            $("#city").html('');
            $("#city").append(result);
        }
    }, function () {
    });

    $("#city").change(
        function () {
            var cityNo = $("#city").val();
            resetCenter();
            if (isBlank(cityNo)) {
                return;
            }

            initCenterList();
        });
}

function initCenterList() {
    var url = BASE_PATH + "/commons/queryCenterList";
    ajaxGet(url, {cityNo: $("#city").val(), orgCode: orgCode}, function (data) {
        var list = data.returnValue;
        if (list != null && list.length > 0) {
            var html = '';
            for (var i = 0; i < list.length; i++) {
                html = html + '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].centerId + '" data-text="' + list[i].centerName + '"><span>' + list[i].centerName + '</span></label> </li>';
            }

            $('#centerId').find('.multi-select-list').append(html);
        }

    }, function () {
    });
}

var centerIdStr = '';
$('#center').find('.multi-select').multiSelect({
    check: function ($instance) {
        centerIdStr = $instance.value.toString();
    }
});

function resetCenter() {
    centerIdStr = '';
    $('#centerId').find('.multi-select-item').not(':first').remove();
    $('#centerId').find('.multi-select-checkall').removeAttr("checked");
    $('#centerId').find('.multi-select-value').val('');
    $('#centerId').find('.multi-select-text').val('');
    $('#centerId').find('.multi-select-text').text('');
}

function initContractType() {
    var html = '';
    html = html + '<li class="multi-select-item"> <label><input type="checkbox" value="sp" data-text="授牌"><span>授牌</span></label> </li>';
    html = html + '<li class="multi-select-item"> <label><input type="checkbox" value="B" data-text="B"><span>B</span></label> </li>';
    $('#contractType').find('.multi-select-list').append(html);
}

function initShoupaiType() {
    var html = '';
    for (var i = 0; i < shoupaiTypeList.length; i++) {
        html += '<li class="multi-select-item"> <label><input type="checkbox" value="' + shoupaiTypeList[i].dicCode + '" data-text="' + shoupaiTypeList[i].dicValue + '"><span>' + shoupaiTypeList[i].dicValue + '</span></label> </li>';
    }
    $('#shoupaiType').find('.multi-select-list').append(html);
}

var contractTypeStr = '';
$('#contractType').find('.multi-select').multiSelect({
    check: function ($instance) {
        contractTypeStr = '';
        var arr = $instance.value;
        var isShoupai = false;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == 'sp') {
                isShoupai = true;
            }
            if (i != arr.length - 1){
                contractTypeStr += arr[i] + "','";
            }else{
                contractTypeStr += arr[i];
            }
        }
        if (isShoupai) {
            $('#shoupaiType').show();
        }else{
            $('#shoupaiType').hide();
            resetShoupaiType();
        }
    }
});

var shoupaiTypeStr = '';
$('#shoupaiType').find('.multi-select').multiSelect({
    check: function ($instance) {
        shoupaiTypeStr = '';
        var arr = $instance.value;
        for (var i = 0; i < arr.length; i++) {
            if (i != arr.length - 1){
                shoupaiTypeStr += arr[i] + "','";
            }else{
                shoupaiTypeStr += arr[i];
            }
        }
    }
});
function resetShoupaiType() {
    shoupaiTypeStr = '';
    $('#shoupaiType').find('.multi-select-item input').removeAttr("checked");
    $('#shoupaiType').find('.multi-select-value').val('');
    $('#shoupaiType').find('.multi-select-text').val('');
    $('#shoupaiType').find('.multi-select-text').text('');
}

var mapStoreData = [];//门店集合
var centerList=[];//中心集合
var mk_count;

//创建和初始化地图函数：
function init() {
    map = new BMap.Map("mapDiv", {minZoom: 5, maxZoom: 20, enableMapClick:false});
    map.centerAndZoom($("#cityName").val(), 16);
    setMapEvent();// 设置地图事件
    addMapControl();// 向地图添加控件

    function tilesLoaded() {
        var cp = map.getCenter();//获取当前中心点
        console.log("地图加载完毕,中心坐标：" + cp.lng + "," + cp.lat);
        var bs = map.getBounds();   //获取可视区域
        var bssw = bs.getSouthWest();   //可视区域左下角
        //var bsne = bs.getNorthEast();   //可视区域右上角
        //console.log("当前地图可视范围是：" + bssw.lng + "," + bssw.lat + "到" + bsne.lng + "," + bsne.lat);
        var distance = map.getDistance(new BMap.Point(cp.lng, cp.lat), new BMap.Point(bssw.lng, bssw.lat)).toFixed(0);
        var zoom = map.getZoom();
        console.log('中心点到左下角的距离是：' + distance + ' 米,缩放级别：' + zoom);

        //加载数据
        optionsData.longitude = cp.lng;
        optionsData.latitude = cp.lat;
        optionsData.distance = distance;
        if (distance != 0 && zoom >= 15) {//缩放级别大于等于15，显示门店
            optionsData.showLevel='';
            if(centerList.length>0){
                map.clearOverlays();//清空标记
                centerList=[];
            }
            map.removeOverlay(mk_count);
            loadStoreData(optionsData);
        } else if(zoom >= 9 && zoom<15){//缩放级别大于等于9，小于15，显示中心
            if(optionsData.showLevel=='CENTER'){
                return;
            }
            optionsData.showLevel='CENTER';
            mapStoreData = [];
            map.clearOverlays();
            loadStoreCount(optionsData);
        } else {//缩放级别小于9，显示城市
            if(optionsData.showLevel=='CITY'){
                return;
            }
            mapStoreData = [];
            map.clearOverlays();
            optionsData.showLevel='CITY';
            loadStoreCount(optionsData);

        }
        // 必须放到最后执行
        setStyle();
    }

    map.addEventListener("tilesloaded", tilesLoaded);
}

function setStyle() {
    map.setMapStyleV2({
        styleJson:[{
            "featureType": "subway",
            "elementType": "geometry",
            "stylers": {
                "visibility": "off"
            }
        }, {
            "featureType": "subwaystation",
            "elementType": "geometry",
            "stylers": {
                "visibility": "off"
            }
        }, {
            "featureType": "manmade",
            "elementType": "labels",
            "stylers": {
                "visibility": "off"
            }
        }, {
            "featureType": "manmade",
            "elementType": "geometry",
            "stylers": {
                "visibility": "on"
            }
        }, {
            "featureType": "building",
            "elementType": "geometry",
            "stylers": {
                "visibility": "on"
            }
        }, {
            "featureType": "subwaylabel",
            "elementType": "labels",
            "stylers": {
                "visibility": "off"
            }
        }, {
            "featureType": "subwaylabel",
            "elementType": "labels.icon",
            "stylers": {
                "visibility": "off"
            }
        }, {
            "featureType": "poilabel",
            "elementType": "labels",
            "stylers": {
                "visibility": "off"
            }
        }, {
            "featureType": "poilabel",
            "elementType": "labels.icon",
            "stylers": {
                "visibility": "off"
            }
        }, {
            "featureType": "railway",
            "elementType": "geometry",
            "stylers": {
                "visibility": "off"
            }
        }, {
            "featureType": "highway",
            "elementType": "geometry",
            "stylers": {
                "visibility": "on"
            }
        }, {
            "featureType": "highway",
            "elementType": "geometry.fill",
            "stylers": {
                "color": "#ffffffff"
            }
        }, {
            "featureType": "road",
            "elementType": "geometry.fill",
            "stylers": {
                "color": "#ffffffff"
            }
        }, {
            "featureType": "highway",
            "elementType": "geometry.stroke",
            "stylers": {
                "color": "#ffffffff"
            }
        }, {
            "featureType": "highway",
            "elementType": "labels",
            "stylers": {
                "visibility": "on"
            }
        }]
    });
}

var cityName;
var optionsData = {};
function search() {


    var cityNo = $("#city").val();
    if (isBlank(cityNo)) {
        Dialog.alertError("请选择城市！");
        return;
    }
    optionsData = {};
    optionsData.contractType = contractTypeStr;
    optionsData.shoupaiType = shoupaiTypeStr;
    optionsData.cityNo = cityNo;
    optionsData.centerId = centerIdStr;
    optionsData.brand = $("#brand").val();
    cityName = $("#city").find("option:selected").text();

    //中心不为空，地图中心默认为选中的第一个
    if(centerIdStr!=null && centerIdStr!=''){
        $.ajax({
            url: "/storeMap/getCenterPosition",
            data: {centerId:centerIdStr},
            async: false,
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                if(data!=null ){
                    var pt = new BMap.Point(data[0].longitude, data[0].latitude);
                    map.centerAndZoom(pt, 16);
                }
            }
        });
    }else{
        map.centerAndZoom(cityName, 16);
    }
    var cp = map.getCenter();//获取当前中心点
    var bs = map.getBounds();   //获取可视区域
    var bssw = bs.getSouthWest();   //可视区域左下角
    var distance = map.getDistance(new BMap.Point(cp.lng, cp.lat), new BMap.Point(bssw.lng, bssw.lat)).toFixed(0);

    optionsData.longitude = cp.lng;
    optionsData.latitude = cp.lat;
    optionsData.distance = distance;

    //清空标志
    mapStoreData = [];
    map.clearOverlays();

}

function loadStoreData(optionsData) {
    $.ajax({
        url: "/storeMap/getMapInfo",
        data: optionsData,
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            if (data != null) {
                if (data.returnData != null) {
                    for (var i = 0; i < data.returnData.length; i++) {
                        var obj = data.returnData[i];
                        var flag = true;//默认显示
                        for (var j = 0; j < mapStoreData.length; j++) {
                            if (mapStoreData[j].storeId == obj.storeId) {
                                //已存在的不重复显示在地图上
                                flag = false;
                            }
                        }
                        if (flag) {
                            var pt = new BMap.Point(data.returnData[i].longitude, data.returnData[i].latitude);
                            var myIcon = new BMap.Icon("/meta/images/crm/" + data.returnData[i].mapMarkerStyle + ".png", new BMap.Size(18, 25));
                            var mk = new BMap.Marker(pt, {icon: myIcon});  // 创建标注
                            mk.storeData = obj;
                            mk.addEventListener("click", function (e) {
                                var p = e.target;
                                var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
                                var opts = {
                                    width: 200,     // 信息窗口宽度
                                    height: 60,     // 信息窗口高度
                                    enableMessage: false//设置允许信息窗发送短息
                                }
                                var content = "<div class='markerInfo' data-storeid='" + p.storeData.storeId + "'>" + p.storeData.name +"（"+ p.storeData.storeNo+"）"+ "<br/>地址：" + p.storeData.addressDetail + "</div>";
                                var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
                                map.openInfoWindow(infoWindow, point); //开启信息窗口
                            });
                            map.addOverlay(mk);
                            console.log("添加门店图标：" + obj.storeId);
                        }
                    }
                    mapStoreData = data.returnData;//更新最新集合
                }
            } else {
                Dialog.alertError("获取附近门店失败");
            }
        }
    });
}

function loadStoreCount(optionsData) {
    $.ajax({
        url: "/storeMap/getStoreCount",
        data: optionsData,
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            centerList=data;
            //var newIcon = new BMap.Icon("/meta/images/crm/marker_position.png", new BMap.Size(30, 47));

            for(var i=0;i<data.length;i++){
                if(data[i].longitude!=null){
                    var point = new BMap.Point(data[i].longitude, data[i].latitude);
                    var txt = data[i].centerName;
                    var mouseoverTxt = txt + " " + data[i].storeNum + "家" ;
                    var myCompOverlay = new ComplexCustomOverlay(point, mouseoverTxt,mouseoverTxt);
                    map.addOverlay(myCompOverlay);
                }else{
                    var cityName = data[i].cityName;
                    if(cityName==null||cityName==''){
                        return;
                    }


                    var myGeo = new BMap.Geocoder();
                    myGeo.centerData=data[i];
                    // 将地址解析结果显示在地图上,并调整地图视野
                    myGeo.getPoint(cityName, function(point,e){
                        var p= e.city;
                        if (point) {
                            for(var j=0;j<centerList.length;j++){
                                if(p.indexOf(centerList[j].cityName)>=0){
                                    var txt = centerList[j].cityName;
                                    var mouseoverTxt = txt + " " + centerList[j].storeNum + "家" ;
                                    var myCompOverlay = new ComplexCustomOverlay(point, mouseoverTxt,mouseoverTxt);
                                    map.addOverlay(myCompOverlay);
                                }
                            }
                        }
                    }, cityName);
                }


                /*mk_count = new BMap.Marker(point, {icon: newIcon});  // 创建标注
                mk_count.centerData = data[i];
                mk_count.addEventListener("click", function (e) {
                    var p = e.target;
                    var point = new BMap.Point(p.centerData.longitude, p.centerData.latitude);
                    var opts = {
                        width: 0,     // 信息窗口宽度
                        height: 0,     // 信息窗口高度
                        enableMessage: false//设置允许信息窗发送短息
                    }
                    var content = "<div class='markerInfo' data-count='" + p.centerData.storeNum + "'>" + p.centerData.centerName + "门店数量：" + p.centerData.storeNum + "</div>";
                    var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
                    map.openInfoWindow(infoWindow, point); //开启信息窗口
                });

                map.addOverlay(mk_count);// 将标注添加到地图中*/
            }

        }
    });
}

function setMapEvent() {
    map.enableScrollWheelZoom();
    map.enableKeyboard();
    map.enableDragging();
    map.enableDoubleClickZoom();
}

// 向地图添加控件
function addMapControl() {
    /*var scaleControl = new BMap.ScaleControl({
        anchor: BMAP_ANCHOR_BOTTOM_LEFT
    });
    scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
    map.addControl(scaleControl);
    var navControl = new BMap.NavigationControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        type: BMAP_NAVIGATION_CONTROL_LARGE
    });
    map.addControl(navControl);*/
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    map.addControl(top_left_navigation);
}


// 复杂的自定义覆盖物
function ComplexCustomOverlay(point, text, mouseoverText){
    this._point = point;
    this._text = text;
    this._overText = mouseoverText;
}
ComplexCustomOverlay.prototype = new BMap.Overlay();
ComplexCustomOverlay.prototype.initialize = function(map){
    this._map = map;
    var div = this._div = document.createElement("div");
    div.style.position = "absolute";
    div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
    div.style.backgroundColor = "#EE5D5B";
    div.style.border = "1px solid #BC3B3A";
    div.style.color = "white";
    div.style.height = "24px";
    div.style.padding = "0px 5px 0px 5px";
    div.style.lineHeight = "24px";
    div.style.whiteSpace = "nowrap";
    div.style.MozUserSelect = "none";
    div.style.fontSize = "12px"
    var span = this._span = document.createElement("span");
    div.appendChild(span);
    span.appendChild(document.createTextNode(this._text));
    var that = this;

    var arrow = this._arrow = document.createElement("div");
    arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
    arrow.style.position = "absolute";
    arrow.style.width = "11px";
    arrow.style.height = "10px";
    arrow.style.top = "22px";
    arrow.style.left = "10px";
    arrow.style.overflow = "hidden";
    div.appendChild(arrow);

    div.onmouseover = function(){
        this.style.backgroundColor = "#6BADCA";
        this.style.borderColor = "#0000ff";
        this.getElementsByTagName("span")[0].innerHTML = that._overText;
        arrow.style.backgroundPosition = "0px -20px";
    }

    div.onmouseout = function(){
        this.style.backgroundColor = "#EE5D5B";
        this.style.borderColor = "#BC3B3A";
        this.getElementsByTagName("span")[0].innerHTML = that._text;
        arrow.style.backgroundPosition = "0px 0px";
    }

    map.getPanes().labelPane.appendChild(div);

    return div;
}
ComplexCustomOverlay.prototype.draw = function(){
    var map = this._map;
    var pixel = map.pointToOverlayPixel(this._point);
    this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
    this._div.style.top  = pixel.y - 30 + "px";
}