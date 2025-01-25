<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../jsp/common/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商品库存管理页面</span>
    </div>
    <div class="search">
        <form method="post" action="${pageContext.request.contextPath }/stocklist.html">
            <input name="method" value="query" type="hidden">
            <input type="hidden" name="pageIndex" value="1"/>
            <span>商品编码：</span>
            <input name="queryProCode" type="text" value="${queryProCode }">

            <span>商品名称：</span>
            <input name="queryProName" type="text" value="${queryProName }">

            <input value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath }/stockadd.html">添加商品</a>
        </form>
    </div>
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">商品编码</th>
            <th width="20%">商品名称</th>
            <th width="10%">库存量</th>
            <th width="10%">商品售价</th>
            <th width="10%">商品进价</th>
            <th width="10%">生产厂家</th>
            <th width="10%">产地</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="stock" items="${page.list }" varStatus="status">
            <tr>
                <td>
                    <span>${stock.productcode }</span>
                </td>
                <td>
                    <span>${stock.productname }</span>
                </td>
                <td>
                    <span>${stock.inventory }</span>
                </td>
                <td>
                    <span>${stock.price}</span>
                </td>
                <td>
                    <span>${stock.purchaseprice}</span>
                </td>
                <td>
                    <span>${stock.manufacturer}</span>
                </td>
                <td>
                    <span>${stock.place}</span>
                </td>
                <td>
                        <%--                    <span><a class="viewProduct" href="javascript:;" proid=${product.id } proname=${product.productname }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>--%>
                    <span><a class="modifyStock" href="javascript:;" proid=${stock.id } proname=${stock.productname }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteStock" href="javascript:;" proid=${stock.id } proname=${stock.productname }><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/stocklist.js"></script>
