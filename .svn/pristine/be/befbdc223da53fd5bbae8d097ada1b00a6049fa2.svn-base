<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>收入拆分明细</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>

    <style>
        body {
            padding: 0px;
        }

        .searchForm {
            padding-top: 5px;
        }

        .w300 {
            width: 300px !important;
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
                <input type="hidden" id='parentId' value="${id}"/>
                <shiro:hasPermission name="/skStatement:cancel">
                    <input type="hidden" id='toCancel' value="1"/>
                </shiro:hasPermission>
                <input type="hidden" id='userId' value="${userId}"/>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label ">关键字</label>
                        <div class="layui-input-inline w300">
                            <input type="text" id="keyword" name="keyword" autocomplete="off" class="layui-input"
                                   placeholder="订单编号、客户名称、楼室号">
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

    var active;
    layui.use(['element', 'form', 'table', 'layer'], function () {
        var element = layui.element,
            form = layui.form,
            layer = layui.layer,
            $ = layui.$,
            table = layui.table;

        var toCancel = NullToZero($("#toCancel").val());
        var userId = NullToEmpty($("#userId").val());

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
                {type: 'numbers', fixed: true, title: '序号', width: 60, align: 'center'},
                {field: 'reportId', fixed: true, title: '订单编号', width: 160, align: 'center'},
                {field: 'customerName', title: '客户姓名', width: 135, align: 'center', style: 'text-align: left'},
                {field: 'buildingNo', title: '楼室号', width: 120, align: 'center'},
                {
                    field: 'roughtArea', title: '大定面积（㎡）', width: 150, align: 'center', style: 'text-align: right'
                    , templet: function (d) {
                        return formatMoney(d.roughtArea);
                    }
                },
                {
                    field: 'roughtAmount', title: '大定金额（元）', width: 160, align: 'center', style: 'text-align: right'
                    , templet: function (d) {
                        return formatMoney(d.roughtAmount);
                    }
                },
                {
                    field: 'roughtDate', title: '大定日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.roughtDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.roughtDate, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'roughAuditTime', title: '大定过审时间', width: 180, align: 'center'
                    , templet: function (d) {
                        if (d.roughAuditTime == null) {
                            return "-";
                        } else {
                            return formatDate(d.roughAuditTime, "yyyy-MM-dd hh:mm:ss");
                        }
                    }
                },
                {
                    field: 'cxArea', title: '成销面积（㎡）', width: 150, align: 'center', style: 'text-align: right'
                    , templet: function (d) {
                        return formatMoney(d.cxArea);
                    }
                },
                {
                    field: 'cxAmount', title: '成销总价（元）', width: 160, align: 'center', style: 'text-align: right'
                    , templet: function (d) {
                        return formatMoney(d.cxAmount);
                    }
                },
                {
                    field: 'dealDate', title: '成销日期', width: 120, align: 'center'
                    , templet: function (d) {
                        if (d.dealDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.dealDate, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'yjAmount_bef', title: '成销收入税前（元）', width: 160, align: 'center' , style: 'text-align: right'
                    , templet: function (d) {
                        return formatMoney(d.yjAmount_bef);
                    }
                },
                {
                    field: 'sjAmount_bef', title: '成销实收金额（元）', width: 160, align: 'center' , style: 'text-align: right'
                    , templet: function (d) {
                        return formatMoney(d.sjAmount_bef);
                    }
                },
                {
                    field: 'allocatAmount_bef', title: '本次拆分金额（元）', width: 160, align: 'center' , style: 'text-align: right'
                    , templet: function (d) {
                        return formatMoney(d.allocatAmount_bef);
                    }
                }
            );
            if (toCancel == "1") {
                row.push({
                    title: '操作', width: 70, align: 'center', style: 'text-align: left', fixed: 'right'
                    , templet: function (row) {
                        var showContent = '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="javascript:skAllocateDetailPage.cancelDtl(' + row.id + ')">撤销</a>';
                        return showContent; //列渲染
                    }
                });
            }
            var cols = [];
            cols.push(row);
            return cols;
        }

        reloadData();//初始化加载表格

        active = {
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

            optionsData.parentId = $("#parentId").val();
            optionsData.keyword = trimStr($("#keyword").val());

            table.reload('mainTable', {
                url: BASE_PATH + '/skAllocate/querySkAllocateDtlList',
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

    var skAllocateDetailPage = {}
    skAllocateDetailPage.cancelDtl = function (id) {
        parent.layer.confirm("确认撤销吗？", {icon: 3, title: '提示'}, function () {
            var index = parent.layer.load(2);
            $.ajax({
                url: ctx + '/skAllocate/cancelDtl',
                type: 'post',
                data: {id: id},
                dataType: 'json',
                success: function (data) {
                    parent.layer.close(index);
                    if (data.returnCode != 200) {
                        parent.msgAlert(data.returnMsg);
                    } else {
                        parent.layer.dataFlag =  true;
                        parent.msgAlert(data.returnMsg);
                        active.search();
                    }
                }
            });
        });
    }

</script>

</body>
</html>