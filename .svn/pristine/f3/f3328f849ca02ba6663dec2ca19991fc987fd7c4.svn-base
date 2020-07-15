$(function () {
});
StoreDepositSerial = function () {
};
/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll",true);
    var params = {
    };
    httpGetWithParams('StoreDepositSerialForm', 'LoadCxt', params, BASE_PATH + '/storeDepositSerial/queryStoreDepositSerialList', function () {
        pageInfo("StoreDepositSerialForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    },"1");
};

/**
 * 查询
 *
 */
StoreDepositSerial.search = function () {
	if(checkForAdd()){
		$('#curPage').val('1');
	    initList();
	}
};

StoreDepositSerial.export = function () {
	
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
		
		var agreementNo = $("#agreementNo").val();
		var oaNo = $("#oaNo").val();
		var orderType = $("#orderType").val();
	    window.location.href = LOC_RES_BASE_PATH + '/storeDepositSerial/exportStoreDepositSerial?'
	        + "dateCreateStart=" + dateCreateStart
	        + "&dateCreateEnd=" + dateCreateEnd
	        + "&storeAcCityNo=" + storeAcCityNo
	        + "&accountProjectCodeList=" + accountProjectCodeList
	        + "&storeCondition=" + storeCondition
	        + "&companyCondition=" + companyCondition
	        + "&agreementNo=" + agreementNo
	        + "&oaNo=" + oaNo
	        + "&orderType=" + orderType
	        + "&cookieName=" + cookieName;
	    
	    window.setTimeout(function(){
	        $("#btn-reset").attr("disabled",false);
	    },10000);
	}
};

StoreDepositSerial.reset=function () {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$('form input[type="checkbox"]').attr("checked", false);
	window.location.href = BASE_PATH + '/storeDepositSerial';
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
	if(storeAcCityNo == ""){
		Dialog.alertInfo("请选择业绩城市!");
		return false;
	}
	if(dateCreateStart == ""){
		Dialog.alertInfo("请选择入账开始日期!");
		return false;
	}
	if(dateCreateEnd == ""){
		Dialog.alertInfo("请选择入账截止日期!");
		return false;
	}
	return true;
};



