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

<form id="accountProjectCreateForm">
    <div class="container theme-hipage ng-scope" role="main" id="divs" style="width: 700px;">
        <div class="row">
            <div class="page-content">
                <span class="" style="float:right"><a href="javascript:AccountProject.closeCreate();" class="btn btn-default">&times;</a></span>
                <h4 class="border-bottom pdb10"><strong>核算主体维护</strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" style="margin-right: 0px;"><i>*</i>归属城市 ：</label>
                            <span class="control-select">
                                <select class="form-control w300" title="" id="newCityNo" name="newCityNo"
                                        notnull="true" >
                                     <c:forEach items="${citylist}" var="city">
<%--                                      <input type="hidden" id="cityName" name="cityName" value="${city.cityName}" /> --%>
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
<!-- 						<div class="form-group" name="group" style="margin-left: 120px;"> -->
<!-- 							  <label  ><i>*</i>核算主体 ：</label> -->
<!-- 							  <div class="multi-select" id="centerId" name="centerId" style="width: 300px;"> -->
<!-- 								  <input type="hidden" class="multi-select-value" readonly="readonly" id="newAccountProjectNos" name="newAccountProjectNos"> -->
<!-- 								  <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 300px; height: 34px; margin-left: 0px;"> -->
<!-- 								  <ul class="multi-select-list"> -->
<!-- 									  <li class="multi-select-item"> -->
<!-- 										  <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label> -->
<!-- 									  </li> -->
<%-- 									  <c:forEach items="${accountProjectList}" var="accountProjectList"> --%>
<!-- 										  <li class="multi-select-item"> -->
<%-- 										  	<label><input  type="checkbox" value="${accountProjectList.accountProjectNo}" data-text="${accountProjectList.accountProjectStr}"><span>${accountProjectList.accountProjectStr}</span></label> --%>
<!-- 									  	  </li> -->
<%--                                      </c:forEach> --%>
<!-- 								  </ul> -->
<!-- 							  </div> -->
<!-- 							  <span class="fc-warning" id="newAccountProjectNoStr"></span> -->
<!-- 						</div> -->
							<div class="form-group" style="margin-left: 110px;">
                                <label style="width:90px;text-align:right;"><i>*</i>核算主体 ：</label>
                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        $("#newAccountProjectNos").multiselect({
                                            enableFiltering: true,
                                            filterPlaceholder: '请输入核算主体编号或名称',
                                            buttonWidth: '300px',
                                            nonSelectedText: '请选择核算主体',
                                            nSelectedText: '个核算主体被选中',
                                            includeSelectAllOption: false,
                                            selectAllText: '全选/取消',
                                            allSelectedText: '已选中所有核算主体',
                                            selectedClass: 'active1',
                                            numberDisplayed: 10,
                                            dropRight: true,
                                            maxHeight: 275,
                                            dropUp: true,
                                            onChange: function (element, checked) {
                                                var brands = $('#newAccountProjectNos option:selected');
                                                var selected = [];
                                                $(brands).each(function (index, brand) {
                                                    selected.push([ $(this).val() ]);
                                                });
                                                $("#newAccountProjectNoKey").val(selected);
                                                console.log(selected);
                                            }
                                        });
                                        //控制select显示
                                        $("span.multiselect-selected-text").css({
                                            "text-align": 'left',
                                            display: 'inline-block'
                                        });
                                        $(".input-group").css(
                                        	"width","271px"
                                        );
                                        //图标颜色
                                        $(".input-group-btn i").css(
                                        	"color","#4169E1"
                                        );
                                        //图标颜色
                                        $(".input-group-addon i").css(
                                        	"color","#4169E1"
                                        );
                                        
                                        $("b.caret").css("margin-left", "170px");
                                    });
                                </script>
                                <select class="form-control" style="width:150px;" id="newAccountProjectNos" name="newAccountProjectNos"
                                        multiple="multiple">
                                    <c:forEach items="${accountProjectList}" var="accountProjectList">
                                        <option value="${accountProjectList.accountProjectNo}">${accountProjectList.accountProjectStr}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" id="newAccountProjectNoKey" name="newAccountProjectNoKey"/>
                                <span class="fc-warning"></span>
                            </div>
                                <span class="fc-warning" id="newAccountProjectNoStr"></span>
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