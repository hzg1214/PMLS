/**
 * Created by eju on 2018/1/18.
 */

//格式化时间
var formatDate = function (date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '' + m + '' + d;
};

/************************************组织架构级联查询start*************************************/

/**
 * 根据组织架构初始化归属区域
 * @param selector 组织架构的选择器，不传的话默认为organization
 * @param selfSelector 归属区域的选择器，不传默认为region
 * @param selectors 下级级联组件的选择器，如果需要自定义，这里可以传数组，不传则取默认值
 */
function initRegion(selector, selfSelector, selectors) {

    if (isBlank(selector)) {
        selector = "#organization";
    }

    //组织架构
    var organization = $(selector).val();

    if (isBlank(selfSelector)) {
        if ("2017" == organization) {
            selfSelector = "#city";
        } else if ("2018" == organization) {
            selfSelector = "#region";
        }else if ("2019" == organization) {
//            selfSelector = "#region";
            selfSelector = "#areaCity";
        }
    }

    if (isBlank(selectors) || selectors.length <= 0) {
        selectors = ["#region", "#areaCity", "#city", "#centerGroup","#dept"];
    }

    //2017年没有归属区域和归属城市,直接查询所在城市
    if ("2017" == organization) {
        url = BASE_PATH + "/commonReport/getCityGroup";

        $(selectors[0]).find("input[type=\"text\"]").attr("disabled", true);
        $(selectors[1]).find("input[type=\"text\"]").attr("disabled", true);

    } else if ("2018" == organization) {
        var url = BASE_PATH + "/commonReport/getRegion";

        $(selectors[0]).find("input[type=\"text\"]").attr("disabled", false);
        $(selectors[1]).find("input[type=\"text\"]").attr("disabled", false);
    }
    else if ("2019" == organization) {
        var url = BASE_PATH + "/commonReport/getAllAreaCity";

//        $(selectors[0]).find("input[type=\"text\"]").attr("disabled", false);
        $(selectors[0]).find("input[type=\"text\"]").attr("disabled", true);
        $(selectors[1]).find("input[type=\"text\"]").attr("disabled", false);
    }

    var params = {
        yearly: organization,
        regionCodes: "",
        areaCityCodes: ""
    }

    ajaxGet(url, params, function (data) {

        var list = data.list;

        //清空下级选项
        clearMultiSelect(selectors);

        if ("2018" == organization) {
            handleMultiSelect(list, "1", selfSelector);
        } else if ("2019" == organization) {
//            handleMultiSelect(list, "1", selfSelector);
            handleMultiSelect(list, "2", selfSelector);
        } 
        else if ("2017" == organization) {
            handleMultiSelect(list, "3", selfSelector);
        }


    }, function () {

    });
}

/**
 * 下拉控件改变值的事件初始化
 * @param selector 组织架构的选择器，不传的话默认为organization
 * @param selectors 下级级联组件的选择器，不传则取默认值
 */
function initMultiSelect(selector, selectors) {

    if (isBlank(selector)) {
        selector = "#organization";
    }

    if (isBlank(selectors) || selectors.length <= 0) {
        selectors = {
            region: "#region",
            areaCity: "#areaCity",
            city: "#city",
            centerGroup: "#centerGroup",
            dept: "#dept"
        }
    }

    $("div[name^='group']").find('.multi-select').multiSelect({
        check: function ($instance) {

            var organization = $(selector).val();

            var groupName = $($instance).parent().attr("name");

            if (groupName == "group1") {
                //归属区域选择事件
                if (!isBlank(selectors.region)) {

                    var url = BASE_PATH + "/commonReport/getAreaCity";

                    var regionCodes = $(selectors.region).find(".multi-select-value").val();

                    var params = {
                        yearly: organization,
                        regionCodes: regionCodes
                    }

                    ajaxGet(url, params, function (data) {

                        var list = data.list;

                        var nextSelectors = [selectors.areaCity, selectors.city, selectors.centerGroup];

                        //清空下级选项
                        clearMultiSelect(nextSelectors);

                        handleMultiSelect(list, "2", selectors.areaCity);

                    }, function () {
                        var nextSelectors = [selectors.areaCity, selectors.city, selectors.centerGroup];
                        clearMultiSelect(nextSelectors);
                    });

                }

            } else if (groupName == "group2") {
                //归属城市选择事件
                if (!isBlank(selectors.areaCity)) {

                    var url = BASE_PATH + "/commonReport/getCityGroup";

                    var regionCodes = $(selectors.region).find(".multi-select-value").val();
                    var areaCityCodes = $(selectors.areaCity).find(".multi-select-value").val();

                    var params = {
                        yearly: organization,
                        regionCodes: regionCodes,
                        areaCityCodes: areaCityCodes
                    }

                    ajaxGet(url, params, function (data) {

                        var list = data.list;

                        var nextSelectors = [selectors.city, selectors.centerGroup];

                        //清空下级选项
                        clearMultiSelect(nextSelectors);

                        handleMultiSelect(list, "3", selectors.city);

                    }, function () {
                        var nextSelectors = [selectors.city, selectors.centerGroup];
                        clearMultiSelect(nextSelectors);
                    });

                }

            } else if (groupName == "group3") {
                //所在城市选择事件
                if (!isBlank(selectors.city)) {
                    //查询归属中心
                    var url = BASE_PATH + "/commonReport/getCenterGroup";

                    var regionCodes = $(selectors.region).find(".multi-select-value").val();
                    var areaCityCodes = $(selectors.areaCity).find(".multi-select-value").val();
                    var cityIds = $(selectors.city).find(".multi-select-value").val();

                    var params = {
                        yearly: organization,
                        regionCodes: regionCodes,
                        areaCityCodes: areaCityCodes,
                        cityIds: cityIds
                    }

                    ajaxGet(url, params, function (data) {

                        var list = data.list;

                        var nextSelectors = [selectors.centerGroup];

                        //清空下级选项
                        clearMultiSelect(nextSelectors);

                        handleMultiSelect(list, "4", selectors.centerGroup);

                    }, function () {
                        var nextSelectors = [selectors.centerGroup];
                        clearMultiSelect(nextSelectors);
                    });

                    //查询归属部门
                    var urlDept = BASE_PATH + "/commonReport/getDeptGroup";
                    var paramsDept = {
                        cityIds: cityIds
                    }
                    ajaxGet(urlDept, paramsDept, function (data) {

                        var list = data.list;

                        var nextSelectors = [selectors.dept];

                        //清空下级选项
                        clearMultiSelect(nextSelectors);

                        handleMultiSelect(list, "5", selectors.dept);

                    }, function () {
                        var nextSelectors = [selectors.dept];
                        clearMultiSelect(nextSelectors);
                    });
                }

            }
        }
    });

}

function initFollowAim() {
    clearMultiSelect(["#followAim"]);
    //跟进目的
    var list = [{'code': '19501', 'value': '签约'},
        {'code': '19502', 'value': '交易'},
        {'code': '19503', 'value': '联动'},
        {'code': '19504', 'value': '培训'},
        {'code': '19505', 'value': '翻牌'},
        {'code': '19506', 'value': '系统'},
        {'code': '19507', 'value': '查存活'}];

    handleMultiSelect(list, "6", "#followAim");
}

/**
 * 组织架构切换事件
 * @param selector
 */
function initOrganizationEvent(selector) {
    if (isBlank(selector)) {
        selector = "#organization";
    }

    $(selector).change(function () {
        initRegion();
    });
}


/**
 * 清空多选下拉框的选项
 * @param selectors 多选框的选择器（集合类型）
 */
function clearMultiSelect(selectors) {

    if (!isBlank(selectors) && selectors.length > 0) {
        for (var i in selectors) {
            if (!isBlank(selectors[i])) {
                $(selectors[i]).find(".multi-select-item").not(':first').remove();
                $(selectors[i]).find('.multi-select-checkall').removeAttr("checked");
                clearValue(selectors[i]);
            }
        }
    }

}

/**
 * 清空下拉框的值
 * @param selector
 */
function clearValue(selector) {
    $(selector).find('.multi-select-value').val('');
    $(selector).find('.multi-select-text').val('');
    $(selector).find('.multi-select-text').text('');
}

/**
 * 为下拉框赋值
 * @param list 后台返回数据
 * @param typeId 类型，1，归属区域  2，归属城市  3，所在城市 4，归属中心 5,归属部门 6,跟进目的
 * @param selfSelector 当前需要赋值的选择器
 */
function handleMultiSelect(list, typeId, selfSelector) {

    if (list != null && list.length > 0 && !isBlank(typeId) && !isBlank(selfSelector)) {

        var html = '';

        if (typeId == "1") {

            for (var i = 0; i < list.length; i++) {
                html += '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].regionCode + '" data-text="' + list[i].regionName + '"><span>' + list[i].regionName + '</span></label> </li>';
            }

        } else if (typeId == "2") {

            for (var i = 0; i < list.length; i++) {
                html += '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].areaCityCode + '" data-text="' + list[i].areaCityName + '"><span>' + list[i].areaCityName + '</span></label> </li>';
            }

        } else if (typeId == "3") {

            for (var i = 0; i < list.length; i++) {
                html += '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].cityId + '" data-text="' + list[i].cityGroupName + '"><span>' + list[i].cityGroupName + '</span></label> </li>';
            }

        } else if (typeId == "4") {

            for (var i = 0; i < list.length; i++) {
                html += '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].centerGroupId + '" data-text="' + list[i].centerGroupName + '"><span>' + list[i].centerGroupName + '</span></label> </li>';
            }

        } else if (typeId == "5") {

            for (var i = 0; i < list.length; i++) {
                html += '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].projectDepartmentId + '" data-text="' + list[i].projectDepartment + '"><span>' + list[i].projectDepartment + '</span></label> </li>';
            }

        }else if (typeId == "6") {

            for (var i = 0; i < list.length; i++) {
                html += '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].code + '" data-text="' + list[i].value + '"><span>' + list[i].value + '</span></label> </li>';
            }

        }else if (typeId == "7") {

            for (var i = 0; i < list.length; i++) {
                html += '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].accountProjectNo + '" data-text="' + list[i].accountProject + '"><span>' + list[i].accountProject + '</span></label> </li>';
            }

        }

        $(selfSelector).find('.multi-select-list').append(html);

    }

}

/************************************组织架构级联查询end*************************************/

function initCityList(selector, orgCode,type) {

    if (isBlank(selector)) {
        selector = "#cityNo";
    }

    var selectors = [selector];
    clearMultiSelect(selectors);

    var url = "";
    if("1" == type){
        url += BASE_PATH + "/commons/getStoreCity"
    }else {
        url += BASE_PATH + "/commons/queryCityList";
    }
    var params = {orgCode: orgCode};
    ajaxGet(url, params, function (data) {

        var list = data.returnValue;
        if (list != null && list.length > 0) {
            var html = '';
            for (var i = 0; i < list.length; i++) {
                html = html + '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].cityNo + '" data-text="' + list[i].cityName + '"><span>' + list[i].cityName + '</span></label> </li>';
            }

            $(selector).find('.multi-select-list').append(html);
        }
    }, function () {
    });
}


/**
 * 搜索保存的check
 * @param selectors = {organization : "#organization" , region : "#region",areaCity : "#areaCity",city : "#city",centerGroup : "#centerGroup",followAim : "#followAim"};
 * @returns {boolean}
 */
function validSelect(selectors) {
    if (!isBlank(selectors)) {

        if (isBlank(selectors.organization)) {
            selectors.organization = "#organization";
        }
        if (isBlank(selectors.region)) {
            selectors.region = "#region";
        }
        if (isBlank(selectors.areaCity)) {
            selectors.areaCity = "#areaCity";
        }
        if (isBlank(selectors.city)) {
            selectors.city = "#city";
        }
        if (isBlank(selectors.centerGroup)) {
            selectors.centerGroup = "#centerGroup";
        }
        if (isBlank(selectors.followAim)) {
            selectors.followAim = "#followAim";
        }
        if (isBlank(selectors.dept)) {
            selectors.dept = "#dept";
        }

    } else {
        selectors = {
            organization: "#organization",
            region: "#region",
            areaCity: "#areaCity",
            city: "#city",
            centerGroup: "#centerGroup",
            followAim: "#followAim",
            dept: "#dept"
        };
    }
    var organization = $(selectors.organization).val();
    var regionCodes = $(selectors.region).find(".multi-select-value").val();
    var areaCitys = $(selectors.areaCity).find(".multi-select-value").val();
    var cityIds = $(selectors.city).find(".multi-select-value").val();

    if ("2017" == organization) {
        if (isBlank(cityIds)) {
            systemLoaded("#contentAll");
            Dialog.alertInfo("架构年份为2017时，所在城市必选！");
            return false;
        }
    } else if ("2018" == organization) {
        if (isBlank(regionCodes)) {
            systemLoaded("#contentAll");
            Dialog.alertInfo("架构年份为2018时，归属区域必选！");
            return false;
        }
    }else if ("2019" == organization) {
//    	if (isBlank(regionCodes)) {
        if (isBlank(areaCitys)) {
            systemLoaded("#contentAll");
//            Dialog.alertInfo("架构年份为2019时，归属区域必选！");
            Dialog.alertInfo("架构年份为2019时，归属城市必选！");
            return false;
        }
    }

    return true;
}
