<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<c:if test="${fn:length(contentlist) gt 0}"><div id="detailList2" class="s-scoll-y" style="height: 450px;"></c:if><c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
    <table class="table table-striped table-hover table-bordered" style="width:1700px;">
            <col style="width:170px">
            <col style="width:50px">
            <col style="width:150px">
            <col style="width:150px">
            <col style="width:120px">
            <col style="width:160px">
            <col style="width:100px">
            <col style="width:100px">
            <tr>
                <th>佣金编号</th>
                <th>区域</th>
                <th>楼盘</th>
                <th>客户姓名</th>
                <th>手机</th>
                <th>公司</th>
                <th>经纪人</th>
                <th>奖励类型</th>
                <th>奖励金额（元）</th>
                <th>结算金额（元）</th>
                <th>能否结算</th>
                <th>结算状态</th>
                <th>认定日</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
					<td>${list.countId}</td>
                    <td>${list.districtNm}</td>
                    <td title="${list.estateNm}">${fn:substring(list.estateNm, 0, 8)}
						<c:if test="${fn:length(list.estateNm) >= '9'}">
								...
						</c:if>
					</td>
                    <td>${list.customerNm}</td>
                    <td>${list.customerTel}</td>
                    <td title="${list.companyNm}">${fn:substring(list.companyNm, 0, 8)}
						<c:if test="${fn:length(list.companyNm) >= '9'}">
								...
						</c:if>
				     </td>
                    <td>${list.empNm}</td>
                    <td>${list.rewardTypeNm}</td>
                    <td>${list.rewardMoney}</td>
                    <td>${list.accountMoney}</td>
                    <td>${list.accountTypeNm}</td>
                    <td>${list.accountStatusNm}</td>
                    <td>${sdk:ymd2(list.recognitionDay)}</td>
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
