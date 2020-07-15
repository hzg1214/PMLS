<%@ page contentType="text/html;charset=UTF-8"
         trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>新建人员</title>
    <%@include file="../../common/common.jsp" %>
    <style type="text/css">
     	body {
            padding: 0px;
            overflow: hidden;
        }
        .layui-input-block2 {
            margin-left: 110px;
            min-height: 36px;
            margin-right: 20px;
        }

        .labelWidth {
            width: 70px !important;
        }

        .itemClass {
            width: 420px !important;
            margin-left: 20px;
            margin-top: 15px;
        }

        .layui-anim-upbit {
            max-height: 150px !important; /* 适当调 300以下 合适就好 */
        }

        .xm-select-dl {
            max-height: 150px !important; /* 适当调 300以下 合适就好 */
        }
    </style>
</head>
<script type="application/javascript">

    var id = '${id}';
    var userDto = '${userDto}';

</script>
<body>
<div class="layui-card">
    <div class="layui-card-body" style="height: 300px">
        <div class="layui-form myForm" >
            <input type="hidden" name="id" id="id" value="${id}">
            <div class="layui-form-item itemClass" >
                <label class="layui-form-label labelWidth"><span class="redSpan">*</span>城市</label>
                <div class="layui-input-block2">
                    <select id="cityNo" lay-filter="cityNo" lay-search="" style="width: 220px!important;"
                            placeholder="请选择">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item itemClass">
                <label class="layui-form-label labelWidth"><span class="redSpan">*</span>中心</label>
                <c:choose>
                    <c:when test="${!empty id}">
                        <div class="layui-input-block2">
                            <select id="centerGroup" lay-filter="centerGroup" lay-search="" placeholder="请选择">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="layui-input-block2">
                            <select id="addCenterIds" name="addCenterIds" lay-filter="addCenterIds"
                                    xm-select="addCenterIds"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="layui-form-item itemClass">
                <label class="layui-form-label labelWidth"><span class="redSpan">*</span>人员工号</label>
                <div class="layui-input-block2">
                    <input type="text" class="layui-input" id="userCode" autocomplete="off"
                           placeholder="请输入" lay-verify="">
                </div>
            </div>
            <div class="layui-form-item itemClass" style="margin-top: 17px;">
                <label class="layui-form-label labelWidth"><span class="redSpan">*</span>人员姓名</label>
                <div class="layui-input-block2">
                    <input type="text" class="layui-input" id="userName" autocomplete="off"
                           placeholder="请输入" lay-verify="">
                </div>
            </div>
            <%--<div class="layui-form-item itemClass">--%>
            <%--<label class="layui-form-label labelWidth"><span class="redSpan">*</span>是否pmls</label>--%>
            <%--<div class="layui-input-block2">--%>
            <%--<select id="isPmlsCenter" name="isPmlsCenter" lay-filter="isPmlsCenter">--%>
            <%--<option value="1">是</option>--%>
            <%--<option value="0">否</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--</div>--%>
        </div>
    </div>
</div>
<script
        src="${pageContext.request.contextPath}/meta/pmls/js/basicInformation/personnelPermissions/addUserPage.js?v=${vs}"></script>
</body>
</html>
