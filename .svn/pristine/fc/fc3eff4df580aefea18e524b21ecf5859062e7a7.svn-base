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
    	changeCity("newCityNo", "newCenterId");
    });
</script>

<form id="mCenterUserCreateForm">
    <div class="container theme-hipage ng-scope" role="main" id="divs" style="width: 700px;">
        <div class="row">
            <div class="page-content">
                <span class="" style="float:right"><a href="javascript:mCenterUser.closeCreate();" class="btn btn-default">&times;</a></span>
                <h4 class="border-bottom pdb10"><strong>管理人员维护</strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" style="margin-right: 0px;"><i>*</i>归属城市 ：</label>
                            <span class="control-select">
                                <select class="form-control w300" title="" id="newCityNo" name="newCityNo"
                                        notnull="true" onchange="changeCity('newCityNo','newCenterId')">
                                     <c:forEach items="${citylist}" var="city">
                                         <option value="${city.cityNo}"
                                                 <c:if test="${city.cityNo eq userInfo.cityNo}">selected</c:if>
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
						<div class="form-group" name="group" style = "padding-left: 119px;">
							<label><i>*</i>归属中心 ：</label>
							<span class="control-select">
							<div class="multi-select" id="newCenterId" name="newCenterId" style="width: 300px;">
								<input type="hidden" class="multi-select-value" readonly="readonly" id="newCenterIds" name="newCenterIds"> 
								<input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
									style="width: 300px; height: 34px; margin-left: 0px;">
								<ul class="multi-select-list">
									<li class="multi-select-item">
										<label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
									</li>
								</ul>
							</div>
							<span class="fc-warning" id="newCenterIdStr"></span>
							</span>
						</div>
					</li>
				</ul>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" style="margin-right: 0px;"><i>*</i>员工工号 ：</label>
                            <input type="text" class="form-control w300" id="newUserCode"
                                   name="newUserCode" placeholder="" notnull="true" autocomplete="off"
                                   maxlength="30">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" style="margin-right: 0px;"><i>*</i>员工姓名 ：</label>
                            <input type="text" class="form-control w300" id="newUserName"
                                   name="newUserName" placeholder="" notnull="true" autocomplete="off"
                                   maxlength="30">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"></label>
                            <span class="fc-warning" id="newWarningMsg"></span>
                        </div>
                    </li>
                </ul>

            </div>
        </div>
    </div>

</form>