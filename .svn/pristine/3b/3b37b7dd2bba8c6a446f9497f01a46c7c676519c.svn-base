/** ************************公共部分***************************** */
$(function() {

	// 初始化

	// 绑定回车事件
	enterSubmit('#loginForm', function() {
		Login.login();
	});

});

/** **********************登录******************************* */
Login = function() {
};
var wait=60;
var timer = null;

// 用户登录
Login.login = function() {

	// 校验输入信息
	if (checkLogin()) {

		var formId = "loginForm";
		var url = BASE_PATH + "/login";

		httpPost(formId, url, function(data) {

			var returnVal = data.returnValue;
			if (!returnVal) {
				returnVal = "/companys";
			}

			window.location.href = BASE_PATH + returnVal;

		}, function(data) {

			// 返回码
			var returnCode = data.returnCode;

			var returnMsg = data.returnMsg;

			// var tip = "<i class='fa fa-times-circle'></i>  ";
			// $('.text-danger').removeClass("hide").empty().html(tip + returnMsg)
			// 		.show();
            $('#passwordError').empty().html(returnMsg).css("visibility","visible");
			//$('#randCodeLi').show();
			//changeRandCode();
		});
	}

};

// 校验登录信息
checkLogin = function() {

	var username = $("#username").val();
	var password = $("input[name='password']").val();
	var LoginModeFlag = $("#LoginModeFlag").val();
	//var randCode = $("#randCode1").val();

	var tip = "<i class='fa fa-minus-circle'></i>  ";

	if (!username) {
		$("#userNameError").empty().html("工号不能为空！").css("visibility","visible");
		return false;
	} else {
        $("#userNameError").css("visibility","hidden");
	}

	if (!password) {
		if(LoginModeFlag == "1"){
            $('#passwordError').empty().html("验证码不能为空！").css("visibility","visible");
		}else{
            $('#passwordError').empty().html("密码不能为空！").css("visibility","visible");
		}
		return false;
	} else {
        $("#passwordError").css("visibility","hidden");
	}
	/*var randCodeDisplay = $('#randCodeLi').css('display');
	if (randCodeDisplay != 'none' && $.trim(randCode) == "") {
		Dialog.alertError("验证码不正确");
		return false;
	}*/
	// if (loginErrorCount >= 1 && (randCode == "" || randCode ==
	// $("#randCode1").attr('placeholder'))) {
	// $('.text-danger').removeClass("hide").empty().html(tip +
	// "请输入验证码!").show();
	// return false;
	// } else {
	// $(".text-danger").hide();
	// }

	return true;
};

/** **********************公共部分******************************* */
/**
 * 绑定回车提交
 * 
 * @param formId
 * @param callback
 */
function enterSubmit(form, callback) {
	// 绑定回车
	$('body').bind(
			'keydown',
			function() {
				var keyCode = event.keyCode ? event.keyCode
						: event.which ? event.which : event.charCode;
				if (keyCode == '13') {
					Login.login()
					if (callback) {
						callback();
					}
				}
			});
}

function getIdCode(){

	if($("#username").val()!=null && $("#username").val()!=''){
		time($("#idCodeBtn"));
		//var postUrl=BASE_PATH + "/getIdCode?userName=" + $("#username").val();
		var postUrl=BASE_PATH + "/getIdCode";
		$.ajax(
			{
				url:postUrl,
				data :{"userName":$("#username").val()},
				type : 'POST',
				dataType : 'json',
				success: function (data) {
					//data = data ? JSON.parse(data) : '';
					if(data.code==200){
//						alert("验证码发送成功，请在易居房地产交易服务企业号系统通知应用中获取。");
                        $("#userNameError").css("visibility","hidden");
                        $("#passwordError").css("visibility","hidden");
                        $(".reminder-text").empty().html("<i class=\"icon-info\"></i> 验证码发送成功，请在易居房地产交易服务企业号系统通知应用中获取。");
					}else{
                        $('#passwordError').empty().html(data.msg).css("visibility","visible");
                        stopTimer($("#idCodeBtn"));
					}
				}
			}
		);
	}else{
        $("#userNameError").empty().html("请输入工号！");
        $("#userNameError").css("visibility","visible");
	}
}
function time(o) {
	if (wait == 0) {
		o.removeAttr("disabled");
		o.html("获取验证码");
		wait = 60;
	} else {
		o.attr("disabled", true);
		o.html("重发(" + wait + "秒)");
		wait--;
		timer = setTimeout(function() {
				time(o)
			},
			1000)
	}
}
function stopTimer(o){
    if (wait <= 0 || timer == null) return;
    $("#idCodeBtn").removeAttr("disabled");
    $("#idCodeBtn").html("获取验证码");
    wait = 60;
    clearTimeout(timer);
}