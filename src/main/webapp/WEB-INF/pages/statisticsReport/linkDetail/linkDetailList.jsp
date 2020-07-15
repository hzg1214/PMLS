<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="../../common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/meta/layui/lay/modules/newTable.js?v=${vs}"></script>
    <style>
    </style>
</head>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">架构年份</label>
                        <div class="layui-input-inline">
                            <select id="orgYear" name="orgYear" lay-verify="required" lay-filter="orgYear">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">查询维度</label>
                        <div class="layui-input-inline">
                            <select id="serachKey" name="serachKey" xm-select="serachKey"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">核算主体</label>
                        <div class="layui-input-inline">
                            <select id="accountProject" name="accountProject" xm-select="accountProject"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">归属区域</label>
                        <div class="layui-input-inline">
                            <select id="region" name="region" xm-select="region"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">归属城市</label>
                        <div class="layui-input-inline">
                            <select id="areaCity" name="areaCity" xm-select="areaCity"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所在城市</label>
                        <div class="layui-input-inline">
                            <select id="city" name="city" xm-select="city"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">归属中心</label>
                        <div class="layui-input-inline">
                            <select id="centerGroup" name="centerGroup" xm-select="centerGroup"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">报备来源</label>
                        <div class="layui-input-inline">
                            <select id="customerFromId" name="customerFromId" lay-verify="customerFromId"
                                    lay-search="" lay-filter="customerFromId">
                                <option value="">请选择</option>
                                <c:forEach items="${customerFromList}" var="list">
                                    <option value="${list.dicCode}">${list.dicValue}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="estateId"
                                   placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">楼盘名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="estateNm"
                                   placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">报备编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="reportId"
                                   placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">门店编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="storeNo" placeholder="请输入"
                                   lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">报备日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="reportDateStart" id="reportDateStart" lay-verify="reportDateStart"
                                   placeholder="请选择" autocomplete="off" lay-verify="required" class="layui-input"
                                   readonly>
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" name="reportDateEnd" autocomplete="off" id="reportDateEnd"
                                   lay-verify="reportDateEnd"
                                   placeholder="请选择" lay-verify="required" class="layui-input" readonly>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">大定日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="roughDateStart" autocomplete="off" id="roughDateStart"
                                   lay-verify="roughDateStart"
                                   placeholder="请选择" lay-verify="required" class="layui-input" readonly>
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" name="roughDateEnd" autocomplete="off" id="roughDateEnd"
                                   lay-verify="roughDateEnd"
                                   placeholder="请选择" lay-verify="required" class="layui-input" readonly>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">成销日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="dealDateStart" autocomplete="off" id="dealDateStart"
                                   lay-verify="dealDateStart"
                                   placeholder="请选择" lay-verify="required" class="layui-input" readonly>
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" name="dealDateEnd" autocomplete="off" id="dealDateEnd"
                                   lay-verify="dealDateEnd"
                                   placeholder="请选择" lay-verify="required" class="layui-input" readonly>
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
                <div>
                    <table id="contentTable" lay-size="sm" lay-filter="content"></table>
                </div>
            </div>
            </section>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="${ctx}/meta/pmls/js/statisticsReport/linkDetail/linkDetail.js?_v=${vs}"></script>

<script type="text/javascript" src="${ctx}/meta/pmls/js/common/reportComm.js?_v=${vs}"></script>
</body>
</html>