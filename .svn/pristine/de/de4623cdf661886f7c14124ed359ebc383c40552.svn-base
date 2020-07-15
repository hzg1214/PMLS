$(function () {
	initMultiSelectM();
	initWjCity('wjCity');
    // 初始化查询
    initList();
    //选择文件后，进行导入
    $("#historyDataFile").change(function(){
		if($(this).val() == "")
		{
			return;
		}
		dataImport();
	})
});


KeFuWj = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {
    httpGet('keFuWjForm', 'LoadCxt', BASE_PATH + '/keFuWj/getKeFuWjList', function () {
        pageInfo("keFuWjForm", function () {
            initList();
        });
    });
};
/**
 * 查询
 *
 */
KeFuWj.search = function () {
	if(checkForSearch()){
		$('#curPage').val('1');
		initList();
	}
};

KeFuWj.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);
    $("#KeFuWjForm").find("input").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    $("#KeFuWjForm").find("select").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};

KeFuWj.modify = function(id) {

    var url = LOC_RES_BASE_PATH + '/keFuWj/modify/' + id;
    var title = '问卷模板维护';
    var params = {};
    var dialogOptions = {
        width : 500,
        ok : function() {
            var reback = KeFuWj.update(id,1);
            return reback;
        },
        /*cancel : function () {
            var reback = KeFuWj.update(id,2);
            return reback;
        },*/
        okVal : '确认',
        cancel : true,
        cancelVal : '取消',
        zIndex : 1000
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
        KeFuWj.dialog = dialog;
    }, dialogOptions);

}


KeFuWj.update = function(id,type) {

    if (Validator.validForm("keFuWjModifyForm")) {
        var url = BASE_PATH + '/keFuWj/update';
        var cityNos = $("#newCityNos").val();
        var params = {
            id : id,
            type : type,
            cityNos : cityNos
        }
        systemLoading("", true);
        $.ajax({
            url: url,
            data: params,
            type: 'POST',
            async: false,
            dataType: 'json',
            success: function (data) {
                systemLoaded();
                if (data && data.returnCode == '200') {
                    initList();
                    reback = true;
                } else {
                    $(message).empty().html(data.returnMsg);
                    reback = false;
                }
            },
            error: function (data) {
                systemLoaded();
                $("#oldWarningMsg").empty().html("操作失败");
                systemLoaded();
                reback = false;
            }
        });
    }else{
        return false;
    }
}

/**
 * 删除处理
 */
KeFuWj.remove = function(id,satisfactionNum) {

    if(satisfactionNum>0){
        Dialog.alertError("该模板已生成满意度调查记录，不允许删除！");
        return false;
    }

    var result = "是否确定删除此信息?";

    var params = {
        id : id
    };

    Dialog.confirm(result, function() {

        restPost(BASE_PATH + "/keFuWj/remove", params, function(data) {
            initList();
        }, function(data) {
            Dialog.alertError(data.returnMsg);
        });
    });
};

KeFuWj.finalize = function(id) {
    if(!isBlank(id)) {
        systemLoading("", true);
        $.ajax({
            url:BASE_PATH+"/keFuWj/finalize",
            data:$.param({
                id:id
            }),
            type:"post",
            success:function(data){
                data = JSON.parse(data);
                if(data && data.returnCode == '200'){
                    Dialog.alertSuccess("状态变更成功!");
                    systemLoaded();
                    initList();
                }
            },
            error:function(){
                Dialog.alertError("状态变更失败");
                systemLoaded();
            }
        });
    }

}

KeFuWj.reset = function (pageType) {
    $("#keFuWjForm :text").val("");
    $("#keFuWjForm").find("select").val("");
    var url = BASE_PATH + "/commons/clearSearchParam?pageType=" + pageType;
    $.ajax({
        type: "GET",
        url: url,
        async: true,
        dataType: "json",
        success: function (data) {//成功与否都不处理
        },
        error: function () {
        }
    });
    $(" input[ name='wjCitys' ] ").val("");
    $("#wjCity .multi-select-item input").attr("checked", false);
    KeFuWj.search();
};

KeFuWj.view = function (id) {

    var url = BASE_PATH + '/keFuWj/tokeFuWjView';
    var title = '问卷预览';
    var params = {
        id:id
    };
    var dialogOptions = {
        width : 800
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
        dialog.position('50%', '25%');
        KeFuWj.dialog = dialog;
    }, dialogOptions);
}

KeFuWj.close = function() {
    KeFuWj.dialog.close();
};

KeFuWj.export = function () {
    //归属城市必填
    if(checkForSearch()){
    	var cookieName = guid();
    	$("#btn-reset").attr("disabled",true);
    	showExprotLoading(cookieName);
    	window.location.href = LOC_RES_BASE_PATH + '/KeFuWj/exportKeFuWjList?'
    	+ "cityNo=" + $("#cityNo").val()
    	+ "&storeCity=" + $("#storeCity").val()
    	+ "&dateCreate=" + $("#dateCreate").val()
		+ "&dateCreateEnd=" + $("#dateCreateEnd").val()
        + "&companyValue=" + $("#companyValue").val()
    	+ "&categoryOne=" + $("#categoryOne").val()
    	+ "&categoryTwo=" + $("#categoryTwo").val()
        + "&userName=" + $("#userName").val()
		+ "&dealStatus=" + $("#dealStatus").val()
    	+ "&checkStatus=" + $("#checkStatus").val()
    	+ "&cookieName=" + cookieName;
    	window.setTimeout(function(){
    		$("#btn-reset").attr("disabled",false);
    	},10000);
    }
}
function checkForSearch(){
	/*var cityNoStr = $("#cityNo").val();
	if(cityNoStr == '' || cityNoStr == null || cityNoStr == undefined){
   	 	Dialog.alertInfo("请选择归属城市");
   	 	return false;
	}*/
	var dateCreate = $("#dateCreate").val();//开始日期
	var dateCreateEnd = $("#dateCreateEnd").val();//结束日期
	//有结束日期没有开始日期
	if((dateCreate == null || dateCreate == "" || dateCreate == undefined)
			&&(dateCreateEnd != null && dateCreateEnd != "" && dateCreateEnd != undefined) ){
		Dialog.alertInfo("请选择创建开始日期");
   	 	return false;
	}
	if((dateCreateEnd == null || dateCreateEnd == "" || dateCreateEnd == undefined)
			&&(dateCreate != null && dateCreate != "" && dateCreate != undefined) ){
		Dialog.alertInfo("请选择创建结束日期");
		return false;
	}
	if((dateCreateEnd != null && dateCreateEnd != "" && dateCreateEnd != undefined)
			&&(dateCreate != null && dateCreate != "" && dateCreate != undefined) ){
		if(dateCreate > dateCreateEnd){
			Dialog.alertInfo("开始日期不能大于结束日期");
			return false;
		}
	}
	return true;
};

/**
 * 初始化下拉框
 * 
 * @param selector
 * @param selectors
 * @returns
 */
function initMultiSelectM(selector, selectors) {
    $("div[name^='city']").find('.multi-select').multiSelect({
        check : function($instance) {
        }
    });

}
/**
 * 初始化下拉框
 *
 * @param selector
 * @param selectors
 * @returns
 */
function initMultiSelectH(selector, selectors) {
    $("div[name^='group']").find('.multi-select').multiSelect({
        check : function($instance) {
            /*$(".ui_buttons input").attr("disabled","disabled");*/
            systemLoading("", true);
            var cityNos = $("#newCityNos").val();
            var hid = $("#hid").val();
            $.ajax({
                url:BASE_PATH+"/keFuWj/queryCityIsAvailable",
                data:$.param({
                    cityNos:cityNos,
                    id:hid
                }),
                type:"post",
                success:function(data){
                    data = JSON.parse(data);
                    if(data && data.returnCode == '200'){
                        if(data.returnMsg!=null&&data.returnMsg!=""){
                            $("#oldWarningMsg").html(data.returnMsg+'是否覆盖？');
                        }else{
                            $("#oldWarningMsg").html(data.returnMsg);
                        }

                        /*$(".ui_buttons input").removeAttr("disabled");*/
                        systemLoaded();
                    }
                },
                error:function(){
                    $("#oldWarningMsg").html("查询城市是否需要覆盖错误");
                    /*$(".ui_buttons input").removeAttr("disabled");*/
                    systemLoaded();
                }
            });
        }
    });

}

/**
 * 全部、已启用、草签、未启用  切换
 */
KeFuWj.tabClick = function(){
	$("#taskType0,#taskType1,#taskType2,#taskType3").click(function(){
		$('#curPage').val('1');
        var selectId = this.id;
        $("#KeFuWjForm :text").val("");
        $("#KeFuWjForm").find("select").val("");
        if(selectId == "taskType0"){
        	$("#presentation0").removeClass("active");$("#presentation1").removeClass("active");
            $("#presentation2").removeClass("active");$("#presentation3").removeClass("active");
            $("#presentation0").addClass("active");
            $("#wjStatus").val("");
        }else if(selectId == "taskType1"){
            $("#wjStatus").val("24703");//已启用
            $("#presentation0").removeClass("active");$("#presentation1").removeClass("active");
            $("#presentation2").removeClass("active");$("#presentation3").removeClass("active");
            $("#presentation1").addClass("active");
        }else if(selectId == "taskType2"){
        	 $("#wjStatus").val("24701");//草稿
        	 $("#presentation0").removeClass("active");$("#presentation1").removeClass("active");
             $("#presentation2").removeClass("active");$("#presentation3").removeClass("active");
             $("#presentation2").addClass("active");
        }else if(selectId == "taskType3"){
        	 $("#wjStatus").val("24702");//未启用
        	 $("#presentation0").removeClass("active");$("#presentation1").removeClass("active");
             $("#presentation2").removeClass("active");$("#presentation3").removeClass("active");
	         $("#presentation3").addClass("active");
        }
        initList();
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
 * 加载适用城市列表
 * @param selCity
 * @param selCenter
 * @param type
 * @returns
 */
function initWjCityH(wjCity,type) {
	clearMultiSelectM(wjCity);
	var url = BASE_PATH + "/keFuWj/getWjCityList";
	var date=new Date;
	var organization=date.getFullYear(); 
	  var params = {
			  yearly: organization
	  };
	  ajaxGet(url, params, function(data) {
          var hid = $("#hid").val();
          $.ajax({
              url:BASE_PATH+"/keFuWj/getWjCheckCityList",
              data:$.param({
                  id:hid
              }),
              type:"post",
              success:function(cityDate){
                  cityDate = JSON.parse(cityDate);
                  if(cityDate && cityDate.returnCode == '200'){
                      var list = data.returnValue;
                      var citylist = cityDate.returnValue;
                      if (list != null && list.length > 0) {
                          var html = '';
                          var cityNos = '';
                          var cityNames = '';
                          for (var i = 0; i < list.length; i++) {
                              var checked='';
                              if(citylist!=null){
                                  for (var j= 0;j<citylist.length;j++){
                                      if(list[i].cityNo==citylist[j].cityNo) {
                                          checked='checked';
                                          cityNos = cityNos+list[i].cityNo+';';
                                          cityNames = cityNames+list[i].cityName+';';
                                          break;
                                      }
                                  }
                              }
                              html = html
                                  + '<li class="multi-select-item"> <label><input '+checked+' type="checkbox" value="'
                                  + list[i].cityNo + '" data-text="'
                                  + list[i].cityName + '"><span>'
                                  + list[i].cityName + '</span></label> </li>';
                          }
                          $('#' + wjCity).find('.multi-select-list').append(html);
                          if(cityNos!=''){
                              $($(".multi-select-value").get(0)).val(cityNos.substring(0,cityNos.length-1));
                          }
                          if(cityNames!=''){
                              $($(".multi-select-text").get(0)).val(cityNames.substring(0,cityNames.length-1));
                          }
                      }
                  }
              }
          });

		}, function() {
		});
}

/**
 * 加载适用城市列表
 * @param selCity
 * @param selCenter
 * @param type
 * @returns
 */
function initWjCity(wjCity,type) {
    clearMultiSelectM(wjCity);
    var url = BASE_PATH + "/keFuWj/getWjCityList";
    var date=new Date;
    var organization=date.getFullYear();
    var params = {
        yearly: organization
    };
    ajaxGet(url, params, function(data) {

        var list = data.returnValue;
        if (list != null && list.length > 0) {
            var html = '';
            for (var i = 0; i < list.length; i++) {
                var checked='';
//					if(infoCenterId==list[i].centerId)
//					{
//						checked='checked';
//					}
                html = html
                    + '<li class="multi-select-item"> <label><input '+checked+' type="checkbox" value="'
                    + list[i].cityNo + '" data-text="'
                    + list[i].cityName + '"><span>'
                    + list[i].cityName + '</span></label> </li>';
            }
            $('#' + wjCity).find('.multi-select-list').append(html);
        }
    }, function() {
    });
} 
    
	/**
     * 导入弹出选择文件对话框
     * 
     */
    KeFuWj.import = function() {	
    	$("#historyDataFile").click();
    };

    /**
     * 导入
     * @returns
     */
    function dataImport(){
    	var statrDate = $('#countDateStart').val();
    	var endStart = $('#countDateEnd').val();
    	var url= BASE_PATH + "/keFuWj/imput";
    	systemLoading("", true);
    	httpPost("imputForm",url, function(data) {
    		$("#historyDataFile").val('');
    		systemLoaded();
    		Dialog.alertError(data.returnMsg);
    		if(statrDate != '' && endStart != '')
    		{
    			KeFuWj.search();
    		}
    	}, function(data) {
    		$("#historyDataFile").val('');
    		Dialog.alertError(data.returnMsg);
    		systemLoaded();
    		return false;
    		
    		
    	});	
    }
    
    
    /**
     * 下载模板
     */
    KeFuWj.exportWj = function(id){

        var cookieName = guid();
        showExprotLoading(cookieName);
        window.location.href = BASE_PATH + "/keFuWj/exportWjMbById?"
            + "&id=" + id
            + "&cookieName=" + cookieName;
        window.setTimeout(function () {
        }, 12000);
    }