<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>修改日志</title>
    <style type="text/css">
        body {
            padding: 0px;
            overflow: hidden;
        }

        dl {
            max-height: 180px !important;
        }

        .layui-form-item {
            padding-bottom: 0px !important;
            margin-bottom: 0px !important;
        }
        .lable-form-left {
            width: 150px !important;
        }

        .lable-form-right {
            width: 400px !important;
            text-align: left;
        }

    </style>
<body>
<script type="application/javascript">
    var storeLogView = ${storeLog};
</script>

<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-form" >
            <c:if test="${storeLog.changeName == 1 }">
                <div class="layui-form-item">
                    <label class="layui-form-label lable-form-left ">修改项目名称：</label>
                    <label class="layui-form-label lable-form-right ">${storeLog.updateIteam}</label>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label lable-form-left">原门店名称：</label>
                    <label class="layui-form-label lable-form-right ">${storeLog.oldStoreName}</label>
                </div>
            </c:if>
            <c:if test="${storeLog.changeAddress == 1 }">
                <div class="layui-form-item">
                    <label class="layui-form-label lable-form-left">原门店地址：</label>
                    <label class="layui-form-label lable-form-right ">${storeLog.oldAddressDetail}</label>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label lable-form-left">修改后门店地址：</label>
                    <label class="layui-form-label lable-form-right ">${storeLog.newAddressDetail}</label>
                </div>
            </c:if>
            <div class="layui-form-item">
                <label class="layui-form-label lable-form-left">修改人：</label>
                <label class="layui-form-label lable-form-right ">${storeLog.userName}</label>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label lable-form-left">修改时间：</label>
                <label class="layui-form-label lable-form-right ">${storeLog.updateDate}</label>
            </div>
        </div>
    </div>
</div>

<div class="layui-card">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>附件</legend>
        </fieldset>

        <div id="uploadImg" style="padding: 0 70px;">
            <div>附件(营业执照等)</div>

            <hr class="dottedLine"/>
            <div class="layui-row">
                <div class="layui-col-xs12 lable-right">
                    <div class="layui-upload-list upload_img_list">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['element', 'laydate', 'layedit', 'form', 'table', 'layer', 'upload'], function () {
        var element = layui.element,
            form = layui.form,
            layer = layui.layer,
            upload = layui.upload,
            $ = layui.$;

        loadImageList("uploadImg", storeLogView.fileDtos, false);

    });
</script>

</body>
</html>
