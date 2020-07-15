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
</head>

<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container theme-hipage ng-scope">
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">公盘合同列表</a></li>
				<li><a href="#"  class="a_hover">>公盘合同列表</a></li>
				<li><a href="#"  class="a_hover">>公盘合同终止详情</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="page-content">
				<h4 class="border-bottom pdb10">
					<strong>公盘合同终止详情</strong>
					<a href="${ctx}/gpContractChange" class="btn btn-primary">返回</a>
					<shiro:hasPermission name="/ct:OPERATE_STATUS">
						<c:if test="${gpContractChangeInfo.approveState eq 1}">
							<a href="javascript:void(0)" onclick="operateChangeCt(${gpContractChangeInfo.id});" id="operateChangeCt" class="btn btn-primary" style="margin-right:10px;">运营维护状态</a>
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
							<col style="width:200px;">
							<col style="width:auto">
							<tr>
								<td class="talabel" style="vertical-align: text-top;">公盘合同终止编号：</td>
								<td style="vertical-align: text-top;">${gpContractChangeInfo.gpContractStopNo}</td>
								<td class="talabel" style="vertical-align: text-top;">公盘合同编号：</td>
								<td style="vertical-align: text-top;">
									<a href="${ctx}/gpContract/${gpContractChangeInfo.gpContractId}">${gpContractChangeInfo.gpContractNo}</a>
								</td>
								
							</tr>
							<tr>
								<td class="talabel" style="vertical-align: text-top;">经纪公司：</td>
								<td style="vertical-align: text-top;">
									${gpContractChangeInfo.companyName}
								</td>
								<td class="talabel" style="vertical-align: text-top;">终止类型：</td>
								<td style="vertical-align: text-top;">${gpContractChangeInfo.stopTypeNm}</td>
							</tr>
							<tr>
								<td class="talabel">终止日期：</td>
								<td>
								${sdk:ymd2(gpContractChangeInfo.stopDate)}
								</td>
								<td class="talabel">预计退款金额：</td>
								<td>
									<fmt:formatNumber value="${gpContractChangeInfo.ptBackAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
								</td>
							</tr>
							<tr>
								<td class="talabel">终止具体原因：</td>
								<td>${gpContractChangeInfo.stopReason}</td>
							</tr>
							<tr>
								<td class="talabel">终止方案描述：</td>
								<td>${gpContractChangeInfo.stopDescribe}</td>
							</tr>
							<tr>
								<td class="talabel">备注：</td>
								<td>${gpContractChangeInfo.remarks}</td>
							</tr>
							<tr>
								<td class="talabel">创建人：</td>
								<td>${gpContractChangeInfo.userName}
								</td>
								<td class="talabel">创建时间：</td>
                            	<td>${gpContractChangeInfo.dateCreate}</td>
							</tr>
							
							<tr>
								<td class="talabel">提交OA状态：</td>
								<c:if test="${gpContractChangeInfo.submitOAStatus eq 21202||gpContractChangeInfo.submitOAStatus eq 21204}">
									<td style="color:red;">${gpContractChangeInfo.submitOAStatusNm}</td>
								</c:if>
								<c:if test="${gpContractChangeInfo.submitOAStatus eq 21201||gpContractChangeInfo.submitOAStatus eq 21203}">
									<td>${gpContractChangeInfo.submitOAStatusNm}</td>
								</c:if>
								<td class="talabel" >合同终止审核状态：</td>
								<td>${gpContractChangeInfo.approveStatusNm}</td>
							</tr>
							<tr>
								<td class="talabel" >合同终止过审时间：</td>
								<td>${gpContractChangeInfo.approvePassDate}</td>
							</tr>
						</table>
					</div>
                 <div class="page-content" >
                    <h4 style="margin-right:14px;margin-left:1px;">
                        <strong>终止门店信息</strong>
                    </h4>
                    <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                    <tr>
                        <th style="width: 100px;">门店编号</th>
                        <th style="width: 150px;">门店名称</th>
                        <th style="width: 180px;">门店地址</th>
                        <th style="width: 120px;">门店负责人</th>
                        <th style="width: 120px;">门店负责人电话</th>
                        <th style="width: 120px;">门店负责人</th>
                    </tr>
                    <c:forEach items="${gpContractChangeInfo.gpContractChangeStoreList}" var="item" varStatus="listIndex">
                        <tr>
                            <td>${item.storeNo}</td>
                            <td title="${item.storeName}" class="text-overflow">${item.storeName}</td>
                            <td title="${item.storeAddress}" class="text-overflow">${item.storeAddress}</td>
                            <td >${item.storeManager}</td>
                            <td >${item.storeManagerPhone}</td>
                            <td >${item.maintainerName}(${item.maintainer})</td>
                        </tr>
                    </c:forEach>
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
                                            终止合作协议书/单方解除函
                                        </h4>
                                        <div class="thumb-xs-box" id="stopContractFileList">
                                            <c:if test="${not empty gpContractChangeInfo.stopContractFileList}">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${gpContractChangeInfo.stopContractFileList}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="终止合作协议书/单方解除函" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                                            保证金收据
                                        </h4>
                                        <div class="thumb-xs-box" id="receiptFileList">
                                            <c:if test="${not empty gpContractChangeInfo.receiptFileList}">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${gpContractChangeInfo.receiptFileList}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="保证金收据" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                                            <c:if test="${not empty gpContractChangeInfo.othersFileList }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${gpContractChangeInfo.othersFileList}" var="list"
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
<script type="text/javascript">
    function operateChangeCt(contractId) {
        if(!isBlank(contractId)) {
            systemLoading("", true);
            $.ajax({
                url:BASE_PATH+"/gpContractChange/operateChangeCt",
                data:$.param({
                    id:contractId
                }),
                type:"post",
                success:function(data){
                    data = JSON.parse(data);
                    if(data && data.returnCode == '200'){
                        Dialog.alertSuccess("状态变更成功!");
                        $("#operateChangeCt").hide();
                        systemLoaded();
                        location.href = BASE_PATH + "/gpContractChange";
                        //location.href = BASE_PATH + "/contract/contractId/contractStatus";
                    }
                },
                error:function(){
                    Dialog.alertError("状态变更失败");
                    $("#operateChangeCt").hide();
                    systemLoaded();
                }
            });
        }

    };

</script>
</html>
