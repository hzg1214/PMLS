<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>
    <%@ include file="/WEB-INF/page/common/meta.jsp"%>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
    <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateEdit.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/expand/bootstrap-select.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/cssreport/bootstrap-select.css?_v=${vs}">
     <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/relateProjectLeader.js?_v=${vs}"></script>
  </head>
  <body style="padding-bottom:34px;">
    <!-- 头部 -->
    <jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
        <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
    </jsp:include>
    <form id="estateEditForm">

        <input type="hidden" id="id" name="estId" value="${id}">
        <input type="hidden" id="estateId" name="estateId" value="${estate.estateId}">
        <input type="hidden" name="mgtKbn" id="mgtKbn" value="${estate.mgtKbn}"><!-- 物业类型 -->
        <input type="hidden" name="ownYearKbn" id="ownYearKbn" value="${estate.ownYearKbn}"><!-- 产权年限 -->
        <input type="hidden" name="decorationKbn" id="decorationKbn" value="${estate.decorationKbn}"><!-- 装修情况 -->
        <input type="hidden" name="typeKbn" id="typeKbn" value="${estate.typeKbn}"><!-- 建筑类型 -->
        <input type="hidden" name="auditStatus" id="auditStatus" value="${estate.auditStatus}">
        <input type="hidden" id="picNum" value="80">
        <input type="hidden" name="houseTypeFlag" id="houseTypeFlag" value="${houseTypeFlag}">
        <input type="hidden" name="estatePosition" id="estatePosition" value="${estate.estatePosition}">
        <input type="hidden"  name="lnkAccountProject" id="lnkAccountProject" >
        <input type="hidden"  name="lnkAccountProjectCode" id="lnkAccountProjectCode" >
        <div class="container theme-hipage ng-scope" role="main">
            <div class="row">
                <div class="page-content">
                    <!-- 楼盘基本信息和详情 -->
                    <div id="loadDetail"></div>
                    <%--<!-- 楼盘联动规则 -->--%>
                    <%--<div id="loadRule"></div>--%>
                    <%--<!-- 楼盘在售户型 -->--%>
                    <%--<div id="loadType"></div>--%>
                    <%--<!-- 楼盘相册 -->--%>
                    <%--<div id="loadPhoto"></div>--%>
                    <%--<!-- 楼盘周边配套 -->--%>
                    <%--<div id="loadSupport"></div>--%>
                </div>



            </div>
        </div>
    </form>
  </body>
  <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateCommonNew.js?_v=${vs}"></script>
</html>
