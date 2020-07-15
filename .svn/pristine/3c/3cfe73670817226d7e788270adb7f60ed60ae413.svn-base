<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<style>
.half li {
	width:780px;
}

#divs{
	margin:0 auto;
    width: 720px;
}
</style>
<script>
    $(function () {
        $("#inComeStatus").attr("style","width: 177px;");
    });
</script>
<form id = "reportToValidRejectForm" >
<div class="container theme-hipage ng-scope" role="main" id="divs">

	<span class="" style="float:right"><a href="javascript:ReportToValid.close();" class="btn btn-default">&times;</a></span>

        <div class="row">
            <div class="page-content" style="clear:initial">
                <h4 class="border-bottom pdb10"><strong>收入类型</strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 25%;margin-bottom: 30px;">
                            <label class="fw-normal w200 text-right" ><i>*</i>收入类型 ：</label>
                            <span class="control-select">
	                            <t:dictSelect field="inComeStatus" id="inComeStatus" xmlkey="LOOKUP_Dic" dbparam="P1:253" nullOption="请选择"  classvalue="10" > </t:dictSelect>
                			</span>
                            <span class="fc-warning" id="inComeStatusStr"></span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</form>