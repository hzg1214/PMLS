$(function(){
    //上传开始
    var options = {
        "url":BASE_PATH + '/file/uploadCommonFile',
        "isDeleteImage":false,//删除时校验checkEditImage
        "isAddImage":true,   //添加时校验checkEditImage
        "isCommonFile":true  //公共上传文件
    };
    photoUploader(options,null,null,null);
});

function checkEditImage(files){
    var bol = true;
    var fileSize = 0;

    for( var i = 0 ; i < files.length ; i++ ){
        fileSize = files[i].size;
        var photoExt = files[i].name.substr(files[i].name.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
        if (photoExt != '.jpg' && photoExt != '.png' ) {
            Dialog.alertInfo("请上传后缀名为jpg、png的文件!");
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