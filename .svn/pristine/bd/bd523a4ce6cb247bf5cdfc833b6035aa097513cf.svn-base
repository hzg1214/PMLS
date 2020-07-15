<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加报备</title>
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
    <%--var id='${id}';--%>
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">新建报备</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>基本信息</legend>
            </fieldset>
            <div class="layui-form myForm" style="margin-top:20px;">
                <input type="hidden" name="estateId" id="estateId" value="${estateInfo.estate.estateId}">
                <input type="hidden" name="estateNm" id="estateNm" value="${estateInfo.estate.estateNm}">
                <input type="hidden" name="cityNo" id="cityNo" value="${userInfo.cityNo}">
                <input type="hidden" name="partnerNm" id="partnerNm" value="${estateInfo.estate.partnerNm}">



                <input type="hidden" name="countDateEndStr" id="countDateEndStr" value="${countDateEndStr}">

                <label class="layui-form-label"><b>项目编号：${estateInfo.estate.projectNo}</b></label>
                <label class="layui-form-label"
                       style=" width: 500px!important; text-align: left"><b>楼盘名称：${estateInfo.estate.estateNm}</b></label>


                <div class="layui-form-item">
                    <label class="layui-form-label">合作方</label>
                    <label class="layui-form-label" style="text-align: left;width: 500px!important;">${estateInfo.estate.partnerNm}</label>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>业绩归属城市</label>
                    <div class="layui-input-block">
                        <select id="accCity" name="accCity" lay-verify="accCity"
                                lay-filter="accCity" lay-search="">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>业绩归属中心</label>
                    <div class="layui-input-block">
                        <select id="centerId" name="centerId" lay-verify="centerId"
                                lay-filter="centerId" lay-search="">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>收入中心</label>
                    <div class="layui-input-block">
                        <select id="srCenterId" name="srCenterId" lay-verify="srCenterId"
                                lay-filter="srCenterId" lay-search="">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>收入类型</label>
                    <div class="layui-input-block">
                        <select id="srType" name="srType" lay-verify="srType" lay-filter="srType">
                            <option value="">请选择</option>
                            <option value="27201">带看奖</option>
                            <option value="27202">成交奖</option>
                            <option value="27203">广告咨询费</option>
                            <option value="27204">拓客服务费</option>
                            <option value="27205">其他</option>
                        </select>
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>收入金额</label>
                    <div class="layui-input-block">
                        <input type="text" id="srAmount" name="srAmount" lay-verify="srAmount" maxlength="12"
                               oninput="this.value=this.value.replace(/[^\-?\d.]/g,'')" autocomplete="off"   lay-filter="srAmount" class="layui-input" placeholder="请输入">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>报备日期</label>
                    <div class="layui-input-block">
                        <input type="text" id="reportDate" name="reportDate" lay-verify="required"
                               autocomplete="off" lay-filter="reportDate" class="layui-input" placeholder="请选择">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入" id="memo" name="memo" class="layui-textarea"></textarea>
                    </div>
                </div>

                <div class="operationPageToolbar">
                    <button type="button" class="layui-btn" onclick="reportAdd()" id="reportPcAddBtn">提交</button>
                    <button type="button" class="layui-btn layui-btn-primary" onclick="back()">取消</button>
                </div>

            </div>
        </div>
    </div>


<%--    <div class="layui-card">
        <div class="layui-card-body">
            <div class="operationPageToolbar">
                <button type="button" class="layui-btn" onclick="reportAdd()" id="reportPcAddBtn">提交</button>
                <button type="button" class="layui-btn layui-btn-primary" onclick="back()">取消</button>
            </div>
        </div>
    </div>--%>
</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/otherReport/qtReportAdd.js?v=${vs}"></script>
</body>
</html>
