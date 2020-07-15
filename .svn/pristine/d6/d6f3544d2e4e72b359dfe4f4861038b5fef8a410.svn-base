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
    <span style="position: absolute;right:20px;display:block"><a href="javascript:KeFuInvested.close();" class="btn btn-default">&times;</a></span>
    <div class="row" style="width: 912px; height: 650px;overflow-x:hidden;overflow-y:scroll">
        <div class="col-md-10 content" style="width: 90%;">
                <div class="page-content">
                    <h4>
                        <strong><span style="width: 818px;">满意度调查</span></strong>
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
                        <td colspan="3">${surveyData.data.wjdcjd}</td>
                    </tr>
                    <tr>
                        <td class="talabel">门店现状：</td>
                        <td>${surveyData.data.storeStatusStr}</td>
                        <td class="talabel">谈访对象：</td>
                        <td>${surveyData.data.dcObjectName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">联系方式：</td>
                        <td>${surveyData.data.dcObjectTel}</td>
                    </tr>
                    <tr>
                        <td class="talabel">VI合规性&nbsp;&nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="talabel">店招：</td>
                        <td>${surveyData.data.dzStatusStr}</td>
                        <td class="talabel">背景墙：</td>
                        <td>${surveyData.data.bjqStatusStr}</td>
                    </tr>
                    <tr>
                        <td class="talabel">KT板：</td>
                        <td>${surveyData.data.ktbStatusStr}</td>
                        <td class="talabel">其他：</td>
                        <td>${surveyData.data.otherRemark}</td>
                    </tr>
                    <tr>
                        <td class="talabel">备注：</td>
                        <td>${surveyData.data.comments}</td>
                    </tr>
                    <tr>
                        <td class="talabel">调查人：</td>
                        <td>${surveyData.data.userName}</td>
                        <td class="talabel">创建时间：</td>
                        <td>${sdk:ymd2(surveyData.data.dateCreate)}</td>
                    </tr>
                </table>
                </div>
                <strong>
                	<span style="margin-left: 20px;">问卷调查</span>
                </strong>
<!--                 问卷调查按类别分类得分列表 -->
				<div style="margin-left: 58px;margin-right: -40px;padding-top: 10px;">
                <table id="keFuWjTableId" class="table table-striped table-hover table-bordered">
                    <tr>
                        <th style="width:160px;">服务项目</th>
                        <th style="width:160px;">分数</th>
                    </tr>
                    <c:forEach items="${surveyData.flList}" var="flList">
                        <tr>
                            <td>${flList.wjfl}</td>
                            <td>${flList.wjflScore}</td>
                        </tr>
                    </c:forEach>
                    <tr>
	                       <td>综合得分</td>
	                       <td style="text-align: center">${surveyData.data.wjdcTotalscore}</td>
                    </tr>
                </table>
                </div>
<!--                 题目及答案列表 -->
				<div style="margin-left: 58px;margin-right: 50px;">
                <c:forEach items="${surveyData.tmList}" var="tmlist" varStatus="tmstatus">
                        <c:choose>
                            <c:when test="${tmlist.wjtmType=='25201'}">
                                <div>
                                    <div class="tm">
                                        ${tmlist.rowNo}.${tmlist.wjtmValue}
                                    </div>
                                    <br>
                                    <div class="da">
                                        <c:forEach items="${tmlist.daList}" var="dalist" varStatus="status">
                                        <div>
                                            <input type="radio" value="${status.index}" <c:if test="${dalist.checked==1}">checked</c:if> disabled>${dalist.wjxx}
                                        </div>
                                        </c:forEach>
                                    </div>
                                    <br>
                                </div>
                            </c:when>
                            <c:when test="${tmlist.wjtmType=='25202'}">
                                <div>
                                    <div class="tm">
                                            ${tmstatus.count}.${tmlist.wjtmValue}
                                    </div>
                                    <br>
                                    <div class="da">
                                        <c:forEach items="${tmlist.daList}" var="dalist" varStatus="status">
                                            <div>
                                                <input type="checkbox" value="${status}" <c:if test="${dalist.checked==1}">checked</c:if> disabled>${dalist.wjxx}
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <br>
                                </div>
                            </c:when>
                            <c:when test="${tmlist.wjtmType=='25203'}">
                                <div>
                                    <div class="tm">
                                            ${tmstatus.count}.${tmlist.wjtmValue}
                                    </div>
                                    <br>
                                    <div class="da">
                                        <div>
<%--                                             <input type="text" cols="10" rows="10" value="${tmlist.wjdacomments}" readonly style="margin: 0px; width: 710px; height: 60px;"> --%>
                                            <textarea class="textarea" readonly>${tmlist.wjdacomments}</textarea>
                                        </div>
                                    </div>
                                    <br>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                 </div>
            </div>
    </div>
</div>