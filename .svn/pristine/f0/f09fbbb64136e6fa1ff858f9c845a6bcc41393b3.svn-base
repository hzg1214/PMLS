<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
    <link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/cssreport/bootstrap-select.css?_v=${vs}">
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/expand/bootstrap-select.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/otherReport/qtSuccessSaleHandle.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-multiselect.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/bootstrap-multiselect.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/jquery-ui.css?_v=${vs}">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 14px;
        }

        table {
            font-size: 1em;
        }

        .ui-draggable, .ui-droppable {
            background-position: top;
        }
        .ui-autocomplete-loading {
            background: white url("http://jqueryui.com/resources/demos/autocomplete/images/ui-anim_basic_16x16.gif") right center no-repeat;
        }
        .ui-autocomplete {
            max-height: 258px;
            overflow-y: auto;
            overflow-x: hidden;
            background-color: #EEEEEE;
            width: 540px;
            padding-left: 0px;
        }
        .w125x{
            width:160px!important;
            margin-right:5px
        }
        .select2-container--open{
            z-index:10000;
            font-size:14px;
        }
        .select2-results__options{
            max-height:322px!important;
        }
        .autocomplete-suggestions {font-size: 12px; -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box; border: 1px solid #999; background: #FFF; cursor: default; overflow: auto; -webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); -moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); }
        .autocomplete-suggestion { padding: 2px 5px; white-space: nowrap; overflow: hidden; }
        .autocomplete-no-suggestion { padding: 2px 5px;}
        .autocomplete-selected { background: #F0F0F0; }
        .autocomplete-suggestions strong { font-weight: bold; color: #000; }
        .autocomplete-group { padding: 2px 5px; }
        .autocomplete-group strong { font-weight: bold; font-size: 14px; color: #000; display: block; border-bottom: 1px solid #000; }


        .retCommission tr {
            font-size: 12px;
            height: 32px;
        }

        .retCommission input {
            width: 98%;
            height: 28px;
            text-align: right;
        }
    </style>

</head>
<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<form id="qtSuccessSaleHandle">
    <input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">
    <input type="hidden" id="objCount" value="0">
    <input type="hidden" id="id" name="id" value="${qtReportInfo.id}">
    <input type="hidden" id="projectNo" value="${qtReportInfo.estate.projectNo}">
    <div class="container theme-hipage ng-scope" role="main">
        <div class="crumbs">
            <ul style="margin-right:150px;">
                <li><a href="#" class="a_hover">联动管理</a></li>
                <li><a href="#" class="a_hover">>其他收入</a></li>
                <li><a href="#" class="a_hover">>成销</a></li>
            </ul>
        </div>
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>成销</strong></h4>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <strong>
                                <label class="fw-normal w100 text-right"><strong>项目编号：</strong></label>${qtReportInfo.estate.projectNo}
                                <label class="fw-normal w100 text-right"><strong>楼盘名称：</strong></label>${qtReportInfo.estate.estateNm}
                            </strong>
                        </div>
                    </li>
                </ul>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">合作方：</label>${qtReportInfo.estate.partnerNm}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="srType"><i>* </i>收入类型：</label>
                            <select style="width:200px;" class="form-control" name="srType" id="srType">
                                <option value="">请选择</option>
                                <option value="27201" <c:if test="${qtReportInfo.srType eq '27201'}">selected</c:if>>
                                    带看奖
                                </option>
                                <option value="27202" <c:if test="${qtReportInfo.srType eq '27202'}">selected</c:if>>
                                    成交奖
                                </option>
                                <option value="27203" <c:if test="${qtReportInfo.srType eq '27203'}">selected</c:if>>
                                    广告咨询费
                                </option>
                                <option value="27204" <c:if test="${qtReportInfo.srType eq '27204'}">selected</c:if>>
                                    拓客服务费
                                </option>
                                <option value="27205" <c:if test="${qtReportInfo.srType eq '27205'}">selected</c:if>>
                                    其他
                                </option>
                            </select>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="dealAmount"><i>* </i>成销金额：</label>
                            <input type="text" class="form-control w200" name="dealAmount"
                                   id="dealAmount" placeholder="" notnull="true"
                                   maxlength="12" dataType="needMoney"
                                   oninput="this.value=this.value.replace(/\s+/g,'')">元
                            <span class="fc-warning"></span>
                        </div>
                    </li>

                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="dealDate"><i>* </i>成销日期：</label>
                            <input type="text" class="calendar-icon w200" style="width: 200px;" name="dealDate"
                                   id="dealDate"
                                   onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                   class="ipttext Wdate" notnull="true"/>
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>
                </ul>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="befYJSRAmount"><i>* </i>应计收入税前：</label>
                            <input type="text" class="form-control w200" name="befYJSRAmount"
                                   id="befYJSRAmount" placeholder="" notnull="true"
                                   maxlength="12" dataType="needMoney"
<%--                                    value='${qtReportInfo.srAmount}' --%>
                                   value='<fmt:formatNumber value="${qtReportInfo.srAmount}" pattern="###0.00"/>'
                                   oninput="this.value=this.value.replace(/\s+/g,'')">元
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="aftYJSRAmount"><i>* </i>应计收入税后：</label>
                            <input type="text" class="form-control w200" name="aftYJSRAmount"
                                   id="aftYJSRAmount" placeholder="" notnull="true"
                                   maxlength="12" dataType="needMoney"
                                   oninput="this.value=this.value.replace(/\s+/g,'')">元
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="befYJFYAmount"><i>* </i>应计返佣税前：</label>
                            <input type="text" class="form-control w200" name="befYJFYAmount"
                                   id="befYJFYAmount" placeholder="" notnull="true"
                                   maxlength="12" dataType="needMoney"
                                   oninput="this.value=this.value.replace(/\s+/g,'')">元
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="aftYJFYAmount"><i>* </i>应计返佣税后：</label>
                            <input type="text" class="form-control w200" name="aftYJFYAmount"
                                   id="aftYJFYAmount" placeholder="" notnull="true"
                                   maxlength="12" dataType="needMoney"
                                   oninput="this.value=this.value.replace(/\s+/g,'')">元
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>


                <div class="form-group" style="display:inline-block;width: 47%;">
                    <input type="hidden" id="oldAccCityNo" value="${qtReportInfo.accCityNo}"/>
                    <input type="hidden" name="accCityNo" id="accCityNo" value="${qtReportInfo.accCityNo}"/>
                    <label class="fw-normal w120 text-right"><i>*</i>业绩归属城市：</label>
                    <div id="divAccCitySelect" style="display:inline-block;width:200px;">
                        <select class="form-control selectpicker" title="" id="accCitySelect"  notnull="true"
                                style="width:200px;" data-live-search="true"></select>
                    </div>
                    <span class="fc-warning"></span>
                </div>
                <div class="form-group"  style="display:inline-block;width: 47%;">
                    <input type="hidden" id="oldCenterId" value="${qtReportInfo.centerId}"/>
                    <input type="hidden" name="centerId" id="centerId" value="${qtReportInfo.centerId}"/>
                    <input type="hidden" name="centerName" id="centerName" value="${qtReportInfo.centerName}"/>
                    <label class="fw-normal w150 text-right"><i>*</i>业绩归属中心：</label>
                    <div style="display:inline-block; vertical-align: middle;">
                        <select class="form-control selectpicker1" title="" id="centerSelect" notnull="true"
                                style="width:200px;"></select>
                    </div>
                    <span class="fc-warning"></span>
                </div>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <input type="hidden" name="accountProject" id="accountProject" value=""/>
                            <label class="fw-normal w120 text-right"><i>*</i>核算主体：</label>
                            <select class="form-control" title="" id="accountProjectNo" name="accountProjectNo"
                                    style="width:200px;">
                                <option value="" selected="selected">请选择</option>
                                <c:forEach items="${accountProjectList}" var="acc">
                                    <option value="${acc.accountProjectNo}"
                                            <c:if test="${qtReportInfo.accountProjectNo == acc.accountProjectNo}">selected</c:if>>
                                            ${acc.accountProjectNo}_${acc.accountProject}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </li>
                </ul>


            </div>

            <div style="padding-left: 10px;">
                <input type="hidden" name="fyList" id="fyList" value=""/>
                <h4 style="font-size:14px">
                    <strong>返佣对象维护</strong><img style="width: 20px;height: 20px;margin-left: 30px" onclick="javascript:addObj()" src="${ctx}/meta/images/crm/plus.png">
                </h4>
                <table class="retCommission" border="1" style=" border:#ddd 1px solid; width:1000px">
                    <colgroup>
                        <col width="500px">
                        <col width="150px">
                        <col width="150px">
                        <col width="100px">
                    </colgroup>
                    <thead>
                    <tr>
                        <td align="center">返佣对象</td>
                        <td align="center">应计返佣税前（元）</td>
                        <td align="center">应计返佣税后（元）</td>
                        <td align="center">操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>

                </table>
                <div id="errorMsg" style="color: red;margin-bottom: 5px;visibility: hidden;height: 20px;"></div>
            </div>

            <div class="page-content" style="padding-left: 10px;">
                <h4 style="font-size:14px">
                    <strong>附件</strong>
                </h4>
            </div>
            <table class="table-sammary" name="Viewerbox">
                <col style="width:145px;">
                <col style="width:auto">
                <tr>
                    <td colspan="2">
                        <div class="" role="main">
                            <div>
                                <div class="pd10">
                                    <h4 class="thumb-title">
                                        <i>*</i>成销确认书/佣金结算资料
                                    </h4>
                                    <div class="thumb-xs-box" id="fileIdPhotoToDeal">
                                        <div class="thumb-xs-list item-photo-add">
                                            <input type="hidden" name="limitSize" value="10">
                                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                                <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-deal" multiple="multiple">
                                                <input type="hidden" name="fileTypeId" value="1025"/>
                                                <input type="hidden" name="fileSourceId" value="20"/>
                                                <input type="hidden" name="companyId"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="" role="main">
                            <div>
                                <div class="pd10">
                                    <h4 class="thumb-title">
                                        <i>*</i>返佣确认函
                                    </h4>
                                    <div class="thumb-xs-box" id="fileIdPhotoToFY">
                                        <div class="thumb-xs-list item-photo-add">
                                            <input type="hidden" name="limitSize" value="10">
                                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                                <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-fy" multiple="multiple">
                                                <input type="hidden" name="fileTypeId" value="1064"/>
                                                <input type="hidden" name="fileSourceId" value="20"/>
                                                <input type="hidden" name="companyId"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>

        </div>
    </div>


    <div class="text-center">
        <a href="javascript:successSale();" class="btn btn-primary" id="successSale">成销</a>
        <a href="javascript:backPre();" class="btn btn-primary mgl20">返回</a>
    </div>
</form>
</body>

</html>

<script type="text/javascript" src="${ctx}/meta/js/common/jquery-ui.js?_v=${vs}"></script>

