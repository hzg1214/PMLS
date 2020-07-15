$(function () {
    linkCityCenter("cityNo", "centerId");
    // 初始化查询
    initList();
});

yfCenterUser = function () {
    dialogCreate: null;
    dialogModify: null;

};

/**
 * 初始化查询
 */
initList = function () {
    httpGet('yfCenterUserListForm', 'LoadCxt', BASE_PATH + '/yfCenterUser/q', function () {
        pageInfo("yfCenterUserListForm", function () {
            initList();
        });

    });
};

/**
 * 查询
 */
yfCenterUser.search = function () {
    $('#curPage').val('1');
    initList();
};

/**
 * 新增画面对话框
 */
yfCenterUser.create = function () {

    var url = LOC_RES_BASE_PATH + '/yfCenterUser/create';
    var title = '人员维护';
    var params = {};

    var dialogOptions = {
        width: 500,
        ok: function () {
            var reback = yfCenterUser.save();
            return reback;
        },
        okVal: '保存',
        cancel: true,
        cancelVal: '取消',
        zIndex: 1000
    };

    Dialog.ajaxOpenDialog(url, params, title, function (dialog, resData) {
        yfCenterUser.dialogCreate = dialog;
    }, dialogOptions);

}

yfCenterUser.closeCreate = function () {
    if (yfCenterUser.dialogCreate != null && !yfCenterUser.dialogCreate.closed) {
        yfCenterUser.dialogCreate.close();
    }
}

/**
 * 新增保存处理
 */
yfCenterUser.save = function () {
    if (Validator.validForm("yfCenterUserCreateForm")) {
        var selectCityNo = $("#newCityNo").val();
        var selectCenterId = $("#newCenterId").val();
        var userCode = $("#newUserCode").val();
        var userName = $("#newUserName").val();

        var url = BASE_PATH + '/yfCenterUser/save';
        var params = {
            cityNo: selectCityNo,
            centerId: selectCenterId,
            userCode: userCode,
            userName: userName
        }
        return asyncPost(url, params, "#newWarningMsg");
    } else {
        return false;
    }
}


function asyncPost(url, params, message) {

    if (url.indexOf("?") > 0) {
        url = url + "&" + rnd();
    } else {
        url = url + "?" + rnd();
    }
    $(message).empty();
    var reback = false;
    systemLoading("", true);
    $.ajax({
        url: url,
        data: params,
        type: 'POST',
        async: false,
        dataType: 'json',
        success: function (data) {
            systemLoaded();
            if (data && data.returnCode == '200') {
                initList();
                reback = true;
            } else {
                $(message).empty().html(data.returnMsg);
                reback = false;
            }
        },
        error: function (data) {
            systemLoaded();
            $(message).empty().html("操作失败");
            systemLoaded();
            reback = false;
        }
    });

    return reback;
}


/**
 * 编辑画面对话框
 */
yfCenterUser.modify = function (id) {

    var url = LOC_RES_BASE_PATH + '/yfCenterUser/modify/' + id;
    var title = '人员维护';
    var params = {};
    var dialogOptions = {
        width: 500,
        ok: function () {
            var reback = yfCenterUser.update(id);
            return reback;
        },
        okVal: '保存',
        cancel: true,
        cancelVal: '取消',
        zIndex: 1000
    };

    Dialog.ajaxOpenDialog(url, params, title, function (dialog, resData) {
        yfCenterUser.dialogModify = dialog;
    }, dialogOptions);

}


yfCenterUser.closeModify = function () {
    if (yfCenterUser.dialogModify != null && !yfCenterUser.dialogModify.closed) {
        yfCenterUser.dialogModify.close();
    }
}

/**
 * 编辑保存处理
 */
yfCenterUser.update = function (yfId) {

    if (Validator.validForm("yfCenterUserModifyForm")) {
        var url = BASE_PATH + '/yfCenterUser/update';

        var selectCityNo = $("#oldCityNo").val();
        var selectCenterId = $("#oldCenterId").val();
        var userCode = $("#oldUserCode").val();
        var userName = $("#oldUserName").val();
        var params = {
            id: yfId,
            cityNo: selectCityNo,
            centerId: selectCenterId,
            userCode: userCode,
            userName: userName
        }
        return asyncPost(url, params, "#oldWarningMsg");

    } else {
        return false;
    }
}

/**
 * 删除处理
 */
yfCenterUser.remove = function (yfId,userCode) {

    $.post(BASE_PATH + "/yfCenterUser/checkWHRIsUsed",{user_code:userCode},function(data){
        data = JSON.parse(data);
        if(data && data.returnCode == '200'){
            if(data.returnValue=='false'){
                Dialog.alertError('友房通存在该经服和门店或项目的维护关系，不允许删除！');
                return false;
            }else{
                $.post(BASE_PATH + "/yfCenterUser/queryYFTBind",{userCode:userCode},function(data){
                    data = JSON.parse(data);
                    if(data && data.returnCode == '200'){
                        if(data.returnValue>0){
                            Dialog.alertError(data.returnMsg);
                            return false;
                        }else{
                            var result = "是否确定删除此信息?</h3>";

                            var params = {
                                id: yfId
                            };

                            Dialog.confirm(result, function () {

                                restPost(BASE_PATH + "/yfCenterUser/delete", params, function (data) {
                                    initList();
                                }, function (data) {
                                    Dialog.alertError(data.returnMsg);
                                });
                            });
                        }
                    }
                });
            }
        }
    });

};


//重置输入内容
yfCenterUser.reset = function () {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    yfCenterUser.search();
};


//填充储存的表单值
yfCenterUser.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);

    $("#yfCenterUserListForm").find("input").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    $("#yfCenterUserListForm").find("select").each(function () {
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

function linkCityCenter(selCity, selCenter) {
    $("#" + selCity + "").change(function () {
        $("#" + selCenter + " option").remove();

        var cityNo = $("#" + selCity).val();
        if (cityNo == null || cityNo == "") {
            var html = "<option value='' selected>请选择</option>";
            $('#' + selCenter).append(html);
            return false;
        }


        var url = BASE_PATH + "/yfCenterUser/city/" + cityNo;
        var params = {};
        ajaxGet(url, params, function (data) {
            var result = "<option value=''>请选择</option>";
            $.each(data.returnValue, function (n, value) {
                result += "<option value='" + value.centerId + "'>"
                    + value.centerName + "</option>";
            });
            $("#" + selCenter).html('');
            $("#" + selCenter).append(result);
        }, function () {
        });
    })
}