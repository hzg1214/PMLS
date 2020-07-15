<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto; overflow-x:scroll;">
    <table class="table table-striped table-hover table-bordered" style="width:8000px; font-size:12px;">
        <tr style="text-align: center;font-weight: bolder;">
            <td rowspan="2" style="vertical-align: middle;width:50px;">序号</td>
            <td rowspan="2" style="vertical-align: middle;width:110px;">核算主体</td>
            <td colspan="7">维护信息</td>
            <td colspan="7">公司信息</td>
            <td colspan="13">门店信息</td>
            <td colspan="14">合同信息</td>
            <td colspan="2">翻牌业绩信息</td>
            <td colspan="8">乙转甲</td>
            <td colspan="6">续签</td>
            <td colspan="6">保证金信息</td>
            <td rowspan="2" style="vertical-align: middle;width:200px;">备注</td>
        </tr>

        <tr>
            <th style="display:none"><input value="checkbox" type="checkbox"></th>
            <th>维护区域</th>
            <th>维护城市</th>
            <th>维护事业部</th>
            <th>维护中心</th>
            <th>维护人姓名</th>
            <th>维护人工号</th>
            <th>维护开始日期</th>

            <th>公司编号</th>
            <th>公司名称</th>
            <th>公司经营地址</th>
            <th>法人</th>
            <th style="width:130px;">公司注册地址</th>
            <th>营业执照编码</th>
            <th>营业执照性质</th>

            <th>门店编号</th>
            <th>挂牌名称</th>
            <th>门店城市</th>
            <th>所属行政区</th>
            <th>门店所属中心</th>
            <th>门店地址</th>
            <th>门店规模</th>
            <th>门店资质等级</th>
            <th>经营场所类型</th>
            <th>门店负责人</th>
            <th>联系电话</th>
            <th>门店签约状况</th>
            <th>营业状态</th>

            <th>合同编号</th>
            <th>合作模式</th>
            <th>协议书编号</th>
            <th>合作开始日期</th>
            <th>合作到期日期</th>
            <th>门店数(扣除)</th>
            <th>门店数(不扣除)</th>
            <th>违约金金额</th>
            <th>草签日期</th>
            <th>签约类型</th>
            <th>OA审核通过日期</th>
            <th>OA审核撤销日期</th>
            <th>OA审核终止日期</th>
            <th>合同审核状态</th>

            <th>翻牌模式</th>
            <th>翻牌日期</th>

            <th>合同编号</th>
            <th>协议书编号</th>
            <th>公司编号</th>
            <th>公司名称</th>
            <th>乙转甲起始日期</th>
            <th>合作结束日期</th>
            <th>OA审核通过日期</th>
            <th>业绩归属人</th>

            <th>合同编号</th>
            <th>协议书编号</th>
            <th>续签起始日期</th>
            <th>合作结束日期</th>
            <th>OA审核通过日期</th>
            <th>业绩归属人</th>

            <th>应收金额</th>
            <th>到账金额</th>
            <th>未收金额</th>
            <th>到账日期</th>
            <th>退款金额</th>
            <th>退款日期</th>
        </tr>
            <c:forEach items="${reportlist}" var="list" varStatus="status">
            <c:if test="${list.storeCnt == -1}">
            <tr class="J_eatateItem" data-hidenumberflg="0" style="color: red"></c:if>
                <c:if test="${list.storeCnt == 1}">
            <tr class="J_eatateItem" data-hidenumberflg="0"></c:if>
                <!-- (当前页-1*每页显示数)+从1开始自增 -->
                <td style="width: 50px;">${list.rowNum}</td>
                <td>${list.checkBody}</td>
              <td>${list.area}</td>
               <td>${list.city}</td>
             <td>${list.sybName}</td>
            <td style="text-align:center;" title="${list.groupName}">
                    ${fn:substring(list.groupName, 0, 5)}
                <c:if test="${fn:length(list.groupName) >= '5'}">
                    ...
                </c:if>
            </td>
            <td>${list.maintainerName }</td>
            <td>${list.maintainer }</td>
            <td>${list.dateMtcStart }</td>

            <td>${list.companyNo}</td>
            <td style="text-align:center;" title="${list.companyName}">
                    ${fn:substring(list.companyName, 0, 5)}
                <c:if test="${fn:length(list.companyName) >= '5'}">
                    ...
                </c:if>
            </td>
            <td style="text-align:center;" title="${list.address}">
                    ${fn:substring(list.address, 0, 5)}
                <c:if test="${fn:length(list.address) >= '5'}">
                    ...
                </c:if>
            </td>
            <td>${list.legalPerson}</td>
            <td style="text-align:center;" title="${list.businessLicenseCompanyAddress}">
                    ${fn:substring(list.businessLicenseCompanyAddress, 0, 5)}
                <c:if test="${fn:length(list.businessLicenseCompanyAddress) >= '5'}">
                    ...
                </c:if>
            </td>
            <td style="text-align:center;" title="${list.businessLicenseNo}">
                    ${fn:substring(list.businessLicenseNo, 0, 5)}
                <c:if test="${fn:length(list.businessLicenseNo) >= '5'}">
                    ...
                </c:if>
            </td>
            <td>${list.businessLicenseType }</td>
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
            <td>${list.abtypeStore}</td>
            <td>${list.businessPlaceTypeName}</td>

            <td>${list.contacts}</td>
            <td>${list.mobilePhone}</td>
            <td>${list.storeSignStatus}</td>
            <td>${list.businessStatus}</td>
            <td>
                <a href="javascript:void(0);"
                   onclick="toContractDetail(${list.contractId},${list.contractStatus})">${list.contractNo}</a>
            </td>
            <td>${list.cooperateMode }</td>
            <td>${list.agreementNo }</td>
            <td>${list.dateLifeStart }</td>
            <td>${list.dateLifeEnd }</td>
            <td>${list.storeCnt }</td>
            <td>${list.newStoreCnt }</td>
            <td>${list.penaltyFee }</td>
            <td>${list.dateCreate }</td>
            <td>${list.contractdistinctionName }</td>
            <td>${list.performDate }</td>
            <td>${list.cancelDate }</td>
            <td>${list.dateUpdate }</td>
            <td>${list.contractCheckStatusName }</td>
            <td>${list.flipModeName }</td>
            <td>${list.flipPassDate }</td>
            <td>
                <a href="javascript:void(0);"
                   onclick="toContractDetailPre('${list.b2AContractNo}')">${list.b2AContractNo}</a>
            </td>
            <td>${list.b2AAgreementNo }</td>
            <td>${list.b2ACompanyNo }</td>
            <td>${list.b2ACompanyName }</td>
            <td>${list.b2ADateLifeStart }</td>
            <td>${list.b2ADateLifeEnd }</td>
            <td>${list.b2APerformDate }</td>
            <td>${list.b2APerformancePeople }</td>
            <td><a href="javascript:void(0);"
                   onclick="toContractDetailPre('${list.reNewContractNo }')">${list.reNewContractNo }</a></td>
            <td>${list.reNewAgreementNo }</td>
            <td>${list.reNewDateLifeStart }</td>
            <td>${list.reNewDateLifeEnd }</td>
            <td>${list.reNewPerformDate }</td>
            <td>${list.reNewPerformancePeople }</td>
            <td>${list.depositFee }</td>
            <td>${list.arrivalDepositFee }</td>
            <td>${list.uncollectedAccount }</td>
            <td>${list.dateArrivalAct }</td>
            <td>${list.refundAmount }</td>
            <td>${list.refundDate }</td>
            <td>${list.remark }</td>
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
