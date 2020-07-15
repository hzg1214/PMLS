/**
 * Created by haidan on 2019/10/16.
 */
$(function () {
});

QTDealBack = function () {
};

QTDealBack.save = function () {
    var dealDate = $("#dealDate").val();
    var backDealDate = $("#backDealDate").val();
    var memo = $("#memo").val();

    var befYJSSAmount = $("#befYJSSAmount").val();
    var aftYJSSAmount = $("#aftYJSSAmount").val();
    var befSJFYAmount = $("#befSJFYAmount").val();
    var aftSJFYAmount = $("#aftSJFYAmount").val();

    if (isNullEmpty(backDealDate)) {
        Dialog.alertError("请选择退成销日期！");
        systemLoaded();
        return false;
    } else if (backDealDate < dealDate) {
        Dialog.alertError("退成销日期应大于或等于成销日期！");
        systemLoaded();
        return false;
    }

    if (isNullEmpty(memo)) {
        Dialog.alertError("请填写退成销原因！");
        systemLoaded();
        return false;
    } else if (memo.length > 200) {
        Dialog.alertError("退成销原因不能超过200字！");
        systemLoaded();
        return false;
    }

    if (isNotZero(befYJSSAmount) || isNotZero(aftYJSSAmount)) {
        Dialog.alertError("该订单已有应计实收数据，不允许退成销！");
        systemLoaded();
        return false;
    }

    if (isNotZero(befSJFYAmount) || isNotZero(aftSJFYAmount)) {
        Dialog.alertError("该订单已有实际返佣数据，不允许退成销！");
        systemLoaded();
        return false;
    }

    var url = BASE_PATH + '/qtSuccessSale/dealBack';
    httpPost('dealBackForm', url, function (data) {
        window.history.back(-1);
    }, function (data) {
        Dialog.alertError(data.returnMsg);
        systemLoaded();
    });

};

function isNullEmpty(obj) {
    if (obj == null || obj == "" || obj == undefined) {
        return true;
    } else {
        return false;
    }
}

function isNotZero(obj) {
    if (obj != null && obj != undefined && obj != "" && obj > 0) {
        return true;
    } else {
        return false;
    }
}

QTDealBack.backPre=function () {
    window.history.back(-1);
}
