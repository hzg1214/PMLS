var evalNormalTotal;
var evalOffSetTotal;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;


    init();

    function init() {

        // 付款方式
        dictionaryLinkageIsService("payType", 223, function () {
            form.render('select');
        });

        var nowDate = new Date().format('yyyy-MM-dd');

        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }

        laydate.render({
            elem: '#recordTime',
            type: 'date',
            format: 'yyyy-MM-dd',
            min: switchDate,
            trigger: 'click'
        });

        laydate.render({
            elem: '#predictPayTime',
            type: 'date',
            format: 'yyyy-MM-dd',
            min: nowDate,
            trigger: 'click'
        });

        form.render('select'); // 刷新单选框渲染

        // 取得主体数据
        getMainData()

    }

    function getMainData() {
        var url = BASE_PATH + "/sceneExpent/getBatchExpentInfo/" + $("#estateId").val();
        var params = {};
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        ajaxGet(url, params, function (data) {
            setProjectValue(data.returnData);
            drawCard(data.returnData);
            initialization();
            parent.layer.close(loadIndex);
        }, function (data) {
            parent.layer.close(loadIndex);
            parent.msgError(data.returnMsg);
        });
    }

    function initialization() {
        form.on('select(accountProject)', function (data) {
            batchExpent.changeAccountProject(this);
        });
    }

    function setProjectValue(d) {
        var id = isEmpty(d.id) ? 0 : d.id;
        var cityNo = isEmpty(d.cityNo) ? "" : d.cityNo;
        var estateId = isEmpty(d.estateId) ? "" : d.estateId;
        var estateNm = isEmpty(d.estateNm) ? "" : d.estateNm;
        var estateNm = isEmpty(d.estateNm) ? "" : d.estateNm;
        var projectNo = isEmpty(d.projectNo) ? "" : d.projectNo;
        var isSpecialProject = isEmpty(d.isSpecialProject) ? 0 : d.isSpecialProject;

        var recordTime = isEmpty(d.recordTime) ? "" : formatDate(d.recordTime, "yyyy-MM-dd");
        var predictPayTime = isEmpty(d.predictPayTime) ? "" : formatDate(d.predictPayTime, "yyyy-MM-dd");
        var payType = isEmpty(d.payType) ? 22303 : d.payType;

        $("#id").val(id);
        $("#estateCityNo").val(cityNo);
        $("#cityNo").val(cityNo);
        $("#estateId").val(estateId);
        $("#estateNm").val(estateNm);
        $("#projectNo").val(projectNo);
        $("#isSpecialProject").val(isSpecialProject);

        $("#recordTime").val(recordTime);
        $("#predictPayTime").val(predictPayTime);
        $("#payType").val(payType);

        //初始化加载图片
        loadImageList('uploadImg', d.fileList, true);
    }

    function drawCard(d) {
        var companys = d.companyList;
        var valStr = "";


        for (var i = 0; i < companys.length; i++) {
            var tableOffSetIndex = 2 * i + 2;

            var c = companys[i];

            var comParentId = NullToEmpty(c.id);
            var proParentId = NullToEmpty(c.proParentId);
            var companyName = NullToEmpty(c.companyName);
            var companyNo = NullToEmpty(c.companyNo);
            var companyId = NullToEmpty(c.companyId);
            var accountProject = NullToEmpty(c.accountProject);
            var accountProjectNo = NullToEmpty(c.accountProjectNo);
            var frameOaName = NullToEmpty(c.frameOaName);
            var frameOaNo = NullToEmpty(c.frameOaNo);
            var vendorId = NullToEmpty(c.vendorId);
            var vendorName = NullToEmpty(c.vendorName);
            var vendorCode = NullToEmpty(c.vendorCode);
            var receiveBankName = NullToEmpty(c.receiveBankName);
            var receiveBankAccountCardCode = NullToEmpty(c.receiveBankAccountCardCode);
            var receiveBankAccountName = NullToEmpty(c.receiveBankAccountName);
            var receiveBankProvinceName = NullToEmpty(c.receiveBankProvinceName);
            var receiveBankCityName = NullToEmpty(c.receiveBankCityName);
            var receiveBankSerialNo = NullToEmpty(c.receiveBankSerialNo);
            var offSetFlag = NullToEmpty(c.offSetFlag);
            var remarks = NullToEmpty(c.remarks);


            var areaTotal = NullToEmpty(c.areaTotal);
            var roughAmountTotal = NullToEmpty(c.roughAmountTotal);
            var dealAmountTotal = NullToEmpty(c.dealAmountTotal);
            var sqYjsrAmountTotal = NullToEmpty(c.sqYjsrAmountTotal);
            var sqYjfyAmountTotal = NullToEmpty(c.sqYjfyAmountTotal);
            var sqYjdyAmountTotal = NullToEmpty(c.sqYjdyAmountTotal);
            var sqSjsrAmountTotal = NullToEmpty(c.sqSjsrAmountTotal);
            var sqSjfyAmountTotal = NullToEmpty(c.sqSjfyAmountTotal);
            var sqSjdyAmountTotal = NullToEmpty(c.sqSjdyAmountTotal);
            var requestAmountTotal = NullToEmpty(c.requestAmountTotal);
            var taxAmountTotal = NullToEmpty(c.taxAmountTotal);


            valStr += '<div class="layui-card" name="cardCompany" id="divCard' + companyNo + '">';
            valStr += '    <div class="layui-card-body">';
            valStr += '           <div class="layui-form">';
            valStr += '               <div class="layui-form-item layui-row" style="margin-top: 10px;">';
            valStr += '                   <div class="layui-col-xs6">';
            valStr += '                       <label class="layui-form-label"><b>经纪公司</b> </label>';
            valStr += '                       <div class="layui-col-xs3">';
            valStr += '                           <label class="layui-form-label w300"><b>' + companyName + '(' + companyNo + ')</b></label>';
            valStr += '                           <input type="hidden" name="comParentId" value="' + comParentId + '"/>';
            valStr += '                           <input type="hidden" name="companyNo" value="' + companyNo + '"/>';
            valStr += '                           <input type="hidden" name="companyId" value="' + companyId + '"/>';
            valStr += '                           <input type="hidden" name="companyName" value="' + companyName + '"/>';
            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '                   <div class="layui-col-xs6">';
            valStr += '                       <label class="layui-form-label"><i>*</i>核算主体</label>';
            valStr += '                       <div class="layui-col-xs6" style="text-align: left">';
            valStr += '                           <select name="accountProject" lay-filter="accountProject" defVal="' + accountProjectNo + '" onchange="javascript:batchExpent.changeAccountProject(this)" >';
            valStr += $("#oaAccountProject").html();
            valStr += '                           </select>';
            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '               </div>';
            valStr += '               <div class="layui-form-item layui-row">';
            valStr += '                   <div class="layui-col-xs6">';
            valStr += '                       <label class="layui-form-label"><i>*</i>合同</label>';
            valStr += '                       <div class="layui-col-xs6">';
            valStr += '                           <input type="text" placeholder="请选择" name="frameOaNo" lay-filter="frameOaNo" readonly disabled value="' + frameOaNo + '" data-code="' + frameOaNo + '" data-name="' + frameOaName + '"  class="layui-input">';
            valStr += '                       </div>';
            valStr += '                       <div class="layui-col-xs1" style="padding-left: 10px">';
            valStr += '                           <button class="layui-btn layui-btn-normal" onclick="javascript:batchExpent.getFrmAgreement(this);">选择 </button>';
            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '                   <div class="layui-col-xs6">';
            valStr += '                       <label class="layui-form-label"><i>*</i>供应商</label>';
            valStr += '                       <div class="layui-col-xs6">';
            valStr += '                           <input type="text"  name="vendorId" lay-filter="vendorId" readonly disabled value="' + vendorName + '" data-id="' + vendorId + '" data-code="' + vendorCode + '" class="layui-input">';
            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '               </div>';
            valStr += '               <div class="layui-form-item layui-row">';
            valStr += '                   <div class="layui-col-xs6">';
            valStr += '                       <label class="layui-form-label"><i>*</i>收款银行</label>';
            valStr += '                       <div class="layui-col-xs6">';
            valStr += '                           <input type="text" placeholder="请选择" name="receiveBankName" lay-filter="receiveBankName" readonly disabled value="' + receiveBankName + '" class="layui-input">';
            valStr += '                       </div>';
            valStr += '                       <div class="layui-col-xs1" style="padding-left: 10px">';
            valStr += '                           <button class="layui-btn layui-btn-normal" onclick="javascript:batchExpent.getReceiveBank(this);">选择 </button>';
            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '                   <div class="layui-col-xs6">';
            valStr += '                       <label class="layui-form-label"><i>*</i>银行账户</label>';
            valStr += '                       <div class="layui-col-xs6">';
            valStr += '                           <input type="text" name="receiveBankAccountCardCode" lay-filter="receiveBankAccountCardCode" readonly disabled value="' + receiveBankAccountCardCode + '" class="layui-input">';
            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '               </div>';
            valStr += '               <div class="layui-form-item layui-row">';
            valStr += '                   <div class="layui-col-xs6">';
            valStr += '                       <label class="layui-form-label"><i>*</i>收款户名</label>';
            valStr += '                       <div class="layui-col-xs6">';
            valStr += '                           <input type="text" name="receiveBankAccountName" lay-filter="receiveBankAccountName" readonly disabled value="' + receiveBankAccountName + '" class="layui-input">';
            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '                   <div class="layui-col-xs6">';
            valStr += '                       <label class="layui-form-label"><i>*</i>收款省市</label>';
            valStr += '                       <div class="layui-col-xs6">';
            valStr += '                        <input type="text" name="receiveBankProvinceCityName" lay-filter="receiveBankProvinceCityName" readonly disabled value="' + receiveBankProvinceName + " " + receiveBankCityName + '" class="layui-input">';
            valStr += '                           <input type="hidden" name="receiveBankProvinceName" lay-filter="receiveBankProvinceName" value= "' + receiveBankProvinceName + '" / >';
            valStr += '                           <input type="hidden" name="receiveBankCityName" lay-filter="receiveBankCityName" value= "' + receiveBankCityName + '" / >';
            valStr += '                           <input type="hidden" name="receiveBankSerialNo" lay-filter="receiveBankSerialNo" value= "' + receiveBankSerialNo + '" / >';
            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '               </div>';

            valStr += '               <div class="layui-form-item layui-row">';
            valStr += '                   <div class="layui-col-xs12">';
            valStr += '                       <label class="layui-form-label"><i>*</i>冲抵请款</label>';
            valStr += '                       <div class="layui-col-xs9">';
            valStr += '                            <input ';
            if (!isEmpty(offSetFlag) && offSetFlag == 1) {
                valStr += '  checked="checked"';
            }
            valStr += '    type="radio" value="1" tag="offSetFlag"  name="offSetFlag' + companyNo + '" title="包含" lay-filter="offSetFlag' + companyNo + '" >';
            valStr += '                            <input ';
            if (isEmpty(offSetFlag) || offSetFlag == 0) {
                valStr += '  checked="checked"';
            }
            valStr += '     type="radio" value="0" tag="offSetFlag"  name="offSetFlag' + companyNo + '" title="不包含" lay-filter="offSetFlag' + companyNo + '" >';

            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '               </div>';
            valStr += '               <div class="layui-form-item layui-row">';
            valStr += '                   <div class="layui-col-xs12">';
            valStr += '                       <label class="layui-form-label">备注</label>';
            valStr += '                       <div class="layui-col-xs9">';
            valStr += '                           <textarea placeholder="200字以内" maxlength="200" name="remarks" lay-filter="remarks" class="layui-textarea">' + remarks + '</textarea>';
            valStr += '                       </div>';
            valStr += '                   </div>';
            valStr += '               </div>';
            valStr += '              <div tag="normal">';
            valStr += '                    <div class="layui-form-item layui-row">';
            valStr += '                        <div class="layui-col-xs12">';
            valStr += '                            <div class="layui-col-xs11" style="padding-left: 80px">';
            valStr += '                                   <label ><b>正常请款房源：</b></label>';
            valStr += '                            </div>';
            valStr += '                        </div>';
            valStr += '                    </div>';
            valStr += '                    <div class="layui-form-item layui-row">';
            valStr += '                        <div class="layui-col-xs12">';
            valStr += '                            <div class="layui-col-xs11" style="padding-left: 80px">';
            valStr += '                                <table id="table' + companyNo + '" name="reportTable" lay-size="sm" lay-filter="reportTable"></table>';
            valStr += '                            </div>';
            valStr += '                        </div>';
            valStr += '                    </div>';
            valStr += '              </div>';

            valStr += '               <div tag="offSet">';
            valStr += '                     <div class="layui-form-item layui-row">';
            valStr += '                         <div class="layui-col-xs12">';
            valStr += '                             <div class="layui-col-xs11" style="padding-left: 80px">';
            valStr += '                                    <label ><b>冲抵请款房源：</b></label> ';
            valStr += '                                    <img style="width: 20px;height: 20px;margin-left: 30px" onclick="javascript:batchExpent.addOffSetInfo(this,\'' + companyNo + '\',\'' + tableOffSetIndex + '\');" src="/meta/images/crm/plus.png">';
            valStr += '                             </div>';
            valStr += '                         </div>';
            valStr += '                     </div>';
            valStr += '                     <div class="layui-form-item layui-row">';
            valStr += '                         <div class="layui-col-xs12">';
            valStr += '                             <div class="layui-col-xs11" style="padding-left: 80px">';
            valStr += '                                 <table id="offset' + companyNo + '" name="offSetReportTable" lay-size="sm" lay-filter="offSetReportTable"></table>';
            valStr += '                             </div>';
            valStr += '                         </div>';
            valStr += '                     </div>';
            valStr += '               </div>';

            valStr += '               <div class="layui-form-item layui-row">';
            valStr += '                   <div class="layui-col-xs11">';
            valStr += '                    <input type="hidden" name="areaTotal" lay-filter="areaTotal" value="' + areaTotal + '" / >';
            valStr += '                    <input type="hidden" name="roughAmountTotal" lay-filter="roughAmountTotal" value="' + roughAmountTotal + '" / >';
            valStr += '                    <input type="hidden" name="dealAmountTotal" lay-filter="dealAmountTotal" value="' + dealAmountTotal + '" / >';
            valStr += '                    <input type="hidden" name="sqYjsrAmountTotal" lay-filter="sqYjsrAmountTotal" value="' + sqYjsrAmountTotal + '" / >';
            valStr += '                    <input type="hidden" name="sqYjfyAmountTotal" lay-filter="sqYjfyAmountTotal" value="' + sqYjfyAmountTotal + '" / >';
            valStr += '                    <input type="hidden" name="sqYjdyAmountTotal" lay-filter="sqYjdyAmountTotal" value="' + sqYjdyAmountTotal + '" / >';
            valStr += '                    <input type="hidden" name="sqSjsrAmountTotal" lay-filter="sqSjsrAmountTotal" value="' + sqSjsrAmountTotal + '" / >';
            valStr += '                    <input type="hidden" name="sqSjfyAmountTotal" lay-filter="sqSjfyAmountTotal" value="' + sqSjfyAmountTotal + '" / >';
            valStr += '                    <input type="hidden" name="sqSjdyAmountTotal" lay-filter="sqSjdyAmountTotal" value="' + sqSjdyAmountTotal + '" / >';
            valStr += '                    <input type="hidden" name="requestAmountTotal" lay-filter="requestAmountTotal" value="' + requestAmountTotal + '" / >';
            valStr += '                    <input type="hidden" name="taxAmountTotal" lay-filter="taxAmountTotal" value="' + taxAmountTotal + '" / >';
            valStr += '                       <div class="layui-col-xs3">&nbsp;</div>';
            valStr += '                       <div class="layui-col-xs3" >';
            valStr += '                              <b>含税请款金额总计：</b><span tag="requestAmountAll"></span>元';
            valStr += '                       </div>';
            valStr += '                       <div class="layui-col-xs3" >';
            valStr += '                              <b>税额总计：</b><span tag="taxAmountAll"></span>元';
            valStr += '                       </div>';
            valStr += '                       <div class="layui-col-xs3" >';
            valStr += '                              <b>不含税请款金额总计：</b><span tag="requestAmountNoTaxAll"></span>元';
            valStr += '                       </div>';
            valStr += '                 </div>';
            valStr += '           </div>';
            valStr += '        </div>';
            valStr += '    </div>';
            valStr += '</div>';

        }

        $("#companyInfoList").html(valStr);

        form.render("radio");

        for (var i = 0; i < companys.length; i++) {
            var c = companys[i];
            var companyNo = NullToEmpty(c.companyNo);
            var accountProjectNo = NullToEmpty(c.accountProjectNo);
            var offSetFlag = NullToEmpty(c.offSetFlag);

            $("#divCard" + companyNo).find("select[name='accountProject']").val(accountProjectNo);
            form.render("select");

            var tableIndex = 2 * i + 1;
            tableRender(companyNo, c.reportList, c.checkBodyList, tableIndex); // table 渲染
            var tableOffSetIndex = 2 * i + 2;
            offSetRender(companyNo, c.offSetList, tableOffSetIndex);

            form.on('radio(offSetFlag' + companyNo + ')', function (data) {
                batchExpent.offSetFlagChange(this, companyNo);
            });

            if (isEmpty(offSetFlag) || offSetFlag == 0) {
                $("#divCard" + companyNo).find("div[tag='offSet']").hide();
            }
        }
    }

    function tableRender(tableId, data, checkBodyList, tableIndex) {
        table.render({
            elem: "#table" + tableId
            , cols: setCols(tableId, checkBodyList)
            , id: 'content' + tableId
            , height: 'full'
            , limit: 1000
            , data: data
            , page: false
            , done: function (res, curr, count) {

                var fixTrStr = '';
                fixTrStr += '<tr tag="sum">'
                fixTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-0 "></div></td>';
                fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-1">小计：</div></td>';
                fixTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-2"></div></td>';
                fixTrStr += '</tr>';


                var contentTrStr = '';
                contentTrStr += '<tr tag="sum">'
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-0"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-1"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-2"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-3">';
                contentTrStr += '    <input type="hidden" name="areaSum">';
                contentTrStr += '    <input type="hidden" name="roughAmountSum">';
                contentTrStr += '    <input type="hidden" name="dealAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqYjsrAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqYjfyAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqYjdyAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqSjsrAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqSjfyAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqSjdyAmountSum">';
                contentTrStr += '    <input type="hidden" name="requestAmountSum">';
                contentTrStr += '    <input type="hidden" name="taxAmountSum">';
                contentTrStr += '</div></td>';

                contentTrStr += '<td data-field="area" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-4"><span name="area"></span></div></td>';
                contentTrStr += '<td data-field="roughAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-5"><span name="roughAmount"></span></div></td>';
                contentTrStr += '<td data-field="dealAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-6"><span name="dealAmount"></span></div></td>';

                contentTrStr += '<td data-field="sqYjsrAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-0"><span name="sqYjsrAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqYjfyAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-1"><span name="sqYjfyAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqSjsrAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-2"><span name="sqYjdyAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqSjsrAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-3"><span name="sqSjsrAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqSjfyAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-4"><span name="sqSjfyAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqSjfyAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-5"><span name="sqSjdyAmount"></span></div></td>';

                contentTrStr += '<td data-field="requestAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-8"><span name="requestAmount"></span></div></td>';
                contentTrStr += '<td data-field="taxAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-9"><span name="taxAmount"></span></div></td>';

                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-10"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-11"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-12"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-13"></div></td>';
                contentTrStr += '</tr>';

                var rightTrStr = '';
                rightTrStr += '<tr tag="sum">'
                rightTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-13"><span></span></div></td>';
                rightTrStr += '</tr>';

                $("div[lay-id='content" + tableId + "'] .layui-table-fixed-l .layui-table-body tbody").append(fixTrStr);
                $("div[lay-id='content" + tableId + "'] .layui-table-main tbody").append(contentTrStr);
                $("div[lay-id='content" + tableId + "'] .layui-table-fixed-r .layui-table-body tbody").append(rightTrStr);

                evalNormalTotal(tableId);

                $("div[lay-id='content" + tableId + "'] .layui-table-main tbody")
                    .find("tr[tag!='sum']").each(function (i, tr) {
                    batchExpent.evalRequestTypeByTr(tr);
                });
            }
        });

    }

    function setCols(companyNo, checkBodyList) {
        var row1 = [
            {type: 'numbers', fixed: true, rowspan: 2, title: '序号', width: 60},
            {
                field: 'reportNo', fixed: true, title: '报备编号', rowspan: 2, width: 150, align: 'center'
            },
            {
                field: 'buildingNo', fixed: true, title: '楼室号', rowspan: 2, width: 120, align: 'center',
                templet: function (d) {
                    if (isEmpty(d.buildingNo)) {
                        return "-"
                    } else {
                        return d.buildingNo;
                    }
                }
            },
            {
                field: 'customerNm', title: '客户姓名', rowspan: 2, width: 150, align: 'center',
                templet: function (d) {
                    var reportId = NullToEmpty(d.reportId);
                    var reportDetailId = NullToEmpty(d.reportDetailId);
                    var reportNo = NullToEmpty(d.reportNo);
                    var id = NullToEmpty(d.id);
                    var comParentId = NullToEmpty(d.comParentId);
                    var proParentId = NullToEmpty(d.proParentId);

                    var buildingNo = NullToEmpty(d.buildingNo);
                    var customerNm = NullToEmpty(d.customerNm);
                    var area = NullToZero(d.area);
                    var roughAmount = NullToZero(d.roughAmount);
                    var dealAmount = NullToZero(d.dealAmount);
                    var sqYjsrAmount = NullToZero(d.sqYjsrAmount);
                    var sqYjfyAmount = NullToZero(d.sqYjfyAmount);
                    var sqYjdyAmount = NullToZero(d.sqYjdyAmount);
                    var sqSjsrAmount = NullToZero(d.sqSjsrAmount);
                    var sqSjfyAmount = NullToZero(d.sqSjfyAmount);
                    var sqSjdyAmount = NullToZero(d.sqSjdyAmount);
                    var requestType = NullToZero(d.requestType);

                    var progress = NullToEmpty(d.progress);
                    var confirmStatus = NullToEmpty(d.confirmStatus);

                    var ret = "";
                    ret += '<input type="hidden" name="reportId" value="' + reportId + '">';
                    ret += '<input type="hidden" name="reportDetailId" value="' + reportDetailId + '">';
                    ret += '<input type="hidden" name="reportNo" value="' + reportNo + '">';

                    ret += '<input type="hidden" name="id" value="' + id + '">';
                    ret += '<input type="hidden" name="comParentId" value="' + comParentId + '">';
                    ret += '<input type="hidden" name="proParentId" value="' + proParentId + '">';


                    ret += '<input type="hidden" name="buildingNo" value="' + buildingNo + '">';
                    ret += '<input type="hidden" name="customerNm" value="' + customerNm + '">';
                    ret += '<input type="hidden" name="area" value="' + area + '">';
                    ret += '<input type="hidden" name="roughAmount" value="' + roughAmount + '">';
                    ret += '<input type="hidden" name="dealAmount" value="' + dealAmount + '">';
                    ret += '<input type="hidden" name="sqYjsrAmount" value="' + sqYjsrAmount + '">';
                    ret += '<input type="hidden" name="sqYjfyAmount" value="' + sqYjfyAmount + '">';
                    ret += '<input type="hidden" name="sqYjdyAmount" value="' + sqYjdyAmount + '">';

                    ret += '<input type="hidden" name="sqSjsrAmount" value="' + sqSjsrAmount + '">';
                    ret += '<input type="hidden" name="sqSjfyAmount" value="' + sqSjfyAmount + '">';
                    ret += '<input type="hidden" name="sqSjdyAmount" value="' + sqSjdyAmount + '">';
                    ret += '<input type="hidden" name="requestType" value="' + requestType + '">';

                    ret += '<input type="hidden" name="progress" value="' + progress + '">';
                    ret += '<input type="hidden" name="confirmStatus" value="' + confirmStatus + '">';
                    ret += '<span>' + customerNm + '</span>'
                    return ret;
                }
            },
            {
                field: 'area', title: '销售面积', rowspan: 2, width: 120, align: 'center', templet: function (d) {
                    var area = d.area;
                    return "<div style='text-align: right'>" + formatMoney(area) + "</div>";
                }
            },
            {
                field: 'roughAmount', title: '大定总价', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var roughAmount = d.roughAmount;
                    return "<div style='text-align: right'>" + formatMoney(roughAmount) + "</div>";
                }
            },
            {
                field: 'dealAmount', title: '成销总价', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var dealAmount = d.dealAmount;
                    return "<div style='text-align: right'>" + formatMoney(dealAmount) + "</div>";
                }
            },
            {title: '累计税前', colspan: 6, align: 'center'},
            {
                field: 'requestAmount', title: '含税请款金额<i>*</i>', rowspan: 2, width: 150, align: 'center',
                templet: function (d) {
                    var requestAmount = isEmpty(d.requestAmount) ? "" : formatAccount2(d.requestAmount);
                    return '<input type="text" name="requestAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchExpent.evalNormalItem(\'' + companyNo + '\',\'requestAmount\');batchExpent.evalRequestType(this);" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')"  onchange="javascript:batchExpent.formatter(this,0)" class="renderInput mr" value="' + requestAmount + '">'
                }
            },
            {
                field: 'taxAmount', title: '税额<i>*</i>', rowspan: 2, width: 150, align: 'center',
                templet: function (d) {
                    var taxAmount = isEmpty(d.taxAmount) ? "" : formatAccount2(d.taxAmount);
                    return '<input type="text" name="taxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchExpent.evalNormalItem(\'' + companyNo + '\',\'taxAmount\');batchExpent.evalRequestType(this);" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:batchExpent.formatter(this,0)"  class="renderInput mr" value="' + taxAmount + '">'
                }

            },
            {
                field: 'requestType', title: '请款类别', rowspan: 2, width: 100, align: 'center',
                templet: function (d) {
                    var requestTypeStr = "-";
                    if (isEmpty(d.requestType) || d.requestType == 0) {
                        requestTypeStr = "-";
                    } else if (d.requestType == 1) {
                        requestTypeStr = "返佣";
                    } else if (d.requestType == 2) {
                        requestTypeStr = "垫佣";
                    }

                    return '<span name="requestType">' + requestTypeStr + '</span>';
                }
            },
            {
                field: 'selCheckBodyId', title: '考核主体<i>*</i>', rowspan: 2, width: 200, align: 'center',
                templet: function (d) {
                    var ret = '<select  name="checkBody" lay-ignore class="renderInput">';
                    ret += '<option value="">请选择</option>'
                    var dbCheckBodyId = NullToEmpty(d.checkBodyId);
                    if (!isEmpty(checkBodyList)) {
                        for (var i = 0; i < checkBodyList.length; i++) {
                            var b = checkBodyList[i];
                            var checkBodyId = NullToEmpty(b.checkBodyId);
                            var checkBodyName = NullToEmpty(b.checkBodyName);
                            var selected = dbCheckBodyId == checkBodyId ? 'selected="selected"' : '';
                            ret += '<option value="' + checkBodyId + '" ' + selected + '>' + checkBodyName + '</option>';
                        }
                    }
                    ret += '</select>';
                    return ret;
                }
            },
            {
                field: 'memo', title: '说明', rowspan: 2, width: 150, align: 'center',
                templet: function (d) {
                    var memo = isEmpty(d.memo) ? "" : d.memo;
                    return '<input type="text" name="memo" maxlength="200" autocomplete="off"  class="renderInput ml" value="' + memo + '">'
                }
            },
            {
                title: '操作', fixed: 'right', align: 'center', rowspan: 2, width: 80,
                templet: function (d) {
                    return "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:batchExpent.delete(this,\"" + d.id + "\",\"" + d.comParentId + "\",\"" + d.proParentId + "\",\"" + companyNo + "\")'>删除</a>";
                }
            }
        ];
        var row2 = [
            {
                field: 'sqYjsrAmount', title: '应计收入', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqYjsrAmount = d.sqYjsrAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqYjsrAmount) + "</div>";
                }
            },
            {
                field: 'sqYjfyAmount', title: '应计返佣', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqYjfyAmount = d.sqYjfyAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqYjfyAmount) + "</div>";
                }
            },
            {
                field: 'sqYjdyAmount', title: '应计垫佣', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqYjdyAmount = d.sqYjdyAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqYjdyAmount) + "</div>";
                }
            },
            {
                field: 'sqSjsrAmount', title: '实收收入', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqSjsrAmount = d.sqSjsrAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqSjsrAmount) + "</div>";
                }
            },
            {
                field: 'sqSjfyAmount', title: '实际返佣', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqSjfyAmount = d.sqSjfyAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqSjfyAmount) + "</div>";
                }
            },
            {
                field: 'sqSjdyAmount', title: '实际垫佣', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqSjdyAmount = d.sqSjdyAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqSjdyAmount) + "</div>";
                }
            }
        ]
        var cols = [];
        cols.push(row1);
        cols.push(row2);
        return cols;
    }

    function offSetRender(tableId, data, tableIndex) {
        table.render({
            elem: "#offset" + tableId
            , cols: setOffSetCols(tableId)
            , id: 'offsetBlock' + tableId
            , height: 'full'
            , data: data
            , limit: 1000
            , page: false
            , done: function (res, curr, count) {
                var fixTrStr = '';
                fixTrStr += '<tr tag="sum">'
                fixTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-0 "></div></td>';
                fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-1">小计：</div></td>';
                fixTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-2"></div></td>';
                fixTrStr += '</tr>';

                var contentTrStr = '';
                contentTrStr += '<tr tag="sum">'
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-0"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-1"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-2"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-3">';
                contentTrStr += '    <input type="hidden" name="areaSum">';
                contentTrStr += '    <input type="hidden" name="roughAmountSum">';
                contentTrStr += '    <input type="hidden" name="dealAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqYjsrAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqYjfyAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqYjdyAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqSjsrAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqSjfyAmountSum">';
                contentTrStr += '    <input type="hidden" name="sqSjdyAmountSum">';
                contentTrStr += '    <input type="hidden" name="requestAmountSum">';
                contentTrStr += '    <input type="hidden" name="taxAmountSum">';
                contentTrStr += '</div></td>';
                contentTrStr += '<td data-field="area" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-4"><span name="area"></span></div></td>';
                contentTrStr += '<td data-field="roughAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-5"><span name="roughAmount"></span></div></td>';
                contentTrStr += '<td data-field="dealAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-6"><span name="dealAmount"></span></div></td>';

                contentTrStr += '<td data-field="sqYjsrAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-0"><span name="sqYjsrAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqYjfyAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-1"><span name="sqYjfyAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqSjsrAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-2"><span name="sqYjdyAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqSjsrAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-3"><span name="sqSjsrAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqSjfyAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-4"><span name="sqSjfyAmount"></span></div></td>';
                contentTrStr += '<td data-field="sqSjfyAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-5"><span name="sqSjdyAmount"></span></div></td>';

                contentTrStr += '<td data-field="requestAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-8"><span name="requestAmount"></span></div></td>';
                contentTrStr += '<td data-field="taxAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-9"><span name="taxAmount"></span></div></td>';

                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-10"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-11"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-12"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-13"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-14"></div></td>';

                contentTrStr += '</tr>';

                var rightTrStr = '';
                rightTrStr += '<tr tag="sum">'
                rightTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-14"><span></span></div></td>';
                rightTrStr += '</tr>';

                var leftTable = $("div[lay-id='offsetBlock" + tableId + "'] .layui-table-fixed-l ");
                var mainTable = $("div[lay-id='offsetBlock" + tableId + "'] .layui-table-main ");
                var rightTable = $("div[lay-id='offsetBlock" + tableId + "'] .layui-table-fixed-r ");

                $(leftTable).find(".layui-table-body tbody").append(fixTrStr);
                $(mainTable).find("tbody").append(contentTrStr);
                $(rightTable).find(".layui-table-body tbody").append(rightTrStr);

                $(leftTable).removeClass('layui-hide');
                $(mainTable).find('div[class="layui-none"]').empty().remove();
                $(rightTable).removeClass('layui-hide')

                evalOffSetTotal(tableId);
            }
        });
    }

    function setOffSetCols(companyNo) {
        var row1 = [
            {type: 'numbers', fixed: true, rowspan: 2, title: '序号', width: 60},
            {field: 'reportNo', fixed: true, title: '报备编号', rowspan: 2, width: 150, align: 'center'},
            {
                field: 'buildingNo', fixed: true, title: '楼室号', rowspan: 2, width: 120, align: 'center',
                templet: function (d) {
                    if (isEmpty(d.buildingNo)) {
                        return "-"
                    } else {
                        return d.buildingNo;
                    }
                }
            },
            {
                field: 'customerNm', title: '客户姓名', rowspan: 2, width: 150, align: 'center',
                templet: function (d) {
                    var reportId = NullToEmpty(d.reportId);
                    var reportDetailId = NullToEmpty(d.reportDetailId);
                    var reportNo = NullToEmpty(d.reportNo);
                    var id = NullToEmpty(d.id);
                    var comParentId = NullToEmpty(d.comParentId);
                    var proParentId = NullToEmpty(d.proParentId);

                    var buildingNo = NullToEmpty(d.buildingNo);
                    var customerNm = NullToEmpty(d.customerNm);
                    var area = NullToZero(d.area);
                    var roughAmount = NullToZero(d.roughAmount);
                    var dealAmount = NullToZero(d.dealAmount);
                    var sqYjsrAmount = NullToZero(d.sqYjsrAmount);
                    var sqYjfyAmount = NullToZero(d.sqYjfyAmount);
                    var sqYjdyAmount = NullToZero(d.sqYjdyAmount);
                    var sqSjsrAmount = NullToZero(d.sqSjsrAmount);
                    var sqSjfyAmount = NullToZero(d.sqSjfyAmount);
                    var sqSjdyAmount = NullToZero(d.sqSjdyAmount);

                    var requestType = NullToEmpty(d.requestType);
                    var progress = NullToEmpty(d.progress);
                    var confirmStatus = NullToEmpty(d.confirmStatus);

                    var ret = "";
                    ret += '<input type="hidden" name="reportId" value="' + reportId + '">';
                    ret += '<input type="hidden" name="reportDetailId" value="' + reportDetailId + '">';
                    ret += '<input type="hidden" name="reportNo" value="' + reportNo + '">';

                    ret += '<input type="hidden" name="id" value="' + id + '">';
                    ret += '<input type="hidden" name="comParentId" value="' + comParentId + '">';
                    ret += '<input type="hidden" name="proParentId" value="' + proParentId + '">';

                    ret += '<input type="hidden" name="buildingNo" value="' + buildingNo + '">';
                    ret += '<input type="hidden" name="customerNm" value="' + customerNm + '">';
                    ret += '<input type="hidden" name="area" value="' + area + '">';
                    ret += '<input type="hidden" name="roughAmount" value="' + roughAmount + '">';
                    ret += '<input type="hidden" name="dealAmount" value="' + dealAmount + '">';
                    ret += '<input type="hidden" name="sqYjsrAmount" value="' + sqYjsrAmount + '">';
                    ret += '<input type="hidden" name="sqYjfyAmount" value="' + sqYjfyAmount + '">';
                    ret += '<input type="hidden" name="sqYjdyAmount" value="' + sqYjdyAmount + '">';
                    ret += '<input type="hidden" name="sqSjsrAmount" value="' + sqSjsrAmount + '">';
                    ret += '<input type="hidden" name="sqSjfyAmount" value="' + sqSjfyAmount + '">';
                    ret += '<input type="hidden" name="sqSjdyAmount" value="' + sqSjdyAmount + '">';

                    ret += '<input type="hidden" name="progress" value="' + progress + '">';
                    ret += '<input type="hidden" name="confirmStatus" value="' + confirmStatus + '">';
                    ret += '<span>' + customerNm + '</span>'
                    return ret;
                }
            },
            {
                field: 'area', title: '销售面积', rowspan: 2, width: 120, align: 'center', templet: function (d) {
                    var area = d.area;
                    return "<div style='text-align: right'>" + formatMoney(area) + "</div>";
                }
            },
            {
                field: 'roughAmount', title: '大定总价', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var roughAmount = d.roughAmount;
                    return "<div style='text-align: right'>" + formatMoney(roughAmount) + "</div>";
                }
            },
            {
                field: 'dealAmount', title: '成销总价', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var dealAmount = d.dealAmount;
                    return "<div style='text-align: right'>" + formatMoney(dealAmount) + "</div>";
                }
            },
            {title: '累计税前', colspan: 6, align: 'center'},
            {
                field: 'requestAmount', title: '含税请款金额<i>*</i>', rowspan: 2, width: 150, align: 'center',
                templet: function (d) {
                    var requestAmount = isEmpty(d.requestAmount) ? "" : formatAccount2(d.requestAmount);
                    return '<input type="text" name="requestAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchExpent.evalOffSetItem(\'' + companyNo + '\',\'requestAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:batchExpent.formatter(this,1)" class="renderInput mr" value="' + requestAmount + '">'
                }
            },
            {
                field: 'taxAmount', title: '税额<i>*</i>', rowspan: 2, width: 150, align: 'center',
                templet: function (d) {
                    var taxAmount = isEmpty(d.taxAmount) ? "" : formatAccount2(d.taxAmount);
                    return '<input type="text" name="taxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchExpent.evalOffSetItem(\'' + companyNo + '\',\'taxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:batchExpent.formatter(this,1)" class="renderInput mr" value="' + taxAmount + '">'
                }

            },
            {
                field: 'requestType', title: '请款类别', rowspan: 2, width: 100, align: 'center', templet: function (d) {
                    var ret = " <select  name ='requestType' lay-ignore class='renderInput'>"

                    if (isEmpty(d.requestType) || d.requestType == 0) {
                        ret += '<option value="0" selected="selected">请选择</option>'
                    } else {
                        ret += '<option value="0">请选择</option>'
                    }
                    if (!isEmpty(d.requestType) && d.requestType == 1) {
                        ret += '<option value="1" selected="selected">返佣</option>'
                    } else {
                        ret += '<option value="1">返佣</option>'
                    }
                    if (!isEmpty(d.requestType) && d.requestType == 2) {
                        ret += '<option value="2" selected="selected">垫佣</option>'
                    } else {
                        ret += '<option value="2">垫佣</option>'
                    }
                    ret += '</select>'
                    return ret;
                }
            },
            {
                field: 'selAccountProjectList', title: '核算主体<i>*</i>', rowspan: 2, width: 200, align: 'center',
                templet: function (d) {

                    var ret = '<select  name="selAccountProject" onchange="javascript:batchExpent.changeOffSetAccountProject(this)" lay-ignore class="renderInput">';

                    if (d.progress == '13505' && d.confirmStatus == '13601') {
                        ret += '<option value="' + d.accountProjectNo + '" data-name="' + d.accountProject + '">' + d.accountProjectNo + "_" + d.accountProject + '</option>';
                    } else {
                        var dbAccountProjectList = d.accountProjectList;
                        var dbAccountProjectNo = NullToEmpty(d.accountProjectNo);
                        ret += ' <option value="">请选择</option>'

                        for (var i = 0; i < dbAccountProjectList.length; i++) {
                            var item = dbAccountProjectList[i];
                            var accountProjectCode = NullToEmpty(item.lnkAccountProjectCode);
                            var accountProjectName = NullToEmpty(item.accountProject);

                            ret += ' <option value="' + accountProjectCode + '"';
                            ret += '         data-accountProjectNo="' + accountProjectCode + '"';
                            ret += '         data-accountProject="' + accountProjectName + '"';
                            if (accountProjectCode == dbAccountProjectNo) {
                                ret += ' selected="selected"';
                            }
                            ret += '>' + accountProjectCode + '_' + accountProjectName + ' </option>';
                        }
                    }

                    ret += '</select>';

                    return ret;
                }
            },
            {
                field: 'selCheckBodyId', title: '考核主体<i>*</i>', rowspan: 2, width: 200, align: 'center',
                templet: function (d) {
                    var ret = '<select  name="checkBody" lay-ignore class="renderInput">';
                    ret += '<option value="">请选择</option>'
                    var dbCheckBodyId = NullToEmpty(d.checkBodyId);
                    var dbCheckBodyList = d.checkBodyList;
                    for (var i = 0; i < dbCheckBodyList.length; i++) {
                        var b = dbCheckBodyList[i];
                        var checkBodyId = NullToEmpty(b.checkBodyId);
                        var checkBodyName = NullToEmpty(b.checkBodyName);
                        var selected = dbCheckBodyId == checkBodyId ? 'selected="selected"' : '';
                        ret += '<option value="' + checkBodyId + '" ' + selected + '>' + checkBodyName + '</option>';
                    }

                    ret += '</select>';
                    return ret;
                }
            },
            {
                field: 'memo', title: '说明', rowspan: 2, width: 150, align: 'center',
                templet: function (d) {
                    var memo = isEmpty(d.memo) ? "" : d.memo;
                    return '<input type="text" name="memo" maxlength="200" autocomplete="off"  class="renderInput ml" value="' + memo + '">'
                }
            },
            {
                title: '操作', fixed: 'right', align: 'center', rowspan: 2, width: 80,
                templet: function (d) {
                    return "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:batchExpent.deleteOffSet(this,\"" + d.id + "\",\"" + d.comParentId + "\",\"" + d.proParentId + "\",\"" + companyNo + "\")'>删除</a>";
                }
            }
        ];
        var row2 = [
            {
                field: 'sqYjsrAmount', title: '应计收入', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqYjsrAmount = d.sqYjsrAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqYjsrAmount) + "</div>";
                }
            },
            {
                field: 'sqYjfyAmount', title: '应计返佣', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqYjfyAmount = d.sqYjfyAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqYjfyAmount) + "</div>";
                }
            },
            {
                field: 'sqYjdyAmount', title: '应计垫佣', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqYjdyAmount = d.sqYjdyAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqYjdyAmount) + "</div>";
                }
            },
            {
                field: 'sqSjsrAmount', title: '实收收入', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqSjsrAmount = d.sqSjsrAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqSjsrAmount) + "</div>";
                }
            },
            {
                field: 'sqSjfyAmount', title: '实际返佣', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqSjfyAmount = d.sqSjfyAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqSjfyAmount) + "</div>";
                }
            },
            {
                field: 'sqSjdyAmount', title: '实际垫佣', rowspan: 2, width: 150, align: 'center', templet: function (d) {
                    var sqSjdyAmount = d.sqSjdyAmount;
                    return "<div style='text-align: right'>" + formatMoney(sqSjdyAmount) + "</div>";
                }
            }
        ]
        var cols = [];
        cols.push(row1);
        cols.push(row2);
        return cols;
    }

    // 成销确认书/佣金结算资料
    uploadRender('uploadImg', {fileTypeId: '1032', fileSourceId: '9', exts: 'jpg|png|jpeg|pdf'});

    var active = {
        submit: function () {
            toSave(1)
        }
        , save: function () {
            toSave(0)
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

    evalNormalTotal = function (tableId) {
        batchExpent.evalNormalItem(tableId, "area");
        batchExpent.evalNormalItem(tableId, "roughAmount");
        batchExpent.evalNormalItem(tableId, "dealAmount");
        batchExpent.evalNormalItem(tableId, "sqYjsrAmount");
        batchExpent.evalNormalItem(tableId, "sqYjfyAmount");
        batchExpent.evalNormalItem(tableId, "sqYjdyAmount");
        batchExpent.evalNormalItem(tableId, "sqSjsrAmount");
        batchExpent.evalNormalItem(tableId, "sqSjfyAmount");
        batchExpent.evalNormalItem(tableId, "sqSjdyAmount");

        batchExpent.evalNormalItem(tableId, "requestAmount");
        batchExpent.evalNormalItem(tableId, "taxAmount");
    }

    evalOffSetTotal = function (tableId) {
        batchExpent.evalOffSetItem(tableId, "area");
        batchExpent.evalOffSetItem(tableId, "roughAmount");
        batchExpent.evalOffSetItem(tableId, "dealAmount");
        batchExpent.evalOffSetItem(tableId, "sqYjsrAmount");
        batchExpent.evalOffSetItem(tableId, "sqYjfyAmount");
        batchExpent.evalOffSetItem(tableId, "sqYjdyAmount");
        batchExpent.evalOffSetItem(tableId, "sqSjsrAmount");
        batchExpent.evalOffSetItem(tableId, "sqSjfyAmount");
        batchExpent.evalOffSetItem(tableId, "sqSjdyAmount");

        batchExpent.evalOffSetItem(tableId, "requestAmount");
        batchExpent.evalOffSetItem(tableId, "taxAmount");
    }

    function toSave(type) {

        // 检验内容
        if (vaild(type) == false) {
            return false;
        }

        saveToService(type)
    }

    function vaild(type) {

        var flag = true;
        $("div[name='cardCompany']").each(function (i, obj) {
            var companyNo = $(obj).find("input[name='companyNo']").val();
            var remark = $(obj).find("textarea[name='remarks']").val();
            var remarksLen = remark.length;
            if (!isEmpty(remarksLen) && remarksLen >= 200) {
                parent.msgAlert("经纪公司编号（" + companyNo + "）的请款备注已超200字，请缩减后再提交！")
                flag = false;
                return false;
            }
        })
        if (!flag) return flag;

        // 提交的场合
        if (type == 1) {

            // 入账日期
            var recordTime = $("#recordTime").val();
            if (isEmpty(recordTime)) {
                parent.msgAlert("请填写入账日期！")
                return false;
            }

            // 关账日期 <= 入账日期
            var switchDate = $("#switchDate").val();
            if (isEmpty(switchDate)) {
                switchDate = "1900-1-1";
            }
            if (!dateCompare(recordTime, switchDate)) {
                parent.msgAlert("入账日期不能在关账日期之前！");
                return false;
            }

            // 预计付款日期
            var predictPayTime = $("#predictPayTime").val();
            if (isEmpty(predictPayTime)) {
                parent.msgAlert("请填写预计付款日期！")
                return false;
            }
            // 付款方式
            var payType = $("#payType").val();
            if (isEmpty(payType)) {
                parent.msgAlert("请选择付款方式！")
                return false;
            }

            // 经纪公司面板
            $("div[name='cardCompany']").each(function (i, obj) {
                var companyNo = $(obj).find("input[name='companyNo']").val();
                var companyName = $(obj).find("input[name='companyName']").val()
                var accountProjectNo = $(obj).find("select[name='accountProject']").val();
                if (isEmpty(accountProjectNo)) {
                    parent.msgAlert("请选择经纪公司[" + companyName + "]的核算主体!");
                    flag = false;
                    return false;
                }

                var frameOaNo = $(obj).find("input[name='frameOaNo']").attr("data-code");
                var frameOaName = $(obj).find("input[name='frameOaNo']").attr("data-name");
                if (isEmpty(frameOaNo) || isEmpty(frameOaName)) {
                    parent.msgAlert("请选择经纪公司[" + companyName + "]的合同!");
                    flag = false;
                    return false;
                }

                var vendorId = $(obj).find("input[name='vendorId']").attr("data-id");
                var vendorCode = $(obj).find("input[name='vendorId']").attr("data-code");
                var vendorName = $(obj).find("input[name='vendorId']").val();
                if (isEmpty(vendorId) || isEmpty(vendorName) || isEmpty(vendorCode)) {
                    parent.msgAlert("请选择经纪公司[" + companyName + "]的供应商!");
                    flag = false;
                    return false;
                }
                var receiveBankName = $(obj).find("input[name='receiveBankName']").val();
                if (isEmpty(receiveBankName)) {
                    parent.msgAlert("请选择经纪公司[" + companyName + "]的收款银行!");
                    flag = false;
                    return false;
                }
                var receiveBankAccountCardCode = $(obj).find("input[name='receiveBankAccountCardCode']").val();
                if (isEmpty(receiveBankAccountCardCode)) {
                    parent.msgAlert("请选择经纪公司[" + companyName + "]的银行账户!");
                    flag = false;
                    return false;
                }
                var receiveBankAccountName = $(obj).find("input[name='receiveBankAccountName']").val();
                if (isEmpty(vendorId)) {
                    parent.msgAlert("请选择经纪公司[" + companyName + "]的收款户名!");
                    flag = false;
                    return false;
                }
                var receiveBankProvinceCityName = $(obj).find("input[name='receiveBankProvinceCityName']").val();
                if (isEmpty(vendorId)) {
                    parent.msgAlert("请选择经纪公司[" + companyName + "]的收款省市!");
                    flag = false;
                    return false;
                }

                //  正常请款房源 校验
                var tBodyNormal = $(obj).find("div[lay-id='content" + companyNo + "'] .layui-table-main tbody");
                $(tBodyNormal).find("tr[tag!='sum']").each(function (i, tr) {
                    var reportNo = $(tr).find("input[name='reportNo']").val();
                    var requestAmount = $(tr).find("input[name='requestAmount']").val();
                    var taxAmount = $(tr).find("input[name='taxAmount']").val();
                    var requestType = $(tr).find("input[name='requestType']").val();
                    var checkBodyId = $(tr).find("select[name='checkBody']").val();

                    var sqYjfyAmount = $(tr).find("input[name='sqYjfyAmount']").val();
                    var sqSjfyAmount = $(tr).find("input[name='sqSjfyAmount']").val();
                    var sqSjdyAmount = $(tr).find("input[name='sqSjdyAmount']").val();
                    if (isEmpty(requestAmount)) {
                        parent.msgAlert("正常请款房源：请输入报备编号" + reportNo + "的请款金额！");
                        flag = false;
                        return false;
                    }
                    if (isEmpty(taxAmount)) {
                        parent.msgAlert("正常请款房源：请输入报备编号" + reportNo + "的税额！");
                        flag = false;
                        return false;
                    }
                    if (isEmpty(checkBodyId)) {
                        parent.msgAlert("正常请款房源：请选择报备编号" + reportNo + "的考核主体！");
                        flag = false;
                        return false;
                    }

                    // 提交时校验每个报备请款金额应大于0，否则提示： 报备编号BB2018080600002请款金额应大于0！
                    if (Number(requestAmount) <= 0) {
                        parent.msgError("正常请款房源：报备编号" + reportNo + "请款金额应大于0！");
                        flag = false;
                        return false;
                    }
                    if (Number(taxAmount) < 0) {
                        parent.msgError("正常请款房源：报备编号" + reportNo + "税额应大于0！");
                        flag = false;
                        return false;
                    }

                    //提示 报备编号BB2018080600002不符合请款条件，请从请款列表中删除。
                    if (isEmpty(requestType) || requestType == 0) {
                        parent.msgError("正常请款房源：报备编号" + reportNo + "不符合请款条件，请从请款列表中删除。");
                        flag = false;
                        return false;
                    } else if (requestType == 1) {
                        // 提交时校验每个报备请款金额应小于等于应返金额，否则提示： 报备编号BB2018080600002请款金额不能大于应返金额！
                        if (Number(requestAmount) > Number(sqYjfyAmount)) {
                            parent.msgError("正常请款房源：报备编号" + reportNo + "请款金额不能大于应返金额！");
                            flag = false
                            return false;
                        }

                        // 提交时校验每个报备请款金额应小于等于【应返金额-实返金额】，否则提示： 报备编号BB2018080600002 请款金额不能大于应返金额与实返金额之差！
                        if (Number(requestAmount) > (Number(sqYjfyAmount) - Number(sqSjfyAmount)).toFixed(2)) {
                            parent.msgError("正常请款房源：报备编号" + reportNo + "请款金额不能大于应返金额与实返金额之差！");
                            flag = false;
                            return false;
                        }
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
                        parent.msgError("正常请款房源：报备编号" + reportNo + "税率没有落在税率区间内 请修改");
                        flag = false;
                        return false;
                    }

                });

                // 冲抵请款房源
                var tBodyOffSet = $(obj).find("div[lay-id='offsetBlock" + companyNo + "'] .layui-table-main tbody");
                $(tBodyOffSet).find("tr[tag!='sum']").each(function (i, tr) {
                    var reportNo = $(tr).find("input[name='reportNo']").val();
                    var requestAmount = $(tr).find("input[name='requestAmount']").val();
                    var taxAmount = $(tr).find("input[name='taxAmount']").val();
                    var requestType = $(tr).find("select[name='requestType']").val();

                    var accountProjectCode = $(tr).find("select[name='selAccountProject']").val();
                    var checkBodyId = $(tr).find("select[name='checkBody']").val();

                    var sqYjfyAmount = $(tr).find("input[name='sqYjfyAmount']").val();
                    var sqYjdyAmount = $(tr).find("input[name='sqYjdyAmount']").val();
                    var sqSjfyAmount = $(tr).find("input[name='sqSjfyAmount']").val();
                    var sqSjdyAmount = $(tr).find("input[name='sqSjdyAmount']").val();

                    if (isEmpty(requestAmount)) {
                        parent.msgAlert("冲抵请款房源：请输入报备编号" + reportNo + "的请款金额！");
                        flag = false;
                        return false;
                    }
                    if (isEmpty(taxAmount)) {
                        parent.msgAlert("冲抵请款房源：请输入报备编号" + reportNo + "的税额！");
                        flag = false;
                        return false;
                    }
                    if (isEmpty(accountProjectCode)) {
                        parent.msgAlert("冲抵请款房源：请选择报备编号" + reportNo + "的核算主体！");
                        flag = false;
                        return false;
                    }
                    if (isEmpty(checkBodyId)) {
                        parent.msgAlert("冲抵请款房源：请选择报备编号" + reportNo + "的考核主体！");
                        flag = false;
                        return false;
                    }

                    // 冲抵记录表提交时校验每个报备请款金额应小于0，否则提示： 报备编号BB2018080600002请款金额应小于0！
                    if (Number(requestAmount) >= 0) {
                        parent.msgError("冲抵请款房源：报备编号" + reportNo + "请款金额应小于0！");
                        flag = false;
                        return false;
                    }
                    if (Number(taxAmount) > 0) {
                        parent.msgError("冲抵请款房源：报备编号" + reportNo + "税额应小于0！");
                        flag = false;
                        return false;
                    }

                    if (isEmpty(requestType) || requestType == 0) {
                        parent.msgError("冲抵请款房源：报备编号" + reportNo + "请选择请款类型。");
                        flag = false;
                        return false;
                    } else if (requestType == 1) {
                        if (Math.abs(Number(requestAmount)) > (Number(sqSjfyAmount) - Number(sqSjdyAmount))) {
                            parent.msgError("冲抵请款房源：报备编号" + reportNo + "请款金额不能大于实返金额与实垫金额之差！请重新填写。");
                            flag = false;
                            return false;
                        }
                    } else if (requestType == 2) {
                        if (Number(sqSjdyAmount) == 0) {
                            parent.msgError("冲抵请款房源：报备编号" + reportNo + "实际垫佣金额为0，请重新选择请款方式或者删除此条记录。");
                            flag = false;
                            return false;
                        } else if (Number(sqSjdyAmount) > 0 && Math.abs(Number(requestAmount)) > Number(sqSjdyAmount)) {
                            parent.msgError("冲抵请款房源：报备编号" + reportNo + "请款金额不能大于实垫金额！请重新填写。");
                            flag = false;
                            return false;
                        }
                    }
                });

                // 经纪公司 " + companyInfo + " 含税请款金额总计应大于0，请重新填写！
                var requestAmountTotal = $(obj).find("input[name='requestAmountTotal']").val();
                if (isEmpty(requestAmountTotal) || Number(requestAmountTotal) <= 0) {
                    parent.msgAlert("经纪公司[" + companyName + "]含税请款金额总计应大于0，请重新填写！");
                    flag = false;
                    return false;
                }

            });

            if (!flag) return flag;

            // 成销确认书/佣金结算资料
            var fileSize = $(".upload_img_list .item_img").size()
            if (fileSize == null || fileSize <= 0) {
                parent.msgAlert("请上传[成销确认书/佣金结算资料]！")
                return false;
            }
        }
    }

    function saveToService(type) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneExpent/saveOACashBill";
        var param = getSavaData(type);
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(param),
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
                parent.layer.close(loadIndex);
                parent.msgAlert(data.returnMsg);
            }
        });

    }

    function getSavaData(type) {

        // 协议文件
        var fileIds = '';
        $("#uploadImg .item_img").each(function () {
            fileIds += $(this).data("id") + ',';
        });
        if (fileIds != '') {
            fileIds = fileIds.substring(0, fileIds.length - 1);
        }
        var fileRecordMainIds = fileIds;

        var param = {};
        param.submitStatus = type;
        param.fileRecordMainIds = fileRecordMainIds;

        param.id = $("#id").val();
        param.estateCityNo = $("#estateCityNo").val();
        param.cityNo = $("#cityNo").val();
        param.estateId = $("#estateId").val();
        param.estateNm = $("#estateNm").val();
        param.projectNo = $("#projectNo").val();
        param.isSpecialProject = $("#isSpecialProject").val();

        param.recordTime = $("#recordTime").val();
        param.predictPayTime = $("#predictPayTime").val();
        param.payType = $("#payType").val();

        var companyList = [];
        $("div[name='cardCompany']").each(function (i, cardCompany) {
            var companyInfo = {};
            companyInfo.id = $(cardCompany).find("input[name='comParentId']").val();
            companyInfo.companyId = $(cardCompany).find("input[name='companyId']").val();
            companyInfo.companyName = $(cardCompany).find("input[name='companyName']").val();
            companyInfo.accountProjectNo = $(cardCompany).find("select[name='accountProject']").val();
            companyInfo.accountProject = $(cardCompany).find("select[name='accountProject']").find("option:selected").attr("data-name");
            companyInfo.frameOaNo = $(cardCompany).find("input[name='frameOaNo']").attr("data-code");
            companyInfo.frameOaName = $(cardCompany).find("input[name='frameOaNo']").attr("data-name");
            companyInfo.vendorId = $(cardCompany).find("input[name='vendorId']").attr("data-id");
            companyInfo.vendorCode = $(cardCompany).find("input[name='vendorId']").attr("data-code");
            companyInfo.vendorName = $(cardCompany).find("input[name='vendorId']").val();
            companyInfo.receiveBankName = $(cardCompany).find("input[name='receiveBankName']").val();
            companyInfo.receiveBankAccountCardCode = $(cardCompany).find("input[name='receiveBankAccountCardCode']").val();
            companyInfo.receiveBankAccountName = $(cardCompany).find("input[name='receiveBankAccountName']").val();
            companyInfo.receiveBankProvinceName = $(cardCompany).find("input[name='receiveBankProvinceName']").val();
            companyInfo.receiveBankCityName = $(cardCompany).find("input[name='receiveBankCityName']").val();
            companyInfo.receiveBankSerialNo = $(cardCompany).find("input[name='receiveBankSerialNo']").val();
            companyInfo.offSetFlag = parseInt($(cardCompany).find("input[tag='offSetFlag']:checked").val());
            companyInfo.remarks = $(cardCompany).find("textarea[name='remarks']").val();

            var reportList = [];
            var tbodyNoraml = $(cardCompany).find("div[tag='normal']").find(".layui-table-main tbody");
            $(tbodyNoraml).find("tr[tag!='sum']").each(function (i, tr) {
                var reportRecord = {};
                reportRecord.reportId = $(tr).find("input[name='reportId']").val();
                reportRecord.reportDetailId = $(tr).find("input[name='reportDetailId']").val();
                reportRecord.reportNo = $(tr).find("input[name='reportNo']").val();
                reportRecord.id = $(tr).find("input[name='id']").val();
                reportRecord.buildingNo = $(tr).find("input[name='buildingNo']").val();
                reportRecord.customerNm = $(tr).find("input[name='customerNm']").val();
                reportRecord.area = $(tr).find("input[name='area']").val();
                reportRecord.roughAmount = $(tr).find("input[name='roughAmount']").val();
                reportRecord.dealAmount = $(tr).find("input[name='dealAmount']").val();
                reportRecord.sqYjsrAmount = $(tr).find("input[name='sqYjsrAmount']").val();
                reportRecord.sqYjfyAmount = $(tr).find("input[name='sqYjfyAmount']").val();
                reportRecord.sqYjdyAmount = $(tr).find("input[name='sqYjdyAmount']").val();
                reportRecord.sqSjsrAmount = $(tr).find("input[name='sqSjsrAmount']").val();
                reportRecord.sqSjfyAmount = $(tr).find("input[name='sqSjfyAmount']").val();
                reportRecord.sqSjdyAmount = $(tr).find("input[name='sqSjdyAmount']").val();
                reportRecord.requestType = $(tr).find("input[name='requestType']").val();

                reportRecord.requestAmount = $(tr).find("input[name='requestAmount']").val();
                reportRecord.taxAmount = $(tr).find("input[name='taxAmount']").val();
                reportRecord.checkBodyName = $(tr).find("select[name='checkBody']").find("option:selected").text();
                reportRecord.checkBodyId = $(tr).find("select[name='checkBody']").val();
                reportRecord.memo = $(tr).find("input[name='memo']").val();

                reportList.push(reportRecord);
            });
            companyInfo.reportList = reportList;

            var offSetList = [];
            var tbodyOffSet = $(cardCompany).find("div[tag='offSet']").find(".layui-table-main tbody");
            $(tbodyOffSet).find("tr[tag!='sum']").each(function (i, tr) {
                var offSetRecord = {};
                offSetRecord.reportId = $(tr).find("input[name='reportId']").val();
                offSetRecord.reportDetailId = $(tr).find("input[name='reportDetailId']").val();
                offSetRecord.reportNo = $(tr).find("input[name='reportNo']").val();
                var repId = $(tr).find("input[name='id']").val()
                offSetRecord.id = isEmpty(repId) ? null : repId;
                offSetRecord.buildingNo = $(tr).find("input[name='buildingNo']").val();
                offSetRecord.customerNm = $(tr).find("input[name='customerNm']").val();
                offSetRecord.area = $(tr).find("input[name='area']").val();
                offSetRecord.roughAmount = $(tr).find("input[name='roughAmount']").val();
                offSetRecord.dealAmount = $(tr).find("input[name='dealAmount']").val();
                offSetRecord.sqYjsrAmount = $(tr).find("input[name='sqYjsrAmount']").val();
                offSetRecord.sqYjfyAmount = $(tr).find("input[name='sqYjfyAmount']").val();
                offSetRecord.sqYjdyAmount = $(tr).find("input[name='sqYjdyAmount']").val();
                offSetRecord.sqSjsrAmount = $(tr).find("input[name='sqSjsrAmount']").val();
                offSetRecord.sqSjfyAmount = $(tr).find("input[name='sqSjfyAmount']").val();
                offSetRecord.sqSjdyAmount = $(tr).find("input[name='sqSjdyAmount']").val();

                offSetRecord.requestAmount = $(tr).find("input[name='requestAmount']").val();
                offSetRecord.taxAmount = $(tr).find("input[name='taxAmount']").val();
                offSetRecord.requestType = $(tr).find("select[name='requestType']").val();

                offSetRecord.accountProjectNo = $(tr).find("select[name='selAccountProject']").val();
                offSetRecord.accountProject = $(tr).find("select[name='selAccountProject']").find("option:selected").attr("data-name");
                offSetRecord.checkBodyName = $(tr).find("select[name='checkBody']").find("option:selected").text();
                offSetRecord.checkBodyId = $(tr).find("select[name='checkBody']").val();
                offSetRecord.memo = $(tr).find("input[name='memo']").val();

                offSetList.push(offSetRecord)
            });
            companyInfo.offSetList = offSetList;

            companyInfo.areaTotal = $(cardCompany).find("input[name='areaTotal']").val();
            companyInfo.roughAmountTotal = $(cardCompany).find("input[name='roughAmountTotal']").val();
            companyInfo.dealAmountTotal = $(cardCompany).find("input[name='dealAmountTotal']").val();
            companyInfo.sqYjsrAmountTotal = $(cardCompany).find("input[name='sqYjsrAmountTotal']").val();
            companyInfo.sqYjfyAmountTotal = $(cardCompany).find("input[name='sqYjfyAmountTotal']").val();
            companyInfo.sqYjdyAmountTotal = $(cardCompany).find("input[name='sqYjdyAmountTotal']").val();
            companyInfo.sqSjsrAmountTotal = $(cardCompany).find("input[name='sqSjsrAmountTotal']").val();
            companyInfo.sqSjfyAmountTotal = $(cardCompany).find("input[name='sqSjfyAmountTotal']").val();
            companyInfo.sqSjdyAmountTotal = $(cardCompany).find("input[name='sqSjdyAmountTotal']").val();
            companyInfo.requestAmountTotal = $(cardCompany).find("input[name='requestAmountTotal']").val();
            companyInfo.taxAmountTotal = $(cardCompany).find("input[name='taxAmountTotal']").val();
            companyList.push(companyInfo);
        });
        param.companyList = companyList;

        return param;
    }
});

batchExpent = {}

batchExpent.changeAccountProject = function (obj) {

    var divCard = $(obj).parents("div[class='layui-card']");
    var accountProjectNo = $(divCard).find("select[name='accountProject']").val();
    $(divCard).find("input[name='frameOaNo']").val('');
    $(divCard).find("input[name='frameOaNo']").attr("data-name", "");
    $(divCard).find("input[name='frameOaNo']").attr("data-code", "");

    $(divCard).find("input[name='vendorId']").val('');
    $(divCard).find("input[name='vendorId']").attr("data-id", "");
    $(divCard).find("input[name='vendorId']").attr("data-code", "");

    $(divCard).find("input[name='receiveBankName']").val('');
    $(divCard).find("input[name='receiveBankAccountCardCode']").val('');
    $(divCard).find("input[name='receiveBankAccountName']").val('');

    $(divCard).find("input[name='receiveBankProvinceName']").val('');
    $(divCard).find("input[name='receiveBankCityName']").val('');
    $(divCard).find("input[name='receiveBankSerialNo']").val('');
    $(divCard).find("input[name='receiveBankProvinceCityName']").val('');

    $(divCard).find("div[tag='normal'] select[name='checkBody']").html('<option value="">请选择</option>');

    var url = BASE_PATH + "/sceneExpent/getOACheckBodyList";
    if (!isEmpty(accountProjectNo)) {
        var params = {accountProjectCode: accountProjectNo};
        ajaxGet(url, params, function (data) {
            var dataLength = data.returnData.length;
            var result = '<option value="">请选择</option>';
            $.each(data.returnData, function (n, value) {
                if (dataLength > 1) {
                    result += '<option value="' + value.checkBodyId + '">' + value.checkBodyName + '</option>';
                }
                if (dataLength > 0 && dataLength < 2) {
                    result += '<option value="' + value.checkBodyId + '" selected="selected" >' + value.checkBodyName + '</option>';
                }
            });

            $(divCard).find("div[tag='normal'] select[name='checkBody']").empty().append(result);
            $(divCard).find("div[tag='normal'] select[name='checkBody']").change();

        }, function (data) {
            parent.msgAlert(data.returnMsg)
        });
    }

}
batchExpent.getFrmAgreement = function (obj) {
    var divCard = $(obj).parents("div[class='layui-card']");
    var accountProjectNo = $(divCard).find("select[name='accountProject']").val();
    if (isEmpty(accountProjectNo)) {
        parent.msgAlert("请选择核算主体!");
        return false;
    }

    parent.layer.open({
        type: 2,
        title: '选择合同',
        area: ['820px', '660px'],
        content: BASE_PATH + '/sceneExpent/getOAFrmAgreement/' + accountProjectNo
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                var w_frameOaNo = $(divCard).find("input[name='frameOaNo']").attr("data-code");
                var w_vendorId = $(divCard).find("input[name='vendorId']").attr("data-id");

                if (w_frameOaNo != formData.frameOaNo) {
                    $(divCard).find("input[name='frameOaNo']").val(NullToEmpty(formData.frameOaNo));
                    $(divCard).find("input[name='frameOaNo']").attr("data-code", NullToEmpty(formData.frameOaNo));
                    $(divCard).find("input[name='frameOaNo']").attr("data-name", NullToEmpty(formData.frameOaName));
                    $(divCard).find("input[name='vendorId']").val(NullToEmpty(formData.vendorName));
                    $(divCard).find("input[name='vendorId']").attr("data-id", NullToEmpty(formData.vendorId));
                    $(divCard).find("input[name='vendorId']").attr("data-code", NullToEmpty(formData.vendorCode));
                }

                if (w_vendorId != formData.vendorId) {
                    $(divCard).find("input[name='receiveBankName']").val('');
                    $(divCard).find("input[name='receiveBankAccountCardCode']").val('');
                    $(divCard).find("input[name='receiveBankAccountName']").val('');
                    $(divCard).find("input[name='receiveBankProvinceName']").val('');
                    $(divCard).find("input[name='receiveBankCityName']").val('');
                    $(divCard).find("input[name='receiveBankSerialNo']").val('');
                    $(divCard).find("input[name='receiveBankProvinceCityName']").val('');
                }
            }
            parent.layer.close(index);
        }
    });

}
batchExpent.getReceiveBank = function (obj) {
    var divCard = $(obj).parents("div[class='layui-card']");
    var accountProjectNo = $(divCard).find("select[name='accountProject']").val();
    if (isEmpty(accountProjectNo)) {
        parent.msgAlert("请选择核算主体!");
        return false;
    }

    var frameOaNo = $(divCard).find("input[name='frameOaNo']").attr("data-code");
    var vendorId = $(divCard).find("input[name='vendorId']").attr("data-id");

    if (isEmpty(frameOaNo) || isEmpty(vendorId)) {
        parent.msgAlert("请选择合同");
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
                $(divCard).find("input[name='receiveBankName']").val(NullToEmpty(formData.bankName));
                $(divCard).find("input[name='receiveBankAccountCardCode']").val(NullToEmpty(formData.bankAccountCardCode));
                $(divCard).find("input[name='receiveBankAccountName']").val(NullToEmpty(formData.bankAccountName));
                $(divCard).find("input[name='receiveBankProvinceName']").val(NullToEmpty(formData.provinceName));
                $(divCard).find("input[name='receiveBankCityName']").val(NullToEmpty(formData.cityName));
                $(divCard).find("input[name='receiveBankSerialNo']").val(NullToEmpty(formData.serialNo));
                $(divCard).find("input[name='receiveBankProvinceCityName']").val(NullToEmpty(formData.provinceName) + ' ' + NullToEmpty(formData.cityName));
            }
            parent.layer.close(index);
        }
    });
}
batchExpent.delete = function (obj, repRowId, comRowId, proRowId, companyNo) {

    parent.msgConfirm("确认要删除吗？", function () {
        var url = BASE_PATH + "/sceneExpent/removeFromCashBill";
        var params = {
            repRowId: repRowId,
            comRowId: comRowId,
            proRowId: proRowId
        };
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        restPost(url, params, function (data) {
            parent.layer.close(loadIndex);
            parent.msgProp(data.returnMsg);

            // tr =》body 部分
            var index = $(obj).parents("tr").attr("data-index");

            var leftTBody = $("div[lay-id='content" + companyNo + "']   .layui-table-fixed-l .layui-table-body tbody");
            var mainTBody = $("div[lay-id='content" + companyNo + "']   .layui-table-main tbody");
            var rightTBody = $("div[lay-id='content" + companyNo + "']  .layui-table-fixed-r .layui-table-body tbody");

            $(leftTBody).find("tr[data-index='" + index + "']").empty().remove();
            $(mainTBody).find("tr[data-index='" + index + "']").empty().remove();
            $(rightTBody).find("tr[data-index='" + index + "']").empty().remove();

            // tbody=>company部分
            if ($(mainTBody).find("tr[tag!='sum']").length == 0) {
                $("#divCard" + companyNo).remove();
            }
            if ($("#companyInfoList").find("div[name='cardCompany']").length == 0) {
                parent.redirectTo('delete', null, null);
            }

            evalNormalTotal(companyNo);

        }, function (data) {
            parent.layer.close(loadIndex);
            parent.msgError(data.returnMsg);
        });
    });
}
batchExpent.evalNormalItem = function (tableId, itemName) {
    var itemTotal = 0.00;

    $("div[lay-id='content" + tableId + "'] .layui-table-main tbody").find("input[name='" + itemName + "']").each(function () {
        var value = $(this).val();
        itemTotal = itemTotal + formatAccount(value);
    });

    $("div[lay-id='content" + tableId + "']  .layui-table-main tbody").find("span[name='" + itemName + "']").html(formatCurrency(itemTotal));
    $("div[lay-id='content" + tableId + "']  .layui-table-main tbody").find("input[name='" + itemName + "Sum']").val(itemTotal);

    batchExpent.evalAllItem(tableId, itemName);
}
batchExpent.evalOffSetItem = function (tableId, itemName) {
    var itemTotal = 0.00;

    $("div[lay-id='offsetBlock" + tableId + "'] .layui-table-main tbody").find("input[name='" + itemName + "']").each(function () {
        var value = formatAccount($(this).val());
        if ((itemName == "requestAmount" || itemName == "taxAmount") && value > 0) {
            value = -1 * value
        }
        itemTotal = itemTotal + value;
    });

    $("div[lay-id='offsetBlock" + tableId + "']  .layui-table-main tbody").find("span[name='" + itemName + "']").html(formatCurrency(itemTotal));
    $("div[lay-id='offsetBlock" + tableId + "']  .layui-table-main tbody").find("input[name='" + itemName + "Sum']").val(itemTotal);

    batchExpent.evalAllItem(tableId, itemName);
}
batchExpent.evalAllItem = function (tableId, itemName) {
    var itemTotal = 0.00;

    $("#divCard" + tableId).find("input[name='" + itemName + "Sum']").each(function () {
        var value = $(this).val();
        itemTotal = itemTotal + formatAccount(value);
    });

    $("#divCard" + tableId).find("input[name='" + itemName + "Total']").val(itemTotal);

    if (itemName == "requestAmount" || itemName == "taxAmount") {
        if (itemName == "requestAmount") {
            $("#divCard" + tableId).find("span[tag='requestAmountAll']").html(formatCurrency(itemTotal));
        }
        if (itemName == "taxAmount") {
            $("#divCard" + tableId).find("span[tag='taxAmountAll']").html(formatCurrency(itemTotal));
        }

        var requestAmountTotal = $("#divCard" + tableId).find("input[name='requestAmountTotal']").val()
        var taxAmountTotal = $("#divCard" + tableId).find("input[name='taxAmountTotal']").val()
        var requestAmountNoTaxAll = formatAccount(requestAmountTotal) - formatAccount(taxAmountTotal);

        $("#divCard" + tableId).find("span[tag='requestAmountNoTaxAll']").html(formatCurrency(requestAmountNoTaxAll));
    }

}
batchExpent.evalRequestType = function (obj) {
    var tr = $(obj).parents("tr");
    batchExpent.evalRequestTypeByTr(tr);
}
batchExpent.evalRequestTypeByTr = function (tr) {
    var progress = $(tr).find("input[name='progress']").val(); // 当前报备流程
    var confirmStatus = $(tr).find("input[name='confirmStatus']").val(); // 当前报备状态

    if (progress != 13505 && (confirmStatus != 13601 || confirmStatus != 13603)) {
        batchExpent.setRequestType(0);
        return;
    }

    // 请款类别：
    var requestAmount = $(tr).find("input[name='requestAmount']").val(); // 请款金额

    // 没有请款的场合
    if (requestAmount == "" || Number(requestAmount) == 0) {
        batchExpent.setRequestType(tr, 0);
        return;
    }

    // 如果是特殊项目且当前状态是大定的时候，默认为垫佣
    var isSpecialProject = $("#isSpecialProject").val();
    if ((isSpecialProject == 1 || isSpecialProject == 2) && progress == 13505 && confirmStatus == 13603) {
        batchExpent.setRequestType(tr, 2);
        return;
    }


    var sqYjsrAmount = $(tr).find("input[name='sqYjsrAmount']").val();  // 应计收入
    var sqYjfyAmount = $(tr).find("input[name='sqYjfyAmount']").val();  // 应计返佣
    var sqYjdyAmount = $(tr).find("input[name='sqYjdyAmount']").val();  // 应计垫佣

    var sqSjsrAmount = $(tr).find("input[name='sqSjsrAmount']").val();  // 实收收入
    var sqSjfyAmount = $(tr).find("input[name='sqSjfyAmount']").val();  // 实际返佣
    var sqSjdyAmount = $(tr).find("input[name='sqSjdyAmount']").val();  // 实际垫佣

    //    应计收入   应计返佣   应计垫佣     结果
    // 1.  ╳         ╳         ╳           -
    // 2.  ╳         ╳         ╳           -
    // 3.  ╳         √         ╳           -
    // 4.  ╳         √         √           -
    // 5.  √         ╳         ╳           -
    // 6.  √         ╳         √           -
    // 7.  √         √         ╳           返佣
    // 8.  √         √         √           正常

    // 返佣：请款金额≤实收收入-实际返佣
    if (Number(requestAmount) <= (Number(sqSjsrAmount) - Number(sqSjfyAmount)).toFixed(2)) {
        // 场景7 和场景8 的情况
        if (Number(sqYjsrAmount) > 0 && Number(sqYjfyAmount) > 0) {
            batchExpent.setRequestType(tr, 1);
        } else {
            batchExpent.setRequestType(tr, 0);
        }
    }
    // 垫佣：请款金额≤应计垫佣-实际垫佣
    else if (Number(requestAmount) <= (Number(sqYjdyAmount) - Number(sqSjdyAmount)).toFixed(2)) {
        if (Number(sqYjdyAmount) > 0 &&
            // (Number(sqYjsrAmount)<=0 && Number(sqYjfyAmount)<=0)   ||        // 场景2
            (Number(sqYjsrAmount) > 0 && Number(sqYjfyAmount) > 0)      // 场景8
        ) {
            batchExpent.setRequestType(tr, 2);
        } else {
            batchExpent.setRequestType(tr, 0);
        }
    } else {
        batchExpent.setRequestType(tr, 0);
    }

}
batchExpent.setRequestType = function (tr, val) {
    if (val == 1) {
        $(tr).find("span[name='requestType']").text("返佣");
        $(tr).find("input[name='requestType']").val("1");
    } else if (val == 2) {
        $(tr).find("span[name='requestType']").text("垫佣");
        $(tr).find("input[name='requestType']").val("2");
    } else {
        $(tr).find("span[name='requestType']").text("-");
        $(tr).find("input[name='requestType']").val("0");
    }
}

batchExpent.offSetFlagChange = function (obj, companyNo) {
    var divCard = $(obj).parents("div[class='layui-card']");
    var offSetValue = $(obj).val();
    var offSetDiv = $(divCard).find("div[tag='offSet']");

    var leftTBody = $(divCard).find("div[lay-id='offsetBlock" + companyNo + "'] .layui-table-fixed-l .layui-table-body tbody");
    var mainTBody = $(divCard).find("div[lay-id='offsetBlock" + companyNo + "']  .layui-table-main tbody");
    var rightTBody = $(divCard).find("div[lay-id='offsetBlock" + companyNo + "']  .layui-table-fixed-r .layui-table-body tbody");

    if (offSetValue == 0) {
        if ($(mainTBody).find("tr[tag!='sum']").length == 0) {
            $(offSetDiv).hide();
        } else {
            parent.msgConfirm("冲抵房源数据已添加，是否确认清空？", function () {

                $(offSetDiv).find("tr[tag!='sum']").each(function () {
                    var repRowId = $(this).find("input[name='id']").val();
                    var comRowId = $(this).find("input[name='comParentId']").val();
                    var proRowId = $(this).find("input[name='proParentId']").val();
                    var index = $(this).attr("data-index");

                    ajaxGet(BASE_PATH + "/cashBill/removeFromCashBill", {
                        "repRowId": repRowId,
                        "comRowId": comRowId,
                        "proRowId": proRowId
                    }, function (data) {

                        $(leftTBody).find("tr[data-index='" + index + "']").empty().remove();
                        $(mainTBody).find("tr[data-index='" + index + "']").empty().remove();
                        $(rightTBody).find("tr[data-index='" + index + "']").empty().remove();

                    });
                })

                $(offSetDiv).hide();

            }, function () {
                $(divCard).find('input[tag="offSetFlag"]').removeAttr("checked")
                $(divCard).find('input[tag="offSetFlag"]:eq(0)').prop('checked', 'checked');
            });

        }
    } else {
        $(offSetDiv).show();
    }
}
batchExpent.addOffSetInfo = function (obj, companyNo, tableIndex) {

    var leftTable = $("div[lay-id='offsetBlock" + companyNo + "'] .layui-table-fixed-l ");
    var mainTable = $("div[lay-id='offsetBlock" + companyNo + "'] .layui-table-main ");
    var rightTable = $("div[lay-id='offsetBlock" + companyNo + "'] .layui-table-fixed-r ");

    var rowIndex = $(mainTable).find("tr").length;

    var content = $("div[lay-id='content" + companyNo + "'] .layui-table-main ");

    var reportNoList = $(content).find("input[name='reportNo']").map(function () {
        return $(this).val();
    }).get().join(",");

    parent.layer.open({
        type: 2,
        title: '选择冲抵房源',
        area: ['820px', '660px'],
        content: BASE_PATH + '/sceneExpent/getOffsetInfo?companyNo=' + escape(companyNo) + "&reportNoList=" + escape(reportNoList)
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                var reportNo = NullToEmpty(formData.reportNo);

                var line = 0;
                $(mainTable).find("input[name='reportNo']").each(function (i, obj) {
                    if (reportNo == $(obj).val()) {
                        line++;
                    }
                });
                if (line >= 2) {
                    parent.msgAlert("同一报备最多只能添加两次冲抵");
                    return;
                }

                var projectNo = NullToEmpty(formData.projectNo);

                var buildingNo = NullToEmpty(formData.buildingNo);

                var reportId = NullToEmpty(formData.reportId);
                var reportDetailId = NullToEmpty(formData.reportDetailId);
                var customerNm = NullToEmpty(formData.customerNm);
                var area = NullToEmpty(formData.area);
                var roughAmount = NullToEmpty(formData.roughAmount);
                var dealAmount = NullToEmpty(formData.dealAmount);
                var sqYjsrAmount = NullToEmpty(formData.sqYjsrAmount);
                var sqYjfyAmount = NullToEmpty(formData.sqYjfyAmount);
                var sqYjdyAmount = NullToEmpty(formData.sqYjdyAmount);
                var sqSjsrAmount = NullToEmpty(formData.sqSjsrAmount);
                var sqSjfyAmount = NullToEmpty(formData.sqSjfyAmount);
                var sqSjdyAmount = NullToEmpty(formData.sqSjdyAmount);
                var requestType = NullToEmpty(formData.requestType);
                var progress = NullToEmpty(formData.progress);
                var confirmStatus = NullToEmpty(formData.confirmStatus);
                var accountProjectNo = NullToEmpty(formData.accountProjectNo);
                var accountProject = NullToEmpty(formData.accountProject);
                var checkBodyId = NullToEmpty(formData.checkBodyId);

                var requestAmount = isEmpty(formData.requestAmount) ? "" : formatMoney(formData.requestAmount);
                var taxAmount = isEmpty(formData.taxAmount) ? "" : formatMoney(formData.taxAmount);


                var fixTrStr = '';
                fixTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
                fixTrStr += '<td ><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-0 laytable-cell-numbers">' + rowIndex + '</div></td>';
                fixTrStr += '<td ><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-1">' + reportNo + '</div></td>';
                fixTrStr += '<td ><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-2">' + buildingNo + '</div></td>';
                fixTrStr += '</tr>';

                var contentTrStr = '';
                contentTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-0 laytable-cell-numbers">' + rowIndex + '</div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-1">' + reportNo + '</div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-2">' + buildingNo + '</div></td>';
                contentTrStr += '<td align="center" ><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-3">';
                contentTrStr += '<input type="hidden" name="reportId" value="' + reportId + '">';
                contentTrStr += '<input type="hidden" name="reportDetailId" value="' + reportDetailId + '">';
                contentTrStr += '<input type="hidden" name="reportNo" value="' + reportNo + '">';
                contentTrStr += '<input type="hidden" name="buildingNo" value="' + buildingNo + '">';
                contentTrStr += '<input type="hidden" name="customerNm" value="' + customerNm + '">';
                contentTrStr += '<input type="hidden" name="area" value="' + area + '">';
                contentTrStr += '<input type="hidden" name="roughAmount" value="' + roughAmount + '">';
                contentTrStr += '<input type="hidden" name="dealAmount" value="' + dealAmount + '">';
                contentTrStr += '<input type="hidden" name="sqYjsrAmount" value="' + sqYjsrAmount + '">';
                contentTrStr += '<input type="hidden" name="sqYjfyAmount" value="' + sqYjfyAmount + '">';
                contentTrStr += '<input type="hidden" name="sqYjdyAmount" value="' + sqYjdyAmount + '">';
                contentTrStr += '<input type="hidden" name="sqSjsrAmount" value="' + sqSjsrAmount + '">';
                contentTrStr += '<input type="hidden" name="sqSjfyAmount" value="' + sqSjfyAmount + '">';
                contentTrStr += '<input type="hidden" name="sqSjdyAmount" value="' + sqSjdyAmount + '">';
                contentTrStr += '<input type="hidden" name="requestType" value="' + requestType + '">';

                contentTrStr += '<input type="hidden" name="progress" value="' + progress + '">';
                contentTrStr += '<input type="hidden" name="confirmStatus" value="' + confirmStatus + '">';
                contentTrStr += '<span>' + customerNm + '</span>'

                contentTrStr += '</div></td>';
                contentTrStr += '<td data-field="area" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-4">' + formatMoney(area) + '</div></td>';
                contentTrStr += '<td data-field="roughAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-5">' + formatMoney(roughAmount) + '</div></td>';
                contentTrStr += '<td data-field="dealAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-6">' + formatMoney(dealAmount) + '</div></td>';

                contentTrStr += '<td data-field="sqYjsrAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-0">' + formatMoney(sqYjsrAmount) + '</div></td>';
                contentTrStr += '<td data-field="sqYjfyAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-1">' + formatMoney(sqYjfyAmount) + '</div></td>';
                contentTrStr += '<td data-field="sqSjsrAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-2">' + formatMoney(sqYjdyAmount) + '</div></td>';
                contentTrStr += '<td data-field="sqSjsrAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-3">' + formatMoney(sqSjsrAmount) + '</div></td>';
                contentTrStr += '<td data-field="sqSjfyAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-4">' + formatMoney(sqSjfyAmount) + '</div></td>';
                contentTrStr += '<td data-field="sqSjfyAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-1-5">' + formatMoney(sqSjdyAmount) + '</div></td>';

                contentTrStr += '<td data-field="requestAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-8">';
                contentTrStr += '<input type="text" name="requestAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchExpent.evalOffSetItem(\'' + companyNo + '\',\'requestAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:batchExpent.formatter(this,1)" class="renderInput mr" value="' + requestAmount + '">'
                contentTrStr += '</div></td>';
                contentTrStr += '<td data-field="taxAmount" align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-9">';
                contentTrStr += '<input type="text" name="taxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchExpent.evalOffSetItem(\'' + companyNo + '\',\'taxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:batchExpent.formatter(this,1)" class="renderInput mr" value="' + taxAmount + '">'
                contentTrStr += '</div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-10">';
                contentTrStr += ' <select  name ="requestType" lay-ignore class="renderInput">"';
                contentTrStr += '   <option value="0">请选择</option>';
                contentTrStr += '   <option value="1">返佣</option>';
                contentTrStr += '   <option value="2">垫佣</option>';
                contentTrStr += ' </select>';
                contentTrStr += '</div></td>';
                // 核算主体
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-11">';
                contentTrStr += '     <select  name="selAccountProject" onchange="javascript:batchExpent.changeOffSetAccountProject(this)" lay-ignore class="renderInput">';
                if (progress == '13505' && confirmStatus == '13601') {
                    contentTrStr += '     <option value="' + accountProjectNo + '" data-name="' + accountProject + '">' + accountProjectNo + "_" + accountProject + '</option>';
                } else {
                    contentTrStr += batchExpent.getOALnkAccountProjectSelectOption(projectNo, accountProjectNo)
                }
                contentTrStr += '     </select>';
                contentTrStr += '</div></td>';
                // 考核主体
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-12">';
                contentTrStr += '     <select name="checkBody" lay-ignore class="renderInput">';
                contentTrStr += batchExpent.getOACheckBodySelectOption(accountProjectNo, checkBodyId);
                contentTrStr += '     </select>';
                contentTrStr += '</div></td>';

                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-13">';
                contentTrStr += '   <input type="text" name="memo" maxlength="200" autocomplete="off"  class="renderInput ml" value="">'
                contentTrStr += '</div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-14"></div></td>';

                contentTrStr += '</tr>';

                var rightTrStr = '';
                rightTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
                rightTrStr += '<td ><div class="layui-table-cell laytable-cell-' + tableIndex + '-0-14">'
                rightTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="javascript:batchExpent.deleteLocalOffSet(this,\'' + companyNo + '\')">删除</a></div></td>';

                rightTrStr += '</tr>';

                $(leftTable).find("tr[tag='sum']").before(fixTrStr);
                $(mainTable).find("tr[tag='sum']").before(contentTrStr);
                $(rightTable).find("tr[tag='sum']").before(rightTrStr);

                evalOffSetTotal(companyNo)
            }
            parent.layer.close(index);
        }
    });
}
batchExpent.deleteOffSet = function (obj, repRowId, comRowId, proRowId, companyNo) {

    parent.msgConfirm("确认要删除吗？", function () {
        var url = BASE_PATH + "/sceneExpent/removeFromCashBill";
        var params = {
            repRowId: repRowId,
            comRowId: comRowId,
            proRowId: proRowId
        };
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        restPost(url, params, function (data) {
            parent.layer.close(loadIndex);
            parent.msgProp(data.returnMsg);

            // tr =》body 部分
            var index = $(obj).parents("tr").attr("data-index");

            var leftTBody = $("div[lay-id='offsetBlock" + companyNo + "']  .layui-table-fixed-l .layui-table-body tbody");
            var mainTBody = $("div[lay-id='offsetBlock" + companyNo + "']  .layui-table-main tbody");
            var rightTBody = $("div[lay-id='offsetBlock" + companyNo + "'] .layui-table-fixed-r .layui-table-body tbody");

            $(leftTBody).find("tr[data-index='" + index + "']").empty().remove();
            $(mainTBody).find("tr[data-index='" + index + "']").empty().remove();
            $(rightTBody).find("tr[data-index='" + index + "']").empty().remove();

            evalOffSetTotal(companyNo)

        }, function (data) {
            parent.layer.close(loadIndex);
            parent.msgError(data.returnMsg);
        });
    });

}

batchExpent.deleteLocalOffSet = function (obj, companyNo) {

    // tr =》body 部分
    var index = $(obj).parents("tr").attr("data-index");

    var leftTBody = $("div[lay-id='offsetBlock" + companyNo + "']  .layui-table-fixed-l .layui-table-body tbody");
    var mainTBody = $("div[lay-id='offsetBlock" + companyNo + "']  .layui-table-main tbody");
    var rightTBody = $("div[lay-id='offsetBlock" + companyNo + "'] .layui-table-fixed-r .layui-table-body tbody");

    $(leftTBody).find("tr[data-index='" + index + "']").empty().remove();
    $(mainTBody).find("tr[data-index='" + index + "']").empty().remove();
    $(rightTBody).find("tr[data-index='" + index + "']").empty().remove();

    evalOffSetTotal(companyNo)
}

batchExpent.changeOffSetAccountProject = function (obj) {

    var accountProjectNo = $(obj).val();
    var tr = $(obj).parents("tr");
    var options = "";
    if (!isEmpty(accountProjectNo)) {
        options = batchExpent.getOACheckBodySelectOption(accountProjectNo, '');
    }
    $(tr).find("select[name='checkBody']").empty().html(options);

}
batchExpent.formatter = function (o, offset) {
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

batchExpent.getOALnkAccountProjectSelectOption = function (projectNo, accountProjectCode) {
    var url = BASE_PATH + "/sceneExpent/getOALnkAccountProjectList";
    var result = "<option value=''>请选择</option>";
    if (!isEmpty(projectNo)) {
        var params = {projectNo: projectNo};
        $.ajax({
            url: url,
            data: params,
            async: false,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                $.each(data.returnData, function (n, value) {
                    var xaccountProjectCode = NullToEmpty(value.lnkaccountProjectCode);
                    var xaccountProjectName = NullToEmpty(value.lnkAccountProject);

                    result += "<option ";
                    if (data.returnData.length == 1 || accountProjectCode == xaccountProjectCode) {
                        result += " selected ";
                    }
                    result += "value='" + xaccountProjectCode + "' data-name='" + xaccountProjectName + "'>" + xaccountProjectCode + "_" + xaccountProjectName + "</option>";
                });
            }
        });
    }

    return result;
}
batchExpent.getOACheckBodySelectOption = function (accountProjectNo, checkBodyId) {
    var url = BASE_PATH + "/sceneExpent/getOACheckBodyList";
    var result = "<option value=''>请选择</option>";
    if (!isEmpty(accountProjectNo)) {
        var params = {accountProjectCode: accountProjectNo};
        $.ajax({
            url: url,
            data: params,
            async: false,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                $.each(data.returnData, function (n, value) {
                    var xcheckBodyId = NullToEmpty(value.checkBodyId);
                    var xcheckBodyName = NullToEmpty(value.checkBodyName);

                    result += "<option";
                    if (data.returnData.length == 1 || checkBodyId == xcheckBodyId) {
                        result += " selected ";
                    }
                    result += " value='" + xcheckBodyId + "'>" + xcheckBodyName + "</option>";
                });
            }
        });
    }

    return result;
}


