<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/report/reportToValid.js?_v=${vs}"></script>
</head>
<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

 	<input type = "hidden"  id = "id"   name = "id" value = "${reportInfo.id}"/>
 	<input type = "hidden"  id = "yearMonth" name ="yearMonth" value = "${yearMonth}"/>
    <input type = "hidden"  id = "roughInputDate" name ="roughInputDate" value = "${reportInfo.roughInputDate}"/>
    <input type = "hidden"  id = "reportId" name ="reportId" value = "${reportInfo.reportId}"/>
    <input type = "hidden"  id = "companyNo" name ="companyNo" value = "${reportInfo.companyId}"/>
    
    <input type = "hidden"  id = "customerFromHide" name ="customerFromHide" value = "${reportInfo.customerFrom}"/>
  	<div class="container theme-hipage ng-scope" role="main">
  		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">联动管理</a></li>
				<li><a href="#"  class="a_hover">>案场管理</a></li>
				<li><a href="#"  class="a_hover">>
					<c:choose>
                       	<c:when test="${fromType eq '1'}">
                       		大定待审核
                       	</c:when>
                       	<c:when test="${fromType eq '2'}">
                       		大定已审核
                       	</c:when>
                   </c:choose>
				</a></li>
				<li><a href="#"  class="a_hover">>大定审核详情</a></li>
			</ul>
		</div>
        <div class="row">
            <div class="page-content">
            	<h4 class="border-bottom pdb10">
      					<strong>大定审核详情</strong>
                        <c:if test="${fromType eq '0'}">
                            <a href="${ctx}/report?searchParam=1" class="btn btn-primary">返回</a>
                        </c:if>
                        <c:if test="${fromType eq '1'}">
                            <a href="${ctx}/reportToValid?searchParam=1" class="btn btn-primary">返回</a>
                        </c:if>
                        <c:if test="${fromType eq '2'}">
                            <a href="${ctx}/reportToValid/valided?searchParam=1" class="btn btn-primary">返回</a>
                        </c:if>
                        <c:if test="${fromType eq '3'}">
                            <a href="${ctx}/sceneEstate/qSceneRecognition/${reportInfo.estateId}?searchParam=1" class="btn btn-primary">返回</a>
                        </c:if>
                        <c:if test="${reportInfo.roughAuditStatus eq '0'}">
                            <shiro:hasPermission name="/sceneEstate:REPORT_AUDIT">
                                <a hideA href="javascript:void(0);" onclick="ReportToValid.toReject('${reportInfo.id}','${fromType}','${reportInfo.estateId}')" class="btn btn-primary" style="margin-right:10px;">审核驳回</a>
                                <a hideA href="javascript:void(0);" onclick="ReportToValid.toPass('${reportInfo.id}','${fromType}','${reportInfo.estateId}','${passFlag}','${reportInfo.isApproval}','${reportInfo.detailId}')" class="btn btn-primary" style="margin-right:10px;">审核通过</a>
                            </shiro:hasPermission>
                        </c:if>
                </h4>
              <table class="table-sammary">
                <col style="width:103px;">
                <col style="width:auto;">
                 <col style="width:109px;">
                <col style="width:auto;">
                 <col style="width:109px;">
                <col style="width:auto;">
                <tr>
					<td style="text-align: right;"><strong>项目编号：</strong></td>
					<td colspan="5"><strong>${reportInfo.projectNo}</strong>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>楼盘名称：</strong>
									<strong>${reportInfo.estateNm}</strong>
					</td>
                </tr>
                <tr>
                    <td class="talabel">报备编号：</td>
					<td>${reportInfo.reportId}</td>
                  <td class="talabel">经纪公司：</td>
                  <td>${reportInfo.companyNm}</td>
                  <td class="talabel">门店名称：</td>
                  <td>${reportInfo.storeNm}</td>
                </tr>
                <tr>
                    <td class="talabel">报备经纪人：</td>
                    <td>${reportInfo.reportAgent} &nbsp; ${reportInfo.reportAgentTel}</td>
                  <td class="talabel">客户：</td>
                  <td>${reportInfo.customerNm} &nbsp; ${reportInfo.customerTel}</td>
                  <td class="talabel">客户：</td>
                  <td>${reportInfo.customerNmTwo} &nbsp; ${reportInfo.customerTelTwo}</td>
                </tr>
                <tr>
                    <td class="talabel">客户人数：</td>
                    <td>${reportInfo.customerNum}</td>
                    <td class="talabel">楼室号：</td>
                    <td>${reportInfo.buildingNo}</td>
                    <td class="talabel">大定面积：</td>
                    <td>${reportInfo.roughArea}m<sup>2</sup></td>
                </tr>
                  <tr>
                      <td class="talabel">大定总价：</td>
                      <td><c:if test="${empty reportInfo.roughAmount}">0</c:if><fmt:formatNumber value="${reportInfo.roughAmount}" pattern="#.00"/>元</td>
                      <td class="talabel">大定日期：</td>
                      <td>${sdk:ymd2(reportInfo.roughInputDate)}</td>
                      <td class="talabel">业绩归属人：</td>
                      <td>${reportInfo.contactNm}</td>
                     
                  </tr>
                  <tr>
                  	  <td class="talabel">业绩归属中心：</td>
                      <td>${reportInfo.centerGroupName}</td>
                      <td class="talabel">当前进度：</td>
                      <td>大定</td>
                      <td class="talabel">状态：</td>
                      <td id="roughAuditStatus">
                          <c:if test="${reportInfo.roughAuditStatus eq '0'}">待审核</c:if>
                          <c:if test="${reportInfo.roughAuditStatus eq '2'}">审核驳回</c:if>
                          <c:if test="${reportInfo.roughAuditStatus eq '1'}">审核通过</c:if>
                      </td>
                      
                  </tr>
        		<tr>
        			<td class="talabel">操作人：</td>
                      <td>${reportInfo.empNm}</td>
                    <td class="talabel">操作时间：</td>
                    <td>${reportInfo.crtDt}</td>
                  <td class="talabel">报备来源：</td>
                  <td>
                  	<c:if test="${reportInfo.customerFrom eq 17401}">CRM</c:if>
                  	<c:if test="${reportInfo.customerFrom eq 17402}">CRM</c:if>
					<c:if test="${reportInfo.customerFrom eq 17403}">APP</c:if>
					<c:if test="${reportInfo.customerFrom eq 17404}">房友助手</c:if>
					<c:if test="${reportInfo.customerFrom eq 17405}">友房通</c:if>
                  </td>
                     
                </tr>
                  <c:if test="${reportInfo.roughAuditStatus eq '2'}">
                      <tr>
                          <td class="talabel">审核意见：</td>
                          <td colspan="5" style="color: red;">${reportInfo.roughAuditDesc}</td>
                      </tr>
                  </c:if>
              </table>
                <div class="page-content" style="padding-left: 10px;">
                    <h4 style="font-size:16px">
                        <strong>附件</strong>
                    </h4>
                </div>
                <table class="table-sammary" name="Viewerbox">
                    <col style="width:145px;">
                    <col style="width:auto">
                    <tr>
                        <td colspan="2">
                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h5 class="thumb-title">
                                            带看单
                                        </h5>
                                        <div class="thumb-xs-box" id="fileIdPhotoToSee">
                                            <c:if test="${not empty reportInfo.picList}">
                                                <c:set  var="fileSize" value="0"/>
                                                <c:forEach items="${reportInfo.picList}" var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1022}">
                                                        <c:set var="fileSize" value="${fileSize + 1}"/>
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
																<span class="thumb-img">
																	<span class="thumb-img-container">
																		<span class="thumb-img-content">
																			<img alt="带看单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
																		</span>
																	</span>
																</span>
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            
                                            
                                            
                                           <c:if test="${not empty reportInfo.fangyouFileList}">
                                              <c:forEach items="${reportInfo.fangyouFileList}" var="list" varStatus="status">
                                                <c:if test="${(list.typeId eq 3 and list.fileTypeCode eq 1022)}">
                                                	<c:set var="showFlag" value="1"></c:set>
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="" data-original="${list.url}" src="${list.url}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                            <!-- 老数据显示,房友typeId为3且是带看单1022的为新数据，下面无需显示 -->
                                            <c:if test="${showFlag ne '1'}">
	                                            <c:forEach items="${reportInfo.fangyouFileList}" var="list" varStatus="status">
	                                                <c:if test="${list.typeId eq 2}">
	                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
	                                                    <div class="thumb-xs-list item-photo-list">
	                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
	                                                        <span class="thumb-img">
	                                                            <span class="thumb-img-container">
	                                                                <span class="thumb-img-content">
	                                                                    <img alt="" data-original="${list.url}" src="${list.url}" class="empPhoto"/>
	                                                                </span>
	                                                            </span>
	                                                        </span>
	                                                        </a>
	                                                    </div>
                                                	</c:if>
                                            	</c:forEach>
                                            </c:if>
                                          </c:if>  
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h5 class="thumb-title">
                                           大定单
                                        </h5>
                                        <div class="thumb-xs-box" id="fileIdPhotoBox">
                                            <c:if test="${not empty reportInfo.picList}">
                                                <c:set  var="fileSize" value="0"/>
                                                <c:forEach items="${reportInfo.picList}" var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1023}">
                                                        <c:set var="fileSize" value="${fileSize + 1}"/>
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
																<span class="thumb-img">
																	<span class="thumb-img-container">
																		<span class="thumb-img-content">
																			<img alt="大定单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
																		</span>
																	</span>
																</span>
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            
                                            
                                            
                                           <c:if test="${not empty reportInfo.fangyouFileList}">
                                              <c:forEach items="${reportInfo.fangyouFileList}" var="list" varStatus="status">
                                                <c:if test="${list.typeId eq 3 and list.fileTypeCode eq 1023 }">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="" data-original="${list.url}" src="${list.url}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                          </c:if>  
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            甲方项目负责人名片
                                        </h4>
                                        <div class="thumb-xs-box" id="fileIdPhotoCard">
                                            <c:if test="${not empty reportInfo.picList}">
                                                <c:set  var="fileSize" value="0"/>
                                                <c:forEach items="${reportInfo.picList}" var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1024}">
                                                        <c:set var="fileSize" value="${fileSize + 1}"/>
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
																<span class="thumb-img">
																	<span class="thumb-img-container">
																		<span class="thumb-img-content">
																			<img alt="甲方项目负责人名片" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
																		</span>
																	</span>
																</span>
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${not empty reportInfo.fangyouFileList}">
                                              <c:forEach items="${reportInfo.fangyouFileList}" var="list" varStatus="status">
                                                <c:if test="${list.typeId eq 3 and list.fileTypeCode eq 1024 }">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="" data-original="${list.url}" src="${list.url}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                          </c:if>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
           </div>
        </div>
    </div>
</body>
</html>
