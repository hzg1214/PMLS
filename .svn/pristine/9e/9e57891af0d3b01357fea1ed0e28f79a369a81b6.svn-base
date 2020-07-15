<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>联动分销合同详情</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">

        .cooperationPage .cooperationForm .layui-form-item{
            margin-bottom: 0px;
        }

        .cooperationPage .cooperationForm .layui-form-item .layui-form-label{
            width: 182px;
            padding: 9px 3px 9px 15px;
        }
        .cooperationPage .cooperationForm .layui-form-item .layui-input-inline{
            width: 230px;
            height: 38px;
            line-height: 38px;
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
        .cooperationPage .layui-upload .layui-upload-list dd .img {
            min-height: 120px;
            max-width: 157px
        }
        /*上传图片的样式结束*/

        .cooperationPage .fileContent{
            margin-left: 70px;
        }

        .cooperationPage hr {
            background-color:#FFFFFF;
            border-top: 1px dashed #e6e6e6;
        }

        .textOverflow {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    </style>
</head>
<script type="application/javascript">
    var cooperationInfo = ${cooperationInfo};
    console.log(${cooperationInfo});
</script>
<body>

<div class="cooperationPage">
    <input type="hidden" name="id" id="id" value="${cooperationInfo.id}">
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">联动分销合同详情</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <c:if test="${cooperationInfo.approveState eq 10401 or cooperationInfo.approveState eq 10404}">
                        <shiro:hasPermission name="/estate:CHANGE_DETAIL">

                        </shiro:hasPermission>
                        <shiro:hasPermission name="/cooperation:submitOA">
                            <button type="button" class="layui-btn" data-type="startOAaudit">发起OA审核</button>
                        </shiro:hasPermission>
                    </c:if>
                    <button type="button" class="layui-btn layui-btn-primary" data-type="backCooperationList">返回</button>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>基本信息</legend>
            </fieldset>
            <div class="layui-form cooperationForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">合同编号：</label>
                        <div class="layui-input-inline">
                            ${cooperationInfo.contractNo}
                        </div>
                    </div>


                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">合作项目：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.projectName}
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">项目地址：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.projectAddr}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">项目合同类型：</label>
                        <div class="layui-input-inline">
                            ${cooperationInfo.contractType}
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">渠道公司：</label>
                        <div class="layui-input-inline textOverflow" title="${cooperationInfo.companyName}">
                            ${cooperationInfo.companyName}
                        </div>
                    </div>
                    <%--<div class="layui-inline">--%>
                        <%--<label class="layui-form-label">分销佣金计算公式：</label>--%>
                        <%--<div class="layui-input-inline">--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${not empty cooperationInfo.commissionAmount && cooperationInfo.commissionAmount!=0 && not empty cooperationInfo.commissionRatio && cooperationInfo.commissionRatio!=0}">--%>
                                    <%--${sdk:mf2(cooperationInfo.commissionAmount)}元&nbsp;&nbsp;+&nbsp;&nbsp;总价*${cooperationInfo.commissionRatio}%--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${not empty cooperationInfo.commissionAmount && cooperationInfo.commissionAmount!=0}">--%>
                                    <%--${sdk:mf2(cooperationInfo.commissionAmount)}元--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${not empty cooperationInfo.commissionRatio && cooperationInfo.commissionRatio!=0}">--%>
                                    <%--总价*${cooperationInfo.commissionRatio}%--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">项目合同：</label>
                        <div class="layui-input-inline">
                            ${cooperationInfo.htOaNo}
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目合同周期：</label>
                        <div class="layui-input-inline dateLifeInput">
                            ${sdk:ymd2(cooperationInfo.htDateStart)} 至 ${sdk:ymd2(cooperationInfo.htDateEnd)}
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">分销合同名称：</label>
                        <div class="layui-input-inline dateLifeInput textOverflow" title="${cooperationInfo.contractName}">
                            ${cooperationInfo.contractName}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">分销合同周期：</label>
                        <div class="layui-input-inline dateLifeInput">
                            ${cooperationInfo.dateLifeStart} 至 ${cooperationInfo.dateLifeEnd}
                        </div>
                    </div>
                </div>

                <%--
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">分销合同类别：</label>
                                        <div class="layui-input-inline">
                                            <c:choose>
                                                <c:when test="${cooperationInfo.htCategory eq '-7901965901473037886'}">
                                                    非模板合同
                                                </c:when>
                                                <c:when test="${cooperationInfo.htCategory eq '3198954431064198420'}">
                                                    模板合同
                                                </c:when>
                                                <c:otherwise>
                                                    特殊合同
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                            <%--        <div class="layui-inline">
                                        <label class="layui-form-label">分销合同类型：</label>
                                        <div class="layui-input-inline">
                                            ${cooperationInfo.htType}
                                        </div>
                                    </div>
                                </div>--%>
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">项目收入标的：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.incomeSubject}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">项目收入结算条件：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.commissionMemo}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">项目收入描述：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.commissionMemoRemark}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">项目合同返佣标准：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.rtnCommission}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">项目合同返佣结算条件：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.rtnCommissionMemo}
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">分销合作房源范围：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.cooperationRange}
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">成功销售的标准：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.succeedSell}
                        </div>
                    </div>
                </div>



                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">分销合同返佣结算条件：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto">
                            ${cooperationInfo.fyjstj}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">分销合同返佣结算标准：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.fyjsbz}
                        </div>
                    </div>
                </div>



                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">满足结算条件支付节点：</label>
                        <div class="layui-input-inline">
                            ${cooperationInfo.settlePayNum} <span>个工作日内支付</span>
                        </div>
                    </div>

                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">违约金额（万元）：</label>
                        <div class="layui-input-inline">
                            ${cooperationInfo.violateAmount}
                        </div>
                    </div>
                </div>

<!--                 <div class="layui-form-item"> -->
<!--                     <div class="layui-inline"> -->
<!--                         <label class="layui-form-label">分销合同版本：</label> -->
<!--                         <div class="layui-input-inline"> -->
<%--                             ${cooperationInfo.hteditionStr} --%>
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->

                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">分销合同签约类型：</label>
                        <div class="layui-input-inline">
                            ${cooperationInfo.signTypeNm}
                        </div>
                    </div>
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">分销合同类型：</label>
                        <div class="layui-input-inline">
                            <c:choose>
                                <c:when test="${cooperationInfo.htType eq '1671583364489561661'}">
                                    返佣
                                </c:when>
                                <c:when test="${cooperationInfo.htType eq '-1835969197405944893'}">
                                    垫佣
                                </c:when>
                                <c:otherwise>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">分销合同类别：</label>
                        <div class="layui-input-inline">
                            <c:choose>
                                <c:when test="${cooperationInfo.htCategory eq '3198954431064198420'}">
                                    模板合同
                                </c:when>
                                <c:when test="${cooperationInfo.htCategory eq '-7901965901473037886'}">
                                    非模板合同
                                </c:when>

                                <c:when test="${cooperationInfo.htCategory eq '-938620031608930158'}">
                                    特殊合同
                                </c:when>
                            </c:choose>
                        </div>
                    </div>


                    <c:if test="${cooperationInfo.htType=='-1835969197405944893'}">
                        <div class="layui-inline" style="width: auto;">
                            <label class="layui-form-label">垫佣比例：</label>
                            <div class="layui-input-inline">
                                    ${cooperationInfo.dybl}%
                            </div>
                        </div>
                    </c:if>
                </div>
                <c:if test="${cooperationInfo.signType=='29202'}">
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">经纪公司电子授权人：</label>
                        <div class="layui-input-inline">
                            ${cooperationInfo.companyEleUser}
                        </div>
                    </div>
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">经纪公司授权人电话：</label>
                        <div class="layui-input-inline">
                                ${cooperationInfo.companyEleTel}
                        </div>
                    </div>
                </div>
                </c:if>



                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">风险提示：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.riskWarning}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: auto;">
                        <label class="layui-form-label">备注：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            ${cooperationInfo.remark}
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">提交OA状态：</label>
                        <div class="layui-input-inline">
                            <c:if test="${cooperationInfo.formState==0}">
                                提交OA中
                            </c:if>
                            <c:if test="${cooperationInfo.formState==10}">
                                已提交
                            </c:if>
                            <c:if test="${cooperationInfo.formState==-10}">
                                <span style="color: red">提交失败</span>
                            </c:if>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">OA单号：</label>
                        <div class="layui-input-inline">
                            ${cooperationInfo.oaNo}
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">创建人：</label>
                        <div class="layui-input-inline">
                            ${(cooperationInfo.userCreateName)}
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">创建时间：</label>
                        <div class="layui-input-inline">
                            ${sdk:hmsfs(cooperationInfo.dateCreate)}
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">合同审核状态：</label>
                        <div class="layui-input-inline">
                            ${cooperationInfo.approveStateName}
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">合同审核通过时间：</label>
                        <div class="layui-input-inline">
                            <c:if test="${not empty cooperationInfo.performDate && !(cooperationInfo.performDate eq null)}">
                                ${sdk:hmsfs(cooperationInfo.performDate)}
                            </c:if>
                        </div>
                    </div>
                </div>
                <c:if test="${cooperationInfo.formState==-10}">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">提交失败原因：</label>
                        <div class="layui-input-inline" style="width: 750px;height: auto;">
                            <span style="color: red">${cooperationInfo.errmsg}</span>
                        </div>
                    </div>
                </div>
                </c:if>

            </div>
        </div>
    </div>

    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>附件</legend>
            </fieldset>
            <div class="fileContent" id="uploadContractImg">
                <div>合同</div><hr/>
                <div class="layui-upload-list upload_img_list">
                    <%--<c:forEach items="${cooperationInfo.fileList}" var="file">
                        <c:if test="${file.fileTypeId eq '1067'}">
                            <dd class="item_img" id="${file.fileRecordMainId}" data-id="${file.fileRecordMainId}">
                                <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" >
                                <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" />
                            </dd>
                        </c:if>
                    </c:forEach>--%>
                </div>
            </div>
            <div class="fileContent" id="uploadOtherImg">
                <div>其他</div><hr/>
                <div class="layui-upload-list upload_img_list">
                   <%-- <c:forEach items="${cooperationInfo.fileList}" var="file">
                        <c:if test="${file.fileTypeId eq '1068'}">
                            <dd class="item_img" id="${file.fileRecordMainId}" data-id="${file.fileRecordMainId}">
                                <img src="${file.fileAbbrUrl}" data-original="${file.fileUrl}" class="img" >
                                <input type="hidden" name="dzd_img[]" value="${file.fileAbbrUrl}" />
                            </dd>
                        </c:if>
                    </c:forEach>--%>
                </div>
            </div>

        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/cooperation/cooperationInfo.js?_v=${vs}"></script>
</body>
</html>
