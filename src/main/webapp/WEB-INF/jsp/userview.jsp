<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>员工管理页面 >> 员工信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>员工编号：</strong><span>${user.userCode }</span></p>
            <p><strong>员工名称：</strong><span>${user.userName }</span></p>
            <p><strong>员工性别：</strong>
            	<span>
            		<c:if test="${user.gender == 1 }">男</c:if>
					<c:if test="${user.gender == 2 }">女</c:if>
				</span>
			</p>
            <p><strong>出生日期：</strong><span><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" /></span></p>
            <p><strong>员工电话：</strong><span>${user.phone }</span></p>
            <p><strong>员工地址：</strong><span>${user.address }</span></p>
            <p><strong>员工角色：</strong><span>${user.userRoleName }</span></p>
			<div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="返回" >
            </div>
        </div>
    </div>
</section>
<%@include file="../jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userview.js"></script>