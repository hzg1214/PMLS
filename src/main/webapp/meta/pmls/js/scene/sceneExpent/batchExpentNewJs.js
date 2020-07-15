var $;
var cashBillInfoJson = {};
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects;
        $ = layui.$;


    init();
    function init() {
        // 付款方式
        dictionaryLinkageIsServiceSync("payType", 223, function () {
            form.render('select');
        });

        var recordTimeInit = $("#recordTimeInit").val();
        var nowDate = $("#nowDate").val();
        laydate.render({
            elem: '#recordTime',
            type: 'date',
            format: 'yyyy-MM-dd',
            value:recordTimeInit,
            trigger: 'click'
        });

        laydate.render({
            elem: '#predictPayTime',
            type: 'date',
            min: nowDate,
            format: 'yyyy-MM-dd',
            trigger: 'click'
        });

        form.render("radio");
        form.render('select'); // 刷新单选框渲染
        form.render();

        var isEdit = $("#isEdit").val();
        if(isEdit=='1') {
            console.log("cashBillInfoJsonDto=" + window.cashBillInfoJsonDto);
            cashBillInfoJson = eval('(' + window.cashBillInfoJsonDto.replace(/[\r\n]/g, "\\n") + ')');
            $("#payType").val(cashBillInfoJson.payType);
            tableRender(cashBillInfoJson.reportDetails);


            var fileList1 = [], fileList2 = [], fileList3 = [];
            if (cashBillInfoJson != null && cashBillInfoJson.fileList != null && cashBillInfoJson.fileList.length > 0) {
                for (var i = 0; i < cashBillInfoJson.fileList.length; i++) {
                    if (cashBillInfoJson.fileList[i].fileTypeId == '1032') {
                        fileList1.push(cashBillInfoJson.fileList[i]);
                    } else if (cashBillInfoJson.fileList[i].fileTypeId == '1089') {
                        fileList2.push(cashBillInfoJson.fileList[i]);
                    } else if (cashBillInfoJson.fileList[i].fileTypeId == '1090') {
                        fileList3.push(cashBillInfoJson.fileList[i]);
                    }
                }
            }
        }else {
            $("#payType").val(22303);
            tableRender([]);
        }
        //初始化加载图片
        loadImageList('uploadImg',fileList1,true);
        loadImageList('uploadFpImg',fileList2,true);
        loadImageList('uploadOtherImg',fileList3,true);


        uploadRender('uploadImg', {fileTypeId: '1032', fileSourceId: '9', exts: 'jpg|png|jpeg|pdf'});
        uploadRender('uploadFpImg',{fileTypeId:'1089',fileSourceId:'9',exts:'jpg|png|jpeg|pdf'});
        uploadRender('uploadOtherImg',{fileTypeId:'1090',fileSourceId:'9',exts:'jpg|png|jpeg|pdf'});


        form.render('select'); // 刷新单选框渲染
        form.render();
    }





    function tableRender(data) {
        table.render({
            elem: '#normalReport'
            , cols: setNormalCols()
            , id: 'normalBlock'
            , height: 'full'
            , limit: 1000
            , data: data
            , page: false
            , done: function (res, curr, count) {
                var fixTrStr = '';
                fixTrStr += '<tr tag="sum">'
                fixTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-0 "></div></td>';
                fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-1">合计：</div></td>';
                fixTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-2"></div></td>';
                fixTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-3"></div></td>';
                fixTrStr += '</tr>';

                var contentTrStr = '';
                contentTrStr += '<tr tag="sum">'
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-0"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-1"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-2"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-3"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-4">';
                contentTrStr += '    <input type="hidden" name="areaSum">';
                contentTrStr += '    <input type="hidden" name="dealAmountSum">';
                contentTrStr += '    <input type="hidden" name="requestAmountSum">';
                contentTrStr += '    <input type="hidden" name="taxAmountSum">';
                contentTrStr += '</div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-5"></div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-6"><span name="area"></span></div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-7"><span name="dealAmount"></span></div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-8"><span name="requestAmount"></span></div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-9"><span name="taxAmount"></span></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-10"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-11"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-12"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-13"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-14"></div></td>';

                var rightTrStr = '';
                rightTrStr += '<tr tag="sum">'
                rightTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-14"><span></span></div></td>';
                rightTrStr += '</tr>';


                var leftTable = $("div[lay-id='normalBlock'] .layui-table-fixed-l ");
                var mainTable = $("div[lay-id='normalBlock'] .layui-table-main ");
                var rightTable = $("div[lay-id='normalBlock'] .layui-table-fixed-r ");

                $(leftTable).find(".layui-table-body tbody").append(fixTrStr);
                $(mainTable).find("tbody").append(contentTrStr);
                $(rightTable).find(".layui-table-body tbody").append(rightTrStr);

                $(leftTable).removeClass('layui-hide');
                $(mainTable).find('div[class="layui-none"]').empty().remove();
                $(rightTable).removeClass('layui-hide')

                batchExpentNewJs.evalNormalTotal();
            }
        });
    }

    function setNormalCols() {
        var row = [
            {type: 'numbers', fixed: true, title: '序号', width: 50},
            {field: 'reportNo', fixed: true, title: '订单编号', width: 160, align: 'center'
                , templet: function (d) {
                    return d.reportNo;
                }
            },

            {field: 'customerNm', fixed: true, title: '客户姓名', width: 130, align: 'center'
                , templet: function (d) {
                    return d.customerNm;
                }
            },
            {field: 'buildingNo', fixed: true, title: '楼室号', width: 139, align: 'center'},
            {field: 'isGlobalControl', title: '垫佣控制规则', width: 115, align: 'center'
                , templet: function (d) {
                    var reportId	 		=        NullToEmpty(d.reportId);
                    var reportNo 			=        NullToEmpty(d.reportNo);
                    var buildingNo 			=        NullToEmpty(d.buildingNo);
                    var customerNm 			=        NullToEmpty(d.customerNm);
                    var area 				=        NullToZero(d.area);
                    var dealAmount			=        NullToZero(d.dealAmount);
                    var requestAmount  		=        NullToZero(d.requestAmount);
                    //var taxAmount      		=        NullToZero(d.kpTaxAmount);
                    var requestType     	=        NullToZero(d.requestType);
                    var reportDetailId  	=        NullToZero(d.reportDetailId);

                    var offSetFlag          =        0;//新增选择的PMLS_JsStatement_Dtl.offSetFlag是int类型  和新增保持一致
                    var offSetFlagBooLean   =        d.offSetFlag;

                    if(offSetFlagBooLean){
                        offSetFlag = 1;
                    }
                    var offSetFlag     		=        NullToZero(offSetFlag);
                    var accountProject 		=        NullToEmpty(d.accountProject);
                    var accountProjectNo 	=        NullToEmpty(d.accountProjectNo);
                    var pjsdId       		=        NullToEmpty(d.pjsdId);
                    var isGlobalControl 	=        NullToEmpty(d.isGlobalControl);
                    var isGlobalControlStr  =        isGlobalControl == 1 ? "项目总控" : "房源单控";
                    var jssId  				=        NullToEmpty(d.jssId);

                    var contentTrStr = '';
                    contentTrStr += '<input type="hidden" name="reportId" 			value="' +  reportId	 	 + '">';
                    contentTrStr += '<input type="hidden" name="reportNo" 			value="' +  reportNo 		 + '">';
                    contentTrStr += '<input type="hidden" name="buildingNo" 		value="' +  buildingNo 		 + '">';
                    contentTrStr += '<input type="hidden" name="customerNm" 		value="' +  customerNm 		 + '">';
                    contentTrStr += '<input type="hidden" name="area" 				value="' +  area 			 + '">';
                    contentTrStr += '<input type="hidden" name="dealAmount" 		value="' +  dealAmount		 + '">';
                    contentTrStr += '<input type="hidden" name="requestAmount" 		value="' +  requestAmount  	 + '">';
                    contentTrStr += '<input type="hidden" name="requestType" 		value="' +  requestType      + '">';
                    contentTrStr += '<input type="hidden" name="reportDetailId" 	value="' +  reportDetailId   + '">';
                    contentTrStr += '<input type="hidden" name="offSetFlag" 		value="' +  offSetFlag     	 + '">';
                    contentTrStr += '<input type="hidden" name="accountProject" 	value="' +  accountProject 	 + '">';
                    contentTrStr += '<input type="hidden" name="accountProjectNo" 	value="' +  accountProjectNo + '">';
                    contentTrStr += '<input type="hidden" name="pjsdId" 			value="' +  pjsdId       	 + '">';
                    contentTrStr += '<input type="hidden" name="isGlobalControl" 	value="' +  isGlobalControl  + '">';
                    contentTrStr += '<input type="hidden" name="jssId" 			  	value="' +  jssId  			 + '">';

                    contentTrStr += '<span name="isGlobalControl">'+ isGlobalControlStr+'</span>';
                    return contentTrStr;
                }
            },
            {field: 'projectName', title: '项目', width: 130, align: 'center'},
            {
                field: 'area', title: '面积（㎡）', width: 100, align: 'center', style: 'text-align:right'
                , templet: function (d) {
                    return formatMoney(d.area);
                }
            },
            {
                field: 'dealAmount', title: '签约总价（元）', width: 130, align: 'center', style: 'text-align:right'
                , templet: function (d) {
                    return formatMoney(d.dealAmount);
                }
            },
            {
                field: 'requestAmount', title: '含税请款金额（元）', width: 150, align: 'center', style: 'text-align:right'
                , templet: function (d) {
                    return formatMoney(d.requestAmount);
                }
            },
            {
                field: 'taxAmount', title: '税额（元）', width: 130, align: 'center', style: 'text-align:right'
                , templet: function (d) {
                    var taxAmount = d.taxAmount==null ? "" : formatAccount2(d.taxAmount);
                    var contentTrStr = '<input type="text" name="taxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchExpentNewJs.evalNormalItem(\'taxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:batchExpentNewJs.formatter(this,0)" class="renderInput mr" value="' + taxAmount + '">';
                    return contentTrStr;
                }
            },
            {
                field: 'requestType', title: '请款类别', width: 90, align: 'center',
                templet: function (d) {
                    var requestTypeStr = "-";
                    if (isEmpty(d.requestType) || d.requestType == 0) {
                        requestTypeStr = "-";
                    } else if (d.requestType == 1) {
                        requestTypeStr = "返佣";
                    } else if (d.requestType == 2) {
                        requestTypeStr = "垫佣";
                    }
                    var contentStr = '';
                    contentStr += '<span name="requestType">'+ requestTypeStr+'</span>';
                    return contentStr;
                }
            },
            {field: 'accountProject', title: '核算主体', width: 160, align: 'center'
                , templet: function (d) {
                    return d.accountProject;
                }
            },
            {field: 'checkBodyName', title: '考核主体', width: 180, align: 'center'
                , templet: function (d) {
                    var checkBodyId 		=        NullToEmpty(d.checkBodyId);
                    var checkBodyName 	    =        NullToEmpty(d.checkBodyName);
                    var contentTrStr = '';
                    contentTrStr += '<input type="hidden" name="checkBodyId" value="'+checkBodyId+'"/><input type="hidden" name="checkBodyName" value="'+checkBodyName+'"/>';
                    contentTrStr += '<span name="checkBodyNameShow">'+checkBodyName+'</span><a class="layui-btn layui-btn-xs"  style="float: right;margin-top: 3px;"  onclick="javascript:batchExpentNewJs.selectCheckBody(this)">选择</a>';
                    return contentTrStr;
                }
            },
            {field: 'memo', title: '说明', width: 200, align: 'center'
                ,   templet:function (d) {
                    var contentTrStr = '   <input type="text" name="memo" maxlength="200" autocomplete="off"  class="renderInput ml" value="'+ d.memo +'">';
                    return contentTrStr;
                }
            },
            {
                title: '操作', fixed: 'right', align: 'center', width: 80,
                templet: function (d) {
                    return "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:batchExpentNewJs.delete(\"normalBlock\",this)'>删除</a>";
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }





    var active = {
        submit: function () {
            toSave(1)
        }
        , save: function () {
            toSave(0)
        }
        ,openDialogGetJssDtl: function () {
            batchExpentNewJs.openDialogGetJssDtl();
        }
        , cancel: function () {
            parent.redirectTo('delete', null, null);
        }
        , goback: function () {
            parent.redirectTo('delete', null, null);
        }
    }

    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });


    function toSave(type) {
        // 检验内容
        if (vaild(type)) {
            saveOrSubmit(type);
        }

    }

    function vaild(type) {//校验参数
        var jssNo = $("#jssNo").val();
        if (isEmpty(jssNo)) {
            parent.msgAlert("请选择结算书！");
            return false;
        }
        // 提交的场合
        if (type == 1) {
            var companyName = $("#companyName").val();
            if (isEmpty(companyName)) {
                parent.msgAlert("渠道公司不能为空！");
                return false;
            }

            var estateNm = $("#estateNm").val();
            if (isEmpty(estateNm)) {
                parent.msgAlert("项目不能为空！");
                return false;
            }

            var receiveBankName = $("#receiveBankName").val();
            if (isEmpty(receiveBankName)) {
                parent.msgAlert("请选择收款银行!");
                return false;
            }

            var receiveBankAccountCardCode = $("#receiveBankAccountCardCode").val();
            if (isEmpty(receiveBankAccountCardCode)) {
                parent.msgAlert("银行账户不能为空！");
                return false;
            }

            var receiveBankAccountName = $("#receiveBankAccountName").val();
            if (isEmpty(receiveBankAccountName)) {
                parent.msgAlert("收款户名不能为空！");
                return false;
            }


            var receiveBankProvinceCityName = $("#receiveBankProvinceCityName").val();
            if (isEmpty(receiveBankProvinceCityName)) {
                parent.msgAlert("收款省市不能为空！");
                return false;
            }

            // 入账日期
            var recordTime = $("#recordTime").val();
            if (isEmpty(recordTime)) {
                parent.msgAlert("请选择入账日期！");
                return false;
            }

            // 预计付款日期
            var predictPayTime = $("#predictPayTime").val();
            if (isEmpty(predictPayTime)) {
                parent.msgAlert("请选择预计付款日期！");
                return false;
            }
            // 付款方式
            var payType = $("#payType").val();
            if (isEmpty(payType)) {
                parent.msgAlert("请选择付款方式！");
                return false;
            }

            var checkFlag = true;
            var normalBlock = $("div[lay-id='normalBlock'] .layui-table-main tbody");
            $(normalBlock).find("tr[tag!='sum']").each(function (i, tr) {
                var requestAmount  		=		$(tr).find("input[name='requestAmount']").val();
                var taxAmount      		=		$(tr).find("input[name='taxAmount']").val();

                if (taxAmount==undefined || taxAmount==null || taxAmount=='') {
                    parent.msgError("第" + (i+1) + "行税额没有输入 请输入");
                    checkFlag = false;
                    return false;
                }


                var taxrate = taxAmount / requestAmount;
                var taxFlag = false;
                if (
                    (taxrate >= 0.166 && taxrate <= 0.175) ||
                    (taxrate >= 0.156 && taxrate <= 0.165) ||
                    (taxrate >= 0.126 && taxrate <= 0.135) ||
                    (taxrate >= 0.106 && taxrate <= 0.115) ||
                    (taxrate >= 0.096 && taxrate <= 0.105) ||
                    (taxrate >= 0.056 && taxrate <= 0.065) ||
                    (taxrate >= 0.046 && taxrate <= 0.055) ||
                    (taxrate >= 0.026 && taxrate <= 0.035) ||
                    (taxrate >= 0.012 && taxrate <= 0.017) ||
                    (taxrate >= 0.008 && taxrate <= 0.012) ||
                    taxrate == 0) {
                    taxFlag = true;

                }
                if (!taxFlag) {
                    parent.msgError("第" + (i+1) + "行税率没有落在税率区间内 请修改");
                    checkFlag = false;
                    return false;
                }



                var checkBodyId      	=		$(tr).find("input[name='checkBodyId']").val();
                var checkBodyName      	=		$(tr).find("input[name='checkBodyName']").val();
                if(isEmpty(checkBodyId)||isEmpty(checkBodyName)){
                    parent.msgError("第" + (i+1) + "行考核主体为空 请选择");
                    checkFlag = false;
                    return false;
                }

            });

            var trLength = $(normalBlock).find("tr[tag!='sum']").length;
            if(trLength<1){
                parent.msgAlert("请至少选择一条请款订单！");
                return false;
            }
            if(!checkFlag){
                return false;
            }


            // var fileSize = $("#uploadImg .item_img").size();
            // if (fileSize == null || fileSize <= 0) {
            //     parent.msgAlert("请上传[成销确认书/佣金结算资料]！");
            //     return false;
            // }

            var fileSize2 = $("#uploadFpImg .item_img").size();
            if (fileSize2 == null || fileSize2 <= 0) {
                parent.msgAlert("请上传[发票]！");
                return false;
            }
        }

        var remarks = $("#remarks").val();
        var remarksLen = remarks.length;
        if (!isEmpty(remarksLen) && remarksLen > 200) {
            parent.msgAlert("请款备注已超200字，请重新填写！")
            return false;
        }
        return true;
    }

    function saveOrSubmit(type) {//保存提交方法
        var loadIndex = parent.layer.load(2, {shade: [0.1]});

        var areaTotal = $("div[lay-id='normalBlock']  .layui-table-main tbody").find("input[name='areaSum']").val();
        var dealAmountTotal = $("div[lay-id='normalBlock']  .layui-table-main tbody").find("input[name='dealAmountSum']").val();
        var requestAmountTotal = $("#reportCard").find("input[name='requestAmountTotal']").val();
        var taxAmountTotal = $("#reportCard").find("input[name='taxAmountTotal']").val();
        var noTaxAmountTotal = $("#reportCard").find("input[name='noTaxAmountTotal']").val();




        var cashBillCompany = {};
        cashBillCompany.proParentId                 = $("#proParentId").val();
        cashBillCompany.id                          = $("#comParentId").val();
        cashBillCompany.pjsNostr                    = $("#jssNo").val();
        cashBillCompany.receiveBankName             = $("#receiveBankName").val();
        cashBillCompany.receiveBankAccountCardCode  = $("#receiveBankAccountCardCode").val();
        cashBillCompany.receiveBankAccountName      = $("#receiveBankAccountName").val();
        cashBillCompany.receiveBankProvinceName     = $("#receiveBankProvinceName").val();
        cashBillCompany.receiveBankCityName         = $("#receiveBankCityName").val();
        cashBillCompany.receiveBankSerialNo         = $("#receiveBankSerialNo").val();
        cashBillCompany.recordDate                  = $("#recordTime").val();
        cashBillCompany.predictPayTime              = $("#predictPayTime").val();
        cashBillCompany.payType                     = $("#payType").val();
        cashBillCompany.remarks                     = $("#remarks").val();

        cashBillCompany.areaTotal                   = areaTotal;
        cashBillCompany.dealAmountTotal             = dealAmountTotal;
        cashBillCompany.requestAmountTotal          = requestAmountTotal;
        cashBillCompany.taxAmountTotal              = taxAmountTotal;
        cashBillCompany.amountTotal                 = requestAmountTotal;
        cashBillCompany.amountTax                   = taxAmountTotal;
        cashBillCompany.amountNoTax                 = noTaxAmountTotal;

        cashBillCompany.submitOaStatus              = type;
        cashBillCompany.offSetFlag     		        = false;

        //获取上传图片的id
        var fileIds='';
        $("#uploadImg .item_img").each(function () {
            fileIds+=$(this).data("id")+',';
        });
        $("#uploadFpImg .item_img").each(function () {
            fileIds+=$(this).data("id")+',';
        });
        $("#uploadOtherImg .item_img").each(function () {
            fileIds+=$(this).data("id")+',';
        });
        cashBillCompany.fileRecordMainIds = fileIds;


        var reportList = [];
        var normalBlock = $("div[lay-id='normalBlock'] .layui-table-main tbody");
        $(normalBlock).find("tr[tag!='sum']").each(function (i, tr) {
            var cashBillReport = {};
            cashBillReport.reportId	 			=		$(tr).find("input[name='reportId']").val();
            cashBillReport.reportNo 			=		$(tr).find("input[name='reportNo']").val();
            cashBillReport.buildingNo 			=		$(tr).find("input[name='buildingNo']").val();
            cashBillReport.customerNm 			=		$(tr).find("input[name='customerNm']").val();
            cashBillReport.area 				=		$(tr).find("input[name='area']").val();
            cashBillReport.dealAmount			=		$(tr).find("input[name='dealAmount']").val();
            cashBillReport.requestAmount  		=		$(tr).find("input[name='requestAmount']").val();
            cashBillReport.taxAmount      		=		$(tr).find("input[name='taxAmount']").val();
            cashBillReport.requestType     	    =		$(tr).find("input[name='requestType']").val();
            cashBillReport.reportDetailId  	    =		$(tr).find("input[name='reportDetailId']").val();
            cashBillReport.offSetFlag     		=	    false;//  0   true
            if($(tr).find("input[name='offSetFlag']").val()==1){
                cashBillReport.offSetFlag       =       true;
                cashBillCompany.offSetFlag      =	    true;
            }
            cashBillReport.accountProject 		=		$(tr).find("input[name='accountProject']").val();
            cashBillReport.accountProjectNo 	=		$(tr).find("input[name='accountProjectNo']").val();
            cashBillReport.pjsdId       		=		$(tr).find("input[name='pjsdId']").val();
            cashBillReport.isGlobalControl 	    =		$(tr).find("input[name='isGlobalControl']").val();
            cashBillReport.jssId  				=		$(tr).find("input[name='jssId']").val();
            cashBillReport.memo            	    =		$(tr).find("input[name='memo']").val();
            cashBillReport.checkBodyId     	    =		$(tr).find("input[name='checkBodyId']").val();
            cashBillReport.checkBodyName  		=		$(tr).find("input[name='checkBodyName']").val();
            reportList.push(cashBillReport)
        });
        cashBillCompany.reportList = reportList;



        var url = BASE_PATH + "/sceneExpent/saveOACashBillNew";
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(cashBillCompany),
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (data) {
                parent.layer.close(loadIndex);
                if ("200" == data.returnCode) {
                    parent.msgProp("操作成功！");
                    parent.redirectTo('delete', null, null);
                } else {
                    parent.msgAlert(data.returnMsg);
                }
            },
            error: function (data) {
                //parent.layer.close(loadIndex);
                parent.msgAlert(data.returnMsg);
            }
        });

    }

});

//删除图片
function UPLOAD_IMG_DEL(divs) {
    $("#"+divs).remove();
}

batchExpentNewJs = {}
batchExpentNewJs.getJss = function (obj) {
    parent.layer.open({
        type: 2,
        title: '选择结算书',
        area: ['820px', '660px'],
        content: BASE_PATH + '/sceneExpent/jssListPopup/'
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                console.log('选择结算书'+formData);
                $("#reportCard").show();
                $("#fieldset1").show();
                $("#uploadImg").show();
                $("#uploadFpImg").show();
                $("#uploadOtherImg").show();

                if($("#jssNo").val()!=NullToEmpty(formData.jssNo)){
                    $("#jssNo").val(NullToEmpty(formData.jssNo));
                    $("#accountProjectNo").val(NullToEmpty(formData.hsCode));
                    $("#companyName").val(NullToEmpty(formData.companyName));
                    $("#estateNm").val(NullToEmpty(formData.projectName));
                    $("#vendorId").val(NullToEmpty(formData.vender_id));
                    $("#vendorName").val(NullToEmpty(formData.vender_name));


                    $("#receiveBankName").val('');
                    $("#receiveBankAccountCardCode").val('');
                    $("#receiveBankAccountName").val('');
                    $("#receiveBankProvinceName").val('');
                    $("#receiveBankCityName").val('');
                    $("#receiveBankSerialNo").val('');
                    $("#receiveBankProvinceCityName").val('');

                    batchExpentNewJs.clearReport();
                    batchExpentNewJs.getReport();
                }
            }
            parent.layer.close(index);
        }
    });
}


batchExpentNewJs.getReceiveBank = function (obj) {//选择收款银行
    var jssNo = $("#jssNo").val();
    var vendorId = $("#vendorId").val();
    if (isEmpty(jssNo)) {
        parent.msgAlert("请选择结算书!");
        return false;
    }

    parent.layer.open({
        type: 2,
        title: '选择银行',
        area: ['820px', '660px'],
        content: BASE_PATH + '/sceneExpent/getOAReceiveBank/' + vendorId
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                $("#receiveBankName").val(NullToEmpty(formData.bankName));
                $("#receiveBankAccountCardCode").val(NullToEmpty(formData.bankAccountCardCode));
                $("#receiveBankAccountName").val(NullToEmpty(formData.bankAccountName));
                $("#receiveBankProvinceName").val(NullToEmpty(formData.provinceName));
                $("#receiveBankCityName").val(NullToEmpty(formData.cityName));
                $("#receiveBankSerialNo").val(NullToEmpty(formData.serialNo));
                $("#receiveBankProvinceCityName").val(NullToEmpty(formData.provinceName) + ' ' + NullToEmpty(formData.cityName));
            }
            parent.layer.close(index);
        }
    });
}

batchExpentNewJs.selectCheckBody = function (obj) {
    var hsCode = $(obj).parents("tr").find("input[name='accountProjectNo']").val();
    if (isEmpty(hsCode)) {
        parent.msgErrorProp("核算主体为空！")
        return;
    }
    parent.layer.open({
        type: 2,
        title: '选择考核主体',
        area: ['820px', '660px'],
        content: BASE_PATH + '/jsStatement/jsKhListPopup/' + hsCode
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            parent.layer.close(index);
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                var hsCode = $(obj).parents("tr").find("input[name='checkBodyId']").val(formData.khCode);
                var hsCode = $(obj).parents("tr").find("input[name='checkBodyName']").val(formData.khName);
                var hsCode = $(obj).parents("tr").find('span[name="checkBodyNameShow"]').html(formData.khName);
            }
        }
    });
}


batchExpentNewJs.openDialogGetJssDtl = function (obj) {
    var jssNo = $("#jssNo").val();
    if (isEmpty(jssNo)) {
        parent.msgAlert("请选择结算书!");
        return false;
    }

    var leftTable = $("div[lay-id='normalBlock'] .layui-table-fixed-l ");
    var mainTable = $("div[lay-id='normalBlock'] .layui-table-main ");
    var rightTable = $("div[lay-id='normalBlock'] .layui-table-fixed-r ");

    parent.layer.open({
        type: 2,
        title: '选择请款订单',
        area: ['820px', '660px'],
        content: BASE_PATH + '/sceneExpent/jssDtlListPopup?jssNo='+jssNo
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();
            //确认的回调
            if (formData != null) {
                console.log('选择请款订单'+formData);

            }

            //确认的回调
            if (formData != null) {
                var pjsdId = NullToEmpty(formData.id);
                var line = 0;
                $(mainTable).find("input[name='pjsdId']").each(function (i, obj) {
                    if (pjsdId == $(obj).val()) {
                        line++;
                    }
                });
                if (line > 0) {
                    parent.msgAlert("选择的请款订单已存在");
                    return;
                }
                batchExpentNewJs.addColReport(formData);

                parent.layer.close(index);
            }
        }
    });
}

batchExpentNewJs.getReport= function(){
    var jssNo = $("#jssNo").val();
    if(!isEmpty(jssNo)){
            var params = {  jssNo: jssNo
                            ,pageLimit:100
                            ,curPage:1
                         };
            $.ajax({
                url: BASE_PATH + '/sceneExpent/selectJsStatementDtlListCanQk',
                data: params,
                async: false,
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    $.each(data.returnData, function (n, value) {
                        batchExpentNewJs.addColReport(value);
                    });
                }
            });
    }
}

batchExpentNewJs.addColReport= function(formData){
    var leftTable = $("div[lay-id='normalBlock'] .layui-table-fixed-l ");
    var mainTable = $("div[lay-id='normalBlock'] .layui-table-main ");
    var rightTable = $("div[lay-id='normalBlock'] .layui-table-fixed-r ");

    $(leftTable).find(".layui-table-body").removeAttr("style");
    $(rightTable).find(".layui-table-body").removeAttr("style");

    var rowIndex = $(mainTable).find("tr").length;
    //对应字段已和cg核对
    var reportId	 		=        NullToEmpty(formData.rId);
    var reportNo 			=        NullToEmpty(formData.reportId);
    var buildingNo 			=        NullToEmpty(formData.buildingNo);
    var customerNm 			=        NullToEmpty(formData.customerName);
    var area 				=        NullToZero(formData.cxArea);
    var dealAmount			=        NullToZero(formData.cxAmount);
    var requestAmount  		=        NullToZero(formData.kpAmount);
    var taxAmount      		=        NullToZero(formData.kpTaxAmount);
    var requestType     	=        NullToZero(formData.jsStatementType);
    var reportDetailId  	=        NullToZero(formData.reportDetailId);
    var offSetFlag     		=        NullToZero(formData.offSetFlag);
    var accountProject 		=        NullToEmpty(formData.hsName);
    var accountProjectNo 	=        NullToEmpty(formData.hsCode);
    var pjsdId       		=        NullToEmpty(formData.id);
    var isGlobalControl 	=        NullToEmpty(formData.isGlobalControl);
    var jssId  				=        NullToEmpty(formData.jssId);
    var projectName  		=        NullToEmpty(formData.projectName);

    var isGlobalControlStr  =        isGlobalControl == 1 ? "项目总控" : "房源单控";
    var requestTypeStr      =         '-';
    if(requestType==1){
        requestTypeStr = '返佣';
    }else if(requestType==2){
        requestTypeStr = '垫佣';
    }

    var fixTrStr = '';
    fixTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
    fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-0 laytable-cell-numbers">' + rowIndex + '</div></td>';
    fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-1">' + reportNo + '</div></td>';
    fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-2">' + customerNm + '</div></td>';
    fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-3">' + buildingNo + '</div></td>';
    fixTrStr += '</tr>';

    var contentTrStr = '';
    contentTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-0 laytable-cell-numbers">' + rowIndex + '</div></td>';
    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-1">' + reportNo + '</div></td>';
    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-2">' + customerNm + '</div></td>';
    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-3">' + buildingNo + '</div></td>';


    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-4">';
    contentTrStr += '<input type="hidden" name="reportId" 			value="' +  reportId	 	 + '">';
    contentTrStr += '<input type="hidden" name="reportNo" 			value="' +  reportNo 		 + '">';
    contentTrStr += '<input type="hidden" name="buildingNo" 		value="' +  buildingNo 		 + '">';
    contentTrStr += '<input type="hidden" name="customerNm" 		value="' +  customerNm 		 + '">';
    contentTrStr += '<input type="hidden" name="area" 				value="' +  area 			 + '">';
    contentTrStr += '<input type="hidden" name="dealAmount" 		value="' +  dealAmount		 + '">';
    contentTrStr += '<input type="hidden" name="requestAmount" 		value="' +  requestAmount  	 + '">';
    contentTrStr += '<input type="hidden" name="requestType" 		value="' +  requestType      + '">';
    contentTrStr += '<input type="hidden" name="reportDetailId" 	value="' +  reportDetailId   + '">';
    contentTrStr += '<input type="hidden" name="offSetFlag" 		value="' +  offSetFlag     	 + '">';
    contentTrStr += '<input type="hidden" name="accountProject" 	value="' +  accountProject 	 + '">';
    contentTrStr += '<input type="hidden" name="accountProjectNo" 	value="' +  accountProjectNo + '">';
    contentTrStr += '<input type="hidden" name="pjsdId" 			value="' +  pjsdId       	 + '">';
    contentTrStr += '<input type="hidden" name="isGlobalControl" 	value="' +  isGlobalControl  + '">';
    contentTrStr += '<input type="hidden" name="jssId" 			  	value="' +  jssId  			 + '">';
    contentTrStr += '<span name="isGlobalControl">' + isGlobalControlStr + '</span>'
    contentTrStr += '</div></td>';
    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-5">' + projectName + '</div></td>';
    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-6">' + formatMoney(area) + '</div></td>';
    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-7">' + formatMoney(dealAmount) + '</div></td>';
    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-8">' + formatMoney(requestAmount) + '</div></td>';

    contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-1-0-9">';
    contentTrStr += '<input type="text" name="taxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchExpentNewJs.evalNormalItem(\'taxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:batchExpentNewJs.formatter(this,0)" class="renderInput mr" value="'+taxAmount+'">'
    contentTrStr += '</div></td>';
    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-10">' + requestTypeStr + '</div></td>';
    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-11">' + accountProject + '</div></td>';

    contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-1-0-12" align="center">';
    contentTrStr += '<input type="hidden" name="checkBodyId" value=""/><input type="hidden" name="checkBodyName" value=""/>';
    contentTrStr += '<span name="checkBodyNameShow"></span><a class="layui-btn layui-btn-xs" style="float: right;margin-top: 3px;" onclick="javascript:batchExpentNewJs.selectCheckBody(this)">选择</a>';
    contentTrStr += '</div></td>';

    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-13">';
    contentTrStr += '   <input type="text" name="memo" maxlength="200" autocomplete="off"  class="renderInput ml" value="">'
    contentTrStr += '</div></td>';


    contentTrStr += '</div></td>';
    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-14"></div></td>';
    contentTrStr += '</tr>';

    var rightTrStr = '';
    rightTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
    rightTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-16">'
    rightTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="javascript:batchExpentNewJs.delete(\'normalBlock\',this)">删除</a></div></td>';
    rightTrStr += '</tr>';

    $(leftTable).find("tr[tag='sum']").before(fixTrStr);
    $(mainTable).find("tr[tag='sum']").before(contentTrStr);
    $(rightTable).find("tr[tag='sum']").before(rightTrStr);

    batchExpentNewJs.evalNormalTotal();
}

batchExpentNewJs.evalNormalTotal = function () {
    batchExpentNewJs.evalNormalItem("area");
    batchExpentNewJs.evalNormalItem("dealAmount");
    batchExpentNewJs.evalNormalItem("requestAmount");
    batchExpentNewJs.evalNormalItem("taxAmount");
}

batchExpentNewJs.evalNormalItem = function (itemName) {
    var itemTotal = 0.00;

    $("div[lay-id='normalBlock'] .layui-table-main tbody").find("input[name='" + itemName + "']").each(function () {
        var value = $(this).val();
        itemTotal = itemTotal + formatAccount(value);
    });
    itemTotal = formatAccount2(itemTotal);
    $("div[lay-id='normalBlock']  .layui-table-main tbody").find("span[name='" + itemName + "']").html(formatCurrency(itemTotal));
    $("div[lay-id='normalBlock']  .layui-table-main tbody").find("input[name='" + itemName + "Sum']").val(itemTotal);

    batchExpentNewJs.evalAllItem(itemName);
}


batchExpentNewJs.evalAllItem = function (itemName) {
    var itemTotal = 0.00;

    $("#reportCard").find("input[name='" + itemName + "Sum']").each(function () {
        var value = $(this).val();
        itemTotal = itemTotal + formatAccount(value);
    });
    itemTotal = formatAccount2(itemTotal);
    $("#reportCard").find("input[name='" + itemName + "Total']").val(itemTotal);
    $("#reportCard").find("span[tag='" + itemName + "Total']").html(formatCurrency(itemTotal));

    if(itemName == 'taxAmount'){
        var requestAmountTotal = $("#reportCard").find("input[name='requestAmountTotal']").val();
        var taxAmountTotal = $("#reportCard").find("input[name='taxAmountTotal']").val();
        var noTaxAmountTotal = requestAmountTotal - formatAccount(taxAmountTotal);
        $("#reportCard").find("input[name='noTaxAmountTotal']").val(noTaxAmountTotal);
        $("#reportCard").find("span[tag='noTaxAmountTotal']").html(formatCurrency(noTaxAmountTotal));
    }
}


batchExpentNewJs.delete = function (block, obj) {
    var index = $(obj).parents("tr").attr("data-index");

    var leftTBody = $("div[lay-id='" + block + "']  .layui-table-fixed-l .layui-table-body tbody");
    var mainTBody = $("div[lay-id='" + block + "']  .layui-table-main tbody");
    var rightTBody = $("div[lay-id='" + block + "'] .layui-table-fixed-r .layui-table-body tbody");

    $(leftTBody).find("tr[data-index='" + index + "']").empty().remove();
    $(mainTBody).find("tr[data-index='" + index + "']").empty().remove();
    $(rightTBody).find("tr[data-index='" + index + "']").empty().remove();


    $(leftTBody).find("tr[tag!='sum']").each(function (i, tr) {
        $(tr).attr("data-index", i);
        $(tr).find(".laytable-cell-numbers").html(i + 1);
    });

    $(mainTBody).find("tr[tag!='sum']").each(function (i, tr) {
        $(tr).attr("data-index", i);
        $(tr).find(".laytable-cell-numbers").html(i + 1);
    });

    $(rightTBody).find("tr[tag!='sum']").each(function (i, tr) {
        $(tr).attr("data-index", i);
    });
    batchExpentNewJs.evalNormalTotal();
}

batchExpentNewJs.formatter = function (o, offset) {
    var tmp = o.value
    if (isEmpty(tmp)) {
        tmp = 0;
    }
    if (offset) {
        if (tmp > 0) {
            tmp = -1 * tmp;
        }
    }
    o.value = parseFloat(tmp).toFixed(2);
}

batchExpentNewJs.clearReport = function () {
    $("div[lay-id='normalBlock']").find("tbody tr[tag!=sum]").empty().remove();
    batchExpentNewJs.evalNormalTotal();
}