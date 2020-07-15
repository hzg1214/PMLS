<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<style>
.divs{
	width:600px;
}
.page-content{
	margin-left:20px;
}
</style>
<form id="fangyouAccountAddForm">
<div class="divs">

	<input type="hidden" name="storeId" value="${storeId}">
	<input type="hidden" name="storeNo" value="${storeNo}">
	<input type="hidden" name="name" value="${name}">
	<input type="hidden" name="operType" value="${operType}">
		<span class="" style="float:right"><a href="javascript:FangyouAccount.close();" class="btn btn-default">&times;</a></span>
		<div class="row">
			<div class="page-content" >
				<h4><strong>绑定房友账号</strong></h4>     
<br><br><br>
				<ul class="list-inline form-inline pdb30">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w100 text-left" for="content"><i>*</i> 房友账号： </label>
	                            <input type="text" id="fangyouNo" name="fangyouNo" class="form-control w200" maxlength="10" placeholder="请输入" notnull="true" autocomplete="off">
                            	<span class="fc-warning"></span>
	                        </div>
	                    </li>
	              </ul>
<br><br><br>
            </div>
		</div>
</div>
</form>

