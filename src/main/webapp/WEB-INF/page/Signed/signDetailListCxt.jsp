<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<script type="text/javascript" src="${ctx}/meta/js/Signed/storeFollowListCtx.js?_v=${vs}"></script>
<style>

    tr th {
        height: 25px;
        line-height: 0px !important;

    }
</style>

<div class="" style="height: auto; overflow-x:scroll;">
    <table class="table table-striped table-hover table-bordered" style="width:4933px; font-size:12px;">
        <tr style="text-align: center;font-weight: bolder;">
            <th rowspan="3" style="vertical-align: middle;" width="1.2%">序号</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.0%">归属区域</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.0%">归属城市</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.0%">所在城市</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.0%">归属中心</th>
            <th rowspan="3" style="vertical-align: middle;" width="2%">门店编号</th>
            <th rowspan="3" style="vertical-align: middle;" width="3%">门店名称</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.0%">区域</th>
            <th rowspan="3" style="vertical-align: middle;" width="6%">详细地址</th>
            <th rowspan="3" style="vertical-align: middle;" width="1.5%">联系人</th>
            <th rowspan="3" style="vertical-align: middle;" width="2%">联系方式</th>
            <th rowspan="3" style="vertical-align: middle;" width="1.5%">经纪人数</th>
            <th rowspan="3" style="vertical-align: middle;" width="1.5%">门店状态</th>
            <th rowspan="3" style="vertical-align: middle;" width="5.5%">公司名称</th>
            <th rowspan="3" style="vertical-align: middle;" width="8%">公司注册地址</th>
            <th rowspan="3" style="vertical-align: middle;" width="3.5%">合同编号</th>
            <th rowspan="3" style="vertical-align: middle;" width="1.8%">合作模式</th>
            <th rowspan="3" style="vertical-align: middle;" width="2%">草签日期</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.5%">OA审核通过日期</th>
            <th rowspan="3" style="vertical-align: middle;">保证金</th>
            <th rowspan="3" style="vertical-align: middle;" width="2%">翻牌日期</th>
            <th rowspan="3" style="vertical-align: middle;">营业状态</th>
            <th colspan="4">平台服务费</th>
            <th colspan="4">交易</th>
            <th colspan="6">联动</th>
            <!--  <th colspan="5">联动收佣返佣</th> -->
            <th rowspan="3" style="vertical-align: middle;">系统使用</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.5%">房友安装机器数</th>
            <th rowspan="3" style="vertical-align: middle;">房友活跃度</th>
            <th rowspan="3" style="vertical-align: middle;">IP地址</th>
            <th rowspan="3" style="vertical-align: middle;">创建人员</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.1%">创建人员工号</th>
            <th rowspan="3" style="vertical-align: middle;">签约人员</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.1%">签约人员工号</th>
            <th rowspan="3" style="vertical-align: middle;">维护人员</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.1%">维护人员工号</th>
            <th rowspan="3" style="vertical-align: middle;" width="2.1%">最后跟进日期</th>
        </tr>

        <tr>
            <th rowspan="2" style="vertical-align: middle;">月缴费标准</th>
            <th rowspan="2" style="vertical-align: middle;">已缴金额</th>
            <th rowspan="2" style="vertical-align: middle;">到期日期</th>
            <th rowspan="2" style="vertical-align: middle;">状态</th>
            <th rowspan="2" style="vertical-align: middle;">报单</th>
            <th rowspan="2" style="vertical-align: middle;">网签</th>
            <!-- <th rowspan="2" style="vertical-align: middle;">贷款</th>
            <th rowspan="2" style="vertical-align: middle;">评估</th>
            <th rowspan="2" style="vertical-align: middle;">办证</th>-->
            <th rowspan="2" style="vertical-align: middle;">不动产受理</th>
            <th rowspan="2" style="vertical-align: middle;">办结</th>
            <!--  <th rowspan="2" style="vertical-align: middle;">净收入</th> -->
            <th rowspan="2" style="vertical-align: middle;">报备</th>
            <th rowspan="2" style="vertical-align: middle;">带看</th>
            <th colspan="2">大定</th>
            <th colspan="2">成销</th>
            <!-- <th colspan="3">开发商-收佣</th>
            <th colspan="2">门店-返佣</th> -->
        </tr>

        <tr>
            <th style="vertical-align: middle;">套数</th>
            <th style="vertical-align: middle;">金额</th>
            <th style="vertical-align: middle;">套数</th>
            <th style="vertical-align: middle;">金额</th>
            <!-- <th style="vertical-align: middle;">应计</th>
            <th style="vertical-align: middle;">应收</th>
            <th style="vertical-align: middle;">实收</th>
            <th style="vertical-align: middle;">应计</th>
            <th style="vertical-align: middle;">实付</th>  -->
        </tr>
        <c:forEach items="${reportlist}" var="list" varStatus="status">
            <tr>
                <td>${(pageInfo.curPage-1)*10+status.count}</td>
                <td>${list.regionName }</td>
                <td>${list.areaCityName }</td>
                <td>${list.cityGroupName }</td>
                <td>${list.centerGroupName }</td>
                <td>${list.storeNo }</td>
                    <%-- <td style="text-align:center;" title="${list.storeName}">
                        ${fn:substring(list.storeName, 0, 5)}
                        <c:if test="${fn:length(list.storeName) > '5'}">
                        ...
                        </c:if>
                    </td> --%>
                <td>${list.storeName}</td>
                <td>${list.districtName }</td>
                    <%-- <td style="text-align:center;" title="${list.addressDetail}">
                        ${fn:substring(list.addressDetail, 0, 5)}
                        <c:if test="${fn:length(list.addressDetail) > '5'}">
                        ...
                        </c:if>
                    </td> --%>
                <td>${list.addressDetail}</td>
                <td>${list.contacts }</td>
                <td>${list.mobilePhone }</td>
                <td>${list.storeScale }</td>
                <td>${list.storeStatus }</td>
                    <%-- <td style="text-align:center;" title="${list.companyName}">
                        ${fn:substring(list.companyName, 0, 5)}
                        <c:if test="${fn:length(list.companyName) > '5'}">
                        ...
                        </c:if>
                    </td> --%>
                <td>${list.companyName}</td>
                    <%-- <td style="text-align:center;" title="${list.businessCompanyAddress}">
                        ${fn:substring(list.businessCompanyAddress, 0, 5)}
                        <c:if test="${fn:length(list.businessCompanyAddress) > '5'}">
                        ...
                        </c:if>
                    </td> --%>
                <td>${list.businessCompanyAddress}</td>
                    <%-- <td style="text-align:center;" title="${list.contractNo}">
                       ${fn:substring(list.contractNo, 0, 5)}
                       <c:if test="${fn:length(list.contractNo) > '5'}">
                       ...
                       </c:if>
                   </td> --%>
                <td>${list.contractNo}</td>
                <td>${list.cooperateMode }</td>
                <td>${list.contractDateCreate }</td>
                <td>${list.performDate }</td>
                <td>${list.depositFee }</td>
                <td>${list.dateFlopCkAccept }</td>
                <td>${list.businessStatus }</td>
                <td>${list.receivableMoney }</td>
                <td>${list.accountMoney }</td>
                <td>${list.overDate }</td>
                <td>${list.platformStatus }</td>
                <td>${list.exchangeReportNum }</td>
                <td>${list.exchangeSignedNum }</td>
                <td>${list.exchangeEstateNum }</td>
                    <%--  <td>${list.exchangeLoanedNum }</td>
                     <td>${list.exchangeEvaluateNum }</td>
                     <td>${list.exchangePermitNum }</td> --%>
                <td>${list.exchangeHandleEndNum }</td>
                    <%--  <td>${list.exchangeProfit }</td> --%>
                <td>${list.linkReportNum }</td>
                <td>${list.linkSeeNum }</td>
                <td>${list.linkRoughNum }</td>
                <td>${list.linkRoughAmount }</td>
                <td>${list.linkDealNum }</td>
                <td>${list.linkDealAmount }</td>
                    <%--  <td>${list.linkDeveloperCommissionAccrued }</td>
                     <td>${list.linkDeveloperCommissionReceivable }</td>
                     <td>${list.linkDeveloperCommissionReal }</td>
                     <td>${list.linkStoreCommissionAccrued }</td>
                     <td>${list.linkStoreCommissionReal }</td> --%>
                <td>${list.fangyouSystem }</td>
                <td>${list.fangyouMachineNum }</td>
                <td>${list.fangyouLive }</td>
                <td>${list.fangyouIPAddress }</td>
                <td>${list.createName }</td>
                <td>${list.createNameNo }</td>
                <td>${list.signedName }</td>
                <td>${list.signedNameNo }</td>
                <td>${list.maintainerName }</td>
                <td>${list.maintainerCode }</td>
                <td>
                    <a href="javascript:StoreFollowListCtx.follow(${list.storeId});">${list.dateFollowUpNew }</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

${pageInfo.html}

<c:if test="${fn:length(reportlist) le 0}">
    <div class="nodata">
        <i class="icon-hdd"></i>
        <p>暂无数据...</p>
    </div>
</c:if>
