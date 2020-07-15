<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/linkAchieveChange/achieveMentUserList.js?_v=${vs}"></script>
<script>
    $(function () {
        // 初始化查询
        AchieveMentUser.initList();
    });
</script>
<!-- <div class="fs14 tanchuang-pannel " role="main"> -->
<div class="" role="main">

    <span class="" style="float:right">
        <a href="javascript:LinkAchieveChange.close();" class="btn btn-default">&times;</a>
    </span>

    <div class="row">
        <div class="page-content" style="clear: initial;">
            <h4><strong>选择变更后业绩归属人</strong></h4>
            <!-- 搜索条件区域 -->
            <form id="achieveMentUserForm">
                <!-- 默认排序字段、排序类型 -->
                <input type="hidden" id="orderName" name="orderName" value="dateCreate">
                <input type="hidden" id="orderType" name="orderType" value="DESC">
                <input type="hidden" id="ids" name="ids" value="${ids}">

                <ul class="list-inline form-inline" style="margin-bottom: 0px;">
                    <li>
                        <div class="form-group">
                            <label class="">员工编号</label>
                            <input type="text" class="form-control" id="userCode" name="userCode">
                        </div>
                        <div class="form-group">
                            <label class="">员工姓名</label>
                            <input type="text" class="form-control" id="userName" name="userName">
                        </div>
                        <button type="button" class="btn btn-primary" onclick="javascript:AchieveMentUser.search();">
                            搜索
                        </button>
                    </li>
                </ul>
                <div id="errorMsg" style="color: red;margin-bottom: 5px;visibility: hidden;height: 20px;"></div>
                <!-- 动态加载区域块  begin -->
                <!-- 异步加载列表内容 -->
                <div id="load_content">
                    <div id="LoadCxtPopup"></div>
                </div>
            </form>
        </div>
    </div>
</div>