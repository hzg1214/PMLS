var item = [];

item.push('<div class="thumb-xs-list item-photo-add" >');
item.push('<a href="javscript:void(0);" class="thumbnail swipebox">');
item.push('<span class="thumb-img" style="display: none;">');
item.push('<span class="thumb-img-container">');
item.push('<span class="thumb-img-content">');
item.push('<img src="" class="img empPhoto"/>');
item.push('<label class="fileName" style="display: none;"></label>');
item.push('</span>');
item.push('</span>');
item.push('</span>');
item.push('<span class="thumb-bottom-roup" style="display: none;">');
item.push('<i class="icon down-icon"></i>');
item.push('<i class="icon remove-icon btn-remove-photo"></i>');
item.push('</span>');
item.push('<input type="file" name="fileName" class="btn-flie file-control" onchange="upload(this, \'storeEditForm\');">');

item.push('</a>');
item.push('</div>');
/**
 * 上传图片
 * 
 * @param self
 */
function upload(self, type) {

	//systemLoading("", true);

	var fileSize = 0;
	var photoExt = self.value.substr(self.value.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
	if (photoExt != '.jpg' && photoExt != '.png') {
		Dialog.alertInfo("请上传后缀名为jpg,png的照片!");
		self.value = null;
		systemLoaded();
		return;
	}
	var file = null;
	try {
		var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
		if (isIE && !self.files) {
			var filePath = self.value;
			var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
			file = fileSystem.GetFile(filePath);
			fileSize = file.Size;
		} else {
			file = self.files[0];
			fileSize = file.size;
		}
		fileSize = Math.round(fileSize / 1024 * 100) / 100 / 1024; // 单位为KB
	} catch (e) {
	}
	if (fileSize > 5) {
		Dialog.alertInfo("所选文件不能大于5M！");
		self.value = null;
		systemLoaded();
	} else {

		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function(e) {
			/*$(self).parent().parent().find('img').first().attr("src", this.result);// this.result
			console.log($(self).parent().parent().find('.fileName').first().text(),
					file.name);
			$(self).parent().parent().find('.fileName').first().text(file.name);*/

        	var $item = $(item.join('')).clone();
//			var $item = $(self).parents(".item-photo-add").prev().show();
        	$self = $(self).parents(".item-photo-add");
        	$self.find("img").attr("src",this.result);
        	$self.find(".fileName").text(file.name);
        	$self.find("a").attr("title",file.name);
        	$self.attr("class","thumb-xs-list item-photo-list");
        	$self.find(".thumb-img").show();
        	$self.find(".thumb-bottom-roup").show();
//	    	$self.before($(item.join('')).clone()); 
        	var length = $(self).parents(".thumb-xs-box").children().length;
        	var fileName = "fileName" + length;
        	$self.find(".btn-flie").hide().attr("name",fileName);
	    	if(length<10){
		    	$self.after($item);
	    	}
		};
		
//		var url = BASE_PATH + '/files/upload';
		var url = BASE_PATH + '/file/uploadFile';

		console.log("aa");

		//httpPost(type, url, function(data) {
        //
		//	var fileRecordMainId = data.fileRecordMainId;
		//	$("#fileRecordMainId").val(fileRecordMainId);
        //
		//	var fileNo = data.fileNo;
		//	$("#fileNo").val(fileNo);
        //
		//	console.log(data);
        //
		//	systemLoaded();
        //
		//}, function(data) {
		//	Dialog.alertError(data.returnMsg);
		//	systemLoaded();
		//});
	}
}

$(function(){
	$(document).on('click', '.btn-remove-photo', function(e){
		if($(".thumb-xs-box").find(".item-photo-add").length==0){
        	var $item = $(item.join('')).clone();
			$(".thumb-xs-box").append($item);
		}
	    	
		$(this).parents(".thumb-xs-list").remove();
		return;
	});

    $("#newStoreCity").change(function(){
        $("#newStoreDistrict option").remove();

        var cityNo = $("#newStoreCity").val();
        if(cityNo == null || cityNo == "")
        {
            var html = "<option value='' selected>请选择</option>";
            $('#newStoreDistrict').append(html);
            return false;
        }

        var url = BASE_PATH +  "/linkages/city/" + cityNo;
        var params = {};
        ajaxGet(url, params, function(data) {
            var result = "";
            $.each(data.returnValue, function(n, value) {
                result += "<option value='" + value.districtNo + "'>"
                    + value.districtName + "</option>";
            });
            $("#newStoreDistrict").html('');
            $("#newStoreDistrict").append(result);
        }, function() {
        });
    })
})