<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>项目信息变更</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .editPopupLay .layui-form{
            padding-top: 5px;
            height: 624px;
            background-color: #FFF;
            overflow: hidden;
        }
        .editPopupLay .layui-form .hidden{
            display: none;
        }
        .editPopupLay .layui-form .layui-form-item.text{
            /*margin-bottom: 10px;*/
        }
        .editPopupLay .layui-form .layui-input-inline.text-center{
            height: 38px;
            line-height: 38px;
        }
        .editPopupLay .layui-form-item .layui-inline .layui-form-label{
            width: 110px;
        }
        .editPopupLay .layui-form-item .layui-inline .layui-form-label .requiredClass{
            color: #FF0000;
        }

    </style>
</head>
<script type="application/javascript">

</script>
<body>

<div class="editPopupLay">
    <input type="hidden" id="id" name="id" value="${id}"/>

    <input type="hidden" id="oldEstatePosition" name="oldEstatePosition" value="${estate.estatePosition}">
    <input type="hidden" id="oldCountryNo" name="oldCountryNo" value="${estate.countryNo}">
    <input type="hidden" id="oldName" name="oldName" value="${estate.estateNm}">
    <input type="hidden" id="oldCityNo" name="oldCityNo" value="${estate.realityCityNo}">
    <input type="hidden" id="oldDistrictNo" name="oldDistrictNo" value="${estate.districtId}">
    <input type="hidden" id="oldAddress" name="oldAddress" value="${estate.realityCityNm}${estate.districtNm}${estate.areaNm}${estate.address}">

    <input type="hidden" id="oldSalesStatus" name="oldSalesStatus" value="${estate.salesStatus}">
    <input type="hidden" id="oldSalesStatusStr" name="oldSalesStatusStr" value="${estate.salesStatusStr}">

    <input type="hidden" id="oldBusinessMode" name="oldBusinessMode" value="${estate.businessModel}">
    <input type="hidden" id="oldBusinessModeStr" name="oldBusinessModeStr" value="<c:if test="${25501 eq estate.businessModel}">标准</c:if>
								<c:if test="${25502 eq estate.businessModel}">非标准（保证金）</c:if>
								<c:if test="${25503 eq estate.businessModel}">非标准（诚意金）</c:if>
								<c:if test="${25504 eq estate.businessModel}">非标准（包销）</c:if>
								<c:if test="${25505 eq estate.businessModel}">非标准（前佣）</c:if>
								<c:if test="${25506 eq estate.businessModel}">非标准（全民经纪）</c:if>
								<c:if test="${25507 eq estate.businessModel}">非标准（其他）</c:if>
								<c:if test="${25508 eq estate.businessModel}">纯垫佣</c:if>">

    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form">
                <div class="layui-form-item text">
                    <div class="layui-inline">
                        <label class="layui-form-label">修改项目类型<span class="requiredClass">*</span></label>
                        <div class="layui-input-inline" style="width: auto;">
                            <input type="checkbox" name="estateAddress" value="1" lay-skin="primary" title="项目地址" lay-filter="estateType">
                            <input type="checkbox" name="salesStatus" value="1" lay-skin="primary" title="销售状态" lay-filter="estateType">
                            <input type="checkbox" name="businessMode" value="1" lay-skin="primary" title="业务模式" lay-filter="estateType">
                        </div>
                    </div>
                </div>
                <div class="hidden estateAddress">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">原项目地址</label>
                        <div class="layui-input-inline text-center" style="width: auto;">
                            ${estate.realityCityNm}${estate.districtNm}${estate.areaNm}${estate.address}
                                <c:if test="${estate.estatePosition eq null or estate.estatePosition eq 0}">
                                    （国内）
                                </c:if>
                                <c:if test="${estate.estatePosition eq 1}">
                                    （海外）
                                </c:if>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item text">
                    <div class="layui-inline" style="width: 100%;">
                        <label class="layui-form-label">修改后项目地址<span class="requiredClass">*</span></label>
                        <c:if test="${estate.estatePosition eq null or estate.estatePosition eq 0}">
                        <div class="layui-input-inline" style="width: 130px;">
                            <select id="realityCityNo" name="realityCityNo" lay-verify="required" lay-search="" lay-filter="realityCityNo">
                                <option value="">请选择城市</option>
                                <c:forEach items="${citylist}" var="city">
                                    <option value="${city.cityNo}" <c:if test="${city.cityNo eq estate.realityCityNo}">selected</c:if>>${city.cityName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="layui-input-inline inlandDiv" style="width: 130px;">
                            <select id="districtNo" name="districtNo" lay-verify="required" lay-search="">
                                <option value="">请选择区域</option>
                                <c:if test="${!empty districtList}">
                                    <c:forEach items="${districtList}" var="district">
                                        <option value="${district.districtNo}"<c:if test="${district.districtNo eq estate.districtId}">selected</c:if>>${district.districtName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        </c:if>
                        <c:if test="${estate.estatePosition eq 1}">
                        <div class="layui-input-inline">
                            <select id="newCountryNo" name="newCountryNo" lay-verify="required" lay-search="">
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
                        <div class="layui-input-inline" style='
                            <c:if test="${estate.estatePosition eq null or estate.estatePosition eq 0}">width: 300px;</c:if>
                            <c:if test="${estate.estatePosition eq 1}">width: 380px;</c:if>'>
                            <input type="text" class="layui-input" id="newAddress" name="newAddress" placeholder="具体地址信息" >
                        </div>
                    </div>
                </div>
                </div>

                <div class="hidden salesStatus">
                <div class="layui-form-item text">
                    <div class="layui-inline">
                        <label class="layui-form-label">原销售状态</label>
                        <div class="layui-input-inline text-center">
                            ${estate.salesStatusStr}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">修改后销售状态<span class="requiredClass">*</span></label>
                        <div class="layui-input-inline" style="width: auto;">
                            <input type="radio" value="1442" title="待售" name="newSalesStatus">
                            <input type="radio" value="1441" title="在售" name="newSalesStatus">
                            <input type="radio" value="1443" title="售完" name="newSalesStatus">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item text">
                    <div class="layui-inline">
                        <label class="layui-form-label">实际开盘日期<span class="requiredClass">*</span></label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="openTime" readonly="" placeholder="yyyy-MM-dd">
                        </div>
                    </div>
                </div>
                </div>

                <div class="hidden businessMode">
                <div class="layui-form-item text">
                    <div class="layui-inline">
                        <label class="layui-form-label">原业务模式</label>
                        <div class="layui-input-inline text-center">
                            <c:if test="${25501 eq estate.businessModel}">标准</c:if>
                            <c:if test="${25502 eq estate.businessModel}">非标准（保证金）</c:if>
                            <c:if test="${25503 eq estate.businessModel}">非标准（诚意金）</c:if>
                            <c:if test="${25504 eq estate.businessModel}">非标准（包销）</c:if>
                            <c:if test="${25505 eq estate.businessModel}">非标准（前佣）</c:if>
                            <c:if test="${25506 eq estate.businessModel}">非标准（全民经纪）</c:if>
                            <c:if test="${25507 eq estate.businessModel}">非标准（其他）</c:if>
                            <c:if test="${25508 eq estate.businessModel}">纯垫佣</c:if>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">修改后业务模式<span class="requiredClass">*</span></label>
                        <div class="layui-input-inline">
                            <select id="newBusinessMode" name="newBusinessMode" lay-verify="required" lay-search="">
                                <option value="">请选择业务模式</option>
                                <option value="25501">标准</option>
                                <option value="25502">非标准（保证金）</option>
                                <option value="25503">非标准（诚意金）</option>
                                <option value="25504">非标准（包销）</option>
                                <option value="25505">非标准（前佣）</option>
                                <option value="25506">非标准（全民经纪）</option>
                                <option value="25507">非标准（其他）</option>
                                <option value="25508">纯垫佣</option>
                            </select>
                        </div>
                    </div>
                </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline" style="width: 100%;">
                        <label class="layui-form-label">修改原因<span class="requiredClass">*</span></label>
                        <div class="layui-input-inline">
                            <textarea id="modifyReason" name="modifyReason" placeholder="请输入修改原因" class="layui-textarea" style="width: 580px;height: 140px;"></textarea>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/estate/estateEditPopup.js?_v=${vs}"></script>

</body>
</html>
