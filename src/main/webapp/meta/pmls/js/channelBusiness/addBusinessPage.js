/*
 * 渠道品牌js
 * */
var layer;
var id=window.id;
var jsonDto={};
var treeObj;
var form,upload;
var selectMaintainerInfo;//选择的维护人信息
layui.config({
    base: "/meta/layui/lay/modules/"
}).extend({
    treeSelect: "treeSelect"
});

layui.use(['tree', 'layer', 'form','treeSelect','upload'], function() {
    layer = layui.layer,
        $ = layui.jquery;
    form = layui.form;
    upload = layui.upload;

    var treeSelect = layui.treeSelect;
    if(id!=null && id !=''){
        if(window.businessDto!=''){
            //jsonDto=eval('(' + window.businessDto + ')');
            jsonDto=eval('(' + window.businessDto.replace(/[\r\n]/g,"\\n") + ')');

            $("input[name='taxRate']").each(function(){
                if($(this).val()==jsonDto.taxRate){
                    $(this).attr('checked','');
                }
            })

            $("#companyName").val(jsonDto.companyName);
            $("#businessLicenseNo").val(jsonDto.businessLicenseNo);
            $("#realityCityNo").val(jsonDto.cityNo);
            $("#districtNo").val(jsonDto.districtNo);
            $("#address").val(jsonDto.address);
            $("#legalPerson").val(jsonDto.legalPerson);
            $("#contactNumber").val(jsonDto.contactNumber);
            $("#dockingPeo").val(jsonDto.dockingPeo);
            $("#dockingPeoTel").val(jsonDto.dockingPeoTel);
            $("#brandId").val(jsonDto.brandId);
            // $("#remark").val(jsonDto.remark);

            $(".dockingPeoItem").hide();
            $(".dockingPeoTelItem").hide();
            $(".maintainerItem").hide();//隐藏维护人的编辑
            $(".blockTitle").text('编辑借佣渠道');

            //初始化城市选项
            cityLinkageIsService(jsonDto.cityNo,function () {
                form.render();
                //初始化区域
                districtLinkageIsService(jsonDto.cityNo,jsonDto.districtNo,function () {
                    form.render();
                });
            });

            //初始化图片
            loadImageList('fileBusiness',jsonDto.fileList,true);


            //存在框架协议合同不允许修改渠道名称和统一社会信用代码
            if(jsonDto.frameContractNo!=null && jsonDto.frameContractNo!='' && jsonDto.approveState !=10405){
                $("#companyName").attr('disabled',"");
                $("#businessLicenseNo").attr('disabled',"");
                // $("input[name='isFyCompany']").attr('disabled',"");//是否我司不可修改
            }
        }
    }else{
        cityLinkageIsService(null,function () {
            form.render();
        });
    }
    //城市选择事件
    form.on('select(realityCityNo)', function (data) {
        console.log(data);
        if(data.value!=null && data.value!=''){
            districtLinkageIsService(data.value,null,function () {
                form.render();
            });
        }
    });
    //初始化渠道分类
    if(window.channelTypeList!=null && window.channelTypeList!=''){
        //var listData=eval('(' + window.channelTypeList + ')');
        var listData=eval('(' + window.channelTypeList.replace(/[\r\n]/g,"\\n") + ')');
        var result = "";
        $.each(listData, function (n, value) {
            if(jsonDto!=null && jsonDto.channelType!=null && jsonDto.channelType!=''&& jsonDto.channelType==value.dicCode){
                result+="<input type='radio' name='channelType' value="+ value.dicCode + " title='"+value.dicValue+"' checked=''>"
            }else{
                result+="<input type='radio' name='channelType' value="+ value.dicCode + " title='"+value.dicValue+"' >"
            }
        });
        $("#channelType").html('');
        $("#channelType").append(result);
    }
    //初始化渠道等级
    if(window.channelLevelList!=null && window.channelLevelList!=''){
        //var listData=eval('(' + window.channelLevelList + ')');
        var listData=eval('(' + window.channelLevelList.replace(/[\r\n]/g,"\\n") + ')');
        var result = "";
        $.each(listData, function (n, value) {
            if(jsonDto!=null && jsonDto.channelLevel!=null && jsonDto.channelLevel!=''&& jsonDto.channelLevel==value.dicCode){
                result+="<input type='radio' name='channelLevel' value="+ value.dicCode + " title='"+value.dicValue+"' checked=''>"
            }else{
                result+="<input type='radio' name='channelLevel' value="+ value.dicCode + " title='"+value.dicValue+"' >"
            }
        });
        $("#channelLevel").html('');
        $("#channelLevel").append(result);
    }
    form.render();

    //初始化渠道品牌选项
    treeSelect.render({
        // 选择器
        elem: '#brandId',
        // 数据
        data: ctx+'/channelBrandController/getChannelBrandList2',
        // 异步加载方式：get/post，默认get
        type: 'post',
        // 占位符
        placeholder: '请选择',
        // 是否开启搜索功能：true/false，默认false
        search: true,
        // 一些可定制的样式
        style: {
            folder: {
                enable: false
            },
            line: {
                enable: true
            },
        },
        // 点击回调
        click: function(d){
            console.log(d);
        },
        // 加载完成后的回调函数
        success: function (d) {
            console.log(d);
            //选中节点，根据id筛选
            if(jsonDto!=null && jsonDto.brandId!=null && jsonDto.brandId!=''){
                treeSelect.checkNode('tree', jsonDto.brandId);
            }

            console.log($('#brandId').val());
            //获取zTree对象，可以调用zTree方法
            treeObj = treeSelect.zTree('tree');
            console.log(treeObj);
            //刷新树结构
            treeSelect.refresh('tree');

            //存在框架协议合同不允许修改渠道名称和统一社会信用代码
            if(jsonDto.frameContractNo!=null && jsonDto.frameContractNo!='' && jsonDto.approveState !=10405){
                // $(".brandIdInputBlock .layui-select-title input").attr('disabled',"");
                /*$(".brandIdInputBlock").html('<select disabled="disabled">\n' +
                    '        <option value="">'+jsonDto.brandName+'</option>\n' +
                    '      </select>')*/
                $(".brandIdInputBlock .layui-treeSelect").hide();
                $(".brandIdInputBlock").append('<input type="text" class="layui-input" disabled value="'+jsonDto.brandName+'">');
            }
        }
    });

    //上传图片功能初始化
    uploadRender('fileBusiness',{fileTypeId:'1044',fileSourceId:'3'})

    var active = {
        //选择维护人
        selectMaintain: function () {
            parent.layer.open({
                type: 2,
                title: '选择维护人',
                area: ['800px', '600px'],
                content: '/businessManagerController/selectMaintainPage'
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        selectMaintainerInfo=formData;
                        $("#maintainerName").val(formData.maintainerName);
                        $("#centerName").val(formData.centerName);
                        parent.layer.close(index);
                    }
                }
            });
        },
    };
    $('.layui-form .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});
//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}
//校验商户统一社会信用代码是否存在
function checkBusiness() {
    var businessLicenseNo= $("#businessLicenseNo").val();
    //营业执照长度满足校验数据库是否存在
    if((businessLicenseNo.length==15 || businessLicenseNo.length==18)){
        checkBusinessLicenseNo('',businessLicenseNo);
    }
}
//校验商户名是否存在
function checkBusinessCompanyName() {
    var companyName= $("#companyName").val();
    if(companyName!=null && companyName!='' ){
        checkBusinessLicenseNo(companyName,'');
    }
}
//校验商户是否存在
function checkBusinessLicenseNo(companyName,businessLicenseNo) {
    var paramData={
        companyName:companyName,
        businessLicenseNo:businessLicenseNo
    }
    if(jsonDto!=null && jsonDto!=''){
        paramData.id=jsonDto.id;
    }
    $.ajax({
        url: ctx+'/businessManagerController/getBusinessInfo2',
        type: 'post',
        dataType: 'json',
        data:paramData,
        success: function (data) {
            parent.layer.closeAll();
            console.log(data);
            if(data.returnCode=='200'){
                if(data.returnData!=null){
                    addCompanyReleaseCity(data.returnData.id,companyName,businessLicenseNo);//添加公司到对应的城市
                }
            }else{
                parent.layer.alert('数据加载失败');
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}
//添加商户城市关系表
function addCompanyReleaseCity(id,companyName,businessLicenseNo){
    $.ajax({
        url: ctx+'/businessManagerController/addCompanyReleaseCity',
        type: 'post',
        dataType: 'json',
        data:{
            id:id
        },
        success: function (data) {
            console.log(data);
            if(data.returnCode=='200'){
                var msg='';
                if(companyName!=''){
                    msg='您输入的渠道名称对应的渠道已存在，请返回列表页查看！';
                }
                if(businessLicenseNo!=''){
                    msg='您输入的统一社会信用代码对应的渠道已存在，请返回列表页查看！';
                }
                /*parent.layer.alert(msg,{icon: 2, title:'提示'},function () {
                    parent.layer.closeAll();
                    back();
                });*/
                parent.layer.open({
                    content: msg,
                    icon:2,
                    title:'提示',
                    yes: function(index, layero){
                        parent.layer.close(index); //如果设定了yes回调，需进行手工关闭
                        back();
                    },
                    cancel:function(index,layero){
                        parent.layer.close(index);
                        if(companyName!=''){
                            $("#companyName").focus();
                        }else if(businessLicenseNo!=''){
                            $("#businessLicenseNo").focus();
                        }
                    }
                });
            }else{
                parent.layer.alert('数据加载失败');
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}

function submitForm(){
    parent.layer.load(2);
    var companyName=replaceBrackets($("#companyName").val().trim());
    var businessLicenseNo=$("#businessLicenseNo").val().trim();
    var realityCityNo=$("#realityCityNo").val();
    var cityName = $("#realityCityNo").find("option:selected").text();
    var districtNo=$("#districtNo").val();
    var districtName = $("#districtNo").find("option:selected").text();
    var address=replaceBrackets($("#address").val().trim());
    var legalPerson=$("#legalPerson").val().trim();
    var contactNumber=$("#contactNumber").val().trim();
    var dockingPeo=$("#dockingPeo").val().trim();
    var dockingPeoTel=$("#dockingPeoTel").val().trim();
    var channelType=$("input[name='channelType']:checked").val();
    var brandId=$("#brandId").val();
    var channelLevel=$("input[name='channelLevel']:checked").val();
    var taxRate=$("input[name='taxRate']:checked").val();
    //var remark=$("#remark").val();
    // var isFyCompany = $("input[name='isFyCompany']:checked").val();
    var maintainerName = $("#maintainerName").val();
    var maintainer = '';
    var centerId ='';
    var channelTypeName='';
    var channelLevelName='';
    var taxRateName='';
    var brandName ='';
    if(selectMaintainerInfo!=null){
        maintainer = selectMaintainerInfo.maintainer;
        centerId = selectMaintainerInfo.centerId;
    }

    //获取上传图片的id
    var fileIds='';
    $(".layui-upload .item_img").each(function () {
        fileIds+=$(this).data("id")+',';
    });
    if(fileIds!=''){
        fileIds=fileIds.substring(0,fileIds.length-1);
    }
    var reg =/\s/;//校验是否存在空格
    if(isBlank(companyName)){
        parent.layer.closeAll();
        parent.layer.alert('请输入渠道名称！',{icon: 2, title:'提示'});
        return;
    }else if(reg.test(companyName)){
        parent.layer.closeAll();
        parent.layer.alert('渠道名称不能含有空格！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(businessLicenseNo)){
        parent.layer.closeAll();
        parent.layer.alert('请输入统一社会信用代码！',{icon: 2, title:'提示'});
        return;
    }else if(businessLicenseNo.length!=15 && businessLicenseNo.length!=18){
        parent.layer.closeAll();
        parent.layer.alert('请输入正确的统一社会信用代码！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(realityCityNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择城市！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(districtNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择区域！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(address)){
        parent.layer.closeAll();
        parent.layer.alert('请输入详细地址！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(legalPerson)){
        parent.layer.closeAll();
        parent.layer.alert('请输入法定代表人！',{icon: 2, title:'提示'});
        return;
    }
    if(!isBlank(contactNumber) && !checkPhoneNumber(contactNumber)){
        parent.layer.closeAll();
        parent.layer.alert('请输入正确的法人手机号码！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(dockingPeo)){
        parent.layer.closeAll();
        parent.layer.alert('请输入管理员！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(dockingPeoTel)){
        parent.layer.closeAll();
        parent.layer.alert('请输入管理员手机号码！',{icon: 2, title:'提示'});
        return;
    }else if(!checkPhoneNumber(dockingPeoTel)){
        parent.layer.closeAll();
        parent.layer.alert('请输入正确的管理员手机号码！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(channelType)){
        parent.layer.closeAll();
        parent.layer.alert('请选择渠道分类！',{icon: 2, title:'提示'});
        return;
    }else{
        channelTypeName = $("input[name='channelType']:checked")[0].title;
    }

    if(isBlank(brandId)){
        parent.layer.closeAll();
        parent.layer.alert('请选择渠道品牌！',{icon: 2, title:'提示'});
        return;
    }else{
        brandName = treeObj.getSelectedNodes()[0].brandName;
    }
    if(isBlank(channelLevel)){
        parent.layer.closeAll();
        parent.layer.alert('请选择渠道等级！',{icon: 2, title:'提示'});
        return;
    }else{
        channelLevelName = $("input[name='channelLevel']:checked")[0].title;
    }
    if(isBlank(taxRate)){
        parent.layer.closeAll();
        parent.layer.alert('请选择增值税税率！',{icon: 2, title:'提示'});
        return;
    }else{
        taxRateName = $("input[name='taxRate']:checked")[0].title;
    }
    /*if(isBlank(isFyCompany)){
        parent.layer.closeAll();
        parent.layer.alert('请选择是否我司！',{icon: 2, title:'提示'});
        return;
    }*/
    if(id=='' && isBlank(maintainerName)){
        parent.layer.closeAll();
        parent.layer.alert('请选择维护人！',{icon: 2, title:'提示'});
        return;
    }
    if($("#fileBusiness .item_img").length==0){
        parent.layer.closeAll();
        parent.layer.alert('请上传营业执照！',{icon: 2, title:'提示'});
        return;
    }

    var obj={};
    obj.companyName=companyName;
    obj.businessLicenseNo=businessLicenseNo;
    obj.cityNo=realityCityNo;
    obj.cityName=cityName;
    obj.districtNo=districtNo;
    obj.districtName=districtName;
    obj.address=address;
    obj.legalPerson=legalPerson;
    obj.contactNumber=contactNumber;
    obj.dockingPeo=dockingPeo;
    obj.dockingPeoTel=dockingPeoTel;
    obj.channelType=channelType;
    obj.channelTypeName=channelTypeName;
    obj.brandId=brandId;
    obj.brandName=brandName;
    obj.channelLevel=channelLevel;
    obj.channelLevelName=channelLevelName;
    obj.taxRate=taxRate;
    obj.taxRateName=taxRateName;
    //obj.remark=remark;
    obj.maintainer=maintainer;
    obj.maintainerName=maintainerName;
    obj.centerId=centerId;
    obj.fileIds=fileIds;

    obj.brandType=29401;    // 分销渠道
    obj.pmlsCenterId=centerId;
    obj.isProcuration=1;   // 借佣渠道
    obj.arteryType=29501;   // 大渠道

    var reqUrl=ctx + '/businessManagerController/addBusiness';
    if(id!=null && id!=''){//修改商户
        obj.id=id;
        reqUrl=ctx + '/businessManagerController/updateBusiness';
    }else{
        obj.isFyCompany=0;
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
                //parent.redirectTo('replace',ctx+'/businessManagerController/businessManagerList','渠道');
                back();
            }else{
                if(data.returnMsg!=null){
                    parent.layer.alert(data.returnMsg,{icon: 2, title:'提示'});
                }else{
                    parent.layer.alert("数据加载失败",{icon: 2, title:'提示'});
                }
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}

