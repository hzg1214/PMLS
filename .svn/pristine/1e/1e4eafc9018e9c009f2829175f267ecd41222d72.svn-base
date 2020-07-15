var table;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
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
            {field: 'buildingNoName', title: '楼室号', width: 200, align: 'center'},
            {field: 'roughArea', title: '面积', width: 100, align: 'center'},
            {field: 'roughAmount', title: '表价', width: 100, align: 'center'}
            ,{field: 'inner_total_money', title: '里价', width: 100, align: 'center'}
            ,{field: 'house_type', title: '户型', width: 100, align: 'center'}
        );
        var cols = [];
        cols.push(row);
        return cols;
    }

    reloadData();//初始化加载表格

    var active = {
        reload: function () {
            reloadData();
        }

    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData() {
        var projectNo = trimStr($("#projectNo").val());
        var index = layer.load(2, {shade: 0.1});
        var optionsData = {};
        var buildingNo = trimStr($("#buildingNo").val());
        var oldBuildingNoId = trimStr($("#oldBuildingNoId").val());
        optionsData.buildingNo = buildingNo;
        optionsData.oldBuildingNoId = oldBuildingNoId;

        table.reload('mainTable', {
            url: BASE_PATH + "/sceneTrade/queryBuildingNoByEstateId/" + projectNo,
            cols: setCols(),
            height: window.innerHeight - $(".layui-table-view").offset().top - 20,
            where: optionsData,
            page:{
                curr: 1 //重新从第 1 页开始
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
        parent.msgAlert('请选择！');
        return;
    }
    return {};
}
