<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
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
    var reportInfo = ${info};
    console.log(${info});
</script>
<div>
    <div class="layui-card">
        <div class="layui-card-body">

            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">大定审核详情</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <c:if test="${0 == info.roughAuditStatus}">
                        <shiro:hasPermission name="/sceneEstate:REPORT_AUDIT">
                            <button class="layui-btn layui-btn-normal" data-type="pass">审核通过</button>
                            <button class="layui-btn layui-btn-danger " data-type="refuse">审核驳回</button>
                        </shiro:hasPermission>
                    </c:if>

                    <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
                </div>
            </div>


            <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
                <legend>基本信息</legend>
            </fieldset>

            <div class="layui-row">

                <div class="layui-col-xs2 lable-left"><b>项目编号：${info.projectNo}</b></div>
                <div class="layui-col-xs10 lable-right">
                    <b> &nbsp;&nbsp;&nbsp;楼盘名称：${info.estateNm}</b>
                </div>

                <input type="hidden" id="projectNo" name="projectNo" lay-verify="projectNo" class="layui-input"
                       value="${info.projectNo}">
                <input type="hidden" id="id" name="id" lay-verify="id" class="layui-input"
                       value="${info.id}">
                <input type="hidden" id="estateId" name="estateId" lay-verify="estateId" class="layui-input"
                       value="${info.estateId}">
                <input type="hidden" id="reportId" name="reportId" lay-verify="reportId" class="layui-input"
                       value="${info.reportId}">
                <input type="hidden" id="detailId" name="detailId" lay-verify="detailId" class="layui-input"
                       value="${info.detailId}">
                <input type="hidden" id="passFlag" name="passFlag" lay-verify="passFlag" class="layui-input"
                       value="${passFlag}">
                <input type="hidden" id="isApproval" name="isApproval" lay-verify="isApproval" class="layui-input"
                       value="${info.isApproval}">
                <input type="hidden" id="customerFromHide" name="customerFromHide" value="${info.customerFrom}"/>
                <input type="hidden" id="companyNo" name="companyNo" value="${info.companyId}"/>
                <input type="hidden" id="roughInputDate" name="roughInputDate" value="${info.roughInputDate}"/>
                <input type="hidden" id="buildingNo" name="buildingNo" lay-verify="buildingNo" class="layui-input"
                       value="${info.buildingNo}">
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">订单编号：</div>
                <div class="layui-col-xs4 lable-right">${info.reportId}</div>
                <div class="layui-col-xs2 lable-left">经纪公司：</div>
                <div class="layui-col-xs4 lable-right">

                    <c:choose>
                        <c:when test="${not empty  info.companyNm}">
                           ${info.companyNm}(${info.companyId})
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">客户姓名：</div>
                <div class="layui-col-xs4 lable-right">${info.customerNm}</div>
                <div class="layui-col-xs2 lable-left">手机号码：</div>
                <div class="layui-col-xs4 lable-right">${info.customerTel}</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">客户姓名：</div>
                <div class="layui-col-xs4 lable-right">${info.customerNmTwo}</div>
                <div class="layui-col-xs2 lable-left">手机号码：</div>
                <div class="layui-col-xs4 lable-right">${info.customerTelTwo}</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">是否包销房源：</div>
                <div class="layui-col-xs4 lable-right">
                    <c:if test="${info.isWrap eq 1}">
                        是
                    </c:if>
                    <c:if test="${info.isWrap ne 1}">
                        否
                    </c:if>
                </div>
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">物业类型：</div>
                <div class="layui-col-xs4 lable-right">${info.wyTypName}</div>
                <div class="layui-col-xs2 lable-left">楼室号：</div>
                <div class="layui-col-xs4 lable-right">${info.buildingNo}</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">大定面积(㎡)：</div>
                <div class="layui-col-xs4 lable-right">${info.roughArea}</div>
                <div class="layui-col-xs2 lable-left">大定总价(元)：</div>
                <div class="layui-col-xs4 lable-right">
                    <fmt:formatNumber value="${info.roughAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
                </div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">大定日期：</div>
                <div class="layui-col-xs4 lable-right">${sdk:ymd2(info.roughInputDate)}</div>
                <div class="layui-col-xs2 lable-left">业绩归属人：</div>
                <div class="layui-col-xs4 lable-right">${info.contactNm}</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">业绩归属中心：</div>
                <div class="layui-col-xs4 lable-right">${info.centerName}</div>
                <div class="layui-col-xs2 lable-left">订单进度：</div>
                <div class="layui-col-xs4 lable-right">大定</div>
            </div>

            <div class="layui-row">
                <div class="layui-col-xs2 lable-left">报备来源：</div>
                <div class="layui-col-xs4 lable-right">${info.customerFromNm}</div>
                <div class="layui-col-xs2 lable-left">状态：</div>
                <div class="layui-col-xs4 lable-right">${info.roughAuditStatusNm}</div>
            </div>

            <c:if test="${not empty info.roughAuditDesc && '2' eq info.roughAuditStatus}">
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">审核意见：</div>
                    <div class="layui-col-xs10 lable-right"><i>${info.roughAuditDesc}</i></div>
                </div>
            </c:if>

        </div>
    </div>
    <div class="layui-card">
        <div class="layui-card-body">

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>附件</legend>
            </fieldset>
            <div id="uploadWatchImg" style="padding: 0 70px;">
                <div>带看单</div>
                <hr class="dottedLine"/>
                <div class="layui-row">
                    <div class="layui-col-xs12 lable-right">
                        <div class="layui-upload-list upload_img_list">
                            <%--<c:if test="${not empty info.fileList}">--%>
                                <%--<c:set var="fileSize" value="0"/>--%>
                                <%--<c:forEach items="${info.fileList}" var="list" varStatus="status">--%>
                                    <%--<c:if test="${list.fileTypeId eq 1022}">--%>
                                        <%--<dd class="item_img" id="${list.fileRecordMainId}"--%>
                                            <%--data-id="${list.fileRecordMainId}">--%>
                                            <%--<img src="${list.fileAbbrUrl}" class="img">--%>
                                        <%--</dd>--%>
                                    <%--</c:if>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                        </div>
                    </div>
                </div>
            </div>
            <div id="uploadRoughtImg" style="padding: 0 70px;">
                <div>大定单</div>
                <hr class="dottedLine"/>
                <div class="layui-row">
                    <div class="layui-col-xs12 lable-right">
                        <div class="layui-upload-list upload_img_list">
                            <%--<c:if test="${not empty info.fileList}">--%>
                                <%--<c:set var="fileSize" value="0"/>--%>
                                <%--<c:forEach items="${info.fileList}" var="list" varStatus="status">--%>
                                    <%--<c:if test="${list.fileTypeId eq 1023}">--%>
                                        <%--<dd class="item_img" id="${list.fileRecordMainId}"--%>
                                            <%--data-id="${list.fileRecordMainId}">--%>
                                            <%--<img src="${list.fileAbbrUrl}" class="img">--%>
                                        <%--</dd>--%>
                                    <%--</c:if>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                        </div>
                    </div>
                </div>
            </div>
            <div id="uploadPartFirstImg" style="padding: 0 70px;">
                <div>甲方项目负责人名片</div>
                <hr class="dottedLine"/>
                <div class="layui-row">
                    <div class="layui-col-xs12 lable-right">
                        <div class="layui-upload-list upload_img_list">
                            <%--<c:if test="${not empty info.fileList}">--%>
                                <%--<c:set var="fileSize" value="0"/>--%>
                                <%--<c:forEach items="${info.fileList}" var="list" varStatus="status">--%>
                                    <%--<c:if test="${list.fileTypeId eq 1024}">--%>
                                        <%--<dd class="item_img" id="${list.fileRecordMainId}"--%>
                                            <%--data-id="${list.fileRecordMainId}">--%>
                                            <%--<img src="${list.fileAbbrUrl}" class="img">--%>
                                        <%--</dd>--%>
                                    <%--</c:if>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/scene/sceneTrade/roughtAudit.js?v=${vs}"></script>
</body>
</html>
