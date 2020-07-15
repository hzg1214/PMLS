<%@page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://eju.com/sdk" prefix="sdk" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="t" uri="/eju-tags"%>
<%@page pageEncoding="UTF-8"%>
<%--<c:set var="vs" value="2.0.20200116.05"/>--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/WEB-INF/pages/common/pmlsTagLib.jsp" %>
    <link rel="icon" href="${pageContext.request.contextPath}/meta/images/favicon.ico" type="image/x-icon">
    <meta http-eqUIv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="referrer" content="no-referrer">
    <script>
        var ctx = "${pageContext.request.contextPath}";
        var vs="${vs}";

        //js中请求的base路径
        var BASE_PATH = '${ctx}';
        //js中本地静态文件base路径(包括:js,特殊的本地图片,所有插件的文件)
        var LOC_RES_BASE_PATH = '${locResPath}';

    </script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/meta/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/meta/pmls/css/admin.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/meta/pmls/css/formSelects-v4.css?v=${vs}">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/meta/js/common/viewerBox/viewer.min.css?v=${vs}">

    <script src="${pageContext.request.contextPath}/meta/layui/layui.js?v=${vs}"></script>
    <script src="${pageContext.request.contextPath}/meta/layui/lay/modules/tree1.0.js?v=${vs}"></script>
    <script src="${pageContext.request.contextPath}/meta/js/common/viewerBox/viewer.min.js?v=${vs}"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/meta/pmls/css/theme.css">
    
<!--     WdatePicker -->
    <script src="${pageContext.request.contextPath}/meta/My97DatePicker/WdatePicker.js?v=${vs}"></script>
<%--     <script src="${pageContext.request.contextPath}/meta/My97DatePicker/calendar.js?v=${vs}"></script> --%>
    
<!--     echarts -->
    <script src="${pageContext.request.contextPath}/meta/echartsjs/echarts.min.js?v=${vs}"></script>
<%--     <script src="${pageContext.request.contextPath}/meta/echartsjs/echarts.js?v=${vs}"></script> --%>
<%--     <script src="${pageContext.request.contextPath}/meta/echartsjs/echarts.common.min.js?v=${vs}"></script> --%>

    <script>
        var element,layer,$,upload;
        layui.use(['element','layer','upload'], function(){
            element = layui.element;
            layer=layui.layer;
            $=layui.$;
            upload=layui.upload;
        });
    </script>

    <script src="${pageContext.request.contextPath}/meta/pmls/js/common/comm.js?v=${vs}"></script>

    <%--<script src="${pageContext.request.contextPath}/meta/pmls/js/jquery-1.7.1.js?v=${vs}"></script>--%>
    <script src="${pageContext.request.contextPath}/meta/pmls/js/formSelects-v4.js?v=${vs}"></script>
</head>
</html>
