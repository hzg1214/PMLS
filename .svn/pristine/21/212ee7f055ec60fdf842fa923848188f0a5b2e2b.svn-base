/** ************************公共部分***************************** */
$(function() {
	 //初始化查询
	initList();
});

/** **************************js*************************** */

initList = function (){
	httpGet('excelTasktForm', 'LoadCxt', BASE_PATH + '/excelTask/q', function() {
		
		pageInfo("excelTasktForm", function() {
			initList();
		});
	});
}


/**
 * 查询
 * 
 */
Search = function() {
	$('#curPage').val('1');
	initList();
};

function doUpload(fileName,filePath){
	
	var filePathtmp = filePath.replace(/\\/g,'/');
	
	$('#fileNameId').val(fileName);
	$('#filePathId').val(filePathtmp);
	$("#excelTasktForm").submit();
}

//删除
function doDelete(id,name){
	
	var nametmp = name.replace(/\\/g,'/');
	var result = "是否删除此excel？";
	Dialog.confirm(result, function() {
		$.ajax({
			type : "get",
			async : false,
			url:BASE_PATH + '/excelTask/delete?name='+nametmp+"&id="+id, 
			  success: function(result){
				   var result = eval('('+result+')');
				   if(result.isDelete == 1){
					   Search();
					   Dialog.alertInfo("删除成功",true);
				   }else if(result.isDelete == 0){
					   Dialog.alertError("excel已使用无法删除",true);
				   }
	      },
				error : function(errorMsg) {
					Dialog.alertError("操作失败!");
				}
		});
	});
}








