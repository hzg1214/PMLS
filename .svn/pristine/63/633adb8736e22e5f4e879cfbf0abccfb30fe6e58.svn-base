
var $;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
        $ = layui.$;

    //初始化附件预览
    //initViewer('uploadContractImg');
    //initViewer('uploadOtherImg');
    var uploadContractImgList=[],uploadOtherImgList=[];
    if(cooperationInfo!=null && cooperationInfo.fileList!=null && cooperationInfo.fileList.length>0){
        for(var i=0;i<cooperationInfo.fileList.length;i++){
            if(cooperationInfo.fileList[i].fileTypeId=='1067'){
                uploadContractImgList.push(cooperationInfo.fileList[i]);
            }else if(cooperationInfo.fileList[i].fileTypeId=='1068'){
                uploadOtherImgList.push(cooperationInfo.fileList[i]);
            }
        }
    }
    //初始化加载图片
    loadImageList('uploadContractImg',uploadContractImgList,false);
    loadImageList('uploadOtherImg',uploadOtherImgList,false);

    var active = {
        //
        startOAaudit:function(){
            var id = $("#id").val();
            //发起OA申请

            window.parent.layer.confirm('确认发起OA申请？',{icon: 3, title:'提示'}, function(index){
                window.parent.layer.close(index);
                var loadIndex = layer.load(2);
                var formData = {
                    id:id
                };
                $.ajax({
                    url: BASE_PATH + '/cooperationController/sendOACooperation',
                    type: 'post',
                    data:formData,
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        layer.close(loadIndex);
                        if (data.returnCode == -1){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                window.parent.refreshRight();
                            });
                        }
                    }
                });
            });
            //sendOACooperation
        },
        backCooperationList:function () {
            window.parent.redirectTo('delete');
        }
    };

    $('.cooperationPage .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function initViewer(elemId) {
        setTimeout(function () {
            let node = document.getElementById(elemId);
            let domObj = node.getElementsByClassName("upload_img_list")[0];
            var viewer = new Viewer(domObj, {
                url: 'data-original',
                show: function () {// 动态加载图片后，更新实例
                    viewer.update();
                },
            });
        }, 100);
    }

});
