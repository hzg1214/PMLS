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
<script type="text/javascript" src="${ctx}/meta/js/gpCsMemberContract/gpCsMemberContract.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateCompany.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<style type="text/css">
    .text-overflow {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 100%;
    }
    textarea {
    	width: 1035px;
    }
</style>
</head>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<form id="gpCsMemberContractAddForm">
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
	<input type="hidden" name="gpContractId" id="gpContractId" >
	<input type="hidden" name="companyId" id="companyId" >
	<input type="hidden" name="companyNo" id="companyNo1" >
	<input type="hidden" name="companyName" id="companyName1" >
	<input type="hidden" name="centerId" id="centerId" value="${centerId}">
	<input type="hidden" name="centerName" id="centerName" value="${centerName}" >
    <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4><strong>新建公盘初始会员合同</strong></h4>
                <p><strong>基本信息</strong></p>
                <table class="table-sammary">
                    <col style="width:125px;">
                    <col style="width:auto;">
                    <col style="width:146px;">
                    <col style="width:auto;">
                    <tr>
                        <td class="talabel required">甲方名称</td>
                        <td>
                            <input type="text" class="form-control w300" id="partyB" name="partyB" placeholder=""   readonly="readonly" style="background-color: #F9F9F9">
                            <button type="button" class="btn btn-primary"  onclick="javascript:relateCsCompany('fromGpCsContract');" style="vertical-align: top;">选择公司</button>
                        </td>
                        <td class="talabel">法定代表/负责人</td>
                        <td>
                            <input type="text" class="form-control w300" id="legalPerson" name="legalPerson" placeholder="" notnull="true" maxlength="20" readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">公司注册地址</td>
                        <td colspan="3">
                            <input type="hidden" class="form-control w120" id="partyBcityNo" name="partyBcityNo">
                            <input type="hidden" class="form-control w120" id="partyBdistrictNo" name="partyBdistrictNo">
                            <input type="text" class="form-control w120" id="partyBCityName" name="partyBCityName" notnull="true" readonly="readonly" style="background-color: #F9F9F9">
                            <input type="text" class="form-control w200" id="partyBDistrictName" name="partyBDistrictName" notnull="true" readonly="readonly" style="width:132px;background-color: #F9F9F9">
                            <input type="text" class="form-control w300" id="partyBAddress" name="partyBAddress" notnull="true" maxlength="100" readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">统一社会信用代码</td>
                        <td>
                            <input type="text" class="form-control w300" readonly="readonly" id="registerId" name="registerId" placeholder="" notnull="true" maxlength="30" style="background-color: #F9F9F9">
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">我方全称</td>
                        <td>
                            <input type="hidden"  id="ourFullName" name="ourFullName">
                            <div class="control-group">
                				<span class="control-select">
                					<select class="form-control w300" title="" id="ourFullId" readonly  name="ourFullId" notnull="true">
			                              <option value="" >请选择</option>
		                					    <c:forEach items="${fullNameList}" var="list">
                                                    <option value="${list.id}" >${list.name}</option>
                                                </c:forEach>
		                            </select>
                				</span>
                            </div>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">初始会员协议书编号</td>
                        <td>
                            <input type="text" class="form-control w300" id="agreementNo" name="agreementNo" placeholder="" notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">初始会员费</td>
                        <td>
                            <input type="text" class="form-control w300" id="csMemberAmount" name="csMemberAmount" placeholder="" notnull="true" datatype="moneyWithTwo" maxlength="9">
                            <span class="fc-warning"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="talabel required">甲方授权代表</td>
                        <td>
                            <input type="text" class="form-control w300" id="partyBNm" name="partyBNm" placeholder="请输入甲方授权代表"  notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">甲方联系方式</td>
                        <td>
                            <input type="text" class="form-control w300" id="partyBTel" name="partyBTel" placeholder="请输入甲方联系方式" notnull="true" maxlength="11" >
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">业绩归属人</td>
                        <td>
                            <input type="hidden" id="exPersonId" name="exPersonId" value="${userIdCreate}">
                            <input type="text" class="form-control w300" id="exPerson" name="exPerson" value="${userName}" placeholder="" notnull="true"
                                   readonly="readonly" style="background-color: #F9F9F9">
                            <button type="button" class="btn btn-primary"  onclick="javascript:achieveMentChange('0');">选择业绩归属人</button>
                        	 <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">业绩归属中心</td>
                        <td>
                        	<select style="width:300px;" class="form-control" name="selectCenterName" notnull="true"  id="selectCenterName" >
							</select>
							<span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">开户名</td>
                        <td>
                            <input type="text" class="form-control w300" notnull="true" id="accountNm" name="accountNm" placeholder="请输入开户名" maxlength="50">
                        </td>
                        <td class="talabel required">开户省市</td>
                        <td>
							<span class="control-select">
								<input type="hidden" id="accountProvinceNm" name="accountProvinceNm">
								<select class="form-control" title="" notnull="true" id="accountProvinceNo" name="accountProvinceNo" required readonly style="width: 148px;">
									<option value="">请选择省份</option>
									<c:forEach items="${provinceList}" var="province">
                                        <option value="${province.provinceNo}">${province.provinceName}</option>
                                    </c:forEach>
								</select>
							</span>
                            <span class="control-select">
								<input type="hidden" id="accountCityNm" name="accountCityNm">
								<select class="form-control" title="" id="accountCityNo" notnull="true" name="accountCityNo" required readonly style="width: 148px;">
									<c:forEach items="${cityList}" var="city">
                                        <option value="${city.cityNo}">${city.cityName}</option>
                                    </c:forEach>
								</select>
							</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">开户行</td>
                        <td>
                            <input type="text" class="form-control w300" id="bankAccountNm" name="bankAccountNm" placeholder="请输入开户行" maxlength="50" notnull="true">
                        </td>
                        <td class="talabel required">银行帐号</td>
                        <td>
                            <input type="text" class="form-control w300" id="bankAccount" name="bankAccount" dataType="bankAccount"  placeholder="请输入银行帐号" maxlength="50" notnull="true">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>

                    <tr>
                        <td class="talabel">合同备注</td>
                        <td>
                            <textarea maxlength="300" name="remark" id="remark" cols="30" rows="10" style="word-break:break-all;word-wrap: break-word;resize: none;width:895px;"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>
<div class="container theme-hipage ng-scope" role="main">
    <p><strong>附件</strong></p>
</div>

<!-- 营业执照 -->
<div class="container theme-hipage ng-scope" role="main" id="csMemberFileBox1">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>营业执照
            </h4>
            <div class="thumb-xs-box" id="csMemberFileBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="1057"/>
                        <input type="hidden" name="fileSourceId" value="16"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：营业执照须字迹清晰。</i>
        </div>
    </div>
</div>
<!-- 法人身份证 -->
<div class="container theme-hipage ng-scope" role="main" id="fileIdCardBox1">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>法人身份证
            </h4>
            <div class="thumb-xs-box" id="fileIdCardBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="1052"/>
                        <input type="hidden" name="fileSourceId" value="16"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：身份证正反面，照片清晰。</i>
        </div>
    </div>
</div>
<!-- 公盘系统服务协议 -->
<div class="container theme-hipage ng-scope" role="main" id="fileContractBox1">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>公盘系统服务协议
            </h4>
            <div class="thumb-xs-box" id="fileContractBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="1053"/>
                        <input type="hidden" name="fileSourceId" value="16"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：必须上传公盘协议书所有页面。</i>
        </div>
    </div>
</div>
<div class="container theme-hipage ng-scope" role="main" style="display:none;" id="fileBusinessBox2">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>营业执照
            </h4>
            <div class="thumb-xs-box" id="fileBusiness">
                
            </div>
            <i class="fontset">注：营业执照须字迹清晰。</i>
        </div>
    </div>
</div>

<!-- 中介法定代表人/负责人身份证或名片 -->
<div class="container theme-hipage ng-scope" role="main" style="display:none;" id="fileIdCardBox2">
	<div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>法人身份证
            </h4>
            <div class="thumb-xs-box" id="fileIdCard">
                
            </div>
            <i class="fontset">注：身份证正反面，照片清晰。</i>
        </div>
    </div>
</div>
<!-- 公盘协议书 -->
<div class="container theme-hipage ng-scope" role="main" style="display:none;" id="fileContractBox2">
   <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>公盘系统服务协议
            </h4>
            <div class="thumb-xs-box" id="fileContract">
                
            </div>
            <i class="fontset">注：必须上传公盘协议书所有页面。</i>
        </div>
    </div>
</div>
<!-- 初始会员协议 -->
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>初始会员协议
            </h4>
            <div class="thumb-xs-box" id="csMemberFileBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="1054"/>
                        <input type="hidden" name="fileSourceId" value="16"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：合作中介或中介公司需加盖公章确认。</i>
        </div>
    </div>
</div>
<!-- 初始会员费付款凭证 -->
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>初始会员费付款凭证
            </h4>
            <div class="thumb-xs-box" id="csMemberPayBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="1056"/>
                        <input type="hidden" name="fileSourceId" value="16"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
           <i class="fontset">注：付款凭证需清晰。</i>
        </div>
    </div>
</div>
<!-- 其他 -->
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                其他
            </h4>
            <div class="thumb-xs-box" id="fileOtherBox">
                <div class="thumb-xs-list item-photo-add" >
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                        <input type="hidden" name="fileTypeId" value="1055" />
                        <input type="hidden" name="fileSourceId" value="16" />
                        <input type="hidden" name ="companyId" value="">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="lockHandle2"></div>
<div class="text-center">
    <a href="javascript:void(0)" onclick="doSave()" class="btn btn-primary">草签</a>
	<a href="${ctx}/gpCsMemberContract" class="btn btn-default mgl20">取消</a>
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
