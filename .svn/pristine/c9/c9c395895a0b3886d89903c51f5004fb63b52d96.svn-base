<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
            <tr>
                <th>项目编号</th>
                <th>业绩归属城市</th>
                <th>城市</th>
                <th>区域</th>
                <th>类型</th>
                <th>楼盘名</th>
                <th>总业绩（元）</th>
                <th>带看奖励（元）</th>
                <th>认筹奖励（元）</th>
                <th>大定奖励（元）</th>
                <th>成销奖励（元）</th>
                <th>佣金（元）</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
					<td>${list.estateId}</td>
					<td>${list.cityNoNm}</td>
					<td>${list.realityCityNm}</td>
                    <td>${list.districtNm}</td>
                    <td>${list.estateTypeNm}</td>
                    <td>${list.estateNm}</td>
                    <td>${list.totalPerformance}</td>
                    <td>${list.relationReward}</td>
                    <td>${list.pledgedReward}</td>
                    <td>${list.subscribedReward}</td>
                    <td>${list.bargainReward}</td>
                    <td>${list.commission}</td>
					<td>
						<a href="${ctx}/sceneCommission/qSceneCommissionDetail/${list.estateId}">查看</a> 						
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
