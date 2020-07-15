<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<form id="followAddForm">
	<div class="container theme-hipage ng-scope" role="main">
	<span style="float:right"><a href="javascript:Follow.close();" class="btn btn-default">&times;</a></span>
		<div class="row">
			<div class="page-content">
				<input type="hidden" name="storeId" value="${storeId}">
				<h4 class="border-bottom pdb10"><strong>添加跟进</strong></h4>
               	<ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="title"><i>*</i>跟进主题：</label>
                            <input type="text" class="form-control w300"  id="title" name="title" placeholder="请输入" notnull="true" value="" maxlength="50">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进日期：</label>
                            <input  type="text" class="calendar-icon" name="dateCreate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" class="ipttext Wdate"/>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进类型：</label>
                            <t:dictSelect field="followType" xmlkey="LOOKUP_Dic" dbparam="P1:107"></t:dictSelect>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline pdb20">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="content"><i>*</i>跟进内容：</label>
                            <textarea id="content"  name="content" cols="30" placeholder="请输入" notnull="true" maxlength="500" rows="10"  style="resize: none;"></textarea>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
			</div>
		</div>
	</div>
</form>

