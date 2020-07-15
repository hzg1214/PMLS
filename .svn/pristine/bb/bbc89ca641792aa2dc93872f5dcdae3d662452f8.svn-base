<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp"%>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
    <script type="text/javascript" src="${ctx}/meta/js/store/storeDecorationRacord.js?_v=${vs}"></script>
</head>
<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
</jsp:include>
<div class="container">
	<div class="crumbs">
		<ul>
			<li><a href="#"  class="a_hover">门店管理</a></li>
			<li><a href="#"  class="a_hover">>门店详情</a></li>
			<li><a href="#"  class="a_hover">>收款</a></li>
		</ul>
	</div>
    <div class="row article">
        <!-- 左侧菜单 -->
        <jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp"
                     flush="true">
            <jsp:param value="110407" name="leftMenuSelectId" />
        </jsp:include>
        <div class="col-md-10 content">
            <div class="page-content">
                <h4>
                    <strong>收款</strong>
                    <a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a>
                </h4>
                </br>
                <div class="" style="height: auto;">
                    <table class="table table-striped table-hover table-bordered">
                        <tr>
                            <%--<th>门店编号</th>
                            <th>门店名称</th>
                            <th>门店地址</th>
                            <th>签约类型</th>--%>
                            <th>收款类型</th>
                            <th>收费周期</th>
                            <th>收费标准</th>

                            <%--<th>保证金金额</th>--%>
                            <th>收款方式</th>
                            <th>支付金额</th>
                            <%--<th>支付税</th>--%>
                            <th>收款人</th>
                            <th>收款时间</th>
                            <th>状态</th>
                            <th>备注</th>
                        </tr>
                        <c:forEach items="${contentlist}" var="list">
                            <tr class="J_eatateItem" data-hidenumberflg="0">
                                <%--<td>${list.storeNo}</td>
                                <td>${list.name}</td>
                                <td>${list.addressDetail}</td>
                                <td>${list.contractTypeName}</td>--%>
                                <td>
                                    ${list.receivablesTypeName}
                                </td>
                                <td><c:if test="${list.receivablesTypeName == '平台费'}">
                                    (${fn:substring(list.chargingCycleStart, 0, 10)}~${fn:substring(list.chargingCycleEnd, 0, 10)})
                                    </c:if>
                                </td>
                                <td>${list.chargeStandard}</td>
                                <%--<td>${list.bondMoney}</td>--%>
                                <td>${list.receivablesStyleName}</td>
                                <td>${list.payMoney}</td>
                                <%--<td>${list.payTotalFee}</td>--%>
                                <td>${list.userNameCreate}</td>
                                <td>${list.dateCreate}</td>
                                <td>${list.payStatus}</td>
                                <td>${list.remarks}</td>
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
            </div>
        </div>
    </div>
</div>
</body>
</html>
