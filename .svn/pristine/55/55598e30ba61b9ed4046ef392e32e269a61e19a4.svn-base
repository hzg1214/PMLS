/**
 * Add By cning 2017.7.11
 */
Dialog.alertSuccess = function(){
	dialog: null;
}

$(function(){	
	linkDistrict("newCityNo","newDistrictNo");

	if($("#flag").val() == "300"){
		$("#chbMore").hide();
        $("#chb5").attr("checked","checked");
        $("#oldUlContactNumber").show();
        $("#ulContactNumber").show();
	}

	//修改项目默认不显示
	$("#ulName").hide();
	$("#oldUlName").hide();
	$("#ulAddress").hide();
	$("#oldUlAddress").hide();
	$("#ulBusinessLicenseNo").hide();
	$("#oldUlBusinessLicenseNo").hide();
	$("#ulLegalPerson").hide();
	$("#oldUlLegalPerson").hide();
	
	$("#chb1").change(function(){
		if($(this).is(':checked')) {
			$("#ulName").show();
			$("#oldUlName").show();
			$("#itemWarning").hide();
			$("#fileMustRemain").show();
		}else{
			$("#ulName").hide();
			$("#oldUlName").hide();
			$("#newCompanyName").val("");

            if($("#chb1").is(':checked')== false
                &&$("#chb2").is(':checked')== false
                &&$("#chb3").is(':checked')== false
                &&$("#chb4").is(':checked')== false){
                $("#fileMustRemain").hide();
            }
		}
	});
	$("#chb2").change(function(){
		if($(this).is(':checked')) {
			$("#ulAddress").show();
			$("#oldUlAddress").show();
			$("#itemWarning").hide();
            $("#fileMustRemain").show();
		}else{
			$("#ulAddress").hide();
			$("#oldUlAddress").hide();
			$("#newAddress").val("");

            if($("#chb1").is(':checked')== false
                &&$("#chb2").is(':checked')== false
                &&$("#chb3").is(':checked')== false
                &&$("#chb4").is(':checked')== false){
                $("#fileMustRemain").hide();
            }
		}
	});
	$("#chb3").change(function(){
		if($(this).is(':checked')) {
			$("#ulBusinessLicenseNo").show();
			$("#oldUlBusinessLicenseNo").show();
			$("#itemWarning").hide();
            $("#fileMustRemain").show();
		}else{
			$("#ulBusinessLicenseNo").hide();
			$("#oldUlBusinessLicenseNo").hide();
			$("#newBusinessLicenseNo").val("");

            if($("#chb1").is(':checked')== false
                &&$("#chb2").is(':checked')== false
                &&$("#chb3").is(':checked')== false
                &&$("#chb4").is(':checked')== false){
                $("#fileMustRemain").hide();
            }
		}
	});
	$("#chb4").change(function(){
		if($(this).is(':checked')) {
			$("#ulLegalPerson").show();
			$("#oldUlLegalPerson").show();
			$("#itemWarning").hide();
            $("#fileMustRemain").show();
		}else{
			$("#ulLegalPerson").hide();
			$("#oldUlLegalPerson").hide();
			$("#newLegalPerson").val("");

            if($("#chb1").is(':checked')== false
                &&$("#chb2").is(':checked')== false
                &&$("#chb3").is(':checked')== false
                &&$("#chb4").is(':checked')== false){
                $("#fileMustRemain").hide();
            }
		}
	});
    $("#chb5").change(function() {
        if ($(this).is(':checked')) {
            $("#oldUlContactNumber").show();
            $("#ulContactNumber").show();
            $("#itemWarning").hide();

            if($("#chb1").is(':checked')== false
                &&$("#chb2").is(':checked')== false
                &&$("#chb3").is(':checked')== false
                &&$("#chb4").is(':checked')== false){
                $("#fileMustRemain").hide();
            }
        }else{
            $("#oldUlContactNumber").hide();
            $("#ulContactNumber").hide();
        }
    });

	//营业执照图片信息处理
	$(".ui_state_highlight").click(function(){
		// 校验输入信息
		if (Validator.validForm("companyEditForm")) {
			$("#itemWarning").html="";
			$("#itemWarning").hide();
			$("#companyNameWarning").html();
			$("#companyNameWarning").hide();
			$("#addressWarning").html();
			$("#addressWarning").hide();
			$("#busWarning").html();
			$("#busWarning").hide();
			$("#personWarning").html();
			$("#personWarning").hide();
			if($("#chb1").is(':checked')== false 
					&& $("#chb2").is(':checked') == false 
					&& $("#chb3").is(':checked') == false 
					&& $("#chb4").is(':checked') == false
                    &&$("#chb5").is(':checked') == false)
			{
				$("#itemWarning").html("请选择修改项目!");
				$("#itemWarning").show();
				
				return false
			}
			
			if($("#chb1").is(':checked')== true)
			{
				if($("#newCompanyName").val().trim()==$("#companyName").val().trim())
				{
					$("#companyNameWarning").html("修改后公司名称和原公司名称一样！");
					$("#companyNameWarning").show();
					return false;
				}
				var companyName = $("#newCompanyName").val();
				if($("#newCompanyName").val().trim() != "" && $("#newCompanyName").val().trim() != null){
					var flag = true;
					var companyNo = "";
					var url_a = BASE_PATH+"/companys/queryCompanyName";
					$.ajax({
						url:url_a,
						async: false,
						type:"get",
						data:{
							companyName	: companyName,
						},
						success:function(data){
							data = JSON.parse(data);
							if(data.length>0 && data[0].companyName != null){
								companyNo=data[0].companyNo;
								flag = false;
							}
						}
					});
					if(!flag){
						$("#companyNameWarning").html("系统中已存在相同的公司名称，公司编号为"+companyNo+"。");
						$("#companyNameWarning").show();
						return false;
					}
				}
			}
			
			if($("#chb2").is(':checked')== true)
			{
				var oldAddress = $("#oldCityName").val()+$("#oldDistrictName").val()+$("#address").val();
				var newAddress = $("#newCityNo").find("option:selected").text()+$("#newDistrictNo").find("option:selected").text()+$("#newAddress").val();
				if(oldAddress == newAddress)
				{
					$("#addressWarning").html("修改后公司注册地址和原公司注册地址一样！");
					$("#addressWarning").show();
					return  false;
				}
			}
			
			if($("#chb3").is(':checked')== true) {
				var newBusinessLicenseNo = $("#newBusinessLicenseNo").val().trim();
				var reg1 = /^[A-Za-z0-9]+$/;//英文加数字
				var reg2 = /^[A-Za-z]+$/;// 纯英文
				var reg3 = /^[0-9]*$/;//纯数字
				
				if(reg2.test(newBusinessLicenseNo)== true){
					$("#busWarning").html("统一社会信用代码输入错误！");
					$("#busWarning").show();
					return false;
				}
				if(!(reg1.test(newBusinessLicenseNo)== true || reg3.test(newBusinessLicenseNo)== true)){
					$("#busWarning").html("统一社会信用代码输入错误！");
					$("#busWarning").show();
					return false;
				}
				var len = newBusinessLicenseNo.length;
				if(len!=15 && len!=18){
					$("#busWarning").html("统一社会信用代码必须是15位或18位！");
					$("#busWarning").show();
					return false;
				}
				if($("#newBusinessLicenseNo").val().trim()==$("#businessLicenseNo").val().trim())
				{
					$("#busWarning").html("修改后统一社会信用代码和原统一社会信用代码一样！");
					$("#busWarning").show();
					return false;
				}
			}
			
			if($("#chb4").is(':checked')== true)
			{
				if($("#newLegalPerson").val().trim()==$("#legalPerson").val().trim())
				{
					$("#personWarning").html("修改后法定代表人和原法定代表人一样！");
					$("#personWarning").show();
					return false;
				}				
			}
            if($("#chb5").is(':checked')== true)
            {
                if($("#oldContactNumber").val().trim()==$("#contactNumber").val().trim())
                {
                    $("#sContactNumber").html("修改后法定代表人电话和原法修改后法定代表人电话一样！");
                    $("#sContactNumber").show();
                    return false;
                }
            }
		} else {
			systemLoaded();
			return false;
		}
		console.log(1122211);
		//上传营业执照验证
        if($("#chb1").is(':checked')== true
                    ||$("#chb2").is(':checked')== true
                    ||$("#chb3").is(':checked')== true
                    ||$("#chb4").is(':checked')== true){
        	
        	console.log(1111);
            if(!handlerFileInfo()){
                return false;
            }
        }

		return true;
	});
	//上传开始
	var options = {
		"url":BASE_PATH + '/file/uploadCommonFile',
//		"oaUrl":BASE_PATH + "/contracts/oa/upload",
		"isDeleteImage":false,//删除时校验checkEditImage
		"isAddImage":true,   //添加时校验checkEditImage
		"isCommonFile":true  //公共上传文件
	};
	photoUploader(options,null,null,null);
});

/*$(function(){
	
});*/

var fileRecordMainIds = new Array();

function checkEditImage(files){
	var bol = true;
	var fileSize = 0;
	
	for( var i = 0 ; i < files.length ; i++ ){  
		fileSize = files[i].size;
		var photoExt = files[i].name.substr(files[i].name.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
		if (photoExt != '.jpg' && photoExt != '.jpeg' && photoExt != '.png' ) {
			Dialog.alertInfo("请上传后缀名为jpg、jpeg、png的文件!");
			systemLoaded();
			bol = false;
			return bol;
		}

		if (fileSize > 5000*1024) {
			Dialog.alertInfo("所选文件不能大于5M！");
			self.value = null;
			systemLoaded();
			bol = false;
			return bol;
		}
	}

	return bol;
}


//保存时处理营业执照文件信息
function handlerFileInfo(){
	$("#businessWarning").html="";
	$("#businessWarning").hide();
	var bol = true;
	var fileRecordMainIds = "";
	$("input[name=fileRecordMainIdHidden]").each(function(){	
		var typeId = $(this).parent().find("input[name=fileTypeId]").val();
		if($(this).val()=="" ){
			$("#businessWarning").html("图片上传出现异常，请删除重新上传！");
			$("#businessWarning").show();
			bol = false;
			return false;
		}
		fileRecordMainIds += ","+$(this).val();
	})
	
	if(fileRecordMainIds==""){
		$("#businessWarning").html("请上传营业执照！");
		$("#businessWarning").show();
		bol = false;
		return false;
	}

	fileRecordMainIds = fileRecordMainIds.substring(1);
	$("#fileRecordMainIds").val(fileRecordMainIds);
	
	$("#businessWarning").html="";
	$("#businessWarning").hide();
	return bol;
}



