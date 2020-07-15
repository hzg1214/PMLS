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

        .lable-form-right {
            width: 400px !important;
            text-align: left;
        }

        .myForm .layui-inline {
            width: 420px;
        }

        .w250 {
            width: 250px !important;
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
                <div class="blockTitle">大定修改</div>
            </div>
            <div class="layui-col-xs6 blockBtn">
                <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
            <legend>基本信息</legend>
        </fieldset>

        <div class="layui-form myForm" style="margin-top:20px;">
            <div class="layui-form-item">
                <label class="layui-form-label"><b>项目编号：${info.projectNo}</b></label>
                <label class="layui-form-label lable-form-right"><b>楼盘名称：${info.estateNm}</b></label>
                <input type="hidden" id="id" name="id" lay-verify="id" class="layui-input"
                       value="${info.id}">
                <input type="hidden" id="estateId" name="estateId" lay-verify="estateId" class="layui-input"
                       value="${info.estateId}">
                <input type="hidden" id="reportId" name="reportId" lay-verify="reportId" class="layui-input"
                       value="${info.reportId}">
                <input type="hidden" id="projectNo" name="projectNo" lay-verify="projectNo" class="layui-input"
                       value="${info.projectNo}">
                <input type="hidden" id="reportDetailId" name="reportDetailId" lay-verify="reportDetailId"
                       class="layui-input"
                       value="${info.detailId}">
                <input type="hidden" id="customerIdTwo" name="customerIdTwo" value="${info.customerIdTwo}">
                <input type="hidden" id="oldFileRecordMainIds" name="oldFileRecordMainIds">
                <input type="hidden" id="seeDate" name="seeDate" lay-verify="seeDate" class="layui-input"
                      value="${sdk:ymd2(info.seeDate)} ">
                <input type="hidden" id="switchDate" value="${switchDate}"/>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">订单编号：</label>
                <label class="layui-form-label lable-form-right">${info.reportId}</label>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">经纪公司：</label>
                <label class="layui-form-label lable-form-right">${info.companyNm}</label>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>客户姓名</label>
                <div class="layui-inline">
                    <input type="hidden" id="oldCustomerNm" name="oldCustomerNm" value="${info.customerNm}">

                    <input type="text" id="customerNm" name="customerNm" lay-verify="required|customerNm"
                           value="${info.customerNm}"
                           lay-filter="customerNm" class="layui-input" placeholder="请输入">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>手机号码</label>
                <div class="layui-inline">
                    <input type="hidden" id="oldCustomerTel" name="oldCustomerTel" value="${info.customerTel}">
                    <input type="text" id="customerTel" name="customerTel" lay-verify="required|customerTel"
                           maxlength="11" value="${info.customerTel}"
                           lay-filter="customerTel" class="layui-input" placeholder="请输入11位电话号码">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">客户姓名</label>
                <div class="layui-inline">

                    <input type="hidden" id="oldCustomerNmTwo" name="oldCustomerNmTwo" value="${info.customerNmTwo}">
                    <input type="text" id="customerNmTwo" name="customerNmTwo" lay-verify="customerNmTwo"
                           value="${info.customerNmTwo}"
                           lay-filter="customerNmTwo" class="layui-input" placeholder="请输入">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-inline">

                    <input type="hidden" id="oldCustomerTelTwo" name="oldCustomerTelTwo" value="${info.customerTelTwo}">
                    <input type="text" id="customerTelTwo" name="customerTelTwo" lay-verify="customerTelTwo"
                           maxlength="11" value="${info.customerTelTwo}"
                           lay-filter="customerTelTwo" class="layui-input" placeholder="请输入11位电话号码">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否包销房源</label>
                <input type="hidden" id="oldIsWrap" value="${info.isWrap}"/>
                <div class="layui-inline">
                    <input type="radio" value="1" name="isWrap" lay-filter="isWrap"
                           <c:if test="${info.isWrap eq 1 }">checked</c:if>
                           lay-verify="required|isWrap" title="是">
                    <input type="radio" value="0" name="isWrap" lay-filter="isWrap"
                           <c:if test="${info.isWrap ne 1 }">checked</c:if>
                           lay-verify="required|isWrap" title="否">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>楼室号</label>
                <input type="hidden" id="oldBuildingNoId" name="oldBuildingNoId" value="${info.buildingNoId}">
                <input type="hidden" id="oldBuildingNo" name="oldBuildingNo" value="${info.buildingNo}">

                <input type="hidden" id="newChange_buildingNoId" name="newChange_buildingNoId" value="${info.buildingNoId}"/>
                <input type="hidden" id="newChange_buildingNo" name="newChange_buildingNo" value="${info.buildingNo}"/>
                <input type="hidden" id="newChange_roughArea" name="newChange_roughArea" value="${info.roughArea}"/>

                <div class="layui-inline w250">
                    <input <c:if test="${info.isWrap eq 1 }"> readonly disabled</c:if>
                            type="text" id="buildingNo" name="buildingNo" lay-verify="buildingNo"
                           lay-filter="buildingNo"  maxlength="30" autocomplete="off" value="${info.buildingNo}"
                           class="layui-input" placeholder="请输入" data-buildingNoId="${info.buildingNoId}"
                    >
                </div>
                <div id="divbuildingNo" class="layui-inline" style="width: 100px!important;
                <c:if test="${info.isWrap ne 1 }"> display: none;</c:if> ">
                    <button class="layui-btn layui-btn-normal" data-type="selectBuildingNo">选择</button>
                </div>


            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>物业类型</label>
                <div class="layui-inline w250">

                    <input type="hidden" id="oldWYTypeCode" name="oldWYTypeCode" value="${info.wyTypeCode}">
                    <input type="hidden" id="oldWYTypeName" name="oldWYTypeName" value="${info.wyTypeName}">

                    <select id="ddlWYType" name="ddlWYType" lay-verify="ddlWYType" lay-filter="ddlWYType">
                        <option value="" data-storeNo="" data-storeName="">请选择</option>
                        <c:forEach items="${wyTypeList}" var="wyType">
                            <option value="${wyType.wyTypeCode}"
                                    <c:if test="${info.wyTypeCode eq wyType.wyTypeCode}">selected</c:if>
                            >${wyType.wyTypName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>大定面积(㎡)</label>
                <input type="hidden" id="oldRoughArea" name="oldRoughArea" value="${info.roughArea}">
                <div class="layui-inline w250">
                    <input
                            <c:if test="${info.isWrap eq 1 }">readonly disabled</c:if>
                            type="text" id="roughArea" name="roughArea" lay-verify="roughArea"
                            lay-filter="roughArea"
                            maxlength="20" autocomplete="off" value="${info.roughArea}"
                            oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                            class="layui-input" placeholder="请输入">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>大定总价(元)</label>
                <input type="hidden" id="oldRoughAmount" name="oldRoughAmount"
                       value="<fmt:formatNumber value="${info.roughAmount}" pattern="#.00"/>">
                <div class="layui-inline w250">
                    <input type="text" id="roughAmount" name="roughAmount" lay-verify="roughAmount"
                           lay-filter="roughAmount" maxlength="20" autocomplete="off"
                           value="<fmt:formatNumber value="${info.roughAmount}" pattern="#.00"/>"
                           oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                           class="layui-input" placeholder="请输入">
                </div>

            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>大定日期</label>
                <input type="hidden" id="oldBizOperDate" name="oldBizOperDate" value="${sdk:ymd2(info.roughInputDate)}">

                <div class="layui-inline w250">
                    <input type="text" id="roughInputDate" name="roughInputDate" lay-verify="roughInputDate"
                           placeholder="请选择"
                           value="${sdk:ymd2(info.roughInputDate)}" autocomplete="off"
                           lay-filter="roughInputDate" class="layui-input">
                </div>
            </div>

        </div>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>附件</legend>
        </fieldset>

        <div class="layui-form enclosureForm">
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>带看单</label>
                <div class="layui-input-block">

                    <div class="layui-upload" id="uploadWatchImg">
                        <button type="button" class="layui-btn uploadImg">上传</button>
                        <i>注：带看单必须包含中介名称、客户名称、客户电话、驻场签字和客户签字信息。</i>
                        <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                            <div class="layui-upload-list upload_img_list">
                                <%--<c:if test="${not empty info.fileList}">--%>
                                    <%--<c:set var="fileSize" value="0"/>--%>
                                    <%--<c:forEach items="${info.fileList}" var="list" varStatus="status">--%>
                                        <%--<c:if test="${list.fileTypeId eq 1022}">--%>
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
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>大定单</label>
                <div class="layui-input-block">

                    <div class="layui-upload" id="uploadRoughtImg">
                        <button type="button" class="layui-btn uploadImg">上传</button>
                        <i>注：大定单必须包含大定面积、金额、楼室号、客户名、客户电话、客户签名、日期和甲方盖章信息。</i>
                        <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                            <div class="layui-upload-list upload_img_list">
                                <%--<c:if test="${not empty info.fileList}">--%>
                                    <%--<c:set var="fileSize" value="0"/>--%>
                                    <%--<c:forEach items="${info.fileList}" var="list" varStatus="status">--%>
                                        <%--<c:if test="${list.fileTypeId eq 1023}">--%>
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
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>甲方项目负责人名片</label>
                <div class="layui-input-block">

                    <div class="layui-upload" id="uploadPartFirstImg">
                        <button type="button" class="layui-btn uploadImg">上传</button>
                        <i>注：带看单上签字的甲方相关负责人名片。</i>
                        <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                            <div class="layui-upload-list upload_img_list">
                                <%--<c:if test="${not empty info.fileList}">--%>
                                    <%--<c:set var="fileSize" value="0"/>--%>
                                    <%--<c:forEach items="${info.fileList}" var="list" varStatus="status">--%>
                                        <%--<c:if test="${list.fileTypeId eq 1024}">--%>
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
            </div>
            <div class="operationPageToolbar">
                <button class="layui-btn layui-btn-normal" data-type="save">确定</button>
                <button class="layui-btn layui-btn-primary" data-type="cancel">取消</button>
            </div>

        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/scene/sceneTrade/roughtUpt.js?v=${vs}"></script>

</body>
</html>