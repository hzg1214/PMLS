<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<style>
    .half li {
        width: 85%
    }

    .w200 {
        width: 200px !important;
        margin-right: 20px
    }
    .searchable-select {
        width: 300px;
    }

    #divs {
        margin: 0 auto;
    }
</style>
<script>
    $(function () {
    	$('#editAccountProjectNos').searchableSelect();
    });
</script>
<form id="accountProjectModifyForm">
    <div class="container theme-hipage ng-scope" role="main" id="divs" style="width: 700px;">
        <div class="row">
            <div class="page-content"><span class="" style="float:right"> <a
                    href="javascript:AccountProject.closeModify();" class="btn btn-default">&times;</a></span>
                <h4 class="border-bottom pdb10"><strong>核算主体维护</strong></h4>
                <input type="hidden" id="recordId" name="recordId" value="${info.id}">
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" ><i>*</i>归属城市 ：</label>
                            <span class="control-select" style="margin-left: -20px;" disabled="true">
                                <select class="form-control w300" disabled="disabled" readonly="readonly" style="background-color: #eaeaea;" title="" id="editCityNo" name="editCityNo"
                                        notnull="true">
                                  <c:forEach items="${citylist}" var="city">
                                     <option value="${city.cityNo}"
                                             <c:if test="${city.cityNo eq info.cityNo}">selected</c:if>
                                     >${city.cityName}</option>
                                  </c:forEach>
                                </select>
                                <span class="fc-warning"></span>
                			</span>
                        </div>
                    </li>
                </ul>

                <ul class="list-inline half form-inline">
					<li>
<!-- 						<div class="form-group" name="group" style="margin-left: 120px;"> -->
<!-- 							  <label  ><i>*</i>核算主体 ：</label> -->
<!-- 							  <span class="control-select"> -->
<!--                                 <select class="form-control w300" title="" id="editAccountProjectNos" name="editAccountProjectNos" -->
<!--                                         notnull="true"> -->
<%--                                          <c:if test="${!empty accountProjectList}"> --%>
<%--                                              <c:forEach items="${accountProjectList}" var="accountProjectList"> --%>
<%--                                                  <option value="${accountProjectList.accountProjectNo}" --%>
<%--                                                          <c:if test="${accountProjectList.accountProjectNo eq info.accountProjectNo}">selected</c:if> --%>
<%--                                                  >${accountProjectList.accountProjectStr}</option> --%>
<%--                                              </c:forEach> --%>
<%--                                          </c:if> --%>
<!--                                 </select> -->
<!--                                 <span class="fc-warning"></span> -->
<!--                 			</span> -->
<!-- 						</div> -->
						<div class="form-group" style="margin-left: 119px;">
                            <label><i>*</i>核算主体 ：</label>
                            <span class="control-select">
	                            <select class="form-control" title="" id="editAccountProjectNos" name="editAccountProjectNos" style="width:177px;">
	                                <option value="" selected="selected">请选择核算主体</option>
	                                <c:if test="${!empty accountProjectList}"> 
		                                <c:forEach items="${accountProjectList}" var="accountProjectList">
		                                	<option value="${accountProjectList.accountProjectNo}"
		                                    <c:if test="${accountProjectList.accountProjectNo eq info.accountProjectNo}">selected</c:if>
                                                 >${accountProjectList.accountProjectStr}</option>
		                                </c:forEach>
                                    </c:if>
	                            </select>
                            <span class="fc-warning"></span>
                             <span class="fc-warning" id="editAccountProjectNoStr"></span>
                			</span>
                        </div>
					</li>
				</ul>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"></label>
                            <span class="fc-warning" id="oldWarningMsg"></span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</form>