<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<style type="text/css">
    .text-overflow {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 100%;
    }
</style>
</head>
<body>
    <!-- 头部 -->
    <jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
        <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
    </jsp:include>
    <div class="container">
        <div class="row article">
            <!-- 左侧菜单 -->
            <jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
                flush="true">
                <jsp:param value="110404" name="leftMenuSelectId" />
            </jsp:include>
            <div class="col-md-10 content" >
                <div class="page-content" name="Viewerbox">
                <br>
                    <h4>
                        <strong>合同变更详情</strong>
                        <a href="${ctx}/contractChangeNew/list/${contractChange.contractId}/${contractStatus}" class="btn btn-primary">返回</a>
                    </h4>
                    <div class="" style="height: auto;">
                        <form id="storeRelocationForm">
                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" style="padding-left:10px;">
                                        <label class="fw-normal w125 text-right">变更合同编号</label>
                                        <span style="padding-left:5px;">${contractChange.contractNo}</span>
                                    </div>
                                </li>
                            </ul>

                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" style="padding-left:10px;">
                                        <label class="fw-normal w125 text-right">变更类型：</label>
                                        <span style="padding-left:5px;">门店迁址</span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left: 15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">经纪公司：</label>
                                        <span style="padding-left:5px;">${contractChange.oldUpdateCompanyName} &nbsp;&nbsp; ${contractChange.companyBankNo}</span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left: 15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">门店：</label>
                                        <span style="padding-left:5px;">${contractChange.storeList[0].name} &nbsp;&nbsp; ${contractChange.storeList[0].storeNo}</span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left: 15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">门店原址：</label>
                                        <span style="padding-left:5px;">${contractChange.oldStoreAddressDetail} </span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group" >
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">门店变更后地址：</label>
                                        <span style="padding-left:5px;">${contractChange.storeCityName}${contractChange.storeDistrictName}${contractChange.storeAdresss2}  </span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">备注：</label>
                                       <span style="padding-left:5px;">${contractChange.remarks}  </span>
                                    </div>
                                </li>
                            </ul>
                            
                            <p><strong>门店信息</strong></p>
                            <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                                <tr>
                                    <th style="width: 100px;">门店编号</th>
                                    <th style="width: 150px;">门店名称</th>
                                    <th style="width: 120px;">门店负责人</th>
                                    <th style="width: 120px;">门店负责人电话</th>
                                    <th style="width: 120px;">门店资质等级</th>
                                </tr>
                                    <tr>
                                        <td>${contractChange.storeList[0].storeNo}</td>
                                        <td style="text-align:center;" title="${contractChange.storeList[0].name}" class="text-overflow">${contractChange.storeList[0].name}</td>
                                        <td>${contractChange.storeList[0].storeManager} </td>
                                        <td>${contractChange.storeList[0].storeManagerPhone}</td>
                                        <td>
                                               		乙类（乙3）
                                        </td>
                                    </tr>
                            </table>
                            
                    </form>
                    </div>
    				
    				
    				<div  class="" role="main">
					    <h4>
					        <strong>附件</strong>
					    </h4>
					</div>	
                    <!-- 补充协议 --> 
				<div class="" role="main">
				        <div class="row">
				                <div class="pd10">
									<h4 class="thumb-title">
										补充协议
									</h4>
									<div class="thumb-xs-box" id="fileComplement">
                                        <c:if test="${not empty contractChange.fileComplement }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${contractChange.fileComplement}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="补充协议" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                    </a>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </div>
								</div>
				     </div>
				 </div> 
				 <!-- 房友确认单 --> 
				<div class="" role="main">
				        <div class="row">
				                <div class="pd10">
									<h4 class="thumb-title">
										房友确认单
									</h4>
									<div class="thumb-xs-box" id="fileFyConfirmation">
                                        <c:if test="${not empty contractChange.fileFyConfirmation }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${contractChange.fileFyConfirmation}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="房友确认单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                    </a>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </div>
									
								</div>
				     </div>
				 </div> 
				 <!-- 门店照片 --> 
				<div class="" role="main">
				        <div class="row">
				                <div class="pd10">
									<h4 class="thumb-title">
										门店照片
									</h4>
									<div class="thumb-xs-box" id="fileStorePhoto">
                                        <c:if test="${not empty contractChange.fileStorePhoto }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${contractChange.fileStorePhoto}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="房友确认单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                    </a>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </div>
								</div>
				     </div>
				 </div> 
				  <!-- 其他 --> 
				<div class="" role="main">
				        <div class="row">
				                <div class="pd10">
									<h4 class="thumb-title">
										其他
									</h4>
									<div class="thumb-xs-box" id="fileOtherList">
                                        <c:if test="${not empty contractChange.fileOtherList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${contractChange.fileOtherList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="房友确认单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                    </a>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </div>
								</div>
				     </div>
				 </div> 



                </div>
            </div>
        </div>
    </div>
</body>
</html>


