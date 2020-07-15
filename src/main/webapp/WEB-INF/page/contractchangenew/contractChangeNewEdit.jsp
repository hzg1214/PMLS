<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateCompany.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/contractchangenew/contractChangeNew.js?_v=${vs}"></script>
<style type="text/css">
    .text-overflow {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 100%;
    }
</style>
</head>
<body>
    <!-- 头部 -->
    <jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
        <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
    </jsp:include>
    <div class="container">
        <div class="row article">
            <!-- 左侧菜单 -->
            <jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
                flush="true">
                <jsp:param value="110404" name="leftMenuSelectId" />
            </jsp:include>
            <div class="col-md-10 content">
                <div class="page-content">
                <br>
                    <h4>
                        <strong>修改合同变更</strong>
                        <a href="${ctx}/contractChangeNew/list/${contractId}/${contractStatus}" class="btn btn-primary">返回</a>
                    </h4>
                    <div class="" style="height: auto;">
                        <form id="contractChangeForm">
                            <input type="hidden" name="id" value="${contractChange.id}">
                            <input type="hidden" name="contractId" id="contractId" value="${contractId}">
                            <input type="hidden" name="contractStatus" id="contractStatus" value="${contractStatus}">
                            <input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
                            <ul class="list-inline half form-inline">
                                <li>
                                    <div class="form-group">
                                        <label class="fw-normal w140  text-right" style="vertical-align: top;float:left">合同变更申请编号：</label>
                                        ${contractChange.contractStopNo}
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline half form-inline">
                                <li>
                                    <div class="form-group">
                                        <label class="fw-normal w140  text-right" style="vertical-align: top;float:left">变更合同编号：</label>
                                        ${contractChange.contractNo}
                                    </div>
                                </li>
                            </ul>

                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" style="padding-left:10px;">
                                        <label class="fw-normal w125 text-right">变更类型</label>
                                        <span style="padding-left:5px;">三方变更</span>
                                        <input type="hidden" name="changeType" value="17003"/>
                                    </div>
                                </li>
                            </ul>
                        
                            <ul class="list-inline form-inline" >
                                <li style="padding-left:15px;">
                                    <div class="form-group" id="checkbox" >
                                        <label class="fw-normal w125 text-right" ><i>* </i>三方变更类型</label>
                                        <span style="padding-left:5px;"><input type="radio" name="threePartChangeType" value="23001" <c:if test="${contractChange.threePartChangeType eq 23001}">checked</c:if>/>甲转甲</span>
                                        <span style="padding-left:15px;"><input type="radio" name="threePartChangeType" value="23002" <c:if test="${contractChange.threePartChangeType eq 23002}">checked</c:if>/>甲转乙</span>
                                        <span style="padding-left:15px;"><input type="radio" name="threePartChangeType" value="23003" <c:if test="${contractChange.threePartChangeType eq 23003}">checked</c:if>/>乙转乙</span>
                                    </div> 
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left: 15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">转让方</label>
                                        <input type="hidden" name="oldUpdateCompanyName" value="${contractChange.oldUpdateCompanyName}">
                                        <input type="hidden" name="oldCompanyId" id="oldCompanyId" value="${contractChange.oldCompanyId}"/>
                                        <span style="padding-left:5px;">${contractChange.oldUpdateCompanyName}</span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            <ul  class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>受让方</label>
                                        <span style="padding-left:5px;">
                                             <input type="hidden" id="newCompanyId" name="newCompanyId" value="${contractChange.newCompanyId}">
                                             <input type="text" class="form-control w300" readonly id="newCompanyName" name="newCompanyName" value="${contractChange.newCompanyName}" placeholder="" style="background-color: #F9F9F9;" notnull="true">
                                             <button type="button" class="btn btn-primary"  onclick="javascript:ContractB2A.popCompany({},ContractB2A.companyPopCallBack);" style="vertical-align: top;">选择公司</button>
                                             </span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">受让方法定代表人</label>
                                        <span style="padding-left:5px;">
                                             <input type="text" style="background-color: #F9F9F9;" readonly class="form-control w300" value="${contractChange.newLegalPerson}" readonly id="newLegalPerson" name="newLegalPerson" placeholder="" notnull="true"  maxlength="20">
                                        </span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group" >
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">受让方注册地址</label>
                                        <span style="padding-left:5px;">
                                            <input type="hidden" id="newCompanyAddressCityNo" name="newCompanyAddressCityNo" value="${contractChange.newCompanyAddressCityNo}">
                                            <input type="hidden" id="newCompanyAddressDistrictNo" name="newCompanyAddressDistrictNo" value="${contractChange.newCompanyAddressDistrictNo}">
                                            <input type="hidden" id="newCompanyAddress" name="newCompanyAddress" value="${contractChange.newCompanyAddress}">
                                            <input type="text" style="background-color: #F9F9F9;" class="form-control w300" id="address3" value="${contractChange.newCompanyAddressDetail}" readonly>
                                        </span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>合作模式</label>
                                       <input type="hidden" name="contractType" value="${contractChange.contractType}">
                                       <span style="padding-left:5px;"><input type="radio" disabled value="10301" <c:if test="${contractChange.contractType eq 10301}">checked</c:if>/>A</span>
                                       <span style="padding-left:15px;"><input type="radio" disabled value="10302" <c:if test="${contractChange.contractType eq 10302}">checked</c:if>/>B</span>
                                       <span style="padding-left:15px;"><input type="radio" disabled value="10304" <c:if test="${contractChange.contractType eq 10304}">checked</c:if>/>A转B</span>
                                       <span class="fc-warning"></span>
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">合作协议书编号</label>
                                        <span>
                                            <input value="${contractChange.newAgreementNo}" type="text" style="background-color: #F9F9F9;" class="form-control w300" readonly name="newAgreementNo" notnull="true" maxlength="20">
                                        </span>
                                   </div>
                                   <div class="form-group" style="padding-left: 50px;">
                                        <label><i>* </i>合作周期</label>
                                         <div class="input-group date">
                                             <fmt:parseDate value="${contractChange.newDateLifeStart}" var="newDateLifeStart" pattern="yyyy-MM-dd HH:mm:ss"/>
                                             <fmt:parseDate value="${contractChange.dateLifeStart}" var="dateLifeStartPre" pattern="yyyy-MM-dd HH:mm:ss"/>
                                             <input type="hidden" id="dateLifeStartPre" value='<fmt:formatDate value="${dateLifeStartPre}" pattern="yyyy-MM-dd" />'>
                                            <input type="text" class="calendar-icon form-control w100" name="newDateLifeStart"
                                               id="newDateLifeStart"
                                               value="<fmt:formatDate value="${newDateLifeStart}" pattern="yyyy-MM-dd" />"
                                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'newDateLifeEnd\');}',minDate:'#F{$dp.$D(\'dateLifeStartPre\');}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                               readonly="readonly" class="ipttext Wdate" style="width: 120px;" notnull="true"/>
                                         </div>
                                         <span>-</span>
                                         <div class="input-group date">
                                             <fmt:parseDate value="${contractChange.newDateLifeEnd}" var="dateLifeEnd" pattern="yyyy-MM-dd HH:mm:ss"/>
                                          <input type="text" class="calendar-icon form-control w100" name="newDateLifeEnd"
                                               id="newDateLifeEnd" value="<fmt:formatDate value="${dateLifeEnd}" pattern="yyyy-MM-dd" />"
                                               readonly="readonly" class="ipttext Wdate" style="width: 120px;"/>
                                         </div>
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                    <div class="form-group" >
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">保证金金额</label>
                                        <input value="${contractChange.totleDepositFee}" type="text" style="background-color: #F9F9F9;" class="form-control w300" readonly name="totleDepositFee">
                                    </div>
                                    <div class="form-group" style="padding-left: 60px;">
                                        <label>违约金额</label>
                                        <input value="${contractChange.penaltyFee}" type="text" style="background-color: #F9F9F9;" class="form-control w300" readonly name="penaltyFee" >
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>受让方授权代表</label>
                                        <span>
                                        <input type="text" class="form-control w300" value="${contractChange.authRepresentative}" id="authRepresentative" name="authRepresentative" placeholder="" required  notnull="true" maxlength="20">
                                        </span>
                                        <span class="fc-warning"></span>
                                   </div>
                                   <div class="form-group" style="padding-left: 50px;">
                                        <label><i>* </i>联系方式</label>
                                        <input type="text" class="form-control w300" value="${contractChange.agentContact}" id="agentContact" notnull="true" name="agentContact" placeholder="" maxlength="11" >
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">受让方公司账号</label>
                                        <input type="text" class="form-control w300" id="companyBankNo" value="${contractChange.companyBankNo}" name="companyBankNo" placeholder="" value=""  maxlength="50">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">开户银行</label>
                                        <input type="text" class="form-control w300" id="bankAccount" value="${contractChange.bankAccount}" name="bankAccount" placeholder="" value=""  maxlength="30">
                                   </div>
                                   <div class="form-group" style="padding-left: 70px;">
                                        <label>开户名</label>
                                        <input type="text" class="form-control w300" id="accountName" value="${contractChange.accountName}" name="accountName" placeholder="" value=""  maxlength="20">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">受让方收件人</label>
                                        <input type="text" class="form-control w300" id="recipients" value="${contractChange.recipients}" name="recipients" placeholder="" value=""  maxlength="20">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">受让方收件地址</label>
                                       <span class="control-select">
                                    <select class="form-control w120" title="" id="cityNo" name="cityNo">
                                          <option value="" selected="selected">请选择城市</option>
                                          <c:forEach items="${citylist}" var="city">
                                            <c:if test="${contractChange.cityNo eq city.cityNo}">
                                                <option value="${city.cityNo}" selected="selected">${city.cityName}</option>
                                            </c:if>
                                            <c:if test="${contractChange.cityNo ne city.cityNo}">
                                                <option value="${city.cityNo}">${city.cityName}</option>
                                            </c:if>
                                          </c:forEach>
                                    </select>
                                </span>
                                <span class="control-select">
                                    <select class="form-control w200"  id="districtNo" name="districtNo" data-value="${contractChange.districtNo}">
                                        <option selected="selected" value="" >请选区域</option>
                                    </select>
                                </span>
                                <input type="text" class="form-control w300" id="recipientsAddress" value="${contractChange.recipientsAddress}" name="recipientsAddress" placeholder="具体地址信息" value=""  maxlength="100">
                                   </div>
                                </li>
                            </ul>

                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">备注</label>
                                       <span style="padding-left:5px;"> <textarea maxlength="150" name="remarks" id="remarks" cols="30" rows="10"  style="resize: none;" >${contractChange.remarks}</textarea></span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            
                            <p><strong>门店信息</strong></p>
                            <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                                <tr>
                                    <th style="width: 100px;">门店编号</th>
                                    <th style="width: 150px;">门店名称</th>
                                    <th style="width: 180px;">门店地址</th>
                                    <th style="width: 120px;"><i>*</i>门店负责人</th>
                                    <th style="width: 120px;"><i>*</i>门店负责人电话</th>
                                    <th style="width: 120px;"><i>*</i>门店资质等级</th>
                                </tr>
                                <%--<c:forEach items="${storeList}" var="item" varStatus="listIndex">--%>
                                    <tr>
                                        <td>${contractChange.storeList[0].storeNo}</td>
                                        <td style="text-align:left;" title="${contractChange.storeList[0].name}" class="text-overflow">${contractChange.storeList[0].name}</td>
                                        <td style="text-align:left;" title="${contractChange.storeList[0].addressDetail}" class="text-overflow">${contractChange.storeList[0].addressDetail}</td>
                                        <td><input type="text" name="storeList[0].storeManager" value="${contractChange.storeList[0].storeManager}" style="width: 120px" notnull="true"> </td>
                                        <td><input type="text" name="storeList[0].storeManagerPhone" value="${contractChange.storeList[0].storeManagerPhone}" style="width: 120px" notnull="true"> </td>
                                        <td>
                                            <select id="storeAbType" name="storeList[0].ABTypeStore" style="width: 60px;" onchange="fnChangeAbTypeStore(this)">
                                                <c:if test="${contractChange.storeList[0].abtypeStore eq 19901}">
                                                    <option value="19901" selected>甲类</option>
                                                    <option value="19902">乙类</option>
                                                </c:if>
                                                <c:if test="${contractChange.storeList[0].abtypeStore eq 19902}">
                                                    <option value="19901">甲类</option>
                                                    <option value="19902" selected>乙类</option>
                                                </c:if>
                                            </select>
                                            <c:if test="${contractChange.storeList[0].abtypeStore eq 19902}">
                                                <input type="text" id="bTypeStore" readonly value="乙3" style="width: 60px;background-color: #F9F9F9;">
                                            </c:if>
                                            <c:if test="${contractChange.storeList[0].abtypeStore eq 19901}">
                                                <input type="text" id="bTypeStore" readonly value="乙3" style="width: 60px;background-color: #F9F9F9;display: none">
                                            </c:if>
                                        </td>
                                        <input type="hidden" name="storeList[0].contractId" value="${contractId}">
                                        <input type="hidden" name="storeList[0].storeId" value=${storeId}>
                                        <input type="hidden" name="storeList[0].contractStopId" value="${contractChange.storeList[0].contractStopId}">
                                        <input type="hidden" id="abTypeStore" value="${contractChange.storeList[0].oldAbtypeStore}">
                                    </tr>
                                <%--</c:forEach>--%>
                            </table>
                            
                    </form>
                    </div>
    
                    <div class="" role="main">
                        <p><strong>附件</strong></p>
                    </div>
                    <div class="" role="main">
                            <div class="row">
                                    <div class="pd10">
                                        <h4 class="thumb-title">
                                            <i>*</i>受让方营业执照
                                        </h4>
                                        <div class="thumb-xs-box" id="transfereeBox">
                                            <c:if test="${not empty contractChange.photosFileList }">
                                                <c:set var="fileSize1" value="0"/>
                                                <c:forEach items="${contractChange.photosFileList}"
                                                           var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1033}">
                                                        <c:set var="fileSize1" value="${fileSize1 + 1}"/>
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="${list.fileUrl}" class="thumbnail swipebox"
                                                               target="_blank">
                                                                <span class="thumb-img">
                                                                    <span class="thumb-img-container">
                                                                        <span class="thumb-img-content">
                                                                            <img alt="受让方营业执照" data-original="${list.url50}"
                                                                                 src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                        </span>
                                                                    </span>
                                                                    <span class="thumb-bottom-roup">
                                                                        <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                                                    </span>
                                                                </span>
                                                            </a>
                                                            <input type="hidden" name="limitSize" value="10">
                                                            <input type="file" id="file${fileSize1}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
                                                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                                            <input type="hidden" name="fileTypeId" value="1033" />
                                                            <input type="hidden" name="fileSourceId" value="10" />
                                                            <input type="hidden" name ="companyId" value="">
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <div class="thumb-xs-list item-photo-add" <c:if test="${fileSize1 >=10  }">style="display: none;"</c:if>>
                                                <input type="hidden" name="limitSize" value="10">
                                                <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                                    <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                                    <input type="hidden" name="fileTypeId" value="1033" />
                                                    <input type="hidden" name="fileSourceId" value="10" />
                                                    <input type="hidden" name ="companyId" value="">
                                                </a>
                                            </div>
                                        </div>
                                        <i class="fontset">注：营业执照须字迹清晰且。</i>
                                    </div>
                         </div>
                     </div>

                    <div class="" role="main">
                            <div class="row">
                                    <div class="pd10">
                                        <h4 class="thumb-title">
                                            <i>*</i>国家企业信用信息公示系统网站截图
                                        </h4>
                                        <div class="thumb-xs-box" id="webImgBox">
                                            <c:if test="${not empty contractChange.photosFileList }">
                                                <c:set var="fileSize2" value="0"/>
                                                <c:forEach items="${contractChange.photosFileList}"
                                                           var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1034}">
                                                        <c:set var="fileSize2" value="${fileSize2 + 1}"/>
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="${list.fileUrl}" class="thumbnail swipebox"
                                                               target="_blank">
                                                                    <span class="thumb-img">
                                                                        <span class="thumb-img-container">
                                                                            <span class="thumb-img-content">
                                                                                <img alt="受让方营业执照" data-original="${list.url50}"
                                                                                     src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                            </span>
                                                                        </span>
                                                                    </span>
                                                                    <span class="thumb-bottom-roup">
                                                                        <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                                                    </span>
                                                            </a>
                                                            <input type="hidden" name="limitSize" value="10">
                                                            <input type="file" id="file${fileSize2}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
                                                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                                            <input type="hidden" name="fileTypeId" value="1034" />
                                                            <input type="hidden" name="fileSourceId" value="10" />
                                                            <input type="hidden" name ="companyId" value="">
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <div class="thumb-xs-list item-photo-add" <c:if test="${fileSize2 >=10  }">style="display: none;"</c:if>>
                                                <input type="hidden" name="limitSize" value="10">
                                                <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                                    <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                                    <input type="hidden" name="fileTypeId" value="1034" />
                                                    <input type="hidden" name="fileSourceId" value="10" />
                                                    <input type="hidden" name ="companyId" value="">
                                                </a>
                                            </div>
                                        </div>
                                        <i class="fontset">注：须信息完整且字迹清晰。</i>
                                    </div>
                         </div>
                     </div>

                     <div class="" role="main">
                            <div class="row">
                                    <div class="pd10">
                                        <h4 class="thumb-title">
                                            <i>*</i>受让方法人身份证
                                        </h4>
                                        <div class="thumb-xs-box" id="idCardBox">
                                            <c:if test="${not empty contractChange.photosFileList }">
                                                <c:set var="fileSize3" value="0"/>
                                                <c:forEach items="${contractChange.photosFileList}"
                                                           var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1035}">
                                                        <c:set var="fileSize3" value="${fileSize3 + 1}"/>
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="${list.fileUrl}" class="thumbnail swipebox"
                                                               target="_blank">
                                                                <span class="thumb-img">
                                                                    <span class="thumb-img-container">
                                                                        <span class="thumb-img-content">
                                                                            <img alt="受让方营业执照" data-original="${list.url50}"
                                                                                 src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                        </span>
                                                                    </span>
                                                                </span>
                                                                <span class="thumb-bottom-roup">
                                                                    <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                                                </span>
                                                            </a>
                                                            <input type="hidden" name="limitSize" value="10">
                                                            <input type="file" id="file${fileSize3}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
                                                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                                            <input type="hidden" name="fileTypeId" value="1035" />
                                                            <input type="hidden" name="fileSourceId" value="10" />
                                                            <input type="hidden" name ="companyId" value="">
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <div class="thumb-xs-list item-photo-add" <c:if test="${fileSize3 >=10  }">style="display: none;"</c:if>>
                                                <input type="hidden" name="limitSize" value="10">
                                                <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                                    <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                                    <input type="hidden" name="fileTypeId" value="1035" />
                                                    <input type="hidden" name="fileSourceId" value="10" />
                                                    <input type="hidden" name ="companyId" value="">
                                                </a>
                                            </div>
                                        </div>
                                        <i class="fontset">注：身份证正反面，照片清晰。</i>
                                    </div>
                         </div>
                     </div>

                     <div class="" role="main">
                            <div class="row">
                                    <div class="pd10">
                                        <h4 class="thumb-title">
                                            <i>*</i>三方变更协议
                                        </h4>
                                        <div class="thumb-xs-box" id="threePartBox">
                                            <c:if test="${not empty contractChange.photosFileList }">
                                                <c:set var="fileSize4" value="0"/>
                                                <c:forEach items="${contractChange.photosFileList}"
                                                           var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1036}">
                                                        <c:set var="fileSize4" value="${fileSize4 + 1}"/>
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="${list.fileUrl}" class="thumbnail swipebox"
                                                               target="_blank">
                                                                <span class="thumb-img">
                                                                    <span class="thumb-img-container">
                                                                        <span class="thumb-img-content">
                                                                            <img alt="受让方营业执照" data-original="${list.url50}"
                                                                                 src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                        </span>
                                                                    </span>
                                                                </span>
                                                                <span class="thumb-bottom-roup">
                                                                    <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                                                </span>
                                                            </a>
                                                            <input type="hidden" name="limitSize" value="10">
                                                            <input type="file" id="file${fileSize4}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
                                                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                                            <input type="hidden" name="fileTypeId" value="1036" />
                                                            <input type="hidden" name="fileSourceId" value="10" />
                                                            <input type="hidden" name ="companyId" value="">
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <div class="thumb-xs-list item-photo-add" <c:if test="${fileSize4 >=10  }">style="display: none;"</c:if>>
                                                <input type="hidden" name="limitSize" value="10">
                                                <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                                    <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                                    <input type="hidden" name="fileTypeId" value="1036" />
                                                    <input type="hidden" name="fileSourceId" value="10" />
                                                    <input type="hidden" name ="companyId" value="">
                                                </a>
                                            </div>
                                        </div>
                                        <i class="fontset">注：须使用法务提供版本，内容完整，盖章清晰。</i>
                                    </div>
                         </div>
                     </div>

                    <div class="" role="main">
                            <div class="row">
                                    <div class="pd10">
                                        <h4 class="thumb-title">
                                            <i>*</i>转让方保证金收据
                                        </h4>
                                        <div class="thumb-xs-box" id="receiveBox">
                                            <c:if test="${not empty contractChange.photosFileList }">
                                                <c:set var="fileSize5" value="0"/>
                                                <c:forEach items="${contractChange.photosFileList}"
                                                           var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1037}">
                                                        <c:set var="fileSize5" value="${fileSize5 + 1}"/>
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="${list.fileUrl}" class="thumbnail swipebox"
                                                               target="_blank">
                                                                <span class="thumb-img">
                                                                    <span class="thumb-img-container">
                                                                        <span class="thumb-img-content">
                                                                            <img alt="受让方营业执照" data-original="${list.url50}"
                                                                                 src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                        </span>
                                                                    </span>
                                                                </span>
                                                                <span class="thumb-bottom-roup">
                                                                    <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                                                </span>
                                                            </a>
                                                            <input type="hidden" name="limitSize" value="10">
                                                            <input type="file" id="file${fileSize5}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
                                                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                                            <input type="hidden" name="fileTypeId" value="1037" />
                                                            <input type="hidden" name="fileSourceId" value="10" />
                                                            <input type="hidden" name ="companyId" value="">
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <div class="thumb-xs-list item-photo-add" <c:if test="${fileSize5 >=10  }">style="display: none;"</c:if>>
                                                <input type="hidden" name="limitSize" value="10">
                                                <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                                    <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                                    <input type="hidden" name="fileTypeId" value="1037" />
                                                    <input type="hidden" name="fileSourceId" value="10" />
                                                    <input type="hidden" name ="companyId" value="">
                                                </a>
                                            </div>
                                        </div>
                                        <i class="fontset">注：收据内容完整，盖章签字清晰。</i>
                                    </div>
                         </div>
                     </div>

                    <div class="" role="main">
                        <div class="row">
                            <div class="pd10">
                                <h4 class="thumb-title">其他</h4>
                                <div class="thumb-xs-box" id="otherBox">
                                    <c:if test="${not empty contractChange.photosFileList }">
                                        <c:set var="fileSize6" value="0"/>
                                        <c:forEach items="${contractChange.photosFileList}"
                                                   var="list" varStatus="status">
                                            <c:if test="${list.fileTypeId eq 1038}">
                                                <c:set var="fileSize6" value="${fileSize6 + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="${list.fileUrl}" class="thumbnail swipebox"
                                                       target="_blank">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="受让方营业执照" data-original="${list.url50}"
                                                                             src="${list.fileAbbrUrl}" class="empPhoto"/>
																	</span>
																</span>
															</span>
                                                            <span class="thumb-bottom-roup">
                                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                                            </span>
                                                    </a>
                                                    <input type="hidden" name="limitSize" value="10">
                                                    <input type="file" id="file${fileSize6}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
                                                    <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                                    <input type="hidden" name="fileTypeId" value="1038" />
                                                    <input type="hidden" name="fileSourceId" value="10" />
                                                    <input type="hidden" name ="companyId" value="">
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add" <c:if test="${fileSize6 >=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                            <input type="hidden" name="fileTypeId" value="1038" />
                                            <input type="hidden" name="fileSourceId" value="10" />
                                            <input type="hidden" name ="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <span id="msgId" style="color:red"></span>
                <div class="text-center">
                    <a href="javascript:void(0)" onclick="fnSave(1)" class="btn btn-primary">保存</a>
                    <a href="${ctx}/contractChangeNew/list/${contractId}/${contractStatus}" class="btn btn-default mgl20">取消</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>


