<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
    		<%--<col style="width:88px">--%>
            <col style="width:88px">
            <col style="width:144px">
            <col style="width:74px">
            <col style="width:158px">
            <col style="width:116px">
            <col style="width:68px">
            <col style="width:68px">
            <col style="width:74px">
            <col style="width:60px">
            <col style="width:102px">
            <col style="width:128px">
            <tr>
                <%--<th>编号</th>--%>
                <th>项目编号</th>
                <th>楼盘名称</th>
                <th>楼盘城市</th>
                <th>楼盘地址</th>
                <!-- <th>合作方</th> -->
                <th>合作方名称</th>
                <th>审核状态</th>
                <th>发布状态</th>
                <th>项目状态</th>
                <th>创建人</th>
                <!-- <th>录入部门</th> -->
                <!-- <th>合作期自</th>
                <th>合作期至</th> -->
                <th>创建日期</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
					<td style="display:none">${list.id}</td>
                    <td style="display:none">${list.cityNo}</td>
                    <td style="display:none">${list.partner}</td>
                    <td style="display:none">${list.auditStatus}</td>
                    <td style="display:none">${list.releaseStatus}</td>
                    <%--<td><a href="${ctx}/estate/${list.id}">${list.estateId}</a></td>--%>
                    <td><a href="${ctx}/estate/${list.id}">${list.projectNo}</a></td>
                    <td style="text-align:left;" title="${list.estateNm}" class="text-overflow">
                    	${list.estateNm}
                    </td>
                    <td>
						<c:if test="${list.estatePosition eq 0}">${list.realityCityNm}</c:if>
						<c:if test="${list.estatePosition eq 1}">${list.countryNm}</c:if>
					</td>
                    <td style="text-align:left;" title="${list.realityCityNm}${list.districtNm}${list.address}" class="text-overflow">
                    	${list.realityCityNm}${list.districtNm}${list.address}
                    </td>
                    <td style="text-align:left;" title="${list.partnerNm}" class="text-overflow">
                    	${list.partnerNm}
                    </td>
                    <td>${list.auditStatusName}</td>
                    <td>${list.releaseStatusName}</td>
                    <td>
						<c:choose>
							<c:when test="${list.projectStatus eq 20301}">
								跟单
							</c:when>
							<c:when test="${list.projectStatus eq 20306}">
								立项
							</c:when>
							<c:when test="${list.projectStatus eq 20302}">
								签约
							</c:when>
							<c:when test="${list.projectStatus eq 20303}">
								结案
							</c:when>
							<c:when test="${list.projectStatus eq 20304}">
								取消跟单
							</c:when>
							<c:when test="${list.projectStatus eq 20305}">
                                                                                    
                            </c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</td>
                    <td>${list.empName}</td>
                    <td>${sdk:ymd2(list.crtDt)}</td>
					<td>
						<a href="${ctx}/estate/${list.id}">查看</a> 
						<!-- 编辑 只要不是已发布的都可以编辑 只能自己编辑自己的-->
						<c:if test="${list.releaseStatus ne 13001 and list.auditStatus ne 12903 and (list.empId eq userInfo.userId or list.sceneEmpId eq userInfo.userId) and list.projectStatus ne 20302 and list.projectStatus ne 20303 and list.projectStatus ne 20306}">
							<a href="${ctx}/estate/u/${list.id}"> 编辑</a>
						</c:if>
						<%--<!-- 只有审核状态为通过,并且发布过[发布时间不为Null]的才能报备-->
						<c:if test="${list.auditStatus eq 12903 && list.releaseDt ne null}">
							<a href="${ctx}/estate/toReport1/${list.id}">报备</a> 
						</c:if>--%>
						<shiro:hasPermission name="/estate:ESTATE_AUDIT">
							<c:if test="${list.auditStatus eq 12901}">
								<a href="${ctx}/estate/toaudit/${list.id}">审核${idList}</a>
							</c:if>
						</shiro:hasPermission>
							<!-- 审核下属的未审核的记录 自己不能-->
							<%-- <c:if test="${list.empId eq idList and list.empId ne userInfo.userId and list.auditStatus eq 12901}">
								<a href="${ctx}/estate/toaudit/${list.id}">审核${idList}</a>
							</c:if> --%>
							<!-- 审核通过的才能发布 -->
							<shiro:hasPermission name="/estate:ESTATE_PUBLISH">
								<c:if test="${list.auditStatus eq 12903 and list.releaseStatus eq 13002}">
									<%--<c:if test="${list.cooperationMode ne 20402 or !empty list.measureDate}"><a href="javascript:EstateRelease.release(${list.id});">发布</a></c:if>--%>
									<%--<c:if test="${list.cooperationMode eq 20402 and empty list.measureDate}"><a href="javascript:EstateRelease.alertCantRelease();">发布</a></c:if>--%>
									<a href="javascript:EstateRelease.release(${list.id});">发布</a>
								</c:if>
							</shiro:hasPermission>
							<!-- 已发布的才能下架 -->
							
						<c:forEach items="${userIdList}" var="idList">
							<c:if test="${list.empId eq idList and list.releaseStatus eq 13001}">
								<a href="javascript:EstateDown.down(${list.id});">下架</a>
							</c:if>
						</c:forEach>
						<!-- 自己能撤回自己的审核状态是未审核的记录 将审核状态撤回到未提交-->
						<c:if test="${list.empId eq userInfo.userId and list.auditStatus eq 12901}">
							<a href="javascript:Estate.backOff(${list.id});">撤回</a>
						</c:if>
						<c:choose>
							<c:when test="${list.projectStatus eq 20304}">
								<a href="javascript:Estate.startProject(${list.id});">跟单</a >
							</c:when>
							<c:when test="${list.projectStatus eq 20301}">
								<a href="javascript:Estate.startCancel(${list.id});">取消跟单</a >
							</c:when>
							<c:when test="${list.projectStatus eq 20302}">
								<a href="javascript:Estate.endProject(${list.id});">结案</a >
							</c:when>
							<c:when test="${list.projectStatus eq 20303}">
								<a href="javascript:Estate.endCancel(${list.id});">取消结案</a >
							</c:when>
						</c:choose>
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