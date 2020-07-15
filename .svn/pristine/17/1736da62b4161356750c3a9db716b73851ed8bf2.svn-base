<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<style type="text/css">
    .text-overflow {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 100%;
    }
</style>
<c:if test="${fn:length(contentlist) gt 0}"><div id="divProjectTrace" class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div id="divProjectTrace" class="s-scoll-y" style="height: auto;"></c:if>
<table id="tblProjectTrace" class="table table-striped table-hover table-bordered" style="font-size: 12px;width:4380px;">
    <colgroup>
        <col style="width:100px"><%--城市--%>
        <col style="width:100px"><%--项目编号--%>
        <col style="width:150px"><%--项目名称--%>
        <col style="width:150px"><%--甲方名称--%>
        <col style="width:150px"><%--开发商名称--%>
        <%--概况--%>
        <col style="width:120px"><%--PMLS建项日期--%>
        <col style="width:120px"><%--销售状态--%>
        <col style="width:120px"><%--项目状态--%>
        <col style="width:120px"><%--OA立项完成日期--%>
        <col style="width:120px"><%--甲方结算节点--%>
        <%--最新预计（实&预）--%>
        <col style="width:120px"><%--大定金额--%>
        <col style="width:120px"><%--成销金额--%>
        <col style="width:120px"><%--税后收入--%>
        <col style="width:120px"><%--税后返佣--%>
        <col style="width:120px"><%--税前净收--%>
        <col style="width:120px"><%--税后净收--%>
        <col style="width:120px"><%--营业费用-内佣--%>
        <col style="width:120px"><%--营业费用-销售成本--%>
        <col style="width:120px"><%--营业费用-工资+奖金--%>
        <col style="width:120px"><%--预计利润--%>
        <%--当年实际--%>
        <col style="width:120px"><%--大定金额--%>
        <col style="width:120px"><%--成销金额--%>
        <col style="width:120px"><%--税后收入--%>
        <col style="width:120px"><%--实收--%>
        <col style="width:120px"><%--财务实收--%>
        <col style="width:120px"><%--税后返佣--%>
        <col style="width:120px"><%--税前净收--%>
        <col style="width:120px"><%--税后净收--%>
        <col style="width:120px"><%--营业费用-内佣--%>
        <col style="width:120px"><%--营业费用-销售成本--%>
        <col style="width:120px"><%--营业费用-工资+奖金--%>
        <col style="width:120px"><%--实际利润--%>
        <%--累计实际--%>
        <col style="width:120px"><%--实际大定套数--%>
        <col style="width:120px"><%--实际大定金额--%>
        <col style="width:120px"><%--实际成销套数--%>
        <col style="width:120px"><%--实际成销金额--%>
        <col style="width:120px"><%--应计收入税前--%>
        <col style="width:120px"><%--应计收入税后--%>
        <col style="width:120px"><%--税前净收--%>
        <col style="width:120px"><%--税后净收--%>
        <col style="width:120px"><%--应收收入--%>
        <col style="width:120px"><%--应计实收--%>
        <col style="width:120px"><%--财务实收--%>
        <col style="width:120px"><%--应计返佣--%>
        <col style="width:120px"><%--实际返佣--%>
        <col style="width:120px"><%--应计内佣--%>
        <col style="width:120px"><%--实际营业费用--%>
        <col style="width:120px"><%--实际项目利润--%>
        <col style="width:120px"><%--实际项目利润率--%>
        <%--垫佣跟踪--%>
        <col style="width:120px"><%--是否垫佣--%>
        <col style="width:120px"><%--前三个月回款金额--%>
        <col style="width:300px"><%--垫佣方案--%>
        <col style="width:120px"><%--实际垫佣--%>
        <col style="width:120px"><%--应计垫佣--%>
        <col style="width:120px"><%--实际垫佣已回款--%>
        <col style="width:120px"><%--实际垫佣未回款--%>
        <col style="width:120px"><%--应收垫佣--%>
        <col style="width:120px"><%--应收垫佣已回款--%>
        <col style="width:120px"><%--应收垫佣未回款--%>
        <%--账龄跟踪--%>
        <col style="width:120px"><%--应计未回款 账龄合计--%>
        <col style="width:120px"><%--应计未回款 1-3个月--%>
        <col style="width:120px"><%--应计未回款 4-6个月--%>
        <col style="width:120px"><%--应计未回款 7-9个月--%>
        <col style="width:120px"><%--应计未回款 10-12个月--%>
        <col style="width:120px"><%--应计未回款 一年以上--%>
        <col style="width:120px"><%--应收未回款（含已开票）账龄合计--%>
        <col style="width:120px"><%--应收未回款（含已开票）1-3个月--%>
        <col style="width:120px"><%--应收未回款（含已开票）4-6个月--%>
        <col style="width:120px"><%--应收未回款（含已开票）7-9个月--%>
        <col style="width:120px"><%--应收未回款（含已开票）10-12个月--%>
        <col style="width:120px"><%--应收未回款（含已开票）一年以上--%>

        <%--开票--%>
        <col style="width:120px">
        <col style="width:120px">
        <col style="width:120px">
        <col style="width:120px">
        <col style="width:120px">
        <col style="width:120px">
        <col style="width:120px">


        <col style="width:1px">
    </colgroup>
    <tbody class="s-w100">
    <tr>
        <th rowspan="3" style="vertical-align: middle;width: 100px;">城市</th>
        <th rowspan="3" style="vertical-align: middle;width: 100px;">项目编号</th>
        <th rowspan="3" style="vertical-align: middle;width: 150px;">项目名称</th>
        <th rowspan="3" style="vertical-align: middle;width: 150px;">甲方名称</th>
        <th rowspan="3" style="vertical-align: middle;width: 150px;">开发商名称</th>
        <th colspan="5" style="width: 600px;">概况</th>
        <th colspan="10" style="width: 1200px;">最新预计（实&预）</th>
        <th colspan="12" style="width: 1440px;">当年实际</th>
 <%--         <th colspan="16" style="width: 1920px;">累计实际</th> --%>
        <th colspan="17" style="width: 1920px;">累计实际</th>
        <th colspan="10" style="width: 1380px;">垫佣跟踪</th>
        <th colspan="12" style="width: 1200px;">回款账龄分析(${endDate})</th>
        <th rowspan="2" colspan="7">开票账龄(${endDate})</th>
    </tr>
    <tr>
        <th rowspan="2" style="vertical-align: middle;">PMLS建项日期</th>
        <th rowspan="2" style="vertical-align: middle;">销售状态</th>
        <th rowspan="2" style="vertical-align: middle;">项目状态</th>
        <th rowspan="2" style="vertical-align: middle;">OA立项完成日期</th>
        <th rowspan="2" style="vertical-align: middle;">甲方结算节点</th>

        <th rowspan="2" style="vertical-align: middle;">大定金额</th>
        <th rowspan="2" style="vertical-align: middle;">成销金额</th>
        <th rowspan="2" style="vertical-align: middle;">税后收入</th>
        <th rowspan="2" style="vertical-align: middle;">税后返佣</th>
        <th rowspan="2" style="vertical-align: middle;">税前净收</th>
        <th rowspan="2" style="vertical-align: middle;">税后净收</th>
        <th colspan="3" style="width: 220px;">营业费用</th>
        <th rowspan="2" style="vertical-align: middle;">预计利润</th>

        <th rowspan="2" style="vertical-align: middle;">大定金额</th>
        <th rowspan="2" style="vertical-align: middle;">成销金额</th>
        <th rowspan="2" style="vertical-align: middle;">税后收入</th>
        <th rowspan="2" style="vertical-align: middle;">实收</th>
        <th rowspan="2" style="vertical-align: middle;">财务实收</th>
        <th rowspan="2" style="vertical-align: middle;">税后返佣</th>
        <th rowspan="2" style="vertical-align: middle;">税前净收</th>
        <th rowspan="2" style="vertical-align: middle;">税后净收</th>
        <th colspan="3" style="width: 220px;">营业费用</th>
        <th rowspan="2" style="vertical-align: middle;">实际利润</th>

        <th rowspan="2" style="vertical-align: middle;">实际大定套数</th>
        <th rowspan="2" style="vertical-align: middle;">实际大定金额</th>
        <th rowspan="2" style="vertical-align: middle;">实际成销套数</th>
        <th rowspan="2" style="vertical-align: middle;">实际成销金额</th>
        <th rowspan="2" style="vertical-align: middle;">应计收入税前</th>
        <th rowspan="2" style="vertical-align: middle;">应计收入税后</th>
        <th rowspan="2" style="vertical-align: middle;">税前净收</th>
        <th rowspan="2" style="vertical-align: middle;">税后净收</th>
        <th rowspan="2" style="vertical-align: middle;">应收收入</th>
        <th rowspan="2" style="vertical-align: middle;">应计实收</th>
        <th rowspan="2" style="vertical-align: middle;">财务实收</th>
        <th rowspan="2" style="vertical-align: middle;">应计返佣</th>
        <th rowspan="2" style="vertical-align: middle;">实际返佣</th>
        <th rowspan="2" style="vertical-align: middle;">应计内佣</th>
        <th rowspan="2" style="vertical-align: middle;">实际营业费用</th>
        <th rowspan="2" style="vertical-align: middle;">实际项目利润</th>
        <th rowspan="2" style="vertical-align: middle;">实际项目利润率</th>

        <th rowspan="2" style="vertical-align: middle;">是否垫佣</th>
        <th rowspan="2" style="vertical-align: middle;">前三个月回款金额</th>
        <th rowspan="2" style="vertical-align: middle;">垫佣方案</th>
        <th rowspan="2" style="vertical-align: middle;">实际垫佣</th>
        <th rowspan="2" style="vertical-align: middle;">应计垫佣</th>
        <th rowspan="2" style="vertical-align: middle;">实际垫佣已回款</th>
        <th rowspan="2" style="vertical-align: middle;">实际垫佣未回款</th>
        <th rowspan="2" style="vertical-align: middle;">应收垫佣</th>
        <th rowspan="2" style="vertical-align: middle;">应收垫佣已回款</th>
        <th rowspan="2" style="vertical-align: middle;">应收垫佣未回款</th>

        <th colspan="6">应计未回款</th>
        <th colspan="6">应收未回款（含已开票）</th>
    </tr>
    <tr>
        <th>内佣</th>
        <th>销售成本</th>
        <th>工资+奖金</th>

        <th>内佣</th>
        <th>销售成本</th>
        <th>工资+奖金</th>

        <th>账龄合计</th>
        <th>1-3个月</th>
        <th>4-6个月</th>
        <th>7-9个月</th>
        <th>10-12个月</th>
        <th>一年以上</th>

        <th>账龄合计</th>
        <th>1-3个月</th>
        <th>4-6个月</th>
        <th>7-9个月</th>
        <th>10-12个月</th>
        <th>一年以上</th>

        <th>账龄合计</th>
        <th>1-3个月</th>
        <th>4-6个月</th>
        <th>7-9个月</th>
        <th>10-12个月</th>
        <th>13-24个月</th>
        <th>24个月以上</th>

    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr class="J_eatateItem" data-hidenumberflg="0">
            <td style="vertical-align: middle">${list.cityName}</td><%--城市--%>
            <td style="vertical-align: middle">${list.projectNo}</td><%--项目编号--%>
            <td style="vertical-align: middle" class="text-overflow" title="${list.projectName}">${list.projectName}</td><%--项目名称--%>
            <td style="vertical-align: middle" class="text-overflow" title="${list.partnerNm}">${list.partnerNm}</td><%--甲方名称--%>
            <td style="vertical-align: middle" class="text-overflow" title="${list.devCompany}">${list.devCompany}</td><%--开发商名称--%>
                <%--概况--%>
            <td style="vertical-align: middle">${list.projectCrtDt}</td><%--PMLS建项日期--%>
            <td style="vertical-align: middle">${list.saleStatusNm}</td><%--销售状态--%>
            <td style="vertical-align: middle">${list.signStatusNm}</td><%--项目状态--%>
            <td style="vertical-align: middle">${list.budgetDate}</td><%--OA立项完成日期--%>
            <td style="vertical-align: middle">${list.commissionConditionNm}</td><%--甲方结算节点--%>
                <%--最新预计（实&预）--%>
            <td style="vertical-align: middle">${list.abaRoughAmt}</td><%--大定金额--%>
            <td style="vertical-align: middle">${list.abaDealAmt}</td><%--成销金额--%>
            <td style="vertical-align: middle">${list.abaAtIncome}</td><%--税后收入--%>
            <td style="vertical-align: middle">${list.abaAtRebate}</td><%--税后返佣--%>
            <td style="vertical-align: middle">${list.abaPtNetIncome}</td><%--税前净收--%>
            <td style="vertical-align: middle">${list.abaAtNetIncome}</td><%--税后净收--%>
            <td style="vertical-align: middle">${list.abaBusInnerCom}</td><%--营业费用-内佣--%>
            <td style="vertical-align: middle">${list.abaBusSaleCost}</td><%--营业费用-销售成本--%>
            <td style="vertical-align: middle">${list.abaBusWage}</td><%--营业费用-工资+奖金--%>
            <td style="vertical-align: middle">${list.abaProfit}</td><%--预计利润--%>
                <%--当年实际--%>
            <td style="vertical-align: middle">${list.actRoughAmt}</td><%--大定金额--%>
            <td style="vertical-align: middle">${list.actDealAmt}</td><%--成销金额--%>
            <td style="vertical-align: middle">${list.actAtIncome}</td><%--税后收入--%>
            <td style="vertical-align: middle">${list.actRealIncome}</td><%--实收--%>
            <td style="vertical-align: middle">${list.actFinanceIncome}</td><%--财务实收--%>
            <td style="vertical-align: middle">${list.actAtRebate}</td><%--税后返佣--%>
            <td style="vertical-align: middle">${list.actPtNetIncome}</td><%--税前净收--%>
            <td style="vertical-align: middle">${list.actAtNetIncome}</td><%--税后净收--%>
            <td style="vertical-align: middle">${list.actBusInnerCom}</td><%--营业费用-内佣--%>
            <td style="vertical-align: middle">${list.actBusSaleCost}</td><%--营业费用-销售成本--%>
            <td style="vertical-align: middle">${list.actBusWage}</td><%--营业费用-工资+奖金--%>
            <td style="vertical-align: middle">${list.actRealProfit}</td><%--实际利润--%>
                <%--累计实际--%>
            <td style="vertical-align: middle">${list.totalRoughNum}</td><%--实际大定套数--%>
            <td style="vertical-align: middle">${list.totalRoughAmt}</td><%--实际大定金额--%>
            <td style="vertical-align: middle">${list.totalDealNum}</td><%--实际成销套数--%>
            <td style="vertical-align: middle">${list.totalDealAmt}</td><%--实际成销金额--%>
            <td style="vertical-align: middle">${list.totalIncomesq}</td><%--应计收入税前--%>
            <td style="vertical-align: middle">${list.totalIncome}</td><%--应计收入税后--%>
            <td style="vertical-align: middle">${list.totalPtNetIncome}</td><%--税前净收--%>
            <td style="vertical-align: middle">${list.totalAtNetIncome}</td><%--税后净收--%>
            <td style="vertical-align: middle">${list.totalRecieveIncome}</td><%--应收收入--%>
            <td style="vertical-align: middle">${list.totalRecieveRealIncome}</td><%--应计实收--%>
            <td style="vertical-align: middle">${list.totalFinanceIncome}</td><%--财务实收--%>
            <td style="vertical-align: middle">${list.totalRecieveRebate}</td><%--应计返佣--%>
            <td style="vertical-align: middle">${list.totalRealRecieveRebate}</td><%--实际返佣--%>
            <td style="vertical-align: middle">${list.totalRecieveInnerCom}</td><%--应计内佣--%>
            <td style="vertical-align: middle">${list.totalRealBusFee}</td><%--实际营业费用--%>
            <td style="vertical-align: middle">${list.totalRealProfit}</td><%--实际项目利润--%>
            <td style="vertical-align: middle">${list.totalRealProfitRate}</td><%--实际项目利润率--%>
                <%--垫佣跟踪--%>
            <td style="vertical-align: middle">${list.isPadCom}</td><%--是否垫佣--%>
            <td style="vertical-align: middle">${list.pre3Amt}</td><%--前三个月回款金额--%>
            <td style="vertical-align:middle;text-align: left;" title="${list.padComRule}"><%--垫佣方案--%>
                ${fn:substring(list.padComRule, 0, 30)}
                <c:if test="${fn:length(list.padComRule) > '30'}">
                    ...
                </c:if>
            </td>
            <td style="vertical-align: middle">${list.realPadComAmt}</td><%--实际垫佣--%>
            <td style="vertical-align: middle">${list.accruedPadComAmt}</td><%--应计垫佣--%>
            <td style="vertical-align: middle">${list.padComBack}</td><%--实际垫佣已回款--%>
            <td style="vertical-align: middle">${list.padComUnBack}</td><%--实际垫佣未回款--%>
            <td style="vertical-align: middle">${list.receivePadComAmt}</td><%--应收垫佣 todo--%>
            <td style="vertical-align: middle">${list.receivePadComBack}</td><%--应收垫佣已回款 todo--%>
            <td style="vertical-align: middle">${list.receivePadComUnBack}</td><%--应收垫佣未回款 todo--%>
                <%--账龄跟踪--%>
            <td style="vertical-align: middle">${list.yjAmountAll}</td><%--应计未回款 账龄合计--%>
            <td style="vertical-align: middle">${list.yjAmount13}</td><%--应计未回款 1-3个月--%>
            <td style="vertical-align: middle">${list.yjAmount46}</td><%--应计未回款 4-6个月--%>
            <td style="vertical-align: middle">${list.yjAmount79}</td><%--应计未回款 7-9个月--%>
            <td style="vertical-align: middle">${list.yjAmount1012}</td><%--应计未回款 10-12个月--%>
            <td style="vertical-align: middle">${list.yjAmount1399}</td><%--应计未回款 一年以上--%>
            <td style="vertical-align: middle">${list.ysAmountAll}</td><%--应收未回款（含已开票）账龄合计--%>
            <td style="vertical-align: middle">${list.ysAmount13}</td><%--应收未回款（含已开票）1-3个月--%>
            <td style="vertical-align: middle">${list.ysAmount46}</td><%--应收未回款（含已开票）4-6个月--%>
            <td style="vertical-align: middle">${list.ysAmount79}</td><%--应收未回款（含已开票）7-9个月--%>
            <td style="vertical-align: middle">${list.ysAmount1012}</td><%--应收未回款（含已开票）10-12个月--%>
            <td style="vertical-align: middle">${list.ysAmount1399}</td><%--应收未回款（含已开票）一年以上--%>

            <td style="vertical-align: middle">${list.kpAmountAll}</td>
            <td style="vertical-align: middle">${list.kpAmount13}</td>
            <td style="vertical-align: middle">${list.kpAmount46}</td>
            <td style="vertical-align: middle">${list.kpAmount79}</td>
            <td style="vertical-align: middle">${list.kpAmount1012}</td>
            <td style="vertical-align: middle">${list.kpAmount1324}</td>
            <td style="vertical-align: middle">${list.kpAmount2499}</td>
        </tr>
    </c:forEach>
</table>
</div>

${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
    <div class="nodata">
        <i class="icon-hdd"></i>
        <p>暂无数据...</p>
    </div>
</c:if>
