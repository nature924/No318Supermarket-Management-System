<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../jsp/common/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商品管理页面 >> 商品修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="productForm" name="productForm" method="post" action="${pageContext.request.contextPath }/productmodify.html">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <input type="hidden" name="id" value="${product.id }"/>
            <div class="">
                <label for="productcode">商品编码：</label>
                <input type="text" name="productcode" id="productcode" value="${product.productcode }" readonly="readonly">
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="productname">商品名称：</label>
                <input type="text" name="productname" id="productname" value="${product.productname }">
                <font color="red"></font>
            </div>
            <div>
                <label for="price">商品售价：</label>
                <input type="text" name="price" id="price" value="${product.price }">
                <font color="red"></font>

            </div>
            <div>
                <label for="purchaseprice">商品进价：</label>
                <input type="text" name="purchaseprice" id="purchaseprice" value="${product.purchaseprice }">
                <font color="red"></font>
            </div>
            <div>
                <label for="manufacturer">生产厂家：</label>
                <input type="text" name="manufacturer" id="manufacturer" value="${product.manufacturer }">
            </div>
            <div>
                <label for="place">产地：</label>
                <input type="text" name="place" id="place" value="${product.place }">
            </div>
            <div class="providerAddBtn">
                <input type="button" name="add" id="add" value="保存">
                <input type="button" id="back" name="back" value="返回" >
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="../jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/productmodify.js"></script>
