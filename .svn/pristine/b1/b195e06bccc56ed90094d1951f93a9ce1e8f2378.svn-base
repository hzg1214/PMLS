<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<form id="releaseCityForm" style="height: auto;width: auto" action="${ctx}/estate/changeStatusMode">
	<input type="hidden" name="id" value="${id}">
	<input type="hidden" name="estateId" value="${estateId}">
	<input type="hidden" name="flag" value="${flag}">
	<input type="hidden" name="cityNo" value="${cityNo}">
	<input type="hidden" name="cityNm" value="${cityNm}">
	<input type="hidden" name="oldReleaseCity" value="${oldReleaseCity}">
	<input type="hidden" name ="citylistLenth" value="${citylistLenth}" />
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:EstateType.closePopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<label class="fw-normal text-left" style="padding-left:20px;width:150px"><strong>项目发布状态变更</strong></label>
				<div class="border-bottom"></div>
				<span class="fc-warning" id="errormsg" style="padding-left:20px"></span>
				<table class="table-sammary">
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w125 text-left" style="margin-left:55px;">&nbsp;&nbsp;原项目发布城市：</label>
							<label class="fon-normal" id="cityReleaseName">
							<c:if test="${flag eq '0'}">${cityNm}</c:if>
							<c:if test="${flag eq '1'}">
								 ${oldReleaseCity}
							</c:if>
							</label>
							<span class="fc-warning"></span>
						</td>
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal text-left" style="margin-left:35px;width:140px">变更后项目发布城市：</label>
								<script type="text/javascript">
								var data = 
                                    $(document).ready(function() {
                                   	 	$("#relseaseCity").multiselect("destroy").multiselect({  
                                           /*  enableFiltering: true,
                                            filterPlaceholder:'搜索',
                                           */
                                            buttonWidth: '140px',
                                            nonSelectedText:'请选择',
                                            nSelectedText:'个城市被选中',
                                            includeSelectAllOption:true,
                                            selectAllText:'全选/取消全选',
                                            allSelectedText:'已选中所有城市',
                                            selectedClass: 'active1',
                                            numberDisplayed : 10,
                                            dropRight: true,
                                            maxHeight: 275,
                                            dropUp: true,
                                            onChange: function(element, checked) {
                                                var brands = $('#relseaseCity option:selected');
                                                var selected = [];
                                                $(brands).each(function(index, brand){
                                                    selected.push([$(this).val()]);
                                                });
                                                $("#select_newRelseaseCityNo").val(selected);
                                                console.log(selected);
                                            }
                                        }); 
                                   	//控制select显示
                                   	//$(".dropdown-toggle").css("text-align","left");
                                   	$("span.multiselect-selected-text").css({"text-align":'left',display:'inline-block'});
                                    $("b.caret").css("margin-left","46px"); 
                                    });
                                </script>
							 <select class="form-control" title="" id="relseaseCity" name="relseaseCity"  multiple="multiple">
              					<%-- <c:forEach items="${citylist}" var="city">
	                           		<option value="${city.cityNo}" <c:forEach items="${oldReleaseCityNo}" var="oldCityNo"> ${oldCityNo== city.cityNo ? 'selected = "selected"' : ''}</c:forEach>>${city.cityName}</option>
	                            </c:forEach> --%>
	                            <c:forEach items="${citylist}" var="city">
	                           		<option value="${city.cityNo}">${city.cityName}</option>
	                            </c:forEach>
				            </select>
				            <span id="warning-noCity" style="color:#f00;font-size:12px;margin-left:35px"></span>
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" id="select_newRelseaseCityNo" name="select_newRelseaseCityNo" />
						</td>
					</tr>
					
				</table>
				<div class="text-center">
                <a href="javascript:EstateType.changeReleaseCity(${id})" class="btn btn-primary mgt20">　确定　</a>
				<a href="javascript:EstateType.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            </div>
			</div>
		</div>
	</div>
</form>

