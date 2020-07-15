<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<form id="estateAuditNoForm">
	<input type="hidden" name="id" value="${id}">
	<input type="hidden" name="auditStatus" value="12904">
	<div class="container theme-hipage ng-scope" role="main">
		<div class="row">
			<div class="page-content">
				<ul class="list-inline form-inline">
					<li>
						<div class="form-group">
							<label class="fw-normal w100 text-right" style="vertical-align: top;" for="auditMemo">说明：</label>
							<textarea name="auditMemo" id="auditMemo" cols="30" placeholder="审核不通过说明" maxlength="400" rows="10"
								style="resize: none;" notnull="true"></textarea>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</form>