<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Online-Pharmacy</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
                <div class="article">
                    <div class="profile">
                        <h1>My Profile</h1>
                        <hr/>
                        <div class="info">
                            <p>Name: <c:out value="${sessionScope.name}"/></p>
                            <p>Surname: <c:out value="${sessionScope.surname}"/></p>
                            <p>Login: <c:out value="${sessionScope.login}"/></p>
                            <p>Role:
                                <c:choose>
                                    <c:when test="${sessionScope.user_role_id==1}">
                                        Pharmacist
                                    </c:when>
                                    <c:when test="${sessionScope.user_role_id==2}">
                                        Doctor
                                    </c:when>
                                    <c:when test="${sessionScope.user_role_id==3}">
                                        Client<br/><br/>
                                        Balance: <c:out value="${sessionScope.balance}"/>
                                    </c:when>
                                </c:choose>
                            </p>
                        </div>
                        <div class="buttons">
                            <c:if test="${sessionScope.user_role_id==3}">
                                <a href="pharmacy?command=register">Recharge the balance</a>
                                <input type="button" name="balance" value="Recharge the balance">
                                <br/><br/>
                            </c:if>
                            <a href="pharmacy?command=register">Edit profile</a>
                            <a href="pharmacy?command=register">Show history</a>
                        </div>
                    </div>
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
