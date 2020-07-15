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
        elem: '#contractListTable'
        ,url:ctx + '/pmlsContacts/q'
        ,where:{storeId:storeId}
        ,cellMinWidth: 150
        ,limit:1000
        ,cols: [[
            // {field:'numbers', width:80, title: '序号',align: 'center'}
            {field:'name',width:200,  title: '姓名',align: 'center'}
            ,{field:'mobilePhone', width:200,  title: '手机号码',align: 'center'}
            ,{field:'email', width:300,  title: '邮箱',align: 'center'}
            ,{field:'userName', width:200,  title: '创建人',align: 'center'}
            ,{title: '操作',align: 'left',templet: function(row) {
                    var showContent='';
                    //showContent+='<a class="layui-btn layui-btn-mini" onclick="showStoreContacts(' + row.id + ')">查看</a>';
                    showContent+='<a class="layui-btn layui-btn-xs" style="width:60px;" onclick="uptStoreContacts(' + row.id + ')">编辑</a>';
                    showContent+='<a class="layui-btn layui-btn-danger layui-btn-xs" style="width:60px;" onclick="delsStoreContacts(' + row.id + ')">删除</a>';
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



//upt
function uptStoreContacts(id){
    parent.layer.open({
        type: 2,
        btn: ['确认', '取消'],
        title: '编辑',
        area: ['500px', '400px'],
        content: ctx + '/pmlsContacts/toEdit/'+id,
        yes: function(index, layero){
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            console.log(formData);
            if(formData!=null){
                parent.layer.close(index);//关闭弹窗

                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/pmlsContacts/update/'+formData.id,
                    type: 'post',
                    data:formData,
                    dataType: 'json',
                    success: function (data) {
                        parent.layer.closeAll();
                        console.log(data);
                        if (data.returnCode != 200){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        }else{
                            parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                window.location.reload();
                            });
                        }
                    }
                });
            }

        }
    });
}

//del
function delsStoreContacts(id){
    parent.layer.confirm("确认删除吗？",{icon: 3, title:'提示'},function(){
        parent.layer.load(2);
        $.ajax({
            url: ctx + '/pmlsContacts/destroy/'+id,
            type: 'post',
            dataType: 'json',
            success: function (data) {
                parent.layer.closeAll();
                console.log(data);
                if (data.returnCode != 200){
                    parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                }else{
                    parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                        parent.layer.closeAll();
                        window.location.reload();
                    });
                }
            }
        });
    });
}

