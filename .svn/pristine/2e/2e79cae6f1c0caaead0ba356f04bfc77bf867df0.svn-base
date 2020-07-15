<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp"%>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/scene/estate/sceneRecognition.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<style type="text/css">
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
    input[type="number"]{
        -moz-appearance: textfield;
    }
</style>
</head>
<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
</jsp:include>

<div class="container theme-hipage ng-scope" role="main">
    <div class="crumbs">
        <ul style="margin-right:150px;">
            <li><a href="#"  class="a_hover">联动管理</a></li>
            <li><a href="#"  class="a_hover">>案场管理</a></li>
            <li><a href="#"  class="a_hover">>项目</a></li>
            <li><a href="#"  class="a_hover">>批量成销</a></li>
        </ul>
    </div>
    <div class="page-content">
        <h4 class="border-bottom pdb10" style="margin-top:20px;">
            <strong>批量成销</strong>
            <span class="" style="float:right"><a href="${ctx}/sceneEstate/qSceneRecognition/${estateId}" class="btn btn-primary" style="float:right">返回</a></span><span style="color: red;font-size: 14px;" id="errorMsg"></span>
        </h4>
    </div>
<div class="row">
    <div class="page-content">
    <div class="container">
        <span><strong>项目编号：${projectNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;楼盘名称：${estateNm}</strong></span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" id="btn-output" class="btn btn-info" onclick="javascript:BatchSale.output();">导出批量成销数据
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" id="btn-imput" class="btn btn-primary" onclick="javascript:BatchSale.imput();">导入批量成销数据
        </button>
        <br/><br/>
        成销房源列表(<span style="color: red;">批量成销操作不适用多返佣对象的房源</span>)
        <form id="batchSaleFrom">
            <input id="projectNo" name="projectNo" value="${projectNo}" type="hidden">
            <input id="estateNm" name="estateNm" value="${estateNm}" type="hidden">
            <input id="estateId" name="estateId" value="${estateId}" type="hidden">
        </form><%--
        <div style="overflow-x: auto; overflow-y: auto;">--%>
        <div id="divReport" style="height: auto;width: 100%">
            <table id="myTable" class="table table-bordered table-hover table-striped text-center" style="width: 1140px;font-size: 12px">
                <tr>
                    <td style="width: 50px;">序号</td>
                    <td style="width: 150px;">报备编号</td>
                    <td style="width: 150px;">楼室号</td>
                    <td style="width: 80px;">客户姓名<font color="red">*</font></td>
                    <td style="width: 120px;">客户手机号<font color="red">*</font></td>
                    <td style="width: 80px;">客户姓名</td>
                    <td style="width: 120px;">客户手机号</td>
                    <td style="width: 100px;">成销面积(㎡)<font color="red">*</font></td>
                    <td style="width: 150px;">成销金额(元)<font color="red">*</font></td>
                    <td style="width: 180px;">核算主体<font color="red">*</font></td>
                    <td style="width: 120px;">应计收入税前(元)<font color="red">*</font></td>
                    <td style="width: 120px;">应计收入税后(元)<font color="red">*</font></td>
                    <td style="width: 120px;">应计返佣税前(元)<font color="red">*</font></td>
                    <td style="width: 120px;">应计返佣税后(元)<font color="red">*</font></td>
                    <td style="width: 120px;">应计垫佣税前(元)</td>
                    <td style="width: 120px;">应计垫佣税后(元)</td>
                    <td style="width: 70px;">操作</td>
                </tr>

                <c:if test="${batchSale.batchSaleDetails!=null}">
                    <c:forEach var="list" items="${batchSale.batchSaleDetails}" varStatus="status">
                        <tr>
                            <td>${ status.index + 1}</td>
                            <td>${list.reportId}</td>
                            <%--<td title="${list.floor}">
                                    ${fn:substring(list.floor, 0, 8)}
                                <c:if test="${fn:length(list.floor) >= '8'}">
                                    ...
                                </c:if>
                            </td>--%>
                            <td>${list.floor}</td>
                            <td>
                                <input id="batchDetailId_${status.index}" type="hidden" value="${list.batchDetailId}">
                                <input id="reportId_${status.index}" type="hidden" value="${list.reportId}">
                                <input id="batchId_${status.index}" type="hidden" value="${list.batchId}"/>
                                <input id="companyNo_${status.index}" type="hidden" value="${list.companyNo}"/>
                                <input id="floor_${status.index}" type="hidden" value="${list.floor}"/>
                                <input id="customerName1_${status.index}" style="width: 100%;" type="text" value="${list.customerName1}"/></td>
                            <td><input id="customerPhone1_${status.index}" style="width: 100%;" type="text" value="${list.customerPhone1}"/></td>
                            <td><input id="customerName2_${status.index}" style="width: 100%;" type="text" value="${list.customerName2}"/></td>
                            <td><input id="customerPhone2_${status.index}" style="width: 100%;" type="text" value="${list.customerPhone2}"/></td>
                            <td>
                                <input id="saleAcreage_${status.index}" style="width: 100%;text-align: right;" onkeyup="sumAcreageAndAmountNew()" type="text" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                       onchange="formatter(this)"
                                        <c:if test="${list.saleAcreage!=null}">
                                            value="<fmt:formatNumber value='${list.saleAcreage}' pattern='#0.00' maxFractionDigits='2'/>"
                                        </c:if>
                                        <c:if test="${list.saleAcreage==null}">
                                            value=""
                                        </c:if>
                                />
                            </td>
                            <td>
                                <input id="saleAmount_${status.index}" style="width: 100%;text-align: right;" onkeyup="sumAcreageAndAmountNew()" type="text" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                       onchange="formatter(this)"
                                        <c:if test="${list.saleAmount!=null}">
                                            value="<fmt:formatNumber value='${list.saleAmount}' pattern='#0.00' maxFractionDigits='2'/>"
                                        </c:if>
                                        <c:if test="${list.saleAmount==null}">
                                            value=""
                                        </c:if>
                                />
                            </td>
                            <td><input id="accountProject_${status.index}" style="width: 100%;" type="text" value="${list.accountProject}"/></td>
                            <td>
                                <input id="befYjsrTaxAmount_${status.index}" style="width: 100%;text-align: right;" onkeyup="sumAcreageAndAmountNew()" type="text" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                       onchange="formatter(this)"
                                        <c:if test="${list.befYjsrTaxAmount!=null}">
                                            value="<fmt:formatNumber value='${list.befYjsrTaxAmount}' pattern='#0.00' maxFractionDigits='2'/>"
                                        </c:if>
                                        <c:if test="${list.befYjsrTaxAmount==null}">
                                            value=""
                                        </c:if>
                                />

                            </td>
                            <td>
                                <input id="aftYjsrTaxAmount_${status.index}" style="width: 100%;text-align: right;" onkeyup="sumAcreageAndAmountNew()" type="text" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                       onchange="formatter(this)"
                                        <c:if test="${list.aftYjsrTaxAmount!=null}">
                                            value="<fmt:formatNumber value='${list.aftYjsrTaxAmount}' pattern='#0.00' maxFractionDigits='2'/>"
                                        </c:if>
                                        <c:if test="${list.aftYjsrTaxAmount==null}">
                                            value=""
                                        </c:if>
                                />
                            </td>
                            <td>
                                <input id="befYjfyTaxAmount_${status.index}" style="width: 100%;text-align: right;" onkeyup="sumAcreageAndAmountNew()" type="text" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                       onchange="formatter(this)"
                                        <c:if test="${list.befYjfyTaxAmount!=null}">
                                            value="<fmt:formatNumber value='${list.befYjfyTaxAmount}' pattern='#0.00' maxFractionDigits='2'/>"
                                        </c:if>
                                        <c:if test="${list.befYjfyTaxAmount==null}">
                                            value=""
                                        </c:if>
                                />
                            </td>
                            <td>
                                <input id="aftYjfyTaxAmount_${status.index}" style="width: 100%;text-align: right;" onkeyup="sumAcreageAndAmountNew()" type="text" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                       onchange="formatter(this)"
                                        <c:if test="${list.aftYjfyTaxAmount!=null}">
                                            value="<fmt:formatNumber value='${list.aftYjfyTaxAmount}' pattern='#0.00' maxFractionDigits='2'/>"
                                        </c:if>
                                        <c:if test="${list.aftYjfyTaxAmount==null}">
                                            value=""
                                        </c:if>
                                />
                            </td>
                            <td>
                                <input id="befYjdyTaxAmount_${status.index}" style="width: 100%;text-align: right;" onkeyup="sumAcreageAndAmountNew()" type="text" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                       onchange="formatter(this)"
                                        <c:if test="${list.befYjdyTaxAmount!=null}">
                                            value="<fmt:formatNumber value='${list.befYjdyTaxAmount}' pattern='#0.00' maxFractionDigits='2'/>"
                                        </c:if>
                                        <c:if test="${list.befYjdyTaxAmount==null}">
                                            value=""
                                        </c:if>
                                />
                            </td>
                            <td>
                                <input id="aftYjdyTaxAmount_${status.index}" style="width: 100%;text-align: right;" onkeyup="sumAcreageAndAmountNew()" type="text" oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')"
                                       onchange="formatter(this)"
                                        <c:if test="${list.aftYjdyTaxAmount!=null}">
                                            value="<fmt:formatNumber value='${list.aftYjdyTaxAmount}' pattern='#0.00' maxFractionDigits='2'/>"
                                        </c:if>
                                        <c:if test="${list.aftYjdyTaxAmount==null}">
                                            value=""
                                        </c:if>
                                />
                            </td>
                            <td><a href="javascript:void(0)" onclick="BatchSale.deleteBatchSaleDetail(this,'${list.batchDetailId}','${list.batchId}')" class="btn btn-link">删除</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                <%--<td colspan="3" style="border-right: 1px solid #fff;"></td>
                <td colspan="4" style="text-align: left;">合计</td>--%>
                <td style="border-right: 1px solid #fff;"></td>
                <td style="border-right: 1px solid #fff;"></td>
                <td style="border-right: 1px solid #fff;"></td>
                <td style="text-align: left;border-right: 1px solid #fff;font-weight:bold">合计</td>
                <td style="border-right: 1px solid #fff;"></td>
                <td style="border-right: 1px solid #fff;"></td>
                <td></td>
                <td style="text-align: right;"><span id="countAcreage">0.00</span></td>
                <td style="text-align: right;"><span id="countAmount">0.00</span></td>
                <td></td>
                <td style="text-align: right;"><span id="countBefYjsr">0.00</span></td>
                <td style="text-align: right;"><span id="countAftYjsr">0.00</span></td>
                <td style="text-align: right;"><span id="countBefYjfy">0.00</span></td>
                <td style="text-align: right;"><span id="countAftYjfy">0.00</span></td>
                <td style="text-align: right;"><span id="countBefYjdy">0.00</span></td>
                <td style="text-align: right;"><span id="countAftYjdy">0.00</span></td>
                <td></td>

                <input type="hidden" id="listSize" value="${fn:length(batchSale.batchSaleDetails)}"/>
                <input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">

            </table>
        </div>
        <c:if test="${fn:length(batchSale.batchSaleDetails) le 0}">
            <div class="nodata">
                <i class="icon-hdd"></i>
                <p>暂无数据...</p>
            </div>
        </c:if>
    <div style="width: 100%;height:50px;margin-top: 20px; ">
        <div style="width: 40%;float: left;">
            <label class="control-label" style="float: left;line-height: 34px;font-weight:normal"><span style="color: red;line-height: 34px;">*</span>成销日期:</label>
            <input  style="float: left;width: 30%;" type="text" class="calendar-icon form-control w100" name="saleDate" id="saleDate" value="${sdk:ymd2(batchSale.saleDate)}" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'${yearMonth}',maxDate:'%y-%M-%d'})" readonly="readonly" class="ipttext Wdate"/>
            <span class="fc-warning" id="saleDateMsg" style="margin-left: 11px;line-height: 30px;"></span>
        </div>
        <div style="width: 40%;float: left;">
            <label class="control-label" style="float: left;margin-right: 3%;line-height: 34px;margin-left: 15%;font-weight:normal;">结算确认日期:</label>
        <input style="float: left;width: 30%;" type="text" class="calendar-icon form-control w100" name="settlementDate" id="settlementDate" value="${sdk:ymd2(batchSale.settlementDate)}" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" readonly="readonly" class="ipttext Wdate"/>
        </div>
    </div>
        <div class="container theme-hipage ng-scope" role="main" style="margin-bottom: -30px;">
            <p><strong style="font-size:18px;">附件</strong></p>
        </div>

        <div class="container theme-hipage ng-scope" role="main">
            <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title" style="font-weight:normal;">
                       <span style="color: red">*</span>成销确认书/佣金结算资料&nbsp;&nbsp;<span style="color: red" id="fileRecordMsg"></span>
                    </h4>
                    <div class="thumb-xs-box" id="fileIdPhotoToDeal">
                        <c:if test="${batchSale.fileList != null }">
                            <c:set  var="fileSize" value="0"/>
                            <c:forEach items="${batchSale.fileList}" var="list" varStatus="status">
                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                <div class="thumb-xs-list item-photo-list">
                                    <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="成销确认书" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
                                        <span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
                                    </a>
                                    <input type="hidden" name="limitSize" value="10">
                                    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
                                    <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                    <input type="hidden" name="fileTypeId" value="1025" />
                                    <input type="hidden" name="fileSourceId" value="5" />
                                </div>
                            </c:forEach>
                        </c:if>
                        <div class="thumb-xs-list item-photo-add"
                             <c:if test="${batchSale.fileList != null && batchSale.fileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file"
                                <c:if test="${fn:length(batchSale.batchSaleDetails) le 0}"> disabled="disabled" </c:if>
                                       class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
                                <input type="hidden" name="fileTypeId" value="1025" />
                                <input type="hidden" name="fileSourceId" value="5" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>


</div>
        <div style="width: 100%;text-align: center;margin-top: 90px;">
            <a id="submitBut" href="javascript:BatchSale.submitBatchSale()"
               class="btn btn-primary" style="margin-right: 2%;width: 8%;"
                    <c:if test="${fn:length(batchSale.batchSaleDetails) le 0}"> disabled="disabled" </c:if>
            >提交</a>
            <a id="saveBut" href="javascript:BatchSale.updateBatchSaleDetailAll()"
               class="btn btn-info" style="margin-right: 2%;width: 8%;"
                    <c:if test="${fn:length(batchSale.batchSaleDetails) le 0}"> disabled="disabled" </c:if>
            >保存</a>
            <a href="${ctx}/sceneEstate/qSceneRecognition/${estateId}" class="btn btn-default" style="width: 8%">取消</a>
        </div>
        <br/>
        <br/>
    </div>
</div>
</div>
<form name="imputForm" id="imputForm" method="post" action="${ctx}/sceneEstate/imput"
      target="hiddenFrame" enctype="multipart/form-data">
    <input type="file" id="historyDataFile" name="historyDataFile" accept=".xls,.xlsx" style="display:none">
    <input type="hidden" id="estateTypeImput" name="estateTypeImput">
</form>
<iframe name="hiddenFrame" id="hiddenFrame" style="display:none">
</iframe>
</body>
<script>
    $(function () {
        sumAcreageAndAmount();
        var options = {
            "url":BASE_PATH + '/file/uploadCommonFile',
            "isDeleteImage":false,//删除时校验checkEditImage
            "isAddImage":false,   //添加时校验checkEditImage
            "isCommonFile":true  //公共上传文件
        };
        photoUploader(options,null,null,null);

         var width = $("#divReport").width;
         var height = $("#divReport").height();
         FixTable("myTable", 3, 1140, height+40);
         $("#myTable_tableColumnClone tr:last").hide()
    });
</script>
</html>