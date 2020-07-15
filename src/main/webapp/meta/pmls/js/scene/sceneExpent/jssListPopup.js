var active;
var layer;
var table;
layui.use(['element', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        form = layui.form,
        $ = layui.$;
    layer = layui.layer;
    table = layui.table;
    init();

    function init() {
        form.render('select'); //刷新select选择框渲染
        tableRender();
        reloadData();
    }

    function tableRender() {
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

    }

    function setCols() {
        var row = [];
        row.push(
            {type: 'radio'},
            {field: 'jssNo', title: '结算书编号', width: 150, align: 'center'},
            {field: 'companyName', title: '渠道公司', width: 250, align: 'center', style: 'text-align: left'},
            {field: 'projectName', title: '项目名称', width: 160, align: 'center', style: 'text-align: left'},
            {
                field: 'contractYdTotalAmount',
                title: '合同约定金额（元）',
                width: 160,
                align: 'center',
                style: 'text-align: right'
                ,
                templet: function (d) {
                    var contractYdTotalAmount = d.contractYdTotalAmount;
                    return formatMoney(contractYdTotalAmount)
                }
            },
            {
                field: 'jsTotalAmount', title: '本次结算金额（元）', width: 160, align: 'center', style: 'text-align: right'
                , templet: function (d) {
                    return formatMoney(d.jsTotalAmount);
                }
            },
            {
                field: 'kpTotalAmount', title: '实际开票金额（元）', width: 160, align: 'center', style: 'text-align: right'
                , templet: function (d) {
                    return formatMoney(d.kpTotalAmount);
                }
            }
        );
        var cols = [];
        cols.push(row);
        return cols;
    }


    active = {
        reload: function () {
            reloadData();
        }
    };


    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index = layer.load(2, {shade: 0.1});

        var optionsData = {};
        optionsData.companyNo = trimStr($("#companyNo").val());
        optionsData.projectNo = trimStr($("#projectNo").val());
        optionsData.approveStatus = '10403';
        optionsData.canQk         = 'qk';

        table.reload('mainTable', {
            url: BASE_PATH + '/jsStatement/queryList',
            cols: setCols(),
            where: optionsData,
            height: window.innerHeight - $(".layui-table-view").offset().top - 10,
            page: {
                curr: 1
            },
            done: function (res, curr, count) {
                layer.close(index);
            }
        });
    }
});


function getFormData() {
    var checkStatus = table.checkStatus("mainTable"); //获取选中行状态
    var data = checkStatus.data;  //获取选中行数据
    if (checkStatus.data.length == 1) {
        return data[0];
    } else {
        parent.msgAlert('请选择结算书！');
        return;
    }
    return {};
}




