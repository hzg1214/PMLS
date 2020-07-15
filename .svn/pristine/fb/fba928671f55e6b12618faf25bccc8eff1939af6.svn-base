/*****************************初始化******************************/
/*****************************初始化******************************/

storeDecorationRecord = function(){};

//再次申请装修确认
storeDecorationRecord.toAdd = function(storeId,userId){
	var url = BASE_PATH + "/storeDecoration/toAdd/"+storeId+"/"+userId;
	var result = "是否确认再次申请装修？";
	Dialog.confirm(result, function() {
			// 新增修改记录
			httpPost("decorationForm" , url, function(data) {
//				Dialog.alertSuccess("申请成功");
				if (data.returnCode == '200') {
					location.href = BASE_PATH + "/storeDecoration/toView/"+storeId;
				} else {
					//Dialog.alertError(data.returnMsg);
				}
				
			}, function(data) {
				//Dialog.alertError(data.returnMsg);
			});
	});
};
//休眠一秒
function sleep(numberMillis) { 
	var now = new Date(); 
	var exitTime = now.getTime() + numberMillis; 
	while (true) { 
	now = new Date(); 
	if (now.getTime() > exitTime) 
	return; 
	} 
	}