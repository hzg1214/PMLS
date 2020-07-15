<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
 <div class="container" role="main">
 
    <span class="" style="float:right"><a href="javascript:ReportDetail.close();" class="btn btn-default">&times;</a></span>
 
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>
                	<c:choose>
			         	<c:when test="${reqMap.brokeragePage eq '22003'}">
			         		结佣
			         	</c:when>
			         	<c:otherwise>
			         		 ${info.progressNm}
			         	</c:otherwise>
			       </c:choose>
                </strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <strong>
                                <label class="fw-normal w100 text-right"><strong>项目编号：</strong></label>${info.projectNo}
                                <label class="fw-normal w100 text-right"><strong>楼盘名称：</strong></label>${info.estateNm}
                            </strong>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
				<li>
					<div class="form-group">
						<label class="fw-normal w100 text-right">报备编号：</label>${info.reportId}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w100 text-right">报备来源：</label>
						${info.customerFromNm}
					</div>
				</li>
			</ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">经纪公司：</label>${info.companyNm}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">门店：</label>${info.storeNm}
                        </div>
                    </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">报备经纪人：</label>${info.reportAgent}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">经纪人手机：</label>${info.reportAgentTel}

                        </div>
                    </li>
                </ul>
                
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">客户：</label>${info.customerNm}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">手机号码：</label>${info.customerTel}

                        </div>
                    </li>

                </ul>
                
            	<ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">客户：</label>${info.customerNmTwo}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">手机号码：</label>${info.customerTelTwo}

                        </div>
                    </li>
                </ul>
                
                <c:choose>
                	<c:when test="${reqMap.brokeragePage eq '22003'}">
                		<ul class="list-inline half form-inline">
		                   <li>
		                        <div class="form-group">
		                            <label class="fw-normal w100 text-right">楼室号：</label>${info.buildingNo}
		                        </div>
		                    </li>
		                     <li>
	                            <div class="form-group">
	                                <label class="fw-normal w100 text-right">${info.progressNm}日期：</label>
	                                <fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
	                                <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
	                            </div>
	                        </li>
				                  
				      </ul>
                	</c:when>
                
                	<c:otherwise>
                		<ul class="list-inline half form-inline">
			                    <li>
			                        <div class="form-group">
			                            <label class="fw-normal w100 text-right">业绩归属：</label>${info.contactNm}
			                        </div>
			                    </li>
			                    <c:if test="${info.progress ne 13504 and info.progress ne 13505}">
			                        <li>
			                            <div class="form-group">
			                                <label class="fw-normal w100 text-right">${info.progressNm}日期：</label>
			                                <fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                                <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
			                            </div>
			                        </li>
			                    </c:if>
			
			                    <c:if test="${info.progress eq 13505}">
			                        <li>
			                            <div class="form-group">
			                                <label class="fw-normal w100 text-right">结算确认日期：</label>
			                                <fmt:parseDate value="${info.settleConfirmDate}" var="settleConfirmDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                                <fmt:formatDate value="${settleConfirmDate}" pattern="yyyy-MM-dd" />
			                            </div>
			                        </li>
			                    </c:if>
			                </ul>
			                <c:if test="${info.progress eq 13504 or info.progress eq 13505}">
				                <ul class="list-inline half form-inline">
				                     <li>
				                        <div class="form-group">
				                            <label class="fw-normal w100 text-right">楼室号：</label>${info.buildingNo}
				                        </div>
				                    </li>
				                     <li>
				                        <div class="form-group">
				                            <label class="fw-normal w100 text-right">大定面积：</label>${info.roughArea}m<sup>2</sup>
				                        </div>
				                    </li>
				                </ul>
				                <ul class="list-inline half form-inline">
				                     <li>
				                        <div class="form-group">
				                            <label class="fw-normal w100 text-right">大定总价：</label><fmt:formatNumber value="${info.roughAmount}" pattern="#,##0.00#"/>元
				                        </div>
				                    </li>
			                        <li>
			                            <div class="form-group">
			                                <label class="fw-normal w100 text-right">${info.progressNm}日期：</label>
			                                <fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                                <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
			                            </div>
			                        </li>
				                </ul>
			                </c:if>
                	</c:otherwise>
                </c:choose>
                
                <c:if test="${info.progress eq 13505}">
	                <ul class="list-inline half form-inline">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">成销面积：</label>${info.area}m<sup>2</sup>
	                        </div>
	                    </li>
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">成销总价：</label><fmt:formatNumber value="${info.dealAmount}" pattern="#,##0.00#"/>元
	                            
	                        </div>
	                    </li>
	                </ul>
                </c:if>
                <ul class="list-inline half form-inline">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">操作人：</label>${info.empNm}
                        </div>
                    </li>
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">操作日期：</label>${info.recognitionDay}
                            
                        </div>
                    </li>
                </ul>
                
                
                
                <c:if test="${info.progress eq 13504}">
	                <ul class="list-inline half form-inline">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">审核状态：</label>${info.roughAuditStatusNm}
	                        </div>
	                    </li>
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">审核时间：</label>${info.roughAuditTime}
	                        </div>
	                    </li>
	                </ul>
	               <ul class="list-inline half form-inline">
                       <%--<li>
                           <div class="form-group">
                               <label class="fw-normal w100 text-right">大定日期：</label>
                               <fmt:parseDate value="${info.roughInputDate}" var="roughInputDate" pattern="yyyy-MM-dd HH:mm:ss"/>
                               <fmt:formatDate value="${roughInputDate}" pattern="yyyy-MM-dd" />
                           </div>
                       </li>--%>
                       <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">审核人：</label>${info.roughAuditUserName}
	                        </div>
	                    </li>
	                </ul>
                </c:if>
                
                <c:if test="${reqMap.brokeragePage eq '22003'}">
	                <ul class="list-inline half form-inline">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">应计税前金额：</label>${info.yjPreamountSum}
	                        </div>
	                    </li>
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">应计税后金额：</label>${info.yjAfteramountSum}
	                        </div>
	                    </li>
	                </ul>
	                
	                <ul class="list-inline half form-inline">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">实返税前金额：</label>${info.sjPreamountSum}
	                        </div>
	                    </li>
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">实返税后金额：</label>${info.sjAfteramountSum}
	                        </div>
	                    </li>
	                </ul>
               </c:if>
                
                
                 	<ul class="list-inline half form-inline">
                 		<c:if test="${reqMap.brokeragePage eq '22003'}">    
                		<li>
	                        <div class="form-group">
	                            <label class="fw-normal w120 text-right">结佣状态：</label>${info.brokerageStatusNm}
	                        </div>
	                    </li>
	                    </c:if>
                 		<%-- <c:if test="${info.customerFrom eq 17403}">
		                    <li>
		                        <div class="form-group">
		                            <label class="fw-normal w120 text-right">APP报备编号：</label>${info.fyReportId}
		                        </div>
		                    </li>
	                    </c:if> --%>
                    </ul>
   
            </div>
   		<c:if test="${reqMap.brokeragePage eq '22003'}">         
            <div style="margin-top:1px;" class="table table-bordered">
	            <table class="table table-striped table-hover">
	              <tr>
	                <th>序号</th>
	                <th>结佣日期</th>
	                <th>结佣税前金额</th>
	                <th>结佣税后金额</th>
	                <th>操作人</th>
	                <th>操作时间</th>
	              </tr>
	              <c:forEach var="detailList" items="${info.brokerageDetailList}" varStatus="status">
	              	<c:if test="${detailList.amountType eq 'sj'}">
	             	<tr>
	                  <td>${status.count}</td>
	                  <td>${detailList.ym}</td>
	                  <td>${detailList.preamount}</td>
	                  <td>${detailList.afteramount}</td>
	                  <td>${detailList.userName}</td>
	                  <td>${detailList.opDt}</td>
	                </tr>
	                </c:if>
	              </c:forEach>
	            </table>
	          </div>
	    </c:if>
            
            
            
<c:if test="${not empty info.fileList or not empty info.fangyouFileList}">
    <c:forEach items="${info.fileList}" var="list" varStatus="status">
        <c:if test="${list.fileTypeId eq 1021}">
            <c:set var="type1021" value="${info.progress}"></c:set>
        </c:if>
        <c:if test="${list.fileTypeId eq 1022}">
            <c:set var="type1022" value="${info.progress}"></c:set>
        </c:if>
        <c:if test="${list.fileTypeId eq 1023}">
            <c:set var="type1023" value="${info.progress}"></c:set>
        </c:if>
        <c:if test="${list.fileTypeId eq 1024}">
            <c:set var="type1024" value="${info.progress}"></c:set>
        </c:if>
        <c:if test="${list.fileTypeId eq 1025}">
            <c:set var="type1025" value="${info.progress}"></c:set>
        </c:if>
    </c:forEach>
   
   <c:forEach items="${info.fangyouFileList}" var="list" varStatus="status">
          <c:if test="${list.typeId eq 1}">
            <c:set var="type1021" value="${info.progress}"></c:set>
        </c:if>
        <c:if test="${list.typeId eq 2}">
            <c:set var="type1022" value="${info.progress}"></c:set>
        </c:if>
        <c:if test="${list.typeId eq 3}">
            <c:set var="type1023" value="${info.progress}"></c:set>
        </c:if>
   </c:forEach>
</c:if>
        <c:if test="${(type1021 eq info.progress && type1021 eq 13501) ||
            (type1022 eq info.progress && type1022 eq 13502) ||
            (type1023 eq info.progress && type1023 eq 13504) ||
            (type1024 eq info.progress && type1024 eq 13504) ||
            (type1025 eq info.progress && type1025 eq 13505)}">
            <div class="page-content">
                <h4>
                    <strong>附件</strong>
                </h4>
            </div>
            <table class="table-sammary" name="Viewerbox">
                <col style="width:145px;">
                <col style="width:auto">
                <c:if test="${(type1021 eq info.progress && type1021 eq 13501) ||
                    (type1022 eq info.progress && type1022 eq 13502) ||
                    (type1023 eq info.progress && type1023 eq 13504) ||
                    (type1025 eq info.progress && type1025 eq 13505)}">
                <c:if test="${info.progress eq 13504}">
                <tr>
                    <td colspan="2">
                        <div class="" role="main">
                            <div>
                                <div class="pd10" >
                                    <h4 class="thumb-title">
                                            带看单
                                    </h4>
                                    <div class="thumb-xs-box">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${info.fileList}" var="list" varStatus="status">
                                                <c:if test="${info.progress eq 13504 && list.fileTypeId eq 1022}">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${not empty info.fangyouFileList}">
	                                            <c:forEach items="${info.fangyouFileList}" var="list" varStatus="status">
	                                                <c:if test="${info.progress eq 13504 && (list.typeId eq 3 && list.fileTypeCode eq 1022)}">
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
		                                            <c:forEach items="${info.fangyouFileList}" var="list" varStatus="status">
			                                                <c:if test="${info.progress eq 13504 && (list.typeId eq 2)}">
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
                </c:if>
                <tr>
                    <td colspan="2">
                        <div class="" role="main">
                            <div>
                                <div class="pd10" >
                                    <h4 class="thumb-title">
                                        <c:if test="${info.progress eq 13501}">
                                            报备明细表
                                        </c:if>
                                        <c:if test="${info.progress eq 13502}">
                                            带看单
                                        </c:if>
                                        <c:if test="${info.progress eq 13504}">
                                        
                                            大定单
                                        </c:if>
                                        <c:if test="${info.progress eq 13505}">
                                            成销确认书/佣金结算资料
                                        </c:if>
                                    </h4>
                                    <div class="thumb-xs-box">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${info.fileList}" var="list" varStatus="status">
                                                <c:if test="${info.progress eq 13501 && list.fileTypeId eq 1021}">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                                <c:if test="${info.progress eq 13502 && list.fileTypeId eq 1022}">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                                <c:if test="${info.progress eq 13504 && list.fileTypeId eq 1023}">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                                <c:if test="${info.progress eq 13505 && list.fileTypeId eq 1025}">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${not empty info.fangyouFileList}">
                                            <c:forEach items="${info.fangyouFileList}" var="list" varStatus="status">
                                              <c:if test="${info.progress eq 13501 && list.typeId eq 1}">
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
                                                <c:if test="${info.progress eq 13502 && list.typeId eq 2}">
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
                                                <c:if test="${info.progress eq 13504 && (list.typeId eq 3 && list.fileTypeCode eq 1023)}">
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
                
            </c:if>
                <c:if test="${type1024 eq info.progress and type1024 eq 13504}">
                    <tr>
                        <td colspan="2">
                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            甲方项目负责人名片
                                        </h4>
                                        <div class="thumb-xs-box">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${info.fileList}" var="list" varStatus="status">
                                                <c:if test="${info.progress eq 13504 && list.fileTypeId eq 1024}">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                            <span class="thumb-img">
                                                                <span class="thumb-img-container">
                                                                    <span class="thumb-img-content">
                                                                        <img alt="" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                    </span>
                                                                </span>
                                                            </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${not empty info.fangyouFileList}">
                                            <c:forEach items="${info.fangyouFileList}" var="list" varStatus="status">
                                                <c:if test="${info.progress eq 13504 && list.typeId eq 3 && list.fileTypeCode eq 1024}">
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
                </c:if>
                <c:if test="${empty type1024 && info.progress eq 13504}">
                    <tr>
                        <td colspan="2">
                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            甲方项目负责人名片
                                        </h4>
                                        <div class="thumb-xs-box">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${info.fileList}" var="list" varStatus="status">
                                                <c:if test="${info.progress eq 13504 && list.fileTypeId eq 1024}">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${not empty info.fangyouFileList}">
                                            <c:forEach items="${info.fangyouFileList}" var="list" varStatus="status">
                                                <c:if test="${info.progress eq 13504 && (list.typeId eq 3 && list.fileTypeCode eq 1024)}">
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
                </c:if>
            </table>
            </c:if>
    </div>
    </div>

