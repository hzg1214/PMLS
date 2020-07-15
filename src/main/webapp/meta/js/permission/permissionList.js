




/**
 * 初始化查询
 */
initList = function() {

    httpGet('LoadPermissionForm', 'LoadPermissionList', BASE_PATH + '/permission/SearchPermissionList', function() {

        pageInfo("LoadPermissionForm", function() {
            initList();
        });

    });

};

//处理业务阶段多选
$('#functionSelect').find('.multi-select').multiSelect({
    check: function ($instance) {
    }
});

/** **************************demo js*************************** */
Permission = function() {
};

/**
 * 查询
 *
 */
Permission.search = function() {
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

Permission.insert= function(){
    var title = '新增';
    var url = BASE_PATH + '/permission/insertPermission';
    var params = {
    };
    var dialogOptions = {
        width : 800,
        /* height : 760, */
        ok : function() {
            var reback = Permission.PermissionData();
            return reback;
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

        dialog.position('50%', '25%');
        Permission.dialog = dialog;

    }, dialogOptions);

}

Permission.close = function () {

    Permission.dialog.close();
};

Permission.delete=function() {

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
    var url = BASE_PATH + '/permission/deletePermission';
    var params = {
        id : ids,
    }
    restPost(url, params, function(data){
        console.log(data);
        window.location.href="/permission/permissionList";
        $("#errorMessage").css("visibility", "hidden");

    }, function(data){
        if(data.returnCode != "200"){
            Dialog.alertError(data.returnMsg);
        }
    })

}

Permission.searchList=function(){
    var url = BASE_PATH + '/permission/searchList';
    var params = {};
    restPost(url, params, function(data){
        console.log(data);
        html="";
        var list =  data.returnData;

        for (var i = 0; i < list.length; i++) {
            html += '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].functionCode + '" data-text="' + list[i].functionName + '" "><span>' + list[i].functionName + '</span></label> </li>';
        }
        $("#functionCode").find('.multi-select-list').append(html);
    }, function(data){
        if(data.returnCode != "200"){
            Dialog.alertError(data.returnMsg);
        }
    })
}

Permission.PermissionData=function(){
    var userCode=$("#userCode").val();
    var userName=$("#userName").val();
    var isClick=$("input[name='isClick']:checked").val();
    var isShow=$("input[name='isShow']:checked").val();
    var functionCode = $("#functionCode").find(".multi-select-value").val();
    var functionName = $("#functionCode").find(".multi-select-value").text();
   console.log(functionCode);
    console.log(functionName);
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
    if(isShow=="" ||isShow==null || isShow==undefined){
        $("#errorMsg").text("请先选择是否显示!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }
    if(isClick=="" ||isClick==null || isClick==undefined){
        $("#errorMsg").text("请先选是否可点击");
        $("#errorMsg").css("visibility","initial");
        return false;
    }


    var url = BASE_PATH + '/permission/savePermission';
    var params = {
        userCode : userCode,
        userName : userName,
        functionCode:functionCode,
        isShow : isShow,
        isClick:isClick
    }
    restPost(url, params, function(data){
        console.log(data);

    }, function(data){
        if(data.returnCode != "200"){
            Dialog.alertError(data.returnMsg);
        }
    })
}
Permission.reset=function(){
    $("#userCode").val('');
    $("#functionName").val('');
}