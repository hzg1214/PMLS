b<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div id="detailList" style="height: auto;">
<c:if test="${fn:length(contentlist) gt 0}">
 <div class="s-scoll-y" style="margin-bottom:20px;" id="divSceneRecognition" >
</c:if><c:if test="${fn:length(contentlist) le 0}">
<div class="s-scoll-y" style="height: auto;" id="divSceneRecognition"></c:if>
    <table class="table table-striped table-hover table-bordered"  style="width:3130px;" id="tbSceneRecognition">
	        <col style="width:150px">
			<col style="width:100px">
			<col style="width:130px">
			<col style="width:200px">
			<col style="width:200px">
			<col style="width:90px">
			<col style="width:120px">
			<col style="width:90px">
		<col style="width:90px">
		<col style="width:90px">
		<col style="width:120px">
		<col style="width:90px">
		<col style="width:120px">
		<col style="width:120px">
		<col style="width:150px">
		<col style="width:120px">
		<col style="width:100px">
		<col style="width:130px">
		<col style="width:150px">
		<col style="width:120px">
		<col style="width:120px">
		<col style="width:120px">
		<col style="width:auto">
            <tr>
            	<!-- <th><input type='checkbox' title="" id="J_checkBoxAll"></th> -->
                <th>报备编号</th>
                <th>客户</th>
                <th>客户手机号</th>
                <th>经纪公司</th>
                <th>门店地址</th>
                <th>业绩归属人</th>
                <th>业绩归属人中心</th>
                <th>最新进度</th>
                <th>确认状态</th>
				<th>报备来源</th>
                <th>报备日期</th>
                <th>楼室号</th>
	            <th>带看日期</th>
	            <th>大定面积</th>
	            <th>大定总价</th>
				<th>大定日期</th>
	            <th>大定审核状态</th>
	            <th>成销面积</th>
	            <th>成销总价</th>
	            <th>成销日期</th>
	            <th>退定日期</th>
	            <th>退房日期</th>
                <th >操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_estateItem" data-hidenumberflg="0">
					<%-- <td><c:if test="${list.confirmStatusNm eq '未认定'}"><input type='checkbox'></c:if></td> --%>
					<%-- <td value ="${list.reportId}">${list.reportId}</td> --%>

					<td><a href="${ctx}/report/${list.estateId}/${list.companyId}/${list.customerId}/${list.id}/2">${list.reportId}</a></td>
					<td style="text-align:center;" title="${list.customerNm}" class="text-overflow">
						${list.customerNm}
                    </td>
                    <td>${list.customerTel}</td>
                    <td title="${list.companyNm}" class="text-overflow" style="text-align:left;">${list.companyNm}</td>


                    <td style="text-align:left;" title="${list.addressDetail}" class="text-overflow">
							${list.addressDetail}
                    </td>
                    <td>${list.maintainerName}</td>
                    <td>${list.centerName}</td>

                    <c:choose>
                    	<c:when test="${list.brokerageStatus eq '22002' or list.brokerageStatus eq '22003'}">
                    		<td>结佣</td>
                    	</c:when>
                    	<c:otherwise>
                    		<td>${list.latestProgressNmHandle}</td>
                    	</c:otherwise>
                    </c:choose>
                    <td>${list.confirmStatusNmHandle}</td>
					<td><c:if test="${list.customerFrom eq '17401'}">CRM</c:if><c:if test="${list.customerFrom eq '17403'}">APP</c:if><c:if test="${list.customerFrom eq '17405'}">友房通</c:if></td>
                    <td>${list.reportDate10} </td>
                    <td style="text-align:center;" title="${list.buildingNo}" class="text-overflow">
							${list.buildingNo}
                 	</td>
	                <td>${sdk:ymd2(list.seeDate)}</td>
	                <td><c:if test="${empty list.roughArea}">0</c:if><fmt:formatNumber value="${list.roughArea}" pattern="#.00"/></td>
	                <td title="${list.roughAmount}" class="text-overflow"><c:if test="${empty list.roughAmount}">0</c:if><fmt:formatNumber value="${list.roughAmount}" pattern="#.00"/>元</td>
	                <td>${sdk:ymd2(list.roughInputDate)}</td>
					<c:choose>
						<c:when test="${list.latestProgressNmHandle eq '大定' or list.latestProgressNmHandle eq '成销' or list.brokerageStatus eq '22002' or list.brokerageStatus eq '22003'}">
							<td>
								<c:if test="${list.roughAuditStatus eq '0'}">待审核</c:if>
								<c:if test="${list.roughAuditStatus eq '1'}">审核通过</c:if>
								<c:if test="${list.roughAuditStatus eq '2'}">审核驳回</c:if>
							</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
					</c:choose>
	              	<td><c:if test="${empty list.area}">0</c:if><fmt:formatNumber value="${list.area}" pattern="#.00"/>㎡</td>
	              	<td title="${list.dealAmount}" class="text-overflow"><c:if test="${empty list.dealAmount}">0</c:if><fmt:formatNumber value="${list.dealAmount}" pattern="#.00"/>元</td>
	              	<td>${sdk:ymd2(list.dealDate)}</td>
	                <td>${sdk:ymd2(list.roughBackDate)}</td>
	                <td>${sdk:ymd2(list.dealBackDate)}</td>
                    <td>
						<c:if test="${(list.customerFrom eq 17403 or list.customerFrom eq 17405) or (list.confirmStatusHandle eq 13602)}">
							<a href="${ctx}/report/${list.estateId}/${list.companyId}/${list.customerId}/${list.id}/2">查看</a>
						</c:if>
                        <%--<c:if test="${list.acCityNo eq userAcCityNo}">--%>

                            <%-- <c:if test="${list.customerFrom eq 17403}">
                                                        <a href="${ctx}/report/${list.estateId}/${list.companyId}/${list.customerId}/${list.id}/2">查看</a>
                                                    </c:if> --%>
                            <c:if test="${list.confirmStatus eq 13603 and list.latestProgress eq 13502 and list.customerFrom ne 17403 and list.customerFrom ne 17405}">
                                <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13502')">带看</a>
                                <!-- <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13503')">认筹</a> -->
                                <!-- <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13504')">大定</a> -->
                                <!-- <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13505')">成销</a> -->
                                <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13602')"><i>无效</i></a>
                            </c:if>
                            <c:if test="${list.confirmStatus eq 13603 and list.latestProgress eq 13503 and list.customerFrom ne 17403 and list.customerFrom ne 17405}">
                                <!-- <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13503')">认筹</a> -->
                                <%-- <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13504')">大定</a>
                                <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13505')">成销</a> --%>
                                <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13504')">大定</a>
                                <!-- <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13505')">成销</a> -->
                                <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13602')"><i>无效</i></a>
                            </c:if>
                            <c:if test="${list.confirmStatus eq 13603 and list.latestProgress eq 13504 and list.customerFrom ne 17403 and list.customerFrom ne 17405}">
                                <%-- 	<a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13504')">大定</a>
                                    <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13505')">成销</a> --%>
                                <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13504')">大定</a>
                                <!-- <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13505')">成销</a> -->
                                <!-- <a href="javascript:SceneRecognition.rebackView('${list.reportId}','13503')">退筹</a> -->
                                <%-- <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13602')"><i>无效</i></a> --%>
                            </c:if>
                            <c:if test="${list.confirmStatus eq 13603 and list.latestProgress eq 13505}">
                                <%-- <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13505')">成销</a> --%>
                                <c:if test="${list.roughAuditStatus eq '0'}">
                                    <shiro:hasPermission name="/lnk:BL_PERMISSION">
                                        <a href="${ctx}/reportToValid/toView/${list.id}?fromType=3">大定审核</a>
                                    </shiro:hasPermission>
                                </c:if>
                                <c:if test="${list.roughAuditStatus eq '1'}">
                                    <shiro:hasPermission name="/lnk:BL_PERMISSION">
                                        <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13505')">成销</a>
                                    </shiro:hasPermission>
                                </c:if>
                                <c:if test="${list.roughAuditStatus eq '2' and (list.customerFrom eq '17401' or list.customerFrom eq '17402')}">
                                    <a href="javascript:void(0);"
                                       onclick="toOperDetailUpdate('${list.id}','${list.reportDetailId-1}')">大定修改</a>
                                    <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13602')"><i>无效</i></a>
                                </c:if>
                                <c:if test="${list.roughAuditStatus eq '1'}">
                                    <shiro:hasPermission name="/lnk:BL_PERMISSION">
                                        <a href="javascript:SceneRecognition.rebackView('${list.reportId}','13504')">退定</a>
                                    </shiro:hasPermission>
                                </c:if>

                                <c:if test="${list.roughAuditStatus eq '1'}">
                                    <shiro:hasPermission name="/lnk:BL_PERMISSION">
                                        <a href="javascript:BatchSale.addBatchSale('${list.reportId}')">加入批量成销</a>
                                    </shiro:hasPermission>
                                </c:if>
                            </c:if>
                            <c:if test="${list.confirmStatus eq 13601 and list.latestProgress eq 13505 and list.brokerageStatus ne '22002' and list.brokerageStatus ne '22003'}">
                                <shiro:hasPermission name="/lnk:BL_PERMISSION">
                                    <a href="javascript:SceneRecognition.rebackView('${list.reportId}','13505')">退房</a>
                                </shiro:hasPermission>
                            </c:if>
                            <c:if test="${ list.customerFrom ne 17403 and list.customerFrom ne 17405 and list.confirmStatus eq 13601 and list.latestProgress eq 13505 and (list.brokerageStatus eq '22002' or list.brokerageStatus eq '22003')}">
                                <a href="${ctx}/report/${list.estateId}/${list.companyId}/${list.customerId}/${list.id}/2">查看</a>
                            </c:if>
                            <c:if test="${oaOperatorStr eq '1' and list.signDate ne null
                          and (list.confirmStatus eq 13601 or (list.confirmStatus eq 13603 and list.isSpecialProject eq 1))
                          and list.latestProgress eq 13505 and list.roughAuditStatus eq '1' and list.brokerageStatus ne '22003'}">
                                <a href="javascript:BatchCashBill.addToBatchCash('${list.reportId}')">加入批量请款</a>
                            </c:if>
                            <%--<a href="javascript:BatchSale.addBatchSale('${list.reportId}','${list.buildingNo}')">加入批量成销</a>--%>
                        <%--</c:if>--%>
					</td>
					<td style="display:none">${list.id}</td>
					<td style="display:none">${list.estateId} </td>
					<td style="display:none">${list.companyId}</td>
					<td style="display:none">${list.customerId}</td>
	                <td style="display:none">${list.cityNo}</td>
	                <td style="display:none">${list.estateType}</td>
	                <td style="display:none">${list.latestProgress}</td>
	                <td style="display:none">${list.confirmStatus}</td>
						<input type="hidden" ta = 'signDate' value=" ${list.signDate}">
						<input type="hidden" ta = 'roughAuditStatus' value=" ${list.roughAuditStatus}">
						<input type="hidden" ta = 'brokerageStatus' value=" ${list.brokerageStatus}">
						<input type="hidden" ta = 'oaOperatorStr' value=" ${oaOperatorStr}">
						<input type="hidden" ta = 'isSpecialProject' value=" ${list.isSpecialProject}">
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
