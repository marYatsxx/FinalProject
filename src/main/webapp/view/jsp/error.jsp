<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Online-Pharmacy</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<style>
    <%@include file='../../view/css/style.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <jsp:include page="element/header.jsp"/>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <p>This is error page... It seems like... something happened<3</p>
                    <c:out value="${requestScope.error}"/>
                    <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}" />
                    <c:set var="message" value="${requestScope['javax.servlet.error.message']}" />
                    <c:if test="${not empty code}">
                        <h3>Error code: <c:out value="${code}" /></h3>
                    </c:if>
                    <c:if test="${not empty message}">
                        <h3><c:out value="${message}" /></h3>
                    </c:if>
                </div>
                <div class="clr"></div>
            </div>
        </div>
    </div>
    <jsp:include page="element/footer.jsp"/>
</div>
</body>
</html>