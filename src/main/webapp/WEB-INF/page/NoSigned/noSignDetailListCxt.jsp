<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<script type="text/javascript" src="${ctx}/meta/js/Signed/storeFollowListCtx.js?_v=${vs}"></script>

<div class="" style="height: auto; overflow-x:scroll;">
    <table class="table table-striped table-hover table-bordered" style="width:2833px; font-size:12px;">
        <tbody>
        <tr style="text-align: center;font-weight: bolder;">
            <td style="vertical-align: middle;" width="2.1%">序号</td>
            <th style="vertical-align: middle;" width="3.4%">归属区域</th>
            <th style="vertical-align: middle;" width="3.4%">归属城市</th>
            <th style="vertical-align: middle;" width="3.4%">所在城市</th>
            <th style="vertical-align: middle;" width="3.4%">归属中心</th>
            <th style="vertical-align: middle;" width="5%">门店编号</th>
            <th style="vertical-align: middle;" width="7%">门店名称</th>
            <th style="vertical-align: middle;" width="3.4%">区域</th>
            <th style="vertical-align: middle;" width="11%">详细地址</th>
            <th style="vertical-align: middle;" width="4%">联系人</th>
            <th style="vertical-align: middle;" width="4.5%">联系方式</th>
            <th style="vertical-align: middle;" width="4%">经纪人数</th>
            <th style="vertical-align: middle;">门店状态</th>
            <th style="vertical-align: middle;">连锁品牌</th>
            <th style="vertical-align: middle;">当前使用系统</th>
            <th style="vertical-align: middle;">当前店招品牌到期时间</th>
            <th style="vertical-align: middle;">主营商圈/板块</th>
            <th style="vertical-align: middle;">历史合同到期日</th>
            <th style="vertical-align: middle;">合同结束原因</th>
            <th style="vertical-align: middle;">创建人员</th>
            <th style="vertical-align: middle;">创建人员工号</th>
            <th style="vertical-align: middle;">维护人员</th>
            <th style="vertical-align: middle;">维护人员工号</th>
            <th style="vertical-align: middle;">门店所属中心</th>
            <th style="vertical-align: middle;">最后跟进日期</th>
        </tr>

        <c:forEach items="${reportlist}" var="list" varStatus="status">
            <tr>
                <td>${(pageInfo.curPage-1)*10+status.count}</td>
                <td>${list.regionName }</td>
                <td>${list.areaCityName }</td>
                <td>${list.cityGroupName }</td>
                <td>${list.centerGroupName }</td>
                <td>${list.storeNo }</td>
                <td>${list.storeName}</td>
                <td>${list.districtName }</td>
                <td>${list.addressDetail}</td>
                <td>${list.contacts }</td>
                <td>${list.mobilePhone }</td>
                <td>${list.storeScale }</td>
                <td>${list.storeStatus }</td>
                <td>${list.chainBrand }</td>
                <td>${list.nowUserSystem }</td>
                <td>${list.storeLeaseDueTime }</td>
                    <%-- <td style="text-align:center;" title="${list.mainBusiness}">
                             ${fn:substring(list.mainBusiness, 0, 6)}
                             <c:if test="${fn:length(list.mainBusiness) > '6'}">
                                 ...
                             </c:if>
                   </td> --%>
                <td>${list.mainBusiness}</td>
                <td>${list.hisDueDate }</td>
                <td>${list.dueCause }</td>
                <td>${list.createName }</td>
                <td>${list.createNameNo }</td>
                <td>${list.maintainerName }</td>
                <td>${list.maintainerCode }</td>
                <td>${list.centerName }</td>
                <td>
                    <a
                            href="javascript:StoreFollowListCtx.follow(${list.storeId});">${list.dateFollowUpNew }
                    </a>
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
