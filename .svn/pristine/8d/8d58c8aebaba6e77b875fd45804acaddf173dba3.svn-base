$(function () {
    init();
});


function init() {

    var info = eval('(' + $("#list").val() + ')');

    // 百度地图API功能
    map = new BMap.Map("mapDiv");

    var points = [];
    var startIcon = new BMap.Icon("/meta/images/crm/startPoint.png", new BMap.Size(30, 32));
    var endIcon = new BMap.Icon("/meta/images/crm/endPoint.png", new BMap.Size(30, 32));

    for (var i = 0; i < info.length; i++) {
        points.push(new BMap.Point(info[i].longitude, info[i].latitude));
        if (i == 0) {
            addMapOverlay("签到时间：" + info[i].followDate, "", info[i].latitude, info[i].longitude, startIcon);// 向地图添加覆盖物
        } else if (i == info.length - 1) {
            addMapOverlay("签到时间：" + info[i].followDate, "", info[i].latitude, info[i].longitude, endIcon);// 向地图添加覆盖物
        } else {
            addMapOverlay("签到时间：" + info[i].followDate, "", info[i].latitude, info[i].longitude);// 向地图添加覆盖物
        }
    }

    var view = map.getViewport(eval(points));
    var mapZoom = view.zoom;
    var centerPoint = view.center;
    map.centerAndZoom(centerPoint, mapZoom);

    var polyline = new BMap.Polyline(points, {
        strokeColor: "red",//设置颜色
        strokeWeight: 3, //宽度
        strokeOpacity: 0.5 //透明度
    });

    map.addOverlay(polyline);

    addMapControl();// 向地图添加控件

    if (info.length > 2) {
        //2个以上签到位置才执行
        var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/Mario.png", new BMap.Size(32, 70), {
            //offset: new BMap.Size(0, -5),
            imageOffset: new BMap.Size(0, 0)
        });

        window.run = function () {

            var carMk = new BMap.Marker(points[0], {icon: myIcon});
            map.addOverlay(carMk);
            var paths = points.length;
            i = 0;
            function resetMkPoint(i) {
                carMk.setPosition(points[i]);

                if (i < paths) {
                    setTimeout(function () {
                        i++;
                        if (i == paths) {
                            i = 0;
                        }
                        resetMkPoint(i);
                    }, 1000);
                }
            }

            setTimeout(function () {
                resetMkPoint(0);
            }, 1000)

        }

        setTimeout(function () {
            run();
        }, 1500);
    }

}

function addMapOverlay(title, content, latitude, longitude, myIcon) {
    var markers = [{
        title: title,
        content: content,
        imageOffset: {
            width: 0,
            height: 3
        },
        position: {
            lat: latitude,
            lng: longitude
        }
    }];

    for (var index = 0; index < markers.length; index++) {
        var point = new BMap.Point(markers[index].position.lng,
            markers[index].position.lat);
        var marker = null;
        if (myIcon != null) {
            marker = new BMap.Marker(point, {icon: myIcon});
        } else {
            marker = new BMap.Marker(point);
        }

        var label = new BMap.Label(markers[index].title, {
            offset: new BMap.Size(25, 5)
        });
        marker.setLabel(label);
        marker.disableDragging();//不可拖拽
        marker.data = markers[index];
        marker.addEventListener("click", function (e) {
            var p = e.target;
            var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
            if (p.data.content == "") {
                var myGeo = new BMap.Geocoder();
                myGeo.getLocation(point, function (rs) {
                    var addComp = rs.addressComponents;
                    var addressDetail = addComp.province + "" + addComp.city + "" + addComp.district + addComp.street + "" + addComp.streetNumber;
                    console.log("当前地址：" + addressDetail);
                    var content = p.data.title + "<br/>地址：" + addComp.district + addComp.street + addComp.streetNumber;
                    var opts = {
                        width: 200,     // 信息窗口宽度
                        height: 50,     // 信息窗口高度
                        enableMessage: false//设置允许信息窗发送短息
                    };
                    var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
                    map.openInfoWindow(infoWindow, point); //开启信息窗口
                });
            } else {
                var content = p.data.title + "<br/>地址：" + p.data.content;
                var opts = {
                    width: 200,     // 信息窗口宽度
                    height: 50,     // 信息窗口高度
                    enableMessage: false//设置允许信息窗发送短息
                };
                var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
                map.openInfoWindow(infoWindow, point); //开启信息窗口
            }

        });
        map.addOverlay(marker);
    }
    ;
}

// 向地图添加控件
function addMapControl() {
    //左下角比例尺控件
    var scaleControl = new BMap.ScaleControl({
        anchor: BMAP_ANCHOR_BOTTOM_LEFT
    });
    scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
    map.addControl(scaleControl);

    //左上角平移缩放控件
    var navControl = new BMap.NavigationControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        type: BMAP_NAVIGATION_CONTROL_LARGE
    });
    map.addControl(navControl);

}