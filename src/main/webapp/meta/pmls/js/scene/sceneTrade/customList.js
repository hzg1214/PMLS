layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;

    init();

    function init() {

        form.render('select'); // 刷新单选框渲染

        tableRender();

        reloadData();
    }

    function tableRender() {
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            //, url: BASE_PATH + '/sceneTrade/queryCustomLegList'
            , id: 'contentReload'
            , height: "full"
            , page: true
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
        var row = [
            {type: 'numbers', title: '序号', width: 60},
            {field: 'customerNm', title: '客户', width: 180, align: 'center'},
            {field: 'customerTel', title: '客户手机', width: 150, align: 'center'},
            {field: 'registerCnt', title: '报备楼盘数', width: 150, align: 'center'},
            {field: 'succSaleCnt', title: '成交楼盘数', width: 150, align: 'center'},
            {field: 'vaild', title: '客户有效性', width: 100, align: 'center'},
            {
                title: '操作', align: 'center', width: 100,
                templet: function (d) {
                    return "<a class='layui-btn layui-btn-xs' onclick='javascript:customList.showDetail(\"" + d.customerTel + "\",\"" + d.customerNm + "\",\"" + d.registerCnt + "\",\"" + d.succSaleCnt + "\",\"" + d.vaild + "\")'>查看</a>";
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }


    var active = {
        reload: function () {
            var optionsData = {};
            optionsData.customerNm = trimStr($("#customerNm").val());
            optionsData.vaild = trimStr($("#vaild").val());
            sessionStorage.removeItem('PMLS_SCENE_TRADE_CUSTOM_SEARCH');
            reloadData(optionsData);
        },
        reset: function () {
            $("#customerNm").val("");
            $("#vaild").val("");
            form.render('select'); // 刷新单选框渲染
            active.reload();
        }
    };


    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index = layer.load(2);

        var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_CUSTOM_SEARCH');
        var pageIndex = 1;
        if (sessionData != null && sessionData != '') {
            optionsData = JSON.parse(sessionData);
            pageIndex = optionsData.curr;
            $("#customerNm").val(optionsData.customerNm);
            $("#vaild").val(optionsData.vaild);
            form.render('select'); // 刷新单选框渲染
        }

        table.reload('contentReload', {
            url: BASE_PATH + '/sceneTrade/queryCustomLegList',
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
                sessionStorage.setItem("PMLS_SCENE_TRADE_CUSTOM_SEARCH", JSON.stringify(optionsData));
            }
        });
    }

    function valid() {
        return true;
    }


});

var customList = {}

customList.showDetail = function (customerTel, customerNm, registerCnt, succSaleCnt, vaild) {
    var url = BASE_PATH + "/sceneTrade/customDetail/" + customerTel + "/" + customerNm + "/" + registerCnt + "/" + succSaleCnt + "/" + vaild
    parent.redirectTo('append', url, '客户详情');
}