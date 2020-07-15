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
<%--<script>
    $(function () {
        /*initWjCityH('newCityNo');*/
        initMultiSelectH();
        /*$("#newCityNo .multi-select-item input[type='checkbox']").each(function(){
            $(this).click(function () {
                alert(1111);
            });
        })*/;
    });
</script>--%>

<form id="keFuWjModifyForm">
    <div class="container theme-hipage ng-scope" role="main" id="divs" style="width: 700px;">
        <div class="row">
            <div class="page-content"><span class="" style="float:right">
                <a href="javascript:KeFuWj.close();" class="btn btn-default">&times;</a></span>
                <h4 class="border-bottom pdb10"><strong>问卷模板维护</strong></h4>
                <input id="hid" type="hidden" value="${keFuWjH.id}">
                <div class="form-group">
                    <label class="fw-normal w200 text-right"></label>
                    <span class="fc-warning" id="warningMsg"></span>
                </div>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" ><i>*</i>模板编号 ：</label>
                            <span>${keFuWjH.wjCode}</span>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right" ><i>*</i>模板名称 ：</label>
                            <span>${keFuWjH.wjName}</span>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                       <%-- <div class="form-group" name="group" style = "padding-left: 126px;">
                            <label>适用城市 ：</label>
                            <span class="control-select">
							<div class="multi-select" id="newCityNo" name="newCityNo" style="width: 300px;margin-left: 20px;">
								<input type="hidden" class="multi-select-value" onblur="cityChange()" readonly="readonly" id="newCityNos" name="newCityNos">
								<input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                       style="width: 300px; height: 34px; margin-left: 0px;">
								<ul class="multi-select-list">
									<li class="multi-select-item">
										<label><input type="checkbox"  class="multi-select-checkall" value=""><span>全部</span></label>
									</li>
								</ul>
							</div>
							<span class="fc-warning" id="newCenterIdStr"></span>
							</span>
                        </div>--%>
                        <%--<div class="form-group" style="margin-left: 126px;">
                            <label><i>*</i>适用城市 ：</label>
                            <span class="control-select">
	                            <select class="form-control" title="" id="newCityNos" name="newCityNos" style="width:177px;">
	                                <option value="" selected="selected">请选择核算主体</option>
	                                <c:if test="${!empty cityList}">
                                        <c:forEach items="${cityList}" var="cityList">
		                                	<option value="${cityList.cityNo}">${cityList.cityName}</option>
                                        </c:forEach>
                                    </c:if>
	                            </select>
                            <span class="fc-warning"></span>
                             <span class="fc-warning" id="newCenterIdStr"></span>
                			</span>
                        </div>--%>
                           <div class="form-group" style="margin-left: 110px;">
                               <label style="width:90px;text-align:right;"><i>*</i>适用城市 ：</label>
                               <script type="text/javascript">
                                   $(document).ready(function () {
                                       $("#newCityNo").multiselect({
                                           enableFiltering: true,
                                           filterPlaceholder: '请输入城市编号或名称',
                                           buttonWidth: '300px',
                                           nonSelectedText: '请选择适用城市',
                                           nSelectedText: '个城市被选中',
                                           includeSelectAllOption: false,
                                           selectAllText: '全选/取消',
                                           allSelectedText: '已选中所有城市',
                                           selectedClass: 'active1',
                                           numberDisplayed: 10,
                                           dropRight: true,
                                           maxHeight: 275,
                                           dropUp: true,
                                           onChange: function (element, checked) {
                                               debugger;
                                               var brands = $('#newCityNo option:selected');
                                               var selected = [];
                                               $(brands).each(function (index, brand) {
                                                   selected.push([ $(this).val() ]);
                                               });
                                               $("#newCityNos").val(selected);
                                               console.log(selected);
//                                                systemLoading("", true);
                                               var cityNos = $("#newCityNos").val();
                                               var hid = $("#hid").val();
                                               $.ajax({
                                                   url:BASE_PATH+"/keFuWj/queryCityIsAvailable",
                                                   data:$.param({
                                                       cityNos:cityNos,
                                                       id:hid
                                                   }),
                                                   type:"post",
                                                   success:function(data){
                                                       data = JSON.parse(data);
                                                       if(data && data.returnCode == '200'){
                                                           if(data.returnMsg!=null&&data.returnMsg!=""){
                                                               $("#oldWarningMsg").html(data.returnMsg+'是否覆盖？');
                                                           }else{
                                                               $("#oldWarningMsg").html(data.returnMsg);
                                                           }

                                                           /*$(".ui_buttons input").removeAttr("disabled");*/
                                                           systemLoaded();
                                                       }
                                                   },
                                                   error:function(){
                                                       $("#oldWarningMsg").html("查询城市是否需要覆盖错误");
                                                       /*$(".ui_buttons input").removeAttr("disabled");*/
                                                       systemLoaded();
                                                   }
                                               });
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
                                       $("b.caret").prev().css("float", "left");
                                       $("b.caret").css("float", "right");
                                       $("b.caret").css("margin-top", "8px");
                                   });
                               </script>
                               <select class="form-control" style="width:150px;" id="newCityNo" name="newCityNo"
                                       multiple="multiple">
                                   <c:forEach items="${cityList}" var="cityList">
                                       <option value="${cityList.cityNo}">${cityList.cityName}</option>
                                   </c:forEach>
                               </select>
                               <input type="hidden" id="newCityNos" name="newCityNos"/>
                               <span class="fc-warning">
                               </span>
                           </div>
                           <span class="fc-warning" id="newCenterIdStr"></span>
                    </li>
                </ul>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"></label>
                            <span class="fc-warning" id="oldWarningMsg" style="margin-left: -225px;"></span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</form>