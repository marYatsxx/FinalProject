<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title><fmt:message key="site.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<style>
    <%@include file='../css/style.css' %>
    form {
        text-align: center;
    }
</style>
<body>
<div class="main">
    <div class="wrap">
        <%@include file="element/header.jspf"%>
        <div class="content">
            <h1><fmt:message key="homepage.welcome"/></h1>
            <p><fmt:message key="login.message"/></p>
            <form name="loginForm" method="POST" action="pharmacy">
                <input type="hidden" name="command" value="login"/>
                <fmt:message key="user.login"/> :<br/>
                <input type="text" name="login" required/><br/>
                <fmt:message key="user.password"/> :<br/>
                <input type="password" name="password" required/>
                <div class="error" style="text-align: center">
                    <c:if test="${not empty sessionScope.error}">
                        <fmt:message key="${sessionScope.error}"/>
                    </c:if>
                </div>
                <br/>
                <input id="button" type="submit" value="<fmt:message key="button.login"/>"/>
                <br/>
            </form>
            <p><fmt:message key="login.question"/><a href="pharmacy?command=viewRegister"> <fmt:message key="login.register"/></a></p>
            <br/>
        </div>
    </div>
    <%@include file="element/footer.jspf"%>
</div>
</body>
</html>