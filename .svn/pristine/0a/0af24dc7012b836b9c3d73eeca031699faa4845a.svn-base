<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>

<script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>

<script type="text/javascript" src="${ctx}/meta/js/contract/contractAdd.js?_v=${vs}"></script> 

<script type="text/javascript" src="${ctx}/meta/js/contract/contractCom.js?_v=${vs}"></script> 

<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateStores.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateCompany.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUser.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/template.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

<form id = "contractAddForm" >

	<input type="hidden"  id ="companyId"  name ="companyId"  value="">
	<input type ="hidden"  id = "companyInfoNo"   name ="companyNo" value=""/>
	<input type ="hidden"  id = "storeIdArray"   name = "storeIdArray"  />
	<input type ="hidden"  id = "centerId"   name = "centerId" value="${centerId}" />
	<!-- 存放经纪公司城市编码 -->
	<input type ="hidden"  id = "companyCityNo"   name = "companyCityNo" />
	<input type ="hidden"  id = "companyCityName"   name = "companyCityName" />
	<!-- 存放fangyou附件id集 -->
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
<!-- 	开户省市 -->
	<input type="hidden" name="accountProvinceName" id="accountProvinceName"  />
	<input type="hidden" name="accountCityName" id="accountCityName"  />
	
	<!-- 中介营业执照 -->
	<%--<input type="hidden" name="oaFileIdBusiness" id="oaFileIdBusiness" /><!-- oa -->--%>
	<!-- 中介法定代表人/负责人身份证或名片 -->
	<%--<input type="hidden" name="oaFileIdCard" id="oaFileIdCard" /><!-- oa -->--%>
	<!-- 合作协议书 -->
	<%--<input type="hidden" name="oaFileIdPhoto" id="oaFileIdPhoto" /><!-- oa -->--%>
	<!-- 中介门店照片（门店外景和室内） -->
	<%--<input type="hidden" name="oaFileIdStore" id="oaFileIdStore" /><!-- oa -->--%>
	 <!-- 房友系统申请安装单/确认单 -->
	<%--<input type="hidden" name="oaFileIdInstall" id="oaFileIdInstall" /><!-- oa -->--%>
	 <!-- 其他 -->
	<%--<input type="hidden" name="oaFileIdOther" id="oaFileIdOther" /><!-- oa -->--%>
	
	<!--  <input type="hidden" name="oaApproveType" id="oaApproveType" /><!-- OA审批流程类别 -->
	
	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4><strong>新建合同</strong></h4>
                <p><strong>基本信息</strong></p>
                <table class="table-sammary">
                	<col style="width:125px;">
                	<col style="width:auto;">
                	<col style="width:125px;">
                	<col style="width:auto;">
                	<tr>
                		<td class="talabel required">甲方名称</td>
                		<td>
                			<input type="text" class="form-control w300" id="partyB" name="partyB" placeholder=""   readonly="readonly" style="background-color: #F9F9F9">
                            <button type="button" class="btn btn-primary"  onclick="javascript:relateCompany('fromContract');" style="vertical-align: top;">选择公司</button>
                        </td>
                        <td class="talabel required">法定代表/负责人</td>
                		<td>
                			<input type="text" class="form-control w300" id="lealPerson" name="lealPerson" placeholder=""  notnull="true"  maxlength="20" readonly="readonly" style="background-color: #F9F9F9">
                            <!-- <span class="fc-warning"></span>-->
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">公司注册地址</td>
                		<td colspan="3">
                			<input type="hidden" class="form-control w120" id="partyBcityNo" name="partyBcityNo" >
                            <input type="hidden" class="form-control w120" id="partyBdistrictNo" name="partyBdistrictNo" >
                            <input type="hidden" class="form-control w120" id="partyBareaNo" name="partyBareaNo" >
                            <input type="text" class="form-control w120" id="partyBCityName" name="partyBCityName"  notnull="true"  readonly="readonly" style="background-color: #F9F9F9">
                            <input type="text" class="form-control w200" id="partyBDistrictName" name="partyBDistrictName"  notnull="true"  readonly="readonly" style="width:132px;background-color: #F9F9F9">
                            <!-- <input type="text" class="form-control w120" id="partyBAreaName" name="partyBAreaName"  readonly="readonly"> -->
                            <input type="text" class="form-control w300" id="partyBAddress" name="partyBAddress"  notnull="true"  maxlength="100"  readonly="readonly" style="background-color: #F9F9F9">
                            <!-- <span class="fc-warning"></span>-->
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">统一社会信用代码</td>
                		<td>
                			<input type="text" class="form-control w300" readonly="readonly" id="registrId" name="registrId" placeholder=""  notnull="true"  maxlength="30"  style="background-color: #F9F9F9">
                            <span class="fc-warning"></span>
                        </td>
                         <td class="talabel required">协议书编号</td>
                		<td>
                			 <input type="text" class="form-control w300" id="agreementNo" name="agreementNo" placeholder=""  notnull="true"  maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">合作模式</td> 
                		<td colspan="3">
                         	<input type="radio" value="10307" id="10307" name="contractType" onchange="Contract.changeContract(this);" style="vertical-align: -2px; margin-right: 3px;"><label for="10307" class="fon-normal small" style="margin-right:5px;">授牌</label>
                         	<input type="radio" value="10302" id="10302" name="contractType" onchange="Contract.changeContract(this);" style="vertical-align: -2px; margin-right: 3px;"><label for="10302" class="fon-normal small" style="margin-right:5px;">B</label>
                            <%-- <c:forEach items="${contractTypeList}" var="contractTypeList">
                            	<c:if test="${contractTypeList.dicCode == 10302 || contractTypeList.dicCode == 10307 }">
                            		<input type="radio" value="${contractTypeList.dicCode}" id="${contractTypeList.dicCode}" name="contractType" onchange="Contract.changeContract(this);" style="vertical-align: -2px; margin-right: 3px;"><label for="${contractTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${contractTypeList.dicValue}</label>
                            	</c:if>
                        	</c:forEach> --%>
                        </td>
                	</tr>
                  <!--Add 2017/4/6 cning start  -->
	                <tr id="agreementTypeId">
	                	<td class="talabel required">合作协议书类型</td> 
	                	<td colspan="3">
	                		<c:forEach items="${agreementTypeList}" var="agreementTypeList">
	                		<!-- Add By NingChao 2017/04/07 Start -->                		
	                			<c:if test="${agreementTypeList.dicCode != 11003 && agreementTypeList.dicCode != 11001 && agreementTypeList.dicCode != 11006}">
	                		<!-- Add By NingChao 2017/04/07 End -->
	                        		<input type="radio" value="${agreementTypeList.dicCode}" id="${agreementTypeList.dicCode}" name="agreementType"  >
									<label for="${agreementTypeList.dicCode}" class="fon-normal small">${agreementTypeList.dicValue}</label>
								<!-- Add By NingChao 2017/04/07 Start -->         
								</c:if>
								<!-- Add By NingChao 2017/04/07 End -->
							</c:forEach>  
	                	</td>
	                </tr>
                	<tr id="OAapproval" style="display:none;"> 
                		<td class="talabel required">OA审批流程类别</td>
                		<td colspan="3">
                			<c:forEach items="${oaApproveTypeList}" var="oaApproveTypeList">
                        		<input type="radio" value="${oaApproveTypeList.dicCode}" id="${oaApproveTypeList.dicCode}" name="oaApproveType" style="vertical-align: -2px; margin-right: 3px;"><label for="${oaApproveTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${oaApproveTypeList.dicValue}</label>
                        	</c:forEach>
                		</td>
                	</tr>
                	<tr id="shoupaiTypeLi" style="display:none;"> 
                		<td class="talabel required">授牌类型</td>
                		<td colspan="3">
                			<c:forEach items="${shoupaiTypeList}" var="shoupaiTypeList">
                        		<input type="radio" value="${shoupaiTypeList.dicCode}" id="${shoupaiTypeList.dicCode}" onchange="Contract.changeShoupaiType(this);" name="shoupaiType" style="vertical-align: -2px; margin-right: 3px;">
                        		<label for="${shoupaiTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${shoupaiTypeList.dicValue}</label>
                        	</c:forEach>
                		</td>
                	</tr>
                	<tr>
                		<td class="talabel required">合同生效日期</td>
                		<td>
                			<input  type="text" class="calendar-icon form-control w300" name="dateLifeStart" id="dateLifeStart" 
                				onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'dateLifeEnd\',{d:-1});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                			class="ipttext Wdate"  notnull="true"/>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">合同到期日期</td>
                		<td>
                			<input  type="text" class="calendar-icon form-control w300" name="dateLifeEnd" id="dateLifeEnd" 
                				onFocus="WdatePicker({minDate:'#F{$dp.$D(\'dateLifeStart\',{d:1});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                			class="ipttext Wdate"  notnull="true"/>
                            <span class="fc-warning"></span>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">甲方授权代表</td>
                		<td>
                			<input type="hidden" class="form-control w300" id="contractNo" name="contractNo" placeholder="系统生成" value="" readonly="readonly">
                			<input type="text" class="form-control w300" id="authRepresentative" name="authRepresentative" placeholder=""  notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                       <td class="talabel required">联系方式</td>
                		<td>
                			<input type="text" class="form-control w300" id="agentContact" name="agentContact" placeholder="" maxlength="11" >
                        	<span class="fc-warning"></span>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">每店保证金额</td>
                		<td>
                			<input type="text" class="form-control w300" id="depositFee" name="depositFee" placeholder=""  notnull="true"  onblur="calculateTotleDepositFee(2)" datatype="needInteger"  maxlength="9" >
                			<span style="color:#FF0000;" id="errorTip"> &nbsp;请输入整数!</span>
                            <!-- <span class="fc-warning"></span> -->
                        </td>
                        <td class="talabel required">合作门店数</td>
                		<td>
                			<input type="text" class="form-control w300" id="storeNum" name="storeNum" placeholder="" notnull="true" datatype="posNumWithOutZero"  maxlength="9"  readonly="readonly" style="background-color: #F9F9F9">
                            <span class="fc-warning"></span>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">总保证金</td>
                		<td>
                			<input type="text" class="form-control w300" id="totleDepositFee" name="totleDepositFee" placeholder=""  notnull="true"  datatype="moneyWithFlot"  maxlength="9"  readonly="readonly" style="background-color: #F9F9F9">
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">违约金金额</td>
                		<td>
                			<input type="text" class="form-control w300" id="penaltyFee" name="penaltyFee" placeholder="" notnull="true"  datatype="needInteger"  maxlength="9" onblur="listeningpenaltyFee()">
                            <!-- <span class="fc-warning"></span> -->
                            <span style="color:#FF0000;" id="errorTip2"> &nbsp;请输入整数!</span>
                        </td>
                	</tr>
                	
                	<tr>
                		<td class="talabel" id="accountNameLabel">开户名</td>
                		<td>
                			<input type="text" class="form-control w300" id="accountName" name="accountName" placeholder="" value=""  maxlength="20">
                        </td>
                        <td class="talabel "  id="accountProvinceNoLabel">开户省市</td>
                		<td >
                			<div class="control-group">
                				<span class="control-select">
                					<select class="form-control w120" title="" id="accountProvinceNo" name="accountProvinceNo" >
			                              <option value="" selected="selected">请选择省</option>
		                					    <c:forEach items="${provinceList}" var="city">
					                            <option value="${city.provinceNo}">${city.provinceName}</option>
					                      </c:forEach>
		                            </select>
                				</span>
                				<span class="control-select">
                					<select class="form-control w180" Style="width:177px;" id="accountCityNo" name="accountCityNo">
		                            	<option selected="selected" value="">请选择城市</option>	
		                            </select>
                				</span>
<!--                 				<input type="text" class="form-control w300" id="accountCityName" name="accountCityName" placeholder="具体地址信息" value=""  maxlength="100"> -->
                			</div>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel" id="bankAccountLabel">开户行</td>
                		<td>
                            <input type="text" class="form-control w300" id="bankAccount" name="bankAccount" placeholder="" value=""  maxlength="30">
                        </td>
                        <td class="talabel " id="companyBankNoLabel">银行账号</td>
                		<td>
                			<input type="text" class="form-control w300" id="companyBankNo" name="companyBankNo" oninput="this.value=this.value.replace(/[^0-9a-zA-Z/-]+/g,'')" placeholder="" value=""  maxlength="50">
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">业绩归属拓展人</td>
                		<td>
                			<input type="hidden" id="expandingPersonnelId" name="expandingPersonnelId" value="${userId}">
                			<input type="text" class="form-control w300" id="expandingPersonnel" name="expandingPersonnel" value="${userName}" placeholder=""  notnull="true" readonly="readonly" style="background-color: #F9F9F9">
                             <!-- <button type="button" class="btn btn-primary"  onclick="javascript:relateUser(1,null);">选择</button> -->
                             <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">业绩归属中心</td>
                		<td>
                			<input type="text" class="form-control w300" id="centerName" name="centerName" value="${centerName}" placeholder=""  notnull="true" readonly="readonly" style="background-color: #F9F9F9">
                            <span class="fc-warning"></span>
                        </td>
                	</tr>
                	<tr>
                        <td class="talabel">甲方收件人</td>
                		<td>
                            <input type="text" class="form-control w300" id="recipients" name="recipients" placeholder="" value=""  maxlength="20">
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel">甲方收件地址</td>
                		<td colspan="3">
                			<div class="control-group">
                				<span class="control-select">
                					<select class="form-control w120" title="" id="cityNo" name="cityNo">
			                              <option value="" selected="selected">请选择</option>
		                					    <c:forEach items="${citylist}" var="city">
					                            <option value="${city.cityNo}">${city.cityName}</option>
					                      </c:forEach>
		                            </select>
                				</span>
                				<span class="control-select">
                					<select class="form-control w200"  id="districtNo" name="districtNo">
		                            	<option selected="selected" value="">请选择行政区</option>	
		                            </select>
                				</span>
                				<input type="text" class="form-control w300" id="recipientsAddress" name="recipientsAddress" placeholder="具体地址信息" value=""  maxlength="100">
                			</div>
                        </td>
                	</tr>
                	
	                
                <!--Add 2017/4/6 cning end  -->
                	
                	<%-- <tr id="B2Achange">
                		<td class="talabel required" >是否乙类转甲类</td>
                		<td colspan="2">
                			<c:forEach items="${ContractTypeB2AList}" var="ContractTypeB2AList">
                        		<input type="radio" value="${ContractTypeB2AList.dicCode}" id="${ContractTypeB2AList.dicCode}" name="ContractTypeB2A" style="vertical-align: -2px; margin-right: 3px;"><label for="${ContractTypeB2AList.dicCode}" class="fon-normal small" style="margin-right:5px;">${ContractTypeB2AList.dicValue}</label>
                        	</c:forEach>
                		</td>
                	</tr> --%>
                	<input type="hidden" name="ContractTypeB2A" id="20202" value="20202"/>
                </table>
                <p><strong>门店信息</strong></p>
                <!--<ul class="list-inline half form-inline">
                    <li>
                        <button type="button" class="btn btn-primary"  onclick="javascript:relateStores('fromContract');">关联门店信息</button>
                    </li>
                </ul>-->
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                	<tr class="isShowClass">
		                <th style="width:80px">门店名称</th>
		            <!--    <th style="width:60px">所在区域</th>-->
		                <th style="width:80px">门店地址</th>
		            <!--    <th style="width:80px">创建日期</th>-->
		                <th style="width:60px">门店维护人</th>
		                <th style="width:60px">门店负责人</th>
		                <th style="width:80px">负责人电话</th>
		                <th style="width:100px" id="storeGrade">门店资质等级</th>
		                <th style="width:50px">操作</th>
		            </tr>
                </table>
				<table class="table-sammary">
					<col style="width:125px;">
					<col style="width:auto;">
					<tr>
						<td class="talabel">合同备注</td>
						<td>
							<textarea maxlength="300" name="remark" id="remark" cols="30" rows="10"  style="word-break:break-all;word-wrap: break-word;resize: none;"></textarea>
						</td>
					</tr>
				</table>
             </div>
        </div>
    </div>
</form>

<!-- 中介营业执照 -->


<div class="container theme-hipage ng-scope" role="main">
	<p><strong>附件</strong></p>
</div>
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						营业执照
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="1" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
					<i class="fontset">注：营业执照须字迹清晰。</i>
				</div>
     </div>
 </div> 

<!-- 中介法定代表人/负责人身份证或名片 -->
 <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						身份证
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="2" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
					<i class="fontset">注：身份证正反面，照片清晰。</i>
				</div>
     </div>
 </div>

<!-- 合作协议书 -->
 <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						<i>*</i>合同照片
					</h4>
					<div class="thumb-xs-box" id="fileIdPhotoBox">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="3" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
					<i class="fontset">注：含合同封皮的正反面页、合同要素表页、全部附件页（附件合作确认函正文无需填写，仅在骑缝处盖章；空白附件划掉后上传）。</i>
				</div>
     </div>
 </div>

<!-- 中介门店照片（门店外景和室内） -->
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						门店照片
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="4" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
					<i class="fontset">注：含店招、门牌号（须字迹清晰可辨识）的内外景照片，乙类须有授权经理或以上入镜。</i>
				</div>
     </div>
 </div>
 
 <!-- 房友系统申请安装单/确认单 -->
 <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						房友确认单
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="5" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
					<i class="fontset">注：房友确认单须中介盖章且与门店实际经营地址一致。</i>
				</div>
     </div>
 </div>
 <!--重要提示函 -->
 <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						重要提示函
					</h4>
					<div class="thumb-xs-box" id="noticeBox">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="1020" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
					<i class="fontset">注：须中介签字并盖章确认。</i>
				</div>
     </div>
 </div>
 <!-- <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						补充协议
					</h4>
					<div class="thumb-xs-box" id="complementBox">todo
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="1026" />todo
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
					<i class="fontset">注：需双方均已盖章。</i>
				</div>
     </div>
 </div> -->

 <!-- 其他 -->
 <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						其他
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="6" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
					<i class="fontset">注：乙类须提供《承诺书》及店东手持或签字时照片，乙一类另须提供核名单。</i>
				</div>
     </div>
 </div>
 
                <div class="text-center">
                  	<a href="javascript:Contract.add();" class="btn btn-primary">草签</a>
	         		<a href="${ctx}/contract" class="btn btn-default mgl20">取消</a>
                </div>
	<div class="lockHandle" style="display:none;position: fixed;left: 0px; top: 0px; width: 100%;height: 100%;overflow: hidden;z-index: 1981;background: #000;filter: alpha(opacity=40);opacity: .4;"></div>
</body>

<%-- 使用template模板  --%>
<!-- 通用的图片显示 模板 -->
<script type="text/html" id="template_relateStoreTable">
{{if list}}
		{{each list}} 
	<tr>
		<td data-storeno={{$value.storeNo}}>{{$value.name}}</td>
		<td>{{$value.addressDetail}}</td>
		<td>{{$value.maintainerName}}</td>
		<td><input style='border: 1px solid #ccc;border-radius: 4px;height:28px' size='10' id='contactName{{$value.storeId}}' name='contactName{{$value.storeId}}' notnull='true' attr='ctname' value='{{$value.storeManager}}'></td>
		<td><input style='border: 1px solid #ccc;border-radius: 4px;height:28px' size='15' maxlength='11' id='contactPhoneNo{{$value.storeId}}' name='contactPhoneNo{{$value.storeId}}' notnull='true' attr='ctphone' value='{{$value.storeManagerPhone}}'></td>
		<td class="storeGradedata">
			<select  class='storetype' id='storetype{{$value.storeId}}' name='storetype{{$value.storeId}}' style='width:70px;' onchange='storetypeChange(this)'>
				<option value=''>请选择</option>
				<option value='19901' {{$value.aType}}>甲类</option>
				<option value='19902' {{$value.bType}}>乙类</option>
			</select>
			<input type='text' class="c3" readonly='readonly' style='width:70px;{{$value.bTypediv}};' id='bTypenamelst{{$value.storeId}}' name='bTypenamelst{{$value.storeId}}' value='{{$value.bTypenamelst}}'>
		</td>
		<td>
			<a href='javascript:void(0)' onclick='removeTr(this ,1)'>删除</a>
		</td>
		<td style="display:none;">
			<input type='hidden' class="cn3" id='storetypeBlst{{$value.storeId}}' name='storetypeBlst{{$value.storeId}}' value='{{$value.bTypelst}}'>
			<input type='hidden' id='maintainer{{$value.storeId}}' name='maintainer{{$value.storeId}}' attr='mtc' value='{{$value.maintainer}}'>
			<input name='storeIds' id='storeIds{{$value.storeId}}' type='hidden' value='{{$value.storeId}}'>
		</td>
	<tr>
{{/each}}
{{/if}} 
</script>

</html>
