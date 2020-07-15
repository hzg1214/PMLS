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
            {type: 'radio', width: 62},
            {field: 'contractNo', title: '合同编号', width: 150, align: 'center'},
            {field: 'contractName', title: '合同名称', width: 150, align: 'center',
            	templet: function (d) {
                return "<div style='text-align: left'>" + d.contractName + "</div>";
	        	}
	        },
            {
                field: 'dateLifeStart', title: '合作周期', width: 200, align: 'center',
                templet: function (d) {
                    if (d.dateLifeStart == null || d.dateLifeEnd == null) {
                        return "-";
                    } else {
                        return formatDate(d.dateLifeStart, "yyyy-MM-dd")+"至"+formatDate(d.dateLifeEnd, "yyyy-MM-dd");
                    }
                }
            },
	        {field: 'htType', title: '合作类型', width: 100, align: 'center'},
            {field: 'htedition', title: '合作版本', width: 100, align: 'center',
	        	templet: function (d) {
                    if (d.htedition == 28301) {
                        return "PMLS";
                    } else if(d.htedition == 28302){
                        return "房友";
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
        var optionsData = {
            cooperationSelect: "user"
        };

        var contractNo = trimStr($("#contractNo").val());//合同名称
        var htType = $("#htType").val();
        optionsData.contractKey = contractNo;
        optionsData.htType = htType;
        optionsData.approveState = '10403';//审核通过
        optionsData.projectNo = $("#projectNo").val();//项目编号
        optionsData.companyNo = $("#companyNo").val();//经纪公司编号

        table.reload('mainTable', {
            url: BASE_PATH + '/cooperationController/getCooperationList',
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
        layer.alert('请选择分销合同！', {icon: 2, title: '提示'});
        return;
    }
}
