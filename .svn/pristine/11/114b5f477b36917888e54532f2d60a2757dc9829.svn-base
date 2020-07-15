<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto; overflow-x:scroll;">
    <table class="table table-striped table-hover table-bordered" style="width:9040px; font-size:12px;">
        <tr style="text-align: center;font-weight: bolder;">
            <td rowspan="2" style="vertical-align: middle;width:50px;">序号</td>
            <td colspan="5">业绩信息</td>
            <td colspan="8">公司信息</td>
            <td colspan="13">门店信息</td>
            <td colspan="18">公盘合作协议信息</td>
            <td colspan="2">保证金信息</td>
            <td colspan="1" rowspan="1">会员信息</td>
            <td colspan="3">门店录入信息</td>
            <td colspan="6">业绩归属人员信息</td>
            <td colspan="6">门店维护人员信息</td>
            <td rowspan="2">备注</td>
        </tr>
        <tr>
            <!--5-->
            <th>业绩归属城市</th>
            <th>业绩所在城市</th>
            <th>业绩归属事业部</th>
            <th>业绩归属中心</th>
            <th>业绩归属人</th>

            <th>公司编号</th>
            <th style="width: 300px;">公司名</th>
            <th>公司经营地址</th>
            <th>法人</th>
            <th>联系电话</th>
            <th>公司注册地址</th>
            <th style="width: 160px;">统一社会信用代码</th>
            <th>营业执照性质</th>
            <th>门店编号</th>
            <th>挂牌名称</th>
            <th>门店城市</th>
            <th>所属行政区</th>
            <th>门店所属中心</th>
            <th>门店地址</th>
            <th>门店规模</th>
            <th>经营场所类型</th>
            <th>门店关联1.0房友账号</th>
            <th>连锁情况</th>
            <th>连锁品牌</th>
            <th>经纪人数</th>
            <th>营业状态</th>
            <th>合同编号</th>
            <th>合同类别</th>
            <th>甲方授权代表</th>
            <th>甲方联系方式</th>
            <th style="width: 300px;">开户名</th>
            <th style="width: 300px;">开户行</th>
            <th>开户省市</th>
            <th style="width: 300px;">银行账号</th>
            <th>协议书编号</th>
            <th>合作开始日期</th>
            <th>合作结束日期</th>
            <th>门店数</th>
            <th>终止类型</th>
            <th>终止日期</th>
            <th>草签日期</th>
            <th>审批通过日期</th>
            <th>合同审批状态</th>
            <th>是否首签</th>
            <%--<th>公盘合同终止编号</th>
            <th>终止类型</th>
            <th>终止日期</th>
            <th>OA审核通过日期</th>
            <th>合同审核状态</th>--%>
            <%--<th>公盘保证金</th>--%>
            <th>是否到账</th>
            <th>到账日期</th>
            <th>会员生效/失效日期</th>
            <th>姓名</th>
            <th>工号</th>
            <th>录入日期</th>
            <th>拓展专员姓名</th>
            <th>拓展专员工号</th>
            <th>拓展经理工号</th>
            <th>拓展经理姓名</th>
            <th>中心负责人名</th>
            <th>中心负责人工号</th>
            <th>维护事业部</th>
            <th>维护中心</th>
            <th>维护组</th>
            <th>维护人姓名</th>
            <th>维护人工号</th>
            <th>维护开始日期</th>
        </tr>
        <c:forEach items="${reportlist}" var="list" varStatus="status">

        <c:if test="${list.storeCnt eq -1}">
            <tr class="J_eatateItem" data-hidenumberflg="0" style="color: red">
        </c:if>
        <c:if test="${list.storeCnt eq 1}">
            <tr class="J_eatateItem" data-hidenumberflg="0">
        </c:if>
            <!-- (当前页-1*每页显示数)+从1开始自增 -->
            <td>${list.rowNum}</td>
            <td>${list.areaCityName}</td>
            <td>${list.performanceCity}</td>
            <td>${list.performanceDepartment}</td>
            <td>${list.performanceCenter}</td>
            <td>${list.performancePeople}</td>
            <td>${list.companyNo}</td>
            <td>${list.companyName}</td>
            <td style="text-align:center;" title="${list.address}">
                    ${fn:substring(list.address, 0, 5)}
                <c:if test="${fn:length(list.address) >= '5'}">
                    ...
                </c:if>
            </td>
            <td>${list.legalPerson}</td>
            <td>${list.contactNumber}</td>
            <td style="text-align:center;" title="${list.businessLicenseCompanyAddress}">
                    ${fn:substring(list.businessLicenseCompanyAddress, 0, 5)}
                <c:if test="${fn:length(list.businessLicenseCompanyAddress) >= '5'}">
                    ...
                </c:if>
            </td>
            <td style="text-align:center;">
                    ${list.businessLicenseNo}
            </td>
            <td>${list.businessLicenseType}</td>
            <td>${list.storeNo}</td>
            <td style="text-align:center;" title="${list.storeName}">
                    ${fn:substring(list.storeName, 0, 5)}
                <c:if test="${fn:length(list.storeName) >= '5'}">
                    ...
                </c:if>
            </td>
            <td>${list.storeCityName}</td>
            <td>${list.districtName}</td>
            <td>${list.storeGroupName}</td>
            <td style="text-align:center;" title="${list.addressDetail}">
                    ${fn:substring(list.addressDetail, 0, 5)}
                <c:if test="${fn:length(list.addressDetail) >= '5'}">
                    ...
                </c:if>
            </td>
            <td>${list.storeSizeScaleName}</td>
            <td>${list.businessPlaceTypeName}</td>
            <td>${list.fyAccount}</td>
            <td>${list.linkageSituation}</td>
            <td>${list.brandName}</td>
            <td>${list.agentNum}</td>
            <td>${list.businessStatus}</td>
            <td>${list.gpContractNo}</td>
            <td>${list.contractType}</td>
            <td>${list.partyBNm}</td>
            <td>${list.partyBTel}</td>
            <td>${list.accountNm}</td>
            <td style="text-align:center;" title="${list.bankAccountNm}">
                    ${fn:substring(list.bankAccountNm, 0, 12)}
                <c:if test="${fn:length(list.bankAccountNm) >= '12'}">
                    ...
                </c:if>
            </td>
            <td>${list.accountProvinceCityNm}</td>
            <td style="text-align:center;" title="${list.bankAccount}">
                    ${fn:substring(list.bankAccount, 0, 12)}
                <c:if test="${fn:length(list.bankAccount) >= '12'}">
                    ...
                </c:if>
            </td>
            <td>${list.gpAgreementNo}</td>
            <td>${list.dateLifeStart}</td>
            <td>${list.dateLifeEnd}</td>
            <td>${list.storeCnt}</td>
            <td>${list.stopType}</td>
            <td>${list.stopDate}</td>
            <td>${list.dateCreate}</td>
            <td>${list.performDate}</td>
            <td>${list.contractCheckStatusName}</td>
            <td>${list.isFirst}</td>
            <%--<td>${list.gpContractStopNo}</td>
            <td>${list.stopType}</td>
            <td>${list.stopDate}</td>
            <td>${list.approvePassDate}</td>
            <td>${list.stopApproveStatus}</td>--%>
            <%--<td>${list.depositFee}</td>--%>
            <td>${list.isArrival}</td>
            <td>${list.pastTrialDate}</td>
            <td>${list.approvePassDate}</td>
            <td>${list.userName}</td>
            <td>${list.userCode}</td>
            <td>${list.storeDateCreate}</td>
            <td>${list.expandCommissioner}</td>
            <td>${list.expandCommissionerNum}</td>
            <td>${list.expandManager}</td>
            <td>${list.expandManagerNum}</td>
            <td>${list.centerLeaderName}</td>
            <td>${list.centerLeaderCode}</td>

            <td style="text-align:center;" title="${list.areaGroupName}">
                    ${fn:substring(list.areaGroupName, 0, 5)}
                <c:if test="${fn:length(list.areaGroupName) >= '5'}">
                    ...
                </c:if>
            </td>
            <td style="text-align:center;" title="${list.centerGroupName}">
                    ${fn:substring(list.centerGroupName, 0, 5)}
                <c:if test="${fn:length(list.centerGroupName) >= '5'}">
                    ...
                </c:if>
            </td>
            <td style="text-align:center;" title="${list.groupName}">
                    ${fn:substring(list.groupName, 0, 5)}
                <c:if test="${fn:length(list.groupName) >= '5'}">
                    ...
                </c:if>
            </td>
            <td>${list.maintainerName}</td>
            <td>${list.maintainer}</td>
            <td>${list.dateMtcStart}</td>
            <td>${list.remark}</td>
            </c:forEach>
    </table>
</div>

${pageInfo.html}

<c:if test="${fn:length(reportlist) le 0}">
    <div class="nodata">
        <i class="icon-hdd"></i>
        <p>暂无数据...</p>
    </div>
</c:if>
