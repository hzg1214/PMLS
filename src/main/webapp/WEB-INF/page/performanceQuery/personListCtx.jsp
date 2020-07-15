<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto; overflow-x:scroll;">
<table class="table table-striped table-hover table-bordered" style="width:2070px;font-size: 12px;">
    <tbody class="s-w100">
    <tr>
        <th rowspan="2" style="vertical-align: middle;width:100px;">归属区域</th>
        <th rowspan="2" style="vertical-align: middle;width:100px;">归属城市</th>
        <th rowspan="2" style="vertical-align: middle;width:100px;">所在城市</th>
        <th rowspan="2" style="vertical-align: middle;width:100px;">中心</th>
        <th rowspan="2" style="vertical-align: middle;width:60px;">姓名</th>
        <th rowspan="2" style="vertical-align: middle;width:60px;">工号</th>
        <th colspan="3" style="vertical-align: middle;width:210px;">维护门店</th>
        <th colspan="6" style="vertical-align: middle;width:420px;">拓展</th>
        <th colspan="4" style="vertical-align: middle;width:320px;">交易</th>
        <th colspan="6" style="vertical-align: middle;width:600px;">联动</th>
    </tr>
    <tr>
        <th>门店数</th>
        <th>签约B</th>
        <th>翻牌验收</th>
        <th>草签B</th>
        <th>签约B</th>
        <th>甲类</th>
        <th>装修完成</th>
        <th>翻牌验收</th>
        <th>续签</th>
        <th>报单</th>
        <th>网签审核</th>
        <th>不动产受理</th>
        <th>办结</th>
        <th>报备</th>
        <th>带看</th>
        <th>大定套数</th>
        <th>大定金额</th>
        <th>成销套数</th>
        <th>成销金额</th>
    </tr>

    <c:forEach items="${contentList}" var="list">
    <tr class="J_eatateItem" data-hidenumberflg="0">
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.regionName}</td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.areaCityName}</td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.cityName}</td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.centerName}</td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.maintainerName}</td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.maintainerCode}</td>

        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.mtcStoreNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.mtcSignBNum}</td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.mtcFlopPassNum} </td>

        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.expInitBNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.expSignBNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.expClassANum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.expFlopCompleteNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.expFlopPassNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.expRenewNum} </td>

        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.excReportNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.excNetSignNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.excEstateAcptNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.excHandleEndNum} </td>

        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.lnkReportNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.lnkSeeNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.lnkRoughNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.lnkRoughAmount} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.lnkDealNum} </td>
        <td style="vertical-align: middle;<c:if test="${list.regionName == '合计'}">font-weight: bold</c:if>">${list.lnkDealAmount} </td>
    </tr>
    </c:forEach>
</table>
</div>

${pageInfo.html}

<c:if test="${fn:length(contentList) le 0}">
    <div class="nodata">
        <i class="icon-hdd"></i>
        <p>暂无数据...</p>
    </div>
</c:if>
