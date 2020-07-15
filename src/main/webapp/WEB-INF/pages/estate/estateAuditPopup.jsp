<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>项目审核</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .editPopupLay .layui-form{
            padding-top: 5px;
            height: 274px;
            background-color: #FFF;
            overflow: hidden;
        }
        .editPopupLay .layui-form .hidden{
            display: none;
        }
        .editPopupLay .layui-form-item .layui-inline .layui-form-label .requiredClass{
            color: #FF0000;
        }
    </style>
</head>
<script type="application/javascript">

</script>
<body>

<div class="editPopupLay">
    <input type="hidden" id="id" name="id" value="${id}"/>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form">
                <div class="layui-form-item text">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>审核结果</label>
                        <div class="layui-input-inline" style="width: auto;">
                            <input type="radio" name="auditStatus" value="12903" title="审核通过" lay-filter="auditStatus">
                            <input type="radio" name="auditStatus" value="12902" title="审核不通过" lay-filter="auditStatus">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: 100%;">
                        <label class="layui-form-label">
                            <span class="requiredClass hidden auditStatusSpan">*</span>原因
                        </label>
                        <div class="layui-input-inline">
                            <textarea id="auditMemo" name="auditMemo" placeholder="请输入原因" class="layui-textarea" style="width: 320px;height: 140px;"></textarea>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/meta/pmls/js/estate/estateAuditPopup.js?_v=${vs}"></script>
</body>
</html>
