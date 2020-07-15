<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="/WEB-INF/pages/common/pmlsTagLib.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房友新房分销系统</title>
    <%--<%@include file="common/common.jsp" %>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/meta/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/meta/pmls/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/meta/pmls/css/theme.css">
    <style type="text/css">
        .tabIframe {
            width: 100%;
            height: 100%;
            border: 0px;
        }
        .down{
            position: absolute;
            width: 11px;
            height: 11px;
            background-image: url(../../meta/images/down-icon.png);
            background-repeat: no-repeat;
            top: 50%;
            right: 3px;
            margin-top: -3px;
        }

        body .yjPlanBtn2-class .layui-layer-btn1{
            border-color: #1E9FFF!important;
            background-color: #1E9FFF!important;
            color: #fff!important;
        }
    </style>


</head>
<script type="application/javascript">
    var ctx = '${pageContext.request.contextPath}';
    var vs="${vs}";
    //js中请求的base路径
    var BASE_PATH = '${ctx}';
    //js中本地静态文件base路径(包括:js,特殊的本地图片,所有插件的文件)
    var LOC_RES_BASE_PATH = '${locResPath}';
</script>
<script src="${pageContext.request.contextPath}/meta/pmls/js/common/comm.js?v=${vs}"></script>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">

        <div class="layui-form">
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item" ><a href="javascript:;">${userInfo.userName}</a></li>

            <li class="layui-nav-item" >
                <a href="javascript:selectCity();">${userInfo.cityName}</a>
                <span class="down"></span>
                    <%--<c:forEach items="${userInfo.cities}" var="list">
                        <c:if test="${userInfo.cityNo eq list.cityNo}">
                            <a href="javascript:">${list.cityName}</a>
                        </c:if>
                    </c:forEach>--%>
                    <%--<dl class="layui-nav-child">
                        <c:forEach items="${userInfo.cities}" var="list">
                            <c:if test="${userInfo.cityNo eq list.cityNo}">
                                <dd class="layui-this" >  <a  href="javascript:changeCity('${list.cityNo}')">${list.cityName}</a></dd>
                            </c:if>
                            <c:if test="${userInfo.cityNo ne list.cityNo}">
                                <dd  >  <a  href="javascript:changeCity('${list.cityNo}')">${list.cityName}</a></dd>
                            </c:if>
                        </c:forEach>

                    </dl>--%>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:window.open('${pageContext.request.contextPath}/app/login','_self');">退出</a>
            </li>
        </ul>

        </div>

    </div>
	<c:set var="flag1" value="0"></c:set>
    <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
            <div class="layui-logo" style="cursor: pointer"> <b><a href="${ctx}/app/index">房友新房分销系统</a></b> </div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree layui-inline" style="margin-right: 10px;" lay-filter="leftNav">
                <%--<li class="layui-nav-item ">
                    <a class="" href="javascript:showRight('/borrowMoneyController/hkPlanList');">渠道还款计划</a>
                </li>--%>
                <c:forEach var="dto" items="${userInfo.auths}" varStatus="status">
                    <c:if test="${dto.parentId==0}">
                        <li class="layui-nav-item ">
                        <c:choose>
                        <c:when test="${dto.url=='' and dto.type=='M' }">
                            <a class="" href="javascript:;">${dto.authName}</a>
                            <dl class="layui-nav-child">
                                <c:forEach var="childDto" items="${userInfo.auths}" varStatus="status">
                                    <c:if test="${dto.authId==childDto.parentId}">
                                        <c:choose>
                                            <c:when test="${childDto.url=='' and childDto.type=='P' }">
                                                <dd><a href="javascript:;" style="text-indent:1em;">${childDto.authName}</a>
                                                    <dl class="layui-nav-child">
                                                        <c:forEach var="childThreeDto" items="${userInfo.auths}" varStatus="status">
                                                            <c:if test="${childDto.authId==childThreeDto.parentId}">
                                                                <dd><a href="javascript:showRight('${childThreeDto.url}');" style="text-indent:1em;"><span>${childThreeDto.authName}</span></a></dd>
                                                            </c:if>
                                                        </c:forEach>
                                                    </dl>
                                                </dd>
                                            </c:when>
                                            <c:otherwise>
                                                <dd><a href="javascript:showRight('${childDto.url}');" style="text-indent:1em;"><span>${childDto.authName}</span></a></dd>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </c:forEach>
                            </dl>
                        </c:when>
                        <c:otherwise>
	                        <c:if test="${dto.url=='/frontPanel'}">
	                       		<c:set var="flag1" value="1"></c:set>
	                       	</c:if>
                            <dd><a href="javascript:showRight('${dto.url}');"><span>${dto.authName}</span></a></dd>
                        </c:otherwise>
                        </c:choose>
                    </li>
                    </c:if>
                </c:forEach>

                <%--&ndash;%&gt;--%>
                <%--<li class="layui-nav-item ">--%>
                    <%--<a class="" href="javascript:;">渠道管理</a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd><a href="javascript:showRight('/channelBrandController/channelBrandList');">渠道品牌</a></dd>--%>
                        <%--<dd><a href="javascript:showRight('/businessManagerController/businessManagerList');">渠道</a></dd>--%>
                        <%--<dd><a href="javascript:showRight('/pmlsFrameContract/frameContractList');">框架协议</a></dd>--%>
                        <%--<dd><a href="javascript:showRight('/cooperationController/cooperationList');">合作确认函</a></dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a href="javascript:;">开发商管理</a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd><a href="javascript:showRight('/developerBrand');">开发商品牌</a></dd>--%>
                        <%--<dd><a href="javascript:showRight('/developer');">开发商</a></dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a href="javascript:;">项目管理</a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd><a href="javascript:showRight('/pmlsEstate/index');">楼盘列表</a></dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a href="javascript:;">交易管理</a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd><a href="javascript:showRight('/sceneTrade');">按项目</a></dd>--%>
                        <%--<dd><a href="javascript:showRight('/sceneTrade/report')">按订单</a></dd>--%>
                        <%--<dd><a href="javascript:showRight('/sceneTrade/rought');">大定审核</a></dd>--%>
                        <%--<dd><a href="javascript:showRight('/sceneTrade/custom');">客户</a></dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a href="javascript:;">结算管理</a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd><a href="javascript:showRight('/pmlsSceneCommission');">收入</a></dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;">返佣</a>--%>
                            <%--<dl class="layui-nav-child">--%>
                                <%--<dd><a href="javascript:showRight('/pmlsSceneCommission/yjSjFyList');">返佣</a></dd>--%>
                                <%--<dd><a href="javascript:showRight('/pmlsCashBill');">请款单</a></dd>--%>
                            <%--</dl>--%>
                        <%--</dd>--%>
                        <%--<dd><a href="javascript:;showRight('/pmlsLnkYjDy/index');">垫佣管理</a></dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;">内佣</a>--%>
                            <%--<dl class="layui-nav-child">--%>
                                <%--<dd><a href="javascript:showRight('/pmlsLnkYjNy/index');">应计内佣</a></dd>--%>
                                <%--<dd><a href="javascript:;showRight('/pmlsKfCommission/kfIndex');">可发内佣</a></dd>--%>
                            <%--</dl>--%>
                        <%--</dd>--%>


                        <%--<dd><a href="javascript:;showRight('/pmlsSwitch')">关账</a></dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a href="javascript:;">统计分析</a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd><a href="javascript:showRight('/pmlsLinkMarginDetail');">资金成本</a></dd>--%>
                        <%--<dd><a href="javascript:showRight('/pmlsLinkDetail');">联动明细</a></dd>--%>
                        <%--<dd><a href="javascript:showRight('/pmlsLinkProjectDetail');">联动项目明细</a></dd>--%>
                        <%--<dd><a href="javascript:;">联动跟踪分析</a></dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a href="javascript:;">系统设置</a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd><a href="javascript:showRight('/personnelPermissions');">人员权限</a></dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
            </ul>
        </div>

    </div>

    <div class="layui-body">
        <div class="layui-card layadmin-header">

            <span class="layui-breadcrumb" lay-separator="/" style="visibility: visible;" >
              <%--<a href="">主页</a>--%>
              <%--<a href="">国际新闻</a>
              <a href="">亚太地区</a>
              <a><cite>正文</cite></a>--%>
            </span>
        </div>
        <!-- 内容主体区域 -->
	        <div  id="content" style="padding: 15px;height:calc(100% - 30px);">
	        	<c:if test="${flag1 == '0'}">
		        	<iframe id="myframe" class="tabIframe" src="/dashboard"></iframe> 
	        	</c:if>
	        	<c:if test="${flag1 == '1'}">
		            <iframe id="myframe" class="tabIframe" src="/frontPanel"></iframe>
	        	</c:if>
	        
	        </div>
           
    </div>
    <div id="previewImage"></div>
    <%--<div class="layui-footer">
        <!-- 底部固定区域 -->
        ©底部固定区域
    </div>--%>

</div>
<script src="${pageContext.request.contextPath}/meta/layui/layui.js?v=${vs}"></script>
<script>
    var element,$;
    layui.use(['element','layer'], function() {
         element = layui.element,
         $ = layui.$;

         //默认选中渠道品牌
         // $(".layui-nav-tree dd").each(function () {
         //     if($(this).text()=='渠道品牌'){
         //         $(this).parent().parent().addClass('layui-nav-itemed');
         //         $(this).addClass('layui-this');
         //         $(this).trigger('click');
         //         $(this.firstChild.firstChild).trigger('click');
         //     }
         // })
     });
    setTimeout(function () {
        $(".layui-nav-item").click(function () {
            if(!$(this).hasClass('layui-nav-itemed')){
                $(this).removeClass('layui-nav-itemed');
            }else{//打开后默认选中第一个
                $(".layui-nav-item").removeClass('layui-nav-itemed');
                $(this).addClass('layui-nav-itemed');
                //$(this.children[1].children[0].firstChild.firstChild).trigger('click');
            }
        });
    },1000);

    if(top.location!=self.location){
        console.log("不是顶层窗口");
        window.parent.location.href=window.location.href;
    }

    var breadcrumbArray=[];
    function showRight(url) {
        //document.getElementById("myframe").src=url;
        if(url=='/dashboard?yfLogin=1'){
            yfLogin();
        }
        $(".layadmin-header").show();
        $("#content").attr("style","padding: 15px;height:calc(100% - 80px);")
        var name=$('.layui-nav-tree .layui-this').text();
        redirectTo('replace',url,name);
        sessionStorage.clear();//清空缓存
    };
    //刷新面包屑
    function redirectTo(type,url,name){
        $(".layadmin-header").show();
        $("#content").attr("style","padding: 15px;height:calc(100% - 80px);")
        if(type=='delete'){//点击返回删除最后一个
            breadcrumbArray.pop();
            document.getElementById("myframe").src=breadcrumbArray[breadcrumbArray.length-1].url;
        }else if(type=='replace'){//替换全部面包屑
            breadcrumbArray=[];
            breadcrumbArray.push({name:'主页',url:'/app/index'});
            breadcrumbArray.push({name:name,url:url});
            document.getElementById("myframe").src=breadcrumbArray[breadcrumbArray.length-1].url;
        }else if(type=='append'){//追加面包屑
            breadcrumbArray.push({name:name,url:url});
            document.getElementById("myframe").src=url;
        }else if(type=='jump'){//点击面包屑跳转指定页面
            var newBreadcrumbArray =[];
            document.getElementById("myframe").src=url;
            $(".layadmin-header").show();
            for(var i=0;i<breadcrumbArray.length;i++){
                if(breadcrumbArray[i].name!=name){
                    newBreadcrumbArray.push(breadcrumbArray[i]);
                }else{
                    newBreadcrumbArray.push(breadcrumbArray[i]);
                    break;
                }
            }
            breadcrumbArray=newBreadcrumbArray;
        }
        var content="";
        for(var i=0;i<breadcrumbArray.length;i++){
            if(i==breadcrumbArray.length-1){
                content+='<a><cite>'+breadcrumbArray[i].name+'</cite></a>'
            }else{
                content+='<a href="javascript:redirectTo(\'jump\''+',\''+breadcrumbArray[i].url+'\',\''+breadcrumbArray[i].name+'\');">'+breadcrumbArray[i].name+'</a>'
            }
        }
        $(".layui-breadcrumb").html(content);
        element.render();
    }

//    layui.use([ 'element','form', 'layer'], function (){
//        var  element = layui.element,
//                form = layui.form,
//                layer = layui.layer,
//                $ = layui.$;
//
//        form.render('select');
//
//        form.on('select(headerCityChange)', function (data) {
//            changeCity(data.value);
//        });
//    });

    //城市弹出框
    function selectCity() {
        parent.layer.open({
            type: 2,
            title: '选择城市',
            area: ['800px', '600px'],
            content: '/app/selectCity'
            ,scrollbar: false
            ,resize:false
        });
    }


    // 岗位下拉变动
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

    function refreshRight() {
        //document.getElementById('myframe').contentWindow.location.reload(true)
        var frame = document.getElementById("myframe");
        var path = frame.getAttribute("src");
        frame.setAttribute("src", path);
        $(".layadmin-header").show();
    }

    function  yfLogin() {
        var yftLoginUrl = '';
        var desSign = '';
        var tempwindow=window.open('_blank'); // 先打开页面
        $.ajax({
            type: "POST",
            url: BASE_PATH + "/yfLogin/ajaxYfLogin",
            contentType: 'application/json;charset=utf-8', //设置请求头信息
            dataType: "json",
            async:false,
            success: function (data) {
                if (data.returnCode == 200 || data.returnCode == '200') {
                    yftLoginUrl = data.returnData.yftLoginUrl;
                    desSign = encodeURIComponent(data.returnData.desSign);
                }
            }
        });
        var yfurl = yftLoginUrl + "?formVals=" + desSign;
        tempwindow.location = yfurl; // 后更改页面地址
    }
</script>
</body>
</html>
