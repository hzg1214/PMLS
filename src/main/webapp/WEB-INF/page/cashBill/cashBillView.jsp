<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/page/common/taglib.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/cashbill/cashBillView.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/cashbill/frmAgreementList.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/cashbill/receiveBankList.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
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
<div class="container theme-hipage ng-scope" role="main">

    <div class="crumbs">
        <ul style="margin-right:150px;">
            <li><a href="#" class="a_hover">联动管理</a></li>
            <li><a href="#" class="a_hover">>案场管理</a></li>
            <li><a href="#" class="a_hover">>项目</a></li>
            <li><a href="#" class="a_hover">>批量请款</a></li>
        </ul>
    </div>

    <div class="page-content">
        <h4 class="border-bottom pdb10" style="overflow:hidden;">
            <strong style="position:relative;top:10px;">批量请款</strong>
            <span id="errMsg" style="color: red;padding-left: 20px;position:relative;top:10px;"></span>
            <a type="button" class="btn btn-primary" style="float: right;margin-top: 3px;"
               href="${ctx}/sceneEstate/qSceneRecognition/${project.estateId}?searchParam=1">返回</a>
        </h4>
    </div>
    <div class="row">
        <div class="page-content">
            <form id="cashBillForm">
                <input type="hidden" id="submitStatus" name="submitStatus" value="0">
                <input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">
                <input type="hidden" name="id" value="${project.id}">
                <input type="hidden" id="estateCityNo" name="estateCityNo" value="${estateCityNo}">
                <input type="hidden" id="cityNo" name="cityNo" value="${estateCityNo}">
                <input type="hidden" id="estateId" name="estateId" value="${project.estateId}">
                <input type="hidden" id="estateNm" name="estateNm" value="${project.estateNm}">
                <input type="hidden" id="projectNo" name="projectNo" value="${project.projectNo}">
                <input type="hidden" id="isSpecialProject" name="isSpecialProject" value="${project.isSpecialProject}">
                <input type="hidden" id="amountNoTax" name="amountNoTax" value="${project.amountNoTax}">
                <input type="hidden" id="amountTax" name="amountTax" value="${project.amountTax}">
                <input type="hidden" id="amountTotal" name="amountTotal" value="${project.amountTotal}">
                <input type="hidden" id="listSize" name="listSize" value="${project.companyList.size()}">
                <span style="padding-left: 20px;">
                    <strong>项目编号：${project.projectNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;楼盘名称：${project.estateNm}</strong></span>


                <table style="margin-top:10px" class="table-sammary">
                    <col style="width:70px;">
                    <col style="width:200px;">
                    <col style="width:123px;">
                    <col style="width:200px;">
                    <tr>
                        <td class="talabel" style="padding-left: 30px;"><i>*</i>入账日期：</td>
                        <td><input type="text" name="recordTime" value="${sdk:ymd2(project.recordTime)}" notnull="true"
                                   onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'${yearMonth}'})"
                                   readonly="readonly" class="ipttext Wdate form-control w160 calendar-icon">
                            <span class="fc-warning"></span></td>
                        <td class="talabel"><i>*</i>预计付款日期：</td>
                        <td><input type="text" name="predictPayTime" value="${sdk:ymd2(project.predictPayTime)}"
                                   notnull="true"
                                   onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd' ,minDate:'%y-%M-%d'})"
                                   readonly="readonly" class="ipttext Wdate form-control w160 calendar-icon">
                            <span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel" style="padding-left: 30px;"><i>*</i>付款方式：</td>
                        <td style="width: 157px;">
                            <c:if test="${project.payType == null}">
                                <t:dictSelect field="payType" id="payType" xmlkey="LOOKUP_Dic"
                                              dbparam="P1:223" classvalue="30" defaultVal="22303"
                                              txWidth="157px"></t:dictSelect>
                            </c:if>
                            <c:if test="${project.payType != null}">
                                <t:dictSelect field="payType" id="payType" xmlkey="LOOKUP_Dic"
                                              dbparam="P1:223" classvalue="30" defaultVal="${project.payType}"
                                              txWidth="157px"></t:dictSelect>
                            </c:if>
                        </td>
                    </tr>
                </table>
                <c:forEach items="${project.companyList}" var="company" varStatus="comIndex">
                    <div id="divcompany_${comIndex.index}" tag='companyDiv'
                         style="margin-top: 10px; padding-top: 10px; border: 1px dashed #0e78c9;">
                        <table id="companyTb1_${comIndex.index}" tag="companyTbl" class="table-sammary">
                            <input type="hidden" name="companyList[${comIndex.index}].id" value="${company.id}">
                            <input type="hidden" name="companyList[${comIndex.index}].companyId"
                                   value="${company.companyId}">
                            <input type="hidden" name="companyList[${comIndex.index}].companyName"
                                   value="${company.companyName}">
                            <col style="width:70px;">
                            <col style="width:200px;">
                            <col style="width:123px;">
                            <col style="width:auto;">
                            <tr>
                                <td class="talabel" style="padding-left: 50px;"><b>经纪公司：</b></td>
                                <td><b>${company.companyName}&nbsp;(${company.companyNo})</b></td>
                                <td class="talabel"><i>*</i>核算主体：</td>
                                <td style="width: 200px;">
                                    <select class="form-control" id="accountProjectNo${comIndex.index}"
                                            tag="accountProjectNo"
                                            onchange="changeAccountProject(this)"
                                            name="companyList[${comIndex.index}].accountProjectNo" notnull="true"
                                            style="width:250px;"></select>
                                    <input type="hidden" tag="accountProject"
                                           name="companyList[${comIndex.index}].accountProject"
                                           id="hidAccountProject${comIndex.index}"
                                           value="${company.accountProject}"/>

                                    <input type="hidden" tag="oaProjectNo"
                                           name="companyList[${comIndex.index}].oaProjectNo"
                                           id="hidOaProjectNo${comIndex.index}"
                                           value="${company.oaProjectNo}"/>
                                    <input type="hidden" tag="oaProjectName"
                                           name="companyList[${comIndex.index}].oaProjectName"
                                           id="hidOaProjectName${comIndex.index}"
                                           value="${company.oaProjectName}"/>

                                    <input type="hidden" id="oldAccountProject${comIndex.index}"
                                           value="${company.accountProjectNo}"/>
                                    <span class="fc-warning"></span>
                                </td>
                            </tr>
                            <tr id="frmAgreement${comIndex.index}">
                                <td class="talabel" style="padding-left: 40px;"><i>*</i>框架协议：</td>
                                <td>
                                    <input type="text" notnull="true" class="form-control w200"
                                           name="companyList[${comIndex.index}].frameOaNo"
                                           tag="frameOaNo"
                                           placeholder="" value="${company.frameOaNo}"
                                           readonly="readonly" style="background-color: #F9F9F9;width: 250px;">
                                    <button type="button" class="btn btn-primary"
                                            onclick="javascript:getFrmAgreement(${comIndex.index},this);"
                                            style="vertical-align: top;">选择
                                    </button>
                                    <input type="hidden" tag="frameOaName"
                                           name="companyList[${comIndex.index}].frameOaName"
                                           value="${company.frameOaName}">
                                    <span class="fc-warning"></span>
                                </td>
                                <td class="talabel"><i>*</i>供应商：</td>
                                <td>
                                    <input type="hidden" tag="vendorId" name="companyList[${comIndex.index}].vendorId"
                                           value="${company.vendorId}">
                                    <input type="hidden" tag="vendorCode"
                                           name="companyList[${comIndex.index}].vendorCode"
                                           value="${company.vendorCode}">
                                    <input type="text" tag="vendorName" name="companyList[${comIndex.index}].vendorName"
                                           value="${company.vendorName}"
                                           class="form-control w200" placeholder="" notnull="true" readonly="readonly"
                                           style="background-color: #F9F9F9;width: 250px;">
                                    <span class="fc-warning"></span>
                                </td>
                            </tr>
                            <tr id="receiveBank${comIndex.index}">
                                <td class="talabel" style="padding-left: 40px;"><i>*</i>收款银行：</td>
                                <td>
                                    <input type="text" tag="receiveBankName"
                                           name="companyList[${comIndex.index}].receiveBankName"
                                           value="${company.receiveBankName}"
                                           notnull="true" placeholder="" readonly="readonly" class="form-control w200"
                                           style="background-color: #F9F9F9;width: 250px;">
                                    <button type="button" class="btn btn-primary"
                                            onclick="javascript:getReceiveBank(${comIndex.index},this);"
                                            style="vertical-align: top;">选择
                                    </button>
                                    <span class="fc-warning"></span>
                                </td>
                                <td class="talabel"><i>*</i>银行账号：</td>
                                <td>
                                    <input type="text" tag="receiveBankAccountCardCode"
                                           name="companyList[${comIndex.index}].receiveBankAccountCardCode"
                                           value="${company.receiveBankAccountCardCode}"
                                           class="form-control w200" notnull="true" placeholder="" readonly="readonly"
                                           style="background-color: #F9F9F9;width: 250px;">
                                    <span class="fc-warning"></span>
                                </td>
                            </tr>
                            <tr id="receiveBankData${comIndex.index}">
                                <td class="talabel" style="padding-left: 40px;"><i>*</i>收款户名：</td>
                                <td>
                                    <input type="text"
                                           class="form-control w200" notnull="true" placeholder="" readonly="readonly"
                                           tag="receiveBankAccountName"
                                           name="companyList[${comIndex.index}].receiveBankAccountName"
                                           value="${company.receiveBankAccountName}"
                                           lass="form-control w200" notnull="true"
                                           style="background-color: #F9F9F9;width: 250px;">
                                    <span class="fc-warning"></span>
                                </td>
                                <td class="talabel"><i>*</i>收款省市：</td>
                                <td>
                                    <input type="text" tag="receiveBankProvinceCityName"
                                           class="form-control w200" notnull="true" placeholder="" readonly="readonly"
                                           value="${company.receiveBankProvinceName} ${company.receiveBankProvinceName}"
                                           style="background-color: #F9F9F9;width: 250px;">
                                    <input type="hidden" tag="receiveBankProvinceName"
                                           name="companyList[${comIndex.index}].receiveBankProvinceName"
                                           value="${company.receiveBankProvinceName}">
                                    <input type="hidden" tag="receiveBankCityName"
                                           name="companyList[${comIndex.index}].receiveBankCityName"
                                           value="${company.receiveBankCityName}">
                                    <input type="hidden" tag="receiveBankSerialNo"
                                           name="companyList[${comIndex.index}].receiveBankSerialNo"
                                           value="${company.receiveBankSerialNo}">
                                    <span class="fc-warning"></span>
                                </td>
                            </tr>
                            <tr id="remarks${comIndex.index}">
                                <td class="talabel" style="padding-left: 40px;">备注：</td>
                                <td>
                                    <textarea maxlength="200" class="remarksClass" placeholder="200字以内" style="width:888px;height: 100px;" tag="remarks" name="companyList[${comIndex.index}].remarks" value="" cols="30" rows="10">${company.remarks}</textarea>
                                </td>
                            </tr>
                        </table>
                        <div style="padding-left: 60px; padding-top: 20px; padding-bottom: 15px">
                            <table border="0">
                                <col style="width:300px;">
                                <col style="width:750px;">
                                <tr>
                                    <td valign="top">
                                        <div id="companyDiv_fix_${comIndex.index}"
                                             style="overflow: hidden; width:300px">
                                            <table id="companyTb2_fix_${comIndex.index}" border="1"
                                                   style=" border:#ddd 1px solid;font-size: 12px;"
                                                   width="300px">
                                                <col style="width:50px;">
                                                <col style="width:160px;">
                                                <col style="width:90px;">
                                                <tr style="height:25px;">
                                                    <td rowspan="2" align="center">序号</td>
                                                    <td rowspan="2" align="center">报备编号</td>
                                                    <td rowspan="2" align="center">楼室号</td>
                                                </tr>
                                                <tr style="height:25px;"></tr>
                                                <c:forEach items="${company.reportList}" var="report" varStatus="index">
                                                    <tr name="detail" detail="${report.id}" style="height:28px;">
                                                        <td style="width:50px;" align="center">${index.index+1}</td>
                                                        <td style="width:160px;" align="center">${report.reportNo}</td>
                                                        <td style="width:90px;line-height: 25px;max-width:90px; " align="center" title= '${report.buildingNo}' class="text-overflow"><c:if
                                                                test="${report.buildingNo eq null}">-</c:if><c:if
                                                                test="${report.buildingNo ne null}">${report.buildingNo}</c:if></td>
                                                    </tr>
                                                </c:forEach>

                                                <tr name="total" id="total${comIndex.index}" align="center"
                                                    style="height:25px;">
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>

                                            </table>
                                        </div>
                                    </td>
                                    <td valign="top" style="border-right:#ddd 1px solid">
                                        <div id="companyDiv_${comIndex.index}" style="overflow: auto; width:750px;  ">
                                            <table id="companyTb2_${comIndex.index}" class="companyTb2" border="1"
                                                   style=" border:#ddd 1px solid;font-size: 12px;"
                                                   width="1890px" align="center">

                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px;">
                                                <col style="width:100px">
                                                <col style="width:200px">
                                                <col style="width:200px">
                                                <col style="width:50px;">
                                                <tr style="height:25px;">
                                                    <td rowspan="2" align="center">客户姓名</td>
                                                    <td rowspan="2" align="center">销售面积</td>
                                                    <td rowspan="2" align="center">大定总价</td>
                                                    <td rowspan="2" align="center">成销总价</td>
                                                    <td colspan="6" align="center">累计税前</td>
                                                    <td rowspan="2" align="center">含税请款金额<font color="red">*</font></td>
                                                    <td rowspan="2" align="center">税额<font color="red">*</font></td>
                                                    <td rowspan="2" align="center">请款类别<font color="red">*</font></td>
                                                    <td rowspan="2" align="center">考核主体<i>*</i></td>
                                                    <td rowspan="2" align="center">说明</td>
                                                    <td rowspan="2" align="center">操作</td>
                                                </tr>
                                                <tr style="height:25px;">
                                                    <td align="center">应计收入</td>
                                                    <td align="center">应计返佣</td>
                                                    <td align="center">应计垫佣</td>
                                                    <td align="center">实收收入</td>
                                                    <td align="center">实际返佣</td>
                                                    <td align="center">实际垫佣</td>
                                                </tr>
                                                <c:forEach items="${company.reportList}" var="report" varStatus="index">
                                                    <tr name="detail" style="height:28px;">


                                                        <input type="hidden"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].reportId"
                                                               value="${report.reportId}">
                                                        <input type="hidden"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].reportDetailId"
                                                               value="${report.reportDetailId}">
                                                        <input type="hidden" tag="reportNo"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].reportNo"
                                                               value="${report.reportNo}">
                                                        <input type="hidden"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].id"
                                                               value="${report.id}">
                                                        <input type="hidden"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].buildingNo"
                                                               value="${report.buildingNo}">
                                                        <input type="hidden"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].customerNm"
                                                               value="${report.customerNm}">
                                                        <input type="hidden" tag="area"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].area"
                                                               value="${report.area}">
                                                        <input type="hidden" tag="roughAmount"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].roughAmount"
                                                               value="${report.roughAmount}">
                                                        <input type="hidden" tag="dealAmount"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].dealAmount"
                                                               value="${report.dealAmount}">
                                                        <input type="hidden" tag="sqYjsrAmount"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].sqYjsrAmount"
                                                               value="${report.sqYjsrAmount}">
                                                        <input type="hidden" tag="sqYjfyAmount"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].sqYjfyAmount"
                                                               value="${report.sqYjfyAmount}">
                                                        <input type="hidden" tag="sqYjdyAmount"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].sqYjdyAmount"
                                                               value="${report.sqYjdyAmount}">
                                                        <input type="hidden" tag="sqSjsrAmount"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].sqSjsrAmount"
                                                               value="${report.sqSjsrAmount}">
                                                        <input type="hidden" tag="sqSjfyAmount"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].sqSjfyAmount"
                                                               value="${report.sqSjfyAmount}">
                                                        <input type="hidden" tag="sqSjdyAmount"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].sqSjdyAmount"
                                                               value="${report.sqSjdyAmount}">
                                                        <input type="hidden" tag="requestType"
                                                               name="companyList[${comIndex.index}].reportList[${index.index}].requestType"
                                                               value="${report.requestType}">

                                                        <input type ="hidden" tag="progress" value="${report.progress}">
                                                        <input type ="hidden" tag="confirmStatus" value="${report.confirmStatus}">

                                                        <td align="center">${report.customerNm}</td>
                                                        <td align="center" tag="area"><c:if
                                                                test="${report.area eq null}">-</c:if><c:if
                                                                test="${report.area ne null}"><fmt:formatNumber
                                                                value="${report.area}"
                                                                pattern="#,##0.00"
                                                                maxFractionDigits="2"/></c:if></td>
                                                        <td align="center" tag="roughAmount"><c:if
                                                                test="${report.roughAmount eq null}">-</c:if><c:if
                                                                test="${report.roughAmount ne null}"><fmt:formatNumber
                                                                value="${report.roughAmount}" pattern="#,##0.00"
                                                                maxFractionDigits="2"/></c:if></td>
                                                        <td align="center" tag="dealAmount"><c:if
                                                                test="${report.dealAmount eq null}">-</c:if><c:if
                                                                test="${report.dealAmount ne null}"><fmt:formatNumber
                                                                value="${report.dealAmount}" pattern="#,##0.00"
                                                                maxFractionDigits="2"/></c:if></td>

                                                        <td align="center" tag="sqYjsrAmount"><c:if
                                                                test="${report.sqYjsrAmount eq null}">-</c:if><c:if
                                                                test="${report.sqYjsrAmount ne null}"><fmt:formatNumber
                                                                value="${report.sqYjsrAmount}" pattern="#,##0.00"
                                                                maxFractionDigits="2"/></c:if></td>
                                                        <td align="center" tag="sqYjfyAmount"><c:if
                                                                test="${report.sqYjfyAmount eq null}">-</c:if><c:if
                                                                test="${report.sqYjfyAmount ne null}"><fmt:formatNumber
                                                                value="${report.sqYjfyAmount}" pattern="#,##0.00"
                                                                maxFractionDigits="2"/></c:if></td>
                                                        <td align="center" tag="sqYjdyAmount"><c:if
                                                                test="${report.sqYjdyAmount eq null}">-</c:if><c:if
                                                                test="${report.sqYjdyAmount ne null}"><fmt:formatNumber
                                                                value="${report.sqYjdyAmount}" pattern="#,##0.00"
                                                                maxFractionDigits="2"/></c:if></td>
                                                        <td align="center" tag="sqSjsrAmount"><c:if
                                                                test="${report.sqSjsrAmount eq null}">-</c:if><c:if
                                                                test="${report.sqSjsrAmount ne null}"><fmt:formatNumber
                                                                value="${report.sqSjsrAmount}" pattern="#,##0.00"
                                                                maxFractionDigits="2"/></c:if></td>
                                                        <td align="center" tag="sqSjfyAmount"><c:if
                                                                test="${report.sqSjfyAmount eq null}">-</c:if><c:if
                                                                test="${report.sqSjfyAmount ne null}"><fmt:formatNumber
                                                                value="${report.sqSjfyAmount}" pattern="#,##0.00"
                                                                maxFractionDigits="2"/></c:if></td>
                                                        <td align="center" tag="sqSjdyAmount"><c:if
                                                                test="${report.sqSjdyAmount eq null}">-</c:if><c:if
                                                                test="${report.sqSjdyAmount ne null}"><fmt:formatNumber
                                                                value="${report.sqSjdyAmount}" pattern="#,##0.00"
                                                                maxFractionDigits="2"/></c:if></td>

                                                        <td align="center">
                                                            <input name="companyList[${comIndex.index}].reportList[${index.index}].requestAmount"
                                                                   type="text" tag="requestAmount"
                                                                   onkeyup="requestAmountEval(this)"
                                                                   style="width:90%;text-align:right"
                                                                   oninput="this.value=this.value.replace(/[^\-\d.]/g,'')"
                                                                   onchange="formatter(this)"
                                                                   notnull="true"
                                                                   value="${report.requestAmount}"/>
                                                        </td>

                                                        <td align="center">
                                                            <input name="companyList[${comIndex.index}].reportList[${index.index}].taxAmount"
                                                                   type="text" tag="taxAmount" align="right"
                                                                   onkeyup="taxAmountEval(this)"
                                                                   style="width:90%;text-align:right"
                                                                   oninput="this.value=this.value.replace(/[^\-\d.]/g,'')"
                                                                   onchange="formatter(this)"
                                                                   notnull="true"
                                                                   value="${report.taxAmount}"/>

                                                        </td>

                                                        <td align="center">
                                            <span tag="requestType">
                                                <c:if test="${report.requestType eq null ||  report.requestType==0}">-</c:if>
                                                <c:if test="${report.requestType ne null && report.requestType==1}">返佣</c:if>
                                                <c:if test="${report.requestType ne null && report.requestType==2}">垫佣</c:if>
                                            </span>
                                                        </td>

                                                        <td align="center">
                                                            <select tag="selCheckBodyId"  notnull="true" style="width:90%"onchange="changeCheckBody(this)">
                                                                <option value="">请选择</option>
                                                                <c:forEach items="${company.checkBodyList}" var="checkBody" varStatus="chkIndex">
                                                                    <option value="${checkBody.checkBodyId}"
                                                                            data-checkBodyId = "${checkBody.checkBodyId}"
                                                                            data-checkBodyName = "${checkBody.checkBodyName}"
                                                                            <c:if test="${report.checkBodyId eq checkBody.checkBodyId}">selected="selected"</c:if>
                                                                    >
                                                                            ${checkBody.checkBodyName}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                            <input type="hidden" tag="checkBodyName"
                                                                    name="companyList[${comIndex.index}].reportList[${index.index}].checkBodyName"
                                                                    value="${report.checkBodyName}"/>
                                                            <input type="hidden" tag="checkBodyId"
                                                                    name="companyList[${comIndex.index}].reportList[${index.index}].checkBodyId"
                                                                    value="${report.checkBodyId}"/>
                                                        </td>
                                                        <td align="center">
                                                            <input name="companyList[${comIndex.index}].reportList[${index.index}].memo"
                                                                   style="width:90%"
                                                                   id="memo" maxlength="100" type="text"
                                                                   value="${report.memo}"/>
                                                        </td>

                                                        <td align="center"><a href="javascript:void(0);"
                                                                              onclick="removeFromCashBill('${report.id}','${company.id}','${project.id}',${comIndex.index},this)">删除</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                <tr name="total" id="total${comIndex.index}" align="center"
                                                    style="height:25px;">
                                                    <td colspan="1" align="center"><b>合计</b>

                                                        <input type="hidden" tag="areaTotal"
                                                               name="companyList[${comIndex.index}].areaTotal">
                                                        <input type="hidden" tag="roughAmountTotal"
                                                               name="companyList[${comIndex.index}].roughAmountTotal">
                                                        <input type="hidden" tag="dealAmountTotal"
                                                               name="companyList[${comIndex.index}].dealAmountTotal">
                                                        <input type="hidden" tag="sqYjsrAmountTotal"
                                                               name="companyList[${comIndex.index}].sqYjsrAmountTotal">
                                                        <input type="hidden" tag="sqYjfyAmountTotal"
                                                               name="companyList[${comIndex.index}].sqYjfyAmountTotal">
                                                        <input type="hidden" tag="sqYjdyAmountTotal"
                                                               name="companyList[${comIndex.index}].sqYjdyAmountTotal">
                                                        <input type="hidden" tag="sqSjsrAmountTotal"
                                                               name="companyList[${comIndex.index}].sqSjsrAmountTotal">
                                                        <input type="hidden" tag="sqSjfyAmountTotal"
                                                               name="companyList[${comIndex.index}].sqSjfyAmountTotal">
                                                        <input type="hidden" tag="sqSjdyAmountTotal"
                                                               name="companyList[${comIndex.index}].sqSjdyAmountTotal">
                                                        <input type="hidden" tag="requestAmountTotal"
                                                               name="companyList[${comIndex.index}].requestAmountTotal">
                                                        <input type="hidden" tag="taxAmountTotal"
                                                               name="companyList[${comIndex.index}].taxAmountTotal">

                                                    </td>
                                                    <td><b><span tag="lblAreaTotal"></span></b></td>
                                                    <td><b><span tag="lblAoughAmountTotal"></span></b></td>
                                                    <td><b><span tag="lblDealAmountTotal"></span></b></td>
                                                    <td><b><span tag="lblSqYjsrAmountTotal"></span></b></td>
                                                    <td><b><span tag="lblSqYjfyAmountTotal"></span></b></td>
                                                    <td><b><span tag="lblSqYjdyAmountTotal"></span></b></td>
                                                    <td><b><span tag="lblSqSjsrAmountTotal"></span></b></td>
                                                    <td><b><span tag="lblSqSjfyAmountTotal"></span></b></td>
                                                    <td><b><span tag="lblSqSjdyAmountTotal"></span></b></td>
                                                    <td style="text-align:right; padding: 2px"><b><span
                                                            tag="lblRequestAmountTotal"></span></b>
                                                    </td>
                                                    <td style="text-align:right; padding: 2px"><b><span
                                                            tag="lbltaxAmountTotal"></span></b>
                                                    </td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </c:forEach>


                <div class="page-content" style="padding-left: 10px; padding-top: 20px">
                    <h4 style="font-size:16px;line-height: 0px;">
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
                                    <div class="pd10">
                                        <i>*</i>成销确认书/佣金结算资料
                                        <div style="margin-top: 10px" class="thumb-xs-box" id="fileIdPhotoToCashBill">
                                            <c:if test="${not empty project.fileList}">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${project.fileList}" var="list" varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank" title="${list.fileName}">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="成销确认书/佣金结算资料"
                                                                             data-original="${list.url50}"
                                                                             src="${list.fileAbbrUrl}"
                                                                             class="empPhoto"/>
																	</span>
																</span>
															</span>
                                                            <span class="thumb-bottom-roup">
																<i class="icon down-icon"></i><i
                                                                    class="icon remove-icon btn-remove-photo"></i>
															</span>
                                                        </a>
                                                        <input type="hidden" name="limitSize" value="10">
                                                        <input type="file" id="file${fileSize}"
                                                               class="btn-flie file-control hide" data-limit="10"
                                                               multiple="multiple"/>
                                                        <input type="hidden" name="fileRecordMainIdHidden"
                                                               value="${list.fileRecordMainId}"/>
                                                        <input type="hidden" name="fileTypeId" value="1032"/>
                                                        <input type="hidden" name="fileSourceId" value="9"/>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                            <div class="thumb-xs-list item-photo-add"
                                                 <c:if test="${fileSize>=10  }">style="display: none;"</c:if>>
                                                <input type="hidden" name="limitSize" value="10">
                                                <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                                    <input type="file" class="btn-flie file-control" data-limit="10"
                                                           multiple="multiple">
                                                    <input type="hidden" name="fileTypeId" value="1032"/>
                                                    <input type="hidden" name="fileSourceId" value="9"/>
                                                    <input type="hidden" name="companyId" value="">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
            <!-- 				</div> -->
            <div class="text-center" style="padding-bottom: 30px;">
                <input type="button" id="btn-submit" onclick="saveCashBill(1);" class="btn btn-primary"
                       style="width: 100px;background-color: #286090;" value="提交">
                <input type="button" id="btn-save" onclick="saveCashBill(0);" class="btn btn-primary"
                       style="width: 100px;margin-left: 100px!important;background-color: #5bc0de" value="保存">
                <a href="${ctx}/sceneEstate/qSceneRecognition/${project.estateId}?searchParam=1" class="btn btn-default"
                   style="width: 100px;margin-left: 100px!important;">取消</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/meta/js/common/jquery-ui.js?_v=${vs}"></script>
</body>
</html>