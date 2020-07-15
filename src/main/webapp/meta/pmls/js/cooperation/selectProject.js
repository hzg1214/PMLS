
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
        row1.push(
            {type:'radio'},
            {field: 'projectNo',title: '项目编号',width:150, align: 'center'},
            {field: 'estateNm',title: '楼盘名称',width:200, align: 'left'},
            {field: 'realityCityNm',title: '楼盘城市',width:100, align: 'center'
                ,templet: function(d){
                    var str = "";
                    if(d.estatePosition==0){
                        str = d.realityCityNm;
                    }
                    if(d.estatePosition==1){
                        str = d.countryNm;
                    }
                    return str;
                }
            },
            {field: 'address',title: '楼盘地址',width:250, align: 'left'
                ,templet: function(d){
                    return d.realityCityNm + d.districtNm + d.address;
                }
            }
        );
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
        var optionsData={auditStatus:'12903',fxPd:'yes'};

        var estateNm = $("#estateNm").val();
        optionsData.estateNm = estateNm;

        table.reload('mainTable', {
            url: BASE_PATH + '/pmlsEstate/list',
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
    }else{
        layer.alert('请选择合作项目！',{icon: 2, title:'提示'});
        return;
    }
    return {};
}
