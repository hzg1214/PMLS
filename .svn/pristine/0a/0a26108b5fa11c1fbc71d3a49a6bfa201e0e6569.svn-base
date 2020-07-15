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

        .pl-30 {
            padding-left: 30px
        }

        .layui-form-label {
            width: 120px !important;
        }

        .w300 {
            width: 300px !important;
            text-align: left;
        }

        .w500 {
            width: 500px !important;
            text-align: left;
        }

        .renderInput {
            width: 100%;
            height: 90%
        }

        .mc {
            text-align: center;
        }

        .ml {
            text-align: left;
        }

        .mr {
            text-align: right;
        }

        .layui-table-link {
            cursor: pointer;
        }

        .layui-table td, .layui-table th, .s12 {
            font-size: 12px !important;
        }

    </style>
</head>

<script type="application/javascript">
    var cashBillInfoJsonDto = '${cashBillInfoJson}';
</script>
<body>

<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-row blockBody">
            <div class="layui-col-xs6">
                <div class="blockTitle">请款申请</div>
            </div>
            <div class="layui-col-xs6 blockBtn">
                <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
            <legend>基本信息</legend>
        </fieldset>

        <div class="layui-form">
            <input type="hidden" id="proParentId" name="proParentId" value="${cashBillInfo.proParentId}">
            <input type="hidden" id="comParentId" name="comParentId" value="${cashBillInfo.comParentId}">
            <input type="hidden" id="nowDate" name="nowDate" value="${nowDate}">
            <input type="hidden" id="isEdit" name="isEdit" value="${isEdit}">
            <c:choose>
                <c:when test="${isEdit eq '1'}">
                    <input type="hidden" id="recordTimeInit" name="recordTimeInit" value="${sdk:ymd2(cashBillInfo.recordTime)}">
                </c:when>
                <c:otherwise>
                    <input type="hidden" id="recordTimeInit" name="recordTimeInit" value="${nowDate}">
                </c:otherwise>
            </c:choose>


            <div class="layui-form-item layui-row">
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>结算书</label>
                    <div class="layui-col-xs6">
                        <input type="text" placeholder="请选择" id="jssNo" name="jssNo" lay-filter="jssNo" readonly disabled value="${cashBillInfo.pjsNostr}" class="layui-input">
                        <input type="hidden" id="vendorId" name="vendorId" value="${cashBillInfo.vendorId}">
                        <input type="hidden" id="accountProjectNo" name="accountProjectNo" value="${cashBillInfo.accountProjectNo}">
                    </div>
                    <div class="layui-col-xs1" style="padding-left: 10px">
                        <c:if test="${isEdit eq '2'}">
                        <button class="layui-btn layui-btn-normal" onclick="javascript:batchExpentNewJs.getJss(this);">选择 </button>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="layui-form-item layui-row">
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>渠道公司</label>
                    <div class="layui-col-xs6">
                        <input type="text" id="companyName" name="companyName" lay-filter="companyName" readonly disabled value="${cashBillInfo.companyName}" class="layui-input">
                        <%-- <input type="hidden" id="companyNo" name="companyNo" value="">--%>
                    </div>
                </div>
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>项目</label>
                    <div class="layui-col-xs6">
                        <input type="text" id="estateNm" name="estateNm" lay-filter="estateNm" readonly disabled value="${cashBillInfo.estateNm}" class="layui-input">
                        <%-- <input type="hidden" id="projectNo" name="projectNo" value="">--%>
                    </div>
                </div>
            </div>

            <div class="layui-form-item layui-row">
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>供应商</label>
                    <div class="layui-col-xs6">
                        <input type="text" id="vendorName" name="vendorName" lay-filter="vendorName" readonly disabled value="${cashBillInfo.vendorName}" class="layui-input">
                        <%-- <input type="hidden" id="companyNo" name="companyNo" value="">--%>
                    </div>
                </div>
            </div>


            <div class="layui-form-item layui-row">
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>收款银行</label>
                    <div class="layui-col-xs6">
                        <input type="text" placeholder="请选择" id="receiveBankName" name="receiveBankName" lay-filter="receiveBankName" readonly disabled value="${cashBillInfo.receiveBankName}" class="layui-input">
                    </div>
                    <div class="layui-col-xs1" style="padding-left: 10px">
                        <button class="layui-btn layui-btn-normal" onclick="javascript:batchExpentNewJs.getReceiveBank(this);">选择 </button>
                    </div>
                </div>
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>银行账户</label>
                    <div class="layui-col-xs6">
                        <input type="text" id="receiveBankAccountCardCode"  name="receiveBankAccountCardCode" lay-filter="receiveBankAccountCardCode" readonly disabled value="${cashBillInfo.receiveBankAccountCardCode}" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-row">
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>收款户名</label>
                    <div class="layui-col-xs6">
                        <input type="text" id="receiveBankAccountName" name="receiveBankAccountName" lay-filter="receiveBankAccountName" readonly disabled value="${cashBillInfo.receiveBankAccountName}" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>收款省市</label>
                    <div class="layui-col-xs6">
                        <input type="text" id="receiveBankProvinceCityName" name="receiveBankProvinceCityName" lay-filter="receiveBankProvinceCityName" readonly disabled value="${cashBillInfo.receiveBankProvinceName}${cashBillInfo.receiveBankCityName}" class="layui-input">
                        <input type="hidden" id="receiveBankProvinceName" name="receiveBankProvinceName" lay-filter="receiveBankProvinceName" value= "${cashBillInfo.receiveBankProvinceName}">
                        <input type="hidden" id="receiveBankCityName" name="receiveBankCityName" lay-filter="receiveBankCityName" value= "${cashBillInfo.receiveBankCityName}">
                        <input type="hidden" id="receiveBankSerialNo" name="receiveBankSerialNo" lay-filter="receiveBankSerialNo" value= "${cashBillInfo.receiveBankSerialNo}">
                    </div>
                </div>
            </div>



            <div class="layui-form-item layui-row">

                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>入账日期</label>
                    <div class="layui-col-xs6">
                        <input type="text" id="recordTime" name="recordTime" lay-verify="required|recordTime"
                               autocomplete="off" lay-filter="recordTime" class="layui-input" value="${sdk:ymd2(cashBillInfo.recordTime)}">
                    </div>
                </div>
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>预计付款日期</label>
                    <div class="layui-col-xs6">
                        <input type="text" id="predictPayTime" name="predictPayTime"
                               autocomplete="off" lay-verify="required|predictPayTime" lay-filter="predictPayTime" value="${sdk:ymd2(cashBillInfo.predictPayTime)}"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-row">
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>付款方式</label>
                    <div class="layui-col-xs6">
                        <select id="payType" name="payType" lay-verify="payType" lay-filter="payType">
                        </select>
                    </div>
                </div>
            </div>


            <div class="layui-form-item layui-row">
                <div class="layui-col-xs12">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-col-xs9">
                        <textarea placeholder="200字以内" maxlength="200" id="remarks" name="remarks" lay-filter="remarks" class="layui-textarea">${cashBillInfo.remarks}</textarea>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>



<div id="reportCard" <c:if test="${isEdit eq '2'}"> style="display: none;" </c:if> class="layui-card">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>请款订单</legend>
        </fieldset>
        <div class="layui-form">
            <div tag="normal">
                <div class="layui-row">
                    <div class="layui-col-xs12 lable-right" style="padding-left: 50px"><b></b>
                        <button class="layui-btn layui-btn-normal layui-btn-sm" data-type="openDialogGetJssDtl">
                            选择请款订单
                        </button>
                    </div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs12" style="padding-left: 51px;">
                        <table id="normalReport" name="normalReport" lay-size="sm"
                               lay-filter="normalReport"></table>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs12" style="padding: 10px 80px">
                    <div class="layui-col-xs2">&nbsp;</div>
                    <div class="layui-col-xs10">

                        <div class="layui-col-xs3 s12">
                            <b>含税请款金额总计：</b><span tag="requestAmountTotal">0</span>元
                            <input type="hidden" name="requestAmountTotal" value="">
                        </div>

                        <div class="layui-col-xs3 s12">
                            <b>税额总计：</b><span tag="taxAmountTotal">0</span>元
                            <input type="hidden" name="taxAmountTotal" value="">
                        </div>

                        <div class="layui-col-xs3 s12">
                            <b>不含税请款金额总计：</b><span tag="noTaxAmountTotal">0</span>元
                            <input type="hidden" id="noTaxAmountTotal" name="noTaxAmountTotal" value="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="layui-card">
    <div class="layui-card-body">
        <fieldset id="fieldset1" class="layui-elem-field layui-field-title" <c:if test="${isEdit eq '1'}"> style="margin-top: 20px;"  </c:if><c:if test="${isEdit eq '2'}"> style="margin-top: 20px;display: none;" </c:if>>
            <legend>附件</legend>
        </fieldset>

        <%--<div class="layui-upload" id="uploadImg" <c:if test="${isEdit eq '2'}"> style="display: none;" </c:if>>
            <div class="layui-col-xs2 lable-left"><i>*</i>成销确认书/佣金结算资料：</div>
            <div class="layui-col-xs10 lable-right" style="padding: 0px">
                <button type="button" class="layui-btn uploadImg">上传</button>
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px; ">
                    <div class="layui-upload-list upload_img_list">
                    </div>
                </blockquote>
            </div>
        </div>--%>

        <div class="layui-upload" id="uploadFpImg" <c:if test="${isEdit eq '2'}"> style="display: none;" </c:if>>
            <div class="layui-col-xs2 lable-left"><i>*</i>发票：</div>
            <div class="layui-col-xs10 lable-right" style="padding: 0px">
                <button type="button" class="layui-btn uploadImg">上传</button>
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px; ">
                    <div class="layui-upload-list upload_img_list">
                    </div>
                </blockquote>
            </div>
        </div>


        <div class="layui-upload" id="uploadOtherImg" <c:if test="${isEdit eq '2'}"> style="display: none;" </c:if>>
            <div class="layui-col-xs2 lable-left"><i></i>其它：</div>
            <div class="layui-col-xs10 lable-right" style="padding: 0px">
                <button type="button" class="layui-btn uploadImg">上传</button>
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px; ">
                    <div class="layui-upload-list upload_img_list">
                    </div>
                </blockquote>
            </div>
        </div>


        <div class="layui-row">
            <div class="layui-col-xs2 lable-left"></div>
            <div class="layui-col-xs10 lable-right">
                <button class="layui-btn layui-btn-normal" data-type="submit">提交</button>
                <button class="layui-btn layui-btn-normal" data-type="save">保存</button>
                <button class="layui-btn layui-btn-primary" data-type="cancel">取消</button>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/scene/sceneExpent/batchExpentNewJs.js?v=${vs}"></script>
</body>
