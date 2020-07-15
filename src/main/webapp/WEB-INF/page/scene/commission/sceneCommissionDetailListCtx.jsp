<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div id="detailList" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
            <tr>
            	<!-- <th><input type='checkbox' id="J_checkBoxAll"></th> -->
                <th>报备编号</th>
                <th>公司</th>
                <th>经纪人部门</th>
                <th>经纪人</th>
                <th>客户姓名</th>
                <th>客户电话</th>
                <th>奖励类型</th>
                <th>奖励金额（元）</th>
                <th>结算金额（元）</th>
                <th>报备日</th>
                <th>认定日期</th>
                <th>结算状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list"  varStatus="status">
				<tr class="J_estateItem" data-hidenumberflg="0">
					<!-- <td><input type='checkbox'></td> -->
					<td value ="${list.reportId}">${list.reportId}</td>
                    <td>${list.companyNm}</td>
                    <td>${list.deptNm}</td>
                    <td>${list.empNm}</td>
                    <td>${list.customerNm}</td>
                    <td>${list.customerTel}</td>
                    <td value="${list.progress}">${list.progressNm}</td>
                    <td><input type='text' name="reward" oldvalue="${list.reward}" value="${list.reward}" style="width:80px;text-align:center;" <c:if test="${list.accountStatus eq 14201}">readonly="readonly"</c:if>></td>
                    <td><input type='text' name="accountReward" oldvalue="${list.accountReward}" value="${list.accountReward}" style="width:80px;text-align:center;" <c:if test="${list.accountStatus eq 14201}">readonly="readonly"</c:if> onchange="checkAccount(this)"></td>
                    <td>${list.reportDate10}</td>
                    <td>${list.recognitionDay10}</td>
                    <td>${list.accountStatusNm}</td>
                    <td>
						<a href="javascript:SceneCommissionDetail.modify2(${status.index});" >修改佣金</a>
		                <a href="javascript:SceneCommissionDetail.confirm2(${status.index});" >确认结算</a>
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
