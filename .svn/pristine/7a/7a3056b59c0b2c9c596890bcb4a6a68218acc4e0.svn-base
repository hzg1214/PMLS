<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>渠道还款计划详情</title>
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
    var hkPlanDto='${hkPlanDto}';
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div style="margin: 0 auto;">
                <div class="layui-row blockBody">
                    <div class="layui-col-xs6">
                        <div class="blockTitle">渠道还款计划详情</div>
                    </div>
                    <div class="layui-col-xs6 blockBtn">
                        <button id="hkButton" type="button" class="layui-btn" onclick="updateHkPlan()">还款</button>
                        <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                    </div>
                </div>

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">编号：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.jyhkplanNo}</div>
                    <div class="layui-col-xs2 lable-left">借佣申请编号：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.jyapplyNo}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">渠道公司：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.companyName}（${hkPlanDto.companyNo}）</div>
                    <div class="layui-col-xs2 lable-left">楼盘名称：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.projectName}（${hkPlanDto.projectNo}）</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">资方：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.zfName}</div>
                    <div class="layui-col-xs2 lable-left">结算金额：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(hkPlanDto.jsTotalAmount)}元</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">借佣比例：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.jyBl*100}%</div>
                    <div class="layui-col-xs2 lable-left">借佣金额：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(hkPlanDto.jyTotalAmount)}元</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">借佣周期：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.jyMonths}个月</div>
                    <div class="layui-col-xs2 lable-left">年利率：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.yearRate*100}%</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">预计利息：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(hkPlanDto.yjInterest)}元</div>
                    <div class="layui-col-xs2 lable-left">预计还款金额：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(hkPlanDto.yjhkAmount)}元</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">借款日：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(hkPlanDto.jkDate)}</div>
                    <div class="layui-col-xs2 lable-left">应还款日：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(hkPlanDto.yhkDate)}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">还款状态：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.planStatusName}</div>
                    <div class="layui-col-xs2 lable-left">实际还款日：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(hkPlanDto.sjhkDate)}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">实际利息：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(hkPlanDto.sjInterest)}元</div>
                    <div class="layui-col-xs2 lable-left">实际还款金额：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(hkPlanDto.sjhkAmount)}元</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">是否逾期：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.yqFlag eq 0 ? '否':'是'}</div>
                    <div class="layui-col-xs2 lable-left">还款类型：</div>
                    <div class="layui-col-xs4 lable-right">${hkPlanDto.hkTypeName}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">备注：</div>
                    <div class="layui-col-xs10 lable-right">${hkPlanDto.remark}</div>
                </div>
            </div>

        </div>
    </div>
    <div class="layui-card interestLayuiCard" >
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>利息</legend>
            </fieldset>

            <div id="interestTable" lay-filter="interestTable"></div>
        </div>
    </div>
    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>借佣房源</legend>
            </fieldset>

            <div id="houseDetailTable" lay-filter="houseDetailTable"></div>
        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/borrowMoney/hkPlanDetailPage.js?v=${vs}"></script>

</body>
</html>
