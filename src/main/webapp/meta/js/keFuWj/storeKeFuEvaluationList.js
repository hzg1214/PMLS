$(function () {
});


KeFuEvaluation = function () {
    dialog: null;
};
/**
 * 已调查问卷-查看弹框
 */
KeFuEvaluation.toEvaluationView  = function(id,storeId,StoreNo){

        var url = BASE_PATH + '/keFuWj/toEvaluationView';
        var title = '测评';
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
            KeFuEvaluation.dialog = dialog;
        }, dialogOptions);
}  

KeFuEvaluation.close = function() {
	KeFuEvaluation.dialog.close();
};
