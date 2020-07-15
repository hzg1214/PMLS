<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>退成销</title>
    <%@include file="../common/common.jsp" %>
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

    </style>
</head>
<script type="application/javascript">

</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">退成销</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>基本信息</legend>
            </fieldset>


            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><b>项目编号：</b></div>
                <div class="layui-col-xs4 lable-right"><b>${qtReport.estate.projectNo}</b></div>
                <div class="layui-col-xs2 lable-left"><b>楼盘名称：</b></div>
                <div class="layui-col-xs4 lable-right"><b>${qtReport.estateNm}</b></div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">合作方：</div>
                <div class="layui-col-xs4 lable-right">${qtReport.estate.partnerNm}</div>
                <div class="layui-col-xs2 lable-left"></div>
                <div class="layui-col-xs4 lable-right"></div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">成销金额：</div>
                <div class="layui-col-xs4 lable-right">${sdk:mf2(qtReport.dealAmount)}元</div>
                <div class="layui-col-xs2 lable-left">成销日期：</div>
                <div class="layui-col-xs4 lable-right">${sdk:ymd2(qtReport.dealDate)}</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">应计收入税前：</div>
                <div class="layui-col-xs4 lable-right">${sdk:mf2(qtReport.srAmount)}元</div>
                <div class="layui-col-xs2 lable-left">应计收入税后：</div>
                <div class="layui-col-xs4 lable-right">${sdk:mf2(qtReport.aftYJSRAmount)}元</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">应计返佣税前：</div>
                <div class="layui-col-xs4 lable-right">${sdk:mf2(qtReport.befYJFYAmount)}元</div>
                <div class="layui-col-xs2 lable-left">应计返佣税后：</div>
                <div class="layui-col-xs4 lable-right">${sdk:mf2(qtReport.aftYJFYAmount)}元</div>
            </div>




            <div class="layui-form myForm" style="margin-top:20px;">
                <input type="hidden" id="id" name="id" value="${qtReport.id}">
                <input type="hidden" id="dealDate" value="${sdk:ymd2(qtReport.dealDate)}">
                <input type="hidden" id="befYJSSAmount" value="${qtReport.befYJSSAmount}">
                <input type="hidden" id="aftYJSSAmount" value="${qtReport.aftYJSSAmount}">
                <input type="hidden" id="befSJFYAmount" value="${qtReport.befSJFYAmount}">
                <input type="hidden" id="aftSJFYAmount" value="${qtReport.aftSJFYAmount}">
                <input type="hidden" id="countDateEndStr" value="${countDateEndStr}">


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 170px;"><span class="redSpan">*</span>成销日期</label>
                    <div class="layui-input-block" style="margin-left: 200px;">
                        <input type="text" id="backDealDate" name="backDealDate" lay-verify="required"
                               autocomplete="off" lay-filter="backDealDate" class="layui-input" placeholder="请选择">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 170px;"><span class="redSpan">*</span>原因</label>
                    <div class="layui-input-block" style="margin-left: 200px;">
                        <textarea placeholder="请输入" id="memo" name="memo" rows="10" class="layui-textarea"></textarea>
                    </div>
                </div>

            </div>

            <div class="operationPageToolbar" style="padding-top: 50px;">
                <button type="button" class="layui-btn" onclick="qtReportBackSale()">提交</button>
                <button type="button" class="layui-btn layui-btn-primary" onclick="back()">取消</button>
            </div>


        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/otherReport/qtDealBack.js?v=${vs}"></script>
</body>
</html>
