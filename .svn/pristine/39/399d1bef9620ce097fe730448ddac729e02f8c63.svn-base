var jsonMap = null;
/** ************************公共部分***************************** */
$(function () {
    initList();
    var searchParamMap = CommonUtil.getCookie("FRAMECONTRACT_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
});

/** **************************demo js*************************** */

FrameContract = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {

    var params = {

    };
    httpGet('frameContractListForm', 'LoadCxt', BASE_PATH + '/frameContract/getList', function () {

        pageInfo("frameContractListForm", function () {
            initList();
        });

    });

};
/**
 * 查询
 *
 */
FrameContract.search = function () {
    $('#curPage').val('1');
    initList();
};

FrameContract.close = function () {

	FrameContract.dialog.close();
};
//按钮重置各种条件
FrameContract.reset=function (pageType) {
	$("#frameContractListForm :text").val("");
	$("#frameContractListForm").find("select").val("");
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
	FrameContract.search();
};
//填充储存的表单值
FrameContract.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	$("#frameContractListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#frameContractListForm").find("select").each(function () {
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
function cancel(id) {
	Dialog.confirm("是否确认废除？", function() {
		$.ajax({
			url:BASE_PATH+"/frameContract/cancel",
			data:$.param({
				id:id
			}),
			type:"post",
			success:function(data){
				if(data){
					Dialog.alertInfo("框架协议已作废");
					initList();
					return;
				}
			},
			error:function(){
				Dialog.alertError("框架协议作废失败");
			}
		})
	},function () {});
	
};
function toaddFrameContract(){
	location.href = BASE_PATH + '/frameContract/toInsertFrameContract';
}
$(document).on('click', '.chacha', function(){
    $('.ui_state_highlight').click();
});
FrameContract.getOAOpinions = function(flowId) {

    var url = BASE_PATH + "/contracts/oa/opinionsNew/" + flowId;

    var params = {};

    ajaxGet(url, params, function(data) {

        var showContent = "<span class=\"\" style=\"float:right\">"+
            "<a type=\"button\" href=\"javascript:void(0)\"  class=\"btn btn-default chacha\" style=\"float: right;margin-top: -5px;\">&times;</a>"+
            "<h4 style=\"font-size:16px\"  class=\"border-bottom pdb10\"><strong>联动框架合同审批意见</strong></h4>";
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
                "<td style=\"text-align: center\">"+ returnValue[i].empName+"</td>"+
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
