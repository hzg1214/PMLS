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
    <script type="text/javascript" src="${ctx}/meta/js/gpContract/gpContractAdd.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/relate/relateCompany.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/template.js?_v=${vs}"></script>
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

<form id="gpAddForm">
    <input type="hidden" id="companyId" name="companyId" value="">
    <input type="hidden" id="storeIdArray" name="storeIdArray"/>
    <input type="hidden" id="centerId" name="centerId" value="${centerId}"/>
    <input type="hidden" id="centerName" name="centerName" value="${centerName}"/>
    <!-- 存放经纪公司城市编码 -->
    <input type="hidden" id="companyCityNo" name="companyCityNo"/>
    <input type="hidden" id="companyCityName" name="companyCityName"/>
    <!-- 存放fangyou附件id集 -->
    <input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"/>

    <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4><strong>新建公盘合同</strong></h4>
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
                            <button type="button" class="btn btn-primary"  onclick="javascript:relateCompany('fromGpContract');" style="vertical-align: top;">选择公司</button>
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
                					<select class="form-control w300" title="" id="ourFullId" name="ourFullId" notnull="true">
			                              <option value="" selected="selected">请选择</option>
		                					    <c:forEach items="${fullNameList}" var="list">
                                                    <option value="${list.id}">${list.name}</option>
                                                </c:forEach>
		                            </select>
                				</span>
                            </div>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">公盘协议书编号</td>
                        <td>
                            <input type="text" class="form-control w300" id="gpAgreementNo" name="gpAgreementNo" placeholder="" notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel"><i class="icon-explain" title="该甲方公司所有的有效的B模式合同保证金到账金额之和-保证金退款金额之和"></i>&nbsp;翻牌到账保证金</td>
                        <td>
                            <input type="text" style="background-color: #F9F9F9" readonly="readonly" class="form-control w300" id="deposit" name="deposit" placeholder="" datatype="moneyWithTwo" maxlength="9">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">门店数</td>
                        <td>
                            <input type="text" class="form-control w300" id="storeNum" name="storeNum" placeholder="" notnull="true" datatype="posNumWithOutZero" maxlength="9" readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                        <td class="talabel required">保证金</td>
                        <td>
                            <input type="text" class="form-control w300" id="depositFee" name="depositFee" placeholder="" notnull="true" datatype="moneyWithTwo" maxlength="9">
                            <span class="fc-warning"></span>
                        </td>

                    </tr>
                    <tr>
                        <td class="talabel required">合同生效日期</td>
                        <td>
                            <input type="text" class="calendar-icon form-control w300" name="dateLifeStart" id="dateLifeStart"
                                   onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'dateLifeEnd\',{d:-364});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                   class="ipttext Wdate" notnull="true"/>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">合同到期日期</td>
                        <td>
                            <input type="text" class="calendar-icon form-control w300" name="dateLifeEnd" id="dateLifeEnd"
                                   onFocus="WdatePicker({minDate:'#F{$dp.$D(\'dateLifeStart\',{d:364});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                   class="ipttext Wdate" notnull="true"/>
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
                            <input type="hidden" id="exPersonId" name="exPersonId" value="${userId}">
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
                            <%-- <input type="text" class="form-control w300"  id="centerName" name="centerName" value="${centerName}" placeholder="" notnull="true" readonly="readonly"
                                   style="background-color: #F9F9F9"> --%>
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
                            <input type="text" class="form-control w300" id="bankAccount" name="bankAccount" oninput="this.value=this.value.replace(/[^0-9a-zA-Z/-]+/g,'')" dataType="bankAccount"  placeholder="请输入银行帐号" maxlength="50" notnull="true">
                            <span class="fc-warning"></span>
                        </td>
                    </tr>

                    <tr>
                        <td class="talabel">合同备注</td>
                        <td>
                            <textarea maxlength="300" name="remark" id="remark" cols="30" rows="10" style="word-break:break-all;word-wrap: break-word;resize: none;width:885px;"></textarea>
                        </td>
                    </tr>
                </table>
                <p><strong>门店信息</strong></p>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                    <tr class="isShowClass">
                        <th style="width:80px">门店编号</th>
                        <th style="width:80px">门店名称</th>
                        <th style="width:60px">门店维护人</th>
                        <th style="width:60px">门店负责人</th>
                        <th style="width:80px">负责人电话</th>
                        <%--<th style="width:120px">资质等级</th>--%>
                        <th style="width:50px">操作</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>
<div class="container theme-hipage ng-scope" role="main">
    <p><strong>附件</strong></p>
</div>
<div class="container theme-hipage ng-scope" role="main" id="csMemberFileBox1">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>营业执照
            </h4>
            <div class="thumb-xs-box" id="fileBusinessBox">
                <div class="thumb-xs-list item-photo-add" id="fileBusiness">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                        <input type="hidden" name="fileTypeId" value="1" />
                        <input type="hidden" name="fileSourceId" value="12" />
                        <input type="hidden" name ="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：营业执照须字迹清晰。</i>
        </div>
    </div>
</div>

<!-- 中介法定代表人/负责人身份证或名片 -->
<div class="container theme-hipage ng-scope" role="main"  id="fileIdCardBox1">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>法人身份证
            </h4>
            <div class="thumb-xs-box" id="fileIdCardBox">
                <div class="thumb-xs-list item-photo-add" id="fileIdCard">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                        <input type="hidden" name="fileTypeId" value="2" />
                        <input type="hidden" name="fileSourceId" value="12" />
                        <input type="hidden" name ="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：身份证正反面，照片清晰。</i>
        </div>
    </div>
</div>
<!-- 公盘协议书 -->
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>公盘系统服务协议
            </h4>
            <div class="thumb-xs-box" id="fileContractBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="50">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="50" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="3"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：必须上传公盘协议书所有页面。</i>
        </div>
    </div>
</div>
<%--<!-- 授权委托书 -->
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>授权委托书（确认易居房友15日可支付房源方分成佣金）
            </h4>
            <div class="thumb-xs-box" id="fileProxyBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="1042"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：委托方需加盖公章确认。</i>
        </div>
    </div>
</div>--%>

<!-- 直联盘勾选表 -->
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i></i>直联盘勾选表
            </h4>
            <div class="thumb-xs-box" id="fileCheckBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="1061"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：每个门店需提供一份。</i>
        </div>
    </div>
</div>

<!-- 授权委托书 -->
<div class="container theme-hipage ng-scope" role="main">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <i>*</i>易居房友经纪业务共享平台规则
            </h4>
            <div class="thumb-xs-box" id="fileRuleBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="1062"/>
                        <input type="hidden" name="fileSourceId" value="12"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
            <i class="fontset">注：需加盖公章。</i>
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
                        <input type="hidden" name="fileTypeId" value="6" />
                        <input type="hidden" name="fileSourceId" value="12" />
                        <input type="hidden" name ="companyId" value="">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="text-center">
    <a href="javascript:GpContract.add();" class="btn btn-primary">草签</a>
    <a href="${ctx}/gpContract" class="btn btn-default mgl20">取消</a>
</div>
<div class="lockHandle" style="display:none;position: fixed;left: 0px; top: 0px; width: 100%;height: 100%;overflow: hidden;z-index: 1981;background: #000;filter: alpha(opacity=40);opacity: .4;"></div>
</body>

<%-- 使用template模板  --%>
<!-- 通用的图片显示 模板 -->
<script type="text/html" id="template_relateStoreTable">
    {{if list}}
    {{each list}}
    <tr>
        <td>{{$value.storeNo}}</td>
        <td data-storeno={{$value.storeNo}}>{{$value.name}}</td>
        <td>{{$value.maintainerName}}</td>
        <td><input style='border: 1px solid #ccc;border-radius: 4px;height:28px' size='10' id='contactName{{$value.storeId}}' name='contactName{{$value.storeId}}' notnull='true' attr='ctname' value='{{$value.storeManager}}'></td>
        <td><input style='border: 1px solid #ccc;border-radius: 4px;height:28px' size='15' maxlength='11' id='contactPhoneNo{{$value.storeId}}' name='contactPhoneNo{{$value.storeId}}' notnull='true' attr='ctphone' value='{{$value.storeManagerPhone}}'></td>
        <%--<td class="storeGradedata">
            <select  class='storetype' id='storetype{{$value.storeId}}' name='storetype{{$value.storeId}}' style='width:70px;' onchange='storetypeChange(this)'>
                <option value=''>请选择</option>
                <option value='19901' {{$value.aType}}>甲类</option>
                <option value='19902' {{$value.bType}}>乙类</option>
            </select>
                <input type='text' class="c3" readonly='readonly' style='width:70px;{{$value.bTypediv}};' id='bTypenamelst{{$value.storeId}}' name='bTypenamelst{{$value.storeId}}' value='{{$value.bTypenamelst}}'>
                <input type='hidden' class="cn3" id='storetypeBlst{{$value.storeId}}' name='storetypeBlst{{$value.storeId}}' value='{{$value.bTypelst}}'>
        </td>--%>
        <td>
            <a href='javascript:void(0)' onclick='removeTr(this ,2)'>删除</a>
        </td>
        <td style="display:none;">
            <input type='hidden' id='maintainer{{$value.storeId}}' name='maintainer{{$value.storeId}}' attr='mtc' value='{{$value.maintainer}}'>
            <input name='storeIds' id='storeIds{{$value.storeId}}' type='hidden' value='{{$value.storeId}}'>
        </td>
    <tr>
        {{/each}}
        {{/if}}
</script>
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
</html>
