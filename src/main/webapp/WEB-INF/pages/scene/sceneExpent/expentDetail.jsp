<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>请款详情</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <style type="text/css">
        .lable-left{
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
            width: 200px;
        }
        .lable-right{
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
            width: 300px;
        }
        .layui-tab-item .layui-table thead>tr{
            color:#999999;
        }
        .layui-btn-mini {
            height: 22px;
            line-height: 22px;
            padding: 0 5px;
            font-size: 12px;
        }
        .layui-table-link {
            cursor: pointer;
        }
        
        .expentDetailPage hr {
            background-color:#FFFFFF;
            border-top: 1px dashed #e6e6e6;
        }
        
        .layui-table td, .layui-table th {
            font-size: 12px !important;
        }
    </style>
</head>
<script type="application/javascript">
    var reportDetails='${cashBillInfoJson.reportDetails}';//商户信息
    var fileList=${cashBillInfoJson.fileList};//商户信息
</script>
<body>
<div class="expentDetailPage">
    <div class="layui-card">
        <div class="layui-card-body">
            <div style="margin: 0 auto;">
                <div class="layui-row blockBody">
                    <div class="layui-col-xs6">
                        <div class="blockTitle">请款详情</div>
                    </div>
                    <div class="layui-col-xs6 blockBtn">
                        <button type="button" class="layui-btn layui-btn-primary" onclick="goback()">返回</button>
                    </div>
                </div>

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">请款单编号：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.cashBillNo}</div>
                    <div class="layui-col-xs2 lable-left">结算书编号：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.pjsNostr} </div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">渠道公司：</div>
                    <c:choose>
                        <c:when test="${!empty cashBillInfo.companyNo}">
                            <div class="layui-col-xs4 lable-right">${cashBillInfo.companyName} (${cashBillInfo.companyNo})</div>
                        </c:when>
                        <c:otherwise>
                            <div class="layui-col-xs4 lable-right"></div>
                        </c:otherwise>
                    </c:choose>
					<div class="layui-col-xs2 lable-left">项目名称：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.estateNm} &nbsp; (${cashBillInfo.projectNo})</div>
<!--                     <div class="layui-col-xs2 lable-left">不含税请款金额：</div> -->
<%--                     <c:choose> --%>
<%--                         <c:when test="${empty cashBillInfo.amountNoTax}"> --%>
<!--                             <div class="layui-col-xs4 lable-right">-</div> -->
<%--                         </c:when> --%>
<%--                         <c:otherwise> --%>
<%--                             <div class="layui-col-xs4 lable-right">￥<fmt:formatNumber type="number" value="${cashBillInfo.amountNoTax}" pattern="0.00" maxFractionDigits="2"/></div> --%>
<%--                         </c:otherwise> --%>
<%--                     </c:choose> --%>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">核算主体：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.accountProjectNo}_${cashBillInfo.accountProject}</div>
                    <div class="layui-col-xs2 lable-left">合同编号：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.frameOaNo}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">供应商：</div>
                    <div class="layui-col-xs4 lable-right">
                        <c:choose>
                            <c:when test="${!empty cashBillInfo.vendorCode}">
                                ${cashBillInfo.vendorName}&nbsp;(${cashBillInfo.vendorCode})
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </div>



                    <div class="layui-col-xs2 lable-left">收款银行：</div>
                    <div class="layui-col-xs4 lable-right">
                        ${cashBillInfo.receiveBankName}
                    </div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">银行账号：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.receiveBankAccountCardCode}</div>
                    <div class="layui-col-xs2 lable-left">收款户名：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.receiveBankAccountName}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">收款省市：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.receiveBankProvinceName}&nbsp;${cashBillInfo.receiveBankCityName}</div>
                    <div class="layui-col-xs2 lable-left">入账日期：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(cashBillInfo.recordDate)}</div>
                </div>

				<div class="layui-row">
                    <div class="layui-col-xs2 lable-left">请款金额：</div>
                    <c:choose>
                        <c:when test="${empty cashBillInfo.requestAmountTotal}">
                            <div class="layui-col-xs4 lable-right">-</div>
                        </c:when>
                        <c:otherwise>
                            <div class="layui-col-xs4 lable-right"><fmt:formatNumber type="number" value="${cashBillInfo.requestAmountTotal}" pattern="0.00" maxFractionDigits="2"/>元</div>
                        </c:otherwise>
                    </c:choose>
                    <div class="layui-col-xs2 lable-left">税额：</div>
                    <c:choose>
                        <c:when test="${empty cashBillInfo.taxAmountTotal}">
                            <div class="layui-col-xs4 lable-right">-</div>
                        </c:when>
                        <c:otherwise>
                            <div class="layui-col-xs4 lable-right"><fmt:formatNumber type="number" value="${cashBillInfo.taxAmountTotal}" pattern="0.00" maxFractionDigits="2"/>元</div>
                        </c:otherwise>
                    </c:choose>
                </div>
                
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">预计付款日期：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(cashBillInfo.predictPayTime)}</div>
                    <div class="layui-col-xs2 lable-left">付款方式：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.payTypeNm}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">提交OA状态：</div>

                    <c:choose>
                        <c:when test="${cashBillInfo.submitOaStatus eq '21204' || cashBillInfo.submitOAStatus eq '21204'}">
                            <div class="layui-col-xs4 lable-right" style="color:red;">${cashBillInfo.submitOaStatusNm}</div>
                        </c:when>
                        <c:otherwise>
                            <div class="layui-col-xs4 lable-right">${cashBillInfo.submitOaStatusNm}</div>
                        </c:otherwise>
                    </c:choose>


                    <c:choose>
                        <c:when test="${not empty cashBillInfo.oaNo}">
                            <div class="layui-col-xs2 lable-left">OA编号：</div>
                            <div class="layui-col-xs4 lable-right">${cashBillInfo.oaNo}</div>
                        </c:when>
                        <c:otherwise>
                            <div class="layui-col-xs2 lable-left"></div>
                            <div class="layui-col-xs4 lable-right"></div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">申请人：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.userName}</div>
                    <div class="layui-col-xs2 lable-left">申请日期：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(cashBillInfo.applyTime)}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">审核状态：</div>
                    <div class="layui-col-xs4 lable-right">${cashBillInfo.approveStatusNm}</div>
                    <div class="layui-col-xs2 lable-left">审核通过日期：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(cashBillInfo.approveTime)}</div>
                </div>
            </div>

        </div>
    </div>

    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>请款订单</legend>
            </fieldset>
            <div class="layui-tab layui-tab-brief">
                <div class="layui-tab-content" style="min-height: 100px;">
                    <table id="contentTable" lay-size="sm" lay-filter="content"></table>
                </div>
            </div>
        </div>
<!--         <div class="layui-card-body"> -->
<!--             <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;"> -->
<!--                 <legend>冲抵请款订单</legend> -->
<!--             </fieldset> -->
<!--             <div class="layui-tab layui-tab-brief"> -->
<!--                 <div class="layui-tab-content" style="min-height: 100px;"> -->
<!--                     <table id="offSetContentTable" lay-size="sm" lay-filter="offSetContent"></table> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
    </div>

    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>附件</legend>
            </fieldset>
            <div class="fileContent" id="upload_cxImg_list" style="display: none">
                <div style="margin-left: 50px;">
                    <div>成销确认书/佣金结算资料</div><hr/>
                </div>
                <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:50px;">
<%--                     <c:forEach items="${cashBillInfo.cashBillFileList}" var="file"> --%>
<!--                         <dd class="item_img"> -->
<%--                             <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" > --%>
<%--                             <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" /> --%>
<!--                         </dd> -->
<%--                     </c:forEach> --%>
                </div>
            </div>
            <div class="fileContent" id="upload_fpImg_list">
                <div style="margin-left: 50px;">
                    <div>发票</div><hr/>
                </div>
                <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:50px;">
                </div>
            </div>
            <div class="fileContent" id="upload_qtImg_list">
                <div style="margin-left: 50px;">
                    <div>其他</div><hr/>
                </div>
                <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:50px;">
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${ctx}/meta/pmls/js/scene/sceneExpent/expentDetail.js?v=${vs}"></script>

</body>
</html>
