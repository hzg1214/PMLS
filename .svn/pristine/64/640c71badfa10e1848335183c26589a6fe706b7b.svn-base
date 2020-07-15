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

</head>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">

                <div class="layui-form-item" style="margin-bottom:0px;">
                    <div class="layui-inline">
                        <label class="layui-form-label">渠道公司</label>
                        <div class="layui-input-inline">
                            <input type="text" id="companyNo" name="companyNo" lay-verify="companyNo"
                                   lay-filter="companyNo" autocomplete="off"
                                   class="layui-input" placeholder="渠道公司、编号">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目</label>
                        <div class="layui-input-inline">
                            <input type="text" id="projectNo" name="projectNo" lay-verify="projectNo"
                                   lay-filter="projectNo" autocomplete="off"
                                   class="layui-input" placeholder="项目名称、编号">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                    </div>
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

<script src="${ctx}/meta/pmls/js/scene/sceneExpent/jssListPopup.js?v=${vs}"></script>

</body>
</html>