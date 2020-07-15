<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
    <c:if test="${fn:length(contentlist) gt 0}">
    <div id="divReport" class="s-scoll-y" style="margin-bottom:20px;"<%-- style="height: 500px;"--%>></c:if>
    <c:if test="${fn:length(contentlist) le 0}">
        <div id="divReport" class="s-scoll-y" style="height: auto;"></c:if>
            <table id="tblReport" class="table table-striped table-hover table-bordered" style="width:3900px;">
                <tbody class="s-w100">
                <col style="width:145px">
                <!-- <col style="width:110px">
                <col style="width:110px"> -->
                <col style="width:130px">
                <col style="width:80px">
                <col style="width:180px">
                <col style="width:200px">
                <col style="width:155px">
                <col style="width:130px">
                <col style="width:135px">
                <col style="width:130px">
                <col style="width:120px">
                <col style="width:110px">
                <col style="width:110px">
                <col style="width:120px">
                <col style="width:130px">
                <col style="width:130px">
                <col style="width:130px">
                <col style="width:130px">
                <col style="width:130px">
                <col style="width:130px">
                <col style="width:150px">
                <col style="width:150px">
                <col style="width:130px">
                <col style="width:150px">
                <col style="width:130px">
                <col style="width:130px">
                <col style="width:140px">
                <col style="width:1px">
                <tr>
                    <th>报备编号</th>
                   <!--  <th>业绩归属城市</th>
                    <th>楼盘所在城市</th> -->
                    <%--<th>类型</th>--%>
                    <th>楼盘名称</th>
                    <th>楼盘城市</th>
                    
                    <th>楼盘地址</th>
                    <th>经纪公司</th>
                    <%--<th>员工</th>--%>
                    <th>门店地址</th>
                    <th>客户</th>
                    <%--<th>报备来源</th>--%>
                    <th>客户手机号</th>
                    <th>业绩归属人</th>
                    <th>业绩归属人中心</th>
                    <th>最新进度</th>
                    <th>确认状态</th>
                    <th>大定审核状态</th>
                    <th>操作人</th>
                    <th>报备日期</th>
                    <th>楼室号</th>
                    <th>带看日期</th>
                    <th>大定面积</th>
                    <th>大定总价</th>
                    <th>大定日期</th>
                    <th>成销面积</th>
                    <th>成销总价</th>
                    <th>成销日期</th>
                    <th>退定日期</th>
                    <th>退房日期</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${contentlist}" var="list">
                <tr class="J_eatateItem" data-hidenumberflg="0">
                    <td>
                        <a href="${ctx}/report/${list.estateId}/${list.companyId}/${list.customerId}/${list.id}/0">${list.reportId}</a>
                    </td>
                    
                    <td style="text-align:left;" title="${list.estateNm}">${fn:substring(list.estateNm, 0, 7)}
                        <c:if test="${fn:length(list.estateNm) >= '8'}">
                            ...
                        </c:if>
                    </td>
                    <td>${list.realityCityNm}</td>
                    <td style="text-align:left;" title="${list.address}">${fn:substring(list.address, 0, 11)}
                        <c:if test="${fn:length(list.address) >= '12'}">
                            ...
                        </c:if>
                    </td>
                    <td style="text-align:left;" title="${list.companyNm}">${fn:substring(list.companyNm, 0,12)}
                        <c:if test="${fn:length(list.companyNm) >= '12'}">
                            ...
                        </c:if>
                    </td>
                    <td style="text-align:left;" title="${list.storeAddress}">${fn:substring(list.storeAddress, 0, 8)}
                        <c:if test="${fn:length(list.storeAddress) >= '9'}">
                            ...
                        </c:if>
                    </td>
                        <%--<td>${list.empNm}</td>--%>
                    <td style="text-align:left;" title="${list.customerNm}">${fn:substring(list.customerNm, 0, 7)}
                        <c:if test="${fn:length(list.customerNm) >= '8'}">
                            ...
                        </c:if>
                    </td>
                        <%--<td>${list.customerFromStr }</td>--%>
                    <td>${list.customerTel}</td>
                    <td>${list.contactNm}</td>
                    <%-- <td>${list.centerName}</td> --%>
                    <td  title="${list.centerName}">${fn:substring(list.centerName, 0, 7)}
                        <c:if test="${fn:length(list.centerName) >= '8'}">
                            ...
                        </c:if>
                    </td>
                    <c:choose>
                    	<c:when test="${list.brokerageStatus eq '22002' or list.brokerageStatus eq '22003'}">
                    		<td>结佣</td>
                    	</c:when>
                        <c:when test="${list.latestProgress eq '13503'}">
                            <td>带看</td>
                        </c:when>
                    	<c:otherwise>
                    		<td>${list.latestProgressNm}</td>
                    	</c:otherwise>
                    </c:choose>
                    <td>${list.confirmStatusNm}</td>
                    <c:choose>
                        <c:when test="${list.latestProgressNm eq '大定' or list.latestProgressNm eq '成销' or list.brokerageStatus eq '22002' or list.brokerageStatus eq '22003'}">
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
                    <td>${list.empNm}</td>
                    <td>${sdk:ymd2(list.reportDate)}</td>
                    <td style="text-align:center;" title="${list.buildingNo}">
                            ${fn:substring(list.buildingNo, 0, 6)}
                        <c:if test="${fn:length(list.buildingNo) >= '6'}">
                            ...
                        </c:if>
                    </td>
                    <td>${sdk:ymd2(list.seeDate)}</td>
                    <td><c:if test="${empty list.roughArea}">0</c:if><fmt:formatNumber value="${list.roughArea}"
                                                                                       pattern="#.00"/>㎡
                    </td>
                    <td><c:if test="${empty list.roughAmount}">0</c:if><fmt:formatNumber value="${list.roughAmount}"
                                                                                         pattern="#.00"/>元
                    </td>
                    <td>${sdk:ymd2(list.roughInputDate)}</td>
                    <td><c:if test="${empty list.area}">0</c:if><fmt:formatNumber value="${list.area}"
                                                                                  pattern="#.00"/>㎡
                    </td>
                    <td><c:if test="${empty list.dealAmount}">0</c:if><fmt:formatNumber value="${list.dealAmount}"
                                                                                        pattern="#.00"/>元
                    </td>
                    <td>${sdk:ymd2(list.dealDate)}</td>
                    <td>${sdk:ymd2(list.roughBackDate)}</td>
                    <td>${sdk:ymd2(list.dealBackDate)}</td>
                    <td>
                    	<c:if test="${(list.customerFrom eq 17403 or list.customerFrom eq 17405)  or (list.confirmStatus eq 13602)}">
							<a href="${ctx}/report/${list.estateId}/${list.companyId}/${list.customerId}/${list.id}/0">查看</a>
						</c:if>
                        <%--<c:if test="${list.acCityNo eq userAcCityNo}">--%>
                            <c:if test="${list.confirmStatus eq 13601 and list.latestProgress eq 13501 and list.customerFrom ne 17403 and list.customerFrom ne 17405}">
                                <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13502','${list.estateId}')">带看</a>
                                <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13602','${list.estateId}')"><i>无效</i></a>
                            </c:if>
                            <c:if test="${list.confirmStatus eq 13601 and list.latestProgress eq 13502 and list.customerFrom ne 17403 and list.customerFrom ne 17405}">
                                <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13504','${list.estateId}')">大定</a>
                                <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13602','${list.estateId}')"><i>无效</i></a>
                            </c:if>
                            <c:if test="${list.confirmStatus eq 13601 and list.latestProgress eq 13503 and list.customerFrom ne 17403 and list.customerFrom ne 17405}">
                                <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13504','${list.estateId}')">大定</a>
                            </c:if>
                            <c:if test="${list.confirmStatus eq 13601 and list.latestProgress eq 13504}">
                                <c:if test="${list.roughAuditStatus eq '0'}">
                                    <shiro:hasPermission name="/lnk:BL_PERMISSION">
                                        <a href="${ctx}/reportToValid/toView/${list.id}?fromType=0">大定审核</a>
                                    </shiro:hasPermission>
                                </c:if>
                                <c:if test="${list.roughAuditStatus eq '1'}">
                                    <shiro:hasPermission name="/lnk:BL_PERMISSION">
                                        <a href="javascript:SceneRecognition.toSceneHouseInfo('${list.reportId}', '13505','${list.estateId}')">成销</a>
                                    </shiro:hasPermission>
                                </c:if>
                                <c:if test="${list.roughAuditStatus eq '2' and (list.customerFrom eq '17401' or list.customerFrom eq '17402')}">
                                    <a href="javascript:void(0);"
                                       onclick="toOperDetailUpdate('${list.id}','${list.reportDetailId-1}')">大定修改</a>
                                    <a href="javascript:SceneRecognition.recognitionStatus('${list.reportId}', '13602','${list.estateId}')"><i>无效</i></a>
                                </c:if>
                                <c:if test="${list.roughAuditStatus eq '1'}">
                                    <shiro:hasPermission name="/lnk:BL_PERMISSION">
                                        <a href="javascript:SceneRecognition.rebackView('${list.reportId}','13504','${list.estateId}')">退定</a>
                                    </shiro:hasPermission>
                                </c:if>
                            </c:if>
                            <c:if test="${list.confirmStatus eq 13601 and list.latestProgress eq 13505 and list.brokerageStatus ne '22002' and list.brokerageStatus ne '22003'}">
                                <shiro:hasPermission name="/lnk:BL_PERMISSION">
                                    <a href="javascript:SceneRecognition.rebackView('${list.reportId}','13505','${list.estateId}')">退房</a>
                                </shiro:hasPermission>
                            </c:if>

                        <%--</c:if>--%>
						<c:if test="${list.customerFrom ne 17403 and list.customerFrom ne 17405 and list.confirmStatus eq 13601 and list.latestProgress eq 13505 and (list.brokerageStatus eq '22002' or list.brokerageStatus eq '22003')}">
							<a href="${ctx}/report/${list.estateId}/${list.companyId}/${list.customerId}/${list.id}/0">查看</a>
						</c:if>
					</td>
                    <td style="display:none">${list.id}</td>
                    <td style="display:none">${list.estateId}</td>
                    <td style="display:none">${list.companyId}</td>
                    <td style="display:none">${list.customerId}</td>
                    <td style="display:none">${list.cityNo}</td>
                    <td style="display:none">${list.estateType}</td>
                    <td style="display:none">${list.latestProgress}</td>
                    <td style="display:none">${list.confirmStatus}</td>
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
