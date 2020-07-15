
var layout;
var layer;
layui.use(['tree', 'layer','table', 'form'], function() {
    layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;
    var form = layui.form;

    var addPermission = NullToZero($("#addPermission").val());
    var uptPermission = NullToZero($("#uptPermission").val());
    var delPermission = NullToZero($("#delPermission").val());

    layout = [
        { name: '渠道品牌编号',field: 'brandCode', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 15%;text-align:center;' },
        { name: '渠道品牌名称',field: 'brandName', treeNodes: true, headerClass: 'value_col', colClass: 'value_col', style: 'width: 40%'},
        // { name: '备注',field: 'remark', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 20%' },
        { name: '创建人',field: 'createUserName', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 10%;text-align:center;' },
        { name: '创建日期',field: 'dateCreate', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 10%;text-align:center;' },
        {
            name: '操作',
            headerClass: 'value_col',
            colClass: 'value_col',
            style: 'width: 25%',
            render: function (row) {
                var showContent = '';
                if (addPermission == "1") {
                    showContent += '<a class="layui-btn layui-btn-mini" style="width:60px;" onclick="add(' + row.id + ')">添加下级</a>';
                }
                if (uptPermission == "1") {
                    showContent += '<a class="layui-btn layui-btn-normal layui-btn-mini" style="width:60px;" onclick="update(' + row.id + ')">编辑</a>';
                }
                if (delPermission == "1") {
                    showContent += '<a class="layui-btn layui-btn-danger layui-btn-mini" style="width:60px;" onclick="del(' + row.id + ')">删除</a>';
                }
                return showContent; //列渲染
            }
        },
    ];
    reloadTable({});

    var active = {
        addParentMenu: function () {
            add(0);
        },
        reload:function () {
            var optionsData={};
            var brandCode=$("#brandCode").val();
            var brandName=$("#brandName").val();
            optionsData.brandCode=brandCode;
            optionsData.brandName=brandName;
            console.log(optionsData);
            reloadTable(optionsData);
        },
        reset:function () {
            $("#brandCode").val('');
            $("#brandName").val('');
            active['reload'].call(this);
        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});

function reloadTable(optionsData){
    parent.layer.load(2);
    $.ajax({
        url: ctx + '/channelBrandController/getChannelBrandList',
        type: 'post',
        dataType: 'json',
        data:optionsData,
        success: function (data) {
            parent.layer.closeAll();
            console.log(data);
            if(data.returnCode=='200'){
                loadTable(data.returnData);
            }else{
                parent.layer.alert('数据加载失败');
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}

//刷新数据
function loadTable(data){
    //重置div
    $(".treeTable").html('<div id=\"menuTable\"></div>');
    layui.treeGird({
        elem: '#menuTable', //传入元素选择器
        nodes: data,
        layout: layout,
    });

    for(var i=0;i<data.length;i++){
        var elem=$("#menuTable tr[id="+data[i].id+"] td")[1];
        $(elem).addClass('fontWeight');
    }
}
//添加
function add(id){
    parent.layer.open({
        type: 2,
        btn: ['确认', '取消'],
        title: '新建渠道品牌',
        area: ['500px', '400px'],
        content: ctx + '/channelBrandController/addBrandPage?parentId='+id,
        yes: function(index, layero){
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            console.log(formData);
            if(formData!=null){
                parent.layer.close(index);//关闭弹窗

                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/channelBrandController/addBrand',
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
                                reloadTable();
                            });
                        }
                    }
                });
            }

        }
    });
}
//修改
function update(id){
    parent.layer.open({
        type: 2,
        btn: ['确认', '取消'],
        title: '编辑',
        area: ['500px', '400px'],
        content: ctx + '/channelBrandController/addBrandPage?id='+id,
        yes: function(index, layero){
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            console.log(formData);
            if(formData!=null){
                parent.layer.close(index);//关闭弹窗
                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/channelBrandController/updateBrand',
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
                                reloadTable();
                            });
                        }
                    }
                });
            }

        }
    });
}
//删除
function del(id){
    parent.layer.confirm("确认删除当前及子节点数据吗？",{icon: 3, title:'提示'},function(){
        parent.layer.load(2);
        $.ajax({
            url: ctx + '/channelBrandController/deleteBrand',
            type: 'post',
            data:{id:id},
            dataType: 'json',
            success: function (data) {
                parent.layer.closeAll();
                console.log(data);
                if (data.returnCode != 200){
                    parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                }else{
                    parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                        parent.layer.closeAll();
                        reloadTable();
                    });
                }
            }
        });
    });
}
