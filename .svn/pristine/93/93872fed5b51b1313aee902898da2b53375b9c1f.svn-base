<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<form id="accountProjectForm" style="height: auto;width: auto" action="${ctx}/frameContract/chooseAccountProject">
	<input type="hidden" name="loginUserCode" value="${loginUserCode}">
	<input type="hidden" name ="cityAccountProject" value="${cityAccountProject}" />
	<input type="hidden" name ="contractId" value="${contractId}" />
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:FrameContractAdd.closePopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<label class="fw-normal text-left" style="padding-left:20px;width:150px"><strong>选择核算主体</strong></label>
				<div class="border-bottom"></div>
				<span class="fc-warning" id="errormsg" style="padding-left:20px"></span>
				<table class="table-sammary">
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal text-left" style="margin-left:100px;width:100px"">核算主体：</label>
							<select class="form-control w200" title="" id="userAccountProject" name="userAccountProject">
								 <c:if test="${!empty userMappingAccountProject}">
	                                    <c:forEach items="${userMappingAccountProject}" var="user">
	                                        <option value="${user.accountProjectCode}"
	                                                <c:if test="${user.accountProjectCode eq cityAccountProjectCode}">selected="selected"</c:if>>${user.accountProject}</option>
	                                    </c:forEach>
	                                    
	                            </c:if>
                            </select>
				            <span id="warning-noCity" style="color:#f00;font-size:12px;margin-left:35px"></span>
						</td>
					</tr>
					
				</table>
				<div class="text-center">
                <a href="javascript:FrameContractAdd.chooseAccountProject(${contractId})" class="btn btn-primary mgt20">　确定　</a>
				<a href="javascript:FrameContractAdd.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            </div>
			</div>
		</div>
	</div>
</form>

