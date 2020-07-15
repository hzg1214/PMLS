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

<form id = "reportToValidRejectForm" >
<div class="container theme-hipage ng-scope" role="main" id="divs">

	<span class="" style="float:right"><a href="javascript:ReportToValid.close();" class="btn btn-default">&times;</a></span>

        <div class="row">
            <div class="page-content" style="clear:initial">
                <h4 class="border-bottom pdb10"><strong>意见填写</strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal text-right" for="roughAuditDesc" style="vertical-align:top;width: 45px;"><i>*</i>意见</label>
                            <textarea type="textarea" notnull="true" maxlength="1024" required="true" class="vera validatebox-text validatebox-invalid" id="roughAuditDesc" style="width:622px;height: 200px;padding:0px;" name="roughAuditDesc" data-options="required:true"></textarea>
                            <span class="fc-warning" id="itemWarning"></span>
                        </div>
                    </li> 
                </ul>
            </div>
        </div>
    </div>
    
</form>