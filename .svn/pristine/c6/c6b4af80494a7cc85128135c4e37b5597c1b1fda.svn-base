<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

  <c:if test="${fn:length(contentlist) gt 0}">
  <!-- <div class="s-scoll-y" style="margin-bottom:20px; border:1px solid #ddd;" id="divStatistic" > -->
  <div id="divStatistic" class="s-scoll-y" style="margin-bottom:20px;"></c:if>
  <c:if test="${fn:length(contentlist) le 0}">
  <div class="s-scoll-y" style="height: auto;" id="divStatistic" ></c:if>
    
    <table class="table table-striped table-hover table-bordered"  style="width:2800px;" id="tbStatistic">
    		<tbody class="s-w100">
    		<col style="width:90px">
			<col style="width:160px">
			<col style="width:80px">
			<col style="width:200px"> 
			<col style="width:110px">
            <tr>
                  <%--<th>编号</th>--%>
                  <th>项目编号</th>
                  <th>楼盘名称</th>
		          <th>楼盘城市</th>
		          
		          <th>楼盘地址</th>
		          <!-- <th>类型</th>
		          
		          <th>合作起始日</th>
		          <th>合作截止日</th> -->
		          <th>总业绩</th>
		          <th>带看奖励</th>
		          <th>大定奖励</th>
		          <th>成销奖励</th>
		          <th>佣金</th>
		          <th>报备</th>
		          <th>带看</th>
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
		            <%--<td><a href="${ctx}/estate/${list.id}">${list.estateId}</a></td>--%>
		            <td><a href="${ctx}/estate/${list.id}">${list.projectNo}</a></td>
		            <td style="text-align:left;" title="${list.estateNm}">${fn:substring(list.estateNm, 0, 10)}
						<c:if test="${fn:length(list.estateNm) >= '11'}">
								...
						</c:if>
					</td>
		            <td>${list.realityCityNm}</td>
		            <%-- <td>${list.districtNm}</td> --%>
		            
		             <td style="text-align:left;" title="${list.districtNm}${list.address}" class="text-overflow">
                    	${list.districtNm}${list.address} 
                    </td>
		            
		           <%--  <td>${list.cooperationDtStart}</td>
		            <td>${list.cooperationDtEnd}</td> --%>
		            <td>${list.totalPerformance}</td>
		            <td>${list.relationReward}</td>
		            <td>${list.subscribedReward}</td>
		            <td>${list.bargainReward}</td>
		            <td>${list.commission}</td>
		            <td>${list.reportPeopleNum}</td>
		            <td>${list.relationPeopleNum}</td>
		            <td>${list.subscribedPeopleNum}</td>
		            <td>${list.roughAreaSum}</td>
                    <td>${list.roughAmountSum}</td>
		            <td>${list.signPeopleNum}</td>
		            <td>${list.dealAreaSum}</td>
                    <td>${list.dealAmountSum}</td>
					<td>
						<a href="${ctx}/statistic/qCompanyInit/${list.estateId}/${list.countDateStart}/${list.countDateEnd}/${list.countDateTypeKbn}">查看</a> 
					</td>
					<td style="display:none">${list.cityNo}</td>
		            <td style="display:none">${list.district}</td>
		            <td style="display:none">${list.estateType}</td>
		            <td style="display:none">${list.countDateStart}</td>
					<td style="display:none">${list.countDateEnd}</td>
					<td style="display:none">${list.countDateTypeKbn}</td>
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

  

