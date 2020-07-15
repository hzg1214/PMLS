<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript"
            src="${ctx}/meta/js/houseLinkage/linkProjectTrace/linkProjectTrace.js?_v=${vs}"></script>
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

<div class="container theme-hipage ng-scope" role="main" id="contentAll">
    <div class="row">
        <div class="page-content">
            <h4 class="border-bottom pdb10"><strong>联动项目跟踪</strong></h4>
            <form id="LinkProjectTraceListForm">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="form-group" name="group2">
                                <label class="lab" style="    padding-left: 20px;">归属城市：</label>
                                <script type="text/javascript">
                                    var data =
                                        $(document).ready(function() {
                                            $("#relseaseCity").multiselect("destroy").multiselect({
                                                enableFiltering: true,
                                                filterPlaceholder:'请输入城市',
                                                buttonWidth: '210px',
                                                nonSelectedText:'请选择',
                                                nSelectedText:'个城市被选中',
                                                includeSelectAllOption:false,
                                                selectAllText:'全选/取消',
                                                allSelectedText:'已选中所有城市',
                                                selectedClass: 'active1',
                                                numberDisplayed : 10,
                                                dropRight: true,
                                                maxHeight: 275,
                                                dropUp: true,
                                                onChange: function(element, checked) {
                                                    var brands = $('#relseaseCity option:selected');
                                                    var selected = [];
                                                    $(brands).each(function(index, brand){
                                                        selected.push([$(this).val()]);
                                                    });
                                                    $("#cityNo").val(selected);
                                                    console.log(selected);
                                                }
                                            });
                                            $("span.multiselect-selected-text").css({"text-align":'left',display:'inline-block'});
                                            $("b.caret").css("margin-left","46px");
                                        });
                                </script>
                                <select class="form-control" title="" id="relseaseCity" name="relseaseCity"  multiple="multiple">
                                    <c:forEach items="${citylist}" var="city">
                                        <option value="${city.cityNo}">${city.cityName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="hidden" id="cityNo" name="cityNo" />
                            <div class="form-group">
                                <label class="lab" style="    padding-left: 20px;">项目：</label>
                                <input type="text" class="form-control w200" id="projectNo" name="projectNo"
                                       placeholder="请输入项目编号或名称" style="width:250px; margin-left: 0px;">
                            </div>

                            <div class="form-group" style="width: 340px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 20px;">查询年月：</label>
                                <input type="text" size="11" class="calendar-icon form-control" name="startDate" id="startDate"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM',minDate:'2017-01',maxDate:'%y-%M-%d'})"
                                       readonly="readonly" class="ipttext Wdate"style="width: 110px;"/> -
                                <input type="text" size="11" class="calendar-icon form-control" name="endDate" id="endDate"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'%y-%M-%d'})"
                                       readonly="readonly" class="ipttext Wdate" style="width: 110px;"/>
                            </div>
                            <div class="pull-right">
                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:ProjectTrace.search();">查询
                                </button>
                                <button type="button" class="btn btn-primary" id="btn-reset"
                                        onclick="javascript:ProjectTrace.export()">导出
                                </button>
                            </div>
                            <div class="form-inline" id="exportMsg">
                                <li>
                                    <label class="lab" style="width: 200px;font-size: 12px;font-weight: bold;color: red">您当前有<span id="reportSize">${userReportSize}</span>个文件正在下载中……</label>
                                </li>
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

</body>
<script type="application/javascript">
    $(function(){
        $(".multiselect").find("b").css("margin-left","126px");
    })
</script>
</html>
