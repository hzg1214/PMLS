<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<c:if test="${fn:length(contentlist) gt 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<table class="table table-striped table-hover table-bordered" style="font-size: 12px;">
    <tbody class="s-w100">
    <tr>
        <th rowspan="3" style="vertical-align: middle;width: 45px;">序号</th>
        <th rowspan="3" style="vertical-align: middle;width: 100px;">项目归属区域</th>
        <th rowspan="3" style="vertical-align: middle;width: 100px;">项目归属城市</th>
        <th rowspan="3" style="vertical-align: middle;width: 100px;">项目所在城市</th>
        <th rowspan="3" style="vertical-align: middle;width: 100px;">项目归属部门</th>
        <th rowspan="3" style="vertical-align: middle;width: 100px;">报备归属城市</th>
        <c:if test="${searchType ne '1'}">
        <th rowspan="3" style="vertical-align: middle;width: 100px;">报备归属中心</th>
        </c:if>
        <th rowspan="3" style="vertical-align: middle;width: 200px;">楼盘名</th>
        <th rowspan="3" style="vertical-align: middle;width: 60px;">城市</th>
        <th rowspan="3" style="vertical-align: middle;width: 80px;">区域</th>
        <th rowspan="3" style="vertical-align: middle;width: 180px;">地址</th>
        <th rowspan="3" style="vertical-align: middle;width: 130px;">项目编号</th>
        <th rowspan="3" style="vertical-align: middle;width: 120px;">合作类型</th>
        <th rowspan="3" style="vertical-align: middle;width: 180px;">合作方名称</th>
        <th rowspan="3" style="vertical-align: middle;width: 60px;">是否大客户</th>
        <th rowspan="3" style="vertical-align: middle;width: 60px;">大客户简称</th>
        <th rowspan="3" style="vertical-align: middle;width: 60px;">是否独家</th>
        <th rowspan="3" style="vertical-align: middle;width: 60px;">是否直签</th>
        <th rowspan="3" style="vertical-align: middle;width: 60px;">垫佣金额</th>
        <th rowspan="3" style="vertical-align: middle;width: 100px;">合作周期</th>
        <th rowspan="3" style="vertical-align: middle;width: 80px;">项目状态</th>
        <th rowspan="3" style="vertical-align: middle;width: 100px;">物业类型</th>
        <th rowspan="3" style="vertical-align: middle;width: 80px;">合作模式</th>
        <th colspan="12" style="width: 1200px;">当期实际(不含结转)</th>
        <th colspan="12" style="width: 1200px;">总累计</th>
    </tr>
    <tr>
        <th colspan="3" style="width: 220px;">大定</th>
        <th colspan="3" style="width: 220px;">成销</th>
        <th colspan="3">应计收入（税前）</th>
        <th colspan="3">实际回款</th>
        <th colspan="3">大定</th>
        <th colspan="3">成销</th>
        <th colspan="3">应计收入（税前）</th>
        <th colspan="3">实际回款</th>
    </tr>
    <tr>
        <th style="width: 50px;">套数</th>
        <th style="width: 50px;">面积</th>
        <th style="width: 120px;">金额</th>
        <th>套数</th>
        <th>面积</th>
        <th>金额</th>
        <th>收入</th>
        <th>返佣</th>
        <th>净收</th>
        <th>收入</th>
        <th>返佣</th>
        <th>净收</th>

        <th>套数</th>
        <th>面积</th>
        <th>金额</th>
        <th>套数</th>
        <th>面积</th>
        <th>金额</th>
        <th>收入</th>
        <th>返佣</th>
        <th>净收</th>
        <th>收入</th>
        <th>返佣</th>
        <th>净收</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr class="J_eatateItem" data-hidenumberflg="0">
            <td style="vertical-align: middle">${list.rowNum}</td>
            <td style="vertical-align: middle">${list.regionName}</td>
            <td style="vertical-align: middle">${list.areaCityName}</td>
            <td style="vertical-align: middle">${list.cityGroupName}</td>
            <td style="vertical-align: middle">${list.centerGroupName}</td>
            <td style="vertical-align: middle">${list.performCityName}</td>
            <c:if test="${searchType ne '1'}">
            <td style="vertical-align: middle">${list.performCenterName}</td>
            </c:if>
            <td style="vertical-align: middle">${list.estateNm}</td>
            <td style="vertical-align: middle">${list.cityName}</td>
            <td style="vertical-align: middle">${list.districtName}</td>
            <td style="text-align:center;" title="${list.address}">
                    ${fn:substring(list.address, 0, 10)}
                <c:if test="${fn:length(list.address) >= '10'}">
                    ...
                </c:if>
            </td>
            <td style="vertical-align: middle">${list.projectNo}</td> 
              <td style="vertical-align: middle">${list.partnerName}</td>
            <td style="text-align:center;" title="${list.partnerNm}">
                    ${fn:substring(list.partnerNm, 0, 10)}
                <c:if test="${fn:length(list.partnerNm) >= '10'}">
                    ...
                </c:if>
            </td>
            <td style="vertical-align: middle">${list.bigCustomerFlagVakue}</td>
            <td style="vertical-align: middle">${list.bigCustomerName}</td>
            <td style="vertical-align: middle">${list.particularFlagVakue}</td>
            <td style="vertical-align: middle">${list.directSignFlagVakue}</td> 
            <td style="vertical-align: middle">${list.yjdyAmount}</td> 
            <td style="vertical-align: middle">${list.estateCycle}</td> 
            
            <td style="vertical-align: middle">${list.projectStatusName}</td>
            <td style="vertical-align: middle">${list.mgtKbnName}</td>
            <td style="vertical-align: middle">${list.cooperationModeName}</td>
         
            <td style="vertical-align: middle">${list.curRoughNum}</td>
            <td style="vertical-align: middle">${list.curRoughArea}</td>
            <td style="vertical-align: middle">${list.curRoughAmount}</td>
            <td style="vertical-align: middle">${list.curDealNum}</td>
            <td style="vertical-align: middle">${list.curDealArea}</td>
            <td style="vertical-align: middle">${list.curDealAmount}</td>
            <td style="vertical-align: middle">${list.curEPTIncome}</td>
            <td style="vertical-align: middle">${list.curEPTCommission}</td>
            <td style="vertical-align: middle">${list.curEPTProfit}</td>
            <td style="vertical-align: middle">${list.curAPTIncome}</td>
            <td style="vertical-align: middle">${list.curAPTCommission}</td>
            <td style="vertical-align: middle">${list.curAPTProfit}</td>
            <td style="vertical-align: middle">${list.accRoughNum}</td>
            <td style="vertical-align: middle">${list.accRoughArea}</td>
            <td style="vertical-align: middle">${list.accRoughAmount}</td>
            <td style="vertical-align: middle">${list.accDealNum}</td>
            <td style="vertical-align: middle">${list.accDealArea}</td>
            <td style="vertical-align: middle">${list.accDealAmount}</td>
            <td style="vertical-align: middle">${list.accEPTIncome}</td>
            <td style="vertical-align: middle">${list.accEPTCommission}</td>
            <td style="vertical-align: middle">${list.accEPTProfit}</td>
            <td style="vertical-align: middle">${list.accAPTIncome}</td>
            <td style="vertical-align: middle">${list.accAPTCommission}</td>
            <td style="vertical-align: middle">${list.accAPTProfit}</td>
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
