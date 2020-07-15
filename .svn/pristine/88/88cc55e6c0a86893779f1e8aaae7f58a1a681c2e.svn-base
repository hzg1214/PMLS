<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>联动分销合同列表</title>
    <%@include file="../common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
        .searchForm{
            padding-top: 5px;
        }
        .searchForm .layui-form-item .layui-input-inline{

        }

    </style>
</head>
<body>
<script type="application/javascript">
    var userId = '387';

</script>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form id="searchForm" class="layui-form searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">合同编号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="contractNo" name="contractNo" class="layui-input" lay-verify="" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="projectName" name="projectName" class="layui-input" lay-verify="" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">渠道公司</label>
                        <div class="layui-input-inline">
                            <input type="text" id="companyName" name="companyName" class="layui-input" lay-verify="" placeholder="请输入">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item"  style="margin-bottom:0px;">
                    <div class="layui-inline">
                        <label class="layui-form-label">合同状态</label>
                        <div class="layui-input-inline">
                            <select id="approveState" name="approveState" lay-verify="required" lay-search="">
                                <option value="">请选择</option>
                                <option value="10401">草签</option>
                                <option value="10402">审核中</option>
                                <option value="10403">审核通过</option>
                                <option value="10404">审核未通过</option>
                                <option value="10405">作废</option>
                            </select>
                        </div>
                    </div>
 <%--                   <div class="layui-inline">
                        <label class="layui-form-label">创建日期</label>
                        <div class="layui-input-inline" style="width: 87px;margin-right: 5px;">
                            <input type="text" id="createDateMin" class="layui-input" autocomplete="off" placeholder="开始日期"
                                   style="padding-left: 6px;">
                        </div>
                        <div class="layui-form-mid" style="margin-right: 5px;">-</div>
                        <div class="layui-input-inline" style="width: 87px;">
                            <input type="text" id="createDateMax" class="layui-input" autocomplete="off" placeholder="结束日期"
                                   style="padding-left: 6px;">
                        </div>
                    </div>--%>
                    <div class="layui-inline">
                        <label class="layui-form-label">OA单号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="oaNo" name="oaNo" class="layui-input" lay-verify="" placeholder="请输入">
                        </div>
                    </div>
                    <%--<div class="layui-inline">
                        <label class="layui-form-label">创建人</label>
                        <div class="layui-input-inline">
                            <input type="text" id="createUserName" name="createUserName" class="layui-input" lay-verify="" placeholder="请输入">
                        </div>
                    </div>--%>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="reload">查询</button>
                        <button type="reset" class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div>
    <div class="layui-card">
        <div class="layui-card-header">
            <div class="layui-btn-group toolbar">

                <shiro:hasPermission name="/pmlsFXHT:FXHT_ADD">
                    <button class="layui-btn" data-type="addPmlsCooperation">新建联动分销合同</button>
                </shiro:hasPermission>
            </div>
        </div>
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>

</div>
</body>
</html>
<script type="text/html" id="toolMainTable">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    {{#  if(d.approveState == '10401' || d.approveState == '10404'){ }}
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">编辑</a>
    {{#  } }}
    {{#  if(d.approveState == '10401' || d.approveState == '10404'){ }}
    <shiro:hasPermission name="/pmlsFXHT:FXHT_INVALID">
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="invalid">作废</a>
    </shiro:hasPermission>
    {{#  } }}
</script>
<script type="text/javascript" src="${ctx}/meta/pmls/js/cooperation/cooperationList.js?_v=${vs}"></script>
