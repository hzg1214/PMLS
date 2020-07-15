<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<form id="followEditForm">
	<div class="container theme-hipage ng-scope" role="main">
		<div class="row">
			<div class="page-content">
				<input type="hidden" name="followId" value="${followDetail.followId}">
				<h4 class="border-bottom pdb10"><strong>编辑跟进</strong></h4>
               	<ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="title"><i>*</i>跟进主题：</label>
                            <input type="text" class="form-control w300"  id="title"  name="title" placeholder="请输入" notnull="true" value="${followDetail.title}"  maxlength="50">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进人：</label>
                            <input type="text" class="form-control w300" value="${followDetail.userNameCreate}" readonly>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进日期：</label>
                            <input type="text" class="calendar-icon" name="dateCreate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" class="ipttext Wdate" value="${sdk:ymd2(followDetail.dateCreate)}"/>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进类型：</label>
                            <t:dictSelect field="followType" xmlkey="LOOKUP_Dic" dbparam="P1:107" defaultVal="${followDetail.followType}"></t:dictSelect>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">跟进门店：</label>
                            <input type="text" class="form-control w300" value="${followDetail.storeName}" readonly>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline pdb20">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="content"><i>*</i>跟进内容：</label>
                            <textarea id="content"  name="content" cols="30" placeholder="请输入" notnull="true" maxlength="500" rows="10"  style="resize: none;">${followDetail.content}</textarea>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
			</div>
		</div>
	</div>
</form>

