<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房友新房分销系统</title>
    <%@include file="../common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
        .layui-btn-mini {
            height: 22px;
            line-height: 22px;
            padding: 0 5px;
            font-size: 12px;
        }
        .fontWeight{
            font-weight: bold;
        }
        #menuTable thead tr th{
            text-align: center;
        }
    </style>
</head>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <shiro:hasPermission name="/lnk_channel:add_permission">
                <input type="hidden" id='addPermission' value="1"/>
            </shiro:hasPermission>

            <shiro:hasPermission name="/lnk_channel:upt_permission">
                <input type="hidden" id='uptPermission' value="1"/>
            </shiro:hasPermission>

            <shiro:hasPermission name="/lnk_channel:del_permission">
                <input type="hidden" id='delPermission' value="1"/>
            </shiro:hasPermission>
            <div class="layui-form">
                <div class="layui-form-item" style="margin-bottom:0px;">
                    <div class="layui-inline">
                        <label class="layui-form-label">品牌编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="brandCode" placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">品牌名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="brandName" placeholder="请输入"  lay-verify="">
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
            <div class="layui-btn-group toolbar">

                <shiro:hasPermission name="/pmlsQDPP:QDPP_ADD">
                    <button class="layui-btn" data-type="addParentMenu">新建渠道品牌</button>
                </shiro:hasPermission>
            </div>
        </div>
        <div class="layui-card-body">
            <div class="treeTable">
                <div id="menuTable"></div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/channelBusiness/channelBrandList.js?v=${vs}"></script>
</body>
</html>
