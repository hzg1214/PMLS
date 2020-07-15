$(function() {
});
//基本信息标签
var markHtml =  '<input type="text" class="form-control w125" name="mark[0]" placeholder="" maxlength="4">'
	         	+'<span class="btn btn-link btn-add-input-tag" onclick="delMark(this)"> 删除</span>';
//在售户型标签
var labelHtml = '<input type="text" class="form-control w125" name="label[0]" placeholder="" maxlength="30">'
    			+'<span class="btn btn-link btn-add-input-tag" onclick="delLabel(this)"> 删除</span>';
//梯户
var stairCase = '<ul class="stair list-inline form-inline">'
		+ '<li>'
		+ '<div class="form-group">'
		+ '<label class="fw-normal w100 text-right"></label>'
		+ '<input type="text" class="form-control w50" name="staircase[0]" placeholder="" dataType="posNumWithZero">梯'
		+ '<input type="text" class="form-control w50" name="household[0]" placeholder="" dataType="posNumWithZero">户'
		+ '<span onclick="delStair(this)" class="btn btn-link btn-add-houst-type"> 删除</a>' + '</span>'
		+ '</div>' + '</li>' + '</ul>';
//周边配套
var matchHtml = '<ul class="match list-inline form-inline"> '
		+ '<li> '
		+ '<div class="form-group"> '
		+ '<label class="fw-normal w100 text-right">类型：</label>'
		+ '<input type="text" class="form-control w100" name="title[0]" placeholder="" maxlength="30">'
		+ '<label class="fw-normal w100 text-right">描述：</label>'
		+ '<input type="text" class="form-control w300" name="description[0]" placeholder="" maxlength="100"> '
		+ '<span onclick="delMatch(this)" class="btn btn-link btn-add-descriptions"> 删除</span> '
		+ '</div>' + '</li>' + '</ul>';

//基本信息标签
function addMark(obj){
	var $myobj = $(obj).parent();
	var flag=true;
	$($myobj).children("input").each(function(){
		if($(this).val() == undefined || $(this).val()==""){
			Dialog.alertError("请录入标签内容！");
			flag=false;
			return false;
		}
	});
	if(flag==false){
		return;
	}
    if($($myobj).children("input").length == 4){
        Dialog.alertError("最大支持4个标签！");
        return false;
    }
	var name=$($myobj).children("input").last().attr('name');
	var num=name.substring(name.indexOf("[")+1,name.indexOf("]"));
	num=parseInt(num)+1;
	var tempHtml = markHtml.replace(/mark\[0\]/g,"mark["+num+"]");
    $($myobj).append(tempHtml);
}
//基本信息标签
function delMark(obj){
	$(obj).prev().remove();
	$(obj).remove();
}
//在售户型标签
function addLabel(obj){
	var $myobj = $(obj).parent();
	var flag=true;
	$($myobj).children("input").each(function(){
		if($(this).val() == undefined || $(this).val()==""){
			Dialog.alertError("请录入标签内容！");
			flag=false;
			return false;
		}
	});
	if(flag==false){
		return;
	}
	var name=$($myobj).children("input").last().attr('name');
	var num=name.substring(name.indexOf("[")+1,name.indexOf("]"));
	num=parseInt(num)+1;
	var tempHtml = labelHtml.replace(/label\[0\]/g,"label["+num+"]");
    $($myobj).append(tempHtml);
}
//在售户型标签
function delLabel(obj){
	$(obj).prev().remove();
	$(obj).remove();
}
//梯户
function addStair(obj){
	var $myobj = $(obj).parent().parent().parent().parent();
	var flag=true;
	$($myobj).children(".stair:last").children("li").children("div").children("input").each(function(){
		if($(this).val() == undefined || $(this).val()==""){
			Dialog.alertError("请录入梯户内容！");
			flag=false;
			return false;
		}
	});
	if(flag==false){
		return;
	}
	var name = $($myobj).children(".stair:last").children("li").children("div").children("input").last().attr('name');
	var num=name.substring(name.indexOf("[")+1,name.indexOf("]"));
	num=parseInt(num)+1;
	var tempHtml = stairCase.replace(/staircase\[0\]/g,"staircase["+num+"]");
	tempHtml = tempHtml.replace(/household\[0\]/g,"household["+num+"]");
	$($myobj).children(".stair:last").after(tempHtml);
}
//梯户
function delStair(obj){
	$(obj).parent().parent().parent().remove();
}
//周边配套
function addMatch(obj){
	var $myobj = $(obj).parent().parent().parent().parent();
	var flag=true;
	$($myobj).children(".match:last").children("li").children("div").children("input").each(function(){
		if($(this).val() == undefined || $(this).val()==""){
			Dialog.alertError("请录入类型和描述！");
			flag=false;
			return false;
		}
	});
	if(flag==false){
		return;
	}
	var name = $($myobj).children(".match:last").children("li").children("div").children("input").last().attr('name');
	var num=name.substring(name.indexOf("[")+1,name.indexOf("]"));
	num=parseInt(num)+1;
	var tempHtml = matchHtml.replace(/title\[0\]/g,"title["+num+"]");
	tempHtml = tempHtml.replace(/description\[0\]/g,"description["+num+"]");
	$($myobj).children(".match:last").after(tempHtml);
}
//周边配套
function delMatch(obj){
	$(obj).parent().parent().parent().remove();
}

//图片上传
//panel-photo-uploader
var item;
var dPotoId = "";
var deleteTypeId = "";
var uploadPhotoId1 = "";
var uploadPhotoId2 = "";
var uploadPhotoId3 = "";
var uploadPhotoId4 = "";
var uploadPhotoId5 = "";
var uploadPhotoId6 = "";
var uploadPhotoId7 = "";
var uploadPhotoId8 = "";
var uploadPhotoId81 = "";
var uploadPhotoId9 = "";
var uploadPhotoId91 = "";
var uploadPhotoId10 = "";
var uploadPhotoId101 = "";
var uploadPhotoId11 = "";
var uploadPhotoId111 = "";

/*var photoUploader = function(){
  item = [];
  item.push('<li class="item_photo_uploader">');
  item.push('<span class="btn_remove_photo" onclick="removePhoto(this);"></span><img src="" class="img-thumbnail"/><span onclick="setCover(this);" data="0" class="set-cover">设为封面</span><i class="cover_bg"></i>');
  item.push('</li>');
  item = item.join('');
  uploadPhotoId1 = $("#uploadPhotoId1").val();
  uploadPhotoId2 = $("#uploadPhotoId2").val();
  uploadPhotoId3 = $("#uploadPhotoId3").val();
  uploadPhotoId4 = $("#uploadPhotoId4").val();
  uploadPhotoId5 = $("#uploadPhotoId5").val();
  uploadPhotoId6 = $("#uploadPhotoId6").val();
  uploadPhotoId7 = $("#uploadPhotoId7").val();
  uploadPhotoId8 = $("#uploadPhotoId8").val();
  uploadPhotoId81 = $("#uploadPhotoId81").val();
  uploadPhotoId9 = $("#uploadPhotoId9").val();
  uploadPhotoId91 = $("#uploadPhotoId91").val();
  uploadPhotoId10 = $("#uploadPhotoId10").val();
  uploadPhotoId101 = $("#uploadPhotoId101").val();
  uploadPhotoId11 = $("#uploadPhotoId11").val();
  uploadPhotoId111 = $("#uploadPhotoId111").val();
};*/

function setCover(obj){
	var $this = $(obj);
	$(".item_photo_uploader").removeClass("item_cover");
	$this.parent().addClass("item_cover");
    $(".set-cover").attr("data","0");
    $this.attr("data","1");
    var imgName=$this.next().next().attr("name");
    if(imgName=="houseTypePhotos"||imgName=="templetPhotos"){
    	var imgId=$this.prev().attr("id");
    	$("#houseCoverImg").val(imgId);
    }else if(imgName=="photo1"||imgName=="photo2"||imgName=="photo3"||imgName=="photo4"||imgName=="photo5"){
    	var imgId=$this.prev().attr("id");
    	$("#estateCoverImg").val(imgId);
    }
}

function removePhoto(obj){
	var $ul = $(obj).parent().parent();
	var picId="";
    if ($(obj).next().attr("id") != null){
    	picId = $(obj).next().attr("id");
    	picId=picId.trim();
      }
    var isCover=$(obj).next().next().attr("data");
    var imgName=$(obj).next().next().next().next().attr("name");
    if(imgName==undefined){
    	imgName=$(obj).next().next().attr("name");
    }
    if(imgName=="houseTypePhotos"){
    	dPotoId=uploadPhotoId6.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId6=newPhotoId.join(",");
    	$("#uploadPhotoId6").val(uploadPhotoId6);
    	if(isCover==1){
    		$("#houseCoverImg").val("");
        }
    }else if(imgName=="templetPhotos"){
    	dPotoId=uploadPhotoId7.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId7=newPhotoId.join(",");
    	$("#uploadPhotoId7").val(uploadPhotoId7);
    	if(isCover==1){
    		$("#houseCoverImg").val("");
        }
    }else if(imgName=="photo1"){
    	dPotoId=uploadPhotoId1.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId1=newPhotoId.join(",");
    	$("#uploadPhotoId1").val(uploadPhotoId1);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="photo2"){
    	dPotoId=uploadPhotoId2.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId2=newPhotoId.join(",");
    	$("#uploadPhotoId2").val(uploadPhotoId2);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="photo3"){
    	dPotoId=uploadPhotoId3.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId3=newPhotoId.join(",");
    	$("#uploadPhotoId3").val(uploadPhotoId3);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="photo4"){
    	dPotoId=uploadPhotoId4.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId4=newPhotoId.join(",");
    	$("#uploadPhotoId4").val(uploadPhotoId4);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="photo5"){
    	dPotoId=uploadPhotoId5.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId5=newPhotoId.join(",");
    	$("#uploadPhotoId5").val(uploadPhotoId5);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="houseTypePhotos8"){
    	dPotoId=uploadPhotoId8.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId8=newPhotoId.join(",");
    	$("#uploadPhotoId8").val(uploadPhotoId8);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="templetPhotos81"){
    	dPotoId=uploadPhotoId81.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId81=newPhotoId.join(",");
    	$("#uploadPhotoId81").val(uploadPhotoId81);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="houseTypePhotos9"){
    	dPotoId=uploadPhotoId9.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId9=newPhotoId.join(",");
    	$("#houseTypePhotos9").val(uploadPhotoId9);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="templetPhotos91"){
    	dPotoId=uploadPhotoId91.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId91=newPhotoId.join(",");
    	$("#uploadPhotoId91").val(uploadPhotoId91);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="houseTypePhotos10"){
    	dPotoId=uploadPhotoId10.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId10=newPhotoId.join(",");
    	$("#houseTypePhotos10").val(uploadPhotoId10);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="templetPhotos101"){
    	dPotoId=uploadPhotoId101.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId101=newPhotoId.join(",");
    	$("#uploadPhotoId101").val(uploadPhotoId101);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }else if(imgName=="houseTypePhotos111"){
    	dPotoId=uploadPhotoId111.split(",");
    	var newPhotoId=new Array();
    	$.each(dPotoId,function(i,val){
            if(val!=picId){
            	newPhotoId.push(val);
            }
        }); 
    	uploadPhotoId111=newPhotoId.join(",");
    	$("#houseTypePhotos111").val(uploadPhotoId111);
    	if(isCover==1){
    		$("#estateCoverImg").val("");
        }
    }
    var limit = $ul.find('.item_photo_uploader_add').find(".btn-add-photo").data('limit');
    $(obj).parent().fadeOut(function(){
    	$(obj).parent().remove();
      if($ul.find('.item_photo_uploader').size()<= limit){
        $ul.find('.item_photo_uploader_add').show();
      };
    });
}

function addPhoto(obj) {
    var $this = $(obj);
    var $li = $this.parent();
    var $ul = $li.parent();
    if ($ul.find('.item_photo_uploader').size() > 10) {
        alert('上传图片数目超过上限！');
        return false;
    }
    $("#btn-save").attr("disabled", "disabled");
    $("#btn-submit").attr("disabled", "disabled");
    var file = obj.files[0];//this.files[0];
    if (file.type.indexOf('image') === -1) {
        alert('您选择的文件不是图片！');
        return false;
    }
    ;
    var fileSize = ((file.size / 1024) / 1024).toFixed(2);
    if (fileSize > 5) {
        alert("所选文件不能大于5M！");
        obj.value = null;
        return false;
    }
    var limit = $this.data('limit');
    var name = $this.data('name');
    var formdata = new FormData();
    formdata.append('file', file);
    formdata.append('name', name);
//    formdata.append('estateId', $("#estateId").val());

//    console.log($this.val());
    var $clone = $this.clone();
    $clone.prependTo($li);
    $this.addClass("hide");
    $this.removeAttr('accept');
    $this.removeAttr('capture');
    $this.removeAttr('data-limit');
    $this.removeAttr('data-name');
    $this.attr('name', name);
    if (name == 'houseTypePhotos' || name == 'templetPhotos') {
        item = [];
        item.push('<li class="item_photo_uploader">');
        item.push('<span class="btn_remove_photo" onclick="removePhoto(this);"></span><img src="" class="img-thumbnail"/>');
        item.push('</li>');
        item = item.join('');
    } else {
        item = [];
        item.push('<li class="item_photo_uploader">');
        item.push('<span class="btn_remove_photo" onclick="removePhoto(this);"></span><img src="" class="img-thumbnail"/><span onclick="setCover(this);" data="0" class="set-cover">设为封面</span><i class="cover_bg"></i>');
        item.push('</li>');
        item = item.join('');
    }
    var $item = $(item);
    $item.append($this);
    $item.find('.btn_remove_photo').data('limit', limit).hide();
    $li.before($item);
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function (e) {
        $item.find('img').attr("src", this.result);//this.result
    };
    if ($ul.find('.item_photo_uploader').size() > limit) {
        $ul.find('.item_photo_uploader_add').hide();
    } else {
        $ul.find('.item_photo_uploader_add').show();
    }
    ;
    $.ajax({
        type: "POST",
        //url:BASE_PATH + '/estate/upload',
        url: BASE_PATH + '/file/uploadFile/linkage',
        data: formdata,
        processData: false,
        contentType: false,
        dataType: "json",
        success: function (data) {
            if (name == "photo1") {
                if (uploadPhotoId1 != "") {
                    uploadPhotoId1 += ",";
                }
                uploadPhotoId1 += data.returnValue;
                $("#uploadPhotoId1").val(uploadPhotoId1);
            } else if (name == "photo2") {
                if (uploadPhotoId2 != "") {
                    uploadPhotoId2 += ",";
                }
                uploadPhotoId2 += data.returnValue;
                $("#uploadPhotoId2").val(uploadPhotoId2);
            } else if (name == "photo3") {
                if (uploadPhotoId3 != "") {
                    uploadPhotoId3 += ",";
                }
                uploadPhotoId3 += data.returnValue;
                $("#uploadPhotoId3").val(uploadPhotoId3);
            } else if (name == "photo4") {
                if (uploadPhotoId4 != "") {
                    uploadPhotoId4 += ",";
                }
                uploadPhotoId4 += data.returnValue;
                $("#uploadPhotoId4").val(uploadPhotoId4);
            } else if (name == "photo5") {
                if (uploadPhotoId5 != "") {
                    uploadPhotoId5 += ",";
                }
                uploadPhotoId5 += data.returnValue;
                $("#uploadPhotoId5").val(uploadPhotoId5);
            } else if (name == "houseTypePhotos") {
                if (uploadPhotoId6 != "") {
                    uploadPhotoId6 += ",";
                }
                uploadPhotoId6 += data.returnValue;
                $("#uploadPhotoId6").val(uploadPhotoId6);
            } else if (name == "templetPhotos") {
                if (uploadPhotoId7 != "") {
                    uploadPhotoId7 += ",";
                }
                uploadPhotoId7 += data.returnValue;
                $("#uploadPhotoId7").val(uploadPhotoId7);
            } else if (name == "houseTypePhotos8") {
                if (uploadPhotoId8 != "" && uploadPhotoId8 != undefined) {
                    uploadPhotoId8 += ",";
                }
                uploadPhotoId8 += data.returnValue;
                $("#uploadPhotoId8").val(uploadPhotoId8);
            } else if (name == "templetPhotos81") {
                if (uploadPhotoId81 != "" && uploadPhotoId8 != undefined) {
                    uploadPhotoId81 += ",";
                }
                uploadPhotoId81 += data.returnValue;
                $("#uploadPhotoId81").val(uploadPhotoId81);
            } else if (name == "houseTypePhotos9") {
                if (uploadPhotoId9 != "" && uploadPhotoId8 != undefined) {
                    uploadPhotoId9 += ",";
                }
                uploadPhotoId9 += data.returnValue;
                $("#uploadPhotoId9").val(uploadPhotoId9);
            } else if (name == "templetPhotos91") {
                if (uploadPhotoId91 != "" && uploadPhotoId8 != undefined) {
                    uploadPhotoId91 += ",";
                }
                uploadPhotoId91 += data.returnValue;
                $("#uploadPhotoId91").val(uploadPhotoId91);
            } else if (name == "houseTypePhotos10") {
                if (uploadPhotoId10 != "" && uploadPhotoId8 != undefined) {
                    uploadPhotoId10 += ",";
                }
                uploadPhotoId10 += data.returnValue;
                $("#uploadPhotoId10").val(uploadPhotoId10);
            } else if (name == "templetPhotos101") {
                if (uploadPhotoId101 != "" && uploadPhotoId8 != undefined) {
                    uploadPhotoId101 += ",";
                }
                uploadPhotoId101 += data.returnValue;
                $("#uploadPhotoId101").val(uploadPhotoId101);
            } else if (name == "houseTypePhotos11") {
                if (uploadPhotoId11 != "" && uploadPhotoId8 != undefined) {
                    uploadPhotoId11 += ",";
                }
                uploadPhotoId11 += data.returnValue;
                $("#uploadPhotoId11").val(uploadPhotoId11);
            } else if (name == "templetPhotos111") {
                if (uploadPhotoId111 != "" && uploadPhotoId8 != undefined) {
                    uploadPhotoId111 += ",";
                }
                uploadPhotoId111 += data.returnValue;
                $("#uploadPhotoId111").val(uploadPhotoId111);
            }
            $item.find('.btn_remove_photo').show();
            $item.find('.img-thumbnail').attr('id', data.returnValue);
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#btn-save").attr("disabled", false);
            $("#btn-submit").attr("disabled", false);
        },
        error: function () {
            Dialog.alertInfo('图片上传失败！请尝试重新上传！');
        }
    });
}

//checkbox选中事件
//建筑类型
function typeKbnChange(){
	var result = new Array();
    $("[name = typeKbnCB]:checkbox").each(function () {
        if ($(this).is(":checked")) {
            result.push($(this).attr("value"));
        }
    });
    $("#typeKbn").val(result.join(","));
}
//物业类型
function mgtKbnChange(){
    var result = new Array();
    $("[name = mgtKbnCB]:checkbox").each(function () {
        if ($(this).is(":checked")) {
            result.push($(this).attr("value"));
        }
    });
    $("#mgtKbn").val(result.join(","));
}
//装修情况
function decorationKbnChange(){
    var result = new Array();
    $("[name = decorationKbnCB]:checkbox").each(function () {
        if ($(this).is(":checked")) {
            result.push($(this).attr("value"));
        }
    });
    $("#decorationKbn").val(result.join(","));
}

function districtChange(){
	var url = BASE_PATH + "/linkages/area/"
			+ $("#districtNo").val();
	var params = {};
	ajaxGet(url, params, function(data) {
		var result = "<option value=''>请选择板块</option>";
		$.each(data.returnValue, function(n, value) {
			result += "<option value='" + value.areaNo + "'>"
					+ value.areaName + "</option>";
		});
		$("#areaNo").html('');
		$("#areaNo").append(result);
	}, function() {
	});
}

function getEmpList() {
	$("#deptId").change(
	function() {
		if ($("#deptId").val() != '') {
			var url = BASE_PATH + "/estate/empList/" + $("#deptId").val();
			var params = {};

			ajaxGet(url, params, function(data) {
				var result = "<option value=''>请选择我方负责人</option>";
				$.each(data.returnValue, function(n, value) {
					result += "<option value='" + value.userId + "'>"
							+ value.userName + "</option>";
				});
				$("#empId").html('');
				$("#empId").append(result);
			}, function() {
			});
		} else {
			var result = "<option value=''>请选择我方负责人</option>";
			$("#empId").html('');
			$("#empId").append(result);
		}
	});
}
function getcommissionStatus() {
	 $("input[name=commissionKbn]").each(function(){
	        $(this).click(function(){
	            var commissionStatus = $(this).val();
	            if(commissionStatus=="1482"){
	            	  $("#commissionStatus").text("%");
	            } else {
	              $("#commissionStatus").text("元");
	            }
	        });
	    });
	 var commissionStatus2=$("input[name='commissionKbn']:checked").val();
	   if(commissionStatus2=="1482"){
		   $("#commissionStatus").text("%");
	  } else {
		  $("#commissionStatus").text("元");
	  }
  }
function checkItem(type){
	if ($("#districtNo").val() == "" /*|| $("#areaNo").val() == ""*/  || $("#address").val() == "") {
		Dialog.alertInfo("楼盘区域板块地址信息不能为空！");
		return false;
	}
	if ($('input[name="salesStatus"]:checked').val() == undefined
			|| $('input[name="salesStatus"]:checked').val() == "") {
		Dialog.alertInfo("请选择销售状态");
		return false;
	}
	//是否独家
	if ($('input[name="particularFlag"]:checked').val() == undefined
			|| $('input[name="particularFlag"]:checked').val() == "") {
		Dialog.alertInfo("请选择是否独家");
		return false;
	}
	//总价段大小校验
	var min = $("#salePriceUnitMin").val();
	var max = $("#salePriceUnitMax").val();	
	if(Number(min)>Number(max)){
		Dialog.alertInfo("最大总价段必须大于最小总价段");
		return false;
	}
	if ($('input[name="partner"]:checked').val() == undefined
			|| $('input[name="partner"]:checked').val() == "") {
		Dialog.alertInfo("请选择合作方类型");
		return false;
	}
	if ($("#empId").val() == "") {
		Dialog.alertInfo("请选择我方项目负责人！");
		return false;
	}
	//是否大客户
	if ($('input[name="bigCustomerFlag"]:checked').val() == undefined
			|| $('input[name="bigCustomerFlag"]:checked').val() == "") {
		Dialog.alertInfo("请选择是否大客户");
		return false;
	}  
	//在选择大客户的时候需要选择是否直签
	if($('input[name="bigCustomerFlag"]:checked').val() == 22601){
		if ($('input[name="directSignFlag"]:checked').val() == undefined
				|| $('input[name="directSignFlag"]:checked').val() == "") {
			Dialog.alertInfo("请选择是否直签");
			return false;
		}
	}
	
	if ($('input[name="authenticationKbn"]:checked').val() == undefined
			|| $('input[name="authenticationKbn"]:checked').val() == "") {
		Dialog.alertInfo("请选择认证类型");
		return false;
	}
	if ($("#commissionCondition").val() == "" || $("#commissionCondition").val() ==  undefined) {
		Dialog.alertInfo("请选择收入结算条件");
		return false;
	}
	if (type == 1) {
		if($("#uploadPhotoId6").val() == undefined || $("#uploadPhotoId6").val() == ""){
			Dialog.alertInfo("请选择户型图");
			return false;
		}
	}
	if($('input[name="estateCoverImg"]').val() == undefined || $('input[name="estateCoverImg"]').val() == ""){
		Dialog.alertInfo("请选择封面图");
		return false;
	}
	
}
