<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div <c:if test='${popFlag eq 1}'> class="container" </c:if> style="height: auto;<c:if test='${popFlag ne 1}'>width:940px;</c:if>">
    <c:if test="${popFlag eq 1}">
	    <span class="" style="float:right"><a href="javascript:Contract.close();" class="btn btn-default">&times;</a></span>
	    <h4 class="border-bottom pdb10"><strong>门店签约历史</strong></h4>
    </c:if>
    <table class="table table-striped table-hover table-bordered">
            <c:if test="${popFlag eq 1}">
	            <tr>
	                <th style="width:120px;">合同编号</th>
	                <th style="width:120px;">协议书编号</th>
	                <th style="width:150px;">公司名称</th>
	                <th style="width:70px;">合作模式</th>
	                <th style="width:70px;">合同状态</th>
	                <th style="width:70px;">变更状态</th>
	                <th style="width:120px;">创建时间</th>
	                <th style="width:120px;">业绩归属人(工号)</th>
	                <th style="width:70px;">查看</th>
	            </tr>
            </c:if>
            <c:if test="${popFlag ne 1}">
                <tr>
                    <th style="width:13%;">合同编号</th>
                    <th style="width:10%;">协议书编号</th>
                    <th style="width:14%;">公司名称</th>
                    <th style="width:7%;">合作模式</th>
                    <th style="width:7%;">合同状态</th>
                    <th style="width:7%;">变更状态</th>
                    <th style="width:10%;">创建时间</th>
                    <th style="width:12%;">业绩归属人(工号)</th>
                    <th style="width:5%;">查看</th>
                </tr>
            </c:if>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
					<td><a href="javascript:void(0)" onclick="storeGotoContract('${ctx}/contract/${list.id}/${list.contractStatus}','${ctx}/contract')">${list.contractNo}</a></td>
					<td>${list.agreementNo}</td>
					<td title="${list.companyName}">
						${fn:substring(list.companyName, 0, 20)}
						<c:if test="${fn:length(list.companyName) >= '20'}">
							...
						</c:if>
					</td>
					<td>${list.contractTypeName}</td>
					<td>${list.contractStatusName}</td>
					<td>${list.storeStateName}</td>
					<td>${list.dateCreate}</td>
					<td>${list.expandingPersonnel}(${list.expandingPersonnelId})</td>
<!-- 				<td><a href="${ctx}/contract/${list.id}/${list.contractStatus}">查看</a></td>  -->
					<td><a href="javascript:void(0)" onclick="storeGotoContract('${ctx}/contract/${list.id}/${list.contractStatus}','${ctx}/contract')">查看</a></td>
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
