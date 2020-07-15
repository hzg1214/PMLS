<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>公司详情</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .lable-left{
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
            width: 200px;
        }
        .lable-right{
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
            width: 300px;
        }
        .layui-tab-item .layui-table thead>tr{
            color:#999999;
        }
        .layui-btn-mini {
            height: 22px;
            line-height: 22px;
            padding: 0 5px;
            font-size: 12px;
        }
    </style>
</head>
<script type="application/javascript">
    var businessDto='${businessDto}';
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div style="margin: 0 auto;">
                <div class="layui-row blockBody">
                    <div class="layui-col-xs2">
                        <div class="blockTitle">公司详情</div>
                        <input type="hidden" id="acCityNo" value="${businessDto.acCityNo}"/>
                        <input type="hidden" id="userCityNo" value="${userCityNo}"/>
                    </div>
                    <div class="layui-col-xs10 blockBtn">
                        <c:if test="${businessDto.brandType eq 29401 }"><!-- 29401: 分销渠道 -->
                            <c:if test="${businessDto.arteryType eq 29502 }"><!-- 29502: 非大渠道 -->
                                <c:if test="${businessDto.isProcuration eq 0 }"><!-- 0: 非借佣渠道 -->
                                    <button type="button" class="layui-btn" onclick="adjustToProcuration(1)">调整为借佣渠道</button>
                                </c:if>
                                <c:if test="${businessDto.isProcuration eq 1 }"><!-- 1: 借佣渠道 -->
                                    <button type="button" class="layui-btn" onclick="adjustToProcuration(0)">调整为非借佣渠道</button>
                                </c:if>
                            </c:if>
                            <c:if test="${businessDto.arteryType eq 29501 }"><!-- 29501: 大渠道 -->
                                <c:if test="${businessDto.acCityNo eq userCityNo }"><!-- 只有公司归属城市和登录人城市一致才可以操作 -->
                                    <shiro:hasPermission name="/BUSINESS:UPDATEMAINTAIN">
                                       <button type="button" class="layui-btn" onclick="updateMaintain()">分配维护人</button>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="/BUSINESS:ADDUSERMANAGER">
                                        <button type="button" class="layui-btn" onclick="addUserManager()">新建管理员</button>
                                    </shiro:hasPermission>
                                </c:if>
                            </c:if>
                            <button type="button" class="layui-btn" onclick="addFrameContract()">新建联动框架协议</button>
                            <button type="button" class="layui-btn" onclick="addCooperation()">新建联动分销合同</button>
                        </c:if>
                        <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                    </div>
                </div>

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">编号：</div>
                    <div class="layui-col-xs10 lable-right">${businessDto.companyNo}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">公司名称：</div>
                    <div class="layui-col-xs4 lable-right">${businessDto.companyName}</div>
                    <div class="layui-col-xs2 lable-left">统一社会信用代码：</div>
                    <div class="layui-col-xs4 lable-right">${businessDto.businessLicenseNo}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">法定代表人：</div>
                    <div class="layui-col-xs4 lable-right">${businessDto.legalPerson}</div>
                    <div class="layui-col-xs2 lable-left">法人手机号码：</div>
                    <div class="layui-col-xs4 lable-right">${businessDto.contactNumber}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">注册地址：</div>
                    <div class="layui-col-xs10 lable-right" style="width: calc(100% - 200px);">${businessDto.addressDetail}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">业务类型：</div>
                    <div class="layui-col-xs10 lable-right" style="width: calc(100% - 200px);">
                    <c:if test="${businessDto.brandType eq 29401 }">
                        分销渠道
                        <c:if test="${businessDto.isProcuration eq 1 }">
                            、借佣渠道
                        </c:if>
                    </c:if>
                    <c:if test="${businessDto.brandType eq 29402 }">
                        其他
                    </c:if>
                    </div>
                </div>

                <c:if test="${businessDto.brandType eq 29401 }"><!-- 29401: 分销渠道 -->
                    <div class="layui-row">
                        <div class="layui-col-xs2 lable-left">渠道分类：</div>
                        <div class="layui-col-xs4 lable-right">${businessDto.channelTypeName}</div>
                        <div class="layui-col-xs2 lable-left">渠道品牌：</div>
                        <div class="layui-col-xs4 lable-right">${businessDto.brandName}</div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs2 lable-left">渠道等级：</div>
                        <div class="layui-col-xs10 lable-right">${businessDto.channelLevelName}</div>
                        <div class="layui-col-xs2 lable-left">增值税税率：</div>
                        <div class="layui-col-xs4 lable-right">${businessDto.taxRateName}</div>
                    </div>
                    <c:if test="${businessDto.arteryType eq 29501}">
                        <!-- 只有借佣渠道的情况显示维护人相关信息 -->
                        <div class="layui-row">
                            <div class="layui-col-xs2 lable-left">借佣维护人：</div>
                            <div class="layui-col-xs4 lable-right">${businessDto.maintainerName}</div>
                            <div class="layui-col-xs2 lable-left">借佣维护中心：</div>
                            <div class="layui-col-xs4 lable-right">${businessDto.centerName}</div>
                        </div>
                    </c:if>
                </c:if>
                <%--<div class="layui-row">
                    <div class="layui-col-xs2 lable-left">联系人：</div>
                    <div class="layui-col-xs4 lable-right">${businessDto.dockingPeo}</div>
                    <div class="layui-col-xs2 lable-left">联系人手机号码：</div>
                    <div class="layui-col-xs4 lable-right">${businessDto.dockingPeoTel}</div>
                </div>--%>
                <%--<div class="layui-row">
                    <div class="layui-col-xs2 lable-left">备注：</div>
                    <div class="layui-col-xs10 lable-right">${businessDto.remark}</div>
                </div>--%>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">创建人：</div>
                    <div class="layui-col-xs4 lable-right">${businessDto.userCreateName}</div>
                    <div class="layui-col-xs2 lable-left">创建时间：</div>
                    <div class="layui-col-xs4 lable-right">
                        <fmt:parseDate value="${businessDto.dateCreate}" var="dateCreate" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <fmt:formatDate value="${dateCreate}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </div>
                </div>

            </div>

        </div>
    </div>
    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>附件</legend>
            </fieldset>
            <div style="padding: 0 70px;" id="fileBusiness">
                <div>营业执照</div><hr class="dottedLine"/>
                <div class="layui-upload-list upload_img_list" >
                </div>
            </div>
        </div>
    </div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title">
                    <li class="layui-this">关联门店</li>
                    <c:if test="${businessDto.brandType eq 29401 }"><!-- 29401: 分销渠道 -->
                        <c:if test="${businessDto.arteryType eq 29501}"><!-- 29501: 大渠道 -->
                            <li>维护人</li>
                            <li>管理员</li>
                        </c:if>
                        <li>框架协议</li>
                        <li>分销合同</li>
                        <li>对公账户</li>
                    </c:if>
                    <li>日志</li>
                </ul>
                <div class="layui-tab-content" style="min-height: 100px;">
                    <div class="layui-tab-item layui-show">
                        <div id="storeTable" lay-filter="storeTable"></div>
                    </div>
                    <c:if test="${businessDto.brandType eq 29401 }"><!-- 29401: 分销渠道 -->
                        <c:if test="${businessDto.arteryType eq 29501}"><!-- 29501: 大渠道 -->
                            <div class="layui-tab-item">
                                <div id="maintainTable" lay-filter="maintainTable"></div>
                            </div>
                            <div class="layui-tab-item">
                                <div id="userManagerTable" lay-filter="userManagerTable"></div>
                            </div>
                        </c:if>
                        <div class="layui-tab-item">
                            <div id="frameworkAgreementTable" lay-filter="frameworkAgreementTable"></div>
                        </div>
                        <div class="layui-tab-item">
                            <div id="cooperationConfirmationTable" lay-filter="cooperationConfirmationTable"></div>
                        </div>
                        <div class="layui-tab-item">
                            <div id="publicAccountsTable" lay-filter="publicAccountsTable"></div>
                        </div>
                    </c:if>
                    <div class="layui-tab-item">
                        <%--<div id="logsTable" lay-filter="logsTable"></div>--%>
                        <table class="layui-table">
                            <colgroup>
                                <col width="100">
                                <col >
                                <col width="100">
                                <col width="180">
                            </colgroup>
                            <thead>
                            <tr>
                                <th style="text-align: center;">序号</th>
                                <th style="text-align: center;">描述</th>
                                <th style="text-align: center;">创建人</th>
                                <th style="text-align: center;">操作时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${businessDto.logsList}" var="dto" varStatus="index">
                                <tr>
                                    <td style="text-align: center;">${dto.rowNum}</td>
                                    <td style="text-align: left;">${dto.operationContent}</td>
                                    <td style="text-align: center;">${dto.userCreateName}</td>
                                    <td style="text-align: center;">${dto.dateCreate}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${empty businessDto.logsList}"><div class="nodata" style="text-align: center;">暂无数据</div></c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/channelBusiness/businessDetailPage.js?v=${vs}"></script>

</body>
</html>
