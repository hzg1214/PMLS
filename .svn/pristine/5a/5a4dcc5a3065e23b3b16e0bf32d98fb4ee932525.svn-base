<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
 <div class="container" role="main">
 
    <span class="" style="float:right"><a href="javascript:QtReportDetail.close();" class="btn btn-default">&times;</a></span>
 
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>
                	<c:choose>
			         	<c:when test="${reqMap.operStatus eq '22003' || reqMap.operStatus eq '22002'}">
			         		结佣
			         	</c:when>
			         	<c:when test="${reqMap.operStatus eq '27301'}">
			         		报备
			         	</c:when>
			         	<c:when test="${reqMap.operStatus eq '27302'}">
			         		成销
			         	</c:when>
			         	<c:otherwise>
			         	</c:otherwise>
			       </c:choose>
                </strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <strong>
                                <label class="fw-normal w100 text-right"><strong>项目编号：</strong></label>${info.estate.projectNo}
                                <label class="fw-normal w100 text-right"><strong>楼盘名称：</strong></label>${info.estate.estateNm}
                            </strong>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备编号：</label>${info.reportNo}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w100 text-right">合作方：</label>
						${info.partnerNm}
					</div>
				</li>
			</ul>
<!-- 			报备 -->
			<c:if test="${reqMap.operStatus eq '27301'}">
        	<c:forEach var="detail" items="${info.qtReportDetailList}" varStatus="status">
	        <c:if test="${detail.businessType eq '27301'}">
                <ul class="list-inline half form-inline">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">收入类型：</label>${detail.detailSrTypeName}
	                        </div>
	                    </li>
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">收入金额：</label>
	                            <fmt:formatNumber value="${detail.srAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                    </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">报备日期：</label>${sdk:ymd2(detail.businessDate)}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">备注：</label>${detail.memo}
                        </div>
                    </li>

                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">操作人：</label>${detail.userName}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">操作时间：</label>
							<c:choose>
								<c:when test="${not empty detail.uptDate}">
									${sdk:ymd2(detail.uptDate)}
								</c:when>
								<c:otherwise>
									${sdk:ymd2(detail.crtDate)}
								</c:otherwise>
							</c:choose>
                        </div>
                    </li>
                </ul>
	        </c:if>
	        </c:forEach>
	        </c:if>
<!-- 	        成销 -->
			<c:if test="${reqMap.operStatus eq '27302'}">
            <c:forEach var="detail" items="${info.qtReportDetailList}" varStatus="status">
            <c:if test="${detail.businessType eq '27302'}">
            	<ul class="list-inline half form-inline">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">收入类型：</label>${detail.detailSrTypeName}
	                        </div>
	                    </li>
	                    <li>
		                    <div class="form-group">
		                        <label class="fw-normal w100 text-right">成销金额：</label>
		                        <fmt:formatNumber value="${detail.dealAmount}" pattern="#,##0.00#"/>元
		                    </div>
		                </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">成销日期：</label>${sdk:ymd2(detail.businessDate)}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">应计收入税前：</label>
	                            <fmt:formatNumber value="${detail.befYJSRAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                    </li>
	                    <li>
		                    <div class="form-group">
		                        <label class="fw-normal w100 text-right">应计收入税后：</label>
		                        <fmt:formatNumber value="${detail.aftYJSRAmount}" pattern="#,##0.00#"/>元
		                    </div>
		                </li>  
                </ul>
                <ul class="list-inline half form-inline">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-right">应计返佣税前：</label>
	                            <fmt:formatNumber value="${detail.befYJFYAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                    </li>
	                    <li>
		                    <div class="form-group">
		                        <label class="fw-normal w100 text-right">应计返佣税后：</label>
		                        <fmt:formatNumber value="${detail.aftYJFYAmount}" pattern="#,##0.00#"/>元
		                    </div>
		                </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">核算主体：</label>${detail.accountProject}(${detail.accountProjectNo})
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">操作人：</label>${detail.userName}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">操作时间：</label>
							<c:choose>
								<c:when test="${not empty detail.uptDate}">
									${sdk:ymd2(detail.uptDate)}
								</c:when>
								<c:otherwise>
									${sdk:ymd2(detail.crtDate)}
								</c:otherwise>
							</c:choose>
                        </div>
                    </li>
                </ul>
                <div class="page-content">
		             <h4>
		                 <strong style="font-size: 15px;margin-left: 40px;">返佣维护</strong>
		             </h4>
		         </div>
		         <div style="margin-left: 50px;" class="table">
		            <table class="table table-striped table-hover table-bordered" style="width: 70%;">
		              <tr>
		                <th style="width: 80px;">序号</th>
		                <th style="width: 400px;">返佣对象</th>
		                <th>应计返佣税前(元)</th>
		                <th>应计返佣税后(元)</th>
		              </tr>
		              <c:forEach var="detailList" items="${yjReport}" varStatus="status">
		             	<tr>
		                  <td>${status.count}</td>
		                  <td>${detailList.companyName}(${detailList.companyNo})</td>
		                  <td><fmt:formatNumber value="${detailList.yjbefTaxAmount}" pattern="#,##0.00#"/></td>
		                  <td><fmt:formatNumber value="${detailList.yjaftTaxAmount}" pattern="#,##0.00#"/></td>
		                </tr>
		              </c:forEach>
		            </table>
		        </div>
            </c:if>
		    </c:forEach>
		    </c:if>
<!--             结佣 -->
            <c:if test="${reqMap.operStatus eq '22003' || reqMap.operStatus eq '22002'}">
            	<ul class="list-inline half form-inline">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w120 text-right">收入类型：</label>${info.srTypeName}
	                        </div>
	                    </li>
	                    <li>
		                    <div class="form-group">
		                        <label class="fw-normal w100 text-right">成销金额：</label>
		                        <fmt:formatNumber value="${info.dealAmount}" pattern="#,##0.00#"/>
		                    </div>
		                </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">成销日期：</label>${sdk:ymd2(info.brokerageDate)}
                        </div>
                    </li>
                    <li>
                       <div class="form-group">
                       <c:if test="${reqMap.operStatus eq '22003'}">
                           <label class="fw-normal w100 text-right">结佣状态：</label>已结佣
                       </c:if>
                       <c:if test="${reqMap.operStatus eq '22002'}">
                           <label class="fw-normal w100 text-right">结佣状态：</label>部分结佣
                       </c:if>
                       </div>
                   	</li>
                </ul>
                <ul class="list-inline half form-inline">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w120 text-right">应计收入税前：</label>
	                            <fmt:formatNumber value="${info.befYJSRAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                    </li>
	                    <li>
		                    <div class="form-group">
		                        <label class="fw-normal w100 text-right">应计收入税后：</label>
		                        <fmt:formatNumber value="${info.aftYJSRAmount}" pattern="#,##0.00#"/>元
		                    </div>
		                </li>  
                </ul>
                <ul class="list-inline half form-inline">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w120 text-right">应计返佣税前：</label>
	                            <fmt:formatNumber value="${info.befYJFYAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                    </li>
	                    <li>
		                    <div class="form-group">
		                        <label class="fw-normal w100 text-right">应计返佣税后：</label>
		                        <fmt:formatNumber value="${info.aftYJFYAmount}" pattern="#,##0.00#"/>元
		                    </div>
		                </li>  
                </ul>
		        <div class="page-content">
		             <h4>
		                 <strong style="font-size: 15px;margin-left: 20px;">返佣维护</strong>
		             </h4>
		         </div>
		         <div class="table">
		            <table class="table table-striped table-hover table-bordered" >
		              <tr>
		                <th style="width:80px;">序号</th>
		                <th style="width:300px;">返佣对象</th>
		                <th>应计返佣税前(元)</th>
		                <th>应计返佣税后(元)</th>
		                <th>实际返佣税前(元)</th>
		                <th>实际返佣税后(元)</th>
		                <th>操作日期</th>
		              </tr>
		              <c:forEach var="detailList" items="${yjReport}" varStatus="status">
		             	<tr>
		                  <td>${status.count}</td>
		                  <td>${detailList.companyName}(${detailList.companyNo})</td>
		                  <td><fmt:formatNumber value="${detailList.yjbefTaxAmount}" pattern="#,##0.00#"/></td>
		                  <td><fmt:formatNumber value="${detailList.yjaftTaxAmount}" pattern="#,##0.00#"/></td>
		                  <td><fmt:formatNumber value="${detailList.sjbefTaxAmount}" pattern="#,##0.00#"/></td>
		                  <td><fmt:formatNumber value="${detailList.sjaftTaxAmount}" pattern="#,##0.00#"/></td>
		                  <td>${sdk:ymd2(detailList.operDetailDate)}</td>
		                </tr>
		              </c:forEach>
		            </table>
		        </div>
		    </c:if> 
        </div>
    </div>
</div>

