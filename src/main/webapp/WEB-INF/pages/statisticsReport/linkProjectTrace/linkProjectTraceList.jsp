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
                    <input type="hidden" name="countDateEndStr" id="countDateEndStr" value="${countDateEndStr}">
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
                            <input type="text" class="layui-input" autocomplete="off" id="projectNo" name="projectNo"
                                   lay-verify="projectNo"
                                   placeholder="请输入项目编号、名称">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">截至年月</label>
                        <%--<div class="layui-input-inline w90">--%>
                            <%--<input type="text" name="startDate" id="startDate" lay-verify="startDate"--%>
                                   <%--autocomplete="off" lay-filter="startDate" class="layui-input" placeholder="开始日期">--%>
                        <%--</div>--%>
                        <div class="layui-input-inline">
                            <input type="text" name="endDate" id="endDate" lay-verify="endDate"
                                   autocomplete="off" lay-filter="endDate"
                                   class="layui-input" placeholder="请选择">
                        </div>
                    </div>


<%--                    <div class="layui-inline">
                        <div class="layui-input-inline" style="width: 390px!important;">
                            <input type="radio" value="1" id="statisticsCity" name="statisticsNum" lay-filter="statisticsNum" title="按城市汇总">
                            <input type="radio" value="2" id="statisticsArea" name="statisticsNum" lay-filter="statisticsNum" title="按区域汇总">
                            <input type="radio" value="5" id="statisticsBigCustomer" name="statisticsNum" lay-filter="statisticsNum" title="按大客户汇总">
                        </div>
                       &lt;%&ndash; <input type="hidden" value="0" id="statisticsNum" name="statisticsNum" >&ndash;%&gt;
                    </div>--%>


                    <div class="layui-inline">
                        <label class="layui-form-label">汇总条件</label>
                        <div class="layui-input-inline">
                            <select id="statisticsNum" name="statisticsNum" lay-verify="required" lay-search=""
                                    lay-filter="statisticsNum">
                                <option value="" selected="selected">请选择</option>
                                <option value="1">按城市汇总</option>
                                <option value="2">按区域汇总</option>
                                <option value="5">按大客户汇总</option>
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
<script type="text/javascript"
        src="${ctx}/meta/pmls/js/statisticsReport/linkProjectTrace/linkProjectTrace.js?_v=${vs}"></script>

<script type="text/javascript" src="${ctx}/meta/pmls/js/common/reportComm.js?_v=${vs}"></script>
</body>
</html>