
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
        , limits: [10,20,30,50,100]
        , limit: 10 //默认采用60
        , method: 'post'
        , loading:false
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
        var pageType = $("#pageType").val();
        if(pageType=='developer'){
            row1.push(
                {type:'radio'},
                {field: 'developerCode',title: '合作方编号',width:115, align: 'center'},
                {field: 'developerName',title: '合作方',width:260, align: 'left'},
                {field: 'developerBrandName',title: '合作方品牌',width:200, align: 'center'},
                {field: 'isYjDy',title: '是否垫佣甲方',width:120, align: 'center',
                    templet: function (d) {
                        if(d.isYjDy==1){
                            return '是';
                        }else {
                            return '否';
                        }
                    }
                }
            );
        }else {
            row1.push(
                {type:'radio'},
                {field: 'developerCode',title: '开发商编号',width:100, align: 'center'},
                {field: 'developerName',title: '开发商名称',width:240, align: 'left'},
                {field: 'developerBrandName',title: '开发商品牌',width:110, align: 'center'},
                {field: 'bigCustomerFlag',title: '是否大客户',width:120, align: 'center',
                    templet: function (d) {
                        if(d.bigCustomerFlag==22601||d.bigCustomerFlag=='22601'){
                            return '是';
                        }else {
                            return '否';
                        }
                    }
                },
                {field: 'dockingPeo',title: '开发商对接人',width:120, align: 'center'},
                {field: 'dockingPeoTel',title: '开发商对接电话',width:130, align: 'center'}
            );
        }




        var cols = [];
        cols.push(row1);
        return cols;
    }

    reloadData();//初始化加载表格

    var active = {
        reload:function(){
            reloadData();
        },

    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(){
        var index = layer.load(2,{shade: 0.1});
        var optionsData={};

        var developerCode=$("#developerCode").val();
        var developerName=$("#developerName").val();
        var partner = $("#partner").val();
        optionsData.developerCode=developerCode;
        optionsData.developerName=developerName;
        optionsData.partner=partner;

        table.reload('mainTable', {
            url: BASE_PATH + '/developer/getDeveloperList',
            cols:setCols(),
            height: window.innerHeight-$(".layui-table-view").offset().top-10,
            where: optionsData,
            page:{
                curr: 1 //重新从第 1 页开始
            },
            done: function(res, curr, count){
                layer.close(index);
            }
        });
    }

});

function getFormData(){
    var checkStatus = table.checkStatus("mainTable"); //获取选中行状态
    var data = checkStatus.data;  //获取选中行数据
    if(checkStatus.data.length==1){
        return data[0];
    }else {
        var pageType = $("#pageType").val();
        if(pageType=='developer'){
            layer.alert('请选择合作方！',{icon: 2, title:'提示'});
        }else {
            layer.alert('请选择开发商！',{icon: 2, title:'提示'});
        }
        return;
    }
}
