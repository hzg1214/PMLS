var defaultYear = new Date().getFullYear();

// 架构年份
function organizationLinkAge(start, end, def, orgChangeCallBack) {

    if (isEmpty(start)) {
        start = 2018;
    }
    if (isEmpty(end)) {
        end = defaultYear;
    }
    if (isEmpty(def)) {
        def = defaultYear;
    }

    var result = "";
    for (var i = end; i >= start; i--) {
        var value = i;
        if (i == def) {
            result += "<option value='" + value + "' selected>" + i + "</option>";
        } else {
            result += "<option value='" + value + "'>" + i + "</option>";
        }
    }
    $("#orgYear").empty().append(result);

    form.render('select')

    form.on('select(orgYear)', function (data) {
        var orgYear = $('#orgYear').val();
        orgYearChange(orgYear);
        orgChangeCallBack ? orgChangeCallBack() : $.noop();
    });

    orgYearChange(def);
}

function orgYearChange(orgYear) {

    resetValue('region');
    resetValue('areaCity');
    resetValue('city');
    resetValue('centerGroup');

    if (orgYear == 2017) {
        formSelects.disabled('region');
        formSelects.disabled('areaCity');
        cityLinkAge();
    }
    else if (orgYear == 2018) {
        formSelects.undisabled('region');
        formSelects.undisabled('areaCity');
        regionLinkAge();
    }
    // 2019,2020
    else {
        formSelects.disabled('region');
        formSelects.undisabled('areaCity');
        areaCityLinkAge();
    }
}

// HBL归属区域
function regionLinkAge() {

    var url = BASE_PATH + "/pmlsCommonReport/queryHblRegionList";
    var params = {};
    params.organization = $("#orgYear").val();
    var result = [];
    var allIds = [];
    ajaxGet(url, params, function (data) {
        $.each(data.returnData, function (n, value) {
            result.push({"value": value.qyCode, "name": value.qyName});
            allIds.push(value.qyCode);
        });
        formSelects.data('region', 'local', {arr: result});

        // 绑定change事件
        formSelects.on('region', function (id, vals, val, isAdd, isDisabled) {
            resetValue('areaCity');
            resetValue('city');
            resetValue('centerGroup');

            var ids = [];
            if (isAdd) {
                vals.push(val);
            }

            if (vals.length > 0) {
                for (var i = 0; i < vals.length; i++) {
                    if (isAdd == true || vals[i].value != val.value) {
                        ids.push(vals[i].value)
                    }
                }
            }
            if (!isEmpty(ids)) {
                areaCityLinkAge(ids.join());
            }
        });

        formSelects.btns('region', [
            {
                icon: "xm-iconfont icon-quanxuan",
                name: '全选',
                click: function (id) {
                    //点击后的操作
                    layui.formSelects.value(id, allIds);
                    resetValue('areaCity');
                    resetValue('city');
                    resetValue('centerGroup');

                    areaCityLinkAge();

                }
            }, {
                icon: "xm-iconfont icon-qingkong",
                name: '清空',
                click: function (id) {
                    //点击后的操作
                    formSelects.value(id, [])

                    resetValue('areaCity');
                    resetValue('city');
                    resetValue('centerGroup');

                }
            }
        ]);

    });
}

// HBL归属板块
function areaCityLinkAge(regionCodes) {

    if (isEmpty(regionCodes)) {
        regionCodes = formSelects.value('region', 'valStr');
    }

    var url = BASE_PATH + "/pmlsCommonReport/queryHblAreaCityList";
    var params = {};
    params.organization = $("#orgYear").val();
    params.regionCodes = regionCodes;

    var result = [];
    var allIds = [];
    ajaxGet(url, params, function (data) {

        $.each(data.returnData, function (n, value) {
            result.push({"value": value.areaCityCode, "name": value.areaCityName});
            allIds.push(value.areaCityCode);
        });
        formSelects.data('areaCity', 'local', {arr: result});

        // 绑定change事件
        formSelects.on('areaCity', function (id, vals, val, isAdd, isDisabled) {
            resetValue('city');
            resetValue('centerGroup');

            var ids = [];
            if (isAdd) {
                vals.push(val);
            }

            if (vals.length > 0) {
                for (var i = 0; i < vals.length; i++) {
                    if (isAdd == true || vals[i].value != val.value) {
                        ids.push(vals[i].value)
                    }
                }
            }
            if (!isEmpty(ids)) {
                cityLinkAge(ids.join());
            }
        });

        formSelects.btns('areaCity', [
            {
                icon: "xm-iconfont icon-quanxuan",
                name: '全选',
                click: function (id) {
                    //点击后的操作
                    layui.formSelects.value(id, allIds);
                    resetValue('city');
                    resetValue('centerGroup');

                    cityLinkAge();

                }
            }, {
                icon: "xm-iconfont icon-qingkong",
                name: '清空',
                click: function (id) {
                    //点击后的操作
                    formSelects.value(id, [])

                    resetValue('city');
                    resetValue('centerGroup');

                }
            }
        ]);
    });
}

// HBL归属城市
function cityLinkAge(areaCityCodes) {
    if (isEmpty(areaCityCodes)) {
        areaCityCodes = formSelects.value('areaCity', 'valStr');
    }
    var url = BASE_PATH + "/pmlsCommonReport/queryHblCityList";
    var params = {};
    params.organization = $("#orgYear").val();
    params.regionCodes = formSelects.value('region', 'valStr');
    params.areaCityCodes = areaCityCodes;

    var result = [];
    var allIds = [];
    ajaxGet(url, params, function (data) {

        $.each(data.returnData, function (n, value) {
            result.push({"value": value.cityId, "name": value.cityGroupName});
            allIds.push(value.cityId);
        });
        formSelects.data('city', 'local', {arr: result});

        // 绑定change事件
        formSelects.on('city', function (id, vals, val, isAdd, isDisabled) {
            resetValue('centerGroup');


            var ids = [];
            if (isAdd) {
                vals.push(val);
            }

            if (vals.length > 0) {
                for (var i = 0; i < vals.length; i++) {
                    if (isAdd == true || vals[i].value != val.value) {
                        ids.push(vals[i].value)
                    }
                }
            }
            if (!isEmpty(ids)) {
                centerGroupLinkAge(ids.join());
            }
        });

        formSelects.btns('city', [
            {
                icon: "xm-iconfont icon-quanxuan",
                name: '全选',
                click: function (id) {
                    //点击后的操作
                    layui.formSelects.value(id, allIds);
                    resetValue('centerGroup');
                    centerGroupLinkAge();

                }
            }, {
                icon: "xm-iconfont icon-qingkong",
                name: '清空',
                click: function (id) {
                    //点击后的操作
                    formSelects.value(id, [])
                    resetValue('centerGroup');
                }
            }
        ]);
    });

}

// HBL归属中心
function centerGroupLinkAge(cityIds) {
    if (isEmpty(cityIds)) {
        cityIds = formSelects.value('city', 'valStr');
    }
    var url = BASE_PATH + "/pmlsCommonReport/queryHblCenterList";
    var params = {};
    params.organization = $("#orgYear").val();
    params.regionCodes = formSelects.value('region', 'valStr');
    params.areaCityCodes = formSelects.value('areaCity', 'valStr');
    params.cityIds = cityIds;

    var result = [];
    ajaxGet(url, params, function (data) {

        $.each(data.returnData, function (n, value) {
            result.push({"value": value.centerId, "name": value.centerName});
        });
        formSelects.data('centerGroup', 'local', {arr: result});

    });

}

function resetValue(id) {
    formSelects.data(id, 'local', {arr: []});
}

/**
 * desc:架构年份为2019或2020时，归属城市必选;
 *        架构年份为2018时，归属区域必选;
 *      架构年份为2017时，所在城市必选;
 * @param organization
 * @param areaCityCodes
 * @param region
 * @param city
 * @returns
 */
function checkComm(organization, areaCityCodes, region, city) {
    if (organization == '2019' || organization == '2020') {
        if (isBlank(areaCityCodes)) {
            parent.layer.closeAll();
            parent.layer.alert('架构年份为2019或2020时，归属城市必选！', {icon: 2, title: '提示'});
            return false;
        }
    } else if (organization == '2018') {
        if (isBlank(region)) {
            parent.layer.closeAll();
            parent.layer.alert('架构年份为2018时，归属区域必选！', {icon: 2, title: '提示'});
            return false;
        }
    } else if (organization == '2017') {
        if (isBlank(city)) {
            parent.layer.closeAll();
            parent.layer.alert('架构年份为2017时，所在城市必选！', {icon: 2, title: '提示'});
            return false;
        }
    }
    return true;
}