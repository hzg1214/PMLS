$(function() {
	// 初始化查询
	initList();
});

function changeState() {
	checkState();
}

function initList() {
	checkState();
}

/**
 * 检查装修状态
 */
function checkState() {
	var state = $("#decorationState").val();
	var decorationCompNm = $("#decorationCompNm");
	var decorationContractNo = $("#decorationContractNo");
	var dateDecorationBill = $("#dateDecorationBill");
	var oaFlopNo = $("#oaFlopNo");
	var dateFlopCkAccept = $("#dateFlopCkAccept");
	
	// 未装修
	if (state == 16301) {
	}
	if (state == 16302) {
		decorationCompNm.attr("notnull","true");
	}
	if (state == 16303) {
		decorationCompNm.attr("notnull","true");
		decorationContractNo.attr("notnull","true");
		dateDecorationBill.attr("notnull","true");
		oaFlopNo.attr("notnull","true");
		dateFlopCkAccept.attr("notnull","true");
	}
}
