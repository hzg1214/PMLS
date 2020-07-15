<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <style>
         .w90 {
             width: 90px !important;
         }

         /*.layui-form-label {*/
         /*    width: 100px;*/
         /*}*/

         body{padding: 0px; /*overflow-y: scroll;*/}
         .layui-btn-mini {
               height: 22px;
               line-height: 22px;
               padding: 0 5px;
               font-size: 12px;
            }
    </style>
</head>

<body>

<div>
    <div class="layui-card">
        <!-- Main content -->
        <div class="layui-card-body">
            <div class="layui-form">
                <input type="hidden" name="years" id="years" value="">
                <input type="hidden" name="cityNo" id="cityNo" value="${cityNo}">
                <input type="hidden" name="flagHref" id="flagHref" value="0">
                <input type="hidden" name="countDateEndStr" id="countDateEndStr" value="${countDateEndStr}">

                <div class="layui-form-item">


                    <div class="layui-inline">
                        <label class="layui-form-label">归属项目部</label>
                        <div class="layui-input-inline">
                            <select id="projectDepartmentId" name="projectDepartmentId" lay-verify="required" lay-filter="projectDepartmentId">
                                <option value="" selected>请选择</option>
                                <c:forEach items="${rebacklist}" var="list">
                                    <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">楼盘名称</label>
                        <div class="layui-input-inline">
                            <select id="estateNmKey" name="estateNmKey" lay-verify="required" lay-search="" lay-filter="estateNmKey"
                                    xm-select-max="10" xm-select="estateNmKey" xm-select-search="" xm-select-show-count = '1' xm-select-height="30px" xm-select-skin="normal">
                                <option value="">请选择</option>
                                <c:forEach items="${estateList}" var="list">
                                    <option value="${list}">${list}</option>
                                </c:forEach>
                            </select>
<%--                            <input type="hidden" id="estateNmKey" name="estateNmKey" />--%>
                        </div>
                    </div>


                    <div class="layui-inline">
                        <label class="layui-form-label">模板类型</label>
                        <div class="layui-input-inline">
                            <select id="estateType" name="estateType" lay-verify="required" lay-search=""
                                    lay-filter="estateType">
                                <option value="" selected="selected">请选择</option>
                                <%-- <c:forEach items="${estateTypeList}" var="list">
                                     <option value="${list.dicCode}">${list.dicValue}</option>
                                 </c:forEach>--%>
                                <option value="24502">应计收入模板</option>
                                <option value="24501">应收收入模板</option>
                                <option value="24504">应计实收模板</option>
                            </select>
                        </div>
                    </div>


                    <input type="hidden" name="businessType" id="businessType" value="CX">
                    <div class="layui-inline">
                        <label class="layui-form-label">成销日期</label>
                        <div class="layui-input-inline w90">
                            <input type="text" name="countDateStart" id="countDateStart" lay-verify="countDateStart"
                                   autocomplete="off" lay-filter="countDateStart" class="layui-input" placeholder="开始日期">
                        </div>
                        <div class="layui-input-inline w90">
                            <input type="text" name="countDateEnd" id="countDateEnd" lay-verify="countDateEnd"
                                   autocomplete="off" lay-filter="countDateEnd"
                                   class="layui-input" placeholder="结束日期">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">订单编号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="reportId" id="reportId" lay-verify="reportId" autocomplete="off" placeholder="请输入订单编号" class="layui-input">
                        </div>
                        <div class="layui-input-inline" style="width: 315px;">
                            <input type="text" name="searchKey" id="searchKey" lay-verify="searchKey" autocomplete="off"  placeholder="请输入项目编号、楼室号、客户姓名、电话" class="layui-input">
                        </div>
                    </div>

          <%--          <div class="layui-inline toolbar">
                        <button class="layui-btn" data-type="reload">查询</button>
                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>--%>

                </div>
            </div>
        </div>
    </div>
</div>

<div>
        <div class="layui-card">
            <div class="layui-card-header">
                <div class="layui-inline toolbar">
                    <button class="layui-btn"  data-type="export">导出</button>
                    <button class="layui-btn" id="btnImport" data-type="import">导入</button>
                    <button class="layui-btn" data-type="reload">查询</button>
                    <button class="layui-btn" data-type="reset">重置</button>
                </div>
            </div>
            <div class="layui-card-body">
                <table id="contentTable" lay-size="sm" lay-filter="content"></table>
            </div>
        </div>
</div>



<script type="text/javascript" src="${ctx}/meta/pmls/js/commission/commission.js?_v=${vs}"></script>
<script src="${ctx}/meta/pmls/js/formSelects-v4.js?_v=${vs}"></script>

</body>
</html>