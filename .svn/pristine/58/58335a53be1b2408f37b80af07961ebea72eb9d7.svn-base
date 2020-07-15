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

        .layui-tab-content .layui-form-label {
            width: 100px !important;
        }
        .layui-table-link {
            cursor: pointer;
        }
        .layui-input, .layui-textarea {
            display: block;
            width: 20%;
            padding-left: 10px;
        }
    </style>

</head>
<body>
<div style="background: white">
    <div>
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-tab layui-tab-card">
                    <ul class="layui-tab-title">
                        <li class="layui-this" act-type="all">项目</li>
                        <li act-type="register">合作</li>
                        <li act-type="watch">结算</li>
                        <li act-type="qk">请款</li>
                    </ul>

                    <div class="layui-tab-content" style="padding-top: 10px">
                        <div id="allTabItem" class="layui-tab-item layui-show">
                            <div style="padding:45px;">
                                <h2 style="text-align:left; font-size:16px; padding:0 0 15px; margin:0 0 15px; height:24px; line-height:24px;">立项</h2>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">立项单编号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="oaId" id="oaId" placeholder="请输入立项单编号" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">项目编号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="projectNo" id="projectNo" placeholder="请输入项目编号" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">核算编码：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="hsCode" id="hsCode" placeholder="请输入核算编码" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">核算主体：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="hsName" id="hsName" placeholder="请输入核算主体" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">考核主体编码：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="khCode" id="khCode" placeholder="请输入考核主体编码" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">考核主体：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="khName" id="khName" placeholder="请输入考核主体" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">预计垫佣金额：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="yjDyAmount" id="yjDyAmount" placeholder="请输入预计垫佣金额" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">是否垫佣：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="dykbn" id="dykbn" placeholder="请输入是否垫佣" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">预计当年大定金额：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="subscribedThisYear" id="subscribedThisYear" placeholder="请输入预计当年大定金额" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">预计明年大定金额：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="subscribedNextYear" id="subscribedNextYear" placeholder="请输入预计明年大定金额" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">垫佣开始时间：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="dyDateStart" id="dyDateStart" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">垫佣结束时间：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="dyDateEnd" id="dyDateEnd" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">垫佣金额：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="dyAmount" id="dyAmount" placeholder="请输入垫佣金额" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <a class="layui-btn" onclick="oaProject();">提交</a>
                                    </div>
                                </div>
                                <label id="oaProjectError" style="color:red;font-size:18px;"class="label"></label>
                            </div>
                            <div style="padding:45px;">
                                <h2 style="text-align:left; font-size:16px; padding:0 0 15px; margin:0 0 15px; height:24px; line-height:24px;">签约/进场确认</h2>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">类型</label>
                                    <div class="layui-input-block">
                                        <input type="radio" name="projectType" value="1" title="签约">
                                        <input type="radio" name="projectType" value="2" title="进场确认">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">oa单号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="oaIdTwo" id="oaIdTwo" placeholder="请输入oa单号" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">项目编号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="projectNoTwo" id="projectNoTwo" placeholder="请输入项目编号" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">返佣结算条件：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="rtnCommissionMemo" id="rtnCommissionMemo" placeholder="请输入返佣结算条件" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">返佣标准：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="rtnCommission" id="rtnCommission" placeholder="请输入返佣标准" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">收入结算描述：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="commissionMemo" id="commissionMemo" placeholder="请输入收入结算描述" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">收入结算条件：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="commissionConditionVal" id="commissionConditionVal" placeholder="请输入收入结算条件" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">收入标的：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="incomeSubject" id="incomeSubject" placeholder="请输入收入标的" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">合作期开始：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="cooperationDtStart" id="cooperationDtStart" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">合作期结束：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="cooperationDtEnd" id="cooperationDtEnd" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单选框</label>
                                    <div class="layui-input-block">
                                        <input type="radio" name="signKbn" value="1" title="新签">
                                        <input type="radio" name="signKbn" value="2" title="续签">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">核算编码：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="hsCodeTwo" id="hsCodeTwo" placeholder="请输入核算编码" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">核算主体：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="hsNameTwo" id="hsNameTwo" placeholder="请输入核算主体" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">考核主体编码：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="khCodeTwo" id="khCodeTwo" placeholder="请输入考核主体编码" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">考核主体：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="khNameTwo" id="khNameTwo" placeholder="请输入考核主体" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">我司签约主体：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="wsqyzt" id="wsqyzt" placeholder="请输入我司签约主体" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">客户Code：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="developersCode" id="developersCode" placeholder="请输入客户Code" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">客户名：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="developersName" id="developersName" placeholder="请输入客户名" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <a class="layui-btn" onclick="oaSignOrApproach();">提交</a>
                                    </div>
                                </div>
                                <label id="oaSignOrApproachError" style="color:red;font-size:18px;"class="label"></label>
                            </div>
                        </div>
                        <div id="registerTabItem" class="layui-tab-item">
                            <div style="padding:45px;">
                                <h2 style="text-align:left; font-size:16px; padding:0 0 15px; margin:0 0 15px; height:24px; line-height:24px;">框架协议</h2>
                                <div style="overflow:hidden;">
                                    <div style="display: flex;">
                                        <span style="float:left;font-size:14px; color:#333;">合同单号(例：HTLD201910231082)：</span>
                                        <input type="text" name="contractNo" id="contractNo" placeholder="请输入合同单号"  class="layui-input">
                                        <a class="layui-btn" onclick="oaFrameContract(1);" style="margin-left: 25px;">审批通过</a>
                                        <a class="layui-btn layui-btn-danger" onclick="oaFrameContract(0);">审批不通过</a>
                                    </div>
                                </div>
                                <label id="oaFrameContractError" style="color:red;font-size:18px;"class="label"></label>
                            </div>
                            <div style="padding:45px;">
                                <h2 style="text-align:left; font-size:16px; padding:0 0 15px; margin:0 0 15px; height:24px; line-height:24px;">分销合同</h2>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">oa编号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="oaNo" id="oaNo" placeholder="请输入oa编号" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">合同类型：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="htType" id="htType" placeholder="请输入合同类型（标准合同/框架合同）" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">合同类别：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="htCategory" id="htCategory" placeholder="请输入合同类别（返佣/借佣）" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">风险预警：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="riskWarning" id="riskWarning" placeholder="请输入风险预警" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">考核Code：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="khCodeThere" id="khCodeThere" placeholder="请输入考核Code" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">考核名称：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="khNameThere" id="khNameThere" placeholder="请输入考核名称" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <a class="layui-btn" onclick="oaDistribution(1);" style="margin-left: 25px;">审批通过</a>
                                        <a class="layui-btn layui-btn-danger" onclick="oaDistribution(0);">审批不通过</a>
                                    </div>
                                </div>
                                <label id="oaDistributionError" style="color:red;font-size:18px;"class="label"></label>
                            </div>
                        </div>
                        <div id="watchTabItem" class="layui-tab-item">
                            <div style="padding:45px;">
                                <h2 style="text-align:left; font-size:16px; padding:0 0 15px; margin:0 0 15px; height:24px; line-height:24px;">结算书</h2>
                                <div style="overflow:hidden;">
                                    <div style="display: flex;">
                                        <span style="float:left;font-size:14px; color:#333;">结算书OA编号：</span>
                                        <input type="text" name="jyNo" id="jyNo" placeholder="请输入结算书OA编号"  class="layui-input">
                                        <a class="layui-btn" onclick="oaStatement(1);" style="margin-left: 25px;">审批通过</a>
                                        <a class="layui-btn layui-btn-danger" onclick="oaStatement(0);">审批不通过</a>
                                    </div>
                                </div>
                                <label id="oaStatementError" style="color:red;font-size:18px;"class="label"></label>
                            </div>
<!--                             <div style="padding:45px;"> -->
<!--                                 <h2 style="text-align:left; font-size:16px; padding:0 0 15px; margin:0 0 15px; height:24px; line-height:24px;">借佣三方协议</h2> -->
<!--                                 <div style="overflow:hidden;"> -->
<!--                                     <div style="display: flex;"> -->
<!--                                         <span style="float:left;font-size:14px; color:#333;">借佣编号：</span> -->
<!--                                         <input type="text" name="jyNoTwo" id="jyNoTwo" placeholder="请输入借佣编号"  class="layui-input"> -->
<!--                                         <a class="layui-btn" onclick="oaAgreement(1);" style="margin-left: 25px;">审批通过</a> -->
<!--                                         <a class="layui-btn layui-btn-danger" onclick="oaAgreement(0);">审批不通过</a> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <label id="oaAgreementError" style="color:red;font-size:18px;"class="label"></label> -->
<!--                             </div> -->
<!--                             <div style="padding:45px;"> -->
<!--                                 <h2 style="text-align:left; font-size:16px; padding:0 0 15px; margin:0 0 15px; height:24px; line-height:24px;">借佣请款单</h2> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">借佣编号：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="jyNoThere" id="jyNoThere" placeholder="请输入借佣编号" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">收款银行：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="receiveBankName" id="receiveBankName" placeholder="请输入收款银行" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">卡号：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="receiveBankAccountCardCode" id="receiveBankAccountCardCode" placeholder="请输入卡号" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">收款人户名：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="receiveBankAccountName" id="receiveBankAccountName" placeholder="请输入收款人户名" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">省（银行）：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="receiveBankProvinceName" id="receiveBankProvinceName" placeholder="请输入省（银行）" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">市（银行）：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="receiveBankCityName" id="receiveBankCityName" placeholder="请输入市（银行）" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">银行序号：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="receiveBankSerialNo" id="receiveBankSerialNo" placeholder="请输入银行序号" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">入账日期：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="recordDate" id="recordDate" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">最后付款日期：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="lastpayDate" id="lastpayDate" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <a class="layui-btn" onclick="oaReceivables(1);" style="margin-left: 25px;">审批通过</a> -->
<!--                                         <a class="layui-btn layui-btn-danger" onclick="oaReceivables(0);">审批不通过</a> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <label id="oaReceivablesError" style="color:red;font-size:18px;"class="label"></label> -->
<!--                             </div> -->
<!--                             <div style="padding:45px;"> -->
<!--                                 <h2 style="text-align:left; font-size:16px; padding:0 0 15px; margin:0 0 15px; height:24px; line-height:24px;">借佣支出单</h2> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">借佣编号：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="jyNoFour" id="jyNoFour" placeholder="请输入借佣编号" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <label class="layui-form-label">入账日期：</label> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <input type="text" name="recordDateTwo" id="recordDateTwo" class="layui-input"> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="layui-form-item"> -->
<!--                                     <div class="layui-input-block"> -->
<!--                                         <a class="layui-btn" onclick="oaExpenditure(1);" style="margin-left: 25px;">审批通过</a> -->
<!--                                         <a class="layui-btn layui-btn-danger" onclick="oaExpenditure(0);">审批不通过</a> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <label id="oaExpenditureError" style="color:red;font-size:18px;"class="label"></label> -->
<!--                             </div> -->
                        </div>
                        <div id="qkTabItem" class="layui-tab-item">
                            <div style="padding:45px;">
                                <h2 style="text-align:left; font-size:16px; padding:0 0 15px; margin:0 0 15px; height:24px; line-height:24px;">请款单</h2>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">oa编号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="oaNoTwo" id="oaNoTwo" placeholder="请输入oa编号" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <a class="layui-btn" onclick="oaQk(1);" style="margin-left: 25px;">审批通过</a>
                                        <a class="layui-btn layui-btn-danger" onclick="oaQk(0);">审批不通过</a>
                                    </div>
                                </div>
                                <label id="oaQkError" style="color:red;font-size:18px;"class="label"></label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/student/textMain.js?v=${vs}"></script>


</body>
</html>