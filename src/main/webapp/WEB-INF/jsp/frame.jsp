﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../jsp/common/head.jsp"%>
    <div class="right">
        <img class="wColck" src="${pageContext.request.contextPath }/images/clock.jpg" alt=""/>
        <div class="wFont">
            <h2>${sessionScope.USER_CODE.userName }</h2>
            <p>欢迎来到超市管理系统!</p>
        </div>
    </div>
</section>
<%@include file="../jsp/common/foot.jsp" %>
