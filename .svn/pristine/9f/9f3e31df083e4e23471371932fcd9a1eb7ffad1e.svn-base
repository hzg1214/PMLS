/** ************************公共部分***************************** */
$(function () {
    initOrgList();
    // 初始化查询
    //initList();
});

/** **************************demo js*************************** */
StoreStop = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {
    var params = {
        "cityIdStr": cityIdStr,
        "centerIdStr": centerIdStr
    };

    httpGetWithParams('storeStopDetailForm', 'LoadCxt',params, BASE_PATH + '/storeStopReport/query', function () {
        pageInfo("storeStopDetailForm", function () {
            initList();
        });
    });
};
/**
 * 查询
 *
 */
StoreStop.search = function () {
    var result = valid();
    if (result) {
        initList();
    }
};

StoreStop.export = function () {
    var result = valid();
    if (result) {
        var cookieName = guid();
        showExprotLoading(cookieName);

        window.location.href = LOC_RES_BASE_PATH + '/storeStopReport/export?dateStart=' + $('#dateStart').val() + '&dateEnd='+$('#dateEnd').val() + '&cityIdStr=' + cityIdStr
            + '&centerIdStr=' + centerIdStr + "&cookieName=" + cookieName;
    }

};

function checkDate(self) {
    if ($("#dateEnd").val() != ''
        && $("#dateStart").val() > $("#dateEnd").val()) {
        Dialog.alertError("当期日期起始日不能大于结束日！");
        $(self).val('');
    }
};

function valid() {
    if (isBlank($("#org").val())) {
        Dialog.alertError("请选择架构！");
        return;
    }

    if (isBlank(cityIdStr)) {
        Dialog.alertInfo('请选择城市！')
        return;
    }

    if (isBlank($('#dateStart').val()) || isBlank($('#dateEnd').val())) {
        Dialog.alertInfo("请选择日期！");
        return false;
    }

    return true;
}