<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<style>input[required]:invalid, input:focus:invalid, textarea[required]:invalid, textarea:focus:invalid{box-shadow: none}</style>
<script type="text/javascript" src="${ctx}/meta/js/common/validation.js?_v=${vs}"></script>
</head>
<body>
<div class="container theme-hipage ng-scope" style="width:720px; height:337px; min-height:300px;" role="main">
    <span style="float:right"><a href="javascript:Feeback.close();" class="btn btn-default">&times;</a></span>
        <div class="row">
            <div class="page-content" style="clear:initial">
                <h4 class="border-bottom pdb10"><strong>意见反馈</strong></h4>
                <form id="feebackForm">
                    <ul class="list-inline form-inline">
	                    <li>
	                       <div class="form-group">
	                           <label class="fw-normal w100 text-right" for="feebackTitle"><i>*</i>反馈主题</label>
	                           <input type="text" notnull="true" maxlength="20" required="true" class="gm_short" id="feebackTitle" style="width:453px" name="feebackTitle" data-options="required:true"></input>
	                           <span class="fc-warning"></span>
	                       </div>
	                    </li>
                    </ul>
                    <ul class="list-inline form-inline pdb20">
                        <li>
	                        <div class="form-group">
		                        <label class="fw-normal w100 text-right" for="feebackContent" style="vertical-align:top;"><i>*</i>反馈意见</label>
		                        <textarea type="textarea" notnull="true" maxlength="1024" required="true" class="vera validatebox-text validatebox-invalid" id="feebackContent" style="width:453px;height: 200px;padding:0px;" name="feebackContent" data-options="required:true"></textarea>
		                        <span class="fc-warning" style="vertical-align:top;"></span>
	                        </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</body>
</html>