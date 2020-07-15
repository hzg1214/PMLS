/** ************************公共部分***************************** */
window.showFlag = false;

var jsonMap = null;
$(function () {
    initList();
    if($("#orderTypeFlag").val() == 21401){
    	showFlag = true;
    }
    if(showFlag){
    	var searchParamMap = CommonUtil.getCookie("STORERECEIVE_LIST_SEARCH");
    }else{
    	var searchParamMap = CommonUtil.getCookie("STOREADJUST_LIST_SEARCH");
    }
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
});

/** **************************demo js*************************** */

StoreReceive = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {
    httpGet('storeReceiveListForm', 'LoadCxt', BASE_PATH + '/storeReceive/getList', function () {
        pageInfo("storeReceiveListForm", function () {
            initList();
        });
    });

};
/**
 * 查询
 *
 */
StoreReceive.search = function () {
    $('#curPage').val('1');
    initList();
};

StoreReceive.close = function () {

	StoreReceive.dialog.close();
};
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
StoreReceive.reset=function (pageType) {
	$("#storeReceiveListForm :text").val("");
	$("#storeReceiveListForm").find("select").val("");
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
	StoreReceive.search();
};
function removeTr(id) {
	$.ajax({
		url:BASE_PATH+"/storeReceive/update",
		data:$.param({
			id:id
		}),
		type:"post",
		success:function(data){
			if(data){
				Dialog.alertInfo("删除成功");
				initList();
				return;
			}
		},
		error:function(){
			Dialog.alertError("删除失败");
		}
	})
	
};
function submitTr(id,submitOaStatus) {
	$.ajax({
		url:BASE_PATH+"/storeReceive/update",
		data:$.param({
			id:id,
			submitOaStatus:submitOaStatus
		}),
		type:"post",
		success:function(data){
			if(data){
				Dialog.alertInfo("提交成功");
				initList();
				return;
			}
		},
		error:function(){
			Dialog.alertError("提交失败");
		}
	})
	
};
//填充储存的表单值
StoreReceive.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	$("#storeReceiveListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#storeReceiveListForm").find("select").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};
$(document).on('click', '.chacha', function(){
    $('.ui_state_highlight').click();
});
StoreReceive.getOAOpinions = function(flowId) {

    var url = BASE_PATH + "/contracts/oa/opinionsNew/" + flowId;

    var params = {};

    ajaxGet(url, params, function(data) {

        var showContent = "<span class=\"\" style=\"float:right\">"+
            "<a type=\"button\" href=\"javascript:void(0)\"  class=\"btn btn-default chacha\" style=\"float: right;margin-top: -5px;\">&times;</a>"+
            "<h4 style=\"font-size:16px\"  class=\"border-bottom pdb10\"><strong>保证金收款审批意见</strong></h4>";
        showContent += "<table class=\"table table-striped table-hover table-bordered\" border=\"1\" style=\"width:900px;\" id=\"logTable\"></table>";
        var returnValue = data.returnData;
        var html="<tr><th width=\"100px\" style=\"text-align: center\">序号</th>"+
            "<th width=\"400px\" style=\"text-align: center\">审批意见</th>"+
            "<th width=\"150px\" style=\"text-align: center\">审批人</th>"+
            "<th width=\"250px\" style=\"text-align: center\">审批日期</th>"+
            "</tr>";
        for (var i = 0; i < returnValue.length; i++) {
            html += "<tr>"+
                "<td style=\"text-align: center;border-right:0\" >"+(i+1)+"</td>"+
                "<td style=\"text-align: left\">"+returnValue[i].content+"</td>"+
                "<td style=\"text-align: center\">"+returnValue[i].empName+"</td>"+
                "<td style=\"text-align: center\">"+returnValue[i].createDate.replace("T"," ")+"</td>"+
                "</tr>";
        }
        $.dialog({
            content : showContent,
            ok : function() {
                return;
            }
        });
        $('#ldg_lockmask').show();
        $("#logTable").html(html);
        $('.ui_state_highlight').hide();

    }, function(data) {

        var returnMsg = data.returnMsg;
        Dialog.alertError(returnMsg);
    });

};

StoreReceive.toCreateReverse=function (receiveId) {
	Dialog.confirm("确认发起红冲?",function(){
        systemLoading("", true);
		ajaxGet(BASE_PATH+"/storeReceive/reverse/"+receiveId,{},function (data) {
            if(data.returnCode == '200'){
                StoreReceive.search();
                Dialog.alertSuccess();
            }else{
                Dialog.alertError(data.returnMsg);
            }
            systemLoaded();
        },function (data) {
            Dialog.alertError(data.returnMsg);
            systemLoaded();
        });
	},function () {

    })
}