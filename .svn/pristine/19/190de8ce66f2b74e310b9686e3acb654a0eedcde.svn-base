
var $;
layui.use(['element', 'laydate', 'form', 'table', 'layer','upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        upload = layui.upload;
        $ = layui.$;

    var dateLifeStartObj = laydate.render({
        elem: '#dateLifeStart',
        trigger: 'click',
        done: function (value, date, endDate) {
            var minDate  = new Date(value);
            var minTemp  = {
                year: minDate.getFullYear(),
                month: minDate.getMonth(),
                date: minDate.getDate(),
                hours: minDate.getHours(),
                minutes: minDate.getMinutes(),
                seconds: minDate.getSeconds()
            };
            $.extend(true, dateLifeEndObj.config, {min: minTemp});
        }
    });

    var dateLifeEndObj = laydate.render({
        elem: '#dateLifeEnd',
        trigger: 'click',
        done: function (value, date, endDate) {
            var maxDate  = new Date(value);
            var maxTemp  = {
                year: maxDate.getFullYear(),
                month: maxDate.getMonth(),
                date: maxDate.getDate(),
                hours: maxDate.getHours(),
                minutes: maxDate.getMinutes(),
                seconds: maxDate.getSeconds()
            };
            $.extend(true, dateLifeStartObj.config, {max: maxTemp});
        }
    });

    //分销合同名称
    dictionaryLinkageIsServiceSync("contractNameType", 300, function () {
        form.render('select');
    });
    form.on('select(contractNameType)', function (data) {
        if(data.value == '30005' || data.value==30005){
            $("#contractNameDescDiv").show();
        }else{
            $("#contractNameDescDiv").hide();
        }
        form.render();
    });

    form.on('radio(signType)', function(data){
        console.log("signType="+data.value);
        if(data.value=='29201'){
            $("#companyEleUserDiv").hide();
            $("#companyEleTelDiv").hide();

            $("#htType1").prop("checked",false);
            $("#htType2").prop("checked",false);
            $("input[name='htType']").removeProp("disabled");

            $("#htCategory1").prop("checked",false);
            $("#htCategory2").prop("checked",false);
            $("#htCategory3").prop("checked",false);
            $("input[name='htCategory']").removeProp("disabled");

            $("#dyblDiv").hide();
        }else {
            $("#companyEleUserDiv").show();
            $("#companyEleTelDiv").show();

            $("#htType1").prop("checked",true);
            $("#htType2").prop("checked",false);
            $("input[name='htType']").prop("disabled","disabled");

            $("#htCategory1").prop("checked",true);
            $("#htCategory2").prop("checked",false);
            $("#htCategory3").prop("checked",false);
            $("input[name='htCategory']").prop("disabled","disabled");

            $("#dyblDiv").hide();
        }
        form.render();
    });


    form.on('radio(htType)', function(data){
        console.log("htType="+data.value);
        if(data.value=='1671583364489561661'){//返佣
            $("#dyblDiv").hide();
        }else {//垫佣
            $("#dyblDiv").show();
        }
        form.render();
    });

    //上传合同
    uploadRender('uploadContractImg',{fileTypeId:'1067',fileSourceId:'22',exts:'jpg|png|jpeg|pdf'});
    //上传其他附件
    uploadRender('uploadOtherImg',{fileTypeId:'1068',fileSourceId:'22',exts:'jpg|png|jpeg|pdf'});

    var active = {
        //选择经纪公司
        selectCompany:function(){
            parent.layer.open({
                type: 2,
                title: '选择经纪公司',
                area: ['800px', '600px'],
                content: '/cooperationController/selectCompany'
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    if(formData!=null) {
                        var loadIndex = parent.layer.load(2);
                        var paramData = {
                            id:formData.id
                        };
                        $.ajax({
                            url: BASE_PATH + '/cooperationController/checkCompany',
                            type: 'post',
                            data:paramData,
                            dataType: 'json',
                            success: function (data) {
                                console.log(data);
                                parent.layer.close(loadIndex);
                                if (data.returnCode == -1){
                                    parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                                } else {
                                    $("#companyNo").val(formData.companyNo);
                                    $("#businessLicenseNo").val(formData.businessLicenseNo);
                                    $("#companyName").val(formData.companyName);
                                    //关闭弹出层
                                    parent.layer.close(index);
                                }
                            }
                        });
                    }
                    //end
                }
            });
        },
        //选择项目
        selectProject:function(){
            parent.layer.open({
                type: 2,
                title: '选择合作项目',
                area: ['800px', '600px'],
                content: '/cooperationController/selectProject'
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        $("#projectNo").val(formData.projectNo);
                        $("#projectName").val(formData.estateNm);
                        $("#projectAddr").val(formData.realityCityNm + formData.districtNm + formData.address);
                        //清空收入类合同信息
                        $("#srlhtOaNo").val('');
                        $("#hsCode").val('');
                        $("#hsName").val('');
                        $("#htDateStart").val('');
                        $("#htDateEnd").val('');
                        $("#htDate").val('');
                        $("#incomeSubject").val('');
                        $("#commissionMemo").val('');
                        $("#commissionMemoRemark").val('');
                        $("#rtnCommission").val('');
                        $("#rtnCommissionMemo").val('');

                        $("#htoapassDate").val("");
                        $("#wsqyzt").val("");
                        $("#developersCode").val("");
                        $("#developersName").val("");
                        $("#khCode").val("");
                        $("#khName").val("");
                        //关闭弹出层
                        parent.layer.close(index);
                    }
                    //end
                }
            });
        },
        //选择项目合同
        selectEstateHt:function(){
            var projectNo = $("#projectNo").val();
            if(!projectNo){
                parent.layer.alert("请先选择项目！", {icon: 2, title:'提示'});
                return;
            }
            parent.layer.open({
                type: 2,
                title: '选择项目合同',
                area: ['800px', '600px'],
                content: '/cooperationController/selectEstateHt?projectNo='+projectNo
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        $("#htOaNo").val(formData.htOaNo);
                        $("#contractType").val(formData.contractType);
                        $("#hsCode").val(formData.hsCode);
                        $("#hsName").val(formData.hsName);
                        $("#htDateStart").val(formData.htDateStart.substring(0,10));
                        $("#htDateEnd").val(formData.htDateEnd.substring(0,10));
                        $("#htDate").val(formData.htDateStart.substring(0,10) + '至' + formData.htDateEnd.substring(0,10));
                        $("#incomeSubject").val(formData.incomeSubject);
                        $("#commissionMemo").val(formData.commissionMemo);
                        $("#commissionMemoRemark").val(formData.commissionMemoRemark);
                        $("#rtnCommission").val(formData.rtnCommission);
                        $("#rtnCommissionMemo").val(formData.rtnCommissionMemo);

                        $("#htoapassDate").val(formData.oapassDate);
                        $("#wsqyzt").val(formData.wsqyzt);
                        $("#developersCode").val(formData.developersCode);
                        $("#developersName").val(formData.developersName);
                        $("#khCode").val(formData.khCode);
                        $("#khName").val(formData.khName);
                        $("#cooperationRange").val(formData.houseRange);
                        $("#dateLifeStart").val("");
                        $("#dateLifeEnd").val("");

                        var minDate  = new Date(formData.htDateStartLimit);
                        var minTemp  = {
                            year: minDate.getFullYear(),
                            month: minDate.getMonth(),
                            date: minDate.getDate(),
                            hours: minDate.getHours(),
                            minutes: minDate.getMinutes(),
                            seconds: minDate.getSeconds()
                        };
                        var maxDate  = new Date(formData.htDateEndLimit);
                        var maxTemp  = {
                            year: maxDate.getFullYear(),
                            month: maxDate.getMonth(),
                            date: maxDate.getDate(),
                            hours: maxDate.getHours(),
                            minutes: maxDate.getMinutes(),
                            seconds: maxDate.getSeconds()
                        };
                        $.extend(true, dateLifeStartObj.config, {min:minTemp,max: maxTemp});
                        $.extend(true, dateLifeEndObj.config, {min:minTemp,max: maxTemp});
                        //关闭弹出层
                        parent.layer.close(index);
                    }
                    //end
                }
            });
        },
        //提交表单
        submitCooperation:function () {
            var formData={};
            //
            var companyNo = $("#companyNo").val();
            var businessLicenseNo = $("#businessLicenseNo").val();
            var companyName = $("#companyName").val();
            if(!companyNo){
                layer.alert('请选择经纪公司！',{icon: 2, title:'提示'});
                return;
            }
            var projectNo = $("#projectNo").val();
            var projectName = $("#projectName").val();
            if(!projectNo){
                layer.alert('请选择合作项目！',{icon: 2, title:'提示'});
                return;
            }

            //项目合同信息
            var htOaNo = $("#htOaNo").val();
            var contractType = $("#contractType").val();
            var hsCode = $("#hsCode").val();
            var hsName = $("#hsName").val();
            var khCode = $("#khCode").val();
            var khName = $("#khName").val();
            var htDateStart = $("#htDateStart").val();
            var htDateEnd = $("#htDateEnd").val();
            var incomeSubject = $("#incomeSubject").val();
            var commissionMemo = $("#commissionMemo").val();
            var commissionMemoRemark = $("#commissionMemoRemark").val();
            var rtnCommission = $("#rtnCommission").val();
            var rtnCommissionMemo = $("#rtnCommissionMemo").val();

            var htoapassDate = $("#htoapassDate").val();
            var wsqyzt = $("#wsqyzt").val();
            var developersCode = $("#developersCode").val();
            var developersName = $("#developersName").val();


            var projectAddr			= $("#projectAddr").val();
            var cooperationRange	= $("#cooperationRange").val();
            var succeedSell			= $("#succeedSell").val();
            var settlePayNum		= $("#settlePayNum").val();
            var violateAmount		= $("#violateAmount").val();
            var signType			= $("input[name='signType']:checked").val();


            var htType = '1671583364489561661';//返佣
            var htCategory = '3198954431064198420';//模板
            var dybl = '0';  //0
            if(signType=='29201'){
                htType = $("input[name='htType']:checked").val();
                htCategory = $("input[name='htCategory']:checked").val();
                if(htType!='1671583364489561661'){//垫佣
                    dybl = $("#dybl").val();
                }
            }


            var companyEleUser = '';
            var companyEleTel = '';
            if(signType=='29202'){
                companyEleUser	   	= $("#companyEleUser").val();
                companyEleTel		= $("#companyEleTel").val();
            }

            if(!htOaNo){
                layer.alert('请选择项目合同！',{icon: 2, title:'提示'});
                return;
            }


            var contractNameType = $("#contractNameType").val();
            var contractName = $("#contractNameType option:selected").text();
            var contractNameDesc = trimStr($("#contractNameDesc").val());

            if(isEmpty(contractNameType)){
                layer.alert('请选择分销合同名称！',{icon: 2, title:'提示'});
                return;
            }

            if(contractNameType == '30005' || contractNameType==30005){
                contractName = contractNameDesc;

                if(isEmpty(contractNameDesc)){
                    layer.alert('请输入分销合同具体名称！',{icon: 2, title:'提示'});
                    return;
                }
                if(isIncludeBlank(contractNameDesc)){
                    layer.alert('分销合同具体名称含有非法字符！',{icon: 2, title:'提示'});
                    return;
                }
            }else {
                contractNameDesc = '';
            }


/*            var contractName = trimStr($("#contractName").val());
            if(!contractName){
                layer.alert('请输入分销合同名称！',{icon: 2, title:'提示'});
                return;
            }
            if(isIncludeBlank(contractName)){
                layer.alert('分销合同名称含有非法字符！',{icon: 2, title:'提示'});
                return;
            }*/
//            var htedition = $("input[name='htedition']:checked").val();
//            if(!htedition){
//                layer.alert('请选择分销合同版本！',{icon: 2, title:'提示'});
//                return;
//            }




            if(isEmpty(signType)){
                layer.alert('请选择签约类型！',{icon: 2, title:'提示'});
                return;
            }


            if(signType=='29201'){
                if(isEmpty(htType)){
                    layer.alert('请选择分销合同类型！',{icon: 2, title:'提示'});
                    return;
                }

                var htCategoryCheck = $("input[name='htCategory']:checked").val();
                if(isEmpty(htCategoryCheck)){
                    layer.alert('请选择分销合同类别！',{icon: 2, title:'提示'});
                    return;
                }

                if(isEmpty(dybl)){
                    layer.alert('请输入垫佣比例！',{icon: 2, title:'提示'});
                    return;
                }
                if(!checkIsNumber(dybl)){
                    layer.alert('请输入合法的垫佣比例！',{icon: 2, title:'提示'});
                    return;
                }
            }

            if(signType=='29202'){
                if(isEmpty(companyEleUser)){
                    layer.alert('请输入经纪公司电子授权人！',{icon: 2, title:'提示'});
                    return;
                }

                if(isEmpty(companyEleTel)){
                    layer.alert('请输入经纪公司授权人电话！',{icon: 2, title:'提示'});
                    return;
                }
                if (!checkPhoneNumber(companyEleTel)) {
                    parent.msgAlert('经纪公司授权人电话不正确！',{icon: 2, title:'提示'})
                    return false;
                }
            }





            var dateLifeStart = $("#dateLifeStart").val();
            var dateLifeEnd = $("#dateLifeEnd").val();
            if(!dateLifeStart || !dateLifeEnd){
                layer.alert('请选择分销合同周期！',{icon: 2, title:'提示'});
                return;
            }
            if(dateLifeStart > dateLifeEnd){
                layer.alert('请选择有效分销合同周期！',{icon: 2, title:'提示'});
                return;
            }
/*            if((htDateEnd < dateLifeEnd) || (dateLifeStart < htDateStart)){
                layer.alert('分销合同周期需在项目合同周期内！',{icon: 2, title:'提示'});
                return;
            }*/
            var fyjstj = trimStr($("#fyjstj").val());
            if(!fyjstj){
                layer.alert('请输入分销合同返佣结算条件！',{icon: 2, title:'提示'});
                return;
            }
            var fyjsbz = trimStr($("#fyjsbz").val());
            if(!fyjsbz){
                layer.alert('请输入分销合同返佣结算标准！',{icon: 2, title:'提示'});
                return;
            }



            if(isEmpty(trimStr(projectAddr))){
                layer.alert('项目地址不能为空！',{icon: 2, title:'提示'});
                return;
            }

            if(isEmpty(trimStr(cooperationRange))){
                layer.alert('请输入分销合作房源范围！',{icon: 2, title:'提示'});
                return;
            }
            if(isEmpty(trimStr(succeedSell))){
                layer.alert('请输入成功销售的标准！',{icon: 2, title:'提示'});
                return;
            }

            if(!checkIsNumber(settlePayNum)){
                layer.alert('请输入满足结算条件支付节点！',{icon: 2, title:'提示'});
                return;
            }

            if(!checkIsNumber(violateAmount)){
                layer.alert('请输入合法的违约金额！',{icon: 2, title:'提示'});
                return;
            }




            // var commissionAmount = trimStr($("#commissionAmount").val());
            // var commissionRatio = trimStr($("#commissionRatio").val());
            var amountReg = /^(([1-9]\d*)|\d)(\.\d*)?$/;
            // if(commissionAmount && !amountReg.test(commissionAmount)){
            //     layer.alert('请输入有效分销佣金计算公式固定值！',{icon: 2, title:'提示'});
            //     return false;
            // }
            // if(commissionRatio && !amountReg.test(commissionRatio)){
            //     layer.alert('请输入有效分销佣金计算公式总价比例！',{icon: 2, title:'提示'});
            //     return false;
            // }
            // if(commissionRatio>100){
            //     layer.alert('分销佣金计算公式总价比例不能大于100！',{icon: 2, title:'提示'});
            //     return;
            // }
            var remark = trimStr($("#remark").val());
            //获取上传图片的id
            var fileIds='';
            var contractFlag = true;//合同附件必填效验
            $("#uploadContractImg .item_img").each(function () {
                fileIds+=$(this).data("id")+',';
                contractFlag = false;
            });
            $("#uploadOtherImg .item_img").each(function () {
                fileIds+=$(this).data("id")+',';
            });
            if(contractFlag && signType=='29201'){
                layer.alert('请上传合同附件！',{icon: 2, title:'提示'});
                return;
            }
            if(fileIds!=''){
                fileIds=fileIds.substring(0,fileIds.length-1);
            }
            //
            formData.companyNo=companyNo;
            formData.businessLicenseNo=businessLicenseNo;
            formData.companyName=companyName;
            formData.projectNo=projectNo;
            formData.projectName=projectName;
            formData.contractName=contractName;
            formData.htedition=28302;//默认房友
            formData.dateLifeStart=dateLifeStart;
            formData.dateLifeEnd=dateLifeEnd;
            // formData.commissionAmount=commissionAmount;
            // formData.commissionRatio=commissionRatio;
            formData.fyjstj= fyjstj;
            formData.fyjsbz=fyjsbz;
            formData.remark=remark;
            formData.fileIds=fileIds;
            //合同信息
            formData.htOaNo=htOaNo;
            formData.contractType=contractType;
            formData.hsCode=hsCode;
            formData.hsName=hsName;
            formData.khCode=khCode;
            formData.khName=khName;
            formData.htDateStart=htDateStart;
            formData.htDateEnd=htDateEnd;
            formData.incomeSubject=incomeSubject;
            formData.commissionMemo=commissionMemo;
            formData.commissionMemoRemark=commissionMemoRemark;
            formData.rtnCommission=rtnCommission;
            formData.rtnCommissionMemo=rtnCommissionMemo;
            formData.htoapassDate=htoapassDate;
            formData.wsqyzt=wsqyzt;
            formData.developersCode=developersCode;
            formData.developersName=developersName;

            formData.projectAddr		= projectAddr;
            formData.cooperationRange	= cooperationRange;
            formData.succeedSell		= succeedSell;
            formData.settlePayNum		= settlePayNum;
            formData.violateAmount		= violateAmount;
            formData.signType			= signType;
            formData.companyEleUser	   	= companyEleUser;
            formData.companyEleTel		= companyEleTel;
            formData.htType             = htType;
            formData.htCategory         = htCategory;
            formData.dybl               = dybl;
            formData.contractNameType   = contractNameType;
            formData.contractNameDesc   = contractNameDesc;
            //
            console.log(formData);
            var loadIndex = layer.load(2);
            $.ajax({
                url: BASE_PATH + '/cooperationController/addCooperation',
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
                            active['backCooperationList'].call(this);
                        });
                    }
                }
            });
        },
        backCooperationList:function () {
            window.parent.redirectTo('delete');
        }
    };

    $('.cooperationPage .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});

//删除图片
function UPLOAD_IMG_DEL(divs) {
    $("#"+divs).remove();
}
