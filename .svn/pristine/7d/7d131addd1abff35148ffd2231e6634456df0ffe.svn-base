<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/Signed/storeFollowListCtx.js?_v=${vs}"></script> 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.0&ak=${sysConfig.baiduApiKey}"></script>
</head>
<body>

	<div class="container theme-hipage ng-scope" role="main">
		<div class="row">
			<div class="page-content">
				<h4 class="border-bottom pdb10"><strong>跟进详情</strong></h4>
                <p><strong>基本信息</strong></p>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进主题：</label>
                            ${followDetail.title}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进人：</label>
                            ${followDetail.userNameCreate}	
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进日期：</label>
                            ${followDetail.dateCreate}	
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进类型：</label>
                            ${followDetail.followTypeName}
                        </div>
                    </li>
                </ul>
                <c:if test="${followDetail.signTime != null and not empty followDetail.signTime}">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group">
                                <label class="fw-normal w100 text-right">签到时间：</label>
                                    ${followDetail.signTime}
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group">
                                <label class="fw-normal w100 text-right">签到图片：</label>
                                <c:if test="${not empty followDetail.signPicList}">
                                <div style="width:600px;display:inline-block" >
                                    <table class="table-sammary" width="100%" cellspacing="0" cellpadding="0">
                                        <col style="width:200px;">
                                        <col style="width:200px;">
                                        <col style="width:200px;">
                                        <tr>
                                            <c:forEach items="${followDetail.signPicList}" var="pic" varStatus="status">
                                            <td>
                                                <a href="<c:if test="${empty pic.bigPictureUrl}">javascript:void(0)</c:if><c:if test="${not empty pic.bigPictureUrl}">${pic.bigPictureUrl}</c:if>" target=" _blank" style="display: inline-block;  height: auto; vertical-align: -webkit-baseline-middle;">
                                                    <img src="<c:if test="${empty pic.smallPictureUrl}">${ctx}/meta/images/store.png</c:if><c:if test="${not empty pic.smallPictureUrl}">${pic.smallPictureUrl}</c:if>" alt="" class="img"> <br />
                                                </a>
                                            </td>
                                            <c:if test="${status.count}%3==0" var="test" scope="page">
                                        </tr>
                                        <tr>
                                            </c:if>
                                            </c:forEach>
                                        </tr>
                                    </table>
                                    </div>
                                </c:if>
                            </div>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${followDetail.signOutTime != null and not empty followDetail.signOutTime}">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group">
                                <label class="fw-normal w100 text-right">签退时间：</label>
                                    ${followDetail.signOutTime}
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group">
                                <label class="fw-normal w100 text-right">签退图片：</label>
                                <c:if test="${not empty followDetail.signOutPicList}">
                                    <div style="width:600px;display:inline-block" >
                                    <table class="table-sammary" width="100%" cellspacing="0" cellpadding="0">
                                        <col style="width:200px;">
                                        <col style="width:200px;">
                                        <col style="width:200px;">
                                        <tr>
                                            <c:forEach items="${followDetail.signOutPicList}" var="pic" varStatus="status">
                                            <td>
                                                <a href="<c:if test="${empty pic.bigPictureUrl}">javascript:void(0)</c:if><c:if test="${not empty pic.bigPictureUrl}">${pic.bigPictureUrl}</c:if>" target=" _blank" style="display: inline-block;  height: auto; vertical-align: -webkit-baseline-middle;">
                                                    <img src="<c:if test="${empty pic.smallPictureUrl}">${ctx}/meta/images/store.png</c:if><c:if test="${not empty pic.smallPictureUrl}">${pic.smallPictureUrl}</c:if>" alt="" class="img"> <br />
                                                </a>
                                            </td>
                                            <c:if test="${status.count}%3==0" var="test" scope="page">
                                        </tr>
                                        <tr>
                                            </c:if>
                                            </c:forEach>
                                        </tr>
                                    </table>
                                    </div>
                                </c:if>
                            </div>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${followDetail.longitude != null and not empty followDetail.longitude}">
	                <ul class="list-inline form-inline">
	                    <li>
	                        <div class="form-group">
	                        	<input type="hidden" id="longitude" value="${followDetail.longitude}">
	                        	<input type="hidden" id="latitude" value="${followDetail.latitude}">
		                            <label class="fw-normal w100 text-right" style="vertical-align: top">跟进位置：</label>
		                            <div style="width:600px;height:300px;border:#ccc solid 1px;border-radius: 4px;font-size:12px;display:inline-block" id="map"></div>
		                            <%-- (${followDetail.longitude},${followDetail.latitude}) --%>
	                            
	                        </div>
	                    </li>
	                </ul>
                </c:if>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进门店：</label>
                            ${followDetail.storeName}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline pdb20">
                    <li>
                        <div class="form-group" style="word-wrap: break-word; word-break: break-all;">
                            <label class="fw-normal w100 text-right" style="vertical-align: top;">跟进内容：</label>
                            ${followDetail.content}
                        </div>
                    </li>
                </ul>
			</div>
		</div>
	</div>
</body>
</html>
