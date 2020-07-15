<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<form id="estateDownForm">
	<input type="hidden" name="id" value="${id}">
	<input type="hidden" name="releaseStatus" value="13002">
	<div class="" role="main" style="height: auto;width:650px;">
		<div class="row">
		<a href="javascript:EstateDown.closePopup();" class="btn btn-default" style="float: right;">&times;</a>
			    <h4 class="border-bottom pdb10">
					<strong>下架</strong>
				</h4>
			<div class="page-content">
				<ul class="list-inline form-inline">
					<li>
						<div class="form-group">
							<label class="" style="vertical-align: top;" for="releaseOffMemo">说明：</label>
							<textarea name="releaseOffMemo" id="releaseOffMemo" cols="30" placeholder="下架说明" maxlength="400" rows="10"
								style="resize: none;" notnull="true"></textarea>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</form>