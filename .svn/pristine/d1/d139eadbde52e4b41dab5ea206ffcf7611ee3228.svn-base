<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<c:if test="${fn:length(contentlist) gt 0}"><div id="detailList" class="s-scoll-y" style="height: 450px;"></c:if><c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
    <table class="table table-striped table-hover table-bordered" style="width:2100px;">
<!--  <div id="detailList" style="height: auto;">
    <table class="table table-striped table-hover table-bordered"> -->
            <col style="width:160px">
            <tr>
                <th>公司</th>
                <th>总业绩</th>
                <th>带看奖励（元）</th>
                <th>认筹奖励（元）</th>
                <th>大定奖励（元）</th>
                <th>成销奖励（元）</th>
                <th>佣金</th>
                <th>报备</th>
                <th>带看</th>
                <th>认筹</th>
                <th>退筹</th>
                <th>大定</th>
                <th>大定面积</th>
                <th>大定金额</th>
                <th>成销</th>
                <th>成销面积</th>
                <th>成销金额</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
					<td style="display:none">${list.estateId}</td>
					<td style="display:none">${list.companyId}</td>
					<td style="display:none">${list.countDateStart}</td>
					<td style="display:none">${list.countDateEnd}</td>
					<td style="display:none">${list.countDateTypeKbn}</td>
                    <td title="${list.companyNm}">${fn:substring(list.companyNm, 0, 8)}
						<c:if test="${fn:length(list.companyNm) >= '9'}">
								...
						</c:if>
				     </td>
                    <td>${list.totalPerformance}</td>
                    <td>${list.relationReward}</td>
                    <td>${list.pledgedReward}</td>
                    <td>${list.subscribedReward}</td>
                    <td>${list.bargainReward}</td>
                    <td>${list.commission}</td>
                    <td>${list.reportPeopleNum}</td>
                    <td>${list.relationPeopleNum}</td>
                    <td>${list.pledgedPeopleNum}</td>
                    <td>${list.getBackPeopleNum}</td>
                    <td>${list.subscribedPeopleNum}</td>
                    <td>${list.roughAreaSum}</td>
                    <td>${list.roughAmountSum}</td>
                    <td>${list.signPeopleNum}</td>
                    <td>${list.dealAreaSum}</td>
                    <td>${list.dealAmountSum}</td>
					<td>
						<a href="${ctx}/sceneStatisticEstate/qSceneStatisticDetailInit/${list.estateId}/${list.companyId}/${list.countDateStart}/${list.countDateEnd}/${list.countDateTypeKbn}">查看</a> 						
					</td>
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
