$(function () {
});
//基本信息标签
var markHtml = '<input type="text" class="form-control w125" name="mark[0]" placeholder="" maxlength="4">'
             + '<span class="btn btn-link btn-add-input-tag" onclick="delMark(this)"> 删除</span>';
//在售户型标签
var labelHtml = '<input type="text" class="form-control w125" name="label[0]" placeholder="" maxlength="30">'
              + '<span class="btn btn-link btn-add-input-tag" onclick="delLabel(this)"> 删除</span>';
//梯户
var stairCase = '<ul class="stair list-inline form-inline">'
                    + '<li>'
                        + '<div class="form-group">'
                            + '<label class="fw-normal w100 text-right"></label>'
                            + '<input type="text" class="form-control w50" name="staircase[0]" placeholder="" dataType="posNumWithZero">梯'
                            + '<input type="text" class="form-control w50" name="household[0]" placeholder="" dataType="posNumWithZero">户'
                            + '<span onclick="delStair(this)" class="btn btn-link btn-add-houst-type"> 删除</a>' + '</span>'
                        + '</div>'
                    + '</li>'
                + '</ul>';
//周边配套
var matchHtml = '<ul class="match list-inline form-inline"> '
                + '<li> '
                    + '<div class="form-group"> '
                        + '<label class="fw-normal w100 text-right">类型：</label>'
                        + '<input type="text" class="form-control w100" name="title[0]" placeholder="" maxlength="30">'
                        + '<label class="fw-normal w100 text-right">描述：</label>'
                        + '<input type="text" class="form-control w300" name="description[0]" placeholder="" maxlength="100"> '
                        + '<span onclick="delMatch(this)" class="btn btn-link btn-add-descriptions"> 删除</span> '
                    + '</div>'
                    + '</li>'
                + '</ul>';

//基本信息标签
function addMark(obj) {
    var $myobj = $(obj).parent();
    var flag = true;
    $($myobj).children("input").each(function () {
        if ($(this).val() == undefined || $(this).val() == "") {
            Dialog.alertError("请录入标签内容！");
            flag = false;
            return false;
        }
    });
    if (flag == false) {
        return;
    }
    if($($myobj).children("input").length == 4){
        Dialog.alertError("最大支持4个标签！");
        return false;
    }
    var name = $($myobj).children("input").last().attr('name');
    var num = name.substring(name.indexOf("[") + 1, name.indexOf("]"));
    num = parseInt(num) + 1;
    var tempHtml = markHtml.replace(/mark\[0\]/g, "mark[" + num + "]");
    $($myobj).append(tempHtml);
}
//基本信息标签
function delMark(obj) {
    $(obj).prev().remove();
    $(obj).remove();
}
//在售户型标签
function addLabel(obj) {
    var $myobj = $(obj).parent();
    var flag = true;
    $($myobj).children("input").each(function () {
        if ($(this).val() == undefined || $(this).val() == "") {
            Dialog.alertError("请录入标签内容！");
            flag = false;
            return false;
        }
    });
    if (flag == false) {
        return;
    }
    var name = $($myobj).children("input").last().attr('name');
    var num = name.substring(name.indexOf("[") + 1, name.indexOf("]"));
    num = parseInt(num) + 1;
    var tempHtml = labelHtml.replace(/label\[0\]/g, "label[" + num + "]");
    $($myobj).append(tempHtml);
}
//在售户型标签
function delLabel(obj) {
    $(obj).prev().remove();
    $(obj).remove();
}
//梯户
function addStair(obj) {
    var $myobj = $(obj).parent().parent().parent().parent();
    var flag = true;
    $($myobj).children(".stair:last").children("li").children("div").children("input").each(function () {
        if ($(this).val() == undefined || $(this).val() == "") {
            Dialog.alertError("请录入梯户内容！");
            flag = false;
            return false;
        }
    });
    if (flag == false) {
        return;
    }
    var name = $($myobj).children(".stair:last").children("li").children("div").children("input").last().attr('name');
    var num = name.substring(name.indexOf("[") + 1, name.indexOf("]"));
    num = parseInt(num) + 1;
    var tempHtml = stairCase.replace(/staircase\[0\]/g, "staircase[" + num + "]");
    tempHtml = tempHtml.replace(/household\[0\]/g, "household[" + num + "]");
    $($myobj).children(".stair:last").after(tempHtml);
    $($myobj).children(".stair:last").find("input").attr("maxlength","3");
}
//梯户
function delStair(obj) {
    $(obj).parent().parent().parent().remove();
}
//周边配套
function addMatch(obj) {
    var $myobj = $(obj).parent().parent().parent().parent();
    var flag = true;
    $($myobj).children(".match:last").children("li").children("div").children("input").each(function () {
        if ($(this).val() == undefined || $(this).val() == "") {
            Dialog.alertError("请录入类型和描述！");
            flag = false;
            return false;
        }
    });
    if (flag == false) {
        return;
    }
    var name = $($myobj).children(".match:last").children("li").children("div").children("input").last().attr('name');
    var num = name.substring(name.indexOf("[") + 1, name.indexOf("]"));
    num = parseInt(num) + 1;
    var tempHtml = matchHtml.replace(/title\[0\]/g, "title[" + num + "]");
    tempHtml = tempHtml.replace(/description\[0\]/g, "description[" + num + "]");
    $($myobj).children(".match:last").after(tempHtml);
}
//周边配套
function delMatch(obj) {
    $(obj).parent().parent().parent().remove();
}

/***********************************************************图片上传start**************************************************************/
var picNum = $("#picNum").val();
/**
 * 设置封面
 * @param obj
 */
function setCover(obj) {

    var $this = $(obj);
    var $ul = $(obj).parent().parent();
    //移除所有设为封面
    $(".item_photo_uploader").removeClass("item_cover");
    //当前添加设为封面
    $this.parent().addClass("item_cover");
    //所有图片的值设为不是封面
    $(".set-cover").attr("data", "0");
    //当前值设为封面
    $this.attr("data", "1");

    //将设为封面的fileNo赋到隐藏域中
    var typeName = $ul.find(".btn-add-photo").data("name");
    var imgId = $this.parent().find(".sellFileNo").val();
    if(typeName=="houseTypePhotos"||typeName=="templetPhotos"){
        $("#houseCoverImg").val(imgId);
    }else if(typeName=="photo1"||typeName=="photo2"||typeName=="photo3"||typeName=="photo4"||typeName=="photo5"){
        $("#estateCoverImg").val(imgId);
    }
}

/**
 * 删除文件
 * @param obj
 */
function removePhoto(obj) {
    var $ul = $(obj).parent().parent();
    var $li = $(obj).parent();

    //清掉是否是封面的值
    var isCover = 0;
    var coverElem = $li.find(".set-cover");
    if(coverElem){
        isCover = coverElem.attr("data");
    }

    var typeName = $ul.find(".btn-add-photo").data("name");
    if (isCover == 1) {
        if(typeName=="houseTypePhotos"||typeName=="templetPhotos"){
            $("#houseCoverImg").val("");
        }else if(typeName=="photo1"||typeName=="photo2"||typeName=="photo3"||typeName=="photo4"||typeName=="photo5"){
            $("#estateCoverImg").val("");
        }
    }

    //图片数量限制
    var limit = $ul.find('.item_photo_uploader_add').find(".btn-add-photo").data('limit');
    $(obj).parent().fadeOut(function () {
        $(obj).parent().remove();
        if ($ul.find('.item_photo_uploader').size() <= limit) {
            $ul.find('.item_photo_uploader_add').show();
        }
    });

    //清空添加里面的数据，防止上传同一张图片的时候无法上传
    $ul.find(".btn-add-photo").val("");
}

function addPhoto(obj) {
    var $this = $(obj);
    var $li = $this.parent();
    var $ul = $li.parent();

    //上传之前已有图片数量
    var picCount = $ul.find('.item_photo_uploader').size() - $ul.find('.item_photo_uploader_add').size();

    if ($ul.find('.item_photo_uploader').size() > 10) {
        alert('上传图片数目超过上限！');

        //清空添加里面的数据，防止上传同一张图片的时候无法上传
        $ul.find(".btn-add-photo").val("");

        return false;
    }

    //禁用保存按钮
    $("#btn-save").attr("disabled", "disabled");
    $("#btn-submit").attr("disabled", "disabled");

    var file = obj.files[0];//当前文件信息
    if (file.type.indexOf('image') === -1) {
        alert('您选择的文件不是图片！');

        //清空添加里面的数据，防止上传同一张图片的时候无法上传
        $ul.find(".btn-add-photo").val("");

        return false;
    }

    var fileSize = ((file.size / 1024) / 1024).toFixed(2);
    if (fileSize > 5) {
        alert("所选文件不能大于5M！");

        obj.value = null;
        //清空添加里面的数据，防止上传同一张图片的时候无法上传
        $ul.find(".btn-add-photo").val("");

        return false;
    }

    //图片数量限制
    var limit = $this.data('limit');
    var name = $this.data('name');

    //提交表单
    var formdata = new FormData();
    formdata.append('file', file);
    formdata.append('name', name);

    systemLoading("", true);
    $.ajax({
        type: "POST",
        //url:BASE_PATH + '/estate/upload',
        url: BASE_PATH + '/file/upload/linkage',
        data: formdata,
        processData: false,
        contentType: false,
        dataType: "json",
        success: function (data) {
            var picHtml = "";
            if(name == 'houseTypePhotos' || name == 'templetPhotos'){
                picHtml = setPic(false,data);
            }else{
                picHtml = setPic(true,data);
            }

            $li.before(picHtml);

            var $thisPic = $li.prev();
            $thisPic.append(setHideInfo(name,picNum,data));
            picNum ++;

            if ($ul.find('.item_photo_uploader').size() > limit) {
                $ul.find('.item_photo_uploader_add').hide();
            } else {
                $ul.find('.item_photo_uploader_add').show();
            }
            systemLoaded();
        },
        complete: function (XMLHttpRequest, textStatus) {
            systemLoaded();
            $("#btn-save").attr("disabled", false);
            $("#btn-submit").attr("disabled", false);
        },
        error: function () {
            systemLoaded();
            Dialog.alertInfo('图片上传失败！请尝试重新上传！');
        }
    });
}

/**
 * 设置隐藏域里的值
 * @param type 图片类型
 * @param num 图片下标
 * @param data 返回的图片数据
 * @returns {string}
 */
function setHideInfo(type,num,data){
    var hideHtml =
                    '<input type="hidden" name="'+type+'['+num+'].fileSuffix" value="'+data.returnValue.fileSuffix+'" class="fileSuffix">'+
                    '<input type="hidden" name="'+type+'['+num+'].fileAbbrUrl" value="'+data.returnValue.picUrl_20_percent+'" class="fileAbbrUrl">'+
                    '<input type="hidden" name="'+type+'['+num+'].url50" value="'+data.returnValue.picUrl_50_percent+'" class="url50">'+
                    '<input type="hidden" name="'+type+'['+num+'].fileUrl" value="'+data.returnValue.picUrl_500+'" class="fileUrl">'+
                    '<input type="hidden" name="'+type+'['+num+'].sellFileNo" value="'+data.returnValue.file_id+'" class="sellFileNo">'+
                    '<input type="hidden" name="'+type+'['+num+'].sfImage" value="'+data.returnValue.isImage+'" class="sfImage">'+
                    '<input type="hidden" name="'+type+'['+num+'].fileFullName" value="'+data.returnValue.fileName+'" class="fileFullName">';
    return hideHtml;
}

/**
 * 设置图片
 * @param needCover 是否需要封面
 * @param data 返回的图片数据
 */
function setPic(needCover,data){
    var html = "";
    if(needCover){
        html += '<li class="item_photo_uploader">' +
                    '<span class="btn_remove_photo" onclick="removePhoto(this);"></span>' +
                    '<a href="'+data.returnValue.picUrl_500+'" target="_blank">' +
                        '<img src="'+data.returnValue.picUrl_20_percent+'" class="img-thumbnail"/>' +
                    '</a>' +
                    '<span onclick="setCover(this);" data="0" class="set-cover">设为封面</span>' +
                    '<i class="cover_bg"></i>' +
                '</li>';
    }else{
        html += '<li class="item_photo_uploader">' +
                    '<span class="btn_remove_photo" onclick="removePhoto(this);"></span>' +
                    '<a href="'+data.returnValue.picUrl_500+'" target="_blank">' +
                        '<img src="'+data.returnValue.picUrl_20_percent+'" class="img-thumbnail"/>' +
                    '</a>' +
                '</li>';
    }

    return html;
}


/***********************************************************图片上传end**************************************************************/

//checkbox选中事件
function typeKbnChange() {
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
	hideFlag = false;
	$(".houseType").show();
	$("#houseTypeEdit").val("0");
    var result = new Array();
    $("[name = mgtKbnCB]:checkbox").each(function () {
        if ($(this).is(":checked")) {
            result.push($(this).attr("value"));
            var optionVal = $(this).val();
            if("13405" == optionVal){
            	//物业类型为商铺
            	$(".houseType").hide();
            	console.log(hideFlag);
            	hideFlag = true;
            	console.log(hideFlag);
            	$("#houseTypeEdit").val("1");
            }
        }
    });
    $("#mgtKbn").val(result.join(","));
}
//产权年限
function ownYearKbnChange(){
    var result = new Array();
    $("[name = ownYearKbnCB]:checkbox").each(function () {
        if ($(this).is(":checked")) {
            result.push($(this).attr("value"));
        }
    });
    $("#ownYearKbn").val(result.join(","));
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

function districtChange() {
    var url = BASE_PATH + "/linkages/area/"
        + $("#districtNo").val();
    var params = {};
    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择板块</option>";
        $.each(data.returnValue, function (n, value) {
            result += "<option value='" + value.areaNo + "'>"
                + value.areaName + "</option>";
        });
        $("#areaNo").html('');
        $("#areaNo").append(result);
    }, function () {
    });
}

function getEmpList() {
    $("#deptId").change(
        function () {
            if ($("#deptId").val() != '') {
                var url = BASE_PATH + "/estate/empList/" + $("#deptId").val();
                var params = {};

                ajaxGet(url, params, function (data) {
                    var result = "<option value=''>请选择负责人</option>";
                    $.each(data.returnValue, function (n, value) {
                        result += "<option value='" + value.userId + "'>"
                            + value.userName + "</option>";
                    });
                    $("#empId").html('');
                    $("#empId").append(result);
                }, function () {
                });
            } else {
                var result = "<option value=''>请选择负责人</option>";
                $("#empId").html('');
                $("#empId").append(result);
            }
        });
}
function getcommissionStatus() {
    $("input[name=commissionKbn]").each(function () {
        $(this).click(function () {
            var commissionStatus = $(this).val();
            if (commissionStatus == "1482") {
                $("#commissionStatus").text("%");
            } else {
                $("#commissionStatus").text("元");
            }
        });
    });
    var commissionStatus2 = $("input[name='commissionKbn']:checked").val();
    if (commissionStatus2 == "1482") {
        $("#commissionStatus").text("%");
    } else {
        $("#commissionStatus").text("元");
    }
}
function checkItem(type) {
    if ($("#districtNo").val() == "" /*|| $("#areaNo").val() == ""*/ || $("#address").val() == "") {
        Dialog.alertInfo("楼盘区域板块地址信息不能为空！");
        return false;
    }
    if ($('input[name="salesStatus"]:checked').val() == undefined
        || $('input[name="salesStatus"]:checked').val() == "") {
        Dialog.alertInfo("请选择销售状态");
        return false;
    }
    //是否可垫佣甲方
    var custodyFlg = $('input[name="custodyFlg"]:checked').val();
    if ($('input[name="custodyFlg"]:checked').val() == undefined
    		|| $('input[name="custodyFlg"]:checked').val() == "") {
    	Dialog.alertInfo("请选择是否可垫佣甲方");
    	return false;
    }
    
    if(custodyFlg == 1){
    	if ($("#mattressNailText").val() == undefined
    			|| $("#mattressNailText").val() == "") {
    		Dialog.alertInfo("请选择垫佣甲方简称");
    		return false;
    	}
    	
    }
  //是否独家
	if ($('input[name="particularFlag"]:checked').val() == undefined
			|| $('input[name="particularFlag"]:checked').val() == "") {
		Dialog.alertInfo("请选择是否独家");
		return false;
	}
    // if ($('input[name="cooperationMode"]:checked').val() == undefined
    //     || $('input[name="cooperationMode"]:checked').val() == "") {
    //     Dialog.alertInfo("请选择合作模式");
    //     return false;
    // }
  //是否大客户
	if ($('input[name="bigCustomerFlag"]:checked').val() == undefined
			|| $('input[name="bigCustomerFlag"]:checked').val() == "") {
		Dialog.alertInfo("请选择开发商是否大客户");
		return false;
	}  
	//在选择大客户的时候需要选择大客户开发商和输入开发商全称
	if($('input[name="bigCustomerFlag"]:checked').val() == 22601){
		if ($("#devCompanyText").val() == undefined
				|| $("#devCompanyText").val() == "" || $("#devCompanyText").val() == "请选择") {
			Dialog.alertInfo("请选择开发商简称");
			return false;
		}
		if ($("#developerNameBigYes").val() == undefined
				|| $("#developerNameBigYes").val() == "") {
			Dialog.alertInfo("请输入开发商全称");
			return false;
		}
	}
	//在选择大客户的时候需要选择大客户开发商和输入开发商全称
	if($('input[name="bigCustomerFlag"]:checked').val() == 22602){
		if ($("#developerNameBigNo").val() == undefined
				|| $("#developerNameBigNo").val() == "") {
			Dialog.alertInfo("请输入开发商全称");
			return false;
		}
	}
	//在选择大客户的时候需要选择是否直签
	if($('input[name="bigCustomerFlag"]:checked').val() == 22601){
		if ($('input[name="directSignFlag"]:checked').val() == undefined
				|| $('input[name="directSignFlag"]:checked').val() == "") {
			Dialog.alertInfo("请选择是否直签");
			return false;
		}
	}
    //总价段大小校验
    var min = $("#salePriceUnitMin").val();
    var max = $("#salePriceUnitMax").val();
    if (Number(min) > Number(max)) {
        Dialog.alertInfo("最大总价段必须大于最小总价段");
        return false;
    }
    if ($('input[name="partner"]:checked').val() == undefined
        || $('input[name="partner"]:checked').val() == "") {
        Dialog.alertInfo("请选择合作方类型");
        return false;
    }
    if ($("#empId").val() == "") {
        Dialog.alertInfo("请选择案场负责人！");
        return false;
    }
    // if ($('input[name="authenticationKbn"]:checked').val() == undefined
    //     || $('input[name="authenticationKbn"]:checked').val() == "") {
    //     Dialog.alertInfo("请选择认证类型");
    //     return false;
    // }
    // if ($("#commissionCondition").val() == "" || $("#commissionCondition").val() ==  undefined) {
		// Dialog.alertInfo("请选择收入结算条件");
		// return false;
    // }
    /*if ($('input[name="commissionKbn"]:checked').val() == undefined
        || $('input[name="commissionKbn"]:checked').val() == "") {
        Dialog.alertInfo("请选择佣金方式");
        return false;
    }*/
    /*if (type == 1) {
        if ($(".houseTypePhotos").find(".item_photo_uploader").size() <= 1){
            Dialog.alertInfo("请选择户型图");
            return false;
        }
    }*/

    /*if ($('input[name="estateCoverImg"]').val() == undefined || $('input[name="estateCoverImg"]').val() == "") {
        Dialog.alertInfo("请选择封面图");
        return false;
    }*/

}
//基本信息交验
function checkForAdd(){
	var estateNm = $("#estateNm").val().trim();
	var alertMsg = "填写完项目基本信息才允许保存";
	if (!estateNm) {
		Dialog.alertInfo(alertMsg);
		return false;
	}

    var cityNo = $("#cityNo").val();
    if(!cityNo){
        Dialog.alertInfo(alertMsg);
        return false;
    }

	var projectDepartmentId = $("#projectDepartmentId").val();
	if(!projectDepartmentId){
		Dialog.alertInfo(alertMsg);
		return false;
	}

    if((!$("#id").val()&&$("input[name='estatePosition']:checked").val() == "0")
        ||($("#id").val()&&$("#estatePosition").val() == "0")) {

        var address = $("#address").val().trim();
        if (!$("#districtNo").val() || !address) {
            Dialog.alertInfo("楼盘地址不能为空！");
            return false;
        }
    }else{
        var address = $("#address").val().trim();
        if (!$("#countryNo").val() || !address) {
            Dialog.alertInfo("楼盘地址不能为空！");
            return false;
        }
    }
	var cooperationMode = $("input[name='cooperationMode']:checked").val();
	if(!cooperationMode){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	var salesStatus = $("input[name='salesStatus']:checked").val();
	if(!salesStatus){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	//是否独家 
	var particularFlag = $("input[name='particularFlag']:checked").val();
	if(!particularFlag){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	//是否可垫佣甲方
	var custodyFlg = $("input[name='custodyFlg']:checked").val();
	if(custodyFlg == null || custodyFlg == undefined || custodyFlg ==""){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	
	//垫佣甲方
	var mattressNailText = $("#mattressNailText").val().trim();
	if(custodyFlg == 1){
		if(mattressNailText == "" || mattressNailText == null || mattressNailText == undefined){
			Dialog.alertInfo("请选择垫佣甲方简称");
			return false;
		}
	}
	var salePriceUnitMin = $("#salePriceUnitMin").val();
	var salePriceUnitMax = $("#salePriceUnitMax").val();
	if(!salePriceUnitMin || !salePriceUnitMax){
		Dialog.alertInfo(alertMsg);
		return false;
	}else{
		if(!jQuery.isNumeric(salePriceUnitMin) || !jQuery.isNumeric(salePriceUnitMax)){
			Dialog.alertInfo("总价段只能为金额！");
			return false;
		}
	}
	var partner = $("input[name='partner']:checked").val();
	if(!partner){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	var partnerNm = $("#partnerNm").val().trim();
	if(!partnerNm){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	var partnerContactNm = $("#partnerContactNm").val().trim();
	if(!partnerContactNm){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	var partnerContactTel = $("#partnerContactTel").val().trim();
	if(!partnerContactTel){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	var sceneEmpId = $("#empId").val().trim();
	if(!sceneEmpId){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	var empTel = $("#empTel").val();
	if(!empTel){
		Dialog.alertInfo(alertMsg).trim();
		return false;
	}
	var cooperationDtStart = $("#cooperationDtStart").val();
	if(!cooperationDtStart){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	var cooperationDtEnd = $("#cooperationDtEnd").val();
	if(!cooperationDtEnd){
		Dialog.alertInfo(alertMsg);
		return false;
	}
	
	var flag = true;
	$("#baseInfo .fc-warning").each(function(){
		if(this.innerText){
			flag = false;
		}
	})
	if(!flag){
		Dialog.alertInfo("无效电话号码！");
		return false;
	}
	
	
	var bigCustomerChange = $("input[name='bigCustomerFlag']:checked").val();
	var developerNameBigYes = $("#developerNameBigYes").val();//开发商全称
	var developerNameBigNo = $("#developerNameBigNo").val();//开发商全称
	//是大客户
	if(bigCustomerChange==22601){
		if ($("#devCompanyText").val() == undefined
				|| $("#devCompanyText").val() == "" || $("#devCompanyText").val() == "请选择") {
			Dialog.alertInfo("请选择开发商简称");
			return false;
		}
		if(developerNameBigYes == null || developerNameBigYes == undefined || developerNameBigYes ==""){
			Dialog.alertInfo("开发商全称不能为空！");
			return false;
		}
	}else if(bigCustomerChange==22602){
		if(developerNameBigNo == null || developerNameBigNo == undefined || developerNameBigNo ==""){
			Dialog.alertInfo("开发商全称不能为空！");
			return false;
		}
	}
	return true;
}

/**
 * 合作方类型为开发商，相关联动信息
 * @returns
 */
function autoSetDevMer(){
	var partner = $("input[name='partner']:checked").val();
	if(partner && partner === '12801'){
		var partnerNm = $("#partnerNm").val().trim();
		var partnerContactNm = $("#partnerContactNm").val().trim();
		var partnerContactTel = $("#partnerContactTel").val().trim();
		if(partnerNm){
			$("#devCompany").val(partnerNm);
		}
		if(partnerContactNm){
			$("#devCompanyBroker").val(partnerContactNm);
		}
		if(partnerContactTel){
			$("#devCompanyBrokerTel").val(partnerContactTel);
		}
	}
}
//页面显示input
function bigCustomerChange(type){
	//type=2时候为编辑初始化页面
	
	var bigCustomerChange = $("input[name='bigCustomerFlag']:checked").val();
	//是大客户
	if(bigCustomerChange==22601){
		$("#devCompany").hide();
		$("#selectDevCompany2").show();
		$("#selectDevCompany2").css("display","inline-block");
		$("#directSignListShow").show();
		$("#developerNameYes").show();
		$("#developerNameShow").show();
		$("#developerNameNo").hide();
		if(type == '1'){
			$("#devCompanyText").val("");
			$("#devCompany").val("");
			$("#selectDevCompany").val('');//选中
			$("#developerNameBigYes").val('');//
	        $('.selectpicker2').selectpicker('refresh');  
			$('input:radio[name="directSignFlag"]').removeAttr("checked");
			$("#developerNameNo").val('');
		}
	}
	//不是大客户
	if(bigCustomerChange==22602){
		$("#selectDevCompany2").hide();
		$("#devCompany").show();
		$("#directSignListShow").hide();
		$("#developerNameYes").hide();
		$("#developerNameNo").show();
		if(type == '1'){
			$("#devCompany").val("");
			$("#developerNameYes").val("");
			$("#developerNameBigNo").val('');//
		}
	}
};

//页面显示input
function custodyChange(type){
	//type=2时候为编辑初始化页面
	
	var custodyChange = $("input[name='custodyFlg']:checked").val();
	//可垫佣
	if(custodyChange==1){
		$("#mattressNail").show();
		$("#mattressNail").css("display","inline-block");
		$("#selectMattressNail2").css("display","inline-block");
		if(type == '1'){
			$("#mattressNailText").val("");
			$("#selectMattressNail").val('');//选中
			$('.selectpicker3').selectpicker('refresh'); 
		}
	}
	//不可垫佣
	if(custodyChange==0){
		$("#mattressNail").hide();
		$("#mattressNail").css("display","none");
		if(type == '1'){
			$("#mattressNailText").val("");
			$('.selectpicker3').selectpicker('refresh'); 
		}
	}
};