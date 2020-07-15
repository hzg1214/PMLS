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
<script type="application/javascript">
    var reportInfo = ${info};
    console.log(${info});
</script>
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
                <input type="hidden" id="reportDetailId" name="reportDetailId" lay-verify="reportDetailId"
                       class="layui-input"
                       value="${info.detailId}">
                <input type="hidden" id="projectNo" name="projectNo" lay-verify="projectNo" class="layui-input"
                       value="${info.projectNo}">
                <input type="hidden" id="cityNo" name="cityNo" lay-verify="cityNo" class="layui-input"
                       value="${info.cityNo}">
                <input type="hidden" id="switchDate" value="${switchDate}"/>
                <input type="hidden" id="oldFileRecordMainIds" name="oldFileRecordMainIds">

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
                <input type="hidden" id="oldWYTypeCode" name="oldWYTypeCode" value="${info.wyTypeCode}">
                <input type="hidden" id="wyTypeName" name="wYTypeName" value="${info.wyTypeName}">
                <input type="hidden" id="oldWYTypeName" name="oldWYTypeName" value="${info.wyTypeName}">

                <div class="layui-col-xs2 lable-left">楼室号：</div>
                <div class="layui-col-xs4 lable-right">${info.buildingNo}</div>

                <input type="hidden" id='oldBuildingNo' value="${info.buildingNo}">
                <input type="hidden" id='buildingNo' value="${info.buildingNo}">
                <input type="hidden" id="oldBuildingNoId" value="${info.buildingNoId}"/>
                <input type="hidden" id="buildingNoId" value="${info.buildingNoId}"/>

            </div>


            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">大定面积(㎡)：</div>
                <div class="layui-col-xs4 lable-right">${info.roughArea}</div>
                <input type="hidden" id='roughArea' value="${info.roughArea}">
                <input type="hidden" id='oldRoughArea' value="${info.roughArea}">
                <div class="layui-col-xs2 lable-left">大定总价(元)：</div>
                <div class="layui-col-xs4 lable-right">
                    <fmt:formatNumber value="${info.roughAmount}" pattern="#,##0.00"/>
                    <input type="hidden" id='roughAmount'
                           value="<fmt:formatNumber value="${info.roughAmount}" pattern="#.00"/>">
                    <input type="hidden" id='roughAmount' value="${info.roughAmount}">
                    <input type="hidden" id='oldRoughAmount' value="${info.roughAmount}">
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">大定日期：</div>
                <div class="layui-col-xs4 lable-right">${sdk:ymd2(info.roughInputDate)}</div>
                <input type="hidden" id='roughInputDate' value="${info.roughInputDate}">
                <input type="hidden" id='roughDate' value="${info.roughDate}">
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>客户姓名：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">
                    <input type="hidden" id="customerId" name="customerId" value="${info.customerId}">
                    <input type="hidden" id="oldCustomerNm" name="oldCustomerNm" value="${info.customerNm}">
                    <input type="text" id="customerNm" name="customerNm" lay-verify="required|customerNm" maxlength="50"
                           autocomplete="off" value="${info.customerNm}" lay-filter="customerNm" class="layui-input"
                           placeholder="请输入">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>
                <div class="layui-col-xs2 lable-left"><i>*</i>手机号码：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">
                    <input type="hidden" id="oldCustomerTel" name="oldCustomerTel" value="${info.customerTel}">
                    <input type="text" id="customerTel" name="customerTel" lay-verify="required|customerTel"
                           maxlength="11" autocomplete="off" value="${info.customerTel}" lay-filter="customerTel"
                           class="layui-input" placeholder="请输入11位电话号码">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">客户姓名：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">

                    <input type="hidden" id="customerIdTwo" name="customerIdTwo" value="${info.customerIdTwo}">
                    <input type="hidden" id="oldCustomerNmTwo" name="oldCustomerNmTwo" value="${info.customerNmTwo}">
                    <input type="text" id="customerNmTwo" name="customerNmTwo" lay-verify="required|customerNmTwo"
                           maxlength="50" autocomplete="off" value="${info.customerNmTwo}" lay-filter="customerNmTwo"
                           class="layui-input" placeholder="请输入">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>
                <div class="layui-col-xs2 lable-left">手机号码：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">
                    <input type="hidden" id="oldCustomerTelTwo" name="oldCustomerTelTwo" value="${info.customerTelTwo}">
                    <input type="text" id="customerTelTwo" name="customerTelTwo" lay-verify="required|customerTelTwo"
                           maxlength="11" autocomplete="off" value="${info.customerTelTwo}" lay-filter="customerTelTwo"
                           class="layui-input" placeholder="请输入11位电话号码">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>成销面积(㎡)：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">
                    <input type="hidden" id="oldArea" name="oldArea" value="${info.area}">
                    <input type="text" id="dealArea" name="dealArea" lay-verify="dealArea" maxlength="11"
                           maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                           value="${info.area}" autocomplete="off" placeholder="请输入"
                           lay-filter="dealArea" class="layui-input">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>
                <div class="layui-col-xs2 lable-left"><i>*</i>成销总价(元)：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">
                    <input type="hidden" id="oldDealAmount" name="oldDealAmount"
                           value="<fmt:formatNumber value="${info.dealAmount}" pattern="#.00"/>">

                    <input type="text" id="dealAmount" name="dealAmount" lay-verify="dealAmount" maxlength="18"
                           maxlength="20" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                           placeholder="请输入"
                           value="<fmt:formatNumber value="${info.dealAmount}" pattern="#.00"/>"
                           lay-filter="dealAmount" class="layui-input">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>成销日期：</div>

                <div class="layui-col-xs3 lable-right " style="padding: 0px">
                    <input type="hidden" id="oldBizOperDate" name="oldBizOperDate"
                           value="${sdk:ymd2(info.bizOperDate)}">
                    <input type="text" id="dealDate" name="dealDate" lay-verify="dealDate"
                           value="${sdk:ymd2(info.bizOperDate)}" autocomplete="off" placeholder="请选择"
                           lay-filter="dealDate" class="layui-input">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>
                <div class="layui-col-xs2 lable-left">结算日期：</div>
                <div class="layui-col-xs3 lable-right " style="padding: 0px">
                    <input type="hidden" id="oldSettleConfirmDate" name="oldSettleConfirmDate"
                           value="${sdk:ymd2(info.settleConfirmDate)}">
                    <input type="text" id="settleConfirmDate" name="settleConfirmDate"
                           lay-verify="settleConfirmDate" autocomplete="off" placeholder="请选择"
                           value="${sdk:ymd2(info.settleConfirmDate)}" lay-filter="settleConfirmDate"
                           class="layui-input">
                </div>
                <div class="layui-col-xs1">&nbsp;</div>

            </div>
            <div class="layui-row layui-form-item" style="margin-bottom:0px">
                <input type="hidden" id='dbAccountProjectNo' value="${info.accountProjectNo}">
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
            <legend>附件</legend>
        </fieldset>

        <div class="layui-upload" id="uploadSuccSaleImg">
            <div class="layui-col-xs2 lable-left"><i>*</i>成销确认书/佣金结算资料：</div>
            <div class="layui-col-xs10 lable-right" style="padding: 0px">

                <button type="button" class="layui-btn uploadImg">上传</button>
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                    <div class="layui-upload-list upload_img_list">
                        <%--<c:if test="${not empty info.fileList}">--%>
                        <%--<c:set var="fileSize" value="0"/>--%>
                        <%--<c:forEach items="${info.fileList}" var="list" varStatus="status">--%>
                        <%--<c:if test="${list.fileTypeId eq 1025}">--%>
                        <%--<dd class="item_img" id="${list.fileRecordMainId}"--%>
                        <%--data-id="${list.fileRecordMainId}">--%>
                        <%--<div class="operate">--%>
                        <%--<i onclick="UPLOAD_IMG_DEL('${list.fileRecordMainId}')"--%>
                        <%--class="close layui-icon"></i></div>--%>
                        <%--<img src="${list.fileAbbrUrl}" class="img">--%>
                        <%--<input type="hidden" name="dzd_img[]"--%>
                        <%--value="${list.fileAbbrUrl}">--%>
                        <%--</dd>--%>
                        <%--</c:if>--%>
                        <%--</c:forEach>--%>
                        <%--</c:if>--%>
                    </div>
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

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/succSaleUpt.js?v=${vs}"></script>
</body>
</html>
