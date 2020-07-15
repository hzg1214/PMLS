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

    <link rel="stylesheet" href="${ctx}/meta/pmls/zTree/css/zTreeStyle/zTreeStyle.css?v=${vs}" type="text/css">
</head>

<body>

<div>
    <div class="layui-card">
        <!-- Main content -->
        <div class="layui-card-body">
            <div class="layui-form">
                <input type="hidden" name="year" id="year" value="${year}">
                <input type="hidden" name="month" id="month" value="${month}">


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 100px;">请选择关账年月</label>
                        <div class="layui-input-inline">
                            <input type="text" name="yearMonth" id="yearMonth" lay-verify="yearMonth"
                                   placeholder="请选择" lay-verify="required" class="layui-input">
                            <input type="hidden" id="switchYear" name="switchYear" value="">
                            <input type="hidden" id="switchMonth" name="switchMonth" value="">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">城市</label>
                        <div class="layui-input-inline">
                            <select id="searchCityNo" name="searchCityNo" lay-verify="required" lay-search="" lay-filter="searchCityNo"
                                    xm-select="searchCityNo" xm-select-search="" xm-select-show-count = '1' xm-select-height="30px" xm-select-skin="normal">
                                <option value="">请选择</option>
                                <c:forEach items="${cityDtoList}" var="list">
                                    <option value="${list.cityNo}">${list.cityName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="layui-inline toolbar">
                        <button class="layui-btn" data-type="reload">查询</button>
                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div>
    <div class="layui-card">

        <div class="layui-card-header">
            <div class="layui-inline toolbar">
              <shiro:hasPermission name="/pmlsSwitch:E_CLOSE">
                <button class="layui-btn" id="btnImport" data-type="closeSwitch">关账</button>
              </shiro:hasPermission>
              <shiro:hasPermission name="/pmlsSwitch:E_OPEN">
                <button class="layui-btn"  data-type="openSwitch">开账</button>
              </shiro:hasPermission>
            </div>
        </div>

        <div class="box box-primary" style="padding-left: 10px;padding-top: 10px;">
            <div class="box-body">
                <div>
                    <ul>
                        <li style="padding:5px;">注:红字表示该区域已关账，黑字表示未关账。</li>
                        <li><ul id="treeDemo" class="ztree"></ul></li>
                    </ul>
                </div>
            </div>
        </div>


    </div>
</div>


<script type="text/javascript" src="${ctx}/meta/pmls/js/commission/switchList.js?v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/pmls/js/jquery-1.7.1.js?v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/pmls/zTree/js/jquery.ztree.core.js?v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/pmls/zTree/js/jquery.ztree.excheck.js?v=${vs}"></script>

</body>
</html>