<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
    <script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>

    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/store/storeEdit.js?_v=${vs}"></script>
    <%--<script type="text/javascript" src="${ctx}/meta/js/store/storeMap.js?_v=${vs}"></script>--%>
    <script type="text/javascript" src="${ctx}/meta/js/store/storeUpload.js?_v=${vs}"></script>
<style>
	span.thumb-img-content img{
		    z-index: 2;
	}
</style>
</head>
<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>
<form id="storeEditForm">
    <input type="hidden" id="storeId" name="storeId" value="${storeDetail.storeId}"/>
    <input type="hidden" name="longitude" id="longitude" value="${storeDetail.longitude}">
    <input type="hidden" name="latitude" id="latitude" value="${storeDetail.latitude}">
    <input type="hidden" name="pictureRefId" id="pictureRefId" value="${storeDetail.pictureRefId}">

    <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>编辑门店</strong></h4>
                <p><strong>基本信息</strong></p>
                <table class="table-sammary">
                    <col style="width:150px;">
                    <col style="width:auto;">
                    <col style="width:125px;">
                    <col style="width:auto;">
                    <tr>
                        <td class="talabel required">门店名称：</td>
                        <td><input type="hidden" name="name" id="name" value="${storeDetail.name}">
                            ${storeDetail.name}</td>
                        <td class="talabel required">门店地址：</td>
                        <td><input type="hidden" name="addressDetail" id="addressDetail"
                                   value="${storeDetail.addressDetail}">
                            ${storeDetail.addressDetail}</td>
                    </tr>
                    <tr>
                        <td class="talabel">门店负责人：</td>
                        <td><input type="text" class="form-control w300" id="storeManager" name="storeManager"
                                   value="${storeDetail.storeManager}"></td>
                        <td class="talabel">负责人电话：</td>
                        <td><input type="text" class="form-control w300" id="storeManagerPhone" name="storeManagerPhone"
                                   value="${storeDetail.storeManagerPhone}"></td>
                    </tr>
                    <tr>
                        <td class="talabel">连锁情况：</td>
                        <td><input type="text" class="form-control w300" id="linkageSituation" name="linkageSituation"
                                   value="${storeDetail.linkageSituation}"></td>
                        <td class="talabel">连锁品牌：</td>
                        <td>
                            <select class="form-control w300" title="" id="chainBrand" name="chainBrand">
                                <option
                                        <c:if test="${empty storeDetail.chainBrand or storeDetail.chainBrand eq ''}">selected="selected"</c:if>
                                        value="">请选品牌
                                </option>
                                <c:if test="${!empty chainBrandList}">
                                    <c:forEach items="${chainBrandList}" var="brand">
                                        <option value="${brand.brandNo}"
                                                <c:if test="${storeDetail.chainBrand eq brand.brandNo}">selected="selected"</c:if>>${brand.brandName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">经纪人数：</td>
                        <td>
                            <select class="form-control w300" title="" id="storePersonNum" name="storePersonNum">
                                <option
                                        <c:if test="${empty storeDetail.storePersonNum or storeDetail.storePersonNum eq ''}">selected="selected"</c:if>
                                        value="">请选经纪人数
                                </option>
                                <c:if test="${!empty storePersonList}">
                                    <c:forEach items="${storePersonList}" var="storePerson">
                                        <option value="${storePerson.dicCode}"
                                                <c:if test="${storeDetail.storePersonNum eq storePerson.dicCode}">selected="selected"</c:if>>${storePerson.dicValue}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </td>
                        <td class="talabel">当前使用系统：</td>
                        <td><input type="text" class="form-control w300" id="nowUserSystem" name="nowUserSystem"
                                   value="${storeDetail.nowUserSystem}"></td>
                    </tr>
                    <tr>
                        <td class="talabel">加盟到期时间：</td>
                        <td>
                            <input type="text" class="calendar-icon form-control w300" name="storeDueTime"
                                   id="storeDueTime"
                                   value="${storeDetail.storeDueTime}"
                                   onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                   class="ipttext Wdate"/>
                        </td>
                        <td class="talabel">租赁到期时间：</td>
                        <td>
                            <input type="text" class="calendar-icon form-control w300" name="storeLeaseDueTime"
                                   id="storeLeaseDueTime"
                                   value="${storeDetail.storeLeaseDueTime}"
                                   onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                   class="ipttext Wdate"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">主营业务：</td>
                        <td>
                            <div class="multi-select w300">
                                <input type="hidden" class="multi-select-value" readonly="readonly" id="mainBusiness"
                                       name="mainBusiness" value="${fn:replace(storeDetail.mainBusiness,',',',')}">
                                <input type="text" class="multi-select-text w300" id="mainBusinessName"
                                       name="mainBusinessName"
                                       value="${fn:replace(storeDetail.mainBusinessName,',',';')}" readonly>
                                <ul class="multi-select-list">
                                    <c:if test="${!empty mainBusinessList}">
                                        <c:forEach items="${mainBusinessList}" var="mainBusiness">
                                            <li class="multi-select-item">
                                                <label><input type="checkbox" value="${mainBusiness.dicCode}"
                                                              data-text="${mainBusiness.dicValue}"
                                                              <c:if test="${fn:contains(storeDetail.mainBusiness,mainBusiness.dicCode)}">checked</c:if>><span>${mainBusiness.dicValue}</span></label>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>
                        </td>
                        <td class="talabel">交易方式：</td>
                        <td>
                            <select class="form-control w300" title="" id="transactionMode" name="transactionMode">
                                <option
                                        <c:if test="${empty storeDetail.transactionMode or storeDetail.transactionMode eq ''}">selected="selected"</c:if>
                                        value="">请选交易方式
                                </option>
                                <c:if test="${!empty transactionModeList}">
                                    <c:forEach items="${transactionModeList}" var="transactionMode">
                                        <option value="${transactionMode.dicCode}"
                                                <c:if test="${storeDetail.transactionMode eq transactionMode.dicCode}">selected="selected"</c:if>>${transactionMode.dicValue}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">经营场所类型：</td>
                        <td>
                        	<input type="hidden" name="businessPlaceEditFlag" valeu="${storeDetail.businessPlaceEditFlag}" />
	                            <c:forEach items="${businessPlaceTypeList}" var="list">
	          						<input type="radio" value="${list.dicCode}"  id="businessPlaceType"  name="businessPlaceType" <c:if test="${list.dicCode eq storeDetail.businessPlaceType}">checked</c:if>
	          						<c:if test="${storeDetail.businessPlaceEditFlag eq '0' || storeDetail.businessPlaceEditFlag eq '1' ||storeDetail.businessPlaceEditFlag eq '2'}">disabled="disabled"</c:if>
	          						>
									<label class="fon-normal small">${list.dicValue}</label>
								</c:forEach>
                        </td>
                        <td class="talabel required">门店规模：</td>
                        <td>
	                            <c:forEach items="${storeSizeScaleList}" var="list">
	          						<input type="radio" value="${list.dicCode}"  id="storeSizeScale"  name="storeSizeScale" <c:if test="${list.dicCode eq storeDetail.storeSizeScale}">checked</c:if>
	          						<c:if test="${storeDetail.businessPlaceEditFlag eq '0' || storeDetail.businessPlaceEditFlag eq '1' ||storeDetail.businessPlaceEditFlag eq '2'}">disabled="disabled"</c:if>
	          						>
									<label class="fon-normal small">${list.dicValue}</label>
								</c:forEach>
                        </td>
                    </tr>
                    <c:if test="${storeDetail.renewFlag eq 18302}">
                        <tr>
                            <td class="talabel required">是否续签：</td>
                            <td>
                                <c:forEach items="${neededRenewList}" var="list">
                                    <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="neededRenew"
                                           <c:if test="${storeDetail.neededRenew eq list.dicCode}">checked</c:if>
                                           style='vertical-align:-3px'>
                                    <label for="${list.dicCode}" class="fon-normal small">${list.dicValue}</label>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${btoAAlertFalg eq 1}">
                        <tr>
                            <td class="talabel required">乙转甲提醒：</td>
                            <td>
                                <input type="radio" value="1" id="bToAAlert1" name="bToAAlert" <c:if test="${storeDetail.btoAAlert eq 1}">checked</c:if> style='vertical-align:-3px'>
                                <label for="bToAAlert1" class="fon-normal small">开启</label>
                                <input type="radio" value="2" id="bToAAlert2" name="bToAAlert" <c:if test="${storeDetail.btoAAlert eq 2}">checked</c:if> style='vertical-align:-3px'>
                                <label for="bToAAlert2" class="fon-normal small">关闭</label>
                            </td>
                        </tr>
                        <tr id="descTr">
                            <td class="talabel required" style="width:130px;">乙转甲提醒&nbsp;&nbsp;&nbsp;<p>关闭说明：</p></td>
                            <td>
                                <textarea type="textarea" notnull="true" maxlength="500" required="true" class="vera validatebox-text validatebox-invalid" id="bToAAlertDesc" style="width:880px;height:100px;padding:0px;" name="bToAAlertDesc" data-options="required:true">${storeDetail.btoAAlertDesc}</textarea>
                            </td>
                        </tr>
                    </c:if>
                </table>

                <p><strong>门店图片</strong></p>
                <input type="hidden" name="fileRecordMainId" value="${storeDetail.fileRecordMainId}"
                       id="fileRecordMainId"/>
                <input type="hidden" name="fileTypeId" value="4"/>
                <input type="hidden" name="fileSourceId" value="2"/>


                <!-- 门店图片列表 -->
                <table class="table-sammary">
                    <col style="width:140px;">
                    <col style="width:auto;">
                    <tr>
                        <td colspan="2">
                            <div class="container theme-hipage ng-scope" role="main">
                                <div class="row">
                                    <div class="pd10">
                                        <%--<h4 class="thumb-title">--%>
                                        <%--门店图片--%>
                                        <%--</h4>--%>
                                        <div class="thumb-xs-box" id="thumbXsBox">
                                            <c:if test="${storeDetail.fileUrl!=null && storeDetail.fileUrl!=''}">
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="${storeDetail.fileUrl}" class="thumbnail swipebox"
                                                       target="_blank">
								   	<span class="thumb-img">
								   		<span class="thumb-img-container">
								   			<span class="thumb-img-content">
								   				<img alt="门店图片列表" src="${storeDetail.fileAbbrUrl}" class="empPhoto"/>
								   				<label class="fileName" style="display: none;"></label>
								   			</span>
							   			</span>
						   			</span>
                                                    </a>
                                                    <input type="hidden" name="limitSize" value="10">
                                                    <input type="file" id="file${fileSize}"
                                                           class="btn-flie file-control hide" data-limit="10"
                                                           multiple="multiple"/>
                                                </div>
                                            </c:if>
                                            <c:if test="${storeDetail.storePicList != null }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${storeDetail.storePicList}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="${list.bigPictureUrl}" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="门店图片列表" src="${list.smallPictureUrl}"
                                                             class="empPhoto"/>
										   				<label class="fileName" style="display: none;"></label>
										   			</span>
									   			</span>
								   			</span>
                                                            <!-- <span class="thumb-bottom-roup">
                                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                                            </span> -->
                                                        </a>

                                                        <input type="hidden" name="limitSize" value="10">
                                                        <input type="file" id="file${fileSize}"
                                                               class="btn-flie file-control hide" data-limit="10"
                                                               multiple="multiple"/>
                                                    </div>
                                                </c:forEach>

                                            </c:if>

                                            <c:if test="${storeDetail.storePicList == null
						|| ((storeDetail.fileUrl!=null && storeDetail.storePicList.size()<9) || (storeDetail.fileUrl==null && storeDetail.storePicList.size()<10))  }">
                                                <div class="thumb-xs-list item-photo-add">
                                                    <input type="hidden" name="limitSize" value="10">
                                                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<span class="thumb-img" style="display: none;">
							<span class="thumb-img-container">
							<span class="thumb-img-content">
							<img src="" class="img empPhoto"/>
							<label class="fileName" style="display: none;"></label>
							</span>
							</span>
							</span>
                                                        <span class="thumb-bottom-roup" style="display: none;">
				   		   		<i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
				   		   	</span>
                                                        <input type="file" name="fileName0"
                                                               class="btn-flie file-control"
                                                               onchange="upload(this, 'storeEditForm');">
                                                    </a>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>


                <br>
                <br>
                <br>
            </div>
            <div class="text-center">
                <button type="button" onclick="Edit('storeEditForm');" class="btn btn-primary">保存</button>
                <a href="${ctx}/store?searchParam=1" class="btn btn-default mgl10">取消</a>
            </div>
        </div>
    </div>
</form>
</body>

<script type="text/javascript">
    $(function () {
        var banFlag = ${banFlag};
        if (banFlag) {
            $("#cityNo").attr("disabled", "disabled");
            $("#districtNo").attr("disabled", "disabled");
            $("#address").attr("disabled", "disabled");
        }
    });
</script>
</html>
