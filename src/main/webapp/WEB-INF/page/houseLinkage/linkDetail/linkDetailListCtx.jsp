<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<c:if test="${fn:length(contentlist) gt 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<table class="table table-striped table-hover table-bordered" style="max-width: none;width:29600px;font-size: 12px;">
    <tbody class="s-w100">
    <tr>
        <th rowspan="3" style="vertical-align: middle;width: 45px;">序号</th>
        <th colspan="13" style="vertical-align: middle;width: 1340px;">业绩信息</th>
        <th colspan="35" style="vertical-align: middle;width: 4600px;">案件信息</th>
        
        <th colspan="8" style="vertical-align: middle;width: 600px;">总累计应计</th>
        <th colspan="8">当年以前应计</th>
        <th colspan="8">${year}应计</th>
        <th colspan="8">1月应计</th>
        <th colspan="8">2月应计</th>
        <th colspan="8">3月应计</th>
        <th colspan="8">4月应计</th>
        <th colspan="8">5月应计</th>
        <th colspan="8">6月应计</th>
        <th colspan="8">7月应计</th>
        <th colspan="8">8月应计</th>
        <th colspan="8">9月应计</th>
        <th colspan="8">10月应计</th>
        <th colspan="8">11月应计</th>
        <th colspan="8">12月应计</th>
        
        <!-- 应收收入 -->
        <th rowspan="2" colspan="2" style="vertical-align: middle;width: 150px;">总累计应收收入</th>
        <th rowspan="2" colspan="2" style="vertical-align: middle;width: 150px;">当年以前应收收入</th>
        <th rowspan="2" colspan="2"  style="vertical-align: middle;width: 150px;">${year}年应收收入合计</th>
        <th colspan="24"  style="vertical-align: middle;width: 1800px;">${year}年应收收入</th>
        
        <th colspan="10">总累计实际</th>
        <th colspan="8">当年以前实际</th>
        <th colspan="8">${year}实际</th>
        <th colspan="8">1月实际</th>
        <th colspan="8">2月实际</th>
        <th colspan="8">3月实际</th>
        <th colspan="8">4月实际</th>
        <th colspan="8">5月实际</th>
        <th colspan="8">6月实际</th>
        <th colspan="8">7月实际</th>
        <th colspan="8">8月实际</th>
        <th colspan="8">9月实际</th>
        <th colspan="8">10月实际</th>
        <th colspan="8">11月实际</th>
        <th colspan="8">12月实际</th>
        
        <th rowspan="3" style="vertical-align: middle">岗位佣金小计</th>
        <th rowspan="3" style="vertical-align: middle">团佣小计</th>
        <th rowspan="3" style="vertical-align: middle">备注</th>
        
        <th colspan="6">人员信息</th>

        <th rowspan="3" style="vertical-align: middle;width:250px">返佣对象一</th>
        <th rowspan="3" style="vertical-align: middle">应计返佣税前</th>
        <th rowspan="3" style="vertical-align: middle">应计返佣税后</th>
        <th rowspan="3" style="vertical-align: middle;width:250px">返佣对象二</th>
        <th rowspan="3" style="vertical-align: middle">应计返佣税前</th>
        <th rowspan="3" style="vertical-align: middle">应计返佣税后</th>
        <th rowspan="3" style="vertical-align: middle;width:250px">返佣对象三</th>
        <th rowspan="3" style="vertical-align: middle">应计返佣税前</th>
        <th rowspan="3" style="vertical-align: middle">应计返佣税后</th>
    </tr>
    <tr>
        <th rowspan="2" style="vertical-align: middle;width: 90px;">核算主体编号</th>
        <th rowspan="2" style="vertical-align: middle;width: 90px;">核算主体</th>
        <th rowspan="2" style="vertical-align: middle;width: 90px;">业绩归属区域</th>
        <th rowspan="2" style="vertical-align: middle;width: 90px;">业绩归属城市</th>
        <th rowspan="2" style="vertical-align: middle;width: 90px;">业绩所在城市</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">业绩归属事业部</th>
        <th rowspan="2" style="vertical-align: middle;width: 130px;">业绩归属中心</th>
        <th rowspan="2" style="vertical-align: middle;width: 70px;">业绩归属组</th>
        <th rowspan="2" style="vertical-align: middle;width: 70px;">业绩归属人</th>
        <th rowspan="2" style="vertical-align: middle;width: 70px;">业绩归属人工号</th>
        <th rowspan="2" style="vertical-align: middle;width: 90px;">项目归属城市</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">项目归属部门</th>
        <th rowspan="2" style="vertical-align: middle;width: 90px;">收入归属城市</th><%--added by haidan 2019/04/28--%>

        <th rowspan="2" style="vertical-align: middle;width: 50px;">来源</th>
        <th colspan="8" style="vertical-align: middle;width: 1080px;">经纪公司</th>
        <th colspan="3" style="vertical-align: middle;width: 200px;">客户信息</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">系统编号</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">项目编号</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">楼盘名</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">合作方名称</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">是否大客户</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">大客户简称</th>
        
        
        <th rowspan="2" style="vertical-align: middle;width: 140px;">是否垫佣</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">报备编号</th>
        <th rowspan="2" style="vertical-align: middle;width: 100px;">报备时间</th>
        <th rowspan="2" style="vertical-align: middle;">带看时间</th>
        <th rowspan="2" style="vertical-align: middle;">认筹时间</th>
        <th rowspan="2" style="vertical-align: middle;">楼室号</th>
        <th rowspan="2" style="vertical-align: middle;">套数</th>
        <th colspan="5" style="vertical-align: middle;">大定</th>
        <th colspan="5" style="vertical-align: middle;">成销</th>
        
        <% for (int i = 1; i<=15; i++) { %>
        <th colspan="2">成销收入</th>
        <th colspan="2">返佣金额</th>
        <th colspan="2">成销净收</th>
        <th colspan="2">达垫佣节点的垫佣</th>
        <% } %>
		
		<th colspan="2">1月应收</th>
        <th colspan="2">2月应收</th>
        <th colspan="2">3月应收</th>
        <th colspan="2">4月应收</th>
        <th colspan="2">5月应收</th>
        <th colspan="2">6月应收</th>
        <th colspan="2">7月应收</th>
        <th colspan="2">8月应收</th>
        <th colspan="2">9月应收</th>
        <th colspan="2">10月应收</th>
        <th colspan="2">11月应收</th>
        <th colspan="2">12月应收</th>
		
		
        <th colspan="2">实际收入</th>
        <th colspan="2">实际返佣</th>
        <th colspan="2">实际净收</th>
        <th colspan="2">达垫佣节点的实付垫佣</th>
        <th colspan="1">垫佣回款</th>
        <th colspan="1">当前仍处于垫付状态的垫佣</th>

        <% for (int i = 1; i<=14; i++) { %>
        <th colspan="2">实际收入</th>
        <th colspan="2">实际返佣</th>
        <th colspan="2">实际净收</th>
        <th colspan="2">达垫佣节点的实付垫佣</th>
        <% } %>
        
        <th colspan="2">拓展维护人员</th>
        <th colspan="2">拓展维护经理</th>
        <th colspan="2">拓展维护总监</th>
    </tr>
    <tr>
        <th style="vertical-align: middle;width: 90px;">门店编号</th>
        <th style="vertical-align: middle;width: 90px;">门店名称</th>
        <th style="vertical-align: middle;width: 90px;">公司名称</th>
        <th style="vertical-align: middle;width: 90px;">门店地址</th>
        <th style="vertical-align: middle;width: 80px;">门店规模</th>
        <th style="vertical-align: middle;width: 90px;">合作模式</th>
        <th style="vertical-align: middle;width: 90px;">业务员姓名</th>
        <th style="vertical-align: middle;width: 90px;">业务员电话</th>
        <th style="vertical-align: middle">客户姓名</th>
        <th style="vertical-align: middle">联系电话</th>
        <th style="vertical-align: middle">人数</th>
        <th style="vertical-align: middle">大定面积</th>
        <th style="vertical-align: middle">大定单价</th>
        <th style="vertical-align: middle">大定总价</th>
        <th style="vertical-align: middle">大定日期</th>
        <th style="vertical-align: middle">大定审核通过日期</th>
        <th style="vertical-align: middle">成销面积</th>
        <th style="vertical-align: middle">成销单价</th>
        <th style="vertical-align: middle">成销总价</th>
        <th style="vertical-align: middle">成销日期</th>
        <th style="vertical-align: middle">收入类型</th>
       
        <% for (int i = 1; i<=60; i++) { %>
			<th style="vertical-align: middle;">税前</th>
			<th style="vertical-align: middle;">税后</th>
        <% } %>
		<!-- 应收收入 -->
		<% for (int i = 1; i <= 15; i++) { %>
        	<th style="vertical-align: middle;">税前</th>
        	<th style="vertical-align: middle;">税后</th>
        <% } %>
        <th style="vertical-align: middle;">税前</th>
        <th style="vertical-align: middle;">税后</th>
        <th style="vertical-align: middle;">税前</th>
        <th style="vertical-align: middle;">税后</th>
        <th style="vertical-align: middle;">税前</th>
        <th style="vertical-align: middle;">税后</th>
        <th style="vertical-align: middle;">税前</th>
        <th style="vertical-align: middle;">税后</th>
        <th style="vertical-align: middle;">税前</th>
        <th style="vertical-align: middle; ">税前</th>

        <% for (int i = 1; i<=56; i++) { %>
			<th style="vertical-align: middle;">税前</th>
			<th style="vertical-align: middle;">税后</th>
         <% } %>

        <th style="vertical-align: middle">姓名</th>
        <th style="vertical-align: middle">工号</th>
        <th style="vertical-align: middle">姓名</th>
        <th style="vertical-align: middle">工号</th>
        <th style="vertical-align: middle">姓名</th>
        <th style="vertical-align: middle">工号</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <c:if test="${list.suitNum == -1}" ><tr class="J_eatateItem" data-hidenumberflg="0" style="color: red"></c:if>
        <c:if test="${list.suitNum == 1}" ><tr class="J_eatateItem" data-hidenumberflg="0"></c:if>

        <td style="vertical-align: middle">${list.rowNum}</td>
        <td style="vertical-align: middle">${list.accountProjectNo}</td>
        <td style="text-align:middle;" title="${list.accountProject}">
                ${fn:substring(list.accountProject, 0, 5)}
            <c:if test="${fn:length(list.accountProject) >= '5'}">
                ...
            </c:if>
        </td>
        <td style="vertical-align: middle">${list.regionName}</td>
        <td style="vertical-align: middle">${list.areaCityName}</td>
        <td style="vertical-align: middle">${list.cityGroupName}</td>
        <td style="vertical-align: middle">${list.areaGroupName}</td>
        <td style="vertical-align: middle">${list.centerGroupName}</td>
        <td style="vertical-align: middle">${list.groupName}</td>
        <td style="vertical-align: middle">${list.expenderName}</td>
        <td style="vertical-align: middle">${list.expenderCode}</td>
        <td style="vertical-align: middle">${list.projectCityName}</td>
        <td style="vertical-align: middle">${list.projectDepartmentName}</td>
        <td style="vertical-align: middle">${list.srCityName}</td><%--added by haidan 2019/04/28--%>

        <td style="vertical-align: middle;width: 60px;">${list.customerFromName}</td>
        <td style="vertical-align: middle">${list.storeNo}</td>
        <td style="text-align:center;" title="${list.storeName}">
                ${fn:substring(list.storeName, 0, 5)}
            <c:if test="${fn:length(list.storeName) >= '5'}">
                ...
            </c:if>
        </td>
        <td style="text-align:center;" title="${list.companyName}">
                ${fn:substring(list.companyName, 0, 5)}
            <c:if test="${fn:length(list.companyName) >= '5'}">
                ...
            </c:if>
        </td>
        <td style="text-align:center;" title="${list.addressDetail}">
                ${fn:substring(list.addressDetail, 0, 5)}
            <c:if test="${fn:length(list.addressDetail) >= '5'}">
                ...
            </c:if>
        </td>
        <td style="vertical-align: middle">${list.storeSize}</td>
        <td style="vertical-align: middle">${list.contractTypeName}</td>
        <td style="vertical-align: middle">${list.saleName}</td>
        <td style="vertical-align: middle">${list.salePhone}</td>

        <td style="vertical-align: middle">${list.customerNm}</td>
        <td style="vertical-align: middle">${list.customerTel}</td>
        <td style="vertical-align: middle">${list.customerNum}</td>

        <td style="vertical-align: middle">${list.estateId}</td>
        <td style="vertical-align: middle">${list.projectNo}</td>
        <td style="vertical-align: middle">${list.estateNm}</td>
        
        <td style="vertical-align: middle">${list.partnerNm}</td>
        <td style="vertical-align: middle">${list.bigCustomerFlagStr}</td>
        <td style="vertical-align: middle">${list.bigCustomerName}</td>
        
        <td style="vertical-align: middle">${list.prepaidName}</td>
        <td style="vertical-align: middle">${list.reportId}</td>
        <td style="vertical-align: middle">${list.reportDate}</td>
        <td style="vertical-align: middle">${list.seeDate}</td>
        <td style="vertical-align: middle">${list.pledgedDate}</td>
        <td style="vertical-align: middle">${list.buildingNo}</td>
        <td style="vertical-align: middle">${list.suitNum}</td>

        <td style="vertical-align: middle">${list.roughArea}</td>
        <td style="vertical-align: middle">${list.roughPrice}</td>
        <td style="vertical-align: middle">${list.roughAmount}</td>
        <td style="vertical-align: middle">${list.roughDate}</td>
        <td style="vertical-align: middle">${list.roughAuditTime}</td>

        <td style="vertical-align: middle">${list.dealArea}</td>
        <td style="vertical-align: middle">${list.dealPrice}</td>
        <td style="vertical-align: middle">${list.dealAmount}</td>
        <td style="vertical-align: middle">${list.dealDate}</td>
        <td style="vertical-align: middle">${list.incomeStatusStr}</td>

        <!--总累计应计-->
        <td style="vertical-align: middle">${list.totalEPTIncome}</td>
        <td style="vertical-align: middle">${list.totalEATIncome}</td>
        <td style="vertical-align: middle">${list.totalEPTCommission}</td>
        <td style="vertical-align: middle">${list.totalEATCommission}</td>
        <td style="vertical-align: middle">${list.totalEPTProfit}</td>
        <td style="vertical-align: middle">${list.totalEATProfit}</td>
        <td style="vertical-align: middle">${list.totalEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.totalEATPrepaid}</td>

        <!--当年以前应计-->
        <td style="vertical-align: middle">${list.beforeEPTIncome }</td>
        <td style="vertical-align: middle">${list.beforeEATIncome }</td>
        <td style="vertical-align: middle">${list.beforeEPTCommission }</td>
        <td style="vertical-align: middle">${list.beforeEATCommission }</td>
        <td style="vertical-align: middle">${list.beforeEPTProfit }</td>
        <td style="vertical-align: middle">${list.beforeEATProfit }</td>
        <td style="vertical-align: middle">${list.beforeEPTPrepaid }</td>
        <td style="vertical-align: middle">${list.beforeEATPrepaid }</td>

        <!--当年应计-->
        <td style="vertical-align: middle">${list.thisEPTIncome}</td>
        <td style="vertical-align: middle">${list.thisEATIncome}</td>
        <td style="vertical-align: middle">${list.thisEPTCommission}</td>
        <td style="vertical-align: middle">${list.thisEATCommission}</td>
        <td style="vertical-align: middle">${list.thisEPTProfit}</td>
        <td style="vertical-align: middle">${list.thisEATProfit}</td>
        <td style="vertical-align: middle">${list.thisEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.thisEATPrepaid}</td>

        <!--1月应计-->
        <td style="vertical-align: middle">${list.janEPTIncome}</td>
        <td style="vertical-align: middle">${list.janEATIncome}</td>
        <td style="vertical-align: middle">${list.janEPTCommission}</td>
        <td style="vertical-align: middle">${list.janEATCommission}</td>
        <td style="vertical-align: middle">${list.janEPTProfit}</td>
        <td style="vertical-align: middle">${list.janEATProfit}</td>
        <td style="vertical-align: middle">${list.janEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.janEATPrepaid}</td>

        <!--2月应计-->
        <td style="vertical-align: middle">${list.febEPTIncome}</td>
        <td style="vertical-align: middle">${list.febEATIncome}</td>
        <td style="vertical-align: middle">${list.febEPTCommission}</td>
        <td style="vertical-align: middle">${list.febEATCommission}</td>
        <td style="vertical-align: middle">${list.febEPTProfit}</td>
        <td style="vertical-align: middle">${list.febEATProfit}</td>
        <td style="vertical-align: middle">${list.febEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.febEATPrepaid}</td>

        <!--3月应计-->
        <td style="vertical-align: middle">${list.marEPTIncome}</td>
        <td style="vertical-align: middle">${list.marEATIncome}</td>
        <td style="vertical-align: middle">${list.marEPTCommission}</td>
        <td style="vertical-align: middle">${list.marEATCommission}</td>
        <td style="vertical-align: middle">${list.marEPTProfit}</td>
        <td style="vertical-align: middle">${list.marEATProfit}</td>
        <td style="vertical-align: middle">${list.marEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.marEATPrepaid}</td>

        <!--4月应计-->
        <td style="vertical-align: middle">${list.aprEPTIncome}</td>
        <td style="vertical-align: middle">${list.aprEATIncome}</td>
        <td style="vertical-align: middle">${list.aprEPTCommission}</td>
        <td style="vertical-align: middle">${list.aprEATCommission}</td>
        <td style="vertical-align: middle">${list.aprEPTProfit}</td>
        <td style="vertical-align: middle">${list.aprEATProfit}</td>
        <td style="vertical-align: middle">${list.aprEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.aprEATPrepaid}</td>

        <!--5月应计-->
        <td style="vertical-align: middle">${list.mayEPTIncome}</td>
        <td style="vertical-align: middle">${list.mayEATIncome}</td>
        <td style="vertical-align: middle">${list.mayEPTCommission}</td>
        <td style="vertical-align: middle">${list.mayEATCommission}</td>
        <td style="vertical-align: middle">${list.mayEPTProfit}</td>
        <td style="vertical-align: middle">${list.mayEATProfit}</td>
        <td style="vertical-align: middle">${list.mayEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.mayEATPrepaid}</td>

        <!--6月应计-->
        <td style="vertical-align: middle">${list.junEPTIncome}</td>
        <td style="vertical-align: middle">${list.junEATIncome}</td>
        <td style="vertical-align: middle">${list.junEPTCommission}</td>
        <td style="vertical-align: middle">${list.junEATCommission}</td>
        <td style="vertical-align: middle">${list.junEPTProfit}</td>
        <td style="vertical-align: middle">${list.junEATProfit}</td>
        <td style="vertical-align: middle">${list.junEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.junEATPrepaid}</td>

        <!--7月应计-->
        <td style="vertical-align: middle">${list.julEPTIncome}</td>
        <td style="vertical-align: middle">${list.julEATIncome}</td>
        <td style="vertical-align: middle">${list.julEPTCommission}</td>
        <td style="vertical-align: middle">${list.julEATCommission}</td>
        <td style="vertical-align: middle">${list.julEPTProfit}</td>
        <td style="vertical-align: middle">${list.julEATProfit}</td>
        <td style="vertical-align: middle">${list.julEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.julEATPrepaid}</td>

        <!--8月应计-->
        <td style="vertical-align: middle">${list.augEPTIncome}</td>
        <td style="vertical-align: middle">${list.augEATIncome}</td>
        <td style="vertical-align: middle">${list.augEPTCommission}</td>
        <td style="vertical-align: middle">${list.augEATCommission}</td>
        <td style="vertical-align: middle">${list.augEPTProfit}</td>
        <td style="vertical-align: middle">${list.augEATProfit}</td>
        <td style="vertical-align: middle">${list.augEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.augEATPrepaid}</td>

        <!--9月应计-->
        <td style="vertical-align: middle">${list.sepEPTIncome}</td>
        <td style="vertical-align: middle">${list.sepEATIncome}</td>
        <td style="vertical-align: middle">${list.sepEPTCommission}</td>
        <td style="vertical-align: middle">${list.sepEATCommission}</td>
        <td style="vertical-align: middle">${list.sepEPTProfit}</td>
        <td style="vertical-align: middle">${list.sepEATProfit}</td>
        <td style="vertical-align: middle">${list.sepEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.sepEATPrepaid}</td>

        <!--10月应计-->
        <td style="vertical-align: middle">${list.octEPTIncome}</td>
        <td style="vertical-align: middle">${list.octEATIncome}</td>
        <td style="vertical-align: middle">${list.octEPTCommission}</td>
        <td style="vertical-align: middle">${list.octEATCommission}</td>
        <td style="vertical-align: middle">${list.octEPTProfit}</td>
        <td style="vertical-align: middle">${list.octEATProfit}</td>
        <td style="vertical-align: middle">${list.octEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.octEATPrepaid}</td>

        <!--11月应计-->
        <td style="vertical-align: middle">${list.novEPTIncome}</td>
        <td style="vertical-align: middle">${list.novEATIncome}</td>
        <td style="vertical-align: middle">${list.novEPTCommission}</td>
        <td style="vertical-align: middle">${list.novEATCommission}</td>
        <td style="vertical-align: middle">${list.novEPTProfit}</td>
        <td style="vertical-align: middle">${list.novEATProfit}</td>
        <td style="vertical-align: middle">${list.novEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.novEATPrepaid}</td>

        <!--12月应计-->
        <td style="vertical-align: middle">${list.decEPTIncome}</td>
        <td style="vertical-align: middle">${list.decEATIncome}</td>
        <td style="vertical-align: middle">${list.decEPTCommission}</td>
        <td style="vertical-align: middle">${list.decEATCommission}</td>
        <td style="vertical-align: middle">${list.decEPTProfit}</td>
        <td style="vertical-align: middle">${list.decEATProfit}</td>
        <td style="vertical-align: middle">${list.decEPTPrepaid}</td>
        <td style="vertical-align: middle">${list.decEATPrepaid}</td>
		
		<!--总累计应收-->
        <td style="vertical-align: middle">${list.totalRBPTIncome}</td>
        <td style="vertical-align: middle">${list.totalRBATIncome}</td>
        <!--当年以前应收-->
        <td style="vertical-align: middle">${list.beforeRBPTIncome}</td>
        <td style="vertical-align: middle">${list.beforeRBATIncome}</td>
        <!--当年应收-->
        <td style="vertical-align: middle">${list.thisRBPTIncome}</td>
        <td style="vertical-align: middle">${list.thisRBATIncome}</td>
        <!--1月应收-->
        <td style="vertical-align: middle">${list.janRBPTIncome}</td>
        <td style="vertical-align: middle">${list.janRBATIncome}</td>
        <!--2月应收-->
        <td style="vertical-align: middle">${list.febRBPTIncome}</td>
        <td style="vertical-align: middle">${list.febRBBATIncome}</td>
        <!--3月应收-->
        <td style="vertical-align: middle">${list.marRBPTIncome}</td>
        <td style="vertical-align: middle">${list.marRBATIncome}</td>
        <!--4月应收-->
        <td style="vertical-align: middle">${list.aprRBPTIncome}</td>
        <td style="vertical-align: middle">${list.aprRBATIncome}</td>
        <!--5月应收-->
        <td style="vertical-align: middle">${list.mayRBPTIncome}</td>
        <td style="vertical-align: middle">${list.mayRBATIncome}</td>
        <!--6月应收-->
        <td style="vertical-align: middle">${list.junRBPTIncome}</td>
        <td style="vertical-align: middle">${list.junRBATIncome}</td>
        <!--7月应收-->
        <td style="vertical-align: middle">${list.julRBPTIncome}</td>
        <td style="vertical-align: middle">${list.julRBATIncome}</td>
        <!--8月应收-->
        <td style="vertical-align: middle">${list.augRBPTIncome}</td>
        <td style="vertical-align: middle">${list.augRBATIncome}</td>
        <!--9月应收-->
        <td style="vertical-align: middle">${list.sepRBPTIncome}</td>
        <td style="vertical-align: middle">${list.sepRBATIncome}</td>
        <!--10月应收-->
        <td style="vertical-align: middle">${list.octRBPTIncome}</td>
        <td style="vertical-align: middle">${list.octRBATIncome}</td>
        <!--11月应收-->
        <td style="vertical-align: middle">${list.novRBPTIncome}</td>
        <td style="vertical-align: middle">${list.novRBATIncome}</td>
        <!--12月应收-->
        <td style="vertical-align: middle">${list.decRBPTIncome}</td>
        <td style="vertical-align: middle">${list.decRBATIncome}</td>
        
        <!--总累计实际-->
        <td style="vertical-align: middle">${list.totalAPTIncome}</td>
        <td style="vertical-align: middle">${list.totalAATIncome}</td>
        <td style="vertical-align: middle">${list.totalAPTCommission}</td>
        <td style="vertical-align: middle">${list.totalAATCommission}</td>
        <td style="vertical-align: middle">${list.totalAPTProfit}</td>
        <td style="vertical-align: middle">${list.totalAATProfit}</td>
        <td style="vertical-align: middle">${list.totalAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.totalAATPrepaid}</td>
        <td style="vertical-align: middle">${list.totalAPTReceive}</td>
        <td style="vertical-align: middle">${list.totalAPTBalance}</td>

        <!--当年以前实际-->
        <td style="vertical-align: middle">${list.beforeAPTIncome }</td>
        <td style="vertical-align: middle">${list.beforeAATIncome }</td>
        <td style="vertical-align: middle">${list.beforeAPTCommission }</td>
        <td style="vertical-align: middle">${list.beforeAATCommission }</td>
        <td style="vertical-align: middle">${list.beforeAPTProfit }</td>
        <td style="vertical-align: middle">${list.beforeAATProfit }</td>
        <td style="vertical-align: middle">${list.beforeAPTPrepaid }</td>
        <td style="vertical-align: middle">${list.beforeAATPrepaid }</td>

        <!--当年实际-->
        <td style="vertical-align: middle">${list.thisAPTIncome}</td>
        <td style="vertical-align: middle">${list.thisAATIncome}</td>
        <td style="vertical-align: middle">${list.thisAPTCommission}</td>
        <td style="vertical-align: middle">${list.thisAATCommission}</td>
        <td style="vertical-align: middle">${list.thisAPTProfit}</td>
        <td style="vertical-align: middle">${list.thisAATProfit}</td>
        <td style="vertical-align: middle">${list.thisAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.thisAATPrepaid}</td>

        <!--1月实际-->
        <td style="vertical-align: middle">${list.janAPTIncome}</td>
        <td style="vertical-align: middle">${list.janAATIncome}</td>
        <td style="vertical-align: middle">${list.janAPTCommission}</td>
        <td style="vertical-align: middle">${list.janAATCommission}</td>
        <td style="vertical-align: middle">${list.janAPTProfit}</td>
        <td style="vertical-align: middle">${list.janAATProfit}</td>
        <td style="vertical-align: middle">${list.janAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.janAATPrepaid}</td>

        <!--2月实际-->
        <td style="vertical-align: middle">${list.febAPTIncome}</td>
        <td style="vertical-align: middle">${list.febAATIncome}</td>
        <td style="vertical-align: middle">${list.febAPTCommission}</td>
        <td style="vertical-align: middle">${list.febAATCommission}</td>
        <td style="vertical-align: middle">${list.febAPTProfit}</td>
        <td style="vertical-align: middle">${list.febAATProfit}</td>
        <td style="vertical-align: middle">${list.febAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.febAATPrepaid}</td>

        <!--3月实际-->
        <td style="vertical-align: middle">${list.marAPTIncome}</td>
        <td style="vertical-align: middle">${list.marAATIncome}</td>
        <td style="vertical-align: middle">${list.marAPTCommission}</td>
        <td style="vertical-align: middle">${list.marAATCommission}</td>
        <td style="vertical-align: middle">${list.marAPTProfit}</td>
        <td style="vertical-align: middle">${list.marAATProfit}</td>
        <td style="vertical-align: middle">${list.marAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.marAATPrepaid}</td>

        <!--4月实际-->
        <td style="vertical-align: middle">${list.aprAPTIncome}</td>
        <td style="vertical-align: middle">${list.aprAATIncome}</td>
        <td style="vertical-align: middle">${list.aprAPTCommission}</td>
        <td style="vertical-align: middle">${list.aprAATCommission}</td>
        <td style="vertical-align: middle">${list.aprAPTProfit}</td>
        <td style="vertical-align: middle">${list.aprAATProfit}</td>
        <td style="vertical-align: middle">${list.aprAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.aprAATPrepaid}</td>

        <!--5月实际-->
        <td style="vertical-align: middle">${list.mayAPTIncome}</td>
        <td style="vertical-align: middle">${list.mayAATIncome}</td>
        <td style="vertical-align: middle">${list.mayAPTCommission}</td>
        <td style="vertical-align: middle">${list.mayAATCommission}</td>
        <td style="vertical-align: middle">${list.mayAPTProfit}</td>
        <td style="vertical-align: middle">${list.mayAATProfit}</td>
        <td style="vertical-align: middle">${list.mayAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.mayAATPrepaid}</td>

        <!--6月实际-->
        <td style="vertical-align: middle">${list.junAPTIncome}</td>
        <td style="vertical-align: middle">${list.junAATIncome}</td>
        <td style="vertical-align: middle">${list.junAPTCommission}</td>
        <td style="vertical-align: middle">${list.junAATCommission}</td>
        <td style="vertical-align: middle">${list.junAPTProfit}</td>
        <td style="vertical-align: middle">${list.junAATProfit}</td>
        <td style="vertical-align: middle">${list.junAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.junAATPrepaid}</td>

        <!--7月实际-->
        <td style="vertical-align: middle">${list.julAPTIncome}</td>
        <td style="vertical-align: middle">${list.julAATIncome}</td>
        <td style="vertical-align: middle">${list.julAPTCommission}</td>
        <td style="vertical-align: middle">${list.julAATCommission}</td>
        <td style="vertical-align: middle">${list.julAPTProfit}</td>
        <td style="vertical-align: middle">${list.julAATProfit}</td>
        <td style="vertical-align: middle">${list.julAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.julAATPrepaid}</td>

        <!--8月实际-->
        <td style="vertical-align: middle">${list.augAPTIncome}</td>
        <td style="vertical-align: middle">${list.augAATIncome}</td>
        <td style="vertical-align: middle">${list.augAPTCommission}</td>
        <td style="vertical-align: middle">${list.augAATCommission}</td>
        <td style="vertical-align: middle">${list.augAPTProfit}</td>
        <td style="vertical-align: middle">${list.augAATProfit}</td>
        <td style="vertical-align: middle">${list.augAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.augAATPrepaid}</td>

        <!--9月实际-->
        <td style="vertical-align: middle">${list.sepAPTIncome}</td>
        <td style="vertical-align: middle">${list.sepAATIncome}</td>
        <td style="vertical-align: middle">${list.sepAPTCommission}</td>
        <td style="vertical-align: middle">${list.sepAATCommission}</td>
        <td style="vertical-align: middle">${list.sepAPTProfit}</td>
        <td style="vertical-align: middle">${list.sepAATProfit}</td>
        <td style="vertical-align: middle">${list.sepAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.sepAATPrepaid}</td>

        <!--10月实际-->
        <td style="vertical-align: middle">${list.octAPTIncome}</td>
        <td style="vertical-align: middle">${list.octAATIncome}</td>
        <td style="vertical-align: middle">${list.octAPTCommission}</td>
        <td style="vertical-align: middle">${list.octAATCommission}</td>
        <td style="vertical-align: middle">${list.octAPTProfit}</td>
        <td style="vertical-align: middle">${list.octAATProfit}</td>
        <td style="vertical-align: middle">${list.octAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.octAATPrepaid}</td>

        <!--11月实际-->
        <td style="vertical-align: middle">${list.novAPTIncome}</td>
        <td style="vertical-align: middle">${list.novAATIncome}</td>
        <td style="vertical-align: middle">${list.novAPTCommission}</td>
        <td style="vertical-align: middle">${list.novAATCommission}</td>
        <td style="vertical-align: middle">${list.novAPTProfit}</td>
        <td style="vertical-align: middle">${list.novAATProfit}</td>
        <td style="vertical-align: middle">${list.novAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.novAATPrepaid}</td>

        <!--12月实际-->
        <td style="vertical-align: middle">${list.decAPTIncome}</td>
        <td style="vertical-align: middle">${list.decAATIncome}</td>
        <td style="vertical-align: middle">${list.decAPTCommission}</td>
        <td style="vertical-align: middle">${list.decAATCommission}</td>
        <td style="vertical-align: middle">${list.decAPTProfit}</td>
        <td style="vertical-align: middle">${list.decAATProfit}</td>
        <td style="vertical-align: middle">${list.decAPTPrepaid}</td>
        <td style="vertical-align: middle">${list.decAATPrepaid}</td>

        <td style="vertical-align: middle">${list.totalNPTCommission}</td>
        <td style="vertical-align: middle">${list.totalNATCommission}</td>
        <td style="vertical-align: middle">${list.remark}</td>

        <td style="vertical-align: middle">${list.expenderName}</td>
        <td style="vertical-align: middle">${list.expenderCode}</td>
        <td style="vertical-align: middle">${list.groupLeaderName}</td>
        <td style="vertical-align: middle">${list.groupLeaderCode}</td>
        <td style="vertical-align: middle">${list.centerLeaderName}</td>
        <td style="vertical-align: middle">${list.centerLeaderCode}</td>

        <%--返佣--%>
        <td style="vertical-align: middle">${list.fyObject1}</td>
        <td style="vertical-align: middle">${list.befYJFY1}</td>
        <td style="vertical-align: middle">${list.aftYJFY1}</td>
        <td style="vertical-align: middle">${list.fyObject2}</td>
        <td style="vertical-align: middle">${list.befYJFY2}</td>
        <td style="vertical-align: middle">${list.aftYJFY2}</td>
        <td style="vertical-align: middle">${list.fyObject3}</td>
        <td style="vertical-align: middle">${list.befYJFY3}</td>
        <td style="vertical-align: middle">${list.aftYJFY3}</td>

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
