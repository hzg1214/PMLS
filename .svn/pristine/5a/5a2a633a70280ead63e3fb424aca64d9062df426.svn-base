var layer;
var form,upload;
var infoJsonDto;
var addCol;
layui.use(['element', 'laydate', 'form', 'table', 'layer','upload','tree'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        formSelects = layui.formSelects,
        $ = layui.jquery;

        layer = layui.layer;
        form = layui.form;
        upload = layui.upload;

    laydate.render({
        elem: '#dealDate',
        type: 'date',
        format: 'yyyy-MM-dd'
        , max: $("#countDateEndStr").val(),
        trigger: 'click'
    });
    form.render();

    //infoJsonDto=eval('(' + window.qtReportInfoJson + ')');
    infoJsonDto=eval('(' + window.qtReportInfoJson.replace(/[\r\n]/g,"\\n") + ')');
    achievementCity("accCitySelect",infoJsonDto.accCityNo,function () {
        form.render();
        achievementCenter("centerSelect",infoJsonDto.accCityNo,infoJsonDto.centerId,function () {
            form.render();
        });
        achievementCenter("srCenterId",infoJsonDto.accCityNo,infoJsonDto.srCenterId,function () {
            form.render();
        });
    });

    uploadRender('fileSuccSalelist',{fileTypeId:'1025',fileSourceId:'20',exts:'jpg|png|jpeg|pdf'});
    uploadRender('fileSuccSalelistQrh',{fileTypeId:'1064',fileSourceId:'20',exts:'jpg|png|jpeg|pdf'});

    table.render({
        elem: '#contentTable'
        , cols: setCols()
        , id: 'contentReload'
        ,loading:false
        , method: 'post'
        , request: {
            pageName: 'curPage' //页码的参数名称，默认：page
            , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
        }
        , response: {
            statusName: 'returnCode' //数据状态的字段名称，默认：code
            , statusCode: 200 //成功的状态码，默认：0
            , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
            , countName: 'totalCount' //数据总数的字段名称，默认：count
            , dataName: 'returnData' //数据列表的字段名称，默认：data
        }
    });

    function setCols() {
        var row1 = [];
        row1.push(
            {field:'companyNoSelect',title: '返佣对象',width:500, align: 'center'},
            {field:'yjfybefTaxAmountLay',title: '应计返佣税前（元）',width:200, align: 'right'},
            {field:'yjfyaftTaxAmountLay',title: '应计返佣税后（元）',width:200, align: 'right'},
            {
                title: '操作', width: 100, align: 'center', templet: function (d) {
                    var ret = "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='removeTr(this)'>删除</a>";
                    return ret;
                }
            }
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }


    var active = {
/*        btnAddFydx: function () {
            addCol();
        }*/
    }


    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });




    //城市选择事件
    form.on('select(accCitySelect)', function (data) {
        if(data.value!=null && data.value!=''){
            achievementCenter("centerSelect",data.value,null,function () {
                form.render();
            });
            achievementCenter("srCenterId",data.value,null,function () {
                form.render();
            });
        }
    });


    
});


function showSelect(obj) {
    parent.layer.open({
        type: 2,
        title: '选择公司',
        area: ['800px', '660px'],
        content: '/pmlsFrameContract/selectBusinessPage'
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {

            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                $(obj).html();
                var $trHtml = $(obj).parent();
                $trHtml.find('input[name="companyNo"]').val(formData.companyNo);
                $trHtml.find('input[name="companyName"]').val(formData.companyName);
                $trHtml.find('span[name="companyShowName"]').html(formData.companyName);

        /*        var w_companyId = $("#companyId").attr("data-companyId");

                $("#companyId").attr("data-branchId", formData.topbranchId);
                $("#companyId").attr("data-companyId", formData.id);
                $("#companyId").attr("data-companyNo", formData.companyNo);
                $("#companyId").val(formData.companyName);*/

                parent.layer.close(index);
            }
        }
    });
}

function removeTr(obj) {
    var currTr = $(obj).parents('tr');
    currTr.remove();
}


function successSale() {
    parent.layer.load(2);



    var id = $("#id").val();
    var srType = $("#srType").val();
    var dealAmount = $("#dealAmount").val();
    var dealDate = $("#dealDate").val();
    var befYJSRAmount = $("#befYJSRAmount").val();
    var aftYJSRAmount = $("#aftYJSRAmount").val();
    var befYJFYAmount = $("#befYJFYAmount").val();
    var aftYJFYAmount = $("#aftYJFYAmount").val();
    var accCityNo = $("#accCitySelect").find("option:selected").val();
    var centerId = $("#centerSelect").find("option:selected").val();
    var centerName = $("#centerSelect").find("option:selected").text();
    var srCenterId = $("#srCenterId").find("option:selected").val();
    var srCenterName = $("#srCenterId").find("option:selected").text();
    var accountProjectNo = $("#accountProjectNo").find("option:selected").val();
    var accountProject = "";

    var aPTxt = $("#accountProjectNo").find("option:selected").text();
    var txtArr = aPTxt.split("_");
    if (txtArr && txtArr.length > 1) {
       accountProject = txtArr[1];
        // $("#accountProject").val(accountProject);
    }




    var optionsData = {};
    optionsData.id                          =  id;
    optionsData.accountProjectNo 			=  accountProjectNo;
    optionsData.accountProject              =  accountProject;
    optionsData.fileTypeId                  =  1025;
    optionsData.befYJSRAmount               =  befYJSRAmount;
    optionsData.aftYJSRAmount               =  aftYJSRAmount;
    optionsData.befYJFYAmount               =  befYJFYAmount;
    optionsData.aftYJFYAmount               =  aftYJFYAmount;
    optionsData.srType                      =  srType;
    optionsData.dealDate                    =  dealDate;
    optionsData.limitSize                   =  10;
    optionsData.dealAmount                  =  dealAmount;
    optionsData.accCityNo                   =  accCityNo;
    optionsData.centerId                    =  centerId;
    optionsData.centerName                  =  centerName;
    optionsData.srCenterId                  =  srCenterId;
    optionsData.srCenterName                =  srCenterName;
    optionsData.fileSourceId                =  20;

    //optionsData.companyName 				=;
    //optionsData.yjfyaftTaxAmount          =;
    //optionsData.companyNo                 =;
    //optionsData.contractChangePicId       =;
    //optionsData.fileRecordMainIdHidden    =;
    //optionsData.oaFileId                  =;
    //optionsData.yjfybefTaxAmount          =;
    //optionsData.companyId                 =;
    //optionsData.fileNoHidden              =;

    if (isNullEmpty(srType)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择收入类型！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(dealAmount)) {
        parent.layer.closeAll();
        parent.layer.alert('请输入成销金额！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(dealDate)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择成销日期！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(befYJSRAmount)) {
        parent.layer.closeAll();
        parent.layer.alert('请输入应计收入税前！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(aftYJSRAmount)) {
        parent.layer.closeAll();
        parent.layer.alert('请输入应计收入税后！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(befYJFYAmount)) {
        parent.layer.closeAll();
        parent.layer.alert('请输入应计返佣税前！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(aftYJFYAmount)) {
        parent.layer.closeAll();
        parent.layer.alert('请输入应计返佣税后！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(accCityNo)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择业绩归属城市！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(centerId)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择业绩归属中心！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(srCenterId)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择收入中心！',{icon: 2, title:'提示'});
        return false;
    }
    if (isNullEmpty(accountProjectNo)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择核算主体！',{icon: 2, title:'提示'});
        return false;
    }

    var fydxAry = [];
    var checkFlag = true;
    $("div[lay-id='contentReload'] .layui-table-main tbody").find("tr[tag='fydxTr']").each(function (i) {
        var companyNo = $(this).find("input[name='companyNo']").val();
        var companyName = $(this).find("input[name='companyName']").val();
        var yjfybefTaxAmount = $(this).find("input[name='yjfybefTaxAmount']").val();
        var yjfyaftTaxAmount = $(this).find("input[name='yjfyaftTaxAmount']").val();
        if(isNullEmpty(companyNo)|| isNullEmpty(yjfybefTaxAmount) || isNullEmpty(yjfyaftTaxAmount)){
            parent.layer.closeAll();
            parent.layer.alert('第'+(i+1)+"行返佣对象维护存在空值，请填写完毕",{icon: 2, title:'提示'});
            checkFlag = false;
            return false;
        }
        fydxAry.push(companyName);
    });
    if(!checkFlag){
        return false;
    }

    var repeatCompanyName = isRepeatAry(fydxAry);
    if(!isNullEmpty(repeatCompanyName)){
        parent.layer.closeAll();
        parent.layer.alert('返佣对象'+repeatCompanyName+"存在重复，请重新选择",{icon: 2, title:'提示'});
        return false;
    }

    var trSize = $("div[lay-id='contentReload'] .layui-table-main tbody").find("tr[tag='fydxTr']").length;

    if (befYJFYAmount > 0 || aftYJFYAmount > 0) {
        //检查应计返佣不为0 返佣对象必须维护
        if (trSize<=0) {
            parent.layer.closeAll();
            parent.layer.alert('应计返佣不为0，请维护返佣对象！',{icon: 2, title:'提示'});
            return false;
        }

        //检查应计返佣税前/应计返佣税后与维护返佣对象的应计返佣税前总和/应计返佣税后总和一致
        var yjfybefTaxAmount = 0;
        $("input[name='yjfybefTaxAmount']").each(function () {
            var bef = $(this).val();
            yjfybefTaxAmount = yjfybefTaxAmount + parseFloat(bef);
        });

        if (parseFloat(befYJFYAmount).toFixed(2) != parseFloat(yjfybefTaxAmount).toFixed(2)) {
            parent.layer.closeAll();
            parent.layer.alert('应计返佣税前金额与维护返佣对象的应计返佣税前总和不一致！',{icon: 2, title:'提示'});
            return false;
        }

        var yjfyaftTaxAmount = 0;
        $("input[name='yjfyaftTaxAmount']").each(function () {
            var aft = $(this).val();
            yjfyaftTaxAmount = yjfyaftTaxAmount + parseFloat(aft);
        });
        if (parseFloat(aftYJFYAmount).toFixed(2) != parseFloat(yjfyaftTaxAmount).toFixed(2)) {
            parent.layer.closeAll();
            parent.layer.alert('应计返佣税后金额与维护返佣对象的应计返佣税后总和不一致！',{icon: 2, title:'提示'});
            return false;
        }

        //当应计返佣税前金额大于0时，返佣确认函附件必须上传
        var fileSize = $("#upload_img_SuccSaleQrh .item_img").size();
        if (fileSize == null || fileSize <= 0) {
            parent.layer.closeAll();
            parent.layer.alert('请上传返佣确认函！',{icon: 2, title:'提示'});
            return false;
        }
    }



    var fileSizeCx = $("#upload_img_SuccSalelist .item_img").size();
    if (fileSizeCx == null || fileSizeCx <= 0) {
        parent.layer.closeAll();
        parent.layer.alert('请上传成销确认书/佣金结算资料！',{icon: 2, title:'提示'});
        return false;
    }

    //获取上传图片的id
    var fileIds = '';
    $(".layui-upload .item_img").each(function () {
        fileIds += $(this).data("id") + ',';
    });
    if (fileIds != '') {
        fileIds = fileIds.substring(0, fileIds.length - 1);
    }


    var fyList = [];
    var tbody = $("div[lay-id='contentReload'] .layui-table-main tbody");
    $(tbody).find("tr[tag='fydxTr']").each(function () {
        var companyNo = $(this).find("input[name='companyNo']").val();
        var companyName = $(this).find("input[name='companyName']").val();
        var yjfybefTaxAmount = $(this).find("input[name='yjfybefTaxAmount']").val();
        var yjfyaftTaxAmount = $(this).find("input[name='yjfyaftTaxAmount']").val();
        var obj = {};
        obj.companyNo=companyNo,
        obj.companyName=companyName,
        obj.yjfybefTaxAmount=yjfybefTaxAmount,
        obj.yjfyaftTaxAmount=yjfyaftTaxAmount

        fyList.push(obj);
    });
    optionsData.fileRecordMainIds           =fileIds;
    optionsData.fyList                      = JSON.stringify(fyList);

    var url = BASE_PATH + '/pmlsQtSuccessSale/successSale';
    $.ajax({
        type: "POST",
        url: url,
        data: optionsData,
        dataType: "json",
        success: function (data) {
            parent.layer.closeAll();
            if (data.returnCode == 200 || data.returnCode == '200') {
                back();
            } else {
                parent.layer.alert(data.returnMsg,{icon: 2, title:'提示'});
            }
        },
        error: function () {
            parent.layer.closeAll();
            parent.layer.alert('成销失败');
        }
    });

}


function addCol(){
    var befYJFYAmount = $("#befYJFYAmount").val();
    if (befYJFYAmount == null || befYJFYAmount == "" || befYJFYAmount == undefined || befYJFYAmount == 0) {
        parent.layer.alert('应计返佣为0，不允许维护返佣对象!',{icon: 2, title:'提示'});
        return false;
    }

    var trSize = $("div[lay-id='contentReload'] .layui-table-main tbody").find("tr[tag='fydxTr']").length;
    if (trSize>19) {
        parent.layer.alert('最多允许添加20个返佣对象!',{icon: 2, title:'提示'});
        return false;
    }


    var contentTrStr = '';
    contentTrStr += '<tr tag="fydxTr">'

    contentTrStr += '<td data-field="companyNoSelect" align="left" data-content="">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-0">'
    contentTrStr += '<input type="hidden" name="companyNo" value=""/><input type="hidden" name="companyName" value=""/>';
    contentTrStr += '<span name="companyShowName"></span><a class="layui-btn layui-btn-xs" style="float: right;" onclick="showSelect(this)">选择</a>';
    contentTrStr += '</div></td>';


    contentTrStr += '<td data-field="yjfybefTaxAmount" align="center" data-content="">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-1">'
    contentTrStr += '<input type="text" name="yjfybefTaxAmount" value="" style="width: 90%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '</div></td>';

    contentTrStr += '<td data-field="yjfyaftTaxAmount" align="center" data-content="">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-2">'
    contentTrStr += '<input type="text" name="yjfyaftTaxAmount" value="" style="width: 90%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '</div></td>';


    contentTrStr += '<td>';
    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-3">'
    contentTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="removeTr(this)">删除</a>';
    contentTrStr += '</div></td>';




    contentTrStr += '</tr>';
    $("div[lay-id='contentReload'] .layui-table-main tbody").append(contentTrStr);
}

//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}


function isNullEmpty(obj){
    if(obj == null || obj == "" || obj == undefined){
        return true;
    }else{
        return false;
    }
}

