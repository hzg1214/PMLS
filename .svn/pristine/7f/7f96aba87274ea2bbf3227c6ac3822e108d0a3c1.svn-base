var layer;
var form;
layui.use(['element', 'table', 'layer'], function () {
        var element = layui.element,
            table = layui.table,
            upload = layui.upload,
            $ = layui.$;
        layer = layui.layer;
        form = layui.form;

        var id = $("#id").val();

        var uploadSuccSaleImg = [], uploadProjectContractImg = [], uploadBranchContractImg = [], uploadOtherImg = [];
        if (fileListInfo != null && fileListInfo.length > 0) {
            for (var i = 0; i < fileListInfo.length; i++) {
                var fileInfo = fileListInfo[i];
                if (fileInfo.fileTypeId == '1101') {
                    uploadSuccSaleImg.push(fileInfo);
                } else if (fileInfo.fileTypeId == '1102') {
                    uploadProjectContractImg.push(fileInfo);
                } else if (fileInfo.fileTypeId == '1103') {
                    uploadBranchContractImg.push(fileInfo);
                } else if (fileInfo.fileTypeId == '1104') {
                    uploadOtherImg.push(fileInfo);
                }
            }
        }

        init();

        function init() {

            loadImageList("uploadSuccSaleImg", uploadSuccSaleImg, true);
            loadImageList("uploadProjectContractImg", uploadProjectContractImg, true)
            loadImageList("uploadBranchContractImg", uploadBranchContractImg, true)
            loadImageList("uploadOtherImg", uploadOtherImg, true)

            form.render("radio");
            form.render('select'); // 刷新单选框渲染

            form.render();


            if (isEmpty(id)) {
                tableRender([]);
                offSetRender([]);
            } else {
                getJsStatementDtl(id);
            }
            form.on('radio(offSetFlag)', function (data) {
                addJsStatement.offSetFlagChange(data.value);
            });

            form.on('radio(kpRate)', function (data) {
                addJsStatement.kpRateChange(data.value);
            });

        }

        function getJsStatementDtl(id) {
            var url = BASE_PATH + "/jsStatement/getJsStatementDtlById";
            var loadIndex = parent.layer.load(2, {shade: [0.1]});
            var params = {id: id};
            $.ajax({
                url: url,
                data: params,
                async: false,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    if (data && data.returnCode == '200') {
                        var d = data.returnData;
                        tableRender(d.normalList);
                        offSetRender(d.offsetList);
                    } else {
                        parent.msgError(data.returnMsg);
                    }
                    parent.layer.close(loadIndex);
                },
                error: function (data) {
                    parent.layer.close(loadIndex);
                    parent.msgError("操作异常！");
                }
            });
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
                    fixTrStr += '</tr>';

                    var contentTrStr = '';
                    contentTrStr += '<tr tag="sum">'
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-0"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-1"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-2"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-3">';
                    contentTrStr += '    <input type="hidden" name="cxAreaSum">';
                    contentTrStr += '    <input type="hidden" name="cxAmountSum">';
                    contentTrStr += '    <input type="hidden" name="vaildDYAmountSum">';
                    contentTrStr += '    <input type="hidden" name="vaildFYAmountSum">';

                    contentTrStr += '    <input type="hidden" name="contractYdAmountSum">';
                    contentTrStr += '    <input type="hidden" name="jsAmountSum">';
                    contentTrStr += '    <input type="hidden" name="kpAmountSum">';
                    contentTrStr += '    <input type="hidden" name="kpTaxAmountSum">';

                    contentTrStr += '</div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-4"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-5"></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-6"><span name="cxArea"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-7"><span name="cxAmount"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-8"></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-9"><span name="vaildDYAmount">-</span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-10"><span name="vaildFYAmount"></span></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-11"></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-12"><span name="contractYdAmount"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-13"><span name="jsAmount"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-14"><span name="kpAmount"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-15"><span name="kpTaxAmount"></div></span></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-16"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-17"></div></td>';


                    var rightTrStr = '';
                    rightTrStr += '<tr tag="sum">'
                    rightTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-17"><span></span></div></td>';
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

                    addJsStatement.evalNormalTotal();

                    $(mainTable).find("tr[tag!='sum']").each(function (i, tr) {
                        addJsStatement.evalJsStatementTypeByTr(tr);
                    });
                }
            });
        }

        function setNormalCols() {
            var row = [
                {type: 'numbers', fixed: true, title: '序号', width: 60},
                {field: 'reportId', fixed: true, title: '订单编号', width: 140, align: 'center'},
                {field: 'buildingNo', fixed: true, title: '楼室号', width: 120, align: 'center'},
                {
                    field: 'customerName', title: '客户姓名', width: 120, align: 'center'
                    , templet: function (d) {

                        var id = NullToEmpty(d.id);
                        var reportNo = NullToEmpty(d.reportId);
                        var buildingNo = NullToEmpty(d.buildingNo);
                        var customerName = NullToEmpty(d.customerName);

                        var cxArea = NullToZero(d.cxArea);
                        var cxAmount = NullToZero(d.cxAmount);
                        var dealDate = isEmpty(d.dealDate) ? "" : formatDate(d.dealDate, "yyyy-MM-dd");
                        var vaildDYAmount = NullToZero(d.vaildDYAmount);
                        var vaildFYAmount = NullToZero(d.vaildFYAmount);
                        var oldJsTotalAmount = NullToZero(d.oldJsTotalAmount);
                        var isGlobalControl = NullToZero(d.isGlobalControl);
                        var projectCode = NullToEmpty(d.projectCode);
                        var projectName = NullToEmpty(d.projectName);
                        var accountProjectNo = NullToEmpty(d.accountProjectNo);
                        var accountProject = NullToEmpty(d.accountProject);

                        var ret = "";
                        ret += '<input type="hidden" name="id" value="' + id + '">';
                        ret += '<input type="hidden" name="reportNo" value="' + reportNo + '">';
                        ret += '<input type="hidden" name="buildingNo" value="' + buildingNo + '">';
                        ret += '<input type="hidden" name="customerName" value="' + customerName + '">';
                        ret += '<input type="hidden" name="cxArea" value="' + cxArea + '">';
                        ret += '<input type="hidden" name="cxAmount" value="' + cxAmount + '">';
                        ret += '<input type="hidden" name="dealDate" value="' + dealDate + '">';
                        ret += '<input type="hidden" name="vaildDYAmount" value="' + vaildDYAmount + '">';
                        ret += '<input type="hidden" name="vaildFYAmount" value="' + vaildFYAmount + '">';
                        ret += '<input type="hidden" name="oldJsTotalAmount" value="' + oldJsTotalAmount + '">';
                        ret += '<input type="hidden" name="jsStatementType" value="-1">';
                        ret += '<input type="hidden" name="isGlobalControl" value="' + isGlobalControl + '">';
                        ret += '<input type="hidden" name="projectCode" value="' + projectCode + '">';
                        ret += '<input type="hidden" name="projectName" value="' + projectName + '">';
                        ret += '<input type="hidden" name="accountProjectNo" value="' + accountProjectNo + '">';
                        ret += '<input type="hidden" name="accountProject" value="' + accountProject + '">';

                        ret += '<span>' + customerName + '</span>'

                        return ret;
                    }
                },
                {field: 'projectName', title: '项目', width: 120, align: 'center'},
                {
                    field: 'isGlobalControl', title: '垫佣控制规则', width: 105, align: 'center', templet: function (d) {
                        var isGlobalControl = NullToZero(d.isGlobalControl);
                        if (isGlobalControl == 0) {
                            return "房源单控";
                        } else {
                            return "项目总控";
                        }
                    }
                },
                {
                    field: 'cxArea', title: '面积（㎡）', width: 100, align: 'center', style: 'text-align:right'
                    , templet: function (d) {
                        return formatMoney(d.cxArea);
                    }
                },
                {
                    field: 'cxAmount', title: '签约总价（元）', width: 130, align: 'center', style: 'text-align:right'
                    , templet: function (d) {
                        return formatMoney(d.cxAmount);
                    }
                },
                {
                    field: 'dealDate', title: '成销日期', width: 100, align: 'center',
                    templet: function (d) {
                        if (d.dealDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.dealDate, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'vaildDYAmount', title: '可垫金额（元）', width: 130, align: 'center', style: 'text-align:right'
                    , templet: function (d) {
                        var isSpecialProject = $("#isSpecialProject").val();
                        if (isSpecialProject == 1 || isSpecialProject == 2) {
                            return '-'
                        } else {
                            return formatMoney(d.vaildDYAmount);
                        }

                    }
                },
                {
                    field: 'vaildFYAmount', title: '可返金额（元）', width: 130, align: 'center', style: 'text-align:right'
                    , templet: function (d) {
                        return formatMoney(d.vaildFYAmount);
                    }
                },
                {
                    field: 'serviceFeeDes', title: '服务费比例<i>*</i>', width: 280, align: 'center',
                    templet: function (d) {
                        var serviceFeeDes = isEmpty(d.serviceFeeDes) ? "" : d.serviceFeeDes;
                        return '<input type="text" name="serviceFeeDes" maxlength="20" autocomplete="off"  class="renderInput ml" value="' + serviceFeeDes + '">'
                    }
                },
                {
                    field: 'contractYdAmount', title: '合同约定金额(元)<i>*</i>', width: 130, align: 'center',
                    templet: function (d) {
                        var contractYdAmount = d.contractYdAmount == null ? "" : formatAccount2(d.contractYdAmount);
                        return '<input type="text" name="contractYdAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalNormalItem(\'contractYdAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,0)" class="renderInput mr" value="' + contractYdAmount + '">'
                    }
                },
                {
                    field: 'jsAmount', title: '本次结算金额(元)<i>*</i>', width: 130, align: 'center',
                    templet: function (d) {
                        var jsAmount = d.jsAmount == null ? "" : formatAccount2(d.jsAmount);
                        return '<input type="text" name="jsAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalNormalItem(\'jsAmount\');addJsStatement.evalJsStatementType(this)" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,0)" class="renderInput mr" value="' + jsAmount + '">'
                    }
                },
                {
                    field: 'kpAmount', title: '实际开票金额(元)<i>*</i>', width: 130, align: 'center',
                    templet: function (d) {
                        var kpAmount = d.kpAmount == null ? "" : formatAccount2(d.kpAmount);
                        return '<input type="text" name="kpAmount" maxlength="20" autocomplete="off"  onkeyup="javascript:addJsStatement.evalNormalItem(\'kpAmount\');addJsStatement.setJsAmountAfter(\'normalBlock\',this,true)" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,0)" class="renderInput mr" value="' + kpAmount + '">'

                    }
                },
                {
                    field: 'kpTaxAmount', title: '税额(元)<i>*</i>', width: 130, align: 'center',
                    templet: function (d) {
                        var kpTaxAmount = d.kpTaxAmount == null ? "" : formatAccount2(d.kpTaxAmount);
                        return '<input type="text" name="kpTaxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalNormalItem(\'kpTaxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,0)" class="renderInput mr" value="' + kpTaxAmount + '">'
                    }
                },
                {
                    field: 'jsStatementType', title: '结算类型', width: 100, align: 'center',
                    templet: function (d) {
                        return '<span name="jsStatementType">-</span>';
                    }
                },
                {
                    title: '操作', fixed: 'right', align: 'center', width: 80,
                    templet: function (d) {
                        return "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:addJsStatement.delete(\"normalBlock\",this)'>删除</a>";
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        }

        function offSetRender(data) {
            table.render({
                elem: "#offsetReport"
                , cols: setOffSetCols()
                , id: 'offsetBlock'
                , height: 'full'
                , data: data
                , limit: 1000
                , page: false
                , done: function (res, curr, count) {
                    var fixTrStr = '';
                    fixTrStr += '<tr tag="sum">'
                    fixTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-0 "></div></td>';
                    fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-1">合计：</div></td>';
                    fixTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-2"></div></td>';
                    fixTrStr += '</tr>';

                    var contentTrStr = '';
                    contentTrStr += '<tr tag="sum">'
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-0"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-1"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-2"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-3">';
                    contentTrStr += '    <input type="hidden" name="cxAreaSum">';
                    contentTrStr += '    <input type="hidden" name="cxAmountSum">';
                    contentTrStr += '    <input type="hidden" name="vaildDYAmountSum">';
                    contentTrStr += '    <input type="hidden" name="vaildFYAmountSum">';

                    contentTrStr += '    <input type="hidden" name="contractYdAmountSum">';
                    contentTrStr += '    <input type="hidden" name="jsAmountSum">';
                    contentTrStr += '    <input type="hidden" name="kpAmountSum">';
                    contentTrStr += '    <input type="hidden" name="kpTaxAmountSum">';
                    contentTrStr += '</div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-4"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-5"></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-6"><span name="cxArea"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-7"><span name="cxAmount"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-8"></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-9"><span name="vaildDYAmount"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-10"><span name="vaildFYAmount"></span></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-11"></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-12"><span name="contractYdAmount"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-13"><span name="jsAmount"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-14"><span name="kpAmount"></span></div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-15"><span name="kpTaxAmount"></div></span></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-16"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-17"></div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-18"></div></td>';


                    var rightTrStr = '';
                    rightTrStr += '<tr tag="sum">'
                    rightTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-18"><span></span></div></td>';
                    rightTrStr += '</tr>';


                    var leftTable = $("div[lay-id='offsetBlock'] .layui-table-fixed-l ");
                    var mainTable = $("div[lay-id='offsetBlock'] .layui-table-main ");
                    var rightTable = $("div[lay-id='offsetBlock'] .layui-table-fixed-r ");

                    $(leftTable).find(".layui-table-body tbody").append(fixTrStr);
                    $(mainTable).find("tbody").append(contentTrStr);
                    $(rightTable).find(".layui-table-body tbody").append(rightTrStr);

                    $(leftTable).removeClass('layui-hide');
                    $(mainTable).find('div[class="layui-none"]').empty().remove();
                    $(rightTable).removeClass('layui-hide')

                    addJsStatement.evalOffSetTotal();
                }
            });
        }

        function setOffSetCols() {
            var row = [
                {type: 'numbers', fixed: true, title: '序号', width: 60},
                {field: 'reportId', fixed: true, title: '订单编号', width: 140, align: 'center'},
                {field: 'buildingNo', fixed: true, title: '楼室号', width: 120, align: 'center'},
                {
                    field: 'customerName', title: '客户姓名', width: 120, align: 'center'
                    , templet: function (d) {
                        var id = NullToEmpty(d.id);
                        var reportNo = NullToEmpty(d.reportId);
                        var buildingNo = NullToEmpty(d.buildingNo);
                        var customerName = NullToEmpty(d.customerName);

                        var cxArea = NullToZero(d.cxArea);
                        var cxAmount = NullToZero(d.cxAmount);
                        var dealDate = isEmpty(d.dealDate) ? "" : formatDate(d.dealDate, "yyyy-MM-dd");
                        var vaildDYAmount = NullToZero(d.vaildDYAmount);
                        var vaildFYAmount = NullToZero(d.vaildFYAmount);
                        var oldJsTotalAmount = NullToZero(d.oldJsTotalAmount);
                        var isGlobalControl = NullToZero(d.isGlobalControl);
                        var projectCode = NullToEmpty(d.projectCode);
                        var projectName = NullToEmpty(d.projectName);

                        var ret = "";
                        ret += '<input type="hidden" name="id" value="' + id + '">';
                        ret += '<input type="hidden" name="reportNo" value="' + reportNo + '">';
                        ret += '<input type="hidden" name="buildingNo" value="' + buildingNo + '">';
                        ret += '<input type="hidden" name="customerName" value="' + customerName + '">';
                        ret += '<input type="hidden" name="cxArea" value="' + cxArea + '">';
                        ret += '<input type="hidden" name="cxAmount" value="' + cxAmount + '">';
                        ret += '<input type="hidden" name="dealDate" value="' + dealDate + '">';
                        ret += '<input type="hidden" name="vaildDYAmount" value="' + vaildDYAmount + '">';
                        ret += '<input type="hidden" name="vaildFYAmount" value="' + vaildFYAmount + '">';
                        ret += '<input type="hidden" name="oldJsTotalAmount" value="' + oldJsTotalAmount + '">';
                        ret += '<input type="hidden" name="isGlobalControl" value="' + isGlobalControl + '">';
                        ret += '<input type="hidden" name="projectCode" value="' + projectCode + '">';
                        ret += '<input type="hidden" name="projectName" value="' + projectName + '">';

                        ret += '<span>' + customerName + '</span>'
                        return ret;
                    }
                },
                {field: 'projectName', title: '项目', width: 120, align: 'center', style: 'text-align:left'},
                {
                    field: 'isGlobalControl', title: '垫佣控制规则', width: 105, align: 'center', templet: function (d) {
                        var isGlobalControl = NullToZero(d.isGlobalControl);
                        if (isGlobalControl == 0) {
                            return "房源单控";
                        } else {
                            return "项目总控";
                        }
                    }
                },
                {
                    field: 'cxArea', title: '面积（㎡）', width: 100, align: 'center', style: 'text-align:right'
                    , templet: function (d) {
                        return formatMoney(d.cxArea);
                    }
                },
                {
                    field: 'cxAmount', title: '签约总价（元）', width: 130, align: 'center', style: 'text-align:right'
                    , templet: function (d) {
                        return formatMoney(d.cxAmount);
                    }
                },
                {
                    field: 'dealDate', title: '成销日期', width: 100, align: 'center',
                    templet: function (d) {
                        if (d.dealDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.dealDate, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'vaildDYAmount', title: '实际可垫金额(元)', width: 130, align: 'center', style: 'text-align:right'
                    , templet: function (d) {
                        return formatMoney(d.vaildDYAmount);
                    }
                },
                {
                    field: 'vaildFYAmount', title: '实际可返金额(元)', width: 130, align: 'center', style: 'text-align:right'
                    , templet: function (d) {
                        return formatMoney(d.vaildFYAmount);
                    }
                },
                {
                    field: 'serviceFeeDes', title: '服务费比例<i>*</i>', width: 280, align: 'center',
                    templet: function (d) {
                        var serviceFeeDes = isEmpty(d.serviceFeeDes) ? "" : d.serviceFeeDes;
                        return '<input type="text" name="serviceFeeDes" maxlength="20" autocomplete="off"  class="renderInput ml" value="' + serviceFeeDes + '">'
                    }
                },
                {
                    field: 'contractYdAmount', title: '合同约定金额(元)<i>*</i>', width: 130, align: 'center',
                    templet: function (d) {
                        var contractYdAmount = d.contractYdAmount == null ? "" : formatAccount2(d.contractYdAmount);
                        return '<input type="text" name="contractYdAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalOffSetItem(\'contractYdAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,1)" class="renderInput mr" value="' + contractYdAmount + '">'
                    }
                },
                {
                    field: 'jsAmount', title: '本次结算金额(元)<i>*</i>', width: 130, align: 'center',
                    templet: function (d) {
                        var jsAmount = d.jsAmount == null ? "" : formatAccount2(d.jsAmount);
                        return '<input type="text" name="jsAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalOffSetItem(\'jsAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,1)" class="renderInput mr" value="' + jsAmount + '">'
                    }
                },
                {
                    field: 'kpAmount', title: '实际开票金额(元)<i>*</i>', width: 130, align: 'center',
                    templet: function (d) {
                        var kpAmount = d.kpAmount == null ? "" : formatAccount2(d.kpAmount);
                        return '<input type="text" name="kpAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalOffSetItem(\'kpAmount\');addJsStatement.setJsAmountAfter(\'offsetBlock\',this,true)" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,1)" class="renderInput mr" value="' + kpAmount + '">'
                    }
                },
                {
                    field: 'kpTaxAmount', title: '税额(元)<i>*</i>', width: 130, align: 'center',
                    templet: function (d) {
                        var kpTaxAmount = d.kpTaxAmount == null ? "" : formatAccount2(d.kpTaxAmount);
                        return '<input type="text" name="kpTaxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalOffSetItem(\'kpTaxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,1)" class="renderInput mr" value="' + kpTaxAmount + '">'
                    }
                },
                {
                    field: 'jsStatementType', title: '结算类型<i>*</i>', width: 100, align: 'center',
                    templet: function (d) {
                        var ret = ' <select  name ="jsStatementType" lay-ignore class="renderInput">"';
                        if (isEmpty(d.jsStatementType) || d.jsStatementType == -1) {
                            ret += '<option value="-1" selected="selected">请选择</option>'
                        } else {
                            ret += '<option value="-1">请选择</option>'
                        }
                        if (!isEmpty(d.jsStatementType) && d.jsStatementType == 1) {
                            ret += '<option value="1" selected="selected">返佣</option>'
                        } else {
                            ret += '<option value="1">返佣</option>'
                        }
                        if (!isEmpty(d.jsStatementType) && d.jsStatementType == 2) {
                            ret += '<option value="2" selected="selected">垫佣</option>'
                        } else {
                            ret += '<option value="2">垫佣</option>'
                        }
                        ret += '</select>'
                        return ret;
                    }
                },
                {
                    field: 'accountProjectNo', title: '核算主体<i>*</i>', width: 200, align: 'center',
                    templet: function (d) {
                        var ret = '<select  name="selAccountProject" lay-ignore class="renderInput">';

                        if (d.progress == '13505' && d.confirmStatus == '13601') {
                            ret += '<option value="' + d.accountProjectNo + '" data-hsname="' + d.accountProject + '">' + d.accountProjectNo + "_" + d.accountProject + '</option>';
                        } else {
                            var dbAccountProjectList = d.hsCodeList;
                            var dbAccountProjectNo = NullToEmpty(d.hsCode);
                            ret += ' <option value="">请选择</option>'
                            for (var i = 0; i < dbAccountProjectList.length; i++) {
                                var item = dbAccountProjectList[i];
                                var accountProjectCode = NullToEmpty(item.hsCode);
                                var accountProjectName = NullToEmpty(item.hsname);

                                ret += ' <option value="' + accountProjectCode + '"';
                                ret += '         data-hsname="' + accountProjectName + '"';
                                if (dbAccountProjectList.length == 1 || accountProjectCode == dbAccountProjectNo) {
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
                    title: '操作', fixed: 'right', align: 'center', width: 80,
                    templet: function (d) {
                        return "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:addJsStatement.delete(\"offsetBlock\",this)'>删除</a>";
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        }

        var active = {
            save: function () {
                toSave(0)
            },
            submit: function () {
                toSave(1)
            },
            cancel: function () {
                parent.redirectTo('delete', null, null);
            },
            goback: function () {
                parent.redirectTo('delete', null, null);
            },
            openDialogCompany: function () {
                addJsStatement.openDialogCompany();
            },
            openDialogProject: function () {
                addJsStatement.openDialogProject();
            },
            openDialogKhCode: function () {
                addJsStatement.openDialogKhCode();
            },
            openDialogFrameOaNo: function () {
                addJsStatement.openDialogFrameOaNo();
            },
            openDialogNormalReport: function () {
                addJsStatement.openDialogNormalReport();
            },
            openDialogOffsetReport: function () {
                addJsStatement.openDialogOffsetReport();
            }

        }

        $('.layui-btn').on('click', function () {
            var type = $(this).attr('data-type');
            active[type] ? active[type].call(this) : '';
        });

        function toSave(type) {
            // 检验内容
            if (vaild(type) == false) {
                return false;
            }

            saveToService(type)
        }

        function vaild(type) {
            var flag = true;
            // 渠道公司
            var companyId = $("#companyId").attr("data-companyId");
            var companyNo = $("#companyId").attr("data-companyNo");
            var companyName = $("#companyId").val();
            if (isEmpty(companyId) || isEmpty(companyNo) || isEmpty(companyName)) {
                parent.msgAlert("请选择渠道公司！")
                return false;
            }

            // 项目
            var projectNo = $("#projectNo").attr("data-projectNo");
            var projectName = $("#projectNo").val();
            if (isEmpty(projectNo) || isEmpty(projectName)) {
                parent.msgAlert("请选择项目！")
                return false;
            }

            // 提交的场合
            if (type == 1) {
                // 核算主体
                var hsCode = $("#ddlHSCode").val();
                var hsName = $("#ddlHSCode").find("option:selected").attr("data-hsName");
                if (isEmpty(hsCode) || isEmpty(hsName)) {
                    parent.msgAlert("请选择核算主体！")
                    return false;
                }

                // 考核主体
                var khCode = $("#khCode").attr("data-khCode");
                var khName = $("#khCode").val();
                if (isEmpty(khCode) || isEmpty(khName)) {
                    parent.msgAlert("请选择考核主体！")
                    return false;
                }
                // 合同
                var frameOaName = $("#frameOaNo").attr("data-frameOaName");
                var frameOaNo = $("#frameOaNo").val();
                if (isEmpty(frameOaName) || isEmpty(frameOaNo)) {
                    parent.msgAlert("请选择合同！")
                    return false;
                }
                // 供应商
                var vendorId = $("#vendorId").attr("data-vendorId");
                var vendorCode = $("#vendorId").attr("data-vendorCode");
                var venderName = $("#vendorId").val();
                if (isEmpty(vendorId) || isEmpty(vendorCode || isEmpty(vendorCode))) {
                    parent.msgAlert("请确认供应商！")
                    return false;
                }

                // 供应商税率
                var kpRate = $("input[name='kpRate']:checked").val();
                if (isEmpty(kpRate)) {
                    parent.msgAlert("请选择供应商税率！")
                    return false;
                }
                // 是否包含冲抵结算
                var offSetFlag = $("input[name='offSetFlag']:checked").val();
                if (isEmpty(offSetFlag)) {
                    parent.msgAlert("请选择[是否包含冲抵结算]！")
                    return false;
                }

                // 本次结算金额总计：
                var jsTotalAmount = $("input[name='jsAmountTotal']").val();
                if (isEmpty(jsTotalAmount) || Number(jsTotalAmount) < 0) {
                    parent.msgAlert("本次结算金额总计不能小于零！")
                    return false;
                }
                // 实际开票金额总计：
                var kpTotalAmount = $("input[name='kpAmountTotal']").val();
                if (isEmpty(kpTotalAmount) || Number(kpTotalAmount) < 0) {
                    parent.msgAlert("实际开票金额总计不能小于零！")
                    return false;
                }
                // 实际开票税额总计
                var kpTotalTaxAmount = $("input[name='kpTaxAmountTotal']").val();
                if (isEmpty(kpTotalTaxAmount) || Number(kpTotalTaxAmount) < 0) {
                    parent.msgAlert("实际开票税额总计不能小于零！")
                    return false;
                }

                // 正常结算
                var vaildFYAmountTotal = 0;
                var vaildDYAmountTotal = 0;
                var isSpecialProject = $("#isSpecialProject").val();
                var normalBlock = $("div[lay-id='normalBlock'] .layui-table-main tbody");

                var normalSize = $(normalBlock).find("tr[tag!='sum']").length;
                if (normalSize == 0) {
                    parent.msgAlert("请选择正常结算订单！")
                    return false;
                }

                $(normalBlock).find("tr[tag!='sum']").each(function (i, tr) {
                        var reportNo = $(tr).find("input[name='reportNo']").val();

                        // 服务费比例
                        var serviceFeeDes = $(tr).find("input[name='serviceFeeDes']").val();
                        if (isEmpty(serviceFeeDes)) {
                            parent.msgAlert("正常结算：请输入订单编号[" + reportNo + "]的服务费比例！");
                            flag = false;
                            return false;
                        }

                        // 合同约定金额
                        var contractYdAmount = $(tr).find("input[name='contractYdAmount']").val();
                        if (isEmpty(contractYdAmount)) {
                            parent.msgAlert("正常结算：请输入订单编号[" + reportNo + "]的合同约定金额！");
                            flag = false;
                            return false;
                        }
                        if (Number(contractYdAmount) <= 0) {
                            parent.msgAlert("正常结算：订单编号[" + reportNo + "]的合同约定金额应大于0!");
                            flag = false;
                            return false;
                        }

                        // 本次结算金额
                        var jsAmount = $(tr).find("input[name='jsAmount']").val();
                        if (isEmpty(jsAmount)) {
                            parent.msgAlert("正常结算：请输入订单编号[" + reportNo + "]的本次结算金额！");
                            flag = false;
                            return false;
                        }
                        if (Number(jsAmount) <= 0) {
                            parent.msgAlert("正常结算：订单编号[" + reportNo + "]的本次结算金额应大于0!");
                            flag = false;
                            return false;
                        }
                        // 实际开票金额
                        var kpAmount = $(tr).find("input[name='kpAmount']").val();
                        if (isEmpty(kpAmount)) {
                            parent.msgAlert("正常结算：请输入订单编号[" + reportNo + "]的实际开票金额！");
                            flag = false;
                            return false;
                        }
                        if (Number(kpAmount) <= 0) {
                            parent.msgAlert("正常结算：订单编号[" + reportNo + "]的实际开票金额应大于0!");
                            flag = false;
                            return false;
                        }
                        // 税额
                        var kpTaxAmount = $(tr).find("input[name='kpTaxAmount']").val();
                        if (isEmpty(kpTaxAmount)) {
                            parent.msgAlert("正常结算：请输入订单编号[" + reportNo + "]的实际税额！");
                            flag = false;
                            return false;
                        }
                        if (Number(kpTaxAmount) < 0) {
                            parent.msgAlert("正常结算：订单编号[" + reportNo + "]的实际税额应大于0!");
                            flag = false;
                            return false;
                        }

                        // 本次结算金额：值小于或等于合同约定金额
                        if (Number(jsAmount) > Number(contractYdAmount)) {
                            parent.msgAlert("正常结算：订单编号[" + reportNo + "]的本次结算金额应当小于或等于合同约定金额！");
                            flag = false;
                            return false;
                        }

                        // 实际开票金额：值小于等于本次结算金额
                        if (Number(kpAmount) > Number(jsAmount)) {
                            parent.msgAlert("正常结算：订单编号[" + reportNo + "]的实际开票金额应当小于或等于本次结算金额！");
                            flag = false;
                            return false;
                        }

                        // 税额：值小于等于实际开票金额
                        if (Number(kpTaxAmount) > Number(kpAmount)) {
                            parent.msgAlert("正常结算：订单编号[" + reportNo + "]的实际税额应当小于或等于实际开票金额！");
                            flag = false;
                            return false;
                        }

                        // 税率区间
                        var taxrate = kpTaxAmount / kpAmount;
                        if (!checkRate(taxrate)) {
                            parent.msgError("正常结算：订单编号[" + reportNo + "]税率没有落在税率区间内 请修改!");
                            flag = false;
                            return false;
                        }

                        // 历史结算金额总和 + 本次结算金额<=本次合同约定金额
                        var oldJsTotalAmount = NullToZero($(tr).find("input[name='oldJsTotalAmount']").val());
                        if ((Number(oldJsTotalAmount) + Number(jsAmount)).toFixed > Number(contractYdAmount)) {
                            parent.msgAlert("正常结算：订单编号[" + reportNo + "]的历史结算金额总和与本次结算金额之和应当小于或等于本次合同约定金额！");
                            flag = false;
                            return false
                        }

                        // 结算类型
                        var jsStatementType = $(tr).find("input[name='jsStatementType']").val();
                        var isGlobalControl = NullToEmpty($(tr).find("input[name='isGlobalControl']").val());
                        if (isEmpty(jsStatementType) || (jsStatementType != 1 && jsStatementType != 2)) {
                            parent.msgError("正常结算：订单编号[" + reportNo + "]不符合结算条件，请从列表中删除。");
                            flag = false;
                            return false;
                        } else if (jsStatementType == 1) {
                            var vaildFYAmount = NullToZero($(tr).find("input[name='vaildFYAmount']").val());
                            if (Number(jsAmount) > Number(vaildFYAmount)) {
                                parent.msgError("正常结算：订单编号[" + reportNo + "]本次结算金额不能大于可返金额！");
                                flag = false;
                                return false;
                            }
                            if (isGlobalControl == "1") {
                                vaildFYAmountTotal = (Number(vaildFYAmountTotal) + Number(jsAmount)).toFixed(2);
                            }
                        } else if (jsStatementType == 2) {
                            if (isSpecialProject != 1 && isSpecialProject != 2) {
                                var vaildDYAmount = NullToZero($(tr).find("input[name='vaildDYAmount']").val());
                                if (Number(jsAmount) > Number(vaildDYAmount)) {
                                    parent.msgError("正常结算：订单编号[" + reportNo + "]本次结算金额不能大于可垫金额！");
                                    flag = false;
                                    return false;
                                }
                                if (isGlobalControl == "1") {
                                    vaildDYAmountTotal = (Number(vaildDYAmountTotal) + Number(jsAmount)).toFixed(2);
                                }
                            }
                        }

                    }
                );

                if (flag == false) {
                    return false;
                }

                var com_ZK_vaildDYAmount = NullToZero($("#reportCard").find("input[name='com_ZK_vaildDYAmount']").val());
                var com_ZK_vaildFYAmount = NullToZero($("#reportCard").find("input[name='com_ZK_vaildFYAmount']").val());
                if (isSpecialProject != 1 && isSpecialProject != 2) {
                    // 总控公司可垫金额 > 本次计算金额（总控& 垫佣结算）
                    if (Number(vaildDYAmountTotal) > 0 && Number(com_ZK_vaildDYAmount) < Number(vaildDYAmountTotal)) {
                        parent.msgError("正常结算：本次计算金额的总控垫佣结算金额不能大于总控公司可垫金额");
                        return false;
                    }
                }
                // 总控公司可返金额 > 本次计算金额（总控& 返佣结算）
                if (Number(vaildFYAmountTotal) > 0 && Number(com_ZK_vaildFYAmount) < Number(vaildFYAmountTotal)) {
                    parent.msgError("正常结算：本次计算金额的总控返佣结算金额不能大于总控公司可返金额");
                    return false;
                }

                // 冲抵结算
                var offsetBlock = $("div[lay-id='offsetBlock'] .layui-table-main tbody");
                var offsetSize = $(offsetBlock).find("tr[tag!='sum']").length;
                if (offSetFlag == 1) {
                    if (offsetSize == 0) {
                        parent.msgAlert("请选择冲抵结算订单！")
                        return false;
                    }
                } else {
                    if (offsetSize > 0) {
                        parent.msgAlert("请确认[是否包含冲抵结算]！")
                        return false;
                    }
                }

                $(offsetBlock).find("tr[tag!='sum']").each(function (i, tr) {
                    var reportNo = $(tr).find("input[name='reportNo']").val();

                    // 服务费比例
                    var serviceFeeDes = $(tr).find("input[name='serviceFeeDes']").val();
                    if (isEmpty(serviceFeeDes)) {
                        parent.msgAlert("冲抵结算：请输入订单编号[" + reportNo + "]的服务费比例！");
                        flag = false;
                        return false;
                    }

                    // 合同约定金额
                    var contractYdAmount = $(tr).find("input[name='contractYdAmount']").val();
                    if (isEmpty(contractYdAmount)) {
                        parent.msgAlert("冲抵结算：请输入订单编号[" + reportNo + "]的合同约定金额！");
                        flag = false;
                        return false;
                    }
                    if (Number(contractYdAmount) >= 0) {
                        parent.msgAlert("冲抵结算：订单编号[" + reportNo + "]的合同约定金额应小于0!");
                        flag = false;
                        return false;
                    }

                    // 本次结算金额
                    var jsAmount = $(tr).find("input[name='jsAmount']").val();
                    if (isEmpty(jsAmount)) {
                        parent.msgAlert("冲抵结算：请输入订单编号[" + reportNo + "]的本次结算金额！");
                        flag = false;
                        return false;
                    }
                    if (Number(jsAmount) >= 0) {
                        parent.msgAlert("冲抵结算：订单编号[" + reportNo + "]的本次结算金额应小于0!");
                        flag = false;
                        return false;
                    }
                    // 实际开票金额
                    var kpAmount = $(tr).find("input[name='kpAmount']").val();
                    if (isEmpty(kpAmount)) {
                        parent.msgAlert("冲抵结算：请输入订单编号[" + reportNo + "]的实际开票金额！");
                        flag = false;
                        return false;
                    }
                    if (Number(kpAmount) >= 0) {
                        parent.msgAlert("冲抵结算：订单编号[" + reportNo + "]的实际开票金额应小于0!");
                        flag = false;
                        return false;
                    }
                    // 实际税额
                    var kpTaxAmount = $(tr).find("input[name='kpTaxAmount']").val();
                    if (isEmpty(kpTaxAmount)) {
                        parent.msgAlert("冲抵结算：请输入订单编号[" + reportNo + "]的实际税额！");
                        flag = false;
                        return false;
                    }
                    if (Number(kpAmount) > 0) {
                        parent.msgAlert("冲抵结算：订单编号[" + reportNo + "]的实际税额应小于0!");
                        flag = false;
                        return false;
                    }

                    // 税率区间
                    var taxrate = kpTaxAmount / kpAmount;
                    if (!checkRate(taxrate)) {
                        parent.msgError("冲抵结算：订单编号[" + reportNo + "]税率没有落在税率区间内 请修改");
                        flag = false;
                        return false;
                    }

                    // 本次结算金额：值小于或等于合同约定金额
                    if (Math.abs(Number(jsAmount)) > Math.abs(Number(contractYdAmount))) {
                        parent.msgAlert("冲抵结算：订单编号[" + reportNo + "]的本次结算金额不能超出合同约定金额！");
                        flag = false;
                        return false;
                    }
                    // 实际开票金额：值小于等于本次结算金额
                    if (Math.abs(Number(kpAmount)) > Math.abs(Number(jsAmount))) {
                        parent.msgAlert("冲抵结算：订单编号[" + reportNo + "]的实际开票金额不能超出本次结算金额！");
                        flag = false;
                        return false;
                    }

                    // 税额：值小于等于实际开票金额
                    if (Math.abs(Number(kpTaxAmount)) > Math.abs(Number(kpAmount))) {
                        parent.msgAlert("冲抵结算：订单编号[" + reportNo + "]的税额应不能大于实际开票金额！");
                        flag = false;
                        return false;
                    }

                    // 结算类型
                    var jsStatementType = $(tr).find("select[name='jsStatementType']").val();
                    if (isEmpty(jsStatementType) || (jsStatementType != 1 && jsStatementType != 2)) {
                        parent.msgError("冲抵结算：订单编号[" + reportNo + "]不符合结算条件，请从列表中删除。");
                        flag = false;
                        return false;
                    } else if (jsStatementType == 1) {
                        var vaildFYAmount = NullToZero($(tr).find("input[name='vaildFYAmount']").val());
                        if (Math.abs(Number(jsAmount)) > Math.abs(Number(vaildFYAmount))) {
                            parent.msgError("冲抵结算：订单编号[" + reportNo + "]本次结算金额不能超出实际可返金额！");
                            flag = false;
                            return false;
                        }
                    } else if (jsStatementType == 2) {
                        var vaildDYAmount = NullToZero($(tr).find("input[name='vaildDYAmount']").val());
                        if (Math.abs(Number(jsAmount)) > Math.abs(Number(vaildDYAmount))) {
                            parent.msgError("冲抵结算：订单编号[" + reportNo + "]本次结算金额不能超出实际可垫金额！");
                            flag = false;
                            return false;
                        }
                    }


                    var hsCode = $(tr).find("select[name='selAccountProject']").val();
                    var hsName = $(tr).find("select[name='selAccountProject']").find("option:selected").attr("data-hsname");

                    if (isEmpty(hsCode) || isEmpty(hsName)) {
                        parent.msgAlert("冲抵结算：请选择订单编号[" + reportNo + "]的核算主体！");
                        flag = false;
                        return false;
                    }

                });

                if (flag == false) {
                    return false;
                }

                // 成销确认书 / 佣金结算资料：附件，必填
                var fileSize1 = $("#uploadSuccSaleImg .item_img").size()
                if (fileSize1 == null || fileSize1 <= 0) {
                    msgAlert("请上传[成销确认书/佣金结算资料]！")
                    return false;
                }

                // 项目合同：附件，必填
                var fileSize2 = $("#uploadProjectContractImg .item_img").size()
                if (fileSize2 == null || fileSize2 <= 0) {
                    msgAlert("请上传[项目合同]！")
                    return false;
                }
                // 分销合同：附件，必填
                var fileSize3 = $("#uploadBranchContractImg .item_img").size()
                if (fileSize3 == null || fileSize3 <= 0) {
                    msgAlert("请上传[分销合同]！")
                    return false;
                }
            }

            return true;
        }

        function saveToService(type) {
            var loadIndex = parent.layer.load(2, {shade: [0.1]});
            var url = BASE_PATH + "/jsStatement/save";
            var param = getLocalData(type);
            if (!isEmpty(param.id)) {
                var url = BASE_PATH + "/jsStatement/update";
            }
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

        function getLocalData(type) {
            var param = {};
            param.submitStatus = type;
            param.id = $("#id").val();
            param.exclude = $("#exclude").val();
            param.companyId = $("#companyId").attr("data-companyId");
            param.companyNo = $("#companyId").attr("data-companyNo");
            param.companyName = $("#companyId").val();

            param.projectNo = $("#projectNo").attr("data-projectNo");
            param.projectName = $("#projectNo").val();
            param.isSpecialProject = $("#isSpecialProject").val();

            param.hsCode = $("#ddlHSCode").val();
            param.hsName = $("#ddlHSCode").find("option:selected").attr("data-hsName");

            param.khCode = $("#khCode").attr("data-khCode");
            param.khName = $("#khCode").val();

            param.frameOaName = $("#frameOaNo").attr("data-frameOaName");
            param.frameOaNo = $("#frameOaNo").val();

            param.vender_id = $("#vendorId").attr("data-vendorId");
            param.vender_code = $("#vendorId").attr("data-vendorCode");
            param.vender_name = $("#vendorId").val();

            param.kpRate = $("input[name='kpRate']:checked").val();
            param.offSetFlag = $("input[name='offSetFlag']:checked").val();

            param.remark = $("#remark").val();

            param.contractYdTotalAmount = $("input[name='contractYdAmountTotal']").val();
            param.jsTotalAmount = $("input[name='jsAmountTotal']").val();
            param.kpTotalAmount = $("input[name='kpAmountTotal']").val();
            param.kpTotalTaxAmount = $("input[name='kpTaxAmountTotal']").val();
            if (checkIsNumber(param.kpTotalAmount) && checkIsNumber(param.kpTotalTaxAmount)) {
                param.kpTotalTaxAfterAmount = parseFloat(parseFloat(param.kpTotalAmount) - parseFloat(param.kpTotalTaxAmount)).toFixed(2);
            }
            param.requestAmount = 0;
            param.jssType = '28002';              // 28002:垫返佣
            param.businessType = '联动';
            param.yyType = '返佣结算';

            // region 正常结算数据
            var reportList = [];
            var normalBlock = $("div[lay-id='normalBlock'] .layui-table-main tbody");
            $(normalBlock).find("tr[tag!='sum']").each(function (i, tr) {
                var normalReport = {};
                normalReport.id = $(tr).find("input[name='id']").val();
                normalReport.reportId = $(tr).find("input[name='reportNo']").val();
                normalReport.customerName = $(tr).find("input[name='customerName']").val();
                normalReport.customerTel = $(tr).find("input[name='customerTel']").val();
                normalReport.buildingNo = $(tr).find("input[name='buildingNo']").val();
                normalReport.cxArea = $(tr).find("input[name='cxArea']").val();
                normalReport.cxAmount = $(tr).find("input[name='cxAmount']").val();
                normalReport.dealDate = $(tr).find("input[name='dealDate']").val();
                normalReport.jsAmount = $(tr).find("input[name='jsAmount']").val();
                normalReport.kpAmount = $(tr).find("input[name='kpAmount']").val();
                normalReport.kpTaxAmount = $(tr).find("input[name='kpTaxAmount']").val();
                if (checkIsNumber(normalReport.kpAmount) && checkIsNumber(normalReport.kpTaxAmount)) {
                    normalReport.kpTaxAmountAfter = parseFloat(parseFloat(normalReport.kpAmount) - parseFloat(normalReport.kpTaxAmount)).toFixed(2);
                }
                normalReport.contractYdAmount = $(tr).find("input[name='contractYdAmount']").val();
                normalReport.projectCode = $(tr).find("input[name='projectCode']").val();
                normalReport.projectName = $(tr).find("input[name='projectName']").val();
                normalReport.jsStatementType = $(tr).find("input[name='jsStatementType']").val();
                normalReport.offSetFlag = 0;
                normalReport.requestAmount = 0;
                normalReport.serviceFeeDes = $(tr).find("input[name='serviceFeeDes']").val();
                normalReport.isGlobalControl = $(tr).find("input[name='isGlobalControl']").val();
                normalReport.hsCode = param.hsCode;
                normalReport.hsName = param.hsName;
                reportList.push(normalReport)
            });
            //endregion

            //region 冲抵结算数据
            var offsetBlock = $("div[lay-id='offsetBlock'] .layui-table-main tbody");
            $(offsetBlock).find("tr[tag!='sum']").each(function (i, tr) {
                var offsetReport = {};
                offsetReport.id = $(tr).find("input[name='id']").val();
                offsetReport.reportId = $(tr).find("input[name='reportNo']").val();
                offsetReport.customerName = $(tr).find("input[name='customerName']").val();
                offsetReport.customerTel = $(tr).find("input[name='customerTel']").val();
                offsetReport.buildingNo = $(tr).find("input[name='buildingNo']").val();
                offsetReport.cxArea = $(tr).find("input[name='cxArea']").val();
                offsetReport.cxAmount = $(tr).find("input[name='cxAmount']").val();
                offsetReport.dealDate = $(tr).find("input[name='dealDate']").val();
                offsetReport.jsAmount = $(tr).find("input[name='jsAmount']").val();
                offsetReport.kpAmount = $(tr).find("input[name='kpAmount']").val();
                offsetReport.kpTaxAmount = $(tr).find("input[name='kpTaxAmount']").val();
                if (checkIsNumber(offsetReport.kpAmount) && checkIsNumber(offsetReport.kpTaxAmount)) {
                    offsetReport.kpTaxAmountAfter = parseFloat(parseFloat(offsetReport.kpAmount) - parseFloat(offsetReport.kpTaxAmount)).toFixed(2);
                }
                offsetReport.contractYdAmount = $(tr).find("input[name='contractYdAmount']").val();
                offsetReport.projectCode = $(tr).find("input[name='projectCode']").val();
                offsetReport.projectName = $(tr).find("input[name='projectName']").val();
                offsetReport.jsStatementType = $(tr).find("select[name='jsStatementType']").val();
                offsetReport.offSetFlag = 1;
                offsetReport.requestAmount = 0;
                offsetReport.serviceFeeDes = $(tr).find("input[name='serviceFeeDes']").val();
                offsetReport.isGlobalControl = $(tr).find("input[name='isGlobalControl']").val();

                offsetReport.hsCode = $(tr).find("select[name='selAccountProject']").val();
                offsetReport.hsName = $(tr).find("select[name='selAccountProject']").find("option:selected").attr("data-hsname");
                reportList.push(offsetReport)
            });
            param.reportList = reportList;
            //endregion

            //获取上传图片的id
            var fileIds = [];
            $(".layui-upload .item_img").each(function () {
                fileIds.push($(this).data("id"));
            });
            param.fileRecordMainIds = fileIds.join(",");
            return param;
        }

        function checkRate(taxrate) {
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
                return true;

            }
            return false;
        }

        // 成销确认书/佣金结算资料
        uploadRender('uploadSuccSaleImg', {fileTypeId: '1101', fileSourceId: '31', exts: 'jpg|png|jpeg|pdf'});
        // 项目合同
        uploadRender('uploadProjectContractImg', {fileTypeId: '1102', fileSourceId: '31', exts: 'jpg|png|jpeg|pdf'});
        // 分销合同
        uploadRender('uploadBranchContractImg', {fileTypeId: '1103', fileSourceId: '31', exts: 'jpg|png|jpeg|pdf'});
        // 其他
        uploadRender('uploadOtherImg', {fileTypeId: '1104', fileSourceId: '31', exts: 'jpg|png|jpeg|pdf'});
    }
)

//删除图片
function UPLOAD_IMG_DEL(divs) {
    $("#"+divs).remove();
}

addJsStatement = {}

addJsStatement.evalNormalTotal = function () {
//    addJsStatement.evalNormalItem("cxArea");
    addJsStatement.evalNormalItem("cxAmount");
    addJsStatement.evalNormalItem("vaildDYAmount");
    addJsStatement.evalNormalItem("vaildFYAmount");
    addJsStatement.evalNormalItem("contractYdAmount");
    addJsStatement.evalNormalItem("jsAmount");
    addJsStatement.evalNormalItem("kpAmount");
    addJsStatement.evalNormalItem("kpTaxAmount");
}
addJsStatement.evalNormalItem = function (itemName) {

    var isSpecialProject = $("#isSpecialProject").val();
    if ("vaildDYAmount" == itemName && (isSpecialProject == 1 || isSpecialProject == 2)) {
        return;
    }

    var itemTotal = 0.00;

    $("div[lay-id='normalBlock'] .layui-table-main tbody").find("input[name='" + itemName + "']").each(function () {
        var value = $(this).val();
        itemTotal = itemTotal + formatAccount(value);
    });
    itemTotal = formatAccount2(itemTotal);
    $("div[lay-id='normalBlock']  .layui-table-main tbody").find("span[name='" + itemName + "']").html(formatCurrency(itemTotal));
    $("div[lay-id='normalBlock']  .layui-table-main tbody").find("input[name='" + itemName + "Sum']").val(itemTotal);

    addJsStatement.evalAllItem(itemName);
}

addJsStatement.evalOffSetTotal = function () {
    //   addJsStatement.evalOffSetItem("cxArea");
    addJsStatement.evalOffSetItem("cxAmount");
    addJsStatement.evalOffSetItem("vaildDYAmount");
    addJsStatement.evalOffSetItem("vaildFYAmount");
    addJsStatement.evalOffSetItem("contractYdAmount");
    addJsStatement.evalOffSetItem("jsAmount");
    addJsStatement.evalOffSetItem("kpAmount");
    addJsStatement.evalOffSetItem("kpTaxAmount");
}
addJsStatement.evalOffSetItem = function (itemName) {
    var itemTotal = 0.00;

    $("div[lay-id='offsetBlock'] .layui-table-main tbody").find("input[name='" + itemName + "']").each(function () {
        var value = formatAccount($(this).val());
        if ((itemName == "contractYdAmount" || itemName == "jsAmount" || itemName == "kpAmount" || itemName == "kpTaxAmount")
            && value > 0) {
            value = -1 * value
        }
        itemTotal = itemTotal + value;
    });
    itemTotal = formatAccount2(itemTotal);
    $("div[lay-id='offsetBlock']  .layui-table-main tbody").find("span[name='" + itemName + "']").html(formatCurrency(itemTotal));
    $("div[lay-id='offsetBlock']  .layui-table-main tbody").find("input[name='" + itemName + "Sum']").val(itemTotal);

    addJsStatement.evalAllItem(itemName);
}


addJsStatement.evalAllItem = function (itemName) {
    var itemTotal = 0.00;

    $("#reportCard").find("input[name='" + itemName + "Sum']").each(function () {
        var value = $(this).val();
        itemTotal = itemTotal + formatAccount(value);
    });
    itemTotal = formatAccount2(itemTotal);
    $("#reportCard").find("input[name='" + itemName + "Total']").val(itemTotal);
    $("#reportCard").find("span[tag='" + itemName + "Total']").html(formatCurrency(itemTotal));

}


addJsStatement.delete = function (block, obj) {
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

    if (block == "normalBlock") {
        addJsStatement.evalNormalTotal();
    } else {
        addJsStatement.evalOffSetTotal();
    }

}

addJsStatement.formatter = function (o, offset) {
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


addJsStatement.openDialogCompany = function () {
    parent.layer.open({
        type: 2,
        title: '选择渠道公司',
        area: ['820px', '660px'],
        content: BASE_PATH + '/jsStatement/jsCompanyListPopup'
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                parent.layer.close(index);
                var companyId = $("#companyId").attr("data-companyId");

                if (formData.companyId != companyId) {
                    $("#companyId").attr("data-companyId", formData.companyId);
                    $("#companyId").attr("data-companyNo", formData.companyNo);
                    $("#companyId").val(formData.companyName);

                    addJsStatement.clearProject();
                    addJsStatement.clearHSCode();
                    addJsStatement.clearKHCode();
                    addJsStatement.clearFrameOaNo();
                    addJsStatement.clearReport();

                }
            }

        }
    });
}
addJsStatement.openDialogProject = function () {
    var companyNo = $("#companyId").attr("data-companyNo");
    if (isEmpty(companyNo)) {
        parent.msgErrorProp("请选择渠道公司！")
        return;
    }
    parent.layer.open({
        type: 2,
        title: '选择项目',
        area: ['820px', '660px'],
        content: BASE_PATH + '/jsStatement/jsProjectListPopup/' + companyNo
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                parent.layer.close(index);
                var projectNo = $("#projectNo").attr("data-projectNo");

                if (formData.projectNo != projectNo) {

                    if (formData.isSpecialProject != '2' && isEmpty(formData.signDate)) {
                        parent.msgError("该项目未签收入类合同！");
                        return;
                    }

                    $("#reportCard").show();
                    $("#projectNo").attr("data-projectNo", formData.projectNo);
                    $("#projectNo").val(formData.projectName);
                    $("#isSpecialProject").val(formData.isSpecialProject);

                    addJsStatement.clearHSCode();
                    addJsStatement.bindHSCode(formData.projectNo);
                    addJsStatement.getZkInfo(companyNo, formData.projectNo);
                    addJsStatement.clearKHCode();
                    addJsStatement.clearFrameOaNo();
                    addJsStatement.clearReport();

                }
            }

        }
    });
}
addJsStatement.openDialogKhCode = function () {

    var hsCode = $("#ddlHSCode").val();
    if (isEmpty(hsCode)) {
        parent.msgErrorProp("请选择核算主体！")
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
                var khCode = $("#khCode").attr("data-khCode");

                if (formData.khCode != khCode) {
                    $("#khCode").attr("data-khCode", formData.khCode);
                    $("#khCode").val(formData.khName);

                }
            }
        }
    });

}
addJsStatement.openDialogFrameOaNo = function () {
    var hsCode = $("#ddlHSCode").val();
    var projectNo = $("#projectNo").attr("data-projectNo");
    if (isEmpty(hsCode)) {
        parent.msgErrorProp("请选择核算主体！")
        return;
    }

    parent.layer.open({
        type: 2,
        title: '选择合同',
        area: ['820px', '660px'],
        content: BASE_PATH + '/jsStatement/jsFrameOaListPopup/' + hsCode + '/' + projectNo
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                parent.layer.close(index);
                var khCode = $("#frameOaNo").attr("data-frameOaNo");

                if (formData.frameOaNo != frameOaNo) {
                    $("#frameOaNo").attr("data-frameOaName", formData.frameOaName);
                    $("#frameOaNo").val(formData.frameOaNo);

                    $("#vendorId").attr("data-vendorId", formData.vendorId);
                    $("#vendorId").attr("data-vendorCode", formData.vendorCode);
                    $("#vendorId").val(formData.vendorName);
                }
            }
        }
    });
}
addJsStatement.openDialogNormalReport = function () {


    var companyNo = $("#companyId").attr("data-companyNo");
    var projectNo = $("#projectNo").attr("data-projectNo");

    if (isEmpty(companyNo) || isEmpty(projectNo)) {
        parent.msgErrorProp("请选择渠道公司和项目！")
        return;
    }
    var leftTable = $("div[lay-id='normalBlock'] .layui-table-fixed-l ");
    var mainTable = $("div[lay-id='normalBlock'] .layui-table-main ");
    var rightTable = $("div[lay-id='normalBlock'] .layui-table-fixed-r ");

    if ($(mainTable).find("tr[tag!=sum]").length >= 80) {
        parent.msgAlert("正常结算订单不允许超过80个！")
        return false;
    }
    $(leftTable).find(".layui-table-body").removeAttr("style");
    $(rightTable).find(".layui-table-body").removeAttr("style");

    var reportNoList = $("div[lay-id='offsetBlock'] .layui-table-main ").find("input[name='reportNo']").map(function () {
        return $(this).val();
    }).get().join(",");

    parent.layer.open({
        type: 2,
        title: '选择结算订单(正常)',
        area: ['820px', '660px'],
        content: BASE_PATH + '/jsStatement/jsNormalReportListPopup/' + companyNo + '/' + projectNo + '?&reportNoList=' + escape(reportNoList)
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formDataList = iframeWin.getFormData();

            var rowSize = $(mainTable).find("tr").length;

            //确认的回调
            if (formDataList != null) {
                var size = formDataList.length - 1;
                var line = 0;
                var v_reportNoList = [];
                // for check
                for (var i = size; i >= 0; i--) {
                    var formData = formDataList[i];
                    var reportNo = NullToEmpty(formData.reportId);

                    $(mainTable).find("input[name='reportNo']").each(function (j, obj) {
                        if (reportNo == $(obj).val()) {
                            line++;
                            v_reportNoList.push(reportNo);
                            formDataList.splice(i, 1);
                        }
                    });
                }
                if (rowSize - 1 + formDataList.length > 80) {
                    parent.msgAlert("正常结算订单不允许超过80个！")
                    return false;
                }

                if (line > 0) {
                    var reportNoStr = v_reportNoList.join(",");
                    parent.msgAlert(reportNoStr + "已存在，其他订单已添加成功！");
                }
                // for set
                for (var i = 0; i < formDataList.length; i++) {
                    var rowIndex = rowSize + i;
                    var formData = formDataList[i];

                    var reportNo = NullToEmpty(formData.reportId);
                    var buildingNo = NullToEmpty(formData.buildingNo);
                    var customerName = NullToEmpty(formData.customerName);
                    var customerTel = NullToEmpty(formData.customerTel);
                    var isGlobalControl = NullToZero(formData.isGlobalControl);
                    var isGlobalControlStr = NullToZero(isGlobalControl) == 0 ? "房源单控" : "项目总控";

                    var cxArea = NullToZero(formData.cxArea);
                    var cxAmount = NullToZero(formData.cxAmount);
                    var dealDate = isEmpty(formData.dealDate) ? "" : formatDate(formData.dealDate, "yyyy-MM-dd");
                    var dealDateStr = isEmpty(formData.dealDate) ? "-" : formatDate(formData.dealDate, "yyyy-MM-dd");
                    var vaildDYAmount = NullToZero(formData.vaildDYAmount);
                    var vaildFYAmount = NullToZero(formData.vaildFYAmount);
                    var oldJsTotalAmount = NullToZero(formData.vaildFYAmount);
                    var projectCode = NullToEmpty(formData.projectCode);
                    var projectName = NullToEmpty(formData.projectName);
                    var accountProjectNo = NullToEmpty(formData.accountProjectNo);
                    var accountProject = NullToEmpty(formData.accountProject);

                    var fixTrStr = '';
                    fixTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
                    fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-0 laytable-cell-numbers">' + rowIndex + '</div></td>';
                    fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-1">' + reportNo + '</div></td>';
                    fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-2">' + buildingNo + '</div></td>';
                    fixTrStr += '</tr>';

                    var contentTrStr = '';
                    contentTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
                    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-0 laytable-cell-numbers">' + rowIndex + '</div></td>';
                    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-1">' + reportNo + '</div></td>';
                    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-2">' + buildingNo + '</div></td>';
                    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-3">';
                    contentTrStr += '<input type="hidden" name="reportNo" value="' + reportNo + '">';
                    contentTrStr += '<input type="hidden" name="buildingNo" value="' + buildingNo + '">';
                    contentTrStr += '<input type="hidden" name="customerName" value="' + customerName + '">';
                    contentTrStr += '<input type="hidden" name="customerTel" value="' + customerTel + '">';
                    contentTrStr += '<input type="hidden" name="cxArea" value="' + cxArea + '">';
                    contentTrStr += '<input type="hidden" name="cxAmount" value="' + cxAmount + '">';
                    contentTrStr += '<input type="hidden" name="dealDate" value="' + dealDate + '">';
                    contentTrStr += '<input type="hidden" name="vaildDYAmount" value="' + vaildDYAmount + '">';
                    contentTrStr += '<input type="hidden" name="vaildFYAmount" value="' + vaildFYAmount + '">';
                    contentTrStr += '<input type="hidden" name="oldJsTotalAmount" value="' + oldJsTotalAmount + '">';
                    contentTrStr += '<input type="hidden" name="jsStatementType" value="-1">';


                    contentTrStr += '<input type="hidden" name="isGlobalControl" value="' + isGlobalControl + '">';
                    contentTrStr += '<input type="hidden" name="projectCode" value="' + projectCode + '">';
                    contentTrStr += '<input type="hidden" name="projectName" value="' + projectName + '">';
                    contentTrStr += '<input type="hidden" name="accountProjectNo" value="' + accountProjectNo + '">';
                    contentTrStr += '<input type="hidden" name="accountProject" value="' + accountProject + '">';

                    contentTrStr += '<span>' + customerName + '</span>'
                    contentTrStr += '</div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-4">' + projectName + '</div></td>';
                    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-5">' + isGlobalControlStr + '</div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-6">' + formatMoney(cxArea) + '</div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-7">' + formatMoney(cxAmount) + '</div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-8">' + dealDateStr + '</div></td>';

                    var isSpecialProject = $("#isSpecialProject").val();
                    if (isSpecialProject == 1 || isSpecialProject == 2) {
                        contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-9">-</div></td>';
                    } else {
                        contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-9">' + formatMoney(vaildDYAmount) + '</div></td>';
                    }

                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-10">' + formatMoney(vaildFYAmount) + '</div></td>';
                    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-11">';
                    contentTrStr += '   <input type="text" name="serviceFeeDes" maxlength="20" autocomplete="off"  class="renderInput ml" value="">'
                    contentTrStr += '</div></td>';

                    contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-1-0-12">';
                    contentTrStr += '<input type="text" name="contractYdAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalNormalItem(\'contractYdAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,0)" class="renderInput mr" value="">'
                    contentTrStr += '</div></td>';

                    contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-1-0-13">';
                    contentTrStr += '<input type="text" name="jsAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalNormalItem(\'jsAmount\');addJsStatement.evalJsStatementType(this);" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,0)" class="renderInput mr" value="">'
                    contentTrStr += '</div></td>';

                    contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-1-0-14">';
                    contentTrStr += '<input type="text" name="kpAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalNormalItem(\'kpAmount\');addJsStatement.setJsAmountAfter(\'normalBlock\',this,true)" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,0)" class="renderInput mr" value="">'
                    contentTrStr += '</div></td>';

                    contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-1-0-15">';
                    contentTrStr += '<input type="text" name="kpTaxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalNormalItem(\'kpTaxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,0)" class="renderInput mr" value="">'
                    contentTrStr += '</div></td>';

                    contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-16">';
                    contentTrStr += '<span name="jsStatementType">-</span>';
                    contentTrStr += '</div></td>';
                    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-17"></div></td>';
                    contentTrStr += '</tr>';

                    var rightTrStr = '';
                    rightTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
                    rightTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-17">'
                    rightTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="javascript:addJsStatement.delete(\'normalBlock\',this)">删除</a></div></td>';
                    rightTrStr += '</tr>';

                    $(leftTable).find("tr[tag='sum']").before(fixTrStr);
                    $(mainTable).find("tr[tag='sum']").before(contentTrStr);
                    $(rightTable).find("tr[tag='sum']").before(rightTrStr);
                }

                addJsStatement.evalNormalTotal();

                parent.layer.close(index);

            }
        }
    });


}
addJsStatement.openDialogOffsetReport = function () {
    var companyNo = $("#companyId").attr("data-companyNo");
    var projectNo = $("#projectNo").attr("data-projectNo");

    if (isEmpty(companyNo) || isEmpty(projectNo)) {
        parent.msgErrorProp("请选择渠道公司和项目！")
        return;
    }

    var leftTable = $("div[lay-id='offsetBlock'] .layui-table-fixed-l ");
    var mainTable = $("div[lay-id='offsetBlock'] .layui-table-main ");
    var rightTable = $("div[lay-id='offsetBlock'] .layui-table-fixed-r ");

    if ($(mainTable).find("tr[tag!=sum]").length >= 20) {
        parent.msgAlert("选择冲抵结算订单已达上限！")
        return false;
    }
    $(leftTable).find(".layui-table-body").removeAttr("style");
    $(rightTable).find(".layui-table-body").removeAttr("style");

    var reportNoList = $("div[lay-id='normalBlock'] .layui-table-main ").find("input[name='reportNo']").map(function () {
        return $(this).val();
    }).get().join(",");

    parent.layer.open({
        type: 2,
        title: '选择结算订单(冲抵)',
        area: ['820px', '660px'],
        content: BASE_PATH + '/jsStatement/jsOffsetReportListPopup/' + companyNo + '/' + projectNo + '?&reportNoList=' + escape(reportNoList)
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            var rowIndex = $(mainTable).find("tr").length;

            //确认的回调
            if (formData != null) {
                var reportNo = NullToEmpty(formData.reportId);
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

                var buildingNo = NullToEmpty(formData.buildingNo);
                var customerName = NullToEmpty(formData.customerName);
                var customerTel = NullToEmpty(formData.customerTel);
                var projectCode = NullToEmpty(formData.projectCode);
                var projectName = NullToEmpty(formData.projectName);
                var isGlobalControl = NullToZero(formData.isGlobalControl);
                var isGlobalControlStr = NullToZero(isGlobalControl) == 0 ? "房源单控" : "项目总控";

                var cxArea = NullToZero(formData.cxArea);
                var cxAmount = NullToZero(formData.cxAmount);
                var dealDate = isEmpty(formData.dealDate) ? "" : formatDate(formData.dealDate, "yyyy-MM-dd");
                var dealDateStr = isEmpty(formData.dealDate) ? "-" : formatDate(formData.dealDate, "yyyy-MM-dd");
                var vaildDYAmount = NullToZero(formData.vaildDYAmount);
                var vaildFYAmount = NullToZero(formData.vaildFYAmount);
                var oldJsTotalAmount = NullToZero(formData.oldJsTotalAmount);

                var accountProjectNo = NullToEmpty(formData.accountProjectNo);
                var accountProject = NullToEmpty(formData.accountProject);
                var progress = NullToEmpty(formData.progress);
                var confirmStatus = NullToEmpty(formData.confirmStatus);

                var fixTrStr = '';
                fixTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
                fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-0 laytable-cell-numbers">' + rowIndex + '</div></td>';
                fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-1">' + reportNo + '</div></td>';
                fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-2">' + buildingNo + '</div></td>';
                fixTrStr += '</tr>';

                var contentTrStr = '';
                contentTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
                contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-0 laytable-cell-numbers">' + rowIndex + '</div></td>';
                contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-1">' + reportNo + '</div></td>';
                contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-2">' + buildingNo + '</div></td>';
                contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-3">';
                contentTrStr += '<input type="hidden" name="reportNo" value="' + reportNo + '">';
                contentTrStr += '<input type="hidden" name="buildingNo" value="' + buildingNo + '">';
                contentTrStr += '<input type="hidden" name="customerName" value="' + customerName + '">';
                contentTrStr += '<input type="hidden" name="customerTel" value="' + customerTel + '">';
                contentTrStr += '<input type="hidden" name="cxArea" value="' + cxArea + '">';
                contentTrStr += '<input type="hidden" name="cxAmount" value="' + cxAmount + '">';
                contentTrStr += '<input type="hidden" name="dealDate" value="' + dealDate + '">';
                contentTrStr += '<input type="hidden" name="vaildDYAmount" value="' + vaildDYAmount + '">';
                contentTrStr += '<input type="hidden" name="vaildFYAmount" value="' + vaildFYAmount + '">';
                contentTrStr += '<input type="hidden" name="oldJsTotalAmount" value="' + oldJsTotalAmount + '">';
                contentTrStr += '<input type="hidden" name="isGlobalControl" value="' + isGlobalControl + '">';
                contentTrStr += '<input type="hidden" name="projectCode" value="' + projectCode + '">';
                contentTrStr += '<input type="hidden" name="projectName" value="' + projectName + '">';

                contentTrStr += '<span>' + customerName + '</span>'
                contentTrStr += '</div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-4">' + projectName + '</div></td>';
                contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-5">' + isGlobalControlStr + '</div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-6">' + formatMoney(cxArea) + '</div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-7">' + formatMoney(cxAmount) + '</div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-8">' + dealDateStr + '</div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-9">' + formatMoney(vaildDYAmount) + '</div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-10">' + formatMoney(vaildFYAmount) + '</div></td>';
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-11">';
                contentTrStr += '   <input type="text" name="serviceFeeDes" maxlength="20" autocomplete="off"  class="renderInput ml" value="">'
                contentTrStr += '</div></td>';

                contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-2-0-12">';
                contentTrStr += '<input type="text" name="contractYdAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalOffSetItem(\'contractYdAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,1)" class="renderInput mr" value="">'
                contentTrStr += '</div></td>';

                contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-2-0-13">';
                contentTrStr += '<input type="text" name="jsAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalOffSetItem(\'jsAmount\');" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,1)" class="renderInput mr" value="">'
                contentTrStr += '</div></td>';

                contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-2-0-14">';
                contentTrStr += '<input type="text" name="kpAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalOffSetItem(\'kpAmount\');addJsStatement.setJsAmountAfter(\'offsetBlock\',this,true)" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,1)" class="renderInput mr" value="">'
                contentTrStr += '</div></td>';

                contentTrStr += '<td  align="right"><div class="layui-table-cell laytable-cell-2-0-15">';
                contentTrStr += '<input type="text" name="kpTaxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:addJsStatement.evalOffSetItem(\'kpTaxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" onchange="javascript:addJsStatement.formatter(this,1)" class="renderInput mr" value="">'
                contentTrStr += '</div></td>';


                contentTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-2-0-16">';
                contentTrStr += ' <select  name ="jsStatementType" lay-ignore class="renderInput">"';
                contentTrStr += '   <option value="-1">请选择</option>';
                contentTrStr += '   <option value="1">返佣</option>';
                contentTrStr += '   <option value="2">垫佣</option>';
                contentTrStr += ' </select>';
                contentTrStr += '</div></td>';

                // 核算主体
                contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-2-0-17">';
                contentTrStr += '     <select  name="selAccountProject" lay-ignore class="renderInput">';
                if (progress == '13505' && confirmStatus == '13601') {
                    contentTrStr += '     <option value="' + accountProjectNo + '" data-hsname="' + accountProject + '">' + accountProjectNo + "_" + accountProject + '</option>';
                } else {
                    contentTrStr += addJsStatement.getOALnkAccountProjectSelectOption(projectNo, accountProjectNo)
                }
                contentTrStr += '     </select>';
                contentTrStr += '</div></td>';

                contentTrStr += '<td><div class="layui-table-cell laytable-cell-2-0-18"></div></td>';
                contentTrStr += '</tr>';

                var rightTrStr = '';
                rightTrStr += '<tr data-index="' + (rowIndex - 1) + '" >'
                rightTrStr += '<td align="center" ><div class="layui-table-cell laytable-cell-2-0-18">'
                rightTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="javascript:addJsStatement.delete(\'offsetBlock\',this)">删除</a></div></td>';
                rightTrStr += '</tr>';

                $(leftTable).find("tr[tag='sum']").before(fixTrStr);
                $(mainTable).find("tr[tag='sum']").before(contentTrStr);
                $(rightTable).find("tr[tag='sum']").before(rightTrStr);

                addJsStatement.evalOffSetTotal();

                parent.layer.close(index);

            }
        }
    });


}


addJsStatement.clearProject = function () {
    $("#projectNo").val("");
    $("#projectNo").attr("data-projectNo", "");
    $("#projectNo").attr("data-projectNo")
    $("#isSpecialProject").val();
    $("#reportCard").hide();
}
addJsStatement.clearHSCode = function () {
    $("#ddlHSCode").empty().html("");
    form.render('select');
}
addJsStatement.bindHSCode = function (projectNo) {
    if (!isEmpty(projectNo)) {
        var url = BASE_PATH + '/jsStatement/queryJsHSCodeList';
        var params = {projectNo: projectNo};
        restPost(url, params, function (data) {
            var result = "<option value='' data-hsName=''>请选择核算主体</option>";
            if (data.returnData.length == 1) {
                result = "";
            }
            $.each(data.returnData, function (n, value) {
                result += "<option value='" + value.hsCode + "' data-hsName='" + value.hsname + "'>" + value.hsCode + "_" + value.hsname + "</option>";
            });
            $("#ddlHSCode").empty().html("").append(result);
            form.render('select');
        }, function (data) {

        });

    }

}
addJsStatement.getZkInfo = function (companyNo, projectNo) {
    if (!isEmpty(companyNo) && !isEmpty(projectNo)) {
        var url = BASE_PATH + '/jsStatement/getZkInfo';
        var params = {companyNo: companyNo, projectNo: projectNo};
        ajaxGet(url, params, function (data) {
            var d = data.returnData
            var xm_ZK_vaildDYAmount = NullToZero(d.xm_ZK_vaildDYAmount);
            var xm_ZK_vaildFYAmount = NullToZero(d.xm_ZK_vaildFYAmount);
            var com_ZK_vaildDYAmount = NullToZero(d.com_ZK_vaildDYAmount);
            var com_ZK_vaildFYAmount = NullToZero(d.com_ZK_vaildFYAmount);

            $("#reportCard").find("input[name='xm_ZK_vaildDYAmount']").val(xm_ZK_vaildDYAmount);
            $("#reportCard").find("input[name='com_ZK_vaildDYAmount']").val(com_ZK_vaildDYAmount);
            $("#reportCard").find("input[name='xm_ZK_vaildFYAmount']").val(xm_ZK_vaildFYAmount);
            $("#reportCard").find("input[name='com_ZK_vaildFYAmount']").val(com_ZK_vaildFYAmount);

            var isSpecialProject = $("#isSpecialProject").val();
            if (isSpecialProject == 1 || isSpecialProject == 2) {
                $("#reportCard").find("span[tag='xm_ZK_vaildDYAmount']").html("-");
                $("#reportCard").find("span[tag='com_ZK_vaildDYAmount']").html("-");
            } else {
                $("#reportCard").find("span[tag='xm_ZK_vaildDYAmount']").html(formatCurrency(xm_ZK_vaildDYAmount));
                $("#reportCard").find("span[tag='com_ZK_vaildDYAmount']").html(formatCurrency(com_ZK_vaildDYAmount));
            }
            $("#reportCard").find("span[tag='xm_ZK_vaildFYAmount']").html(formatCurrency(xm_ZK_vaildFYAmount));
            $("#reportCard").find("span[tag='com_ZK_vaildFYAmount']").html(formatCurrency(com_ZK_vaildFYAmount));

        }, function (data) {

        });
    }
}
addJsStatement.clearKHCode = function () {
    $("#khCode").val("");
    $("#khCode").attr("data-khCode", "");
}
addJsStatement.clearFrameOaNo = function () {
    $("#frameOaNo").val("");
    $("#frameOaNo").attr("data-frameOaNo", "");

    $("#vendorId").val("");
    $("#vendorId").attr("data-vendorId", "");
    $("#vendorId").attr("data-vendorCode", "");

}
addJsStatement.clearReport = function () {

    $("div[lay-id='normalBlock']").find("tbody tr[tag!=sum]").empty().remove();
    $("div[lay-id='offsetBlock']").find("tbody tr[tag!=sum]").empty().remove();


}
addJsStatement.clearUploadFile = function () {

}

addJsStatement.offSetFlagChange = function (offSetValue) {

    var offSetDiv = $("div[tag='offSet']");

    var leftTBody = $("div[lay-id='offsetBlock'] .layui-table-fixed-l .layui-table-body tbody");
    var mainTBody = $("div[lay-id='offsetBlock']  .layui-table-main tbody");
    var rightTBody = $("div[lay-id='offsetBlock']  .layui-table-fixed-r .layui-table-body tbody");

    if (offSetValue == 0) {
        if ($(mainTBody).find("tr[tag!='sum']").length == 0) {
            $(offSetDiv).hide();
        } else {
            parent.msgConfirm("冲抵房源数据已添加，是否确认清空？", function () {
                $(leftTBody).find("tr[tag!='sum']").empty().remove();
                $(mainTBody).find("tr[tag!='sum']").empty().remove();
                $(rightTBody).find("tr[tag!='sum']").empty().remove();
                $(offSetDiv).hide();
                addJsStatement.evalOffSetTotal();
            }, function () {
                $('input[tag="offSetFlag"]').removeAttr("checked")
                $('input[tag="offSetFlag"]:eq(0)').prop('checked', 'checked');
                form.render("radio");
            });
        }
    } else {
        $(offSetDiv).show();
    }
}

addJsStatement.getOALnkAccountProjectSelectOption = function (projectNo, accountProjectCode) {
    var url = BASE_PATH + "/jsStatement/queryJsHSCodeList";
    var result = "<option value=''>请选择</option>";
    if (!isEmpty(projectNo)) {
        var params = {projectNo: projectNo};
        $.ajax({
            url: url,
            data: params,
            async: false,
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                $.each(data.returnData, function (n, value) {
                    var hsCode = NullToEmpty(value.hsCode);
                    var hsname = NullToEmpty(value.hsname);

                    result += "<option ";
                    if (data.returnData.length == 1 || accountProjectCode == hsCode) {
                        result += " selected ";
                    }
                    result += "value='" + hsCode + "' data-hsname='" + hsname + "'>" + hsCode + "_" + hsname + "</option>";
                });
            }
        });
    }

    return result;
}

addJsStatement.evalJsStatementType = function (obj) {
    var tr = $(obj).parents("tr");
    addJsStatement.evalJsStatementTypeByTr(tr);
}
addJsStatement.evalJsStatementTypeByTr = function (tr) {

    var jsAmount = $(tr).find("input[name='jsAmount']").val(); // 本次结算金额
    // 没有请款的场合
    if (jsAmount == "" || Number(jsAmount) == 0) {
        addJsStatement.setJsStatementType(tr, 0);
        return;
    }

    var vaildDYAmount = $(tr).find("input[name='vaildDYAmount']").val();  // 可垫
    var vaildFYAmount = $(tr).find("input[name='vaildFYAmount']").val();  // 可返

    // 返佣：请款金额≤可返
    if (Number(jsAmount) <= Number(vaildFYAmount) && (Number(vaildFYAmount) > 0)) {
        addJsStatement.setJsStatementType(tr, 1);
        return;
    }

    // 垫佣：请款金额≤可垫
    if (Number(jsAmount) <= Number(vaildDYAmount) && (Number(vaildDYAmount) > 0)) {
        addJsStatement.setJsStatementType(tr, 2);
        return;
    }
    // 如果是大定垫佣的场合 ，默认可垫
    var isSpecialProject = $("#isSpecialProject").val();
    if (isSpecialProject == 1 || isSpecialProject == 2) {
        addJsStatement.setJsStatementType(tr, 2);
        return;
    }
    addJsStatement.setJsStatementType(tr, 0);


}
addJsStatement.setJsStatementType = function (tr, val) {
    if (val == 1) {
        $(tr).find("span[name='jsStatementType']").text("返佣");
        $(tr).find("input[name='jsStatementType']").val("1");
    } else if (val == 2) {
        $(tr).find("span[name='jsStatementType']").text("垫佣");
        $(tr).find("input[name='jsStatementType']").val("2");
    } else {
        $(tr).find("span[name='jsStatementType']").text("-");
        $(tr).find("input[name='jsStatementType']").val("-1");
    }
}

addJsStatement.setJsAmountAfter = function (block, obj, flag) {
    var taxRate = $("input[name='kpRate']:checked").val();
    if (isEmpty(taxRate)) {
        return;
    }
    var bef = "kpAmount";
    var tax = "kpTaxAmount";
    var befAmount = $(obj).parents("tr").find("input[name='" + bef + "']").val();
    if (!isEmpty(befAmount) && !isEmpty(taxRate)) {
        var taxAmount = parseFloat(befAmount - (befAmount / (1 + parseFloat(taxRate)))).toFixed(2);
        if (block == "offsetBlock" && taxAmount > 0) {
            taxAmount = -1 * taxAmount;
        }
        $(obj).parents("tr").find("input[name='" + tax + "']").val(taxAmount);

    }

    if (flag && block == "normalBlock") {
        addJsStatement.evalNormalItem(tax);
    }
    if (flag && block == "offsetBlock") {
        addJsStatement.evalOffSetItem(tax);
    }

}
addJsStatement.kpRateChange = function () {
    var itemName = "kpAmount";
    var taxName = "kpTaxAmount";
    $("div[lay-id='normalBlock'] .layui-table-main tbody").find("input[name='" + itemName + "']").each(function () {
        addJsStatement.setJsAmountAfter('normalBlock', this, false)
    });
    addJsStatement.evalNormalItem(taxName);

    $("div[lay-id='offsetBlock'] .layui-table-main tbody").find("input[name='" + itemName + "']").each(function () {
        addJsStatement.setJsAmountAfter('offsetBlock', this, false)
    });
    addJsStatement.evalOffSetItem(taxName);
}


