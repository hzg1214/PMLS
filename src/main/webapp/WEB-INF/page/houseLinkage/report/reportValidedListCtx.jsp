<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
    <c:if test="${fn:length(contentlist) gt 0}">
    <div id="divReport" class="s-scoll-y" style="margin-bottom:20px;"<%-- style="height: 500px;"--%>></c:if>
    <c:if test="${fn:length(contentlist) le 0}">
        <div id="divReport" class="s-scoll-y" style="height: auto;"></c:if>
            <table id="tblReport" class="table table-striped table-hover table-bordered" style="width:2200px;">
                <tbody class="s-w100">
                <col style="width:145px">
                <col style="width:130px">
                <col style="width:80px">
                <col style="width:180px">
                <col style="width:200px">
                <col style="width:155px">
                <col style="width:130px">
                <col style="width:135px">
                <col style="width:130px">
                <col style="width:120px">
                <col style="width:110px">
                <col style="width:110px">
                <col style="width:120px">
                <col style="width:130px">
                <col style="width:110px">
                <col style="width:180px">
                <col style="width:110px">
                <col style="width:110px">
                <col style="width:1px">
                <tr>
                    <th>报备编号</th>
                    <th>楼盘名称</th>
                    <th>楼盘城市</th>
                    <th>楼盘地址</th>
                    <th>经纪公司</th>
                    <th>门店地址</th>
                    <th>客户</th>
                    <th>客户手机号</th>
                    <th>业绩归属人</th>
                    <th>业绩归属人中心</th>
                    <th>楼室号</th>
                    <th>报备日期</th>
                    <th>带看日期</th>
                    <th>大定日期</th>
                    <th>大定审核状态</th>
                    <th>审核时间</th>
                    <th>审核人</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${contentlist}" var="list">
                <tr class="J_eatateItem" data-hidenumberflg="0">
                    <td>
                        <a href="${ctx}/report/${list.estateId}/${list.companyId}/${list.customerId}/${list.id}/4">${list.reportId}</a>
                    </td>

                    <td style="text-align:left;" title="${list.estateNm}" class="text-overflow">
                    	${list.estateNm}
                    </td>
                    <td class="text-overflow">${list.realityCityNm}</td>
                    <td style="text-align:left;" title="${list.address}" class="text-overflow">
                    	${list.address}
                    </td>
                    <td style="text-align:left;" title="${list.companyNm}" class="text-overflow">
                    	${list.companyNm}
                    </td>
                    <td style="text-align:left;" title="${list.storeAddress}" class="text-overflow">
                    	${list.storeAddress}
                    </td>
                    <td style="text-align:left;" title="${list.customerNm}" class="text-overflow">
                    	${list.customerNm}
                    </td>
                    <td>${list.customerTel}</td>
                    <td>${list.contactNm}</td>
                    <td  title="${list.centerName}" class="text-overflow">
                    	${list.centerName}
                    </td>
                    <td style="text-align:center;" title="${list.buildingNo}" class="text-overflow">
                            ${list.buildingNo}
                    </td>
                    <td>${sdk:ymd2(list.reportDate)}</td>
                    <td>${sdk:ymd2(list.seeDate)}</td>
                    <td>${sdk:ymd2(list.roughInputDate)}</td>
                    <td>
                        <c:if test="${list.roughAuditStatus eq '0'}">待审核</c:if>
                        <c:if test="${list.roughAuditStatus eq '1'}">审核通过</c:if>
                        <c:if test="${list.roughAuditStatus eq '2'}">审核驳回</c:if>
                    </td>
                    <td>${list.roughAuditTime}</td>
                    <td>${list.roughAuditName}</td>
                    <td>
                        <a href="${ctx}/reportToValid/toView/${list.id}?fromType=2">查看</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>

        ${pageInfo.html}

        <c:if test="${fn:length(contentlist) le 0}">
        <div class="nodata">
            <i class="icon-hdd"></i>
            <p>暂无数据...</p>
        </div>
        </c:if>
