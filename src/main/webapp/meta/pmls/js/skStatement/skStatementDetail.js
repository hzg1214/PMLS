/**
 * Created by haidan on 2020/7/2.
 */
var active;
layui.use(['layer', 'table'], function () {
    var table = layui.table,
        layer = layui.layer;

    var toCancel = NullToZero($("#toCancel").val());
    var userId = NullToEmpty($("#userId").val());
    var skSerialNo = $("#skSerialNo").val()
    //拆分记录
    table.render({
        elem: '#allocateRecordTable'
        , cols: setCols()
        , id: 'allocateRecordTable'
        , url: BASE_PATH + '/skAllocate/recordList'
        , even: false
        , page: true
        , height: "full"
        , limits: [10, 20, 30]
        , limit: 10 //默认采用60
        , method: 'post'
        , loading: false
        , request: {
            pageName: 'curPage' //页码的参数名称，默认：page
            , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
        }
        , where: {
            skSerialNo: skSerialNo
        }
        , response: {
            statusName: 'returnCode' //数据状态的字段名称，默认：code
            , statusCode: 200 //成功的状态码，默认：0
            , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
            , countName: 'totalCount' //数据总数的字段名称，默认：count
            , dataName: 'returnData' //数据列表的字段名称，默认：data
        }
    });

    //日志
    table.render({
        elem: '#allocateLogTable'
        , cols: setLogCols()
        , id: 'allocateLogTable'
        , url: BASE_PATH + '/skAllocate/logList'
        , even: false
        , page: true
        , height: "full"
        , limits: [10, 20, 30]
        , limit: 10 //默认采用60
        , method: 'post'
        , loading: false
        , request: {
            pageName: 'curPage' //页码的参数名称，默认：page
            , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
        }
        , where: {
            skSerialNo: skSerialNo
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
            {field: 'serialNo', title: '编号', width: 200, align: 'center'},
            {
                field: 'amount_bef', title: '本次拆分金额（元）', width: 200, align: 'center', style: 'text-align: right'
                , templet: function (d) {
                    var saleAmount = isEmpty(d.amount_bef) ? "0.00" : formatMoney(d.amount_bef);
                    return saleAmount;
                }
            },
            {field: 'userNameCreate', title: '操作人', width: 150, align: 'center'},
            {
                field: 'dateUpdate', title: '操作时间', width: 180, align: 'center',
                templet: function (d) {
                    if (d.dateUpdate == null) {
                        return "";
                    } else {
                        return formatDate(d.dateUpdate, "yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {
                title: '操作', width: 200, align: 'center', style: 'text-align: left', fixed: 'right',
                templet: function (row) {
                    var showContent = '';
                    showContent += '<a class="layui-btn layui-btn-xs" onclick="javascript:skStatementDetail.showDetail(' + row.id + ')">查看</a>';
                    if (toCancel == "1" || row.userIdCreate == userId) {
                        showContent += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="javascript:skStatementDetail.cancelAll(' + row.id + ')">整单撤销</a>';
                    }
                    return showContent; //列渲染
                }
            }
        );
        var cols = [];
        cols.push(row);
        return cols;
    }

    function setLogCols() {
        var row = [];
        row.push(
            {type: 'numbers', title: '序号', width: 60, align: 'center'},
            {field: 'logMsg', title: '描述', width: 570, align: 'center', style: 'text-align: left'},
            {field: 'userNameCreate', title: '操作人', width: 120, align: 'center'},
            {
                field: 'dateCreate', title: '操作时间', width: 180, align: 'center',
                templet: function (d) {
                    if (d.dateCreate == null || d.dateCreate == "") {
                        return "";
                    } else {
                        return formatDate(d.dateCreate, "yyyy-MM-dd hh:mm:ss");
                    }
                }
            }
        );
        var cols = [];
        cols.push(row);
        return cols;
    }

    active = {
        goback: function () {
            parent.redirectTo('delete', null, null);
        }
    }

    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

});

var skStatementDetail = {}

//查看
skStatementDetail.showDetail = function (id) {
    parent.layer.dataFlag = false;
    parent.layer.open({
        type: 2,
        btn: [],
        title: '收入拆分明细',
        area: ['820px', '630px'],
        content: ctx + '/skAllocate/showDetail/' + id
        , scrollbar: false
        , resize: false
        ,end : function(){
            if(parent.layer.dataFlag){
                window.location.reload();
            }
        }
    });
}

//整单撤销
skStatementDetail.cancelAll = function (id) {
    parent.layer.confirm("确认整单撤销吗？", {icon: 3, title: '提示'}, function () {
        parent.layer.load(2);
        $.ajax({
            url: ctx + '/skAllocate/cancelAll',
            type: 'post',
            data: {id: id},
            dataType: 'json',
            success: function (data) {
                parent.layer.closeAll();
                if (data.returnCode != 200) {
                    parent.msgAlert(data.returnMsg);
                } else {
                    parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                        window.location.reload();
                    });
                }
            }
        });
    });
}
skStatementDetail.reloadFlag = false;
