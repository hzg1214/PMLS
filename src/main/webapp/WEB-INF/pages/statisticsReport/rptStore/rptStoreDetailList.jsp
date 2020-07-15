<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="../../common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/meta/layui/lay/modules/newTable.js?v=${vs}"></script>
    <style>
        .labelWidth {
            width: 90px;
        }
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
                        <label class="layui-form-label labelWidth">归属城市</label>
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
                        <label class="layui-form-label ">业务类型</label>
                        <div class="layui-input-inline">
                            <select id="brandType" name="brandType" lay-verify="brandType"
                                    lay-search="" lay-filter="brandType">
                                <option value="">请选择</option>
                                <c:forEach items="${brandTypeList}" var="list">
                                    <option value="${list.dicCode}">${list.dicValue}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label labelWidth">是否房友门店</label>
                        <div class="layui-input-inline">
                            <select id="isFyStore" name="isFyStore" lay-filter="isFyStore">
                            	<option value="">请选择</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label ">门店</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="storeNo"
                                   placeholder="门店编号、名称" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">经纪公司</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="companyNo"
                                   placeholder="公司编号、名称" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label labelWidth">大定数据</label>
                        <div class="layui-input-inline">
                            <select id="roughYear" name="roughYear" lay-verify="roughYear" lay-filter="roughYear">
                                <option value="">请选择年份</option>
                                <option value="2020">2020</option>
                                <option value="2019">2019</option>
                                <option value="2018">2018</option>
                                <option value="2017">2017</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <select id="roughDimension" name="roughDimension" lay-verify="roughDimension" lay-filter="roughDimension">
                                <option value="">请选择</option>
                                <option value="yearDimension">年维度</option>
                                <option value="monthDimension">月维度</option>
                                <option value="weekDimension">周维度</option>
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
                <div>
                    <table id="contentTable" lay-size="sm" lay-filter="content"></table>
                </div>
            </div>
            </section>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/meta/pmls/js/common/reportComm.js?_v=${vs}"></script>
<script type="text/javascript"
        src="${ctx}/meta/pmls/js/statisticsReport/rptStore/rptStoreDetailList.js?_v=${vs}"></script>

</body>
</html>