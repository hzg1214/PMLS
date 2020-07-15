<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>


<form id = "contactsEditForm" >

<input type="hidden" name="_method" value="put"/>


 <input type = "hidden"  id = "id"   name = "id" value = "${info.id}"/>

<div class="container theme-hipage ng-scope" role="main">

	<span class="" style="float:right"><a href="javascript:Contacts.close();" class="btn btn-default">&times;</a></span>

        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>新建联系人</strong></h4>
                <p><strong>主要信息</strong></p>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                             <label class="fw-normal w100 text-right" for="name"><i>* </i>姓名</label>
                            <input type="text" class="form-control w300" id="name" name="name" placeholder="" value="${info.name}"  notnull="true"  maxlength="10">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="sex">性别</label>
                             <c:forEach items="${sexList}" var="sexList">
                        		<input type="radio" value="${sexList.dicCode}" id="${sexList.dicCode}" name="sex"   <c:if test="${sexList.dicCode eq info.sex}">checked="checked"</c:if> ><label for="${sexList.dicCode}" class="fon-normal small">${sexList.dicValue}</label>
                        	</c:forEach>
                             <span class="fc-warning"></span>   
                        </div>
                    </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="mobilePhone"><i>* </i>手机</label>
                            <input type="text" class="form-control w300" id="mobilePhone" name="mobilePhone" value="${info.mobilePhone}"  notnull="true"  datatype="posNumWithOutZero" maxlength="11">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="email">邮件</label>
                            <input type="text" class="form-control w300" id="email" name="email" placeholder="" value="${info.email}"  datatype="email"  maxlength="50">
                        	<span class="fc-warning"></span>
                        </div>
                    </li>  
                </ul>
              
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">微信</label>
                            <input type="text" class="form-control w300" id="wechat" name="wechat" placeholder="" value="${info.wechat}"   maxlength="50">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="qQ">QQ</label>
                            <input type="text" class="form-control w300" id="qQ" name="qQ" placeholder="" value="${info.qQ}"  datatype="posNumWithZero"  maxlength="50">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    
                </ul>
                
                <ul class="list-inline half form-inline">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">联系地址</label>
                            <input type="text" class="form-control w300" id="address" name="address" placeholder="" value="${info.address}"   maxlength="100">
                            <span class="fc-warning"></span>
                        </div>
                    </li> 
                   
                </ul>
                <ul class="list-inline form-inline pdb20">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">备注</label>
                            <input type="text" class="form-control w300" id="remark" name = "remark" value="${info.remark}" maxlength="500">   
                            <span class="fc-warning"></span>                         
                        </div>
                    </li>
                </ul>
                <!-- <div class="text-center">
                  <button type="button" class="btn btn-primary" onclick="javascript:Contacts.update();" >保存</button>  
                  <button type="button" class="btn btn-primary" onclick="javascript:Contacts.back();">返回</button>  
                </div> -->
            </div>
        </div>
    </div>
    
</form>