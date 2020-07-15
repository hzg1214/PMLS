<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<form id = "contactsAddForm" >

 <div class="container theme-hipage ng-scope" role="main">
 
  	<span class="" style="float:right"><a href="javascript:Contract.close();" class="btn btn-default">&times;</a></span>
 
        <div class="row">
            <div class="page-content">
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right"><i>*</i>事业部区域</label>
                             <select class="form-control" title="" id="busNo" name="busNo" >
                            	<option value="">请选择事业部区域</option>
                            	<c:if test="${not empty busMap}">
	                                <c:forEach items="${busMap}" var="bus">
	                                    <option value="${bus.key}">${bus.value}</option>
	                                </c:forEach>
                            	</c:if>
                            </select>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    
    
</form>
