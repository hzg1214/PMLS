var form;
var formSelects;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;
    form = layui.form;
    formSelects = layui.formSelects;


    init();

    function init() {

        // 组织架构
        organizationLinkAge();
        $("#orgYear").attr("disabled", "disabled");
        form.render('select'); //刷新select选择框渲染

        formSelects.render('debtsType');
        formSelects.render('revenueNode');
    }


    function tableRender() {
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            , id: 'contentReload'
            , page: true
            , height: "full"
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , even: false //开启隔行背景
            , method: 'post'
            , loading: false
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
    }

    function setCols() {

        // yj: 应计, ys: 应收, kp: 开票
        var revenueNodeList = formSelects.value('revenueNode', 'val');             //  收入节点

        // zx: 自行计提 ,cf: 惩罚性计提 ,df: 抵房计提 ,ss: 诉讼计提
        var debtsTypeList = formSelects.value('debtsType', 'val');                 //  坏账类型


        var cols = [];
        cols.push(getRow1(revenueNodeList, debtsTypeList));
        cols.push(getRow2(revenueNodeList, debtsTypeList));
        cols.push(getRow3(revenueNodeList, debtsTypeList));
        cols.push(getRow4(revenueNodeList, debtsTypeList));

        return cols;
    }

    function getRow1(revenueNodeList, debtsTypeList) {
        var row1 = [];

        row1.push({type: 'numbers', title: '序号', width: 60, rowspan: 4});                            //  1
        row1.push({field: '', title: '基础信息', align: 'center', width: 150, colspan: 24});         //  2  - 25
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "yj")) {
            var yjcol = debtsTypeList.length == 0 ? 91 : 3 + debtsTypeList.length * 22;
            row1.push({field: '', title: '应计坏账', align: 'center', width: 150, colspan: yjcol});     //  26 - 116
        }
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "ys")) {
            var yscol = debtsTypeList.length == 0 ? 44 : debtsTypeList.length * 11;
            row1.push({field: '', title: '应收坏账', align: 'center', width: 150, colspan: yscol});     //  117 - 160
        }
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "kp")) {
            var kpcol = debtsTypeList.length == 0 ? 44 : debtsTypeList.length * 11;
            row1.push({field: '', title: '开票坏账', align: 'center', width: 150, colspan: kpcol});     //  161 - 204
        }
        return row1;
    }

    function getRow2(revenueNodeList, debtsTypeList) {
        var row2 = [];
        //基础信息
        row2.push({field: '', title: '业绩信息', align: 'center', width: 150, colspan: 8});       // 2 - 9
        row2.push({field: '', title: '案件信息', align: 'center', width: 150, colspan: 16});      // 10 - 25

        // 应计坏账
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "yj")) {
            row2.push({field: '', title: '合计', align: 'center', width: 150, colspan: 3});       // 26 - 28

            if (debtsTypeList.length == 0 || contains(debtsTypeList, "zx")) {
                row2.push({field: '', title: '自行计提', align: 'center', width: 150, colspan: 22});      // 29 - 50
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "cf")) {
                row2.push({field: '', title: '惩罚性计提', align: 'center', width: 150, colspan: 22});    // 51 - 72
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "df")) {
                row2.push({field: '', title: '抵房计提', align: 'center', width: 150, colspan: 22});      // 73 - 94
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "ss")) {
                row2.push({field: '', title: '诉讼计提', align: 'center', width: 150, colspan: 22});      // 95 - 116
            }
        }
        // 应收坏账
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "ys")) {
            // 117 - 120
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "zx")) {
                row2.push({field: '', title: '自行计提', align: 'center', width: 150, colspan: 11});      // 117 - 127
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "cf")) {
                row2.push({field: '', title: '惩罚性计提', align: 'center', width: 150, colspan: 11});    // 128 - 138
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "df")) {
                row2.push({field: '', title: '抵房计提', align: 'center', width: 150, colspan: 11});      // 139 - 149
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "ss")) {
                row2.push({field: '', title: '诉讼计提', align: 'center', width: 150, colspan: 11});      // 150 - 160
            }
        }
        // 开票坏账
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "kp")) {
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "zx")) {
                row2.push({field: '', title: '自行计提', align: 'center', width: 150, colspan: 11});      // 161 - 171
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "cf")) {
                row2.push({field: '', title: '惩罚性计提', align: 'center', width: 150, colspan: 11});    // 172 - 182
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "df")) {
                row2.push({field: '', title: '抵房计提', align: 'center', width: 150, colspan: 11});      // 183 - 193
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "ss")) {
                row2.push({field: '', title: '诉讼计提', align: 'center', width: 150, colspan: 11});      // 194 - 204
            }
        }
        return row2;
    }

    function getRow3(revenueNodeList, debtsTypeList) {
        var row3 = [];
        //region 基础信息
        //基础信息-业绩信息
        row3.push({field: 'A2', title: '核算主体编号', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A3', title: '核算主体', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A4', title: '业绩归属区域', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A5', title: '业绩归属城市', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A6', title: '业绩归属中心', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A7', title: '项目归属城市', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A8', title: '项目归属部门', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A9', title: '收入归属城市', align: 'center', width: 150, colspan: 1, rowspan: 2});
        //基础信息-案件信息
        row3.push({field: 'A10', title: '项目编号', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A11', title: '楼盘名', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A12', title: '合作方名称', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A13', title: '是否大客户', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A14', title: '大客户名称', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A15', title: '报备编号', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: 'A16', title: '楼室号', align: 'center', width: 150, colspan: 1, rowspan: 2});
        row3.push({field: '', title: '大定', align: 'center', width: 150, colspan: 4, rowspan: 1});
        row3.push({field: '', title: '成销', align: 'center', width: 150, colspan: 3, rowspan: 1});
        row3.push({field: '', title: '收入', align: 'center', width: 150, colspan: 2, rowspan: 1});
        //endregion
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "yj")) {
            //region 应计坏账
            // 应计坏账-坏账合计
            row3.push({field: '', title: '计提坏账合计', align: 'center', width: 150, colspan: 2, rowspan: 1});
            row3.push({field: 'A28', title: '余额', align: 'center', width: 150, colspan: 1, rowspan: 2});
            // 应计坏账-自行计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "zx")) {
                row3.push({field: '', title: '小计', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 4, rowspan: 1});
            }
            // 应计坏账-惩罚性计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "cf")) {
                row3.push({field: '', title: '小计', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 4, rowspan: 1});
            }
            // 应计坏账-抵房计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "df")) {
                row3.push({field: '', title: '小计', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 4, rowspan: 1});
            }
            // 应计坏账-诉讼计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "ss")) {
                row3.push({field: '', title: '小计', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 4, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 4, rowspan: 1});
            }
            //endregion
        }
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "ys")) {
            //region 应收坏账
            // 应收坏账-自行计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "zx")) {
                row3.push({field: 'A117', title: '计提金额小计', align: 'center', width: 150, colspan: 1, rowspan: 2});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 2, rowspan: 1});
            }
            // 应收坏账-惩罚性计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "cf")) {
                row3.push({field: 'A128', title: '计提金额小计', align: 'center', width: 150, colspan: 1, rowspan: 2});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 2, rowspan: 1});
            }
            // 应收坏账-抵房计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "df")) {
                row3.push({field: 'A139', title: '计提金额小计', align: 'center', width: 150, colspan: 1, rowspan: 2});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 2, rowspan: 1});
            }
            // 应收坏账-诉讼计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "ss")) {
                row3.push({field: 'A150', title: '计提金额小计', align: 'center', width: 150, colspan: 1, rowspan: 2});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 2, rowspan: 1});
            }
            //endregion
        }
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "kp")) {
            //region 开票坏账
            // 开票坏账-自行计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "zx")) {
                row3.push({field: 'A161', title: '计提金额小计', align: 'center', width: 150, colspan: 1, rowspan: 2});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 2, rowspan: 1});
            }
            // 开票坏账-惩罚性计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "cf")) {
                row3.push({field: 'A172', title: '计提金额小计', align: 'center', width: 150, colspan: 1, rowspan: 2});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 2, rowspan: 1});
            }
            // 开票坏账-抵房计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "df")) {
                row3.push({field: 'A183', title: '计提金额小计', align: 'center', width: 150, colspan: 1, rowspan: 2});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 2, rowspan: 1});
            }
            // 开票坏账-诉讼计提
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "ss")) {
                row3.push({field: 'A194', title: '计提金额小计', align: 'center', width: 150, colspan: 1, rowspan: 2});
                row3.push({field: '', title: '第一次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第二次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第三次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第四次', align: 'center', width: 150, colspan: 2, rowspan: 1});
                row3.push({field: '', title: '第五次', align: 'center', width: 150, colspan: 2, rowspan: 1});
            }
            //endregion
        }
        return row3;
    }

    function getRow4(revenueNodeList, debtsTypeList) {
        var row4 = [];
        //region 基础信息
        row4.push({field: 'A17', title: '大定面积', align: 'center', width: 150, colspan: 1, rowspan: 1});
        row4.push({field: 'A18', title: '大定总价', align: 'center', width: 150, colspan: 1, rowspan: 1});
        row4.push({field: 'A19', title: '大定日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
        row4.push({field: 'A20', title: '大定审核通过日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
        row4.push({field: 'A21', title: '成销面积', align: 'center', width: 150, colspan: 1, rowspan: 1});
        row4.push({field: 'A22', title: '成销总价', align: 'center', width: 150, colspan: 1, rowspan: 1});
        row4.push({field: 'A23', title: '成销日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
        row4.push({field: 'A24', title: '应计收入', align: 'center', width: 150, colspan: 1, rowspan: 1});
        row4.push({field: 'A25', title: '应收收入', align: 'center', width: 150, colspan: 1, rowspan: 1});
        //endregion

        if (revenueNodeList.length == 0 || contains(revenueNodeList, "yj")) {
            //region 应计坏账
            row4.push({field: 'A26', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
            row4.push({field: 'A27', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});

            if (debtsTypeList.length == 0 || contains(debtsTypeList, "zx")) {
                // region自行计提
                row4.push({field: 'A29', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A30', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});

                row4.push({field: 'A31', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A32', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A33', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A34', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A35', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A36', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A37', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A38', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A39', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A40', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A41', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A42', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A43', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A44', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A45', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A46', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A47', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A48', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A49', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A50', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                //endregion
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "cf")) {
                //region 惩罚性计提
                row4.push({field: 'A51', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A52', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A53', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A54', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A55', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A56', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A57', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A58', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A59', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A60', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A61', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A62', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A63', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A64', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A65', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A66', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A67', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A68', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A69', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A70', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A71', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A72', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});

                //endregion
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "df")) {
                //region 抵房计提
                row4.push({field: 'A73', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A74', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A75', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A76', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A77', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A78', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A79', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A80', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A81', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A82', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A83', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A84', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A85', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A86', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A87', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A88', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A89', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A90', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A91', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A92', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A93', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A94', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                //endregion
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "ss")) {
                //region 诉讼计提
                row4.push({field: 'A95', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A96', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A97', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A98', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A99', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A100', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A101', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A102', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A103', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A104', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A105', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A106', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A107', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A108', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A109', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A110', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A111', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A112', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A113', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A114', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A115', title: '计入考核金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A116', title: '计提考核主体', align: 'center', width: 150, colspan: 1, rowspan: 1});
                //endregion
            }
            //endregion
        }
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "ys")) {
            //region  应收坏账

            if (debtsTypeList.length == 0 || contains(debtsTypeList, "zx")) {
                // region 自行计提
                row4.push({field: 'A118', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A119', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A120', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A121', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A122', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A123', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A124', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A125', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A126', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A127', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                // endregion
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "cf")) {
                // region 惩罚性计提
                row4.push({field: 'A129', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A130', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A131', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A132', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A133', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A134', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A135', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A136', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A137', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A138', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});

                // endregion
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "df")) {
                // region 抵房计提
                row4.push({field: 'A140', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A141', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A142', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A143', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A144', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A145', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A146', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A147', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A148', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A149', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});

                // endregion
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "ss")) {
                // region 诉讼计提
                row4.push({field: 'A151', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A152', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A153', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A154', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A155', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A156', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A157', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A158', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A159', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A160', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});

                // endregion
            }
            // endregion
        }
        if (revenueNodeList.length == 0 || contains(revenueNodeList, "kp")) {
            //region  开票坏账

            if (debtsTypeList.length == 0 || contains(debtsTypeList, "zx")) {
                // region 自行计提
                row4.push({field: 'A162', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A163', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A164', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A165', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A166', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A167', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A168', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A169', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A170', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A171', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});

                // endregion
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "cf")) {
                // region 惩罚性计提
                row4.push({field: 'A173', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A174', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A175', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A176', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A177', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A178', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A179', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A180', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A181', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A182', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});

                // endregion
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "df")) {
                // region 抵房计提
                row4.push({field: 'A184', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A185', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A186', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A187', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A188', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A189', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A190', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A191', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A192', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A193', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});

                // endregion
            }
            if (debtsTypeList.length == 0 || contains(debtsTypeList, "ss")) {
                // region 诉讼计提
                row4.push({field: 'A195', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A196', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A197', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A198', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A199', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A200', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A201', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A202', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A203', title: '计提日期', align: 'center', width: 150, colspan: 1, rowspan: 1});
                row4.push({field: 'A204', title: '计提金额', align: 'center', width: 150, colspan: 1, rowspan: 1});
                // endregion
            }
            // endregion
        }
        return row4;
    }

    var active = {
        reload: function () {
            if (true == valid()) {
                var optionsData = getParamData();
                reloadData(optionsData);
            }
        },
        export: function () {
            if (true == valid()) {
                var param = getParamData();
                var loadIndex = parent.layer.load(2, {shade: [0.1]});

                var url = BASE_PATH + "/badDebts/export";
                restPost(url, param,
                    function (data) {
                        parent.layer.close(loadIndex);
                        if (data.returnCode == 200) {
                            window.location.href = data.returnData;
                        } else {
                            parent.msgAlert(data.returnMsg);
                        }
                    },
                    function (data) {
                        parent.layer.close(loadIndex);
                        parent.msgAlert(data.returnMsg);
                    }
                );
            }
        }
    }

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index = layer.load(2);
        tableRender();
        table.reload('contentReload', {
            url: BASE_PATH + '/badDebts/queryList',
            //data: getData(),
            cols: setCols(),
            where: optionsData,
            page: {
                curr: 1 //重新从第 1 页开始
            },
            done: function (res, curr, count) {
                layer.close(index);
            }
        });
    }

    function valid() {
        var organization = $("#orgYear").val();                             // 架构年份
        var regionCodes = formSelects.value('region', 'valStr');           // HBL归属区域
        var areaCityCodes = formSelects.value('areaCity', 'valStr');       // HBL归属板块
        var cityIds = formSelects.value('city', 'valStr');                  // HBL归属城市

        if (false == checkComm(organization, areaCityCodes, regionCodes, cityIds)) {
            return false;
        }

        return true;
    }

    function getParamData() {
        var param = {};

        param.organization = $("#orgYear").val();                                       // 架构年份
        param.regionCodes = formSelects.value('region', 'valStr');                    // HBL归属区域
        param.areaCityCodes = formSelects.value('areaCity', 'valStr');                // HBL归属板块
        param.cityIds = formSelects.value('city', 'valStr');                           // HBL归属城市

        param.project = $("#project").val();

        param.debtsTypes = formSelects.value('debtsType', 'valStr');                  // 坏账类型
        param.revenueNodes = formSelects.value('revenueNode', 'valStr');             // 收入节点

        return param;
    }

});