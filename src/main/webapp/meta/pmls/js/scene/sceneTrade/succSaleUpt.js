layui.use(['element', 'laydate', 'form', 'table', 'layer', 'upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        upload = layui.upload,
        $ = layui.$;

    var uploadSuccSaleImg = [];
    if (reportInfo != null && reportInfo.fileList != null && reportInfo.fileList.length > 0) {
        for (var i = 0; i < reportInfo.fileList.length; i++) {
            if (reportInfo.fileList[i].fileTypeId == '1025') {
                uploadSuccSaleImg.push(reportInfo.fileList[i]);
            }
        }
    }

    init();

    function init() {
        var roughDate = $("#roughDate").val();
        if (isEmpty(roughDate)) {
            roughDate = "1900-1-1";
        }
        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }
        if (dateCompare(roughDate, switchDate)) {
            switchDate = roughDate;
        }
        laydate.render({
            elem: '#dealDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            max: nowDate,
            min: switchDate,
            trigger: 'click'
        });


        laydate.render({
            elem: '#settleConfirmDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            trigger: 'click'
        });


        var cityNo = $("#cityNo").val()
        var accountProjectNos = [];
        accountProjectNos.push("accountProjectNo");
        // 核算主体
//        accountProjectLinkageIsService(cityNo, function () {
//            var dbAccountProjectNo = $("#dbAccountProjectNo").val()
//            $("#accountProjectNo").val(dbAccountProjectNo);
//            form.render('select')
//        }, accountProjectNos);

        //20200701  根据项目找到收入类合同(LNK_Estate_Srlht)对应得核算-begin
        var projectNo = $("#projectNo").val();
        initAccountProject(projectNo, function () {
        	var dbAccountProjectNo = $("#dbAccountProjectNo").val()
          $("#accountProjectNo").val(dbAccountProjectNo);
            form.render('select')
        }, accountProjectNos);
        //end
        
        var fileRecordMainIds = "";
        $(".layui-upload .item_img").each(function () {
            fileRecordMainIds += $(this).data("id") + ',';
        })
        if (fileRecordMainIds != "") {
            fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length - 1);
        }
        $("#oldFileRecordMainIds").val(fileRecordMainIds);

        loadImageList("uploadSuccSaleImg", uploadSuccSaleImg, true);

    }

    form.render('select'); // 刷新单选框渲染

    var active = {
        save: function () {
            if (valid() == true) {
                var optionsData = {};
                optionsData.reportId = $("#id").val();
                optionsData.detailId = $("#detailId").val();
                optionsData.reportNo = $("#reportId").val();

                optionsData.reportIdHi = $("#reportId").val();
                optionsData.estateId = $("#estateId").val();
                optionsData.projectNo = $("#projectNo").val();

                optionsData.oldBuildingNoId = $("#buildingNoId").val();
                optionsData.buildingNoId = $("#buildingNoId").val();
                optionsData.oldBuildingNo = $("#buildingNo").val();
                optionsData.buildingNo = $("#buildingNo").val();
                optionsData.roughArea = $("#roughArea").val();
                optionsData.roughAmount = $("#roughAmount").val();
                optionsData.roughDate = $("#roughInputDate").val();

                optionsData.oldWYTypeCode = $("#oldWYTypeCode").val();
                optionsData.oldWYTypeName = $("#oldWYTypeName").val();
                optionsData.wyTypeCode = $("#wyTypeCode").val();
                optionsData.wyTypeName = $("#wyTypeName").val(); // 物业类型

                optionsData.oldRoughArea = NullToZero($("#oldRoughArea").val());
                optionsData.oldRoughAmount = NullToZero($("#oldRoughAmount").val());

                optionsData.oldBizOperDate = $("#oldBizOperDate").val();
                optionsData.bizOperDate = $("#dealDate").val();

                optionsData.customerNm = trimStr($("#customerNm").val());
                optionsData.customerTel = $("#customerTel").val();
                optionsData.customerNmTwo = trimStr($("#customerNmTwo").val());
                optionsData.customerTelTwo = $("#customerTelTwo").val();

                optionsData.oldCustomerNm = $("#oldCustomerNm").val();
                optionsData.oldCustomerTel = $("#oldCustomerTel").val();
                optionsData.customerId = $("#customerId").val();
                optionsData.oldCustomerNmTwo = $("#oldCustomerNmTwo").val();
                optionsData.oldCustomerTelTwo = $("#oldCustomerTelTwo").val();
                optionsData.customerIdTwo = $("#customerIdTwo").val();


                optionsData.oldArea = NullToZero($("#oldArea").val());
                optionsData.area = $("#dealArea").val();

                optionsData.oldDealAmount = NullToZero($("#oldDealAmount").val());
                optionsData.dealAmount = $("#dealAmount").val();

                optionsData.settleConfirmDateOld = $("#oldSettleConfirmDate").val();
                optionsData.settleConfirmDate = $("#settleConfirmDate").val();

                optionsData.reportDetailId = $("#reportDetailId").val();
                optionsData.accountProjectNo = $("#accountProjectNo").val();
                optionsData.oldFileRecordMainIds = $("#oldFileRecordMainIds").val();

                //获取上传图片的id
                var fileIds = '';
                $(".layui-upload .item_img").each(function () {
                    fileIds += $(this).data("id") + ',';
                });
                if (fileIds != '') {
                    fileIds = fileIds.substring(0, fileIds.length - 1);
                }
                optionsData.fileRecordMainIds = fileIds;

                submitToService(optionsData)
            }
        }
        , cancel: function () {

            parent.redirectTo('delete', null, null);
        }
        , goback: function () {
            parent.redirectTo('delete', null, null);
        }
    }

    $(' .layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

    function valid() {
        // 客户姓名
        var customerNm = $("#customerNm").val();
        if (isEmpty(customerNm)) {
            parent.msgAlert("请输入客户姓名！");
            return false;
        }
        // 客户手机号
        var customerTel = $("#customerTel").val();
        if (isEmpty(customerTel)) {
            parent.msgAlert("请输入客户手机号！")
            return false;
        }

        // 客户手机号2
        var customerNmTwo = $("#customerNmTwo").val();
        var customerTelTwo = $("#customerTelTwo").val();
        if (!isEmpty(customerNmTwo) && isEmpty(customerTelTwo)) {
            parent.msgAlert("客户已填写，客户手机必须填写！")
            return false;
        }
        if (isEmpty(customerNmTwo) && !isEmpty(customerTelTwo)) {
            parent.msgAlert("客户手机已填写，客户必须填写！")
            return false;
        }
        // 成销面积
        var area = $("#dealArea").val();
        if (isEmpty(area)) {
            parent.msgAlert("请输入成销面积！")
            return false;
        }
        if (!checkIsNumber(area)) {
            parent.msgAlert("成销面积不正确！")
            return false;
        }

        // 成销总价
        var dealAmount = $("#dealAmount").val();
        if (isEmpty(dealAmount)) {
            parent.msgAlert("请输入成销总价！")
            return false;
        }
        if (!checkIsNumber(dealAmount)) {
            parent.msgAlert("成销总价不正确！")
            return false;
        }

        // 成销日期
        var dealDate = $("#dealDate").val();
        if (isEmpty(dealDate)) {
            parent.msgAlert("请选择成销日期！");
            return false;
        }
        var roughDate = $("#roughDate").val();
        if (isEmpty(roughDate)) {
            roughDate = "1900-1-1";
        }
        // 关账日期 <= 成销日期 <= 当前日期
        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }
        if (!dateCompare(dealDate, switchDate)) {
            parent.msgAlert("成销日期不能在关账日期之前！");
            return false;
        }
        if (!dateCompare(nowDate, dealDate)) {
            parent.msgAlert("成销日期不能在当前日期之后！");
            return false;
        }
        if (!dateCompare(dealDate, roughDate)) {
            parent.msgAlert("成销日期不能在大定日期之前！");
            return false;
        }
        // 核算主体
        var accountProjectNo = $("#accountProjectNo").val();
        if (isEmpty(accountProjectNo)) {
            parent.msgAlert("请选择核算主体！");
            return false;
        }

        // 成销确认书/佣金结算资料
        var fileSize = $("#uploadSuccSaleImg .upload_img_list .item_img").size()
        if (fileSize == null || fileSize <= 0) {
            parent.msgAlert("请上传成销确认书/佣金结算资料！")
            return false;
        }

        var buildingNoId = $("#buildingNoId").val();
        var buildingNo = $("#buildingNo").val();
        var roughArea = $("#roughArea").val();
        var roughAmount = $("#roughAmount").val();
        var bizOperDate = $("#dealDate").val();
        var settleConfirmDate = $("#settleConfirmDate").val();
        var accountProjectNo = $("#accountProjectNo").val();

        //获取上传图片的id
        var fileIds = '';
        $(".layui-upload .item_img").each(function () {
            fileIds += $(this).data("id") + ',';
        });
        if (fileIds != '') {
            fileIds = fileIds.substring(0, fileIds.length - 1);
        }
        var fileRecordMainIds = fileIds;

        var oldBuildingNoId = $("#buildingNoId").val();
        var oldBuildingNo = $("#buildingNo").val();
        var oldRoughArea = NullToZero($("#oldRoughArea").val());
        var oldRoughAmount = NullToZero($("#oldRoughAmount").val());
        var oldCustomerNm = $("#oldCustomerNm").val();
        var oldCustomerTel = $("#oldCustomerTel").val();
        var oldCustomerNmTwo = $("#oldCustomerNmTwo").val();
        var oldCustomerTelTwo = $("#oldCustomerTelTwo").val();
        var oldArea = NullToZero($("#oldArea").val());
        var oldDealAmount = NullToZero($("#oldDealAmount").val());
        var oldBizOperDate = $("#oldBizOperDate").val();
        var settleConfirmDateOld = $("#oldSettleConfirmDate").val();
        var oldAccountProjectNo = $("#dbAccountProjectNo").val();
        var oldFileRecordMainIds = $("#oldFileRecordMainIds").val();


        if (oldBuildingNoId == buildingNoId && oldBuildingNo == buildingNo
            && oldRoughArea == roughArea && oldRoughAmount == roughAmount
            && oldCustomerNm == customerNm && oldCustomerTel == customerTel
            && oldCustomerNmTwo == customerNmTwo && oldCustomerTelTwo == customerTelTwo
            && oldArea == area && oldDealAmount == dealAmount
            && oldBizOperDate == bizOperDate && settleConfirmDateOld == settleConfirmDate
            && oldAccountProjectNo == accountProjectNo && oldFileRecordMainIds == fileRecordMainIds
        ) {
            parent.msgAlert("未做改动！");
            return false;
        }


        return true;
    }


    function submitToService(optionsData) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/updateSuccSale";
        restPost(url, optionsData,
            function (data) {
                parent.layer.close(loadIndex);
                if (data.returnData == 0 || data.returnData == 201 || data.returnData == 202) {
                    parent.msgAlert(data.returnMsg);
                } else {
                    parent.msgProp("操作成功！");
                    parent.redirectTo('delete', null, null);
                }
            },
            function (data) {
                parent.layer.close(loadIndex);
                parent.msgAlert(data.returnMsg);
            })
    }

    // 成销确认书/佣金结算资料
    uploadRender('uploadSuccSaleImg', {fileTypeId: '1025',fileSourceId: '5',refId: $("#id").val(),exts: 'jpg|png|jpeg|pdf'});
});

/**
 * desc：获取项目对应收入类合同得核算主体
 * @param projectNo
 * @param callback
 * @param ids
 * @returns
 */
function initAccountProject(projectNo,callback, ids){

    var url = BASE_PATH + "/sceneTrade/getAccountProjectByProjectNo/" + projectNo;

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
