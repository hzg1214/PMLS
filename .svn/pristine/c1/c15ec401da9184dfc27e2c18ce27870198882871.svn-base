<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<style type="text/css">
/*     .tm{ */
/*         font-weight: bold; */
/*         font-size: 15px; */
/*         } */
    .da{
        margin-top: -20px;
        font-size: 12px;
        margin-left: 15px;
        }
    .titte{
        margin-top: 25px;
        text-align: center;
        font-size: 20px;
        margin-bottom: 20px;
        }
     
</style>

<div class="" role="main">
    <span style="position: absolute;right:20px;display:block"><a href="javascript:KeFuEvaluation.close();" class="btn btn-default">&times;</a></span>
    <div class="row" style="width: 912px; height: 650px;overflow-x:hidden;overflow-y:scroll">
        <div class="col-md-10 content" style="width: 90%;">
                <div class="page-content">
                    <h4>
                        <strong><span style="width: 818px;">门店测评</span></strong>
                    </h4>
                </div>
                <strong><span style="margin-left: 20px;">基本信息</span></strong>
<!--                 基本信息 -->
                <div style="margin-left: 0px;margin-right: -50px;">
                <table class="table-sammary">
                    <col style="width:130px;">
                    <col style="width:atuo;">
                    <col style="width:130px;">
                    <col style="width:atuo;">
                    <tr>
                        <td class="talabel">门店编号：</td>
                        <td>${storeNo}</td>
                        <td class="talabel">门店名称：</td>
                        <td>${storeData.storeName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">门店地址：</td>
                        <td>${storeData.addressDetail}</td>
                        <td class="talabel">公司名称：</td>
                        <td>${storeData.companyName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">调查时间：</td>
                        <td colspan="3">${evaluationData.data.wjdcjd}</td>
                    </tr>
                    <tr>
                        <td class="talabel">门店现状：</td>
                        <td>${evaluationData.data.storeStatusStr}</td>
                        <td class="talabel">谈访对象：</td>
                        <td>${evaluationData.data.dcObjectName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">联系方式：</td>
                        <td>${evaluationData.data.dcObjectTel}</td>
                    </tr>
                    <tr>
                        <td class="talabel">VI合规性&nbsp;&nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="talabel">店招：</td>
                        <td>${evaluationData.data.dzStatusStr}</td>
                        <td class="talabel">背景墙：</td>
                        <td>${evaluationData.data.bjqStatusStr}</td>
                    </tr>
                    <tr>
                        <td class="talabel">KT板：</td>
                        <td>${evaluationData.data.ktbStatusStr}</td>
                        <td class="talabel">其他：</td>
                        <td>${evaluationData.data.otherRemark}</td>
                    </tr>
                    <tr>
                        <td class="talabel">备注：</td>
                        <td>${evaluationData.data.comments}</td>
                    </tr>
                    <tr>
                        <td class="talabel">调查人：</td>
                        <td>${evaluationData.data.userName}</td>
                        <td class="talabel">创建时间：</td>
                        <td>${sdk:ymd2(evaluationData.data.dateCreate)}</td>
                    </tr>
                </table>
                </div>
                <strong>
                	<span style="margin-left: 20px;">门店测评</span>
                </strong>
                <div style="margin-left: 0px;margin-right: -50px;">
                    <table class="table-sammary">
                        <col style="width:130px;">
                        <col style="width:atuo;">
                        <col style="width:130px;">
                        <col style="width:atuo;">
                        <c:forEach items="${evaluationData.evaluationList}" var="list" varStatus="status" begin="0" step="2">
                            <tr>
                                <td class="talabel">${list.cpTitleStr}：</td>
                                <td>${list.cpAnswerStr}(${list.cpScore}分)</td>
                                <c:set var="Continue" value="0"/>
                                <c:forEach items="${evaluationData.evaluationList}" var="valueList" varStatus="valStatus" begin="${status.index+1}">
                                    <c:if test="${Continue==0}">
                                        <td class="talabel">${valueList.cpTitleStr}：</td>
                                        <td>${valueList.cpAnswerStr}(${valueList.cpScore}分)</td>
                                        <c:set var="Continue" value="1"/>
                                    </c:if>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td class="talabel">测评总分：</td>
                            <td>${evaluationData.data.cpTotalScore}分</td>
                        </tr>
                    </table>
                </div>
            </div>
    </div>
</div>