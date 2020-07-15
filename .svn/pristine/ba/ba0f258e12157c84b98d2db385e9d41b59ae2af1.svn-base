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
        .w300 {
            width: 300px !important;
        }

        .layui-tab-content .layui-form-label {
            width: 95px !important;
        }

        .layui-table-link {
            cursor: pointer;
        }
    </style>

</head>
<body>

<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-form">
            <div class="layui-tab layui-tab-card" lay-filter="reportTab">
                <ul class="layui-tab-title">
                    <li lay-id="all" act-type="all" class="layui-this">全部</li>
                    <li lay-id="register" act-type="register">报备</li>
                    <li lay-id="watch" act-type="watch">带看</li>
                    <li lay-id="rought" act-type="rought">大定</li>
                    <li lay-id="sale" act-type="sale">成销</li>
                    <li lay-id="commission" act-type="commission">结佣</li>
                    <input type="hidden" id="backTab" value="${backTab}">
                    <shiro:hasPermission name="/lnk:BL_PERMISSION">
                        <scrpit type="text/javascript">
                            <input type="hidden" id='permission' value="1"/>
                        </scrpit>
                    </shiro:hasPermission>
                </ul>

                <div class="layui-tab-content" style="padding-top: 10px">
                    <div id="allTabItem" class="layui-tab-item layui-show">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">归属项目部</label>
                                <div class="layui-input-inline">
                                    <select id="all-projectDepartmentId" name="all-projectDepartmentId"
                                            lay-verify="all-projectDepartmentId"
                                            lay-filter="all-projectDepartmentId">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">订单编号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="all-reportId" name="all-reportId"
                                           lay-verify="all-reportId" lay-filter="all-reportId" autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">报备来源</label>
                                <div class="layui-input-inline">
                                    <select id="all-customerFrom" name="all-customerFrom"
                                            lay-verify="all-customerFrom"
                                            lay-filter="all-customerFrom">
                                        <option value="">请选择</option>
                                        <option value="17401">PC</option>
                                        <option value="17405">友房通</option>
                                    </select>
                                </div>
                            </div>

                            <%--<div class="layui-inline">--%>
                            <%--<label class="layui-form-label">经纪公司</label>--%>
                            <%--<div class="layui-input-inline">--%>
                            <%--<input type="text" id="all-companyNm" name="all-companyNm"--%>
                            <%--lay-verify="all-companyNm" lay-filter="all-companyNm" autocomplete="off"--%>
                            <%--class="layui-input" placeholder="请输入经纪公司">--%>
                            <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="layui-inline">--%>
                            <%--<label class="layui-form-label">楼室号</label>--%>
                            <%--<div class="layui-input-inline">--%>
                            <%--<input type="text" id="all-buildingNo" name="all-buildingNo"--%>
                            <%--lay-verify="all-buildingNo"--%>
                            <%--lay-filter="all-buildingNo" autocomplete="off"--%>
                            <%--class="layui-input" placeholder="请输入楼室号">--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="layui-inline">--%>
                            <%--<label class="layui-form-label">客户</label>--%>
                            <%--<div class="layui-input-inline">--%>
                            <%--<input type="text" id="all-customerNm" name="all-customerNm"--%>
                            <%--lay-verify="all-customerNm"--%>
                            <%--lay-filter="all-customerNm" autocomplete="off"--%>
                            <%--class="layui-input" placeholder="请输入客户">--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <div class="layui-inline">
                                <label class="layui-form-label">业务阶段</label>
                                <div class="layui-input-inline w90">
                                    <select id="all-dateTypeKbn" name="all-dateTypeKbn" lay-filter="all-dateTypeKbn">
                                        <option value="">请选择</option>
                                        <option value="13701">报备</option>
                                        <option value="13702">带看</option>
                                        <option value="13703">大定</option>
                                        <option value="13704">成销</option>
                                        <option value="13707">结佣</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="all-dateStart" id="all-dateStart" lay-verify="all-dateStart"
                                           autocomplete="off" lay-filter="all-dateStart" class="layui-input"
                                           placeholder="开始日期">
                                </div>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="all-dateEnd" id="all-dateEnd" lay-verify="all-dateEnd"
                                           autocomplete="off" lay-filter="all-dateEnd" class="layui-input"
                                           placeholder="结束日期">
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label">关键字</label>
                                <div class="layui-input-inline w120">
                                    <select id="all-keyTypeKbn" name="all-keyTypeKbn" lay-filter="all-keyTypeKbn">
                                        <option value="">请选择</option>
                                        <option value="0">项目</option>
                                        <option value="2">经纪公司</option>
                                        <option value="3">楼室号</option>
                                        <option value="4">客户</option>
                                        <option value="5">业绩归属人</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline">
                                    <input type="text" id="all-keyword" name="all-keyword"
                                           lay-verify="all-keyword" lay-filter="all-keyword" autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>


                            <div class="layui-inline toolbar">
                                <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                                <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <table id="all-contentTable" lay-size="sm" lay-filter="all-content"></table>
                        </div>
                    </div>
                    <div id="registerTabItem" class="layui-tab-item">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">归属项目部</label>
                                <div class="layui-input-inline">
                                    <select id="register-projectDepartmentId"
                                            name="register-projectDepartmentId"
                                            lay-verify="register-projectDepartmentId"
                                            lay-filter="register-projectDepartmentId">
                                        <option value="">请选择/option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">订单编号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="register-reportId" name="register-reportId"
                                           lay-verify="register-reportId"
                                           lay-filter="register-reportId" autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label">报备来源</label>
                                <div class="layui-input-inline">
                                    <select id="register-customerFrom" name="register-customerFrom"
                                            lay-verify="register-customerFrom"
                                            lay-filter="register-customerFrom">
                                        <option value="">请选择</option>
                                        <option value="17401">PC</option>
                                        <option value="17405">友房通</option>
                                    </select>
                                </div>
                            </div>
                            <%--<div class="layui-inline">--%>
                            <%--<label class="layui-form-label">经纪公司</label>--%>
                            <%--<div class="layui-input-inline">--%>
                            <%--<input type="text" id="register-companyNm" name="register-companyNm"--%>
                            <%--lay-verify="register-companyNm"--%>
                            <%--lay-filter="register-companyNm" autocomplete="off"--%>
                            <%--class="layui-input" placeholder="请输入">--%>
                            <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="layui-inline">--%>
                            <%--<label class="layui-form-label">客户</label>--%>
                            <%--<div class="layui-input-inline">--%>
                            <%--<input type="text" id="register-customerNm" name="register-customerNm"--%>
                            <%--lay-verify="register-customerNm"--%>
                            <%--lay-filter="register-customerNm" autocomplete="off"--%>
                            <%--class="layui-input" placeholder="请输入">--%>
                            <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="layui-inline">--%>
                            <%--<label class="layui-form-label">业绩归属中心</label>--%>
                            <%--<div class="layui-input-inline">--%>
                            <%--<select id="register-centerId" name="register-centerId"--%>
                            <%--lay-verify="register-centerId" lay-filter="register-centerId">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--</select>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <div class="layui-inline">
                                <label class="layui-form-label">报备日期</label>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="register-dateStart" id="register-dateStart"
                                           lay-verify="register-dateStart" lay-filter="register-dateStart"
                                           placeholder="开始日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="register-dateEnd" id="register-dateEnd"
                                           lay-verify="register-dateEnd" lay-filter="register-dateEnd"
                                           placeholder="结束日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label">关键字</label>
                                <div class="layui-input-inline w120">
                                    <select id="register-keyTypeKbn" name="register-keyTypeKbn" lay-filter="register-keyTypeKbn">
                                        <option value="">请选择</option>
                                        <option value="0">项目</option>
                                        <option value="2">经纪公司</option>
                                        <option value="3">楼室号</option>
                                        <option value="4">客户</option>
                                        <option value="5">业绩归属人</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline">
                                    <input type="text" id="register-keyword" name="register-keyword"
                                           lay-verify="register-keyword" lay-filter="register-keyword"
                                           autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>

                            <div class="layui-inline toolbar">
                                <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                                <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <table id="register-contentTable" lay-size="sm" lay-filter="register-content"></table>
                        </div>
                    </div>
                    <div id="watchTabItem" class="layui-tab-item">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">归属项目部</label>
                                <div class="layui-input-inline">
                                    <select id="watch-projectDepartmentId"
                                            name="watch-projectDepartmentId"
                                            lay-verify="watch-projectDepartmentId"
                                            lay-filter="watch-projectDepartmentId">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">订单编号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="watch-reportId" name="watch-reportId"
                                           lay-verify="watch-reportId"
                                           lay-filter="watch-reportId" autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">报备来源</label>
                                <div class="layui-input-inline">
                                    <select id="watch-customerFrom" name="watch-customerFrom"
                                            lay-verify="watch-customerFrom"
                                            lay-filter="watch-customerFrom">
                                        <option value="">请选择</option>
                                        <option value="17401">PC</option>
                                        <option value="17405">友房通</option>
                                    </select>
                                </div>
                            </div>

                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">经纪公司</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" id="watch-companyNm" name="watch-companyNm"--%>
                                           <%--lay-verify="watch-companyNm"--%>
                                           <%--lay-filter="watch-companyNm" autocomplete="off"--%>
                                           <%--class="layui-input" placeholder="请输入">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">客户</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" id="watch-customerNm" name="watch-customerNm"--%>
                                           <%--lay-verify="watch-customerNm"--%>
                                           <%--lay-filter="watch-customerNm" autocomplete="off"--%>
                                           <%--class="layui-input" placeholder="请输入">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">业绩归属中心</label>--%>

                                <%--<div class="layui-input-inline">--%>
                                    <%--<select id="watch-centerId" name="watch-centerId"--%>
                                            <%--lay-verify="watch-centerId" lay-filter="watch-centerId">--%>
                                        <%--<option value="">请选择</option>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="layui-inline">
                                <label class="layui-form-label">带看日期</label>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="watch-dateStart" id="watch-dateStart"
                                           lay-verify="watch-dateStart" lay-filter="watch-dateStart"
                                           placeholder="开始日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="watch-dateEnd" id="watch-dateEnd"
                                           lay-verify="watch-dateEnd" lay-filter="watch-dateEnd" placeholder="结束日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">关键字</label>
                                <div class="layui-input-inline w120">
                                    <select id="watch-keyTypeKbn" name="watch-keyTypeKbn" lay-filter="watch-keyTypeKbn">
                                        <option value="">请选择</option>
                                        <option value="0">项目</option>
                                        <option value="2">经纪公司</option>
                                        <option value="3">楼室号</option>
                                        <option value="4">客户</option>
                                        <option value="5">业绩归属人</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline ">
                                    <input type="text" id="watch-keyword" name="watch-keyword"
                                           lay-verify="watch-keyword" lay-filter="watch-keyword"
                                           autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline toolbar">
                                <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                                <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <table id="watch-contentTable" lay-size="sm" lay-filter="watch-content"></table>
                        </div>
                    </div>
                    <div id="roughtTabItem" class="layui-tab-item">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">归属项目部</label>
                                <div class="layui-input-inline">
                                    <select id="rought-projectDepartmentId" name="rought-projectDepartmentId"
                                            lay-verify="rought-projectDepartmentId"
                                            lay-filter="rought-projectDepartmentId">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">订单编号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="rought-reportId" name="rought-reportId"
                                           lay-verify="rought-reportId"
                                           lay-filter="rought-reportId" autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">报备来源</label>
                                <div class="layui-input-inline">
                                    <select id="rought-customerFrom" name="rought-customerFrom"
                                            lay-verify="rought-customerFrom"
                                            lay-filter="rought-customerFrom">
                                        <option value="">请选择</option>
                                        <option value="17401">PC</option>
                                        <option value="17405">友房通</option>
                                    </select>
                                </div>
                            </div>

                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">经纪公司</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" id="rought-companyNm" name="rought-companyNm"--%>
                                           <%--lay-verify="rought-companyNm"--%>
                                           <%--lay-filter="rought-companyNm" autocomplete="off"--%>
                                           <%--class="layui-input" placeholder="请输入">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">楼室号</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" id="rought-buildingNo" name="rought-buildingNo"--%>
                                           <%--lay-verify="rought-buildingNo"--%>
                                           <%--lay-filter="rought-buildingNo" autocomplete="off"--%>
                                           <%--class="layui-input" placeholder="请输入">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">客户</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" id="rought-customerNm" name="rought-customerNm"--%>
                                           <%--lay-verify="rought-customerNm"--%>
                                           <%--lay-filter="rought-customerNm" autocomplete="off"--%>
                                           <%--class="layui-input" placeholder="请输入">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="layui-inline">
                                <label class="layui-form-label">大定日期</label>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="rought-dateStart" id="rought-dateStart"
                                           lay-verify="rought-dateStart" lay-filter="rought-dateStart"
                                           placeholder="开始日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="rought-dateEnd" id="rought-dateEnd"
                                           lay-verify="rought-dateEnd" lay-filter="rought-dateEnd"
                                           placeholder="结束日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">关键字</label>
                                <div class="layui-input-inline w120">
                                    <select id="rought-keyTypeKbn" name="rought-keyTypeKbn" lay-filter="rought-keyTypeKbn">
                                        <option value="">请选择</option>
                                        <option value="0">项目</option>
                                        <option value="2">经纪公司</option>
                                        <option value="3">楼室号</option>
                                        <option value="4">客户</option>
                                        <option value="5">业绩归属人</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline ">
                                    <input type="text" id="rought-keyword" name="rought-keyword"
                                           lay-verify="rought-keyword" lay-filter="rought-keyword"
                                           autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline toolbar">
                                <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                                <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <table id="rought-contentTable" lay-size="sm" lay-filter="rought-content"></table>
                        </div>
                    </div>
                    <div id="saleTabItem" class="layui-tab-item">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">归属项目部</label>
                                <div class="layui-input-inline">
                                    <select id="sale-projectDepartmentId" name="sale-projectDepartmentId"
                                            lay-verify="sale-projectDepartmentId"
                                            lay-filter="sale-projectDepartmentId">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">订单编号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="sale-reportId" name="sale-reportId"
                                           lay-verify="sale-reportId"
                                           lay-filter="sale-reportId" autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">报备来源</label>
                                <div class="layui-input-inline">
                                    <select id="sale-customerFrom" name="sale-customerFrom"
                                            lay-verify="sale-customerFrom"
                                            lay-filter="sale-customerFrom">
                                        <option value="">请选择</option>
                                        <option value="17401">PC</option>
                                        <option value="17405">友房通</option>
                                    </select>
                                </div>
                            </div>

                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">经纪公司</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" id="sale-companyNm" name="sale-companyNm"--%>
                                           <%--lay-verify="sale-companyNm"--%>
                                           <%--lay-filter="sale-companyNm" autocomplete="off"--%>
                                           <%--class="layui-input" placeholder="请输入">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">楼室号</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" id="sale-buildingNo" name="sale-buildingNo"--%>
                                           <%--lay-verify="sale-buildingNo"--%>
                                           <%--lay-filter="sale-buildingNo" autocomplete="off"--%>
                                           <%--class="layui-input" placeholder="请输入">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">客户</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" id="sale-customerNm" name="sale-customerNm"--%>
                                           <%--lay-verify="sale-customerNm"--%>
                                           <%--lay-filter="sale-customerNm" autocomplete="off"--%>
                                           <%--class="layui-input" placeholder="请输入">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="layui-inline">
                                <label class="layui-form-label">成销日期</label>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="sale-dateStart" id="sale-dateStart"
                                           lay-verify="sale-dateStart" lay-filter="sale-dateStart"
                                           placeholder="开始日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="sale-dateEnd" id="sale-dateEnd"
                                           lay-verify="sale-dateEnd" lay-filter="sale-dateEnd" placeholder="结束日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label">关键字</label>
                                <div class="layui-input-inline w120">
                                    <select id="sale-keyTypeKbn" name="sale-keyTypeKbn" lay-filter="sale-keyTypeKbn">
                                        <option value="">请选择</option>
                                        <option value="0">项目</option>
                                        <option value="2">经纪公司</option>
                                        <option value="3">楼室号</option>
                                        <option value="4">客户</option>
                                        <option value="5">业绩归属人</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline">
                                    <input type="text" id="sale-keyword" name="sale-keyword"
                                           lay-verify="sale-keyword" lay-filter="sale-keyword"
                                           autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline toolbar">
                                <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                                <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <table id="sale-contentTable" lay-size="sm" lay-filter="sale-content"></table>
                        </div>
                    </div>
                    <div id="commissionTabItem" class="layui-tab-item">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">归属项目部</label>
                                <div class="layui-input-inline">
                                    <select id="commission-projectDepartmentId"
                                            name="commission-projectDepartmentId"
                                            lay-verify="commission-projectDepartmentId"
                                            lay-filter="commission-projectDepartmentId">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">订单编号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="commission-reportId" name="commission-reportId"
                                           lay-verify="commission-reportId"
                                           lay-filter="commission-reportId" autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">报备来源</label>
                                <div class="layui-input-inline">
                                    <select id="commission-customerFrom" name="commission-customerFrom"
                                            lay-verify="commission-customerFrom"
                                            lay-filter="commission-customerFrom">
                                        <option value="">请选择</option>
                                        <option value="17401">PC</option>
                                        <option value="17405">友房通</option>
                                    </select>
                                </div>
                            </div>

                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">经纪公司</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" id="commission-companyNm" name="commission-companyNm"--%>
                                           <%--lay-verify="commission-companyNm"--%>
                                           <%--lay-filter="commission-companyNm" autocomplete="off"--%>
                                           <%--class="layui-input" placeholder="请输入">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="layui-inline">
                                <label class="layui-form-label">结佣日期</label>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="commission-dateStart" id="commission-dateStart"
                                           lay-verify="commission-dateStart" lay-filter="commission-dateStart"
                                           placeholder="开始日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-input-inline w90">
                                    <input type="text" name="commission-dateEnd" id="commission-dateEnd"
                                           lay-verify="commission-dateEnd" lay-filter="commission-dateEnd"
                                           placeholder="结束日期"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">关键字</label>
                                <div class="layui-input-inline w120">
                                    <select id="commission-keyTypeKbn" name="commission-keyTypeKbn" lay-filter="commission-keyTypeKbn">
                                        <option value="">请选择</option>
                                        <option value="0">项目</option>
                                        <option value="2">经纪公司</option>
                                        <option value="3">楼室号</option>
                                        <option value="4">客户</option>
                                        <option value="5">业绩归属人</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline">
                                    <input type="text" id="commission-keyword" name="commission-keyword"
                                           lay-verify="commission-keyword" lay-filter="commission-keyword"
                                           autocomplete="off"
                                           class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline toolbar">
                                <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                                <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <table id="commission-contentTable" lay-size="sm"
                                   lay-filter="commission-content"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/reportList.js?v=${vs}"></script>

</body>
</html>