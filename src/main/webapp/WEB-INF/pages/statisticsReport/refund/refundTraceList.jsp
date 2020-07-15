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
        .w90 {
            width: 90px !important;
        }
    </style>
</head>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <input type="hidden" name="year" id="year" value="${year}">
                <input type="hidden" name="month" id="month" value="${month}">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">归属城市</label>
                        <div class="layui-input-inline">
                            <select id="cityNo" name="cityNo" lay-verify="required" lay-search="" lay-filter="cityNo"
                                    xm-select="cityNo" xm-select-search="" xm-select-show-count='1'
                                    xm-select-height="30px" xm-select-skin="normal">
                                <option value="">请选择</option>
                                <c:forEach items="${citylist}" var="city">
                                    <option value="${city.cityNo}">${city.cityName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">项目</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="project"
                                   placeholder="请输入" lay-verify="">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">截止日期</label>
                        <div class="layui-input-inline w90">
                            <input type="text" name="yearMonth" id="yearMonth" lay-verify="yearMonth"
                                   placeholder="年月" lay-verify="required" class="layui-input" autocomplete="off" >
                            <input type="hidden" id="switchYear" name="switchYear" value="">
                            <input type="hidden" id="switchMonth" name="switchMonth" value="">
                        </div>
                        <div class="layui-input-inline w90">
                            <select id="weekNo" name="weekNo" lay-verify="required" lay-filter="weekNo" autocomplete="off" >
                                <option value="" selected>月周</option>

                            </select>
                        </div>
                    </div>

                    <div class="layui-inline toolbar">
                        <button class="layui-btn" id="btnSearch" data-type="reload">查询</button>
                        <button class="layui-btn" id="btnExport" data-type="export">导出</button>
                    </div>
                </div>



                <div class="form-inline" id="exportMsg"
                        <c:if test="${userReportSize eq 0}" > style="display: none" </c:if>
                >
                    <li>
                        <label class="lab" style="width: 200px;font-size: 12px;font-weight: bold;color: red">您当前有<span
                                id="reportSize">${userReportSize}</span>个文件正在下载中……</label>
                    </li>
                </div>



            </div>
        </div>
    </div>
</div>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>

<script type="text/javascript"
        src="${ctx}/meta/pmls/js/statisticsReport/refund/refundTraceList.js?_v=${vs}"></script>

<script type="text/javascript" src="${ctx}/meta/pmls/js/common/reportComm.js?_v=${vs}"></script>
</body>
</html>
