/**
 * Created by haidan on 2020/6/16.
 */
var formSelects;
var form;

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
        //架构年份
        yearlyInit();
        form.render('select'); //刷新select选择框渲染
    }


    function yearlyInit() {
        var defaultYear = new Date().getFullYear();
        var end = defaultYear;
        var start = 2017;

        var result = "";
        for (var i = end; i >= start; i--) {
            var value = i;
            if (i == defaultYear) {
                result += "<option value='" + value + "' selected>" + i + "</option>";
            } else {
                result += "<option value='" + value + "'>" + i + "</option>";
            }
        }
        $("#orgYear").empty().append(result);

        form.render('select')
    }

    function tableRender() {
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            //, url: BASE_PATH + '/sceneTrade/qSceneEstate'
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
            , done: function (res, curr, count) {

                console.info(res);
                console.info(curr);
                console.info(count);
                //style="font-weight:bold"
            }

        });
    }

    function setCols() {
        var row = [
            {field: 'rowNum', title: '序号', width: 60},
            {field: 'gsRegion', title: '项目归属区域', width: 120, align: 'center'},
            {field: 'gsCity', title: '项目归属城市', width: 120, align: 'center'},
            {field: 'szCity', title: '项目所在城市', width: 120, align: 'center'},
            {field: 'gsDepartment', title: '项目归属项目部', width: 140, align: 'center'},
            {field: 'projectNo', title: '项目编号', width: 120, align: 'center'},
            {field: 'projectName', title: '项目名称', width: 150, align: 'center'},
            {field: 'projectStatus', title: '项目状态', width: 120, align: 'center'},
            {field: 'businessModel', title: '业务模式', width: 120, align: 'center'},
            {field: 'developerBrandName', title: '开发商品牌', width: 120, align: 'center'},
            {field: 'developerName', title: '开发商名称', width: 120, align: 'center'},
            {field: 'isBigCustomer', title: '是否大客户', width: 120, align: 'center'},
            {field: 'partnerType', title: '合作方类型', width: 120, align: 'center'},
            {field: 'partnerBrandName', title: '合作方品牌', width: 120, align: 'center'},
            {field: 'partnerName', title: '合作方名称', width: 120, align: 'center'},
            {field: 'totalFieldFlag', title: '共场', width: 120, align: 'center'},
            {field: 'directSignFlag', title: '是否直签', width: 120, align: 'center'},
            {field: 'cooperationDtStart', title: '合作开始时间', width: 120, align: 'center'},
            {field: 'cooperationDtEnd', title: '合作结束时间', width: 120, align: 'center'},
            {field: 'srlHtNo', title: '合同编号', width: 150, align: 'center'},
            {field: 'managementType', title: '物业类型', width: 120, align: 'center'},
            {field: 'realCity', title: '楼盘地理城市', width: 120, align: 'center'},
            {field: 'realDistrict', title: '楼盘区域', width: 120, align: 'center'},
            {field: 'sceneEmpName', title: '开发负责人', width: 120, align: 'center'},
            {field: 'sceneEmpCode', title: '开发负责人工号', width: 140, align: 'center'},
            {field: 'projectLeaderEmpName', title: '项目负责人', width: 120, align: 'center'},
            {field: 'projectLeaderEmpCode', title: '项目负责人工号', width: 140, align: 'center'}
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }

    var active = {
        reload: function () {
            if (true == valid()) {
                var optionsData = {};
                optionsData.organization = $("#orgYear").val();                             // 架构年份
                optionsData.areaCityCodes = formSelects.value('areaCity', 'valStr');     // HBL归属板块
                optionsData.cityIds = formSelects.value('city', 'valStr');                 // HBL归属城市
                optionsData.projectDepartmentId = $("#projectDepartmentId").val();
                optionsData.projectStatus = $("#projectStatus").val();
                optionsData.estateNmKey = $("#estateNmKey").val();

                reloadData(optionsData);
            }
        },
        export: function () {
            if (true == valid()) {
                var organization = $("#orgYear").val();                             // 架构年份
                var areaCityCodes = formSelects.value('areaCity', 'valStr');     // HBL归属板块
                var cityIds = formSelects.value('city', 'valStr');                 // HBL归属城市
                var projectDepartmentId = $("#projectDepartmentId").val();
                var projectStatus = $("#projectStatus").val();
                var estateNmKey = $("#estateNmKey").val();

                var cookieName = guid();
                window.location.href = BASE_PATH + '/pmlsProjectDetail/export?'
                    + "organization=" + organization
                    + "&areaCityCodes=" + areaCityCodes
                    + "&cityIds=" + cityIds
                    + "&projectDepartmentId=" + projectDepartmentId
                    + "&projectStatus=" + projectStatus
                    + "&estateNmKey=" + estateNmKey
                    + "&cookieName=" + cookieName;
            }
        }
    };

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index = layer.load(2);
        tableRender();
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsProjectDetail/queryList',
            cols: setCols(),
            where: optionsData,
            page: {
                curr: 1 //重新从第 1 页开始
            },
            //height: window.innerHeight - $(".layui-table-view").offset().top - 10,
            done: function (res, curr, count) {
                layer.close(index);
            }
        })
        ;
    }

    function valid() {

        var organization = $("#orgYear").val();                             // 架构年份
        var regionCodes = formSelects.value('region', 'valStr');         // HBL归属区域
        var areaCityCodes = formSelects.value('areaCity', 'valStr');     // HBL归属板块
        var cityIds = formSelects.value('city', 'valStr');               // HBL归属城市

        if (false == checkComm(organization, areaCityCodes, regionCodes, cityIds)) {
            return false;
        }
        return true;
    }

});