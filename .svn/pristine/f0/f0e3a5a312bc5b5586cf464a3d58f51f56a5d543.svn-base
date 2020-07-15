<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
</head>

<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container theme-hipage ng-scope">
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">联动管理</a></li>
				<li><a href="#"  class="a_hover">>佣金管理</a></li>
				<li><a href="#"  class="a_hover">>返佣对象详情</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="page-content">
				<input type="hidden" id="receiveId" name="receiveId" value="${receiveId}"/>
				<h4 class="border-bottom pdb10">
					<strong>返佣对象记录查看</strong>
					<a href="${ctx}/lnkYjReport?searchParam=1" class="btn btn-primary">返回</a>
				</h4>
				<table class="table-sammary">
						<col style="width:135px;">
						<col style="width:460px">
						<col style="width:135px;">
						<col style="width:auto">
						<tr>
							<td class="talabel">报备编号：</td>
							<td>${yjReportInfo.reportId}</td>
							<td class="talabel">楼盘名称：</td>
							<td>
								${yjReportInfo.estateNm}
							</td>
						</tr>
						<tr>
							<td class="talabel">经纪公司：</td>
							<td>
								${yjReportInfo.companyNm}
							</td>
							<td class="talabel">门店地址：</td>
							<td>
								${yjReportInfo.addressDetail}
							</td>
						</tr>
						<tr>
							<td class="talabel">客户：</td>
							<td>${yjReportInfo.customerNm}</td>
							<td class="talabel">客户电话：</td>
							<td>${yjReportInfo.customerTel}</td>
						</tr>
						<tr>
							<td class="talabel">最新进度：</td>
							<td>${yjReportInfo.dicValue}</td>
						</tr>
						<c:forEach items="${yjReportInfo.companys}" var="list" varStatus="index">
						<tr>
							<c:if test="${index.count == 1}">
								<td class="talabel">返佣对象：</td>
							</c:if>
							<c:if test="${index.count != 1}">
								<td class="talabel"></td>
							</c:if>
							<c:if test="${list.companyNo != null}">
								<td>${list.companyName}&nbsp;&nbsp;(${list.companyNo})</td>
							</c:if>
						</tr>
						</c:forEach>
					</table>
				<div class="page-content" style="height: 60px;" >
                    <h4  style="margin-right: 15px;margin-left:15px;font-size:16px;height: 45px;">
                        <strong>维护记录</strong>
                    </h4>
                </div>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered"  style="margin-right: 15px;margin-left:15px;width:1140px">
                    <tr>
                    	<th style="width:100px;">序号</th>
                        <th style="width:200px;">维护人</th>
                        <th style="width:200px;">维护时间</th>
                        <th style="width:auto;">变更内容</th>
                    </tr>
                     <c:forEach items="${yjReportInfo.companyMatins}" var="reportList" varStatus="index"> 
                         <tr> 
                         	<td>${index.count}</td> 
                             <td> 
                               ${reportList.userName} 
                             </td> 
                             <td>${reportList.crtDt}</td> 
<%--                              <c:if test="${empty reportList.companyName}"> --%>
<%--                                        </c:if> --%>
                             <td>${reportList.content}</td> 
                         </tr> 
                     </c:forEach>
                </table>
			</div>
		</div>
	</div> 
</body>
</html>
