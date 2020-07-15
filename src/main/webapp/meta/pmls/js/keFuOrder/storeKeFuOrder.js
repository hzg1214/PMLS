var active;
var layer;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        $ = layui.$;
        layer = layui.layer;

//初始化管理员表格
    var storeId = $("#storeId").val();
    table.render({
        elem: '#keFuOrderListTable'
        ,url:ctx + '/pmlsKeFuOrder/getKeFuOrderListByStoreId/'+storeId
        ,where:{}
        ,cellMinWidth: 150
        ,limit:1000
        ,cols: [[
            // {field:'numbers', width:80, title: '序号',align: 'center'}
            {field:'orderNo',width:150,  title: '工单编号',align: 'center'}
            ,{field:'categoryOneNm', width:150,  title: '一级分类',align: 'center'}
            ,{field:'categoryTwoNm', width:150,  title: '二级分类',align: 'center'}
            ,{field:'userName', width:150,  title: '经办人',align: 'center'}
            ,{field:'createName', width:150,  title: '创建人',align: 'center'}
            , {field: 'dateCreate', width: 150, title: '创建日期', align: 'center'
                ,templet: function (d) {
                    if (d.dateCreate == null || d.dateCreate == "") {
                        return "";
                    } else {
                        return formatDate(d.dateCreate, "yyyy-MM-dd");
                    }
                }
            }
            ,{field:'dealStatusNm', width:150,  title: '问题状态',align: 'center'}
            ,{field:'checkStatusNm', width:150,  title: '核验状态',align: 'center'}
            ,{title: '操作',align: 'left',templet: function(row) {
                    var showContent='';
                    showContent+='<a class="layui-btn layui-btn-xs" onclick="showStoreKeFuOrder(' + row.id + ')">查看</a>';
                    return showContent; //列渲染
                }}
        ]]
        , even: false //开启隔行背景
        , page: false
        , method: 'post'
        ,loading:true
        ,request: {
            pageName: 'curPage' //页码的参数名称，默认：page
            ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
        }
        ,response: {
            statusName: 'returnCode' //数据状态的字段名称，默认：code
            ,statusCode: 200 //成功的状态码，默认：0
            ,msgName: 'returnMsg' //状态信息的字段名称，默认：msg
            ,countName: 'totalCount' //数据总数的字段名称，默认：count
            ,dataName: 'returnData' //数据列表的字段名称，默认：data
        }
    });
});



//show
function showStoreKeFuOrder(id){
    var url=ctx+"/pmlsKeFuOrder/getKeFuOrderById/"+id;
    parent.redirectTo('append',url,'工单详情');
}
