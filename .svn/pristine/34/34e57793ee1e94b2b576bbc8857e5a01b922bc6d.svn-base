function inputMoneyFormat(obj) {
    if (obj.value != "") {
        obj.value = getValueByMoneyString(obj.value);
    }
}
function inputNumberFormat(obj) {
    if (obj.value != "") {
        obj.value = getNumberByString(obj.value);
    }
}
// 去掉0-9以外的字符(用于输入整数，前面去零)
function getNumberByString(s) {
    return getCodeNoByString(s) * 1;
}
// 去掉0-9以外的字符(用于输入代码，前面不去零)
function getCodeNoByString(s) {
    var str = DBC2SBC(s);
    var numberValue = "";
    if (str == "") {
        return "";
    }
    for (var i = 0; i < str.length; i++) {
        var s = str.substr(i, 1);
        if (s >= "0" && s <= "9") {
            numberValue += s;
        }
    }
    return numberValue;
}
// 全角转换为半角
function DBC2SBC(str) {
    var result = '';
    str = str.replace(/。/g, "．");
    for (var i = 0; i < str.length; i++) {
        code = str.charCodeAt(i);
        if (code >= 65281 && code <= 65373) result += String.fromCharCode(str.charCodeAt(i) - 65248);
        else if (code == 12288) result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
        else result += str.charAt(i);
    }
    return result;
}
// 去掉数字和小数点以外的非法字符
function getValueByMoneyString(mStr) {
    var moneyStr = DBC2SBC(mStr);
    var realMoney = "";
    if (moneyStr == "") {
        return 0;
    }
    for (var i = 0; i < moneyStr.length; i++) {
        var s = moneyStr.substr(i, 1);
        if (s == "." || (s >= "0" && s <= "9")) {
            realMoney += s;
        }
    }
    return isNaN(realMoney * 1) ? 0 : realMoney * 1;
}
//// 焦点移除时，移除输入框提示信息
//function inputChk(obj) {
//    if ($.trim($(obj).val()) != "") {
//        $(obj).parent().parent().removeClass('has-error').find('.help-block').addClass('hide');
//    }
//}
//// 焦点移除时，移除日期框提示信息
//function inputDateChk(obj) {
//    if ($.trim($(obj).val()) != "") {
//        $(obj).parent().parent().parent().removeClass('has-error').find('.help-block').addClass('hide');
//    }
//}


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
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
/**
 * 日期计算
 * @returns {*}
 */
Date.prototype.dateAdd = function(Number) {
    return new Date(Date.parse(this) + (86400000 * Number));
};
/**
 * 获取本周开始时间
 * @returns {*}
 */
Date.prototype.getWeekStart = function () {
    return new Date(this.getFullYear(), this.getMonth(), this.getDate() - this.getDay() + 1);
};
/**
 * 获取本周结束时间
 * @returns {*}
 */
Date.prototype.getWeekEnd = function () {
    return new Date(this.getFullYear(), this.getMonth(), this.getDate() + ( 6 - this.getDay()));
};
/**
 * 获取上月开始时间
 * @returns {*}
 */
Date.prototype.getPrevMonthStart = function () {
    return new Date(this.getFullYear(), this.getMonth()-1, 1);
};
/**
 * 获取上月结束时间
 * @returns {*}
 */
Date.prototype.getPrevMonthEnd = function () {
    var monthStartDate = new Date(this.getFullYear(), this.getMonth()-1, 1);
    var monthEndDate = new Date(this.getFullYear(), this.getMonth(), 1);
    var monthDays = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
    return new Date(this.getFullYear(), this.getMonth()-1, monthDays);
};
/**
 * 获取近三个月开始时间
 * @returns {*}
 */
Date.prototype.getPrevThreeMonthStart = function () {
    return new Date(this.getFullYear(), this.getMonth()-2, 1);
};
/**
 * 获取近六个月开始时间
 * @returns {*}
 */
Date.prototype.getPrevSixMonthStart = function () {
    return new Date(this.getFullYear(), this.getMonth()-5, 1);
};
/**
 * 获取本月开始时间
 * @returns {*}
 */
Date.prototype.getMonthStart = function () {
    return new Date(this.getFullYear(), this.getMonth(), 1);
};
/**
 * 获取本月结束时间
 * @returns {*}
 */
Date.prototype.getMonthEnd = function () {
    return new Date(this.getFullYear(), this.getMonth(), this.getMonthDays());
};
/**
 * 获取本月的天数
 * @returns {*}
 */
Date.prototype.getMonthDays = function () {
    var monthStartDate = new Date(this.getFullYear(), this.getMonth(), 1);
    var monthEndDate = new Date(this.getFullYear(), this.getMonth() + 1, 1);
    return (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
};
/**
 * 获取本季度开始月份
 * @returns {*}
 */
Date.prototype.getQuarterStartMonth = function () {
    var quarterStartMonth = 0;
    if (this.getMonth() < 3) {
        quarterStartMonth = 0;
    }
    if (2 < this.getMonth() && this.getMonth() < 6) {
        quarterStartMonth = 3;
    }
    if (5 < this.getMonth() && this.getMonth() < 9) {
        quarterStartMonth = 6;
    }
    if (this.getMonth() > 8) {
        quarterStartMonth = 9;
    }
    return quarterStartMonth;
};

/**
 * 获取本季度的开始
 * @returns {*}
 */
Date.prototype.getQuarterStart = function () {
    return new Date(this.getFullYear(), this.getQuarterStartMonth(), 1);
};

/**
 * 获取本季度结束时间
 * @returns {*}
 */
Date.prototype.getQuarterEndDate = function () {
    var quarterEndMonth = this.getQuarterStartMonth() + 2;
    return new Date(this.getFullYear(), quarterEndMonth, this.getMonthDays());
};

/**
 * 导出Excel
 */
var tableToExcel = (function () {
    var uri = 'data:application/vnd.ms-excel;base64,',
        template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
        base64 = function (s) {
            return window.btoa(unescape(encodeURIComponent(s)));
        },
        format = function (s, c) {
            return s.replace(/{(\w+)}/g, function (m, p) {
                return c[p];
            });
        };
    return function (table, name) {
        var $table = [];
        $table = $(table).clone();
        $table.find("td").each(function () {
            if ($(this).css("display") == "none") {
                $(this).remove();
            }
        });
        $table.find("th").each(function () {
            if ($(this).css("display") == "none") {
                $(this).remove();
            }
        });
        var ctx = {
            worksheet: name || 'Worksheet', table: $table.html()
        };
        window.location.href = uri + base64(format(template, ctx));
    };
})();

/**
 * 日历控件
 * @constructor
 */
var crmDatepicker = {
    init: function (startId, endId) {
        $(startId).datepicker({
            language: 'zh-CN',
            todayBtn: 'linked',
            format: 'yyyy-mm-dd',
            clearBtn: true,
            autoClose: true
        }).on('show', function () {
            var endTime = $(endId).val();
            if (endTime == null || endTime == '')
                return;
            $(startId).datepicker('setEndDate', endTime);
        });

        $(endId).datepicker({
            language: 'zh-CN',
            todayBtn: 'linked',
            format: 'yyyy-mm-dd',
            clearBtn: true,
            autoClose: true
        }).on('show', function () {
            var endTime = $(startId).val();
            if (endTime == null || endTime == '')
                return;
            $(endId).datepicker('setStartDate', endTime);
        });
    },
};




















