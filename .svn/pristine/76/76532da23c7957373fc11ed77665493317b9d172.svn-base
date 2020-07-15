<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
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
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>
<div class="container">
    <div class="row article">
        <!-- 左侧菜单 -->
        <jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
                     flush="true">
            <jsp:param value="110402" name="leftMenuSelectId"/>
        </jsp:include>
        <div class="col-md-10 content">
            <div class="page-content ul-line-block" name="Viewerbox">
                <br>
                <h4>
                    <strong>合同终止详情</strong>
                    <a href="${ctx}/contract/changeRecord/${contractId}/${contractStatus}" class="btn btn-primary">返回</a>
                    <shiro:hasPermission name="/ct:OPERATE_AUDIT">
                        <c:if test="${contractChange.approveState eq 1}">
                            <a href="javascript:void(0)" onclick="operateAuditCt('${contractChange.id}','${contractId}','${contractStatus}');" id="operateAuditCt" class="btn btn-primary" style="margin-right:10px;">运营维护审核状态</a>
                        </c:if>
                    </shiro:hasPermission>
                </h4>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140  text-right">合同终止申请编号：</label>
                            ${contractChange.contractStopNo}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140  text-right">终止合同编号：</label>
                            ${contractChange.contractNo}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="width:50%;">
                            <label class="fw-normal w140 text-right">终止类型：</label>
                            ${contractChange.stopTypeStr}
                        </div>
                        <div class="form-group" style="padding-left: 15px;">
                            <label class="fw-normal w140 text-right">终止日期：</label>
                            ${sdk:ymd2(contractChange.stopDate)}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">终止具体原因：</label>
                            ${contractChange.stopReason}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">终止方案阐述：</label>
                            ${contractChange.stopDescribe}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">备注：</label>
                            ${contractChange.remarks}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="width:50%;">
                            <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">创建人：</label>
                            ${contractChange.userCreateName}
                        </div>
                        <div class="form-group" style="padding-left:15px;">
                            <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">创建时间：</label>
                            ${contractChange.dateCreate}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="width: 50%;">

                            <label class="fw-normal w140 text-right">提交OA状态：</label>
                            <c:if test="${contractChange.submitOAStatus eq 21202||contractChange.submitOAStatus eq 21204}">
                                <span style="color:red;">${contractChange.submitOAStatusName}</span>
                            </c:if>
                            <c:if test="${contractChange.submitOAStatus eq 21201||contractChange.submitOAStatus eq 21203}">
                                <span>${contractChange.submitOAStatusName}</span>
                            </c:if>
                        </div>
                        <div class="form-group" style="padding-left:15px;">
                            <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">审核状态：</label>
                            ${contractChange.approveStateName}
                        </div>
                    </li>
                </ul>
                <p><strong>终止门店信息</strong></p>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                    <tr>
                        <th style="width:95px">门店编号</th>
		              	<th style="width:80px">门店名称</th>
		              	<th style="width:100px">门店地址</th>
		              	<th style="width:75px">装修情况</th>
		              	<th style="width:75px">装修类型</th>
		              	<th style="width:75px">装修公司</th>
		              	<th style="width:75px">装修费用(元)</th>
		              	<th style="width:100px;text-align:center;">已收保证金(元)</th>
		              	<th style="width:160px;text-align:center;">保证金余额处理方式</th>
		              	<th style="width:100px;text-align:center;">保证金退还金额(元)</th>
                    </tr>

                    <c:forEach items="${storeList}" var="item" varStatus="listIndex">

                        <tr>
                            <td>${item.storeNo}</td>
                            <td title="${item.name}" class="text-overflow">${item.name}</td>
                            <td title="${item.address}" class="text-overflow">${item.address}</td>
                            <td>
	                 			<c:if test="${item.decorateSituate eq 16304}">
									已装修
								</c:if>
								<c:if test="${item.decorateSituate eq 16301 or item.decorateSituate eq 16302 or item.decorateSituate eq 16303  }">
									未装修
								</c:if>
							</td>
	                 		<td>
	                 			<c:if test="${item.decorateSituate eq 16304}">
									<c:if test="${item.decorationType eq 17001}">
										自费装修
									</c:if>
									<c:if test="${item.decorationType eq 17002}">
										我司装修
									</c:if>
								</c:if>
		                 		<c:if test="${item.decorateSituate eq 16301 or item.decorateSituate eq 16302 or item.decorateSituate eq 16303  }">
									-
								</c:if>

							</td>
	                 		<td>
	                 			<c:if test="${item.decorateSituate eq 16301 or item.decorateSituate eq 16302 or item.decorateSituate eq 16303  }">
									-
								</c:if>
								<c:if test="${item.decorateSituate eq 16304}">
									<c:if test="${item.decorationType eq 17001}">
										-
									</c:if>
									<c:if test="${item.decorationType eq 17002}">
										${item.decorateCompany}
									</c:if>
								</c:if>
	                 		</td>
	                 		<td>
	                 		
	                 			<c:if test="${item.decorateSituate eq 16304}">
									<c:if test="${item.decorationType eq 17001}">
										0.00
									</c:if>
									<c:if test="${item.decorationType eq 17002}">
										<fmt:formatNumber type="number" value="${item.decorateAmount}" pattern="0.00" maxFractionDigits="2"/>
										
									</c:if>
								</c:if>
								<c:if test="${item.decorateSituate eq 16301 or item.decorateSituate eq 16302 or item.decorateSituate eq 16303  }">
									0.00
								</c:if>
	                 			
	                 		</td>
	                 		<td>
	                 			<fmt:formatNumber type="number" value="${item.receivedAmount}" pattern="0.00" maxFractionDigits="2"/>  
	                 			
	                 		</td>
	                 		<td>
	                 			<c:if test="${item.depositBalance eq '21301'}">保证金全部退还</c:if>
	                 			<c:if test="${item.depositBalance eq '21302'}">保证金全部没收</c:if>
	                 			<c:if test="${item.depositBalance eq '21303'}">其他</c:if>
	                 		</td>
	                 		<td>
	                 			<fmt:formatNumber type="number" value="${item.depositBackMoney}" pattern="0.00" maxFractionDigits="2"/>
	                 			
	                 		</td>
                        </tr>

                    </c:forEach>
                </table>
                <p><strong>附件</strong></p>
                <div id="stopContractBoxPc">
                    <ul class="list-inline form-inline">
                        <li>

                            <div class="" role="main">
                                <div>
                                    <div class="pd10">
                                        <h4 class="thumb-title">
                                            终止合作协议书/单方解除函
                                        </h4>
                                        <div class="thumb-xs-box" id="thumbXsBox">
                                            <c:if test="${not empty contractChange.stopContractFileList }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractChange.stopContractFileList}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="终止合作协议书" src="${list.fileAbbrUrl}"
                                                             data-original="${list.url50}" class="empPhoto"/>
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
                        </li>
                    </ul>
                </div>
                <div style="display:none;" id="surrenderBoxPc">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="" role="main">
                                <div>
                                    <div class="pd10">
                                        <h4 class="thumb-title">
                                            合同权利义务转让三方协议书
                                        </h4>
                                        <div class="thumb-xs-box" id="thumbXsBox">
                                            <c:if test="${not empty contractChange.surrenderFileList }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractChange.surrenderFileList}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="三方解约协议" src="${list.fileAbbrUrl}"
                                                             data-original="${list.url50}" class="empPhoto"/>
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
                        </li>
                    </ul>
                </div>
                <ul class="list-inline form-inline">
                    <li>

                        <div class="" role="main">
                            <div>
                                <div class="pd10">
                                    <h4 class="thumb-title">
                                        保证金收据
                                    </h4>
                                    <div class="thumb-xs-box" id="thumbXsBox">
                                        <c:if test="${not empty contractChange.receiptFileList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${contractChange.receiptFileList}" var="list"
                                                       varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox"
                                                       target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="保证金收据" src="${list.fileAbbrUrl}"
                                                             data-original="${list.url50}" class="empPhoto"/>
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

                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>

                        <div class="" role="main">
                            <div>
                                <div class="pd10">
                                    <h4 class="thumb-title">
                                        门店照片
                                    </h4>
                                    <div class="thumb-xs-box" id="storePhoto">
                                        <c:if test="${not empty contractChange.storePhotosFileList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${contractChange.storePhotosFileList}" var="list"
                                                       varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox"
                                                       target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="照片" src="${list.fileAbbrUrl}"
                                                             data-original="${list.url50}" class="empPhoto"/>
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
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>

                        <div class="" role="main">
                            <div>
                                <div class="pd10">
                                    <h4 class="thumb-title">
                                        已付装修款退还证明
                                    </h4>
                                    <div class="thumb-xs-box" id="thumbXsBox">
                                        <c:if test="${not empty contractChange.returnProveFileList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${contractChange.returnProveFileList}" var="list"
                                                       varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox"
                                                       target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="已付装修款退还证明" src="${list.fileAbbrUrl}"
                                                             data-original="${list.url50}" class="empPhoto"/>
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
                    </li>
                </ul>
                <div style="display:none;" id="cancellateBoxPc">
                    <ul class="list-inline form-inline">
                        <li>

                            <div class="" role="main">
                                <div>
                                    <div class="pd10">
                                        <h4 class="thumb-title">
                                            注销单
                                        </h4>
                                        <div class="thumb-xs-box" id="thumbXsBox">
                                            <c:if test="${not empty contractChange.cancellateFileList }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractChange.cancellateFileList}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="注销证明" src="${list.fileAbbrUrl}"
                                                             data-original="${list.url50}" class="empPhoto"/>
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
                        </li>
                    </ul>
                </div>


                <ul class="list-inline form-inline">
                    <li>


                        <ul class="list-inline form-inline">
                            <li>

                                <div class="" role="main">
                                    <div>
                                        <div class="pd10">
                                            <h4 class="thumb-title">
                                                其它
                                            </h4>
                                            <div class="thumb-xs-box" id="thumbXsBox">
                                                <c:if test="${not empty contractChange.othersFileList }">
                                                    <c:set var="fileSize" value="0"/>
                                                    <c:forEach items="${contractChange.othersFileList}" var="list"
                                                               varStatus="status">
                                                        <c:set var="fileSize" value="${fileSize + 1}"/>
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="javascript:void(0);" class="thumbnail swipebox"
                                                               target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="其它" src="${list.fileAbbrUrl}"
                                                             data-original="${list.url50}" class="empPhoto"/>
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
                            </li>
                        </ul>
            </div>
        </div>
    </div>

</div>
</body>
</html>
<script type="text/javascript">
    $(function () {
        var selectValue = ${contractChange.stopType};
        if (selectValue == 16401) {
            $("#stopContractBoxPc").show();
            $("#cancellateBoxPc").show();
            $("#surrenderBoxPc").hide();
        }
        if (selectValue == 16406) {
            $("#stopContractBoxPc").show();
            $("#surrenderBoxPc").hide();
            $("#cancellateBoxPc").hide();
        }
        if (selectValue == 16407) {
            $("#surrenderBoxPc").show();
            $("#stopContractBoxPc").hide();
            $("#cancellateBoxPc").hide();
        }
    });

    function operateAuditCt(id, contractId, contractStatus) {
        if(!isBlank(id)) {
            systemLoading("", true);
            $.ajax({
                url:BASE_PATH+"/stopcontract/operateAuditCt",
                data:$.param({
                    id:id
                }),
                type:"post",
                success:function(data){
                    data = JSON.parse(data);
                    if(data && data.returnCode == '200'){
                        Dialog.alertSuccess("状态变更成功!");
                        $("#operateAuditCt").hide();
                        systemLoaded();
                        location.href = BASE_PATH + '/contract/changeRecord/'+contractId+"/"+contractStatus;
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


