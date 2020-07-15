
function initOrgList() {
    var url = BASE_PATH + "/commons/queryOrgList";
    var params = {};
    ajaxGet(url, params, function (data) {

        var list = data.returnValue;
        if (list != null && list.length > 0) {
            var result = '<option value="">请选择</option>';
            for (var i = 0; i < list.length; i++) {
                if (i == list.length - 1){
                    result += "<option value='" + list[i].orgCode + "' selected>"
                        + list[i].orgYear + "</option>";
                    initCityList(list[i].orgCode);
                }else{
                    result += "<option value='" + list[i].orgCode + "'>"
                        + list[i].orgYear + "</option>";
                }
            }

            $("#org").html('');
            $("#org").append(result);
        }
    }, function () {
    });

    $("#org").change(
        function () {
            var orgCode = $("#org").val();
            resetCenter();
            if (isBlank(orgCode)){
                resetCity();
                return;
            }
            if (orgCode == '1081'){
                $("#centerLabel").html("业务部");
            }else{
                $("#centerLabel").html("中心");
            }

            initCityList(orgCode);
        });
}

function initCityList(orgCode) {

    resetCity();

    var url = BASE_PATH + "/commons/queryCityList";
    var params = {orgCode: orgCode};
    ajaxGet(url, params, function (data) {

        var list = data.returnValue;
        if (list != null && list.length > 0) {
            var html = '';
            for (var i = 0; i < list.length; i++) {
                html = html + '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].cityNo + '" data-text="' + list[i].cityName + '"><span>' + list[i].cityName + '</span></label> </li>';
            }

            $('#cityId').find('.multi-select-list').append(html);
        }
    }, function () {
    });
}

var cityNoStr = '';

$('#city').find('.multi-select').multiSelect({
    check: function ($instance) {
        var cityNo = $instance.value.toString();
        if (isBlank(cityNo)){
            resetCenter();
            return;
        }

        cityNoStr = cityNo;
        initCenterList($("#org").val());
    }
});

var centerIdStr = '';
$('#center').find('.multi-select').multiSelect({
    check: function ($instance) {
        centerIdStr = $instance.value.toString();
    }
});

function initCenterList(orgCode) {

    resetCenter();
    var url = BASE_PATH + "/commons/queryCenterList";

    ajaxGet(url, {cityNo: cityNoStr.replaceAll(",", "','"),orgCode: orgCode}, function (data) {
        var list = data.returnValue;
        if (list != null && list.length > 0) {
            var html = '';
            for (var i = 0; i < list.length; i++) {
                html = html + '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].centerId + '" data-text="' + list[i].centerName + '"><span>' + list[i].centerName + '</span></label> </li>';
            }

            $('#centerId').find('.multi-select-list').append(html);
        }

    }, function () {
    });
}

function resetCity(){
    cityNoStr = '';
    $('#cityId').find('.multi-select-item').not(':first').remove();
    $('#cityId').find('.multi-select-checkall').removeAttr("checked");
    $('#cityId').find('.multi-select-value').val('');
    $('#cityId').find('.multi-select-text').val('');
    $('#cityId').find('.multi-select-text').text('');
}

function resetCenter(){
    centerIdStr = '';
    $('#centerId').find('.multi-select-item').not(':first').remove();
    $('#centerId').find('.multi-select-checkall').removeAttr("checked");
    $('#centerId').find('.multi-select-value').val('');
    $('#centerId').find('.multi-select-text').val('');
    $('#centerId').find('.multi-select-text').text('');
}