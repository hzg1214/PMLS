/*
 * 渠道品牌js
 * */
var layer;
var id = window.id;
var jsonDto = {};

var form, upload, table;
layui.use(['layer', 'table'], function () {
    layer = layui.layer;
    table = layui.table;

    setTimeout(function () {
        if (window.businessDto != '') {
            //jsonDto = eval('(' + window.businessDto + ')');
            jsonDto=eval('(' + window.businessDto.replace(/[\r\n]/g,"\\n") + ')');
            //初始化图片
            loadImageList('fileBusiness', jsonDto.fileList);

        }
        // 关联门店
        table.render({
            elem: '#storeTable'
            , url: ctx + '/businessManagerController/getCompanyStoreList'
            , where: {companyId: jsonDto.id}
            , cellMinWidth: 150
            , limit: 1000
            , cols: [[
                {field: 'storeNo', width: 120, title: '门店编号', align: 'center'
                	,event: 'detail'
                	,style: 'cursor: pointer;color:#01AAED'
                }
                , {field: 'name', width: 180, title: '门店名称', align: 'center', style: 'text-align: left'}
                , {field: 'addressDetail', width: 300, title: '门店地址', align: 'center', style: 'text-align: left'}
                , {
                    field: 'contractTypeName', width: 120, title: '是否房友门店', align: 'center'
                    , templet: function (d) {
                        // b或者A->b  就是房友门店
                        if (d.contractType == '10301' || d.contractType == '10304') {
                            return "是";
                        } else {
                            return "否";
                        }
                    }
                }
                , {field: 'maintainerName', width: 120, title: '维护人', align: 'center'}
                , {
                    field: 'dateCreate', width: 120, title: '创建日期', align: 'center'
                    , templet: function (d) {
                        if (d.dateCreate == null || d.dateCreate == "") {
                            return "";
                        } else {
                            return formatDate(d.dateCreate, "yyyy-MM-dd");
                        }
                    }
                }
            ]]
            , even: false //开启隔行背景
            , page: false
            , method: 'post'
            , loading: true
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

        if (jsonDto.brandType == '29401') // 29401: 分销渠道
        {
            if (jsonDto.arteryType == '29501') // 29501: 大渠道
            {
                //初始化维护人记录表格
                table.render({
                    elem: '#maintainTable'
                    , url: ctx + '/businessManagerController/getCompanyMaintainerList'
                    , where: {companyId: jsonDto.id}
                    , cellMinWidth: 150
                    , limit: 1000
                    , cols: [[
                        {field: 'rowNum', width: 80, title: '序号', align: 'center'}
                        , {field: 'maintainer', width: 100, title: '工号', align: 'center'}
                        , {field: 'maintainerName', width: 100, title: '姓名', align: 'center'}
                        , {field: 'centerName', title: '部门', align: 'left'}
                        , {field: 'dateMtcStart', width: 120, title: '维护开始日期', align: 'center'}
                        , {field: 'dateMtcEnd', width: 120, title: '维护结束日期', align: 'center'}
                        , {field: 'setUserName', width: 100, title: '分配人', align: 'center'}
                        , {field: 'dateCreate', width: 200, title: '分配时间', align: 'center'}
                    ]]
                    , even: false //开启隔行背景
                    , page: false
                    , method: 'post'
                    , loading: true
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

                //初始化管理员表格
                table.render({
                    elem: '#userManagerTable'
                    , url: ctx + '/businessManagerController/getContactsList'
                    , where: {companyNo: jsonDto.companyNo}
                    , cellMinWidth: 150
                    , limit: 1000
                    , cols: [[
                        {field: 'rowNum', width: 80, title: '序号', align: 'center'}
                        , {field: 'dockingPeo', width: 150, title: '姓名', align: 'center'}
                        , {field: 'dockingPeoTel', width: 150, title: '手机号码', align: 'center'}
                        , {
                            title: '操作', align: 'left', templet: function (row) {
                                var showContent = '';
                                showContent += '<a class="layui-btn layui-btn-mini" style="width:60px;" onclick="updateUserManager(' + row.id + ')">编辑</a>';
                                showContent += '<a class="layui-btn layui-btn-danger layui-btn-mini" style="width:60px;" onclick="deleteUserManager(' + row.id + ')">删除</a>';
                                return showContent; //列渲染
                            }
                        }
                    ]]
                    , even: false //开启隔行背景
                    , page: false
                    , method: 'post'
                    , loading: true
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
            //初始化对公账户表格
            table.render({
                elem: '#publicAccountsTable'
                , url: ctx + '/pmlsFrameContract/getBankInfoList'
                // ,data:[]
                , where: {companyNo: jsonDto.companyNo}
                , cellMinWidth: 150
                , limit: 1000
                , cols: [[
                    {field: 'rowNum', title: '序号', width: 80, align: 'center'}
                    , {field: 'bankAccountNm', title: '银行名称', align: 'center'}
                    , {field: 'bankAccount', title: '银行账号', align: 'center'}
                    , {field: 'accountNm', title: '银行户名', align: 'center'}
                    , {
                        title: '省市', align: 'center', templet: function (row) {
                            var showContent = '';
                            showContent += row.accountProvinceNm + row.accountCityNm;
                            return showContent; //列渲染
                        }
                    }
                    , {
                        title: '操作', align: 'left', templet: function (row) {
                            var showContent = '';
                            showContent += '<a class="layui-btn layui-btn-mini" style="width:60px;" onclick="deletePublicAccounts(' + row.id + ')">删除</a>';
                            return showContent; //列渲染
                        }
                    }
                ]]
                , even: false //开启隔行背景
                , page: false
                , method: 'post'
                , loading: true
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

            //初始化框架协议表格
            table.render({
                elem: '#frameworkAgreementTable'
                , url: ctx + '/pmlsFrameContract/getFrameContractList'
                // ,data:[]
                , where: {companyNo: jsonDto.companyNo, oaFile: '1'}
                , cellMinWidth: 150
                , limit: 1000
                , cols: [[
                    {
                        field: 'contractNo',
                        width: 170,
                        title: '框架协议编号',
                        align: 'center',
                        event: 'detail',
                        style: 'cursor: pointer;color:#01AAED'
                    }
                    , {
                        title: '合作周期', width: 200, align: 'center', templet: function (row) {
                            var showContent = formatDate(row.dateLifeStart, 'yyyy-MM-dd') + '至' + formatDate(row.dateLifeEnd, 'yyyy-MM-dd');
                            return showContent; //列渲染
                        }
                    }
                    , {field: 'approveStatusNm', width: 100, title: '审核状态', align: 'center'}
                    , {field: 'validStatusNm', width: 100, title: '生效状态', align: 'center'}
                    , {field: 'userName', width: 100, title: '创建人', align: 'center'}
                    , {field: 'format2', width: 150, title: '创建日期', align: 'center'}
                    , {
                        field: 'contract', width: 100, title: '盖章合同', align: 'center', templet: function (row) {
                            var showContent = '';
                            if (row.oaFileList != null && row.oaFileList.length > 0) {
                                for (var i = 0; i < row.oaFileList.length; i++) {
                                    showContent += '<a href="javascript:downloadOaFile(\'' + row.oaFileList[i].fileUrl + '\',\'' + row.oaFileList[i].fileName + '\');" title="' + row.oaFileList[i].fileName + '"><i class="layui-icon layui-icon-file-b"></i></a>';
                                }
                            }
                            return showContent; //列渲染
                        }
                    }
                    , {
                        title: '操作', align: 'left', templet: function (row) {
                            var showContent = '';
                            showContent += '<a class="layui-btn layui-btn-mini" onclick="showFrameworkAgreement(' + row.id + ')">查看</a>';
                            return showContent; //列渲染
                        }
                    }

                ]]
                , even: false //开启隔行背景
                , page: false
                , method: 'post'
                , loading: true
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

            //初始化合作确认函表格
            table.render({
                elem: '#cooperationConfirmationTable'
                , url: ctx + '/cooperationController/getCooperationList'
                , where: {companyNo: jsonDto.companyNo}
                , cellMinWidth: 150
                , limit: 1000
                , cols: [[
                    {
                        field: 'contractNo',
                        width: 170,
                        title: '分销合同编号',
                        align: 'center',
                        event: 'detail',
                        style: 'cursor: pointer;color:#01AAED'
                    }
                    , {
                        title: '合作周期', width: 200, align: 'center', templet: function (row) {
                            var showContent = formatDate(row.dateLifeStart, 'yyyy-MM-dd') + '至' + formatDate(row.dateLifeEnd, 'yyyy-MM-dd');
                            return showContent; //列渲染
                        }
                    }
                    , {field: 'approveStateName', width: 100, title: '审核状态', align: 'center'}
                    , {field: 'validStatusNm', width: 100, title: '生效状态', align: 'center'}
                    , {field: 'userNameCreate', width: 100, title: '创建人', align: 'center'}
                    , {
                        field: 'dateCreate', width: 150, title: '创建日期', align: 'center', templet: function (row) {
                            var showContent = formatDate(row.dateCreate, 'yyyy-MM-dd');
                            return showContent; //列渲染
                        }
                    }
                    , {
                        field: 'contract', width: 100, title: '盖章合同', align: 'center', templet: function (row) {
                            var showContent = '<a href=""><i class="layui-icon layui-icon-file-b"></i></a>';
                            return showContent; //列渲染
                        }
                    }
                    , {
                        title: '操作', align: 'left', templet: function (row) {
                            var showContent = '';
                            showContent += '<a class="layui-btn layui-btn-mini" style="width:60px;" onclick="showCooperationConfirmation(' + row.id + ')">查看</a>';
                            return showContent; //列渲染
                        }
                    }
                ]]
                , even: false //开启隔行背景
                , page: false
                , method: 'post'
                , loading: true
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
        /*//初始化日志表格
        table.render({
            elem: '#logsTable'
            ,url:ctx + '/businessManagerController/getOperationLogList'
            ,where:{companyNo:jsonDto.companyNo}
            ,cellMinWidth: 150
            ,cols: [[
                {field:'rowNum', width:80, title: '序号',align: 'center'}
                ,{field:'operationContent', title: '描述',align: 'left'}
                ,{field:'userCreateName',width:150, title: '创建人',align: 'center'}
                ,{field:'dateCreate', width:200,title: '操作时间',align: 'center'}
            ]]
            , even: false //开启隔行背景
            , page: false
            , method: 'post'
            ,loading:true
            ,request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            ,response: {
                statusName: 'returnCode' //数据状态的字段名称，默认：code
                ,statusCode: 200 //成功的状态码，默认：0
                ,msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                ,countName: 'totalCount' //数据总数的字段名称，默认：count
                ,dataName: 'returnData' //数据列表的字段名称，默认：data
            }
        });*/

        table.on('tool(storeTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        	var data = obj.data; //获得当前行数据
        	var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        	var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        	if (layEvent == 'detail') {
        		//查看
        		showStoreDetail(data.storeId);
        	}
        })
        
        table.on('tool(frameworkAgreementTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            if (layEvent == 'detail') {
                //查看
                showFrameworkAgreement(data.id);
            }
        })
        table.on('tool(cooperationConfirmationTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            if (layEvent == 'detail') {
                //查看
                showCooperationConfirmation(data.id);
            }
        })

    }, 100)

});

//取消返回上一个页面
function back() {
    parent.redirectTo('delete', null, null);
}

//删除对公账户
function deletePublicAccounts(id) {
    parent.layer.confirm("确认删除吗？", {icon: 3, title: '提示'}, function () {
        parent.layer.load(2);
        $.ajax({
            url: ctx + '/pmlsFrameContract/deleteBankInfo',
            type: 'post',
            data: {id: id},
            dataType: 'json',
            success: function (data) {
                parent.layer.closeAll();
                console.log(data);
                if (data.returnCode != 200) {
                    parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                } else {
                    parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                        parent.layer.closeAll();
                        window.location.reload();
                    });
                }
            }
        });
    })
}

//查看门店详情
function showStoreDetail(id){
    var url=ctx+"/pmlsStore/storeDetail?id="+id;
    parent.redirectTo('append',url,'门店详情');
}

//查看框架协议
function showFrameworkAgreement(id) {
    var url = ctx + "/pmlsFrameContract/frameContractDetailPage?id=" + id;
    parent.redirectTo('append', url, '联动框架协议详情');
}

//查看合作确认函
function showCooperationConfirmation(id) {
    var url = ctx + "/cooperationController/cooperationInfoPage?id=" + id;
    parent.redirectTo('append', url, '联动分销合同详情');
}

//新增管理员
function addUserManager() {
    var companyNo = jsonDto.companyNo;
    parent.layer.open({
        type: 2,
        btn: ['确认', '取消'],
        title: '新建管理员',
        area: ['500px', '400px'],
        content: ctx + '/businessManagerController/addContactsPage?companyNo=' + companyNo,
        yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            console.log(formData);
            if (formData != null) {
                parent.layer.close(index);//关闭弹窗

                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/businessManagerController/addContacts',
                    type: 'post',
                    data: formData,
                    dataType: 'json',
                    success: function (data) {
                        parent.layer.closeAll();
                        console.log(data);
                        if (data.returnCode != 200) {
                            parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                                parent.layer.closeAll();
                                window.location.reload();
                            });
                        }
                    }
                });
            }

        }
    });
}

//修改管理员信息
function updateUserManager(id) {
    parent.layer.open({
        type: 2,
        btn: ['确认', '取消'],
        title: '编辑',
        area: ['500px', '400px'],
        content: ctx + '/businessManagerController/addContactsPage?id=' + id,
        yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            console.log(formData);
            if (formData != null) {
                parent.layer.close(index);//关闭弹窗

                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/businessManagerController/updateContacts',
                    type: 'post',
                    data: formData,
                    dataType: 'json',
                    success: function (data) {
                        parent.layer.closeAll();
                        console.log(data);
                        if (data.returnCode != 200) {
                            parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                                parent.layer.closeAll();
                                window.location.reload();
                            });
                        }
                    }
                });
            }

        }
    });
}

//删除管理员信息
function deleteUserManager(id) {
    parent.layer.confirm("确认删除吗？", {icon: 3, title: '提示'}, function () {
        parent.layer.load(2);
        $.ajax({
            url: ctx + '/businessManagerController/updateContacts',
            type: 'post',
            data: {id: id, isDelete: 1, companyNo: jsonDto.companyNo},
            dataType: 'json',
            success: function (data) {
                parent.layer.closeAll();
                console.log(data);
                if (data.returnCode != 200) {
                    parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                } else {
                    parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                        parent.layer.closeAll();
                        window.location.reload();
                    });
                }
            }
        });
    });

}

//下载OA附件
function downloadOaFile(url, fileName) {
    var requestUrl = ctx + '/businessManagerController/showOaFile?url=' + url + '&fileName=' + encodeURI(fileName);
    window.location.href = requestUrl;
}

//分配维护人
function updateMaintain() {
    parent.layer.open({
        type: 2,
        title: '选择维护人',
        area: ['800px', '660px'],
        content: '/businessManagerController/selectMaintainPage'
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();
            console.log(formData);
            if (formData != null) {
                parent.layer.load(2);
                //修改维护人信息
                $.ajax({
                    url: ctx + '/businessManagerController/updateMaintainer',
                    type: 'post',
                    data: {
                        id: jsonDto.id,
                        maintainer: formData.maintainer,
                        maintainerName: formData.maintainerName,
                        pmlsCenterId: formData.centerId
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.returnCode != 200) {
                            parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                                parent.layer.closeAll();
                                window.location.reload();
                            });
                        }
                    }
                });
                parent.layer.close(index);
            }
        }
    });
}

//新建框架协议
function addFrameContract() {
    var url = ctx + "/pmlsFrameContract/addFrameContractPage?companyId=" + jsonDto.id;
    parent.redirectTo('append', url, '新建联动框架协议');
}

//新建合作协议
function addCooperation() {
    var loadIndex = parent.layer.load(2);
    var paramData = {
        id: jsonDto.id
    };
    $.ajax({
        url: BASE_PATH + '/cooperationController/checkCompany',
        type: 'post',
        data: paramData,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            parent.layer.close(loadIndex);
            if (data.returnCode == -1) {
                if (data.returnMsg.indexOf('经纪公司暂无框架协议') >= 0) {
                    parent.layer.alert('渠道无框架协议，不能新建联动分销合同！', {icon: 2, title: '提示'});
                } else if (data.returnMsg.indexOf('营业执照号没有对应供应商') >= 0) {
                    parent.layer.alert('渠道无对应供应商，不能新建联动分销合同！', {icon: 2, title: '提示'});
                } else {
                    parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                }
            } else {
                var url = ctx + "/cooperationController/addCooperationView?companyId=" + jsonDto.id;
                parent.redirectTo('append', url, '新建联动分销合同');
                //关闭弹出层
                parent.layer.close(index);
            }
        }
    });


}

/*
调整为是否借佣渠道
isProcuration ：0—>否，1->是
 */
function adjustToProcuration(isProcuration) {
    var strMsg = '是否调整为借佣渠道?'
    if (isProcuration == 0) {
        strMsg = '是否调整为非借佣渠道?'
    }
    parent.layer.confirm(strMsg, {icon: 3, title: '提示'}, function () {
        var params = {
            id: jsonDto.id,
            companyNo: jsonDto.companyNo,
            isProcuration: isProcuration
        };
        var url = BASE_PATH + "/businessManagerController/adjustToProcuration";
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        restPost(url, params, function (data) {
            parent.layer.close(loadIndex);
            parent.msgProp(data.returnMsg);
            window.location.reload();
        }, function (data) {
            parent.layer.close(loadIndex);
            parent.msgError(data.returnMsg);
        });
    });
}


