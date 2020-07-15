/** ************************公共部分***************************** */
$(function () {
	//加载城市/区域
	linkDistricts("storeCityNo","districtNo");
    initList();
});

/** **************************demo js*************************** */

StoreMaintenance = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {

    var params = {

    };
    httpGet('storeMaintenanceListForm', 'LoadCxt', BASE_PATH + '/storeMaintenance/getList', function () {

        pageInfo("storeMaintenanceListForm", function () {
            initList();
        });

    });

};
/**
 * 查询
 *
 */
StoreMaintenance.search = function () {
    $('#curPage').val('1');
    initList();
};

StoreMaintenance.close = function () {

	StoreMaintenance.dialog.close();
};

/**
 * 选择业绩归属人弹窗
 */
StoreMaintenance.achieveMentChange = function(){

    var checkFlag = $(".selectReport").is(':checked');
    if(!checkFlag){
        Dialog.alertError("请至少选择一个需要变更维护人的记录！")
        return;
    }

    var ids = "";
    $(".selectReport").each(function(){
        if($(this).is(':checked')){
            ids += $(this).val()+",";
        }
    });
    if(ids != ""){
        ids = ids.substr(0,ids.length-1);
    }
	var flag;
	$('input.selectReport:checked').each(function(i,j){
		var	firstShopCenter = $.trim($('input.selectReport:checked:first').closest('tr').children('td:eq(6)').text()),
		shopCenter = $.trim($(this).closest('tr').children('td:eq(6)').text());
		if(firstShopCenter !== shopCenter){
			Dialog.alertError("请选择相同的门店所属中心！");
			flag = true;
			return false;
		}
		return true;
	});
	if(flag){
		return;
	}
	var storeCenterId = $('input:checked:first').siblings('input#storeCenterId').val();
    var url = BASE_PATH + '/storeMaintenance/maintenanceUser';
    var title = '维护人选择';
    
    var params = {
        ids : ids,
        storeCenterId : storeCenterId
    };
    var dialogOptions = {

        width : 800,
        /* height : 760, */

        ok : function() {
            var reback = StoreMaintenance.saveMaintenance();
            return reback;
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

        dialog.position('50%', '25%');
        StoreMaintenance.dialog = dialog;

    }, dialogOptions);

}

/**
 * 保存业绩
 */
StoreMaintenance.saveMaintenance = function(){
    var ids = $("#ids").val();
    var selectRadio = $("input.selectCenterId:checked");
    var selectCenterId = selectRadio.val();
    if(isBlank(selectCenterId)){
        $("#errorMsg").text("请先选择一个业绩归属人!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }

    var userCode = selectRadio.parent().next().text();
    var userName = selectRadio.parent().next().next().text();

    var url = BASE_PATH + '/storeMaintenance/saveMaintenance';
    var params = {
        ids : ids,
        selectCenterId : selectCenterId,
        userCode : userCode,
        userName : userName
    }
    restPost(url, params, function(data){
        console.log(data);
        initList();
    }, function(data){
        if(data.returnCode != "200"){
            Dialog.alertError(data.returnMsg);
        }
    })

}
function linkDistricts(cityId, districtId){
	$("#"+cityId+"").change(function(){	
		$("#"+districtId+" option").remove();
		
	    var cityNo = $("#"+cityId).val();
	    if(cityNo == null || cityNo == "")
    	{
	    	var html = "<option value='' selected>请选择</option>";
	    	$('#'+districtId).append(html);
	    	return false;
    	}
	    
	    var url = BASE_PATH +  "/linkages/city/" + cityNo;
	    var params = {};
	    ajaxGet(url, params, function(data) {
			var result = "<option value=''>请选择</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.districtNo + "'>"
						+ value.districtName + "</option>";
			});
			$("#"+districtId).html('');
			$("#"+districtId).append(result);
		}, function() {
		});	   
	})
}
//checkbox 全选/取消全选  
var isCheckAll = false;  
function swapCheck() {  
    if (isCheckAll) {  
        $("input[type='checkbox']:visible").each(function() {  
            this.checked = false;  
        });  
        isCheckAll = false;  
    } else {  
        $("input[type='checkbox']:visible").each(function() {  
            this.checked = true;  
        });  
        isCheckAll = true;  
    }  
}  
StoreMaintenance.reset=function (pageType) {
	$("#storeMaintenanceListForm :text").val("");
	$("#storeMaintenanceListForm").find("select").val("");
	var url = BASE_PATH + "/commons/clearSearchParam?pageType="+ pageType;
	$.ajax({
		type: "GET",
		url: url,
		async : true,
		dataType:"json",
		success: function(data){//成功与否都不处理

		},
		error:function(){

		}
	});
	StoreMaintenance.search();
};
