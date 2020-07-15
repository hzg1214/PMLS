<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房友新房分销系统</title>
    <%@include file="../common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
        .searchForm{
            padding-top: 5px;
        }
    </style>
</head>
<body>
<script type="application/javascript">
    var userId = '${userInfo.userId}';
    var userIdList = ${userIdList};

</script>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form id="searchForm" class="layui-form searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">归属项目部</label>
                        <div class="layui-input-inline">
                            <select id="projectDepartmentId" name="projectDepartmentId" lay-verify="required" lay-search="">
                                <option value="">请选择</option>
                                <c:forEach items="${rebacklist}" var="list">
                                    <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目</label>
                        <div class="layui-input-inline">
                            <input type="text" id="estateNm" autocomplete="off" name="estateNm" class="layui-input"   lay-verify="" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">审核状态</label>
                        <div class="layui-input-inline">
                            <select id="auditStatus" name="auditStatus" lay-verify="required" lay-search="">
                                <option value="">请选择</option>
                                <option value="12901">未审核</option>
                                <option value="12902">不通过</option>
                                <option value="12903">通过</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-bottom:0px;">
                    <div class="layui-inline">
                        <label class="layui-form-label">项目状态</label>
                        <div class="layui-input-inline">
                            <select id="projectStatus" name="projectStatus" lay-verify="required" lay-search="">
                                <option value="">请选择</option>
                                <option value="20301">跟单</option>
                                <option value="20306">立项</option>
                                <option value="20302">签约</option>
                                <option value="20303">结案</option>
                                <option value="20304">取消跟单</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">合作方</label>
                        <div class="layui-input-inline">
                            <input type="text" id="partnerNm" autocomplete="off" name="partnerNm" class="layui-input" lay-verify="" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="reload">查询</button>
                        <button type="reset" class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<div>
    <div class="layui-card">
    	<shiro:hasPermission name="/estate:ADD_ESTATE">
	        <div class="layui-card-header">
	            <div class="layui-btn-group toolbar">
	                <button class="layui-btn" data-type="addPmlsEstate">新建项目</button>
	            </div>
	        </div>
        </shiro:hasPermission>
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>

</div>
</body>
</html>
<script type="text/html" id="toolMainTable">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    {{#  if(d.releaseStatus != '13001' && d.auditStatus!='12903' && d.auditStatus!='12904' && (d.empId == userId || d.sceneEmpId==userId)
            && d.projectStatus != '20302' && d.projectStatus != '20303' && d.projectStatus != '20304'
            && d.projectStatus != '20306'){ }}
        <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">编辑</a>
    {{#  } }}

    {{#  if(d.releaseStatus != '13001' && d.auditStatus=='12904' && (d.empId == userId || d.sceneEmpId==userId)
            && d.projectStatus == '20301'){ }}
        <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="editInfo">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="editPartner">合作信息维护</a>
    {{#  } }}

    <%--<shiro:hasPermission name="/pmlsEstate:ESTATE_AUDIT">
        {{#  if(d.auditStatus == '12901'){ }}
        <a class="layui-btn layui-btn-xs" lay-event="audit">审核</a>
        {{#  } }}
    </shiro:hasPermission>--%>

    {{#  if(d.auditStatus == '12901' && d.empId == userId){ }}
<%--        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="revocation">撤回</a>--%>
    {{#  } }}
    {{#  if(d.projectStatus == '20304'){ }}
        <shiro:hasPermission name="/pmlsEstate:ESTATE_START">
        <%--<a class="layui-btn layui-btn-xs" lay-event="startProject">跟单</a>--%>
        </shiro:hasPermission>
    {{#  } else if (d.projectStatus == '20301'){ }}
        <shiro:hasPermission name="/pmlsEstate:ESTATE_START">
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="cancelProject">取消跟单</a>
        </shiro:hasPermission>
    {{#  } else if (d.projectStatus == '20302'){ }}
        <shiro:hasPermission name="/pmlsEstate:ESTATE_CLOSE">
        <a class="layui-btn layui-btn-xs" lay-event="closeProject">结案</a>
        </shiro:hasPermission>
    {{#  } else if (d.projectStatus == '20303'){ }}
        <shiro:hasPermission name="/pmlsEstate:ESTATE_CLOSE">
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="cancelCloseProject">取消结案</a>
        </shiro:hasPermission>
    {{#  } }}
</script>
<script type="text/javascript" src="${ctx}/meta/pmls/js/estate/estateList.js?_v=${vs}"></script>
