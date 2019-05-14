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
                <div class="form-wrap">
                        <h1>Registration</h1>
                        <form name="registrationForm" method="post" action="pharmacy">
                            <input type="hidden" name="command" value="register">
                            <div>
                                <label for="login">Login</label>
                                <input type="text" name="login" required>
                            </div>
                            <div class="error">
                                <c:if test="${not empty sessionScope.registration_error}">
                                    <c:out value="${sessionScope.registration_error}"/>
                                </c:if>
                            </div>
                            <div>
                                <label for="password">Password</label>
                                <input type="password" name="password" required>
                            </div>
                            <div>
                                <label for="repeat_password">Repeat password</label>
                                <input type="password" name="repeat_password" required>
                            </div>
                            <div class="error">
                                <c:if test="${not empty sessionScope.password_error}">
                                    <c:out value="${sessionScope.password_error}"/>
                                </c:if>
                            </div>
                            <div>
                                <label for="name">Name</label>
                                <input type="text" name="name" required>
                            </div>
                            <div>
                                <label for="surname">Surname</label>
                                <input type="text" name="surname" required>
                            </div>
                            <div>
                                <label for="user_role_id">Role</label>
                                <select name="user_role_id" required>
                                    <option >Choose user role</option>
                                    <option value="doctor">Doctor</option>
                                    <option value="client">Client</option>
                                </select>
                            </div>
                            <br/>
                            <div class="register"><input type="submit" value="Register"></div>
                        </form>
                    </div>
            </div>
            <jsp:include page="element/menu.jsp"/>
            <div class="clr"></div>
        </div>
    </div>
    </div>
    <jsp:include page="element/footer.jsp"/>
</div>
</body>
</html>