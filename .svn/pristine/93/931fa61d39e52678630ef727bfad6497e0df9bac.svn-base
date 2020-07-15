<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.0&ak=${sysConfig.baiduApiKey}"></script>
<script type="text/javascript" src="${ctx}/meta/js/Signed/storeFollowListCtx.js?_v=${vs}"></script> 
<div>
	<table class="table table-striped table-hover table-bordered">
		<tr>
			<th scope="col">跟进主题</th>
			<th scope="col">跟进时间</th>
			<th width="" scope="col">跟进人</th>
			<th width="" scope="col">跟进类型</th>
			<th width="" scope="col">操作</th>
		</tr>
		
		<c:forEach items="${contentlist}" var="list">
			<tr>
				<td align="center">${list.title}</td>
				<td align="center">${list.dateCreate}</td>
				<td align="center">${list.userNameCreate}</td>
				<td align="center">${list.followTypeName}</td>
				<td align="center">
					<div class="mailopre">
						<%-- <a href="javascript:Follow.toUpdate('${list.followId}','${list.storeId}');">编辑</a>  --%> 
						
						
						<!-- 门店跟进 所属销售及其上级有查看权限 -->
						<c:forEach items="${userIdList}" var="idList">
						
							<c:if test="${list.userCreate eq idList}">
								<a href="javascript:StoreFollowListCtx.Detail(${list.followId});">查看</a>
							</c:if>
						
						</c:forEach>
						 
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