<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择结算订单</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <style>
        body {
            padding: 0px;
        }

        .searchForm {
            padding-top: 5px;
        }

        .w250 {
            width: 250px !important;
        }

        .layui-table td, .layui-table th {
            font-size: 12px!important;
        }
    </style>
</head>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form searchForm">
                <input type="hidden" id="companyNo" value="${companyNo}">
                <input type="hidden" id="projectNo" value="${projectNo}">
                <input type="hidden" id="reportNoList" value="${reportNoList}">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">关键词</label>
                        <div class="layui-input-inline w250">
                            <input type="text" id="keyword" name="keyword" maxlength="200" class="layui-input" autocomplete="off" placeholder="客户姓名、订单编号、项目名称">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="search">查询</button>
                    </div>
                </div>
            </form>
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>
<script>
    var table;
    layui.use(['element', 'form', 'table', 'layer'], function () {
        var element = layui.element,
            form = layui.form,
            layer = layui.layer,
            $ = layui.$;
        table = layui.table;

        table.render({
            elem: '#mainTable'
            , cols: setCols()
            , height: 'full'
            , even: false //开启隔行背景
            , page: true
            , loading: false
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            , response: {
                statusName: 'returnCode' //数据状态的字段名称，默认：code
                , statusCode: 200 //成功的状态码，默认：0
                , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                , countName: 'totalCount' //数据总数的字段名称，默认：count
                , dataName: 'returnData' //数据列表的字段名称，默认：data
            }
        });

        function setCols() {
            var row = [];
            row.push(
                {type: 'radio'},
                {field: 'reportId', title: '订单编号', width: 150, align: 'center'},
                {field: 'projectName', title: '项目名称', width: 120, align: 'center'},
                {field: 'customerName', title: '客户名称', width: 100, align: 'center'},
                {field: 'buildingNo', title: '楼室号', width: 113, align: 'center'},
                {field: 'vaildDYAmount', title: '实际可垫金额(元)', width: 125, align: 'center',style:'text-align:right'
                    , templet: function (d) {
                        return formatMoney(d.vaildDYAmount);
                    }},
                {field: 'vaildFYAmount', title: '实际可返金额(元)', width: 125, align: 'center',style:'text-align:right'
                    , templet: function (d) {
                        return formatMoney(d.vaildFYAmount);
                    }}
            );
            var cols = [];
            cols.push(row);
            return cols;
        }

        reloadData();//初始化加载表格

        var active = {
            search: function () {
                reloadData();
            }
        };

        $('.toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        function reloadData() {
            var index = layer.load(2, {shade: 0.1});
            var optionsData = {};

            optionsData.companyNo = $("#companyNo").val();
            optionsData.projectNo = trimStr($("#projectNo").val());
            optionsData.reportNoList = $("#reportNoList").val();
            optionsData.keyword = trimStr($("#keyword").val());

            table.reload('mainTable', {
                url: BASE_PATH + '/jsStatement/queryJsOffsetReportList',
                cols: setCols(),
                height: window.innerHeight - $(".layui-table-view").offset().top - 10,
                where: optionsData,
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                done: function (res, curr, count) {
                    layer.close(index);
                }
            });
        }

    });

    function getFormData() {
        var checkStatus = table.checkStatus("mainTable");
        var data = checkStatus.data;
        if (checkStatus.data.length == 1) {
            return data[0];
        } else {
            parent.layer.alert('选择结算订单！', {icon: 2, title: '提示'});
            return;
        }
        return {};
    }

</script>
</body>
</html>


