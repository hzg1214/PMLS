<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>编辑项目</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .estateEditPage .layui-form .hidden{
            display: none;
        }
        .estateEditPage .layui-form-item .layui-inline .layui-form-label{
            width: 300px;
        }
        .estateEditPage .layui-form-item .layui-inline .layui-form-label .requiredClass{
            color: #FF0000;
        }
        .estateEditPage .layui-form .layui-form-item .layui-input-inline{
            width: 420px;
        }
        .estateEditPage .layui-form-item .layui-inline .layui-input-inline.inlandDiv{
            width: 115px;
        }
        .estateEditPage .layui-form-item .layui-inline .layui-input-inline.inlandAddress{
            width: 170px;
        }
        .estateEditPage .layui-form-item .layui-inline .layui-input-inline.overseasDiv{
            width: 115px;
        }
        .estateEditPage .layui-form-item .layui-inline .layui-input-inline.overseasAddress{
            width: 295px;
        }
        .estateEditPage .layui-form-item .layui-inline.toolbar{
            margin-left: 330px;
        }

    </style>
</head>
<script type="application/javascript">
    var estate = ${estate};
    console.log(estate);
</script>
<body>

<div class="estateEditPage">
    <input type="hidden" name="id" id="id" value="${estate.id}">
    <input type="hidden" name="devCompanyId" id="devCompanyId" value="${estate.devCompanyId}">
    <input type="hidden" name="bigCustomerFlag" id="bigCustomerFlag" value="${estate.bigCustomerFlag}">
    <input type="hidden" name="sceneEmpId" id="sceneEmpId" value="${estate.sceneEmpId}"/>

    <div class="layui-card">
        <div class="layui-card-body">

            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">编辑项目</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" data-type="backEstateList">返回</button>
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>楼盘信息</legend>
            </fieldset>
            <div class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>楼盘名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="estateNm" name="estateNm" class="layui-input" placeholder="请输入" maxlength="30" value="${estate.estateNm}"/>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>楼盘位置</label>
                        <div class="layui-input-inline">
                            <c:if test="${estate.estatePosition eq null or estate.estatePosition eq 0}">
                                <input type="radio" name="estatePosition" value="0" title="国内" checked="" disabled="disabled" lay-filter="estatePosition">
                                <input type="radio" name="estatePosition" value="1" title="海外" disabled="disabled" lay-filter="estatePosition">
                            </c:if>
                            <c:if test="${estate.estatePosition eq 1}">
                                <input type="radio" name="estatePosition" value="0" title="国内" disabled="disabled" lay-filter="estatePosition">
                                <input type="radio" name="estatePosition" value="1" title="海外" checked="" disabled="disabled" lay-filter="estatePosition">
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>楼盘地址</label>

                        <c:if test="${estate.estatePosition eq null or estate.estatePosition eq 0}">
                            <div class="layui-input-inline inlandDiv">
                                <select id="realityCityNo" name="realityCityNo" lay-verify="required" lay-search="" lay-filter="realityCityNo">
                                    <option value="">请选择城市</option>
                                </select>
                            </div>
                            <div class="layui-input-inline inlandDiv">
                                <select id="districtNo" name="districtId" lay-verify="required" lay-search="">
                                    <option value="">请选择区域</option>
                                </select>
                            </div>
                        </c:if>
                        <c:if test="${estate.estatePosition eq 1}">
                            <div class="layui-input-inline hidden overseasDiv">
                                <select id="countryNo" name="countryNo" lay-verify="required" lay-search="">
                                    <option value="">请选择国家</option>
                                    <c:forEach var="country" items="${countryList}">
                                        <c:if test="${country.countryName ne '中国'}">
                                            <option value="${country.countryNo}" countryNm="${country.countryName}"
                                                    <c:if test="${estate.countryNo eq country.countryNo}">selected</c:if>
                                            >${country.countryName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                        <div class="layui-input-inline addressInfo inlandAddress ">
                            <input type="text" class="layui-input" id="address" name="address" placeholder="具体地址" value="${estate.address}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>销售状态</label>
                        <div class="layui-input-inline">
                            <c:forEach items="${salesStatusList}" var="list">
                                <c:if test="${list.dicCode ne 1443}">
                                    <input type="radio" value="${list.dicCode}" title="${list.dicValue}"
                                           name="salesStatus" lay-filter="salesStatus"  <c:if test="${list.dicCode eq estate.salesStatus}">checked</c:if>>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>总价段</label>
                        <div class="layui-input-inline" style="width: 100px;">
                            <input type="text" id="salePriceUnitMin" name="salePriceUnitMin" placeholder="￥" autocomplete="off" class="layui-input"
                                value="<fmt:formatNumber value="${estate.salePriceUnitMin}" pattern="#.##"></fmt:formatNumber>">
                        </div>
                        <div class="layui-form-mid">~</div>
                        <div class="layui-input-inline" style="width: 100px;">
                            <input type="text" id="salePriceUnitMax" name="salePriceUnitMax" placeholder="￥" autocomplete="off" class="layui-input"
                                   value="<fmt:formatNumber value="${estate.salePriceUnitMax}" pattern="#.##"></fmt:formatNumber>">
                        </div>
                        <div class="layui-input-inline" style="height: 38px;line-height: 38px;">
                            万元/套
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>物业类型</label>
                        <div class="layui-input-inline" style="width: auto;">
                            <c:set var="theStr" value="${estate.mgtKbn}" />
                            <c:forEach items="${mgtKbnList}" var="list">
                                <input type="checkbox" name="mgtKbnCB" value="${list.dicCode}" title="${list.dicValue}" lay-skin="primary"
                                       <c:if test="${fn:contains(theStr, list.dicCode)}">checked="checked"</c:if>>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>产权年限</label>
                        <div class="layui-input-inline" style="width: auto;">
                            <c:set var="theStr" value="${estate.ownYearKbn}" />
                            <c:forEach items="${ownYearKbnList}" var="list">
                                <input type="checkbox" name="ownYearKbnCB" value="${list.dicCode}" title="${list.dicValue}" lay-skin="primary"
                                       <c:if test="${fn:contains(theStr, list.dicCode)}">checked="checked"</c:if>>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>剩余可售货量金额（亿）</label>
                        <div class="layui-input-inline">
                            <input type="text" id="vendibilityAmount" name="vendibilityAmount" lay-verify="vendibilityAmount" maxlength="8" value="${estate.vendibilityAmount}"
                                   oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')" autocomplete="off"   lay-filter="vendibilityAmount" class="layui-input" placeholder="请输入数字，限两位小数">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>项目月大定金额（亿）</label>
                        <div class="layui-input-inline">
                            <input type="text" id="monthRoughAmount" name="monthRoughAmount" lay-verify="monthRoughAmount" maxlength="8" value="${estate.monthRoughAmount}"
                                   oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')" autocomplete="off"   lay-filter="monthRoughAmount" class="layui-input" placeholder="请输入数字，限两位小数">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>开发商</label>
                        <div class="layui-input-inline">
                            <input type="text" id="devCompanyText" name="devCompanyText" placeholder="请选择" class="layui-input" disabled value="${estate.developerName}">
                        </div>
                        <div class="layui-input-inline" style="width: 64px;">
                            <button type="button" class="layui-btn" data-type="selectDeveloper">选择</button>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>开发商对接人</label>
                        <div class="layui-input-inline">
                            <input type="text" id="devCompanyBroker" name="devCompanyBroker" placeholder="" class="layui-input" value="${estate.devCompanyBroker}" disabled>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>开发商对接人电话</label>
                        <div class="layui-input-inline">
                            <input type="text" id="devCompanyBrokerTel" name="devCompanyBrokerTel" placeholder="" class="layui-input" value="${estate.devCompanyBrokerTel}" disabled>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item" id="bigCustomerFlagDiv">
                    <div class="layui-inline">
                        <label class="layui-form-label">是否大客户<span class="requiredClass">*</span></label>
                        <div class="layui-input-inline" >
                            <div id="bigCustomerFlagStr" class="layui-form-mid">
                                <c:if test="${ '22601' eq estate.bigCustomerFlag}">是</c:if>
                                <c:if test="${ '22602' eq estate.bigCustomerFlag}">否</c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass"></span>开发商简称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="devCompany" name="devCompany" placeholder="" class="layui-input" value="${estate.devCompany}" disabled>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>项目合作意向状态</label>
                        <div class="layui-input-inline">
                            <input id="cooperationStatus1" type="radio" value="1" name="cooperationStatus" title="极有意向" lay-filter="cooperationStatus"
                                   <c:if test="${estate.cooperationStatus eq '1'}">checked</c:if>>
                            <input id="cooperationStatus2" type="radio" value="2" name="cooperationStatus" title="一般跟单" lay-filter="cooperationStatus"
                                   <c:if test="${estate.cooperationStatus eq '2'}">checked</c:if>>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass"></span>项目基本情况</label>
                        <div class="layui-input-inline">
                             <textarea id="projectInfoDesc" class="layui-textarea" autocomplete="off"
                                       style="width: 100%;" placeholder="不超过200字" maxlength="200">${estate.projectInfoDesc}</textarea>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass"></span>所需支持</label>
                        <div class="layui-input-inline">
                                <textarea id="requiredForSupport" class="layui-textarea" autocomplete="off"
                                          style="width: 100%;" placeholder="不超过200字" maxlength="200">${estate.requiredForSupport}</textarea>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>开发负责人</label>
                        <div class="layui-input-inline">
                            <input type="text" id="sceneEmpName" name="sceneEmpName" class="layui-input" disabled placeholder="请选择" value="${estate.userName}">
                        </div>
                        <div class="layui-input-inline" style="width: 64px;">
                            <button type="button" class="layui-btn" data-type="projectLeaderChoose">选择</button>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>开发负责人电话</label>
                        <div class="layui-input-inline">
                            <input type="text" id="empTel" name="empTel" class="layui-input" maxlength="11" placeholder="请输入11位电话号码" value="${estate.empTel}">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="submitEstate">保存</button>
                        <button type="button" class="layui-btn layui-btn-primary" data-type="backEstateList">取消</button>
                    </div>
                </div>

            </div>

        </div>
    </div>

    <div class="layui-card"  style="display: none">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>合作信息</legend>
            </fieldset>
            <div class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>合作方类型</label>
                        <div class="layui-input-inline" style="width: auto;">
                            <c:forEach items="${partnerList}" var="list">
                                <input type="radio" value="${list.dicCode}" title="${list.dicValue}" name="partner" lay-filter="partner"
                                       <c:if test="${list.dicCode eq estate.partner}">checked</c:if>>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            <%--    <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>合作方名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="partnerNm" name="partnerNm" placeholder="请输入" class="layui-input" value="${estate.partnerNm}">
                        </div>
                    </div>
                </div>--%>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>合作方名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="partnerNm" name="partnerNm" placeholder="请选择" class="layui-input" value="${estate.partnerNm}" disabled>
                            <input type="hidden" id="partnerAbbreviationNm" name="partnerAbbreviationNm"  class="layui-input" value="${estate.partnerAbbreviationNm}" >
                            <input type="hidden" id="partnerAbbreviationCode" name="partnerAbbreviationCode" class="layui-input" value="${estate.partnerAbbreviationCode}" >
                        </div>
                        <div class="layui-input-inline" style="width: 64px;">
                            <button type="button" class="layui-btn" data-type="selectPartnerNm" id="selectPartnerNm" style="display: none">选择</button>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass"></span>合作方品牌</label>
                        <div class="layui-input-inline">
                            <input type="text" id="developerBrandName" name="developerBrandName" placeholder="" class="layui-input" value="${estate.developerBrandName}" disabled>
                            <input type="hidden" id="developerBrandId" name="developerBrandId" value="${estate.developerBrandId}" class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>是否可垫佣甲方</label>
                        <div class="layui-input-inline">
                            <input id="custodyFlg1" type="radio" value="1" name="custodyFlg" title="是" lay-filter="custodyFlg" disabled
                                   <c:if test="${ 1 eq estate.custodyFlg}">checked</c:if>>
                            <input id="custodyFlg0" type="radio" value="0" name="custodyFlg" title="否" lay-filter="custodyFlg" disabled
                                   <c:if test="${ 0 eq estate.custodyFlg}">checked</c:if>>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item <c:if test="${ 0 eq estate.custodyFlg}">hidden</c:if> selectMattressNailInfo">
                    <div class="layui-inline">
                        <label class="layui-form-label">垫佣甲方简称<span class="requiredClass">*</span></label>
                        <div class="layui-input-inline">
                            <input type="hidden" id="mattressNailId" name="mattressNailId" value="${estate.mattressNailId}">
                            <input type="text" id="mattressNailName" name="mattressNailName" value="${estate.mattressNailName}" placeholder="" class="layui-input" disabled>

                        <%--     <select id="selectMattressNail" name="mattressNailId" lay-verify="required" lay-search="" disabled>
                                <option value="">请选择</option>
                            </select>--%>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>是否直签</label>
                        <div class="layui-input-inline">
                            <c:forEach items="${directSignList}" var="list">
                                <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="directSignFlag" title="${list.dicValue}" disabled
                                       <c:if test="${list.dicCode eq estate.directSignFlag}">checked</c:if>>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>合作周期</label>
                        <div class="layui-input-inline" style="width: 100px;">
                            <input type="text" class="layui-input" id="cooperationDtStart" readonly="" placeholder="开始日期" value="${sdk:ymd2(estate.cooperationDtStart)}">
                        </div>
                        <div class="layui-form-mid">至</div>
                        <div class="layui-input-inline" style="width: 100px;">
                            <input type="text" class="layui-input" id="cooperationDtEnd" readonly="" placeholder="结束日期" value="${sdk:ymd2(estate.cooperationDtEnd)}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>合作方对接人</label>
                        <div class="layui-input-inline">
                            <input type="text" id="partnerContactNm" name="partnerContactNm" placeholder="请输入" class="layui-input" value="${estate.partnerContactNm}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>对接人电话</label>
                        <div class="layui-input-inline">
                            <input type="text" id="partnerContactTel" name="partnerContactTel" maxlength="11" placeholder="请输入11位电话号码" class="layui-input" value="${estate.partnerContactTel}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>项目归属</label>
                        <div class="layui-input-inline" style="width: 205px;">
                            <select id="cityNo" name="cityNo" lay-verify="required" lay-search="">
                                <option value="">请选择项目归属城市</option>
                                <option value="${userInfo.cityNo}" selected>${userInfo.cityName}</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 205px;">
                            <select id="projectDepartmentId" name="projectDepartmentId" lay-verify="required" lay-search="">
                                <option value="">请选择业绩归属项目部</option>
                                <c:forEach items="${rebacklist}" var="list">
                                    <option value="${list.projectDepartmentId}"
                                            <c:if test="${list.projectDepartmentId eq estate.projectDepartmentId}">selected</c:if>>${list.projectDepartment}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>业务模式</label>
                        <div class="layui-input-inline">
                            <select id="businessModel" name="businessModel" lay-verify="required" lay-search="">
                                <option value="">请选择</option>
                                <c:forEach items="${businessModelTypeList}" var="list">
                                    <option value="${list.dicCode}"
                                            <c:if test="${list.dicCode eq estate.businessModel}">selected</c:if>
                                    >${list.dicValue}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="display: none;">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>是否借佣</label>
                        <div class="layui-input-inline">
                            <c:forEach items="${excuseCommisionList}" var="list">
                                <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="excuseCommisionFlag" title="${list.dicValue}"
                                       <c:if test="${list.dicCode eq estate.isExcuseCommision}">checked</c:if>>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>刷筹</label>
                        <div class="layui-input-inline">
                            <c:forEach items="${brushRaiseList}" var="list">
                                <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="brushRaiseFlag" title="${list.dicValue}"
                                       <c:if test="${list.dicCode eq estate.brushRaiseFlag}">checked</c:if>>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>合作方式</label>
                        <div class="layui-input-inline">
                            <c:forEach items="${totalFieldList}" var="list">
                                <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="totalFieldFlag" title="${list.dicValue}"
                                       <c:if test="${list.dicCode eq estate.totalFieldFlag}">checked</c:if>>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>预计第一季度大定金额</label>
                        <div class="layui-input-inline">
                            <input type="text" id="subscribedQuarter1" name="subscribedQuarter1" class="layui-input" placeholder="请输入"
                                   value="<fmt:formatNumber value="${estate.subscribedQuarter1}" pattern="#.##"></fmt:formatNumber>">
                        </div>
                        <div class="layui-input-inline" style="width:auto;height: 38px;line-height: 38px;">
                            万元
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>预计第二季度大定金额</label>
                        <div class="layui-input-inline">
                            <input type="text" id="subscribedQuarter2" name="subscribedQuarter2" class="layui-input" placeholder="请输入"
                                   value="<fmt:formatNumber value="${estate.subscribedQuarter2}" pattern="#.##"></fmt:formatNumber>">
                        </div>
                        <div class="layui-input-inline" style="width:auto;height: 38px;line-height: 38px;">
                            万元
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>预计第三季度大定金额</label>
                        <div class="layui-input-inline">
                            <input type="text" id="subscribedQuarter3" name="subscribedQuarter3" class="layui-input" placeholder="请输入"
                                   value="<fmt:formatNumber value="${estate.subscribedQuarter3}" pattern="#.##"></fmt:formatNumber>">
                        </div>
                        <div class="layui-input-inline" style="width:auto;height: 38px;line-height: 38px;">
                            万元
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>预计第四季度大定金额</label>
                        <div class="layui-input-inline">
                            <input type="text" id="subscribedQuarter4" name="subscribedQuarter4" class="layui-input" placeholder="请输入"
                                   value="<fmt:formatNumber value="${estate.subscribedQuarter4}" pattern="#.##"></fmt:formatNumber>">
                        </div>
                        <div class="layui-input-inline" style="width:auto;height: 38px;line-height: 38px;">
                            万元
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>预计明年大定金额</label>
                        <div class="layui-input-inline">
                            <input type="text" id="subscribedNextYear" name="subscribedNextYear" class="layui-input" placeholder="请输入"
                                   value="<fmt:formatNumber value="${estate.subscribedNextYear}" pattern="#.##"></fmt:formatNumber>">
                        </div>
                        <div class="layui-input-inline" style="width:auto;height: 38px;line-height: 38px;">
                            万元
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                    	<input type="hidden" name="projectLeaderEmpId" id="projectLeaderEmpId" value="${estate.projectLeaderEmpId}"/>
                        <label class="layui-form-label"><span class="requiredClass">*</span>项目负责人</label>
                        <div class="layui-input-inline">
                            <input type="text" id="projectLeaderName" name="projectLeaderName" class="layui-input" disabled placeholder="请选择" value="${estate.projectLeaderName}">
                        </div>
                        <div class="layui-input-inline" style="width: 64px;">
                            <button type="button" class="layui-btn" data-type="projectLeaderSel">选择</button>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>项目负责人电话</label>
                        <div class="layui-input-inline">
                            <input type="text" id="projectLeaderTel" name="projectLeaderTel" class="layui-input" maxlength="11" placeholder="请输入11位电话号码" value="${estate.projectLeaderTel}">
                        </div>
                    </div>
                </div>
                <%--<div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>开发负责人</label>
                        <div class="layui-input-inline">
                            <input type="text" id="sceneEmpName" name="sceneEmpName" class="layui-input" disabled placeholder="请选择" value="${estate.userName}">
                        </div>
                        <div class="layui-input-inline" style="width: 64px;">
                            <button type="button" class="layui-btn" data-type="projectLeaderChoose">选择</button>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>开发负责人电话</label>
                        <div class="layui-input-inline">
                            <input type="text" id="empTel" name="empTel" class="layui-input" maxlength="11" placeholder="请输入11位电话号码" value="${estate.empTel}">
                        </div>
                    </div>
                </div>--%>
<%--                <div class="layui-form-item">
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="submitEstate">提交</button>
                        <button type="button" class="layui-btn layui-btn-primary" data-type="backEstateList">取消</button>
                    </div>
                </div>--%>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/estate/estateEditInfo.js?_v=${vs}"></script>
</body>
</html>
