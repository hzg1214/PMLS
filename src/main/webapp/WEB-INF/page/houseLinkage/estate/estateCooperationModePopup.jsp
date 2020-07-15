<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<form id="estateTypeAddForm" style="height: auto;width: auto" action="${ctx}/estate/changeCoMode">
	<input type="hidden" name="id" value="${id}">
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:EstateType.closePopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<label class="fw-normal w100 text-left" style="padding-left:20px">修改合作模式</label>
				<div class="border-bottom"></div>
				<span class="fc-warning" id="errormsg" style="padding-left:20px"></span>
				<table class="table-sammary">
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w100 text-left" style="margin-left:25px">楼盘名称：</label>
							<label class="fon-normal">${name}</label>
							<span class="fc-warning"></span>
						</td>
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w100 text-left" style="margin-left:25px">合作模式：</label>
							<c:if test="${mode eq 20402}">
								<input type="radio" value="20402" name="cooperationMode" checked> <label class="fon-normal small w80 text-left">整合</label>
								<input type="radio" value="20401" name="cooperationMode"> <label class="fon-normal small">分销</label>
							</c:if>
							<c:if test="${mode eq 20401}">
								<input type="radio" value="20402" name="cooperationMode"> <label class="fon-normal small w80 text-left">整合</label>
								<input type="radio" value="20401" name="cooperationMode" checked> <label class="fon-normal small">分销</label>
							</c:if>
							<span class="fc-warning"></span>
						</td>
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w100 text-left" style="vertical-align: top;margin-left: 25px">原因：</label>
							<textarea name="cooperationChangeReason" id="cooperationChangeReason" style="width:400px; height:100px"></textarea>
							<span id="warning-noReason" style="color:#f00;font-size:12px;margin-left:135px"></span>
							<span class="fc-warning"></span>
						</td>
					</tr>
				</table>
				<div class="text-center">
                <a href="javascript:EstateType.changeCooperationMode(${id})" class="btn btn-primary mgt20">　确定　</a>
				<a href="javascript:EstateType.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            </div>
			</div>
		</div>
	</div>
</form>