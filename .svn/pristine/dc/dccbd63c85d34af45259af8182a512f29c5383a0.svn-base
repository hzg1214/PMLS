/*
 * 框架协议js
 * */
var layer;
var id=window.id;
var jsonDto={};
var treeObj;
var form,upload,laydate;
var selectBusinessInfo;//选择的渠道信息
var selectBankInfo;//选择的开户信息

layui.use(['tree', 'layer', 'form','upload','laydate'], function() {
    layer = layui.layer,
        $ = layui.jquery;
    form = layui.form;
    upload = layui.upload;
    laydate=layui.laydate;

    laydate.render({
        elem: '#dateLifeStart',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if($('#dateLifeEnd').val()!=''){
                var endTime = new Date($('#dateLifeEnd').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#dateLifeStart').val('');
                }
            }
        }
    });
    laydate.render({
        elem: '#dateLifeEnd',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            if($('#dateLifeStart').val()!=""){
                var startDate = new Date($('#dateLifeStart').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#dateLifeEnd').val('');
                }
            }
        }
    });
    laydate.render({
        elem: '#signDate',
        format: 'yyyy-MM-dd',
        trigger: 'click'
    });
    //从渠道详情过来的新增框架协议
    if(window.businessDto!=null && window.businessDto!=''){
        //selectBusinessInfo=eval('(' + window.businessDto + ')');
        selectBusinessInfo=eval('(' + window.businessDto.replace(/[\r\n]/g,"\\n") + ')');
        $("#companyNo").val(selectBusinessInfo.companyNo);
        $("#companyName").val(selectBusinessInfo.companyName);
        $(".selectBusinessBtn").hide();//隐藏选择渠道按钮
        $("input[name='companyName']").attr('disabled',"true");
    }

    if(id!=null && id !=''){
        if(window.frameContractDto!=''){
            jsonDto=eval('(' + window.frameContractDto.replace(/[\r\n]/g,"\\n") + ')');

            //单选按钮赋值
            $("input[name='agreementType']").each(function(){
                if($(this).val()==jsonDto.agreementType){
                    $(this).attr('checked','');
                }
            })
            $("input[name='reAgreeFlag']").each(function(){
                if($(this).val()==jsonDto.reAgreeFlag){
                    $(this).attr('checked','');
                }
            })

            $("#companyNo").val(jsonDto.companyNo);
            $("#companyName").val(jsonDto.companyName);
            $("#dateLifeStart").val(formatDate(jsonDto.dateLifeStart,'yyyy-MM-dd'));
            $("#dateLifeEnd").val(formatDate(jsonDto.dateLifeEnd,'yyyy-MM-dd'));
            $("#signDate").val(formatDate(jsonDto.signDate,'yyyy-MM-dd'));
            $("#accountNm").val(jsonDto.accountNm);
            $("#accountProvinceNo").val(jsonDto.accountProvinceNo);
            $("#accountCityNo").val(jsonDto.accountCityNo);
            $("#bankAccountNm").val(jsonDto.bankAccountNm);
            $("#bankAccount").val(jsonDto.bankAccount);
            $("#partyBNm").val(jsonDto.partyBNm);
            $("#partyBTel").val(jsonDto.partyBTel);
            $("#contractNote").val(jsonDto.contractNote);

            $(".selectBusinessBtn").hide();//隐藏选择渠道按钮
            $(".blockTitle").text('编辑联动框架协议');
            $("input[name='agreementType']").attr('disabled',"true");
            form.render();

            //初始化区域选项
            initProviceSelect(jsonDto.accountProvinceNo,function () {
                form.render();
                //初始化城市
                initCitySelect(jsonDto.accountProvinceNo,jsonDto.accountCityNo,function () {
                    form.render();
                });
            });

            //初始化加载图片
            loadImageList('fileBusiness',jsonDto.fileBusinessList,true);
            loadImageList('fileContract',jsonDto.fileContractList,true);
            loadImageList('fileOther',jsonDto.attachmentFileList,true);

        }
    }else{
        initProviceSelect(null,function () {
            form.render();
        });
    }

    var active = {
        //选择渠道
        selectBusiness: function () {
            parent.layer.open({
                type: 2,
                title: '选择渠道',
                area: ['800px', '600px'],
                content: '/pmlsFrameContract/selectBusinessPage'
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        selectBusinessInfo=formData;
                        $("#companyNo").val(formData.companyNo);
                        $("#companyName").val(formData.companyName);

                        //获取最新的开户信息
                        $.ajax({
                            url: ctx + '/pmlsFrameContract/getOldFtBankInfo',
                            type: 'get',
                            data:{companyNo:formData.companyNo},
                            dataType: 'json',
                            success: function (data) {
                                if(data!=null){
                                    $("#accountNm").val(data.accountNm);
                                    $("#bankAccountNm").val(data.bankAccountNm);
                                    $("#bankAccount").val(data.bankAccount);

                                    //初始化区域选项
                                    initProviceSelect(data.accountProvinceNo,function () {
                                        form.render();
                                        //初始化城市
                                        initCitySelect(data.accountProvinceNo,data.accountCityNo,function () {
                                            form.render();
                                        });
                                    });
                                }
                            }
                        });
                        parent.layer.close(index);
                    }
                }
            });
        },
        //选择开户行
        selectBankInfo:function () {
            var companyNo=$("#companyNo").val();
            if(companyNo==null || companyNo==''){
                parent.layer.alert('请先选择渠道！',{icon: 2, title:'提示'});
                return;
            }
            parent.layer.open({
                type: 2,
                title: '选择开户信息',
                area: ['800px', '600px'],
                content: '/pmlsFrameContract/selectBankInfoPage?companyNo='+companyNo
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        selectBankInfo=formData;
                        $("#accountNm").val(formData.accountNm);
                        $("#bankAccountNm").val(formData.bankAccountNm);
                        $("#bankAccount").val(formData.bankAccount);

                        //初始化区域选项
                        initProviceSelect(formData.accountProvinceNo,function () {
                            form.render();
                            //初始化城市
                            initCitySelect(formData.accountProvinceNo,formData.accountCityNo,function () {
                                form.render();
                            });
                        });
                        parent.layer.close(index);
                    }
                }
            });
        }
    };
    $('.layui-form .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //省份选择事件
    form.on('select(accountProvinceNo)', function (data) {
        console.log(data);
        if(data.value!=null && data.value!=''){
            initCitySelect(data.value,null,function () {
                form.render();
            });
        }
    });

    form.render();


    uploadRender('fileBusiness',{fileTypeId:'1028',fileSourceId:'8',exts:'jpg|png|jpeg|pdf'});
    uploadRender('fileContract',{fileTypeId:'1029',fileSourceId:'8',exts:'jpg|png|jpeg|pdf'});
    uploadRender('fileOther',{fileTypeId:'1030',fileSourceId:'8',exts:'jpg|png|jpeg|pdf'});

});


//初始化省份和城市选项
function initProviceSelect(checkValue, callback){
    var url = BASE_PATH + "/pmlsFrameContract/province";
    var params = {};
    ajaxGet(url, params, function(data) {
        var result = "<option value=''>请选择省份</option>";
        $.each(data.returnData, function(n, value) {
            if (checkValue != null && checkValue == value.provinceNo) {
                result += "<option value='" + value.provinceNo + "' selected>" + value.provinceName + "</option>";
            } else {
                result += "<option value='" + value.provinceNo + "'>" + value.provinceName + "</option>";
            }
        });
        $("#accountProvinceNo").html('');
        $("#accountProvinceNo").append(result);

        callback ? callback() : $.noop();
    }, function() {
    });

}

//初始化城市选项
function initCitySelect(accountProvinceNo, checkValue, callback){
    var url = BASE_PATH + "/cityCascade/city?provinceNo=" + accountProvinceNo;
    var params = {};

    ajaxGet(url, params, function(data) {
        var result = "<option value=''>请选择城市</option>";
        $.each(data.returnValue, function(n, value) {
            if (checkValue != null && checkValue == value.cityNo) {
                result += "<option value='" + value.cityNo + "' selected>" + value.cityName + "</option>";
            } else {
                result += "<option value='" + value.cityNo + "'>" + value.cityName + "</option>";
            }
        });
        $("#accountCityNo").html('');
        $("#accountCityNo").append(result);
        callback ? callback() : $.noop();
    }, function() {
    });
}


//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}
function submitForm(){
    parent.layer.load(2);
    var agreementType = $("input[name='agreementType']:checked").val();
    var companyNo=$("#companyNo").val();
    var dateLifeStart=$("#dateLifeStart").val();
    var dateLifeEnd=$("#dateLifeEnd").val();
    var signDate=$("#signDate").val();
    var reAgreeFlag = $("input[name='reAgreeFlag']:checked").val();
    var accountNm=$("#accountNm").val();
    var accountProvinceNo=$("#accountProvinceNo").val();
    var accountProvinceNm = $("#accountProvinceNo").find("option:selected").text();
    var accountCityNo=$("#accountCityNo").val();
    var accountCityNm = $("#accountCityNo").find("option:selected").text();
    var bankAccountNm=$("#bankAccountNm").val();
    var bankAccount=$("#bankAccount").val();
    var partyBNm=$("#partyBNm").val();
    var partyBTel=$("#partyBTel").val();
    var contractNote=$("#contractNote").val();

    //获取上传图片的id
    var fileIds='';
    $(".layui-upload .item_img").each(function () {
        fileIds+=$(this).data("id")+',';
    });
    if(fileIds!=''){
        fileIds=fileIds.substring(0,fileIds.length-1);
    }


    if(isBlank(companyNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择渠道公司！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(dateLifeStart)){
        parent.layer.closeAll();
        parent.layer.alert('请输入合同开始日期！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(dateLifeEnd)){
        parent.layer.closeAll();
        parent.layer.alert('请输入合同结束日期！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(signDate)){
        parent.layer.closeAll();
        parent.layer.alert('请输入合同签订日期！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(accountNm)){
        parent.layer.closeAll();
        parent.layer.alert('请输入开户名！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(accountProvinceNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择开户省份！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(accountCityNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择开户城市！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(bankAccountNm)){
        parent.layer.closeAll();
        parent.layer.alert('请输入开户行！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(bankAccount)){
        parent.layer.closeAll();
        parent.layer.alert('请输入银行账号！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(partyBNm)){
        parent.layer.closeAll();
        parent.layer.alert('请输入乙方授权代表！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(partyBTel)){
        parent.layer.closeAll();
        parent.layer.alert('请输入乙方联系方式！',{icon: 2, title:'提示'});
        return;
    }else if(!checkPhoneNumber(partyBTel)){
        parent.layer.closeAll();
        parent.layer.alert('请输入正确的乙方手机号码！',{icon: 2, title:'提示'});
        return;
    }
    if($("#fileBusiness .item_img").length==0){
        parent.layer.closeAll();
        parent.layer.alert('请上传营业执照！',{icon: 2, title:'提示'});
        return;
    }
    if($("#fileContract .item_img").length==0){
        parent.layer.closeAll();
        parent.layer.alert('请上传合同！',{icon: 2, title:'提示'});
        return;
    }


    var obj={};
    obj.agreementType=agreementType;
    obj.companyNo=companyNo;
    obj.dateLifeStart=dateLifeStart;
    obj.dateLifeEnd=dateLifeEnd;
    obj.signDate=signDate;
    obj.reAgreeFlag=reAgreeFlag;
    obj.accountNm=accountNm;
    obj.accountProvinceNo=accountProvinceNo;
    obj.accountProvinceNm=accountProvinceNm;
    obj.accountCityNo=accountCityNo;
    obj.accountCityNm=accountCityNm;
    obj.bankAccountNm=bankAccountNm;
    obj.bankAccount=bankAccount;
    obj.partyBNm=partyBNm;
    obj.partyBTel=partyBTel;
    obj.contractNote=contractNote;
    obj.fileRecordMainIds=fileIds;
    //渠道信息
    if(selectBusinessInfo!=null){
        obj.companyId=selectBusinessInfo.id;
        obj.frameCompanyNo=selectBusinessInfo.companyNo;
        obj.comapanyName=selectBusinessInfo.companyName;
        obj.companyCityNo=selectBusinessInfo.cityNo;
        obj.companyAcCityNo=selectBusinessInfo.acCityNo;
        obj.cityNm=selectBusinessInfo.cityNm;
        obj.companyDistrictNo=selectBusinessInfo.districtNo;
        obj.districtNm=selectBusinessInfo.districtNm;
        obj.address=selectBusinessInfo.address;
        obj.businessLicenseNo=selectBusinessInfo.businessLicenseNo;
        obj.lealPerson=selectBusinessInfo.legalPerson;
        obj.companyContactTel=selectBusinessInfo.contactNumber;
        obj.remark='';
    }
    console.log(obj);

    var reqUrl=ctx + '/pmlsFrameContract/addFrameContract';
    if(id!=null && id!=''){//修改
        obj.id=id;
        obj.frameContractId=id;
        obj.oldfileRecordMainIds=jsonDto.fileRecordMainIds;
        reqUrl=ctx + '/pmlsFrameContract/updateFrameContract';
    }
    console.log(obj);
    $.ajax({
        url: reqUrl,
        type: 'post',
        dataType: 'json',
        data:obj,
        success: function (data) {
            parent.layer.closeAll();
            console.log(data);
            if(data.returnCode=='200'){
                parent.layer.alert(data.returnMsg,{icon: 1, title:'提示'});
                parent.redirectTo('replace',ctx+'/pmlsFrameContract/frameContractList','框架协议');
            }else{
                parent.layer.alert('数据加载失败');
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}

