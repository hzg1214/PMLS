$(function () {
});


KeFuInvested = function () {
    dialog: null;
};
/**
 * 已调查问卷-查看弹框
 */
KeFuInvested.toInvestedView  = function(id,storeId,StoreNo){

        var url = BASE_PATH + '/keFuWj/toInvestedView';
        var title = '满意度调查';
        var params = {
            id:id,
            storeId:storeId,
            storeNo:StoreNo
        };
        var dialogOptions = {
            width : 900
        };

        Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
            dialog.position('50%', '25%');
            KeFuInvested.dialog = dialog;
        }, dialogOptions);
}  

KeFuInvested.close = function() {
	KeFuInvested.dialog.close();
};
