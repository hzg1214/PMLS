$(function() {
	/* linkCityCenter("cityNo", "centerId"); */
	// 初始化查询
	initMultiSelectM();
	initList();
});

mCenterUser = function() {
	dialogCreate: null;
	dialogModify: null;

};

/**
 * 初始化查询
 */
initList = function() {
	httpGet('mCenterUserListForm', 'LoadCxt', BASE_PATH + '/mCenterUser/q',
			function() {
				pageInfo("mCenterUserListForm", function() {
					initList();
				});

			});
};

/**
 * 查询
 */
mCenterUser.search = function() {
	$('#curPage').val('1');
	initList();
};

/**
 * 人员维护对话框
 */
mCenterUser.create = function() {

	var url = LOC_RES_BASE_PATH + '/mCenterUser/create';
	var title = '人员维护';
	var params = {};

	var dialogOptions = {
		width : 500,
		ok : function() {
			var reback = mCenterUser.save();
			return reback;
		},
		okVal : '保存',
		cancel : true,
		cancelVal : '取消',
		zIndex : 1000
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
		mCenterUser.dialogCreate = dialog;
	}, dialogOptions);

}

mCenterUser.closeCreate = function() {
	if (mCenterUser.dialogCreate != null && !mCenterUser.dialogCreate.closed) {
		mCenterUser.dialogCreate.close();
	}
}

/**
 * 新增保存处理
 */
mCenterUser.save = function() {
	if (Validator.validForm("mCenterUserCreateForm")) {
		var selectCityNo = $("#newCityNo").val();
		var selectCenterId = $("#newCenterIds").val();
		if(selectCenterId == '' || selectCenterId == null || selectCenterId == undefined){
			$("#newCenterIdStr").text("不能为空");
			$("#newCenterIdStr").css("visibility","initial");
			 return false;
		}
		var userCode = $("#newUserCode").val();
		var userName = $("#newUserName").val();

		var url = BASE_PATH + '/mCenterUser/save';
		var params = {
			cityNo : selectCityNo,
			centerIds : selectCenterId,
			userCode : userCode,
			userName : userName
		}
		return asyncPost(url, params, "#newWarningMsg");
	} else {
		return false;
	}
}

function asyncPost(url, params, message) {

	if (url.indexOf("?") > 0) {
		url = url + "&" + rnd();
	} else {
		url = url + "?" + rnd();
	}
	$(message).empty();
	var reback = false;
	systemLoading("", true);
	$.ajax({
		url : url,
		data : params,
		type : 'POST',
		async : false,
		dataType : 'json',
		success : function(data) {
			systemLoaded();
			if (data && data.returnCode == '200') {
				initList();
				reback = true;
			} else {
				$(message).empty().html(data.returnMsg);
				reback = false;
			}
		},
		error : function(data) {
			systemLoaded();
			$(message).empty().html("操作失败");
			systemLoaded();
			reback = false;
		}
	});

	return reback;
}

/**
 * 编辑画面对话框
 */
mCenterUser.modify = function(id) {

	var url = LOC_RES_BASE_PATH + '/mCenterUser/modify/' + id;
	var title = '人员维护';
	var params = {};
	var dialogOptions = {
		width : 500,
		ok : function() {
			var reback = mCenterUser.update(id);
			return reback;
		},
		okVal : '保存',
		cancel : true,
		cancelVal : '取消',
		zIndex : 1000
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
		mCenterUser.dialogModify = dialog;
	}, dialogOptions);

}

mCenterUser.closeModify = function() {
	if (mCenterUser.dialogModify != null && !mCenterUser.dialogModify.closed) {
		mCenterUser.dialogModify.close();
	}
}

/**
 * 编辑保存处理
 */
mCenterUser.update = function(yfId) {

	if (Validator.validForm("mCenterUserModifyForm")) {
		var url = BASE_PATH + '/mCenterUser/update';

		var selectCityNo = $("#oldCityNo").val();
		var selectCenterId = $("#oldCenterId").val();
		var userCode = $("#oldUserCode").val();
		var userName = $("#oldUserName").val();
		var params = {
			id : yfId,
			cityNo : selectCityNo,
			centerId : selectCenterId,
			userCode : userCode,
			userName : userName
		}
		return asyncPost(url, params, "#oldWarningMsg");

	} else {
		return false;
	}
}

/**
 * 删除处理
 */
mCenterUser.remove = function(yfId,userCode) {
    var result = "是否确定删除此信息?</h3>";

    var params = {
        id : yfId
    };

    Dialog.confirm(result, function() {

        restPost(BASE_PATH + "/mCenterUser/delete", params, function(data) {
            initList();
        }, function(data) {
            Dialog.alertError(data.returnMsg);
        });
    });
};

// 重置输入内容
mCenterUser.reset = function() {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$("#centerId .multi-select-item input").attr("checked", false);
	mCenterUser.search();
};

// 填充储存的表单值
mCenterUser.setSearchParams = function(searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);

	$("#mCenterUserListForm").find("input").each(
			function() {
				var searchKey = $(this).attr("id");
				var searchValue = jsonMap[searchKey];
				if (searchValue != undefined && searchValue != null
						&& searchValue != '') {
					$("#" + searchKey).val(searchValue);
				}
			});
	$("#mCenterUserListForm").find("select").each(
			function() {
				var searchKey = $(this).attr("id");
				var searchValue = jsonMap[searchKey];
				if (searchValue != undefined && searchValue != null
						&& searchValue != '') {
					$("#" + searchKey).val(searchValue);
				}
			});

	var curPage = jsonMap["curPage"];
	if (curPage > 1) {
		$('#curPageTemp').val(curPage);
		$('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
	}
};

var selCity = 'cityNo';
var selCenter = 'centerId';
changeCity = function(selCity,selCenter,type) {
	clearMultiSelectM(selCenter)
	var infoCenterId = $("#infoCenterId").val();//编辑-老的归属中心id
	var cityNo = $("#" + selCity).val();
	if (cityNo == null || cityNo == "") {
		// var html = "<option value='' selected>请选择</option>";
		var html = "";
		$('#' + selCenter).append(html);
		return false;
	}
	var url = BASE_PATH + "/mCenterUser/city/" + cityNo;
	var params = {};
	ajaxGet(
			url,
			params,
			function(data) {

				var list = data.returnValue;
				if (list != null && list.length > 0) {
					var html = '';
					for (var i = 0; i < list.length; i++) {
						var checked='';
						if(infoCenterId==list[i].centerId)
						{
							checked='checked';
						}
						html = html
								+ '<li class="multi-select-item"> <label><input '+checked+' type="checkbox" value="'
								+ list[i].centerId + '" data-text="'
								+ list[i].centerName + '"><span>'
								+ list[i].centerName + '</span></label> </li>';
					}
					$('#' + selCenter).find('.multi-select-list').append(html);
					if(type==1){
						$('#' + selCenter).find('.multi-select-text').val(list[0].centerName)
					}
				}
			}, function() {
			});
}

/**
 * 初始化下拉框
 * 
 * @param selector
 * @param selectors
 * @returns
 */
function initMultiSelectM(selector, selectors) {
	$("div[name^='group']").find('.multi-select').multiSelect({
		check : function($instance) {
		}
	});

}

/**
 * 清空下拉框
 * 
 * @param selCenter
 * @returns
 */
function clearMultiSelectM(selCenter) {
	$("#" + selCenter).find(".multi-select-item").not(':first').remove();
	$("#" + selCenter).find('.multi-select-checkall').removeAttr("checked");
	$("#" + selCenter).find('.multi-select-value').val('');
	$("#" + selCenter).find('.multi-select-text').val('');
	$("#" + selCenter).find('.multi-select-text').text('');
}

/**
 * 编辑用到
 * @param selCity
 * @param selCenter
 * @returns
 */
function linkCityCenter(selCity, selCenter) {
    $("#" + selCity + "").change(function () {
        $("#" + selCenter + " option").remove();

        var cityNo = $("#" + selCity).val();
        if (cityNo == null || cityNo == "") {
            var html = "<option value='' selected>请选择</option>";
            $('#' + selCenter).append(html);
            return false;
        }


        var url = BASE_PATH + "/yfCenterUser/city/" + cityNo;
        var params = {};
        ajaxGet(url, params, function (data) {
            var result = "<option value=''>请选择</option>";
            $.each(data.returnValue, function (n, value) {
                result += "<option value='" + value.centerId + "'>"
                    + value.centerName + "</option>";
            });
            $("#" + selCenter).html('');
            $("#" + selCenter).append(result);
        }, function () {
        });
    })
}