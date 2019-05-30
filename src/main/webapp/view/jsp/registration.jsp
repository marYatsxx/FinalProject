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
    <%@include file='../../view/css/style.css' %>
</style>
<body>
<div class="main">
    <div class="wrap">
        <%@include file="element/header.jspf"%>
        <div class="content">
            <div class="content_resize">
                <div class="mainbar">
                    <h1><fmt:message key="register.title"/></h1>
                    <form method="post" action="pharmacy">
                        <div class="form-wrap">
                            <input type="hidden" name="command" value="register">
                            <div class="field">
                                <label for="login"><fmt:message key="user.login"/></label>
                                <input type="text" name="login" required>
                            </div>
                            <div class="error">
                                <c:if test="${not empty sessionScope.registration_error}">
                                    <fmt:message key="${sessionScope.registration_error}"/>
                                </c:if>
                            </div>

                            <div class="field">
                                <label for="password"><fmt:message key="user.password"/></label>
                                <input type="password" name="password" required>
                            </div>
                            <div class="field">
                                <label for="repeat_password"><fmt:message key="editprofile.repeat"/></label>
                                <input type="password" name="repeat_password" required>
                            </div>
                            <div class="error">
                                <c:if test="${not empty sessionScope.password_error}">
                                    <fmt:message key="${sessionScope.password_error}"/>
                                </c:if>
                            </div>
                            <div class="field">
                                <label for="name"><fmt:message key="user.name"/></label>
                                <input type="text" name="name" required>
                            </div>
                            <div class="field">
                                <label for="surname"><fmt:message key="user.surname"/></label>
                                <input type="text" name="surname" required>
                            </div>
                            <c:if test="${sessionScope.user_role_id==1}">
                                <div class="field">
                                    <label for="user_role_id"><fmt:message key="user.role"/></label>
                                    <select name="user_role_id" required>
                                        <option><fmt:message key="role.option"/></option>
                                        <option value="admin"><fmt:message key="user.role.admin"/></option>
                                        <option value="doctor"><fmt:message key="user.role.doctor"/></option>
                                        <option value="pharmacist"><fmt:message key="user.role.pharmacist"/></option>
                                    </select>
                                </div>
                            </c:if>
                            <br/>
                        </div>
                        <br/>
                        <div class="submit_button">
                            <input id="button" type="submit" value="<fmt:message key="button.register"/>">
                        </div>
                    </form>
                </div>
                <%@include file="element/menu.jspf"%>
                <div class="clr"></div>
            </div>
        </div>
    </div>
    <%@include file="element/footer.jspf"%>
</div>
</body>
</html>