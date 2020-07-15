<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>房友新房分销系统</title>

    <style>
        .lable-left{
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
            width:100px;
            margin-left: 120px;
        }
        .lable-right{
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
        }
        .myForm .layui-form-label2{
            margin-left: 150px;
            min-height: 36px;
            margin-right: 20px;
            width: 450px;
            float:left
        }
        .myForm .layui-input-block{
            margin-left: 150px;
            min-height: 36px;
            margin-right: 20px;
            width: 450px;
        }
        .layui-tab-item .layui-table thead>tr{
            color:#999999;
        }
		.progressItem span{
			color:#bdbdbd;
		}
		.progressItem .blockTitle{
		    font-weight: bold;
		    line-height: 32px;
		    margin-left: 10px;
    	}
		.progressItem.active span{
			color:#3b91ff;
		}
		.progressItem .image01{
			width: 32px;
		    height: 32px;
		    background-image: url(/meta/pmls/images/01.png);
		    background-size: 100%;
		    background-repeat: no-repeat;
		    float:left;
		}
		.progressItem.active .image01{
		    background-image: url(/meta/pmls/images/1.png);
		}
		
		
		.progressItem .image04{
			width: 32px;
		    height: 32px;
		    background-image: url(/meta/pmls/images/04.png);
		    background-size: 100%;
		    background-repeat: no-repeat;
		    float:left;
		}
		.progressItem.active .image04{
		    background-image: url(/meta/pmls/images/4.png);
		}
		
		
		.progressItem .image05{
			width: 32px;
		    height: 32px;
		    background-image: url(/meta/pmls/images/05.png);
		    background-size: 100%;
		    background-repeat: no-repeat;
		    float:left;
		}
		.progressItem.active .image05{
		    background-image: url(/meta/pmls/images/5.png);
		}
		.fontClass{
            color: #a1a1a1;
        }
    </style>


</head>
<body>
<div class="layui-card">
    <div class="layui-card-body">
    	<div class="layui-row blockBody">
            <div class="layui-col-xs6">
                <div class="blockTitle">订单详情</div>
            </div>
            <div class="layui-col-xs6 blockBtn">
			    <button type="button" class="layui-btn layui-btn-primary" onclick="goBack()">返回</button>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
            <legend>订单</legend>
        </fieldset>
        
        <input type="hidden" id="id" name="id" value="${qtReportInfo.id}">
        <input type="hidden" id="reportNo" name="reportNo" value="${qtReportInfo.reportNo}">
        <input type="hidden" id="brokerageStatus" name="brokerageStatus" value="${qtReportInfo.brokerageStatus}">
        <input type="hidden" id="validStatus" name="validStatus" value="${qtReportInfo.validStatus}">
        <input type="hidden" id="brokerageDate" name="brokerageDate" value="${qtReportInfo.brokerageDate}">
        <input type="hidden" id="brokerageUserName" name="brokerageUserName" value="${qtReportInfo.brokerageUserName}">
        <input type="hidden" id="brokerageUptDate" name="brokerageUptDate" value="${qtReportInfo.uptDate}">
        <input type="hidden" id="brokerageCrtDate" name="brokerageCrtDate" value="${qtReportInfo.crtDate}">
        
        
		<div class="layui-row">
	        <label><b style="margin-left: 50px;">项目编号：${qtReportInfo.estate.projectNo}</b> </label>&nbsp;&nbsp;&nbsp;
	        <label><b>楼盘名称：${qtReportInfo.estate.estateNm}</b> </label>
	    </div>
	    <div class="layui-row">
	        <div class="layui-col-xs2 lable-left">订单编号：</div>
	        <div class="layui-col-xs3 lable-right">${qtReportInfo.reportNo}</div>
	        <div class="layui-col-xs2 lable-left">合作方：</div>
	        <div class="layui-col-xs4 lable-right">
	        	<c:choose>
					<c:when test="${not empty  qtReportInfo.partnerNm}">
						<td>${qtReportInfo.partnerNm}</td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
	        </div>
	    </div>      
        <div class="layui-row">
	        <div class="layui-col-xs2 lable-left">收入类型：</div>
	        <div class="layui-col-xs3 lable-right">${qtReportInfo.srTypeName}</div>
            <div class="layui-col-xs2 lable-left">最新进度：</div>
            <div class="layui-col-xs4 lable-right">${qtReportInfo.reportStatusName}</div>
        </div>
       
    </div>
</div>

<div class="layui-card">
    <div class="layui-card-body">
    	 <fieldset class="layui-elem-field layui-field-title" style="margin-top: 38px;">
             <legend>进度</legend>
         </fieldset>
    	 <div class="layui-form-item" style="margin-left: 315px;">
    	 	<div class="layui-inline">
<!--     	 	报备 -->
					<c:set var="flag1" value="0"></c:set>
					<c:forEach var="qtReportDetail" items="${qtReportInfo.qtReportDetailList}" varStatus="status">
						<c:if test="${qtReportDetail.businessType eq 27301}">
							<!-- 报备、未结佣 -->
							<li class="layui-input-inline progressItem active ">
								<div style="width:200px;height:80px;float:left;">
									<div class="image01"></div>
							    	<lable class="blockTitle">报备</lable>
							    	<span class="">————————</span><br/><br/>
							    	<lable class="progressDate fontClass">
										<fmt:parseDate value="${qtReportDetail.businessDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
						                <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
									</lable>
							    	<lable class="fontClass">
							    		${qtReportInfo.ackStatusName}
							    	</lable>
								</div>
							<c:set var="flag1" value="1"></c:set>
							</li>
						</c:if>
					</c:forEach>
					<!-- 					灰色 -->
					<c:if test="${flag1 eq '0'}">
						<li class="layui-input-inline progressItem ">
							<div style="width:200px;height:80px;float:left;">
								<div class="image01"></div>
						    	<lable class="blockTitle">报备</lable>
						    	<span class="">————————</span><br/><br/>
							</div>
						</li>
					</c:if>

<!-- 			 成销 -->
				<c:set var="flag2" value="0"></c:set>
				<c:set var="reportStatusStr" value="${qtReportInfo.reportStatus}"></c:set>
					<c:forEach var="qtReportDetail" items="${qtReportInfo.qtReportDetailList}" varStatus="status">
						<!-- 								退成销/成销 -->
								<c:if test="${qtReportDetail.businessType eq '27302'}">
									<c:if test="${qtReportInfo.reportStatus eq '27001'}">
										<c:set var="roughAuditStatusNm" value="退成销"></c:set>
										<li class="layui-input-inline progressItem active">
											<div style="width:200px;height:80px;float:left;">
												<div class="image04"></div>
										    	<lable class="blockTitle">成销</lable>
										    	<span class="">————————</span><br/><br/>
										    	<lable class="progressDate fontClass">
													<fmt:parseDate value="${qtReportDetail.businessDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
									                <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
												</lable>
										    	<lable class="fontClass">
										    		${roughAuditStatusNm}
										    	</lable>
											</div>
										</li>
									</c:if>
									<c:if test="${qtReportInfo.reportStatus ne '27001'}">
										<li class="layui-input-inline progressItem active">
											<div style="width:200px;height:80px;float:left;">
												<div class="image04"></div>
										    	<lable class="blockTitle">成销</lable>
										    	<span class="">————————</span><br/><br/>
										    	<lable class="progressDate fontClass">
													<fmt:parseDate value="${qtReportDetail.businessDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
									                <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
												</lable>
										    	<lable class="fontClass">
										    		 ${qtReportInfo.ackStatusName}
										    	</lable>
											</div>
										</li>
									</c:if>
								<c:set var="flag2" value="1"></c:set>
								</c:if>
					</c:forEach>
<!-- 					灰色 -->
					<c:if test="${flag2 eq '0'}">
						<li class="layui-input-inline progressItem">
							<div style="width:200px;height:80px;float:left;">
								<div class="image04"></div>
						    	<lable class="blockTitle">成销</lable>
						    	<span class="">————————</span><br/><br/>
							</div>
						</li>
					</c:if>
<!-- 				结佣 -->
				<div class="layui-input-inline progressItem
					<c:if test="${qtReportInfo.brokerageStatus == '22002' || qtReportInfo.brokerageStatus == '22003'}">
							active
					</c:if>
				">
					<div style="width:200px;height:80px;float:left;">
						<div class="image05"></div>
				    	<lable class="blockTitle">结佣</lable><br/><br/>
<!-- 				    	<span >————————</span> -->
				    	<c:if test="${qtReportInfo.brokerageStatus == '22002' || qtReportInfo.brokerageStatus == '22003'}">
					    	<lable class="fontClass">${qtReportInfo.reportStatusName}</lable>
					    	<lable class="fontClass">${qtReportInfo.ackStatusName}</lable>
				    	</c:if>
					</div>
				</div> 
			</div>
        </div>
        <table id="qtReportProgressTable" lay-size="sm" lay-filter="qtReportProgress"></table>
    </div>
</div>

<div class="layui-card">
    <div class="layui-card-body">
        <table id="qtReportLogTable" lay-size="sm" lay-filter="qtReportLog"></table>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/otherReport/qtReportDetail.js?v=${vs}"></script>

<script type="application/javascript">
    var qtReportInfo='${qtReportInfo}';
</script>
</body>
