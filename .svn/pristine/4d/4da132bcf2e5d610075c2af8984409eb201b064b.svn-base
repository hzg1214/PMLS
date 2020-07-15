<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<style type="text/css">
    .tm{
        font-weight: bold;
        font-size: 15px;
        }
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


<script>

   /* $(function() {

        // 初始化查询
        RelateCompany.initList();
    });*/

    /** ***************************************************** */
    /*$.fn.scrollUnique = function() {
        return $(this).each(function() {
            var eventType = 'mousewheel';
            // 火狐是DOMMouseScroll事件
            if (document.mozHidden !== undefined) {
                eventType = 'DOMMouseScroll';
            }
            $(this).on(eventType, function(event) {
                // 一些数据
                var scrollTop = this.scrollTop,
                    scrollHeight = this.scrollHeight,
                    height = this.clientHeight;

                var delta = (event.originalEvent.wheelDelta) ? event.originalEvent.wheelDelta : -(event.originalEvent.detail || 0);

                if ((delta > 0 && scrollTop <= delta) || (delta < 0 && scrollHeight - height - scrollTop <= -1 * delta)) {
                    // IE浏览器下滚动会跨越边界直接影响父级滚动，因此，临界时候手动边界滚动定位
                    this.scrollTop = delta > 0? 0: scrollHeight;
                    // 向上滚 || 向下滚
                    event.preventDefault();
                }
            });
        });
    };

   $('#wjDiv').scrollUnique();*/

</script>



<!--  <div class="fs14 tanchuang-pannel " role="main"> -->
<div class="" role="main">

    <span style="position: absolute;right:20px;display:block"><a href="javascript:KeFuWj.close();" class="btn btn-default">&times;</a></span>

    <div class="row">
        <div class="page-content" style="margin-left: 15px;">
            <h4><strong>问卷预览</strong></h4>
            <div  id="wjDiv" style="width: 810px; height: 650px;overflow-x:hidden;overflow-y:scroll">
                <img src="${ctx}/meta/images/keFuWj.jpg" alt="客服问卷" width="780px" height="200px"/>
                <div class="titte">${KeFuWj.wjTitle}</div>
                <div style="margin-left: 40px;margin-right: 50px;">
                    <c:forEach items="${KeFuWj.wjDTmList}" var="tmlist" varStatus="tmstatus">
                        <c:choose>
                            <c:when test="${tmlist.wjtmType=='25201'}">
                                <div>
                                    <div class="tm">
                                        ${tmstatus.count}.${tmlist.wjtmValue}
                                    </div>
                                    <br>
                                    <div class="da">
                                        <c:forEach items="${tmlist.wjDDaList}" var="dalist" varStatus="status">
                                        <div>
                                            <input type="radio" value="${status.index}" disabled>${dalist.wjxx}
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
                                        <c:forEach items="${tmlist.wjDDaList}" var="dalist" varStatus="status">
                                            <div>
                                                <input type="checkbox" value="${status}" disabled>${dalist.wjxx}
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
                                            <input type="text" cols="10" rows="10" readonly style="margin: 0px; width: 710px; height: 60px;"/>
                                        </div>
                                    </div>
                                    <br>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    <!-- 单选框 radio name值必须相同-->
                    <%--<div>
                        <div class="tm">
                            性别：
                        </div>
                        <br>
                        <div class="da">
                            <div>
                                <input type="radio" value="1" disabled>男
                            </div>
                            <div>
                                <input type="radio" value="0" disabled>女
                            </div>
                        </div>
                        <br>
                    </div>


                    <!-- 多选框 checkbox name值必须相同并且加[]-->
                    <div>
                        <div class="tm">
                            爱好：
                        </div>
                        <br>
                        <div class="da">
                            <div>
                                <input type="checkbox" value="f" disabled>足球哒哒哒哒篮球大萨达多撒大大大多篮球大萨达多撒大大大多篮球大萨达多撒大大大多篮球大萨达多撒大大大多篮球大萨达多撒大大大多
                            </div>
                            <div>
                                <input type="checkbox" value="b" disabled>篮球大萨达多撒大大大多
                            </div>
                            <div>
                                <input type="checkbox" value="l" disabled>看书发的说法是都是法术
                            </div>
                            <div>
                                <input type="checkbox" value="f" disabled>足球哒哒哒哒
                            </div>
                            <div>
                                <input type="checkbox" value="b" disabled>篮球大萨达多撒大大大多
                            </div>
                            <div>
                                <input type="checkbox" value="l" disabled>看书发的说法是都是法术
                            </div>
                            <div>
                                <input type="checkbox" value="f" disabled>足球哒哒哒哒
                            </div>
                            <div>
                                <input type="checkbox" value="b" disabled>篮球大萨达多撒大大大多
                            </div>
                            <div>
                                <input type="checkbox" value="l" disabled>看书发的说法是都是法术
                            </div>
                            <div>
                                <input type="checkbox" value="f" disabled>足球哒哒哒哒
                            </div>
                            <div>
                                <input type="checkbox" value="b" disabled>篮球大萨达多撒大大大多
                            </div>
                            <div>
                                <input type="checkbox" value="l" disabled>看书发的说法是都是法术
                            </div>
                        </div>
                        <br>
                    </div>
                    <!-- 文本域 textarea -->
                    <div>
                        <div class="tm">
                            个人简介：
                        </div>
                        <br>
                        <div class="da">
                            <div>
                                <input type="text" cols="10" rows="10" readonly style="margin: 0px; width: 710px; height: 60px;"/>
                            </div>
                        </div>
                        <br>
                    </div>--%>
                </div>
            </div>
        </div>

    </div>
</div>