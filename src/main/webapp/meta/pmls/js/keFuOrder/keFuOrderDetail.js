var layer;
var id=window.id;
var jsonDto={};

var form,upload,table;
layui.use(['layer','table'], function() {
    layer = layui.layer;
    table=layui.table;

    setTimeout(function(){
        if(window.keFuOrderJson!=''){
            //jsonDto=eval('(' + window.keFuOrderJson + ')');
            jsonDto=eval('(' + window.keFuOrderJson.replace(/[\r\n]/g,"\\n") + ')');
            //初始化图片

            //初始化加载图片
            loadImageList('fileKeFuOrder',jsonDto.orderFileList);

            $.each(jsonDto.keFuOrderCkDtos,function(i,item){
                loadImageList('fileKeFuOrderReply'+i,item.orderCkFileList);
            });
        }
    },100)

});

//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}


/*function reAlert(id){
    restPost(BASE_PATH + '/keFuOrder/reAlert', {"id":id}, function(data) {
        if(data.returnCode == 200){
            systemLoaded();
            Dialog.alertSuccess();
            $("#reAlert").attr("disabled","disabled");
        }
    }, function(data) {
        Dialog.alertError(data.returnMsg)
        systemLoaded();
    });
}*/


function reAlert(id){
        parent.layer.load(2);
        $.ajax({
            url: ctx + '/pmlsKeFuOrder/reAlert',
            type: 'post',
            dataType: 'json',
            data:{"id":id},
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