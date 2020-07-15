<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script type="text/javascript">
		$("#releaseDt").focus(function(){
			var releaseDt = $("#releaseDt").val();
			if(releaseDt == '' || releaseDt == null)
			{	
			}else
			{
				$(".fc-warning").html('<font></font>');
			}
		});
</script>
<form id="estateReleaseForm">
	<input type="hidden" name="id" value="${id}">
	<input type="hidden" name="releaseStatus" id="releaseStatus" value="13002">
	<div class="" role="main" style="height: auto;width:650px;">
		<div class="row">
				<a href="javascript:EstateRelease.closePopup();" class="btn btn-default" style="float: right;">&times;</a>
			    <h4 class="border-bottom pdb10">
					<strong>发布</strong>
				</h4>
			<div class="page-content">		
			   <ul class="list-inline form-inline">
					<li>
						<div class="form-group">
							<input type="radio" name="releaseFlg" id="releaseFlg" value="1" checked="checked">立即发布
						</div>
					</li>
				</ul>
				<ul class="list-inline form-inline">
					<li>
						<div class="form-group">
							<input type="radio" name="releaseFlg" id="releaseFlg" value="0">预约发布
							<input type="text" class="calendar-icon" id="releaseDt" name="releaseDt"  onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate" value=""/>
						    <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</form>