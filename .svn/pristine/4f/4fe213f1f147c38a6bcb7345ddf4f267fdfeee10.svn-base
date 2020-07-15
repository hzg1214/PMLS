/**
 * Add By cning 2017.7.11
 */
$(function(){
	initList();
	
})

StoreLog = function() {
};

/**
 * 初始化查询
 */
initList = function() {
	httpGet('storeLogListForm', 'LoadCxt', BASE_PATH
			+ '/store/updateLog', function() {
		pageInfo("storeLogListForm", function() {
			initList();
		});
	});
};


//Add 2017/07/12 QJP Start
/**
 * @author lhd
 * 查看门店修改日志详细
 */
function relateStoreLog(Id,StoreId){
			var url = BASE_PATH + '/store/viewLog';
			var title = "查看";
			var params = {
					Id:Id,
			   StoreId:StoreId
			};
			var dialogOptions = {
					cancel : true,
					cancelVal : '返回'
			};
			// 跳转至添加跟进页面
			Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
				dialog.position('50%', '25%');
				StoreLog.dialog = dialog;
			}, dialogOptions);
};
//Add 2017/07/12 QJP End

//关闭dialog
StoreLog.close = function(){
	StoreLog.dialog.close();
}