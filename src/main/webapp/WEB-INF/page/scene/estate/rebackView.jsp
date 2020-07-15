<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<div class="container" role="main">
	<div class="row">
		<div class="page-content">
			<span class="" style="float:right"><a href="javascript:SceneRecognition.close();" class="btn btn-default">&times;</a></span>
			<h4 class="border-bottom pdb10"><strong>
                <c:if test="${status==13503}">
                    退筹
                </c:if>
                <c:if test="${status==13504}">
                    退定
                </c:if>
                <c:if test="${status==13505}">
                    退房
                </c:if>
            </strong></h4>
			<ul class="list-inline half form-inline">
				<li>
					<div class="form-group">
						<strong>
							<label class="fw-normal w100 text-right"><strong>项目编号：</strong></label>${houseInfo.projectNo}
							<label class="fw-normal w100 text-right"><strong>楼盘名称：</strong></label>${houseInfo.estateNm}
						</strong>
					</div>
				</li>
			</ul>
			<ul class="list-inline half form-inline">
				<li style="width: 50%;">
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备编号：</label>${houseInfo.reportId}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备来源：</label>
						${houseInfo.customerFromNm}
					</div>
				</li>
			</ul>
			<ul class="list-inline half form-inline">
				<li style="width: 50%;">
					<div class="form-group">
						<label class="fw-normal w120 text-right">经纪公司：</label>${houseInfo.companyNm}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">门店：</label>${houseInfo.storeNm}
					</div>
				</li>
			</ul>
			
			<ul class="list-inline half form-inline">
				<li style="width: 50%;">
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备经纪人：</label>${houseInfo.reportAgent}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">经纪人手机：</label>${houseInfo.reportAgentTel}

					</div>
				</li>
			</ul>
			
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right">客户姓名 ：</label>${houseInfo.customerNm}
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right">手机号码：</label>${houseInfo.customerTel}
						</div>
					</li>
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right">客户姓名:</label>${houseInfo.customerNmTwo}
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right">手机号码：</label>${houseInfo.customerTelTwo}
						</div>
					</li>
				</ul>
                <c:if test="${status==13504 or status == 13505}">
                    <ul class="list-inline half form-inline">
                        <li style="width: 50%;">
                            <div class="form-group">
                                <label class="fw-normal w120 text-right">楼室号：</label>${houseInfo.buildingNo}
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="fw-normal w120 text-right">大定面积：</label>${houseInfo.roughArea}m<sup>2</sup>
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline half form-inline">
                        <li style="width: 50%;">
                            <div class="form-group">
                                <label class="fw-normal w120 text-right">大定总价：</label><fmt:formatNumber value="${houseInfo.roughAmount}" pattern="#,##0.00#"/>元
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="fw-normal w120 text-right">大定日期：</label>
                                <fmt:parseDate value="${houseInfo.roughDate}" var="roughDate" pattern="yyyy-MM-dd HH:mm:ss"/>
                                <fmt:formatDate value="${roughDate}" pattern="yyyy-MM-dd" />
                            </div>
                        </li>
                    </ul>
                </c:if>
            <form id="rebackForm">
                <c:if test="${status eq 13505}">
                    <ul class="list-inline half form-inline">
                        <li style="width: 50%;">
                            <div class="form-group">
                                <label class="fw-normal w120 text-right">成销面积：</label>${houseInfo.area}m<sup>2</sup>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="fw-normal w120 text-right">成销总价：</label><fmt:formatNumber value="${houseInfo.dealAmount}" pattern="#,##0.00#"/>元

                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline half form-inline">
                        <li style="width: 50%;">
                            <div class="form-group">
                                <label class="fw-normal w120 text-right">成销日期：</label>
                                <fmt:parseDate value="${houseInfo.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
                                <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <c:if test="${status==13505}">
                                    <label class="fw-normal w120 text-right" for="operateDate"><i>*</i>退房日期&nbsp;：</label>
                                    <c:if test="${closeStatus==1}">
		                                <input type="hidden" id="closeDate" value="${yearMonth}">
		                                <input type="text" class="calendar-icon w160" name="operateDate" id="operateDate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'closeDate\')}',maxDate:'%y-%M-%d'})"
	                                       	notnull="true" class="ipttext Wdate" />
	                             	</c:if>  
	                             
		                            <c:if test="${closeStatus==0}">
			                             <fmt:parseDate value="${houseInfo.bizOperDate}" var="bizOperDateBack" pattern="yyyy-MM-dd HH:mm:ss"/>
			                             <input type="text" class="calendar-icon w160" value='<fmt:formatDate value="${bizOperDateBack}" pattern="yyyy-MM-dd" />' name="operateDate" id="operateDate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
		                                       notnull="true" class="ipttext Wdate" />
	                                </c:if>
<!--                                 <input type="text" class="calendar-icon w160" name="operateDate" id="operateDate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" -->
<!--                                        notnull="true" class="ipttext Wdate" /> -->
                                <span class="fc-warning"></span>
                                </c:if>  
                            </div>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${status==13503 or status==13504}">
                <ul class="list-inline half form-inline">
                    <li style="width: 50%;">
                        <div class="form-group">
                            <c:if test="${status==13503}">
                                <label class="fw-normal w120 text-right" for="operateDate"><i>*</i>退筹时间&nbsp;：</label>
                                <input type="text" class="calendar-icon w160" name="operateDate" id="operateDate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                       notnull="true" class="ipttext Wdate" />
                                <span class="fc-warning"></span>
                            </c:if>
                            <c:if test="${status==13504}">
                                <label class="fw-normal w120 text-right" for="operateDate"><i>*</i>退定时间&nbsp;：</label>
                            	<c:if test="${closeStatus==1}">
	                                <input type="hidden" id="closeDate" value="${yearMonth}">
	                                <input type="text" class="calendar-icon w160" name="operateDate" id="operateDate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'closeDate\')}',maxDate:'%y-%M-%d'})"
	                                       notnull="true" class="ipttext Wdate" />
	                             </c:if>  
	                             
	                             <c:if test="${closeStatus==0}">
	                             <fmt:parseDate value="${houseInfo.roughDate}" var="roughDateBack" pattern="yyyy-MM-dd HH:mm:ss"/>
	                             <input type="text" class="calendar-icon w160" value='<fmt:formatDate value="${roughDateBack}" pattern="yyyy-MM-dd" />' name="operateDate" id="operateDate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                       notnull="true" class="ipttext Wdate" />
                                 </c:if>  
	                             <span class="fc-warning"></span>
                            </c:if>
                        </div>
                    </li>
                </ul>
                </c:if>
               <%--  <c:if test="${houseInfo.customerFrom eq 17403}">
	                <ul class="list-inline half form-inline">
	                        <li style="width: 50%;">
	                            <div class="form-group">
	                                <label class="fw-normal w120 text-right">APP报备编号：</label>${houseInfo.fyReportId}
	                            </div>
	                        </li>
	                        <li >
	                            <div class="form-group">
	                            </div>
	                        </li>
	                </ul>
                 </c:if> --%>
    
                
            </form>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" id="errorMsg">
                            <span  class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <p style="color:red;position:absolute;left:50px;"> <!-- 注：选择日期时，日期灰色不可选代表该月份日期业绩已关账。 --></p>
                        </div>
                    </li>
                </ul>
		</div>
	</div>
</div>
</html>
<script>
$(function(){
	var isShowCurrDate = $("#isShowCurrDate").val();
	var closeStatus = $("#closeStatus").val();
	if(isShowCurrDate){
		if(closeStatus==1){
			$("#operateDate").val('${currDate}');	
		}
	}
});
</script>