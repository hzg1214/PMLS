<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>新建合同</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .myForm .layui-input-inline{
            width: 205px;
        }
    </style>
</head>
<script type="application/javascript">
    var id='${id}';
    var frameContractDto='${frameContractDto}';
    var businessDto='${businessDto}';
</script>
<body>

<div>
    <input type="hidden" name="companyNo" id="companyNo"/>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">新建联动框架协议</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>基本信息</legend>
            </fieldset>
            <div class="layui-form myForm" style="margin-top:20px;">
                <div class="layui-form-item" style="display: none;">
                    <label class="layui-form-label"><span class="redSpan">*</span>协议类型</label>
                    <div class="layui-input-block">
                        <input type="radio" name="agreementType" value="22101" title="新签" checked="" lay-filter="agreementType">
                        <input type="radio" name="agreementType" value="22102" title="变更" lay-filter="agreementType">
                        <input type="radio" name="agreementType" value="22103" title="续签" lay-filter="agreementType">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>渠道公司</label>
                    <div class="layui-input-inline" style="width: 420px;">
                        <input type="text" id="companyName" name="companyName" readonly lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                    <button type="button" class="layui-btn selectBusinessBtn" data-type="selectBusiness">选择</button>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="redSpan">*</span>合作周期</label>
                        <div class="layui-input-inline" style="width: 193px;">
                            <input type="text" id="dateLifeStart" class="layui-input test-item" autocomplete="off" placeholder="开始日期">
                        </div>
                        <div class="layui-form-mid">至</div>
                        <div class="layui-input-inline" style="width: 193px;">
                            <input type="text" id="dateLifeEnd" class="layui-input test-item" autocomplete="off" placeholder="结束日期">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>合同签订日期</label>
                    <div class="layui-input-block">
                        <input type="text" id="signDate" class="layui-input test-item" autocomplete="off" placeholder="请选择">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>是否自动续约</label>
                    <div class="layui-input-block">
                        <input type="radio" name="reAgreeFlag" value="22201" title="是" lay-filter="reAgreeFlag">
                        <input type="radio" name="reAgreeFlag" value="22202" title="否" checked="" lay-filter="reAgreeFlag">
                    </div>
                </div>
                <div class="layui-form-item dockingPeoItem">
                    <label class="layui-form-label"><span class="redSpan">*</span>开户名</label>
                    <div class="layui-input-inline" style="width: 420px;">
                        <input type="text" id="accountNm" name="accountNm" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                    <button type="button" class="layui-btn" data-type="selectBankInfo">选择</button>
                </div>
                <div class="layui-form-item dockingPeoTelItem">
                    <label class="layui-form-label"><span class="redSpan">*</span>开户省市</label>
                    <div class="layui-input-inline">
                        <select id="accountProvinceNo" lay-filter="accountProvinceNo" lay-search="" placeholder="请选择">
                            <option value="">请选择省份</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select id="accountCityNo" lay-filter="accountCityNo" lay-search="" placeholder="请选择">
                            <option value="">请选择城市</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>开户行</label>
                    <div class="layui-input-block">
                        <input type="text" id="bankAccountNm" class="layui-input test-item" autocomplete="off" placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>银行账号</label>
                    <div class="layui-input-block">
                        <input type="text" id="bankAccount" class="layui-input test-item" oninput="this.value=this.value.replace(/[^0-9a-zA-Z/-]+/g,'')" autocomplete="off" placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>乙方授权代表</label>
                    <div class="layui-input-block">
                        <input type="text" id="partyBNm" class="layui-input test-item"  autocomplete="off" placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>乙方联系方式</label>
                    <div class="layui-input-block">
                        <input type="text" id="partyBTel"  maxlength="11" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" class="layui-input test-item" autocomplete="off" placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">合同说明</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入" maxlength="300" id="contractNote" autocomplete="off" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>附件</legend>
            </fieldset>
            <div class="layui-form enclosureForm">
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>营业执照</label>
                    <div class="layui-input-block">
                        <div class="layui-upload" id="fileBusiness">
                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <span style="color: red">注：上传营业执照信息。</span>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>合同</label>
                    <div class="layui-input-block">
                        <div class="layui-upload" id="fileContract">
                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <span style="color: red">注：上传合同信息。</span>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">其他</label>
                    <div class="layui-input-block">
                        <div class="layui-upload" id="fileOther">
                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <span style="color: red">注：上传其他附件信息。</span>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
            </div>
            <div class="operationPageToolbar">
                <button type="button" class="layui-btn" onclick="submitForm()">提交</button>
                <button type="button" class="layui-btn layui-btn-primary" onclick="back()">取消</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/frameContract/addFrameContractPage.js?_v=${vs}"></script>
</body>
</html>
