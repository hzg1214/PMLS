<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/gpContract/gpContractEdit.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script>
    <style>
        .icon-explain{
            width: 16px;
            height: 16px;
            display: inline-block;
            vertical-align: middle;
            position: relative;
            text-align: center;
            color: #fff;
            font-style: normal;
            font-size: 10px;
            line-height: 17px;
        }
        .icon-explain::after, .icon-explain::before{
            display: block;
            width: 100%;
            height: 100%;
        }
        .icon-explain::before {
            content: "\20";
            border-radius: 100%;
            background-color: #4A9AFB;
        }
        .icon-explain::after {
            content: "?";
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -8px;
            margin-left: -8px;
        }
    </style>
</head>

<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>
<form id="gpEditForm">
    <input type="hidden" name="_method" value="put"/>
    <input type="hidden" id="id" name="id" value="${gpContract.id}"/>
    <input type="hidden" id="gpContractNo" name="gpContractNo" value="${gpContract.gpContractNo}">
    <input type="hidden" id="companyId" name="companyId" value="${gpContract.companyId}"/>
    <input type="hidden" id="storeIdArray" name="storeIdArray"/>
    <input type="hidden" id="oldStoreIdArray" name="oldStoreIdArray" value="${gpContract.oldStoreIdArray}"/>
    <!-- 存放经纪公司城市编码 -->
    <input type="hidden" id="companyCityName" name="companyCityName" value="${gpContract.companyCityName}"/>
    <!-- 存放fangyou附件id集  old-->
    <input type="hidden" name="oldfileRecordMainIds" id="oldfileRecordMainIds" value="${gpContract.fileRecordMainIds}"/>
    <!-- 存放fangyou附件id集 -->
    <input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds" value="${gpContract.fileRecordMainIds}"/>
    <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>修改公盘合同</strong></h4>
                <p><strong>基本信息</strong></p>
                <table class="table-sammary">
                    <col style="width:125px;">
                    <col style="width:auto;">
                    <col style="width:125px;">
                    <col style="width:auto;">
                    <tr>
                        <td class="talabel">甲方名称</td>
                        <td><input type="text" class="form-control w300" id="partyB" name="partyB" placeholder="甲方名称" value="${gpContract.partyB}" readonly="readonly" style="background-color: #F2F2F2"></td>
                        <td class="talabel required">法定代表/负责人</td>
                        <td><input type="text" class="form-control w300" id="legalPerson" name="legalPerson" placeholder="请输入" notnull="true" value="${gpContract.legalPerson}"
                                   maxlength="20" readonly="readonly" style="background-color: #F2F2F2">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">公司注册地址</td>
                        <td colspan="3">
                            <div class="input-grounp">
                                <input type="hidden" class="form-control w120" id="partyBcityNo" name="partyBcityNo" value="${gpContract.partyBCityNo}">
                                <input type="hidden" class="form-control w120" id="partyBdistrictNo" name="partyBdistrictNo" value="${gpContract.partyBDistrictNo}">
                                <input type="text" class="form-control w120" id="partyBCityName" name="partyBCityName" value="${gpContract.partyBCityName}" notnull="true" readonly="readonly" style="background-color: #F2F2F2">
                                <input type="text" class="form-control w120" id="partyBDistrictName" name="partyBDistrictName" value="${gpContract.partyBDistrictName}" notnull="true" readonly="readonly" style="background-color: #F2F2F2">
                                <input type="text" class="form-control w300" id="partyBAddress" name="partyBAddress"
                                       placeholder="公司地址" notnull="true" value="${gpContract.partyBAddress}" maxlength="100" readonly="readonly" style="background-color: #F2F2F2">
                                <span class="fc-warning"></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">统一社会信用代码</td>
                        <td><input type="text" class="form-control w300" id="registerId" name="registerId" placeholder="" notnull="true" maxlength="30" value="${gpContract.registerId}" readonly="readonly" style="background-color: #F2F2F2">
                    </tr>
                    <tr>
                        <td class="talabel required">我方全称</td>
                        <td>
                            <input type="hidden" id="ourFullName" name="ourFullName" value="${gpContract.ourFullName}">
                            <div class="control-group">
                				<span class="control-select">
                					<select class="form-control w300"  title="" id="ourFullId" name="ourFullId" notnull="true">
			                              <option value="">请选择</option>
                                            <c:forEach items="${fullNameList}" var="list">
                                                <option value="${list.id}" <c:if test="${list.id eq gpContract.ourFullId}">selected</c:if>>${list.name}</option>
                                            </c:forEach>
		                            </select>
                				</span>
                            </div>
                            <span class="fc-warning"></span></td>
                        </td>
                        <td class="talabel required">公盘协议书编号</td>
                        <td>
                            <input type="text" class="form-control w300" id="gpAgreementNo" name="gpAgreementNo" value="${gpContract.gpAgreementNo}" placeholder="" notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel"><i class="icon-explain" title="该甲方公司所有的有效的B模式合同保证金到账金额之和-保证金退款金额之和"></i>&nbsp;翻牌到账保证金</td>
                        <td><input type="text" class="form-control w300" id="deposit" name="deposit" placeholder="" value="${gpContract.deposit}" readonly="readonly" datatype="moneyWithTwo" maxlength="9" style="background-color: #F2F2F2">
                            <span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel required">门店数</td>
                        <td><input type="text" class="form-control w300" id="storeNum" name="storeNum" placeholder="" notnull="true" value="${gpContract.storeNum}" datatype="posNumWithOutZero" readonly="readonly" maxlength="9" style="background-color: #F2F2F2">
                            <span class="fc-warning"></span></td>
                        <td class="talabel required">保证金</td>
                        <td><input type="text" class="form-control w300" id="depositFee" name="depositFee" placeholder="" notnull="true" value="${gpContract.depositFee}"
                                   datatype="moneyWithTwo" maxlength="9">
                            <span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel required">合同生效日期</td>
                        <td><input type="text" class="calendar-icon  form-control w300" name="dateLifeStart"
                                   id="dateLifeStart" value="${sdk:ymd2(gpContract.dateLifeStart)}"
                                   onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'dateLifeEnd\',{d:-364});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                   class="ipttext Wdate" notnull="true"/><span class="fc-warning"></span></td>
                        <td class="talabel required">合同到期日期</td>
                        <td>
                            <input type="text" class="calendar-icon form-control w300" name="dateLifeEnd"
                                   id="dateLifeEnd" value="${sdk:ymd2(gpContract.dateLifeEnd)}"
                                   onFocus="WdatePicker({minDate:'#F{$dp.$D(\'dateLifeStart\',{d:364});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                   class="ipttext Wdate" notnull="true"/>
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">甲方授权代表</td>
                        <td>
                            <input type="text" class="form-control w300" id="partyBNm" name="partyBNm" value="${gpContract.partyBNm}" placeholder="请输入甲方授权代表"  notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">甲方联系方式</td>
                        <td>
                            <input type="text" class="form-control w300" id="partyBTel" name="partyBTel" value="${gpContract.partyBTel}" placeholder="请输入甲方联系方式" maxlength="11" >
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">业绩归属人</td>
                        <td>
                            <input type="hidden" id="exPersonId" name="exPersonId" value="${gpContract.exPersonId}">
                            <input type="text" class="form-control w300" id="exPerson" name="exPerson" placeholder="" notnull="true" readonly="readonly" value="${gpContract.exPerson}" style="background-color: #F2F2F2">
                            <button type="button" class="btn btn-primary"  onclick="javascript:achieveMentChange('1');">选择业绩归属人</button>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">业绩归属中心</td>
                        <td>
                            <input type="hidden" id="centerId" name="centerId" value="${gpContract.centerId}">
                            <input type="hidden" class="form-control w300" id="centerName" name="centerName" value="${gpContract.centerName}">
                            <select style="width:300px;" class="form-control" name="selectCenterName" notnull="true"  id="selectCenterName" >
							</select>
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">开户名</td>
                        <td>
                            <input type="text" class="form-control w300" id="accountNm" name="accountNm" value="${gpContract.accountNm}" placeholder="请输入开户名" maxlength="50" notnull="true">
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">开户省市</td>
                        <td>
							<span class="control-select">
								<input type="hidden" id="accountProvinceNm" name="accountProvinceNm" value="${gpContract.accountProvinceNm}">
								<select class="form-control" title="" id="accountProvinceNo" name="accountProvinceNo" required readonly style="width: 148px;" notnull="true">
									<option value="">请选择省份</option>
									<c:forEach items="${provinceList}" var="province">
                                        <option value="${province.provinceNo}" <c:if test="${province.provinceNo eq gpContract.accountProvinceNo}"> selected</c:if>>${province.provinceName}</option>
                                    </c:forEach>
								</select>
							</span>
                            <span class="control-select">
								<input type="hidden" id="accountCityNm" name="accountCityNm" value="${gpContract.accountCityNm}">
								<select class="form-control" title="" id="accountCityNo" name="accountCityNo" required readonly style="width: 148px;" notnull="true">
									<c:forEach items="${cityList}" var="city">
                                        <option value="${city.cityNo}" <c:if test="${city.cityNo eq gpContract.accountCityNo}"> selected</c:if> >${city.cityName}</option>
                                    </c:forEach>
								</select>
							</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">开户行</td>
                        <td>
                            <input type="text" class="form-control w300" id="bankAccountNm" name="bankAccountNm" value="${gpContract.bankAccountNm}" placeholder="请输入开户行" maxlength="50" notnull="true">
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">银行帐号</td>
                        <td>
                            <input type="text" class="form-control w300" id="bankAccount" oninput="this.value=this.value.replace(/[^0-9a-zA-Z/-]+/g,'')" value="${gpContract.bankAccount}"  name="bankAccount"  dataType="bankAccount" placeholder="请输入银行帐号" maxlength="50" notnull="true">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>

                    <tr>
                        <td class="talabel">合同备注:</td>
                        <td>
                            <textarea maxlength="300" name="remark" id="remark" cols="30" rows="10" style="word-break:break-all;word-wrap: break-word;resize: none;width:885px">${gpContract.remark}</textarea>
                        </td>
                    </tr>
                </table>
                <p><strong>门店信息</strong></p>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                    <tr>
                        <th style="width:80px">门店编号</th>
                        <th style="width:80px">门店名称</th>
                        <th style="width:60px">门店维护人</th>
                        <th style="width:60px">门店负责人</th>
                        <th style="width:80px">负责人电话</th>
                        <%--<th style="width:120px">资质等级</th>--%>
                    </tr>
                    <c:forEach items="${gpContract.storeDetails}" var="storelist">
                        <tr>
                            <td>${storelist.storeNo}</td>
                            <td>${storelist.name}</td>
                            <td>${storelist.maintainerName}</td>
                            <td><input style='border: 1px solid #ccc;border-radius: 4px;height:28px' size='10' id='contactName${storelist.storeId}' name='contactName${storelist.storeId}' notnull='true' attr='ctname' value='${storelist.storeManager}'></td>
                            <td><input style='border: 1px solid #ccc;border-radius: 4px;height:28px' size='15' maxlength='11' id='contactPhoneNo${storelist.storeId}' name='contactPhoneNo${storelist.storeId}' notnull='true' attr='ctphone' value='${storelist.storeManagerPhone}'></td>
                            <%--<td class="storeGradedata">
                                <select class='storetype' id="storetype${storelist.storeId}" name="storetype${storelist.storeId}" style='width: 70px;'  onchange='storetypeChange(this)'>
                                    <option value="">请选择</option>
                                    <option value="19901" <c:if test="${storelist.abtypeStore eq 19901}">selected</c:if>>甲类</option>
                                    <option value="19902" <c:if test="${storelist.abtypeStore eq 19902}">selected</c:if>>乙类</option>
                                </select>
                                <input type="text" id="bTypenamelst${storelist.storeId}" readonly style='width: 70px;<c:if test="${storelist.abtypeStore ne 19902}">display:none;</c:if>' name="bTypenamelst${storelist.storeId}" value="${fn:replace(storelist.btypeStoreName,',',';')}">
                            </td>--%>
                            <td style='display:none'><input name='storetype${storelist.storeId}'  id='storetype${storelist.storeId}'   type='hidden' value='${storelist.abtypeStore}'></td>
                            <td style='display:none'><input name='storetypeBlst${storelist.storeId}'  id='storetypeBlst${storelist.storeId}'   type='hidden' value='${storelist.btypeStore}'></td>
                            <td style='display:none'><input name='storeIds'  id='storeIds${storelist.storeId}'   type='hidden' value='${storelist.storeId}'></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</form>

<!-- 营业执照 begin -->

<div class="container theme-hipage ng-scope" role="main">
    <p><strong>附件</strong></p>
</div>
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>营业执照
            </h4>
            <div class="thumb-xs-box" id="fileBusinessBox">
                <c:if test="${gpContract.fileBusinessList != null }">
                    <c:set var="fileSize" value="0"/>
                    <c:forEach items="${gpContract.fileBusinessList}" var="list" varStatus="status">
                        <c:set var="fileSize" value="${fileSize + 1}"/>
                        <div class="thumb-xs-list item-photo-list">
                            <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank" title="${list.fileName}">
                                <span class="thumb-img">
                                    <span class="thumb-img-container">
                                        <span class="thumb-img-content">
                                            <img alt="营业执照" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                        </span>
                                    </span>
                                </span>
                                <span class="thumb-bottom-roup">
                                    <i class="icon down-icon"></i>
                                    <i class="icon remove-icon btn-remove-photo"></i>
                                </span>
                            </a>
                            <input type="hidden" name="limitSize" value="10">
                            <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"/>
                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}"/>
                            <input type="hidden" name="fileTypeId" value="1"/>
                            <input type="hidden" name="fileSourceId" value="12"/>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="thumb-xs-list item-photo-add"
                     <c:if test="${gpContract.fileBusinessList != null && gpContract.fileBusinessList.size()>=10  }">style="display: none;"</c:if>>
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                        <input type="hidden" name="fileTypeId" value="1"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="${gpContract.companyId}">
                    </a>
                </div>
            </div>
            <i class="fontset">注：营业执照须字迹清晰。</i>
        </div>
    </div>
</div>
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>法人身份证
            </h4>
            <div class="thumb-xs-box" id="fileIdCardBox">
                <c:if test="${gpContract.fileIdCardList != null }">
                    <c:set var="fileSize" value="0"/>
                    <c:forEach items="${gpContract.fileIdCardList}" var="list" varStatus="status">
                        <c:set var="fileSize" value="${fileSize + 1}"/>
                        <div class="thumb-xs-list item-photo-list">
                            <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank" title="${list.fileName}">
                                <span class="thumb-img">
                                    <span class="thumb-img-container">
                                        <span class="thumb-img-content">
                                            <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                        </span>
                                    </span>
                                </span>
                                <span class="thumb-bottom-roup">
                                    <i class="icon down-icon"></i>
                                    <i class="icon remove-icon btn-remove-photo"></i>
                                </span>
                            </a>
                            <input type="hidden" name="limitSize" value="10">
                            <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"/>
                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}"/>
                            <input type="hidden" name="fileTypeId" value="2"/>
                            <input type="hidden" name="fileSourceId" value="12"/>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="thumb-xs-list item-photo-add"
                     <c:if test="${gpContract.fileIdCardList != null && gpContract.fileIdCardList.size()>=10  }">style="display: none;"</c:if>>
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                        <input type="hidden" name="fileTypeId" value="2"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="${gpContract.companyId}">
                    </a>
                </div>
            </div>
            <i class="fontset">注：身份证正反面，照片清晰。</i>
        </div>
    </div>
</div>
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>公盘系统服务协议
            </h4>
            <div class="thumb-xs-box" id="fileContractBox">
                <c:if test="${gpContract.fileContractList != null }">
                    <c:set var="fileSize" value="0"/>
                    <c:forEach items="${gpContract.fileContractList}" var="list" varStatus="status">
                        <c:set var="fileSize" value="${fileSize + 1}"/>
                        <div class="thumb-xs-list item-photo-list">
                            <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank" title="${list.fileName}">
                                <span class="thumb-img">
                                    <span class="thumb-img-container">
                                        <span class="thumb-img-content">
                                            <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                        </span>
                                    </span>
                                </span>
                                <span class="thumb-bottom-roup">
                                    <i class="icon down-icon"></i>
                                    <i class="icon remove-icon btn-remove-photo"></i>
                                </span>
                            </a>
                            <input type="hidden" name="limitSize" value="50">
                            <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="50" multiple="multiple"/>
                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}"/>
                            <input type="hidden" name="fileTypeId" value="3"/>
                            <input type="hidden" name="fileSourceId" value="12"/>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="thumb-xs-list item-photo-add"
                     <c:if test="${gpContract.fileContractList != null && gpContract.fileContractList.size()>=50  }">style="display: none;"</c:if>>
                    <input type="hidden" name="limitSize" value="50">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="50" data-name="photo-shinei" multiple="multiple">
                        <input type="hidden" name="fileTypeId" value="3"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="${gpContract.companyId}">
                    </a>
                </div>
            </div>
            <i class="fontset">注：必须上传公盘协议书所有页面。</i>
        </div>
    </div>
</div>
<%--<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                 <i>*</i>授权委托书（确认易居房友15日可支付房源方分成佣金）
            </h4>
            <div class="thumb-xs-box" id="fileProxyBox">
                <c:if test="${gpContract.fileProxyList != null }">
                    <c:set var="fileSize" value="0"/>
                    <c:forEach items="${gpContract.fileProxyList}" var="list" varStatus="status">
                        <c:set var="fileSize" value="${fileSize + 1}"/>
                        <div class="thumb-xs-list item-photo-list">
                            <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank" title="${list.fileName}">
                                <span class="thumb-img">
                                    <span class="thumb-img-container">
                                        <span class="thumb-img-content">
                                            <img alt="授权委托书" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                        </span>
                                    </span>
                                </span>
                                <span class="thumb-bottom-roup">
                                    <i class="icon down-icon"></i>
                                    <i class="icon remove-icon btn-remove-photo"></i>
                                </span>
                            </a>
                            <input type="hidden" name="limitSize" value="10">
                            <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"/>
                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}"/>
                            <input type="hidden" name="fileTypeId" value="1042"/>
                            <input type="hidden" name="fileSourceId" value="12"/>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="thumb-xs-list item-photo-add"
                     <c:if test="${gpContract.fileProxyList != null && gpContract.fileProxyList.size()>=10  }">style="display: none;"</c:if>>
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                        <input type="hidden" name="fileTypeId" value="1042"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="${gpContract.companyId}">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>--%>
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                 <i></i>直联盘勾选表
            </h4>
            <div class="thumb-xs-box" id="fileCheckBox">
                <c:if test="${gpContract.fileCheckList != null }">
                    <c:set var="fileSize" value="0"/>
                    <c:forEach items="${gpContract.fileCheckList}" var="list" varStatus="status">
                        <c:set var="fileSize" value="${fileSize + 1}"/>
                        <div class="thumb-xs-list item-photo-list">
                            <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank" title="${list.fileName}">
                                <span class="thumb-img">
                                    <span class="thumb-img-container">
                                        <span class="thumb-img-content">
                                            <img alt="直联盘勾选表" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                        </span>
                                    </span>
                                </span>
                                <span class="thumb-bottom-roup">
                                    <i class="icon down-icon"></i>
                                    <i class="icon remove-icon btn-remove-photo"></i>
                                </span>
                            </a>
                            <input type="hidden" name="limitSize" value="10">
                            <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"/>
                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}"/>
                            <input type="hidden" name="fileTypeId" value="1042"/>
                            <input type="hidden" name="fileSourceId" value="12"/>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="thumb-xs-list item-photo-add"
                     <c:if test="${gpContract.fileCheckList != null && gpContract.fileCheckList.size()>=10  }">style="display: none;"</c:if>>
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                        <input type="hidden" name="fileTypeId" value="1061"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="${gpContract.companyId}">
                    </a>
                </div>
            </div>
            <i class="fontset">注：每个门店需提供一份。</i>
        </div>
    </div>
</div>
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                 <i>*</i>易居房友经纪业务共享平台规则
            </h4>
            <div class="thumb-xs-box" id="fileRuleBox">
                <c:if test="${gpContract.fileRuleList != null }">
                    <c:set var="fileSize" value="0"/>
                    <c:forEach items="${gpContract.fileRuleList}" var="list" varStatus="status">
                        <c:set var="fileSize" value="${fileSize + 1}"/>
                        <div class="thumb-xs-list item-photo-list">
                            <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank" title="${list.fileName}">
                                <span class="thumb-img">
                                    <span class="thumb-img-container">
                                        <span class="thumb-img-content">
                                            <img alt="易居房友经纪业务共享平台规则" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                        </span>
                                    </span>
                                </span>
                                <span class="thumb-bottom-roup">
                                    <i class="icon down-icon"></i>
                                    <i class="icon remove-icon btn-remove-photo"></i>
                                </span>
                            </a>
                            <input type="hidden" name="limitSize" value="10">
                            <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"/>
                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}"/>
                            <input type="hidden" name="fileTypeId" value="1062"/>
                            <input type="hidden" name="fileSourceId" value="12"/>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="thumb-xs-list item-photo-add"
                     <c:if test="${gpContract.fileRuleList != null && gpContract.fileRuleList.size()>=10  }">style="display: none;"</c:if>>
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                        <input type="hidden" name="fileTypeId" value="1062"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="${gpContract.companyId}">
                    </a>
                </div>
            </div>
            <i class="fontset">注：需加盖公章。</i>
        </div>
    </div>
</div>
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                其他
            </h4>
            <div class="thumb-xs-box" id="fileOtherBox">
                <c:if test="${gpContract.fileOtherList != null }">
                    <c:set var="fileSize" value="0"/>
                    <c:forEach items="${gpContract.fileOtherList}" var="list" varStatus="status">
                        <c:set var="fileSize" value="${fileSize + 1}"/>
                        <div class="thumb-xs-list item-photo-list">
                            <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank" title="${list.fileName}">
                                <span class="thumb-img">
                                    <span class="thumb-img-container">
                                        <span class="thumb-img-content">
                                            <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                        </span>
                                    </span>
                                </span>
                                <span class="thumb-bottom-roup">
                                    <i class="icon down-icon"></i>
                                    <i class="icon remove-icon btn-remove-photo"></i>
                                </span>
                            </a>
                            <input type="hidden" name="limitSize" value="10">
                            <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"/>
                            <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}"/>
                            <input type="hidden" name="fileTypeId" value="6"/>
                            <input type="hidden" name="fileSourceId" value="12"/>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="thumb-xs-list item-photo-add"
                     <c:if test="${gpContract.fileOtherList != null && gpContract.fileOtherList.size()>=10  }">style="display: none;"</c:if>>
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                        <input type="hidden" name="fileTypeId" value="6"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="${gpContract.companyId}">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="text-center">
    <a href="javascript:GpContract.update();" class="btn btn-primary">保存</a>
    <a href="${ctx}/gpContract?searchParam=1" class="btn btn-primary mgl20">返回</a>
</div>
</body>
</html>
<script type="text/javascript">
		$(function(){
			var url = BASE_PATH + "/gpContract/getLinkUserCenter";
			var exPersonId = $("#exPersonId").val();
		    var params = {userId:exPersonId};
			var centerId = 	$("#centerId").val();
			ajaxGet(url, params, function(data2) {
				var dataLength =  data2.returnValue.length;
				var result = "<option value=''>请选择业绩归属中心</option>";
				$.each(data2.returnValue, function(n, value) {
					if(dataLength > 1) {
						if(centerId == value.exchangeCenterId) {
							result += "<option value='" + value.exchangeCenterId + "' selected>"
							+ value.exchangeCenterName + "</option>";
						}else{
							result += "<option value='" + value.exchangeCenterId + "'>"
							+ value.exchangeCenterName + "</option>";
						}
					}
					if(dataLength > 0 && dataLength < 2) {
						result += "<option value='" + value.exchangeCenterId + "' selected>"
						+ value.exchangeCenterName + "</option>";
					}
				});
				$("#selectCenterName").empty();
				$("#selectCenterName").append(result);
			}, function() {
			});
			$('#exPerson').on("blur",function(){
			    var url = BASE_PATH + "/gpContract/getLinkUserCenter";
				var exPersonId = $("#exPersonId").val();
			    var params = {userId:exPersonId};
				ajaxGet(url, params, function(data2) {
					var dataLength =  data2.returnValue.length;
					var result = "<option value=''>请选择业绩归属中心</option>";
					$.each(data2.returnValue, function(n, value) {
						if(dataLength > 1) {
							result += "<option value='" + value.exchangeCenterId + "'>"
							+ value.exchangeCenterName + "</option>";
						}
						if(dataLength > 0 && dataLength < 2) {
							result += "<option value='" + value.exchangeCenterId + "' selected>"
							+ value.exchangeCenterName + "</option>";
						}
					});
					$("#selectCenterName").empty();
					$("#selectCenterName").append(result);
				}, function() {
				});
			});
		});
	</script>
