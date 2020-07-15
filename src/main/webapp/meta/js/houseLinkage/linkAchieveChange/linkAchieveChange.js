/** ************************公共部分***************************** */
$(function () {
    initList();
    initSelect();
});

/** **************************demo js*************************** */
LinkAchieveChange = function () {
    dialog: null;
};

/**
 * 初始化页面的时候，将业务阶段中的退筹结佣删掉（因为是字典表中查询，所以做页面动态删除）
 */
initSelect = function(){
    $("#latestProgress option").each(function(){
        var optionVal = $(this).val();
        if("13506" == optionVal){
            //删除退筹
            $(this).remove();
        }
        if("13507" == optionVal){
            //删除结佣
            $(this).remove();
        }
    });
}

/**
 * 初始化查询
 */
initList = function () {

    var params = {

    };
    httpGet('linkAchieveChangeForm', 'LoadCxt', BASE_PATH + '/linkAchieveChange/getList', function () {

        pageInfo("linkAchieveChangeForm", function () {
            initList();
        });

    });

};
/**
 * 查询
 *
 */
LinkAchieveChange.search = function () {

    var dateStart = $("#dateStart").val();
    var dateEnd = $("#dateEnd").val();

    if(!isBlank(dateStart) || !isBlank(dateEnd)){
        //业务阶段
        var latestProgress =  $("#latestProgress").val();
        if(isBlank(latestProgress)){
            Dialog.alertError("请先选择一个业务阶段！")
            return;
        }
    }


    $('#curPage').val('1');
    initList();
};

LinkAchieveChange.close = function () {

    LinkAchieveChange.dialog.close();
};

/**
 * 选择业绩归属人弹窗
 */
LinkAchieveChange.achieveMentChange = function(){

    var checkFlag = $(".selectReport").is(':checked');
    if(!checkFlag){
        Dialog.alertError("请至少选择一条报备！")
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

    var url = BASE_PATH + '/linkAchieveChange/linkUser';
    var title = '选择变更后业绩归属人';

    var params = {
        ids : ids
    };
    var dialogOptions = {

        width : 800,
        /* height : 760, */

        ok : function() {
            var reback = LinkAchieveChange.saveLinkAchieve();
            return reback;
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

        dialog.position('50%', '25%');
        LinkAchieveChange.dialog = dialog;

    }, dialogOptions);

}

/**
 * 保存业绩
 */
LinkAchieveChange.saveLinkAchieve = function(){

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

    var url = BASE_PATH + '/linkAchieveChange/saveLinkAchieve';
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
LinkAchieveChange.reset=function (pageType) {
	$("#linkAchieveChangeForm :text").val("");
	$("#linkAchieveChangeForm").find("select").val("");
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
	LinkAchieveChange.search();
};