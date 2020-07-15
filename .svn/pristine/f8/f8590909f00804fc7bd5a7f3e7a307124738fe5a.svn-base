<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>


    <%--<%@ include file="/WEB-INF/page/common/meta.jsp"%>--%>
    <%--<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>--%>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/permission/permissionList.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>


<script>
    $(function() {
     Permission.searchList();


    });

</script>
<!-- <div class="fs14 tanchuang-pannel " role="main"> -->
<div class="" role="main">

    <span class="" style="float:right">
        <a href="javascript:Permission.close();" class="btn btn-default">&times;</a>
    </span>

    <div class="row">
        <div class="page-content" style="clear: initial;">
            <h4><strong>新增数据</strong></h4>
            <!-- 搜索条件区域 -->
            <form id="achieveMentUserForm">
                <!-- 默认排序字段、排序类型 -->
                <input type="hidden" class="form-control" id="cityList" name="cityList">
                <ul class="list-inline form-inline" style="margin-bottom: 0px;">
                    <li>
                        <div class="form-group">
                            <label class="">工号</label>
                            <input type="text" class="form-control" id="userCode" name="userCode">
                        </div>
                        <div class="form-group">
                            <label class="">姓名</label>
                            <input type="text" class="form-control" id="userName" name="userName">
                        </div>

                    </li>
                    <li>
                        <div class="form-group">
                            <label class="" >是否显示：</label>
                            <label style="padding-left: 10px">
                                <input type="radio" name="isShow" id="optionsRadios1" value="10" >是
                            </label>
                            <label style="padding-left: 10px">
                                <input type="radio" name="isShow" id="optionsRadios2" value="20" >否
                            </label>

                        </div>
                        <div class="form-group">
                            <label class="">是否可点击：</label>
                            <label >
                                <input type="radio" name="isClick" id="optionsRadios3" value="10" >是
                            </label>
                            <label >
                                <input type="radio" name="isClick" id="optionsRadios4" value="20" >否
                            </label>

                        </div>
                    </li>

                </ul>
                <ul class="list-inline form-inline" style="margin-bottom: 0px;height: 300px">
                    <li>
                        <div class="form-group" id="functionSelect" name="group3" style="width: 250px; margin-left: -4px;">
                            <label class="lab" style="padding-left: 5px;">选择模块：</label>
                            <div class="multi-select" id="functionCode" name="functionCode"  style="width: 157px;">
                                <input type="hidden" class="multi-select-value" readonly="readonly" name="functionCodes">
                                <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 157px; height: 35px; margin-left: 0px;border: 1px solid #ccc;">
                                <ul class="multi-select-list" style="height: 240px">
                                    <li class="multi-select-item">
                                        <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        </li>
                    </ul>


                <div id="errorMsg" style="color: red;margin-bottom: 5px;visibility: hidden;height: 20px;"></div>
                <!-- 动态加载区域块  begin -->
                <!-- 异步加载列表内容 -->

            </form>
        </div>
    </div>
</div>
