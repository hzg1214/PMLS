/** ************************公共部分***************************** */
var jsonMap = null;
var initCityDist = false;


/**
 * 初始化查询
 */
initList = function() {

    httpGet('LoadDataListForm', 'LoadDataList', BASE_PATH + '/dataAuthority/index', function() {

        pageInfo("LoadDataListForm", function() {
            initList();
        });

    });
    initCityDist = false;
};

/** **************************demo js*************************** */
DataAuthority = function() {
};
/**
 * 查询
 *
 */
DataAuthority.search = function() {
    $('#curPage').val('1');
    initList();
};


//checkbox 全选/取消全选
var isCheckAll = false;
function swapCheck() {
    if (isCheckAll) {
        $("input[type='checkbox']:visible").each(function() {
            this.checked = false;
        });
        isCheckAll = false;
    } else {
        $("input[type='checkbox']:visible").each(function() {
            this.checked = true;
        });
        isCheckAll = true;
    }
}


DataAuthority.close = function () {

    DataAuthority.dialog.close();
};


DataAuthority.insert= function(){
    var title = '新增';
    var url = BASE_PATH + '/dataAuthority/insertData';
    var params = {
    };
    var dialogOptions = {
        width : 800,
        /* height : 760, */
        ok : function() {
            var reback = DataAuthority.authorityData();
            return reback;
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

        dialog.position('50%', '25%');
        DataAuthority.dialog = dialog;

    }, dialogOptions);

}

DataAuthority.authorityData=function(){
    var userCode=$("#userCode").val();
    var userName=$("#userName").val();
    var cityList=$("#cityList").val();
    var authorityLevel=$("input[name='authorityLevel']:checked").val();
    console.info(cityList);
    if(userCode=="" ||userCode==null || userCode==undefined){
        $("#errorMsg").text("请先填写工号!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }
    if(userName=="" ||userName==null || userName==undefined){
        $("#errorMsg").text("请先填写姓名!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }
    if(authorityLevel=="" ||authorityLevel==null || authorityLevel==undefined){
        $("#errorMsg").text("请先选择等级!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }
    if(cityList.length==0){
        $("#errorMsg").text("请先选择城市!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }

    var url = BASE_PATH + '/dataAuthority/saveData';
    var params = {
        userCode : userCode,
        userName : userName,
        authorityLevel : authorityLevel,
        cityList:cityList
    }
    restPost(url, params, function(data){
        console.log(data);

    }, function(data){
        if(data.returnCode != "200"){
            Dialog.alertError(data.returnMsg);
        }
    })
}

DataAuthority.delete=function() {

    var ids = "";
    $(".selectPeople").each(function () {
        if ($(this).is(':checked')) {
            ids += $(this).val() + ",";
        }
    });
    if (ids != "") {
        ids = ids.substr(0, ids.length - 1);
    }
    if (ids == "" || ids == null) {
        $("#errorMessage").text("请先选择要删除的人!");
        $("#errorMessage").css("visibility", "initial");
        return false;
    }
    var url = BASE_PATH + '/dataAuthority/deleteData';
    var params = {
        id : ids,
    }
    restPost(url, params, function(data){
        console.log(data);

        $("#errorMessage").css("visibility", "hidden");

    }, function(data){
        if(data.returnCode != "200"){
            Dialog.alertError(data.returnMsg);
        }
    })

}

DataAuthority.reset=function(){
    $("#userCode").val('');
    $("#cityGroupName").val('');
}