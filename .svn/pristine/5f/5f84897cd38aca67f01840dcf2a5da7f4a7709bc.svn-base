<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" role="main" style="width:500px;">
	<span class="" style="float:right"><a href="javascript:Contract.closeSplit();" id="centerClose" class="btn btn-default">&times;</a></span>
	
	<div class="row">
			<div class="page-content">
				<h4>
					<strong>选择所属中心</strong>
				</h4>
				<!-- 搜索条件区域 -->
				<form id="exchangeCenterForm">
					<div id="load_CenterId">
						<div id="LoadCxtCenterId">
							 <table  id="relateCenterTableId"  class="table table-striped table-bordered table-hover">
								<col style="width:100px;">
								<col style="width:auto;">
								<tr>
									 <th></th>
									 <th>所属中心名称</th>
								</tr>
								<input type="hidden" name="menuCenterLength" id="menuCenterLength" value="${fn:length(info)}">
								<script type="text/javascript">
									$(document).ready(function(){
										var dataLenth = parseInt($("#menuCenterLength").val(), 10);
											 if(dataLenth >  10){
												 $("#exchangeCenterForm").css("height","409px"); 
												 $("#exchangeCenterForm").css("overflow","auto"); 
										}
									});
								</script>
								<c:forEach items="${info}" var="exchangeCenter">
						                 	<tr>
						                 		<td style="width:100px">
						                 			<input type="radio" id="chb_select" name="chb_select"/>
						                 			<input type="hidden" id="centerId" value="${exchangeCenter.exchangeCenterId}">
						                 		</td>
						                 		<td style="text-align:center;" >
						                 			${exchangeCenter.exchangeCenterName}
						                 			
						                 		</td>
						                 	</tr>
								</c:forEach> 
						     </table>
							<c:if test="${fn:length(info) le 0}">
								<div class="nodata">
									<i class="icon-hdd"></i>
									<p>暂无数据...</p>
								</div>
							</c:if>
							<span class="fc-warning" id="errorId" style="display:none">请选择所属中心后再草签！</span>
						</div>
					</div>
				</form>
			</div>
		</div>
</div>
