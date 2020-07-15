<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
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
            width:140px;
            margin-left: 40px;
        }
        .lable-right{
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
            width: 200px;
        }
        .block{
            font-weight: bold;
        }
            /*上传图片的样式开始*/
        .layui-upload{
            width: 850px;
        }
        .layui-upload .layui-quote-nm{
            border-width: 1px;
        }
        .layui-upload .layui-upload-list {
            margin: 0;
            display: inline-block;
        }
        .layui-upload .layui-upload-list dd {
            position: relative;
            margin: 0 10px 10px 0;
            float: left
        }
        .layui-upload .layui-upload-list dd .img {
            min-height: 120px;
            max-width: 157px
        }
        /*上传图片的样式结束*/

		.qtReportViewPage hr {
            background-color:#FFFFFF;
            border-top: 1px dashed #e6e6e6;
        }
    </style>

<body>
<script type="application/javascript">
    var reportInfoDto='${info}';
    var yjReport='${yjReport}';
</script>
<div class="qtReportViewPage">
    <div class="layui-card">
        <div class="layui-card-body">
				<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<input type="hidden" id="operStatus" name="operStatus" value="${reqMap.operStatus}"/>
		            <legend>基本信息</legend>
		        </fieldset>
		        <div class="layui-row">
		        	<label><b style="margin-left: 50px;">项目编号：${info.estate.projectNo}</b> </label>&nbsp;&nbsp;&nbsp;
			        <label><b>楼盘名称：${info.estate.estateNm}</b> </label>
			    </div>
                <div class="layui-row">
                        <div class="layui-col-xs2 lable-left">订单编号：</div>
                        <div class="layui-col-xs5 lable-right">${info.reportNo}</div>
                        <div class="layui-col-xs2 lable-left">合作方：</div>
                        <div class="layui-col-xs5 lable-right">${info.partnerNm}</div>
                </div>
				<!--                 报备 -->
                <c:if test="${reqMap.operStatus eq 27301}">
                	<c:forEach var="detail" items="${info.qtReportDetailList}" varStatus="status">
	        			<c:if test="${detail.businessType eq '27301'}">
			                <div class="layui-row">
			                        <div class="layui-col-xs2 lable-left">收入类型：</div>
			                        <div class="layui-col-xs5 lable-right">${detail.detailSrTypeName}</div>
			                        <div class="layui-col-xs2 lable-left">收入金额：</div>
			                        <div class="layui-col-xs5 lable-right">
			                        	<fmt:formatNumber value="${detail.srAmount}" pattern="#,##0.00#"/>元
			                        </div>
			                </div>
			                <div class="layui-row">
			                        <div class="layui-col-xs2 lable-left">报备日期：</div>
			                        <div class="layui-col-xs5 lable-right">
			                        	<fmt:parseDate value="${detail.businessDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
					                    <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
			                        </div>
			                </div>
			                <div class="layui-row">
			                        <div class="layui-col-xs2 lable-left">备注：</div>
			                        <div class="layui-col-xs5 lable-right">${detail.memo}</div>
			                </div>
			                <div class="layui-row">
			                        <div class="layui-col-xs2 lable-left">操作人：</div>
			                        <div class="layui-col-xs5 lable-right">${detail.userName}</div>
			                        <div class="layui-col-xs2 lable-left">操作时间：</div>
			                        <div class="layui-col-xs5 lable-right">
			                        	<c:choose>
											<c:when test="${not empty detail.uptDate}">
												<fmt:parseDate value="${info.uptDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
					                    		<fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
											</c:when>
											<c:otherwise>
												<fmt:parseDate value="${info.crtDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
					                    		<fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
											</c:otherwise>
										</c:choose>
			                        </div>
			                </div>
                		</c:if>
                	</c:forEach>
<!--                 	补空(背景) begin -->
                	<div class="layui-row" style="margin-bottom: 228px;">
	                        <div class="layui-col-xs2 lable-left"></div>
	                        <div class="layui-col-xs5 lable-right"></div>
	                </div>
<!-- 	                补空 end -->
                </c:if>
                <!--    成销 -->
                <c:if test="${reqMap.operStatus eq '27302'}">
	            <c:forEach var="detail" items="${info.qtReportDetailList}" varStatus="status">
	            <c:if test="${detail.businessType eq '27302'}">
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">收入类型：</div>
	                        <div class="layui-col-xs5 lable-right">${detail.detailSrTypeName}</div>
	                        <div class="layui-col-xs2 lable-left">成销金额：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${detail.dealAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">成销日期：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:parseDate value="${info.businessDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">应计收入税前：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${detail.befYJSRAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                        <div class="layui-col-xs2 lable-left">应计收入税后：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${detail.aftYJSRAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">应计返佣税前：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${detail.befYJFYAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                        <div class="layui-col-xs2 lable-left">应计返佣税后：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${detail.aftYJFYAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">核算主体：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	${detail.accountProject}(${detail.accountProjectNo})
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">操作人：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	${detail.userName}
	                        </div>
	                        <div class="layui-col-xs2 lable-left">操作时间：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<c:choose>
									<c:when test="${not empty detail.uptDate}">
										<fmt:parseDate value="${info.uptDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    		<fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
									</c:when>
									<c:otherwise>
										<fmt:parseDate value="${info.crtDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    		<fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
									</c:otherwise>
								</c:choose>
	                        </div>
	                </div>
	                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	               	 	<legend>收入&返佣</legend>
	            	</fieldset>
	                <div class="layui-card">
					    <div class="layui-card-body">
					        <table id="qtReportCxFyTable" lay-size="sm" lay-filter="qtReportCxFy"></table>
					    </div>
					</div>
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	               	 	<legend>附件</legend>
	            	</fieldset>
		            <div class="fileContent" id="upload_cxImg_list">
		            	<div style="margin-left: 67px;">
			                <div>成销确认书/佣金结算资料</div><hr/>
		                </div>
		                <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:67px;">
<%-- 		                        <c:forEach items="${info.fileList}" var="file"> --%>
<%-- 		                            <c:if test="${file.fileTypeId eq '1025'}"> --%>
<!-- 		                                <dd class="item_img"> -->
<%-- 		                                    <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" > --%>
<%-- 		                                    <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" /> --%>
<!-- 		                                </dd> -->
<%-- 		                            </c:if> --%>
<%-- 		                        </c:forEach> --%>
		                </div>
		            </div>
		            <div class="fileContent" id="upload_fyImg_list">
		            	<div style="margin-left: 67px;">
			                <div>返佣确认函</div><hr/>
		                </div>
		                <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:67px;">
<%-- 		                        <c:forEach items="${info.fileList}" var="file"> --%>
<%-- 		                            <c:if test="${file.fileTypeId eq '1064'}"> --%>
<!-- 		                                <dd class="item_img"> -->
<%-- 		                                    <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" > --%>
<%-- 		                                    <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" /> --%>
<!-- 		                                </dd> -->
<%-- 		                            </c:if> --%>
<%-- 		                        </c:forEach> --%>
		                </div>
		            </div>
                </c:if>
                </c:forEach>
                </c:if>
                <!--    结佣 -->
                <c:if test="${reqMap.operStatus eq '22002' || reqMap.operStatus eq '22003'}">
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">收入类型：</div>
	                        <div class="layui-col-xs5 lable-right">${info.srTypeName}</div>
	                        <div class="layui-col-xs2 lable-left">成销金额：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${info.dealAmount}" pattern="#,##0.00#"/>
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">成销日期：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:parseDate value="${info.brokerageDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
	                        </div>
	                        <c:if test="${reqMap.operStatus eq '22003'}">
		                        <div class="layui-col-xs2 lable-left">结佣状态：</div>
		                        <div class="layui-col-xs5 lable-right">已结佣</div>
	                        </c:if>
	                        <c:if test="${reqMap.operStatus eq '22002'}">
		                        <div class="layui-col-xs2 lable-left">结佣状态：</div>
		                        <div class="layui-col-xs5 lable-right">部分结佣</div>
	                        </c:if>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">应计收入税前：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${info.befYJSRAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                        <div class="layui-col-xs2 lable-left" >应计收入税后：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${info.aftYJSRAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">应计返佣税前：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${info.befYJFYAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                        <div class="layui-col-xs2 lable-left" >应计返佣税后：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:formatNumber value="${info.aftYJFYAmount}" pattern="#,##0.00#"/>元
	                        </div>
	                </div>
	                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	               	 	<legend>返佣</legend>
	            	</fieldset>
	                <div class="layui-card">
					    <div class="layui-card-body">
					        <table id="qtReportJyFyTable" lay-size="sm" lay-filter="qtReportJyFy"></table>
					    </div>
					</div>
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	               	 	<legend>附件</legend>
	            	</fieldset>
		            <div class="fileContent" id="upload_cxImg_list">
		            	<div style="margin-left: 67px;">
			                <div>成销确认书/佣金结算资料</div><hr/>
		                </div>
		                <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:67px;">
<%-- 		                        <c:forEach items="${info.fileList}" var="file"> --%>
<%-- 		                            <c:if test="${file.fileTypeId eq '1025'}"> --%>
<!-- 		                                <dd class="item_img"> -->
<%-- 		                                    <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" > --%>
<%-- 		                                    <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" /> --%>
<!-- 		                                </dd> -->
<%-- 		                            </c:if> --%>
<%-- 		                        </c:forEach> --%>
		                </div>
		            </div>
	                <div class="fileContent" id="upload_fyImg_list">
		                <div style="margin-left: 67px;">
			                <div>返佣确认函</div><hr/>
		                </div>
		                <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:67px;">
<%-- 		                        <c:forEach items="${info.fileList}" var="file"> --%>
<%-- 		                            <c:if test="${file.fileTypeId eq '1064'}"> --%>
<!-- 		                                <dd class="item_img"> -->
<%-- 		                                    <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" > --%>
<%-- 		                                    <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" /> --%>
<!-- 		                                </dd> -->
<%-- 		                            </c:if> --%>
<%-- 		                        </c:forEach> --%>
		                </div>
	                </div>
                </c:if>
        </div>
    </div>
</div>


<script src="${ctx}/meta/pmls/js/otherReport/qtReportOperDetail.js?v=${vs}"></script>
</body>

