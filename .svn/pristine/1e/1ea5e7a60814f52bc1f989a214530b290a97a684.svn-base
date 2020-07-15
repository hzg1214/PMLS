
$(function() {
	linkDistrict("newStoreCity","newStoreDistrict");
	//change();
	if($(".btn-table-group").length == 1){
		$(".btn-table-group").children().click(function(){
			$(".btn-table-group").children().removeClass("active");
			$(this).addClass("active");
		});
	}
	if($("#decorationState").length == 1 && $("#storeDecorationPic").length == 1){
		if($("#decorationState").val() == '16304'){
			$(".btn-table-group").children().removeClass("active");
			$("#storeDecorationPicTab").addClass("active");
			$("#storePic").removeClass("active");
			$("#storeDecorationPic").addClass("active");
		}
	}
});


Dialog.alertSuccess = function(){
	dialog: null;
}

StoreLog = function() {
};

function nameChange(obj){
	 if(obj.checked)
	 { 
		 $("#changeName").show();
		 $("#changeContractDiv").show();
	 }else{
	 	//debugger;
		 $("#changeName").hide();
		 $("#newName").val("");
         if($("#storeAddress:checked").size() == 0){
             $("#changeContractDiv").hide();
         }
	 }
	   
}

function addressChange(obj){
	 if(obj.checked)
	 { 
		 $("#changeAddress").show();
         $("#changeContractDiv").show();
	 }else
	 {
		 $("#changeAddress").hide();
		 $("#newStoreAddress").val("");
		 if($("#storeName:checked").size() == 0){
             $("#changeContractDiv").hide();
		 }
	 }
	   
}

function businessStatusChange(obj){
	if(obj.checked){
		$("#changeBusinessStatus").show();
		$("#businessStatus").val("20901");
	}else{
		$("#changeBusinessStatus").hide();
		$("#businessStatus").val("");
	}
}

function businessPlaceChange(obj){
    if(obj.checked)
    {
        $("#changeBusinessPlace").show();
    }else
    {
        $("#changeBusinessPlace").hide();
    }
}

function storeSizeChange(obj){
    if(obj.checked)
    {
        $("#changeStoreSize").show();
    }else
    {
        $("#changeStoreSize").hide();
    }
}
function storeStoreType(obj){
    if(obj.checked)
    {
        $("#changeStoreType").show();
    }else
    {
        $("#changeStoreType").hide();
    }
}

/**
 * @author lhd
 * 进入更新页面
 */
function relateStore(storeId,storeNo,contractStatus,gpContractStatus){
	var url = BASE_PATH + '/store/ed';
	var params = {
			storeId:storeId,
			storeNo:storeNo,
			contractStatus:contractStatus,
        gpContractStatus:gpContractStatus
	};
	var dialogOptions = {
			ok : function() {
                // var flag = fnforCheck(storeId);
                // if(flag){
                    StoreLog.update(storeId,storeNo);
                //}
			},
			okVal : '保存',
			cancel : true,
			cancelVal : '返回'
	};
	// 跳转至添加跟进页面
	Dialog.ajaxOpenDialog(url, params, "修改门店信息", function(dialog, resData) {
		StoreLog.dialog = dialog;
	}, dialogOptions);
};

function operationUpdate(storeId,storeNo){

        var url = BASE_PATH + '/store/toOperationUpdate';
        var params = {
            storeId:storeId,
            storeNo:storeNo
        };
        var flag = 1;
        var dialogOptions = {
            ok : function() {
                // 确定
                StoreLog.update(storeId,storeNo,flag);
            },
            okVal : '保存',
            cancel : true,
            cancelVal : '取消'
        };
        // 跳转至添加跟进页面
        Dialog.ajaxOpenDialog(url, params, "运营维护门店信息", function(dialog, resData) {
            StoreLog.dialog = dialog;
        }, dialogOptions);
};

/**
 * 修改
 */
StoreLog.update = function(storeId,storeNo,flag) {


	//取得区域名
	var districtName = $("#newStoreDistrict").find("option:checked").html();
	$("#districtName").val(districtName);
	
	$("#newStoreAddress").val($("#newStoreAddress").val().replace(/[ ]/g,""));
	
	if($("#storeName").is(':checked')== false 
			&& $("#storeAddress").is(':checked') == false
			&& $("#businessStatusCheck").is(':checked')==false
            && $("#businessPlaceType").is(':checked')==false
            && $("#storeSizeScale").is(':checked')==false
            && $("#storeType").is(':checked')==false)
	{
		$("#checkchange").html('<font color="red">请选择修改项目</font>');
		return false
	}else
	{
		$("#checkchange").html('<font color="red"></font>');
	}

	if($("#oldName").val() == $("#newName").val())
	{
		$("#checkName").html('<font color="red">修改后门店名称和原门店名称一样!</font>');
		return false
	}else
	{
		$("#checkName").html('<font color="red"></font>');
	}

	if(($("#oldStoreDistrict").val() == $("#newStoreDistrict").val()) && ($("#oldStoreAddress").val() == $("#newStoreAddress").val()) )
	{
		$("#checkAddress").html('<font color="red">修改后门店地址和原门店地址一样!</font>');
		return false
	}

	if($("#businessPlaceType").is(':checked')==true){
	    if($("#oldBusinessPlace").val() == $("input[name='newBusinessPlace']:checked").val()){
            $("#checkChangeBusinessPlace").html('<font color="red">修改后营场所类型和原营场所类型一样!</font>');
            return false
        }
    }

    if($("#storeSizeScale").is(':checked')==true){
        if($("#oldStoreSize").val() == $("input[name='newStoreSize']:checked").val()) {
            $("#checkChangeStoreSize").html('<font color="red">修改后门店规模和原门店规模一样!</font>');
            return false
        }
    }
    if($("#storeType").is(':checked')==true){
        if($("#oldStoreType").val() == $("input[name='newStoreType']:checked").val()) {
            $("#changeStoreTp").html('<font color="red">修改后门店类型和原门店类型一样!</font>');
            return false
        }
    }

    var url = BASE_PATH + '/store/updateDetail/'+storeId+'/'+storeNo;
   if (Validator.validForm("storeDetailForm")) {

		systemLoading("", true);
		if(flag != undefined){
			$("#storeDetailForm").append("<input type='hidden' id='flag' name='flag' value='1'>")
        }
		httpPost("storeDetailForm", url, function(data) {
			if(data.returnCode == 200){
                systemLoaded();
			    Dialog.alertInfoToUrl("修改成功!",BASE_PATH + "/store/"+storeId,true);
            }
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			$("#flag").remove();
			systemLoaded();
		});
	} else {
		systemLoaded();
		return false;
	}   
  return true;
}

function fnforCheck(storeId) {
    var checkUrl = BASE_PATH + '/store/checkStatus';
    var checkParams ={
        storeId:storeId
    }

    var returnFlag = true;
    // check是否能保存修改
    $.ajax({
        type: "GET",
        url: checkUrl,
        async : false,
        dataType:"json",
        data:checkParams,
        success: function(result){//成功与否都不处理
            if(result.returnCode == "-1"){
                returnFlag = false;
            }else{
                returnFlag = true;
            }
        },
        error:function(result){
            returnFlag = false;
        }
    });
    return returnFlag;
}

/**
 *	新增图片
 * @param ctx
 */
var i = 0;
function addImage(ctx){
	var trHtmls = '<tr>'
		+'<td>'
		+'<a href="javascript:void(0)" style="display: inline-block;  height: auto; vertical-align: -webkit-baseline-middle;">'
		+'<img src="'+ctx+'/meta/images/store.png" alt="" class="img"><br/>'
		+'<label class="fileName"></label>'
		+'</a>'
		+'<img src="'+ctx+'/meta/images/delete1.png" onclick="delFile(this)" style="margin-left: 10px;margin-right: 10px">'
		+'<span class="fc-warning">(总大小不超过5M)</span>'
		+'<br>'
		+'<div class="form-group">'
		+'<span class="abso_file btn btn-default">浏览...</span>'
		+'<input class="inputstyle" type="file" id="btnfileUpload" name="fileName'+i+'" onchange="upload(this, \'storeDetailForm\');"><br/>'
		+'</div>'
		+'</td>'
		+'</tr>';
	i ++;
	$("#addStoreImageTable").append(trHtmls);
}

/**
 * 删除图片
 * @param target
 */
function delFile(target){
	$(target).parent().parent().remove();
}

//关闭dialog
StoreLog.close = function(){
	StoreLog.dialog.close();
}


/**
 * 菜单跳转 active切换
 * */
function renewAdd(url,authUrl,storeId) {
    var urlrc = BASE_PATH + "/store/rc";
    var params = {"storeId" : storeId};
    systemLoading("", true);
    ajaxGet(urlrc, params, function (data) {
        var centerId = "";
        var centerName = "";
        if (data.returnValue == "-1")//多个所属中心
        {
            var centerUrl = BASE_PATH + '/contract/center/list';
            var title = '选择所属中心';

            var params = {};
            var dialogOptions = {

                ok: function () {
                    centerId = "";
                    centerName = "";
                    $("#errorId").hide();
                    $("input[type=radio][name=chb_select]").each(function () {
                        if ($(this).is(':checked')) {
                            centerId = $(this).next().val();
                            centerName = $(this).parent().parent().find("td").eq(1).text().trim();
                        }
                    });
                    if (centerId == "") {
                        $("#errorId").show();
                        return false;
                    }
                    url = url + "/" + centerId + "/" + centerName;
                    storeGotoContract(url, authUrl);
                    return true;
                },
                okVal: '确定',
                cancel: true,
                cancelVal: '取消',
                width: 550
            };

            Dialog.ajaxOpenDialog(centerUrl, params, title, function (dialog, resData) {
                $('#centerClose').attr('href', 'javascript:StoreLog.close();');
                StoreLog.dialog = dialog;

            }, dialogOptions);
        }
        else {	//一个所属中心
            centerId = data.returnValue[0].exchangeCenterId;
            centerName = data.returnValue[0].exchangeCenterName;
            url = url + "/" + centerId + "/" + centerName;
            storeGotoContract(url, authUrl);
        }
    }, function (data) {
        Dialog.alertError(data.returnMsg);
        systemLoaded();
    });

}

function changeContractUlChange(obj){

    if($("#changeContractUl").size()==0 && $("#changeContractUl2").size()==0){
		return;
    }

    if($(obj).val() == 1){
        $("#changeContractUl").show();
        $("#changeContractUl2").show();
    }else{
    	$("input[name='contractId']:checked").attr("checked",false);
    	$("input[name='gpContractId']:checked").attr("checked",false);
        $("#changeContractUl").hide();
        $("#changeContractUl2").hide();
    }
}

//合同变更弹层
StoreLog.fnToContractChange = function(obj){
	$("#errorMsg").find(".fc-warning").empty().html("");
	var data = $(obj).data();
	var relocationStatus =  data.relocationstatus;
	var partychangestatus =  data.partychangestatus;
	var b2achangestatus =  data.b2achangestatus;

	if(data.contractpaststatus == "0"){
        Dialog.alertError("该门店合同已过期，不允许做合同变更操作！");
        return false;
    }

	if(data.contractchange == "1"){
        Dialog.alertError("该门店合同撤销中或已撤销，不允许做合同变更申请操作");
        return false;
	}

	/*if(data.isCancel == "17202" || data.isCancel == "17203"){
        Dialog.alertError("该门店合同撤销中或已撤销，不允许做合同变更申请操作");
	}else{*/
        var url = BASE_PATH + '/store/selectChangePop';
        var dialogOptions = {
            ok : function() {
            	$("#errorMsg").find(".fc-warning").empty().html("");
                var data = $("#hiddenInput").data();
                var changeType = $("input[name='changeType']:checked").val();
                if(relocationStatus == "0" && partychangestatus =="0" && b2achangestatus =="0"){
                	if(changeType == 17002){
                		storeGotoContract(BASE_PATH+'/stopcontract/toAddB2A/'+data.storeid+'/'+data.contractid+'/' + data.contractstatus,BASE_PATH+'/contract');
                	}else if(changeType == 17003){
                		storeGotoContract(BASE_PATH+'/contractChangeNew/toAddChange/'+data.storeid+'/'+data.abtypestore+'/'+data.contractid+'/' + data.contractstatus,BASE_PATH+'/contract');
                	}else if(changeType == 17004){
                		//发送ajax请求，如果返回为1则可以发起，否则不能
                		checkDecorationStatus(data.storeid);
                		storeGotoContract(BASE_PATH+'/storeRelocation/toAddStoreRelocation/'+data.storeid+'/'+data.contractid +'/' + data.contractstatus,BASE_PATH+'/contract');
                	}
                } else {
                	//门店迁址申请已发起过
                	if(b2achangestatus != "0" && changeType == 17002){
                		$("#errorMsg").find(".fc-warning").empty().html("合同变更（乙转甲）已申请，请勿重复提交!");
                	}else if(partychangestatus != "0" && changeType == 17003){
                		$("#errorMsg").find(".fc-warning").empty().html("合同变更（三方变更）已申请，请勿重复提交!");
                	}else if(relocationStatus != "0" &&  changeType == 17004){
                		$("#errorMsg").find(".fc-warning").empty().html("合同变更（门店迁址）已申请，请勿重复提交!");
                	}else {
                		$("#errorMsg").find(".fc-warning").empty().html("合同变更申请正在处理中，请处理完毕后进行其他合同变更申请。");
                	}
					return false;
                }
            },
            okVal : '确定'
        };
        // 跳转至添加跟进页面
        Dialog.ajaxOpenDialog(url, data, "选择合同变更类型", function(dialog, resData) {
            StoreLog.dialog = dialog;
        }, dialogOptions);
	/*}*/
}

function storeStopCancel(obj,storeId){

    var url = BASE_PATH + '/store/toStoreStopCancel';
    var params = {
        storeId:storeId
    };
    var dialogOptions = {
        ok : function() {
            if(!Validator.validForm("storeStopCancelForm")){
                return false;
            }

            if($('#storePhoto> .item-photo-list').length && $('#storePhoto> .item-photo-list').length>0){
            }else{
                alert("请上传门店照片");
                return false;
            }
            //验证甲方项目负责人名片附件
            if($('#thumbXsBox> .item-photo-list').length && $('#thumbXsBox> .item-photo-list').length>0){
            }else{
                alert("请上传正常营业说明书");
                return false;
            }

            // 确定
            FnStoreStopCancelAdd();
        },
        okVal : '提交',
        cancel : true,
        cancelVal : '取消'
    };
    // 跳转至门店停业撤销申请
    Dialog.ajaxOpenDialog(url, params, "门店停业撤销申请", function(dialog, resData) {
        StoreLog.dialog = dialog;
    }, dialogOptions);
};

function FnStoreStopCancelAdd() {
    handlerFileInfo();

    var param = $("#storeStopCancelForm").serialize();
    restPost(BASE_PATH+"/StoreStopCancel/storeStopCancelAdd",param,
        function (data) {
            Dialog.alertInfo("申请成功");
            setTimeout(function () {
                window.location = BASE_PATH +"/store/"+$("#storeId").val();
            },2000);
        },function (data) {
            Dialog.alertInfo("申请失败");
        });
}

function handlerFileInfo(){
    var bol = true;
    //附件其它
    var fileRecordMainIds = "";
    $("input[name=fileRecordMainIdHidden]").each(function(){
        if($(this).val()==""){
            Dialog.alertError("图片上传出现异常，请删除重新上传。");
            bol = false;
            return false;
        }
        fileRecordMainIds += ","+$(this).val();
    })
    if(fileRecordMainIds!=""){
        fileRecordMainIds = fileRecordMainIds.substring(1);
    }
    $("#fileRecordMainIds").val(fileRecordMainIds);
    return bol;
}
function checkDecorationStatus(id) {
	$("#errorMsg").find(".fc-warning").empty().html("");
	var flag = false;
	var checkUrl = BASE_PATH + '/storeRelocation/checkDecorationStatus/'+id;
	$.ajax({
		   type: "GET",
		   url: checkUrl,
		   async : false,
		   dataType:"json",
		   contentType: 'application/json',
		   success: function(data){
			   if(data.returnCode=='200'){
				 var returnData = data.returnData;
				 if(returnData == "1"){//为2不能通过
					 flag = true;
				 }else {
					 flag = false;
				 }
			   }
		   }
	});
	if(!flag){
		$("#errorMsg").find(".fc-warning").empty().html("该门店翻牌未验收，请验收完毕后再提交迁址变更申请！");
		return false;
	}
}