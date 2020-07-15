$(function () {
});
StoreDepositDeatil = function () {
};
/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll",true);
    var params = {
    };
    httpGetWithParams('StoreDepositDetatilForm', 'LoadCxt', params, BASE_PATH + '/storeDepositDeatil/queryStoreDepositDeatilList', function () {
        pageInfo("StoreDepositDetatilForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    },"1");
};

/**
 * 查询
 *
 */
StoreDepositDeatil.search = function () {
	if(checkForAdd()){
		$('#curPage').val('1');
	    initList();
	}
};

StoreDepositDeatil.export = function () {
	
	if(checkForAdd()){
		var cookieName = guid();
		
	    $("#btn-reset").attr("disabled",true);
	    showExprotLoading(cookieName);
	    
	    
	    var dateCreateStart = $("#dateCreateStart").val();
		var dateCreateEnd = $("#dateCreateEnd").val();
		var storeAcCityNo = $("#storeAcCityNo").val();
		var accountProjectCode = $("#accountProjectCode").val();
		var accountProjectCodeList = $("#accountProjectCodeList").val();
		
		var storeCondition = $("#storeCondition").val();
		var companyCondition = $("#companyCondition").val();
	    window.location.href = LOC_RES_BASE_PATH + '/storeDepositDeatil/exportStoreDepositDeatil?'
	        + "dateCreateStart=" + dateCreateStart
	        + "&dateCreateEnd=" + dateCreateEnd
	        + "&storeAcCityNo=" + storeAcCityNo
	        + "&accountProjectCodeList=" + accountProjectCodeList
	        + "&storeCondition=" + storeCondition
	        + "&companyCondition=" + companyCondition
	        + "&cookieName=" + cookieName;
	    
	    window.setTimeout(function(){
	        $("#btn-reset").attr("disabled",false);
	    },10000);
	}
};

StoreDepositDeatil.reset=function () {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$('form input[type="checkbox"]').attr("checked", false);
	window.location.href = BASE_PATH + '/storeDepositDeatil';
};
function checkDate(self) {
	if ($("#dateCreateEnd").val() != ''
			&& $("#dateCreateStart").val() > $("#dateCreateEnd").val()) {
		Dialog.alertError("创建时间起始时间不能大于结束时间！");
		$("#dateCreateEnd").val('');
		$("#dateCreateStart").val('');
	}
};
function checkForAdd(){
	var dateCreateStart = $("#dateCreateStart").val();
	var dateCreateEnd = $("#dateCreateEnd").val();
	var storeAcCityNo = $("#storeAcCityNo").val();
	var accountProjectCode = $("#accountProjectCode").val();
	
	var storeCondition = $("#storeCondition").val();
	var companyCondition = $("#companyCondition").val();
	if(dateCreateStart == ""){
		Dialog.alertInfo("请选择入账开始日期!");
		return false;
	}
	if(dateCreateEnd == ""){
		Dialog.alertInfo("请选择入账截止日期!");
		return false;
	}
	if(storeAcCityNo == ""){
		Dialog.alertInfo("请选择业绩城市!");
		return false;
	}
	return true;
};


