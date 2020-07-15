<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
            <tr>
               <!--  <th><input value="checkbox" type="checkbox"></th> -->
                <th style="width:150px">合同编号</th>
                <th style="width:130px">协议书编号</th>
                <th>公司名称</th>
                <th style="width:100px">创建日期</th>
                <th style="width:100px">合同状态</th>
                <!--Mod By tong 2017/04/07 Start  -->
                <!-- <th>合同类型</th> -->
                <th style="width:90px">合作模式</th>
                <!--Mod By tong 2017/04/07 End -->
                <th style="width:90px">业绩归属人(工号)</th>
                <th style="width:100px">操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
					<%-- <td><input type="checkbox" name="blackCheck" value="${list.id}"></td> --%>
					<%-- <td>${list.contractNo}</td> --%>
					<td><a href="javascript:void(0);" onclick="storeGotoContract('${ctx}/contract/${list.id}/${list.contractStatus}','${ctx}/contract')">${list.contractNo}</a></td>
					<td>${list.agreementNo}</td>
					<td  title="${list.companyName}">
						${fn:substring(list.companyName, 0, 20)}
						<c:if test="${fn:length(list.companyName) >= '20'}">
							...
						</c:if>
					</td>
					<td>${list.dateCreate}</td>
					<td>${list.contractStatusName}</td>
					<td>${list.contractTypeName}</td> 
					<td>${list.expandingPersonnel}(${list.expandingPersonnelId})</td>
					<td><a href="javascript:void(0);" onclick="storeGotoContract('${ctx}/contract/${list.id}/${list.contractStatus}','${ctx}/contract')">查看</a> </td>
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
