/** ************************公共部分***************************** */

$(function () {
    initAccountProject();
    initSerachKey();
    initOrganizationEvent();
    initRegion();
    initMultiSelect();

    fileInterval();
    //默认查询维度为业绩
    $("#serachKey .multi-select-text").val("业绩")
    $('#serachKeyGroup').find("input[name=serachKeys]").val('YJ');
    $("#serachKey .multi-select-list .multi-select-item input[value='YJ']").attr("checked",true);
    //如选择的包含收入，所在城市和归属中心为灰色
    $("#serachKey .multi-select-list .multi-select-item input").change(function(){
    	var serachKey = $('#serachKeyGroup').find("input[name=serachKeys]").val();
    	if(serachKey.indexOf("SR") >= 0 ){//置灰
	        $("#centerGroup").find("input[type=\"text\"]").attr("disabled", true);
    	}else{//清空置灰
	        $("#centerGroup").find("input[type=\"text\"]").attr("disabled", false);
    	}
    });
});


//选择核算主体
$('#accountProjectGroup').find('.multi-select').multiSelect({
    check: function ($instance) {

    }
});
$('#serachKeyGroup').find('.multi-select').multiSelect({
	check: function ($instance) {
		
	}
});


/** **************************demo js*************************** */
LinkDetail = function () {
};

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll", true);

    var params = {};

    httpGetWithParams('LinkDetailListForm', 'LoadCxt', params, BASE_PATH + '/linkDetail/queryLinkDetailList', function () {

        pageInfo("LinkDetailListForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    }, "1");
};
/**
 * 查询
 *
 */
LinkDetail.search = function () {

	var serachKeyValue = $('#serachKeyGroup').find("input[name=serachKeys]").val();
	
	if (serachKeyValue == "" || serachKeyValue == null || serachKeyValue == undefined ) {
		Dialog.alertInfo("查询维度不能为空！");
		return false;
	}
    var flag = validSelect();
    if (!flag) {
        return false;
    }

    $('#curPage').val('1');
    initList();
};

LinkDetail.export = function () {

var serachKeyValue = $('#serachKeyGroup').find("input[name=serachKeys]").val();
	
	if (serachKeyValue == "" || serachKeyValue == null || serachKeyValue == undefined ) {
		Dialog.alertInfo("查询维度不能为空！");
		return false;
	}
    var flag = validSelect();
    if (!flag) {
        return false;
    }

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();
    var accountProjectNos = $("#accountProject").find(".multi-select-value").val();
    var serachKey = serachKeyValue.replace(";",",");
    var cookieName = guid();
    $("#btn-reset").attr("disabled", true);
    // showExprotLoading(cookieName);

    var url = LOC_RES_BASE_PATH + "/linkDetail/exportLinkDetailAjax?"
        + "organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + "&estateId=" + $("#estateId").val()
        + "&estateNm=" + $("#estateNm").val()
        + "&reportId=" + $("#reportId").val()
        + "&storeNo=" + $("#storeNo").val()
        + "&reportDateStart=" + $("#reportDateStart").val()
        + "&reportDateEnd=" + $("#reportDateEnd").val()
        + "&roughDateStart=" + $("#roughDateStart").val()
        + "&roughDateEnd=" + $("#roughDateEnd").val()
        + "&dealDateStart=" + $("#dealDateStart").val()
        + "&dealDateEnd=" + $("#dealDateEnd").val()
        + "&accountProjectNos=" + accountProjectNos
        + "&serachKey=" + serachKey
        + "&cookieName=" + cookieName;

    $.ajax({
        type: "GET",
        url: url,
        async: true,
        dataType: "json",
        contentType: 'application/json',
        success: function (data) {
            var dataStr = data.message;
            systemLoading('#contentAll', true, dataStr);
        },
        error: function () {
        }
    });

    window.setTimeout(function () {
        $("#btn-reset").attr("disabled", false);
    }, 10000);

};

function fileInterval() {
    var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "linkDetail_filepath";

    $.ajax({
        type: "GET",
        url: url,
        async: true,
        dataType: "json",
        contentType: 'application/json',
        success: function (data) {
            var fileId = data.fileId;
            console.log("fileId:" + fileId);
            if (fileId != undefined && fileId != null && fileId != '' && fileId != 0) {
                downLoadExcel2(fileId);
            }
        },
        error: function () {
        }
    });

    setTimeout(fileInterval, 10000);

};

function downLoadExcel2(fileId) {
    console.log("fileId=" + fileId);
    var url = BASE_PATH + '/expendReport/downLoadExcel2?fileId=' + fileId;
    window.location.href = url;
    var url = BASE_PATH + '/expendReport/queryReportSize/' + "linkDetail_filepath";
    $.ajax({
        type: "GET",
        url: url,
        async: true,
        dataType: "json",
        contentType: 'application/json',
        success: function (data) {
            $("#reportSize").text(data.userReportSize);
        },
        error: function () {
        }
    });
    systemLoaded('#contentAll');
};

function initAccountProject() {
    clearMultiSelect(["#accountProject"]);
    var url = BASE_PATH + "/linkDetail/getAccountProject";

    ajaxGet(url, {}, function (data) {
        var list = data.list;
        handleMultiSelect(list, "7", "#accountProject");
    }, function () {
    });
};

/**
 * 初始化查询维度
 * @returns
 */
function initSerachKey() {
    clearMultiSelect(["#serachKey"]);
    var html = '<li class="multi-select-item"> <label><input type="checkbox" value="YJ" data-text="业绩"><span>业绩</span></label> </li>'
        + '<li class="multi-select-item"> <label><input type="checkbox" value="SR" data-text="收入"><span>收入</span></label> </li>';
    $("#serachKey").find('.multi-select-list').append(html);
};


/**
 *
 * @param obj
 * @returns
 */
function changeSerachKey() {
    var serachKeys = $('#serachKeyGroup').find("input[name=serachKeys]").val();
    if (serachKeys != null && serachKeys != "" && serachKeys != undefined) {
        if ('SR' == serachKeys) {
            $("#city").find("input[type=\"text\"]").attr("disabled", true);
            $("#centerGroup").find("input[type=\"text\"]").attr("disabled", true);
        }
    }
};

/**
 * 清空下拉框
 * @returns
 */
function changeValue(obj) {
//    var serachKeyValue = $(obj).val();
//    if (serachKeyValue != "" && serachKeyValue != null && serachKeyValue != undefined && serachKeyValue == 'SR') {
//        $("#centerGroup").find("input[type=\"text\"]").attr("disabled", true);
//    } else {
//        $("#centerGroup").find("input[type=\"text\"]").attr("disabled", false);
//    }
    //核算主体
    clearMultiSelectM('accountProject');
    //归属区域
    clearMultiSelectM('region');
    //归属城市
    clearMultiSelectM('areaCity');
    //所在城市
    clearMultiSelectM('city');
    //归属中心
    clearMultiSelectM('centerGroup');
    //报备来源
    clearSelectM('customerFromId');
    //项目编号
    clearInput('estateId');
    //楼盘名称
    clearInput('estateNm');
    //报备编号
    clearInput('reportId');
    //门店编号
    clearInput('storeNo');
    //报备日期
    clearInput('reportDateStart');
    clearInput('reportDateEnd');
    //大定日期
    clearInput('roughDateStart');
    clearInput('roughDateEnd');
    //成销日期
    clearInput('dealDateStart');
    clearInput('dealDateEnd');

    initAccountProject();
//    initSerachKey();
    initOrganizationEvent();
    initRegion();
    initMultiSelect();
}


/**
 * 清空下拉框
 *
 * @param selMen
 * @returns
 */
function clearMultiSelectM(selMen) {
    $("#" + selMen).find(".multi-select-item").not(':first').remove();
    $("#" + selMen).find('.multi-select-checkall').removeAttr("checked");
    $("#" + selMen).find('.multi-select-value').val('');
    $("#" + selMen).find('.multi-select-text').val('');
    $("#" + selMen).find('.multi-select-text').text('');
}
/**
 * 清空select下拉框
 * @param selMen
 * @returns
 */
function clearSelectM(selMen) {
    $("#" + selMen).find("option").each(function () {
        $(this).removeAttr("selected");
    });
}
/**
 * 清空input
 * @param selMen
 * @returns
 */
function clearInput(selMen) {
    $("#" + selMen).val('');
}