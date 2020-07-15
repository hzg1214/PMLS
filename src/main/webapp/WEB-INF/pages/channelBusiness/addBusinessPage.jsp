<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加渠道</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .addressInputBlock  .layui-form-select{
            width: 100px;
        }
        .addressInputBlock .divInline{
            float:left;
            margin-right:10px;
        }
        .layui-treeSelect .ztree li span.button.switch{
            top:-7px;
        }

    </style>
</head>
<script type="application/javascript">
    var id='${id}';
    var businessDto='${businessDto}';//商户信息
    var channelTypeList='${channelTypeList}';//渠道分类
    var channelLevelList='${channelLevelList}';//渠道等级
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">新建借佣渠道</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>基本信息</legend>
            </fieldset>
            <div class="layui-form myForm" style="margin-top:20px;">
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>渠道名称</label>
                    <div class="layui-input-block">
                        <input type="text" id="companyName"  name="companyName" onchange="checkBusinessCompanyName()" onblur="checkBusinessCompanyName()" lay-verify="required" autocomplete="off" placeholder="请输入" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>统一社会信用代码</label>
                    <div class="layui-input-inline">
                        <input type="text" id="businessLicenseNo" style="width: 420px;" maxlength="18" onchange="checkBusiness()" onblur="checkBusiness()"  name="businessLicenseNo" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                    <a href="https://www.qixin.com/" style="color: #fff;" target="_blank" rel="noreferrer"><button type="button" class="layui-btn" style="margin-left: 230px;">快捷查询</button></a>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>注册地址</label>
                    <div class="layui-input-block addressInputBlock">
                        <div class="divInline">
                            <select id="realityCityNo" lay-filter="realityCityNo" lay-search="" placeholder="请选择">
                                <option value="">请选择城市</option>
                            </select>
                        </div>
                        <div class="divInline">
                            <select id="districtNo" lay-filter="districtNo" lay-search="" placeholder="请选择">
                                <option value="">请选择区域</option>
                            </select>
                        </div>
                        <div class="divInline" style="margin-right: 0px;width: 200px;">
                            <input type="text" id="address" name="address" lay-verify="required" placeholder="具体地址" autocomplete="off" class="layui-input">
                        </div>

                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>法定代表人</label>
                    <div class="layui-input-block">
                        <input type="text" id="legalPerson" name="legalPerson" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">法人手机号码</label>
                    <div class="layui-input-block">
                        <input type="text" id="contactNumber" name="contactNumber" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" maxlength="11" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item dockingPeoItem">
                    <label class="layui-form-label"><span class="redSpan">*</span>管理员姓名</label>
                    <div class="layui-input-block">
                        <input type="text" id="dockingPeo" name="dockingPeo" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item dockingPeoTelItem">
                    <label class="layui-form-label"><span class="redSpan">*</span>管理员手机号码</label>
                    <div class="layui-input-block">
                        <input type="text" id="dockingPeoTel" name="dockingPeoTel"  maxlength="11" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>渠道分类</label>
                    <div class="layui-input-block" id="channelType">
                        <%--<select id="channelType" lay-filter="channelType" >
                            <option value="">请选择</option>
                        </select>--%>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>渠道等级</label>
                    <div class="layui-input-block" id="channelLevel">
                        <%--<select id="channelLevel" lay-filter="channelLevel">
                            <option value="">请选择</option>
                        </select>--%>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>增值税税率</label>
                    <div class="layui-input-block" id="taxRate">
                        <input type='radio' name='taxRate' value='0.03' title='3%' >
                        <input type='radio' name='taxRate' value='0.06' title='6%' >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>渠道品牌</label>
                    <div class="layui-input-block brandIdInputBlock">
                        <input id="brandId" type="text" class="layui-input"  lay-filter="tree"></input>
                    </div>
                </div>
                <%--<div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>是否我司</label>
                    <div class="layui-input-block">
                        <input type="radio" name="isFyCompany" value="1" title="是" lay-filter="isFyCompany">
                        <input type="radio" name="isFyCompany" value="0" title="否" lay-filter="isFyCompany">
                    </div>
                </div>--%>
                <div class="layui-form-item maintainerItem">
                    <label class="layui-form-label"><span class="redSpan">*</span>维护人</label>
                    <div class="layui-input-inline" style="width: 420px;">
                        <input type="text" id="maintainerName" name="maintainerName" readonly lay-verify="required" placeholder="请选择" autocomplete="off" class="layui-input">
                    </div>
                    <button type="button" class="layui-btn selectMaintainEmployee" data-type="selectMaintain">选择</button>
                </div>
                <div class="layui-form-item maintainerItem">
                    <label class="layui-form-label"><span class="redSpan">*</span>维护中心</label>
                    <div class="layui-input-block">
                        <input type="text" id="centerName" name="centerName" readonly lay-verify="required" placeholder="" disabled autocomplete="off" class="layui-input">
                    </div>
                </div>
                <%--<div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入" id="remark" class="layui-textarea"></textarea>
                    </div>
                </div>--%>
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
            </div>
            <div class="operationPageToolbar">
                <button type="button" class="layui-btn" onclick="submitForm()">提交</button>
                <button type="button" class="layui-btn layui-btn-primary" onclick="back()">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/channelBusiness/addBusinessPage.js?v=${vs}"></script>
</body>
</html>
