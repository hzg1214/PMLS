/**
 * Created by Sky on 2016/4/5. 房友js
 */
$(function() {
	fangyou.initList();

	$('#J_changePassword').click(function() {
		fangyou.loadChangePassword();
	});

	$(document).on('keyup', 'input[name="secondPwd"]', function() {
		fangyou.checkPassword($('#J_newPassword'), $('#J_affirmPassword'));
	});

});

var fangyou = {
	/**
	 * 初始化列表信息
	 */
	initList : function() {
		var listUrl = BASE_PATH + '/fangyou/account/q/';

		// //初始化基础店铺信息 companyBaseInfo
		// var companyId = $("#companyId").val();
		// httpGet('contactsForm', 'LoadBaseInfo', BASE_PATH +
		// '/companys/'+companyId, function() {
		// });

		httpGet("fangyouAccountForm", "loadAccountList", listUrl, function() {
			var count = $('#J_accountCount').val() || 0;
			$('#J_showAccountCount').text($('#J_accountCount').val() || 0);
			if (count > 0) {
				$('#J_changePassword').show();
			}
		});
	},
	/**
	 * 弹窗状态
	 */
	changePasswordDialog : null,
	/**
	 * 加载修改密码框
	 */
	loadChangePassword : function() {
		var url = BASE_PATH + '/fangyou/password/';
		var param = {};
		var dialogOptions = {
			ok : function() {
				if (!fangyou.checkPassword($('#J_newPassword'),
						$('#J_affirmPassword'))) {
					return;
				}

				var url = BASE_PATH + "/fangyou/password/";
				var param = {
					newPassword : $('#J_newPassword').val(),
					affirmPassword : $('#J_affirmPassword').val(),
					companyId : $('#companyId').val()
				};
				httpPut(url, param, function(data) {
					Dialog.alertInfo("重置密码成功", false);
					//location.reload();
				}, function(data){
					
					var returnMsg = data.returnMsg;
					if(!returnMsg){
						returnMsg = "重置密码失败！";
					}
					
					Dialog.alertError(returnMsg);
				});
			},
			okVal : '确定',
			cancel : true,
			cancelVal : '返回',
			width : '505px',
			height : '300'
		};
		Dialog.ajaxOpenDialog(url, param, "修改管理员密码", function(dialog) {
			this.changePasswordDialog = dialog;
		}, dialogOptions);
	},

	/**
	 * 验证密码
	 */
	checkPassword : function(password1, password2) {
		var pwd1 = $(password1).val();
		var pwd2 = $(password2).val();
		if (pwd1 != pwd2) {
			$('#J_errorPop').show();
			return false;
		} else {
			$('#J_errorPop').hide();
			return true;
		}
	}
};

/**
 * 创建账号按钮
 */
function createFangyou(companyId, userCreate) {

	systemLoading("", true);

	var contractId = null;
	// 公司下是否有审合通过的合同
	var url = BASE_PATH + '/contract/getAuditpassContract/' + companyId;
	var param = {};
	ajaxGet(url, param, function(data) {
		if (null != data.returnValue) {
			contractId = data.returnValue.id;
			// 存在审合通过的合同
			if (contractId) {
				// 创建账号
				var url = BASE_PATH + '/fangyou/createfy';
				var param = {
					companyId : companyId,
					contractId : contractId
				};
				ajaxGet(url, param, function(data) {
					systemLoaded();
					location.href = BASE_PATH + '/fangyou/account/' + companyId
							+ '/' + userCreate;
				}, function(data) {
					// var returnMsg = data.returnMsg;
					// 创建房友账号失败
					// Dialog.alertInfo(returnMsg, true);
					// alert(returnMsg);
					// return;

					systemLoaded();
				});
			}else{
				systemLoaded();
			}
		} else { // 不存在审核通过的合同
			systemLoaded();
			Dialog.alertInfo("该公司下没有合同，不能创建房友账号");
			return false;
		}
	},function(){
		systemLoaded();
	});
};