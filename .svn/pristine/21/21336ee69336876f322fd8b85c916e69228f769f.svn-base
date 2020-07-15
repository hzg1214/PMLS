<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<c:if test="${fn:length(contentlist) gt 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>

<table class="table table-striped table-hover table-bordered" style="width:4200px;font-size: 12px;table-layout: fixed;">
    <tr>
        <th rowspan="2" style="vertical-align: middle" width="2.4%">业绩归属区域</th>
        <th rowspan="2" style="vertical-align: middle" width="2.4%">业绩归属城市</th>
        <th rowspan="2" style="vertical-align: middle" width="2.4%">业绩所在城市</th>
        <th rowspan="2" style="vertical-align: middle" width="2.4%">业绩归属中心</th>
        <th rowspan="2" style="vertical-align: middle" width="2.2%">门店编号</th>
        <th rowspan="2" style="vertical-align: middle" width="3%">门店名称</th>
        <th rowspan="2" style="vertical-align: middle" width="2.2%">店招编号</th>
        <th rowspan="2" style="vertical-align: middle" width="1.8%">城市</th>
        <th rowspan="2" style="vertical-align: middle" width="1.8%">区域</th>
        <th rowspan="2" style="vertical-align: middle" width="5%">地址</th>
        <th rowspan="2" style="vertical-align: middle" width="5.5%">签到详情</th>
        <th rowspan="2" style="vertical-align: middle" width="2.1%">中心</th>
        <th rowspan="2" style="vertical-align: middle" width="1.8%">拓展人员</th>
        <th rowspan="2" style="vertical-align: middle" width="1.8%">签约类型</th>
        <th rowspan="2" style="vertical-align: middle" width="1.8%">合同状态</th>
        <th rowspan="2" style="vertical-align: middle" width="2.5%">翻牌进度</th>
        <th rowspan="2" style="vertical-align: middle" width="1.8%">营业状态</th>
        <th rowspan="2" style="vertical-align: middle" width="1.8%">维护人员</th>
        <th rowspan="2" style="vertical-align: middle" width="1.8%">跟进方式</th>
        <th rowspan="2" style="vertical-align: middle" width="3%">跟进目的</th>
        <th rowspan="2" style="vertical-align: middle" width="6%">跟进描述</th>
        <th rowspan="2" style="vertical-align: middle" width="1.8%">跟进人</th>
        <th rowspan="2" style="vertical-align: middle" width="3.5%">跟进日期</th>
        <th rowspan="2" style="vertical-align: middle" width="3%">签到距门店距离</th>
        <th rowspan="2" style="vertical-align: middle" width="3.5%">签到时间</th>
        <th rowspan="2" style="vertical-align: middle" width="3.5%">签退时间</th>
        <th rowspan="2" style="vertical-align: middle" width="2%">签到时长</th>
        <th colspan="5">服务项目</th>
        <th colspan="6">调查项目</th>
        <th colspan="4">检查项目</th>
        <th rowspan="2" style="vertical-align: middle" width="3.5%">工作总结查看</th>
    </tr>


    <tr>
        <th>联合晨会</th>
        <th>夕会</th>
        <th>门店PK</th>
        <th>门店培训</th>
        <th>区域活动</th>

        <th>招聘培训</th>
        <th>公盘</th>
        <th>系统</th>
        <th>交易</th>
        <th>联动</th>
        <th>财务返款</th>

        <th>VI</th>
        <th>工服</th>
        <th>房源</th>
        <th>合同</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr>
            <td style="vertical-align: middle" class="too-long">${list.regionName}</td>
            <td style="vertical-align: middle" class="too-long">${list.areaCityName}</td>
            <td style="vertical-align: middle" class="too-long">${list.cityGroupName}</td>
            <td style="vertical-align: middle" class="too-long">${list.centerGroupName}</td>
            <td style="vertical-align: middle" class="too-long">${list.storeNo}</td>
            <td style="vertical-align: middle" class="too-long">${list.storeName}</td>
            <td style="vertical-align: middle" class="too-long">${list.signageNo}</td>
            <td style="vertical-align: middle" class="too-long">${list.cityName}</td>
            <td style="vertical-align: middle" class="too-long">${list.areaName}</td>
            <td style="vertical-align: middle" class="too-long">${list.address}</td>
            <td style="vertical-align: middle" class="too-long">
                <c:if test="${not empty list.signLocation}">
                    <a href="javascript:FollowDetail.getSignDetail('${list.storeNo}','${list.signageNo}','${list.storeName}','${list.storeAddress}','${list.follower}','${list.followDate}','${list.followAim}','${list.followDesc}','${list.signPictureRefId}','${list.decorateStatus}')">${list.signLocation}</a>
                </c:if>
            </td>
            <td style="vertical-align: middle" class="too-long">${list.tradeCenter}</td>
            <td style="vertical-align: middle" class="too-long">${list.developer}</td>
            <td style="vertical-align: middle" class="too-long">${list.contractTypeName}</td>
            <td style="vertical-align: middle" class="too-long">${list.contractStatus}</td>
            <td style="vertical-align: middle" class="too-long">${list.decorateStatusName}</td>
            <td style="vertical-align: middle" class="too-long">${list.businessStatusName}</td>
            <td style="vertical-align: middle" class="too-long">${list.maintainerName}</td>
            <td style="vertical-align: middle" class="too-long">${list.followTypeName}</td>
            <td style="vertical-align: middle" class="too-long">${list.followAim}</td>
            <td style="vertical-align: middle" class="too-long">${list.followDesc}</td>
            <td style="vertical-align: middle" class="too-long">
                <c:if test="${not empty list.signLocation}">
                    <a href="javascript:FollowDetail.showMap('${list.followerId}','${list.followDate}')">${list.follower}</a>
                </c:if>
                <c:if test="${empty list.signLocation}">
                    ${list.follower}
                </c:if>
            </td>
            <td style="vertical-align: middle" class="too-long">${list.followDate}</td>
            <td style="vertical-align: middle" class="too-long">${list.distance}</td>
            <td style="vertical-align: middle" class="too-long">${list.signDate}</td>
            <td style="vertical-align: middle" class="too-long">${list.signOutDate}</td>
            <td style="vertical-align: middle" class="too-long">${list.signDuration}</td>

            <td style="vertical-align: middle" class="too-long">${list.morningMeeting}</td>
            <td style="vertical-align: middle" class="too-long">${list.duskMeeting}</td>
            <td style="vertical-align: middle" class="too-long">${list.storePK}</td>
            <td style="vertical-align: middle" class="too-long">${list.storeTrain}</td>
            <td style="vertical-align: middle" class="too-long">${list.areaActivity}</td>

            <td style="vertical-align: middle" class="too-long">${list.hireTrain}</td>
            <td style="vertical-align: middle" class="too-long">${list.publicPlate}</td>
            <td style="vertical-align: middle" class="too-long">${list.system}</td>
            <td style="vertical-align: middle" class="too-long">${list.trade}</td>
            <td style="vertical-align: middle" class="too-long">${list.link}</td>
            <td style="vertical-align: middle" class="too-long">${list.refund}</td>

            <td style="vertical-align: middle" class="too-long">${list.vi}</td>
            <td style="vertical-align: middle" class="too-long">${list.uniform}</td>
            <td style="vertical-align: middle" class="too-long">${list.houseResource}</td>
            <td style="vertical-align: middle" class="too-long">${list.contractName}</td>
            <td style="vertical-align: middle" class="too-long">
                <c:if test="${not empty list.workSumPictureRefId}">
                    <a href="javascript:FollowDetail.showPicture('${list.workSumPictureRefId}')">点击查看图片</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
    <div class="nodata"><i class="icon-hdd"></i>
        <p>暂无数据...</p></div>
</c:if>