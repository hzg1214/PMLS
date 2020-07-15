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

    #divs {
        margin: 0 auto;
    }
</style>
<script>
    $(function () {
    	linkCityCenter("oldCityNo", "oldCenterId",'1');
    });
</script>

<form id="mCenterUserModifyForm">
    <div class="container theme-hipage ng-scope" role="main" id="divs" style="width: 700px;">
        <div class="row">
            <div class="page-content"><span class="" style="float:right"> <a
                    href="javascript:mCenterUser.closeModify();" class="btn btn-default">&times;</a></span>
                <h4 class="border-bottom pdb10"><strong>人员维护</strong></h4>
                <input type="hidden" id="recordId" name="recordId" value="${info.id}">
                <input type="hidden" id="infoCenterId" name="infoCenterId" value="${info.centerId}">
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" ><i>*</i>归属城市 ：</label>
                            <span class="control-select">
                                <select class="form-control w300" title="" id="oldCityNo" name="oldCityNo"
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
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"><i>*</i>归属中心 ：</label>
                            <span class="control-select">
                                <select class="form-control w300" title="" id="oldCenterId" name="oldCenterId"
                                        notnull="true">
                                         <c:if test="${!empty centerList}">
                                             <c:forEach items="${centerList}" var="center">
                                                 <option value="${center.centerId}"
                                                         <c:if test="${center.centerId eq info.centerId}">selected</c:if>
                                                 >${center.centerName}</option>
                                             </c:forEach>
                                         </c:if>
                                </select>
                                <span class="fc-warning"></span>
                			</span>
                        </div>
                    </li>
                </ul>
				<ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" ><i>*</i>员工工号 ：</label>
                            <input type="text" class="form-control w300" id="oldUserCode" value="${info.userCode}"
                                   name="oldUserCode" placeholder="" notnull="true" autocomplete="off" disabled="disabled"
                                   maxlength="30" style="background:#DDD">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" ><i>*</i>员工姓名 ：</label>
                            <input type="text" class="form-control w300" id="oldUserName" value="${info.userName}"
                                   name="oldUserName" placeholder="" notnull="true" autocomplete="off" disabled="disabled"
                                   maxlength="30" style="background:#DDD">
                            <span class="fc-warning"></span>
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