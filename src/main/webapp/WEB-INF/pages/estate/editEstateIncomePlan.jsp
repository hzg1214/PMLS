<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>收入方案维护</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .releaseLay {
            height: 349px;
            background-color: #ffffff;
        }
        .releaseLay .layui-form{
            padding-top: 15px;
        }
        .releaseLay .layui-form-item .layui-inline{
            /*width: 455px;*/
        }
        .releaseLay .layui-form-item .layui-inline .layui-form-label{
            width: 160px;
        }
        .releaseLay .layui-form-item .layui-input-inline{
            width: 320px;
            height: 38px;
            line-height: 38px;
        }
        .releaseLay .xm-select-parent .xm-form-selected dl{
            max-height: 225px;
        }
    </style>
</head>
<script type="application/javascript">

</script>
<body>

<div class="releaseLay">
    <input type="hidden" id="id" name="id" value="${planDto.id}"/>
    <div class="layui-form">
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <table class="layui-table">
                    <colgroup>
                        <col width="60">
                        <col width="200">
                        <col width="180">
                        <col width="170">
                        <col>
                    </colgroup>
                    <thead>
                    <tr>
                        <th style="text-align:center;">条件</th>
                        <th style="text-align:center;">佣金</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td align="center">每套</td>
                        <input type="hidden" id="planType" name="planType" value="${planDto.planType}"/>
                        <td align="center">固定<input type="text" style="width: 100px;height: 25px;text-align: right;" maxlength="18" oninput="this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]" id="fixAmount" name="fixAmount" value="${planDto.fixAmount}"/>元&nbsp;&nbsp;+&nbsp;&nbsp;总价<input type="text" style="width: 100px;height: 25px;text-align: right;" id="totalPercentage" oninput="value=checkPercentage(value)" maxlength="18" name="totalPercentage" value="${planDto.totalPercentage}"/>%</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript" src="${ctx}/meta/pmls/js/estate/editEstateIncomePlan.js?_v=${vs}"></script>
</body>
</html>
