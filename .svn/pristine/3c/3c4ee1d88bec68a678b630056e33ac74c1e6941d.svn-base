layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;

    var currentUrl = BASE_PATH + '/pmlsStore';
    var defTab = "all";
    var backTab = $("#backTab").val();

    // 全部门店
    var all = {
        init: function () {
        	form.on('select(all-storeCityNo)', function (data) {
                console.log("form.on('select(all-storeCityNo)')");
                initDistrict(data.value);
            });
            
          //初始化-区域
            function initDistrict(cityNo) {

            	var cityNo = $('#all-storeCityNo').val();
        	    if(cityNo == null || cityNo == "")
            	{
        	    	var html = "<option value='' selected>请选择</option>";
        	    	$('#all-districtNo').append(html);
        	    	return false;
            	}
        	    
        	    var url = BASE_PATH +  "/linkages/city/" + cityNo;
        	    var params = {};
        	    ajaxGet(url, params, function(data) {
        			var result = "<option value=''>请选择</option>";
        			$.each(data.returnValue, function(n, value) {
        				result += "<option value='" + value.districtNo + "'>"
        						+ value.districtName + "</option>";
        			});
        			$("#all-districtNo").html('');
        			$("#all-districtNo").append(result);
        			form.render();
        		}, function() {
        		});	   

            }

            form.render('select'); //刷新select选择框渲染

            // 表格数据
            this.tableRender();
        },
        tableRender: function () {
            table.render({
                elem: '#all-contentTable'
                , cols: all.setCols()
                , data: []
                , id: 'all-contentReload'
                , height: "full"
                , loading: false
                , even: false //开启隔行背景
                , page: true
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
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
            });
        },
        setCols: function () {
            var row = [
            	{field: 'storeNo',title: '门店编号',width:120, align: 'center',style:'cursor: pointer;color:#01AAED', templet: function (d) {
	                    return "<a class='layui-table-link' onclick='javascript:showDetail(\""  + d.storeId +  "\")'>" + d.storeNo + "</a>";
	                }
            	},
                {field: 'name',title: '门店名称',width:180, align: 'center', style: 'text-align: left'},
                {field: 'groupName',title: 'CRM维护中心',width:160, align: 'center'},
                {field: 'pmlsGroupName',title: '联动维护中心',width:160, align: 'center'},
                {field: 'addressDetail',title: '门店地址',width:350, align: 'center', style: 'text-align: left'},
                {field: 'companyNo',title: '公司编号',width:160, align: 'center'},
                {field: 'companyName',title: '所属公司',width:250, align: 'center', style: 'text-align: left'},
                {field: 'brandTypeStr',title: '业务类型',width:160, align: 'center'},
                {field: 'isFyStoreStr',title: '房友门店',width:160, align: 'center'},
                {field: 'pmlsMaintainName',title: '联动维护人' ,width:150,align: 'center'},
                {field: 'dateCreate',title: '创建时间' ,width:150,align: 'center',
                	templet: function (d) {
                        if (d.dateCreate == null || d.dateCreate == "") {
                            return "";
                        } else {
                        	return formatDate(d.dateCreate, "yyyy-MM-dd");
                        }
                    }
                },
                {title: '操作',width:100,align: 'center',fixed:'right',
                	templet: function(row) {
                        var showContent='';
                        showContent+='<a class="layui-btn layui-btn-mini" onclick="showDetail(' + row.storeId + ')">查看</a>';
                        return showContent; //列渲染
                	}
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },
        active: {
            reload: function () {
                if (all.valid()) {
                	var optionsData={};
                    var storeCityNo=$("#all-storeCityNo").val();
                    var districtNo = $("#all-districtNo").val();//区域
                    var searchKey = trimBlankStr($("#all-searchKey").val());
                    if(isIncludeBlank(searchKey)){
                    	parent.layer.alert('门店文本框有空格，请重新输入！', {
                            icon: 2,
                            title: '提示'
                        });
                    	return;
                    }
                    var groupId=$("#all-groupId").val();
                    var brandType = $("#all-brandType").val();
                    var isFyStore = $("#all-isFyStore").val();
                    optionsData.storeCityNo=storeCityNo;
                    optionsData.districtNo=districtNo;
                    optionsData.searchKey=searchKey;
                    optionsData.groupId=groupId;
                    optionsData.brandType=brandType;
                    optionsData.isFyStore=isFyStore;
                    console.log(optionsData);
                    sessionStorage.removeItem('PMLS_STORE_ALL_SEARCH');
                    all.reloadData(optionsData);
                }
            }
            , reset: function () {
            	 $("#all-storeCityNo").val('');
                 $("#all-districtNo").val('');//区域
                 $("#all-searchKey").val('');
                 $("#all-groupId").val('');
                 $("#all-brandType").val('');
                 $("#all-sisFyStore").val('');
                form.render('select');
                all.active.reload();
            }
        },
        reloadData: function (optionsData) {
        	var index = parent.layer.load(2,{shade: 0.1});
            //var index = layer.load(2);

//            if (!optionsData) {
//                var optionsData = {};
//                optionsData.dateTypeKbn = '13703';
//                optionsData.latestProgress = '13504';
//                optionsData.roughAuditStatus = '0'
//            }

            var sessionData = sessionStorage.getItem('PMLS_STORE_ALL_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#all-storeCityNo").val(optionsData.storeCityNo);
                $("#all-districtNo").val(optionsData.districtNo);
                $("#all-searchKey").val(optionsData.searchKey);
                $("#all-groupId").val(optionsData.groupId);
                $("#all-brandType").val(optionsData.brandType);
                $("#all-isFyStore").val(optionsData.isFyStore);

                form.render('select');
            }


            table.reload('all-contentReload', {
                url: BASE_PATH + '/pmlsStore/querylist',
                cols: all.setCols(),
                height: window.innerHeight - $("#allTabItem .layui-table-view").offset().top - 45,
                where: optionsData,
                page: {
                    curr: pageIndex //重新从第 1 页开始
                },
                done: function (res, curr, count) {
                	parent.layer.close(index);
                    if (!optionsData) {
                        optionsData = {};
                    }
                    optionsData.curr = curr;
                    sessionStorage.setItem("PMLS_STORE_ALL_SEARCH", JSON.stringify(optionsData));
                }
            });
        },
        valid: function () {

            return true;
        }
    }

    //渠道
    var brand = {
            init: function () {
            	form.on('select(brand-storeCityNo)', function (data) {
                    console.log("form.on('select(brand-storeCityNo)')");
                    initDistrict(data.value);
                });
                
              //初始化-区域
                function initDistrict(cityNo) {

                	var cityNo = $('#brand-storeCityNo').val();
            	    if(cityNo == null || cityNo == "")
                	{
            	    	var html = "<option value='' selected>请选择</option>";
            	    	$('#brand-districtNo').append(html);
            	    	return false;
                	}
            	    
            	    var url = BASE_PATH +  "/linkages/city/" + cityNo;
            	    var params = {};
            	    ajaxGet(url, params, function(data) {
            			var result = "<option value=''>请选择</option>";
            			$.each(data.returnValue, function(n, value) {
            				result += "<option value='" + value.districtNo + "'>"
            						+ value.districtName + "</option>";
            			});
            			$("#brand-districtNo").html('');
            			$("#brand-districtNo").append(result);
            			form.render();
            		}, function() {
            		});	   

                }

                form.render('select'); //刷新select选择框渲染

                // 表格数据
                this.tableRender();
            },
            tableRender: function () {
                table.render({
                    elem: '#brand-contentTable'
                    , cols: brand.setCols()
                    , data: []
                    , id: 'brand-contentReload'
                    , height: "full"
                    , loading: false
                    , even: false //开启隔行背景
                    , page: true
                    , limits: [10, 20, 30]
                    , limit: 10 //默认采用60
                    , method: 'post'
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
                });
            },
            setCols: function () {
                var row = [
                	{field: 'storeNo',title: '门店编号',width:120, align: 'center',style:'cursor: pointer;color:#01AAED', templet: function (d) {
	                        return "<a class='layui-table-link' onclick='javascript:showDetail(\""  + d.storeId +  "\")'>" + d.storeNo + "</a>";
	                    }
                		
                	},
                    {field: 'name',title: '门店名称',width:180, align: 'center', style: 'text-align: left'},
                    {field: 'pmlsGroupName',title: '联动维护中心',width:160, align: 'center'},
                    {field: 'addressDetail',title: '门店地址',width:350, align: 'center', style: 'text-align: left'},
                    {field: 'companyNo',title: '公司编号',width:160, align: 'center'},
                    {field: 'companyName',title: '所属公司',width:250, align: 'center', style: 'text-align: left'},
                    {field: 'isFyStoreStr',title: '房友门店',width:160, align: 'center'},
                    {field: 'pmlsMaintainName',title: '联动维护人' ,width:150,align: 'center'},
                    {field: 'dateCreate',title: '创建时间' ,width:150,align: 'center',
                    	templet: function (d) {
                            if (d.dateCreate == null || d.dateCreate == "") {
                                return "";
                            } else {
                            	return formatDate(d.dateCreate, "yyyy-MM-dd");
                            }
                        }
                    },
                    {title: '操作',width:100,align: 'center',fixed:'right',
                    	templet: function(row) {
                            var showContent='';
                            showContent+='<a class="layui-btn layui-btn-mini" onclick="showDetail(' + row.storeId + ')">查看</a>';
                            return showContent; //列渲染
                    	}
                    }
                ];
                var cols = [];
                cols.push(row);
                return cols;
            },
            active: {
                reload: function () {
                    if (brand.valid()) {
                    	var optionsData={};
                        var storeCityNo=$("#brand-storeCityNo").val();
                        var districtNo = $("#brand-districtNo").val();//区域
                        var searchKey = trimBlankStr($("#brand-searchKey").val());
                        if(isIncludeBlank(searchKey)){
                        	parent.layer.alert('门店文本框有空格，请重新输入！', {
                                icon: 2,
                                title: '提示'
                            });
                        	return;
                        }
                        var groupId=$("#brand-groupId").val();
                        var isFyStore = $("#brand-isFyStore").val();
                        optionsData.storeCityNo=storeCityNo;
                        optionsData.districtNo=districtNo;
                        optionsData.searchKey=searchKey;
                        optionsData.groupId=groupId;
                        optionsData.isFyStore=isFyStore;
                        optionsData.brandType='29401';
                        console.log(optionsData);
                        sessionStorage.removeItem('PMLS_STORE_BRAND_SEARCH');
                        brand.reloadData(optionsData);
                    }
                }
                , reset: function () {
                	 $("#brand-storeCityNo").val('');
                     $("#brand-districtNo").val('');//区域
                     $("#brand-searchKey").val('');
                     $("#brand-groupId").val('');
//                     $("#brandType").val('');
                     $("#brand-isFyStore").val('');
                    form.render('select');
                    brand.active.reload();
                }
            },
            reloadData: function (optionsData) {
            	var index = parent.layer.load(2,{shade: 0.1});

                if (!optionsData) {
                    optionsData = {};
                    optionsData.brandType='29401';
                }

                var sessionData = sessionStorage.getItem('PMLS_STORE_BRAND_SEARCH');
                var pageIndex = 1;
                if (sessionData != null && sessionData != '') {
                    optionsData = JSON.parse(sessionData);
                    pageIndex = optionsData.curr;
                    $("#brand-storeCityNo").val(optionsData.storeCityNo);
                    $("#brand-districtNo").val(optionsData.districtNo);
                    $("#brand-searchKey").val(optionsData.searchKey);
                    $("#brand-groupId").val(optionsData.groupId);
//                    $("#all-brandType").val(optionsData.brandType);
                    $("#brand-isFyStore").val(optionsData.isFyStore);

                    form.render('select');
                }


                table.reload('brand-contentReload', {
                    url: BASE_PATH + '/pmlsStore/querylist',
                    cols: brand.setCols(),
                    height: window.innerHeight - $("#brandTabItem .layui-table-view").offset().top - 45,
                    where: optionsData,
                    page: {
                        curr: pageIndex //重新从第 1 页开始
                    },
                    done: function (res, curr, count) {
                    	parent.layer.close(index);
                        if (!optionsData) {
                            optionsData = {};
                        }
                        optionsData.curr = curr;
                        sessionStorage.setItem("PMLS_STORE_BRAND_SEARCH", JSON.stringify(optionsData));
                    }
                });
            },
            valid: function () {

                return true;
            }
        }
    
    
    // 画面初始化
    initialization();

    function initialization() {

        var sessionData_all = sessionStorage.getItem('PMLS_STORE_ALL_SEARCH');
        var sessionData_brand = sessionStorage.getItem('PMLS_STORE_BRAND_SEARCH');

        all.init();         // 全部
        brand.init();          // 渠道

        form.render('select');   // 刷新单选框渲染

        act = {
            all: function () {
                all.reloadData()
            },
            brand: function () {
                brand.reloadData()
            }
        }

        element.on("tab(storeTab)", function (data) {
            var type = "all";
            if (data.index == 0) {
                type = "all";
            }
            else if (data.index == 1) {
                type = "brand";
            }

            act[type] ? act[type].call(this) : '';
            var url = currentUrl + "?backTab=" + type;
            parent.breadcrumbArray[parent.breadcrumbArray.length - 1].url = url;

        });

        // 全部
        $('#allTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            all.active[type] ? all.active[type].call(this) : '';
        });

        // 渠道
        $('#brandTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            brand.active[type] ? brand.active[type].call(this) : '';
        });


        var tab = isEmpty(backTab) ? defTab : backTab;
        element.tabChange('storeTab', tab);
    }
});

//查看
function showDetail(id){
    var url=ctx+"/pmlsStore/storeDetail?id="+id;
    parent.redirectTo('append',url,'门店详情');
}
