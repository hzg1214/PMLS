<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>工单详情</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .lable-left{
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
            width: 200px;
        }
        .lable-right{
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
            width: 300px;
        }
        .myForm .layui-form-label2{
            margin-left: 150px;
            min-height: 36px;
            margin-right: 20px;
            width: 450px;
            float:left
        }
        .myForm .layui-input-block{
            margin-left: 150px;
            min-height: 36px;
            margin-right: 20px;
            width: 450px;
        }
        .layui-tab-item .layui-table thead>tr{
            color:#999999;
        }
        .layui-btn-mini {
            height: 22px;
            line-height: 22px;
            padding: 0 5px;
            font-size: 12px;
        }
    </style>
</head>
<script type="application/javascript">
    var id='${id}';
    var keFuOrderJson='${keFuOrderJson}';
</script>

<script>
    function htmlEscape(html) {
        var escapeMap = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#x27;',
            '`': '&#x60;'
        };
        var source = '(?:' + Object.keys(escapeMap).join('|') + ')',
            testRegexp = new RegExp(source),
            replaceRegexp = new RegExp(source, 'g'),
            string = html == null ? '' : '' + html;
        return testRegexp.test(string) ? string.replace(replaceRegexp, function (match) {
            return escapeMap[match];
        }) : string;
    }
</script>

<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div style="margin: 0 auto;">
                <div class="layui-row blockBody">
                    <div class="layui-col-xs6">
                        <div class="blockTitle">工单详情</div>
                    </div>
                    <div class="layui-col-xs6 blockBtn">
                        <!-- 经办人才能对 (问题状态未处理、处理中) 的工单 有 回复 权限 -->
                        <!-- 处于未处理、处理中的 -->
                        <c:if test="${(keFuOrder.dealStatus ne 24203)}">
                            <c:choose>
                                <c:when test="${keFuOrder.userCode eq userInfo.userCode}">
                                    <button type="button" class="layui-btn reply" onclick="javascript:gdReply('${keFuOrder.id}','${keFuOrder.dealStatus}','${keFuOrder.userCode}','${keFuOrder.userCreate}');">回复</button>
                                    <button type="button" class="layui-btn reAlert" onclick="javascript:reAlert('${keFuOrder.id}');">提醒经办人</button>
                                </c:when>
                                <c:otherwise>
                                    <shiro:hasPermission name="/KEFUORDER:GD_REPLY">
                                        <button type="button" class="layui-btn reply" onclick="javascript:gdReply('${keFuOrder.id}','${keFuOrder.dealStatus}','${keFuOrder.userCode}','${keFuOrder.userCreate}');">回复</button>
                                        <button type="button" class="layui-btn reAlert" onclick="javascript:reAlert('${keFuOrder.id}');">提醒经办人</button>
                                    </shiro:hasPermission>
                                </c:otherwise>
                            </c:choose>
                        </c:if>

                        <shiro:hasPermission name="/KEFUORDER:GD_CHECK">
                            <!-- 回复处理完成、核验未完成 -->
                            <c:if test="${(keFuOrder.dealStatus eq 24203) && (keFuOrder.checkStatus ne 24302)}">
                                <button type="button" class="layui-btn reAlert" onclick="javascript:gdCheck('${keFuOrder.id}','${keFuOrder.dealStatus}','${keFuOrder.checkStatus}','${keFuOrder.userCode}','${keFuOrder.userCreate}');">核验</button>
                            </c:if>
                        </shiro:hasPermission>

                        <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                    </div>
                </div>

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">工单编号：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.orderNo}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">归属城市：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.cityName}</div>
                    <div class="layui-col-xs2 lable-left">门店城市：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.storeCityName}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">经纪公司：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.companyName}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">门店：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.storeName}</div>
                    <div class="layui-col-xs2 lable-left">门店地址：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.storeAddress}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">客户姓名：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.customerName}</div>
                    <div class="layui-col-xs2 lable-left">客户电话：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.customerTel}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">咨询产品名称：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.consultProductNm}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">问题一级分类：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.categoryOneNm}</div>
                    <div class="layui-col-xs2 lable-left">问题二级分类：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.categoryTwoNm}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">问题概要：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.questionTopic}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">问题描述：</div>
                    <div class="layui-col-xs10 lable-right">${keFuOrder.questionDesc}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">工单优先级：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.questionLevelNm}</div>
                    <div class="layui-col-xs2 lable-left">经办人：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.userName}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">创建人：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.createName}</div>
                    <div class="layui-col-xs2 lable-left">创建日期：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.dateCreate}</div>
                </div>

                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">问题状态：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.dealStatusStr}</div>
                    <div class="layui-col-xs2 lable-left">核验状态：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.checkStatusStr}</div>
                </div>

                <c:if test="${(keFuOrder.checkStatus eq 24302)}">
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">核验人：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.verifyUserName}</div>
                    <div class="layui-col-xs2 lable-left">核验日期：</div>
                    <div class="layui-col-xs4 lable-right">${keFuOrder.checkDate}</div>
                </div>
                </c:if>

                
              
            </div>

        </div>
    </div>
    <div class="layui-card">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>附件</legend>
        </fieldset>
        <div style="padding: 0 70px;" id="fileKeFuOrder">
            <div>工单附件</div><hr class="dottedLine"/>
            <div class="layui-upload-list upload_img_list" >
            </div>
        </div>
    </div>
</div>

    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>工单回复</legend>
            </fieldset>

            <c:if test="${not empty keFuOrder.keFuOrderCkDtos }">
                <c:forEach items="${keFuOrder.keFuOrderCkDtos}" var="listDto" varStatus="status">
                    <div class="layui-row">
                        <div class="layui-col-xs2 lable-left"><strong style="font-size:14px;">${listDto.ckUserName}(${listDto.userCode})</strong></div>
                        <div class="layui-col-xs4 lable-right"><strong style="font-size:14px;">&nbsp;&nbsp; ${listDto.ckDate}</strong></div>
                        <div class="layui-col-xs6">
                            <script>
                                document.write(htmlEscape("${listDto.ckDesc}"));
                            </script>
                        </div>
                    </div>

                    <div id="fileKeFuOrderReply${status.index}">
                        <div class="layui-upload-list upload_img_list" >
                        </div>
                    </div>
                    <div></div><hr class="dottedLine"/>
                </c:forEach>
            </c:if>





        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/keFuOrder/keFuOrderDetail.js?v=${vs}"></script>

</body>
</html>
