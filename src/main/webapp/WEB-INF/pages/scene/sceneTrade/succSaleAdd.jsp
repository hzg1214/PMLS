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
        i {
            color: #FF0000;
        }

        .lable-left {
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
        }

        .lable-right {
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
        }

    </style>
</head>
<body>
<div class="layui-form">
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">成销</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
                <legend>基本信息</legend>
            </fieldset>
            <div class="layui-form">
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left"><b>项目编号：${info.projectNo}</b></div>
                    <div class="layui-col-xs10 lable-right">
                        <b> &nbsp;&nbsp;&nbsp;楼盘名称：${info.estateNm}</b>
                    </div>
                    <input type="hidden" id="id" name="id" lay-verify="id" class="layui-input"
                           value="${info.id}">
                    <input type="hidden" id="estateId" name="estateId" lay-verify="estateId" class="layui-input"
                           value="${info.estateId}">
                    <input type="hidden" id="reportId" name="reportId" lay-verify="reportId" class="layui-input"
                           value="${info.reportId}">

                    <input type="hidden" id="projectNo" name="projectNo" lay-verify="projectNo" class="layui-input"
                           value="${info.projectNo}">
                    <input type="hidden" id="cityNo" name="cityNo" lay-verify="cityNo" class="layui-input"
                           value="${info.cityNo}">
                    <input type="hidden" id="htedition" name="htedition" lay-verify="htedition" class="layui-input"
                           value="${info.htedition}">
                    <input type="hidden" id="taxRate" name="taxRate" lay-verify="taxRate" class="layui-input"
                           value="${citySetting.taxRate}">
                    <input type="hidden" id="switchDate" value="${switchDate}"/>
                    <input type="hidden" id="commissionRatio" name="commissionRatio" lay-verify="commissionRatio"
                           class="layui-input"
                           value="${info.commissionRatio}">
                    <input type="hidden" id="commissionAmount" name="commissionAmount" lay-verify="commissionAmount"
                           class="layui-input"
                           value="${info.commissionAmount}">
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">订单编号：</div>
                    <div class="layui-col-xs4 lable-right">${info.reportId}</div>
                    <div class="layui-col-xs2 lable-left">经纪公司：</div>
                    <div class="layui-col-xs4 lable-right">${info.companyNm}</div>

                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">是否包销房源：</div>
                    <div class="layui-col-xs4 lable-right">
                        <c:if test="${info.isWrap eq 1}">
                            是
                        </c:if>
                        <c:if test="${info.isWrap ne 1}">
                            否
                        </c:if>
                    </div>
                    <input type="hidden" id='isWrap' value="${info.isWrap}">
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">物业类型：</div>
                    <div class="layui-col-xs4 lable-right">${info.wyTypName}</div>
                    <input type="hidden" id="wyTypeCode" name="wYTypeCode" value="${info.wyTypeCode}">
                    <div class="layui-col-xs2 lable-left">楼室号：</div>
                    <div class="layui-col-xs4 lable-right">${info.buildingNo}</div>
                    <input type="hidden" id='buildingNo' value="${info.buildingNo}">
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">大定面积(㎡)：</div>
                    <div class="layui-col-xs4 lable-right">${info.roughArea}</div>
                    <input type="hidden" id='roughArea' value="${info.roughArea}">
                    <div class="layui-col-xs2 lable-left">大定总价(元)：</div>
                    <div class="layui-col-xs4 lable-right">
                        <fmt:formatNumber value="${info.roughAmount}" pattern="#,##0.00"/>
                        <input type="hidden" id='roughAmount'
                               value="<fmt:formatNumber value="${info.roughAmount}" pattern="#.00"/>">
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">大定日期：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(info.roughInputDate)}</div>
                    <input type="hidden" id='roughInputDate' value="${sdk:ymd2(info.roughInputDate)}">
                    <input type="hidden" id='roughDate' value="${sdk:ymd2(info.roughDate)}">
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left"><i>*</i>客户姓名：</div>
                    <div class="layui-col-xs3 lable-right " style="padding: 0px">
                        <input type="text" id="customerNm" name="customerNm" lay-verify="required|customerNm"
                               maxlength="50" autocomplete="off" value="${info.customerNm}" lay-filter="customerNm"
                               class="layui-input" placeholder="请输入">
                    </div>
                    <div class="layui-col-xs1">&nbsp;</div>
                    <div class="layui-col-xs2 lable-left"><i>*</i>手机号码：</div>
                    <div class="layui-col-xs3 lable-right " style="padding: 0px">
                        <input type="text" id="customerTel" name="customerTel" lay-verify="required|customerTel"
                               maxlength="11" autocomplete="off" value="${info.customerTel}" lay-filter="customerTel"
                               class="layui-input" placeholder="请输入11位电话号码">
                    </div>
                    <div class="layui-col-xs1">&nbsp;</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">客户姓名：</div>
                    <div class="layui-col-xs3 lable-right " style="padding: 0px">
                        <input type="text" id="customerNmTwo" name="customerNmTwo" lay-verify="required|customerNmTwo"
                               maxlength="50" autocomplete="off" value="${info.customerNmTwo}"
                               lay-filter="customerNmTwo" class="layui-input" placeholder="请输入">
                    </div>
                    <div class="layui-col-xs1">&nbsp;</div>
                    <div class="layui-col-xs2 lable-left">手机号码：</div>
                    <div class="layui-col-xs3 lable-right " style="padding: 0px">
                        <input type="text" id="customerTelTwo" name="customerTelTwo"
                               lay-verify="required|customerTelTwo"
                               maxlength="11" autocomplete="off" value="${info.customerTelTwo}"
                               lay-filter="customerTelTwo" class="layui-input" placeholder="请输入11位电话号码">
                    </div>
                    <div class="layui-col-xs1">&nbsp;</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left"><i>*</i>成销面积(㎡)：</div>
                    <div class="layui-col-xs3 lable-right " style="padding: 0px">
                        <input type="text" id="dealArea" name="dealArea" lay-verify="dealArea" maxlength="11"
                               maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                               onchange="javascript:succSaleAdd.clearPlanAmount(); "
                               value="${info.area}" autocomplete="off" placeholder="请输入"
                               lay-filter="dealArea" class="layui-input">
                    </div>
                    <div class="layui-col-xs1">&nbsp;</div>
                    <div class="layui-col-xs2 lable-left"><i>*</i>成销总价(元)：</div>
                    <div class="layui-col-xs3 lable-right " style="padding: 0px">
                        <input type="text" id="dealAmount" name="dealAmount" lay-verify="dealAmount" maxlength="18"
                               maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                               placeholder="请输入"
                               onchange="javascript:succSaleAdd.clearPlanAmount(); "
                               value="<fmt:formatNumber value="${info.dealAmount}" pattern="#.00"/>"
                               autocomplete="off" lay-filter="dealAmount" class="layui-input">
                    </div>
                    <div class="layui-col-xs1">&nbsp;</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left"><i>*</i>成销日期：</div>

                    <div class="layui-col-xs3 lable-right " style="padding: 0px">
                        <input type="text" id="dealDate" name="dealDate" lay-verify="dealDate"
                               value="${sdk:ymd2(info.bizOperDate)}" autocomplete="off" placeholder="请选择"
                               onchange="javascript:succSaleAdd.clearPlanAmount(); "
                               lay-filter="dealDate" class="layui-input">
                    </div>
                    <div class="layui-col-xs1">&nbsp;</div>
                    <div class="layui-col-xs2 lable-left">结算日期：</div>
                    <div class="layui-col-xs3 lable-right " style="padding: 0px">
                        <input type="text" id="settleConfirmDate" name="settleConfirmDate"
                               lay-verify="settleConfirmDate" autocomplete="off" placeholder="请选择"
                               value="${sdk:ymd2(info.settleConfirmDate)}" lay-filter="settleConfirmDate"
                               class="layui-input">
                    </div>
                    <div class="layui-col-xs1">&nbsp;</div>

                </div>
                <div class="layui-row layui-form-item" style="margin-bottom:0px">
                    <div class="layui-col-xs2 lable-left"><i>*</i>核算主体：</div>
                    <div class="layui-col-xs3 lable-right " style="padding: 0px">
                        <select id="accountProjectNo" name="accountProjectNo"
                                lay-verify="accountProjectNo" lay-filter="accountProjectNo">
                            <option value=""></option>
                        </select>
                    </div>
                    <div class="layui-col-xs7">&nbsp;</div>
                </div>
            </div>
        </div>

    </div>

    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>佣金</legend>
            </fieldset>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>收入方案：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">

                    <c:forEach items="${planList}" var="plan">
                        <c:if test="${plan.companyNo eq '0'}">
                            <input readonly disabled type="text" id="planId" name="planId" lay-verify="planId"
                                   lay-filter="planId" data-id="${plan.planId}" value="${plan.planName}"
                                   autocomplete="off" placeholder="请选择" class="layui-input">
                        </c:if>
                    </c:forEach>

                </div>
                <div class="layui-col-xs1" style="text-align: center">
                    &nbsp;<button class="layui-btn layui-btn-normal" data-type="openIncomePlan">选择</button>
                </div>

                <div class="layui-col-xs6">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>应计收入税前(元)：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">
                    <input type="text" id="befYJSRAmount" name="befYJSRAmount" lay-verify="befYJSRAmount"
                           maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                           placeholder="请输入" autocomplete="off"
                           onchange="javascript:succSaleAdd.setFixRateAfterAmount('befYJSRAmount', 'aftYJSRAmount');"
                           lay-filter="befYJSRAmount" class="layui-input">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>
                <div class="layui-col-xs2 lable-left"><i>*</i>应计收入税后(元)：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">
                    <input type="text" id="aftYJSRAmount" name="aftYJSRAmount" lay-verify="aftYJSRAmount"
                           maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                           placeholder="请输入" autocomplete="off"
                           lay-filter="aftYJSRAmount" class="layui-input">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">垫佣控制规则：</div>
                <div class="layui-col-xs4 lable-right">
                    <c:if test="${info.isGlobalControl eq 1}">
                        项目总控
                    </c:if>
                    <c:if test="${info.isGlobalControl ne 1}">
                        房源单控
                    </c:if>

                    <input type="hidden" id="isGlobalControl" value="${info.isGlobalControl}"/>
                </div>
                <div class="layui-col-xs2 lable-left">垫佣比例：</div>
                <div class="layui-col-xs4 lable-right">
                    <input type="hidden" id="dyRatio" value="${info.dyRatio}"/>
                    <c:if test="${info.dyRatio eq null}">
                        -
                    </c:if>
                    <c:if test="${info.dyRatio ne null}">
                        ${100.00 * info.dyRatio}%
                    </c:if>
                </div>
            </div>
            <div id="fyObject">
                <c:forEach items="${info.fyObjectList}" var="list" varStatus="index">
                    <div class="fyObjectItem" data-companyNo='${list.companyNo}'>
                        <div class="layui-row">
                            <div class="layui-col-xs2 lable-left"><b>返佣对象
                                <c:if test="${index.index == 0}">一：</c:if>
                                <c:if test="${index.index == 1}">二：</c:if>
                                <c:if test="${index.index == 2}">三：</c:if>
                            </b></div>
                            <div class="layui-col-xs9 lable-right"><b>${list.companyNm}(${list.companyNo})</b></div>
                            <div class="layui-col-xs1">
                                <input type="hidden" name='companyNo' value="${list.companyNo}">
                                <c:if test="${index.index == 0}">
                                    <input type="hidden" id="firstCompanyNo" value="${list.companyNo}">
                                </c:if>
                            </div>
                        </div>


                        <div class="layui-row">
                            <div class="layui-col-xs2 lable-left"><i>*</i>返佣方案：</div>
                            <div class="layui-col-xs3 lable-right " style="padding: 0px">
                                <c:forEach items="${planList}" var="plan">
                                    <c:if test="${plan.companyNo eq list.companyNo}">
                                        <input readonly disabled type="text" name="fyPlanId" lay-verify="fyPlanId"
                                               lay-filter="fyPlanId" data-id="${plan.planId}" value="${plan.planName}"
                                               autocomplete="off" placeholder="请选择" class="layui-input">
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="layui-col-xs1" style="text-align: center">
                                &nbsp;<button class="layui-btn layui-btn-normal"
                                              data-companyNo="${list.companyNo}"
                                              onclick="javascript:succSaleAdd.openFyPlan(this)">选择
                            </button>
                            </div>
                            <div class="layui-col-xs6">&nbsp;</div>
                        </div>


                        <div class="layui-row">
                            <div class="layui-col-xs2 lable-left"><i>*</i>增值税税率：</div>
                            <div class="layui-col-xs9 lable-right" style="padding: 0px">
                                <input type="radio" value="0.01" tag="rate" name="rate${list.companyNo}"
                                       lay-filter="rate${list.companyNo}"
                                       lay-verify="rate${list.companyNo}" title="1%">
                                <input type="radio" value="0.03" tag="rate" name="rate${list.companyNo}"
                                       lay-filter="rate${list.companyNo}"
                                       lay-verify="rate${list.companyNo}" title="3%">
                                <input type="radio" value="0.06" tag="rate" name="rate${list.companyNo}"
                                       lay-filter="rate${list.companyNo}"
                                       lay-verify="rate${list.companyNo}" title="6%">
                            </div>
                            <div class="layui-col-xs1">&nbsp;</div>
                        </div>

                        <div class="layui-row">
                            <div class="layui-col-xs2 lable-left"><i>*</i>应计返佣税前(元)：</div>
                            <div class="layui-col-xs3 lable-right" style="padding: 0px">
                                <input type="text" name="befYJFYAmount" lay-verify="befYJFYAmount"
                                       oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')" autocomplete="off"
                                       onchange="javascript: succSaleAdd.setRipRateAfterAmount(this, 'befYJFYAmount', 'aftYJFYAmount');"
                                       value="${list.befYJFYAmount}" placeholder="请输入"
                                       lay-filter="befYJFYAmount" class="layui-input">
                            </div>
                            <div class="layui-col-xs1">&nbsp;</div>

                            <div class="layui-col-xs2 lable-left"><i>*</i>应计返佣税后(元)：</div>
                            <div class="layui-col-xs3 lable-right" style="padding: 0px">
                                <input type="text" name="aftYJFYAmount" lay-verify="aftYJFYAmount"
                                       maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                       value="${list.aftYJFYAmount}" placeholder="请输入" autocomplete="off"
                                       lay-filter="aftYJFYAmount" class="layui-input">
                            </div>
                            <div class="layui-col-xs1">&nbsp;</div>
                        </div>

                        <c:if test="${info.htedition ne '28301'}">
                            <%-- 如果是分销合同的版本是28302：房友  （28301：PMLS）--%>
                            <div class="layui-row">
                                <div class="layui-col-xs2 lable-left">应计垫佣税前(元)：</div>
                                <div class="layui-col-xs3 lable-right" style="padding: 0px">
                                    <input type="text" name="befYJDYAmount" lay-verify="befYJDYAmount"
                                           maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                           value="${list.befYJDYAmount}" placeholder="请输入" autocomplete="off"
                                           onchange="javascript: succSaleAdd.setRipRateAfterAmount(this, 'befYJDYAmount', 'aftYJDYAmount');"
                                           lay-filter="befYJDYAmount" class="layui-input">
                                </div>
                                <div class="layui-col-xs1">&nbsp;</div>
                                <div class="layui-col-xs2 lable-left">应计垫佣税后(元)：</div>
                                <div class="layui-col-xs3 lable-right" style="padding: 0px">
                                    <input type="text" name="aftYJDYAmount" lay-verify="aftYJDYAmount"
                                           maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                           value="${list.aftYJDYAmount}" placeholder="请输入" autocomplete="off"
                                           lay-filter="aftYJDYAmount" class="layui-input">
                                </div>
                                <div class="layui-col-xs1">&nbsp;</div>
                            </div>
                        </c:if>

                    </div>
                </c:forEach>
            </div>
            <%--<c:if test="${info.htedition ne '28301'}">--%>
            <%--<hr class="dottedLine"/>--%>
            <%--<div class="layui-row">--%>
            <%--<div class="layui-col-xs2 lable-left"><i>*</i>房友返佣税前(元)：</div>--%>
            <%--<div class="layui-col-xs3 lable-right" style="padding: 0px">--%>
            <%--<input type="text" id="befFangyouYJFYAmount" name="befFangyouYJFYAmount"--%>
            <%--lay-verify="befFangyouYJFYAmount"--%>
            <%--oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"--%>
            <%--placeholder="请输入" autocomplete="off"--%>
            <%--onchange="javascript:succSaleAdd.setFYRateAfterAmount('befFangyouYJFYAmount', 'aftFangyouYJFYAmount');"--%>
            <%--lay-filter="befFangyouYJFYAmount" class="layui-input">--%>
            <%--</div>--%>
            <%--<div class="layui-col-xs1">&nbsp;</div>--%>

            <%--<div class="layui-col-xs2 lable-left"><i>*</i>房友返佣税后(元)：</div>--%>
            <%--<div class="layui-col-xs3 lable-right" style="padding: 0px">--%>
            <%--<input type="text" id="aftFangyouYJFYAmount" name="aftFangyouYJFYAmount"--%>
            <%--lay-verify="aftFangyouYJFYAmount" autocomplete="off"--%>
            <%--maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"--%>
            <%--placeholder="请输入" lay-filter="aftFangyouYJFYAmount" class="layui-input">--%>
            <%--</div>--%>
            <%--<div class="layui-col-xs1">&nbsp;</div>--%>
            <%--</div>--%>

            <%--</c:if>--%>
        </div>
    </div>

    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>附件</legend>
            </fieldset>

            <div class="layui-upload" id="uploadSuccSaleImg">
                <div class="layui-col-xs2 lable-left"><i>*</i>成销确认书/佣金结算资料：</div>
                <div class="layui-col-xs10 lable-right" style="padding: 0px">

                    <button type="button" class="layui-btn uploadImg">上传</button>
                    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                        <div class="layui-upload-list upload_img_list"></div>
                    </blockquote>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"></div>
                <div class="layui-col-xs10 lable-right">
                    <button class="layui-btn layui-btn-normal" data-type="save">确定</button>
                    <button class="layui-btn layui-btn-primary" data-type="cancel">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/scene/sceneTrade/succSaleAdd.js?v=${vs}"></script>
</body>
</html>
