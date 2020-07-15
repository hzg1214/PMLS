$(function() {
	// 市区域板块
	linkage();

	//初始化多选框
	$('.multi-select').multiSelect({check: function($instance){
		//初始化
	}});
	
	if($("#bToAAlert2").length == 1){
		$("input[name='bToAAlert']").change(function(){
			if($("input[name='bToAAlert']:checked").val() == 2){
				$("#descTr").show();
			}else{
				$("#descTr").hide();
			}
		});
		if($("input[name='bToAAlert']:checked").val() == 2){
			$("#descTr").show();
		}else{
			$("#descTr").hide();
		}
	}
});

/**
 * 创建门店信息
 */
function Add() {
	
	if ($("#cityNo").val() == "" || $("#districtNo").val() == "") {
		Dialog.alertInfo("请选择公司地址的城市区域");
		return false;
	}
	if ($("#fileRecordMainId").val() == "") {
		Dialog.alertInfo("请添加门店图片");
		return false;
	}
	if ($("#longitude").val() == "" || $("#longitude").val() == undefined
			|| $("#latitude").val() == "" || $("#latitude").val() == undefined) {
		Dialog.alertInfo("请标记地图");
		return false;
	}
	var url = BASE_PATH + '/store';
	if (Validator.validForm("storeAddForm")) {

		systemLoading("", true);

		httpPost('storeAddForm', url, function(data) {
			if(data.returnCode == '-1'){
				Dialog.alertError(data.returnMsg);
				systemLoaded();
				return false;
			}
			location.href = BASE_PATH + "/store";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
	} else {
		systemLoaded();
	}
}


/**
 * 门店编辑保存提交
 * 
 * @param type
 * @returns {Boolean}
 */
function Edit(type) {
	var businessPlaceType = $("input[name='businessPlaceType']:checked").val();
	var storeSizeScale = $("input[name='storeSizeScale']:checked").val();
	if(!isBlank(businessPlaceType)) {
		if(!isBlank(storeSizeScale)) {
			var id = $("#storeId").val();
			var url = BASE_PATH + '/store/' + id;
			var params = $("#storeEditForm").serialize();
			
			if (Validator.validForm("storeEditForm")) {
				systemLoading("", true);
				httpPost(type, url, function(data) {
					
					location.href = BASE_PATH + "/store/" + id;
					//systemLoaded();
				}, function(data) {
					Dialog.alertError(data.returnMsg);
					systemLoaded();
				});
				
			} else {
				systemLoaded();
			}
		} else {
			Dialog.alertError("请选择门店规模");
		}
	}else {
		Dialog.alertError("请选择经营场所类型");
	}
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
		+'<input class="inputstyle" type="file" id="btnfileUpload" name="fileName'+i+'" onchange="upload(this, \'storeEditForm\');"><br/>'
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

