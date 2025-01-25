<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../jsp/common/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>会员管理页面</span>
    </div>
    <div class="search">
        <form method="post" action="${pageContext.request.contextPath }/memberlist.html">
            <input name="method" value="query" type="hidden">
            <input type="hidden" name="pageIndex" value="1"/>
            <span>会员名称：</span>
            <input name="queryProName" type="text" value="${queryProName }">

            <span>会员电话：</span>
            <input name="queryProCode" type="text" value="${queryProCode }">

            <input value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath }/memberadd.html">添加会员</a>
        </form>
    </div>

    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">会员名称</th>
            <th width="20%">会员电话</th>
            <th width="10%">年龄</th>
            <th width="20%">创建时间</th>
            <th width="20%">会员期限(天)</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="member" items="${page.list }" varStatus="status">
            <tr>
                <td>
                    <span>${member.membername }</span>
                </td>
                <td>
                    <span>${member.phone }</span>
                </td>
                <td>
                    <span>${member.age}</span>
                </td>
                <td>
                    <fmt:formatDate value="${member.createtime}" pattern="yyyy-MM-dd"/>
                </td>
                <td>
                    <span>${member.endtime}</span>
                </td>
                <td>
                        <%--                    <span><a class="viewProduct" href="javascript:;" proid=${product.id } proname=${product.productname }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>--%>
                    <span><a class="modifyMember" href="javascript:;" proid=${member.id } proname=${member.membername }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteMember" href="javascript:;" proid=${member.id } proname=${member.membername }><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" id="totalPageCount" value="${page.total}"/>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${page.count}"/>
        <c:param name="currentPageNo" value="${page.index}"/>
        <c:param name="totalPageCount" value="${page.total}"/>
    </c:import>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain" >
            <p>你确定要删除该商品吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="../jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/memberlist.js"></script>
