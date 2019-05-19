<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Online-Pharmacy</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<style>
	<%@include file='../css/style.css' %>
    form{
        text-align: center;
    }
</style>
<body>
<div class="main">
    <div class="wrap">
    <jsp:include page="element/header.jsp"/>
	<div class="content">
		<h1>Welcome to Online-pharmacy!</h1>
        <p>Please, log in or create new account.</p>
        <form name="loginForm" method="POST" action="pharmacy">
            <input type="hidden" name="command" value="login" />
			Login:<br/>
            <input type="text" name="login" required />
            <br/>Password:<br/>
            <input type="password" name="password" required />
            <div class="error">
                <c:if test="${not empty sessionScope.error}">
                    <c:out value="${sessionScope.error}"/>
                </c:if>
            </div>
            <br/>
            <input id="button" type="submit" value="Log in"/>
			<br/>
        </form>
		<p>Don't have an account? <a href="pharmacy?command=register">Register now!</a></p>
		<br/>
	</div>
    </div>
	<jsp:include page="element/footer.jsp"/>
</div>
</body>
</html>