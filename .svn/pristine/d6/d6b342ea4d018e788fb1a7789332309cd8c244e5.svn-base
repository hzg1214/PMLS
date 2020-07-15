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
                        <strong>新增合同变更</strong>
                    </h4>
                    <div class="" style="height: auto;">
                        <form id="contractChangeForm">
                            <input type="hidden" name="contractId" id="contractId" value="${contractId}">
                            <input type="hidden" name="contractStatus" id="contractStatus" value="${contractStatus}">
                            <input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" style="padding-left:10px;">
                                        <label class="fw-normal w125 text-right">变更合同编号</label>
                                        <span style="padding-left:5px;">${contractInfo.contract.contractNo}</span>
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
                                        <span style="padding-left:5px;"><input type="radio" name="threePartChangeType" value="23001"/>甲转甲</span>
                                        <span style="padding-left:15px;"><input type="radio" name="threePartChangeType" value="23002"/>甲转乙</span>
                                        <span style="padding-left:15px;"><input type="radio" name="threePartChangeType" value="23003"/>乙转乙</span>
                                    </div> 
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left: 15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">转让方</label>
                                        <input type="hidden" name="oldUpdateCompanyName" value="${contractInfo.contract.companyName}">
                                        <input type="hidden" name="oldCompanyId" id="oldCompanyId" value="${contractInfo.contract.companyId}"/>
                                        <span style="padding-left:5px;">${contractInfo.contract.companyName}</span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            <ul  class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>受让方</label>
                                        <span style="padding-left:5px;">
                                             <input type="hidden" id="newCompanyId" name="newCompanyId">
                                             <input type="text" class="form-control w300" readonly id="newCompanyName" name="newCompanyName" placeholder="" style="background-color: #F9F9F9;" notnull="true">
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
                                             <input type="text" style="background-color: #F9F9F9;" readonly class="form-control w300" readonly id="newLegalPerson" name="newLegalPerson" placeholder="" notnull="true"  maxlength="20">
                                        </span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group" >
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">受让方注册地址</label>
                                        <span style="padding-left:5px;">
                                            <input type="hidden" id="newCompanyAddressCityNo" name="newCompanyAddressCityNo">
                                            <input type="hidden" id="newCompanyAddressDistrictNo" name="newCompanyAddressDistrictNo">
                                            <input type="hidden" id="newCompanyAddress" name="newCompanyAddress">
                                            <input type="text" style="background-color: #F9F9F9;" class="form-control w300" id="address3" readonly>
                                        </span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>合作模式</label>
                                       <input type="hidden" name="contractType" value="${contractInfo.contract.contractType}">
                                       <c:if test="${contractInfo.contract.contractType eq 10301}">
                                           <span style="padding-left:5px;"><input disabled type="radio" value="10301" checked/>A</span>
                                           <span style="padding-left:15px;"><input disabled type="radio" value="10302"/>B</span>
                                           <span style="padding-left:15px;"><input disabled type="radio" value="10304"/>A转B</span>
                                       </c:if>
                                       <c:if test="${contractInfo.contract.contractType eq 10302 or contractInfo.contract.contractType eq 10304}">
                                           <span style="padding-left:5px;"><input disabled type="radio" value="10301"/>A</span>
                                           <span style="padding-left:15px;"><input disabled type="radio" value="10302" checked/>B</span>
                                           <span style="padding-left:15px;"><input disabled type="radio" value="10304"/>A转B</span>
                                       </c:if>
                                       <span class="fc-warning"></span>
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">合作协议书编号</label>
                                        <span>
                                            <input value="${contractInfo.contract.agreementNo}-更" type="text" style="background-color: #F9F9F9;" class="form-control w300" readonly name="newAgreementNo" notnull="true" maxlength="20">
                                        </span>
                                   </div>
                                   <div class="form-group" style="padding-left: 50px;">
                                        <label><i>* </i>合作周期</label>
                                         <div class="input-group date">
                                             <fmt:parseDate value="${contractInfo.contract.dateLifeStart}" var="dateLifeStartPre" pattern="yyyy-MM-dd HH:mm:ss"/>
                                             <input type="hidden" id="dateLifeStartPre" value='<fmt:formatDate value="${dateLifeStartPre}" pattern="yyyy-MM-dd" />'>
                                            <input type="text" class="calendar-icon form-control w100" name="newDateLifeStart"
                                               id="newDateLifeStart"
                                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'newDateLifeEnd\');}',minDate:'#F{$dp.$D(\'dateLifeStartPre\');}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                               readonly="readonly" class="ipttext Wdate" style="width: 120px;" notnull="true"/>
                                         </div>
                                         <span>-</span>
                                         <div class="input-group date">
                                             <fmt:parseDate value="${contractInfo.contract.dateLifeEnd}" var="dateLifeEnd" pattern="yyyy-MM-dd HH:mm:ss"/>
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
                                        <input value="${contractInfo.contract.depositFee}" type="text" style="background-color: #F9F9F9;" class="form-control w300" readonly name="totleDepositFee">
                                    </div>
                                    <div class="form-group" style="padding-left: 60px;">
                                        <label>违约金额</label>
                                        <input value="${contractInfo.contract.penaltyFee}" type="text" style="background-color: #F9F9F9;" class="form-control w300" readonly name="penaltyFee" >
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>受让方授权代表</label>
                                        <span>
                                        <input type="text" class="form-control w300" id="authRepresentative" name="authRepresentative" placeholder="" required  notnull="true" maxlength="20">
                                        </span>
                                        <span class="fc-warning"></span>
                                   </div>
                                   <div class="form-group" style="padding-left: 50px;">
                                        <label><i>* </i>联系方式</label>
                                        <input type="text" class="form-control w300" id="agentContact" notnull="true" name="agentContact" placeholder="" maxlength="11" >
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">受让方公司账号</label>
                                        <input type="text" class="form-control w300" id="companyBankNo" value="${contractInfo.contract.companyBankNo}" name="companyBankNo" placeholder="" value=""  maxlength="50">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">开户银行</label>
                                        <input type="text" class="form-control w300" id="bankAccount" value="${contractInfo.contract.bankAccount}" name="bankAccount" placeholder="" value=""  maxlength="30">
                                   </div>
                                   <div class="form-group" style="padding-left: 70px;">
                                        <label>开户名</label>
                                        <input type="text" class="form-control w300" id="accountName" value="${contractInfo.contract.accountName}" name="accountName" placeholder="" value=""  maxlength="20">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">受让方收件人</label>
                                        <input type="text" class="form-control w300" id="recipients" value="${contractInfo.contract.recipients}" name="recipients" placeholder="" value=""  maxlength="20">
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
                                            <c:if test="${contractInfo.contract.cityNo eq city.cityNo}">
                                                <option value="${city.cityNo}" selected="selected">${city.cityName}</option>
                                            </c:if>
                                            <c:if test="${contractInfo.contract.cityNo ne city.cityNo}">
                                                <option value="${city.cityNo}">${city.cityName}</option>
                                            </c:if>
                                          </c:forEach>
                                    </select>
                                </span>
                                <span class="control-select">
                                    <select class="form-control w200"  id="districtNo" name="districtNo">
                                        <option selected="selected" value="" >请选区域</option>
                                        <c:if test="${!empty districtList}">
                                           <c:forEach items="${districtList}" var="district">
                                              <c:if test="${fn:trim(contractInfo.contract.districtNo) eq district.districtNo}">
                                               <option value="${district.districtNo}" selected="selected">${district.districtName}</option>
                                              </c:if>
                                              <c:if test="${fn:trim(contractInfo.contract.districtNo) ne district.districtNo}">
                                               <option value="${district.districtNo}">${district.districtName}</option>
                                              </c:if>
                                           </c:forEach>
                                       </c:if> 
                                    </select>
                                </span>
                                <input type="text" class="form-control w300" id="recipientsAddress" value="${contractInfo.contract.recipientsAddress}" name="recipientsAddress" placeholder="具体地址信息" value=""  maxlength="100">
                                   </div>
                                </li>
                            </ul>

                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">备注</label>
                                       <span style="padding-left:5px;"> <textarea maxlength="150" name="remarks" id="remarks" cols="30" rows="10"  style="resize: none;" ></textarea></span>
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
                                        <td>${contractInfo.storeDetails[0].storeNo}</td>
                                        <td style="text-align:left;" title="${contractInfo.storeDetails[0].name}" class="text-overflow">${contractInfo.storeDetails[0].name}</td>
                                        <td style="text-align:left;" title="${contractInfo.storeDetails[0].addressDetail}" class="text-overflow">${contractInfo.storeDetails[0].addressDetail}</td>
                                        <td><input type="text" name="storeList[0].storeManager" style="width: 120px" notnull="true"> </td>
                                        <td><input type="text" name="storeList[0].storeManagerPhone" style="width: 120px" notnull="true"> </td>
                                        <td>
                                            <select id="storeAbType" name="storeList[0].ABTypeStore" style="width: 60px;" onchange="fnChangeAbTypeStore(this)">
                                                <option value="19901">甲类</option>
                                                <option value="19902">乙类</option>
                                            </select>
                                            <input type="text" id="bTypeStore" readonly value="乙3" style="width: 60px;background-color: #F9F9F9;display: none">
                                        </td>
                                        <input type="hidden" name="storeList[0].contractId" value="${contractId}">
                                        <input type="hidden" name="storeList[0].storeId" value=${storeId}>
                                        <input type="hidden" id="abTypeStore" value="${abTypeStore}">
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
                                            <div class="thumb-xs-list item-photo-add" >
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
                                            <div class="thumb-xs-list item-photo-add" >
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
                                            <div class="thumb-xs-list item-photo-add" >
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
                                            <div class="thumb-xs-list item-photo-add" >
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
                                            <div class="thumb-xs-list item-photo-add" >
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
                                    <div class="thumb-xs-list item-photo-add" >
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


