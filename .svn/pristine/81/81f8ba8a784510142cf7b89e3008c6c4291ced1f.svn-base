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
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script>
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
	<input type ="hidden"  id ="id"   name="id" value ="${csMember.id}"/>
	<input type ="hidden"  id ="companyNo"   name="companyNo" value ="${csMember.companyNo}"/>
	<input type ="hidden"  id ="companyId"   name="companyId" value ="${csMember.companyId}"/>
	<input type="hidden" name="oldfileRecordMainIds" id="oldfileRecordMainIds"  value = "${csMember.fileRecordMainIds}" />
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds" value ="${csMember.fileRecordMainIds}"  />
	<input type ="hidden"  id ="userIdCreate"   name="userIdCreate" value ="${userIdCreate}"/>
	<input type ="hidden"  id ="nowCityNo"   name="nowCityNo" value ="${cityNo}"/>
	<input type ="hidden"  id ="userName"   name="userName" value ="${userName}"/>
	<input type ="hidden"  id ="userCode"   name="userCode" value ="${userCode}"/>
	<input type ="hidden"  id ="companyNo"   name="companyNo" value ="${csMember.companyNo}"/>
	<input type ="hidden"  id ="companyName"   name="companyName" value ="${csMember.partyB}"/>
	<input type ="hidden"  id ="centerId"   name="centerId" value ="${csMember.centerId}"/>
	<input type ="hidden"  id ="centerName"   name="centerName" value ="${csMember.centerName}"/>
	
    <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4><strong>修改公盘初始会员合同</strong></h4>
                <p><strong>基本信息</strong></p>
                <table class="table-sammary">
                    <col style="width:125px;">
                    <col style="width:auto;">
                    <col style="width:146px;">
                    <col style="width:auto;">
                    <tr>
                        <td class="talabel required">甲方名称</td>
                        <td>
                            <input type="text" class="form-control w300" id="partyB" name="partyB" placeholder=""  value="${csMember.partyB}" readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                        <td class="talabel">法定代表/负责人</td>
                        <td>
                            <input type="text" class="form-control w300" id="legalPerson" name="legalPerson" value="${csMember.legalPerson}" placeholder="" notnull="true" maxlength="20" readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">公司注册地址</td>
                        <td colspan="3">
                            <input type="hidden" class="form-control w120" id="partyBcityNo" name="partyBcityNo" value="${csMember.partyBCityNo}">
                            <input type="hidden" class="form-control w120" id="partyBdistrictNo" name="partyBdistrictNo" value="${csMember.partyBDistrictNo}">
                            <input type="text" class="form-control w120" id="partyBCityName" name="partyBCityName" value="${csMember.partyBCityName}" notnull="true"  readonly="readonly" style="background-color: #F9F9F9">
                            <input type="text" class="form-control w200" id="partyBDistrictName" name="partyBDistrictName"  value="${csMember.partyBDistrictName}" notnull="true" readonly="readonly" style="width:132px;background-color: #F9F9F9">
                            <input type="text" class="form-control w300" id="partyBAddress" name="partyBAddress" notnull="true"  value="${csMember.partyBAddress}" maxlength="100" readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">统一社会信用代码</td>
                        <td>
                            <input type="text" class="form-control w300" readonly="readonly" id="registerId" value="${csMember.registerId}" name="registerId" placeholder="" notnull="true" maxlength="30" style="background-color: #F9F9F9">
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">我方全称</td>
                        <td>
                            <input type="hidden"  id="ourFullName" name="ourFullName">
                            <div class="control-group">
                				<span class="control-select">
                					<%-- <input type="hidden"  id="ourFullId" name="ourFullId"  value="${csMember.ourFullId}" >
                            		<input type="text" class="form-control w300" id="ourFullName" name="ourFullName" notnull="true"  value="${csMember.ourFullName}" readonly="readonly" style="background-color: #F9F9F9"> --%>
                					<select class="form-control w300" title="" id="ourFullId" readonly  name="ourFullId" notnull="true">
                                          <option value="${csMember.ourFullId}" >${csMember.ourFullName}</option>
		                            </select>
                				</span>
                            </div>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">初始会员协议书编号</td>
                        <td>
                            <input type="text" class="form-control w300" id="agreementNo" name="agreementNo" value="${csMember.agreementNo}" placeholder="" notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">初始会员费</td>
                        <td>
                            <input type="text" class="form-control w300" id="csMemberAmount" name="csMemberAmount" value="${csMember.csMemberAmount}" placeholder="" notnull="true" datatype="moneyWithTwo" maxlength="9">
                            <span class="fc-warning"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="talabel required">甲方授权代表</td>
                        <td>
                            <input type="text" class="form-control w300" id="partyBNm" name="partyBNm" placeholder="请输入甲方授权代表" value="${csMember.partyBNm}" notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">甲方联系方式</td>
                        <td>
                            <input type="text" class="form-control w300" id="partyBTel" name="partyBTel" placeholder="请输入甲方联系方式" value="${csMember.partyBTel}" notnull="true" maxlength="11" >
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">业绩归属人</td>
                        <td>
                            <input type="hidden" id="exPersonId" name="exPersonId" value="${csMember.exPersonId}">
                            <input type="text" class="form-control w300" id="exPerson" name="exPerson" placeholder="" notnull="true" readonly="readonly" value="${csMember.exPerson}" style="background-color: #F2F2F2">
                            <button type="button" class="btn btn-primary"  onclick="javascript:achieveMentChange('1');">选择业绩归属人</button>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">业绩归属中心</td>
                        <td>
                            <input type="hidden" id="centerId" name="centerId" value="${csMember.centerId}">
                            <input type="hidden" class="form-control w300" id="centerName" name="centerName" value="${csMember.centerName}">
                            <select style="width:300px;" class="form-control" name="selectCenterName" notnull="true"  id="selectCenterName" >
							</select>
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">开户名</td>
                        <td>
                            <input type="text" class="form-control w300" notnull="true" id="accountNm" name="accountNm" value="${csMember.accountNm}" placeholder="请输入开户名" maxlength="50">
                        </td>
                        <td class="talabel required">开户省市</td>
                        <td>
							<span class="control-select">
								<input type="hidden" id="accountProvinceNm" name="accountProvinceNm">
								<select class="form-control" title="" id="accountProvinceNo" name="accountProvinceNo" required readonly style="width: 148px;">
									<option value="">请选择省份</option>
									<c:forEach items="${provinceList}" var="province">
									<option value="${province.provinceNo}" <c:if test="${province.provinceNo eq csMember.accountProvinceNo}"> selected</c:if>>${province.provinceName}</option>
									</c:forEach>
								</select>
							</span>
                            <span class="control-select">
								<input type="hidden" id="accountCityNm" name="accountCityNm">
								<select class="form-control" title="" id="accountCityNo" name="accountCityNo" required readonly style="width: 148px;">
									<c:forEach items="${cityList}" var="city">
										<option value="${city.cityNo}" <c:if test="${city.cityNo eq csMember.accountCityNo}"> selected</c:if> >${city.cityName}</option>
									</c:forEach>
								</select>
							</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">开户行</td>
                        <td>
                            <input type="text" class="form-control w300" id="bankAccountNm" name="bankAccountNm" value="${csMember.bankAccountNm}" placeholder="请输入开户行" maxlength="50" notnull="true">
                        </td>
                        <td class="talabel required">银行帐号</td>
                        <td>
                            <input type="text" class="form-control w300" id="bankAccount" name="bankAccount" value="${csMember.bankAccount}" dataType="bankAccount"  placeholder="请输入银行帐号" maxlength="50" notnull="true">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>

                    <tr>
                        <td class="talabel">合同备注</td>
                        <td>
                            <textarea maxlength="300" name="remark" id="remark" cols="30" rows="10" style="word-break:break-all;word-wrap: break-word;resize: none;width:895px;">${csMember.remark}</textarea>
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
            <div class="thumb-xs-box" id="fileBusinessBox">
		        <c:if test="${not empty csMember.fileBusinessList}">
		            <c:set var="fileSize" value="0"/>
		            <c:forEach items="${csMember.fileBusinessList}" var="list" varStatus="status">
		                <c:set var="fileSize" value="${fileSize + 1}"/>
		                <div class="thumb-xs-list item-photo-list">
		                    <a href="${list.fileUrl}" title="${list.fileName}" class="thumbnail swipebox" target="_blank">
		                        <span class="thumb-img">
		                            <span class="thumb-img-container">
		                                <span class="thumb-img-content">
		                                    <img alt="营业执照"  src="${list.fileAbbrUrl}" class="empPhoto"/>
		                                </span>
		                            </span>
		                        </span>
		                    </a>
		                </div>
		            </c:forEach>
		        </c:if>
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
               <c:if test="${not empty csMember.fileIdCardList }">
                   <c:set var="fileSize" value="0"/>
                   <c:forEach items="${csMember.fileIdCardList}" var="list" varStatus="status">
                       <c:set var="fileSize" value="${fileSize + 1}"/>
                       <div class="thumb-xs-list item-photo-list">
                           <a href="${list.fileUrl}" title="${list.fileName}" class="thumbnail swipebox" target="_blank">
                               <span class="thumb-img">
                                   <span class="thumb-img-container">
                                       <span class="thumb-img-content">
                                           <img alt="法人身份证"  src="${list.fileAbbrUrl}" class="empPhoto"/>
                                       </span>
                                   </span>
                               </span>
                           </a>
                       </div>
                   </c:forEach>
               </c:if>
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
               <c:if test="${not empty csMember.fileContractList }">
                   <c:set var="fileSize" value="0"/>
                   <c:forEach items="${csMember.fileContractList}" var="list" varStatus="status">
                       <c:set var="fileSize" value="${fileSize + 1}"/>
                       <div class="thumb-xs-list item-photo-list">
                           <a href="${list.fileUrl}" title="${list.fileName}" class="thumbnail swipebox"  tittle target="_blank">
                               <span class="thumb-img">
                                   <span class="thumb-img-container">
                                       <span class="thumb-img-content">
                                           <img alt="公盘系统服务协议"  src="${list.fileAbbrUrl}" class="empPhoto"/>
                                       </span>
                                   </span>
                               </span>
                           </a>
                       </div>
                   </c:forEach>
               </c:if>
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
						初始会员协议
					</h4>
					<div class="thumb-xs-box" id="csMemberFileBox">
					<c:if test="${csMember.csMemberFileList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${csMember.csMemberFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="初始会员协议" src="${list.fileAbbrUrl}" class="empPhoto" />
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
								<input type="hidden" name="fileTypeId" value="1054" />
								<input type="hidden" name="fileSourceId" value="16" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${csMember.csMemberFileList != null && csMember.csMemberFileList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1054" />
				                <input type="hidden" name="fileSourceId" value="16" />
				                <input type="hidden" name ="companyId" value="${csMember.companyId}">
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
						初始会员费付款凭证
					</h4>
					<div class="thumb-xs-box" id="csMemberPayBox">
					<c:if test="${csMember.csMemberPayFileList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${csMember.csMemberPayFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="csMemberPayFileList" src="${list.fileAbbrUrl}" class="empPhoto" />
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
								<input type="hidden" name="fileTypeId" value="1056" />
								<input type="hidden" name="fileSourceId" value="16" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${csMember.csMemberPayFileList != null && csMember.csMemberPayFileList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1056" />
				                <input type="hidden" name="fileSourceId" value="16" />
				                <input type="hidden" name ="companyId" value="${csMember.companyId}">
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
					<div class="thumb-xs-box" id="attachmentFile">
					<c:if test="${csMember.othersFileList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${csMember.othersFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="其他" src="${list.fileAbbrUrl}" class="empPhoto" />
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
								<input type="hidden" name="fileTypeId" value="1055" />
								<input type="hidden" name="fileSourceId" value="16" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${csMember.othersFileList != null && csMember.othersFileList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1055" />
				                <input type="hidden" name="fileSourceId" value="16" />
				                <input type="hidden" name ="companyId" value="${csMember.companyId}">
							</a>
						</div>
					</div>
				</div>
     </div>
 </div> 
<div class="text-center">
    <a href="javascript:void(0)" onclick="doSave()" class="btn btn-primary">保存</a>
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
