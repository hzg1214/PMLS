layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    var toAllocate = NullToZero($("#toAllocate").val());
    var userId = NullToEmpty($("#userId").val());

    init();

    function init() {

        // 开始日期
        laydate.render({
            elem: '#dateStart',
            type: 'date',
            format: 'yyyy-MM-dd',
            trigger: 'click',
            done: function (value, date, endDate) {
                var startDate = new Date(value).getTime();
                if ($('#dateEnd').val() != '') {
                    var endTime = new Date($('#dateEnd').val()).getTime();
                    if (endTime < startDate) {
                        layer.msg('开始时间不能大于结束时间');
                        $('#dateStart').val('');
                    }
                }
            }
        });

        // 结束日期
        laydate.render({
            elem: '#dateEnd',
            type: 'date',
            format: 'yyyy-MM-dd',
            trigger: 'click',
            done: function (value, date, endDate) {
                if ($('#dateStart').val() != "") {
                    var startDate = new Date($('#dateStart').val()).getTime();
                    var endTime = new Date(value).getTime();
                    if (endTime < startDate) {
                        layer.msg('结束时间不能小于开始时间');
                        $('#dateEnd').val('');
                    }
                }
            }
        });

        tableRender();

        reloadData();
    }

    function tableRender() {
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            , id: 'contentReload'
            , data: []
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
            , response: {
                statusName: 'returnCode' //数据状态的字段名称，默认：code
                , statusCode: 200 //成功的状态码，默认：0
                , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                , countName: 'totalCount' //数据总数的字段名称，默认：count
                , dataName: 'returnData' //数据列表的字段名称，默认：data
            }
        });

    }

    function setCols() {
        var row = [];
        row.push(
            {
                field: 'skSerialNo', fixed: true, title: '收入编号', width: 150, align: 'center'
                , templet: function (d) {
                    return "<a class='layui-table-link' onclick='javascript:skStatementList.showDetail(\"" + d.id + "\")'>" + d.skSerialNo + "</a>";
                }
            },
            {field: 'oaNo', title: 'OA编号', width: 150, align: 'center', style: 'text-align: left'},
            {field: 'skTypeName', title: '类型', width: 150, align: 'center'},
            {field: 'khName', title: '考核主体', width: 150, align: 'center'},
            {field: 'projectNo', title: '项目编号', width: 120, align: 'center' },
            {field: 'projectName', title: '项目名称', width: 180, align: 'center', style: 'text-align: left'},
            {field: 'customerName', title: '客户', width: 250, align: 'center', style: 'text-align: left'},
            {
                field: 'recordDate', title: '入账日期', width: 120, align: 'center',
                templet: function (d) {
                    if (d.recordDate == null) {
                        return "-";
                    } else {
                        return formatDate(d.recordDate, "yyyy-MM-dd");
                    }
                }
            },
            {
                field: 'skAmount_bef', title: '收款金额', width: 160, align: 'center', style: 'text-align: right'
                , templet: function (d) {
                    return formatMoney(d.skAmount_bef);
                }
            },
            {
                field: 'allocatedAmount_bef', title: '已拆分金额', width: 160, align: 'center', style: 'text-align: right'
                , templet: function (d) {
                    return formatMoney(d.allocatedAmount_bef);
                }
            },
            {
                field: 'stayAmount_bef', title: '待拆分金额', width: 160, align: 'center', style: 'text-align: right'
                , templet: function (d) {
                    return formatMoney(d.stayAmount_bef);
                }
            },
            {
                field: 'dateCreate', title: '创建日期', width: 120, align: 'center',
                templet: function (d) {
                    if (d.dateCreate == null) {
                        return "-";
                    } else {
                        return formatDate(d.dateCreate, "yyyy-MM-dd");
                    }
                }
            },
            {
                title: '操作', fixed: 'right', align: 'center', style: 'text-align: left', width: 110,
                templet: function (d) {
                    var ret = "<a class='layui-btn layui-btn-xs' onclick='javascript:skStatementList.showDetail(\"" + d.id + "\")'>查看</a>";
                    // 尚未拆分完毕 且 待拆分金额 >0  且 未关账
                    if (d.allocatedFlag != -1 && d.stayAmount_bef != 0 && d.switchFlag == 0) {
                        if (toAllocate == "1" || d.userIdCreate == userId) {
                            ret += "<a class='layui-btn layui-btn-normal layui-btn-xs' onclick='javascript:skStatementList.allocate(\"" + d.id + "\")'>拆分</a>";
                        }
                    }
                    return ret;
                }
            }
        );

        var cols = [];
        cols.push(row);
        return cols;
    }


    active = {
        reload: function () {
            var optionsData = {};
            optionsData.oaNo = trimStr($("#oaNo").val());
            optionsData.khCode = trimStr($("#khCode").val());
            optionsData.projectNo = trimStr($("#projectNo").val());
            optionsData.customerName = trimStr($("#customerName").val());
            optionsData.dateStart = $("#dateStart").val();
            optionsData.dateEnd = $("#dateEnd").val();
            sessionStorage.removeItem('PMLS_SK_STATEMENT_SERVICE_SEARCH');
            reloadData(optionsData);
        },
        reset: function () {
            $("#oaNo").val("");
            $("#khCode").val("");
            $("#projectNo").val("");
            $("#customerName").val("");
            $("#dateStart").val("");
            $("#dateEnd").val("");
            active.reload();
        }
    };


    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index = layer.load(2);
        var sessionData = sessionStorage.getItem('PMLS_SK_STATEMENT_SERVICE_SEARCH');
        var pageIndex = 1;
        if (sessionData != null && sessionData != '') {
            optionsData = JSON.parse(sessionData);
            pageIndex = optionsData.curr;
            $("#oaNo").val(optionsData.oaNo);
            $("#khCode").val(optionsData.khCode);
            $("#projectNo").val(optionsData.projectNo);
            $("#customerName").val(optionsData.customerName);
            $("#dateStart").val(optionsData.dateStart);
            $("#dateEnd").val(optionsData.dateEnd);
        }

        table.reload('contentReload', {
            url: BASE_PATH + '/skStatement/queryList',
            cols: setCols(),
            where: optionsData,
            height: window.innerHeight - $(".layui-table-view").offset().top - 10,
            page: {
                curr: pageIndex //重新从第 1 页开始
            },
            done: function (res, curr, count) {
                layer.close(index);
                if (!optionsData) {
                    optionsData = {};
                }
                optionsData.curr = curr;
                sessionStorage.setItem("PMLS_SK_STATEMENT_SERVICE_SEARCH", JSON.stringify(optionsData));
            }
        });
    }

});

var skStatementList = {}

skStatementList.showDetail = function (id) {
    var url = BASE_PATH + "/skStatement/" + id + "/view";
    parent.redirectTo('append', url, '收入拆分');
}

skStatementList.allocate = function (id) {

    // 拆分前校验
    var ckeckurl = BASE_PATH + "/skStatement/" + id + "/allocateCheck";
    var loadIndex = parent.layer.load(2, {shade: [0.1]});

    $.ajax({
        type: "GET",
        url: ckeckurl,
        async: false,
        dataType: "json",
        contentType: 'application/json',
        success: function (data) {
            parent.layer.close(loadIndex);
            if (data.returnCode == '200') {
                var url = BASE_PATH + "/skStatement/" + id + "/allocate";
                parent.redirectTo('append', url, '收入拆分');
            } else {
                parent.msgAlert(retMsg);
            }
        },
        error: function () {
            parent.layer.close(loadIndex);
            parent.msgAlert("操作发生异常，请联系管理人员！");
        }
    });

}