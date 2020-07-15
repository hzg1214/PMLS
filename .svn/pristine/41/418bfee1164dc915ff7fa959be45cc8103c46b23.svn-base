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
            width:95px;
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
/*             margin-top: 10px; */
/*             margin-left: 67px; */
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
        
        .reportViewPage hr {
            background-color:#FFFFFF;
            border-top: 1px dashed #e6e6e6;
        }

    </style>

<body>
<script type="application/javascript">
    var reportInfoDto='${info}';
    var fyData='${fyData}';
</script>
<div class="reportViewPage">
    <div class="layui-card">
        <div class="layui-card-body">
				<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<input type="hidden" id="progress" name="progress" value="${info.progress}">
				<input type="hidden" id="brokeragePage" name="brokeragePage" value="${reqMap.brokeragePage}">
		            <legend>基本信息</legend>
		        </fieldset>
                <div class="layui-row">
                        <div class="layui-col-xs2 lable-left">订单编号：</div>
                        <div class="layui-col-xs5 lable-right">${info.reportId}</div>
                        <div class="layui-col-xs2 lable-left">门店：</div>
                        <div class="layui-col-xs5 lable-right">${info.storeNm}</div>
                </div>
                <div class="layui-row">
                        <div class="layui-col-xs2 lable-left">渠道公司：</div>
                        <div class="layui-col-xs10 lable-right" style="width: 600px;">${info.companyNm}</div>
                </div>
                <div class="layui-row">
                        <div class="layui-col-xs2 lable-left">楼盘：</div>
                        <div class="layui-col-xs10 lable-right" style="width: 600px;">${info.estateNm}(${info.estateId})</div>
                </div>
                <div class="layui-row">
                        <div class="layui-col-xs2 lable-left">报备经纪人：</div>
                        <div class="layui-col-xs5 lable-right">
                        	<c:if test="${!empty info.reportAgent}">${info.reportAgent}(${info.reportAgentTel})</c:if>
                        </div>
                        <div class="layui-col-xs2 lable-left">客户：</div>
                        <div class="layui-col-xs5 lable-right">
                        	<c:if test="${!empty info.customerNm}">${info.customerNm}(${info.customerTel})</c:if>
                        </div>
                </div>
				<!--                 报备 -->
                <c:if test="${info.progress eq 13501}">
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">客户：</div>
	                        <div class="layui-col-xs5 lable-right"><c:if test="${!empty info.customerNmTwo}">${info.customerNmTwo}(${info.customerTelTwo})</c:if></div>
	                        <div class="layui-col-xs2 lable-left">报备日期：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">操作人：</div>
	                        <div class="layui-col-xs5 lable-right">${info.empNm}</div>
	                        <div class="layui-col-xs2 lable-left">操作时间：</div>
	                        <div class="layui-col-xs5 lable-right">${info.recognitionDay}</div>
	                </div>
<!-- 	                补空 -->
	                <div class="layui-row" style="margin-bottom: 231px;">
	                        <div class="layui-col-xs2 lable-left"></div>
	                </div>
<!-- 	                补空end -->
                </c:if>
				<!--                 带看 -->
                <c:if test="${info.progress eq 13502}">
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">客户：</div>
	                        <div class="layui-col-xs5 lable-right"><c:if test="${!empty info.customerNmTwo}">${info.customerNmTwo}(${info.customerTelTwo})</c:if></div>
	                        <div class="layui-col-xs2 lable-left">带看日期：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">操作人：</div>
	                        <div class="layui-col-xs5 lable-right">${info.empNm}</div>
	                        <div class="layui-col-xs2 lable-left">操作时间：</div>
	                        <div class="layui-col-xs5 lable-right">${info.recognitionDay}</div>
	                </div>
	                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	               	 	<legend>附件</legend>
	            	</fieldset>
		            <div class="fileContent" id="upload_dk_list">
		            	<div style="margin-left: 67px;">
			                <div>带看单</div><hr/>
		            	</div>
		                    <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:67px;">
<%-- 		                        <c:forEach items="${info.fileList}" var="file"> --%>
<%-- 		                            <c:if test="${file.fileTypeId eq '1022'}"> --%>
<!-- 		                                <dd class="item_img"> -->
<%-- 		                                    <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" > --%>
<%-- 		                                    <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" /> --%>
<!-- 		                                </dd> -->
<%-- 		                            </c:if> --%>
<%-- 		                        </c:forEach> --%>
		                    </div>
		            </div>
                </c:if>
                <!--    大定 -->
                <c:if test="${info.progress eq 13504}">
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">客户：</div>
	                        <div class="layui-col-xs5 lable-right"><c:if test="${!empty info.customerNmTwo}">${info.customerNmTwo}(${info.customerTelTwo})</c:if></div>
	                        <div class="layui-col-xs2 lable-left">楼室号：</div>
	                        <div class="layui-col-xs5 lable-right">${info.buildingNo}</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">大定面积：</div>
	                        <div class="layui-col-xs5 lable-right">${info.roughArea}m<sup>2</sup></div>
	                        <div class="layui-col-xs2 lable-left">大定总价：</div>
	                        <div class="layui-col-xs5 lable-right"><fmt:formatNumber value="${info.roughAmount}" pattern="#,##0.00#"/>元</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">大定日期：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
	                        </div>
	                        <div class="layui-col-xs2 lable-left">操作人：</div>
	                        <div class="layui-col-xs5 lable-right">${info.empNm}</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">操作时间：</div>
	                        <div class="layui-col-xs5 lable-right">${info.recognitionDay}</div>
	                        <div class="layui-col-xs2 lable-left">审核状态：</div>
	                        <div class="layui-col-xs5 lable-right">${info.roughAuditStatusNm}</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">审核时间：</div>
	                        <div class="layui-col-xs5 lable-right">${info.roughAuditTime}</div>
	                        <div class="layui-col-xs2 lable-left">审核人：</div>
	                        <div class="layui-col-xs5 lable-right">${info.roughAuditUserName}</div>
	                </div>
	                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	               	 	<legend>附件</legend>
	            	</fieldset>
			        <div class="fileContent" id="upload_dk_list">
			            <div style="margin-left: 67px;">
		                	<div>带看单</div><hr/>
		                </div>
		                    <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:67px;">
<%-- 		                        <c:forEach items="${info.fileList}" var="file"> --%>
<%-- 		                            <c:if test="${file.fileTypeId eq '1022'}"> --%>
<!-- 		                                <dd class="item_img"> -->
<%-- 		                                    <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" > --%>
<%-- 		                                    <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" /> --%>
<!-- 		                                </dd> -->
<%-- 		                            </c:if> --%>
<%-- 		                        </c:forEach> --%>
		                    </div>
		            </div>
		            <div class="fileContent" id="upload_dd_list">
		            	<div style="margin-left: 67px;">
			                <div>大定单</div><hr/>
		                </div>
		                    <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:67px;">
<%-- 		                        <c:forEach items="${info.fileList}" var="file"> --%>
<%-- 		                            <c:if test="${file.fileTypeId eq '1023'}"> --%>
<!-- 		                                <dd class="item_img"> -->
<%-- 		                                    <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" > --%>
<%-- 		                                    <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" /> --%>
<!-- 		                                </dd> -->
<%-- 		                            </c:if> --%>
<%-- 		                        </c:forEach> --%>
		                    </div>
		            </div>
		            <div class="fileContent" id="upload_project_list">
		            	<div style="margin-left: 67px;">
			                <div>甲方项目负责人名片</div><hr/>
		                </div>
		                    <div class="layui-upload-list upload_img_list" style="margin-top: 10px;margin-left:67px;">
<%-- 		                        <c:forEach items="${info.fileList}" var="file"> --%>
<%-- 		                            <c:if test="${file.fileTypeId eq '1024'}"> --%>
<!-- 		                                <dd class="item_img"> -->
<%-- 		                                    <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" > --%>
<%-- 		                                    <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" /> --%>
<!-- 		                                </dd> -->
<%-- 		                            </c:if> --%>
<%-- 		                        </c:forEach> --%>
		                    </div>
		            </div>
                </c:if>
                <!--    成销 -->
                <c:if test="${info.progress eq 13505 && reqMap.brokeragePage eq '22001' }">
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">客户：</div>
	                        <div class="layui-col-xs5 lable-right"><c:if test="${!empty info.customerNmTwo}">${info.customerNmTwo}(${info.customerTelTwo})</c:if></div>
	                        <div class="layui-col-xs2 lable-left">楼室号：</div>
	                        <div class="layui-col-xs5 lable-right">${info.buildingNo}</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">成销面积：</div>
	                        <div class="layui-col-xs5 lable-right">${info.area}m<sup>2</sup></div>
	                        <div class="layui-col-xs2 lable-left">成销总价：</div>
	                        <div class="layui-col-xs5 lable-right"><fmt:formatNumber value="${info.dealAmount}" pattern="#,##0.00#"/>元</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">成销日期：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
	                        </div>
	                        <div class="layui-col-xs2 lable-left" style="width: 100px;margin-left:35px">结算确认日期：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:parseDate value="${info.settleConfirmDate}" var="settleConfirmDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    <fmt:formatDate value="${settleConfirmDate}" pattern="yyyy-MM-dd" />
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">核算主体：</div>
	                        <div class="layui-col-xs5 lable-right">${info.accountProject} (${info.accountProjectNo})</div>
	                        <div class="layui-col-xs2 lable-left">操作人：</div>
	                        <div class="layui-col-xs5 lable-right">${info.empNm}</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">操作时间：</div>
	                        <div class="layui-col-xs5 lable-right">${info.recognitionDay}</div>
	                </div>
	                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	               	 	<legend>收入&返佣</legend>
	            	</fieldset>
	            	<div class="layui-row">
	                        <div class="layui-col-xs2 lable-left" style="width: 100px;">应计收入税前：</div>
	                        <div class="layui-col-xs5 lable-right">${info.yjsrBeforeAmount}</div>
	                        <div class="layui-col-xs2 lable-left" style="width: 100px;">应计收入税后：</div>
	                        <div class="layui-col-xs5 lable-right">${info.yjsrAfterAmount}</div>
	                </div>
	                <div class="layui-card">
					    <div class="layui-card-body">
					        <table id="reportCxFyTable" lay-size="sm" lay-filter="reportCxFy"></table>
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
                </c:if>
                <!--    结佣 -->
                <c:if test="${reqMap.brokeragePage eq '22003'}">
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">客户：</div>
	                        <div class="layui-col-xs5 lable-right"><c:if test="${!empty info.customerNmTwo}">${info.customerNmTwo}(${info.customerTelTwo})</c:if></div>
	                        <div class="layui-col-xs2 lable-left">楼室号：</div>
	                        <div class="layui-col-xs5 lable-right">${info.buildingNo}</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">成销面积：</div>
	                        <div class="layui-col-xs5 lable-right">${info.area}m<sup>2</sup></div>
	                        <div class="layui-col-xs2 lable-left">成销总价：</div>
	                        <div class="layui-col-xs5 lable-right"><fmt:formatNumber value="${info.dealAmount}" pattern="#,##0.00#"/>元</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">成销日期：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    <fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd" />
	                        </div>
	                        <div class="layui-col-xs2 lable-left" style="width: 100px;margin-left:35px">结算确认日期：</div>
	                        <div class="layui-col-xs5 lable-right">
	                        	<fmt:parseDate value="${info.settleConfirmDate}" var="settleConfirmDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			                    <fmt:formatDate value="${settleConfirmDate}" pattern="yyyy-MM-dd" />
	                        </div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">核算主体：</div>
	                        <div class="layui-col-xs5 lable-right">${info.accountProject} (${info.accountProjectNo})</div>
	                        <div class="layui-col-xs2 lable-left">操作人：</div>
	                        <div class="layui-col-xs5 lable-right">${info.empNm}</div>
	                </div>
	                <div class="layui-row">
	                        <div class="layui-col-xs2 lable-left">操作时间：</div>
	                        <div class="layui-col-xs5 lable-right">${info.recognitionDay}</div>
	                </div>
	                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	               	 	<legend>返佣</legend>
	            	</fieldset>
	            	<div class="layui-row">
	                        <div class="layui-col-xs2 lable-left" style="width: 100px;">应计返佣税前：</div>
	                        <div class="layui-col-xs5 lable-right">${info.yjfyBeforeAmount}</div>
	                        <div class="layui-col-xs2 lable-left" style="width: 100px;">应计返佣税后：</div>
	                        <div class="layui-col-xs5 lable-right">${info.yjfyAfterAmount}</div>
	                </div>
	            	<div class="layui-row">
	                        <div class="layui-col-xs2 lable-left" style="width: 100px;">结佣状态：</div>
	                        <div class="layui-col-xs5 lable-right">${info.brokerageStatusNm}</div>
	                </div>
	                <div class="layui-card">
					    <div class="layui-card-body">
					        <table id="reportJyFyTable" lay-size="sm" lay-filter="reportJyFy"></table>
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
                </c:if>
            </div>
    </div>
</div>


<script src="${ctx}/meta/pmls/js/scene/sceneTrade/reportView.js?v=${vs}"></script>
</body>
</html>

