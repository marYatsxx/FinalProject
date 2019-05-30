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
                    <div class="profile">
                        <h1><fmt:message key="button.edit.profile"/></h1>
                        <hr/>
                        <form name="edit_profile" method="post" action="pharmacy">
                            <input type="hidden" name="command" value="editProfile"/>
                            <div class="form-wrap">
                                <div class="field">
                                    <label for="name"><fmt:message key="user.name"/></label>
                                    <input type="text" name="name" value="<c:out value="${sessionScope.name}"/>">
                                </div>
                                <div class="field">
                                    <label for="surname"><fmt:message key="user.surname"/></label>
                                    <input type="text" name="surname" value="<c:out value="${sessionScope.surname}"/>">
                                </dic>
                                    <div class="field">
                                        <label for="login"><fmt:message key="user.login"/></label>
                                        <input type="text" name="login" value="<c:out value="${sessionScope.login}"/>">
                                    </div>
                                    <div class="error">
                                        <c:if test="${not empty sessionScope.login_error}">
                                            <c:out value="${sessionScope.login_error}"/>
                                        </c:if>
                                    </div>
                                    <div class="field">
                                        <label for="password"><fmt:message key="user.password"/></label>
                                        <input type="password" name="password"
                                               value="<c:out value="${sessionScope.user.getPassword()}"/>" required>
                                    </div>
                                    <div class="field">
                                        <label for="repeat_password"><fmt:message key="editprofile.repeat"/></label>
                                        <input type="password" name="repeat_password" required></div>
                                    <div class="error">
                                        <c:if test="${not empty sessionScope.password_error}">
                                            <c:out value="${sessionScope.password_error}"/>
                                        </c:if>
                                    </div>
                                </div>
                                <br/>
                                <div class="buttons">
                                    <input id="button" type="submit" name="save" value="<fmt:message key="button.save"/>">
                                    <br/><br/>
                                </div>
                            </div>
                        </form>
                    </div>
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
