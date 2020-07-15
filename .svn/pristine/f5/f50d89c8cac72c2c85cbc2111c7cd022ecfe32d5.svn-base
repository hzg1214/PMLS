<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>成销</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .addressInputBlock  .layui-form-select{
            width: 100px;
        }
        .addressInputBlock .divInline{
            float:left;
            margin-right:10px;
        }
        .layui-treeSelect .ztree li span.button.switch{
            top:-7px;
        }

    </style>
</head>
<script type="application/javascript">
    var qtReportInfoJson='${qtReportInfoJson}';//商户信息
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">成销</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>基本信息</legend>
            </fieldset>
            <div class="layui-form myForm" style="margin-top:20px;">
                <input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">
<%--                <input type="hidden" id="objCount" value="0">--%>
                <input type="hidden" id="id" name="id" value="${qtReportInfo.id}">
                <input type="hidden" id="projectNo" value="${qtReportInfo.estate.projectNo}">



                <input type="hidden" name="countDateEndStr" id="countDateEndStr" value="${countDateEndStr}">

                <label class="layui-form-label"><b>项目编号：${qtReportInfo.estate.projectNo}</b></label>
                <label class="layui-form-label"
                       style=" width: 500px!important; text-align: left"><b>楼盘名称：${qtReportInfo.estate.estateNm}</b></label>


                <div class="layui-form-item">
                    <label class="layui-form-label">合作方</label>
                    <label class="layui-form-label" style="text-align: left;width: 500px!important;">${qtReportInfo.estate.partnerNm}</label>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>收入类型</label>
                    <div class="layui-input-block">
                        <select id="srType" name="srType" lay-verify="srType" lay-filter="srType">
                            <option value="">请选择</option>
                            <option value="27201" <c:if test="${qtReportInfo.srType eq '27201'}">selected</c:if>>带看奖</option>
                            <option value="27202" <c:if test="${qtReportInfo.srType eq '27202'}">selected</c:if>>成交奖</option>
                            <option value="27203" <c:if test="${qtReportInfo.srType eq '27203'}">selected</c:if>>广告咨询费</option>
                            <option value="27204" <c:if test="${qtReportInfo.srType eq '27204'}">selected</c:if>>拓客服务费</option>
                            <option value="27205" <c:if test="${qtReportInfo.srType eq '27205'}">selected</c:if>>其他</option>
                        </select>
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>成销金额</label>
                    <div class="layui-input-block">
                        <input type="text" id="dealAmount" name="dealAmount" lay-verify="dealAmount" maxlength="12"
                               oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')" autocomplete="off"   lay-filter="dealAmount" class="layui-input" placeholder="请输入">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>成销日期</label>
                    <div class="layui-input-block">
                        <input type="text" id="dealDate" name="dealDate" lay-verify="required"
                               autocomplete="off" lay-filter="dealDate" class="layui-input" placeholder="请选择">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>应计收入税前</label>
                    <div class="layui-input-block">
                        <input type="text" id="befYJSRAmount" name="befYJSRAmount" lay-verify="befYJSRAmount" maxlength="12"
                               value='<fmt:formatNumber value="${qtReportInfo.srAmount}" pattern="###0.00"/>'
                               oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')" autocomplete="off"   lay-filter="befYJSRAmount" class="layui-input" placeholder="请输入">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>应计收入税后</label>
                    <div class="layui-input-block">
                        <input type="text" id="aftYJSRAmount" name="aftYJSRAmount" lay-verify="aftYJSRAmount" maxlength="12"
                               oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')" autocomplete="off"   lay-filter="aftYJSRAmount" class="layui-input" placeholder="请输入">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>应计返佣税前</label>
                    <div class="layui-input-block">
                        <input type="text" id="befYJFYAmount" name="befYJFYAmount" lay-verify="befYJFYAmount" maxlength="12"
                               oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')" autocomplete="off"   lay-filter="befYJFYAmount" class="layui-input" placeholder="请输入">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>应计返佣税后</label>
                    <div class="layui-input-block">
                        <input type="text" id="aftYJFYAmount" name="aftYJFYAmount" lay-verify="aftYJFYAmount" maxlength="12"
                               oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')" autocomplete="off"   lay-filter="aftYJFYAmount" class="layui-input" placeholder="请输入">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>业绩归属城市</label>
                    <div class="layui-input-block">
                        <input type="hidden" id="oldAccCityNo" value="${qtReportInfo.accCityNo}"/>
                       <%-- <input type="hidden" name="accCityNo" id="accCityNo" value="${qtReportInfo.accCityNo}"/>--%>
                        <select id="accCitySelect" name="accCitySelect" lay-verify="accCitySelect"
                                lay-filter="accCitySelect" lay-search="">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>业绩归属中心</label>
                    <div class="layui-input-block">
                        <input type="hidden" id="oldCenterId" value="${qtReportInfo.centerId}"/>
                    <%--    <input type="hidden" name="centerId" id="centerId" value="${qtReportInfo.centerId}"/>
                        <input type="hidden" name="centerName" id="centerName" value="${qtReportInfo.centerName}"/>--%>
                        <select id="centerSelect" name="centerSelect" lay-verify="centerSelect"
                                lay-filter="centerSelect" lay-search="">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>收入中心</label>
                    <div class="layui-input-block">
                        <input type="hidden" id="oldSrCenterId" value="${qtReportInfo.srCenterId}"/>
                        <select id="srCenterId" name="srCenterId" lay-verify="srCenterId"
                                lay-filter="srCenterId" lay-search="">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>核算主体</label>
                    <div class="layui-input-block">
<%--                        <input type="hidden" name="accountProject" id="accountProject" value=""/>--%>
                        <select id="accountProjectNo" name="accountProjectNo" lay-verify="accountProjectNo"
                                lay-filter="accountProjectNo" lay-search="">
                            <option value="">请选择</option>
                            <c:forEach items="${accountProjectList}" var="acc">
                                <option value="${acc.accountProjectNo}"
                                        <c:if test="${qtReportInfo.accountProjectNo == acc.accountProjectNo}">selected</c:if>
                                >${acc.accountProjectNo}_${acc.accountProject}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <input type="hidden" name="fyList" id="fyList" value=""/>
               <%--     <button class="layui-btn" id="btnAddFydx" data-type="btnAddFydx">
                        返佣对象维护
                    </button>--%>
                    <strong>返佣对象维护</strong><img style="width: 20px;height: 20px;margin-left: 10px" onclick="javascript:addCol()" src="${ctx}/meta/pmls/images/plus.png">
                    <table id="contentTable" lay-size="sm" lay-filter="content"></table>
                </div>

            </div>
        </div>
    </div>


    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>附件</legend>
            </fieldset>
            <div class="layui-form enclosureForm">
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>成销确认书/佣金结算资料</label>
                    <div class="layui-input-block">
                        <div class="layui-upload" id="fileSuccSalelist">
                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <span style="color: red">注：上传成销确认书/佣金结算资料。</span>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list" id="upload_img_SuccSalelist"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan"></span>返佣确认函</label>
                    <div class="layui-input-block">
                        <div class="layui-upload" id="fileSuccSalelistQrh">
                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <span style="color: red">注：上传返佣确认函。</span>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list" id="upload_img_SuccSaleQrh"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
            </div>
            <div class="operationPageToolbar">
                <button type="button" class="layui-btn" onclick="successSale()">提交</button>
                <button type="button" class="layui-btn layui-btn-primary" onclick="back()">取消</button>
            </div>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/otherReport/qtSuccessSaleHandle.js?v=${vs}"></script>
</body>
</html>
