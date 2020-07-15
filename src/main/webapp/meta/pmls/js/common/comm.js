function ajaxGet(url, params, successback, failback) {
    if (url.indexOf("?") > 0) {
        url = url + "&" + rnd();
    } else {
        url = url + "?" + rnd();
    }
    $.ajax({
        url: url,
        data: params,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data && data.returnCode == '200') {
                if (successback) {
                    successback(data);
                }
                return;
            }
            if (failback) {
                failback(data);
            }
        }
    });
    return true;
}

/**
 * HTTP POST rest
 *
 * @param formId
 * @param url
 * @param successback
 * @param failback
 * @returns {Boolean}
 */
function restPost(url, params, successback, failback) {

    if (url.indexOf("?") > 0) {
        url = url + "&" + rnd();
    } else {
        url = url + "?" + rnd();
    }
    $.ajax({
        url: url,
        data: params,
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            if (data && data.returnCode == '200') {
                if (successback) {
                    successback(data);
                }
                return;
            }
            if (failback) {
                failback(data);
            }
        }
    });
    return true;

}

// 随机码
function rnd() {
    var random = Math.floor(Math.random() * 10001);
    var id = (new Date().getTime() * random).toString();
    id = id.split('').reverse().join('');
    return 'randomId=' + id;
}


/**
 *已开通城市（二级联动)  新房联动项目
 */
function cityLinkageIsService(checkValue, callback) {
    var url = BASE_PATH + "/sceneTrade/queryCityList";
    var params = {};
    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择</option>";
        $.each(data.returnData, function (n, value) {
            if (checkValue != null && checkValue == value.cityNo) {
                result += "<option value='" + value.cityNo + "' selected>" + value.cityName + "</option>";
            } else {
                result += "<option value='" + value.cityNo + "'>" + value.cityName + "</option>";
            }
        });
        $("#realityCityNo").html('');
        $("#realityCityNo").append(result);

        callback ? callback() : $.noop();
    }, function () {

    });

}

/**
 * 已开通区域（二级联动)  新房联动项目
 */
function districtLinkageIsService(realityCityNo, checkValue, callback) {
    var url = BASE_PATH + "/sceneTrade/queryDistrictListByCityNo/" + realityCityNo;
    var params = {};

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择区域</option>";
        $.each(data.returnData, function (n, value) {
            if (checkValue != null && checkValue == value.districtNo) {
                result += "<option value='" + value.districtNo + "' selected>" + value.districtName + "</option>";
            } else {
                result += "<option value='" + value.districtNo + "'>" + value.districtName + "</option>";
            }
        });
        $("#districtNo").html('');
        $("#districtNo").append(result);
        callback ? callback() : $.noop();
    }, function () {

    });
}

/**
 * 楼盘归属项目部
 */
function projectDepartmentLinkageIsService(callback, ids) {
    var url = BASE_PATH + "/sceneTrade/queryProjectDepartment";
    var params = {};
    if (ids == null) {
        ids = [];
        ids.push("projectDepartmentId")
    }

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择</option>";
        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.projectDepartmentId + "'>"
                + value.projectDepartment + "</option>";
        });
        for (var i = 0; i < ids.length; i++) {
            var id = "#" + ids[i];
            $(id).html('');
            $(id).append(result);
        }
        callback ? callback() : $.noop();
    }, function () {

    });
}

function centerGrpIdLinkageIsService(callback, ids) {
    var url = BASE_PATH + "/sceneTrade/queryAchivCenterLevelList/";

    var params = {};

    if (ids == null) {
        ids = [];
        ids.push("centerId")
    }

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择</option>";
        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.centerGroupId + "'>"
                + value.centerGroupName + "</option>";
        });
        for (var i = 0; i < ids.length; i++) {
            var id = "#" + ids[i];
            $(id).html('');
            $(id).append(result);
        }
        callback ? callback() : $.noop();
    }, function () {

    });

}

/** 业绩中心 */
function centerIdLinkageIsService(usercode, callback, ids) {
    var url = BASE_PATH + "/sceneTrade/getLinkUserCenter"

    var params = {
        userCode: usercode
    };

    if (ids == null) {
        ids = [];
        ids.push("centerId")
    }

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择业绩归属中心</option>";
        var selected = "";
        if (!isEmpty(data) && !isEmpty(data.returnData) && data.returnData.length == 1) {
            selected = " selected "
        }
        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.exchangeCenterId + "'" + selected + "> "
                + value.exchangeCenterName + "</option>";
        });
        for (var i = 0; i < ids.length; i++) {
            var id = "#" + ids[i];
            $(id).html('');
            $(id).append(result);
        }
        callback ? callback() : $.noop();
    }, function () {

    });

}

/* 包销房源楼市号 */
function buildingNoLinkageIsService(projectNo, callback, ids) {
    var url = BASE_PATH + "/sceneTrade/queryBuildingNoByEstateId/" + projectNo;

    var params = {};

    if (ids == null) {
        ids = [];
        ids.push("centerId")
    }

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择楼市号</option>";
        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.buildingNoCode + "'"
                + " data-roughArea ='" + value.roughArea + "'"
                + " data-roughAmount = '" + value.roughAmount + "'>"
                + value.buildingNoName + "</option>";
        });
        for (var i = 0; i < ids.length; i++) {
            var id = "#" + ids[i];
            $(id).html('');
            $(id).append(result);
        }
        callback ? callback() : $.noop();
    }, function () {

    });

}

/**
 * 字典数据取得
 */
function dictionaryLinkageIsService(id, typeId, callback) {
    var url = BASE_PATH + "/sceneTrade/queryDictionary/" + typeId;
    var params = {};

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择</option>"

        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.dicCode + "'>"
                + value.dicValue + "</option>";
        });
        $("#" + id).html('');
        $("#" + id).append(result);
        callback ? callback() : $.noop();
    }, function () {

    });
}

/* 核算主体 */
function accountProjectLinkageIsService(cityNo, callback, ids) {
    var url = BASE_PATH + "/sceneTrade/getAccountProject/" + cityNo;

    var params = {};

    if (ids == null) {
        ids = [];
        ids.push("accountProject")
    }

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择</option>";

        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.accountProjectNo
                + "' data-name='" + value.accountProject + "'>"
                + value.accountProjectNo + "_" + value.accountProject + "</option>";
        });


        for (var i = 0; i < ids.length; i++) {
            var id = "#" + ids[i];
            $(id).html('');
            $(id).append(result);
        }
        callback ? callback() : $.noop();
    }, function () {

    });

}


/* 核算主体 */
function accountProjectOALinkageIsService(projectNo, callback, ids) {
    var url = BASE_PATH + "/sceneTrade/queryaccountProjectByProjectNo/" + projectNo;

    var params = {};

    if (ids == null) {
        ids = [];
        ids.push("accountProject")
    }

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择核算主体</option>";

        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.lnkaccountProjectCode
                + "' data-name='" + value.lnkAccountProject + "'>"
                + value.lnkaccountProjectCode + "_" + value.lnkAccountProject + "</option>";
        });
        for (var i = 0; i < ids.length; i++) {
            var id = "#" + ids[i];
            $(id).html('');
            $(id).append(result);
        }
        callback ? callback() : $.noop();
    }, function () {

    });

}

function userLinkIsService(callback, ids) {
    var url = BASE_PATH + "/sceneTrade/queryLinkUser/";

    var params = {};

    if (ids == null) {
        ids = [];
        ids.push("contactId")
    }

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择业绩归属人</option>";
        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.userCode + "'>"
                + value.userName + "</option>";
        });
        for (var i = 0; i < ids.length; i++) {
            var id = "#" + ids[i];
            $(id).html('');
            $(id).append(result);
        }
        callback ? callback() : $.noop();
    }, function () {

    });


}

/**
 * 金额格式化
 */
function formatMoney(number) {
    if (number == undefined || number == null) {
        return "-";
    }
    var places = 2;
    var thousand = ",";
    var decimal = ".";
    var negative = number < 0 ? "-" : "",
        i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
}


/**
 * 金额格式化
 */
function formatMoney2(number) {
    if (number == undefined || number == null) {
        return "";
    }
    var places = 2;
    var thousand = ",";
    var decimal = ".";
    var negative = number < 0 ? "-" : "",
        i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
}

/**
 * 格式化金额
 * @param obj
 * @returns
 */
function formatAccount(obj) {
    if (isEmpty(obj) || obj == '-' || obj == "" || obj == null || obj == undefined) {
        obj = 0.00;
    } else {
        obj = parseFloat(obj.replace(/,/g, ""));
    }
    return obj;
}

function formatAccount2(obj) {
    if (isEmpty(obj) || obj == '-' || obj == "" || obj == null || obj == undefined) {
        obj = 0.00;
    } else {
        obj = parseFloat(obj).toFixed(2);
    }
    return obj;
}

/**
 * format金额
 * @param num
 * @returns
 */
function formatCurrency(num) {
    if (isEmpty(num)) {
        num = "0";
    }
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num + '.' + cents);
}

function formatDate(datetime, fmt) {
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime) * 1000;
        } else if (datetime.length == 13) {
            datetime = parseInt(datetime);
        }
    }
    datetime = new Date(datetime);
    var o = {
        "M+": datetime.getMonth() + 1,                 //月份
        "d+": datetime.getDate(),                    //日
        "h+": datetime.getHours(),                   //小时
        "m+": datetime.getMinutes(),                 //分
        "s+": datetime.getSeconds(),                 //秒
        "q+": Math.floor((datetime.getMonth() + 3) / 3), //季度
        "S": datetime.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


/**
 * 判断一个对象是否为空
 */
function isBlank(value) {
    if (value == undefined || value == null || value == "") {
        return true;
    }
    return false;
}

function getXMSelectIds(vals) {
    var ids = [];
    if (vals.length > 0) {
        for (var i = 0; i < vals.length; i++) {
            ids.push("'" + vals[i].value + "'");
        }
    }
    return ids.join(",");
}

//不要单引号
function getXMSelectOutIds(vals) {
    var ids = [];
    if (vals.length > 0) {
        for (var i = 0; i < vals.length; i++) {
            ids.push(vals[i].value);
        }
    }
    return ids.join(",");
}

function s4() {
    return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
};

function guid() {
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
}

/**
 * 校验手机号码是否有效
 * @param phone 手机号码
 * @returns {Boolean}  返回判断结果(true:是手机类型 false:不是手机类型)
 */
function checkPhoneNumber(phone) {
    // 验证手机号格式
    var reg = /^[0-9][0-9][0-9]{9}$/;
    if (!reg.test(phone)) {
        return false;
    }
    return true;
}

/**
 * 校验是否是数值
 */
function checkIsNumber(val) {
    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除，
    if (isEmpty(val)) {
        return false;
    }
    if (!isNaN(val)) {
        //对于空数组和只有一个数值成员的数组或全是数字组成的字符串，isNaN返回false，例如：‘123‘、[]、[2]、[‘123‘],isNaN返回false,
        //所以如果不需要val包含这些特殊情况，则这个判断改写为if(!isNaN(val) && typeof val === ‘number‘ )
        return true;
    }

    else {
        return false;
    }
}

/**
 * 检查输入字符串是否为空或者全部都是空格
 */
function isEmpty(str) {
    if (str == undefined || str == null || str == "")
        return true;
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}

function NullToEmpty(str) {
    if (isEmpty(str)) {
        return "";
    } else {
        return str;
    }
}

function NullToZero(str) {
    if (isEmpty(str) || str == "-") {
        return "0";
    } else {
        return str;
    }
}


function showExprotLoading(cookieName, selectId) {
    if (isBlank(selectId)) {
        selectId = "";
    }
    //systemLoading(selectId,true);
    window.cookieLoopNum = self.setInterval("getExportCookie('" + cookieName + "','" + selectId + "')", 1000);
    window.cookieLoopTime = 0;
}


function msgProp(dataStr) {
    layer.msg(dataStr, {
        icon: 1,
        time: 2000 //2秒关闭（如果不配置，默认是3秒）
    });
}

function msgAlertProp(dataStr) {
    layer.msg(dataStr, {
        icon: 7,
        time: 2000 //2秒关闭（如果不配置，默认是3秒）
    });
}

function msgErrorProp(dataStr) {
    layer.msg(dataStr, {
        icon: 2,
        time: 2000 //2秒关闭（如果不配置，默认是3秒）
    });
}

function msgAlert(dataStr) {
    layer.alert(dataStr, {icon: 7, title: '提示'});
}

function msgError(dataStr) {
    layer.alert(dataStr, {icon: 2, title: '错误'});
}

function msgConfirm(dataStr, successback, failback) {
    layer.confirm(dataStr, {icon: 3, title: '提示'}, function (index) {
        if (successback) {
            successback();
        }
        layer.close(index);
        return;
    }, function (index) {
        if (failback) {
            failback();
        }
        layer.close(index);
        return;
    });
}

/**
 * 上传图片初始化
 * @param elemId 图片类型ID
 * @param optionsData 图片上传附加参数，格式：{fileTypeId:'1028',fileSourceId:'8'}
 */
function uploadRender(elemId, optionsData) {
    var exts='jpg|png|jpeg';
    if(optionsData.exts!=null&&optionsData.exts!=''){
        exts=optionsData.exts;
    }
    var multiple = true;//多图上传
    upload.render({
        elem: '#' + elemId + ' .uploadImg',
        url: ctx + '/file/uploadCommonFile',
        multiple: true,
        data: optionsData,
        size: 1024 * 5, //限制文件大小，单位 KB
        exts: exts,//只允许上传jpg,png文件
        before: function (obj) {
            layer.msg('图片上传中...', {
                icon: 16,
                shade: 0.01,
                time: 0
            })
        },
        done: function (res) {
            console.log(res);
            layer.close(layer.msg());//关闭上传提示窗口
            if(res.fileName.indexOf('pdf')>=0 || res.fileName.indexOf('PDF')>=0){
                $('#' + elemId + ' .upload_img_list').append('<dd class="item_img" id="' + res.fileRecordMainId + '" data-id="' + res.fileRecordMainId + '" ><div class="operate"><i onclick=UPLOAD_IMG_DEL("' + res.fileRecordMainId + '") class="close layui-icon"></i></div><img src="' +ctx+ '/meta/pmls/images/pdf.png" class="img" onclick="showPdfPage(\''+res.fileUrl+'\')" style="object-fit:contain;"></dd>');
            }else{
                if (multiple == true) {//调用多图上传方法,其中res.imgid为后台返回的一个随机数字
                    $('#' + elemId + ' .upload_img_list').append('<dd class="item_img" id="' + res.fileRecordMainId + '" data-id="' + res.fileRecordMainId + '"><div class="operate"><i onclick=UPLOAD_IMG_DEL("' + res.fileRecordMainId + '") class="close layui-icon"></i></div><img src="' + res.fileAbbrUrl + '" data-original="' + res.fileUrl + '" class="img" ><input type="hidden" name="dzd_img[]" value="' + res.fileAbbrUrl + '" /></dd>');
                } else {//调用单图上传方法,其中res.imgid为后台返回的一个随机数字
                    $('#' + elemId + ' .upload_img_list').html('<dd class="item_img" id="' + res.fileRecordMainId + '" data-id="' + res.fileRecordMainId + '"><div class="operate"><i onclick=UPLOAD_IMG_DEL("' + res.fileRecordMainId + '") class="close layui-icon"></i></div><img src="' + res.fileAbbrUrl + '" data-original="' + res.fileUrl + '" class="img" ><input type="hidden" name="dzd_img" value="' + res.fileAbbrUrl + '" /></dd>');
                }
            }


            setTimeout(function () {
                // let jqueryObj = $('#'+elemId+' .upload_img_list');//jQuery对象
                // let domObj = jqueryObj[0];//DOM对象
                let node = document.getElementById(elemId);
                let domObj = node.getElementsByClassName("upload_img_list")[0];
                var viewer = new Viewer(domObj, {
                    url: 'data-original',
                    show: function () {// 动态加载图片后，更新实例
                        viewer.update();
                    },
                });
            }, 100)
        }
    })
}
//新窗口打开PDF文件
function showPdfPage(url){
    event.stopPropagation();
    event.cancelBubble=true;
    window.open(url);
}
//删除图片
function UPLOAD_IMG_DEL(divs) {
    $.ajax({
        type: "POST",
        url: BASE_PATH + "/file/delFileCommon",
        data: {"fileRecordMainId": divs},
        async: false,
        success: function (data) {
            var jsonObj = JSON.parse(data);
            if (jsonObj.returnCode == 200) {
                $("#" + divs).remove();
            } else {
                msgAlert("图片删除失败");
            }
        },
        error: function () {
            msgAlert("图片删除失败");
        }
    });
}


//初始化图片列表
function loadImageList(elemId, imageList, isDelete) {
    if (imageList != null && imageList.length > 0) {
        for (var i = 0; i < imageList.length; i++) {
            var res = imageList[i];
            if(res.fileSuffix=='pdf' || res.fileSuffix=='PDF'){//pdf文件
                if (isDelete) {
                    $('#' + elemId + ' .upload_img_list').append('<dd class="item_img" id="' + res.fileRecordMainId + '" data-id="' + res.fileRecordMainId + '" ><div class="operate"><i onclick=UPLOAD_IMG_DEL("' + res.fileRecordMainId + '") class="close layui-icon"></i></div><img src="' +ctx+ '/meta/pmls/images/pdf.png" class="img" onclick="showPdfPage(\''+res.fileUrl+'\')" style="object-fit:contain;"></dd>');
                } else {
                    $('#' + elemId + ' .upload_img_list').append('<dd class="item_img" id="' + res.fileRecordMainId + '" data-id="' + res.fileRecordMainId + '"><div class="operate"></div><img src="' +ctx+ '/meta/pmls/images/pdf.png" class="img" onclick="showPdfPage(\''+res.fileUrl+'\')" style="object-fit:contain;"></dd>');
                }
            }else{//图片文件
                if (isDelete) {
                    $('#' + elemId + ' .upload_img_list').append('<dd class="item_img" id="' + res.fileRecordMainId + '" data-id="' + res.fileRecordMainId + '"><div class="operate"><i onclick=UPLOAD_IMG_DEL("' + res.fileRecordMainId + '") class="close layui-icon"></i></div><img src="' + res.fileAbbrUrl + '" data-original="' + res.fileUrl + '" class="img" ><input type="hidden" name="dzd_img" value="' + res.fileAbbrUrl + '" /></dd>');
                } else {
                    $('#' + elemId + ' .upload_img_list').append('<dd class="item_img" id="' + res.fileRecordMainId + '" data-id="' + res.fileRecordMainId + '"><div class="operate"></div><img src="' + res.fileAbbrUrl + '" data-original="' + res.fileUrl + '" class="img" ><input type="hidden" name="dzd_img[]" value="' + res.fileAbbrUrl + '" /></dd>');
                }
            }
        }
    }
    setTimeout(function () {
       let node = document.getElementById(elemId).parentElement;
//        let domObj = node.getElementsByClassName("upload_img_list")[0];
//     	let domObj = document.getElementsByClassName("upload_img_list")[0];
        var viewer = new Viewer(node, {
            url: 'data-original',
            show: function () {// 动态加载图片后，更新实例
                viewer.update();
//                $(document).on("click", ".viewer-flip-download", function(){
//                    const a = document.createElement('a');
//                    a.href = viewer.image.src;
//                    a.download = viewer.image.alt;
//                    document.body.appendChild(a);
//                    a.click();
//                    document.body.removeChild(a);
//                });
//

                $(document).on("click", ".viewer-flip-download", function(){
                    var src = $(".viewer-canvas").find("img").attr("src");
                    var fileName = $(".viewer-canvas").find("img").attr("alt");
                    //参数格式化
                    fileName = formatOptions(fileName);
                    if (src) {
                        var link = document.createElement('a');
                        link.href = BASE_PATH + "/files/downloadFile"+"?fileName=" + fileName + "&fileUrl=" + src;
                        link.style.cssText = 'display:none;position:absolute;left:-9999px;top:-9999px;';
                        document.body.appendChild(link);
                        link.click();
                        setTimeout(function () {
                            document.body.removeChild(link);
                        }, 5000);
                    }
                    return false;
                });

            }
        });
    }, 100)
}


function formatOptions(option){
    if(!isBlank(option)){
        //将option中的&转义,防止被浏览器识别为参数
        option = option.replace(/\&/g, "%26");
        //将option中的？转义,防止被浏览器识别为参数
        option = option.replace(/\?/g, "%3F");
        //将option中的#转义,防止被浏览器识别为参数
        option = option.replace(/\#/g, "%23");
    }
    return option;
}

/**
 * 其他业绩城市
 */
function achievementCity(nodeId, checkValue, callback) {
    var url = BASE_PATH + "/pmlsQtReport/getBasCitySettingList";
    var params = {};
    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择</option>";
        $.each(data.returnValue, function (n, value) {
            if (checkValue != null && checkValue == value.cityNo) {
                result += "<option value='" + value.cityNo + "' selected>" + value.cityName + "</option>";
            } else {
                result += "<option value='" + value.cityNo + "'>" + value.cityName + "</option>";
            }
        });
        $("#" + nodeId).html('');
        $("#" + nodeId).append(result);

        callback ? callback() : $.noop();
    }, function () {
    });
}

/**
 * 其他业绩中心
 */
function achievementCenter(nodeId, accCity, checkValue, callback) {
    var url = BASE_PATH + "/pmlsQtReport/getAchivAchievementLevelSettingList/" + accCity;
    var params = {};

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择业绩归属中心</option>";
        $.each(data.returnValue, function (n, value) {
            if (checkValue != null && checkValue == value.centerGroupId) {
                result += "<option value='" + value.centerGroupId + "' selected>" + value.centerGroupName + "</option>";
            } else {
                result += "<option value='" + value.centerGroupId + "'>" + value.centerGroupName + "</option>";
            }
        });
        $("#" + nodeId).html('');
        $("#" + nodeId).append(result);
        callback ? callback() : $.noop();
    }, function () {

    });
}

//去掉前后字符串空格
function trimStr(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

//判断字符串是否有空格
function isIncludeBlank(str) {
//	var reg =/\s/;
//	return reg.test(str);
    if (str.indexOf(" ") != -1) {
        return true;//有
    } else {
        return false;//没有
    }
}


function signRed(res) {
    try {
        if (res.returnCode == 200 && res.returnData.length > 0) {
            for (var i = 0; i < res.returnData.length; i++) {
                if (res.returnData[i].num < 0) {
                    /*               //锁定列无效
                                   var $tr = $("div[lay-id='contentReload']").find("tr").eq(i);
                                   $tr.attr('style','color:red');*/
                    $("div[lay-id='contentReload']").find("tr[data-index='" + i + "']").attr('style', 'color:red');
                }
            }
        }
    }
    catch (err) {
        console.log("catch signRed fail")
    }
}


Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

// 日期比较
function dateCompare(date1, date2) {
    var oDate1 = new Date(date1);
    var oDate2 = new Date(date2);
    if (oDate1.getTime() >= oDate2.getTime()) {
        return true;
    } else {
        return false;
    }
}


function buildingNoRepeatCount(buildingNo, reportId, type) {
//	alert(type+"##"+reportId);
    var flag = false;
    var returnData = '';
    var url = BASE_PATH + '/sceneTrade/selectBuildingNoRepeatCount';
    $.ajax({
        type: "POST",
        url: url,
        async: false,
        data: {buildingNo: buildingNo, reportId: reportId},
        dataType: "json",
        success: function (data) {
            if (data.returnCode == '200') {
                returnData = data.returnData;
                if (returnData == undefined || returnData == null || returnData == '') {
                    flag = true;
                }
            } else {

            }
        },
        error: function () {

        }
    });

    if (!flag) {
        msgAlert("该楼室号已报备，请勿重复录入！（报备编号：" + returnData + "）");
    }
    return flag;
}

/**
 * 替换英文括号为中文
 * @param str 字符串
 * @returns
 */
function replaceBrackets(str) {
    var reg = /[\(]/g, reg2 = /[\)]/g;
    var str1 = str.replace(reg, "（").replace(reg2, "）");
    return str1;
}

/**
 * 数组元素重复判断
 * @param ary
 * */
function isRepeatAry(ary) {
    var sortAry = ary.sort();
    for (var i = 0; i < ary.length; i++) {
        if (sortAry[i] == sortAry[i + 1]) {
            return sortAry[i];
        }
    }
    return "";
}

/**
 * 判断2个时间大小
 * @param date1
 * @param date2
 * @returns
 * example：tab('2015-10-10','2015-10-11');
 */
function tabDate(date1,date2){
    var oDate1 = new Date(date1);
    var oDate2 = new Date(date2);
   if(oDate1.getTime() > oDate2.getTime()){
        return true;//第一个大
    } else {
        return false;//第二个大
    }
}

/* 数组元素包含 */
function contains(arr, obj) {
    var i = arr.length;
    while (i--) {
        if (arr[i] === obj) {
            return true;
        }
    }
    return false;
}


function ajaxGetSync(url, params, successback, failback) {
    if (url.indexOf("?") > 0) {
        url = url + "&" + rnd();
    } else {
        url = url + "?" + rnd();
    }
    $.ajax({
        url: url,
        data: params,
        type: 'GET',
        async: false,
        dataType: 'json',
        success: function (data) {
            if (data && data.returnCode == '200') {
                if (successback) {
                    successback(data);
                }
                return;
            }
            if (failback) {
                failback(data);
            }
        }
    });
    return true;
}


function checkPreBack(reportId){
    var flag = true;
    var url = BASE_PATH + "/sceneTrade/getReport";
    var params ={reportId:reportId};
    ajaxGetSync(url, params, function (data) {
        var reportDb = data.returnValue;
        var preBack = reportDb.preBack;
        if('1'==preBack){
            flag = false;
        }
    }, function () {
        flag = false;
    });
    return flag;
}

//去掉前后字符串空格 换行符
function trimBlankStr(str) {
    if(isEmpty(str)){
        return "";
    }else {
        return str.replace(/(^\s*)|(\s*$)/g, "").replace(/[\r\n]/g,"");
    }
}



function checkExport(reportKey){
    var flag = true;
    var url = BASE_PATH + "/pmlsLinkDetail/checkExport";
    var params ={reportKey:reportKey};
    ajaxGetSync(url, params, function (data) {
        var userReportSize = data.returnValue;
        console.log('userReportSize='+userReportSize);
        if(userReportSize>0){
            flag = false;
        }
    }, function () {
    });
    return flag;
}


/**
 * 字典数据取得
 */
function dictionaryLinkageIsServiceSync(id, typeId, callback) {
    var url = BASE_PATH + "/sceneTrade/queryDictionary/" + typeId;
    var params = {};

    ajaxGetSync(url, params, function (data) {
        var result = "<option value=''>请选择</option>"

        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.dicCode + "'>"
                + value.dicValue + "</option>";
        });
        $("#" + id).html('');
        $("#" + id).append(result);
        callback ? callback() : $.noop();
    }, function () {

    });
}



//滚动到指定位置
function scrollToElement(element,speed) {
    if(!speed){
        speed = 300;
    }
    if(!element){
        $("html,body").animate({scrollTop:0},speed);
    }else{
        if(element.length>0){
            $("html,body").animate({scrollTop:$(element).offset().top-200},speed);
        }
    }
}

//type:1:小编辑  2：大编辑
function checkEstateStatus(id){
    var estate = '';
    var url = BASE_PATH + "/pmlsEstate/getEstateById/"+id;
    ajaxGetSync(url, null, function (data) {
        estate = data.returnData;
    }, function () {

    });
    return estate;
}


/**
 * 判断两位小数
 * @param num
 * @returns {Boolean}  返回判断结果
 */
function checkNumTwoDecimals(num) {
    // 验证手机号格式
    var reg = /^[+]?(0|([1-9]\d*))(\.\d{0,2})?$/;
    if (!reg.test(num)) {
        return false;
    }
    return true;
}