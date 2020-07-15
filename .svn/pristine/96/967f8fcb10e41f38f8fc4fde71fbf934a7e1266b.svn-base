/*
 * 渠道品牌js
 * */
var layer;
var id=window.id;
var jsonDto={};

var form,upload,table;
layui.use(['layer','table'], function() {
    layer = layui.layer;
    table=layui.table;

    setTimeout(function(){
        if(window.frameContractDto!=''){
            jsonDto=eval('(' + window.frameContractDto.replace(/[\r\n]/g,"\\n") + ')');
            //初始化图片

            //初始化加载图片
            loadImageList('fileBusiness',jsonDto.fileBusinessList);
            loadImageList('fileContract',jsonDto.fileContractList);
            loadImageList('fileOther',jsonDto.attachmentFileList);

            //是否显示提交审核按钮
            if(window.oaOperator=='1' && (jsonDto.approveState=='10401' || jsonDto.approveState=='10404')){//草签或审核未通过
                if(jsonDto.submitOAStatus!='21202'){//不等于处理中
                    $(".submitOABtn").show();
                }
            }
        }
    },100)

});
//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}

//提交审核
function submitOA(contractId){
//	var curIndex = parent.layer.load(2);
//    parent.layer.load(2);
    
    var accountProjectLenth = $("#accountProjectLenth").val();
    //根据当前登录人获取核算主体数量不止一个，进入选择页面
    if($("#autoToOa").val()!=1){
//    	parent.layer.close(curIndex);//关闭弹窗
	    if(accountProjectLenth != 1){
	    	parent.layer.open({
	    		type : 2,
	    		title : '选择核算主体',
	    		area : [ '400px', '300px' ],
	    		content : '/pmlsFrameContract/toSelAccountProject',
	    		scrollbar : false,
	    		resize : false,
	    		btn : [ '确定', '取消' ],
	    		yes : function(index, layero) {
	    			//确认的回调
	    			var iframeWin = parent.window[layero.find('iframe')[0]['name']];
	    			var formData = iframeWin.getFormData();
	    			console.log(formData);
	    			if (formData != null) {
	    				parent.layer.close(index);//关闭弹窗
	    				var accountProject = formData.accountProject;
	    				var accountProjectCode = formData.accountProjectCode;
	    				var saveUrl = BASE_PATH + '/frameContract/update';
	    				var param = {
	    					"id" : contractId,
	    					"accountProject" : accountProject,
	    					"accountProjectCode" : accountProjectCode,
	    				};
	    				$.ajax({
	    					url : saveUrl, 
	    					type : 'POST',
	    					data : param,
	    					async: false,
	    					success:function(data){
	    					},
	    					error:function(){
	    					}
	    				});
	    			}
	    			parent.layer.closeAll();
	    			submitToOA();
	    		}
	    	});
	    }else{
	    	var accountProject = $("#accountProject").val();
	        var accountProjectCode = $("#accountProjectCode").val();
	        $.ajax({
	            url:BASE_PATH+"/frameContract/update",
	            async: false,
	            data:{
	                id : contractId,
	                accountProject	: accountProject,
	                accountProjectCode:accountProjectCode
	            },
	            type:"post",
	            success:function(data){
	            },
	            error:function(){
	            }
	        });
	        parent.layer.closeAll();
	        submitToOA();
	    }
    }
}


function submitToOA(){
	$("#submitToOA").hide();
	ajaxGet(ctx+"/pmlsFrameContract/submitToOA/"+window.id,null,function (data) {
		if(data.returnCode != "200"){
			parent.layer.closeAll();
			parent.msgError(data.returnMsg);
		}else{
			parent.layer.closeAll();
			parent.msgAlert("提交成功!");
			window.location.reload();
		}
	},function (data) {
		parent.layer.closeAll();
		if(data.returnMsg){
			parent.msgError(data.returnMsg);
		}
	});
}
