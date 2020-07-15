<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/WEB-INF/page/common/meta.jsp"%>
	<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>

	<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/report/achievementView.js?_v=${vs}"></script>
	<%-- <script type="text/javascript" src="${ctx}/meta/js/contract/contract.js?_v=${vs}"></script>  --%>
	<style>
		.tran_step {
			display: table;
			padding: 0;
		}
		.tran_step>li {
			display: table-cell;
			width: 1%;
			text-align: center;
			vertical-align: middle;
			height: 46px;
			line-height: 46px;
			color: #fff;
			position: relative;
			background-color: #c2c6c7;
		}
		.fast-filing-step .tran_step>li.active {
			background-color: #337ab7;
		}
		.tran_step>li.active {
			background-color: #337ab7;
		}
		.tran_step > li:first-child {
			border-radius: 3px 0 0 3px;
		}
		.fast-filing-step .tran_step>li {
			height: 45px;
		}

		.tran_step a {
			color: #fff;
		}
		.step-item>a>span {
			display: block;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
		.step-item>a>span:before {
			content: attr(data-name);
			top: 65%;
		}
		.step-item>a>span:before, .step-item>a>span:after {
			color: #a1a1a1;
			font-size: 13px;
			position: absolute;
			left: 20px;
			top: 37px;
			left: 0;
			right: 0;
		}
		.step-item>a>span:after {
			content: attr(data-date);
			top: 70%;
		}
		.tran_step>li + li:before {
			content: "\20";
			display: block;
			width: 33px;
			height: 33px;
			border-width: 2px 2px 0 0;
			border-color: #fff;
			border-style: solid;
			position: absolute;
			top: 7px;
			left: -17px;
			-webkit-transform: rotate(45deg);
			transform: rotate(45deg);
			background-color:#c2c6c7;
		}
		.fast-filing-step .tran_step>li.active + li:before {
			background-color: #337ab7;
		}
		.tran_step>li.active + li:before {
			background-color: #337ab7;
		}
	</style>
</head>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
	<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
</jsp:include>


<form id = "ReportViewForm" >
	<input type = "hidden"  id = "id"   name = "id" value = "${reportInfo.report.id}"/>
	<input type = "hidden"  id = "customerFromValue"   name = "customerFromValue" value = "${reportInfo.report.customerFrom}"/>
	<div class="container theme-hipage ng-scope" role="main">
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">联动管理</a></li>
				<li><a href="#"  class="a_hover">>案场管理</a></li>
				<li><a href="#"  class="a_hover">>
					<c:choose>
						<c:when test="${fromType eq '0'}">
							报备
						</c:when>
						<c:when test="${fromType eq '2'}">
							项目
						</c:when>
						<c:when test="${fromType eq '3'}">
							大定待审核
						</c:when>
						<c:when test="${fromType eq '4'}">
							大定已审核
						</c:when>
					</c:choose>
				</a></li>
				<li><a href="#"  class="a_hover">>报备详情</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="page-content">
				<h4 class="border-bottom pdb10">
					<strong>报备详情</strong>
					<c:if test="${fromType eq '0'}">
						<a href="${ctx}/report?searchParam=1" class="btn btn-primary">返回</a>
					</c:if>
					<c:if test="${fromType eq '1'}">
						<a href="${ctx}/linkAchieveChange?searchParam=1" class="btn btn-primary">返回</a>
					</c:if>
					<c:if test="${fromType eq '2'}">
						<a href="${ctx}/sceneEstate/qSceneRecognition/${reportInfo.report.estateId}?searchParam=1" class="btn btn-primary">返回</a>
					</c:if>
					<c:if test="${fromType eq '3'}">
						<a href="${ctx}/reportToValid?searchParam=1" class="btn btn-primary">返回</a>
					</c:if>
					<c:if test="${fromType eq '4'}">
						<a href="${ctx}/reportToValid/valided?searchParam=1" class="btn btn-primary">返回</a>
					</c:if>
					<%--<c:if test="${reportInfo.report.acCityNo eq userAcCityNo}">--%>
						<shiro:hasPermission name="/lnk:ACHIEVEMENT_AJ">
							<a href="javascript:AchievementView.editAchievementMode()" class="btn btn-primary" style="margin-right:16px" id="editAchievementMode">业绩调整</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="/lnk:CUSTOMER_AJ">
							<a href="javascript:AchievementView.editCustomerInfoMode()" class="btn btn-primary" style="margin-right:16px">客户信息调整</a>
						</shiro:hasPermission>
					<%--</c:if>--%>
				</h4>
				<table class="table-sammary">
					<col style="width:109px;">
					<col style="width:auto;">
					<col style="width:109px;">
					<col style="width:auto;">
					<col style="width:116px;">
					<col style="width:auto;">
					<tr>
						<td style="text-align: right;"><strong>项目编号：</strong></td>
						<td colspan="5"><strong>${reportInfo.report.projectNo}</strong>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>楼盘名称：</strong>
							<strong>${reportInfo.report.estateNm}</strong>
						</td>
					</tr>
					<tr>
						<td class="talabel">PMLS报备编号：</td>
						<td>${reportInfo.report.reportId}</td>
						<td class="talabel">经纪公司：</td>
						<c:choose>
							<c:when test="${not empty  reportInfo.report.companyNm}">
								<td>${reportInfo.report.companyNm}(${reportInfo.report.companyId})</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
						<td class="talabel">门店名称：</td>
						<td>${reportInfo.report.storeNm}(${reportInfo.report.storeNo})</td>
					</tr>
					<tr>
						<td class="talabel">门店合作模式：</td>
						<td>${reportInfo.report.contractTypeNm}</td>
						<td class="talabel">客户：</td>
						<td>${reportInfo.report.customerNm} &nbsp; ${reportInfo.report.customerTel}</td>
						<td class="talabel">客户：</td>
						<td>${reportInfo.report.customerNmTwo} &nbsp; ${reportInfo.report.customerTelTwo}</td>
					</tr>
					<tr>
						<td class="talabel">报备经纪人：</td>
						<td>${reportInfo.report.reportAgent} &nbsp; ${reportInfo.report.reportAgentTel}</td>
						<td class="talabel">业绩归属中心：</td>
						<td>${reportInfo.report.centerGroupName}</td>
						<td class="talabel">业绩归属人：</td>
						<td>${reportInfo.report.contactNm}</td>
					</tr>
					<c:choose>
						<c:when test="${(reportInfo.report.latestProgress eq '13504' or reportInfo.report.latestProgress eq '13505') and reportInfo.report.roughAuditStatus eq '1'}">
							<tr>
								<td class="talabel">最新进度：</td>
								<c:choose>
									<c:when test="${reportInfo.report.brokerageStatus eq '22002' or reportInfo.report.brokerageStatus eq '22003'}">
										<td>结佣</td>
									</c:when>
									<c:when test="${reportInfo.report.latestProgress eq '13503'}">
										<td>带看</td>
									</c:when>
									<c:otherwise>
										<td>${reportInfo.report.latestProgressNm}</td>
									</c:otherwise>
								</c:choose>
								<td class="talabel">报备来源：</td>
								<td>
									<c:if test="${reportInfo.report.customerFrom eq 17401}">CRM</c:if>
									<c:if test="${reportInfo.report.customerFrom eq 17402}">CRM</c:if>
									<c:if test="${reportInfo.report.customerFrom eq 17403}">APP</c:if>
									<c:if test="${reportInfo.report.customerFrom eq 17404}">房友助手</c:if>
									<c:if test="${reportInfo.report.customerFrom eq 17405}">友房通</c:if>
								</td>
								<td class="talabel">收入类型：</td>
								<td>${reportInfo.report.inComeName}</td>
								<c:if test="${reportInfo.report.customerFrom eq 17403}">
									<td class="talabel">APP报备编号：</td>
									<td>${reportInfo.report.fyReportId}</td>
								</c:if>
							</tr>
							<c:if test="${reportInfo.report.customerFrom eq 17405}">
								<tr>
									<td class="talabel">
										<div style="width: 112px">友房通编码：</div>
									</td>
									<td>${reportInfo.report.fyReportId}</td>
								</tr>
							</c:if>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="talabel">最新进度：</td>
								<c:choose>
									<c:when test="${reportInfo.report.brokerageStatus eq '22002' or reportInfo.report.brokerageStatus eq '22003'}">
										<td>结佣</td>
									</c:when>
									<c:when test="${reportInfo.report.latestProgress eq '13503'}">
										<td>带看</td>
									</c:when>
									<c:otherwise>
										<td>${reportInfo.report.latestProgressNm}</td>
									</c:otherwise>
								</c:choose>
								<td class="talabel">报备来源：</td>
								<td>
									<c:if test="${reportInfo.report.customerFrom eq 17401}">CRM</c:if>
									<c:if test="${reportInfo.report.customerFrom eq 17402}">CRM</c:if>
									<c:if test="${reportInfo.report.customerFrom eq 17403}">APP</c:if>
									<c:if test="${reportInfo.report.customerFrom eq 17404}">房友助手</c:if>
									<c:if test="${reportInfo.report.customerFrom eq 17405}">友房通</c:if>
								</td>
								<c:if test="${reportInfo.report.customerFrom eq 17403}">
									<td class="talabel">APP报备编号：</td>
									<td>${reportInfo.report.fyReportId}</td>
								</c:if>
								<c:if test="${reportInfo.report.customerFrom eq 17405}">
									<td class="talabel">
										<div style="width: 112px">友房通编码：</div>
									</td>
									<td>${reportInfo.report.fyReportId}</td>
								</c:if>
							</tr>
						</c:otherwise>
					</c:choose>
					<c:if test="${(reportInfo.report.customerFrom eq 17403 or reportInfo.report.customerFrom eq 17405) and reportInfo.report.latestProgress==13504}">
						<tr>
							<td class="talabel">大定审核状态：</td>
							<c:if test="${reportInfo.report.roughAuditStatus eq '0'}"><td>待审核</td></c:if>
							<c:if test="${reportInfo.report.roughAuditStatus eq '1'}"><td>审核通过</td></c:if>
							<c:if test="${reportInfo.report.roughAuditStatus eq '2'}"><td>审核驳回</td></c:if>
						</tr>
					</c:if>
				</table>
				<div class="fast-filing-step" style="margin-top:10px;">
					<ul class="tran_step">
						<c:set var="flag1" value="0"></c:set>
						<c:forEach items="${reportInfo.reportDetails}" var="reportDetail" varStatus="reportIndex">
							<c:if test="${reportDetail.progress==13501}"><!-- 报备、未结佣 -->
								<li class="step-item active"><c:choose><c:when test="${reportInfo.report.latestReportFollowDate!=''}"><a><span data-date="${reportInfo.report.latestReportFollowDate}">报备</span></a></c:when><c:otherwise><a><span>报备</span></a></c:otherwise></c:choose></li>
								<c:set var="flag1" value="1"></c:set>
							</c:if>
						</c:forEach>
						<c:if test="${flag1 == '0'}"><li class="">报备</li></c:if>

						<c:set var="flag2" value="0"></c:set>
						<c:forEach items="${reportInfo.reportDetails}" var="reportDetail" varStatus="reportIndex">
							<c:if test="${reportDetail.progress==13502}"><!-- 带看、未结佣 -->
								<li class="step-item active"><c:choose><c:when test="${reportInfo.report.latestRelationFollowDate!=''}"><a><span data-date="${reportInfo.report.latestRelationFollowDate}">带看</span></a></c:when><c:otherwise><a><span>带看</span></a></c:otherwise></c:choose></li>
								<c:set var="flag2" value="1"></c:set>
							</c:if>
						</c:forEach>
						<c:if test="${flag2 == '0'}"><li class="">带看</li></c:if>



						<c:set var="flag4" value="0"></c:set>
						<c:forEach items="${reportInfo.reportDetails}" var="reportDetail" varStatus="reportIndex">
							<c:if test="${reportDetail.progress==13504}"><!-- 大定、未结佣 -->
								<c:if test="${reportInfo.report.roughAuditStatus eq '0'}"><c:set var="roughAuditStatusNm" value="待审核"></c:set></c:if>
								<c:if test="${reportInfo.report.roughAuditStatus eq '1'}"><c:set var="roughAuditStatusNm" value="审核通过"></c:set></c:if>
								<c:if test="${reportInfo.report.roughAuditStatus eq '2'}"><c:set var="roughAuditStatusNm" value="审核驳回"></c:set></c:if>
								<li class="step-item active"><c:choose><c:when test="${reportInfo.report.latestSubscribedFollowDate!=''}"><a><span data-date="${reportInfo.report.latestSubscribedFollowDate}   ${roughAuditStatusNm}">大定</span></a></c:when><c:otherwise><a><span>大定</span></a></c:otherwise></c:choose></li>
								<c:set var="flag4" value="1"></c:set>
							</c:if>
						</c:forEach>
						<c:if test="${flag4 == '0'}"><li class="">大定</li></c:if>

						<c:set var="flag5" value="0"></c:set>
						<c:forEach items="${reportInfo.reportDetails}" var="reportDetail" varStatus="reportIndex">
							<c:if test="${reportDetail.progress==13505}"><!-- 成销、未结佣 -->
								<li class="step-item active"><c:choose><c:when test="${reportInfo.report.latestBargainFollowDate!=''}"><a><span data-date="${reportInfo.report.latestBargainFollowDate}">成销</span></a></c:when><c:otherwise><a><span>成销</span></a></c:otherwise></c:choose></li>
								<c:set var="flag5" value="1"></c:set>
							</c:if>
						</c:forEach>
						<c:if test="${flag5 == '0'}"><li class="">成销</li></c:if>

						<c:choose>
							<c:when test="${reportInfo.report.brokerageStatus eq '22002' or reportInfo.report.brokerageStatus eq '22003'}">
								<li class="step-item active"><a><span  data-date="${reportInfo.report.brokerageStatusNm}">结佣</span></a></li>
							</c:when>
							<c:otherwise>
								<li class="">结佣</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div style="margin-top:50px;" class="table table-bordered">
					<table class="table table-striped table-hover">
						<tr>
							<th>进度</th>
							<th>确认状态</th>
							<th>业务节点发生时间</th>
							<th>操作人</th>
							<th>操作时间</th>
							<th>操作</th>
						</tr>
						<c:forEach var="c" items="${reportInfo.reportDetails}" varStatus="status">
							<c:if test="${c.progress ne 13503}">
								<tr>
									<c:if test="${c.progress eq 13504}">
										<input type = "hidden" id="modFlagControl" name = "modFlagControl" value = "${c.modFlagControl}"/>
									</c:if>
									<td>${c.progressNm}</td>
									<td>${c.confirmStatusNm}</td>
									<td>${c.bizOccurDate}</td>
									<td>${c.uptEmpName}</td>
									<td>${c.followDateDisPlay}</td>
									<td>
										<a href="javascript:void(0);" onclick="toOperDetail(${reportInfo.report.id},${c.id},'22001')">查看</a>
										<c:if test="${(c.progress eq 13504 or c.progress eq 13505)}">
											<c:set var="reportLastDetailId" value="${c.id}"></c:set>
											<c:if test="${(c.progress eq 13504 and reportInfo.report.confirmStatus ne 13602 and (reportInfo.report.customerFrom eq 17402 or reportInfo.report.customerFrom eq 17401) and reportInfo.report.roughAuditStatus eq '2') or (c.progress eq 13505 and reportInfo.report.brokerageStatus eq '22001' and dataSwitch eq '0')}">
												<a href="javascript:void(0);" onclick="toOperDetailUpdate(${reportInfo.report.id},${c.id})">修改</a>
											</c:if>
										</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
						<c:if test="${reportInfo.report.latestProgress eq 13505 and  reportInfo.report.confirmStatus eq 13601 and (reportInfo.report.brokerageStatus eq '22002' or reportInfo.report.brokerageStatus eq '22003')}">
							<tr>
								<td>结佣</td>
								<td>有效</td>
								<td>${reportInfo.report.brokerageYm}</td>
								<td>${reportInfo.report.brokerageUptEmpNm}</td>
								<td>${reportInfo.report.brokerageUptDt}</td>
								<td>
									<a href="javascript:void(0);" onclick="toOperDetail(${reportInfo.report.id},${reportLastDetailId},'22003')">详情</a>
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>


			<div class="page-content">
				<h4 class="border-bottom pdb10">
					<strong>日志</strong>
				</h4>
			</div>
			<table id="reportLogTableId" class="table table-striped table-hover table-bordered">
				<tr>
					<th style="width: 80px;">序号</th>
					<th>变更内容</th>
					<th style="width: 200px;">操作人(工号)</th>
					<th style="width: 250px;">操作时间</th>
				</tr>
				<c:forEach items="${LogInfo}" var="log" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td style="text-align:left">${log.changeContent}</td>
						<td>${log.createUserName}(${log.createUserCode})</td>
						<td>${log.createDate}</td>

					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</form>
<form id="achievementAdjustSubimtForm" style="height: auto;width: auto;display:none" action="${ctx}/report/editAchievementMode">
	<!-- 弹窗返回需要 -->
	<input type = "hidden"  name = "estateId" value = "${estateId}"/>
	<input type = "hidden"  name = "companyId" value = "${companyId}"/>
	<input type = "hidden"  name = "customerId" value = "${customerId}"/>
	<input type = "hidden"  name = "relateId" value = "${relateId}"/>
	<input type = "hidden"  name = "fromType" value = "${fromType}"/>
	<!-- 弹窗传递的参数 -->
	<input type = "hidden"  name = "reportId" value = "${reportInfo.report.reportId}"/>
	<input type = "hidden"  name = "customerNm" value = "${reportInfo.report.customerNm}"/>
	<input type = "hidden"  name = "customerTel" value = "${reportInfo.report.customerTel}"/>
	<input type = "hidden"  name = "contactId" value = "${reportInfo.report.contactId}"/>
	<input type = "hidden"  name = "contactNm" value = "${reportInfo.report.contactNm}"/>
	<input type = "hidden"  name = "centerGroupId" value = "${reportInfo.report.centerGroupId}"/>
	<input type = "hidden"  name = "centerGroupName" value = "${reportInfo.report.centerGroupName}"/>
</form>
<form id="customerAdjustSubimtForm" style="height: auto;width: auto;display:none" action="${ctx}/report/editCustomerInfoMode">
	<!-- 弹窗返回需要 -->
	<input type = "hidden"  name = "estateId" value = "${estateId}"/>
	<input type = "hidden"  name = "companyId" value = "${companyId}"/>
	<input type = "hidden"  name = "customerId" value = "${customerId}"/>
	<input type = "hidden"  name = "relateId" value = "${relateId}"/>
	<input type = "hidden"  name = "fromType" value = "${fromType}"/>
	<!-- 弹窗传递的参数 -->
	<input type = "hidden"  name = "reportId" value = "${reportInfo.report.reportId}"/>
	<input type = "hidden"  name = "customerNm" value = "${reportInfo.report.customerNm}"/>
	<input type = "hidden"  name = "customerTel" value = "${reportInfo.report.customerTel}"/>
	<input type = "hidden"  name = "customerNmTwo" value = "${reportInfo.report.customerNmTwo}"/>
	<input type = "hidden"  name = "customerTelTwo" value = "${reportInfo.report.customerTelTwo}"/>
</form>




</body>
<script type="text/javascript">
    var ReportDetail=function(){}
    ReportDetail.close = function() {
        ReportDetail.dialog.close();
    };
    function toOperDetail(reportId,reportDetailId,brokeragePage) {
        var url = BASE_PATH + "/report/toOperDetail";
        var params = {
            "reportId":reportId,
            "reportDetailId":reportDetailId
            ,"brokeragePage":brokeragePage
        };

        var dialogOptions = {
            width : 400,
            height : 100
        };
        Dialog.ajaxOpenDialog(url, params, "详情", function(dialog, resData) {
            ReportDetail.dialog = dialog;
        }, dialogOptions);
    };
    function toOperDetailUpdate(reportId,reportDetailId) {
        var url = BASE_PATH + "/report/toOperDetailUpdate";
        var params = {
            "reportId":reportId,
            "reportDetailId":reportDetailId
        };

        var dialogOptions = {

            width : 500,
            height : 100,
            ok : function() {
                // 确定
                var reback = ReportDetail.updateOperDetail(reportId,reportDetailId);
                return reback;
            },
            okVal : '确定',
            cancel : true,
            cancelVal : '返回'
        };
        Dialog.ajaxOpenDialog(url, params, "修改", function(dialog, resData) {
            ReportDetail.dialog = dialog;
        }, dialogOptions);
    };

    function handlerFileInfo(newProgressHidden){
        if(newProgressHidden=='13504'){
            //验证带单附件
            if($('#fileIdPhotoToSee> .item-photo-list').length && $('#fileIdPhotoToSee> .item-photo-list').length>0){
            }else{
				/* $("#errorMsg").find(".fc-warning").empty().html("请上传带看单!"); */
                alert("请上传带看单!");
                return false;
            }
            //验证大定单附件
            if($('#fileIdPhotoBox> .item-photo-list').length && $('#fileIdPhotoBox> .item-photo-list').length>0){
            }else{
				/* $("#errorMsg").find(".fc-warning").empty().html("请上传大定单!"); */
                alert("请上传大定单!");
                return false;
            }
            //验证甲方项目负责人名片附件
            if($('#fileIdPhotoCard> .item-photo-list').length && $('#fileIdPhotoCard> .item-photo-list').length>0){
            }else{
				/* $("#errorMsg").find(".fc-warning").empty().html("请上传甲方项目负责人名片!"); */
                alert("请上传甲方项目负责人名片!");
                return false;
            }
        }else{
            if($('#fileIdPhotoToDeal> .item-photo-list').length && $('#fileIdPhotoToDeal> .item-photo-list').length>0){
            }else{
				/* $("#errorMsg").find(".fc-warning").empty().html("请上传甲方项目负责人名片!"); */
                alert("请上传成销确认书/佣金结算资料!");
                return false;
            }
        }
        var bol = true;
        var fileRecordMainIds = "";
        $("input[name=fileRecordMainIdHidden]").each(function(){
            if($(this).val()==""){
                Dialog.alertError("图片上传出现异常，请删除重新上传。");
                bol = false;
                return false;
            }
            fileRecordMainIds += ","+$(this).val();
        })
        if(fileRecordMainIds!=""){
            fileRecordMainIds = fileRecordMainIds.substring(1);
        }
        $("#fileRecordMainIds").val(fileRecordMainIds);

        return bol;
    }

    ReportDetail.updateOperDetail = function(reportId,reportDetailId) {
        systemLoading("", true);
        var newProgressHidden = $("#newProgressHidden").val();
        if(!handlerFileInfo(newProgressHidden)){
            systemLoaded();
            return false;
        }
        var buildingNo = $("#buildingNo").val();
        var reportIdHi = $("#reportIdHi").val();
        if(newProgressHidden=='13504'){
            var flag = buildingNoRepeatCount(buildingNo,reportIdHi,3);
            if(!flag){
                systemLoaded();
//         	$("#errorMsg").find(".fc-warning").empty().html("该楼室号已报备，请勿重复录入！");
                $("#buildingNo").focus();
                return false;
            }
        }

        if (Validator.validForm("operDetailForm")) {

            var oldCustomerNm = $("#oldCustomerNm").val();
            var customerNm = $("#customerNm").val();
            var oldCustomerTel = $("#oldCustomerTel").val();
            var customerTel = $("#customerTel").val();
            var oldBuildingNo = $("#oldBuildingNo").val();
            var buildingNo = $("#buildingNo").val();
            var oldRoughArea = $("#oldRoughArea").val();
            var roughArea = $("#roughArea").val();
            var oldRoughAmount = $("#oldRoughAmount").val();
            var roughAmount = $("#roughAmount").val();
            var oldArea = $("#oldArea").val();
            var area = $("#area").val();
            var oldDealAmount = $("#oldDealAmount").val();
            var dealAmount = $("#dealAmount").val();
            var oldBizOperDate = $("#oldBizOperDate").val();
            var bizOperDate = $("#bizOperDate").val();
            var fileRecordMainIds = $("#fileRecordMainIds").val();
            var oldFileRecordMainIds = $("#oldFileRecordMainIds").val();
            var accountProjectNo = $("#accountProjectNo").val();

            var oldCustomerNmTwo = $("#oldCustomerNmTwo").val();
            var customerNmTwo = $("#customerNmTwo").val();
            var oldCustomerTelTwo = $("#oldCustomerTelTwo").val();
            var customerTelTwo = $("#customerTelTwo").val();
            var settleConfirmDateOld = $("#settleConfirmDateOld").val();
            var settleConfirmDate = $("#settleConfirmDate").val();
            if(oldCustomerNm == customerNm && oldCustomerTel == customerTel
                && oldBuildingNo == buildingNo && oldRoughArea == roughArea
                && oldRoughAmount == roughAmount && oldArea == area
                && oldDealAmount == dealAmount && oldBizOperDate == bizOperDate
                && fileRecordMainIds == oldFileRecordMainIds
                && oldCustomerNmTwo == customerNmTwo && oldCustomerTelTwo == customerTelTwo && settleConfirmDateOld == settleConfirmDate){
				/* $("#errorMsg").find(".fc-warning").empty().html("未做改动"); */
                systemLoaded();
                alert("未做改动!");
                //alertErrorInfo("未做改动!");
                return false;
            }
            if($("#customerNmTwo").val() !="") {
                if ($("#customerTelTwo").val()==undefined || $("#customerTelTwo").val()==null || $("#customerTelTwo").val()=='') {
                    $("#errorMsg").find(".fc-warning").empty().html("客户已填写，客户手机必须填写！");
                    systemLoaded();
                    $("#customerTelTwo").focus();
                    return false;
                }
            }
            if($("#customerTelTwo").val() !="") {
                if ($("#customerNmTwo").val()==undefined || $("#customerNmTwo").val()==null || $("#customerNmTwo").val()=='') {
                    $("#errorMsg").find(".fc-warning").empty().html("客户手机已填写，客户必须填写！");
                    systemLoaded();
                    $("#customerNmTwo").focus();
                    return false;
                }
            }
            if(newProgressHidden=='13505'){
                if ($("#accountProjectNo").val()==undefined || $("#accountProjectNo").val()==null || $("#accountProjectNo").val()=='') {
                    $("#errorMsg").find(".fc-warning").empty().html("核算主体必须选择！");
                    systemLoaded();
                    $("#accountProjectNo").focus();
                    return false;
                }
			}
            var yearMonthDate = $("#yearMonthDate").val();
            if(yearMonthDate != ""  && yearMonthDate != null && yearMonthDate !=undefined){
                if(!tab1(yearMonthDate,bizOperDate)){
					/* $("#errorMsg").find(".fc-warning").empty().html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大定日期小于关帐日期"); */
                    systemLoaded();
                    alert("选择的大定日期处在关账月份，请重新选择!");
                    return false;
                }
            }
            var url = BASE_PATH + "/report/operDetailUpdate/"+reportId+"/"+reportDetailId;
            var params = $("#operDetailForm").serialize();
            ajaxGet(url, params, function(data) {
                systemLoaded();
                if (data.returnCode != 200) {
					/*  $("#errorMsg").find(".fc-warning").empty().html(data.returnMsg); */
                    alert(data.returnMsg);
                    return false;
                }else {
                    ReportDetail.close();
                    location.reload(true);
                }
            }, function(data) {
				/*  $("#errorMsg").find(".fc-warning").empty().html(data.returnMsg); */
                systemLoaded();
                alert(data.returnMsg);
                return false;
            });
        }
        setTimeout(function() {
            systemLoaded();
        }, 3000);
        return false;
    };

    function tab1(date1,date2){
        var flag = true;
        var oDate1 = new Date(date1);
        var oDate2 = new Date(date2);
        if(oDate1.getTime() > oDate2.getTime()){
            //清空
            flag = false;
            return flag;
        }
        return flag;
    };
</script>
</html>
