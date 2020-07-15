var item = [];
var mobileItem = [];

var imgCount = 0;

mobileItem.push('<div class="thumb-xs-list item-photo-list">');
mobileItem.push('<a href="javscript:void(0);" class="thumbnail">');
mobileItem.push('<span class="thumb-img">');
mobileItem.push('<span class="thumb-img-container">');
mobileItem.push('<span class="thumb-img-content">');
mobileItem.push('<img src="" class="empPhoto"/>');
mobileItem.push('</span>');
mobileItem.push('</span>');
mobileItem.push('</span>');
mobileItem.push('</div>');

item.push('<div class="thumb-xs-list item-photo-list">');
item.push('<a href="javscript:void(0);" class="thumbnail swipebox" target="_blank">');
item.push('<span class="thumb-img">');
item.push('<span class="thumb-img-container">');
item.push('<span class="thumb-img-content">');
item.push('<img src="" class="empPhoto"/>');
item.push('</span>');
item.push('</span>');
item.push('</span>');
item.push('<span class="thumb-bottom-roup">');
item.push('<i class="icon down-icon"></i>');
item.push('<i class="icon remove-icon btn-remove-photo"></i>');
item.push('</span>');
item.push('</a>');
item.push('<input type="file" id="file" class="btn-flie file-control hide" data-limit="10"  multiple="multiple">');
item.push('<input type="hidden" name="fileNoHidden">');
item.push('<input type="hidden" name="fileRecordMainIdHidden">');
item.push('<input type="hidden" name="fileTypeId" value="1" />');
item.push('<input type="hidden" name="fileSourceId" value="1" />');
item.push('<input type="hidden" name="oaFileId"  />');
item.push('<input type="hidden" name="contractChangePicId"  />');
	
    
item.push('</div>');

$(function(){
	$(document.getElementsByName("Viewerbox")).each(function(index){
        var viewer = new Viewer(this, {
            url: 'data-original',
            show: function (){
                viewer.update();
            }
        });
	})
});

var photoUploader = function(options,param,successCallback,errorCallback){
	if(options.isMobile){
		item = mobileItem;
	}
	item = item.join('');
	$(document).off("click",".btn-remove-photo").on('click', '.btn-remove-photo', function(e){
		if(options.isDeleteImage){
			if(!checkEditImage(true,true)){
				return false;
			}
		}
		var $this = $(this);
		var fileNo = $(this).parents(".thumb-xs-list").find("input[name='fileNoHidden']").val();
		var $parents = $(this).parents(".thumb-xs-box");
		var fileRecordMainId = $(this).parents(".thumb-xs-list").find("input[name='fileRecordMainIdHidden']").val();
    	var fileTypeId = $parents.find("input[name='fileTypeId']").val();
    	var num = $parents.find("input[name='num']").val();
//    	var pageType = $parents.find("input[name='pageType']").val();
        if(!options.afterDelete){
            //先后删除图片的标识，true后删除，false先删除
			$.ajax({
					type: "POST",
					url: BASE_PATH+"/file/delFileCommon",
					data: {"fileNo":fileNo,"fileRecordMainId":fileRecordMainId},
					async : false,
					success: function(data){
						var jsonObj = jQuery.parseJSON(data) ;
						console.log($(this));
						if(jsonObj.returnCode == 200){
							$this.parent().parent().parent().remove();
							/*if(delFile){
								delFile(fileTypeId, num, pageType);
							}*/
						}else{
							Dialog.alertInfo("图片删除失败");
						}
					},
					error:function(){
						Dialog.alertInfo("图片删除失败");
					}
			});
        }else{
            $this.parent().parent().parent().remove();
            event.stopPropagation();
        }
		var limitSize = $parents.find("input[name='limitSize']").val();
		var fileListSize = $parents.find(".item-photo-list").length;
		if(limitSize>fileListSize){
			$parents.find('.item-photo-add').show();
		}
		return false;
	});

	$(".btn-flie").off("change").on("change",function(){//用此种写法不会出现弹窗关闭后重新打开上传图片重复的情况
	//$(document).off("change",".btn-flie").on('change', '.btn-flie', function(e){
		//debugger;
        imgCount = 0;

		var files = this.files;
		var $this = $(this);
		if(options.isAddImage){
			if(!checkEditImage(this.files)){
				$this.val('');
				return;
			}
		}
		var $parent = $this.parent();
		var $parents = $parent.parent();
		var limitSize = $parents.find("input[name='limitSize']").val();
//    	var key = $parents.find("input[name='key']").val();
//    	var pointCode = $parents.find("input[name='pointCode']").val();
    	var fileTypeId = $parents.find("input[name='fileTypeId']").val();
    	var fileSourceId = $parents.find("input[name='fileSourceId']").val();
    	var companyId = $parents.find("input[name='companyId']").val();
//    	var fileKey2 = $parents.find("input[name='fileKey2']").val();//1:当办证时为permitId
//    	var fileKey3 = $parents.find("input[name='fileKey3']").val();//1:当办证时为processId
    	var keyToken = $parents.find("input[name='keyToken']").val();
//    	var userId = $parents.find("input[name='userId']").val();
//    	var num = $parents.find("input[name='num']").val();

		var refId;
		if( $parents.find("input[name='refId']").size()>0){
			refId = $parents.find("input[name='refId']").val();
		}
    	
    	if(files.length>0){
    		systemLoading("", true);
    		
            var hasFileLength = $this.parents(".thumb-xs-box").find(".item-photo-list").length;
            var fileLength = files.length;
            if(fileLength >= (limitSize - hasFileLength)){
                fileLength = (limitSize - hasFileLength);
                $parents.hide();
			}

			for( var i = 0 ; i < fileLength ; i++ ){  
	//			if(options.isMobile){
	//				if(files[i].type.indexOf('image') === -1){
	//					layer.open({
	//	        		    content: '您选择的文件不是图片！'
	//	        		    ,btn: '关闭'
	//	        		});
	//			      break;
	//			    }
	//        	}
				var formData = new FormData();
	            formData.append('file', files[i]);
	            formData.append('param',JSON.stringify(param));
	//        	formData.append('key',key);
	//        	formData.append('pointCode',pointCode);
	        	formData.append('isMobile',options.isMobile);
	//        	formData.append('fileKey2',fileKey2);
	//        	formData.append('fileKey3',fileKey3);
	        	formData.append('limitSize',limitSize);
	        	formData.append('keyToken',keyToken);
	//        	formData.append('userId',userId);
	//            if(options.isCommonFile){//file公共表
	            	formData.append('fileTypeId',fileTypeId);
	            	formData.append('fileSourceId',fileSourceId);
	            	formData.append('companyId',companyId);
	//            }
//	            var fileListSize = $this.parents(".thumb-xs-box").find(".item-photo-list").length;

				if(refId){
                    formData.append('refId',refId);
				}

	        	var $item = $(item).clone();
	//        	var url = BASE_PATH + "/contracts/oa/upload";

				//modify by haidan 2017/10/17 逻辑更改 上传图片时只传到本地 提交OA审核时再上传至OA
				//$this.val('');
				var bol = uploadFile(options,formData,$item,$parents,fileLength,limitSize,null,fileTypeId,$this);
				if(!bol){
					i=files.length;
				}
				//systemLoaded();
	            /*if(options.oaUrl){
	            	uploadOAFile(options,formData,$item,$parents,fileLength,limitSize,fileTypeId,$this);
	            }else{
            		$this.val('');
	    			var bol = uploadFile(options,formData,$item,$parents,fileLength,limitSize,null,fileTypeId,$this);
	    			if(!bol){
	    				i=files.length;
	    			}
	    			systemLoaded();
	            }*/
			}
    	}
        this.files = null;
	});	
};

function uploadOAFile(options,formData,$item,$parents,fileLength,limitSize,fileTypeId,$this){
	var bol = true;
	$.ajax({
        url : options.oaUrl,
        type : 'POST',
        cache : false,
        data : formData,
        processData : false,
        contentType : false,
        async : true
    }).done(function(json) {
		$this.val('');
        var jsonObj = jQuery.parseJSON(json) ;
		// 返回的文件Id
		var attachmentId = jsonObj.returnValue;
		// 如果为空则弹出提示
		if (!attachmentId) {
			Dialog.alertError("图片上传至OA服务器失败，请重新上传或检查OA权限");
			systemLoaded();
			// 移除图片
			return;
		} else {
			if(options.oaUrl.indexOf("/stopcontract/oa/upload")>-1){
				attachmentId = jsonObj.picId;
			}
			var bol = uploadFile(options,formData,$item,$parents,fileLength,limitSize,attachmentId,fileTypeId,$this);
			if(!bol){
				i=files.length; 
			}
		}
	}, 'json').fail(function() {
		$this.val('');
		Dialog.alertError("图片上传至OA服务器失败，请重新上传或检查OA权限");
		systemLoaded();
		// 移除图片
	});
}

function uploadFile(options,formData,$item,$parents,fileLength,limitSize,attachmentId,type,$this){
	var bol = true;
	$.ajax({
        url : options.url,
        type : 'POST',
        cache : false,
        data : formData,
        processData : false,
        contentType : false,
        async : true
    }).done(function(json) {
        var fileData = jQuery.parseJSON(json) ;
        console.log(fileData);
        if(200==fileData.returnCode){
			if(fileData == null){
				return;
			}
//        	var $item = $(item).clone();
			var rNum=RndNum(4);//随机数
			var fileId = "file"+rNum;
			$item.find("input[type='file']").attr("id",fileId);
			$parents.before($item);
//			if(fileListSize==(limitSize-1)){
//	            $parents.hide();
//	            i=files.length; 
//	            bol = false;
//	        }
//			if(options.isCommonFile){//file公共表
//				var fileData = jsonObj.data;
				var attachmentName = fileData.fileName;
			    var fileAbbrUrl = fileData.fileAbbrUrl;
			    var fileUrl = fileData.fileUrl;
			    var fileRecordMainId = fileData.fileRecordMainId; //文件主键ID
			    var fileIcon = fileData.fileIcon; //文件图片
			    var isImage = fileData.isImage;   //是否图片
			    var fileNo = fileData.fileNo;

			    if(!isImage){//非图片
					$item.find("img").attr("src",BASE_PATH + "/meta/images/share_icon/"+fileIcon);
					$item.find("a").attr("href",BASE_PATH + "/file/downloadFile?fileRecordMainId="+fileRecordMainId);
					$item.find("a").removeClass("swipebox");
			    }else{
			    	$item.find("img").attr("src",fileAbbrUrl);
			    	$item.find("a").attr("href",fileUrl);
			    }
				$item.find("input[name='fileRecordMainIdHidden']").attr("value",fileRecordMainId);
				$item.find("input[name='fileNoHidden']").attr("value",fileNo);
				if(attachmentId!=null){
					if(options.oaUrl.indexOf("/contracts/oa/upload")>-1){
						$item.find("input[name='oaFileId']").attr("value",attachmentId);
					}else if(options.oaUrl.indexOf("/stopcontract/oa/upload")>-1){
						$item.find("input[name='contractChangePicId']").attr("value",attachmentId);
					}
				}
				$item.find("input[name='fileTypeId']").attr("value",$parents.find("input[name='fileTypeId']").attr("value"));
				$item.find("input[name='fileSourceId']").attr("value",$parents.find("input[name='fileSourceId']").attr("value"));
				
				$item.find("a").attr("title",attachmentName);

//				$item.find("input[name='num']").attr("value",rNum);
//				$item.find("input[name^='fileRecordMainId']").attr("id","fileRecordMainId"+ tagArr[type - 1] + rNum );
//				$item.find("input[name^='oaFileId']").attr("id","oaFileId"+tagArr[type - 1] + rNum);
//				$item.find("input[name^='companyId']").attr("id","companyId"+tagArr[type - 1] + rNum);
//				$item.find("input[name='attachType']").attr("value",$parents.find("input[name='attachType']").attr("value"));
//				$item.find("input[name='pageType']").attr("value",$parents.find("input[name='pageType']").attr("value"));
				// 文件上传成功后，数据组织
				
				
//			}else{
//				successCallback(jsonObj,$item,fileId,$this);
//			}
				
				systemLoaded();
        }else{
    		var returnValue = fileData.returnValue;
    		if(returnValue==null || returnValue==''){
    			returnValue = "图片上传失败";
    		}
        	if(options.isMobile){
        		layer.open({
        		    content: returnValue
        		    ,btn: '关闭'
        		});
        	}else{
        		Dialog.alertInfo(returnValue);
        	}
        	
            var fileListSize = $this.parents(".thumb-xs-box").find(".item-photo-list").length;
            if(fileListSize < limitSize){
                $parents.show();
			}
            
            systemLoaded();
        }
    }, 'json').fail(function() {
    	if(options.isMobile){
    		layer.open({
    		    content: '图片上传失败'
    		    ,btn: '关闭'
		    });
    	}else{
    		Dialog.alertInfo("图片上传失败");
    	}
        var fileListSize = $this.parents(".thumb-xs-box").find(".item-photo-list").length;
        if(fileListSize < limitSize){
            $parents.show();
        }
        
        systemLoaded();
    }).always(function(){
        imgCount ++;
        if(imgCount == fileLength){
            systemLoaded();
        }
        
        //置空添加按钮上的值
        $(".btn-flie").val("");
	});
	return bol;
}


/**
 * 获取随机数
 */
function RndNum(n){
	 var rnd="";
	 for(var i=0;i<n;i++)
	 rnd+=Math.floor(Math.random()*10);
	 return rnd;
}

$(document).ready(function(){
	downLoadFile();
});
function downLoadFile(){
    $(document).on("click", ".viewer-flip-download", function(){
        var src = $(".viewer-canvas").find("img").attr("src");
        var fileName = $(".viewer-canvas").find("img").attr("alt");
        //参数格式化
        fileName = formatOptions(fileName);
        if (src) {
            var link = document.createElement('a');
            link.href = BASE_PATH + "/files/downloadFile"+"?fileName=" + fileName + "&fileUrl=" + src;
            link.style.cssText = 'display:none;position:absolute;left:-9999px;top:-9999px;';
            document.body.appendChild(link);
            link.click();
            setTimeout(function () {
                document.body.removeChild(link);
            }, 5000);
        }
        return false;
    });
}