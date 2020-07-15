var QtReportDetail = function() {
}
QtReportDetail.close = function() {
	QtReportDetail.dialog.close();
};
QtReportDetail.toOperDetail =function(qtReportId,operStatus) {
	var url = BASE_PATH + "/qtReport/toOperDetail";
	var params = {
		"id" : qtReportId,
		"operStatus" : operStatus
	};

	var dialogOptions = {
		width : 360,
		height : 100
	};
	Dialog.ajaxOpenDialog(url, params, "查看", function(dialog, resData) {
		QtReportDetail.dialog = dialog;
	}, dialogOptions);
};