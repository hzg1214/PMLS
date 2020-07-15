<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html style="background-color: #fff;">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择城市</title>
    <%@include file="common/common.jsp" %>
    <link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/meta/css/jquery-ui.css?_v=${vs}">

    <style type="text/css">
        .ui-draggable, .ui-droppable {
            background-position: top;
        }
        .ui-autocomplete-loading {
            background: white url("http://jqueryui.com/resources/demos/autocomplete/images/ui-anim_basic_16x16.gif") right center no-repeat;
        }
        #ui-id-1 {
            z-index:10000;
            font-size:14px;
        }
        .ui-autocomplete {
            max-height: 258px;
            overflow-y: auto;
            overflow-x: hidden;
        }
        .w125x{
            width:160px!important;
            margin-right:5px
        }
        .select2-container--open{
            z-index:10000;
            font-size:14px;
        }
        .select2-results__options{
            max-height:322px!important;
        }
        .autocomplete-suggestions {font-size: 15px; -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box; border: 1px solid #999; background: #FFF; cursor: default; overflow: auto; -webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); -moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); }
        .autocomplete-suggestion { padding: 2px 5px; white-space: nowrap; overflow: hidden; }
        .autocomplete-no-suggestion { padding: 2px 5px;}
        .autocomplete-selected { background: #F0F0F0; }
        .autocomplete-suggestions strong { font-weight: bold; color: #000; }
        .autocomplete-group { padding: 2px 5px; }
        .autocomplete-group strong { font-weight: bold; font-size: 16px; color: #000; display: block; border-bottom: 1px solid #000; }

        body {
            padding: 0px;
        }

        .searchForm {
            padding-top: 5px;
        }

        .col-5 {
            width: 20%;
            width: -webkit-calc((100% - 10px*2) / 5);
            width: calc((100% - 10px*2) / 5);
            display: inline-block;
        }

        .layui-city{
            display: block;
            vertical-align: middle;
            text-align: center;
            width: 100%;
            padding-right: 0px;

        }

        .layui-row .active {
            background-color: #1E9FFF;
            border-color: #1E9FFF;
            color: #fff;
        }

        .layui-city-hover{
            cursor: pointer;
            display: block;
            vertical-align: middle;
            text-align: center;
            width: 100%;
            padding-right: 0px;
            background-color: #1E9FFF;
            border-color: #1E9FFF;
            color: #fff;
        }


        .fc-warning{
            color:#FF0000
        }

        .layui-row:before {
            display: none;
        }

        .layui-row:after {
            display: none;
        }

        .cityTable{
            width: 100%;
            background-color: #fff;
            color: #666;
            border-color: grey;
            line-height: 40px;
        }
        .cityTable td{
            border-width: 1px;
            border-style: solid;
            border-color: #e6e6e6;
        }
    </style>
</head>
<script type="application/javascript">

</script>
<body>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline" style="margin-left: 2%">
                        <label class="layui-form-label">城市</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="cityName" placeholder="请输入城市名" lay-verify="">
                            <input type="hidden" id="cityNo">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" onclick="javascript:searchCity()">确定</button>
                        <span class="fc-warning" id="errorMsg" ></span>
                    </div>
                </div>
            </form>
            <div id="mainTable">
                <div class="layui-form">
                    <%--<div class="layui-form-item list-block">--%>
                        <table class="cityTable" cellspacing="15px" style="">
                            <colgroup>
                                <col width="10%">
                                <col width="90%">
                            </colgroup>
                            <tbody class="layui-container">
                            <c:forEach items="${userInfo.flCities}" var="list">
                                <tr>
                                    <td style="text-align: center">${list.firstLetter}</td>
                                    <td>
                                        <div class='layui-row'>
                                            <c:forEach items="${list.cityList}" var="city" varStatus="i">
                                            <c:choose>
                                            <c:when test="${i.index ne 0 && i.index mod 5 == 0}">
                                        </div>
                                        <div class='layui-row'>
                                            <div class='col-5'><span class='layui-city <c:if test="${userInfo.cityNo eq city.cityNo}">active</c:if>' onmouseover="changeClass(this)" onmouseleave="resetClass(this)" onclick="changeCity('${city.cityNo}')">${city.cityName}</span></div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class='col-5'><span class='layui-city <c:if test="${userInfo.cityNo eq city.cityNo}">active</c:if>' onmouseover="changeClass(this)" onmouseleave="resetClass(this)" onclick="changeCity('${city.cityNo}')">${city.cityName}</span></div>
                                            </c:otherwise>
                                            </c:choose>
                                            </c:forEach>
                                            <c:if test="${fn:length(list.cityList) mod 5 == 4}">
                                                <div class='col-5'></div>
                                            </c:if>
                                            <c:if test="${fn:length(list.cityList) mod 5 == 3}">
                                                <div class='col-5'></div><div class='col-5'></div>
                                            </c:if>
                                            <c:if test="${fn:length(list.cityList) mod 5 == 2}">
                                                <div class='col-5'></div><div class='col-5'></div><div class='col-5'></div>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    <%--</div>--%>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
<script src="${pageContext.request.contextPath}/meta/js/common/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/meta/js/common/jquery-ui.js?_v=${vs}"></script>
<script type="text/javascript">
    var dataArray=[];
    var dataArray2=[];
    var jsonStr="";
    $( function() {
        var flCityList = '${userInfo.cities}';
        flCityList = flCityList.replace("[{", "").replace("}]", "");
        var flArr = flCityList.split("}, {");
        $.each(flArr, function (n, value) {
            var cityArr = value.split(",");
            var cityNo = "", cityName = "";
            $.each(cityArr, function (i, val) {
                if (val.indexOf("cityNo") != -1) {
                    cityNo = val.split("=")[1];
                } else if (val.indexOf("cityName") != -1) {
                    cityName = val.split("=")[1];
                }
            });
            dataArray.push({id: cityNo, value: cityName,label:cityName});
            dataArray2.push(cityName);
            jsonStr += cityName;
        });

        $("#errorMsg").empty().html("");

        var option = {
            max: 15,    //列表里的条目数
            minChars: 0,    //自动完成激活之前填入的最小字符
            width: 180,     //提示的宽度，溢出隐藏
            scrollHeight: 200,   //提示的高度，溢出显示滚动条
            matchContains: false,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: true,    //自动填充
            minLength: 1,
            select: function (event, ui) {
                $("#errorMsg").empty().html("");
                $("#cityName").val(ui.item.value);
                $("#cityNo").val(ui.item.id);
            },change: function( event, ui ) {
                var containFlag = $.inArray(this.value, dataArray2);
                if(containFlag < 0 ){
                    $("#errorMsg").empty().html("");
                    $("#cityName").val("");
                    $("#cityNo").val("");
                    if(jsonStr.indexOf(this.value) <0) {
                        $("#errorMsg").empty().html("暂无找到当前输入所匹配的城市!");
                    }
                }else{
                    $.each(dataArray, function (n, value) {
                        if(value.value == $("#cityName").val()){
                            $("#cityNo").val(value.id);
                        }
                    });
                }
            }
        };
        $("#cityName").autocomplete({source: dataArray}, option).on('focus', function () {
            $(this).keydown();
        });

    } );

    function changeClass(obj) {
        $(obj).removeClass("layui-city").addClass("layui-city-hover");
    }

    function resetClass(obj) {
        $(obj).removeClass("layui-city-hover").addClass("layui-city");
    }

    function changeCity(cityNo) {
        var index = parent.layer.load(2,{shade: 0.1});
        var url = BASE_PATH + "/user/changeCity";
        var params = {
            selectedCityNo : cityNo
        };
        ajaxGet(url, params, function(data) {
            parent.layer.close(index);
            window.location.href = BASE_PATH + "/app/index";
        }, function(data) {
        });
    }

    function searchCity(){
        var cityNo = $("#cityNo").val();
        if(cityNo!=""&& cityNo!=null && cityNo!=undefined){
            changeCity(cityNo);
        }else{
            $("#errorMsg").empty().html("请输入城市名称!");
            /*var cityName=$("#cityName").val();
            var containFlag = $.inArray(cityName, dataArray2);
            if(containFlag<0){
                $("#errorMsg").empty().html("请输入城市名称!");
            }else{
                $.each(dataArray, function (n, value) {
                    if(value.value == cityName){
                        changeCity(value.id);
                    }
                });
            }*/
        }
    }

</script>
