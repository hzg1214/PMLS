<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div>

    <table class="table table-striped table-hover table-bordered">
        <thead>
        <colgroup>
            <col style="width:150px">
            <col style="width:100px">
            <col style="width:100px">
            <col style="width:400px">
            <col style="width:400px">
        </colgroup>
        <tr>
            <th colspan="3"></th>
            <th>当期合计</th>
            <th>总累计</th>
        </tr>
        </thead>
        <tbody>
        <tr tag="all" lag="hj">
            <th rowspan="5" style="vertical-align: middle"> 门店总数</th>
            <th colspan="2">合计</th>
            <td></td>
            <td></td>

        </tr>
        <tr tag="all" lag="fpmd">
            <th colspan="2">翻牌门店</th>
            <td></td>
            <td></td>
        </tr>
        <tr tag="all" lag="spmd">
            <th colspan="2">授牌门店</th>
            <td></td>
            <td></td>
        </tr>
        <tr tag="all" lag="spsq">
            <th colspan="2">授牌社区</th>
            <td></td>
            <td></td>
        </tr>
        <tr tag="all" lag="qtmd">
            <th colspan="2">其他门店</th>
            <td></td>
            <td></td>
        </tr>
        <c:if test="${ldFlag eq 1}">
            <tr tag="ldItem" lag="fpmd" item="cjmd">
                <th rowspan="15" style="vertical-align: middle">联动贡献</th>
                <th rowspan="5" style="vertical-align: middle">翻牌门店</th>
                <th>成交门店</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="fpmd" item="ddje">
                <th>大定金额</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="fpmd" item="cxje">
                <th>成销金额</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="fpmd" item="sqms">
                <th>税前毛收</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="fpmd" item="sqjs">
                <th>税前净收</th>
                <td></td>
                <td></td>
            </tr>

            <tr tag="ldItem" lag="spmd" item="cjmd">
                <th rowspan="5" style="vertical-align: middle">授牌门店</th>
                <th>成交门店</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="spmd" item="ddje">
                <th >大定金额</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="spmd" item="cxje">
                <th>成销金额</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="spmd" item="sqms">
                <th>税前毛收</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="spmd" item="sqjs">
                <th>税前净收</th>
                <td></td>
                <td></td>
            </tr>

            <tr tag="ldItem" lag="qtmd" item="cjmd">
                <th rowspan="5" style="vertical-align: middle">其他门店</th>
                <th>成交门店</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="qtmd" item="ddje">
                <th>大定金额</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="qtmd" item="cxje">
                <th>成销金额</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="qtmd" item="sqms">
                <th>税前毛收</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="ldItem" lag="qtmd" item="sqjs">
                <th>税前净收</th>
                <td></td>
                <td></td>
            </tr>
        </c:if>
        <c:if test="${gpFlag eq 1}">
            <tr tag="gpItem" lag="fpmd" item="hymd">
                <th rowspan="12" style="vertical-align: middle"> 公盘</th>
                <th rowspan="4" style="vertical-align: middle">翻牌门店</th>
                <th>会员门店</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="gpItem" lag="fpmd" item="hysf">
                <th>会员收费</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="gpItem" lag="fpmd" item="cjts">
                <th>成交套数</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="gpItem" lag="fpmd" item="cjsr">
                <th>成交收入</th>
                <td></td>
                <td></td>
            </tr>

            <tr tag="gpItem" lag="spmd" item="hymd">
                <th rowspan="4" style="vertical-align: middle">授牌门店</th>
                <th>会员门店</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="gpItem" lag="spmd" item="hysf">
                <th>会员收费</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="gpItem" lag="spmd" item="cjts">
                <th>成交套数</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="gpItem" lag="spmd" item="cjsr">
                <th>成交收入</th>
                <td></td>
                <td></td>
            </tr>

            <tr tag="gpItem" lag="qtmd" item="hymd">
                <th rowspan="4" style="vertical-align: middle">其他门店</th>
                <th>会员门店</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="gpItem" lag="qtmd" item="hysf">
                <th>会员收费</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="gpItem" lag="qtmd" item="cjts">
                <th>成交套数</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="gpItem" lag="qtmd" item="cjsr">
                <th>成交收入</th>
                <td></td>
                <td></td>
            </tr>

        </c:if>
        <c:if test="${yjFlag eq 1}">
            <tr tag="jyItem" lag="fpmd" item="jjmd">
                <th rowspan="12" style="vertical-align: middle"> 交易</th>
                <th rowspan="4" style="vertical-align: middle">翻牌门店</th>
                <th>进件门店</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="jyItem" lag="fpmd" item="qyts">
                <th>签约套数</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="jyItem" lag="fpmd" item="qysr">
                <th>签约收入</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="jyItem" lag="fpmd" item="jrsr">
                <th>金融收入</th>
                <td></td>
                <td></td>
            </tr>

            <tr tag="jyItem" lag="spmd" item="jjmd">
                <th rowspan="4" style="vertical-align: middle">授牌门店</th>
                <th>进件门店</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="jyItem" lag="spmd" item="qyts">
                <th>签约套数</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="jyItem" lag="spmd" item="qysr">
                <th>签约收入</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="jyItem" lag="spmd" item="jrsr">
                <th>金融收入</th>
                <td></td>
                <td></td>
            </tr>


            <tr tag="jyItem" lag="qtmd" item="jjmd">
                <th rowspan="4" style="vertical-align: middle">其他门店</th>
                <th>进件门店</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="jyItem" lag="qtmd" item="qyts">
                <th>签约套数</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="jyItem" lag="qtmd" item="qysr">
                <th>签约收入</th>
                <td></td>
                <td></td>
            </tr>
            <tr tag="jyItem" lag="qtmd" item="jrsr">
                <th>金融收入</th>
                <td></td>
                <td></td>
            </tr>
        </c:if>

        </tbody>

    </table>

</div>
