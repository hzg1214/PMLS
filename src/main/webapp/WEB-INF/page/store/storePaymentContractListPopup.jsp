<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<script type="text/javascript" src="${ctx}/meta/js/store/paymentContract.js?_v=${vs}"></script>
<script>
    $(function () {
        // 初始化查询
        PaymentContract.initList();
    });
</script>
<div class="" role="main">
    <span class="" style="float:right">
        <a href="javascript:StorePayment.close();" class="btn btn-default">&times;</a>
    </span>

    <div class="row">
        <div class="page-content" style="clear: initial;">
            <h4><strong>请选择退款合同</strong></h4>
            <!-- 搜索条件区域 -->
            <form id="contractInfoForm">
                <!-- 默认排序字段、排序类型 -->
                <input type="hidden" id="orderName" name="orderName" value="dateCreate">
                <input type="hidden" id="orderType" name="orderType" value="DESC">
                <input type="hidden" id="contractId" name="contractId">
                <ul class="list-inline form-inline" style="margin-bottom: 0px;">
                    <li>
                        <div class="form-group">
                            <label class="">合同编号</label>
                            <input type="text" class="form-control" id="contractNo" name="contractNo">
                        </div>
                        <div class="form-group">
                            <label class="">经纪公司</label>
                            <input type="text" class="form-control" id="companyName" name="companyName">
                        </div>
                        <button type="button" class="btn btn-primary" onclick="javascript:PaymentContract.search();">
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