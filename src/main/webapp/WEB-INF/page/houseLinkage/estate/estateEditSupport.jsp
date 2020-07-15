<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<p>
	<strong>周边配套</strong>
</p>
<c:forEach items="${estateSupport}" var="list" varStatus="status">
	<ul class="match list-inline form-inline">
		<li>
			<div class="form-group">
				<label class="fw-normal w100 text-right">类型：</label><input type="text" class="form-control w100"
					name="title[${status.index}]" placeholder="" maxlength="25" value="${list.title}"><label class="fw-normal w100 text-right">描述：</label><input
					type="text" class="form-control w300" name="description[${status.index}]" placeholder="" maxlength="100" value="${list.description}">
					<c:if test="${status.first eq true}"><span class="btn btn-link btn-add-descriptions" onclick="addMatch(this);">新增</span></c:if>
				<c:if test="${status.first eq false}"><span class="btn btn-link btn-add-descriptions" onclick="delMatch(this);">删除</span></c:if>
			</div>
		</li>
	</ul>
</c:forEach>
<c:if test="${fn:length(estateSupport) le 0}">
	<ul class="match list-inline form-inline">
		<li>
			<div class="form-group">
				<label class="fw-normal w100 text-right">类型：</label><input type="text" class="form-control w100"
					name="title[0]" placeholder="" maxlength="25" value=""><label class="fw-normal w100 text-right">描述：</label><input
					type="text" class="form-control w300" name="description[0]" placeholder="" maxlength="100" value="">
					<span class="btn btn-link btn-add-descriptions" onclick="addMatch(this);">新增</span>
			</div>
		</li>
	</ul>
</c:if>
<div class="text-center" style="margin-top:40px">
					<c:if test="${auditStatus eq '12904'}">
					   <span onclick="edit()" class="btn btn-primary" style="width: 100px;background-color: #71b0e6;">保存</span>
					</c:if>
					<span onclick="editSubmit()" class="btn btn-primary" style="width: 100px;margin-left: 100px!important;">提交</span>
					<a href="${ctx}/estate?searchParam=1" class="btn btn-default" style="width: 100px;margin-left: 100px!important;">取消</a>
				</div>