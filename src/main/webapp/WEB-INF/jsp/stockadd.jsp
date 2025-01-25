<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../jsp/common/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>库存管理页面 >> 库存添加页面</span>
    </div>
    <div class="providerAdd">
        <form id="productForm" name="productForm" method="post" action="${pageContext.request.contextPath }/stockAdd.html">
            <input type="hidden" name="method" value="add">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <div class="">
                <label for="productcode">商品编码：</label>
                <input type="text" name="productcode" id="productcode" value="">
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="productname">商品名称：</label>
                <input type="text" name="productname" id="productname" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="inventory">库存量：</label>
                <input type="text" name="inventory" id="inventory" value="">
            </div>
            <div>
                <label for="price">商品售价：</label>
                <input type="text" name="price" id="price" value="">
                <font color="red"></font>

            </div>
            <div>
                <label for="purchaseprice">商品进价：</label>
                <input type="text" name="purchaseprice" id="purchaseprice" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="manufacturer">生产厂家：</label>
                <input type="text" name="manufacturer" id="manufacturer" value="">
            </div>
            <div>
                <label for="place">产地：</label>
                <input type="text" name="place" id="place" value="">
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/stockadd.js"></script>
