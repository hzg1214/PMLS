<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div>
	<table class="table table-striped table-hover table-bordered">
	    <col style="width:91px;">
	    <col style="width:118px;">
	    <col style="width:105px;">
	    <col style="width:130px;">
	    <col style="width:105px;">
		<col style="width:60px;">
	    <%--<col style="width:80px;">--%>
	    <col style="width:95px;">
	    <col style="width:75px;">
	    <col style="width:88px;">
		<col style="width:102px;">
		<col style="width:115px;">
		<tr>
			<th scope="col">门店编号</th>
			<th scope="col">门店名称</th>
			<th scope="col">所属中心</th>
			<th scope="col">门店地址</th>
			<!-- 2017/06/30 cning Add  Start -->
			<!-- 2017/06/30 cning Add  End -->
			<th scope="col">所属公司</th>
			<th scope="col">维护人</th>
			<!-- <th scope="col">跟进人员</th> -->
			<%--<th scope="col">创建人</th>--%>
			<th scope="col">创建时间</th>
			<th scope="col">合作模式</th>
			<th scope="col">合同状态</th>
			<th scope="col">装修进度</th>
			<th scope="col">营业状态</th>
			<!-- 2017/06/30 cning Del  Start -->
			<!-- <th scope="col">是否撤销</th> -->
			<!-- 2017/06/30 cning Del  End -->
			<th scope="col">操作</th>
		</tr>
		
		<c:forEach items="${contentlist}" var="list">
			<tr>
				<td align="center"><a href="${ctx}/store/${list.storeId}">${list.storeNo}</a></td>
				<td style="text-align:left;" title="${list.name}">
					${fn:substring(list.name, 0, 6)}
					<c:if test="${fn:length(list.name) > '6'}">
						...
					</c:if>
				</td>
				<!-- 2017/06/30 cning Add  Start -->
                <td style="text-align:left;"  title="${list.groupName}">
                	${fn:substring(list.groupName, 0, 6)}
                	<c:if test="${fn:length(list.groupName) > '6'}">
						...
					</c:if>
                </td>
                <!-- 2017/06/30 cning Add  End -->
                <td style="text-align:left;" title="${list.addressDetail}">
                	${fn:substring(list.addressDetail, 0, 7)}
                	<c:if test="${fn:length(list.addressDetail) > '7'}">
						...
					</c:if>
                </td>
				
				<c:choose> 
					  <c:when test="${empty list.companyName}"><td align="center">-</td></c:when>
					  <c:otherwise>
					  <td style="text-align:left;" title="${list.companyName}">
		                	${fn:substring(list.companyName, 0, 5)}
		                	<c:if test="${fn:length(list.companyName) > '5'}">
								...
							</c:if>
						</td>
					   </c:otherwise>
				</c:choose>
				<td align="center">
						<%-- <c:if test="${(list.contractType ne null && list.contractType ne '') && oaOperator ne null}"> --%>
						${list.maintainerName}
						<%-- </c:if> --%>
				</td>
				<%-- <td align="center">${list.userNameFollow}</td> --%>
				<%--<td align="center">--%>
					<%--${list.userNameCreate}--%>
				<%--</td>--%>
				<td align="center">
					${sdk:ymd2(list.dateCreate)}
				</td>

				<td align="center">
					<c:choose> 
						<c:when test="${list.contractType ==null || list.contractType==0}">未签</c:when>
						<c:otherwise> ${list.contractTypeName} </c:otherwise>
					</c:choose>
				</td>
				<td align="center">
				     <c:choose> 
				      <c:when test="${list.contractType ==null || list.contractType==0}">-</c:when>
				      <c:otherwise>${list.contractStatusName}</c:otherwise>
				     </c:choose>
				</td>
				<td align="center">
					<c:choose> 
						<c:when test="${empty list.decorationStatusName  || list.contractType eq 10306}">-</c:when>
						<c:otherwise> ${list.decorationStatusName} </c:otherwise>
					</c:choose>
				</td>
				<td align="center">
					<c:choose>
						<c:when test="${list.businessStatus ==null || list.businessStatus==0}">-</c:when>
						<c:otherwise>${list.businessStatusName}</c:otherwise>
					</c:choose>
				</td>
				<!-- 2017/06/30 cning Del  Start -->
				<%-- <td align="center">
					<c:if test="${empty list.isCancel}"> -- </c:if>
					<c:if test="${list.isCancel eq 17201 || list.isCancel eq 17202}">否</c:if>
					<c:if test="${list.isCancel eq 17203 }"> 是 </c:if>
				</td> --%>
				<!-- 2017/06/30 cning Del  End -->
				<%-- <td align="center">${list.storeStatusName}</td> --%>
				<td align="center">
					<div class="mailopre">
						<%-- <a href="javascript:Contacts.remove('${list.id}')">删除</a> --%>
						<a href="${ctx}/store/${list.storeId}">查看</a>
						
						<a href="${ctx}/store/u/${list.storeId}">编辑</a>
						
						<!-- 门店所属销售及其上级有编辑权限 -->
						<%-- <c:forEach items="${userIdList}" var="idList">
						
							<c:if test="${list.userCreate eq idList}">
							</c:if>
						
						</c:forEach> --%>
						 
					
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	${pageInfo.html}
</div>
<c:if test="${fn:length(contentlist) le 0}" >
	<div class="nodata"><i class="icon-hdd"></i><p>暂无数据...</p></div>
</c:if>