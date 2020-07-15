<%@page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/scene/inCommission/sceneInCommission.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-multiselect.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/bootstrap-multiselect.js?_v=${vs}"></script>
    <style type="text/css">
        .form-inline {
            overflow: inherit;
        }
    </style>
</head>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>


<div class="container theme-hipage ng-scope" role="main">
    <h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>佣金管理</strong></h4>
    <div class="row">
        <ul class="nav nav-tabs nav-tabs-header">
            <li role="presentation" class="active">
                <a href="${ctx}/sceneCommission">外佣/返佣</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/lnkYjNy">内佣</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/lnkYjDy/index">垫佣</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/settleConfirm">结算确认</a>
            </li>
            <%-- <li role="presentation">
                <a href="${ctx}/linkAchieveChange">业绩调整</a>
            </li> --%>
            <li role="presentation">
                <a href="${ctx}/cashBill">请款单</a>
            </li>
			<li role="presentation"  >
                <a href="${ctx}/lnkYjReport">返佣对象维护</a>
            </li>
        </ul>
        <div class="page-content">
            <form id="SceneInCommissionListForm">
                <input type="hidden" name="years" id="years" value="">
                <input type="hidden" name="cityNo" id="cityNo" value="${userInfo.cityNo}">
                <input type="hidden" name="flagHref" id="flagHref" value="0">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="form-group">
                                <!-- 城市下的中心 -->
                                <div class="form-group" style="width:auto;">
                                    <span>业绩归属项目部：</span>
                                    <select class="form-control" style="width:150px;" id="projectDepartmentId" name="projectDepartmentId">
                                        <option value="" selected>请选择</option>
                                        <c:forEach items="${rebacklist}" var="list">
                                            <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="fc-warning"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="width:90px;text-align:right;">楼盘名称：</label>
                                <script type="text/javascript">
                                    $(document).ready(function() {
                                        $("#estateNm").multiselect({
                                            enableFiltering: true,
                                            filterPlaceholder: '请输入楼盘名称',
                                            buttonWidth: '150px',
                                            nonSelectedText: '请选择',
                                            nSelectedText: '个楼盘被选中',
                                            includeSelectAllOption: false,
                                            selectAllText: '全选/取消',
                                            allSelectedText: '已选中所有楼盘',
                                            selectedClass: 'active1',
                                            numberDisplayed: 10,
                                            dropRight: true,
                                            maxHeight: 275,
                                            dropUp: true,
                                            onChange: function(element, checked) {
                                                var brands = $('#estateNm option:selected');
                                                var selected = [];
                                                $(brands).each(function (index, brand) {
                                                    selected.push(['\''+$(this).val()+'\'']);
                                                });
                                                $("#estateNmKey").val(selected);
                                                console.log(selected);
                                            }
                                        });
                                        //控制select显示
                                        $("span.multiselect-selected-text").css({"text-align":'left',display:'inline-block'});
                                        $("b.caret").css("margin-left","75px");
                                        //图标颜色
                                        $(".input-group-btn i").css(
                                        	"color","#4169E1"
                                        );
                                        //图标颜色
                                        $(".input-group-addon i").css(
                                        	"color","#4169E1"
                                        );
                                    });
                                </script>
                                <select class="form-control" style="width:150px;" id="estateNm" name="estateNm" multiple="multiple">
                                    <c:forEach items="${estateList}" var="list">
                                        <option value="${list}">${list}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" id="estateNmKey" name="estateNmKey" />
                                <span class="fc-warning"></span>
                            </div>
                            <div class="form-group">
                                <!-- 报备编号 -->
                                <label style="width:90px;text-align:right;">报备编号：</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control " id="reportId" name="reportId" style="width:150px;">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control " style="width:355px;" id="searchKey"
                                       name="searchKey" placeholder="请输入项目编号、开发商、楼室号、客户姓名、电话">
                            </div>

                        </div>
                        <div class="form-inline">
                            <%--<div class="form-group" style="margin-left: 32px;">
                                <!-- 业务阶段 -->
                                <i>*</i><span> 业务阶段</span>：
                                <select class="form-control" id="businessType" name="businessType" style="width:150px;">
                                    <option value="" selected>请选择</option>
                                    <option value="DD">大定</option>
                                    <option value="CX">成销</option>
                                </select>
                            </div>--%>
                            <input type="hidden" name="businessType" id="businessType" value="CX">
                            <div class="form-group" style="margin-left: 31px;">
                                <!-- 开始日期 -->
                                <i>*</i> 开始日期：
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="text" class="calendar-icon form-control w100" name="countDateStart"
                                           id="countDateStart"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                           style="width:150px;" readonly="readonly" class="ipttext Wdate"/>
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: 9px;">
                                <!-- 结束日期 -->
                                <i>*</i> 结束日期：
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="text" class="calendar-icon form-control w100" name="countDateEnd"
                                           id="countDateEnd"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'countDateStart\')}',maxDate:'%y-%M-%d'})"
                                           style="width:150px;" readonly="readonly" class="ipttext Wdate"/>
                                    <span class="fc-warning"></span>
                                </div>
                            </div>
                            <div class="form-group" style="margin-left: 9px;">
                                <i>*</i> 模板类型：
                                <!-- 类型 -->
                                <select class="form-control" title="" id="estateType" name="estateType" style="width:150px;">
                                    <option value="" selected="selected">请选择模板类型</option>
                                    <c:forEach items="${estateTypeList}" var="list">
                                    <option value="${list.dicCode}">${list.dicValue}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-inline">

                            <div class="form-group" style="float:right">
                                <div style="float:left">
                                    <button type="button" class="btn btn-primary" id="J_search" onclick="javascript:SceneInCommission.search();">查询
                                    </button>
                                    &nbsp;
                                    <button type="button" class="btn btn-default" id="btn-reset" onclick="javascript:SceneInCommission.reset();">重置
                                    </button>
                                    &nbsp;
                                    <button type="button" id="btn-output" class="btn btn-primary" onclick="javascript:SceneInCommission.output();">导出
                                    </button>
                                    &nbsp;
                                    <button type="button" id="btn-imput" class="btn btn-primary" onclick="javascript:SceneInCommission.imput();">导入
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- 异步加载列表内容 -->
                <div id="load_content">
                    <div id="LoadCxt"></div>
                </div>

            </form>
        </div>
    </div>
</div>
<form name="imputForm" id="imputForm" method="post" action="${pageContext.request.contextPath}/sceneInCommission/imput"
      target="hiddenFrame" enctype="multipart/form-data">
    <input type="file" id="historyDataFile" name="historyDataFile" accept=".xls,.xlsx" style="display:none">
    <input type="hidden" id="estateTypeImput" name="estateTypeImput">
</form>
<iframe name="hiddenFrame" id="hiddenFrame" style="display:none">
</iframe>
</body>

</html>