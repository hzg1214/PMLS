layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
	var element = layui.element,
    laydate = layui.laydate,
    form = layui.form,
    table = layui.table,
    layer = layui.layer,
    formSelects = layui.formSelects,
    $ = layui.$;

	initHH();
	function initHH() {
        $(".hh").css('overflow','initial');
        $(".hh").css('white-space','initial');
        $(".hh").css('text-overflow','initial');
    }

    var active = {
        //项目信息变更
        estateInfoChange:function(){
            var id = $("#id").val();
            parent.layer.open({
                type: 2,
                title: '项目信息变更',
                area: ['800px', '750px'],
                content: '/pmlsEstate/popupEdit?id='+id
                ,scrollbar: false
                ,resize:false
                ,btn: ['保存', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        var loadIndex = layer.load(2);
                        $.ajax({
                            url: BASE_PATH + '/pmlsEstate/updatePopupDetail',
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
                    }
                    //end
                }
            });

        },
        //项目发布城市变更
        popupReleaseCity:function(){
            var id = $("#id").val();
            var estateId = $("#estateId").val();
            var cityNo = $("#cityNo").val();
            var cityNm = $("#cityNm").val();
            var url = '/pmlsEstate/popupReleaseCity/' + id + '/' + estateId + '/' + cityNo +'/' + cityNm;
            parent.layer.open({
                type: 2,
                title: '项目发布状态变更',
                area: ['550px', '450px'],
                content: url
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    if(formData!=null) {
                        var loadIndex = layer.load(2);
                        $.ajax({
                            url: BASE_PATH + '/pmlsEstate/releaseCity',
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
                    }
                    //end
                }
            });
        },
        //项目审核
        projectAudit:function(){
            var id = $("#id").val();
            //审核
            parent.layer.open({
                type: 2,
                title: '项目审核',
                area: ['500px', '400px'],
                content: '/pmlsEstate/estateAuditPopup?id='+id
                ,scrollbar: false
                ,resize:false
                ,btn: ['保存', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();

                    if(formData!=null) {
                        var loadIndex = parent.layer.load(2);
                        $.ajax({
                            url: BASE_PATH + '/pmlsEstate/audit/'+formData.id,
                            type: 'post',
                            data:formData,
                            dataType: 'json',
                            success: function (data) {
                                console.log(data);
                                // parent.layer.close(loadIndex);
                                parent.layer.closeAll();
                                if (data.returnCode == -1){
                                    parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                                } else {
                                    if (isEmpty(data.returnValue)) {
                                        parent.layer.confirm('项目审核成功！', {
                                            btn: ['确定'] //可以无限个按钮
                                            , btn3: function (index, layero) {
                                                //按钮【按钮三】的回调
                                            }
                                        }, function (index, layero) {
                                            //按钮【按钮一】的回调
                                            parent.layer.closeAll();
                                            window.location.reload();
                                        });
                                    } else {
                                        var id = data.returnValue;
                                        console.info(id);
                                        parent.layer.confirm('项目审核成功！', {
                                            btn: ['确定', '审核下一个'] //可以无限个按钮
                                            , btn3: function (index, layero) {
                                                //按钮【按钮三】的回调
                                            }
                                        }, function (index, layero) {
                                            //按钮【按钮一】的回调
                                            parent.layer.closeAll();
                                            window.location.reload();
                                        }, function (index) {
                                            //按钮【按钮二】的回调
                                            parent.layer.closeAll();
                                            window.location.href = "/pmlsEstate/" + id + "?type=detail";
                                        });
                                    }
                                    /*parent.layer.alert("项目审核成功！", {icon: 1, title:'提示'},function(){
                                        parent.layer.closeAll();
                                        window.location.reload();
                                        //active['reload'].call(this);
                                    });*/
                                }
                            }
                        });
                    }
                    //end
                }
            });
        },
        backEstateList:function () {
            window.parent.redirectTo('delete');
        },
        addIncomePlan:function () {
            var projectNo = $("#projectNo").val();
            var url = '/pmlsEstatePlan/yjPlanAdd/' + projectNo
            parent.layer.open({
                skin: 'yjPlanBtn2-class',
                type: 2,
                title: '佣金方案维护',
                area: ['860px', '620px'],
                content: url
                ,scrollbar: false
                ,resize:false
                ,btn: ['保存','保存并启用','取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var lnkYjPlanDto = iframeWin.getFormData();
                    lnkYjPlanDto.isEnable = 0;
                    lnkYjPlanDto.isEdit = 1;
                    console.log(lnkYjPlanDto);

                    if(!lnkYjPlanDto){
                        return false;
                    }
                    console.log("success");

                    if(lnkYjPlanDto!=null){
                        var loadIndex = layer.load(2);  //待改2  此行需放开
                        $.ajax({
                            url: BASE_PATH + '/pmlsEstatePlan/saveYjPlanInfo',
                            type: 'POST',
                            contentType: 'application/json;charset=utf-8', //设置请求头信息
                            data: JSON.stringify(lnkYjPlanDto),
                            dataType: "json",
                            success: function (data) {
                                console.log(data);
                                layer.close(loadIndex);   //待改2  此行需放开
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
                    }
                }
                ,btn2: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var lnkYjPlanDto = iframeWin.getFormData();
                    lnkYjPlanDto.isEnable = 1;
                    lnkYjPlanDto.isEdit = 0;
                    console.log(lnkYjPlanDto);

                    if(!lnkYjPlanDto){
                        return false;
                    }
                    console.log("success");

                    if(lnkYjPlanDto!=null){
                        var loadIndex = layer.load(2);  //待改2  此行需放开
                        $.ajax({
                            url: BASE_PATH + '/pmlsEstatePlan/saveYjPlanInfo',
                            type: 'POST',
                            contentType: 'application/json;charset=utf-8', //设置请求头信息
                            data: JSON.stringify(lnkYjPlanDto),
                            dataType: "json",
                            success: function (data) {
                                console.log(data);
                                layer.close(loadIndex);   //待改2  此行需放开
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
                    }
                    return  false;
                }
            });
        },
        prohibitPlan:function () {
            var id = $(this).data('id');
            var type =$(this).data('enabletype');
            var projectNo = $("#projectNo").val();
            var str = "确认要禁用吗？";
            if(type==1){
                str = "确认要禁用吗？"
            }else{
                str = "确认要启用吗？";
            }
            parent.msgConfirm(str, function () {

//            	var url = BASE_PATH + "/pmlsEstate/toProhibit/"+id+"/"+type;
            	
                var url = BASE_PATH + "/pmlsEstatePlan/toIsEnable/"+id+"/"+projectNo+"/"+type;

                $.ajax({
                    url: url,
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
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
        },
        /*editPlan:function () {
            if($(this).data('plannum') > 0){
                parent.layer.alert('该方案已被使用，不能修改',{icon: 2, title:'提示'});
                return;
            }else{
                var id = $(this).data('id');
                var url = '/pmlsEstatePlan/yjPlanEdit/' + id
                parent.layer.open({
                    type: 2,
                    title: '收入方案维护',
                    area: ['550px', '450px'],
                    content: url
                    ,scrollbar: false
                    ,resize:false
                    ,btn: ['确定', '取消']
                    ,yes: function(index, layero){
                        //确认的回调
                        var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                        var formData = iframeWin.getFormData();
                        console.log(formData);
                        if(formData!=null){
                            var loadIndex = layer.load(2);
                            $.ajax({
                                url: BASE_PATH + '/pmlsEstate/editIncomePlan',
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
                        }
                    }
                });
            }
        },*/
        downloadContract:function () {
            var id = $(this).data('id');
        }

        ,cooperationStatus1:function () {
            uptCooperationStatus('1');
        }
        ,cooperationStatus2:function () {
            uptCooperationStatus('2');
        }

    };

    $('.estateDetailPage .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


});


//下载OA附件
function downloadOaFile(url,fileName) {
    var requestUrl=ctx + '/businessManagerController/showOaFile?url='+url+'&fileName='+encodeURI(fileName);
    window.location.href=requestUrl;
}



function editPlanNew(planId,isEdit) {
    if(isEdit != '1'){
        parent.layer.alert('该方案已启用，不能修改',{icon: 2, title:'提示'});
        return;
    }else{
        var url = '/pmlsEstatePlan/yjPlanEdit/' + planId
        parent.layer.open({
            skin: 'yjPlanBtn2-class',
            type: 2,
            title: '佣金方案维护',
            area: ['860px', '620px'],
            content: url
            ,scrollbar: false
            ,resize:false
            ,btn: ['保存','保存并启用','取消']
            ,yes: function(index, layero){
                //确认的回调
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var lnkYjPlanDto = iframeWin.getFormData();
                    lnkYjPlanDto.isEnable = 0;
                    lnkYjPlanDto.isEdit = 1;
                console.log(lnkYjPlanDto);

                if(!lnkYjPlanDto){
                    return false;
                }
                console.log("success");

                if(lnkYjPlanDto!=null){
                    var loadIndex = layer.load(2);  //待改2  此行需放开
                    $.ajax({
                        url: BASE_PATH + '/pmlsEstatePlan/saveYjPlanInfo',
                        type: 'POST',
                        contentType: 'application/json;charset=utf-8', //设置请求头信息
                        data: JSON.stringify(lnkYjPlanDto),
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                            layer.close(loadIndex);   //待改2  此行需放开
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
                }
            }
            ,btn2: function(index, layero){
                //确认的回调
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var lnkYjPlanDto = iframeWin.getFormData();
                    lnkYjPlanDto.isEnable = 1;
                    lnkYjPlanDto.isEdit = 0;
                console.log(lnkYjPlanDto);

                if(!lnkYjPlanDto){
                    return false;
                }
                console.log("success");

                if(lnkYjPlanDto!=null){
                    var loadIndex = layer.load(2);  //待改2  此行需放开
                    $.ajax({
                        url: BASE_PATH + '/pmlsEstatePlan/saveYjPlanInfo',
                        type: 'POST',
                        contentType: 'application/json;charset=utf-8', //设置请求头信息
                        data: JSON.stringify(lnkYjPlanDto),
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                            layer.close(loadIndex);   //待改2  此行需放开
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
                }
                return  false;
            }
        });
    }
}

function uptCooperationStatus(cooperationStatus) {
    console.log("uptCooperationStatus="+cooperationStatus);
    var id = $("#id").val();
    var url = BASE_PATH + '/pmlsEstate/uptCooperationStatus/'+id+'/'+cooperationStatus;


    var loadIndex = layer.load(2);
    $.ajax({
        type: "POST",
        url: url,
        contentType: 'application/json;charset=utf-8', //设置请求头信息
        dataType: "json",
        success: function (data) {
            layer.close(loadIndex);
            if (data.returnCode == -1){
                parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
            } else {
                parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                    parent.layer.closeAll();
                    window.parent.refreshRight();
                });
            }
        },
        error: function () {
            layer.close(loadIndex);
            parent.layer.alert("操作失败", {icon: 2, title:'提示'});
        }
    });
}

