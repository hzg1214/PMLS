/*
 * 收入管理
 * */

var $;
layui.use(['upload'], function () {
    var upload = layui.upload;
    $ = layui.$;



    var dataParams = parent.params.data();

    //选完文件后不自动上传
    upload.render({
        elem: '#selectFile'
        , url: BASE_PATH + '/pmlsSceneCommission/imput/'
        , data: {
            estateTypeImput: dataParams.estateType,
        }
        , auto: false
        , accept: 'file'
        , exts: 'xls|xlsx'
        , bindAction: '#uploadImport'
        , before: function (obj) {
            layer.load(2);
        }
        , done: function (data) {
            //上传完毕
            if (data.returnCode == -1) {
                layer.alert(data.returnMsg, {icon: 2, title: '提示'},function (index) {
                    layer.closeAll();
                });

            } else {
                    layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function (index) {
                    if(!isBlank(dataParams.estateType)
                        && !isBlank(dataParams.countDateStart)
                        && !isBlank(dataParams.countDateEnd)
                        && !isBlank(dataParams.businessType)
                        && dataParams.countDateStart < dataParams.countDateEnd
                    ){
                        parent.active.reload();
                    }
                        parent.layer.closeAll();
                });
            }
        }
    });

});


/**
 * 判断一个对象是否为空
 */
function isBlank(value){
    if(value == null || value == "" || value == undefined){
        return true;
    }
    return false;
}