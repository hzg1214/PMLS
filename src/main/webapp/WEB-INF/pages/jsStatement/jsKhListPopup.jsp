<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择考核主体</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <style>
        body {
            padding: 0px;
        }

        .searchForm {
            padding-top: 5px;
        }

        .w100 {
            width: 100px !important;
        }

        .layui-table-link {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form searchForm">
                <input type="hidden" id="hsCode" value="${hsCode}">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">考核主体</label>
                        <div class="layui-input-inline">
                            <input type="text" id="khCode" name="khCode" class="layui-input" autocomplete="off" placeholder="编号、名称">
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
            var row1 = [];
            row1.push(
                {type: 'radio'},
                {field: 'khCode', title: '考核编号', width: 200, align: 'center'},
                {field: 'khName', title: '考核名称', width: 505, align: 'center'}
            );
            var cols = [];
            cols.push(row1);
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

            optionsData.hsCode = $("#hsCode").val();
            optionsData.khCode = trimStr($("#khCode").val());

            table.reload('mainTable', {
                url: BASE_PATH + '/jsStatement/queryJsKHCodeList',
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
            parent.layer.alert('请选择考核主体！', {icon: 2, title: '提示'});
            return;
        }
        return {};
    }

</script>
</body>
</html>


