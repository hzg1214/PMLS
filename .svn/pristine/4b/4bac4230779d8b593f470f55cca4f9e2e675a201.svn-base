<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>联动框架协议详情</title>
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
    var oaOperator='${oaOperator}';
    var frameContractDto='${frameContractDto}';
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div style="margin: 0 auto;">
                <div class="layui-row blockBody">
                    <div class="layui-col-xs6">
                        <div class="blockTitle">联动框架协议详情</div>
                    </div>
                    <input type="hidden" id="accountProjectCode" name="accountProjectCode" value="${accountProjectCode}"/>
					<input type="hidden" id="accountProject" name="accountProject" value="${accountProject}"/>
                   	<input type="hidden" id="accountProjectLenth" name="accountProjectLenth" value="${accountProjectLenth}"/>
                   	<input type="hidden" id="autoToOa" name="autoToOa" value="${frameContractDto.autoToOa}"/>
                    <div class="layui-col-xs6 blockBtn">
                        <shiro:hasPermission name="/frameContract:submitOA">
                            <button type="button" id="submitToOA" class="layui-btn submitOABtn" onclick="submitOA(${frameContractDto.id})" style="display: none;">发起OA审核</button>
                        </shiro:hasPermission>
                        <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                    </div>
                </div>

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合同编号：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.contractNo}</div>
                    <div class="layui-col-xs2 lable-left">协议类型：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.agreementTypeVal}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">渠道公司：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.companyName}</div>
                    <div class="layui-col-xs2 lable-left">合作周期：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(frameContractDto.dateLifeStart)}至${sdk:ymd2(frameContractDto.dateLifeEnd)}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合同签订日期：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:ymd2(frameContractDto.signDate)}</div>
                    <div class="layui-col-xs2 lable-left">是否自动续签：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.reAgreeFlagVal}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">开户名：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.accountNm}</div>
                    <div class="layui-col-xs2 lable-left">开户行：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.bankAccountNm}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">开户省市：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.accountProvinceNm}${frameContractDto.accountCityNm}</div>
                    <div class="layui-col-xs2 lable-left">银行账号：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.bankAccount}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合同说明：</div>
                    <div class="layui-col-xs10 lable-right">${frameContractDto.contractNote}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">提交OA状态：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.submitOAStatusNm}</div>
                    <div class="layui-col-xs2 lable-left">创建时间：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.dateCreate}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合同审核状态：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.approveStatusNm}</div>
                    <div class="layui-col-xs2 lable-left">合同审核通过时间：</div>
                    <div class="layui-col-xs4 lable-right">${frameContractDto.approvePassDate}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">OA单号：</div>
                    <div class="layui-col-xs10 lable-right">${frameContractDto.oaNo}</div>
                </div>
            </div>

        </div>
    </div>
    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>附件</legend>
            </fieldset>
            <div style="padding: 0 70px;" id="fileBusiness">
                <div>营业执照</div><hr class="dottedLine"/>
                <div class="layui-upload-list upload_img_list" >

                </div>
            </div>
            <div style="padding: 0 70px;" id="fileContract">
                <div>合同</div><hr class="dottedLine" />
                <div class="layui-upload-list upload_img_list" >

                </div>
            </div>
            <div style="padding: 0 70px;" id="fileOther">
                <div>其他</div><hr class="dottedLine" />
                <div class="layui-upload-list upload_img_list" >

                </div>
            </div>
        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/frameContract/frameContractDetailPage.js?v=${vs}"></script>

</body>
</html>
