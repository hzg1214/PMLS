<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script src="http://api.map.baidu.com/api?v=3.0&ak=${sysConfig.baiduApiKey}" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/meta/js/store/storeMap1.js?_v=${vs}"></script>
    <style type="text/css">
        .form-inline {
            overflow: inherit;
        }
    </style>
    <script type="text/javascript">
        var shoupaiTypeList = ${shoupaiTypeList};
    </script>
</head>
<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<input type="hidden" id="cityNo" value="${cityNo}">
<input type="hidden" id="cityName" value="${cityName}">

<div class="page-content" style="margin-top: -10px;margin-bottom: -10px;">
    <form id="PerformanceSumForm">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-inline" align="center">

                    <div class="form-group">
                        <label>城市</label>
                        <select class="form-control" title="" id="city" name="city" notnull="true" style="width: 100px;">
                        </select>
                    </div>

                    <div class="form-group" id="center" align="left">
                        <label id="centerLabel">中心</label>
                        <div class="multi-select" id="centerId" name="centerId">
                            <input type="hidden" class="multi-select-value" readonly="readonly">
                            <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                   style="width: 120px;">
                            <ul class="multi-select-list">
                                <li class="multi-select-item">
                                    <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="form-group" id="contractType" align="left">
                        <label>合作模式</label>
                        <div class="multi-select">
                            <input type="hidden" class="multi-select-value" readonly="readonly">
                            <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                   style="width: 120px;">
                            <ul class="multi-select-list">
                                <li class="multi-select-item">
                                    <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="form-group" id="shoupaiType" align="left" style="display: none;">
                        <label>授牌类型</label>
                        <div class="multi-select">
                            <input type="hidden" class="multi-select-value" readonly="readonly">
                            <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                   style="width: 120px;">
                            <ul class="multi-select-list">
                                <li class="multi-select-item">
                                    <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                </li>
                            </ul>
                        </div>
                    </div>

                   <%-- <div class="form-group">
                        <label>品牌</label>
                        <select class="form-control" title="" id="brand" name="brand" notnull="true">
                            <option value="-1">请选择</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>--%>

                    <div class="form-group">
                        <button type="button" class="btn btn-primary" id="J_search" onclick="search()">查询
                        </button>
                    </div>

                    <div class="form-group" style="margin-left: 70px">
                        <img src="../../../meta/images/crm/B.png" width="18px" height="24px"><span>&nbsp;B</span>
                        <img src="../../../meta/images/crm/sp.png" width="18px" height="24px" style="margin-left: 20px;"><span>&nbsp;授牌</span>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<div style="width:100%;height:100%;border:#ccc solid 1px;border-radius: 4px;font-size:12px;display:inline-block"
     id="mapDiv"></div>

</body>
</html>
