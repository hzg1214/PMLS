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
            {field: 'reportNo', title: '报备编号', width: 150, align: 'center'},
            {field: 'customerNm', title: '客户姓名', width: 90, align: 'center'},
            {
                field: 'EstateNm', title: '项目', width: 140, align: 'center', templet: function (d) {
                    return "<div style='text-align: left'>" + d.EstateNm + "</div>";
                }
            },
            {
                field: 'buildingNo', title: '楼室号', width: 94, align: 'center', templet: function (d) {
                    return "<div style='text-align: left'>" + d.EstateNm + "</div>";
                }
            },
            {
                field: 'roughAmount', title: '大定总金额(元)', width: 130, align: 'center', templet: function (d) {
                    var roughAmount = d.roughAmount;
                    if (isEmpty(roughAmount)) {
                        roughAmount = "-"
                    } else {
                        roughAmount = formatCurrency(roughAmount)
                    }
                    return "<div style='text-align: right'>" + roughAmount + "</div>";
                }
            },
            {
                field: 'dealAmount', title: '成销总金额(元)', width: 130, align: 'center', templet: function (d) {
                    var dealAmount = d.dealAmount;
                    if (isEmpty(dealAmount)) {
                        dealAmount = "-"
                    } else {
                        dealAmount = formatCurrency(dealAmount)
                    }

                    return "<div style='text-align: right'>" + dealAmount + "</div>";
                }
            }
        );
        var cols = [];
        cols.push(row);
        return cols;
    }

    reloadData();//初始化加载表格

    var active = {
        search: function () {
            reloadData();
        },

    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData() {
        var index = layer.load(2, {shade: 0.1});
        var optionsData = {};

        var companyNo = trimStr($("#companyNo").val());
        var reportNoList = trimStr($("#reportNoList").val());
        var searchKey = trimStr($("#searchKey").val());
        optionsData.companyNo = companyNo;
        optionsData.reportNoList = reportNoList;
        optionsData.searchKey = searchKey;

        table.reload('mainTable', {
            url: BASE_PATH + '/sceneExpent/getOffsetInfoList/q',
            cols: setCols(),
            height: window.innerHeight - $(".layui-table-view").offset().top - 10,
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
        parent.msgAlert('请选择冲抵房源！');
        return;
    }
    return {};
}