<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/frameContract/frameContractAdd.js?_v=${vs}"></script>
</head>

<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container theme-hipage ng-scope">
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">合同管理</a></li>
				<li><a href="#"  class="a_hover">>框架协议管理</a></li>
				<li><a href="#"  class="a_hover">>框架协议详情</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="page-content">
				<input type="hidden" id="receiveId" name="receiveId" value="${receiveId}"/>
				<input type="hidden" id="loginUserCode" name="loginUserCode" value="${loginUserCode}"/>
				<input type="hidden" id="accountProjectCode" name="accountProjectCode" value="${accountProjectCode}"/>
				<input type="hidden" id="accountProject" name="accountProject" value="${accountProject}"/>
				<input type="hidden" id="accountProjectLenth" name="accountProjectLenth" value="${accountProjectLenth}"/>
				<input type="hidden" id="autoToOa" name="autoToOa" value="${frameContractInfo.autoToOa}"/>
				<h4 class="border-bottom pdb10">
					<strong>框架协议详情</strong>
					<a href="${ctx}/frameContract?searchParam=1" class="btn btn-primary">返回</a>
					<c:if test="${oaOperator eq '1' and (frameContractInfo.approveState eq 10401 or frameContractInfo.approveState eq 10404)}">
						<c:if test="${frameContractInfo.submitOAStatus ne 21202}">
                            <a id="submitToOA" href="javascript:FrameContractAdd.submitToOA(${frameContractInfo.id})"
                               class="btn btn-primary" style="margin-right:12px">提交审核</a>
                        </c:if>
					</c:if>
					<shiro:hasPermission name="/fc:OPERATE_STATUS">
                        <c:if test="${frameContractInfo.approveState eq 10402}">
                            <a href="javascript:void(0)" onclick="operateChangeCt(${frameContractInfo.id});" id="operateChangeCt" class="btn btn-primary" style="margin-right:10px;">运营维护合同状态</a>
                        </c:if>
                    </shiro:hasPermission>
				</h4>
				<div style="margin-bottom:20px;">
					<p>
						<strong style="font-size:16px;">基本信息</strong>
					</p>
					<table class="table-sammary">
							<col style="width:145px;">
							<col style="width:auto">
							<!-- <col style="width:130px;">
							<col style="width:auto"> -->
							<col style="width:200px;">
							<col style="width:auto">
							<tr>
								<td class="talabel" style="vertical-align: text-top;">合同编号：</td>
								<td style="vertical-align: text-top;">${frameContractInfo.contractNo}</td>
								<td class="talabel" style="vertical-align: text-top;">协议类型：</td>
								<td style="vertical-align: text-top;">
									${frameContractInfo.agreementTypeVal}
								</td>
								
							</tr>
							<tr>
								<td class="talabel" style="vertical-align: text-top;">乙方公司：</td>
								<td style="vertical-align: text-top;">
									${frameContractInfo.companyName}
								</td>
								<td class="talabel" style="vertical-align: text-top;">统一社会信用代码：</td>
								<td style="vertical-align: text-top;">${frameContractInfo.businessLicenseNo}</td>
							</tr>
							<tr>
								<td class="talabel" style="vertical-align: text-top;">法定代表/负责人：</td>
								<td style="vertical-align: text-top;">${frameContractInfo.legalPerson}</td>
								<td class="talabel" style="vertical-align: text-top;">公司注册地址：</td>
								<td style="vertical-align: text-top;" title="${frameContractInfo.cityNm}${frameContractInfo.districtNm}${frameContractInfo.address}" class="text-overflow">
									${frameContractInfo.cityNm}${frameContractInfo.districtNm}${frameContractInfo.address}
								</td>
							</tr>
							<tr>
								<td class="talabel">合同开始日期：</td>
								<td>
								${sdk:ymd2(frameContractInfo.dateLifeStart)}
								</td>
								<td class="talabel">合同到期日期：</td>
								<td>${sdk:ymd2(frameContractInfo.dateLifeEnd)}</td>
							</tr>
							<tr>
								<td class="talabel">合同签订日期：</td>
								<td>${sdk:ymd2(frameContractInfo.signDate)}</td>
								<td class="talabel">是否自动续签：</td>
								<td>${frameContractInfo.reAgreeFlagVal}</td>
							</tr>
							<tr>
								<td class="talabel">开户名：</td>
								<td>${frameContractInfo.accountNm}
								</td>
								<td class="talabel">开户行：</td>
								<td>${frameContractInfo.bankAccountNm}</td>
							</tr>
							
							<tr>
								<td class="talabel">开户省市：</td>
								<td>${frameContractInfo.accountProvinceNm}${frameContractInfo.accountCityNm}</td>
								<td class="talabel">银行帐号：</td>
								<td>${frameContractInfo.bankAccount}</td>
							</tr>
							<!-- <tr>
								<td class="talabel"  colspan="1" >房友帐号信息接收人：</td>
								<td>${frameContractInfo.fyAccountNm}
								</td>
								<td class="talabel" >接收人手机号码：</td>
								<td>${frameContractInfo.fyAccountNmTel}</td>
							</tr> -->
							<tr>
								<td class="talabel">提交OA状态：</td>
								<c:if test="${frameContractInfo.submitOAStatus eq 21202||frameContractInfo.submitOAStatus eq 21204}">
									<td style="color:red;">${frameContractInfo.submitOAStatusNm}</td>
								</c:if>
								<c:if test="${frameContractInfo.submitOAStatus eq 21201||frameContractInfo.submitOAStatus eq 21203}">
									<td>${frameContractInfo.submitOAStatusNm}</td>
								</c:if>
								
								<td class="talabel">创建时间：</td>
                            	<td>${frameContractInfo.dateCreate}</td>
							</tr>
								
							<tr>
								<td class="talabel" >合同审核状态：</td>
								<td>${frameContractInfo.approveStatusNm}</td>
								<td class="talabel" >合同审核通过时间：</td>
								<td>${frameContractInfo.approvePassDate }</td>
							</tr>
							<tr>
								<td class="talabel" >OA单号：</td>
								<td>${frameContractInfo.oaNo}</td>
							</tr>
							<tr>
								<td class="talabel" style="vertical-align: text-top;">合同说明：</td>
								<td colspan="3">${frameContractInfo.contractNote}
								</td>
							</tr>
							<tr>
								<td class="talabel" style="vertical-align: text-top;">备注：</td>
								<td colspan="3">${frameContractInfo.remark}</td>
							</tr>
						</table>
					</div>
                 <div class="page-content" >
                    <h4 style="margin-right:14px;margin-left:1px;">
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
                                    <div>
                                        <h4 class="thumb-title" style="font-size:14px;;padding-top:0px;">
                                            营业执照
                                        </h4>
                                        <div class="thumb-xs-box" id="fileBusinessList">
                                            <c:if test="${not empty frameContractInfo.fileBusinessList }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${frameContractInfo.fileBusinessList}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="营业执照" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div>
                                        <h4 class="thumb-title" style="font-size:14px;;padding-top:0px;">
                                            合同
                                        </h4>
                                        <div class="thumb-xs-box" id="fileRecordMainAttachment">
                                            <c:if test="${not empty frameContractInfo.fileContractList}">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${frameContractInfo.fileContractList}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="合同" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div >
                                        <h4 class="thumb-title" style="font-size:14px;padding-top:0px;">
                                            其他
                                        </h4>
                                        <div class="thumb-xs-box" id="attachmentFileList">
                                            <c:if test="${not empty frameContractInfo.attachmentFileList }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${frameContractInfo.attachmentFileList}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="其他" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                        </td>
                    </tr>
                   </table>
			</div>
		</div>
	</div> 
</body>
</html>
