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
        , limits: [10, 20, 30, 50, 100]
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
            {field: 'companyNo', title: '公司编号', width: 100, align: 'center'},
            {field: 'companyName', title: '公司名称', width: 200, align: 'center'},
            {field: 'address', title: '公司地址', width: 260, align: 'center'},
            {
                field: 'topbrandName', title: '渠道', width: 155, align: 'center', templet: function (d) {
                    if (isEmpty(d.topbrandName)) {
                        return "房友";
                    } else {
                        return d.topbrandName;
                    }
                }
            }
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }

    reloadData();//初始化加载表格

    var active = {
        reload: function () {
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
        var companyName = trimStr($("#companyName").val());
        optionsData.companyNo = companyNo;
        optionsData.companyName = companyName;
        var estateId = $("#estateId").val();
        if (!isEmpty(estateId)) {
            optionsData.estateId = estateId;
        }

        table.reload('mainTable', {
            // url: BASE_PATH + '/businessManagerController/getBusinessManagerList',
            url: BASE_PATH + '/pmlsFrameContract/getFrameContractCompanyList',
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
    var checkStatus = table.checkStatus("mainTable"); //获取选中行状态
    var data = checkStatus.data;  //获取选中行数据
    if (checkStatus.data.length == 1) {
        return data[0];
    } else {
        parent.layer.alert('请选择经纪公司！', {icon: 2, title: '提示'});
        return;
    }
    return {};
}
