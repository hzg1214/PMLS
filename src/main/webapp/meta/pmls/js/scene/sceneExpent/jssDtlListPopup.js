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
            {field: 'reportId', title: '订单编号', width: 135, align: 'center'},
            // {field: 'isGlobalControl', title: '垫佣控制规则', width: 115, align: 'center'
            //     , templet: function (d) {
            //         var isGlobalControlStr = "";
            //         if (d.isGlobalControl=='1'){
            //             isGlobalControlStr = '项目总控'
            //         }else if(d.isGlobalControl=='0'){
            //             isGlobalControlStr = '房源单控'
            //         }
            //         return isGlobalControlStr;
            //     }
            // },
            {field: 'customerName', title: '客户姓名', width: 100, align: 'center', style: 'text-align: center'},
            {field: 'projectName', title: '项目', width: 165, align: 'center', style: 'text-align: left'},
            {field: 'buildingNo', title: '楼室号', width: 90, align: 'center', style: 'text-align: center'},
            {field: 'kpAmount', title: '实际开票金额（元）', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'jsStatementType', title: '结算类别', width: 90, align: 'center', style: 'text-align: center'
                ,templet: function (d) {
                    var requestTypeStr = "-";
                    if (isEmpty(d.jsStatementType) || d.jsStatementType == 0) {
                        requestTypeStr = "-";
                    } else if (d.jsStatementType == 1) {
                        requestTypeStr = "返佣";
                    } else if (d.jsStatementType == 2) {
                        requestTypeStr = "垫佣";
                    }
                    return requestTypeStr;
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

    function reloadData() {
        var optionsData = {};
        optionsData.searchKey = trimStr($("#searchKey").val());
        optionsData.jssNo = $("#jssNo").val();
        var index = layer.load(2, {shade: 0.1});
        table.reload('mainTable', {
            url: BASE_PATH + '/sceneExpent/selectJsStatementDtlListCanQk',
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
        parent.msgAlert('请选择请款订单！');
        return;
    }
    return {};
}




