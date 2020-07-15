<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=${sysConfig.baiduApiKey}" type="text/javascript"></script>
    <title>房友新房分销系统</title>
    <style>

        i {
            color: #FF0000;
        }

        .lable-left {
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
        }

        .lable-right {
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
        }


    </style>

</head>
<body>
<script type="application/javascript">
    var storeDetailInfo = ${storeDetail};
    console.log(${storeDetail});
</script>

<div>
    <div class="layui-card">
        <div class="layui-card-body">

            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">门店信息

                    </div>

                    <input type="hidden" name="storeId" id="storeId" value="${storeDetail.storeId}">
                    <input type="hidden" name="centerId" id="centerId" value="${storeDetail.centerId}">
                    <input type="hidden" name="pmlsCenterId" id="pmlsCenterId" value="${storeDetail.pmlsCenterId}">
                    <input type="hidden" name="storeNo" id="storeNo" value="${storeDetail.storeNo}">
                    <input type="hidden" name="contractStatus" id="contractStatus"
                           value="${storeDetail.contractStatus}">
                    <input type="hidden" name="gpContractStatus" id="gpContractStatus"
                           value="${storeDetail.gpContractStatus}">
                    <input type="hidden" name="decorationState" id="decorationState"
                           value="${storeDetail.decorationState}"/>

                    <input type="hidden" id="maintainerId" name="maintainerId" value="${storeDetail.maintainer}">


                    <input type="hidden" id="latitude" value="${storeDetail.latitude}"/>
                    <input type="hidden" id="longitude" value="${storeDetail.longitude}"/>
                    <input type="hidden" id="storeName" value="${storeDetail.name}"/>
                    <input type="hidden" id="addressDetail" value="${storeDetail.addressDetail}"/>
                    <input type="hidden" id="createLongitude" value="${storeDetail.createLongitude}"/>
                    <input type="hidden" id="createLatitude" value="${storeDetail.createLatitude}"/>
                    <input type="hidden" id="acCityNo" value="${storeDetail.acCityNo}"/>

                </div>
                <div class="layui-col-xs6 blockBtn">


<!--                     <shiro:hasPermission name="/store:CRM_STORE_OPR"> -->
<!--                         <button class="layui-btn layui-btn-normal" data-type="operationUpdate">运营维护</button> -->
<!--                     </shiro:hasPermission> -->
                    <shiro:hasPermission name="/store:CRM_STORE_UPD">
<!--                     	不是分销门店(29401)的才能修改信息 -->
                        <c:if test="${(storeDetail.businessStatus eq 20903 || storeDetail.businessStatus eq 20901) &&
                        		storeDetail.brandType ne 29401}">
                            <button class="layui-btn layui-btn-normal" data-type="relateStore">修改信息</button>
                        </c:if>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/store:STORE_MTC_MAN">
                    	<c:if test="${storeDetail.brandType eq 29401}">
	                        <button class="layui-btn layui-btn-normal"
	                                onclick="relateStoreUser(${storeDetail.storeId},${storeDetail.pmlsCenterId});"
	                                data-type="relateStoreUser">分配维护人
	                        </button>
	                    </c:if>
                    </shiro:hasPermission>


                    <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>

                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
                <legend>基本信息</legend>
<!--                 <a href="javascript:void(0)" onclick="javascript:storeDetail.showPicture()" -->
<!--                    style=" color: #0e78c9; margin-left:12px;font-size:2px;position: relative;top: 10px;">查看信息修改说明</a> -->
            </fieldset>


            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">门店编号：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.storeNo}</div>
                <div class="layui-col-xs2 lable-left">店招编号：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.signageNo}</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">门店名称：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.name}</div>
                <div class="layui-col-xs2 lable-left">经纪人数：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.storePersonNumName}</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">门店地址：</div>
                <div class="layui-col-xs10 lable-right">${storeDetail.addressDetail}</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">门店负责人：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.storeManager}</div>
                <div class="layui-col-xs2 lable-left">门店负责人电话：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.storeManagerPhone}</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">是否房友门店：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.isFyStoreStr}</div>
				<div class="layui-col-xs2 lable-left">装修进度：</div>
                <div class="layui-col-xs4 lable-right">
                    <c:choose>
                        <c:when test="${empty storeDetail.decorationStatusName or storeDetail.contractType eq 10306}">
                            -
                        </c:when>
                        <c:otherwise>
                            ${storeDetail.decorationStatusName}
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="layui-row">
            	<div class="layui-col-xs2 lable-left">所属公司：</div>
                <c:choose>
                    <c:when test="${not empty storeDetail.companyNo}">
                		<div class="layui-col-xs4 lable-right">${storeDetail.companyName}(${storeDetail.companyNo})</div>
                    </c:when>
                    <c:otherwise>
                		<div class="layui-col-xs4 lable-right">${storeDetail.companyName}</div>
                    </c:otherwise>
                </c:choose>
                <div class="layui-col-xs2 lable-left">连锁品牌：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.chainBrandName}</div>
            </div>
            
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">主营业务：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.mainBusinessName}</div>
                <div class="layui-col-xs2 lable-left">经营场所类型：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.businessPlaceTypeVal}</div>
            </div>
            
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">门店规模：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.storeSizeScaleName}</div>
                <div class="layui-col-xs2 lable-left">门店类型：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.storeTypeNm}</div>
            </div>
            
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">业务类型：</div>
                <div class="layui-col-xs4 lable-right">
<%--                 ${storeDetail.brandTypeStr} --%>
                
                <c:choose>
                        <c:when test="${storeDetail.brandType eq 29401}">
                            	分销渠道
                        </c:when>
                        <c:when test="${storeDetail.brandType eq 29402}">
                            	其它
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">创建人：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.userNameCreate}</div>
                <div class="layui-col-xs2 lable-left">创建时间：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.dateCreate}</div>
            </div>
            
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">CRM维护人：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.maintainerName}</div>
                <div class="layui-col-xs2 lable-left">CRM维护中心：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.centerName}</div>
            </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">联动维护人：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.pmlsMaintainName}</div>
                <div class="layui-col-xs2 lable-left">联动维护中心：</div>
                <div class="layui-col-xs4 lable-right">${storeDetail.pmlsGroupName}</div>
            </div>
        </div>
    </div>

    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-tab layui-tab-card" lay-filter="storeTab">
                    <ul class="layui-tab-title">
                        <li lay-id="storePicTab" act-type="storePic" class="layui-this">拓店图片</li>
                        <li lay-id="storeDecorationPicTab" act-type="storeDecorationPicTab">翻牌后图片</li>
                        <li id="storeMapTab" lay-id="storeMapTab" act-type="storeMapTab" >门店地图</li>
                        <li lay-id="contractTab" act-type="contract">门店联系人</li>
                        <li lay-id="followTab" act-type="follow">跟进</li>
                        <li lay-id="maintainerTab" act-type="maintainer">联动维护人</li>
                        <li lay-id="keFuOrderTab" act-type="keFuOrder">客服督导</li>
                        <li lay-id="storeLog" Tab act-type="storeLog">日志</li>
                    </ul>

                    <div class="layui-tab-content" style="padding-top: 10px">
                        <div id="storePicTabItem" class="layui-tab-item layui-show">
                            <div class="layui-row">
                                <div class="layui-col-xs12 lable-right">
                                    <div class="layui-upload-list upload_img_list">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="storeDecorationPicTabItem" class="layui-tab-item">
                            <div class="layui-row">
                                <div class="layui-col-xs12 lable-right">
                                    <div class="layui-upload-list upload_img_list">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="storeMapTabItem" class="layui-tab-item">
                            <div style="width: 100%; height: 300px; border: #ccc solid 1px; border-radius: 4px; font-size: 12px; display: inline-block" id="mapDiv"></div>
                        </div>
                        <div id="contractTabItem" class="layui-tab-item">
                            <div id="contractListTable" lay-filter="contractListTable"></div>
                        </div>
                        <div id="followTabItem" class="layui-tab-item">
                            <div id="followListTable" lay-filter="followListTable"></div>
                        </div>
                        <div id="maintainerTabItem" class="layui-tab-item">
                            <div id="maintainerListTable" lay-filter="maintainerListTable"></div>
                        </div>
                        <div id="keFuOrderTabItem" class="layui-tab-item">
                            <div id="keFuOrderListTable" lay-filter="keFuOrderListTable"></div>
                        </div>
                        <div id="storeLogItem" class="layui-tab-item">
                            <div id="logListTable" lay-filter="logListTable"></div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- 基本信息、拓店图片、翻牌后门店图片 -->
<script src="${ctx}/meta/pmls/js/store/storeDetail.js?v=${vs}"></script>
<!-- 修改日志 -->
<script src="${ctx}/meta/pmls/js/store/storeLogList.js?v=${vs}"></script>
<!-- 联系人 -->
<script src="${ctx}/meta/pmls/js/contacts/contacts.js?v=${vs}"></script>
<!-- 编辑联系人 -->
<script src="${ctx}/meta/pmls/js/contacts/storeCntctsEditPopup.js?v=${vs}"></script>
<!-- 客服工单 -->
<script src="${ctx}/meta/pmls/js/keFuOrder/storeKeFuOrder.js?v=${vs}"></script>
<!-- 门店维护人 -->
<script src="${ctx}/meta/pmls/js/store/maintainerList.js?v=${vs}"></script>
<!-- 跟进 -->
<script src="${ctx}/meta/pmls/js/store/followList.js?v=${vs}"></script>
<!-- 门店地图 -->
<script type="text/javascript" src="${ctx}/meta/pmls/js/store/pmlsStoreMap.js?_v=${vs}"></script>

</body>
</html>
