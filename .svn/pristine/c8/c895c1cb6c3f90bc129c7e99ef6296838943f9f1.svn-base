<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>房友新房分销系统</title>
    <style>

        .w90 {
            width: 90px !important;
        }
        .w120 {
            width: 120px !important;
        }
        .layui-form-label {
            width: 95px !important;
        }
        .layui-table-link {
            cursor: pointer;
        }

    </style>

</head>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-form-item" style="  border-bottom: 1px solid #E6E6E6;">

                    <div class="layui-inline" style="font-weight: 400;margin-top: 10px;font-size: 16px;">
                        <label><b>项目编号：${estate.projectNo}</b> </label>&nbsp;&nbsp;&nbsp;
                        <label><b>楼盘名称：${estate.estateNm}</b> </label>
                        <input type="hidden" id="projectNo" name="projectNo" value="${estate.projectNo}">
                        <input type="hidden" id="estateId" name="estateId" value="${estate.estateId}">
                        <input type="hidden" id="estateName" name="estateName" value="${estate.estateNm}">
                    </div>

                    <div class="layui-inline" style="float: right;">

                        <input type="hidden" id="oaOperatorStr" value="${oaOperatorStr}"/>
                        <shiro:hasPermission name="/lnk:BL_PERMISSION">
                            <input type="hidden" id='permission' value="1"/>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/lnk:BL_PERMISSION">
                            <button type="button" id="toBatchBackSale" class="layui-btn " data-num="${rebackCount}"
                                    data-type="batchBackSale">批量退房(${rebackCount}套)
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/lnk:BL_PERMISSION">
                            <button type="button" id="toBatchSuccessSale" class="layui-btn " data-num="${saleCount}"
                                    data-type="batchSuccessSale">批量成销(${saleCount}套)
                            </button>
                        </shiro:hasPermission>
<%--                         <shiro:hasPermission name="/lnk:EXPENT_PERMIT"> --%>
<!--                             <input type="hidden" id='expentPermit' value="1"/> -->
<%--                             <c:if test="${oaOperatorStr eq '1' and  (estate.signDate ne null or estate.isSpecialProject eq 2)}"> --%>
<%--                                 <button type="button" id="toBatchExpent" class="layui-btn " data-num="${expentCount}" --%>
<%--                                         data-type="batchExpent">批量请款(${expentCount}套) --%>
<!--                                 </button> -->
<%--                             </c:if> --%>
<%--                         </shiro:hasPermission> --%>
                        <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
                    </div>
                </div>

                <div class="layui-form-item" style="margin-bottom:0px;">
                    <%--<div class="layui-inline">--%>
                    <%--<label class="layui-form-label">归属项目部</label>--%>
                    <%--<div class="layui-input-inline">--%>
                    <%--<select id="projectDepartmentId" name="projectDepartmentId"--%>
                    <%--lay-verify="projectDepartmentId" lay-filter="projectDepartmentId">--%>
                    <%--<option value="">请选择</option>--%>
                    <%--</select>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="layui-inline">
                        <label class="layui-form-label">业绩归属中心</label>
                        <div class="layui-input-inline">
                            <select id="centerId" name="centerId"
                                    lay-verify="centerId" lay-filter="centerId">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <%--<div class="layui-inline">--%>
                        <%--<label class="layui-form-label">订单编号</label>--%>
                        <%--<div class="layui-input-inline">--%>
                            <%--<input type="text" id="reportId" name="reportId" lay-verify="reportId" lay-filter="reportId"--%>
                                   <%--class="layui-input" autocomplete="off" placeholder="请输入">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="layui-inline">
                        <label class="layui-form-label">订单状态</label>
                        <div class="layui-input-inline">
                            <select id="status" name="status" xm-select="status" xm-select-skin="normal"
                                    xm-select-search="" xm-select-show-count='1'>
                                <option value="">请选择</option>
                                <option value="13501,13502">报备</option>
                                <option value="13502,13503">带看</option>
                                <option value="13504,13505">大定</option>
                                <option value="13507,13507">成销</option>
                                <option value="13508,13508">结佣</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">报备来源</label>
                        <div class="layui-input-inline">
                            <select id="customerFrom" name="customerFrom"
                                    lay-verify="customerFrom"
                                    lay-filter="customerFrom">
                                <option value="">请选择</option>
                                <option value="17401">PC</option>
                                <option value="17405">友房通</option>
                            </select>
                        </div>
                    </div>
                    <%--<div class="layui-inline">--%>
                    <%--<label class="layui-form-label">楼室号</label>--%>
                    <%--<div class="layui-input-inline">--%>
                    <%--<input type="text" id="buildingNo" name="buildingNo" lay-verify="buildingNo"--%>
                    <%--lay-filter="buildingNo" autocomplete="off" class="layui-input" placeholder="请输入">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="layui-inline">--%>
                    <%--<label class="layui-form-label">经纪公司</label>--%>
                    <%--<div class="layui-input-inline">--%>
                    <%--<input type="text" id="companyNm" name="companyNm" lay-verify="companyNm"--%>
                    <%--lay-filter="companyNm" autocomplete="off"--%>
                    <%--class="layui-input" placeholder="请输入">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="layui-inline">--%>
                    <%--<label class="layui-form-label">客户</label>--%>
                    <%--<div class="layui-input-inline">--%>
                    <%--<input type="text" id="customerNm" name="customerNm" lay-verify="customerNm"--%>
                    <%--autocomplete="off" lay-filter="customerNm" class="layui-input" placeholder="请输入">--%>
                    <%--</div>--%>
                    <%--</div>--%>


                    <div class="layui-inline">
                        <label class="layui-form-label">业务阶段</label>
                        <div class="layui-input-inline w90">
                            <select id="dateTypeKbn" name="dateTypeKbn" lay-filter="dateTypeKbn">
                                <option value="">请选择</option>
                                <option value="13701">报备</option>
                                <option value="13702">带看</option>
                                <option value="13703">大定</option>
                                <option value="13704">成销</option>
                                <option value="13707">结佣</option>
                            </select>
                        </div>
                        <div class="layui-input-inline w90">
                            <input type="text" name="dateStart" id="dateStart" lay-verify="dateStart"
                                   lay-filter="dateStart" placeholder="开始时间"
                                   autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-input-inline w90">
                            <input type="text" name="dateEnd" id="dateEnd" lay-verify="dateEnd"
                                   lay-filter="dateEnd" placeholder="结束时间"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">关键字</label>
                        <div class="layui-input-inline w120">
                            <select id="keyTypeKbn" name="keyTypeKbn" lay-filter="keyTypeKbn">
                                <option value="">请选择</option>
                                <option value="1">订单编号</option>
                                <option value="2">经纪公司</option>
                                <option value="3">楼室号</option>
                                <option value="4">客户</option>
                                <option value="5">业绩归属人</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" id="keyword" name="keyword"
                                   lay-verify="keyword" lay-filter="keyword"
                                   autocomplete="off"
                                   class="layui-input" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <table id="contentTable" lay-size="sm" lay-filter="content"></table>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/projectDetailList.js?v=${vs}"></script>

</body>
</html>