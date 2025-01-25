<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../jsp/common/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>会员管理页面 >> 会员增加页面</span>
    </div>
    <div class="providerAdd">
        <form id="productForm" name="productForm" method="post" action="${pageContext.request.contextPath }/memberAdd.html">
            <input type="hidden" name="method" value="add">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <div class="">
                <label for="membername">会员名称：</label>
                <input type="text" name="membername" id="membername" value="">
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="phone">会员电话：</label>
                <input type="text" name="phone" id="phone" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="age">年龄：</label>
                <input type="text" name="age" id="age" value="">
            </div>
            <div>
                <label for="endtime">会员期限：</label>
                <input type="text" name="endtime" id="endtime" value="">
                <font color="red"></font>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/memberadd.js"></script>
