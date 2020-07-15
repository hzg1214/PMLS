/** ************************公共部分***************************** */

$(function () {
	initMultiSelectM();
	initOrganizationEvent();
    initRegion();
    initMultiSelect();
    initCost('cost');
    fileInterval();
});


/** **************************demo js*************************** */
LinkMarginDetail = function () {
};

/**
 * 初始化查询
 */
initList = function () {
	var areaCitys = $("#areaCity").find(".multi-select-value").val();

	if (isBlank(areaCitys)) {
        Dialog.alertInfo("归属城市必选！");
        return false;
    }
    systemLoading("#contentAll", true);

    var params = {};

    httpGetWithParams('LinkMarginDetailListForm', 'LoadCxt', params, BASE_PATH + '/linkMarginDetail/queryLinkMarginDetailList', function () {

        pageInfo("LinkMarginDetailListForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    }, "1");
};
/**
 * 查询
 *
 */
LinkMarginDetail.search = function () {

//    var flag = marginValidSelect();
    var flag = validSelect();
    if (!flag) {
        return false;
    }

    $('#curPage').val('1');
    initList();
};

LinkMarginDetail.export = function () {

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    var areaCitys = $("#areaCity").find(".multi-select-value").val();

	if (isBlank(areaCitys)) {
        Dialog.alertInfo("归属城市必选！");
        return false;
    }
    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var costCodes = $("#cost").find(".multi-select-value").val();
    var marginTypeCode = $("#marginTypeCode").val();
    var estateNm = $("#estateNm").val();

    var cookieName = guid();
    $("#btn-reset").attr("disabled", true);
    // showExprotLoading(cookieName);

    var url = LOC_RES_BASE_PATH + "/linkMarginDetail/exportLinkMarginDetailAjax?"
        + "organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&marginTypeCode=" + marginTypeCode
        + "&estateNm=" + estateNm
        + "&costCodes=" + costCodes
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
    var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "linkMarginDetail_filepath";

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
    var url = BASE_PATH + '/expendReport/queryReportSize/'+"linkMarginDetail_filepath";
    $.ajax({
        type: "GET",
        url: url,
        async : true,
        dataType:"json",
        contentType: 'application/json',
        success: function(data){
            $("#reportSize").text(data.userReportSize);
        },
        error:function(){
        }
    });
    systemLoaded('#contentAll');
};

/**
 * 初始化下拉框
 * 
 * @param selector
 * @param selectors
 * @returns
 */
function initMultiSelectM(selector, selectors) {
	$("div[name^='costCenter']").find('.multi-select').multiSelect({
		check : function($instance) {
		}
	});

}

/**
 * 加载成本中心
 * @param cost
 * @param type
 * @returns
 */
function initCost(cost,type) {
    clearMultiSelectM(cost);
    var url = BASE_PATH + "/linkMarginDetail/getCostList";
//    var date=new Date;
//    var organization=date.getFullYear();
    var organization = $("#organization").val();
    var params = {
        organization: organization
    };
    ajaxGet(url, params, function(data) {

        var list = data.returnValue;
        if (list != null && list.length > 0) {
            var html = '';
            for (var i = 0; i < list.length; i++) {
                var checked='';
                html = html
                    + '<li class="multi-select-item"> <label><input '+checked+' type="checkbox" value="'
                    + list[i].costCode + '" data-text="'
                    + list[i].costName + '"><span>'
                    + list[i].costName + '</span></label> </li>';
            }
            $('#' + cost).find('.multi-select-list').append(html);
        }
    }, function() {
    });
} 


/**
 * 清空下拉框
 * @returns
 */
function changeOrganization(obj) {
    //成本中心
	clearMultiSelectM('cost');
	//类型
	clearSelectM('marginTypeCode');
    //项目名称
    clearInput('estateNm');
    initOrganizationEvent();
    initRegion();
    initMultiSelect();
    initCost('cost');
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
