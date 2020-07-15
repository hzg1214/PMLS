<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>新建联动分销合同</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .cooperationPage .cooperationForm .layui-form-item .layui-form-label{
            width: 300px;
        }
        .cooperationPage .cooperationForm  .layui-form-item .layui-form-label .requiredClass{
            color: #FF0000;
        }
        .cooperationPage .cooperationForm .layui-form-item .layui-input-inline{
            width: 420px;
        }
        .cooperationPage .cooperationForm .layui-form-item .layui-input-block{
            margin-left: 330px;
        }
        .cooperationPage .cooperationForm .layui-form-item .layui-input-inline.dateLifeInput{
            width: 193px;
        }

        .cooperationPage .cooperationForm .layui-form-item .layui-input-inline.commissionInput{
            width: 135px;
        }

        .cooperationPage .layui-textarea:disabled, .layui-textarea[disabled]{
            border: 1px solid #DDD;
            background-color: #F5F5F5;
            color: #ACA899;
            opacity: 1;
        }
            /*上传图片的样式开始*/
        .cooperationPage .layui-upload .layui-quote-nm{
            border-width: 1px;
        }
        .cooperationPage .layui-upload .layui-upload-list {
            margin: 0;
            display: inline-block;
        }
        .cooperationPage .layui-upload .layui-upload-list dd {
            position: relative;
            margin: 0 10px 10px 0;
            float: left
        }
        .cooperationPage .layui-upload .layui-upload-list .operate {
            position: absolute;
            top: 0;
            right: 0;
            z-index: 1
        }
        .cooperationPage .layui-upload .layui-upload-list .operate i {
            cursor: pointer;
            background: #2F4056;
            padding: 2px;
            line-height: 15px;
            text-align: center;
            color: #fff;
            margin-left: 1px;
            float: left;
            filter: alpha(opacity=80);
            -moz-opacity: .8;
            -khtml-opacity: .8;
            opacity: .8
        }
        .cooperationPage .layui-upload .layui-upload-list dd .img {
            min-height: 120px;
            max-width: 157px
        }
        /*上传图片的样式结束*/

        .cooperationPage .toolbar{
            margin-top: 15px;
            margin-left: 330px;
            margin-bottom: 5px;
        }

        .cooperationPage .cooperationForm .layui-form-item .layui-input-inline.settlePayNum{
            width: 310px;
        }

    </style>
</head>
<script type="application/javascript">

</script>
<body>

<div class="cooperationPage">
    <input type="hidden" name="companyNo" id="companyNo" value="${companyInfo.companyNo}">
    <input type="hidden" name="businessLicenseNo" id="businessLicenseNo" value="${companyInfo.businessLicenseNo}">
    <input type="hidden" name="projectNo" id="projectNo">

    <input type="hidden" name="htoapassDate" id="htoapassDate">
    <input type="hidden" id="hsCode" name="hsCode"/>
    <input type="hidden" id="hsName" name="hsName"/>
    <input type="hidden" id="khCode" name="khCode"/>
    <input type="hidden" id="khName" name="khName"/>
    <input type="hidden" name="developersCode" id="developersCode">
    <input type="hidden" name="developersName" id="developersName">

    <div class="layui-card">
        <div class="layui-card-body">

            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">新建联动分销合同</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" data-type="backCooperationList">返回</button>
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>基本信息</legend>
            </fieldset>
            <div class="layui-form cooperationForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>渠道公司</label>
                        <div class="layui-input-inline">
                            <input type="text" id="companyName" name="companyName" class="layui-input"
                                   placeholder="请选择" readonly="readonly" value="${companyInfo.companyName}"
                                   disabled/>
                        </div>
                        <c:if test="${isCanSelect}">
                        <div class="layui-input-inline" style="width: 64px;">
                            <button type="button" class="layui-btn" data-type="selectCompany">选择</button>
                        </div>
                        </c:if>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>合作项目</label>
                        <div class="layui-input-inline">
                            <input type="text" id="projectName" name="projectName" class="layui-input" placeholder="请选择"
                                   readonly="readonly" disabled/>
                        </div>
                        <div class="layui-input-inline" style="width: 64px;">
                            <button type="button" class="layui-btn" data-type="selectProject">选择</button>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>项目地址</label>
                        <div class="layui-input-inline">
                            <input type="text" id="projectAddr" name="projectAddr" class="layui-input" disabled/>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>项目合同</label>
                        <div class="layui-input-inline">
                            <input type="text" id="htOaNo" name="htOaNo" class="layui-input" placeholder="请选择"
                                   readonly="readonly" disabled/>
                            <input type="hidden" id="contractType" name="contractType"/>
                        </div>
                        <div class="layui-input-inline" style="width: 64px;">
                            <button type="button" class="layui-btn" data-type="selectEstateHt">选择</button>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>我司签约主体</label>
                        <div class="layui-input-inline">
                            <input type="text" id="wsqyzt" name="wsqyzt" class="layui-input" disabled/>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>项目合同周期</label>
                        <div class="layui-input-inline">
                            <input type="hidden" id="htDateStart" name="htDateStart" class="layui-input"/>
                            <input type="hidden" id="htDateEnd" name="htDateEnd" class="layui-input"/>
                            <input type="text" id="htDate" name="htDate" class="layui-input" disabled/>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">项目收入标的</label>
                        <div class="layui-input-inline">
                            <textarea id="incomeSubject" class="layui-textarea" autocomplete="off" disabled
                                      style="width: 100%;"></textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">项目结算条件</label>
                        <div class="layui-input-inline">
                            <textarea id="commissionMemo" class="layui-textarea" autocomplete="off" disabled
                                      style="width: 100%;"></textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">项目收入描述</label>
                        <div class="layui-input-inline">
                            <textarea id="commissionMemoRemark" class="layui-textarea" autocomplete="off" disabled
                                      style="width: 100%;"></textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">项目合同返佣标准</label>
                        <div class="layui-input-inline">
                            <textarea id="rtnCommission" class="layui-textarea" autocomplete="off" disabled
                                      style="width: 100%;"></textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">项目合同返佣结算条件</label>
                        <div class="layui-input-inline">
                            <textarea id="rtnCommissionMemo" class="layui-textarea" autocomplete="off" disabled
                                      style="width: 100%;"></textarea>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合同名称</label>
                        <div class="layui-input-inline">
                            <select id="contractNameType" name="contractNameType" lay-verify="contractNameType" lay-filter="contractNameType">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" id="contractNameDescDiv" style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合同具体名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="contractNameDesc" name="contractNameDesc" class="layui-input" maxlength="30" placeholder="请输入"/>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item"  style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合同名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="contractName" name="contractName" class="layui-input" placeholder="请输入" maxlength="20"/>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合作房源范围</label>
                        <div class="layui-input-inline">
                            <textarea id="cooperationRange" class="layui-textarea" autocomplete="off"
                                      style="width: 100%;" placeholder="不超过200字" maxlength="200"></textarea>
                        </div>
                    </div>
                </div>


<!--                 <div class="layui-form-item" > -->
<!--                     <div class="layui-inline"> -->
<!--                         <label class="layui-form-label"><span class="requiredClass">*</span>分销合同版本</label> -->
<!--                         <div class="layui-input-inline" id="hteditionDiv"> -->
<%--                             <c:forEach items="${hteditionList}" var="list"> --%>
<%--                                 <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="htedition" disabled --%>
<%--                                 	<c:if test="${list.dicCode eq '28302'}">checked</c:if> --%>
<%--                                  title="${list.dicValue}"> --%>
<%--                             </c:forEach> --%>
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合同签约类型</label>
                        <div class="layui-input-inline">
                            <c:forEach items="${signTypeList}" var="list">
                                <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="signType" lay-filter="signType" title="${list.dicValue}">
                            </c:forEach>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item" id="htTypeDiv">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合同类型</label>
                        <div class="layui-input-inline">
                            <input type="radio" id="htType1" name="htType" value="1671583364489561661" title="返佣" lay-filter="htType">
                            <input type="radio" id="htType2" name="htType" value="-1835969197405944893" title="垫佣" lay-filter="htType">
                        </div>
                    </div>
                </div>


                <div class="layui-form-item" id="htCategoryDiv">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合同类别</label>
                        <div class="layui-input-inline">
                            <input type="radio" id="htCategory1" name="htCategory" value="3198954431064198420" title="模板合同" lay-filter="htCategory">
                            <input type="radio" id="htCategory2" name="htCategory" value="-7901965901473037886" title="非模板合同" lay-filter="htCategory">
                            <input type="radio" id="htCategory3" name="htCategory" value="-938620031608930158" title="特殊合同" lay-filter="htCategory">
                        </div>
                    </div>
                </div>


                <div class="layui-form-item" id="dyblDiv" style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>垫佣比例</label>
                        <div class="layui-input-inline" style="width: 193px;">
                            <input type="text" id="dybl" name="dybl" lay-verify="dybl" maxlength="5"
                                   oninput="this.value=this.value.replace(/[^\d.]/g,'')" autocomplete="off"   lay-filter="dybl" class="layui-input" placeholder="请输入">
                        </div>
                        <div class="layui-form-mid">%</div>
                    </div>
                </div>



                <div class="layui-form-item" id="companyEleUserDiv" style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>经纪公司电子授权人</label>
                        <div class="layui-input-inline">
                            <input type="text" id="companyEleUser" name="companyEleUser" maxlength="100" class="layui-input"/>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" id="companyEleTelDiv" style="display: none">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>经纪公司授权人电话</label>
                        <div class="layui-input-inline">
                            <input type="text" id="companyEleTel" name="companyEleTel" maxlength="11" class="layui-input"/>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合同周期</label>
                        <div class="layui-input-inline dateLifeInput">
                            <input type="text" id="dateLifeStart" class="layui-input" autocomplete="off" placeholder="开始日期">
                        </div>
                        <div class="layui-form-mid">至</div>
                        <div class="layui-input-inline dateLifeInput">
                            <input type="text" id="dateLifeEnd" class="layui-input" autocomplete="off" placeholder="结束日期">
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>成功销售的标准</label>
                        <div class="layui-input-inline">
                            <textarea id="succeedSell" class="layui-textarea" autocomplete="off"
                                      style="width: 100%;" placeholder="不超过200字" maxlength="200">网签+甲方确认</textarea>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合同返佣结算条件</label>
                        <div class="layui-input-inline">
                            <textarea id="fyjstj" class="layui-textarea" autocomplete="off"
                                      style="width: 100%;" placeholder="不超过200字"  maxlength="200" disabled>
1.客户报备截图及《客户确认单》（客户姓名、电话号码须和商品房销售／预售合同上客户姓名、电话号码一致）。
2.已签约商品房销售/预售合同复印件或照片。
3.本协议已生效且在有效合作期限内。
4.经甲乙双方盖章确认的《中介返佣明细表》。
5.提供6%税点的增值税专用发票。
                            </textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>分销合同返佣结算标准</label>
                        <div class="layui-input-inline">
                            <textarea id="fyjsbz" class="layui-textarea" autocomplete="off"
                                      style="width: 100%;" placeholder="不超过200字"  maxlength="200"></textarea>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>满足结算条件支付节点</label>
                        <div class="layui-input-inline settlePayNum">
                            <input type="text" id="settlePayNum" name="settlePayNum" maxlength="8" class="layui-input" oninput="this.value=this.value.replace(/[^\d]/g,'')"  autocomplete="off" placeholder="请输入">
                        </div>
                        <div class="layui-form-mid">个工作日内支付</div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label"><span class="requiredClass">*</span>违约金额（万元）</label>
                        <div class="layui-input-inline">
                            <input type="text" id="violateAmount" name="violateAmount" lay-verify="violateAmount" maxlength="8"
                                   oninput="this.value=this.value.replace(/[^\d.]/g,'')" autocomplete="off"   lay-filter="violateAmount" class="layui-input" placeholder="请输入">
                        </div>
                    </div>
                </div>



                <%--<div class="layui-form-item">--%>
                    <%--<div class="layui-inline">--%>
                        <%--<label class="layui-form-label">分销佣金计算公式</label>--%>
                        <%--<div class="layui-input-inline commissionInput">--%>
                            <%--<input type="text" id="commissionAmount" class="layui-input" placeholder="请输入">--%>
                        <%--</div>--%>
                        <%--<div class="layui-form-mid">--%>
                            <%--元&nbsp;&nbsp;+&nbsp;&nbsp;总价--%>
                        <%--</div>--%>
                        <%--<div class="layui-input-inline commissionInput">--%>
                            <%--<input type="text" id="commissionRatio" class="layui-input" placeholder="请输入">--%>
                        <%--</div>--%>
                        <%--<div class="layui-form-mid">--%>
                            <%--%--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-inline">
                            <textarea id="remark" class="layui-textarea" autocomplete="off"
                                      style="width: 100%;" placeholder="请输入"></textarea>
                        </div>
                    </div>
                </div>


            </div>

        </div>
    </div>

    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>附件</legend>
            </fieldset>

            <div class="layui-form cooperationForm">
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="requiredClass"></span>合同</label>
                    <div class="layui-input-block">
                        <div class="layui-upload" id="uploadContractImg">
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
                        <div class="layui-upload" id="uploadOtherImg">
                            <button type="button" class="layui-btn uploadImg">上传</button>
                            <span style="color: red">注：上传其他附件信息。</span>
                            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                <div class="layui-upload-list upload_img_list"></div>
                            </blockquote>
                        </div>
                    </div>
                </div>
            </div>
            <div class="toolbar">
                <button type="button" class="layui-btn" data-type="submitCooperation">提交</button>
                <button type="button" class="layui-btn layui-btn-primary" data-type="backCooperationList">取消</button>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/cooperation/cooperationAdd.js?_v=${vs}"></script>
</body>
</html>
