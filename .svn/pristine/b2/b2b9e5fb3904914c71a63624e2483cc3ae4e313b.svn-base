layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;

    var defaultUrl = BASE_PATH + '/sceneTrade/custom'

    init();

    function init() {
        tableRender();
    }

    function tableRender() {
        table.render({
            elem: '#contentTable'
            , cols: setCols()
            , url: BASE_PATH + '/sceneTrade/queryCustomDetList'
            , id: 'contentReload'
            , height: "full"
            , page: false
            , loading: false
            , limit:1000
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            , where: {
                customerNm: $("#customerNm").val(),
                customerTel: $("#customerTel").val()
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
        var row = [
            {type: 'numbers', title: '序号', width: 60},
            {
                field: 'estateNm', title: '楼盘名称', width: 180, align: 'center'
                , templet: function (d) {
                    return "<div style='text-align: left'>" + d.estateNm + "</div>";
                }
            },
            {field: 'progressNm', title: '最新进度', width: 120, align: 'center'},
            {field: 'confirmStatusNm', title: '状态', width: 150, align: 'center'},
            {
                field: 'companyNm', title: '经纪公司', minwidth: 240, align: 'center'
                , templet: function (d) {
                    return "<div style='text-align: left'>" + d.companyNm + "</div>";
                }
            },
            {
                field: 'bizOperDate', title: '最新跟进时间', width: 160, align: 'center',
                templet: function (d) {
                    if (d.bizOperDate == null) {
                        return "-";
                    } else {
                        return formatDate(d.bizOperDate, "yyyy-MM-dd");
                    }
                }
            },
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }

    var active = {
        goback: function () {
            parent.redirectTo('delete', null, null);
        }
    }

    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });


});