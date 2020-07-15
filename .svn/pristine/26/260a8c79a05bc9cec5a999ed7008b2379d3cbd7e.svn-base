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
            padding: 0;
        }

        .lable-button {
            text-align: center
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

        .layui-table td, .layui-table th, .s12 {
            font-size: 12px !important;
        }

    </style>
</head>
<body>

<script type="application/javascript">
    var fileListInfo = ${fileListInfo};
</script>
<div class="layui-form">
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">结算书申请</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
                <legend>基本信息</legend>
                <input type="hidden" id="id" value="${info.id}">
                <input type="hidden" id="exclude" value="${info.exclude}">
            </fieldset>

            <c:if test="${id ne null}">
                <div class="layui-col-xs2 lable-left">结算书编号：</div>
                <div class="layui-col-xs4 lable-right" style="padding: 10px 0;">
                        ${info.jssNo}
                </div>
                <div class="layui-col-xs5">&nbsp;</div>
            </c:if>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>渠道公司：</div>
                <div class="layui-col-xs4 lable-right">
                    <input readonly disabled type="text" id="companyId" name="companyId"
                           data-companyId="${info.companyId}" data-companyNo="${info.companyNo}"
                           value="${info.companyName}"
                           autocomplete="off" placeholder="请选择" class="layui-input">
                </div>
                <div class="layui-col-xs1 lable-button">
                    <c:if test="${id eq null}">
                        <button class="layui-btn layui-btn-normal" data-type="openDialogCompany">选择</button>
                    </c:if>
                </div>
                <div class="layui-col-xs5">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>项目：</div>
                <div class="layui-col-xs4 lable-right">
                    <input readonly disabled type="text" id="projectNo" name="projectNo"
                           data-projectNo="${info.projectNo}" value="${info.projectName}"
                           autocomplete="off" placeholder="请选择" class="layui-input">
                    <input type="hidden" id="isSpecialProject" value="${info.isSpecialProject}">
                </div>
                <div class="layui-col-xs1 lable-button">
                    <c:if test="${id eq null}">
                        <button class="layui-btn layui-btn-normal" data-type="openDialogProject">选择</button>
                    </c:if>
                </div>
                <div class="layui-col-xs5">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>核算主体：</div>
                <div class="layui-col-xs4 lable-right">
                    <select id="ddlHSCode" name="ddlHSCode" lay-verify="ddlHSCode" lay-filter="ddlHSCode">
                        <option value="" data-hsName="">请选择核算主体</option>
                        <c:forEach items="${info.hsCodeList}" var="list">
                            <option value="${list.hsCode}" data-hsName="${list.hsname}"
                                    <c:if test="${info.hsCode eq list.hsCode}">selected</c:if>
                            >${list.hsCode}_${list.hsname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="layui-col-xs6">&nbsp;</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>考核主体：</div>

                <div class="layui-col-xs4 lable-right">
                    <input readonly disabled type="text" id="khCode" name="khCode"
                           data-khCode="${info.khCode}" value="${info.khName}"
                           autocomplete="off" placeholder="请选择" class="layui-input">
                </div>
                <div class="layui-col-xs1 lable-button">
                    <button class="layui-btn layui-btn-normal" data-type="openDialogKhCode">选择</button>
                </div>
                <div class="layui-col-xs5">&nbsp;</div>


            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>合同：</div>
                <div class="layui-col-xs4 lable-right">
                    <input readonly disabled type="text" id="frameOaNo" name="frameOaNo"
                           data-frameOaName="${info.frameOaName}" value="${info.frameOaNo}"
                           autocomplete="off" placeholder="请选择" class="layui-input">
                </div>
                <div class="layui-col-xs1 lable-button">
                    <button class="layui-btn layui-btn-normal" data-type="openDialogFrameOaNo">选择</button>
                </div>
                <div class="layui-col-xs5">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>供应商：</div>
                <div class="layui-col-xs4 lable-right">
                    <input readonly disabled type="text" id="vendorId" name="vendorId"
                           data-vendorId="${info.vender_id}" data-vendorCode="${info.vender_code}"
                           value="${info.vender_name}"
                           autocomplete="off" placeholder="请选择" class="layui-input">
                </div>
                <div class="layui-col-xs5">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>供应商税率：</div>
                <div class="layui-col-xs4 lable-right">
                    <input type="radio" value="0.00" name="kpRate" lay-filter="kpRate" lay-verify="kpRate"
                           <c:if test="${info.kpRate eq 0.00 }">checked</c:if> title="0%">
                    <input type="radio" value="0.01" name="kpRate" lay-filter="kpRate" lay-verify="kpRate"
                           <c:if test="${info.kpRate eq 0.01 }">checked</c:if> title="1%">
                    <input type="radio" value="0.03" name="kpRate" lay-filter="kpRate" lay-verify="kpRate"
                           <c:if test="${info.kpRate eq 0.03 }">checked</c:if> title="3%">
                    <input type="radio" value="0.06" name="kpRate" lay-filter="kpRate" lay-verify="kpRate"
                           <c:if test="${info.kpRate eq 0.06 }">checked</c:if> title="6%">
                </div>
                <div class="layui-col-xs5">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>是否包含冲抵结算：</div>
                <div class="layui-col-xs4 lable-right">
                    <input type="radio" tag="offSetFlag" value="1" name="offSetFlag" title="是"
                           <c:if test="${info.offSetFlag eq 1 }">checked</c:if> lay-filter="offSetFlag">
                    <input type="radio" tag="offSetFlag" value="0" name="offSetFlag" title="否"
                           <c:if test="${info.offSetFlag ne 1 }">checked</c:if> lay-filter="offSetFlag">
                </div>
                <div class="layui-col-xs4">&nbsp;</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">备注：</div>
                <div class="layui-col-xs7 lable-right">
                <textarea id="remark" placeholder="200字以内" maxlength="200" name="remark" lay-filter="remark"
                          class="layui-textarea">${info.remark}</textarea>
                </div>
                <div class="layui-col-xs3">&nbsp;</div>
            </div>
        </div>
    </div>

    <div id="reportCard"   <c:if test="${id eq null}"> style="display: none;" </c:if>>
        <div class="layui-card">
            <div class="layui-card-body">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                    <legend>选择结算订单</legend>
                </fieldset>
                <div class="layui-form">
                    <div tag="normal">
                        <div class="layui-row">
                            <div class="layui-col-xs12 lable-right" style="padding-left: 40px"><b>正常结算：</b>
                                <button class="layui-btn layui-btn-normal layui-btn-sm"
                                        data-type="openDialogNormalReport">
                                    选择结算订单
                                </button>
                            </div>
                        </div>
                        <div class="layui-row" style="padding: 5px 0px 0px 80px">
                            <div class="layui-col-xs4 s12">
                                <b>总控项目可垫金额：<span tag="xm_ZK_vaildDYAmount"><fmt:formatNumber
                                        value="${info.zkInfo.xm_ZK_vaildDYAmount}" pattern="#,##0.00"
                                        maxFractionDigits="2"/></span>元</b>
                                <input type="hidden" name="xm_ZK_vaildDYAmount"
                                       value="${info.zkInfo.xm_ZK_vaildDYAmount}">
                            </div>
                            <div class="layui-col-xs4 s12">
                                <b>总控项目可返金额：<span tag="xm_ZK_vaildFYAmount"><fmt:formatNumber
                                        value="${info.zkInfo.xm_ZK_vaildFYAmount}" pattern="#,##0.00"
                                        maxFractionDigits="2"/></span>元</b>
                                <input type="hidden" name="xm_ZK_vaildFYAmount"
                                       value="${info.zkInfo.xm_ZK_vaildFYAmount}">
                            </div>
                        </div>
                        <div class="layui-row" style="padding: 5px 0px 0px 80px">
                            <div class="layui-col-xs4 s12">
                                <b>总控公司可垫金额：<span tag="com_ZK_vaildDYAmount"><fmt:formatNumber
                                        value="${info.zkInfo.com_ZK_vaildDYAmount}" pattern="#,##0.00"
                                        maxFractionDigits="2"/></span>元</b>
                                <input type="hidden" name="com_ZK_vaildDYAmount"
                                       value="${info.zkInfo.com_ZK_vaildDYAmount}">
                            </div>
                            <div class="layui-col-xs4 s12">
                                <b>总控公司可返金额：<span tag="com_ZK_vaildFYAmount"><fmt:formatNumber
                                        value="${info.zkInfo.com_ZK_vaildFYAmount}" pattern="#,##0.00"
                                        maxFractionDigits="2"/></span>元</b>
                                <input type="hidden" name="com_ZK_vaildFYAmount"
                                       value="${info.zkInfo.com_ZK_vaildFYAmount}">
                            </div>
                        </div>
                        <div class="layui-row" style="padding-bottom: 10px;">
                            <div class="layui-col-xs12" style="padding: 10px 80px">

                                <table id="normalReport" name="normalReport" lay-size="sm"
                                       lay-filter="normalReport"></table>
                            </div>
                        </div>
                    </div>

                    <div tag="offSet"  <c:if test="${info.offSetFlag ne 1 }"> style="display:none" </c:if> >
                        <div class="layui-row">
                            <div class="layui-col-xs12 lable-right" style="padding-left: 40px"><b>冲抵结算：</b>
                                <button class="layui-btn layui-btn-normal layui-btn-sm"
                                        data-type="openDialogOffsetReport">
                                    选择结算订单
                                </button>
                            </div>
                        </div>
                        <div class="layui-row">
                            <div class="layui-col-xs12" style="padding: 10px 80px">
                                <table id="offsetReport" name="offsetReport" lay-size="sm"
                                       lay-filter="offsetReport"></table>
                            </div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs12" style="padding: 5px 0px 0px 80px">
                            <div class="layui-col-xs1">&nbsp;</div>
                            <div class="layui-col-xs11">

                                <div class="layui-col-xs3 s12">
                                    <b>合同约定金额总计：</b><span tag="contractYdAmountTotal">0</span>元
                                    <input type="hidden" name="contractYdAmountTotal"
                                           value="${info.contractYdTotalAmount}">
                                </div>

                                <div class="layui-col-xs3 s12">
                                    <b>本次结算金额总计：</b><span tag="jsAmountTotal">0</span>元
                                    <input type="hidden" name="jsAmountTotal" value="${info.jsTotalAmount}">
                                </div>

                                <div class="layui-col-xs3 s12">
                                    <b>实际开票金额总计：</b><span tag="kpAmountTotal">0</span>元
                                    <input type="hidden" name="kpAmountTotal" value="${info.kpTotalAmount}">
                                </div>
                                <div class="layui-col-xs3 s12">
                                    <b>实际开票税额总计：</b><span tag="kpTaxAmountTotal">0</span>元
                                    <input type="hidden" name="kpTaxAmountTotal" value="${info.kpTotalTaxAmount}">
                                </div>
                            </div>
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
                <div class="layui-row">
                    <div class="layui-upload" id="uploadSuccSaleImg">
                        <div class="layui-col-xs2 lable-left"><i>*</i>成销确认书/佣金结算资料：</div>
                        <div class="layui-col-xs10 lable-right" style="padding: 0px">
                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-upload" id="uploadProjectContractImg">
                        <div class="layui-col-xs2 lable-left"><i>*</i>项目合同：</div>
                        <div class="layui-col-xs10 lable-right" style="padding: 0px">
                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-upload" id="uploadBranchContractImg">
                        <div class="layui-col-xs2 lable-left"><i>*</i>分销合同：</div>
                        <div class="layui-col-xs10 lable-right" style="padding: 0px">
                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-upload" id="uploadOtherImg">
                        <div class="layui-col-xs2 lable-left">其他：</div>
                        <div class="layui-col-xs10 lable-right" style="padding: 0px">

                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row">
                <div class="layui-col-xs12 lable-button" style="padding: 10px 80px">
                    <button class="layui-btn layui-btn-normal" data-type="submit">提交</button>
                    <button class="layui-btn layui-btn-normal" data-type="save">保存</button>
                    <button class="layui-btn layui-btn-primary" data-type="cancel">取消</button>
                </div>
            </div>

        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/jsStatement/addJsStatement.js?v=${vs}"></script>
</body>
</html>
