<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp"%>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
    <script type="text/javascript" src="${ctx}/meta/js/store/storeServiceFrameContract.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
</head>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
</jsp:include>

<div class="container" role="main">
    <div class="crumbs">
        <ul>
            <li><a href="#"  class="a_hover">门店管理</a></li>
            <li><a href="#"  class="a_hover">>门店详情</a></li>
            <li><a href="#"  class="a_hover">>交易服务框架协议</a></li>
        </ul>
    </div>
    <div class="row article">

        <!-- 左侧菜单 -->
        <jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp" flush="true">
            <jsp:param value="110407" name="leftMenuSelectId" />
        </jsp:include>

        <div class="col-md-10 content">

            <div class="page-content">

                <h4>
                    <strong>交易服务框架协议</strong>
                    <a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a>
                </h4>

                <c:set var="controlViewEdit" value="0"/>
                <shiro:hasPermission name="/store:SF_CONTRACT">
                    <c:set var="controlViewEdit" value="1"/>

                    <div class="theme-hipage ng-scope" role="main">
                        <div class="row">
                            <div class="pd10">
                                <div class="thumb-xs-box" id="thumbXsBox">
                                    <c:forEach items="${file}" var="list" varStatus="status">
                                        <c:set var="fileSize" value="${fileSize + 1}"/>
                                        <div class="thumb-xs-list item-photo-list">
                                            <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="交易服务框架协议" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
                                                <span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
                                            </a>

                                            <input type="hidden" name="limitSize" value="10">
                                            <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
                                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                            <input type="hidden" name="fileTypeId" value="1031" />
                                            <input type="hidden" name="fileSourceId" value="2" />
                                        </div>
                                    </c:forEach>

                                    <div class="thumb-xs-list item-photo-add" >
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                            <input type="hidden" name="fileTypeId" value="1031" />
                                            <input type="hidden" name="fileSourceId" value="2" />
                                            <input type="hidden" name ="companyId" value="">
                                            <input type="hidden" name ="refId" value="${storeId}">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </shiro:hasPermission>
                <c:if test="${controlViewEdit eq 0}">
                    <div class="theme-hipage ng-scope" role="main">
                        <div class="row">
                            <div class="pd10">
                                <div class="thumb-xs-box" id="thumbXsBox" name="Viewerbox">
                                    <c:forEach items="${file}" var="list" varStatus="status">
                                        <c:set var="fileSize" value="${fileSize + 1}"/>
                                        <div class="thumb-xs-list item-photo-list">
                                            <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="交易服务框架协议" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
                                            </a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>

        </div>

    </div>
</div>

</body>

</html>
