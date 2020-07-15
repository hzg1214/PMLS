<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<script type="text/javascript" src="${ctx}/meta/js/dataAuthority/dataAuthorityList.js?_v=${vs}"></script>
<link href="${ctx}/meta/css/zTreeStyle.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/meta/js/dataAuthority/jquery.ztree.all.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/dataAuthority/dataZtree.js?_v=${vs}"></script>

<%--<script type="text/javascript" src="${ctx}/meta/js/dataAuthority/jquery.ztree.excheck.js?_v=${vs}"></script>--%>
<script>


</script>
<!-- <div class="fs14 tanchuang-pannel " role="main"> -->
<div class="" role="main">

    <span class="" style="float:right">
        <a href="javascript:DataAuthority.close();" class="btn btn-default">&times;</a>
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
                        <div class="form-group">
                            <label class="">权限级别：</label>
                            <label>
                                <input type="radio" name="authorityLevel" id="optionsRadios1" value="0" >全国
                            </label>
                            <label>
                                <input type="radio" name="authorityLevel" id="optionsRadios2" value="1" > 城市
                            </label>
                            <label>
                                <input type="radio" name="authorityLevel" id="optionsRadios3" value="2" >全国城市
                            </label>
                        </div>
                    </li>
                    <li>

                        <div class="form-group">
                            <label class="">架构树</label>
                            <ul id="treeDemo" class="ztree"></ul>
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