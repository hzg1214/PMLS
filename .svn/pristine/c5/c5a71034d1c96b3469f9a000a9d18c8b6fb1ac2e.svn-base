<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>结算书详情</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .lable-left {
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
            width: 200px;
        }

        .lable-right {
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
            width: 330px;
        }

        .lable-rightAll {
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
            width: 800px;
        }

        .layui-table td, .layui-table th {
            font-size: 12px !important;
        }
    </style>
</head>
<script type="application/javascript">
    var jsStatementDto = '${jsStatementDto}';
    var fileListInfo = ${fileListInfo};
</script>
<body>
<div>
    <div class="layui-card" style="padding-bottom: 25px">
        <div class="layui-card-body">
            <div style="margin: 0 auto;">
                <div class="layui-row blockBody">
                    <div class="layui-col-xs6">
                        <div class="blockTitle">结算书详情</div>
                    </div>
                    <div class="layui-col-xs6 blockBtn">
                        <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                    </div>
                </div>
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">结算书编号：</div>
                    <div class="layui-col-xs10 lable-rightAll">${jsStatementDto.jssNo}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">渠道公司：</div>
                    <div class="layui-col-xs4 lable-right">
                        <c:if test="${ !empty jsStatementDto.companyNo}">
                            ${jsStatementDto.companyName}(${jsStatementDto.companyNo})
                        </c:if>
                    </div>
                    <div class="layui-col-xs2 lable-left">项目：</div>
                    <div class="layui-col-xs4 lable-right">
                        <c:if test="${ !empty jsStatementDto.projectNo}">
                            ${jsStatementDto.projectName}(${jsStatementDto.projectNo})
                        </c:if>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">核算主体：</div>
                    <div class="layui-col-xs4 lable-right">
                        <c:if test="${ !empty jsStatementDto.hsCode}">
                            ${jsStatementDto.hsName}(${jsStatementDto.hsCode})
                        </c:if>
                    </div>
                    <div class="layui-col-xs2 lable-left">考核主体：</div>
                    <div class="layui-col-xs4 lable-right">
                        <c:if test="${ !empty jsStatementDto.khCode}">
                            ${jsStatementDto.khName}(${jsStatementDto.khCode})
                        </c:if>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合同：</div>
                    <div class="layui-col-xs4 lable-right">${jsStatementDto.frameOaNo}</div>
                    <div class="layui-col-xs2 lable-left">供应商：</div>
                    <div class="layui-col-xs4 lable-right">
                        <c:if test="${ !empty jsStatementDto.vender_code}">
                            ${jsStatementDto.vender_name}(${jsStatementDto.vender_code})
                        </c:if>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">供应商税率：</div>
                    <div class="layui-col-xs4 lable-right">${jsStatementDto.kpRateStr}</div>
                    <div class="layui-col-xs2 lable-left">是否包含冲抵结算：</div>
                    <div class="layui-col-xs4 lable-right">
                        <c:choose>
                            <c:when test="${jsStatementDto.offSetFlag==1}">
                                是
                            </c:when>
                            <c:otherwise>
                                否
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合同约定金额：</div>
                    <div class="layui-col-xs4 lable-right">
                        <fmt:formatNumber type="number" value="${jsStatementDto.contractYdTotalAmount}" pattern="0.00"
                                          maxFractionDigits="2"/>
                        元
                    </div>
                    <div class="layui-col-xs2 lable-left">结算金额：</div>
                    <div class="layui-col-xs4 lable-right">
                        <fmt:formatNumber type="number" value="${jsStatementDto.jsTotalAmount}" pattern="0.00"
                                          maxFractionDigits="2"/>
                        元
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">开票金额：</div>
                    <div class="layui-col-xs4 lable-right">
                        <fmt:formatNumber type="number" value="${jsStatementDto.kpTotalAmount}" pattern="0.00"
                                          maxFractionDigits="2"/>
                        元
                    </div>
                    <div class="layui-col-xs2 lable-left">已请款金额：</div>
                    <div class="layui-col-xs4 lable-right">
                        <fmt:formatNumber type="number" value="${jsStatementDto.requestAmount}" pattern="0.00"
                                          maxFractionDigits="2"/>
                        元
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">请款单号：</div>
                    <div class="layui-col-xs10 lable-rightAll">
                        <c:if test="${ !empty jsStatementDto.cashBillList}">
                            <c:forEach items="${jsStatementDto.cashBillList}" var="list">
                                <a href="javascript:void(0)" onclick="toCashBillDetail('${list.cashBillNo}');"><span
                                        style="color:#0000FF">${list.cashBillNo}</span></a>&nbsp;&nbsp;
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">备注：</div>
                    <div class="layui-col-xs4 lable-right">${jsStatementDto.remark}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">提交OA状态：</div>
                    <div class="layui-col-xs4 lable-right">
                        <c:if test="${jsStatementDto.formState==0}">
                            提交OA中
                        </c:if>
                        <c:if test="${jsStatementDto.formState==10}">
                            已提交
                        </c:if>
                        <c:if test="${jsStatementDto.formState==-10}">
                            <span style="color: red">提交失败</span>
                        </c:if>
                    </div>
                    <div class="layui-col-xs2 lable-left">OA单号：</div>
                    <div class="layui-col-xs4 lable-right">${jsStatementDto.oaNo}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">创建人：</div>
                    <div class="layui-col-xs4 lable-right">${jsStatementDto.userCreateName}</div>
                    <div class="layui-col-xs2 lable-left">创建时间：</div>
                    <div class="layui-col-xs4 lable-right">${jsStatementDto.dateCreate}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合同审核状态：</div>
                    <div class="layui-col-xs4 lable-right">${jsStatementDto.approveStatusNm}</div>
                    <div class="layui-col-xs2 lable-left">合同审核通过时间：</div>
                    <div class="layui-col-xs4 lable-right">${jsStatementDto.approvedDate}</div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-card">
        <div class="layui-card-body" id="jsOrderCard">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 38px;">
                <legend>结算订单</legend>
            </fieldset>
            <table id="jsOrderTable" lay-size="sm" lay-filter="jsOrderTable"></table>
        </div>
    </div>

    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>附件</legend>
            </fieldset>

            <div id="uploadSuccSaleImg" style="padding: 0 70px;">
                <div>成销确认书/佣金结算资料</div>
                <hr class="dottedLine"/>
                <div class="layui-row">
                    <div class="layui-col-xs12 ">
                        <div class="layui-upload-list upload_img_list">
                        </div>
                    </div>
                </div>
            </div>

            <div id="uploadProjectContractImg" style="padding: 0 70px;">
                <div>项目合同</div>
                <hr class="dottedLine"/>
                <div class="layui-row">
                    <div class="layui-col-xs12 ">
                        <div class="layui-upload-list upload_img_list">
                        </div>
                    </div>
                </div>
            </div>

            <div id="uploadBranchContractImg" style="padding: 0 70px;">
                <div>分销合同</div>
                <hr class="dottedLine"/>
                <div class="layui-row">
                    <div class="layui-col-xs12 ">
                        <div class="layui-upload-list upload_img_list">
                        </div>
                    </div>
                </div>
            </div>

            <div id="uploadOtherImg" style="padding: 0 70px;">
                <div>其他</div>
                <hr class="dottedLine"/>
                <div class="layui-row">
                    <div class="layui-col-xs12 ">
                        <div class="layui-upload-list upload_img_list">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    //取消返回上一个页面
    function back() {
        parent.redirectTo('delete', null, null);
    }
</script>
<script src="${ctx}/meta/pmls/js/jsStatement/jsStatementDetailPage.js?v=${vs}"></script>
</body>
</html>
