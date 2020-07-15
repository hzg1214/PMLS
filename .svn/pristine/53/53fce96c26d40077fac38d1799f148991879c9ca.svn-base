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
        , loading : false
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
        var row1 = [];
        row1.push(
            {type: 'radio', width: 80},
            {field: 'maintainer', title: '人员编号', width: 155, align: 'center'},
            {field: 'maintainerName', title: '人员姓名', width: 200, align: 'center'},
            {field: 'centerName', title: '所属中心',  align: 'center'}
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
        var optionsData = {
//            cooperationSelect: "user"
        };

        var storeCenterId = $("#storeCenterId").val();
        var storeId = $("#storeId").val();
        var acCityNo = $("#acCityNo").val();
        var maintainerName = trimStr($("#maintainerName").val());//人员编号
        if(isIncludeBlank(maintainerName)){
        	parent.layer.alert('维护人姓名、工号中间有空格，请重新输入！', {
        		icon: 2,
        		title: '提示'
        	});
        	return;
        }
        optionsData.maintainerName = maintainerName;
        optionsData.storeCenterId = storeCenterId;
        optionsData.storeId = storeId;
        optionsData.acCityNo = acCityNo;

        table.reload('mainTable', {
        	url: BASE_PATH + '/pmlsStore/queryStoreUser',
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
        layer.alert('请选择维护人！', {icon: 2, title: '提示'});
        return;
    }
}
