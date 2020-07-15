$(function () {
    initMultiSelectM();
    initWjType();
});


function initWjType() {
    var checked='';
    var html = '';
    html = html
        + '<li class="multi-select-item"> <label><input '+checked+' type="checkbox" value="0" data-text="未完成"><span>未完成</span></label> </li>'
        + '<li class="multi-select-item"> <label><input '+checked+' type="checkbox" value="1" data-text="部分完成"><span>部分完成</span></label> </li>'
        + '<li class="multi-select-item"> <label><input '+checked+' type="checkbox" value="2" data-text="已完成"><span>已完成</span></label> </li>';
    $('#wjType').find('.multi-select-list').append(html);
}

function initMultiSelectM(selector, selectors) {
    $("div[name^='wjTypeCenter']").find('.multi-select').multiSelect({
        check : function($instance) {
        }
    });

}

keFuWjAnalyse = function () {
    dialog: null;
};

keFuWjAnalyse.modify = function(id) {

    var url = LOC_RES_BASE_PATH + '/keFuWj/modify/' + id;
    var title = '问卷模板维护';
    var params = {};
    var dialogOptions = {
        width : 500,
        ok : function() {
            var reback = KeFuWj.update(id,1);
            return reback;
        },
        okVal : '确认',
        cancel : true,
        cancelVal : '取消',
        zIndex : 1000
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
        KeFuWj.dialog = dialog;
    }, dialogOptions);

}



keFuWjAnalyse.close = function() {
    keFuWjAnalyse.dialog.close();
};

keFuWjAnalyse.export = function () {
    //归属城市必填
    if(checkForSearch()){
    	var cookieName = guid();
    	$("#btn-reset").attr("disabled",true);
    	showExprotLoading(cookieName);
    	window.location.href = LOC_RES_BASE_PATH + '/keFuWjAnalyse/exportKeFuWjList?'
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


keFuWjAnalyse.reset = function () {
    $("#keFuWjForm :text").val("");
    $("#keFuWjForm").find("select").val("");
    $('#affiliationCityNo').next().find('.searchable-select-items .searchable-select-item').removeClass('selected');
    $($('#affiliationCityNo').next().find('.searchable-select-items .searchable-select-item').get(0)).addClass('selected')
    $('#affiliationCityNo').next().find('.searchable-select-holder').text('请选择')
    $('#placeCityNo').next().find('.searchable-select-items .searchable-select-item').removeClass('selected');
    $($('#placeCityNo').next().find('.searchable-select-items .searchable-select-item').get(0)).addClass('selected')
    $('#placeCityNo').next().find('.searchable-select-holder').text('请选择')

};
    
    
/**
 * 下载模板
 */
keFuWjAnalyse.exportWj = function(){

    var year = $("#year").val();
    if (year==""||year=="undefined"||year==null){
        Dialog.alertError("请选择年份");
        return false;
    }
    var quarter = $("#quarter").val();
    if (quarter==""||quarter=="undefined"||quarter==null){
        Dialog.alertError("请选择季度");
        return false;
    }
    var affiliationCityNo = $("#affiliationCityNo").val();
    if (affiliationCityNo==""||affiliationCityNo=="undefined"||affiliationCityNo==null){
        Dialog.alertError("请选择归属城市");
        return false;
    }
    var wjType = $("#wjType").find(".multi-select-value").val();
    if (wjType==""||wjType=="undefined"||wjType==null){
        Dialog.alertError("请选择问卷状态");
        return false;
    }
    wjType = wjType.replaceAll(';', ',');
    var placeCityNo = $("#placeCityNo").val();
    var store = $("#store").val();
    var company = $("#company").val();

    $.ajax({
        url:BASE_PATH+"/keFuWjAnalyse/queryWjNumber",
        data:$.param({
            year:year,
            quarter:quarter,
            affiliationCityNo:affiliationCityNo,
            placeCityNo:placeCityNo,
            store:store,
            company:company,
            wjType:wjType
        }),
        type:"post",
        success:function(data){
            data = JSON.parse(data);
            if(data && data.returnCode == '200'){
                if(data.returnValue.length>0){
                    if(data.returnValue.length==1){
                        var wjCode = data.returnValue[0].wjCode;
                        /*systemLoading("", true);
                        $("#btn-output").attr("disabled",true);*/
                        var cookieName = guid();
                        var url = BASE_PATH + '/keFuWjAnalyse/exportCheck';
                        systemLoading("", true);
                        $("#btn-output").attr("disabled",true);
                        var param = {
                            wjCode:wjCode,
                            year:year,
                            quarter:quarter,
                            affiliationCityNo:affiliationCityNo,
                            placeCityNo:placeCityNo,
                            store:store,
                            company:company,
                            wjType:wjType
                        }
                        $.post(url, param, function(data) {
                            data = JSON.parse(data);
                            if(data.returnCode==-1){
                                Dialog.alertError(data.returnMsg);
                                systemLoaded();
                                $('#btn-output').removeAttr("disabled");
                            }else{
                                window.location.href = BASE_PATH + '/keFuWjAnalyse/exportWjByCode?'
                                    + "&wjCode=" + wjCode
                                    + "&year=" + year
                                    + "&quarter=" + quarter
                                    + "&affiliationCityNo=" + affiliationCityNo
                                    + "&placeCityNo=" + placeCityNo
                                    + "&store=" + store
                                    + "&company=" + company
                                    + "&wjType=" + wjType
                                    + "&cookieName=" + cookieName;
                                systemLoaded();
                                $('#btn-output').removeAttr("disabled");
                            }

                        });
/*
                        window.location.href = BASE_PATH + '/keFuWjAnalyse/exportWjByCode?'
                            + "&wjCode=" + wjCode
                            + "&year=" + year
                            + "&quarter=" + quarter
                            + "&affiliationCityNo=" + affiliationCityNo
                            + "&placeCityNo=" + placeCityNo
                            + "&store=" + store
                            + "&company=" + company
                            + "&wjType=" + wjType
                            + "&cookieName=" + cookieName;
                        systemLoaded();
                        $('#btn-output').removeAttr("disabled");*/

                    }else{
                        //弹框
                        var url = LOC_RES_BASE_PATH + '/keFuWjAnalyse/modify';
                        var title = '选择模板名称';
                        var params = {
                            year:year,
                            quarter:quarter,
                            affiliationCityNo:affiliationCityNo,
                            placeCityNo:placeCityNo,
                            store:store,
                            company:company
                        };
                        var dialogOptions = {
                            width : 500,
                            ok : function() {
                                var wjCode = $("#wjDetail").val();
                                if(wjCode==""||wjCode=="undefined"||wjCode==null){
                                    $("#oldWarningMsg").html("请选择问卷模板");
                                    return false;
                                }
                                var cookieName = guid();

                                var url = BASE_PATH + '/keFuWjAnalyse/exportCheck';
                                systemLoading("", true);
                                $("#btn-output").attr("disabled",true);
                                var param = {
                                    wjCode:wjCode,
                                    year:year,
                                    quarter:quarter,
                                    affiliationCityNo:affiliationCityNo,
                                    placeCityNo:placeCityNo,
                                    store:store,
                                    company:company,
                                    wjType:wjType
                                }
                                $.post(url, param, function(data) {
                                    data = JSON.parse(data);
                                    if(data.returnCode==-1){
                                        Dialog.alertError(data.returnMsg);
                                        systemLoaded();
                                        $('#btn-output').removeAttr("disabled");
                                    }else{
                                        window.location.href = BASE_PATH + '/keFuWjAnalyse/exportWjByCode?'
                                            + "&wjCode=" + wjCode
                                            + "&year=" + year
                                            + "&quarter=" + quarter
                                            + "&affiliationCityNo=" + affiliationCityNo
                                            + "&placeCityNo=" + placeCityNo
                                            + "&store=" + store
                                            + "&company=" + company
                                            + "&wjType=" + wjType
                                            + "&cookieName=" + cookieName;
                                        systemLoaded();
                                        $('#btn-output').removeAttr("disabled");
                                    }

                                });


                                /*systemLoading("", true);
                                $("#btn-output").attr("disabled",true);
                                window.location.href = BASE_PATH + '/keFuWjAnalyse/exportWjByCode?'
                                    + "&wjCode=" + wjCode
                                    + "&year=" + year
                                    + "&quarter=" + quarter
                                    + "&affiliationCityNo=" + affiliationCityNo
                                    + "&placeCityNo=" + placeCityNo
                                    + "&store=" + store
                                    + "&company=" + company
                                    + "&wjType=" + wjType
                                    + "&cookieName=" + cookieName;
                                systemLoaded();
                                $('#btn-output').removeAttr("disabled");*/
                            },
                            okVal : '确认',
                            cancel : true,
                            cancelVal : '取消',
                            zIndex : 1000
                        };

                        Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
                            keFuWjAnalyse.dialog = dialog;
                        }, dialogOptions);

                    }
                }else{
                    Dialog.alertError("没有问卷！");
                    return false;
                }
            }
        },
        error:function(){
        }
    });


}
