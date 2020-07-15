<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>房友新房分销系统</title>

    <style>
        .lable-left {
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
            width: 100px;
            margin-left: 120px;
        }

        .lable-right {
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
        }

        .myForm .layui-form-label2 {
            margin-left: 150px;
            min-height: 36px;
            margin-right: 20px;
            width: 450px;
            float: left
        }

        .myForm .layui-input-block {
            margin-left: 150px;
            min-height: 36px;
            margin-right: 20px;
            width: 450px;
        }

        .layui-tab-item .layui-table thead > tr {
            color: #999999;
        }

        .progressItem span {
            color: #bdbdbd;
        }

        .progressItem .blockTitle {
            font-weight: bold;
            line-height: 32px;
            margin-left: 10px;
        }

        .progressItem.active span {
            color: #3b91ff;
        }

        .progressItem .image01 {
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/01.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float: left;
        }

        .progressItem.active .image01 {
            background-image: url(/meta/pmls/images/1.png);
        }

        .progressItem .image02 {
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/02.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float: left;
        }

        .progressItem.active .image02 {
            background-image: url(/meta/pmls/images/2.png);
        }

        .progressItem .image03 {
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/03.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float: left;
        }

        .progressItem.active .image03 {
            background-image: url(/meta/pmls/images/3.png);
        }

        .progressItem .image04 {
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/04.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float: left;
        }

        .progressItem.active .image04 {
            background-image: url(/meta/pmls/images/4.png);
        }

        .progressItem .image05 {
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/05.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float: left;
        }

        .progressItem.active .image05 {
            background-image: url(/meta/pmls/images/5.png);
        }

        #logCard .layui-table-cell {
            height: auto;
            overflow: visible;
            text-overflow: inherit;
            white-space: normal;
        }

        .layui-form-item .layui-input-inline {
            width: 180px;
        }

        .fontClass {
            color: #a1a1a1;
        }
    </style>


</head>
<body>
<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-row blockBody">
            <div class="layui-col-xs6">
                <div class="blockTitle">订单详情</div>
            </div>
            <div class="layui-col-xs6 blockBtn">

                <shiro:hasPermission name="/lnk:CUSTOMER_AJ">
                    <button class="layui-btn layui-btn-normal" data-type="openDialogEditCustomer"
                            onclick="openDialogEditCustomer()">客户信息调整
                    </button>
                </shiro:hasPermission>

                <!-- 		    	判断条件：1、有效 2、成销之前 3、没有大定审核通过  4、没有有退定、退房记录(退定、退房时间) -->
                <c:if test="${reportInfo.report.confirmStatus eq 13601 and reportInfo.report.latestProgress le 13505 and empty reportInfo.report.roughBackDate and empty reportInfo.report.dealBackDate and reportInfo.report.roughAuditStatus ne 1}">
                    <shiro:hasPermission name="/lnk:ACHIEVEMENT_AJ">
                        <button class="layui-btn layui-btn-normal" data-type="openDialogEditAchieve"
                                onclick="openDialogEditAchieve()">业绩调整
                        </button>
                    </shiro:hasPermission>
                </c:if>

                <c:if test="${(reportInfo.report.confirmStatus eq 13601 and reportInfo.report.latestProgress eq 13505) and (empty reportInfo.report.rebackFlag or reportInfo.report.rebackFlag eq 'false') and reportInfo.report.sjFyAmount>0}">
                    <shiro:hasPermission name="/lnk:BTN_REBACK">
                        <button class="layui-btn layui-btn-normal" data-type="openDialogUnlockBack"
                                onclick="openDialogUnlockBack(${reportInfo.report.id})">退房解锁
                        </button>
                    </shiro:hasPermission>
                </c:if>


                    <shiro:hasPermission name="/lnk:BTN_PREBACK">
                        <c:choose>
                            <c:when test="${reportInfo.report.roughAuditStatus eq 1 and reportInfo.report.preBack eq '1'}">
                                <button class="layui-btn layui-btn-normal" data-type="uptPreBack"
                                        onclick="uptPreBack(${reportInfo.report.id},'0')">取消预退
                                </button>
                            </c:when>
                            <c:when test="${reportInfo.report.roughAuditStatus eq 1}">
                                <button class="layui-btn layui-btn-normal" data-type="uptPreBack"
                                        onclick="uptPreBack(${reportInfo.report.id},'1')">预退
                                </button>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                    </shiro:hasPermission>
                <button type="button" class="layui-btn layui-btn-primary" onclick="goBack()">返回</button>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
            <legend>订单</legend>
        </fieldset>
        <input type="hidden" id="modFlagControl" name="modFlagControl" value=""/>
        <input type="hidden" id="estateId" name="estateId" value="${estateId}">
        <input type="hidden" id="companyId" name="companyId" value="${companyId}">
        <input type="hidden" id="customerId" name="customerId" value="${customerId}">
        <input type="hidden" id="relateId" name="relateId" value="${relateId}">

        <input type="hidden" id="customerNm" name="customerNm" value="${reportInfo.report.customerNm}">
        <input type="hidden" id="customerTel" name="customerTel" value="${reportInfo.report.customerTel}">
        <input type="hidden" id="customerNmTwo" name="customerNmTwo" value="${reportInfo.report.customerNmTwo}">
        <input type="hidden" id="customerTelTwo" name="customerTelTwo" value="${reportInfo.report.customerTelTwo}">
        <input type="hidden" name="contactId" id="contactId" value="${reportInfo.report.contactId}"/>
        <input type="hidden" name="contactNm" id="contactNm" value="${reportInfo.report.contactNm}"/>
        <input type="hidden" name="centerGroupId" id="centerGroupId" value="${reportInfo.report.centerGroupId}"/>
        <input type="hidden" name="centerGroupName" id="centerGroupName" value="${reportInfo.report.centerGroupName}"/>

        <input type="hidden" name="fyCenterId" id="fyCenterId" value="${reportInfo.report.fyCenterId}"/>
        <input type="hidden" name="fyCenterName" id="fyCenterName" value="${reportInfo.report.fyCenterName}"/>
        <input type="hidden" name="htedition" id="htedition" value="${reportInfo.report.htedition}"/>
        <input type="hidden" name="branchId" id="branchId" value="${reportInfo.report.branchId}"/>

        <input type="hidden" id="id" name="id" value="${reportInfo.report.id}">
        <input type="hidden" id="reportId" name="reportId" value="${reportInfo.report.reportId}">
        <input type="hidden" id="latestProgress" name="latestProgress" value="${reportInfo.report.latestProgress}">

        <input type="hidden" id="confirmStatus" name="confirmStatus" value="${reportInfo.report.confirmStatus}">
        <input type="hidden" id="customerFrom" name="customerFrom" value="${reportInfo.report.customerFrom}">
        <input type="hidden" id="roughAuditStatus" name="roughAuditStatus"
               value="${reportInfo.report.roughAuditStatus}">
        <input type="hidden" id="brokerageStatus" name="brokerageStatus" value="${reportInfo.report.brokerageStatus}">
        <input type="hidden" id="dataSwitch" name="dataSwitch" value="${dataSwitch}">

        <input type="hidden" id="brokerageYm" name="brokerageYm" value="${reportInfo.report.brokerageYm}">
        <input type="hidden" id="brokerageUptEmpNm" name="brokerageUptEmpNm"
               value="${reportInfo.report.brokerageUptEmpNm}">
        <input type="hidden" id="brokerageUptDt" name="brokerageUptDt" value="${reportInfo.report.brokerageUptDt}">
        <input type="hidden" id="reportLastDetailId" name="reportLastDetailId" value="">
        <input type="hidden" id="projectNo" name="projectNo" value="${reportInfo.report.projectNo}">
        <input type="hidden" id="companyNo" name="companyNo" value="${reportInfo.report.companyId}">
        <input type="hidden" id="auditDate" name="auditDate" value="${auditDate}">
        <input type="hidden" id="currDate" name="currDate" value="${currDate}">
        <input type="hidden" id="cooperFlag" name="cooperFlag" value="${cooperFlag}">
        <input type="hidden" id="auditDateFlag" name="auditDateFlag" value="${auditDateFlag}">
        <div class="layui-row">
            <label><b style="margin-left: 50px;">项目编号：${reportInfo.report.projectNo}</b> </label>&nbsp;&nbsp;&nbsp;
            <label><b>楼盘名称：${reportInfo.report.estateNm}</b> </label>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">订单编号：</div>
            <div class="layui-col-xs3 lable-right">${reportInfo.report.reportId}</div>
            <div class="layui-col-xs2 lable-left">经纪公司：</div>
            <div class="layui-col-xs4 lable-right">
                <c:choose>
                    <c:when test="${not empty  reportInfo.report.companyNm}">
                        ${reportInfo.report.companyNm}(${reportInfo.report.companyId})
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>

            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">门店名称：</div>
            <div class="layui-col-xs3 lable-right">${reportInfo.report.storeNm}</div>
            <div class="layui-col-xs2 lable-left">客户：</div>
            <div class="layui-col-xs4 lable-right">${reportInfo.report.customerNm}
                &nbsp; ${reportInfo.report.customerTel}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">客户：</div>
            <div class="layui-col-xs3 lable-right">${reportInfo.report.customerNmTwo}
                &nbsp; ${reportInfo.report.customerTelTwo}</div>
            <div class="layui-col-xs2 lable-left">报备经纪人：</div>
            <div class="layui-col-xs4 lable-right">${reportInfo.report.reportAgent}
                &nbsp; ${reportInfo.report.reportAgentTel}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">是否包销房源：</div>
            <div class="layui-col-xs3 lable-right">
                <c:if test="${reportInfo.report.isWrap eq 1}">是</c:if>
                <c:if test="${reportInfo.report.isWrap ne 1}">否</c:if>
            </div>
            <div class="layui-col-xs2 lable-left">业绩归属人：</div>
            <div class="layui-col-xs4 lable-right">${reportInfo.report.contactNm}</div>
        </div>

        <c:choose>
            <c:when test="${not empty  reportInfo.report.buildingNo}">
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">物业类型：</div>
                    <div class="layui-col-xs3 lable-right">${reportInfo.report.wyTypName}</div>
                    <div class="layui-col-xs2 lable-left">楼室号：</div>
                    <div class="layui-col-xs4 lable-right">${reportInfo.report.buildingNo}</div>
                </div>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>

        <div class="layui-row">
        <div class="layui-col-xs2 lable-left">业绩归属中心：</div>
        <div class="layui-col-xs3 lable-right">${reportInfo.report.centerGroupName}</div>
        <div class="layui-col-xs2 lable-left">订单进度：</div>
        <div class="layui-col-xs4 lable-right">
            <c:choose>
                <c:when test="${reportInfo.report.brokerageStatus eq '22002' or reportInfo.report.brokerageStatus eq '22003'}">
                    结佣
                </c:when>
                <c:otherwise>
                    ${reportInfo.report.latestProgressNm}
                </c:otherwise>
            </c:choose>
        </div>
    </div>
        <c:if test="${reportInfo.report.latestProgress eq '13505' && reportInfo.report.roughAuditStatus eq '1'}">
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">垫佣控制规则：</div>
                <div class="layui-col-xs3 lable-right">
                    <c:if test="${reportInfo.report.isGlobalControl eq 1}">
                        项目总控
                    </c:if>
                    <c:if test="${reportInfo.report.isGlobalControl ne 1}">
                        房源单控
                    </c:if>
                    <input type="hidden" id="isGlobalControl" value="${reportInfo.report.isGlobalControl}"/>
                </div>
                <div class="layui-col-xs2 lable-left">垫佣比例：</div>
                <div class="layui-col-xs4 lable-right">
                    <input type="hidden" id="dyRatio" value="${reportInfo.report.dyRatio}"/>
                    <c:if test="${reportInfo.report.dyRatio eq null}">
                        -
                    </c:if>
                    <c:if test="${reportInfo.report.dyRatio ne null}">
                        ${100.00 * reportInfo.report.dyRatio}%
                    </c:if>
                </div>
            </div>
        </c:if>

        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">报备来源：</div>
            <div class="layui-col-xs3 lable-right">
                <c:if test="${reportInfo.report.customerFrom eq 17401}">CRM</c:if>
                <c:if test="${reportInfo.report.customerFrom eq 17402}">CRM</c:if>
                <c:if test="${reportInfo.report.customerFrom eq 17403}">APP</c:if>
                <c:if test="${reportInfo.report.customerFrom eq 17404}">房友助手</c:if>
                <c:if test="${reportInfo.report.customerFrom eq 17405}">友房通</c:if>
            </div>
        </div>


        <div class="layui-row">
            <label><b style="margin-left: 50px;">收入&返佣</b> </label>
        </div>
        <div class="layui-row" style="margin-top: 10px;" >
            <table id="statistcsBrokerageTable" lay-size="sm" lay-filter="statistcsBrokerageTable"></table>
        </div>
    </div>
</div>

<div class="layui-card">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 38px;">
            <legend>进度</legend>
        </fieldset>
        <div class="layui-form-item" style="margin-left: 60px;">
            <div class="layui-inline">

                <div class="layui-input-inline progressItem
	    	 		<c:if test="${(reportInfo.report.latestProgress eq 13501 && reportInfo.report.confirmStatus eq 13601) 
	    	 		|| reportInfo.report.latestProgress > 13501}">
	    	 		 	active
	    	 		</c:if>
    	 		">
                    <div style="width:180px;height:80px;float:left;">
                        <div class="image01"></div>
                        <lable class="blockTitle">报备</lable>
                        <span class="">——————</span><br/><br/>
                        <lable class="progressDate fontClass">
                            <fmt:parseDate value="${reportInfo.report.reportDate}" var="bizOperDate"
                                           pattern="yyyy-MM-dd HH:mm:ss"/>
                            <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd"/>
                        </lable>
                        <lable class="fontClass">
                            <c:if test="${(reportInfo.report.latestProgress eq 13501 && reportInfo.report.confirmStatus eq 13601)
	    	 		|| reportInfo.report.latestProgress > 13501}">
                                <c:choose>
                                    <c:when test="${reportInfo.report.confirmStatus eq 13601}">
                                        有效
                                    </c:when>
                                    <c:when test="${reportInfo.report.confirmStatus eq 13602}">
                                        无效
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </lable>
                    </div>
                </div>


                <div class="layui-input-inline progressItem
					<c:if test="${(reportInfo.report.latestProgress eq 13502 && reportInfo.report.confirmStatus eq 13601)
					|| reportInfo.report.latestProgress > 13502}">
						active
					</c:if>
				">
                    <div style="width:180px;height:80px;float:left;margin-left: -20px;">
                        <div class="image02"></div>
                        <lable class="blockTitle">带看</lable>
                        <span>——————</span><br/><br/>
                        <lable class="progressDate fontClass">
                            <fmt:parseDate value="${reportInfo.report.seeDate}" var="bizOperDate"
                                           pattern="yyyy-MM-dd HH:mm:ss"/>
                            <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd"/>
                        </lable>
                        <lable class="fontClass">
                            <c:if test="${(reportInfo.report.latestProgress eq 13502 && reportInfo.report.confirmStatus eq 13601)
					|| reportInfo.report.latestProgress > 13502}">
                                <c:choose>
                                    <c:when test="${reportInfo.report.confirmStatus eq 13601}">
                                        有效
                                    </c:when>
                                    <c:when test="${reportInfo.report.confirmStatus eq 13602}">
                                        无效
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </lable>
                    </div>
                </div>

                <div class="layui-input-inline progressItem
					<c:if test="${(reportInfo.report.latestProgress eq 13504 && reportInfo.report.confirmStatus eq 13601)
					|| reportInfo.report.latestProgress > 13504}">
						active
					</c:if>
					">
                    <c:if test="${reportInfo.report.roughAuditStatus eq '0'}"><c:set var="roughAuditStatusNm"
                                                                                     value="待审核"></c:set></c:if>
                    <c:if test="${reportInfo.report.roughAuditStatus eq '1'}"><c:set var="roughAuditStatusNm"
                                                                                     value="审核通过"></c:set></c:if>
                    <c:if test="${reportInfo.report.roughAuditStatus eq '2'}"><c:set var="roughAuditStatusNm"
                                                                                     value="审核驳回"></c:set></c:if>
                    <div style="width:180px;height:80px;float:left;margin-left: -40px;">
                        <div class="image03"></div>
                        <lable class="blockTitle">大定</lable>
                        <span>——————</span><br/><br/>
                        <lable class="progressDate fontClass">
                            <fmt:parseDate value="${reportInfo.report.roughInputDate}" var="bizOperDate"
                                           pattern="yyyy-MM-dd HH:mm:ss"/>
                            <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd"/>
                        </lable>
                        <lable class="fontClass">
                            <c:if test="${(reportInfo.report.latestProgress eq 13504 && reportInfo.report.confirmStatus eq 13601)
					|| reportInfo.report.latestProgress > 13504}">
                                ${roughAuditStatusNm}
                            </c:if>
                        </lable>
                    </div>
                </div>

                <div class="layui-input-inline progressItem
					<c:if test="${reportInfo.report.latestProgress eq 13505 
						&& reportInfo.report.confirmStatus eq 13601}">
						active
					</c:if>
				">
                    <div style="width:180px;height:80px;float:left;margin-left: -60px;">
                        <div class="image04"></div>
                        <lable class="blockTitle">成销</lable>
                        <span>——————</span><br/><br/>
                        <lable class="progressDate fontClass">
                            <fmt:parseDate value="${reportInfo.report.dealDate}" var="bizOperDate"
                                           pattern="yyyy-MM-dd HH:mm:ss"/>
                            <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd"/>
                        </lable>
                        <lable class="fontClass">
                            <c:if test="${reportInfo.report.latestProgress eq 13505
						&& reportInfo.report.confirmStatus eq 13601}">
                                <c:choose>
                                    <c:when test="${reportInfo.report.confirmStatus eq 13601}">
                                        有效
                                    </c:when>
                                    <c:when test="${reportInfo.report.confirmStatus eq 13602}">
                                        无效
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </lable>
                    </div>
                </div>

                <div class="layui-input-inline progressItem
					<c:if test="${reportInfo.report.latestProgress eq 13505 
							&& reportInfo.report.confirmStatus eq 13601
							&& (reportInfo.report.brokerageStatus eq '22002' or reportInfo.report.brokerageStatus eq '22003')}">
							active
					</c:if>
				">
                    <div style="width:180px;height:80px;float:left;margin-left: -80px;">
                        <div class="image05"></div>
                        <lable class="blockTitle ">结佣</lable>
                        <br/><br/>
                        <!-- 				    	<span >————————</span> -->
                        <lable class="fontClass">
                            <c:if test="${reportInfo.report.latestProgress eq 13505
							&& reportInfo.report.confirmStatus eq 13601 && (reportInfo.report.brokerageStatus eq '22002' or reportInfo.report.brokerageStatus eq '22003')}">
                                ${reportInfo.report.brokerageStatusNm}
                            </c:if>
                        </lable>
                    </div>
                </div>
            </div>
        </div>
        <table id="reportProgressTable" lay-size="sm" lay-filter="reportProgress"></table>
    </div>
</div>

<div class="layui-card">
    <div class="layui-card-body" id="brokerage">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 38px;">
            <legend>佣金明细</legend>
        </fieldset>
        <div class="layui-form">
            <div class="layui-tab layui-tab-brief" lay-filter="brokerageTab">
                <ul class="layui-tab-title">
                    <li lay-id="yjsrTab" class="layui-this">应计收入</li>
                    <li lay-id="yssrTab">应收收入</li>
                    <li lay-id="yjssTab">应计实收</li>
                    <li lay-id="yjfyTab">应计返佣</li>
                    <li lay-id="sjfyTab">实际返佣</li>
                    <li lay-id="yjdyTab">应计垫佣</li>
                    <li lay-id="sjdyTab">实际垫佣</li>
                </ul>
                <div class="layui-tab-content" style="padding-top: 10px">
                    <div id="yjsrTabItem" class="layui-tab-item layui-show">
                        <table id="yjsrTable" lay-size="sm" lay-filter="yjsrTable"></table>
                    </div>
                    <div id="yssrTabItem" class="layui-tab-item">
                        <table id="yssrTable" lay-size="sm" lay-filter="yssrTable"></table>
                    </div>
                    <div id="yjssTabItem" class="layui-tab-item">
                        <table id="yjssTable" lay-size="sm" lay-filter="yjssTable"></table>
                    </div>
                    <div id="yjfyTabItem" class="layui-tab-item">
                        <table id="yjfyTable" lay-size="sm" lay-filter="yjfyTable"></table>
                    </div>
                    <div id="sjfyTabItem" class="layui-tab-item">
                        <table id="sjfyTable" lay-size="sm" lay-filter="sjfyTable"></table>
                    </div>
                    <div id="yjdyTabItem" class="layui-tab-item">
                        <table id="yjdyTable" lay-size="sm" lay-filter="yjdyTable"></table>
                    </div>
                    <div id="sjdyTabItem" class="layui-tab-item">
                        <table id="sjdyTable" lay-size="sm" lay-filter="sjdyTable"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="layui-card">
    <div class="layui-card-body" id="logCard">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 38px;">
            <legend>日志</legend>
        </fieldset>
        <table id="reportLogTable" lay-size="sm" lay-filter="reportLog"></table>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/reportDetail.js?v=${vs}"></script>

<script type="text/html" id="operateTpl">
    <a class="layui-btn layui-btn-xs" onclick="toOperDetail(${reportInfo.report.id},${c.id},'22001')">查看</a>
</script>
<script type="application/javascript">
    var reportDetails = '${reportInfo.reportDetails}';
    var logInfo = '${LogInfo}';
    var brokerageInfo = '${brokerageInfo}';

</script>
</body>
</html>
